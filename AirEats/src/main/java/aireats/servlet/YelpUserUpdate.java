package aireats.servlet;

import aireats.dal.*;
import aireats.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/yelpuserupdate")
public class YelpUserUpdate extends HttpServlet {
	
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

        // Retrieve user and validate.
        String userId = req.getParameter("userId");
        if (userId == null || userId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid User ID.");
        } else {
        	try {
        		YelpUsers yelpUser = yelpUsersDao.getYelpUserById(userId);
        		if(yelpUser == null) {
        			messages.put("success", "User ID does not exist.");
        		}
        		req.setAttribute("yelpUser", yelpUser);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/YelpUserUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String userId = req.getParameter("userId");
        if (userId == null || userId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid User ID.");
        } else {
        	try {
        		YelpUsers yelpUser = yelpUsersDao.getYelpUserById(userId);
        		if(yelpUser == null) {
        			messages.put("success", "User ID does not exist. No update to perform.");
        		} else {
                    String newReviewCount = req.getParameter("reviewcount");
        			if (newReviewCount == null|| userId.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid review count.");
        	        } else {
        	        	yelpUser = yelpUsersDao.updateUser(yelpUser, Integer.parseInt(newReviewCount));
        	        	messages.put("success", "Successfully updated " + userId);
        	        }
        		}
        		req.setAttribute("yelpUser", yelpUser);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/YelpUserUpdate.jsp").forward(req, resp);
    }
}
