package com.feedbackSystem.servlets;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.feedbackSystem.conn.DBConnection;
import com.feedbackSystem.models.student;
import com.feedbackSystem.utils.DBUtils;


@WebServlet( urlPatterns = {"/studentLogin"}) 
public class studentLoginServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	public studentLoginServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException,IOException{
		
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException,IOException{
		System.out.println("Hello");
		boolean hasError = false;
		String errString = null;
		String prnNo = request.getParameter("prnNo");
		String password = request.getParameter("password");
		student user = new student();
		if(prnNo.equals("") || password.equals(""))
		{
			hasError = true;
			errString = "Prn No and Password are required";
		}
		else
		{
			Connection conn = null;
			try {
					conn = DBConnection.getMySQLConnection();
                                        System.out.println("Connection Done");
					conn.setAutoCommit(false);
					
					try {
						
						user = DBUtils.findUser(conn, prnNo, password);
						if(user == null) 
						{
							hasError = true;
							errString = "Incorrect Details";
						}
							
						
					}catch(Exception e)
					{
						hasError = true;
						errString = e.getMessage();
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
			user = new student();
            user.setPrnNo(prnNo);
            user.setPassword(password);
 
            request.setAttribute("errString", errString);
            request.setAttribute("user", user);
 
            // Forward to /WEB-INF/views/login.jsp
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/homePage.jsp");
 
            dispatcher.forward(request, response);
		}
		else
		{
			  HttpSession session = request.getSession();
			  session.setAttribute("user1", user);
			  response.sendRedirect(request.getContextPath() + "/welcome");
			  
		}
	}
		
		
		
		
}
	

