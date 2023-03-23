package aireats.dal;

import aireats.model.Recommendations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Data access object (DAO) class to interact with the underƒlying
 * Recommendations table
 * in MySQL instance. This is used to store {@link Recommendations} into MySQL
 * instance and
 * retrieve
 * {@link Recommendations} from MySQL instance.
 */
public class RecommendationsDao<T extends Recommendations> implements Dao<T> {
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
     * Save the Recommendation
     * instance by storing it in MySQL instance.
     * This runs a INSERT statement.
     */
    public Recommendations create(Recommendations recommendation) throws SQLException {
        String insertRecommendation = "INSERT INTO Recommendations(RestaurantId, UserId) VALUES(?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertRecommendation, Statement.RETURN_GENERATED_KEYS);
            insertStmt.setString(1, recommendation.getRestaurantId());
            insertStmt.setString(2, recommendation.getUserId());
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
    public Recommendations delete(Recommendations recommendation) throws SQLException {
        String deleteRecommendation = "DELETE FROM Recommendations WHERE RecommendationId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteRecommendation);
            deleteStmt.setInt(1, recommendation.getRecommendationId());
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
    public Recommendations getRecommendationsFromRecommendationId(Integer recommendationId) throws SQLException {
        String selectRecommendation = "SELECT * FROM Recommendations WHERE RecommendationId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectRecommendation);
            selectStmt.setInt(1, recommendationId);
            results = selectStmt.executeQuery();
            if (results.next()) {
                String resultRestaurantId = results.getString("RestaurantId");
                String resultUserId = results.getString("UserId");
                Recommendations recommendation = new Recommendations(recommendationId, resultRestaurantId,
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
    public List<Recommendations> getRecommendationsFromUserId(String userId) throws SQLException {
        List<Recommendations> returnRecommendationList = new ArrayList<>();
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
                Recommendations recommendation = new Recommendations(resultRecommendationId,
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

    /**
     * Get the matching Recommendation
     * records by fetching from MySQL instance.
     * This runs a SELECT statement and returns a list of matching Recommendation
     * .
     */
    public List<Recommendations> getRecommendationsFromRestaurantId(String restaurantId) throws SQLException {
        List<Recommendations> returnRecommendationList = new ArrayList<Recommendations>();
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
                Recommendations recommendation = new Recommendations(resultRecommendationId,
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
