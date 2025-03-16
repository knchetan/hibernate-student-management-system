package com.student.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
        * SessionFactoryProvider is a utility class that initializes and provides a 
        * singleton Hibernate SessionFactory based on the XML configuration (hibernate.cfg.xml).
        * This class helps in managing sessions for performing CRUD operations on the database.
        */
public class SessionFactoryProvider
{
        private static final SessionFactory sessionFactory = buildSessionFactory();

        /**
         * Builds the SessionFactory from hibernate.cfg.xml.
         *
         * @return the built SessionFactory.
         * @throws ExceptionInInitializerError if the SessionFactory creation fails.
         */
        private static SessionFactory buildSessionFactory()
        {
                try {
                        Configuration configuration = new Configuration();
                        configuration.configure("com/student/hibernate.cfg.xml");
                        return configuration.buildSessionFactory();
                } catch (Throwable ex) {
                        System.err.println("Initial SessionFactory creation failed: " + ex);
                        throw new ExceptionInInitializerError(ex);
                }
        }

        /**
         * Retrieves the singleton SessionFactory.
         *
         * @return the SessionFactory.
         */
        public static SessionFactory getSessionFactory()
        {
                return sessionFactory;
        }

        /**
         * Shuts down the SessionFactory, closing caches and connection pools.
         */
        public static void shutdown()
        {
                getSessionFactory().close();
        }
}
