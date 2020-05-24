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
import com.feedbackSystem.models.questionDetails;
import com.feedbackSystem.utils.DBUtils;


@WebServlet(urlPatterns = {"/addQuestion"})
public class addQuestionServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	String qErr,cErr;
	public addQuestionServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException,IOException{
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/questionPage.jsp");
		dispatcher.forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException,IOException{
		String question =request.getParameter("question");
		String category =request.getParameter("category");
		
		questionDetails newQ= null;
		
		boolean hasError=false;
		String errorString="";
		if(question.equals("") || category.equals(""))
		{
			hasError=true;
			errorString="All fields are necessary";
		}
		else
		{
			boolean check = validate(question,category);
			System.out.println(check);
			if(check==true)
			{	
				System.out.println(check);
				Connection conn = null;
				try {
						conn = DBConnection.getMySQLConnection();
						conn.setAutoCommit(false);
						
						try {
							System.out.println("HELLO");
							DBUtils.insertQuestion(conn, question, category);
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
			newQ = new questionDetails();
            newQ.setQuestion(question);
            newQ.setCategory(category);
 
            request.setAttribute("errString", errorString);
            request.setAttribute("newQ", newQ);
 
            // Forward to /WEB-INF/views/login.jsp
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/questionPage.jsp");
 
            dispatcher.forward(request, response);
		}
		else
		{
			  request.setAttribute("successString","Operation Successful");
				
	           RequestDispatcher dispatcher //
               = this.getServletContext().getRequestDispatcher("/WEB-INF/views/questionPage.jsp");

	           dispatcher.forward(request, response); 
		}

	}
	protected boolean validate(String question,String category)
	{
		boolean[] check = new boolean[]{true,true};
		
		
		if(!Pattern.matches("[A-Za-z? ]+",question)){
			check[0] = false;
			qErr = "Invalid Question";
		}else
			qErr = "";
		
		if(!Pattern.matches("[A-Za-z]+",category)){
			check[1] = false;
			cErr = "Invalid Category";
		}else
			cErr = "";
		
		if(check[0] == false || check[1] == false ){
			return false;
		}
		return true;

	}
	
}
