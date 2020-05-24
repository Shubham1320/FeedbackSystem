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

import com.feedbackSystem.conn.DBConnection;
import com.feedbackSystem.utils.DBUtils;



@WebServlet(urlPatterns = {"/deleteStudent"})
public class deleteStudentServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	String errorString = null;
	
	
	public deleteStudentServlet()	{
		super();
	}
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException,IOException{
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/deleteStudentPage.jsp");
		
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException{
	   Boolean hasError = false; 
	   String prnNo = request.getParameter("prnNo1");
	   Connection conn = null;
		try {
				conn = DBConnection.getMySQLConnection();
				conn.setAutoCommit(false);
				
				try {
					DBUtils.deleteStudent(conn,prnNo);
				}catch(SQLException e)
				{
					hasError = true;
					errorString = e.getMessage();
				}
				
				conn.commit();
		} catch (Exception e) {
			System.out.println("Hello1");
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
		
		if(hasError) {
			System.out.println("Delete");
			request.setAttribute("prnNo1",prnNo);
			request.setAttribute("errString",errorString);
			  RequestDispatcher dispatcher 
              = this.getServletContext().getRequestDispatcher("/WEB-INF/views/deleteStudentPage.jsp");

			  dispatcher.forward(request, response);
		}else {
			  RequestDispatcher dispatcher 
            = this.getServletContext().getRequestDispatcher("/WEB-INF/views/deleteStudentPage.jsp");

			  dispatcher.forward(request, response);
		}
	   
	}
}
