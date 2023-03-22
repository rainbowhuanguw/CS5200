package aireats.dal;

import aireats.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Data access object (DAO) class to interact with the underlying Persons table
 * in MySQL instance. This is used to store {@link Recommendations} into MySQL
 * instance and
 * retrieve
 * {@link Recommendations} from MySQL instance.
 */
public class RecommendationsDao {
    protected ConnectionManager connectionManager;

    // Single pattern: instantiation is limited to one object.
    private static RecommendationsDao instance = null;

    protected RecommendationsDao() {
        connectionManager = new ConnectionManager();
    }

    public static RecommendationsDao getInstance() {
        if (instance == null) {
            instance = new RecommendationsDao();
        }
        return instance;
    }

    /**
     * Save the Recommendations instance by storing it in MySQL instance.
     * This runs a INSERT statement.
     */
    public Recommendations create(Recommendations recommendations) throws SQLException {
        String insertRecommendations = "INSERT INTO Recommendations(RestaurantId, UserId) VALUES(?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertTip, Statement.RETURN_GENERATED_KEYS);
            insertStmt.setString(1, recommendations.getRestaurantId());
            insertStmt.setInt(2, recommendations.getUserId());
            insertStmt.executeUpdate();

            resultKey = insertStmt.getGeneratedKeys();
            int recommendationsId = -1;
            if (resultKey.next()) {
                recommendationsId = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            recommendations.setRecommendationsId(recommendationsId);
            ;
            return recommendations;
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

    /**
     * Delete the Recommendations instance.
     * This runs a DELETE statement.
     */
    public Recommendations delete(Recommendations recommendations) throws SQLException {
        String deleteRecommendations = "DELETE FROM Recommendations WHERE RecommendationId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteRecommendations);
            deleteStmt.setString(1, recommendations.getRecommendationsId());
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

    /**
     * Get the Recommendations record by fetching it from MySQL instance.
     * This runs a SELECT statement and returns a single Recommendations instance.
     */
    public Recommendations getRecommendationsFromRecommendationsId(int recommendationsId) throws SQLException {
        String selectRecommendations = "SELECT * FROM Recommendations WHERE RecommendationId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectPerson);
            selectStmt.setString(1, recommendationsId);
            results = selectStmt.executeQuery();
            if (results.next()) {
                int resultRecommendationsId = results.getInt("RecommendationId");
                String resultRestaurantId = results.getString("RestaurantId");
                String resultUserId = results.getString("UserId");
                Recommendations recommendations = new Recommendations(resultUserId, resultRestaurantId, resultUserId);
                return recommendations;
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

    /**
     * Get the matching Recommendations records by fetching from MySQL instance.
     * This runs a SELECT statement and returns a list of matching Recommendations.
     */
    public List<Recommendations> getRecommendationsFromUserId(String userId) throws SQLException {
        List<Recommendations> returnRecommendationsList = new ArrayList<Recommendations>();
        String selectRecommendations = "SELECT * FROM Recommendations WHERE UserId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectPersons);
            selectStmt.setString(1, userId);
            results = selectStmt.executeQuery();
            while (results.next()) {
                int resultRecommendationsId = results.getInt("RecommendationId");
                String resultRestaurantId = results.getString("RestaurantId");
                String resultUserId = results.getString("UserId");
                Recommendations recommendations = new Recommendations(resultUserId, resultRestaurantId, resultUserId);
                returnRecommendationsList.add(recommendations);
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
        return returnRecommendationsList;
    }

    /**
     * Get the matching Recommendations records by fetching from MySQL instance.
     * This runs a SELECT statement and returns a list of matching Recommendations.
     */
    public List<Recommendations> getRecommendationsFromRestaurantId(String restaurantId) throws SQLException {
        List<Recommendations> returnRecommendationsList = new ArrayList<Recommendations>();
        String selectRecommendations = "SELECT * FROM Recommendations WHERE RestaurantId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectPersons);
            selectStmt.setString(1, restaurantId);
            results = selectStmt.executeQuery();
            while (results.next()) {
                int resultRecommendationsId = results.getInt("RecommendationId");
                String resultRestaurantId = results.getString("RestaurantId");
                String resultUserId = results.getString("UserId");
                Recommendations recommendations = new Recommendations(resultUserId, resultRestaurantId, resultUserId);
                returnRecommendationsList.add(recommendations);
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
        return returnRecommendationsList;
    }
}
