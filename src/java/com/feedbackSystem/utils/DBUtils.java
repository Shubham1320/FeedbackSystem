package com.feedbackSystem.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.feedbackSystem.models.admin;
import com.feedbackSystem.models.className;
import com.feedbackSystem.models.student;
import com.feedbackSystem.models.teacherDetails;
import com.feedbackSystem.models.view1Model;
import com.feedbackSystem.models.view2Model;
import com.feedbackSystem.models.view3Model;


public class DBUtils {

	public static student findUser(Connection conn, 
            String prnNo, String password) throws SQLException {
 
		String sql = "Select * from student a " //
                + " where prnNo = ? and authenticationString = ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, prnNo);
        pstm.setString(2, password);
        ResultSet rs = pstm.executeQuery();
 
        if (rs.next()) {
        	 int rollNo = rs.getInt("rollNo");
            String firstName = rs.getString("firstName");
            String middleName = rs.getString("middleName");
            String lastName = rs.getString("lastName");
            student user = new student();
            
            user.setPrnNo(prnNo);
            user.setRollNo(rollNo);
            user.setPassword(password);
            user.setFirstName(firstName);
            user.setMiddleName(middleName);
            user.setLastName(lastName);
            return user;
        }
        return null;
    }
	
	public static admin findAdmin(Connection conn, 
            String adminId, String adminPassword) throws SQLException {
 
        String sql = "Select adminId,adminPassword from admin where adminId = ? and adminPassword = ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, adminId);
        pstm.setString(2, adminPassword);
        ResultSet rs = pstm.executeQuery();
 
        if (rs.next()) {
            admin user = new admin();
            user.setAdminId(adminId);
            user.setAdminPassword(adminPassword);
            return user;
        }
        return null;
    }
	
	
	public static void insertStudentData(Connection conn,student user,className newClass) throws SQLException{
		String sql = "INSERT INTO student(prnNo,rollNo,firstName,middleName,lastName,authenticationString,admissionYear,o_year,o_divNo) values (?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, user.getPrnNo());
		pstm.setInt(2, user.getRollNo());
		pstm.setString(3, user.getFirstName());
		pstm.setString(4, user.getMiddleName());
		pstm.setString(5, user.getLastName());
		pstm.setString(6, user.getPassword());
		pstm.setInt(7, user.getAdmissionYear());
		pstm.setString(8, newClass.getClassYear());
		pstm.setInt(9, newClass.getClassDivNo());
		pstm.execute();
	}
	

	public static void deleteStudent(Connection conn,String prnNo) throws SQLException{
		System.out.println("Hello I qm");
		String sql = "delete from student where prnNo = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1,prnNo);
		pstm.execute();
	}
	
	public static ArrayList<String> fetchQuestion(Connection conn,String category)throws SQLException {
		String sql = "select catId from category where catName = ?";
		int catId = 0;
		 ArrayList<String> QArray = new ArrayList<String>();
		
		PreparedStatement pstm = conn.prepareStatement(sql);
	    pstm.setString(1, category);
	    ResultSet rs = pstm.executeQuery();
	    
	    if(rs.next()) {
	    	catId = Integer.parseInt(rs.getString("catId"));
	    }
	    
	    String sql1 = "select question from questionary where o_catId = ?";
		
		PreparedStatement pstm1 = conn.prepareStatement(sql1);
	    pstm1.setInt(1,catId);
	    ResultSet rs1 = pstm1.executeQuery();
	    
	    while(rs1.next()) {
	    	QArray.add(rs1.getString("question"));
    	}
    	return QArray;
	}	
	
	public static void insertQuestion(Connection conn, 
            String question, String category) throws SQLException {
		
		int categoryId = 0;
		
		String sql = "select catId from category where catName = ?";
		
		PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, category);
        ResultSet rs = pstm.executeQuery();
 
        if (rs.next()) {
            categoryId = rs.getInt("catId");
        }
		
        String sql1 = "insert into questionary(question,o_catId) values(?,?)";
 
