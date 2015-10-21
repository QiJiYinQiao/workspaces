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
	<title>柜台详情</title>
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

		function submitForm(){
		$('#ff').submit();
		}
		function clearForm(){
		$('#ff').form('clear');
		}
		$(function(){
			$('#dg').datagrid('hideColumn','remark');			
			$('#dg').datagrid('hideColumn','rent_p_idnumber');	
			$('#dg').datagrid('hideColumn','rent_p_passportno');	
		});
		 $(function(){
			 var types = $("#types").val();
			 $('#dg').datagrid({
				url:'getlist?types='+types+'&t='+new Date(),
			 });
		}); 
</script>
	<div data-options="region:'center',title:'柜台详情'">
		<input id="types" value="${types }" style="display:none;">
		<div style="background-color:#F8F8FF;align:center;width: 100%;height: 60px;overflow: hidden;">
					<form id="managephoto" action="managephoto" method="post" style="float:right;">
						<table>
							<tr>
								<td>日期:</td><td><input id="begindate" name="begindate" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;width: 100px;" class="Wdate" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy/MM/dd',maxDate:'#F{$dp.$D(\'enddate\')}'})" type="text"></input></td>
								<td>--</td><td><input id="enddate" name="enddate" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;width: 100px;" class="Wdate" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy/MM/dd',minDate:'#F{$dp.$D(\'begindate\')}'})" type="text"></input></td>
								<td><a onclick="managephoto();" class="easyui-linkbutton" iconCls="icon-search" style="font-size:16px;float: right;" >查询</a></td>
							</tr>
						</table>
					</form>
			</div>
		<!-- 表单开始 -->
				<table id="dg" style="overflow: auto;height: 90%;" class="easyui-datagrid" 
				data-options="rownumbers:true,pagination:true,singleSelect:true,method:'get',onDblClickRow:doubleclick, remoteSort:false,multiSort:false, showFooter: true">
				<thead>
				<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'order_num',width:150,align:'center'">柜台号</th>
				<th data-options="field:'rent_p_tel',width:110,align:'center'">姓名</th>
				<th data-options="field:'equipment_no',width:120,align:'center'">手机号</th>
				<th data-options="field:'equipment_no',width:120,align:'center'">目的地</th>
				<th data-options="field:'rent_begindate',width:170,align:'center',sortable:true">租用日期</th>
				<th data-options="field:'rent_expectdate',width:100,align:'center',sortable:true">返还日期</th>
				<th data-options="field:'rent_enddate',width:170,align:'center',sortable:true">出行天数</th>
				<th data-options="field:'cost_rent_3g',width:80,align:'center'">个数</th>
				<th data-options="field:'upfront_sum',width:80,align:'center'">出发地</th>
				<th data-options="field:'cost_sum',width:80,align:'center'">取货地</th>
				<th data-options="field:'cost_sum',width:80,align:'center'">租赁费用</th>
				<th data-options="field:'cost_sum',width:80,align:'center'">押金</th>
				<th data-options="field:'cost_return_discount',width:112,align:'center'">预收总金额</th>
				<th data-options="field:'cost_return_discount',width:112,align:'center'">已收金额</th>
				<th data-options="field:'cost_return_discount',width:112,align:'center'">待收金额</th>
				<th data-options="field:'cost_return_discount',width:112,align:'center'">国籍</th>
				<th data-options="field:'modify_time',width:170,align:'center',sortable:true">日期</th>
				<th data-options="field:'modify_user',width:80,align:'center'">操作员</th>
				<th data-options="field:'remark',align:'center'">收货地址</th>
				<th data-options="field:'remark',align:'center'">备注</th>
				<th data-options="field:'rent_p_idnumber',align:'center'">身份证号</th>
				<th data-options="field:'rent_p_passportno',align:'center'">护照号</th>
				</tr>
				</thead>
				</table>
		<!-- 表单结束 -->
	</div>
	<!-- 列表弹出框开始 -->
		 <div id="dlg"  class="easyui-dialog" modal="true" title="订单详情"  style="width:880px;height:450px;overflow:hidden;">
			<iframe id="detail" name="contents" src="" width="100%" height="100%" frameborder="0" style="overflow:hidden"></iframe>
		</div>
		
		<script type="text/javascript">
			$(function(){
				$('#dlg').dialog('close');
				});
			function submits(){
				var order_num = $('#order_num').val()+"";
				var rent_p_name = $('#rent_p_names').val()+"";
				var rent_p_tel = $('#rent_p_tels').val()+"";
				var equipment_no = $('#equipment_nos').val()+"";
				var rent_begindate = $('#rent_begindates').val()+"";
				var rent_expectdate = $('#rent_expectdates').val()+"";
				var rent_enddate = $('#rent_enddates').val()+"";
				var d_country = $('#d_countrys').val()+"";
				var payment = $('#payments').val()+"";
				var order_state = $("#order_state").val()+"";
				 $('#dg').datagrid({
					url:"queryByCondition?order_num="+order_num+"&rent_p_name="+rent_p_name+"&rent_p_tel="+rent_p_tel+"&equipment_no="+equipment_no+"&rent_begindate="+rent_begindate+"&rent_expectdate="+rent_expectdate+"&rent_enddate="+rent_enddate+"&d_country="+d_country+"&payment="+payment+"&order_state="+order_state
					});  
				 var pager = $('#dg').datagrid().datagrid('getPager'); // get the pager of datagrid
				 $(pager).pagination({  
					 	beforePageText: '第',//页数文本框前显示的汉字  
				        afterPageText: '页    共 {pages} 页',  
				        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
				        pageList:[10,20,50,100,200],
				       
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
		        pageList:[10,20,50,100,200],
		        /* buttons:[{
					iconCls:'icon-search',
					handler:function(){
						 var field = $("#dg").datagrid("getSelected");
						  if(field==null){
							  alert("请先选择一条数据");
						  }
						  document.getElementById("detail").src="detail?no="+field.no;
					      $('#checkdetail').dialog('open');
					}
				},{
					iconCls:'icon-add',
					handler:function(){
						$("#adds").dialog('open');
					}
				},{
					iconCls:'icon-edit',
					handler:function(){
						var field = $("#dg").datagrid("getSelected");
						if(field==null){
							alert("请先选择一条数据");
						}
						document.getElementById("edit").src="edit?no="+field.no;
						$("#edits").dialog("open");
					}
				}] */
		    });  
		
		});
		
		function checkdetail(){
			  var field = $("#dg").datagrid("getSelected");
			  if(field==null){
				  alert("请先选择一条数据");
			  }
			  document.getElementById("detail").src="detail?order_num="+field.order_num;
			  $('#dlg').dialog('open');
			  /* $('#dlg').dialog('open');
			  var field = $("#dg").datagrid("getSelected");   
			  //alert(field);
			  if(field==null){
				  alert("请先选择一条数据");
			  }
			  //从后台获取数据方法
			  $("#order_num").val(field.order_num);
			  $("#rent_p_name").val(field.rent_p_name);
			  $("#rent_p_tel").val(field.rent_p_tel);
			  $("#equipment_no").val(field.equipment_no);
			  
			  $("#rent_begindate").val(field.rent_begindate);
			  $("#rent_enddate").val(field.rent_enddate);
			  $("#d_country").val(field.d_country);
			  $("#cost_rent_3g").val(field.cost_rent_3g);
			  
			  $("#cost_return").val(field.cost_return);
			  $("#upfront_sum").val(field.upfront_sum);
			  $("#rent_p_idnumber").val(field.rent_p_idnumber);
			  $("#rent_p_passportno").val(field.rent_p_passportno);
			  
			  $("#rent_p_nationality").val(field.rent_p_nationality);
			  $("#s_country").val(field.s_country);
			  $("#create_time").val(field.create_time);
			  $("#create_user").val(field.create_user);
			  $("#remark").val(field.remark);
			  $("#screens").fadeIn("1500"); */
		}
		
		function doubleclick(index,field){
			 // parent.menus.window.document.getElementBmenu_shadow
			  var r = $("#dg").datagrid("getRows",index);
			  document.getElementById("detail").src="detail?order_num="+field.order_num;
			  $('#dlg').dialog('open');
			  //alert(field.equipment_no);
			  //alert(r[0].rent_p_tel);
			  //searchs();
			  
			  //从后台获取数据方法
			/*   $("#order_num").val(field.order_num);
			  $("#rent_p_name").val(field.rent_p_name);
			  $("#rent_p_tel").val(field.rent_p_tel);
			  $("#equipment_no").val(field.equipment_no);
			  
			  $("#rent_begindate").val(field.rent_begindate);
			  $("#rent_enddate").val(field.rent_enddate);
			  $("#d_country").val(field.d_country);
			  $("#cost_rent_3g").val(field.cost_rent_3g);
			  
			  $("#cost_return").val(field.cost_return);
			  $("#upfront_sum").val(field.upfront_sum);
			  $("#rent_p_idnumber").val(field.rent_p_idnumber);
			  $("#rent_p_passportno").val(field.rent_p_passportno);
			  
			  $("#rent_p_nationality").val(field.rent_p_nationality);
			  $("#s_country").val(field.s_country);
			  $("#create_time").val(field.create_time);
			  $("#create_user").val(field.create_user);
			  $("#remark").val(field.remark); */
			  //alert(document.getElementById("detail").src);
			 // $("#screens").fadeIn("1500");
			 
			 /*  alert(r);
			  alert(r.rent_p_tel); 
			  window.location = "search?equipment_no="+equipmentno+"&rent_p_tel="+rentptel; */
		}
		
		function searchs(){
			  //alert("ok");
			  var r = $("#dg").datagrid('getSelected');	// 得到选择行
			  //alert(r.equipment_no);
			  //alert(r.rent_p_tel);
			  var equipmentno = r.equipment_no;
			  var rentptel = r.rent_p_tel;
			 /*  alert(r);
			  alert(r.rent_p_tel); */
			  window.location = "search?equipment_no="+equipmentno+"&rent_p_tel="+rentptel;
			/* for (var i = 0; rows && i < rows.length; i++) {
			     var row = rows[i];
			     var index = $("#dbgrid").datagrid("getRowIndex", row);    // 获取该行的索引
				 alert(index);
			 }   */
		}
	</script>
</body>
 
</html>