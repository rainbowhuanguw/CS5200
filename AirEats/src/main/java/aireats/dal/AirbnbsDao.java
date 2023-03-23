package aireats.dal;

import aireats.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AirbnbsDao {
    protected ConnectionManager connectionManager;

    private static AirbnbsDao instance = null;
    protected AirbnbsDao() {
        connectionManager = new ConnectionManager();
    }
    public static AirbnbsDao getInstance() {
        if (instance == null) {
            instance = new AirbnbsDao();
        }
        return instance;
    }
    
    public Airbnbs create(Airbnbs airbnb) throws SQLException {
        String insertAirbnb = "INSERT INTO Airbnb(AirbnbId, HostId, Name, City, Neighborhood, State, Latitude, Longitude) VALUES(?,?,?,?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertAirbnb);
            
            insertStmt.setString(1, airbnb.getAirbnbId());
            insertStmt.setInt(2, airbnb.getHostId());
            insertStmt.setString(3, airbnb.getName());
            insertStmt.setString(4, airbnb.getCity());
            insertStmt.setString(5, airbnb.getNeighborhood());
            insertStmt.setString(6, airbnb.getState());
            insertStmt.setDouble(7, airbnb.getLatitude());
            insertStmt.setDouble(8, airbnb.getLongitude());
            insertStmt.executeUpdate();
            return airbnb;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (insertStmt != null) {
                insertStmt.close();
            }
        }
    }

    public Airbnbs delete(Airbnbs airbnb) throws SQLException {
        String deleteAirbnb = "DELETE FROM Airbnbs WHERE AirbnbId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteAirbnb);
            
            deleteStmt.setString(1, airbnb.getAirbnbId());
            deleteStmt.executeUpdate();

            // Return null so the caller can no longer operate on the Airbnb instance.
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
    
    public Airbnbs updateName(Airbnbs airbnb, String newName) throws SQLException {
        String updateAirbnb = "UPDATE Airbnbs SET Name=? WHERE AirbnbId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateAirbnb);
            
            updateStmt.setString(1, newName);
            updateStmt.setString(2, airbnb.getAirbnbId());
            updateStmt.executeUpdate();

            airbnb.setName(newName);
            return airbnb;
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


    public Airbnbs getAirbnbById(String airbnbId) throws SQLException {
        String selectAirbnb = "SELECT AirbnbId, HostId, Name, City, Neighborhood, State, Latitude, Longitude "
        		+ "FROM Airbnb WHERE AirbnbId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectAirbnb);
            
            selectStmt.setString(1, airbnbId);
            results = selectStmt.executeQuery();
            if (results.next()) {
                String resultAirbnbId = results.getString("AirbnbId");
                int hostId = results.getInt("HostId");
                String name = results.getString("Name");
                String city = results.getString("City");
                String neighborhood = results.getString("Neighborhood");
                String state = results.getString("State");
                Double latitude = results.getDouble("Latitude");
                Double longitude = results.getDouble("Longitude");

                Airbnbs airbnb = new Airbnbs(resultAirbnbId, hostId, name, city, neighborhood, state, latitude, longitude);
                return airbnb;
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
        return null;
    }
    
    // Get Airbnbs by Name (a name can be used in multiple airbnbs) 
    public List<Airbnbs> getAirbnbByName (String airbnbName) throws SQLException{
    	List<Airbnbs> airbnbs = new ArrayList<Airbnbs>();
    	String selectAirbnb = "SELECT AirbnbId, HostId, Name, City, Neighborhood, State, Latitude, Longitude "
    			+ "FROM Airbnb WHERE Name=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
    	try {
    		connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectAirbnb);
    		
            selectStmt.setString(1, airbnbName);
            results = selectStmt.executeQuery();
            while (results.next()) {
                String resultAirbnbId = results.getString("AirbnbId");
                int hostId = results.getInd("HostId");
                String name = results.getString("Name");
                String city = results.getString("City");
                String neighborhood = results.getString("Neighborhood");
                String state = results.getString("State");
                Double latitude = results.getDouble("Latitude");
                Double longitude = results.getDouble("Longitude");

                Airbnbs airbnb = new Airbnbs(resultAirbnbId, hostId, name, city, neighborhood, state, latitude, longitude);
                airbnbs.add(airbnb);
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
        return airbnbs;
    }
    
    // Get Airbnbs by (city&state)
    public List<Airbnbs> getAirbnbsByCityAndState(String city, String state) throws SQLException {
        List<Airbnbs> airbnbs = new ArrayList<Airbnbs>();
        String selectAirbnbs =
            "SELECT AirbnbId, HostId, Name, City, Neighborhood, State, Latitude, Longitude "
            + "FROM Airbnbs "
            + "WHERE City=? AND State=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectAirbnbs);
            selectStmt.setString(1, city);
            selectStmt.setString(2, state);
            results = selectStmt.executeQuery();
            while (results.next()) {
                String resultAirbnbId = results.getString("AirbnbId");
                int hostId = results.getInd("HostId");
                String resultName = results.getString("Name");
                String resultCity = results.getString("City");
                String resultNeighborhood = results.getString("Neighborhood");
                String resultState = results.getString("State");
                Double resultLatitude = results.getDouble("Latitude");
                Double resultLongitude = results.getDouble("Longitude");

                Airbnbs airbnb = new Airbnbs(resultAirbnbId, hostId, resultName, resultCity, resultNeighborhood, resultState, resultLatitude, resultLongitude);
                airbnbs.add(airbnb);
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
        return airbnbs;
    }
    
    // Get Airbnbs by hostId
    public List<Airbnb> getAirbnbsByHostId(int hostId) throws SQLException {
        List<Airbnb> airbnbs = new ArrayList<Airbnb>();
        String selectAirbnbs =
            "SELECT AirbnbId, HostId, Name, City, Neighborhood, State, Latitude, Longitude " +
            "FROM Airbnbs " +
            "WHERE HostId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectAirbnbs);
            selectStmt.setInt(1, hostId);
            results = selectStmt.executeQuery();
            while (results.next()) {
                String resultAirbnbId = results.getString("AirbnbId");
                int resultHostId = results.getInt("HostId");
                String resultName = results.getString("Name");
                String resultCity = results.getString("City");
                String resultNeighborhood = results.getString("Neighborhood");
                String resultState = results.getString("State");
                Double resultLatitude = results.getDouble("Latitude");
                Double resultLongitude = results.getDouble("Longitude");

                Airbnb airbnb = new Airbnb(resultAirbnbId, resultHostId, resultName, resultCity, resultNeighborhood, resultState, resultLatitude, resultLongitude);
                airbnbs.add(airbnb);
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
        return airbnbs;
    }  
    
}
