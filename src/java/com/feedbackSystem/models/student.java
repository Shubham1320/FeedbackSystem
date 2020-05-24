package com.feedbackSystem.models;

public class student {

	private String prnNo;
	private String password;
	private String firstName;
	private String middleName;
	private String lastName;
	private int admissionYear;
	private int rollNo;
	
	public student() {
		
	}
	
	public student(String prnNo,String password,String firstName,String middleName,String lastName,String admissionYear,String rollNo){
		this.setPrnNo(prnNo);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setMiddleName(middleName);
		this.setPassword(password);
		this.setRollNo(Integer.parseInt(rollNo,10));
		this.setAdmissionYear(Integer.parseInt(admissionYear));
	}
	
	public String getPrnNo() {
		return prnNo;
	}
	public void setPrnNo(String prnNo) {
		this.prnNo = prnNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAdmissionYear() {
		return admissionYear;
	}

	public void setAdmissionYear(int admissionYear) {
		this.admissionYear = admissionYear;
	}

	public int getRollNo() {
		return rollNo;
	}

	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}
	
	
}
