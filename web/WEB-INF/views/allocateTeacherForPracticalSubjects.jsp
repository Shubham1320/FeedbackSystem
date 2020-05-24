<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <meta charset="UTF-8">
  <title>Dashboard</title>
  <!-- Bootstrap CSS CDN -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">

    <!-- Font Awesome JS -->
    <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/solid.js" integrity="sha384-tzzSw1/Vo+0N5UhStP3bvwWPq+uvzCMfrN1fEFe+xBmv1C/AtVX5K0uZtmcHitFZ" crossorigin="anonymous"></script>
    <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/fontawesome.js" integrity="sha384-6OIrr52G08NpOFSZdxxz1xdNSndlD4vdcf/q2myIUVO0VsqaGHJsB0RaBE01VTOY" crossorigin="anonymous"></script>


<!-- jQuery CDN - Slim version (=without AJAX) -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <!-- Popper.JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
    <!-- Bootstrap JS -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script><link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css'>
<style><%@include file="/WEB-INF/css/style1.css"%></style>
</head>
<body>
<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	response.setHeader("Expires", "0"); // Proxies.
	if(session.getAttribute("user") == null)
		response.sendRedirect("http://localhost:8080/feedbackSystem/home1");
	
	%>
<!-- partial:index.partial.html -->
<div class="wrapper">
        <!-- Sidebar  -->
        <nav id="sidebar">
            <div class="sidebar-header">
                <h3>PICT</h3>
            </div>
        <!-- menu profile quick info -->
            <div class="profile clearfix">
              <div class="profile_pic">
                <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSPR5mdMwZN-cc7lJqY0OqiQMJFZpqg9dhYcUQJrxFNtA81inEi"  class="img-circle profile_img">
              </div>
              <div class="profile_info">
                <span>Welcome,</span>
               	<h2>${user.adminId}</h2>	
              </div>
            </div>

            <ul class="list-unstyled components">
                <li class="active">
                <li>
                    <a href="${pageContext.request.contextPath}/welcome1"><i class="fa fa-home"></i>&nbsp;&nbsp;&nbsp;Home</a>
                </li>
                <li>

                    <a href="#studentSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle"><i class="fa fa-graduation-cap"></i>&nbsp;&nbsp;&nbsp;Student </a>
                    <ul class="collapse list-unstyled" id="studentSubmenu">
                        <li>
                            <a href="${pageContext.request.contextPath}/importStudent">Import Student from Excel Sheet</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/addStudent">Add</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/libraryFeedback">Update</a>
                        </li>
                         <li>
                            <a href="${pageContext.request.contextPath}/deleteStudent">Delete</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#teacherSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle"><i class="fa fa-user"></i>&nbsp;&nbsp;&nbsp;Teacher</a>
                    <ul class="collapse list-unstyled" id="teacherSubmenu">
                        <li>
                            <a href="${pageContext.request.contextPath}">Import Teacher from Excel Sheet</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/addTeacher">Add</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/updateTeacher">Update</a>
                        </li>
                         <li>
                            <a href="${pageContext.request.contextPath}/deleteTeacher">Delete</a>
                        </li>
                    </ul>
                </li>
                 <li>
                    <a href="#classSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle"><i class="fa fa-table"></i>&nbsp;&nbsp;&nbsp;Class</a>
                    <ul class="collapse list-unstyled" id="classSubmenu">
                       <li>
                            <a href="${pageContext.request.contextPath}/addClass">Add</a>
                        </li>
                         <li>
                            <a href="${pageContext.request.contextPath}/deleteClass">Delete</a>
                        </li>
                    </ul>
                </li>
                 <li>
                    <a href="#subjectSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle"><i class="fa fa-book-open"></i>&nbsp;&nbsp;&nbsp;Subject</a>
                    <ul class="collapse list-unstyled" id="subjectSubmenu">
                     <li>
                            <a href="${pageContext.request.contextPath}/addSubject">Add</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/updateSubject">Update</a>
                        </li>
                         <li>
                            <a href="${pageContext.request.contextPath}/deleteSubject">Delete</a>
                        </li>
                    </ul>
                </li>
                 <li>
                    <a href="#questionSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle"><i class="fa fa-question-circle"></i>&nbsp;&nbsp;&nbsp;Questions</a>
                    <ul class="collapse list-unstyled" id="questionSubmenu">
                     <li>
                            <a href="${pageContext.request.contextPath}/addQuestion">Add</a>
                        </li>
                         <li>
                            <a href="${pageContext.request.contextPath}/fetchQuestion">Delete</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/fetchTeacherForTH"><i class="fa fa-clone"></i>&nbsp;&nbsp;&nbsp;Allocate Tecaher for Theory</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/fetchTeacherForPR"><i class="fa fa-clone"></i>&nbsp;&nbsp;&nbsp;Allocate Tecaher for Practical</a>
                </li>
            </ul>

            <ul class="list-unstyled CTAs">
                <li>
                    <a href="${pageContext.request.contextPath}/logOut">Logout</a>
                </li>
            </ul>
        </nav>
        <!-- Page Content  -->
        <div id="content">

            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <div class="container-fluid">

                    <button type="button" id="sidebarCollapse" class="btn btn-info">
                        <i class="fas fa-align-left"></i>
                        <span></span>
                    </button>
                    <button class="btn btn-dark d-inline-block d-lg-none ml-auto" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <i class="fas fa-align-justify"></i>
                    </button>
                </div>
            </nav> 
        <span style="color:red">${errString} </span>     
           <!-- Ur Page code -->   
         <br>
          <span style="color:green;font-size:20px">${successString} </span> 
         <br>  
        <form  method="POST" action="${pageContext.request.contextPath}/fetchTeacherForPR">
          
        <input style="visibility:hidden" name="classYears" value="${classYears}">
		<select name="classYear" style="width:559px" class="form-control" required>
			<option value="none" selected >Year</option>                      
			 <c:forEach items="${classYears}" var="classYear1">
	        	<option value="${classYear1}">${classYear1}</option>
	    	</c:forEach>
		</select>
		<input style="visibility:hidden" name="classDivNos" value="${classDivNos}">
		<select name="classDivNo" style="width:559px" class="form-control" required>
			<option value="none" selected >Div no</option>                      
			 <c:forEach items="${classDivNos}" var="classDivNo1">
	        	<option value="${classDivNo1}">${classDivNo1}</option>
	    	</c:forEach>
		</select>
		<br>
		
         <input name="batch" value="${batch}" placeholder="Batch" required="required" class="form-control col-md-7 col-xs-12">          
                 <br />
                 <button type="submit" class="btn btn-success">Fetch</button>
                   
        </form>
         <form  method="POST" action="${pageContext.request.contextPath}/allocateTeacherForPR">
          
          		<input style="visibility:hidden" name="classYear" value="${classYear}">
	    	<input style="visibility:hidden" name="classDivNo" value="${classDivNo}">
	    	  <input style="visibility:hidden" name="batch" value="${batch}">
		<c:set var="count" value="0" scope="page" />
		<c:forEach items="${newView3}" var="v2">
	    	<br><h4 style="color:black">${count+1} ) ${v2.subjectName} - ${v2.subjectCategory}</h4>
	    	<input style="visibility:hidden" name="o_subId${count}" value="${v2.o_subId}">
	    	<input style="visibility:hidden" name="subjectCategory${count}" value="${v2.subjectCategory}">
   	  		<select name="teacher${count}" style="width:559px" class="form-control" required>
   	  			<option value="none" selected >Teacher</option>                      
				 <c:forEach items="${teachers}" var="t1">
        			<option value="${t1.prnNo}">${t1.firstName} ${t1.middleName} ${t1.lastName}</option>
    			</c:forEach>
			</select>
			<c:set var="count" value="${count + 1}" scope="page"/>
	   	</c:forEach>  		
	   	<input style="visibility:hidden" name="count" value="${count}">
                  
                 <br />
                 <button type="submit" class="btn btn-success">Allocate</button>
                   
         </form>
        
                <br><br><br>
                <a href ="${pageContext.request.contextPath}/welcome1"><button style="float:left;background-color: #138496;"  class="btn1 info">Back</button></a>
          </div>
    </div>
    
<!-- partial -->
   <script>
  <%@include file="/WEB-INF/js/script1.js"%>
  </script>

</body>
</html>
        

        
