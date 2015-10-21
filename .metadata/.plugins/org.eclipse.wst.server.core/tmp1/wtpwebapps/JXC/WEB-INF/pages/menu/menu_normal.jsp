<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>登陆首页</title>
	<link rel="stylesheet" type="text/css" href="http://localhost:8080/JXC/media/css/easyui.css">
	<link rel="stylesheet" type="text/css" href="http://localhost:8080/JXC/media/css/icon.css">
	<link rel="stylesheet" type="text/css" href="http://localhost:8080/JXC/media/css/demo.css">
	<script type="text/javascript" src="http://localhost:8080/JXC/media/js/jquery.min.js"></script>
	<script type="text/javascript" src="http://localhost:8080/JXC/media/js/jquery.easyui.min.js"></script>
</head>
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
<body class="easyui-layout">
		<div style="width: 100%;font-size: 16px;border: thin;">订单管理</div>
			<c:choose>
				<c:when test="${countertype eq 1 }">
					<a href="#" onclick="clickutil('order','add');"><div id="orderadd" onmouseenter="enter('orderadd')" onmouseleave="leave('orderadd')" >设备订购(上海柜台)</div></a>
				</c:when>
				<c:otherwise>
					<a href="#" onclick="clickutil('order','otherCounterAdd');"><div id="orderOtheradd" onmouseenter="enter('orderOtheradd')" onmouseleave="leave('orderOtheradd')" >设备订购(其他柜台)</div></a>
				</c:otherwise>
			</c:choose>
			<a href="#" onclick="clickutil('order','search');"><div id="searchorder" onmouseenter="enter('searchorder')" onmouseleave="leave('searchorder')"  >设备退还</div></a>
			<a a href="#" onclick="clickutil('order','list');"><div id="orderlist" onmouseenter="enter('orderlist')" onmouseleave="leave('orderlist')" >订单管理</div></a>
			
			<div style="width: 100%;font-size: 16px;border: thin;">设备管理</div>
			<a href="#" onclick="clickutil('equipment','instorage');"><div id="equipmentinstorage" onmouseenter="enter('equipmentinstorage')" onmouseleave="leave('equipmentinstorage')"  >设备入库</div></a>
			
			<div style="width: 100%;font-size: 16px;border: thin;">库存盘点</div>
			<a href="#" onclick="clickutil('equipment','inventorymainadd');"><div id="equipmentinventory_add" onmouseenter="enter('equipmentinventory_add')" onmouseleave="leave('equipmentinventory_add')"  >新增盘点</div></a>
			<a href="#" onclick="clickutil('equipment','inventorymainlist');"><div id="equipmentinventory_list" onmouseenter="enter('equipmentinventory_list')" onmouseleave="leave('equipmentinventory_list')"  >盘点管理</div></a>
			
			
	<script type="text/javascript">
		//=============柜台菜单点击方法 开始===============================
		function clickutil(str,address){
			//alert("ooclick");
			 /* jQuery.ajax({
		        	url:"firstofall",
		        	type:"POST",
		        	error:function(request){
		        		//alert("no data");
		        	},
		        	success:function(data){
		        		if(data=="0"){
		        			alert("此用户已在别处登陆，请重新登陆");
		        			top.window.location ="login";
		        			//reutrn false; 
		        			//top.document.getElementById("countents").src = ;
		        		}else{
		        			reutrn false;  
		        		}
		        	}
		        	});	 */
			top.document.getElementById("countents").src = str+"/"+address;
		}
		
		function f1(){
			parent.frames["countents"].src = "counter/list";
		}
		//=============柜台菜单点击方法 结束===============================
		/******************************************************************/
		//=============3g订购卡菜单点击方法 开始===============================
		function orderlist(){
			//alert("parent.");
			//alert(top.document.getElementById("countents").src);
			//alert(parent.frames["countents"]);
			//parent.frames["countents"].src = "order/list";
			top.document.getElementById("countents").src = "order/list";
		}
		function addlist(){
			top.document.getElementById("countents").src = "order/add";
			
		}
		function searchs(){
			top.document.getElementById("countents").src = "order/search";
		}
		//=============3g订购卡菜单点击方法 开始===============================
		/******************************************************************/
		//=============用户菜单点击方法 开始===============================
		function userlist(){
			parent.frames["countents"].src = "user/list";
		}
		//=============用户菜单点击方法 结束===============================
		/******************************************************************/
		//=============设备菜单点击方法 开始===============================
		function equipmentlist(){
			top.document.getElementById("countents").src = "equipment/getAll";
		}
		function equipmentadd(){
			top.document.getElementById("countents").src = "equipment/add";
		}
		//=============设备菜单点击方法 结束===============================
		/******************************************************************/
		//=============国际漫游卡菜单点击方法 开始===============================
		function infolist(){
			parent.frames["countents"].src = "info/list";
		}
		//=============国际漫游卡菜单点击方法 结束===============================
			
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