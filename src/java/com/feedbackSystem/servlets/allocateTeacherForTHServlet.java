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
import com.feedbackSystem.models.view1Model;
import com.feedbackSystem.utils.DBUtils;


@WebServlet(urlPatterns = {"/allocateTeacherForTH"})
public class allocateTeacherForTHServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public allocateTeacherForTHServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException,IOException{
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException,IOException{
		

		String classYear = request.getParameter("classYear");
		String classDivNo = request.getParameter("classDivNo");
		
		for(int i = 0;i < Integer.parseInt(request.getParameter("count"));i++) {
			String o_subId = request.getParameter("o_subId"+Integer.toString(i));
			String o_tPrnNo = request.getParameter("teacher"+Integer.toString(i));
			
			Connection conn = null;
			try {
					conn = DBConnection.getMySQLConnection();
					conn.setAutoCommit(false);
					
					try {
						System.out.println("Helloooo");
						DBUtils.updateTeacherIntoLoadMapping(conn,classYear,classDivNo,o_subId,o_tPrnNo,"");
						view1Model newView1 = new view1Model(); 
						newView1.setO_divNo(Integer.parseInt(classDivNo));
						newView1.setO_Year(classYear);
						newView1.settPrnNo(o_tPrnNo);
						newView1.setSubjectId(Integer.parseInt(o_subId));
						DBUtils.insertFeedbackDetails(conn,newView1 );
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
		}
		request.setAttribute("successString","Operation Successful");
		  RequestDispatcher dispatcher 
      = this.getServletContext().getRequestDispatcher("/WEB-INF/views/allocateTeacherForTheorySubjects.jsp");

		  dispatcher.forward(request, response);
	}
}	