package com.theuz.model.db;

import com.theuz.model.db.exceptions.DbException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DB {
    private static EntityManagerFactory emf = null;
    private static EntityManager em = null;

    public static EntityManager getConnection() {
        if (em == null) {
            try {
                emf = Persistence.createEntityManagerFactory("jpa-test-unit");
                EntityManager em = emf.createEntityManager();
                return em;
            } catch (RuntimeException e) {
                throw new DbException(e.getMessage());
            }
        }
        return em;
    }
}
