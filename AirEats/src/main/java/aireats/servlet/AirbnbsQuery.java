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
	
//	@Override
//	public void doGet(HttpServletRequest req, HttpServletResponse resp)
//			throws ServletException, IOException {
//		// Map for storing messages.
//        Map<String, String> messages = new HashMap<String, String>();
//        req.setAttribute("messages", messages);
//
//        List<Airbnbs> airbnbs = new ArrayList<>();
//        
//        // Retrieve and validate parameter.
//        String airbnbName = req.getParameter("airbnbName");
//		if (airbnbName == null || airbnbName.trim().isEmpty()) {
//            messages.put("success", "Please enter a valid id.");
//        } else {
//        	try {
//        		airbnbs = airbnbsDao.getAirbnbByName(airbnbName);
//            } catch (SQLException e) {
//    			e.printStackTrace();
//    			throw new IOException(e);
//            }
//        	messages.put("success", "Displaying results for " + airbnbName);
//        	// Save the previous search term, so it can be used as the default
//        	// in the input box when rendering FindUsers.jsp.
//        	messages.put("previous Airbnb Name", airbnbName);
//        }
//        req.setAttribute("airbnbs", airbnbs);
//        
//        req.getRequestDispatcher("/AirbnbsQuery.jsp").forward(req, resp);
//	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {
	    Map<String, String> messages = new HashMap<String, String>();
	    req.setAttribute("messages", messages);
	    int page = Integer.parseInt(req.getParameter("page") == null ? "1" : req.getParameter("page"));
	    int offSet = (page - 1)*100;

	    String airbnbId = req.getParameter("airbnbId");
	    String airbnbName = req.getParameter("airbnbName");
	    String city = req.getParameter("city");
	    String state = req.getParameter("state");
	    String hostIdParam = req.getParameter("hostId");

	    if (airbnbId != null && !airbnbId.trim().isEmpty()) {
	        handleGetAirbnbById(req, messages, airbnbId, offSet);
	    } else if (city != null && !city.trim().isEmpty() && state != null && !state.trim().isEmpty() && airbnbName != null && airbnbName.trim().isEmpty()) {
	    	handleGetAirbnbByCityStateName(req, messages, city, state, airbnbName, offSet);
	    } else if (airbnbName != null && !airbnbName.trim().isEmpty()) {
	        handleGetAirbnbByName(req, messages, airbnbName, offSet);
	    } else if (city != null && !city.trim().isEmpty() && state != null && !state.trim().isEmpty()) {
	        handleGetAirbnbsByCityAndState(req, messages, city, state, offSet);
	    } else if (hostIdParam != null && !hostIdParam.trim().isEmpty()) {
	        int hostId = Integer.parseInt(hostIdParam);
	        handleGetAirbnbsByHostId(req, messages, hostId, offSet);
	    } else {
	        messages.put("success", "Please provide at least one valid parameter to search.");
	    }

	    req.getRequestDispatcher("/AirbnbsQuery.jsp").forward(req, resp);
	}
	

	public void handleGetAirbnbById(HttpServletRequest req, Map<String, String> messages, String airbnbId, int offSet) throws IOException {
		try {
	        Airbnbs airbnb = airbnbsDao.getAirbnbById(airbnbId, offSet);
	        req.setAttribute("airbnbs", airbnb);
	        messages.put("success", "Displaying results for " + airbnbId);
	        if (airbnb == null) {
	            messages.put("success", "Airbnb with ID " + airbnbId + " not found.");
	        } else {
	        	req.setAttribute("messages", messages);
	            messages.put("success", "Displaying Airbnb with ID " + airbnbId);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new IOException(e);
	    }
	}

	public void handleGetAirbnbByName(HttpServletRequest req, Map<String, String> messages, String airbnbName, int offSet) throws IOException {
	    try {
	        List<Airbnbs> airbnbs = airbnbsDao.getAirbnbByName(airbnbName, offSet);
	        req.setAttribute("airbnbs", airbnbs);
	        messages.put("success", "Displaying results for " + airbnbName);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new IOException(e);
	    }
	}

	public void handleGetAirbnbsByCityAndState(HttpServletRequest req, Map<String, String> messages, String city, String state, int offSet) throws IOException {
	    try {
	        List<Airbnbs> airbnbs = airbnbsDao.getAirbnbsByCityAndState(city, state, offSet);
	        req.setAttribute("airbnbs", airbnbs);
	        messages.put("success", "Displaying results for " + city + ", " + state);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new IOException(e);
	    }
	}

	public void handleGetAirbnbsByHostId(HttpServletRequest req, Map<String, String> messages, int hostId, int offSet) throws IOException {
	    try {
	        List<Airbnbs> airbnbs = airbnbsDao.getAirbnbsByHostId(hostId, offSet);
	        req.setAttribute("airbnbs", airbnbs);
	        messages.put("success", "Displaying results for host ID " + hostId);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new IOException(e);
	    }
	}

	public void handleGetAirbnbByCityStateName(HttpServletRequest req, Map<String, String> messages, String city, String state, String airbnbName, int offSet) throws IOException {
	    try {
	        List<Airbnbs> airbnbs = airbnbsDao.getAirbnbsByCityStateName(city, state, airbnbName, offSet);
	        req.setAttribute("airbnbs", airbnbs);
	        messages.put("success", "Displaying results for " + city + ", " + state + ", " + airbnbName);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new IOException(e);
	    }
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
	    Map<String, String> messages = new HashMap<String, String>();
	    req.setAttribute("messages", messages);
	    int page = Integer.parseInt(req.getParameter("page") == null ? "1" : req.getParameter("page"));
	    int offSet = (page - 1)*100;

	    String airbnbId = req.getParameter("airbnbId");
	    String airbnbName = req.getParameter("airbnbName");
	    String city = req.getParameter("city");
	    String state = req.getParameter("state");
	    String hostIdParam = req.getParameter("hostId");

	    if (airbnbId != null && !airbnbId.trim().isEmpty()) {
	        handleGetAirbnbById(req, messages, airbnbId, offSet);
	    } else if (city != null && !city.trim().isEmpty() && state != null && !state.trim().isEmpty() && airbnbName != null && !airbnbName.trim().isEmpty()) {
	    	handleGetAirbnbByCityStateName(req, messages, city, state, airbnbName, offSet);
	    } else if (airbnbName != null && !airbnbName.trim().isEmpty()) {
	        handleGetAirbnbByName(req, messages, airbnbName, offSet);
	    } else if (city != null && !city.trim().isEmpty() && state != null && !state.trim().isEmpty()) {
	        handleGetAirbnbsByCityAndState(req, messages, city, state, offSet);
	    } else if (hostIdParam != null && !hostIdParam.trim().isEmpty()) {
	        int hostId = Integer.parseInt(hostIdParam);
	        handleGetAirbnbsByHostId(req, messages, hostId, offSet);
	    } else {
	        messages.put("success", "Please provide at least one valid parameter to search.");
	    }

	    req.getRequestDispatcher("/AirbnbsQuery.jsp").forward(req, resp);
    }

}
