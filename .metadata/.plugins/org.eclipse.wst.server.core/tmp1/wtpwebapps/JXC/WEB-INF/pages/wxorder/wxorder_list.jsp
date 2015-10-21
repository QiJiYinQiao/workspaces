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
		function submitForm(){
		$('#ff').submit();
		}
		function clearForm(){
		$('#ff').form('clear');
		}
		 $(function(){
			 var t = new Date();
			 $('#dg').datagrid({
				url:'queryWxOrderByCondition',
				queryParams:{
					t:t,
				},
				pageSize: 20,
			 });
		}); 
</script>
	<div data-options="region:'center',title:'订单管理'">
		<div style="background-color:#F8F8FF;align:center;width: 100%;height: 110px;overflow: hidden;">
					<form  method="post" style="margin-left: 1%;text-align: left;width: 100%;">
						<table style="width: 100%;">
							<tr>
							<td width="10%" > 租用人姓名</td><td width="15%"><input class="searchinput" name="uname" id="uname" value=""/></td>
							<td width="10%">手机号</td><td width="15%"><input class="searchinput" name="uphone" id="uphone" value=""/></td>
							<td width="10%">预定方式</td><td width="15%">
								<select id="schedule_order" style="border: 1px solid #95b8e7;border-radius: 5px;height: 24px;width: 140px;" name="schedule_order" >
									<option value="" selected="selected">全部</option>
									<c:if test="${orderway!=null }">
										<c:forEach items="${orderway }" var="orderway">
											<option value="${orderway.orderwaycode }">${orderway.orderwayname }</option>
										</c:forEach>
									</c:if>
								</select>
							</td>
							<!-- <td width="10%">取货方式</td><td width="15%"><input class="searchinput"  name="schedule_order" value=""/></td> -->
							</tr>
							<tr>
							<td>租用日期</td><td><input  name="rentdate" id="rentdate" value="" style="border: 1px solid #95b8e7;border-radius: 5px;" class="Wdate" type="text" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy-MM-dd'})"/></td>
							<td>返还日期</td><td><input name="enddate" id="enddate" value="" style="border: 1px solid #95b8e7;border-radius: 5px;" class="Wdate" type="text" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy-MM-dd'})"/> </td>
							<td> 目的地</td>
							<td>
								<select id="destionations"  style="border: 1px solid #95b8e7;border-radius: 5px;height: 24px;width: 140px;" name="destination" >
									<option value="0000" selected="selected">全部</option>
									<c:if test="${destionation!=null }">
										<c:forEach items="${destionation }" var="destionation">
											<option value="${destionation.countrycode }">${destionation.countryname }</option>
										</c:forEach>
									</c:if>
								</select>
							</td>
							
							<!--租用人姓名 手机号 租用日期 返还日期 目的地  预定方式  取货方式（自提，快递000） 状态  制单人； 默认显示有效订单-->
							</tr>
							<tr>
								<td width="10%">状态</td><td width="15%">
									<select name="orderstate" id="orderstate" style="border: 1px solid #95b8e7;border-radius: 5px;height: 24px;width: 140px;">
										<option value="0000">全部</option>
										<option value="0001">预定</option>
										<option value="0002">已确认</option>
										<option value="0003">已取消</option>
									</select>
								</td>
								<td width="10%">制单人</td><td width="15%"><input id="createuser" class="searchinput"  name="createuser" value=""/></td>
							</tr>
						</table>
					</form>
					<div align="right" style="margin-bottom: 5px;">
						<a onclick="cancelOrder();" class="easyui-linkbutton" style="margin-right: 20px;width: 80px;height: 25px;">取消订单</a>
						<a onclick="confirmOrder();" class="easyui-linkbutton" style="margin-right: 20px;width: 80px;height: 25px;">确认订单</a>
						<a onclick="checkdetail();" class="easyui-linkbutton" style="margin-right: 20px;width: 60px;height: 25px;">详情</a>
						<a onclick="querybyc();" class="easyui-linkbutton" style="margin-right: 5px;width: 60px;height: 25px;">查询</a>
					</div>
				</div>
		<!-- 表单开始 -->
				<table id="dg" style="overflow: auto;height: 82%;" class="easyui-datagrid" 
				data-options="rownumbers:true,pagination:true,singleSelect:true,method:'post',onDblClickRow:doubleclick, remoteSort:false,multiSort:false, showFooter: true">
				<thead>
				<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'uname',width:110,align:'center'">姓名</th>
				<th data-options="field:'uphone',width:120,align:'center'">手机号</th>
				<th data-options="field:'schedule_order',width:80,align:'center'">预订方式</th>
				<th data-options="field:'destination',width:60,align:'center'">目的地</th>
				<th data-options="field:'rentdate',width:120,align:'center',sortable:true">租用日期</th>
				<th data-options="field:'enddate',width:120,align:'center',sortable:true">返还日期</th>
				<th data-options="field:'ordernum',width:60,align:'center'">个数</th>
				<th data-options="field:'rent_cost',width:80,align:'center'">租赁费用</th>
				<th data-options="field:'deposit',width:60,align:'center'">押金</th>
				<th data-options="field:'shouldgetcost',width:80,align:'center'">应收总金额</th>
				<th data-options="field:'hadgetcost',width:80,align:'center'">已收金额</th>
				<th data-options="field:'pregetcost',width:80,align:'center'">待收金额</th>
				<th data-options="field:'orderstate',width:60,align:'center'">状态</th>
				<th data-options="field:'discountway',width:60,align:'center'">优惠方式</th>
				<!-- 预定  确认 -->
				<th data-options="field:'modifytime',width:170,align:'center',sortable:true">日期</th>
				<th data-options="field:'modifyuser',width:80,align:'center'">制单员</th>
				<th data-options="field:'takeorderaddress',width:280,align:'center'">收货地址</th>
				<th data-options="field:'remark',width:280,align:'center'">备注</th>
				<th data-options="field:'uidcard',width:100,align:'center'">身份证号</th>
				<th data-options="field:'upassport',width:100,align:'center'">护照号</th>
				<th data-options="field:'_operate',width:80,align:'center',formatter:formatOper">操作</th>  
				</tr>
				</thead>
				</table>
		<!-- 表单结束 -->
	</div>
	<!-- 列表弹出框开始 -->
		 <div id="dlg"  class="easyui-dialog" modal="true" title="预订单详情" closed="true" style="width:1150px;height:450px;overflow:hidden;">
			<iframe id="woorderdetail" name="contents" src="" width="100%" height="100%" frameborder="0" style="overflow:hidden"></iframe>
		</div>
		
		<script type="text/javascript">
			function querybyc(){
				//alert("querybyc");
				var uname = $("#uname").val();
				var uphone = $("#uphone").val();
				var schedule_order = $("#schedule_order").val();
				var rentdate = $("#rentdate").val();
				var enddate =  $("#enddate").val();
				var destination  =  $("#destionations").val();
				var orderstate  =  $("#orderstate").val();
				var createuser  =  $("#createuser").val();
				 $('#dg').datagrid({
					url:"queryWxOrderByCondition",
					queryParams:{
						uname:uname,uphone:uphone,schedule_order:schedule_order,rentdate:rentdate,enddate:enddate,destination:destination,
						orderstate:orderstate,createuser:createuser,
					},
					pageSize: 20,
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
			
			function cancelOrder(){
				var row = $("#dg").datagrid("getSelected");
				if(row==null){
					alert("请选择一条数据！")
					return false;
				}
				var id = row.id;
				//alert(id);
				jQuery.ajax({
					url:"cancelOrder",
					data:{id:id},
					type:"post",
					error:function(){},
					success:function(data){
						if(data==1){
							alert("取消订单成功！")
							$("#dg").datagrid("reload");
						}
					}
				});
			}
			
			function confirmOrder(){
				var row = $("#dg").datagrid("getSelected");
				if(row==null){
					alert("请选择一条数据！")
					return false;
				}
				var id = row.id;
				//alert(id);
				jQuery.ajax({
					url:"confirmOrder",
					data:{id:id},
					type:"post",
					error:function(){},
					success:function(data){
						if(data==1){
							alert("确认订单成功！")
							$("#dg").datagrid("reload");
						}
					}
				});
			}
			
			function formatOper(val,row,index){  
			    return '<a href="#" onclick="reloadOrder('+index+')">订单转录</a>';  
			} 
			
		    function reloadOrder(index){  
		        $('#dg').datagrid('selectRow',index);// 关键在这里  
		        var row = $('#dg').datagrid('getSelected');  
		        if (row){  
		           /*  $('#dlg').dialog('open').dialog('setTitle','修改学生信息');  */ 
		           /*  $('#fm').form('load',row);   */
		            /* url = '${ctx}updateStudent.do?id='+row.id;  */ 
		            //alert(row.id);
		            window.open("../order/otherCounterAdd?wxorderid="+row.id,"","width=1200,height=600,left=200,top=100");
		        }  
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
			  document.getElementById("woorderdetail").src="woorderdetail?id="+field.id;
			  $('#dlg').dialog('open');
		}
		
		function doubleclick(index,field){
			document.getElementById("woorderdetail").src="woorderdetail?id="+field.id;
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