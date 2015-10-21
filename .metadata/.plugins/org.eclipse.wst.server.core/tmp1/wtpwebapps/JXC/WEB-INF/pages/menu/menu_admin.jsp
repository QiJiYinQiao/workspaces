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
			<a href="#" onclick="clickutil('order','add');"><div id="orderadd" onmouseenter="enter('orderadd')" onmouseleave="leave('orderadd')" >设备订购(上海柜台)</div></a>
			<a href="#" onclick="clickutil('order','otherCounterAdd');"><div id="orderOtheradd" onmouseenter="enter('orderOtheradd')" onmouseleave="leave('orderOtheradd')" >设备订购(其他柜台)</div></a>
			<a href="#" onclick="clickutil('order','search');"><div id="searchorder" onmouseenter="enter('searchorder')" onmouseleave="leave('searchorder')"  >设备退还</div></a>
			<a a href="#" onclick="clickutil('order','list');"><div id="orderlist" onmouseenter="enter('orderlist')" onmouseleave="leave('orderlist')" >订单管理</div></a>
			
			<div style="width: 100%;font-size: 16px;border: thin;">订单管理</div>
			<a href="#" onclick="clickutil('wxorder','add');"><div id="wxorderadd" onmouseenter="enter('wxorderadd')" onmouseleave="leave('wxorderadd')" >设备预订</div></a>
			<a a href="#" onclick="clickutil('wxorder','list');"><div id="wxorderlist" onmouseenter="enter('wxorderlist')" onmouseleave="leave('wxorderlist')" >预订单管理</div></a>
			<a a href="#" onclick="clickutil('jcborder','list');"><div id="jcborderlist" onmouseenter="enter('jcborderlist')" onmouseleave="leave('jcborderlist')" >网站订单</div></a>
			
			<div style="width: 100%;font-size: 16px;border: thin;">设备管理</div>
			<a href="#" onclick="clickutil('equipment','getAll');"><div id="equipmentlist" onmouseenter="enter('equipmentlist')" onmouseleave="leave('equipmentlist')"  >设备列表</div></a>
			<a href="#" onclick="clickutil('equipment','add');"><div id="equipmentadd" onmouseenter="enter('equipmentadd')" onmouseleave="leave('equipmentadd')"  >添加设备</div></a>
			<a href="#" onclick="clickutil('equipment','instorage');"><div id="equipmentinstorage" onmouseenter="enter('equipmentinstorage')" onmouseleave="leave('equipmentinstorage')"  >设备入库</div></a>
			
			<div style="width: 100%;font-size: 16px;border: thin;">库存盘点</div>
			<a href="#" onclick="clickutil('equipment','inventorymainadd');"><div id="equipmentinventory_add" onmouseenter="enter('equipmentinventory_add')" onmouseleave="leave('equipmentinventory_add')"  >新增盘点</div></a>
			<a href="#" onclick="clickutil('equipment','inventorymainlist');"><div id="equipmentinventory_list" onmouseenter="enter('equipmentinventory_list')" onmouseleave="leave('equipmentinventory_list')"  >盘点管理</div></a>
			
			<div style="width: 100%;font-size: 16px;border: thin;">数据卡管理</div>
			<a href="#" onclick="clickutil('info','list');"><div id="infolist" onmouseenter="enter('infolist')" onmouseleave="leave('infolist')"  >数据卡列表</div></a>
			<a href="#" onclick="clickutil('info','add');"><div id="infoadd" onmouseenter="enter('infoadd')" onmouseleave="leave('infoadd')"  >添加数据卡</div></a>
			
			<div style="width: 100%;font-size: 16px;border: thin;">系统管理</div>
			<a href="#" onclick="clickutil('user','add');"><div id="useradd" onmouseenter="enter('useradd')" onmouseleave="leave('useradd')"  >新增用户</div></a>
			<a href="#" onclick="clickutil('user','discountday');"><div id="userdiscountday" onmouseenter="enter('userdiscountday')" onmouseleave="leave('userdiscountday')"  >优惠天数</div></a>
			<!-- <a href="#" onclick="clickutil('user','managephoto');"><div id="managephoto" onmouseenter="enter('managephoto')" onmouseleave="leave('managephoto')"  >经营图表</div></a> -->
			<a href="#" onclick="clickutil('user','ordertabel');"><div id="ordertabel" onmouseenter="enter('ordertabel')" onmouseleave="leave('ordertabel')"  >订单报表</div></a>
			<a href="#" onclick="clickutil('user','daymanageinfo');"><div id="daymanageinfo" onmouseenter="enter('daymanageinfo')" onmouseleave="leave('daymanageinfo')"  >日经营情况</div></a>
			<a href="#" onclick="clickutil('dictionarycode','list');"><div id="codelist" onmouseenter="enter('codelist')" onmouseleave="leave('codelist')"  >字典管理</div></a>
			
			<div style="width: 100%;font-size: 16px;border: thin;">经营图表</div>
			<a href="#" onclick="clickutil('order','queryOrderDetail');"><div id="orderqueryOrderDetail" onmouseenter="enter('orderqueryOrderDetail')" onmouseleave="leave('orderqueryOrderDetail')"  >订单统计</div></a>
			<a href="#" onclick="clickutil('user','passengerdetail');"><div id="passengerdetail" onmouseenter="enter('passengerdetail')" onmouseleave="leave('passengerdetail')"  >旅客明细</div></a>
			<a href="#" onclick="clickutil('user','billlist');"><div id="billlist" onmouseenter="enter('billlist')" onmouseleave="leave('billlist')"  >移动WIFI月账单</div></a>
			<a href="#" onclick="clickutil('user','dayInOutProportion');"><div id="dayInOutProportion" onmouseenter="enter('dayInOutProportion')" onmouseleave="leave('dayInOutProportion')"  >收支对比图</div></a>
			<a href="#" onclick="clickutil('user','dayInAndOut');"><div id="dayInAndOut" onmouseenter="enter('dayInAndOut')" onmouseleave="leave('dayInAndOut')"  >日收支统计图</div></a>
			<a href="#" onclick="clickutil('user','everyDayOrderNums');"><div id="everyDayOrderNums" onmouseenter="enter('everyDayOrderNums')" onmouseleave="leave('everyDayOrderNums')"  >日订单数量图</div></a>
			<a href="#" onclick="clickutil('user','numbersOfOrdersPie');"><div id="numbersOfOrdersPie" onmouseenter="enter('numbersOfOrdersPie')" onmouseleave="leave('numbersOfOrdersPie')"  >各国订单数量图</div></a>
	
			<div style="width: 100%;font-size: 16px;border: thin;">常用信息</div>
			<a href="#" onclick="clickutil('wxorder','pricelist');"><div id="wxorderpricelist" onmouseenter="enter('wxorderpricelist')" onmouseleave="leave('wxorderpricelist')"  >价目表</div></a>
			<!-- <a href="#" onclick="clickutil('wxorder','counterdetail');"><div id="wxcounterdetail" onmouseenter="enter('wxcounterdetail')" onmouseleave="leave('wxcounterdetail')"  >柜台详情</div></a> -->
			<a href="#" onclick="clickutil('wxorder','pordernum');"><div id="wxpordernum" onmouseenter="enter('wxpordernum')" onmouseleave="leave('wxpordernum')"  >个人订单量</div></a>
			<a href="#" onclick="clickutil('wxorder','preordernum');"><div id="wxpreordernum" onmouseenter="enter('wxpreordernum')" onmouseleave="leave('wxpreordernum')"  >预定订单量</div></a>
			
			<div style="width: 100%;font-size: 16px;border: thin;">权限管理</div>
			<a href="#" onclick="clickutil('role','findAllRole');"><div id="role" onmouseenter="enter('findAllRole')" onmouseleave="leave('findAllRole')"  >角色管理</div></a>
			
			
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