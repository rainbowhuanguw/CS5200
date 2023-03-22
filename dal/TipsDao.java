package aireats.dal;

import aireats.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data access object (DAO) class to interact with the underlying Persons table
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
    public Tips create(Tips tips) throws SQLException {
        String insertTip = "INSERT INTO Tips(UserId, RestaurantId, Compliment_count, Date, Context) VALUES(?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertTip);
            insertStmt.setString(1, tips.getUserId());
            insertStmt.setString(2, tips.getRestaurantId());
            insertStmt.setInt(3, tips.getCompliment_count());
            insertStmt.setTimestamp(4, new Timestamp(tips.getDate().getTime()));
            insertStmt.setString(5, tips.getContext());
            insertStmt.executeUpdate();

            resultKey = insertStmt.getGeneratedKeys();
            int tipsId = -1;
            if (resultKey.next()) {
                tipsId = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            tips.setTipId(tipsId);
            return tips;

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
    public Tips updateContext(Tips tips, String newContext) throws SQLException {
        String updateTips = "UPDATE Tips SET Context=? WHERE TipId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateTips);
            updateStmt.setString(1, newContext);
            updateStmt.setString(2, tips.getTipId());
            updateStmt.executeUpdate();
            // Update the context param before returning to the caller.
            tips.setContext(newContext);
            return tips;
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
    public Tips delete(Tips tips) throws SQLException {
        String deleteTips = "DELETE FROM Tips WHERE TipId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteTips);
            deleteStmt.setString(1, tips.getTipId());
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
            selectStmt.setString(1, tipId);
            results = selectStmt.executeQuery();
            if (results.next()) {
                String resultUserId = results.getString("UserId");
                String resultRestaurantId = results.getString("RestaurantId");
                int resultCompliment_Count = results.getInt("Compliment_count");
                Date resultDate = new Date(results.getTimestamp("Date").getTime());
                String resultContext = results.getString("Context");
                Tips tips = new Tips(resultUserId, resultRestaurantId, resultCompliment_Count, resultDate,
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
            selectStmt = connection.prepareStatement(selectPersons);
            selectStmt.setString(1, userId);
            results = selectStmt.executeQuery();
            while (results.next()) {
                String resultUserId = results.getString("UserId");
                String resultRestaurantId = results.getString("RestaurantId");
                int resultCompliment_Count = results.getInt("Compliment_count");
                Date resultDate = new Date(results.getTimestamp("Date").getTime());
                String resultContext = results.getString("Context");
                Tips tips = new Tips(resultUserId, resultRestaurantId, resultCompliment_Count, resultDate,
                        resultContext);
                returnTipsList.add(person);
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
            selectStmt = connection.prepareStatement(selectPersons);
            selectStmt.setString(1, restaurantId);
            results = selectStmt.executeQuery();
            while (results.next()) {
                String resultUserId = results.getString("UserId");
                String resultRestaurantId = results.getString("RestaurantId");
                int resultCompliment_Count = results.getInt("Compliment_count");
                Date resultDate = new Date(results.getTimestamp("Date").getTime());
                String resultContext = results.getString("Context");
                Tips tips = new Tips(resultUserId, resultRestaurantId, resultCompliment_Count, resultDate,
                        resultContext);
                returnTipsList.add(person);
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
