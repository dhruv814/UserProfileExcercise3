package com.mycompany.hibernate_userprofile_example;

import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author dhruv
 */
public class AppMain {

    private static Scanner sc;
    private static EntityManagerFactory factory;
    private static EntityManager em;
    private static final String PERSISTENCE_UNIT_NAME = "com.mycompany_hibernate_userprofile_example_jar_1.0-SNAPSHOTPU";

    public AppMain() {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = factory.createEntityManager();
        sc = new Scanner(System.in);
    }

    public void retriveData() {
        TypedQuery<UserProfile> q = em.createQuery("SELECT u FROM UserProfile u", UserProfile.class);
        List<UserProfile> results = q.getResultList();
    }

    public void storeData() {
        UserProfile userProfile = new UserProfile();
        System.out.print("Enter Name:");
        String name = sc.next();
        System.out.print("Enter Email:");
        String email = sc.next();
        System.out.print("Enter Phone No. :");
        String phone = sc.next();

        userProfile.setName(name);
        userProfile.setEmail(email);
        userProfile.setPhone(phone);

        try {
            em.getTransaction().begin();
            em.persist(userProfile);
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void main(String[] args) {
        AppMain appMain = new AppMain();
        System.out.println("1: Retrieve data");
        System.out.println("2: Store data");
        try {
            System.out.print("Enter your choice:");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    appMain.retriveData();
                    break;
                case 2:
                    appMain.storeData();
                    break;
            }

        } finally {
            em.close();
        }
    }
}
