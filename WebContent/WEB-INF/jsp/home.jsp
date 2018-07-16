<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring MVC + Bootstrap</title>

<link href="../resources/css/bootstrap.min.css" rel="stylesheet" />
<link href="../resources/css/other.css" rel="stylesheet">

<%
	String path = request.getContextPath() ;
	String basePath = request.getScheme()
		+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    Object check_log = session.getAttribute("userId"); 
 %>


</head>

<body>

	<header>
    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Online Memorandum</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
            <li><a id='logKey' href="javascript:checkLog();"></a></li>
            <li><a href="/demo/user/SignupPage">Sign Up</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
    </header>
    
 <div class="container">

      <div class="row row-offcanvas row-offcanvas-right">

        <div class="col-xs-12 col-sm-9">
          <p class="pull-right visible-xs">
            <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
          </p>
          <div class="jumbotron">
            <h1>Recording Your Feeling!</h1>
            <p>This is one online notebook for you to record your everything</p>
          </div>
          <div class="row">
            <div class="col-xs-6 col-lg-4">
              <h2>Heading</h2>
              <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
              <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
            </div><!--/.col-xs-6.col-lg-4-->
            <div class="col-xs-6 col-lg-4">
              <h2>Heading</h2>
              <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
              <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
            </div><!--/.col-xs-6.col-lg-4-->
            <div class="col-xs-6 col-lg-4">
              <h2>Heading</h2>
              <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
              <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
            </div><!--/.col-xs-6.col-lg-4-->
          </div><!--/row-->
        </div><!--/.col-xs-12.col-sm-9-->

        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar">
          <div class="list-group">
          	<a href="#" class="list-group-item active">ITEMS</a>
            <a href="/demo/user/showPersonalInfor" class="list-group-item">Edit Personal Information</a>
            <a href="/demo/user/uploadFilePage" class="list-group-item">Upload Files</a>
            <a href="#" class="list-group-item">Memories</a>
            <a href="#" class="list-group-item">Switch Emotions</a>
            <a href="#" class="list-group-item">Pictures</a>
            <a href="#" class="list-group-item">Video</a>
          </div>
        </div><!--/.sidebar-offcanvas-->
      </div><!--/row-->

      <hr>

      <footer>
        <p>&copy; 2018 Personal </p>
      </footer>

    </div><!--/.container-->

<script type="text/javascript" src="../resources/js/jquery.js"></script>
<script type="text/javascript" src="../resources/js/bootstrap.min.js"></script>
 <script type="text/javascript">
	//初始化  common.js 定义的一些全局变量
	gv_BasePath = "<%=basePath%>";
	var WUser    ="<%=check_log%>";
	if(WUser == 'null'){
		//alert("未登录系统");
		//window.location.href='/demo/user/SignupPage';
		document.getElementById("logKey").innerHTML  = "Log in"; 
	} else {
		document.getElementById("logKey").innerHTML  = "Log out"; 
	}	
	
	function checkLog() {
		if(WUser == 'null'){
			window.location.href = '/demo/user/loginPage';
		} else {
			window.location.href = '/demo/user/doLogout';
		}	
	}
</script>

</body>
</html>
