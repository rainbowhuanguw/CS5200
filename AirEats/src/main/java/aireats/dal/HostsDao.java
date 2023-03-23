package aireats.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import aireats.model.*;

public class HostsDao<T extends Hosts> implements Dao<T> {
	protected ConnectionManager connectionManager;
	private static HostsDao instance = null;

	protected HostsDao() {
		connectionManager = new ConnectionManager();
	}

	public static HostsDao getInstance() {
		if (instance == null) {
			instance = new HostsDao();
		}
		return instance;
	}

	@Override
	public Hosts create(Hosts host) throws SQLException {
		if (host == null) return host;

		String insertHost = "INSERT INTO Hosts(HostId,HostName) VALUES(?,?)";
		Connection conn = null;
		PreparedStatement insertStmt = null;
		try {
			conn = connectionManager.getConnection();
			insertStmt = conn.prepareStatement(insertHost);
			insertStmt.setInt(1, host.getHostId());
			insertStmt.setString(2, host.getHostName());
			insertStmt.executeUpdate();
			return host;
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

	public Hosts getHostByHostId(Integer hostId) throws SQLException {
		String queryHost = "SELECT * FROM Hosts WHERE HostId=?;";
		Connection conn = null;
		PreparedStatement queryStmt = null;
		ResultSet results = null;
		try {
			conn = connectionManager.getConnection();
			queryStmt = conn.prepareStatement(queryHost);
			queryStmt.setInt(1, hostId);
			results = queryStmt.executeQuery();
			if (results.next()) {
				String hostName = results.getString("HostName");
				Hosts host = new Hosts(hostId, hostName);
				return host;
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

	public Hosts updateName(Hosts host, String newName) throws SQLException {
		String updateHost = "UPDATE Hosts SET HostName=? WHERE HostId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateHost);
			updateStmt.setString(1, newName);
			updateStmt.setInt(2, host.getHostId());
			updateStmt.executeUpdate();
			host.setHostName(newName);
			return host;
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

	@Override
	public Hosts delete(Hosts host) throws SQLException {
		String deleteHost = "DELETE FROM Hosts WHERE HostId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteHost);
			deleteStmt.setInt(1, host.getHostId());
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
