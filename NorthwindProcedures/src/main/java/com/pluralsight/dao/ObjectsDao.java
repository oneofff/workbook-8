package com.pluralsight.dao;

import com.pluralsight.model.CustomerOrderHistory;
import com.pluralsight.model.SalesByCategory;
import com.pluralsight.model.SalesByYear;

import javax.sql.DataSource;
import java.sql.Date;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class ObjectsDao {
    private final DataSource dataSource = MySqlConfig.getDataSource();

    public List<CustomerOrderHistory> getCustomerOrderHistory(String id) {
        String query = "{CALL CustOrderHist(?)}";
        try (var connection = dataSource.getConnection();
             var callableStatement = connection.prepareCall(query)) {
            callableStatement.setString(1, id);
            try (var resultSet = callableStatement.executeQuery()) {
                List<CustomerOrderHistory> orderHistory = new LinkedList<>();
                while (resultSet.next()) {
                    CustomerOrderHistory order = new CustomerOrderHistory(resultSet);
                    orderHistory.add(order);
                }
                return orderHistory;
            }
        } catch (Exception e) {
            throw new RuntimeException("Error fetching customer order history from database", e);
        }
    }

    public List<SalesByYear> getSalesByYear(LocalDate startDate, LocalDate endDate) {
        String query = "{CALL `Sales by Year`(?, ?)}";
        try (var connection = dataSource.getConnection();
             var callableStatement = connection.prepareCall(query)) {
            callableStatement.setDate(1, Date.valueOf(startDate));
            callableStatement.setDate(2, Date.valueOf(endDate));
            try (var resultSet = callableStatement.executeQuery()) {
                List<SalesByYear> salesByYearList = new LinkedList<>();
                while (resultSet.next()) {
                    SalesByYear salesByYear = new SalesByYear(resultSet);
                    salesByYearList.add(salesByYear);
                }
                return salesByYearList;
            }
        } catch (Exception e) {
            throw new RuntimeException("Error fetching sales by year from database", e);
        }
    }

    public List<SalesByCategory> getSalesByCategory(String category, String year) {
        String query = "{CALL SalesByCategory(?, ?)}";
        try (var connection = dataSource.getConnection();
             var callableStatement = connection.prepareCall(query)) {
            callableStatement.setString(1, category);
            callableStatement.setString(2, year);
            try (var resultSet = callableStatement.executeQuery()) {
                List<SalesByCategory> salesByCategoryList = new LinkedList<>();
                while (resultSet.next()) {
                    SalesByCategory salesByCategory = new SalesByCategory(resultSet);
                    salesByCategoryList.add(salesByCategory);
                }
                return salesByCategoryList;
            }
        } catch (Exception e) {
            throw new RuntimeException("Error fetching sales by category from database", e);
        }
    }
}
