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
import com.feedbackSystem.models.view1Model;
import com.feedbackSystem.models.view2Model;
import com.feedbackSystem.utils.DBUtils;


@WebServlet(urlPatterns = {"/giveFeedback1"})
public class fetchFacultyQuestionsServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public fetchFacultyQuestionsServlet() {
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
		System.out.println(newStudent.getFirstName());
		String prnNo = newStudent.getPrnNo();
		String errorString="";
		ArrayList<view2Model> newView2 = new ArrayList<view2Model>();
		
		view1Model newView1 = new view1Model();
		String currentSelected = request.getParameter("current");
		newView1.setO_Year(request.getParameter("o_year"+currentSelected));
		newView1.setO_divNo(Integer.parseInt(request.getParameter("o_divNo"+currentSelected)));
		newView1.setSubjectId(Integer.parseInt(request.getParameter("subjectId"+currentSelected)));
		newView1.settPrnNo((request.getParameter("tPrnNo"+currentSelected)));
		
		System.out.println(newView1.getO_Year());
		System.out.println(newView1.getO_divNo());
		System.out.println(newView1.getSubjectId());
		System.out.println(newView1.gettPrnNo());
		
		int feedbackId = 0;
		
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
						
						 newView2 = DBUtils.giveFeedback(conn,"Faculty");
						 System.out.println(newView2.get(0).getQuestion());
						 feedbackId = DBUtils.getFeedbackId(conn,newView1);
						 System.out.println(feedbackId);
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
		request.setAttribute("feedbackId",feedbackId);
		System.out.println(request.getParameter("button"+currentSelected));
		request.setAttribute("check",request.getParameter("button"+currentSelected));
		
		RequestDispatcher dispatcher //
            = this.getServletContext().getRequestDispatcher("/WEB-INF/views/giveFacultyFeedbackPage.jsp");

	    dispatcher.forward(request, response);

	}
	
	
}
