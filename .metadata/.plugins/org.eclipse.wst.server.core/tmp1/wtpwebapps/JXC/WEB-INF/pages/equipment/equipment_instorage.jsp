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
			.searchstyle{
				border: 1px solid #95b8e7;border-radius: 5px;height: 22px;width: 140px;
			}
			.file-box{ position:relative;width:340px}
			.txt{ height:22px; border:1px solid #cdcdcd; width:180px;}
			.btn{ background-color:#FFF; border:1px solid #CDCDCD;height:24px; width:70px;}
			.file{ position:absolute; top:0px; right:80px; height:24px; filter:alpha(opacity:0);opacity: 0;width:260px } 
	</style>
</head>
<body class="easyui-layout">
	<script type="text/javascript">
			$(function(){
				$("#dg").datagrid({
					url:"getEquipmnetInStorage?t="+new Date().toString(),
				});
			});
		</script>
	<div data-options="region:'center',title:'设备列表'" scrolling="no" style="overflow:hidden;background-color:#F8F8FF;">
		<div style="background-color:#F8F8FF;align:center;width: 100%;height: 40px;overflow: hidden;">
					<div align="right" style="margin-top: 5px;margin-right: 5px;">
						<!-- <a href="downloadModel" class="easyui-linkbutton" style="margin-right: 5px;width: 100px;height: 25px;">下载设备模板</a> -->
						<a onclick="instorage();" class="easyui-linkbutton" style="margin-right: 18px; margin-top:8px; width: 80px;height: 25px;">设备入库</a>
						<!-- <a onclick="checkdetail();" class="easyui-linkbutton" style="margin-right: 5px;width: 60px;height: 25px;">详情</a>
						<a onclick="edits();" class="easyui-linkbutton" style="margin-right: 5px;width: 60px;height: 25px;">编辑</a>
						<a onclick="addeqpt();" class="easyui-linkbutton" style="margin-right: 5px;width: 60px;height: 25px;">新增</a>
						<a onclick="serachWithCondition();" class="easyui-linkbutton" style="margin-right: 5px;width: 60px;height: 25px;">查询</a> -->
					</div>
				</div>
		<!-- 表单开始 -->
				<table id="dg" style="height: 93%;background-color:#F8F8FF" class="easyui-datagrid"  
				data-options="rownumbers:true,pagination:true,singleSelect:true,method:'post',remoteSort:false,multiSort:true">
				<thead>
				<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'no',width:120,align:'center'">设备编号</th>
				<th data-options="field:'counterid',width:120,align:'center'">柜台名称</th>
				<th data-options="field:'rent_begindate',width:170,align:'center',sortable:true">租用日期</th>
				<th data-options="field:'rent_expectdate',width:170,align:'center',sortable:true">预计返还日期</th>
				<th data-options="field:'rent_enddate',width:170,align:'center',sortable:true">实际返还日期</th>
				<th data-options="field:'equipment_state',width:80,align:'center',styler: function(value,row,index){
								if (value=='可用'){
									return 'background-color:#F8F8FF;color:green;';
								}else{
									return 'background-color:#F8F8FF;color:red;';
								}
							}">设备状态</th>
				<th data-options="field:'is_valid',width:80,align:'center'">是否有效</th>
				<th data-options="field:'day_rent',width:80,align:'center'">日租金</th>
				<th data-options="field:'deposit',width:80,align:'center'">押金</th>
				<th data-options="field:'total_rent',width:80,align:'center'">设备租金</th>
				<th data-options="field:'equipment_type',width:80,align:'center'">设备类型</th>
				<th data-options="field:'sim_id',width:80,align:'center',styler:function(value,row,index){
					if(value=='未绑定'){
						return 'background-color:#F8F8FF;color:green;';
					}else{
						return 'background-color:#F8F8FF;color:red';
					}
				}">是否绑定</th>
				<th data-options="field:'in_storage',width:170,align:'center',sortable:true">入库日期</th>
				<th data-options="field:'stock_date',width:170,align:'center',sortable:true">进货日期</th>
				<th data-options="field:'scrap_date',width:170,align:'center',sortable:true">报废日期</th>
				<!-- <th data-options="field:'create_user',width:80,align:'center'">创建人</th>
				<th data-options="field:'create_time',width:170,align:'center',sortable:true">创建时间</th> -->
				<th data-options="field:'modify_user',width:80,align:'center'">操作员</th>
				<th data-options="field:'modify_time',width:170,align:'center',sortable:true">日期</th>
				<th data-options="field:'remark',width:200,align:'center'">备注</th>
				</tr>
				</thead>
				</table>
		<!-- 表单结束 -->
   		 <div id="inportdiv" modal="true" class="easyui-dialog" style="width:400px;height:200px;overflow:hidden;" closed="true" title=" ">
        	 <div class="file-box" style="text-align: center;"><!--  -->
				<form id="uploadform"  action="putinstorage" method="post" enctype="multipart/form-data">
				<input type='text' name='textfield' id='textfield' class='txt' />
				<input type='button' class='btn' value='浏览...' />
				<input type="file" name="instoragefile" class="file" id="fileField" size="28" onchange="document.getElementById('textfield').value=this.value" />
				<input type="submit" name="submit" class="btn" value="上传" />
				</form>
			 </div> 
   		 </div>
	</div>
	 <script type="text/javascript">
		$(function(){
			 var pager = $('#dg').datagrid().datagrid('getPager'); // get the pager of datagrid
			 $(pager).pagination({  
				 	beforePageText: '第',//页数文本框前显示的汉字  
			        afterPageText: '页    共 {pages} 页',  
			        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
			 });
		});
		
		function instorage(){
			$("#inportdiv").dialog("open");
		}
	</script>
</body>
 
</html>