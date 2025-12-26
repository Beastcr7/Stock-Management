package StockManagement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SellStockService {

    private List<SellStock> sellStockList = new ArrayList<>();
    private BuyStockService buyStockService;

    public SellStockService(BuyStockService buyStockService) {
        this.buyStockService = buyStockService;
    }

    // ---------------- SELL STOCK MENU ----------------
    public void sellStockMenu(Scanner sc) {
        int choice;

        do {
            System.out.println("\n--- SELL STOCK MENU ---");
            System.out.println("1. Sell Stock");
            System.out.println("2. List Sold Stocks");
            System.out.println("3. Back");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    sellStock(sc);
                    break;

                case 2:
                    listSoldStocks();
                    break;

                case 3:
                    System.out.println("Returning...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 3);
    }

    // ---------------- SELL LOGIC ----------------
    private void sellStock(Scanner sc) {

        List<BuyStock> availableStocks = buyStockService.getBuyStocks();

        if (availableStocks.isEmpty()) {
            System.out.println("No stocks available to sell.");
            return;
        }

        System.out.println("\n--- AVAILABLE BUY STOCKS ---");
        for (BuyStock b : availableStocks) {
            System.out.println(
                    "BuyID: " + b.getBuyId() +
                            ", Stock: " + b.getStock().getSymbol() +
                            ", Qty: " + b.getQuantity() +
                            ", Buy Price: " + b.getBuyPrice()
            );
        }

        System.out.print("Enter Buy ID: ");
        Long buyId = sc.nextLong();

        BuyStock selected = null;
        for (BuyStock b : availableStocks) {
            if (b.getBuyId().equals(buyId)) {
                selected = b;
                break;
            }
        }

        if (selected == null) {
            System.out.println("BuyStock not found.");
            return;
        }

        System.out.print("Enter quantity to sell: ");
        int qty = sc.nextInt();

        if (qty > selected.getQuantity()) {
            System.out.println("Not enough quantity.");
            return;
        }

        System.out.print("Enter sell price: ");
        double price = sc.nextDouble();

        SellStock sellStock = new SellStock(
                (long) (sellStockList.size() + 1),
                selected,
                LocalDate.now(),
                price,
                qty
        );

        sellStockList.add(sellStock);
        selected.reduceQuantity(qty);

        System.out.println("Stock sold successfully.");
        System.out.println("Sell Amount: " + sellStock.getSellAmount());
    }

    // ---------------- LIST SOLD ----------------
    private void listSoldStocks() {
        if (sellStockList.isEmpty()) {
            System.out.println("No sold stocks.");
            return;
        }

        System.out.println("\n--- SOLD STOCKS ---");
        for (SellStock s : sellStockList) {
            System.out.println(
                    "SellID: " + s.getSellId() +
                            ", BuyID: " + s.getBuyStock().getBuyId() +
                            ", Stock: " + s.getBuyStock().getStock().getSymbol() +
                            ", Qty Sold: " + s.getQuantity() +
                            ", Sell Amount: " + s.getSellAmount()
            );
        }
    }
}
