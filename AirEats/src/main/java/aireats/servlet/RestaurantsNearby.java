package aireats.servlet;

import aireats.dal.*;
import aireats.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
@WebServlet("/RestaurantsNearby")
public class RestaurantsNearby extends HttpServlet {
	
	protected RestaurantsDao restaurantsDao;
	protected AirbnbsDao airbnbsDao;
	
	@Override
	public void init() throws ServletException {
		restaurantsDao = RestaurantsDao.getInstance();
		airbnbsDao = AirbnbsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
		
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        List<Restaurant> restaurants = new ArrayList<>();
        List<Airbnbs> airbnbs = new ArrayList<>();
        
        // Retrieve and validate parameter.
        String airbnbId = req.getParameter("airbnb_id");
        String radius = req.getParameter("radius");
        String pageStr = req.getParameter("page");
        String itemsPerPageStr = req.getParameter("itemsPerPage");
        
		if (airbnbId == null || airbnbId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid id.");
        } else if (radius == null || radius.trim().isEmpty()) {
            messages.put("success", "Please enter a valid radius.");
        } else {
        	try {
        		Airbnbs airbnb = airbnbsDao.getAirbnbById(airbnbId);
        		int page = Integer.parseInt(pageStr);
                int itemsPerPage = Integer.parseInt(itemsPerPageStr);
        		restaurants = restaurantsDao.getNearbyRestaurants(page, itemsPerPage, airbnb.getLatitude(),airbnb.getLongitude(), Integer.valueOf(radius));
        		airbnbs.add(airbnb);
        		messages.put("success", "Airbnb: " + airbnb.getName() + "; City: " + airbnb.getCity() + "; Neighborhood: " + airbnb.getNeighborhood());
        		messages.put("airbnbName", airbnb.getName());
        		messages.put("airbnbArea", airbnb.getCity() + ", " + airbnb.getNeighborhood());
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindUsers.jsp.
        	messages.put("previousAirbnbId", airbnbId);
        }
		req.setAttribute("airbnbs", airbnbs);
        req.setAttribute("restaurants", restaurants);
        req.getRequestDispatcher("/RestaurantsNearby.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Restaurant> restaurants = new ArrayList<>();
        List<Airbnbs> airbnbs = new ArrayList<>();
        
        // Retrieve and validate parameter.
        String airbnbId = req.getParameter("airbnb_id");
        String radius = req.getParameter("radius");
        String pageStr = req.getParameter("page");
        String itemsPerPageStr = req.getParameter("itemsPerPage");
        
		if (airbnbId == null || airbnbId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid id.");
        } else if (radius == null || radius.trim().isEmpty()) {
            messages.put("success", "Please enter a valid radius.");
        } else {
        	try {
        		Airbnbs airbnb = airbnbsDao.getAirbnbById(airbnbId);
        		int page = Integer.parseInt(pageStr);
                int itemsPerPage = Integer.parseInt(itemsPerPageStr);
        		restaurants = restaurantsDao.getNearbyRestaurants(page, itemsPerPage, airbnb.getLatitude(),airbnb.getLongitude(), Integer.valueOf(radius));
        		airbnbs.add(airbnb);
        		messages.put("success", "Airbnb: " + airbnb.getName() + "; City: " + airbnb.getCity() + "; Neighborhood: " + airbnb.getNeighborhood());
        		messages.put("airbnbName", airbnb.getName());
        		messages.put("airbnbArea", airbnb.getCity() + ", " + airbnb.getNeighborhood());
        	} catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	
        }
		req.setAttribute("airbnbs", airbnbs);
        req.setAttribute("restaurants", restaurants);
        req.getRequestDispatcher("/RestaurantsNearby.jsp").forward(req, resp);
    }
}
