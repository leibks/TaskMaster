<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Files Page</title>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link href="../resources/css/filePage.css" rel="stylesheet">
 <link rel="stylesheet" href="../resource/layui/css/layui.css">
</head>
<body>

<script type="text/javascript" src="../resources/js/jquery.js"></script>
<script type="text/javascript" src="../resources/js/bootstrap.min.js"></script>
<script type="text/javascrip" src="../resource/layui/layui.all.js"></script>

<!------ Include the above in your HEAD tag ---------->

<div class="container">
    <div class="row">
        <div class="">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <span class="glyphicon glyphicon-list"></span>File Lists
                    <div class="pull-right action-buttons">
                        <div class="btn-group pull-right dropdown">
                            <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown">
                                <span class="glyphicon glyphicon-cog" style="margin-right: 0px;"></span>                      
                            </button>
                            <ul class="dropdown-menu dropdown"  >
                                <li><a href="http://www.jquery2dotnet.com"><span class="glyphicon glyphicon-pencil"></span>Edit</a></li>
                                <li><a href="http://www.jquery2dotnet.com"><span class="glyphicon glyphicon-trash"></span>Delete</a></li>
                                <li><a href="http://www.jquery2dotnet.com"><span class="glyphicon glyphicon-flag"></span>Flag</a></li>
                            </ul>
                        </div>
                    </div>
                </div>              	
		<ul>
			
		</ul>
	
			  <c:if test="${!empty fileLists}">
			  <c:forEach items="${fileLists}" var="item">
                <div class="panel-body">
                    <ul class="list-group">
                        <li class="list-group-item">
                           <div class="checkbox">
                                <input type="checkbox" id="checkbox" />
                                <label>
                                    ${item.file_name}.${item.file_type}
                                </label>
                            </div>
                            <div class="pull-right action-buttons">                           
                                <button id="special" class="layui-btn">
                                <span class="glyphicon glyphicon-file"></span></button> 
                                                              
                                <a href="/demo/user/deleteFile?file_id=${item.file_id}" class="trash">
                                <span class="glyphicon glyphicon-trash"></span></a>
                                <a href="/demo/user/fileDownLoad?fileId=${item.file_id}" class="flag">
                                <span class="glyphicon glyphicon-download"></span></a>
                            </div>
                        </li>                                                                                   
                    </ul>
                </div>
              </c:forEach>
              </c:if>
                
                <div class="panel-footer">
                    <div class="row">
                        <div class="col-md-6">
                            <h6>
                                Total Count <span class="label label-info">${file_numer}</span></h6>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    	<form action="<%=request.getContextPath() %>/user/UploadFile" method="post"  enctype="multipart/form-data">
  		<div class="form-group">
    		<label for="exampleFormControlFile1">Example file input</label>
    		<input type="file" name='file' class="form-control-file" id="exampleFormControlFile1">
  		</div>
  		<div class="form-group row">
    		<div class="col-sm-10">
      		<button type="submit" class="btn btn-primary">Submit</button>
    		</div>
    	</div>
    	<div class="pull-right">
	      	<a href="/demo/user/home" class="btn btn-primary btn-block" >Back to Home Page</a>
	    </div>
		</form>
</div>
<script>
	$('#special').on('click', function(){
		var layer = layui.layer
		  ,form = layui.form;
    	layer.msg('hello');
  	});
</script>

</body>
</html>