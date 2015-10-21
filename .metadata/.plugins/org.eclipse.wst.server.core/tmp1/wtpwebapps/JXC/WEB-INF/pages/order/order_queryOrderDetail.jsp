<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragrma","no-cache");
response.setDateHeader("Expires",0);
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta HTTP-EQUIV="pragma" CONTENT="no-cache">
	<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
	<meta HTTP-EQUIV="expires" CONTENT="0">
	<title>登陆首页</title>
	<link rel="stylesheet" type="text/css" href="../media/css/easyui.css">
	<link rel="stylesheet" type="text/css" href="../media/css/icon.css">
	<link rel="stylesheet" type="text/css" href="../media/css/demo.css">
	<script type="text/javascript" src="../media/js/WdatePicker.js"></script>
	<script type="text/javascript" src="../media/js/jquery.min.js"></script>
	<script type="text/javascript" src="../media/js/jquery.easyui.min.js"></script>
	<style type="text/css">
		.indiv input{
			border: 1px solid #95b8e7;
			border-radius: 5px;
			height: 20px;
			/* background-color:#B0C4DE; */
			font-size:16px;
			background-color:#F0F8FF;
			
		}
		td span{
			font-size: 16px;
		}
		#screens td{
			color: black;
			font-size: 16px;
		}
		#screens td input{
			color: black;
		}
		.datagrid-view{
			background-color: #F8F8FF;
			font-size: 16px;
		}
		.searchinput{
			border: 1px solid #95b8e7;border-radius: 5px;height:20px;
		}
	
		</style>
</head>
<body class="easyui-layout">
<script >
		 $(function(){
			 var t = new Date();
			 $('#dg').datagrid({
				url:'queryOrderDetail',
				queryParams:{t:t},
				pageSize:20,
			 });
		}); 
