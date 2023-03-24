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

@WebServlet("/yelpusercreate")
public class YelpUserCreate extends HttpServlet {

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
		// Just render the JSP.
		req.getRequestDispatcher("/YelpUserCreate.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		// Retrieve and validate name.
		String userId = req.getParameter("userId");
		String userName = req.getParameter("username");
		if (userId == null || userId.trim().isEmpty()) {
			messages.put("success", "Invalid User ID");
		} else if (userName == null || userName.trim().isEmpty()) {
			messages.put("success", "Invalid UserName");
		} else {
			// Create the YelpUser.
			// String firstName = req.getParameter("firstname");
			// String lastName = req.getParameter("lastname");
			// dob must be in the format yyyy-mm-dd.
			// DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			// String stringDob = req.getParameter("dob");
			// Date dob = new Date();
			// try {
			// dob = dateFormat.parse(stringDob);
			// } catch (ParseException e) {
			// e.printStackTrace();
			// throw new IOException(e);
			// }
			try {
				// Exercise: parse the input for StatusLevel.
				YelpUsers yelpUser = new YelpUsers(userId, userName);
				yelpUser = yelpUsersDao.create(yelpUser);
				messages.put("success", "Successfully created " + userName);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		}

		req.getRequestDispatcher("/YelpUserCreate.jsp").forward(req, resp);
	}
}
