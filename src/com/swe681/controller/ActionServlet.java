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
 * Servlet implementation class ActionServlet
 */
@WebServlet("/ActionServlet")
public class ActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ActionServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String result="";
		String msg=(String) request.getParameter("msg");
		System.out.println("Messageis..........."+msg);
		if(msg.equals("WINNER")||msg.equals("DEAD")){
			String gameName=(String) request.getSession().getAttribute(SessionAttribute.GAME_NAME);
			String userName=(String) request.getSession().getAttribute(SessionAttribute.USER_NAME);
			if(msg.equals("WINNER")||msg.equals("DEAD")){
				result =DatabaseServicesCreateGame.changeUserStatus(gameName,userName,msg);
			}

			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(result);
		}
		else if(msg.equals("CHECK")){
			String gameName=(String) request.getSession().getAttribute(SessionAttribute.GAME_NAME);
			result =DatabaseServicesCreateGame.checkWinner(gameName);

			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(result);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
