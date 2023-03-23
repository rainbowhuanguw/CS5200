//By Youwei Yu
package aireats.AirEats.src.main.java.aireats.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import aireats.AirEats.src.main.java.aireats.model.Categories;
import aireats.dal.ConnectionManager;

public class CategoriesDao<T extends Categories> implements Dao<T> {
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
            insertStmt.setString(2, category.getCategoryListStr());
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
                Categories category = new Categories(restaurantId, categoryListStr);
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

    public List<Restaurant> getRestaurantsByCategories(String keyword) throws SQLException {
        List<Restaurant> ret = new ArrayList<Restaurant>();
        String selectRestaurants = "SELECT Restaurants.RestaurantId, Name, Address, City, State, Zip, Latitude, Longitude, Stars "
                +
                "FROM Categories LEFT JOIN Restaurants " +
                "ON Categories.RestaurantId = Restaurants.RestaurantId " +
                "WHERE Categories.Categories LIKE ?";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectRestaurants);
            selectStmt.setString(1, "%" + keyword + "%");
            results = selectStmt.executeQuery();
            while (results.next()) {
                String restaurantId = results.getString("Restaurants.RestaurantId");
                String name = results.getString("Name");
                String address = results.getString("Address");
                String city = results.getString("City");
                String state = results.getString("State");
                String zip = results.getString("Zip");
                double latitude = results.getDouble("Latitude");
                double longitude = results.getDouble("Longitude");
                double stars = results.getDouble("Stars");
                Restaurant restaurant = new Restaurant(restaurantId, name, address, city, state, zip, latitude,
                        longitude, stars);
                ret.add(restaurant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (selectStmt != null) {
                selectStmt.close();
            }
            if (results != null) {
                results.close();
            }
        }
        return ret;
    }

    public Categories updateCategories(Categories category, List<String> newCategories) throws SQLException {
        String updateCategories = "UPDATE Categories SET Categories=? WHERE RestaurantId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateCategories);
            updateStmt.setString(1, String.join(", ", newCategories));
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
