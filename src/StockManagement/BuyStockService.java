package StockManagement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BuyStockService {

    // Storage (acts like database)
    private List<BuyStock> buyStockList = new ArrayList<>();

    // ---------------- ADD BUY STOCK ----------------
    public void add(Long id, Stock stock, double price, int qty) {
        BuyStock buyStock = new BuyStock(
                id,
                stock,
                LocalDate.now(),
                price,
                qty
        );
        buyStockList.add(buyStock);
    }

    // ---------------- LIST BUY STOCKS ----------------
    public List<BuyStock> list() {
        return buyStockList;
    }

    // ---------------- DELETE BUY STOCK ----------------
    public boolean deleteBuyStock(Long buyId) {
        return buyStockList.removeIf(b -> b.getBuyId().equals(buyId));
    }

    // ---------------- BUY STOCK MENU ----------------
    public void buyStockMenu(Scanner sc) {
        int choice;

        do {
            System.out.println("\n--- BUY STOCK MENU ---");
            System.out.println("1. Add Buy Stock");
            System.out.println("2. List Buy Stocks");
            System.out.println("3. Delete Buy Stock");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter Buy ID: ");
                    Long id = sc.nextLong();

                    // Temporary stock for now; will integrate StockService later
                    Stock stock = new Stock(1L, "NABIL", "Nabil Bank", null, 500);

                    System.out.print("Enter Buy Price: ");
                    double price = sc.nextDouble();

                    System.out.print("Enter Quantity: ");
                    int qty = sc.nextInt();

                    add(id, stock, price, qty);
                    System.out.println("✅ BuyStock added successfully.");
                    break;

                case 2:
                    System.out.println("\n--- LIST OF BOUGHT STOCKS ---");
                    if (buyStockList.isEmpty()) {
                        System.out.println("No buy stocks available.");
                    } else {
                        for (BuyStock b : buyStockList) {
                            System.out.println(
                                    "BuyID: " + b.getBuyId() +
                                            ", Stock: " + b.getStock().getSymbol() +
                                            ", Qty: " + b.getQuantity() +
                                            ", Buy Price: " + b.getBuyPrice() +
                                            ", Date: " + b.getBuyDate()
                            );
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter Buy ID to delete: ");
                    Long delId = sc.nextLong();
                    boolean removed = deleteBuyStock(delId);
                    System.out.println(removed ? "✅ BuyStock deleted." : "❌ BuyStock not found.");
                    break;

                case 4:
                    System.out.println("Returning to main menu...");
                    break;

                default:
                    System.out.println("❌ Invalid choice!");
            }

        } while (choice != 4);
    }

    // ---------------- GETTER (for SellStock integration later) ----------------
    public List<BuyStock> getBuyStocks() {
        return buyStockList;
    }
}
