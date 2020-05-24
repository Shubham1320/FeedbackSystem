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


@WebServlet(urlPatterns = {"/deleteTeacher"})
public class deleteTeacherServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	String qErr,cErr;
	public deleteTeacherServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException,IOException{
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/deleteTeacherPage.jsp");
		
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException,IOException{
		String t_prnNo =request.getParameter("T_prnNo");
	
		
		boolean hasError=false;
		String errorString1="";
		if(t_prnNo.equals(""))
		{
			hasError=true;
			errorString1="Couldn't fetch/empty Please try again";
		}
		else
		{
			Connection conn = null;
			try {
					conn = DBConnection.getMySQLConnection();
					conn.setAutoCommit(false);
					
					try {
						 DBUtils.deleteTeacher(conn,t_prnNo);
					}catch(Exception e){
							hasError = true;
							errorString1 = e.getMessage();
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
            request.setAttribute("errString", errorString1);
            request.setAttribute("T_prnNo",t_prnNo);
            // Forward to /WEB-INF/views/login.jsp
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/deleteTeacherPage.jsp");
 
            dispatcher.forward(request, response);
		}
		else
		{
			RequestDispatcher dispatcher //
            = this.getServletContext().getRequestDispatcher("/WEB-INF/views/deleteTeacherPage.jsp");

			dispatcher.forward(request, response);

		}
	}	
}
