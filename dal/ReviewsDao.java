package aireats.dal;

import aireats.model.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;


/**
 * Data access object (DAO) class to interact with the underlying Persons table in your MySQL
 * instance. This is used to store {@link Persons} into your MySQL instance and retrieve 
 * {@link Persons} from MySQL instance.
 */
public class ReviewsDao {
    private ConnectionManager connectionManager;
    private static ReviewsDao instance = null;

    private ReviewsDao() {
        connectionManager = new ConnectionManager();
    }

    public static ReviewsDao getInstance() {
        if(instance == null) {
            instance = new ReviewsDao();
        }
        return instance;
    }

    /**
     * Saves the review instance by storing it in your MySQL instance.
     * This runs an INSERT statement.
     */
    public Review create(Review review) throws SQLException {
        String query = "INSERT INTO Reviews(ReviewId, UserId, RestaurantId, Stars, Useful, Funny, Cool, Content, Date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection connection = connectionManager.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, review.getReviewId());
            statement.setString(2, review.getUserId());
            statement.setString(3, review.getRestaurantId());
            statement.setInt(4, review.getStars());
            statement.setBigDecimal(5, review.getUseful());
            statement.setBigDecimal(6, review.getFunny());
            statement.setBigDecimal(7, review.getCool());
            statement.setString(8, review.getContent());
            statement.setDate(9, (java.sql.Date) review.getDate());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            throw e;
        }
        return review;
    }

    /**
     * Retrieves a review by its review ID.
     */
    public Review getReviewById(int reviewId) throws SQLException {
        String query = "SELECT * FROM Reviews WHERE ReviewId = ?";
        Connection connection = connectionManager.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, reviewId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                String userId = rs.getString("UserId");
                String restaurantId = rs.getString("RestaurantId");
                String stars = rs.getString("Stars");
                BigDecimal useful = rs.getBigDecimal("Useful");
                BigDecimal funny = rs.getBigDecimal("Funny");
                BigDecimal cool = rs.getBigDecimal("Cool");
                String content = rs.getString("Content");
                Date date = rs.getDate("Date");
                return new Review(stars, userId, restaurantId, reviewId, useful, funny, cool, content, date);
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Updates an existing review in the database.
     */
    public Review update(Review review) throws SQLException {
        String query = "UPDATE Reviews SET UserId=?, RestaurantId=?, Stars=?, Useful=?, Funny=?, Cool=?, Content=?, Date=? WHERE ReviewId=?";
        Connection connection = connectionManager.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
        	statement.setString(1, review.getReviewId());
            statement.setString(2, review.getUserId());
            statement.setString(3, review.getRestaurantId());
            statement.setInt(4, review.getStars());
            statement.setBigDecimal(5, review.getUseful());
            statement.setBigDecimal(6, review.getFunny());
            statement.setBigDecimal(7, review.getCool());
            statement.setString(8, review.getContent());
            statement.setDate(9, (java.sql.Date) review.getDate());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            throw e;
        }
        return review;
    }

    public Review delete(Review review) throws SQLException {
        String query = "DELETE FROM Reviews WHERE ReviewId = ?";
        Connection connection = connectionManager.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, review.getReviewId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            throw e;
        }
        return review;
    }
}