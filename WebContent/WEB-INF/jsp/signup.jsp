<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../../../favicon.ico">

    <title>Signin Template for Bootstrap</title>

<link href="../resources/css/bootstrap.min.css" rel="stylesheet" />
    <!-- Custom styles for this template -->
<link href="../resources/css/signup.css" rel="stylesheet">

  </head>

  <body class="text-center">
    <form class="form-signin" action="<%=request.getContextPath() %>/user/addInfo.do" method="post">
      <img class="mb-4" src="https://getbootstrap.com/assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">
      <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
      
      <label for="inputName" class="sr-only">User Name</label>
      <input type="text" name="userName" id="inputName" class="form-control" placeholder="Username" required autofocus>
      
      <label for="inputPassword" class="sr-only">Password</label>
      <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password" required>
      
      <label for="inputAge" class="sr-only">Email address</label>
      <input type="text"       name="age" id="inputAge" class="form-control" placeholder="age" required autofocus>
      
      <div class="checkbox mb-3">
        <label>
          <input type="checkbox" value="remember-me"> Remember me
        </label>
      </div>
      <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
      <p class="mt-5 mb-3 text-muted">&copy; 2017-2018</p>
    </form>
  </body>
</html>