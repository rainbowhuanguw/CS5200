//By Youwei Yu
package aireats.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import aireats.model.*;

public class CategoriesDao {
    protected ConnectionManager connectionManager;
    private static CategoriesDao instance = null;

    protected CategoriesDao() {
        connectionManager = new ConnectionManager();
    }

    public static CategoriesDao getInstance() {
        if (instance == null) {
            instance = new CategoriesDao();
        }
        return instance;
    }

    public Categories create(Categories category) throws SQLException {
        String insertCategory = "INSERT INTO Categories(RestaurantId,Categories) VALUES(?,?)";
        Connection conn = null;
        PreparedStatement insertStmt = null;
        try {
            conn = connectionManager.getConnection();
            insertStmt = conn.prepareStatement(insertCategory);
            insertStmt.setString(1, category.getRestaurantId());
            StringBuilder categoryListStr = new StringBuilder();
            for(String c:category.getCategoryList()){
                categoryListStr.append(c+", ");
            }
            categoryListStr.delete(categoryListStr.length()-2,categoryListStr.length());
            insertStmt.setString(2, categoryListStr.toString());
            insertStmt.executeUpdate();
            return category;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (insertStmt != null) {
                insertStmt.close();
            }
        }
    }

    public Categories getCategoryByRestaurantId(String restaurantId) throws SQLException {
        String queryCategory = "SELECT * FROM Categories WHERE RestaurantId=?;";
        Connection conn = null;
        PreparedStatement queryStmt = null;
        ResultSet results = null;
        try {
            conn = connectionManager.getConnection();
            queryStmt = conn.prepareStatement(queryCategory);
            queryStmt.setString(1, restaurantId);
            results = queryStmt.executeQuery();
            if (results.next()) {
                String categoryListStr = results.getString("Categories");
                List<String> categoriesList = Arrays.asList(categoryListStr.split(", "));
                Categories category = new Categories(restaurantId, categoriesList);
                return category;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (queryStmt != null) {
                queryStmt.close();
            }
            if (results != null) {
                results.close();
            }
        }
        return null;
    }

    public Categories updateCategories(Categories category, List<String> newCategories) throws SQLException {
        String updateCategories = "UPDATE Categories SET Categories=? WHERE RestaurantId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateCategories);
            StringBuilder categoryListStr = new StringBuilder();
            for(String c:newCategories){
                categoryListStr.append(c+", ");
            }
            categoryListStr.delete(categoryListStr.length()-2,categoryListStr.length());
            updateStmt.setString(1, categoryListStr.toString());
            updateStmt.setString(2, category.getRestaurantId());
            updateStmt.executeUpdate();
            category.setCategoryList(newCategories);
            return category;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (updateStmt != null) {
                updateStmt.close();
            }
        }
    }

    public Categories delete(Categories category) throws SQLException {
        String deleteCategory = "DELETE FROM Categories WHERE RestaurantId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteCategory);
            deleteStmt.setString(1, category.getRestaurantId());
            deleteStmt.executeUpdate();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (deleteStmt != null) {
                deleteStmt.close();
            }
        }

    }
}
