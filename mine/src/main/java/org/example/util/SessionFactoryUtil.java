package org.example.util;

import org.example.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class SessionFactoryUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // Add annotated entity classes
                configuration.addAnnotatedClass(BaseEntity.class);
                configuration.addAnnotatedClass(Person.class);
                configuration.addAnnotatedClass(Employee.class);
                configuration.addAnnotatedClass(Resident.class);
                configuration.addAnnotatedClass(Apartment.class);
                configuration.addAnnotatedClass(Building.class);
                configuration.addAnnotatedClass(Company.class);

                // Build the service registry
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties())
                        .build();

                // Build the SessionFactory
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("There was an error building the SessionFactory.");
            }
        }
        return sessionFactory;
    }
}





//            configuration.addAnnotatedClass(Payment.class);
//            configuration.addAnnotatedClass(ApartmentPayment.class);
//            configuration.addAnnotatedClass(BuildingPayment.class);