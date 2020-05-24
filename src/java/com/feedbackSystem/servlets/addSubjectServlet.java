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


import com.feedbackSystem.conn.DBConnection;
import com.feedbackSystem.utils.DBUtils;


@WebServlet(urlPatterns = {"/addSubject"})
public class addSubjectServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	String subjectCategoryErr = "";
	String subjectIdErr = "";
	String subjectNameErr = "";
	String classYearErr = "";
	String classDivNoErr = "";
	
	
	public addSubjectServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException,IOException{
		
		 ArrayList<String> fetchClassYears=new ArrayList<String>();
		 ArrayList<String> fetchClassDivNos=new ArrayList<String>();
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
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/subjectPage.jsp");
		dispatcher.forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException,IOException{
		String subjectId =request.getParameter("subjectId");
		String subjectName =request.getParameter("subjectName");
		String subjectCategory = request.getParameter("subjectCategory");
		String classYear = request.getParameter("classYear");
		String classDivNo = request.getParameter("classDivNo");
		
		System.out.println(classYear);
		System.out.println(classDivNo);
		System.out.println(subjectCategory);
		System.out.println(subjectName);
		System.out.println(subjectId);
		
		boolean hasError=false;
		String errorString="";
		if(subjectId.equals("") || subjectName.equals("") || subjectCategory.equals("") || classYear.equals("") || classDivNo.equals(""))
		{
			hasError=true;
			errorString="All fields are necessary";
		}
		else
		{
			boolean check = true;
			check = validate(subjectId,subjectName,subjectCategory);
			if(check == true)
			{	
				Connection conn = null;
				try {
						conn = DBConnection.getMySQLConnection();
						conn.setAutoCommit(false);
					
						try {
							System.out.println("Hello");
							DBUtils.insertSubject(conn,subjectId,subjectName,subjectCategory);
							DBUtils.loadMapping(conn,subjectId,subjectCategory,classYear,classDivNo);
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
			else
				hasError = true;
		}
		
		if(hasError)
		{
			request.setAttribute("subjectId",subjectId);
			request.setAttribute("subjectName",subjectName);
			request.setAttribute("subjectCategory",subjectCategory);
			request.setAttribute("classYear",classYear);
			request.setAttribute("classDivNo",classDivNo);
            request.setAttribute("errString", errorString);
            request.setAttribute("subjectIdErr",subjectIdErr);
            request.setAttribute("subjectNameErr",subjectNameErr);
            request.setAttribute("subjectCategoryErr",subjectCategoryErr);
            request.setAttribute("classYearErr",classYearErr);
            request.setAttribute("classDivNoErr",classDivNoErr);
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/subjectPage.jsp");
 
            dispatcher.forward(request, response);
		}
		else
		{
			request.setAttribute("successString","Operation Successful");
			  RequestDispatcher dispatcher 
          = this.getServletContext().getRequestDispatcher("/WEB-INF/views/subjectPage.jsp");

			  dispatcher.forward(request, response);
		}

	}
	
	protected boolean validate(String subjectId,String subjectName,String subjectCategory) {
		
		boolean[] check = new boolean[]{true,true,true,true,true,true};
		
		if(!Pattern.matches("[0-9]+",subjectId) || subjectId.length() != 6) {
			check[0] = false;
			subjectIdErr = "Invalid subject id";
		}else
			subjectIdErr = "";
		
		if(!Pattern.matches("[A-Za-z]+",subjectName)){
			check[1] = false;
			subjectNameErr = "Invalid Subject Name";
		}else
			subjectNameErr = "";
		
		if(!subjectCategory.equals("TH") && !subjectCategory.equals("PR")){
			check[2] = false;
			subjectCategoryErr = "Invalid Subject Category";
		}else
			subjectCategoryErr = "";
		
		
		if(check[0] == false || check[1] == false || check[2] == false ){
			return false;
		}
		
		
		return true;
	}
		
}

