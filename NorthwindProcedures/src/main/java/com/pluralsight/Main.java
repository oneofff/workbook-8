package com.pluralsight;

import com.pluralsight.dao.ObjectsDao;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final ObjectsDao objectsDao = new ObjectsDao();

    public static void main(String[] args) {
        customerOrderHistory();
        salesByYear();
        salesByCategory();
    }

    private static void salesByCategory() {
        System.out.println("Sales by Category");
        System.out.println("Enter a category name ");
        String category = scanner.nextLine();
        System.out.println("Enter year");
        String year = scanner.nextLine();

        objectsDao.getSalesByCategory(category, year)
                .forEach(System.out::println);
    }

    private static void salesByYear() {
        System.out.println("Sales by Year");
        System.out.println("Enter beginning year ");

        String year = scanner.nextLine();
        System.out.println("Enter end year");
        String endYear = scanner.nextLine();

        try {
            LocalDate startDate = LocalDate.of(Integer.parseInt(year), 1, 1);
            LocalDate endDate = LocalDate.of(Integer.parseInt(endYear), 12, 31);
            objectsDao.getSalesByYear(startDate, endDate)
                    .forEach(System.out::println);
        }catch (NumberFormatException e) {
            System.out.println("Invalid year format. Please enter a valid year.");
        }
    }

    private static void customerOrderHistory() {
        System.out.println("Customer Order History");
        System.out.println("Enter a customer ID to see their order history");
        String id = scanner.nextLine();
        objectsDao.getCustomerOrderHistory(id)
                .forEach(order -> System.out.printf("Product: %s, Total: %.2f%n", order.getProductName(), order.getTotal()));
    }
}