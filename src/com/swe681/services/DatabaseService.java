package com.swe681.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class DatabaseService {
	private static String connectionString = "jdbc:mysql://swe645assignment4.cxfdxaz2eghd.us-east-1.rds.amazonaws.com:3306/swe645assignment4";
	private static String userName = "kdighe_swe645";
	private static String password = "kdighe_swe645";
	private static Connection con=null;
	protected static Connection createDbConnection() 
	{
		try {
			if(con!=null&&con.isClosed()!=true)
				return con;
			else
				con=createConnection();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

	private static Connection createConnection()
	{
		Connection con = null;
		System.out.println("---Connection String is -------"+connectionString);
		System.out.println("------Creating RDS MySQL connection-------");
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(connectionString, userName, password);
		} 
		catch (Exception e) 
		{
			System.out.println("------Error connecting-------");
			e.printStackTrace();
		}
		return con;
	}



	public void insertNewSession(String sessionID)
	{
		Connection conn = null;
		PreparedStatement preparedStatement = null;

		conn = createDbConnection();

		try {
			preparedStatement = conn.prepareStatement("INSERT INTO swe645assignment4.sessions VALUES(?,?)");
			preparedStatement.setString(1, sessionID);
			preparedStatement.setString(2,"ACTIVE");


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	public ArrayList<String> getAllActiveSessions()
	{
		Connection conn = null;
		PreparedStatement statement;
		ArrayList<String> sessionlist = new ArrayList<String>();
		String selectQuery = "SELECT * FROM swe645assignment4.sessions; ";
		conn = createDbConnection();

		ResultSet rs;
		try {
			statement = conn.prepareStatement(selectQuery);
			rs = statement.executeQuery();
			while(rs.next())
			{
				sessionlist.add(rs.getString("sessionid"));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return sessionlist;
	}

	public int InsertUser(String firstName, String lastName, String uName, String pword)
	{


		Connection conn = null;
		String insertQuery = "";
		int row  = 0;
		System.out.println("-----------------In InsertStudentDetails function-------------------");
		PreparedStatement preparedStamenet = null;

		System.out.println(firstName + lastName + uName +pword);

		try
		{
			conn = createDbConnection();

			preparedStamenet = conn.prepareStatement("INSERT into swe645assignment4.users(fname,lname,username,pword) values(?,?,?,?)");
			preparedStamenet.setString(1, firstName);
			preparedStamenet.setString(2, lastName);
			preparedStamenet.setString(3, uName);
			preparedStamenet.setString(4, pword);

			preparedStamenet.executeUpdate();

			System.out.println("------query------"+ preparedStamenet.toString());
		}
		catch(Exception e)
		{
			System.out.println("----------Error in InsertListing-------");
			e.printStackTrace();
		}
		
		return row;
	}

	public StringBuilder validation(String firstName, String lastName, String uname, String pword)
	{

		StringBuilder errormsg= new StringBuilder();


		if(firstName.length()<1 || firstName==null)
		{
			errormsg.append("Please enter First Name");
			errormsg.append("\n");
		}
		else if(!firstName.matches("^[a-zA-Z]{1,20}$"))
		{
			errormsg.append("Please enter valid First Name");
			errormsg.append("\n");
		}

		if(lastName.length()<1 || lastName==null)
		{
			errormsg.append("Please enter Last Name");
			errormsg.append("\n");
		}
		else if(!lastName.matches("^[a-zA-Z]{1,20}$"))
		{
			errormsg.append("Please enter valid Last Name");
			errormsg.append("\n");
		}

		if(uname.length()<1 || uname==null)
		{
			errormsg.append("Please enter User Name");
			errormsg.append("\n");
		}
		else if(!uname.matches("^[A-Za-z0-9]{5,20}$"))
		{
			errormsg.append("Please enter valid User Name");
			errormsg.append("\n");
		}

		if(pword.length()<1 || pword==null)
		{
			errormsg.append("Please enter password");
			errormsg.append("\n");
		}
		else if(!pword.matches("^([a-zA-Z0-9#@$%&*]){8,20}$"))
		{
			errormsg.append("Please enter valid password");
			errormsg.append("\n");
		}
		return errormsg;

	}

	public int userExists(String uname)
	{
		Connection conn = null;
		//String insertQuery = "";
		int row  = 0;
		System.out.println("-----------------In Check User Exist function-------------------"+uname);
		PreparedStatement preparedStamenet = null;
		String query = null;
		String userId="";

		try
		{
			conn = createDbConnection();
			System.out.println(query);
			query = "SELECT userid FROM swe645assignment4.users WHERE username = ?";
			preparedStamenet = conn.prepareStatement(query);
			preparedStamenet.setString(1, uname);
			ResultSet rs = preparedStamenet.executeQuery();

			while(rs.next())
			{
				row++;

			}

			System.out.println("---Row value is---"+row);
		}
		catch(Exception e)
		{
			System.out.println("----------Error in InsertListing-------");
			e.printStackTrace();
		}
		return row;

	}

}
