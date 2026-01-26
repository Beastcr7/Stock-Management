package com.stockmanagement;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class SectorService {


    public SectorService(EntityManagerFactory emf) {
    }

    // ---------------- ADD SECTOR ----------------
    public void add(Sector sector) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();               // start DB transaction
            em.persist(sector);       // INSERT into DB
            tx.commit();              // commit changes
            System.out.println("✅ Sector added successfully");
        } catch (Exception e) {
            tx.rollback();            // undo if error
            e.printStackTrace();
        } finally {
            em.close();               // close DB session
        }
    }

    // ---------------- LIST SECTORS ----------------
    public void list() {
        EntityManager em = JPAUtil.getEntityManager();

        List<Sector> sectors =
                em.createQuery("FROM Sector", Sector.class).getResultList();

        if (sectors.isEmpty()) {
            System.out.println("No sectors available.");
            return;
        }

        System.out.println("\n--- AVAILABLE SECTORS ---");
        for (Sector s : sectors) {
            System.out.println(
                    "ID: " + s.getSectorId() +
                            " | Name: " + s.getSectorName() +
                            " | Index: " + s.getCurrentIndex()
            );
        }

        em.close();
    }

    // ---------------- FIND BY ID ----------------
    public Sector getSectorById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        Sector sector = em.find(Sector.class, id);
        em.close();
        return sector;
    }

    // ---------------- UPDATE SECTOR ----------------
    public void update(Long id, String name, BigDecimal index) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Sector sector = em.find(Sector.class, id);
            if (sector == null) {
                System.out.println("❌ Sector not found");
                return;
            }

            sector.setSectorName(name);
            sector.setCurrentIndex(index);

            tx.commit();
            System.out.println("✅ Sector updated successfully");
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // ---------------- DELETE SECTOR ----------------
    public void delete(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Sector sector = em.find(Sector.class, id);
            if (sector == null) {
                System.out.println("❌ Sector not found");
                return;
            }

            em.remove(sector);    // DELETE from DB
            tx.commit();
            System.out.println("✅ Sector deleted successfully");
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // ---------------- SECTOR MENU ----------------
    public void sectorMenu(Scanner sc) {
        int choice;

        do {
            System.out.println("\n--- SECTOR MENU ---");
            System.out.println("1. Add Sector");
            System.out.println("2. List Sectors");
            System.out.println("3. Update Sector");
            System.out.println("4. Delete Sector");
            System.out.println("5. Back");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1 -> {
                    System.out.print("Enter Sector ID: ");
                    Long id = sc.nextLong();
                    sc.nextLine();

                    System.out.print("Enter Sector Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Sector Index: ");
                    BigDecimal index = sc.nextBigDecimal();

                    add(new Sector(id, name, index));
                }

                case 2 -> list();

                case 3 -> {
                    System.out.print("Enter Sector ID: ");
                    Long id = sc.nextLong();
                    sc.nextLine();

                    System.out.print("Enter New Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter New Index: ");
                    BigDecimal index = sc.nextBigDecimal();

                    update(id, name, index);
                }

                case 4 -> {
                    System.out.print("Enter Sector ID: ");
                    delete(sc.nextLong());
                }

                case 5 -> System.out.println("Returning...");

                default -> System.out.println("❌ Invalid choice");
            }

        } while (choice != 5);
    }
}

//doneee
