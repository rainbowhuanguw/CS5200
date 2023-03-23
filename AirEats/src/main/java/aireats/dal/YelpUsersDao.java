package aireats.dal;

import aireats.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.math.BigDecimal;


public class YelpUsersDao {
    protected ConnectionManager connectionManager;

    private static YelpUsersDao instance = null;
    
    protected YelpUsersDao() {
        connectionManager = new ConnectionManager();
    }
    
    public static YelpUsersDao getInstance() {
        if(instance == null) {
            instance = new YelpUsersDao();
        }
        return instance;
    }
    
    public YelpUsers create(YelpUsers yelpUser) throws SQLException {
    	String insertYelpUser = "INSERT INTO YelpUsers(UserId, UserName, review_count, yelping_since,"
    			+ "useful, funny, cool, fans, average_stars, compliment_hot, compliment_more, compliment_profile,"
    			+ "compliment_cute, compliment_list, compliment_note, compliment_plain, compliment_cool, compliment_funny,"
    			+ "compliment_writer, compliment_photos) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
    	Connection connection = null;
    	PreparedStatement insertStmt = null;
    	try {
    		connection = connectionManager.getConnection();
    		insertStmt = connection.prepareStatement(insertYelpUser);
    		
    		insertStmt.setString(1, yelpUser.getUserId());
    		insertStmt.setString(2, yelpUser.getUserName());
    		insertStmt.setInt(3, yelpUser.getReviewCount());
            insertStmt.setTimestamp(4, yelpUser.getYelpingSince());
            insertStmt.setInt(5, yelpUser.getUseful());
            insertStmt.setInt(6, yelpUser.getFunny());
            insertStmt.setInt(7, yelpUser.getCool());
            insertStmt.setInt(8, yelpUser.getFans());
            insertStmt.setBigDecimal(9, yelpUser.getAverageStars());
            insertStmt.setInt(10, yelpUser.getComplimentHot());
            insertStmt.setInt(11, yelpUser.getComplimentMore());
            insertStmt.setInt(12, yelpUser.getComplimentProfile());
            insertStmt.setInt(13, yelpUser.getComplimentCute());
            insertStmt.setInt(14, yelpUser.getComplimentList());
            insertStmt.setInt(15, yelpUser.getComplimentNote());
            insertStmt.setInt(16, yelpUser.getComplimentPlain());
            insertStmt.setInt(17, yelpUser.getComplimentCool());
            insertStmt.setInt(18, yelpUser.getComplimentFunny());
            insertStmt.setInt(19, yelpUser.getComplimentWriter());
            insertStmt.setInt(20, yelpUser.getComplimentPhotos());
    		
    		insertStmt.executeUpdate();
    		return yelpUser;
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
    
    public YelpUsers delete(YelpUsers yelpUser) throws SQLException{
    	String deleteYelpUser = "DELETE FROM YelpUsers WHERE UserId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteYelpUser);
            
            deleteStmt.setString(1, yelpUser.getUserId());
            deleteStmt.executeUpdate();

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
    
    public YelpUsers updateUser(YelpUsers yelpUser, int reviewCount) throws SQLException {
        String updateUser = "UPDATE YelpUsers SET review_count=? WHERE UserId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateUser);
            
            updateStmt.setInt(1, reviewCount);
            updateStmt.setString(2, yelpUser.getUserId());
            updateStmt.executeUpdate();

            // Update the yelpUser param before returning to the caller.
            yelpUser.setReviewCount(reviewCount);
            return yelpUser;
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
    
    public YelpUsers getYelpUserById(String userId) throws SQLException {
        String selectYelpUser =
            "SELECT UserId, UserName, review_count, yelping_since, useful, funny, cool, fans, "
            + "average_stars, compliment_hot, compliment_more, compliment_profile, compliment_cute, compliment_list, "
            + "compliment_note, compliment_plain, compliment_cool, compliment_funny, compliment_writer, compliment_photos " 
            + "FROM YelpUsers " 
            + "WHERE UserId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectYelpUser);
            
            selectStmt.setString(1, userId);
            results = selectStmt.executeQuery();
            if(results.next()) {
                String resultUserId = results.getString("UserId");
                String userName = results.getString("UserName");
                int reviewCount = results.getInt("review_count");
                Timestamp yelpingSince = results.getTimestamp("yelping_since");
                int useful = results.getInt("useful");
                int funny = results.getInt("funny");
                int cool = results.getInt("cool");
                int fans = results.getInt("fans");
                BigDecimal averageStars = results.getBigDecimal("average_stars");
                int complimentHot = results.getInt("compliment_hot");
                int complimentMore = results.getInt("compliment_more");
                int complimentProfile = results.getInt("compliment_profile");
                int complimentCute = results.getInt("compliment_cute");
                int complimentList = results.getInt("compliment_list");
                int complimentNote = results.getInt("compliment_note");
                int complimentPlain = results.getInt("compliment_plain");
                int complimentCool = results.getInt("compliment_cool");
                int complimentFunny = results.getInt("compliment_funny");
                int complimentWriter = results.getInt("compliment_writer");
                int complimentPhotos = results.getInt("compliment_photos");

                YelpUsers yelpUser = new YelpUsers(resultUserId, userName, reviewCount, yelpingSince, 
                		useful, funny, cool, fans, averageStars, complimentHot, complimentMore, 
                		complimentProfile, complimentCute, complimentList, complimentNote, complimentPlain, 
                		complimentCool, complimentFunny, complimentWriter, complimentPhotos);
                return yelpUser;
            }
        } catch (SQLException e) {
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
    
    


}
