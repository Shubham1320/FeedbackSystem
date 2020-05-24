package com.feedbackSystem.models;

public class className {
	private String classDept;
	private String classYear;
	private int classDivNo;
	
	public className() {
		
	}
	
	public className(String classDept,String classYear,String classDivNo) {
		this.classDept = classDept;
		this.classYear = classYear;
		this.classDivNo = Integer.parseInt(classDivNo);	
	}
	
	public String getClassDept() {
		return classDept;
	}
	public void setClassDept(String classDept) {
		this.classDept = classDept;
	}
	public int getClassDivNo() {
		return classDivNo;
	}
	public void setClassDivNo(int classDivNo) {
		this.classDivNo = classDivNo;
	}

	public String getClassYear() {
		return classYear;
	}

	public void setClassYear(String classYear) {
		this.classYear = classYear;
	}
	 
}
