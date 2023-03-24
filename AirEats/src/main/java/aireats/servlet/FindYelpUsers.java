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

public class FindYelpUsers extends HttpServlet {
	protected YelpUsersDao yelpUsersDao;
	@Override
	public void init() throws ServletException {
		yelpUsersDao = YelpUsersDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        YelpUsers yelpUsers = new YelpUsers(null);
        
        // Retrieve and validate name.
        // user ID is retrieved from the URL query string.
        String userId = req.getParameter("userId");
        if (userId == null || userId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
        	// Retrieve BlogUsers, and store as a message.
        	try {
            	yelpUsers = yelpUsersDao.getYelpUserById(userId);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + userId);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindUsers.jsp.
        	messages.put("User ID", userId);
        }
        req.setAttribute("yelpUsers", yelpUsers);
        
        req.getRequestDispatcher("/FindYelpUsers.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        YelpUsers yelpUsers = new YelpUsers(null);
        
        // Retrieve and validate name.
        // ID is retrieved from the form POST submission. By default, it
        // is populated by the URL query string (in FindUsers.jsp).
        String userId = req.getParameter("userId");
        if (userId == null || userId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
        	// Retrieve BlogUsers, and store as a message.
        	try {
        		yelpUsers = yelpUsersDao.getYelpUserById(userId);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + userId);
        }
        req.setAttribute("yelpUsers", yelpUsers);
        
        req.getRequestDispatcher("/FindYelpUsers.jsp").forward(req, resp);
    }
	
	
}
