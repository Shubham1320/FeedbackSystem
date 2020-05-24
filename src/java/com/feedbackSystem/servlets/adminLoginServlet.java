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
import com.feedbackSystem.models.admin;
import com.feedbackSystem.utils.DBUtils;


@WebServlet( urlPatterns = {"/adminLogin"}) 
public class adminLoginServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	public adminLoginServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException,IOException{
		
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException,IOException{
		System.out.println("Shubham");
		boolean hasError = false;
		String errString = null;
		String adminId = request.getParameter("adminId");
		String adminPassword = request.getParameter("adminPassword");
		admin user = new admin();
		if(adminId.equals("") || adminPassword.equals(""))
		{
			hasError = true;
			errString = "AdminId and Password are required";
		}
		else
		{
			Connection conn = null;
			try {
					conn = DBConnection.getMySQLConnection();
					conn.setAutoCommit(false);
					
					try {
						
						user = DBUtils.findAdmin(conn,adminId,adminPassword);
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
			user = new admin();
            user.setAdminId(adminId);
            user.setAdminPassword(adminPassword);
 
            request.setAttribute("errString1", errString);
            request.setAttribute("user", user);
 
            // Forward to /WEB-INF/views/login.jsp
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/homePage.jsp");
 
            dispatcher.forward(request, response);
		}
		else
		{
			  HttpSession session = request.getSession();
			  session.setAttribute("user", user);
			  response.sendRedirect(request.getContextPath() + "/welcome1");
			  
		}
	}
		
		
		
		
}
	

