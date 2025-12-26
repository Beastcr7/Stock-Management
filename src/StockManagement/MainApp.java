package StockManagement;

import java.util.Scanner;

public class MainApp {
    public static void main(String [] args){

        Scanner sc = new Scanner(System.in);

        SectorService sectorService = new SectorService();
        StockService stockService = new StockService(sectorService);

        BuyStockService buyService = new BuyStockService();
        SellStockService sellService = new SellStockService(buyService);

        int choice;

        do { System.out.println("Main Menu :");
        System.out.println("Enter 1 for Sector :");
        System.out.println("Enter 2 for Stock :");
        System.out.println("Enter 3 for BuyStock :");
        System.out.println("Enter 4 for SellStock :");
        System.out.println("Back");
        System.out.println("Enter your choice :");


        choice = sc.nextInt();

     switch (choice){

         case 1 :
             sectorService.SectorMenu(sc);
             break;

         case 2:
             stockService.stockMenu(sc);
             break;

         case 3:
             buyService.buyStockMenu(sc);
             break;

         case 4:
             sellService.sellStockMenu(sc);
             break;

         case 5:
             System.out.println("Exiting application...");
             break;

         default:
             System.out.println("Invalid choice!");
     }

        } while (choice != 5);

        sc.close();
    }
}
