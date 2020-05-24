package com.feedbackSystem.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.feedbackSystem.conn.DBConnection;
import com.feedbackSystem.models.className;
import com.feedbackSystem.models.student;
import com.feedbackSystem.utils.DBUtils;

@WebServlet(urlPatterns = {"/addStudent"})
public class addStudentServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private String errString = "";
	private String prnNoErr = "";
	private String firstNameErr = "";
	private String middleNameErr = "";
	private String lastNameErr = "";
	private String passwordErr = "";
	private String admissionYearErr = "";
	private String classDeptErr="";
	private String classYearErr="";
	private String classDivNoErr="";
	
	public addStudentServlet()	{
		super();
	}
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException,IOException{
		
		System.out.println("Shubham");
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/studentPage.jsp");
		
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException{
		
		boolean hasError = false;
		
		String prnNo = request.getParameter("prnNo");
		String rollNo = request.getParameter("rollNo");
		String firstName = request.getParameter("firstName");
		String middleName = request.getParameter("middleName");
		String lastName = request.getParameter("lastName");
		String password = request.getParameter("password");
		String admissionYear = request.getParameter("admissionYear");
		String classDept = request.getParameter("classDept");
		String classYear = request.getParameter("classYear");
		String classDivNo = request.getParameter("classDivNo");
		System.out.println(classYear);
		student user = new student();
		className newClass = new className();
		
		if(classDivNo.equals("") || classYear.equals("") || classDept.equals("") || password.equals("") || rollNo.equals("")||prnNo.equals("")||firstName.equals("")||middleName.equals("")||lastName.equals("")||admissionYear.equals(""))
		{
			hasError = true;
			errString = "All the fields are necessary";
		}
		else
		{
			errString = "";
			boolean check = validateForm(prnNo,rollNo,firstName,middleName,lastName,password,admissionYear,classDept,classYear,classDivNo);
			
			if(check == true) {
				user = new student(prnNo,password,firstName,middleName,lastName,admissionYear,rollNo);
				newClass = new className(classDept,classYear,classDivNo);
				Connection conn = null;
				try {
						conn = DBConnection.getMySQLConnection();
						conn.setAutoCommit(false);
						
						try {
							
							if(DBUtils.findClassName(conn,newClass) == true) {
								classYearErr = "";
								classDivNoErr = "";
								classDeptErr = "";
								System.out.println(newClass.getClassYear());
								DBUtils.insertStudentData(conn,user,newClass);
							}
							else {
								
								classYearErr = "Invalid Class Year";
								classDivNoErr = "Invalid Class Div No";
								classDeptErr = "Invalid Class Department";
								hasError = true;
							}
								
						}catch(Exception e){
							System.out.println(e.getMessage());
							System.out.println("errorCatch");
							hasError = true;
							errString = e.getMessage();
						}
						conn.commit();
				} catch (Exception e) {
					System.out.println("Hello1");
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
		if(hasError == true){
			request.setAttribute("errString", errString);
			request.setAttribute("prnNoErr", prnNoErr);
			request.setAttribute("firstNameErr", firstNameErr);
			request.setAttribute("middleNameErr", middleNameErr);
			request.setAttribute("lastNameErr", lastNameErr);
			request.setAttribute("admissionYearErr", admissionYearErr);
			request.setAttribute("classYearErr", classYearErr);
			request.setAttribute("classDivNoErr", classDivNoErr);
			request.setAttribute("classDeptErr", classDeptErr);
			request.setAttribute("passwordErr", passwordErr);
			request.setAttribute("prnNo",prnNo);
			request.setAttribute("rollNo",rollNo);
			request.setAttribute("firstName",firstName);
			request.setAttribute("middleName",middleName);
			request.setAttribute("lastName",lastName);
			request.setAttribute("password",password);
			request.setAttribute("admissionYear",admissionYear);
			request.setAttribute("classYear",classYear);
			request.setAttribute("classDivNo",classDivNo);
			request.setAttribute("classDept",classDept);
			
            RequestDispatcher dispatcher 
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/studentPage.jsp");
 
            dispatcher.forward(request, response);
		}
		else{
			
			request.setAttribute("successString","Operation Successful");
			  RequestDispatcher dispatcher 
              = this.getServletContext().getRequestDispatcher("/WEB-INF/views/studentPage.jsp");

			  dispatcher.forward(request, response);
		}
	}	
	
	public boolean validateForm(String prnNo,String rollNo,String firstName,
			String middleName,String lastName,String password,String admissionYear,String classDept,String classYear,String classDivNo)
	{
		boolean[] check = new boolean[]{true,true,true,true,true,true,true,true,true};
		String[] divNo1 = new String[] {"01","02","03","04","05","06","07","08","09","10","11"};
		List<String> divNoArr = Arrays.asList(divNo1); 
		
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
		
		if(!Pattern.matches("[A-Za-z@#!&*^$0-9]+",password)){
			check[4] = false;
			passwordErr = "Invalid Password";
		}else
			passwordErr = "";
		
		if(!Pattern.matches("[0-9]+",admissionYear) || admissionYear.length() != 4){
			check[5] = false;
			admissionYearErr = "Invalid Admission Year";
		}else
			admissionYearErr = "";
		
		if(!classDept.equals("CS") && !classDept.equals("IT") && !classDept.equals("E&TC")){
			check[6] = false;
			classDeptErr = "Invalid Class Department";
		}else
			classDeptErr = "";
		
		if(!classYear.equals("FE") && !classYear.equals("SE") && !classYear.equals("TE") && !classYear.equals("BE") ){
			check[7] = false;
			classYearErr = "Invalid Class Year";
		}else
			classYearErr = "";
	
		if(!divNoArr.contains(classDivNo)){
			check[8] = false;
			classDivNoErr = "Invalid Class Div No";
		}else
			classDivNoErr = "";
		
		if(check[0] == false || check[1] == false || check[2] == false || check[3] == false || check[4] == false||check[5] == false || check[6] == false|| check[7] == false || check[8] == false){
			return false;
		}
		return true;
	}
}
