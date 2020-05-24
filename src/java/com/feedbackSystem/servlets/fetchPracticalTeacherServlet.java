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
import com.feedbackSystem.models.teacherDetails;
import com.feedbackSystem.models.view3Model;
import com.feedbackSystem.utils.DBUtils;


@WebServlet(urlPatterns = {"/fetchTeacherForPR"})
public class fetchPracticalTeacherServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public fetchPracticalTeacherServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException,IOException{
		
		 ArrayList<String> fetchClassYears=new ArrayList<String>();
		 ArrayList<String> fetchClassDivNos=new ArrayList<String>();
		 String errorString = "";
	      Connection conn = null;
		try {
				conn = DBConnection.getMySQLConnection();
				conn.setAutoCommit(false);
					
				try {
					fetchClassYears=DBUtils.fetchAllClassYears(conn);
					fetchClassDivNos=DBUtils.fetchAllClassDivNos(conn);
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
			request.setAttribute("classYears",fetchClassYears);
			request.setAttribute("classDivNos",fetchClassDivNos);
			request.setAttribute("errorString",errorString);
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/allocateTeacherForPracticalSubjects.jsp");
			dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException,IOException{
		String classYear = request.getParameter("classYear");
		String classDivNo = request.getParameter("classDivNo");
		String batch = request.getParameter("batch");
		
		System.out.println(classYear);
		System.out.println(classDivNo);
		
		ArrayList<view3Model> newView3 = new ArrayList<view3Model>();
		ArrayList<teacherDetails> teachers = new ArrayList<teacherDetails>();
		
		boolean hasError=false;
		String errorString="";
		if(classYear.equals("") || classDivNo.equals(""))
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
						System.out.println("Hello");
						newView3 = DBUtils.fetchStudentLoadMapping1(conn,classYear,classDivNo,2,batch);
						teachers = DBUtils.fetchAllTeachers(conn);
					}catch(Exception e)
					{
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
			request.setAttribute("classYear",classYear);
			request.setAttribute("classDivNo",classDivNo);
            request.setAttribute("errString", errorString);
            request.setAttribute("batch",batch);
            
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/allocateTeacherForPracticalSubjects.jsp");
 
            dispatcher.forward(request, response);
		}
		else
		{
			request.setAttribute("classYear",classYear);
			request.setAttribute("classDivNo",classDivNo);
			request.setAttribute("batch",batch);
            request.setAttribute("newView3",newView3);
            request.setAttribute("teachers",teachers);
            
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/allocateTeacherForPracticalSubjects.jsp");
 
            dispatcher.forward(request, response);
		}

	}
}	