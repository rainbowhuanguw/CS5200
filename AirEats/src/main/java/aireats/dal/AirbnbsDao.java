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
        String deleteAirbnb = "DELETE FROM Airbnb WHERE AirbnbId=?;";
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
        String updateAirbnb = "UPDATE Airbnb SET Name=? WHERE AirbnbId=?;";
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
    	return getAirbnbById(airbnbId,0);
    }

    public Airbnbs getAirbnbById(String airbnbId, int offSet) throws SQLException {
        String selectAirbnb = "SELECT AirbnbId, HostId, Name, City, Neighborhood, State, Latitude, Longitude "
        		+ "FROM Airbnb WHERE AirbnbId=?"
        		+ "LIMIT 100 OFFSET ?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectAirbnb);
            
            selectStmt.setString(1, airbnbId);
            selectStmt.setInt(2, offSet);
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
    public List<Airbnbs> getAirbnbByName (String airbnbName, int offSet) throws SQLException{
    	List<Airbnbs> airbnbs = new ArrayList<Airbnbs>();
    	String selectAirbnb = "SELECT AirbnbId, HostId, Name, City, Neighborhood, State, Latitude, Longitude "
    			+ "FROM Airbnb WHERE Name like ?"
    			+ "LIMIT 100 OFFSET ?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
    	try {
    		connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectAirbnb);
    		
            selectStmt.setString(1, "%"+airbnbName+"%");
            selectStmt.setInt(2, offSet);
            results = selectStmt.executeQuery();
            while (results.next()) {
                String resultAirbnbId = results.getString("AirbnbId");
                int hostId = results.getInt("HostId");
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
    public List<Airbnbs> getAirbnbsByCityAndState(String city, String state, int offSet) throws SQLException {
        List<Airbnbs> airbnbs = new ArrayList<Airbnbs>();
        String selectAirbnbs =
            "SELECT AirbnbId, HostId, Name, City, Neighborhood, State, Latitude, Longitude "
            + "FROM Airbnb "
            + "WHERE City=? AND State=?"
            + "LIMIT 100 OFFSET ?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectAirbnbs);
            selectStmt.setString(1, city);
            selectStmt.setString(2, state);
            selectStmt.setInt(3, offSet);
            results = selectStmt.executeQuery();
            while (results.next()) {
                String resultAirbnbId = results.getString("AirbnbId");
                int hostId = results.getInt("HostId");
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
    public List<Airbnbs> getAirbnbsByHostId(int hostId, int offSet) throws SQLException {
        List<Airbnbs> airbnbs = new ArrayList<Airbnbs>();
        String selectAirbnbs =
            "SELECT AirbnbId, HostId, Name, City, Neighborhood, State, Latitude, Longitude " +
            "FROM Airbnb " +
            "WHERE HostId=?"
            + "LIMIT 100 OFFSET ?;";;
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectAirbnbs);
            selectStmt.setInt(1, hostId);
            selectStmt.setInt(2, offSet);
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

                Airbnbs airbnb = new Airbnbs(resultAirbnbId, resultHostId, resultName, resultCity, resultNeighborhood, resultState, resultLatitude, resultLongitude);
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
    
    //Get Airbnbs by state, city and name
    public List<Airbnbs> getAirbnbsByCityStateName(String city, String state, String name, int offSet) throws SQLException {
        List<Airbnbs> airbnbs = new ArrayList<Airbnbs>();
        String selectAirbnbs =
            "SELECT AirbnbId, HostId, Name, City, Neighborhood, State, Latitude, Longitude "
            + "FROM Airbnb "
            + "WHERE City=? AND State=? AND Name like ?"
            + "LIMIT 100 OFFSET ?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectAirbnbs);
            selectStmt.setString(1, city);
            selectStmt.setString(2, state);
            selectStmt.setString(3, "%"+name+"%");
            selectStmt.setInt(4, offSet);
            results = selectStmt.executeQuery();
            while (results.next()) {
                String resultAirbnbId = results.getString("AirbnbId");
                int hostId = results.getInt("HostId");
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
    
    
}
