package StockManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StockService {

    private List<Stock> stocks;
    private SectorService sectorService;

    public StockService(SectorService sectorService) {
        this.stocks = new ArrayList<>();
        this.sectorService = sectorService;
    }

    // ADD STOCK
    public void add(Stock stock) {
        stocks.add(stock);
        System.out.println("Stock added successfully.");
    }

    // LIST STOCKS
    public void list() {
        if (stocks.isEmpty()) {
            System.out.println("No stocks available.");
            return;
        }

        System.out.println("Available Stocks:");
        for (Stock s : stocks) {
            System.out.println(
                    "ID: " + s.getStockId() +
                            ", Symbol: " + s.getSymbol() +
                            ", Company: " + s.getCompanyName() +
                            ", Sector: " + s.getSector().getSectorName() +
                            ", Price: " + s.getCurrentMarketPrice()
            );
        }
    }

    // FIND STOCK BY ID
    public Stock getStockById(Long id) {
        for (Stock s : stocks) {
            if (s.getStockId().equals(id)) {
                return s;
            }
        }
        System.out.println("Stock not found.");
        return null;
    }

    // UPDATE STOCK
    public void update(Long id, String symbol, String company,
                            Sector sector, double price) {

        Stock oldStock = getStockById(id);
        if (oldStock != null) {
            oldStock.setSymbol(symbol);
            oldStock.setCompanyName(company);
            oldStock.setSector(sector);
            oldStock.setCurrentMarketPrice(price);

            System.out.println("Stock updated successfully.");
        }
    }

    // DELETE STOCK
    public void delete(Long id) {
        Stock stock = getStockById(id);
        if (stock != null) {
            stocks.remove(stock);
            System.out.println("Stock deleted successfully.");
        }
    }

    // STOCK MENU
    public void stockMenu(Scanner sc) {
        int choice;

        do {
            System.out.println("\n--- STOCK MENU ---");
            System.out.println("1. Add Stock");
            System.out.println("2. List Stocks");
            System.out.println("3. Update Stock");
            System.out.println("4. Delete Stock");
            System.out.println("5. Back");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter Stock ID: ");
                    Long id = sc.nextLong();
                    sc.nextLine();

                    System.out.print("Enter Symbol: ");
                    String symbol = sc.nextLine();

                    System.out.print("Enter Company Name: ");
                    String company = sc.nextLine();

                    sectorService.list();
                    System.out.print("Enter Sector ID: ");
                    Long sectorId = sc.nextLong();

                    Sector sector = sectorService.getSectorById(sectorId);
                    if (sector == null) break;

                    System.out.print("Enter Market Price: ");
                    double price = sc.nextDouble();

                    add(new Stock(id, symbol, company, sector, price));
                    break;

                case 2:
                    list();
                    break;

                case 3:
                    System.out.print("Enter Stock ID to update: ");
                    Long upId = sc.nextLong();
                    sc.nextLine();

                    System.out.print("New Symbol: ");
                    String newSymbol = sc.nextLine();

                    System.out.print("New Company Name: ");
                    String newCompany = sc.nextLine();

                    sectorService.list();
                    System.out.print("New Sector ID: ");
                    Long newSectorId = sc.nextLong();

                    Sector newSector = sectorService.getSectorById(newSectorId);
                    if (newSector == null) break;

                    System.out.print("New Price: ");
                    double newPrice = sc.nextDouble();

                    update(upId, newSymbol, newCompany, newSector, newPrice);
                    break;

                case 4:
                    System.out.print("Enter Stock ID to delete: ");
                    delete(sc.nextLong());
                    break;

                case 5:
                    System.out.println("Returning...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 5);
    }
}
