package aireats.servlet;

import aireats.dal.*;
import aireats.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * FindUsers is the primary entry point into the application.
 *
 * Note the logic for doGet() and doPost() are almost identical. However, there is a difference:
 * doGet() handles the http GET request. This method is called when you put in the /findusers
 * URL in the browser.
 * doPost() handles the http POST request. This method is called after you click the submit button.
 *
 * To run:
 * 1. Run the SQL script to recreate your database schema: http://goo.gl/86a11H.
 * 2. Insert test data. You can do this by running blog.tools.Inserter (right click,
 *    Run As > JavaApplication.
 *    Notice that this is similar to Runner.java in our JDBC example.
 * 3. Run the Tomcat server at localhost.
 * 4. Point your browser to http://localhost:8080/BlogApplication/findusers.
 */
@WebServlet("/RestaurantsQuery")
public class RestaurantsQuery extends HttpServlet {

    protected RestaurantsDao restaurantsDao;
    protected ReviewsDao reviewsDao;
    protected RecommendationsDao recommendationsDao;
    protected TipsDao tipsDao;
    protected YelpUsersDao yelpUsersDao;

    @Override
    public void init() throws ServletException {
        restaurantsDao = RestaurantsDao.getInstance();
        reviewsDao = ReviewsDao.getInstance();
        recommendationsDao = RecommendationsDao.getInstance();
        tipsDao = TipsDao.getInstance();
        yelpUsersDao = YelpUsersDao.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Restaurant> restaurants = new ArrayList<>();
        List<ReviewsPair> reviews = new ArrayList<>();
        List<TipsPair> tips = new ArrayList<>();
        Restaurant restaurant;

        // Retrieve and validate parameter.
        String restaurantId = req.getParameter("restaurant_id");
        if (restaurantId == null || restaurantId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid id.");
        } else {
            try {
                restaurant = restaurantsDao.getRestaurantById(restaurantId);
                restaurants.add(restaurant);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
            messages.put("success", "Displaying results for " + restaurantId);
            // Save the previous search term, so it can be used as the default
            // in the input box when rendering FindUsers.jsp.
            messages.put("previousRestaurantId", restaurantId);
        }

        if (!restaurants.isEmpty()) {
            try {
                List<Review> tempReview = reviewsDao.getReviewsByRestaurantId(restaurantId);
                for (Review rev : tempReview) {
                    YelpUsers user = yelpUsersDao.getYelpUserById(rev.getUserId());
                    reviews.add(new ReviewsPair(rev, user));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                List<Tips> tempTips = tipsDao.getTipsFromRestaurantId(restaurantId);
                for (Tips tip : tempTips) {
                    YelpUsers user = yelpUsersDao.getYelpUserById(tip.getUserId());
                    tips.add(new TipsPair(tip, user));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        req.setAttribute("restaurants", restaurants);
        req.setAttribute("reviews", reviews);
        req.setAttribute("tips", tips);

        req.getRequestDispatcher("/RestaurantsQuery.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Restaurant> restaurants = new ArrayList<>();
        List<ReviewsPair> reviews = new ArrayList<>();
        List<TipsPair> tips = new ArrayList<>();
        Restaurant restaurant;

        // Retrieve and validate parameter.
        String restaurantId = req.getParameter("restaurant_id");
        if (restaurantId == null || restaurantId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid id.");
        } else {
            try {
                restaurant = restaurantsDao.getRestaurantById(restaurantId);
                restaurants.add(restaurant);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
            messages.put("success", "Displaying results for " + restaurantId);
            // Save the previous search term, so it can be used as the default
            // in the input box when rendering FindUsers.jsp.
            messages.put("previousRestaurantId", restaurantId);
        }

        if (!restaurants.isEmpty()) {
            try {
                List<Review> tempReview = reviewsDao.getReviewsByRestaurantId(restaurantId);

                // combine review and user info
                for (Review rev : tempReview) {
                    YelpUsers user = yelpUsersDao.getYelpUserById(rev.getUserId());
                    reviews.add(new ReviewsPair(rev, user));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                List<Tips> tempTips = tipsDao.getTipsFromRestaurantId(restaurantId);

                // combine tips and user info
                for (Tips tip : tempTips) {
                    YelpUsers user = yelpUsersDao.getYelpUserById(tip.getUserId());
                    tips.add(new TipsPair(tip, user));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        req.setAttribute("restaurants", restaurants);
        req.setAttribute("reviews", reviews);
        req.setAttribute("tips", tips);

        req.getRequestDispatcher("/RestaurantsQuery.jsp").forward(req, resp);
    }

    public class TipsPair {
        private Tips tips;
        private YelpUsers user;

        TipsPair(Tips tips, YelpUsers user) {
            this.tips = tips;
            this.user = user;
        }

        public Tips getTips() {
            return tips;
        }

        public YelpUsers getUser() {
            return user;
        }
    }

    public class ReviewsPair {
        public Review review;
        public YelpUsers user;

        ReviewsPair(Review review, YelpUsers user) {
            this.review = review;
            this.user = user;
        }

        public Review getReview() {
            return review;
        }

        public YelpUsers getUser() {
            return user;
        }
    }
}
