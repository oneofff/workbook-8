package com.pluralsight;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.pluralsight.model.Actor;

import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private final MysqlDataSource dataSource;

    private DataManager() {
        this.dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/sakila");
        System.out.println(System.getenv("DB_USER"));
        System.out.println(System.getenv("DB_PASSWORD"));
        dataSource.setUser(System.getenv("DB_USER"));
        dataSource.setPassword(System.getenv("DB_PASSWORD"));
    }

    public static DataManager getDataManager() {
        return DataManagerHolder.INSTANCE;
    }

    private static class DataManagerHolder {
        private static final DataManager INSTANCE = new DataManager();
    }


        public List<Actor> getActorsByLastName(String lastName) {
            try (var connection = dataSource.getConnection();
                 var statement = connection.prepareStatement("""
                         SELECT actor_id, first_name, last_name FROM actor
                         WHERE last_name = ?
                         """)) {
                statement.setString(1, lastName);
                try (var resultSet = statement.executeQuery()) {
                    if (!resultSet.isBeforeFirst()) {
                        System.out.println("No actors found with last name " + lastName);
                        return List.of();
                    }
                    List<Actor> actors = new ArrayList<>();
                    while (resultSet.next()) {
                        Actor actor = new Actor(resultSet);
                        actors.add(actor);
                    }
                    return actors;
                }
            } catch (Exception e) {
                System.out.println("Error retrieving actors: " + e.getMessage());
                return List.of();

            }
        }

}
