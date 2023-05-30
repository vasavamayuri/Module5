package com.prog.module_5;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHandler {
	public static Connection connectDB()
	{
		// connection object
		Connection connection = null;
		try {
			// returns the class object
			Class.forName("com.mysql.jdbc.Driver");
			// it creates a connection to the database using
			// the url
			connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/appuserdb",
				"adminuser", "user123");
		}

		catch (Exception message) {
			System.out.println(message);
		}
		return connection;
	}

	public static int addUser(User user) throws SQLException
	{
		int result = 0;
		Connection connect = UserDaoHandler.connectDB();
		
		// SQL statement is precompiled and stored in a
		// PreparedStatement object.
		PreparedStatement preparedStatement
			= connect.prepareStatement(
				"insert into user(username,password) values (?,?)");
		
		// set the parameter to the given Java String value
		preparedStatement.setString(1, user.getUsername());
		preparedStatement.setString(2, user.getPassword());
		
		// execute SQl statement insert values to the
		// database
		result = preparedStatement.executeUpdate();
		
		// close the database connection
		connect.close();
		return result;
	}
	public static int updateUser(User user)
		throws SQLException
	{
		int result = 0;
		
		// create connection at the call of this method
		Connection connect = UserDaoHandler.connectDB();
		
		// SQL statement is precompiled and stored in a
		// PreparedStatement object
		PreparedStatement preparedStatement
			= connect.prepareStatement(
				"update user set username=?,password=? where id=?");
		
		// set the parameter to the given Java String value
		preparedStatement.setString(1, user.getUsername());
		preparedStatement.setString(2, user.getPassword());
		
		// execute SQl statement, insert values to the
		// database
		result = preparedStatement.executeUpdate();
		
		// close the database connection
		connect.close();
		return result;
	}
	public static int deleteUser(int id) throws SQLException
	{
		int result = 0;
		
		// create connection at the call of this method
		Connection connect = UserDaoHandler.connectDB();
		
		// SQL statement is precompiled and stored in a
		// PreparedStatement object
		PreparedStatement preparedStatement
			= connect.prepareStatement(
				"delete from USER where id =?");
		// set the integer value to the user id,
		preparedStatement.setInt(1, id);
		
		// execute SQl statement, insert values to the
		// database
		result = preparedStatement.executeUpdate();
		
		// close the database connection
		connect.close();
		
		return result;
	}
	public static User getUserById(int id)
		throws SQLException
	{
		// create a user object
		User user = new User();
		
		// create connection at the call of the method
		Connection connect = UserDaoHandler.connectDB();
		
		// SQL statement is precompiled and stored in a
		// PreparedStatement object
		PreparedStatement preparedStatement
			= connect.prepareStatement(
				"select * from USER where id=?");
		
		// set the integer value to the user id,
		preparedStatement.setInt(1, id);
		
		// A table of data representing a database result
		// set,generated after the query
		ResultSet resultSet
			= preparedStatement.executeQuery();
		
		// checking for saved fields,if more than one
		if (resultSet.next()) {
			// value of the column is assigned to the set
			// method
			user.setId(resultSet.getInt(1));
			user.setUsername(resultSet.getString(2));
			user.setPassword(resultSet.getString(3));
		}
		
		// close the database connection
		connect.close();
		return user;
	}
	public static List<User> getAllUsers(int start,
										int total)
		throws SQLException
	{
		// creating an empty arraylist of type User.
		List<User> list = new ArrayList<User>();
		
		// create connection at the call of the method
		Connection connect = UserDaoHandler.connectDB();
		
		// SQL statement and telling it to select from the
		// first index
		PreparedStatement preparedStatement
			= connect.prepareStatement(
				"select * from user limit " + (start - 1)
				+ "," + total);
		ResultSet resultSet
			= preparedStatement.executeQuery();
		
		// this keep iterating the list of user
		// setting the values to the corresponding integer
		while (resultSet.next()) {
			User user = new User();
			user.setId(resultSet.getInt(1));
			user.setUsername(resultSet.getString(2));
			user.setPassword(resultSet.getString(3));
			// store the values into the list
			list.add(user);
		}
		
		// close the database connection
		connect.close();
		
		return list;
	}
}