        PreparedStatement pstm1 = conn.prepareStatement(sql1);
        pstm1.setString(1, question);
        pstm1.setInt(2, categoryId);
        pstm1.execute();
	}
	
	public static void deleteQuestion(Connection conn,String question)throws SQLException 
	{
		
		String sql = "select questionId from questionary where question = ?";
		int qId=0;

		PreparedStatement pstm = conn.prepareStatement(sql);
	    pstm.setString(1, question);
	    ResultSet rs = pstm.executeQuery();
	    
	    if(rs.next()) {
	    	qId = rs.getInt("questionId");
	    }
	    
	    System.out.println(qId);
	    
	    String sql1 = "delete from questionary where questionId = ?";
		
		PreparedStatement pstm1 = conn.prepareStatement(sql1);
	    pstm1.setInt(1,qId);
	    pstm1.execute();   
	}
	
	public static ArrayList<String> fetchAllSubject(Connection conn)throws SQLException 
	{
		
		String sql = "select subjectName from subjectDetails";

		PreparedStatement pstm = conn.prepareStatement(sql);
	    ResultSet rs = pstm.executeQuery();
	    ArrayList<String> List=new ArrayList<String>();
	    while(rs.next()) {
	    	System.out.println(rs.getString("subjectName"));
	    	List.add(rs.getString("subjectName"));
	    }
	    
	   return List;  
	}
	
	public static ArrayList<String> fetchAllClassYears(Connection conn)throws SQLException 
	{
		
		String sql = "select year from className";

		PreparedStatement pstm = conn.prepareStatement(sql);
	    ResultSet rs = pstm.executeQuery();
	    ArrayList<String> List=new ArrayList<String>();
	    while(rs.next()) {
	    	List.add(rs.getString("year"));
	    }
	    
	   return List;  
	}
	
	public static ArrayList<String> fetchAllClassDivNos(Connection conn)throws SQLException 
	{
		String sql = "select divNo from className";

		PreparedStatement pstm = conn.prepareStatement(sql);
	    ResultSet rs = pstm.executeQuery();
	    ArrayList<String> List=new ArrayList<String>();
	    while(rs.next()) {
	    	List.add(Integer.toString(rs.getInt("divNo")));
	    }
	    
	   return List;  
	}
	
	public static void insertSubject(Connection conn, 
            String subjectId, String subjectName,String subjectCategory) throws SQLException {
	
        String sql = "insert into subjectDetails(subjectId,subjectName,subjectCategory) values(?,?,?)";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1,Integer.parseInt(subjectId));
        pstm.setString(2,subjectName);
        pstm.setString(3,subjectCategory);
        pstm.execute();
	}
	
	public static void deleteSubject(Connection conn,String subject)throws SQLException {
		
		String sql = "delete  from subjectDetails where subjectName = ?";

		PreparedStatement pstm = conn.prepareStatement(sql);
	    pstm.setString(1, subject);
	    pstm.execute();
	}
	
	public static boolean findClassName(Connection conn,className newClass) throws SQLException{
		
		   String sql = "Select * from className " //
	                + " where year = ? and divNo = ? and department = ?" ;
	 
	        PreparedStatement pstm = conn.prepareStatement(sql);
	        pstm.setString(1, newClass.getClassYear());
	        pstm.setInt(2, newClass.getClassDivNo());
	        pstm.setString(3, newClass.getClassDept());
	        ResultSet rs = pstm.executeQuery();
	 
	        if (rs.next()) {
	            return true;
	        }
	        return false;
	}
	
	public static void insertClassName(Connection conn,String classDept,String classYear,String classDivNo)throws SQLException {
		
		 String sql = "insert into className values(?,?,?)";
		 
	        PreparedStatement pstm = conn.prepareStatement(sql);
	        pstm.setString(1,classYear);
	        pstm.setInt(2,Integer.parseInt(classDivNo));
	        pstm.setString(3,classDept);
	        pstm.execute();
		
	}
	
	public static void insertTeacher(Connection conn, String prnNo, String firstName, String middleName,
			String lastName) throws SQLException {
		
        String sql2 = "insert into teacher values(?,?,?,?)";
 
        PreparedStatement pstm2 = conn.prepareStatement(sql2);
        pstm2.setString(1, prnNo);
        pstm2.setString(2, firstName);
        pstm2.setString(3, middleName);
        pstm2.setString(4, lastName);
        pstm2.execute();
		
	}
	
	public static void loadMapping(Connection conn,String subjectId,String subjectCategory,String classYear,String classDivNo) throws SQLException {
		String subCat = subjectCategory,batch1 = "",batch2 = "",batch3 = "",batch4 = "";
		
		System.out.println(subjectCategory);
		System.out.println(classYear);
		System.out.println(classDivNo);
		if(classYear.equals("FE")) {
			batch1 = "A";
			batch2 = "B";
			batch3 = "C";
			batch4 = "D";
		}else if(classYear.equals("SE")) {
			batch1 = "E";
			batch2 = "F";
			batch3 = "G";
			batch4 = "H";
		}else if(classYear.equals("TE")) {
			batch1 = "K";
			batch2 = "L";
			batch3 = "M";
			batch4 = "N";
		}else if(classYear.equals("BE")) {
			batch1 = "P";
			batch2 = "Q";
			batch3 = "R";
			batch4 = "S";
		}
		
		if(subCat.equals("PR")) {
			String sql = "insert into loadMapping(o_year,o_divNo,o_subId,batch) values (?,?,?,?)";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1,classYear);
			pstm.setInt(2,Integer.parseInt(classDivNo));
			pstm.setString(3,subjectId);
			pstm.setString(4,batch1);
			pstm.execute();
			
			String sql2 = "insert into loadMapping(o_year,o_divNo,o_subId,batch) values (?,?,?,?)";
			PreparedStatement pstm2 = conn.prepareStatement(sql2);
			pstm2.setString(1,classYear);
			pstm2.setInt(2,Integer.parseInt(classDivNo));
			pstm2.setString(3,subjectId);
			pstm2.setString(4,batch2);
			pstm2.execute();
			
			String sql3 = "insert into loadMapping(o_year,o_divNo,o_subId,batch) values (?,?,?,?)";
			PreparedStatement pstm3 = conn.prepareStatement(sql3);
			pstm3.setString(1,classYear);
			pstm3.setInt(2,Integer.parseInt(classDivNo));
			pstm3.setString(3,subjectId);
			pstm3.setString(4,batch3);
			pstm3.execute();
			
			String sql4 = "insert into loadMapping(o_year,o_divNo,o_subId,batch) values (?,?,?,?)";
			PreparedStatement pstm4 = conn.prepareStatement(sql4);
			pstm4.setString(1,classYear);
			pstm4.setInt(2,Integer.parseInt(classDivNo));
			pstm4.setString(3,subjectId);
			pstm4.setString(4,batch4);
			pstm4.execute();
		}
		else {
			String sql5 = "insert into loadMapping(o_year,o_divNo,o_subId) values (?,?,?)";
			PreparedStatement pstm5 = conn.prepareStatement(sql5);
			pstm5.setString(1,classYear);
			pstm5.setInt(2,Integer.parseInt(classDivNo));
			pstm5.setString(3,subjectId);
			pstm5.execute();
		}
	}
	
	public static ArrayList<view3Model> fetchStudentLoadMapping1(Connection conn,String classYear,String classDivNo,int check,String batch)throws SQLException 
	{
		String sql = "";
		if(check == 1) {
			sql = "select * from view3 where o_year = ? and o_divNo = ? and batch IS NULL";
		}
		else if(check == 2) {
			sql = "select * from view3 where o_year = ? and o_divNo = ? and batch = ?";
		}
		ArrayList<view3Model> newView3= new ArrayList<view3Model>();
		
		PreparedStatement pstm = conn.prepareStatement(sql);
	    pstm.setString(1,classYear);
	    pstm.setInt(2,Integer.parseInt(classDivNo));
	    if(check == 2) {
	    	pstm.setString(3,batch);
	    }
	    ResultSet rs = pstm.executeQuery();
	    
	    while(rs.next()) {
	    	view3Model temp = new view3Model();

	    	String o_year = rs.getString("o_year");
	    	int o_divNo = rs.getInt("o_divNo");
	     	String o_tPrnNo = rs.getString("o_tPrnNo");
	    	String o_subId = rs.getString("o_subId");
	    	String batch1 = rs.getString("batch");
	    	String subjectName = rs.getString("subjectName");
	    	String subjectCategory = rs.getString("subjectCategory");
	    	
	    	temp.setBatch(batch1);
	    	temp.setO_divNo(o_divNo);
	    	temp.setO_subId(o_subId);
	    	temp.setO_tPrnNo(o_tPrnNo);
	    	temp.setO_year(o_year);
	    	temp.setSubjectCategory(subjectCategory);
	    	temp.setSubjectName(subjectName);
	    	
	    	newView3.add(temp);
	    }

	    return newView3;
	}
	
	public static ArrayList<teacherDetails> fetchAllTeachers(Connection conn)throws SQLException {
		
		String sql = "select tPrnNo,tFirstName,tMiddleName,tLastName from teacher";
		ArrayList<teacherDetails> teachers = new ArrayList<teacherDetails>(); 
		PreparedStatement pstm = conn.prepareStatement(sql);
	    ResultSet rs = pstm.executeQuery();
	    
	    while(rs.next()) {
	    	teacherDetails temp = new teacherDetails();
	    	String tFirstName = rs.getString("tFirstName");
	    	String tMiddleName = rs.getString("tMiddleName");
	    	String tLastName = rs.getString("tLastName");
	    	String tPrnNo = rs.getString("tPrnNo");
	    	temp.setFirstName(tFirstName);
	    	temp.setLastName(tLastName);
	    	temp.setMiddleName(tMiddleName);
	    	temp.setPrnNo(tPrnNo);
	    	teachers.add(temp);
	    }

	    return teachers;
	}
	
	public static void updateTeacherIntoLoadMapping(Connection conn, String classYear, String classDivNo, String o_subId,
			String o_tPrnNo,String batch) throws SQLException {
		String sql = "";
		if(batch.equals("")) {
			sql = "update loadMapping set o_tPrnNo = ? where o_year = ? and o_divNo = ? and o_subId = ? and batch IS NULL";
			 PreparedStatement pstm1 = conn.prepareStatement(sql);
		     pstm1.setString(1,o_tPrnNo );
		     pstm1.setString(2,classYear);
		     pstm1.setInt(3,Integer.parseInt(classDivNo));
		     pstm1.setInt(4,Integer.parseInt(o_subId));
		     pstm1.execute();
		}
		else {
			sql = "update loadMapping set o_tPrnNo = ? where o_year = ? and o_divNo = ? and o_subId = ? and batch = ?";
			PreparedStatement pstm2 = conn.prepareStatement(sql);
			pstm2.setString(1,o_tPrnNo );
			pstm2.setString(2,classYear);
        	pstm2.setInt(3,Integer.parseInt(classDivNo));
        	pstm2.setInt(4,Integer.parseInt(o_subId));
        	pstm2.setString(5,batch);
        	pstm2.execute();
		}
	}
	
	public static ArrayList<view2Model> giveFeedback(Connection conn,String category)throws SQLException 
	{
		
		String sql = "select * from view2 where catName = ?";
		ArrayList<view2Model> newView2 = new ArrayList<view2Model>();
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1,category);
	    	ResultSet rs = pstm.executeQuery();
	    	while(rs.next()) {
	    		view2Model temp = new view2Model();
	    	
	    		int questionId = rs.getInt("questionId");
	    	
	    		String question = rs.getString("question");
	    	
	    		int catId = rs.getInt("catId");
	    
		    	temp.setQuestionId(questionId);
		    	temp.setQuestion(question);
		    	temp.setCatId(catId);
		    	temp.setCatName(category);
		    	
		    	newView2.add(temp);
		}

	    	return newView2;
	}
	
	public static void insertFeedbackDetails(Connection conn,view1Model view1)throws SQLException 
	{
		
		String sql1 = "select * from feedbackDetails where o_year = ? and o_divNo = ? and o_subId = ? and o_tPrnNo = ?";
		PreparedStatement pstm1 = conn.prepareStatement(sql1); 
		pstm1.setString(1,view1.getO_Year());
		pstm1.setInt(2,view1.getO_divNo());
		pstm1.setInt(3,view1.getSubjectId());
		pstm1.setString(4,view1.gettPrnNo());
		ResultSet rs = pstm1.executeQuery();
		if(rs.next() == false) {
		
			String sql = "insert into feedbackDetails(o_year,o_divNo,o_subId,o_tPrnNo) values(?, ?, ?, ?)";

			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1,view1.getO_Year());
			pstm.setInt(2,view1.getO_divNo());
			pstm.setInt(3,view1.getSubjectId());
			pstm.setString(4,view1.gettPrnNo());
		
			pstm.execute();
		}
	}
	
	public static void deleteClass(Connection conn,String year,String classNo)throws SQLException 
	{
		
		String sql = "delete from className where year = ? and divNo = ? ";
		System.out.println(year);
		System.out.println(classNo);
		PreparedStatement pstm = conn.prepareStatement(sql);
	    pstm.setString(1, year);
	    pstm.setInt(2, Integer.parseInt(classNo));
	    pstm.execute();
	}
	
	public static String fetchTeacher(Connection conn,String T_prnNo)throws SQLException {
		
		String sql = "select tFirstName,tMiddleName,tLastName from teacher where tPrnNo = ?";
		
		 String Tname=new String();
		
		PreparedStatement pstm = conn.prepareStatement(sql);
	    pstm.setString(1,T_prnNo);
	    ResultSet rs = pstm.executeQuery();
	    
	    if(rs.next()) {
	    	Tname = rs.getString("tFirstName")+" "+ rs.getString("tMiddleName")+" "+ rs.getString("tLastName");
	    }

	    return Tname;
	}
	
	public static void deleteTeacher(Connection conn,String t_prnNo)throws SQLException 
	{
		
		String sql = "delete from teacher where tPrnNo = ?";

		PreparedStatement pstm = conn.prepareStatement(sql);
	    pstm.setString(1, t_prnNo);
	    pstm.execute();
	}
	
	public static ArrayList<view1Model> fetchStudentLoadMapping(Connection conn,String prnNo)throws SQLException 
	{
		
		String sql = "select * from view1 where prnNo = ?";
		
		ArrayList<view1Model> newView1 = new ArrayList<view1Model>();
		
		PreparedStatement pstm = conn.prepareStatement(sql);
	    pstm.setString(1,prnNo);
	    ResultSet rs = pstm.executeQuery();
	    
	    while(rs.next()) {
	    	view1Model temp = new view1Model();
	    	temp.setPrnNo(prnNo);
	    	temp.settPrnNo("tPrnNo");
	    	String o_year = rs.getString("o_year");
	    	int o_divNo = rs.getInt("o_divNo");
	    	String tFirstName = rs.getString("tFirstName");
	    	String tMiddleName = rs.getString("tMiddleName");
	    	String tLastName = rs.getString("tLastName");
	    	String subjectName = rs.getString("subjectName");
	    	int subjectId = rs.getInt("subjectId");
	    	String tPrnNo = rs.getString("tPrnNo");
	    	String subjectCategory = rs.getString("subjectCategory");
	    	
	    	temp.setO_Year(o_year);
	    	temp.setO_divNo(o_divNo);
	    	temp.settFirstName(tFirstName);
	    	temp.settMiddleName(tMiddleName);
	    	temp.settLastName(tLastName);
	    	temp.setSubjectName(subjectName);
	    	temp.setSubjectId(subjectId);
	    	temp.settPrnNo(tPrnNo);
	    	temp.setSubjectCategory(subjectCategory);
	    	newView1.add(temp);
	    }

	    return newView1;
	}

	public static int getFeedbackId(Connection conn,view1Model newView1) throws SQLException{
	
		String sql = "select feedbackId from feedbackDetails where o_year = ? and o_divNo = ? and o_subId = ? and o_tPrnNo = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1,newView1.getO_Year());
		pstm.setInt(2,newView1.getO_divNo());
		pstm.setInt(3,newView1.getSubjectId());
		pstm.setString(4,newView1.gettPrnNo());
		ResultSet rs = pstm.executeQuery();
		if(rs.next()) {
			return rs.getInt("feedbackId");
		}
		return 0;
	}
	
	public static void insertIntoFeedbackQuestionaryRelation(Connection conn,String feedbackId,String questionId,boolean opt1,boolean opt2,boolean opt3,boolean opt4)throws SQLException {
		
		if(!feedbackId.equals("")) {
		
			String sql = "insert into feedbackQuestionaryRelation values (?,?,?,?,?,?)";
		
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(2,Integer.parseInt(feedbackId));
			pstm.setInt(1,Integer.parseInt(questionId));
			pstm.setBoolean(3,opt1);
			pstm.setBoolean(4,opt2);
			pstm.setBoolean(5,opt3);
			pstm.setBoolean(6,opt4);
			pstm.execute();
		}
		else {
			String sql1 = "insert into feedbackQuestionaryRelation(o_questionId,optA,optB,optC,optD) values (?,?,?,?,?)";
			
			PreparedStatement pstm1 = conn.prepareStatement(sql1);
			pstm1.setInt(1,Integer.parseInt(questionId));
			pstm1.setBoolean(2,opt1);
			pstm1.setBoolean(3,opt2);
			pstm1.setBoolean(4,opt3);
			pstm1.setBoolean(5,opt4);
			pstm1.execute();
		}
		
	}
}
