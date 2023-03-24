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

@WebServlet("/hostsquery")
public class HostsQuery extends HttpServlet{
	protected HostsDao hostsDao;
	
	@Override
	public void init() throws ServletException {
		hostsDao = HostsDao.getInstance();
	}
	
	@Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        Hosts host = null;

        // Retrieve and validate HostId.
        String hostIdString = req.getParameter("hostId");
        if (hostIdString == null || hostIdString.trim().isEmpty()) {
            messages.put("success", "Please enter a valid HostId.");
        } else {
            try {
                int hostId = Integer.parseInt(hostIdString);
                host = hostsDao.getHostByHostId(hostId);
                if (host == null) {
                    messages.put("success", "Host with ID " + hostId + " not found.");
                } else {
                    messages.put("success", "Displaying results for Host ID " + hostId);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }
        req.setAttribute("host", host);

        req.getRequestDispatcher("/HostsQuery.jsp").forward(req, resp);
    }
	
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        Hosts host = null;

        // Retrieve and validate HostId.
        String hostIdString = req.getParameter("hostId");
        if (hostIdString == null || hostIdString.trim().isEmpty()) {
            messages.put("success", "Please enter a valid HostId.");
        } else {
            try {
                int hostId = Integer.parseInt(hostIdString);
                host = hostsDao.getHostByHostId(hostId);
                if (host == null) {
                    messages.put("success", "Host with ID " + hostId + " not found.");
                } else {
                    messages.put("success", "Displaying results for Host ID " + hostId);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }
        req.setAttribute("host", host);

        req.getRequestDispatcher("/HostsQuery.jsp").forward(req, resp);
    }
}
