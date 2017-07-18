package com.swe681.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.swe681.beans.SessionAttribute;
import com.swe681.services.DatabaseService;
import com.swe681.services.HashPassword;

/**
 * Servlet implementation class Login
 */
//@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		DatabaseService ds = new DatabaseService();
		HashPassword hp = new HashPassword();
		boolean correctPassword =  false;
		HttpSession httpSession = request.getSession();
		String errorMessage = " ";
		
		String page = request.getParameter("page");
		//String menuPage = request.getParameter("menuPage");
		
		String userName = request.getParameter("uname");
		String pwd = request.getParameter("password");
		
		int checkUser = ds.userExists(userName);
		correctPassword = hp.hashLoginPassword(pwd, userName);
		
		
		if(page.equals("Not a User Yet?"))
		{
			RequestDispatcher rd= request.getRequestDispatcher("/signup.jsp");
			rd.forward(request, response);
		}
		else
		{
			
			if((userName.equalsIgnoreCase("") || userName.equals(null))||(pwd.equalsIgnoreCase("")||pwd.equals(null))){
				errorMessage = "UserName or Password is empty!";
				request.setAttribute("errormessage", errorMessage);
				RequestDispatcher rd= request.getRequestDispatcher("/login.jsp");
				rd.forward(request, response);
			}
			else if(correctPassword && checkUser>0)
			{	
				//pw.println("Welcome"+userName);
				httpSession.setAttribute(SessionAttribute.USER_NAME, userName);
				RequestDispatcher rd= request.getRequestDispatcher("/menu.jsp");
				//rd.include(request, response);
				rd.forward(request, response);
			}
			else
			{
				if(checkUser == 0)
				{
					errorMessage = "User does not exists";
					request.setAttribute("errormessage", errorMessage);
					RequestDispatcher rd= request.getRequestDispatcher("/login.jsp");
					rd.forward(request, response);
				}
				else
				{
					errorMessage = "Username or Password are invalid";
					request.setAttribute("errormessage", errorMessage);
					RequestDispatcher rd= request.getRequestDispatcher("/login.jsp");
					rd.forward(request, response);
				}
				
				
			}	
			
		}
	}

}
