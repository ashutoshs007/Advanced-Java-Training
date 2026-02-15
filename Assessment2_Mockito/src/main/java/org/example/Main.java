package org.example;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n==== RESTAURANT MENU ====");
            System.out.println("1. Add Menu Item");
            System.out.println("2. View All Items");
            System.out.println("3. Update Price");
            System.out.println("4. Delete Item");
            System.out.println("5. Exit");

            System.out.print("Choose option: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1 -> addItem();
                case 2 -> viewItems();
                case 3 -> updatePrice();
                case 4 -> deleteItem();
                case 5 -> {
                    HibernateUtil.getSessionFactory().close();
                    System.exit(0);
                }
            }
        }
    }

    static void addItem() {

        sc.nextLine();

        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter price: ");
        double price = sc.nextDouble();

        sc.nextLine();

        System.out.print("Enter category: ");
        String category = sc.nextLine();

        System.out.print("Available (true/false): ");
        boolean available = sc.nextBoolean();

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        MenuItem item = new MenuItem(name, price, category, available);
        session.persist(item);

        tx.commit();
        session.close();

        System.out.println("✅ Item Added!");
    }

    static void viewItems() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        List<MenuItem> items =
                session.createQuery("from MenuItem", MenuItem.class).list();

        items.forEach(i ->
                System.out.println(i.getId() + " | " + i.getName() + " | " + i.getPrice()));

        session.close();
    }

    static void updatePrice() {

        System.out.print("Enter ID: ");
        int id = sc.nextInt();

        System.out.print("New Price: ");
        double price = sc.nextDouble();

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        MenuItem item = session.get(MenuItem.class, id);

        if (item != null) {
            item.setPrice(price);
            session.merge(item);
            System.out.println("✅ Price Updated!");
        } else {
            System.out.println("❌ Item Not Found!");
        }

        tx.commit();
        session.close();
    }

    static void deleteItem() {

        System.out.print("Enter ID: ");
        int id = sc.nextInt();

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        MenuItem item = session.get(MenuItem.class, id);

        if (item != null) {
            session.remove(item);
            System.out.println("✅ Item Deleted!");
        } else {
            System.out.println("❌ Item Not Found!");
        }

        tx.commit();
        session.close();
    }
}
