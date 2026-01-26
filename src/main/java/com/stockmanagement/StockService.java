package com.stockmanagement;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class StockService {

    private EntityManagerFactory emf;
    private SectorService sectorService;

    public StockService(EntityManagerFactory emf, SectorService sectorService) {
        this.emf = emf;
        this.sectorService = sectorService;
    }

    // ---------------- ADD STOCK ----------------
    public void add(Stock stock) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(stock);
            tx.commit();
            System.out.println("✅ Stock added successfully");
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // ---------------- LIST STOCKS ----------------
    public void list() {
        EntityManager em = emf.createEntityManager();
        try {
            List<Stock> stocks = em.createQuery("SELECT s FROM Stock s", Stock.class).getResultList();
            System.out.println("\n--- AVAILABLE STOCKS ---");
            if (stocks.isEmpty()) {
                System.out.println("No stocks available.");
            } else {
                for (Stock s : stocks) {
                    System.out.println("ID: " + s.getStockId() +
                            " | Symbol: " + s.getSymbol() +
                            " | Company: " + s.getCompanyName() +
                            " | Sector: " + s.getSector().getSectorName() +
                            " | Price: " + s.getCurrentMarketPrice());
                }
            }
        } finally {
            em.close();
        }
    }

    // ---------------- GET STOCK BY ID ----------------
    public Stock getStockById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Stock stock = em.find(Stock.class, id);
            if (stock == null) System.out.println("❌ Stock not found");
            return stock;
        } finally {
            em.close();
        }
    }

    // ---------------- UPDATE STOCK ----------------
    public void update(Stock updatedStock) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Stock stock = em.find(Stock.class, updatedStock.getStockId());
            if (stock != null) {
                stock.setSymbol(updatedStock.getSymbol());
                stock.setCompanyName(updatedStock.getCompanyName());
                stock.setSector(updatedStock.getSector());
                stock.setCurrentMarketPrice(updatedStock.getCurrentMarketPrice());
                tx.commit();
                System.out.println("✅ Stock updated successfully");
            } else {
                System.out.println("❌ Stock not found");
            }
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // ---------------- DELETE STOCK ----------------
    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Stock stock = em.find(Stock.class, id);
            if (stock != null) {
                em.remove(stock);
                tx.commit();
                System.out.println("✅ Stock deleted successfully");
            } else {
                System.out.println("❌ Stock not found");
            }
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // ---------------- STOCK MENU ----------------
    public void stockMenu(Scanner sc) {
        int choice;
        do {
            System.out.println("\n--- STOCK MENU ---");
            System.out.println("1. Add Stock");
            System.out.println("2. List Stocks");
            System.out.println("3. Update Stock");
            System.out.println("4. Delete Stock");
            System.out.println("5. Back / Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Symbol: ");
                    String symbol = sc.nextLine();

                    System.out.print("Enter Company Name: ");
                    String company = sc.nextLine();

                    sectorService.list();
                    System.out.print("Enter Sector ID: ");
                    Long sectorId = sc.nextLong();
                    sc.nextLine();
                    Sector sector = sectorService.getSectorById(sectorId);
                    if (sector == null) break;

                    System.out.print("Enter Market Price: ");
                    BigDecimal price = sc.nextBigDecimal();
                    sc.nextLine();

                    add(new Stock(symbol, company, sector, price));
                }

                case 2 -> list();

                case 3 -> {
                    System.out.print("Enter Stock ID to update: ");
                    Long id = sc.nextLong();
                    sc.nextLine();

                    Stock stock = getStockById(id);
                    if (stock == null) break;

                    System.out.print("New Symbol (" + stock.getSymbol() + "): ");
                    String symbol = sc.nextLine();
                    if (!symbol.isBlank()) stock.setSymbol(symbol);

                    System.out.print("New Company Name (" + stock.getCompanyName() + "): ");
                    String company = sc.nextLine();
                    if (!company.isBlank()) stock.setCompanyName(company);

                    sectorService.list();
                    System.out.print("New Sector ID (" + stock.getSector().getSectorId() + "): ");
                    Long newSectorId = sc.nextLong();
                    sc.nextLine();
                    Sector newSector = sectorService.getSectorById(newSectorId);
                    if (newSector != null) stock.setSector(newSector);

                    System.out.print("New Market Price (" + stock.getCurrentMarketPrice() + "): ");
                    BigDecimal price = sc.nextBigDecimal();
                    sc.nextLine();
                    stock.setCurrentMarketPrice(price);

                    update(stock);
                }

                case 4 -> {
                    System.out.print("Enter Stock ID to delete: ");
                    Long id = sc.nextLong();
                    sc.nextLine();
                    delete(id);
                }

                case 5 -> System.out.println("Returning...");
                default -> System.out.println("Invalid choice!");
            }

        } while (choice != 5);
    }
}

//done