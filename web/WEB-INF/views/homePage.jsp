<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en" >
<head>
  <meta charset="UTF-8">
  <title>Login</title>
  <link href="<%=request.getContextPath()%>/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css" />
  <style><%@include file="/WEB-INF/css/style.css"%></style>
</head>
<body>

<!-- partial:index.partial.html -->
<!DOCTYPE html>
<html>
  <head>
  	<style><%@include file="/WEB-INF/css/style.css"%></style>
    <meta charset="utf-8">
    <title>Flip Login</title>

<body style="margin-top:2px;background-size: cover;background-repeat: no-repeat;background-image:url(https://c4.wallpaperflare.com/wallpaper/227/256/2/blue-white-material-design-minimal-art-wallpaper-preview.jpg)">

  <div  style="margin-left:350px;margin-top:2px;" class="flip-card">
      <div class="flip-card-inner">
      <div class="flip-card-front">
  <div class="well login-box center-block">
    <img class="logo" src="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSv-c6kDV4Zmd0IF8nIyqZqnOSeQWZ0UinySeuWkoWoIkVI55ig"></img>
    <center><h3>Student Login</h3></center>
    <br>
    <form name="login" method="POST" action="${pageContext.request.contextPath}/studentLogin">

      <input name = "prnNo" type="text"  class="form-control" placeholder="PrnNo"  />

      <input type="text" class="form-control" id="pass" placeholder="Password" name = "password"  />
      
      <button class="btn btn-primary" type="submit" >Login</button>
      <br>
      <br>
       <a href="javascript: void(0);" class="registerbtn">Admin</a>
     </form>  
  </div>
  </div>


 <div  class="flip-card-back">
        <div class="well login-box center-block">
    <img class="logo" src="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSv-c6kDV4Zmd0IF8nIyqZqnOSeQWZ0UinySeuWkoWoIkVI55ig"></img>
    <center><h3>Admin Login</h3></center>
    <br>
    <form method="POST" action="${pageContext.request.contextPath}/adminLogin">

      <input  class="form-control" placeholder="adminId" name = "adminId" type="text" value ="${adminId}" />

      <input  class="form-control" id="pass" placeholder="Password" name = "adminPassword" type="text" value = "${adminPassword }"  />
      
      <button class="btn btn-primary" type="submit" >Login</button>
      <br>
      <br>
      <a href="#" id="unflip-btn" class="registerbtn">Student</a>
	</form>
  </div>
     </div>    
        
        </div>
        </div>
    
  <script src="<%=request.getContextPath()%>/bootstrap/jquery-3.4.1.min.js"></script>
    
    </head>
  </body>
</html>
<!-- partial -->
  <script>
  <%@include file="/WEB-INF/js/script.js"%>
  </script>

</body>
</html>