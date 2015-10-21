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
			 $('#roleTable').datagrid({
				url:'findAllRole?t='+new Date(),
				queryParams:{
					state:5,
				},
				pageSize: 20,
			 });
		}); 
</script>
	<div data-options="region:'center',title:'网站订单管理'">
		<div style="background-color:#F8F8FF;align:center;width: 100%;height: 30px;overflow: hidden;">
					
					<div  align="left" style="margin-bottom: 5px;height: 30px;" >
						<img src="../media/image/icons/edit_add.png">
						<img src="../media/image/icons/edit_remove.png">
						<img src="../media/image/icons/pencil2.png">
					</div>
				</div>
		<!-- 表单开始 -->
				<table id="roleTable" style="overflow: auto;height: 89%;" class="easyui-datagrid" 
				data-options="rownumbers:true,pagination:true,onDblClickRow:doubleclick,singleSelect:true,method:'post',showFooter: true,remoteSort:false,multiSort:false">
				<thead>
				<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'name',width:120,align:'center'">角色名称</th>
				<th data-options="field:'code',width:75,align:'center'">编码</th>
				<th data-options="field:'description',width:110,align:'center'">角色描述</th>
				
				</tr>
				</thead>
				</table>
		<!-- 表单结束 -->
	</div>
		 <div id="dlg"  class="easyui-dialog" modal="true" closed="true" title="网站订单详情"  style="width:850px;height:500px;overflow:hidden;">
			<iframe id="jcbdetail" name="contents" src="" width="100%" height="100%" frameborder="0" style="overflow:hidden"></iframe>
		</div>
		
		<div id="send" class="easyui-dialog" class="easyui-dialog" modal="true" closed="true" title="发送短信"  style="width:570px;height:370px;overflow:hidden;">
			<iframe id="message" name="messages" src="" width="100%" height="100%" frameborder="0" style="overflow:hidden"></iframe>
		</div>
		<script type="text/javascript">
			 $(function(){
				 var pager = $('#roleTable').datagrid().datagrid('getPager'); // get the pager of datagrid
				 $(pager).pagination({  
					 	beforePageText: '第',//页数文本框前显示的汉字  
				        afterPageText: '页    共 {pages} 页',  
				        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
				        pageList:[20,50,100,200],
				    });  
			 });
		
			function queryOrder(){
				var jbc = $("#jbc").val();
				var name = $("#name").val();
				var phone = $("#phone").val();
				var goodname = $("#goodname").val();
				var cfdate = $("#cfdate").val();
				var cfdate_end = $("#cfdate_end").val();
				var returndate = $("#returndate").val();
				var returndate_end = $("#returndate_end").val();
				var payment = $("#payment").val();
				var state = $("#state").val();
				var way = $("#way").val();
				var createtime = $("#createtime").val();
				var createtime_end = $("#createtime_end").val();
				var isphoneconfirm = $("#isphoneconfirm").val();
				var cfcity = $("#cfcity").val();
				
				$("#jcbOrderTable").datagrid({
					url:"queryByCondition",
					queryParams:{
						jbc:jbc,name:name,phone:phone,goodname:goodname,cfdate:cfdate,cfdate_end:cfdate_end,returndate:returndate,returndate_end:returndate_end,
						payment:payment,state:state,way:way,createtime:createtime,createtime_end:createtime_end,isphoneconfirm:isphoneconfirm,cfcity:cfcity,
					},
					pageSize: 20,
					pageList:[20,50,100,200],
				})
				 var pager = $('#jcbOrderTable').datagrid().datagrid('getPager'); // get the pager of datagrid
				 $(pager).pagination({  
					 	beforePageText: '第',//页数文本框前显示的汉字  
				        afterPageText: '页    共 {pages} 页',  
				        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
				        pageList:[20,50,100,200],
				    });  
			}
		</script>
		<script type="text/javascript">
			/* $(function(){
				$('#dlg').dialog('close');
				});
			function submits(){
				//$("#queryBy").submit();
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
				//alert("queryByCondition?id="+id+"&rent_p_name="+rent_p_name+"&rent_p_tel="+rent_p_tel+"&equipment_no="+equipment_no+"&rent_begindate="+rent_begindate+"&rent_expectdate="+rent_expectdate+"&rent_enddate="+rent_enddate+"&d_country="+d_country+"&payment="+payment);
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
			} */
		</script>
	<!-- 列表弹出框结束 -->
	 <script type="text/javascript">
		 
		
		
		function sendmassage(){
			var field = $("#jcbOrderTable").datagrid("getSelected");
			  if(field==null){
				  alert("请先选择一条订单 ");
			  }
			  var name = field.name;
			  var phone = field.phone;
			  var cfdate = field.cfdate;
			  var qhaddress = field.cfcity+field.qhaddress;
			  var jcbNo = field.jbc.substring(10, 17);
			  var content = name+"，您好 。您在龙在天下预订的WIFI订单已确认，请您在登机前到"+qhaddress+"处领取，并随身带好您的JCB卡，以备客服做好相关登记。祝您旅途愉快！";
			  document.getElementById("message").src="jcbordersend?phone="+field.phone+"&content="+content;
			 /*  frames['messages'].document.getElementById("phone").value=field.phone;
			  frames['messages'].document.getElementById("content").value=field.content; */
			  $('#send').dialog('open');
		}
		
		function phoneconfirm(){
			  var field = $("#jcbOrderTable").datagrid("getSelected");
			  if(field==null){
				  alert("请先选择一条数据");
				  return false;
			  }
			  //document.getElementById("jcbdetail").src="jcborderdetail?name="+field.name+"&phone="+field.phone;
			  document.getElementById("jcbdetail").src="jcborderdeconfirm?id="+field.orderid;
			  $('#dlg').dialog('open');
		} 
		
		function checkdetail(){
			 var field = $("#jcbOrderTable").datagrid("getSelected");
			  if(field==null){
				  alert("请先选择一条数据");
				  return false;
			  }
			  //document.getElementById("jcbdetail").src="jcborderdetail?name="+field.name+"&phone="+field.phone;
			  document.getElementById("jcbdetail").src="jcborderdetail?id="+field.orderid;
			  $('#dlg').dialog('open');
		}
		
		function doubleclick(index,field){
			//document.getElementById("jcbdetail").src="jcborderdetail?name="+field.name+"&phone="+field.phone;
			document.getElementById("jcbdetail").src="jcborderdetail?id="+field.orderid;
			$('#dlg').dialog('open');
		}
		
		
		function exportform(){
			var jbc = $("#jbc").val();
			var name = $("#name").val();
			var phone = $("#phone").val();
			var goodname = $("#goodname").val();
			var cfdate = $("#cfdate").val();
			var cfdate_end = $("#cfdate_end").val();
			var returndate = $("#returndate").val();
			var returndate_end = $("#returndate_end").val();
			var payment = $("#payment").val();
			var state = $("#state").val();
			var way = $("#way").val();
			var createtime = $("#createtime").val();
			var createtime_end = $("#createtime_end").val();
			var isphoneconfirm = $("#isphoneconfirm").val();
			var cfcity = $("#cfcity").val();
			
			jQuery.ajax({
				url:'exportform',
				type:'post',
				data:{jbc:jbc,name:name,phone:phone,goodname:goodname,cfdate:cfdate,cfdate_end:cfdate_end,returndate:returndate,returndate_end:returndate_end,payment:payment,state:state,way:way,createtime:createtime,createtime_end:createtime_end,isphoneconfirm:isphoneconfirm,cfcity:cfcity,},
				async:false,
				error:function(){},
				success:function(){
				}
			});
		}
		
	</script>
</body>
 
</html>