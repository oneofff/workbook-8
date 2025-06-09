package com.pluralsight;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/sakila");
        System.out.println(System.getenv("DB_USER"));
        System.out.println(System.getenv("DB_PASSWORD"));
        dataSource.setUser(System.getenv("DB_USER"));
        dataSource.setPassword(System.getenv("DB_PASSWORD"));

        System.out.println("Enter your favorite actor's last name:");
        Scanner scanner = new Scanner(System.in);
        String lastName = scanner.nextLine();

        try (
                var connection = dataSource.getConnection();
                var statement = connection.prepareStatement("""
                        SELECT title FROM film_actor fa
                                                JOIN film f on f.film_id = fa.film_id
                                                JOIN actor a on a.actor_id = fa.actor_id
                                               WHERE a.last_name = ?
                        """)
        ) {
            statement.setString(1, lastName);
            try (var resultSet = statement.executeQuery()) {
                if (!resultSet.isBeforeFirst()) {
                    System.out.println("No films found for actor " + lastName);
                    return;
                }
                System.out.println("Films featuring actor " + lastName + ":");
                while (resultSet.next()) {

                    System.out.println(resultSet.getString("title"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();


        }
    }
}