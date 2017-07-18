package com.swe681.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.swe681.beans.CreateGameBean;
import com.swe681.services.DatabaseServicesCreateGame;

/**
 * Servlet implementation class JoinGame
 */
@WebServlet("/JoinNewGame")
public class JoinGame extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinGame() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//CreateGameBean gameBean = new CreateGameBean();
		ArrayList<String> gameList = new ArrayList<String>();
		RequestDispatcher request_Dispatcher;
		HttpSession httpsession = request.getSession();
		DatabaseServicesCreateGame ds = new DatabaseServicesCreateGame();
		gameList = ds.getGameInfo();
		httpsession.setAttribute("gameList", gameList);
		request_Dispatcher = request.getRequestDispatcher("/JoinGame.jsp");
		request_Dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}

}
