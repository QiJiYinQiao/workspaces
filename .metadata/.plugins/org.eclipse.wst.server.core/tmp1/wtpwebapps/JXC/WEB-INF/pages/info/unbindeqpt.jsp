<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>设备列表</title>
	<link rel="stylesheet" type="text/css" href="../media/css/easyui.css">
	<link rel="stylesheet" type="text/css" href="../media/css/icon.css">
	<link rel="stylesheet" type="text/css" href="../media/css/demo.css">
	<script type="text/javascript" src="../media/js/WdatePicker.js"></script>
	<script type="text/javascript" src="../media/js/jquery.min.js"></script>
	<script type="text/javascript" src="../media/js/jquery.easyui.min.js"></script>
	<style type="text/css">
			.datagrid-view{
			background-color: #F8F8FF;
			font-size: 16px;
			}
	</style>
</head>
<body class="easyui-layout">
				<script type="text/javascript">
					$(function(){
						$("#dg").datagrid({
							url:"queryUnbindEqpt",
						});
					});
				</script>
				<div style="height: 50px;background-color: rgb(248,248,255);text-align: right;">
					<form id="conditionsOfEquipment">
					<table style="padding-top: 1px;">
						<tr>
							<td width="10%">设备号</td>　
							<td width="15%"><input class="easyui-textbox" type="text" id="no" name="no" value="${no }"></td>
							<td width="10%">柜　台</td>　
							<td width="15%"><select id="counterid" name="counterid"  style="border: 1px solid #95b8e7;border-radius: 5px;height: 24px;width:100%;" >
								<option value=""  selected="selected">全部</option>
								<c:if test="${counterlist!=null }">
									<c:forEach items="${counterlist }" var="counterlists">
										<option value="${counterlists.counter_code }">${counterlists.name }</option>
									</c:forEach>
								</c:if>
							</select>
							</td>
							<td width="10%">是否绑定</td>
							<td width="15%">
								<select id="sim_id" style="border: 1px solid #95b8e7;border-radius: 5px;height: 24px;width:100%;" class="searchstyle" name="sim_id">
									<option value="">全部</option>
									<option value="0001">已绑定</option>
									<option value="0002">未绑定</option>
								</select>
							</td>　
							<td width="25%">
								<a onclick="searchs();" class="easyui-linkbutton" iconCls="icon-search" style="font-size:16px;height: 25px;" >查询</a>
								<a onclick="sub();" class="easyui-linkbutton"  style="font-size:16px;width:60px; ">确认</a>
							</td>
						</tr>
					</table>
					<!-- <button onclick="searchs()" style="width:6%;height:25px;">查询</button>
					<button onclick="sub()" style="width:6%;height:25px;">确认</button> -->
					</form>
					</div>
		<!-- 表单开始 -->
				<table id="dg" style="background-color:#F8F8FF;height: 90%;" class="easyui-datagrid"  
				data-options="rownumbers:true,pagination:true,singleSelect:true,method:'post',onDblClickRow:doubleclick">
				<thead>
				<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'no',width:120,align:'center'">设备编号</th>
				<th data-options="field:'counterid',width:120,align:'center'">柜台名称</th>
				<!-- <th data-options="field:'rent_begindate',width:170,align:'center'">租用日期</th>
				<th data-options="field:'rent_expectdate',width:170,align:'center'">预计返还日期</th>
				<th data-options="field:'rent_enddate',width:170,align:'center'">实际返还日期</th> -->
				<th data-options="field:'equipment_state',width:80,align:'center',styler: function(value,row,index){
								if (value=='可用'){
									return 'background-color:#F8F8FF;color:green;';
								}else{
									return 'background-color:#F8F8FF;color:red;';
								}
							}">设备状态</th>
				<!-- <th data-options="field:'is_valid',width:80,align:'center'">是否有效</th>
				<th data-options="field:'day_rent',width:80,align:'center'">日租金</th>
				<th data-options="field:'deposit',width:80,align:'center'">押金</th>
				<th data-options="field:'total_rent',width:80,align:'center'">设备租金</th> -->
				<th data-options="field:'equipment_type',width:80,align:'center'">设备类型</th>
				<th data-options="field:'sim_id',width:80,align:'center',styler: function(value,row,index){
								if (value=='未绑定'){
									return 'background-color:#F8F8FF;color:green;';
								}else{
									return 'background-color:#F8F8FF;color:red;';
								}
								}">是否绑定</th>
				<th data-options="field:'stock_date',width:170,align:'center'">进货日期</th>
				<th data-options="field:'scrap_date',width:170,align:'center'">报废日期</th>
				<!-- <th data-options="field:'create_user',width:80,align:'center'">创建人</th>
				<th data-options="field:'create_time',width:170,align:'center'">创建时间</th> -->
				<th data-options="field:'modify_user',width:80,align:'center'">修改人</th>
				<th data-options="field:'modify_time',width:170,align:'center'">修改时间</th>
				<th data-options="field:'remark',width:200,align:'center'">备注</th>
				
				</tr>
				</thead>
				</table>
				
		<!-- 表单结束 -->
	<!-- </div> -->
	<script type="text/javascript">
	$(function(){
		var pager = $('#dg').datagrid().datagrid('getPager'); // get the pager of datagrid
		 $(pager).pagination({  
			 	beforePageText: '第',//页数文本框前显示的汉字  
		        afterPageText: '页    共 {pages} 页',  
		        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
		    });  
		
	});
	
	function doubleclick(item,field){
		parent.document.getElementById("euipmentno").value=field.no;
		parent.$('#eqptlist').dialog('close');
	}
	
	/* 确定按钮 */
	function sub(){
		var rows = $("#dg").datagrid("getSelected"); 
		if(rows==null){
			alert("请先选择一条数据!");
			return false;
		}
		var equipmentno = rows.no;
		//alert(rows.no);
		parent.document.getElementById("euipmentno").value=rows.no;
		var eqptno = rows.no;
		//parent.equipment_blur(eqptno);
	    parent.$('#eqptlist').dialog('close');
		}
	
	/* 查询 */
	function searchs(){
		/* jQuery.ajax({
			url:"searchOfUnbindEqpt",
			data:$("#conditionsOfEquipment").serialize(),
			type:"post",
			error:function(){
				alert("查询失败");
			},
			success:function(data){
				//alert(data);
				data = $.parseJSON(data);
				$("#dg").datagrid({
					url:"",
				});
				$('#dg').datagrid('loadData',data);
				var pager = $('#dg').datagrid().datagrid('getPager'); // get the pager of datagrid
				 $(pager).pagination({  
					 	beforePageText: '第',//页数文本框前显示的汉字  
				        afterPageText: '页    共 {pages} 页',  
				        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
				    });  
			}
		}); */
		var no = $("#no").val();
		var counterid = $("#counterid").val();
		var sim_id = $("#sim_id").val();
		$("#dg").datagrid({
			url:"searchOfUnbindEqpt",
			queryParams:{
				no:no,counterid:counterid,sim_id:sim_id
			}
		});
		var pager = $('#dg').datagrid().datagrid('getPager'); // get the pager of datagrid
		 $(pager).pagination({  
			 	beforePageText: '第',//页数文本框前显示的汉字  
		        afterPageText: '页    共 {pages} 页',  
		        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
		    });  
	}
	</script>
</body>
 
</html>