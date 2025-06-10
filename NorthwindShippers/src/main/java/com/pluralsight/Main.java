package com.pluralsight;

import com.pluralsight.dao.ShippersDao;
import com.pluralsight.model.Shipper;

import java.util.Scanner;

public class Main {

    private static final ShippersDao shippersDao = new ShippersDao();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        saveNewShipper();
        printAllShippers();
        changeShipperPhone();
        printAllShippers();
        deleteShipper();
        printAllShippers();
    }

    private static void deleteShipper() {
        System.out.println("Delete shipper");
        System.out.println("Enter shipper ID:");

        int shipperId = scanner.nextInt();
        if (shipperId <= 3) {
            throw new RuntimeException("Cannot delete shipper with ID " + shipperId + ". This shipper is a default shipper and cannot be deleted.");
        }
        shippersDao.deleteShipper(shipperId);
        System.out.println("Shipper deleted successfully.");
    }

    private static void changeShipperPhone() {
        System.out.println("Change shipper phone");
        System.out.println("Enter shipper ID:");
        int shipperId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter new phone number:");
        String newPhone = scanner.nextLine();
        shippersDao.updateShipperPhone(shipperId, newPhone);
        System.out.println("Shipper phone updated successfully.");
    }

    private static void printAllShippers() {
        System.out.println("All shippers:");
        shippersDao.getAllShippers().forEach(shipper -> {
            System.out.println(shipper);
            System.out.println("========================================");
        });
    }

    private static void saveNewShipper() {
        System.out.println("Add new shipper");
        Shipper shipper = new Shipper();
        System.out.println("Enter shipper company name:");
        shipper.setCompanyName(scanner.nextLine());
        System.out.println("Enter shipper phone:");
        shipper.setPhone(scanner.nextLine());
        int saveShipperId = shippersDao.saveShipper(shipper);
        System.out.println("Shipper saved with ID: " + saveShipperId);
    }
}