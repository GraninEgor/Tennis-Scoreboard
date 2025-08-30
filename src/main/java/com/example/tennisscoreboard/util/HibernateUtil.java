package com.example.tennisscoreboard.util;

import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
    @Getter
    private static final SessionFactory sessionFactory = buildFactory();

    private static SessionFactory buildFactory() {
        try {
            Configuration configuration = new Configuration().configure();
            return configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static void shutdown() {
        getSessionFactory().close();
    }


}
