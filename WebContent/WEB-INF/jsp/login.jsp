<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring MVC + Bootstrap</title>

<link href="../resources/css/bootstrap.min.css" rel="stylesheet" />
    <!-- Custom styles for this template -->
    
<link href="../resources/css/login.css" rel="stylesheet">

</head>

<body>
    <div class="container">

      <form class="form-signin" action="<%=request.getContextPath() %>/user/login" method="post">
        <h2 class="form-signin-heading">Please log in</h2>
        
       <label for="inputName" class="sr-only">User Name</label>
       <input type="text" name="userName" id="inputName" class="form-control" placeholder="Username" required autofocus>
      
       <label for="inputPassword" class="sr-only">Password</label>
       <input type="password" name="password" id="inputPassword" class="form-control" placeholder="password" required>
        
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> Remember me
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Login in</button>
      </form>

    </div> <!-- /container -->

<script type="text/javascript" src="../resources/js/jquery.js"></script>
<script type="text/javascript" src="../resources/js/bootstrap.min.js"></script>


</body>
</html>
