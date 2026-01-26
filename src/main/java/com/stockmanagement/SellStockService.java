package com.stockmanagement;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class SellStockService {

    private EntityManagerFactory emf;
    private BuyStockService buyStockService;

    public SellStockService(EntityManagerFactory emf, BuyStockService buyStockService) {
        this.emf = emf;
        this.buyStockService = buyStockService;
    }

    // ---------------- SELL STOCK ----------------
    public void sellStock(BuyStock buyStock, int quantity, double sellPrice) {
        if (buyStock == null) {
            System.out.println("❌ BuyStock not found.");
            return;
        }

        if (quantity > buyStock.getQuantity()) {
            System.out.println("❌ Not enough quantity to sell.");
            return;
        }

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            // Create sell record
            SellStock sell = new SellStock(buyStock, LocalDate.now(), sellPrice, quantity);
            em.persist(sell);

            // Reduce quantity in buy stock
            BuyStock managedBuy = em.find(BuyStock.class, buyStock.getBuyId());
            managedBuy.reduceQuantity(quantity);

            tx.commit();
            System.out.println("✅ Stock sold successfully: " + sell);

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // ---------------- LIST SOLD STOCKS ----------------
    public void listSoldStocks() {
        EntityManager em = emf.createEntityManager();
        try {
            List<SellStock> sells = em.createQuery("SELECT s FROM SellStock s", SellStock.class)
                    .getResultList();
            if (sells.isEmpty()) {
                System.out.println("No sold stocks found.");
            } else {
                System.out.println("\n--- SOLD STOCKS ---");
                sells.forEach(System.out::println);
            }
        } finally {
            em.close();
        }
    }

    // ---------------- SELL STOCK MENU ----------------
    public void sellStockMenu(Scanner sc) {
        int choice;
        do {
            System.out.println("\n--- SELL STOCK MENU ---");
            System.out.println("1. Sell Stock");
            System.out.println("2. List Sold Stocks");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    buyStockService.list();
                    System.out.print("Enter BuyStock ID to sell: ");
                    Long buyId = sc.nextLong();
                    sc.nextLine();

                    BuyStock buyStock = buyStockService.getBuyStockById(buyId);
                    if (buyStock == null) break;

                    System.out.print("Enter quantity to sell: ");
                    int qty = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter sell price: ");
                    double price = sc.nextDouble();
                    sc.nextLine();

                    sellStock(buyStock, qty, price);
                }
                case 2 -> listSoldStocks();
                case 3 -> System.out.println("Returning to main menu...");
                default -> System.out.println("❌ Invalid choice!");
            }
        } while (choice != 3);
    }
}
//doneeee