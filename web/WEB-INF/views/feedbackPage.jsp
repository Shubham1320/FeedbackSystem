


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
	<%@include file="/WEB-INF/css/style5.css"%>	
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
    <div id="six" class="button"><i style="margin-left:35px;margin-top:35px" class="fas fa-user fa-2x"></i></div>
    <a href ="${pageContext.request.contextPath}/logOut"><button style="background-color:red;margin-left:1150px;margin-top:-200px;margin-bottom:30px;border-radius:10px 10px 10px 10px"> <i style="color:white"class="fas fa-sign-out-alt fa-2x"></i> </button></a>
 </div>
<div class="header">

  <div class="info" style="margin-top:20px">
    <h1>FEEDBACK MONITORING SYSTEM</h1>
  </div>
</div>


	<c:set var="count" value="0" scope="page"/>
	    	<c:forEach items="${V1}" var="v2">
	    		<form id="form${count}" method = "POST" action="${pageContext.request.contextPath}/giveFeedback1"> 	    		 
	    		 	<input style="visibility:hidden" name="o_year${count}" value="${v2.o_Year}">
	    		 	<input style="visibility:hidden" name="o_divNo${count}" value="${v2.o_divNo}">
	    		 	<input style="visibility:hidden" name="subjectId${count}" value="${v2.subjectId}">
	    		 	<input style="visibility:hidden" name="tPrnNo${count}" value="${v2.tPrnNo}">    		 
	    		 	<input style="visibility:hidden" name="current" value="${count}">
	    		 	<button  name="submit" type="submit" style="padding-left:10px"><div class="flip" style="margin-left:100px">
    					<div class="front" style="">
    					<c:if test="${v2.subjectCategory == 'PR'}">
 							<i style="display:inline"  class="fas fa-flask fa-4x"></i>
						</c:if>
						<c:if test="${v2.subjectCategory == 'TH'}">
 							<i style="display:inline"  class="fas fa-book fa-4x"></i>
						</c:if>
      					 <h1 style="display:inline;margin-left:20px;margin-top:5px" class="text-shadow">${v2.subjectName} - ${v2.subjectCategory }</h1>
    					</div>
   						 <div class="back">
						<h1 style="margin-top:10px">${v2.tFirstName}  ${v2.tMiddleName}  ${v2.tLastName}</h1>
						</div>
   					 	</div>
   					 	</button>
   	  				<c:set var="count" value="${count+1}" scope="page"/>	
   	  			</form>
	    	 </c:forEach>  		 
			

 <div style="background-color:grey;margin-top:800px;height:100px">
  			
  		</div>

<!-- partial -->
  <script>
  <%@include file="/WEB-INF/js/script1.js"%>
  <%@include file="/WEB-INF/js/script2.js"%>
  </script>

</body>
</html>

