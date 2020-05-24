package com.feedbackSystem.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.feedbackSystem.conn.DBConnection;
import com.feedbackSystem.utils.DBUtils;


@WebServlet(urlPatterns = {"/fetchQuestion"})
public class fetchQuestionServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	String qErr,cErr;
	public fetchQuestionServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException,IOException{
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/deleteQuestionPage.jsp");
		
		dispatcher.forward(request, response);		
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException,IOException{
		String category =request.getParameter("category");
		
		ArrayList<String> question = new ArrayList<String>();
		boolean hasError=false;
		String errorString="";
		if(category.equals(""))
		{
			hasError=true;
			errorString="All fields are necessary";
		}
		else
		{
			Connection conn = null;
			try {
					conn = DBConnection.getMySQLConnection();
					conn.setAutoCommit(false);
					
					try {
						 question = DBUtils.fetchQuestion(conn,category);
					}catch(Exception e){
							hasError = true;
							errorString = e.getMessage();
						}
						conn.commit();
					} catch (Exception e) {
						try {
							conn.rollback();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						e.printStackTrace();
					}finally {
			            try {
			            	conn.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
			        }
		}
		if(hasError)
		{
            request.setAttribute("errString", errorString);
 
            // Forward to /WEB-INF/views/login.jsp
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/deleteQuestionPage.jsp");
 
            dispatcher.forward(request, response);
		}
		else
		{
			request.setAttribute("question1", question);
			 RequestDispatcher dispatcher //
             = this.getServletContext().getRequestDispatcher("/WEB-INF/views/deleteQuestionPage.jsp");

			 dispatcher.forward(request, response);

		}

	}
	
	
}
