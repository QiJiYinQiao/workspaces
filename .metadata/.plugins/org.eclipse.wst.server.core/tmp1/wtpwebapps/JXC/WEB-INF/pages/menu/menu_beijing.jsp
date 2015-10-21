<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>登陆首页</title>
	<link rel="stylesheet" type="text/css" href="media/css/easyui.css">
	<link rel="stylesheet" type="text/css" href="media/css/icon.css">
	<link rel="stylesheet" type="text/css" href="media/css/demo.css">
	<script type="text/javascript" src="media/js/jquery.min.js"></script>
	<script type="text/javascript" src="media/js/jquery.easyui.min.js"></script>
	<style type="text/css">
		a:link,a:visited {color: blue; text-decoration:none;font-size:16px;} //未访问：蓝色、无下划线   
		/* a:VISITED{color: blue; text-decoration:none;font-size:16px;}//点击后样式不变 */
		.divstyle{
			backgroundColor:"rgb(226,224,200)";
		}
		a div{
			font-size:14px;
			width:100%;
			height: 20px;
			text-align:center;
			padding-top: 4px;
		}
	</style>
</head>
<body class="easyui-layout" style="background-color: #F8F8FF;overflow: auto;">
			<div style="width: 100%;font-size: 16px;border: thin;">现场订单</div>
			<a href="#" onclick="clickutil('order','otherCounterAdd');"><div id="orderOtheradd" onmouseenter="enter('orderOtheradd')" onmouseleave="leave('orderOtheradd')" >设备订购(其他柜台)</div></a>
			<a href="#" onclick="clickutil('order','search');"><div id="searchorder" onmouseenter="enter('searchorder')" onmouseleave="leave('searchorder')"  >设备退还</div></a>
			<a a href="#" onclick="clickutil('order','list');"><div id="orderlist" onmouseenter="enter('orderlist')" onmouseleave="leave('orderlist')" >订单管理</div></a>
			
			<div style="width: 100%;font-size: 16px;border: thin;">订单管理</div>
			<a a href="#" onclick="clickutil('jcborder','list');"><div id="jcborderlist" onmouseenter="enter('jcborderlist')" onmouseleave="leave('jcborderlist')" >网站订单</div></a>
			
	<div id="menu_shadow" style="display: none; z-index: 1000; top: 0px; left: 0px; position: fixed; height: 100%; width: 100%;opacity:0.8; background-color:#708090;">
	</div>
	<script type="text/javascript">
		//=============柜台菜单点击方法 开始===============================
		function clickutil(str,address){
			top.document.getElementById("countents").src = str+"/"+address;
		}
		
		
		function enter(obj){
			/* alert(obj);
			alert(obj.style+":"+obj.style.cssText);
			 */
			var objs = document.getElementById(obj);
			/* objs.css("backgroundColor","rgb(226,224,200)"); */
			objs.style.backgroundColor="rgb(226,224,200)";
		}
		function leave(obj){
			var objs = document.getElementById(obj);
			objs.style.backgroundColor="";
		}
	</script>
</body>
 
</html>