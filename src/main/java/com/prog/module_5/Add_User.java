package com.prog.module_5;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(name = "addUser", value = "/addUser")
public class AddUser extends HttpServlet {
	protected void
	processRequest(HttpServletRequest request,
				HttpServletResponse response)
		throws ServletException, IOException
	{
		response.setContentType("text/html;charset=UTF-8");
	}
	@Override
	protected void doGet(HttpServletRequest request,
						HttpServletResponse response)
		throws ServletException, IOException
	{
	}
	
	// override the supertype method post
	@Override
	protected void doPost(HttpServletRequest request,
						HttpServletResponse response)
		throws ServletException, IOException
	{
		processRequest(request, response);
		
		// print object for string formatting
		PrintWriter out = response.getWriter();
		
		// Httpservletrequest get parameters from user
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		// Instances of User class
		User user = new User();
		
		// set the parameters gotten to the 'Username' field
		// of User class
		user.setUsername(username);
		user.setPassword(password);

		int status = 0;
		try {
			// static method add the values stored in the
			// user object to the database
			status = UserDaoHandler.addUser(user);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		// check if the values correspond to the one
		// specified
		if (status > 0) {
			out.print("
<p>Record saved successfully!</p>
");
			request.getRequestDispatcher("index.html")
				.include(request, response);
		}
		else {
			out.println("Sorry! unable to save record");
		}

		// close database connection
		out.close();
	}
}

