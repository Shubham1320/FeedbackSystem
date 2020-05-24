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


@WebServlet(urlPatterns = {"/fetchTeacher"})
public class fetchTeacherServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	String qErr,cErr;
	public fetchTeacherServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException,IOException{
		doPost(request,response);		
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException,IOException{
		String T_prnNo =request.getParameter("T_prnNo");
		
		String tName = new String();
		boolean hasError=false;
		String errorString="";
		if(T_prnNo.equals(""))
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
					 tName = DBUtils.fetchTeacher(conn,T_prnNo);
						
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
            request.setAttribute("T_prnNo", T_prnNo);
            // Forward to /WEB-INF/views/login.jsp
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/deleteTeacherPage.jsp");
 
            dispatcher.forward(request, response);
		}
		else
		{
			request.setAttribute("T1", tName);
			 request.setAttribute("T_prnNo", T_prnNo);
			 RequestDispatcher dispatcher //
             = this.getServletContext().getRequestDispatcher("/WEB-INF/views/deleteTeacherPage.jsp");

			 dispatcher.forward(request, response);

		}

	}
	
	
}
