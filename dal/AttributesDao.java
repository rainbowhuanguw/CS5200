package aireats.dal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import aireats.model.*;

public class AttributesDao {
	protected ConnectionManager connectionManager;
	
	private static AttributesDao instance = null;
	protected AttributesDao() {
		connectionManager = new ConnectionManager();
	}
	public static AttributesDao getInstance() {
		if(instance == null) {
			instance = new AttributesDao();
		}
		return instance;
	}
	
	public Attributes create(Attributes attribute) throws SQLException {
		String insertUser = "INSERT INTO Attributes(RestaurantId,Attributes) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertUser);
			
			insertStmt.setString(1, attribute.getRestaurantId());
			insertStmt.setString(2, attribute.getAttributes().toString());

			insertStmt.executeUpdate();
			return attribute;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}
	}
	
	public Attributes getAttributesByRestaurantId(String restaurantId) throws SQLException {
		String selectAttribute = "SELECT RestaurantId,Attributes "
				+ "FROM Attributes WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAttribute);
			selectStmt.setString(1, restaurantId);
			results = selectStmt.executeQuery();
		
			if(results.next()) {
				String resultRestaurantId = results.getString("RestaurantId");
				String attributeString = results.getString("Attributes");
				List<String> attributeList = Arrays.asList(attributeString.substring(1, attributeString.length()-1).split(", "));
				Attributes attribute = new Attributes(resultRestaurantId, attributeList);
				return attribute;
			}
		}catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}
	
	public Attributes updateAttributes(Attributes attribute) throws SQLException {
		String updateAttribute = 
				"UPDATE Attributes SET"
				+ "RestaurantId=?,Attributes=? "
				+ "WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateAttribute);
	
			updateStmt.setString(1, attribute.getRestaurantId());
			updateStmt.setString(2, attribute.getAttributes().toString());
			updateStmt.executeUpdate();
			
			return attribute;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}
	
	public Attributes delete(Attributes attribute) throws SQLException {
		String deleteAttribute = "DELETE FROM Attributes WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteAttribute);
			deleteStmt.setString(1, attribute.getRestaurantId());
			deleteStmt.executeUpdate();
			
			// Return null so the caller can no longer operate on the Attributes instance.
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
}