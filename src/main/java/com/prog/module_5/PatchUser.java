package com.prog.module_5;

import static java.lang.System.out;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(name = "patchUser", value = "/patchUser")
public class PatchUser extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request,
						HttpServletResponse response)
		throws ServletException, IOException
	{
	}

	@Override
	protected void doPost(HttpServletRequest request,
						HttpServletResponse response)
		throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		String userId = request.getParameter("id");
		int id = Integer.parseInt(userId);
		String username = request.getParameter("name");
		String password = request.getParameter("password");
		User user = new User();
		user.setId(id);
		user.setUsername(username);
		user.setPassword(password);
		try {
			int result = UserDaoHandler.updateUser(user);
			if (result > 0) {
				response.sendRedirect("viewUser? page =1");
			}
			else {
				out.print("unable to connect");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		out.close();
	}
}

