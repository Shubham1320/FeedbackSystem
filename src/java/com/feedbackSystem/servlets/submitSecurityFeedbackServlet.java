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


@WebServlet(urlPatterns = {"/submitSecurityFeedback"})
public class submitSecurityFeedbackServlet extends HttpServlet
{
	boolean opt1=false,opt2=false,opt3=false,opt4=false;
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException,IOException{
		doPost(request,response);		
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException,IOException
	{
		String questionId = "";
		String optionSelected = "";
		boolean opt1 = false,opt2 = false,opt3 = false,opt4 = false;
		for(int i=0 ; i < Integer.parseInt(request.getParameter("count")) ; i++) {
			questionId = request.getParameter("questionId"+Integer.toString(i));
			System.out.println(request.getParameter("questionId"+Integer.toString(i)));
			optionSelected = request.getParameter("Q"+Integer.toString(i));
			System.out.println(request.getParameter("Q"+Integer.toString(i)));
			if(optionSelected.equals("Option1")) {
				opt1 = true;
			}else if(optionSelected.equals("Option2")) {
				opt2 = true;
			}else if(optionSelected.equals("Option3")) {
				opt3 = true;
			}else if(optionSelected.equals("Option4")) {
				opt4 = true;
			}
			
			Connection conn = null;
			try {
					conn = DBConnection.getMySQLConnection();
					conn.setAutoCommit(false);
					
					try {
						 DBUtils.insertIntoFeedbackQuestionaryRelation(conn,"",questionId,opt1,opt2,opt3,opt4);
					}catch(Exception e){
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
			RequestDispatcher dispatcher //
            = this.getServletContext().getRequestDispatcher("/WEB-INF/views/welcome.jsp");

			dispatcher.forward(request, response);
		}
	}
}
