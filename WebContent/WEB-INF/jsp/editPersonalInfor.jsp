<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%String first_name = (String)request.getAttribute("first_name");%>
<%String last_name = (String)request.getAttribute("last_name");%>
<%String gender = (String)request.getAttribute("gender");%>
<%String street = (String)request.getAttribute("street");%>
<%String email = (String)request.getAttribute("email");%>
<%int phone_num = (Integer)request.getAttribute("phone_num");%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>editPersonalInfor</title>

<link href="../resources/css/bootstrap.min.css" rel="stylesheet" />
    <!-- Custom styles for this template -->
<link href="../resources/css/login.css" rel="stylesheet">

</head>

<body>
	<div class="container">
	 <div class="row">
	  <div class="col-md-4" >
	   <div class="panel panel-primary">
	    <div class="panel-heading">Personnel information :</div>
	     <div class="panel-body">
		 <form class="form-signin" action="<%=request.getContextPath() %>/user/editInfor" method="post">
		  <label>First Name</label><input name='first_name' type='text' class='form-control'  value='<%=first_name%>' >
	      <label>Last Name : </label><input name='last_name' type='text' class='form-control'  value='<%=last_name%>' > 
		  <label>Gender : </label><input name='personal_gender' type='text' class='form-control'  value='<%=gender%>'>
		  <label>Street : </label><input name='street' type='text' class='form-control'  value='<%=street%>' >
		  <label>Email :</label><input name='email' type='email' class='form-control'  value='<%=email%>' > 
	      <label>Number telephone :</label><input id='phone_num' name='phone_num' type='text' class='form-control'  placeholder='<%=phone_num%>'> 
	      <br />
	      <div class="pull-right">
	      		<button id="edit" class="btn btn-primary btn-block" type="submit">Edit</button>	     
	      </div>
	      <div class="pull-left">
	      	<a href="/demo/user/home" class="btn btn-primary btn-block" >Back to Home Page</a>
	      </div>
	     </form>
	     </div>
	   </div>
	  </div>
	 </div>
	</div>
	
	<script type="text/javascript" src="../resources/js/jquery.js"></script>
	<script type="text/javascript" src="../resources/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$("#edit").click(function(){
				var phone_number = $("#phone_num").val();
				if (isNaN(parseInt(phone_number))) {
					alert("Please Input Corrent Number");
					return false;
				} else {
					alert("Edit Information Successfully");
				}
			})
		})
	</script>
</body>
</html>