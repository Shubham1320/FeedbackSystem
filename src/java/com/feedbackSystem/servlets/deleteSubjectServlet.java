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


@WebServlet(urlPatterns = {"/deleteSubject"})
public class deleteSubjectServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	String qErr,cErr;
	public deleteSubjectServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException,IOException{
		
		 ArrayList<String> fetchSubject=new ArrayList<String>();
	       Connection conn = null;
			try {
					conn = DBConnection.getMySQLConnection();
					conn.setAutoCommit(false);
					
					try {
						System.out.println("Helloooo Shubhs");
						fetchSubject=DBUtils.fetchAllSubject(conn);
						System.out.println(fetchSubject.get(0));
					}catch(Exception e){
							e.printStackTrace();
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
				request.setAttribute("subjectName1", fetchSubject);
				RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/deleteSubjectPage.jsp");
		
				dispatcher.forward(request, response);	
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException,IOException{
		String subject =request.getParameter("S1");
	
		
		boolean hasError=false;
		String errorString1="";
		if(subject.equals(""))
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
						 DBUtils.deleteSubject(conn,subject);
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
            request.setAttribute("Q1", subject);
            // Forward to /WEB-INF/views/login.jsp
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/deleteSubjectPage.jsp");
 
            dispatcher.forward(request, response);
		}
		else
		{
			subject="";
			request.setAttribute("Q1", subject);
			 RequestDispatcher dispatcher //
             = this.getServletContext().getRequestDispatcher("/WEB-INF/views/deleteSubjectPage.jsp");

			 dispatcher.forward(request, response);
		}
	}	
}
