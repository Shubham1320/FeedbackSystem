package com.feedbackSystem.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.feedbackSystem.conn.DBConnection;
import com.feedbackSystem.utils.DBUtils;

@WebServlet(urlPatterns = {"/addClass"})
public class addClassServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	private String errorString;
	private String classYearErr;
	private String classDivNoErr;
	private String classDeptErr;
	boolean hasError;
	
	public addClassServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException,IOException{
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/classPage.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException{
		String classDept  = request.getParameter("classDept");
		String classDivNo = request.getParameter("classDivNo");
		String classYear = request.getParameter("classYear");
		hasError = false;
		boolean check = true;
		
		if(classDept.equals("") || classDivNo.equals("") || classYear.equals("")) {
			hasError = true;
			errorString = "All fields are necessary";
		}
		else{
			
			check = validate(classDept,classDivNo,classYear);
			if(check == true) {
				Connection conn = null;
				try {
						conn = DBConnection.getMySQLConnection();
						conn.setAutoCommit(false);
						
						try {
							DBUtils.insertClassName(conn,classDept,classYear,classDivNo);
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
			else {
				hasError = true;
			}
		}
		
		if(hasError == true) {
			request.setAttribute("errString", errorString);
			request.setAttribute("classYearErr",classYearErr);
			request.setAttribute("classDivNoErr",classDivNoErr);
			request.setAttribute("classDeptErr",classDeptErr);
			request.setAttribute("classYear",classYear);
			request.setAttribute("classDivNo",classDivNo);
			request.setAttribute("classDept",classDept);
			
            RequestDispatcher dispatcher 
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/classPage.jsp");
 
            dispatcher.forward(request, response);
		}
		else
		{
			request.setAttribute("successString","Operation Successful");
			  RequestDispatcher dispatcher 
            = this.getServletContext().getRequestDispatcher("/WEB-INF/views/classPage.jsp");

			  dispatcher.forward(request, response);
		}
	}
	
	protected boolean validate(String classDept,String classDivNo,String classYear) {
		
		boolean[] check = new boolean[]{true,true,true};
		String[] divNo1 = new String[] {"01","02","03","04","05","06","07","08","09","10","11"};
		List<String> divNoArr = Arrays.asList(divNo1); 
		
		if(!classDept.equals("CS") && !classDept.equals("IT") && !classDept.equals("E&TC")){
			check[0] = false;
			classDeptErr = "Invalid Class Department";
		}else
			classDeptErr = "";
		
		if(!classYear.equals("FE") && !classYear.equals("SE") && !classYear.equals("TE") && !classYear.equals("BE") ){
			check[1] = false;
			classYearErr = "Invalid Class Year";
		}else
			classYearErr = "";
	
		if(!divNoArr.contains(classDivNo)){
			check[2] = false;
			classDivNoErr = "Invalid Class Div No";
		}else
			classDivNoErr = "";
		
		if(check[0] == false || check[1] == false || check[2] == false ) {
			return false;
		}
		return true;
	}
}
