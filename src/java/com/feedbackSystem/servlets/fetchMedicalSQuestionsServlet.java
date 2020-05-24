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
import javax.servlet.http.HttpSession;

import com.feedbackSystem.conn.DBConnection;
import com.feedbackSystem.models.student;
import com.feedbackSystem.models.view2Model;
import com.feedbackSystem.utils.DBUtils;


@WebServlet(urlPatterns = {"/medicalSFeedback"})
public class fetchMedicalSQuestionsServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public fetchMedicalSQuestionsServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException,IOException{
		doPost(request,response);		
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException,IOException{
		
		HttpSession session = request.getSession();
		student newStudent = (student) session.getAttribute("user1");
		String prnNo = newStudent.getPrnNo();
		String errorString="";
		ArrayList<view2Model> newView2 = new ArrayList<view2Model>();
		
		if(prnNo.equals(""))
		{
			errorString="All fields are necessary";
		}
		else
		{
			Connection conn = null;
			try {
					conn = DBConnection.getMySQLConnection();
					conn.setAutoCommit(false);
					
					try {
						 newView2 = DBUtils.giveFeedback(conn,"Medical Services");
					}catch(Exception e){
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
		
		
		request.setAttribute("V2",newView2);
		request.setAttribute("user",newStudent);	
		request.setAttribute("errorString",errorString);
		
		RequestDispatcher dispatcher //
            = this.getServletContext().getRequestDispatcher("/WEB-INF/views/giveMedicalSFeedbackPage.jsp");

	    dispatcher.forward(request, response);

	}
	
	
}
