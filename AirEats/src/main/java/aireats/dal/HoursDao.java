package aireats.dal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import aireats.model.*;

public class HoursDao<T extends Hours> implements Dao<T> {
	protected ConnectionManager connectionManager;
	
	private static HoursDao instance = null;
	protected HoursDao() {
		connectionManager = new ConnectionManager();
	}
	public static HoursDao getInstance() {
		if(instance == null) {
			instance = new HoursDao();
		}
		return instance;
	}
	
	public Hours create(Hours hour) throws SQLException {
		String insertHour = 
				"INSERT INTO Hours("
				+ "RestaurantId,Monday,Tuesday,Wednesday,Thursday,Friday,Saturday,Sunday"
				+ ") VALUES(?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertHour);
			insertStmt.setString(1, hour.getRestaurantId());
			insertStmt.setString(2, hour.getMonday());
			insertStmt.setString(3, hour.getTuesday());
			insertStmt.setString(4, hour.getWednesday());
			insertStmt.setString(5, hour.getThursday());
			insertStmt.setString(6, hour.getFriday());
			insertStmt.setString(7, hour.getSaturday());
			insertStmt.setString(8, hour.getSunday());
			insertStmt.executeUpdate();
			
			return hour;
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
	
	public Hours getHoursByRestaurantId(String restaurantId) throws SQLException {
		String selectHour = "SELECT RestaurantId,Monday,Tuesday,Wednesday,Thursday,Friday,Saturday,Sunday "
				+ "FROM Hours WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectHour);
			selectStmt.setString(1, restaurantId);
			results = selectStmt.executeQuery();
		
			if(results.next()) {
				String resultRestaurantId = results.getString("RestaurantId");
				String monday = results.getString("Monday");
				String tuesday = results.getString("Tuesday");
				String wednesday = results.getString("Wednesday");
				String thursday = results.getString("Thursday");
				String friday = results.getString("Friday");
				String saturday = results.getString("Saturday");
				String sunday = results.getString("Sunday");
				Hours hour = new Hours(resultRestaurantId, monday, tuesday, wednesday, thursday, friday, saturday, sunday);
				return hour;
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
	
	public Hours updateHours(Hours hour) throws SQLException {
		String updateHour = 
				"UPDATE Hours SET"
				+ "Monday=?,Tuesday=?,Wednesday=?,Thursday=?,Friday=?,Saturday=?,Sunday=? "
				+ "WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateHour);
	
			updateStmt.setString(1, hour.getMonday());
			updateStmt.setString(2, hour.getTuesday());
			updateStmt.setString(3, hour.getWednesday());
			updateStmt.setString(4, hour.getThursday());
			updateStmt.setString(5, hour.getFriday());
			updateStmt.setString(6, hour.getSaturday());
			updateStmt.setString(7, hour.getSunday());
			updateStmt.setString(8, hour.getRestaurantId());
			updateStmt.executeUpdate();
			
			return hour;
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
	
	public Hours delete(Hours hour) throws SQLException {
		String deleteHour = "DELETE FROM Hours WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteHour);
			deleteStmt.setString(1, hour.getRestaurantId());
			deleteStmt.executeUpdate();
			
			// Return null so the caller can no longer operate on the Hours instance.
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