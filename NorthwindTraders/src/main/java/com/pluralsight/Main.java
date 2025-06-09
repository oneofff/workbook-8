package com.pluralsight;

import com.pluralsight.dao.ObjectsDao;

import java.util.Scanner;

public class Main {
    private static final ObjectsDao dao = new ObjectsDao();

    public static void main(String[] args) {

        boolean running = true;
        while (running) {
            displayMenu();
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    displayAllProducts();
                    break;
                case 2:
                    displayAllCustomers();
                    break;
                case 3:
                    displayAllCategories();
                    displayProductsByCategory(scanner);
                case 0:
                    running = false;
                    System.out.println("Exiting the application.");
                    break;
                default:
                    System.out.println("Invalid option selected.");
            }
        }
    }

    private static void displayProductsByCategory(Scanner scanner) {
        System.out.println("Select a category to view products (or 0 to exit): ");
        int categoryOption = scanner.nextInt();
        scanner.nextLine();
        if (categoryOption == 0) return;
        System.out.println("Displaying products for category ID: " + categoryOption);
        dao.getProductsByCategory(categoryOption)
                .forEach(product -> {
                    System.out.println(product);
                    System.out.println("========================================");
                });
    }

    private static void displayAllCategories() {
        dao.getAllCategories().forEach(category -> {
            System.out.println(category);
            System.out.println("========================================");
        });
    }

    private static void displayAllCustomers() {
        dao.getAllCustomers().forEach(customer -> {
            System.out.println(customer);
            System.out.println("========================================");
        });
    }

    private static void displayMenu() {
        System.out.println("1) Display all products\n" +
                "2) Display all customers\n" +
                "3) Display all categories\n" +
                "0) Exit\n" +
                "Select an option: ");
    }

    public static void displayAllProducts() {
        dao.getAllProducts().forEach(product -> {
            System.out.println(product);
            System.out.println("========================================");
        });
    }
}
