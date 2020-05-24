<!--
	
</html>-->

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

	<link href='https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,700' rel='stylesheet' type='text/css'><link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
	<link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet">

<!-- jQuery CDN - Slim version (=without AJAX) -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <!-- Popper.JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
    <!-- Bootstrap JS -->
    
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
<link rel="stylesheet" href="./style.css"><script src="https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>
    <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script><link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css'>
<style>
	<%@include file="/WEB-INF/css/style2.css"%>
	<%@include file="/WEB-INF/css/style3.css"%>
		<%@include file="/WEB-INF/css/style4.css"%>
		<%@include file="/WEB-INF/css/style6.css"%>
	</style>
</head>
<body>
	<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	response.setHeader("Expires", "0"); // Proxies.
	if(session.getAttribute("user1") == null)
		response.sendRedirect("http://localhost:8080/feedbackSystem/home1");
	
	%>
<!-- partial:index.partial.html -->

<div id="modal-container">
  <div class="modal-background">
    <div class="modal" >
      <h2>  Welcome, ${user.firstName}</h2>
      <p>PRN No : ${user.prnNo}</p>
       <p>Roll No : ${user.rollNo}</p>
      <svg class="modal-svg" xmlns="http://www.w3.org/2000/svg" width="100%" height="100%" preserveAspectRatio="none">
								<rect x="0" y="0" fill="none" width="226" height="162" rx="3" ry="3"></rect>
							</svg>
    </div>
  </div>
</div>

<div class="buttons" style="cursor:pointer">
    <div id="six" class="button"><i style="margin-left:20px;margin-top:20px" class="fas fa-user fa-2x"></i></div>
    <a href ="${pageContext.request.contextPath}/logOut"><button style="background-color:red;margin-left:1150px;margin-top:-200px;margin-bottom:30px;border-radius:10px 10px 10px 10px"> <i style="color:white"class="fas fa-sign-out-alt fa-2x"></i> </button></a>
 </div>
<div class="header">

  <div class="info" style="margin-top:20px">
    <h1>FEEDBACK MONITORING SYSTEM</h1>
  </div>
</div>

		<form method="POST"  id="smileys" action="${pageContext.request.contextPath}/submitSecurityFeedback">
			<br><br>
			<div class="card" style="margin-left:20px;width:1000px">
    			<div class="card-body">
    				 <c:set var="count" value="0" scope="page"/> 
	    		 <c:forEach items="${V2}" var="v3">
	        		 <h2>Q${count+1} ) ${v3.question}</h2>
	    			<input style="visibility:hidden" name="questionId${count}" value="${v3.questionId}">
	    			<br>
  					&emsp;&emsp;&emsp;<input type="radio" name="Q${count}" value="Option1" class="sad">
  					<input type="radio" name="Q${count}" value="Option2" class="neutral">					
  					<input type="radio" name="Q${count}" value="Option3" class="happy">
					<input type="radio" name="Q${count}" value="Option4" class="happy">
					<c:set var="count" value="${count+1}" scope="page"/>
					</c:forEach>  		
					<input style="visibility:hidden" name="count" value="${count}">
    			
    			</div>
  			</div>
			<button  style="margin-left:520px;margin-top:30px;width:200px" type="submit" name ="submit" class="btn btn-success">Submit</button>
	</form> 

 <div style="background-color:grey;margin-top:800px;height:100px">
  			
  		</div>

<!-- partial -->
  <script>
  <%@include file="/WEB-INF/js/script1.js"%>
  <%@include file="/WEB-INF/js/script2.js"%>
  <%@include file="/WEB-INF/js/script3.js"%>
  </script>

</body>
</html>
	
	