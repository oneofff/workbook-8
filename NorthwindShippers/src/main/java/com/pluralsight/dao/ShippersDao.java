package com.pluralsight.dao;

import com.pluralsight.model.Shipper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class ShippersDao {

    private final DataSource dataSource = MySqlConfig.getDataSource();


    public int saveShipper(Shipper shipper) {
        try (var connection = dataSource.getConnection();
             var preparedStatement = connection.prepareStatement(
                     "INSERT INTO shippers (CompanyName, Phone) VALUES (?, ?)",
                     Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, shipper.getCompanyName());
            preparedStatement.setString(2, shipper.getPhone());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (!generatedKeys.next()) throw new RuntimeException("No ID obtained for new shipper");

            return generatedKeys.getInt(1);
        } catch (Exception e) {
            throw new RuntimeException("Error saving shipper to database", e);

        }
    }

    public List<Shipper> getAllShippers() {
        try (var connection = dataSource.getConnection();
             var preparedStatement = connection.prepareStatement("SELECT * FROM shippers");
             var resultSet = preparedStatement.executeQuery()) {

            List<Shipper> shippers = new java.util.ArrayList<>();
            while (resultSet.next()) {
                Shipper shipper = new Shipper(resultSet);
                shippers.add(shipper);
            }
            return shippers;
        } catch (Exception e) {
            throw new RuntimeException("Error fetching shippers from database", e);
        }
    }

    public void updateShipperPhone(int shipperId, String newPhone) {
        try (var connection = dataSource.getConnection();
             var preparedStatement = connection.prepareStatement(
                     "UPDATE shippers SET Phone = ? WHERE ShipperId = ?")) {

            preparedStatement.setString(1, newPhone);
            preparedStatement.setInt(2, shipperId);
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated == 0) {
                throw new RuntimeException("No shipper found with ID: " + shipperId);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error updating shipper phone in database", e);
        }
    }

    public void deleteShipper(int shipperId) {
        try (var connection = dataSource.getConnection();
             var preparedStatement = connection.prepareStatement(
                     "DELETE FROM shippers WHERE ShipperId = ?")) {

            preparedStatement.setInt(1, shipperId);
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted == 0) {
                throw new RuntimeException("No shipper found with ID: " + shipperId);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error deleting shipper from database", e);
        }
    }
}
