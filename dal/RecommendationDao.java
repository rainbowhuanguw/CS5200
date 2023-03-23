package aireats.dal;

import aireats.model.*;
import aireats.models.Recommendation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Data access object (DAO) class to interact with the underlying Persons table
 * in MySQL instance. This is used to store {@link Recommendation} into MySQL
 * instance and
 * retrieve
 * {@link Recommendation} from MySQL instance.
 */
public class RecommendationDao {
    protected ConnectionManager connectionManager;

    // Single pattern: instantiation is limited to one object.
    private static RecommendationDao instance = null;

    protected RecommendationDao() {
        connectionManager = new ConnectionManager();
    }

    public static RecommendationDao getInstance() {
        if (instance == null) {
            instance = new RecommendationDao();
        }
        return instance;
    }

    /**
     * Save the Recommendation
     * instance by storing it in MySQL instance.
     * This runs a INSERT statement.
     */
    public Recommendation create(Recommendation recommendation) throws SQLException {
        String insertRecommendation = "INSERT INTO Recommendations(RestaurantId, UserId) VALUES(?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertRecommendation, Statement.RETURN_GENERATED_KEYS);
            insertStmt.setString(1, recommendation.getRestaurantId());
            insertStmt.setInt(2, recommendation.getUserId());
            insertStmt.executeUpdate();

            resultKey = insertStmt.getGeneratedKeys();
            int recommendationId = -1;
            if (resultKey.next()) {
                recommendationId = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            recommendation.setRecommendationId(recommendationId);
            ;
            return recommendation;
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
     * Delete the Recommendation
     * instance.
     * This runs a DELETE statement.
     */
    public Recommendation delete(Recommendation recommendation) throws SQLException {
        String deleteRecommendation = "DELETE FROM Recommendations WHERE RecommendationId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteRecommendation);
            deleteStmt.setString(1, recommendation.getRecommendationId());
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
     * Get the Recommendation
     * record by fetching it from MySQL instance.
     * This runs a SELECT statement and returns a single Recommendation
     * instance.
     */
    public Recommendation getRecommendationsFromRecommendationId(int recommendationId) throws SQLException {
        String selectRecommendation = "SELECT * FROM Recommendations WHERE RecommendationId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectRecommendation);
            selectStmt.setString(1, recommendationId);
            results = selectStmt.executeQuery();
            if (results.next()) {
                int resultRecommendationsId = results.getInt("RecommendationId");
                String resultRestaurantId = results.getString("RestaurantId");
                String resultUserId = results.getString("UserId");
                Recommendation recommendation = new Recommendation(recommendationId, resultUserId, resultRestaurantId,
                        resultUserId);
                return recommendation;
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
     * Get the matching Recommendation
     * records by fetching from MySQL instance.
     * This runs a SELECT statement and returns a list of matching Recommendation
     * .
     */
    public List<Recommendati> getRecommendationsFromUserId(String userId) throws SQLException {
        List<Recommendatio> returnRecommendationList = new ArrayList<Recommendation>();
        String selectRecommendation = "SELECT * FROM Recommendations WHERE UserId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectRecommendation);
            selectStmt.setString(1, userId);
            results = selectStmt.executeQuery();
            while (results.next()) {
                int resultRecommendationId = results.getInt("RecommendationId");
                String resultRestaurantId = results.getString("RestaurantId");
                String resultUserId = results.getString("UserId");
                Recommendation recommendation = new Recommendation(resultRecommendationId, resultUserId,
                        resultRestaurantId, resultUserId);
                returnRecommendationsList.add(recommendation);
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
        return returnRecommendationList;
    }

    /**
     * Get the matching Recommendation
     * records by fetching from MySQL instance.
     * This runs a SELECT statement and returns a list of matching Recommendation
     * .
     */
    public List<Recommendation> getRecommendationsFromRestaurantId(String restaurantId) throws SQLException {
        List<Recommendation> returnRecommendationList = new ArrayList<Recommendation>();
        String selectRecommendation = "SELECT * FROM Recommendatio WHERE RestaurantId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectRecommendation);
            selectStmt.setString(1, restaurantId);
            results = selectStmt.executeQuery();
            while (results.next()) {
                int resultRecommendationId = results.getInt("RecommendationId");
                String resultRestaurantId = results.getString("RestaurantId");
                String resultUserId = results.getString("UserId");
                Recommendation recommendation = new Recommendation(resultRecommendationId, resultUserId,
                        resultRestaurantId, resultUserId);
                returnRecommendationList.add(recommendation);
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
        return returnRecommendationList;
    }
}
