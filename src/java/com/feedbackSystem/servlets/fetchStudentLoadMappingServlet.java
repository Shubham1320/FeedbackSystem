package com.feedbackSystem.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.feedbackSystem.conn.DBConnection;
import com.feedbackSystem.models.questionDetails;
import com.feedbackSystem.models.student;
import com.feedbackSystem.models.view1Model;
import com.feedbackSystem.utils.DBUtils;


@WebServlet(urlPatterns = {"/fetchStudentLoadMapping"})
public class fetchStudentLoadMappingServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public fetchStudentLoadMappingServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException,IOException{
		doPost(request,response);		
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException,IOException{
		HttpSession session = request.getSession();
		student newStudent = (student) session.getAttribute("user1");
		String prnNo = newStudent.getPrnNo();
		
		boolean hasError=false;
		String errorString="";
		ArrayList<view1Model> newView1 = new ArrayList<view1Model>();
		if(prnNo.equals(""))
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
						 newView1 = DBUtils.fetchStudentLoadMapping(conn, prnNo);
						
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
		
		
		request.setAttribute("V1",newView1);
		request.setAttribute("errorString",errorString);
		request.setAttribute("user",newStudent);
		RequestDispatcher dispatcher //
            = this.getServletContext().getRequestDispatcher("/WEB-INF/views/feedbackPage.jsp");

	    dispatcher.forward(request, response);

		

	}
	
	
}
