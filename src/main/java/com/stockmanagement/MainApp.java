package com.stockmanagement;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;

public class MainApp {
    public static void main(String [] args){

        Scanner sc = new Scanner(System.in);

        // 1️⃣ Create EntityManagerFactory for JPA
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("StockPU");

        // 2️⃣ Initialize Services with EMF
        SectorService sectorService = new SectorService(emf);
        StockService stockService = new StockService(emf, sectorService);

        BuyStockService buyService = new BuyStockService(emf, stockService);
        SellStockService sellService = new SellStockService(emf , buyService);

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
                    sectorService.sectorMenu(sc);
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
//donee
