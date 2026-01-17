
package com.stockmanagement;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("StockPU");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
