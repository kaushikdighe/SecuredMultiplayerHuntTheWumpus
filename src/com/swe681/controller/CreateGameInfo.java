package com.swe681.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.swe681.beans.SessionAttribute;
import com.swe681.services.DatabaseServicesCreateGame;

/**
 * Servlet implementation class CreateGameInfo
 */
@WebServlet("/CreateGameInfo")
public class CreateGameInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateGameInfo() {
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
		String gameName = request.getParameter("gname");
		String username = request.getParameter("username");
		int gameTime = Integer.parseInt(request.getParameter("gtime"));
		HttpSession httpsession = request.getSession();
		httpsession.setAttribute(SessionAttribute.USER_NAME, username);
		DatabaseServicesCreateGame ds = new DatabaseServicesCreateGame();
		System.out.println("----Username in  CreateGameInfo-----"+username);
		
		String gameStatus=SessionAttribute.GAME_CREATED;
		String errormessage = "";
		
		if(gameName.length()>20)
		{
			errormessage = "Game Name should contain less than 20 characters";
			request.setAttribute("errormessage", errormessage);
			RequestDispatcher rd= request.getRequestDispatcher("/newgameinfo.jsp");
			rd.forward(request, response);
		}
		else if(gameTime<30 || gameTime>120)
		{
			errormessage = "Game Time should be more than 30 Seconds and less than 2 minutes";
			request.setAttribute("errormessage", errormessage);
			RequestDispatcher rd= request.getRequestDispatcher("/newgameinfo.jsp");
			rd.forward(request, response);
		}
		else
		{
			
			if(ds.ifGameAlreadyExists(gameName)>0)
			{
				errormessage = "Game Name already exists choose another Name";
				request.setAttribute("errormessage", errormessage);
				RequestDispatcher rd= request.getRequestDispatcher("/newgameinfo.jsp");
				rd.forward(request, response);
			}
			else
			{
				ds.InsertGame(gameName, gameTime, gameStatus,username);
				httpsession.setAttribute(SessionAttribute.GAME_NAME, gameName);
				httpsession.setAttribute(SessionAttribute.GAME_STATUS, gameStatus);
				httpsession.setAttribute(SessionAttribute.USER_NAME, username);
				RequestDispatcher rd= request.getRequestDispatcher("/loading.jsp");
				rd.forward(request, response);
			}
			
			
		}
		
		
		
	}

}
