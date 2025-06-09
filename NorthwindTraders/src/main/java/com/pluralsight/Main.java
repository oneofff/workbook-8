package com.pluralsight;

import com.pluralsight.dao.ProductDao;

import java.util.Scanner;

public class Main {
    private static final ProductDao productDao = new ProductDao();

    public static void main(String[] args) {

        boolean running = true;
        while (running) {
            displayMenu();
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();


            switch (option) {
                case 1:
                    displayAllProducts();
                    break;
                case 2:
                    displayAllCustomers();
                    break;
                case 0:
                    running = false;
                    System.out.println("Exiting the application.");
                    break;
                default:
                    System.out.println("Invalid option selected.");
            }
        }
    }

    private static void displayAllCustomers() {
        productDao.getAllCustomers().forEach(customer -> {
            System.out.println(customer);
            System.out.println("========================================");
        });
    }

    private static void displayMenu() {
        System.out.println("1) Display all products\n" +
                "2) Display all customers\n" +
                "0) Exit\n" +
                "Select an option: ");
    }

    public static void displayAllProducts() {
        productDao.getAllProducts().forEach(product -> {
            System.out.println(product);
            System.out.println("========================================");
        });
    }
}
