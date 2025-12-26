package StockManagement;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SectorService {

    private List<Sector> sectors = new ArrayList<>();

    // ---------- ADD ----------
    public void add(Sector sector) {
        sectors.add(sector);
        System.out.println("New sector added: " + sector.getSectorName());
    }

    // ---------- LIST ----------
    public void list() {
        if (sectors.isEmpty()) {
            System.out.println("No Available Sectors.");
            return;
        }

        System.out.println("\nAvailable Sectors:");
        for (Sector s : sectors) {
            System.out.println(
                    "ID: " + s.getSectorId() +
                            " | Name: " + s.getSectorName() +
                            " | Index: " + s.getCurrentIndex()
            );
        }
    }

    // ---------- FIND ----------
    public Sector getSectorById(Long id) {
        for (Sector s : sectors) {
            if (s.getSectorId().equals(id)) {
                return s;
            }
        }
        return null;
    }

    // ---------- UPDATE ----------
    public void update(Long id, String newName, BigDecimal newIndex) {
        Sector sector = getSectorById(id);
        if (sector != null) {
            sector.setSectorName(newName);
            sector.setCurrentIndex(newIndex);
            System.out.println("Sector updated successfully.");
        } else {
            System.out.println("Sector not found.");
        }
    }

    // ---------- DELETE ----------
    public void delete(Long id) {
        Sector sector = getSectorById(id);
        if (sector != null) {
            sectors.remove(sector);
            System.out.println("Sector deleted successfully.");
        } else {
            System.out.println("Sector not found.");
        }
    }

    // ---------- MENU ----------
    public void sectorMenu(Scanner sc) {
        int choice;

        do {
            System.out.println("\n--- Sector Menu ---");
            System.out.println("1. Add Sector");
            System.out.println("2. List Sectors");
            System.out.println("3. Update Sector");
            System.out.println("4. Delete Sector");
            System.out.println("5. Back");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1 -> {
                    System.out.print("Enter sector id: ");
                    Long id = sc.nextLong();

                    System.out.print("Enter sector name: ");
                    String name = sc.next();

                    System.out.print("Enter sector index: ");
                    BigDecimal index = sc.nextBigDecimal();

                    add(new Sector(id, name, index));
                }

                case 2 -> list();

                case 3 -> {
                    System.out.print("Enter sector id to update: ");
                    Long id = sc.nextLong();

                    System.out.print("Enter new name: ");
                    String name = sc.next();

                    System.out.print("Enter new index: ");
                    BigDecimal index = sc.nextBigDecimal();

                    update(id, name, index);
                }

                case 4 -> {
                    System.out.print("Enter sector id to delete: ");
                    Long id = sc.nextLong();
                    delete(id);
                }

                case 5 -> System.out.println("Returning to main menu...");

                default -> System.out.println("Invalid choice.");
            }

        } while (choice != 5);
    }
}
