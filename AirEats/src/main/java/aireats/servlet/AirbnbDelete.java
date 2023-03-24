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

@WebServlet("/airbnbdelete")
public class AirbnbDelete extends HttpServlet {
    
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
        // Provide a title and render the JSP.
        messages.put("title", "Delete Airbnb");
        req.getRequestDispatcher("/AirbnbDelete.jsp").forward(req, resp);
    }
    
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate AirbnbId.
        String airbnbId = req.getParameter("airbnbId");
        if (airbnbId == null || airbnbId.trim().isEmpty()) {
            messages.put("title", "Invalid AirbnbId");
            messages.put("disableSubmit", "true");
        } else {
            // Delete the Airbnb.
            Airbnbs airbnb = new Airbnbs(airbnbId);
            try {
                airbnb = airbnbsDao.delete(airbnb);
                // Update the message.
                if (airbnb == null) {
                    messages.put("title", "Successfully deleted Airbnb with ID " + airbnbId);
                    messages.put("disableSubmit", "true");
                } else {
                    messages.put("title", "Failed to delete Airbnb with ID " + airbnbId);
                    messages.put("disableSubmit", "false");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }
        
        req.getRequestDispatcher("/AirbnbDelete.jsp").forward(req, resp);
    }
}

