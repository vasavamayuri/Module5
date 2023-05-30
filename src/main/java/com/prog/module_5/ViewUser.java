package com.prog.module_5;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(name = "viewUser", value = "/viewUser")
public class ViewUser extends HttpServlet {
	protected void
	processRequest(HttpServletRequest request,
				HttpServletResponse response)
		throws ServletException, IOException
	{
		response.setContentType("text/html;charset=UTF-8");
	}
	
	// override the supertype method get
	@Override
	protected void doGet(HttpServletRequest request,
						HttpServletResponse response)
		throws ServletException, IOException
	{
		processRequest(request, response);
		PrintWriter out = response.getWriter();
		
		// assigning integer values to web pages
		String pageId = request.getParameter("page");
		int total = 3;
		int pagesId = Integer.parseInt(pageId);
		if (pagesId == 1) {
		}
		else {
			pagesId = pagesId - 1;
			pagesId = pagesId * total + 1;
		}
		
		// initializing list of users
		List<User> list = null;
		out.println(
			"<a href='/appuser_war_exploded/'>Add user</a>");

		out.print("<h1> User Table: </h1>");
		out.print(
			"<table border='1' cellpadding='4' width='80%'>");
		out.print("<tr><th>Id</th><th>username</th></tr>");
		try {
			// getting all users and assigning to the page
			// numbers
			list = UserDaoHandler.getAllUsers(pagesId,
											total);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		// ensuring list is not null
		if (list != null) {
			// iterating through the list of Users
			// And getting username and id of users.
			for (User user : list) {
				out.print("<tr><td>" + user.getId()
						+ "</td><td>" + user.getUsername()
						+ "</td></tr>");
			}
			
			// printing out in a jsp web format.
			out.print("</table>");
			out.print("<a href='viewUser?page=1'>1</a> ");
			out.print("<a href='viewUser?page=2'>2</a> ");
			out.print("<a href='viewUser?page=3'>3</a> ");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request,
						HttpServletResponse response)
		throws ServletException, IOException
	{
	}
}

