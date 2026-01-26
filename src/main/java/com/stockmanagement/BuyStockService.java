package com.stockmanagement;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class BuyStockService {

    private EntityManagerFactory emf;
    private StockService stockService;

    public BuyStockService(EntityManagerFactory emf, StockService stockService) {
        this.emf = emf;
        this.stockService = stockService;
    }

    // ---------------- ADD BUY STOCK ----------------
    public void add(Stock stock, int quantity, double price) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            BuyStock buy = new BuyStock(stock, LocalDate.now(), price, quantity);
            em.persist(buy);
            tx.commit();
            System.out.println("✅ Stock bought successfully: " + buy);
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // ---------------- LIST BUY STOCKS ----------------
    public void list() {
        EntityManager em = emf.createEntityManager();
        try {
            List<BuyStock> buys = em.createQuery("SELECT b FROM BuyStock b", BuyStock.class).getResultList();
            if (buys.isEmpty()) {
                System.out.println("No buy stock records found.");
            } else {
                System.out.println("\n--- BUY STOCK RECORDS ---");
                for (BuyStock b : buys) {
                    System.out.println(b);
                }
            }
        } finally {
            em.close();
        }
    }

    // ---------------- GET BUY STOCK BY ID ----------------
    public BuyStock getBuyStockById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            BuyStock buy = em.find(BuyStock.class, id);
            if (buy == null) System.out.println("❌ BuyStock not found");
            return buy;
        } finally {
            em.close();
        }
    }

    // ---------------- REDUCE QUANTITY AFTER SELL ----------------
    public void reduceQuantity(Long buyId, int qty) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            BuyStock buy = em.find(BuyStock.class, buyId);
            if (buy != null && buy.getQuantity() >= qty) {
                buy.reduceQuantity(qty);
                tx.commit();
                System.out.println("✅ Quantity reduced by " + qty);
            } else {
                System.out.println("❌ Not enough quantity or buy record not found");
                if (tx.isActive()) tx.rollback();
            }
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // ---------------- DELETE BUY STOCK ----------------
    public void delete(Long buyId) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            BuyStock buy = em.find(BuyStock.class, buyId);
            if (buy != null) {
                em.remove(buy);
                tx.commit();
                System.out.println("✅ BuyStock deleted successfully");
            } else {
                System.out.println("❌ BuyStock not found");
            }
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // ---------------- BUY STOCK MENU ----------------
    public void buyStockMenu(Scanner sc) {
        int choice;
        do {
            System.out.println("\n--- BUY STOCK MENU ---");
            System.out.println("1. Buy Stock");
            System.out.println("2. List Buy Stocks");
            System.out.println("3. Delete Buy Stock");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    stockService.list();
                    System.out.print("Enter Stock ID to buy: ");
                    Long stockId = sc.nextLong();
                    sc.nextLine();

                    Stock stock = stockService.getStockById(stockId);
                    if (stock == null) break;

                    System.out.print("Enter quantity: ");
                    int qty = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter buy price: ");
                    double price = sc.nextDouble();
                    sc.nextLine();

                    add(stock, qty, price);
                }
                case 2 -> list();
                case 3 -> {
                    System.out.print("Enter BuyStock ID to delete: ");
                    Long id = sc.nextLong();
                    sc.nextLine();
                    delete(id);
                }
                case 4 -> System.out.println("Returning to main menu...");
                default -> System.out.println("❌ Invalid choice!");
            }
        } while (choice != 4);
    }
}

//done