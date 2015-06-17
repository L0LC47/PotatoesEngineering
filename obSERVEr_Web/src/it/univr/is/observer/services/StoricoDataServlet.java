package it.univr.is.observer.services;

/*
 * The login servlet instantiates a Bean that is of type "UserBean", 
 * and then calls the DAO named "UserDAO".
 * 1. UserBean is a class representing the User table in our Database 
 * (where each column in the user table has a corresponding instance variable 
 * with a setter and a getter method).
 * 2. DAO(data access object) contains methods needed to communicate with 
 * the data source. The only needed method is the login method that checks 
 * if the username and password inputted by the user are valid or not.
 * */

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
public class StoricoDataServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
		response.setContentType("text/html");
		
		String data;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			data = request.getParameter("data");
			date = format.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("dataSelezionata", date);
		response.sendRedirect("userStoricoMappa.jsp");
	}
}