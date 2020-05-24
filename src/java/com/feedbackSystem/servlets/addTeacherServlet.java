package com.feedbackSystem.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.feedbackSystem.conn.DBConnection;
import com.feedbackSystem.models.teacherDetails;
import com.feedbackSystem.utils.DBUtils;


@WebServlet(urlPatterns = {"/addTeacher"})
public class addTeacherServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	String prnNoErr,firstNameErr,middleNameErr,lastNameErr;
	public addTeacherServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException,IOException{
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/teacherPage.jsp");
		dispatcher.forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException,IOException
	{
		String prnNo =request.getParameter("prnNo");
		String firstName =request.getParameter("firstName");
		String middleName =request.getParameter("middleName");
		String lastName =request.getParameter("lastName");
		
		teacherDetails newT= null;
		
		boolean hasError=false;
		String errorString="";
		if(prnNo.equals("") || firstName.equals("") || middleName.equals("") || lastName.equals(""))
		{
			hasError=true;
			errorString="All fields are necessary";
		}
		else
		{
			boolean check = validate(prnNo, firstName, middleName, lastName);
			if(check==true)
			{	
				Connection conn = null;
				try {
					
						conn = DBConnection.getMySQLConnection();
						conn.setAutoCommit(false);
						
						try {
							
							DBUtils.insertTeacher(conn, prnNo, firstName, middleName, lastName);
							
							
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
			{
				 hasError=true;
			}
		}
		
		if(hasError)
		{
			newT = new teacherDetails();
            newT.setPrnNo(prnNo);
            newT.setFirstName(firstName);
            newT.setMiddleName(middleName);
            newT.setLastName(lastName);
 
            request.setAttribute("errString", errorString);
            request.setAttribute("prnNoErr", prnNoErr);
            request.setAttribute("firstNameErr", firstNameErr);
            request.setAttribute("middleNameErr", middleNameErr);
            request.setAttribute("lastNameErr", lastNameErr);
            request.setAttribute("newT", newT);
 
            // Forward to /WEB-INF/views/login.jsp
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/teacherPage.jsp");
 
            dispatcher.forward(request, response);
		}
		else
		{
			request.setAttribute("successString","Operation Successful");
			  RequestDispatcher dispatcher 
            = this.getServletContext().getRequestDispatcher("/WEB-INF/views/teacherPage.jsp");

			  dispatcher.forward(request, response);
		}

	}	
	protected boolean validate(String prnNo,String firstName,String middleName,String lastName)
	{
boolean[] check = new boolean[]{true,true,true,true,true,true};
		
		if(!Pattern.matches("[A-Z0-9]+", prnNo) || prnNo.length() != 11) {
			check[0] = false;
			prnNoErr = "Invalid PRN number";
		}else
			prnNoErr = "";
		
		if(!Pattern.matches("[A-Za-z]+",firstName)){
			check[1] = false;
			firstNameErr = "Invalid First Name";
		}else
			firstNameErr = "";
		
		if(!Pattern.matches("[A-Za-z]+",middleName)){
			check[2] = false;
			middleNameErr = "Invalid Middle Name";
		}else
			middleNameErr = "";
		
		if(!Pattern.matches("[A-Za-z]+",lastName)){
			check[3] = false;
			lastNameErr = "Invalid Last Name";
		}else
			lastNameErr = "";
		
		
		if(check[0] == false || check[1] == false || check[2] == false || check[3] == false){
			return false;
		}
		return true;

	}

}

