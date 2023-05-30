package com.prog.module_5;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(name = "updateUser", value = "/updateUser")
public class UpdateUser extends HttpServlet {
	// override the supertype method post
	@Override
	protected void doGet(HttpServletRequest request,
						HttpServletResponse response)
		throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		
		// collect user id parameter
		String userId = request.getParameter("id");
		
		// the string value is parse as integer to id
		int id = Integer.parseInt(userId);
		try {
			
			// this statement get user by id
			User user = UserDaoHandler.getUserById(id);
			
			// this print the jsp and render web page
			out.println("<h2>Edit User Account</h2>");
			out.print(
				"<form action='patchUser' method='post'>");
			out.print("<table>");
			out.print(
				"<tr><td></td><td><input type='hidden' name='id' value='"
				+ user.getId() + "'/></td></tr>");
			out.print(
				"<tr><td>Name:</td><td><input type='text' name='name' value='"
				+ user.getUsername() + "'/></td></tr>");
			out.print(
				"<tr><td colspan='2'><input type='submit' value='Update'/></td></tr>");
			out.print("</table>");
			out.print("</form>");
			
			// close database connection
			out.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request,
						HttpServletResponse response)
		throws ServletException, IOException
	{
	}
}

