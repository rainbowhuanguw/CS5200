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


@WebServlet("/yelpuserdelete")
public class YelpUserDelete extends HttpServlet {
	
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
        // Provide a title and render the JSP.
        messages.put("title", "Delete YelpUser");        
        req.getRequestDispatcher("/YelpUserDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String userId = req.getParameter("userId");
        if (userId == null || userId.trim().isEmpty()) {
            messages.put("title", "Invalid userId");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the BlogUser.
	        YelpUsers yelpUser = new YelpUsers(userId);
	        try {
	        	yelpUser = yelpUsersDao.delete(yelpUser);
	        	// Update the message.
		        if (yelpUser == null) {
		            messages.put("title", "Successfully deleted " + userId);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + userId);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/YelpUserDelete.jsp").forward(req, resp);
    }
}
