<!--
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
	
	
	
	<hr>
	<h3>Welcome ...</h3>
	<br>
	<h4>PRN No: ${user.prnNo}</h4>
	<h4>Roll No: ${user.rollNo}</h4>
	<h4>Name : ${user.firstName} ${user.middleName} ${user.lastName}</h4>
	<hr>
	<a href = "${pageContext.request.contextPath}/fetchStudentLoadMapping"> <button> Faculty </button> </a>
	<hr>
	<a href = "${pageContext.request.contextPath}/libraryFeedback"> <button> Library </button> </a>
	<hr>
	<a href = "${pageContext.request.contextPath}/canteenFeedback"> <button> Canteen </button> </a>
	<hr>
	<a href = "${pageContext.request.contextPath}/medicalSFeedback"> <button> Medical Services </button> </a>
	<hr>
	<a href = "${pageContext.request.contextPath}/stationaryFeedback"> <button> Stationary </button> </a>
	<hr>
	<a href = "${pageContext.request.contextPath}/securityFeedback"> <button> Security </button> </a>
	<hr>
	<a href = "${pageContext.request.contextPath}/sportsFeedback"> <button> Sports </button> </a>
	<hr>
	<a href = "${pageContext.request.contextPath}/hostelFeedback"> <button> Hostel </button> </a>	
	<hr>
	<a href ="${pageContext.request.contextPath}/logOut"><button> LOGOUT </button></a>
	<hr>
</body>
</html>-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
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

<section class="content">
  <ul class="masonry-list" >
            <li class="tile-job" style="margin-top:600px;width:250px;height:250px">
    			<a href="${pageContext.request.contextPath}/fetchStudentLoadMapping">
      			<div class="tile-primary-content">
        			<p align="center" style="margin-top:30px"><i class="fas fa-chalkboard-teacher fa-4x"></i></p>
      			</div>
      			<div class="tile-secondary-content">
        			<p>FACULTY FEEDBACK</p>
      			</div>
    			</a>
  			</li>
  			  <li class="tile-job" style="margin-top:600px;margin-left:50px;width:250px;height:250px">
    			<a href="${pageContext.request.contextPath}/hostelFeedback">
      			<div class="tile-primary-content" style="background-color: #604951;">
        			<p align="center"><i class="fas fa-building fa-5x"></i></p>
      			</div>
      			<div class="tile-secondary-content">
        			<p>HOSTEL FEEDBACK</p>
      			</div>
    			</a>
  			</li>
  			  <li class="tile-job" style="margin-top:600px;margin-left:50px;width:250px;height:250px">
    			<a href="${pageContext.request.contextPath}/canteenFeedback">
      			<div class="tile-primary-content" style="background-color: #00afe0;">
        			<p align="center"><i class="fas fa-utensils fa-5x"></i></p>
      			</div>
      			<div class="tile-secondary-content">
        			<p>CANTEEN FEEDBACK</p>
      			</div>
    			</a>
  			</li>
  			
  			  <li class="tile-job" style="margin-top:50px;width:250px;height:250px">
    			<a href="${pageContext.request.contextPath}/sportsFeedback">
      			<div class="tile-primary-content" style="background-color: #a49058;">
        			<p align="center"><i class="fas fa-futbol fa-5x"></i></p>
      			</div>
      			<div class="tile-secondary-content">
        			<p>SPORTS FACILITIES</p>
      			</div>
    			</a>
  			</li>
  			  <li class="tile-job" style="margin-top:50px;margin-left:50px;width:250px;height:250px">
    			<a href="${pageContext.request.contextPath}/medicalSFeedback">
      			<div class="tile-primary-content" style="background-color: #e84e40;">
        			<p align="center"><i class="fas fa-ambulance fa-5x"></i></p>
      			</div>
      			<div class="tile-secondary-content">
        			<p>MEDICAL SERVICES</p>
      			</div>
    			</a>
  			</li>
  			  <li class="tile-job" style="margin-top:50px;margin-left:50px;width:250px;height:250px">
    			<a href="${pageContext.request.contextPath}/libraryFeedback"">
      			<div class="tile-primary-content" style="background-color: #71964f;">
        			<p align="center"><i class="fas fa-book fa-5x"></i></p>
      			</div>
      			<div class="tile-secondary-content">
        			<p>LIBRARY FEEDBACK</p>
      			</div>
    			</a>
  			</li>
  			
  			  <li class="tile-job" style="margin-top:50px;width:250px;height:250px">
    			<a href="${pageContext.request.contextPath}/securityFeedback">
      			<div class="tile-primary-content" style="background-color: #00afe0;">
        			<p align="center"><i class="fas fa-shield-alt fa-5x"></i></p>
      			</div>
      			<div class="tile-secondary-content">
        			<p>SECURITY FACILITIES</p>
      			</div>
    			</a>
  			</li>
  			  <li class="tile-job" style="margin-top:50px;margin-left:50px;width:250px;height:250px" >
    			<a href="${pageContext.request.contextPath}/stationaryFeedback">
      			<div class="tile-primary-content" style="background-color: #604951;">
        			<p align="center"><i class="fas fa-print fa-5x"></i></p>
      			</div>
      			<div class="tile-secondary-content">
        			<p>STATIONARY FACILITES</p>
      			</div>
    			</a>
    		</li>
  		</ul>
</section>

 <div style="background-color:grey;margin-top:800px;height:100px">
  			
  		</div>

<!-- partial -->
  <script>
  <%@include file="/WEB-INF/js/script1.js"%>
  <%@include file="/WEB-INF/js/script2.js"%>
  </script>

</body>
</html>
	
	