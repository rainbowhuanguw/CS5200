package aireats.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import aireats.model.Restaurant;

/**
 * Use ConnectionManager to connect to your database instance.
 * 
 * ConnectionManager uses the MySQL Connector/J driver to connect to your local MySQL instance.
 * 
 * In our example, we will create a DAO (data access object) java class to interact with
 * each MySQL table. The DAO java classes will use ConnectionManager to open and close
 * connections.
 * 
 * Instructions:
 * 1. Install MySQL Community Server. During installation, you will need to set up a user,
 * password, and port. Keep track of these values.
 * 2. Download and install Connector/J: http://dev.mysql.com/downloads/connector/j/
 * 3. Add the Connector/J JAR to your buildpath. This allows your application to use the
 * Connector/J library. You can add the JAR using either of the following methods:
 *   A. When creating a new Java project, on the "Java Settings" page, go to the 
 *   "Libraries" tab.
 *   Click on the "Add External JARs" button.
 *   Navigate to the Connector/J JAR. On Windows, this looks something like:
 *   C:\Program Files (x86)\MySQL\Connector.J 8.0\mysql-connector-java-8.0.16-bin.jar
 *   B. If you already have a Java project created, then go to your project properties.
 *   Click on the "Java Build Path" option.
 *   Click on the "Libraries" tab, click on the "Add External Jars" button, and
 *   navigate to the Connector/J JAR.
 * 4. Update the "private final" variables below.
 */
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
	        statement.setBigDecimal(7, restaurant.getLatitude());
	        statement.setBigDecimal(8, restaurant.getLongitude());
	        statement.setBigDecimal(9, restaurant.getStars());
	        statement.executeUpdate();
	    } catch (SQLException e) {
	        System.out.println("Error: " + e.getMessage());
	        throw e;
	    }
	    return restaurant;
	}

	public List<Restaurant> getNearbyRestaurants(double airbnbLatitude, double airbnbLongitude, double maxDistanceInMiles) throws SQLException {
	    String query = "SELECT *, (3959 * acos(cos(radians(?)) * cos(radians(Latitude)) * cos(radians(Longitude) - radians(?)) + sin(radians(?)) * sin(radians(Latitude)))) AS distance FROM Restaurants HAVING distance < ?";
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