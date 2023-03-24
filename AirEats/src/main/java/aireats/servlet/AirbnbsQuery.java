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

@WebServlet("/airbnbsquery")
public class AirbnbsQuery extends HttpServlet {

	protected AirbnbsDao airbnbsDao;
	
	@Override
	public void init() throws ServletException {
		airbnbsDao = AirbnbsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Airbnbs> airbnbs = new ArrayList<>();
        
        // Retrieve and validate parameter.
        String airbnbName = req.getParameter("airbnbName");
		if (airbnbName == null || airbnbName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid id.");
        } else {
        	try {
        		airbnbs = airbnbsDao.getAirbnbByName(airbnbName);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + airbnbName);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindUsers.jsp.
        	messages.put("previous Airbnb Name", airbnbName);
        }
        req.setAttribute("airbnbs", airbnbs);
        
        req.getRequestDispatcher("/AirbnbsQuery.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Airbnbs> airbnbs = new ArrayList<>();
        
        // Retrieve and validate name.
        String airbnbName = req.getParameter("airbnbName");
		if (airbnbName == null || airbnbName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid id.");
        } else {
        	try {
        		airbnbs = airbnbsDao.getAirbnbByName(airbnbName);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + airbnbName);
        }
		req.setAttribute("airbnbs", airbnbs);
        
        req.getRequestDispatcher("/AirbnbsQuery.jsp").forward(req, resp);
    }

}
