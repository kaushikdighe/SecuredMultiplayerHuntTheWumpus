package com.swe681.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.swe681.beans.SessionAttribute;
import com.swe681.services.DatabaseServicesCreateGame;

/**
 * Servlet implementation class JoinOldGame
 */
//@WebServlet("/JoinOldGame")
public class JoinOldGame extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinOldGame() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String target="/JoinNewGame";
		String username=(String) request.getSession().getAttribute(SessionAttribute.USER_NAME);
		System.out.println("----in doGet-----");
		HttpSession session=request.getSession();
		String joinedGame=request.getParameter("id");
		boolean isJoined=DatabaseServicesCreateGame.addUserToGame(joinedGame,username);
		if(isJoined){
			session.setAttribute(SessionAttribute.GAME_NAME, joinedGame);
			session.setAttribute(SessionAttribute.GAME_STATUS, SessionAttribute.GAME_CREATED);
			System.out.println("Exiting.............................................");
			target="/loading.jsp";
		}
		RequestDispatcher rd= request.getRequestDispatcher(target);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		doGet(request, response);
		
		
	}

}