</script>
	<div data-options="region:'center',title:'订单管理'">
		<div style="background-color:#F8F8FF;align:center;width: 100%;height: 110px;overflow: hidden;">
					<form id="queryBy" action="queryByCondition" method="post" style="margin-left: 1%;text-align: left;width: 100%;">
						<table style="width: 100%;">
							<tr>
							<td>租用日期</td><td colspan="3"><input id="rentbegindate_from" name="rentbegindate_from" value="" style="border: 1px solid #95b8e7;border-radius: 5px;" class="Wdate" type="text" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'rentbegindate_to\',{d:-2})}'})"/>
							--<input id="rentbegindate_to" name="rentbegindate_to" value="" style="border: 1px solid #95b8e7;border-radius: 5px;" class="Wdate" type="text" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'rentbegindate_from\',{d:-2})}'})"/>
							</td>
							<td>超期未还</td><td><input id="rent_expectdate" name="rent_expectdate" value="" style="border: 1px solid #95b8e7;border-radius: 5px;" class="Wdate" type="text" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy-MM-dd'})"/></td>
							
							<td> 目的地</td>
							<td>
								<select id="dcountry" style="border: 1px solid #95b8e7;border-radius: 5px;height: 24px;width: 140px;" name="dcountry">
									<option value="" selected="selected">全部</option>
									<c:if test="${country!=null }">
										<c:forEach items="${country }" var="countrys">
											<option value="${countrys.ITEM_VALUE }">${countrys.ITEM_TEXT }</option>
										</c:forEach>
									</c:if>
								</select>
							</td>
							</tr>
							<tr>
							<td width="10%">设备类型</td>
							<td>
								<select id="equipmenttype" style="border: 1px solid #95b8e7;border-radius: 5px;height: 24px;width: 140px;" name="equipmenttype">
									<option value="" selected="selected">全部</option>
									<option value="0001">内部设备</option>
									<option value="0002">外部设备</option>
								</select>
							</td>
							<td width="10%">备注</td><td width="15%"><input class="searchinput" name="remark" id="remark" value=""/></td>
							<!-- <td width="10%">七天内订单</td>
							<td><select id="diffday" style="border: 1px solid #95b8e7;border-radius: 5px;height: 24px;width: 140px;" name="diffday">
								<option value="0000" selected="selected">否</option>
								<option value="0001">是</option>
							</select></td> -->
							</tr>
						</table>
					</form>
					<div align="right" style="margin-bottom: 5px;">
						<a onclick="submits();" class="easyui-linkbutton" style="margin-right: 5px;width: 60px;height: 25px;">查询</a>
						<a onclick="checkdetail();" class="easyui-linkbutton" style="margin-right: 20px;width: 60px;height: 25px;">详情</a>
					</div>
				</div>
		<!-- 表单开始 -->
				<table id="dg" style="overflow: auto;height: 82%;" class="easyui-datagrid" 
				data-options="rownumbers:true,pagination:true,singleSelect:true,method:'post',onDblClickRow:doubleclick, remoteSort:false,multiSort:false, showFooter: true">
				<thead>
				<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'ordernum',width:150,align:'center'">订单编号</th>
				<th data-options="field:'rentpname',width:80,align:'center'">旅客姓名</th>
				<th data-options="field:'rentptel',width:110,align:'center'">旅客电话</th>
				<th data-options="field:'rentbegindate',width:120,align:'center'">出行日期</th>
				<th data-options="field:'rentexpectdate',width:120,align:'center',sortable:true">预计返回日期</th>
				<th data-options="field:'rentenddate',width:120,align:'center',sortable:true">实际返回日期</th>
				<th data-options="field:'costsum',width:80,align:'center',sortable:true">实收金额</th>
				<th data-options="field:'days',width:80,align:'center',sortable:true">出行天数</th>
				<th data-options="field:'avgday',width:40,align:'center'">日均</th>
				<th data-options="field:'remark',width:300,align:'center'">备注</th>
				<th data-options="field:'state',width:80,align:'center',styler: function(value,row,index){
								if (value=='已完成'){
									return 'background-color:#F8F8FF;color:green;';
								}else{
									return 'color:red';
								}
							}">订单状态</th>
				<th data-options="field:'equipmenttype',width:80,align:'center'">设备类型</th>
				<th data-options="field:'modifyuser',width:80,align:'center'">操作员</th>
				<th data-options="field:'modifytime',width:80,align:'center'">日期</th>
				</tr>
				</thead>
				</table>
		<!-- 表单结束 -->
	</div>
		 <div id="dlg"  class="easyui-dialog" modal="true" title="订单详情" closed="true" style="width:880px;height:450px;overflow:hidden;">
			<iframe id="detail" name="contents" src="" width="100%" height="100%" frameborder="0" style="overflow:hidden"></iframe>
		</div>
		
		<script type="text/javascript">
				function submits(){
					var rentbegindate_from = $("#rentbegindate_from").val();
					var rentbegindate_to = $("#rentbegindate_to").val();
					var rent_expectdate = $("#rent_expectdate").val();
					var dcountry = $("#dcountry").val();
					var equipmenttype = $("#equipmenttype").val();
					var remark = $("#remark").val();
					var diffday = $("#diffday").val();
					
					 $('#dg').datagrid({
						url:"queryOrderDetail",
						queryParams:{
							rentbegindate_from:rentbegindate_from,dcountry:dcountry,equipmenttype:equipmenttype,
							remark:remark,diffday:diffday,rentbegindate_to:rentbegindate_to,rent_expectdate:rent_expectdate,
						},
						pageList:[20,50,100,200],
					 });  
					var pager = $('#dg').datagrid().datagrid('getPager'); // get the pager of datagrid
					 $(pager).pagination({  
						 	beforePageText: '第',//页数文本框前显示的汉字  
					        afterPageText: '页    共 {pages} 页',  
					        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
					        pageList:[20,50,100,200],
					    }); 
			}
		</script>
	<!-- 列表弹出框结束 -->
	 <script type="text/javascript">
	  $(function(){
		var pager = $('#dg').datagrid().datagrid('getPager'); // get the pager of datagrid
		 $(pager).pagination({  
			 	beforePageText: '第',//页数文本框前显示的汉字  
		        afterPageText: '页    共 {pages} 页',  
		        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
		        pageList:[20,50,100,200],
		    });  
		
		}); 
		
		function checkdetail(){
			  var field = $("#dg").datagrid("getSelected");
			  if(field==null){
				  alert("请先选择一条数据");
			  }
			  document.getElementById("detail").src="detail?order_num="+field.ordernum;
			  $('#dlg').dialog('open');
		}
		
		function doubleclick(index,field){
			  var r = $("#dg").datagrid("getRows",index);
			  document.getElementById("detail").src="detail?order_num="+field.ordernum;
			  $('#dlg').dialog('open');
		}
		
		function searchs(){
			  var r = $("#dg").datagrid('getSelected');	// 得到选择行
			  var equipmentno = r.equipment_no;
			  var rentptel = r.rent_p_tel;
			  window.location = "search?equipment_no="+equipmentno+"&rent_p_tel="+rentptel;
		}
	</script>
</body>
 
</html>