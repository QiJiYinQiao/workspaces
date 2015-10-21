<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="../media/css/easyui.css">
	<link rel="stylesheet" type="text/css" href="../media/css/icon.css">
	<link rel="stylesheet" type="text/css" href="../media/css/demo.css">
	<script type="text/javascript" src="../media/js/WdatePicker.js"></script>
	<script type="text/javascript" src="../media/js/jquery.min.js"></script>
	<script type="text/javascript" src="../media/js/jquery.easyui.min.js"></script>
</head>
<body class="easyui-layout" >
	<input type="text" style="display:none;" id="status" value="${status }">
	<script>
        window.onload = function() {
        	var status = $("#status").val();
            if (status == "0") {
            alert("导入成功");
            setTimeout(function yanshi(){
            window.location = "getAll";
        },500);
        } else if (status == "-1") {
            alert("导入失败，请确认文件格式是否正确");
            window.location = "getAll";
        }else if(status == "1"){
        	alert("导入失败，表中有重复数据!")
        	window.location = "getAll";
        }
       
}
</script>
</body>
</html>