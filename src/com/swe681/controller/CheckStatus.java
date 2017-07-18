package com.swe681.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.swe681.beans.SessionAttribute;
import com.swe681.services.DatabaseServicesCreateGame;

/**
 * Servlet implementation class CheckStatus
 */
@WebServlet("/CheckStatus")
public class CheckStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckStatus() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String result="";
		String userJoinedStr = "";
		String msg=(String) request.getParameter("msg");
		System.out.println(msg);
		String gameName=(String) request.getSession().getAttribute(SessionAttribute.GAME_NAME);
		System.out.println(gameName);
		System.out.println(request.getSession());

		if(msg.equals("USER2JOINED"))
		{
			
			userJoinedStr=DatabaseServicesCreateGame.isUserJoinedGame(gameName);
			if(userJoinedStr!=null&&userJoinedStr.length()!=0){
				result=userJoinedStr;
			}

		}
		else if(msg.equals("STARTGAME"))
		{
			
			boolean isChanged=DatabaseServicesCreateGame.changeGameStatus(gameName, SessionAttribute.GAME_PLAYING);
			if(isChanged){
				result="STARTGAME";
			}
		}
		else{
			String name = null;
			name = "Hello"+"Akshay";
		}
		//System.out.println("-----user 2 information----" + result);
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(result);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
