package aireats.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import aireats.model.Restaurant;


//MinYih Leu
public class RestaurantsDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static RestaurantsDao instance = null;
	protected RestaurantsDao() {
		connectionManager = new ConnectionManager();
	}
	public static RestaurantsDao getInstance() {
		if(instance == null) {
			instance = new RestaurantsDao();
		}
		return instance;
	}
	
	public Restaurant create(Restaurant restaurant) throws SQLException {
	    String query = "INSERT INTO Restaurants(RestaurantId, Name, Address, City, State, Zip, Latitude, Longitude, Stars) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	    Connection connection = connectionManager.getConnection();
	    try (PreparedStatement statement = connection.prepareStatement(query)) {
	        statement.setString(1, restaurant.getRestaurantId());
	        statement.setString(2, restaurant.getName());
	        statement.setString(3, restaurant.getAddress());
	        statement.setString(4, restaurant.getCity());
	        statement.setString(5, restaurant.getState());
	        statement.setString(6, restaurant.getZip());
	        statement.setDouble(7, restaurant.getLatitude());
	        statement.setDouble(8, restaurant.getLongitude());
	        statement.setDouble(9, restaurant.getStars());
	        statement.executeUpdate();
	    } catch (SQLException e) {
	        System.out.println("Error: " + e.getMessage());
	        throw e;
	    }
	    return restaurant;
	}
	
	public Restaurant getRestaurantById(String id) throws SQLException {
	    String query = "SELECT * FROM Restaurants WHERE RestaurantId = ?";
	    Connection connection = connectionManager.getConnection();
	    try (PreparedStatement statement = connection.prepareStatement(query)) {
	        statement.setString(1, id);
	        ResultSet rs = statement.executeQuery();
	        if (rs.next()) {
	            String restaurantId = rs.getString("RestaurantId");
	            String name = rs.getString("Name");
	            String address = rs.getString("Address");
	            String city = rs.getString("City");
	            String state = rs.getString("State");
	            String zip = rs.getString("Zip");
	            double latitude = rs.getDouble("Latitude");
	            double longitude = rs.getDouble("Longitude");
	            double stars = rs.getDouble("Stars");
	            Restaurant restaurant = new Restaurant(restaurantId, name, address, city, state, zip, latitude, longitude, stars);
	            return restaurant;
	        }
	        return null;
	    } catch (SQLException e) {
	        System.out.println("Error: " + e.getMessage());
	        throw e;
	    }
	}

	public List<Restaurant> getRestaurantByName(String name) throws SQLException {
	    String query = "SELECT * FROM Restaurants WHERE Name = ?";
	    Connection connection = connectionManager.getConnection();
	    try (PreparedStatement statement = connection.prepareStatement(query)) {
	        statement.setString(1, name);
	        ResultSet rs = statement.executeQuery();
	        List<Restaurant> restaurants = new ArrayList<>();
	        while (rs.next()) {
	            String restaurantId = rs.getString("RestaurantId");
	            String restaurantName = rs.getString("Name");
	            String address = rs.getString("Address");
	            String city = rs.getString("City");
	            String state = rs.getString("State");
	            String zip = rs.getString("Zip");
	            double latitude = rs.getDouble("Latitude");
	            double longitude = rs.getDouble("Longitude");
	            double stars = rs.getDouble("Stars");
	            Restaurant restaurant = new Restaurant(restaurantId, restaurantName, address, city, state, zip, latitude, longitude, stars);
	            restaurants.add(restaurant);
	        }
	        return restaurants;
	    } catch (SQLException e) {
	        System.out.println("Error: " + e.getMessage());
	        throw e;
	    }
	}
	
	public List<Restaurant> getNearbyRestaurants(double airbnbLatitude, double airbnbLongitude, double maxDistanceInMiles) throws SQLException {
	    String query = "SELECT * FROM"
	    		+ " (SELECT *, (3959 * acos(cos(radians(?)) * cos(radians(Latitude)) * cos(radians(Longitude) - radians(?)) + sin(radians(?)) * sin(radians(Latitude)))) AS distance "
	    		+ "FROM Restaurants) AS X "
	    		+ "WHERE X.distance < ?";
	    Connection connection = connectionManager.getConnection();
	    List<Restaurant> nearbyRestaurants = new ArrayList<>();
	    try (PreparedStatement statement = connection.prepareStatement(query)) {
	        statement.setDouble(1, airbnbLatitude);
	        statement.setDouble(2, airbnbLongitude);
	        statement.setDouble(3, airbnbLatitude);
	        statement.setDouble(4, maxDistanceInMiles);
	        ResultSet rs = statement.executeQuery();
	        while (rs.next()) {
	            String restaurantId = rs.getString("RestaurantId");
	            String name = rs.getString("Name");
	            String address = rs.getString("Address");
	            String city = rs.getString("City");
	            String state = rs.getString("State");
	            String zip = rs.getString("Zip");
	            double latitude = rs.getDouble("Latitude");
	            double longitude = rs.getDouble("Longitude");
	            double stars = rs.getDouble("Stars");
	            Restaurant restaurant = new Restaurant(restaurantId, name, address, city, state, zip, latitude, longitude, stars);
	            nearbyRestaurants.add(restaurant);
	        }
	        return nearbyRestaurants;
	    } catch (SQLException e) {
	        System.out.println("Error: " + e.getMessage());
	        throw e;
	    }
	}

	public Restaurant delete(Restaurant restaurant) throws SQLException {
	    String query = "DELETE FROM Restaurants WHERE RestaurantId = ?";
	    Connection connection = connectionManager.getConnection();
	    try (PreparedStatement statement = connection.prepareStatement(query)) {
	        statement.setString(1, restaurant.getRestaurantId());
	        statement.executeUpdate();
	    } catch (SQLException e) {
	        System.out.println("Error: " + e.getMessage());
	        throw e;
	    }
	    return restaurant;
	}
}