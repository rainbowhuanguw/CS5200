package aireats.servlet;

import aireats.dal.*;
import aireats.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/airbnbcreate")
public class AirbnbCreate extends HttpServlet {
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
        // Just render the JSP.
        req.getRequestDispatcher("/AirbnbCreate.jsp").forward(req, resp);
    }
    
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate input.
        String airbnbId = req.getParameter("airbnbId");
        if (airbnbId == null || airbnbId.trim().isEmpty()) {
            messages.put("success", "Invalid AirbnbId");
        } else {
            // Create the Airbnb.
            int hostId = Integer.parseInt(req.getParameter("hostId"));
            String name = req.getParameter("name");
            String city = req.getParameter("city");
            String neighborhood = req.getParameter("neighborhood");
            String state = req.getParameter("state");
            double latitude = Double.parseDouble(req.getParameter("latitude"));
            double longitude = Double.parseDouble(req.getParameter("longitude"));

            try {
                Airbnbs airbnb = new Airbnbs(airbnbId, hostId, name, city, neighborhood, state, latitude, longitude);
                airbnb = airbnbsDao.create(airbnb);
                messages.put("success", "Successfully created Airbnb with ID " + airbnbId);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }
        
        req.getRequestDispatcher("/AirbnbCreate.jsp").forward(req, resp);
    }
    
}
