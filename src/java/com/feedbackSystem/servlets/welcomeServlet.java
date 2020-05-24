package com.feedbackSystem.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.feedbackSystem.models.student;



@WebServlet(urlPatterns = {"/welcome"})
public class welcomeServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	public welcomeServlet() {
		super();
	}
	
	@Override
	   protected void doGet(HttpServletRequest request, HttpServletResponse response)
	           throws ServletException, IOException {
	       HttpSession session = request.getSession();

	  
	       student user = (student) session.getAttribute("user1");

	       request.setAttribute("user", user);

	       RequestDispatcher dispatcher 
	               = this.getServletContext().getRequestDispatcher("/WEB-INF/views/welcome.jsp");
	       dispatcher.forward(request, response);

	   }

	   @Override
	   protected void doPost(HttpServletRequest request, HttpServletResponse response)
	           throws ServletException, IOException {
	       doGet(request, response);
	   }

	
}
