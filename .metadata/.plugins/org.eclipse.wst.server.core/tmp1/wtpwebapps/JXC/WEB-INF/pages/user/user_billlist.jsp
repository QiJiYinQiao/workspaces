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
	<script type="text/javascript" src="../media/js/jquery.min.js"></script>
	<script type="text/javascript" src="../media/js/WdatePicker.js"></script>
	<script type="text/javascript" src="../media/js/jquery.easyui.min.js"></script>
	<style type="text/css">
		.datagrid-view{
			background-color: #F8F8FF;
			font-size: 16px;
		}
		input{
			border: 1px solid #95b8e7;
			border-radius: 5px;
			height: 20px;
			/* background-color:#B0C4DE; */
			width:120px;
			font-size:16px;
			
		}
		table td{
			font-size: 16px;border: 1px solid black;
		}
		table.gridtable {
			font-size: 18px;
			font-family: verdana,arial,sans-serif;
			color:#333333;
			border-width: 1px;
			border-color: #666666;
			border-collapse: collapse;
		}
		table.gridtable th {
			border-width: 1px;
			padding: 8px;
			border-style: solid;
			border-color: #666666;
			background-color: #dedede;
		}
		table.gridtable td {
			border-width: 1px;
			padding: 8px;
			border-style: solid;
			border-color: #666666;
			background-color: #ffffff;
		}
	</style>
</head>
<body class="easyui-layout">
		<script type="text/javascript">
			$(function(){
				$("#dg").datagrid({
					url:"getlist"
				})
			});
		</script>
		<!-- 表单开始 -->
		<div data-options="region:'center',title:'移动WIFI月账单'" style="overflow:auto;left:1px; top:1px;background-color: #F8F8FF;text-align: center;width: 100%;height: 100%;">
				<div style="background-color:#F8F8FF;align:center;width: 100%;height:50px;overflow: hidden;">
					<div style="width:100%;height:40px;text-align: center;">
						<span style="font-size:18px;">移动WIFI月账单</span></br>
						<span style="font-size:14px;">客户：天天商旅</span></br>
					</div>
					<!-- <form id="queryBy" action="queryByCondition" method="post" style="margin-left: 1%;text-align: left;width: 100%;">
						<table style="width: 100%;">
							<tr>
								<td>账单月份：</td><td><input id="month" name="month" value="" style="border: 1px solid #95b8e7;border-radius: 5px;" class="Wdate" type="text" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy-MM'})"/></td>
								<td>账单金额：</td><td>39309</td>
							</tr>
						</table>
					</form> -->
					<!-- <div align="right" style="margin-bottom: 5px;">
						<a onclick="checkdetail();" class="easyui-linkbutton" style="margin-right: 5px;width: 60px;height: 25px;">查看</a>
						<a onclick="submits();" class="easyui-linkbutton" style="margin-right: 5px;width: 60px;height: 25px;">查询</a>
					</div> -->
				</div>
				<div style="width:600px;height:400px;text-align: center;margin-left: 25%;">
						<span>账单月份:<input id="month" name="month" value="${datas }" style="border: 1px solid #95b8e7;border-radius: 5px;" class="Wdate" type="text" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy-MM',onpicked:pickdate})"/></span>
						&nbsp;&nbsp;&nbsp;&nbsp;账单金额：<span id="allbillmoney"></span>
					<table id="billlist" class="gridtable" style="width: 100%;height: 100%;">
					<tr><td>国家</td><td>数量</td><td>单价</td><td>金额</td></tr>
					<c:if test="${billlist!=null}">
						<c:forEach items="${billlist }" var="bill">
							<tr><td>${bill.country }</td><td>${bill.number }</td><td>${bill.dayrent }</td><td>${bill.number*bill.dayrent }</td></tr>
						</c:forEach>
					</c:if>
					<tfoot><tr><td>总计</td><td><span id="allnum" style="font-size: 16px;"></span></td><td></td><td><span style="font-size: 16px;" id="allmoney"></span></td></tr></tfoot>
					</table>
				</div>
		<!-- 表单结束 -->
		
			<div id="dlg"  class="easyui-dialog" modal="true" closed="true" title="用户详情"  style="width:880px;height:450px;overflow:hidden;">
				<iframe id="detail" name="contents" src="" width="100%" height="100%" frameborder="0" style="overflow:hidden"></iframe>
			</div>
		</div>
	 <script type="text/javascript">
		function pickdate(){
			var month = $("#month").val();
			//alert(month);
			jQuery.ajax({
				url:'billlist',
				type:'post',
				data:{rentbeingdate:month},
				dataType: "json",
				error:function(){},
				success:function(data){
					 $("#billlist").empty();
					 var str = "";
					 $.each(data, function(commentIndex, comment){
                        //alert(comment.country);
                        str  += "<tr><td>"+comment.country+"</td><td>"+comment.number+"</td><td>"+comment.dayrent+"</td><td>"+(comment.number*comment.dayrent)+"</td></tr>"
                   });
					 $("#billlist").append("<tr><td>国家</td><td>数量</td><td>单价</td><td>金额</td></tr>");
					 $("#billlist").append(str);
					 $("#billlist").append("<tfoot><tr><td>总计</td><td><span id='allnum' style='font-size: 16px;'></span></td><td></td><td><span style='font-size: 16px;' id='allmoney'></span></td></tr></tfoot>");
					 queryAll();
				}
			});
		}
		
		function queryAll(){
			var rowcount = document.getElementById("billlist").rows.length
			var allnum = 0;
			var allmoney = 0;
			for(i=1;i<rowcount-1;i++){
			var num = document.getElementById("billlist").rows[i].cells[1].innerHTML;
			var money = document.getElementById("billlist").rows[i].cells[3].innerHTML;
			//alert(money);
			//alert(num+":"+money);
			allnum += parseInt(num);
			allmoney += parseInt(money);
			}
			//alert("allnum="+allnum+":allmoney="+allmoney);
			$("#allnum").html(allnum);
			$("#allmoney").html(allmoney);
			$("#allbillmoney").html(allmoney);
		}
		
		$(function(){
			queryAll();
		})
		
	</script>
</body>
 
</html>