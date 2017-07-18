package com.swe681.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.swe681.services.DatabaseService;
import com.swe681.services.HashPassword;

/**
 * Servlet implementation class SignUp
 */
//@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		System.out.println("-------------- inside SingUp doGet-----------------");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		
		System.out.println("-------------- inside SingUp doPost-----------------");
		//String button = request.getParameter("task");
		String button = request.getParameter("newuser");
		String fname= request.getParameter("fname");
		String lname= request.getParameter("lname");
		String username= request.getParameter("username");
		String pword= request.getParameter("createPassword");
		HttpSession httpSession = request.getSession();
		
			System.out.println(fname+lname+username+pword);
			HashPassword hp = new HashPassword();
			DatabaseService ds = new DatabaseService();
			
			System.out.println("-------Password was hashed----------------");
			int checkUser = ds.userExists(username);
			System.out.println("This user exists:" + checkUser);
			StringBuilder svalidation= new StringBuilder();
			
			String userCheckError = "UserName already exists";
			
			svalidation=ds.validation(fname, lname, username, pword);
			System.out.println("This is error message:" + svalidation);
			
			System.out.println(svalidation);
			if((svalidation == null || (svalidation.length()<1)) && checkUser==0)
			{
				pword = hp.hashSignUpPassword(pword);
				httpSession.setAttribute("username", username);
				int inserteduser = ds.InsertUser(fname, lname, username, pword);
				System.out.println("----Inserted User is---"+inserteduser);
				RequestDispatcher rd= request.getRequestDispatcher("/menu.jsp");
				rd.forward(request, response);
			}
			else
			{
				if(!(svalidation.length()<1))
				{
					request.setAttribute("errormessage", svalidation.toString());
				}
				else
				{
					request.setAttribute("errormessage", userCheckError.toString());
				}
				
				RequestDispatcher rd= request.getRequestDispatcher("/signup.jsp");
				rd.forward(request, response);
			}
			
	}

}
