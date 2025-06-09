package com.pluralsight;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.pluralsight.model.Actor;
import com.pluralsight.model.Film;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final DataManager dataManager = DataManager.getDataManager();

    public static void main(String[] args) {
        exercise7();
        exercise8_1();
        exercise8_2();
    }

    private static void exercise8_2() {
        System.out.println("Enter your actor id:");
        Scanner scanner = new Scanner(System.in);
        int actorId = scanner.nextInt();
        scanner.close();
        List<Film> actors = dataManager.getFilmsByActorId(actorId);
        if (actors.isEmpty()) return;
        System.out.println("Films featuring actor with id " + actorId + ":");
        for (Film film : actors) {
            System.out.println("-".repeat(20));
            System.out.println(film);
        }
    }

    private static void exercise8_1() {
        System.out.println("Enter your favorite actor's last name:");
        Scanner scanner = new Scanner(System.in);
        String lastName = scanner.nextLine();
        List<Actor> smith = dataManager.getActorsByLastName(lastName);
        if (smith.isEmpty()) return;
        System.out.println("Actors with last name" + lastName + ":");
        for (Actor actor : smith) {
            System.out.println("-".repeat(20));
            System.out.println(actor);
        }


    }

    private static void exercise7() {
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