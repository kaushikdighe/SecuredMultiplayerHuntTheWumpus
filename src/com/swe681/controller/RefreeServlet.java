package com.swe681.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.swe681.services.DatabaseService;



/**
 * Servlet implementation class RefreeServlet
 */
@WebServlet("/RefreeServlet")
public class RefreeServlet extends HttpServlet implements Runnable {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RefreeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(ServletConfig config)
    {
    	
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
		String userId = request.getParameter("id");
		String action = request.getParameter("action");
		
		if(action.equals("new"))
		{
			//TODO Redirect to new page with a fresh session, generate session ID.
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			String sessionID = userId + timestamp.toString();
			DatabaseService databaseService = new DatabaseService();
			databaseService.insertNewSession(sessionID);
			
		}else if(action.equals("join")){
			
			//TODO Present with active session ID list.
			DatabaseService databaseService = new DatabaseService();
			ArrayList<String> sessionList = databaseService.getAllActiveSessions();
			HttpSession session = request.getSession();
			session.setAttribute("sessionlist", sessionList);
			
		}
		
		doGet(request, response);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		Timer timer = new Timer("timer",true);
		
		
	}

}
