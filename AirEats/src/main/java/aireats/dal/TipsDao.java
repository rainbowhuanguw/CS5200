package aireats.dal;

import aireats.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


/**
 * Data access object (DAO) class to interact with the underlying
 * Recommendations table
 * in MySQL instance. This is used to store {@link Tips} into MySQL instance and
 * retrieve
 * {@link Tips} from MySQL instance.
 */
public class TipsDao {
    protected ConnectionManager connectionManager;

    // Single pattern: instantiation is limited to one object.
    private static TipsDao instance = null;

    protected TipsDao() {
        connectionManager = new ConnectionManager();
    }

    public static TipsDao getInstance() {
        if (instance == null) {
            instance = new TipsDao();
        }
        return instance;
    }

    /**
     * Save the Tips instance by storing it in MySQL instance.
     * This runs a INSERT statement.
     */
    public Tips create(Tips tip) throws SQLException {
        String insertTips = "INSERT INTO Tips(UserId, RestaurantId, Compliment_count, Date, Context) VALUES(?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertTips);
            insertStmt.setString(1, tip.getUserId());
            insertStmt.setString(2, tip.getRestaurantId());
            insertStmt.setInt(3, tip.getComplimentCount());
            insertStmt.setDate(4, new Date(tip.getDate().getTime()));
            insertStmt.setString(5, tip.getContext());
            insertStmt.executeUpdate();

            resultKey = insertStmt.getGeneratedKeys();
            int tipId = -1;
            if (resultKey.next()) {
                tipId = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            tip.setTipId(tipId);
            return tip;

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
     * Update the Context of the Tips instance.
     * This runs a UPDATE statement.
     */
    public Tips updateContext(Tips tip, String newContext) throws SQLException {
        String updateTips = "UPDATE Tips SET Context=? WHERE TipId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateTips);
            updateStmt.setString(1, newContext);
            updateStmt.setInt(2, tip.getTipId());
            updateStmt.executeUpdate();
            // Update the context param before returning to the caller.
            tip.setContext(newContext);
            return tip;
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

    /**
     * Delete the Tips instance.
     * This runs a DELETE statement.
     */
    public Tips delete(Tips tip) throws SQLException {
        String deleteTips = "DELETE FROM Tips WHERE TipId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteTips);
            deleteStmt.setInt(1, tip.getTipId());
            deleteStmt.executeUpdate();

            // Return null so the caller can no longer operate on the Tips instance.
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
     * Get the Tips record by fetching it from MySQL instance.
     * This runs a SELECT statement and returns a single Tips instance.
     */
    public Tips getTipsFromTipId(int tipId) throws SQLException {
        String selectTips = "SELECT * FROM Tips WHERE TipId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectTips);
            selectStmt.setInt(1, tipId);
            results = selectStmt.executeQuery();
            if (results.next()) {
                String resultUserId = results.getString("UserId");
                String resultRestaurantId = results.getString("RestaurantId");
                int resultComplimentCount = results.getInt("Compliment_count");
                Date resultDate = new Date(results.getTime("Date").getTime());
                String resultContext = results.getString("Context");
                Tips tips = new Tips(resultUserId, resultRestaurantId, resultComplimentCount, resultDate,
                        resultContext);
                return tips;
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
     * Get the matching Tips records by fetching from MySQL instance.
     * This runs a SELECT statement and returns a list of matching Tips.
     */
    public List<Tips> getTipsFromUserId(String userId) throws SQLException {
        List<Tips> returnTipsList = new ArrayList<Tips>();
        String selectTips = "SELECT * FROM Tips WHERE UserId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectTips);
            selectStmt.setString(1, userId);
            results = selectStmt.executeQuery();
            while (results.next()) {
                String resultUserId = results.getString("UserId");
                String resultRestaurantId = results.getString("RestaurantId");
                int resultComplimentCount = results.getInt("Compliment_count");
                Date resultDate = new Date(results.getTime("Date").getTime());
                String resultContext = results.getString("Context");
                Tips tip = new Tips(resultUserId, resultRestaurantId, resultComplimentCount, resultDate,
                        resultContext);
                returnTipsList.add(tip);
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
        return returnTipsList;
    }

    /**
     * Get the matching Tips records by fetching from MySQL instance.
     * This runs a SELECT statement and returns a list of matching Tips.
     */
    public List<Tips> getTipsFromRestaurantId(String restaurantId) throws SQLException {
        List<Tips> returnTipsList = new ArrayList<Tips>();
        String selectTips = "SELECT * FROM Tips WHERE RestaurantId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectTips);
            selectStmt.setString(1, restaurantId);
            results = selectStmt.executeQuery();
            while (results.next()) {
                String resultUserId = results.getString("UserId");
                String resultRestaurantId = results.getString("RestaurantId");
                int resultComplimentCount = results.getInt("Compliment_count");
                Date resultDate = new Date(results.getDate("Date").getTime());
                String resultContext = results.getString("Context");
                Tips tip = new Tips(resultUserId, resultRestaurantId, resultComplimentCount, resultDate,
                        resultContext);
                returnTipsList.add(tip);
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
        return returnTipsList;
    }
}
