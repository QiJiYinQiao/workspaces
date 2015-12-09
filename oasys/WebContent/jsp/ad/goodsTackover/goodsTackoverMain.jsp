<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>新开办分公司物品交接登记</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<jsp:include page="../../../layout/script.jsp"></jsp:include>
	<style type="text/css">
		.dis{
		display: none;
	}
	</style>
	<script type="text/javascript">
			var $dg;
			var $grid;
			var state = 0;
			$(function() {
				$(window).resize(function(){  
	                $("#dg").datagrid({  
	                	height: $(window).height()-82  
	                });                
	            });  
				
				 $dg = $("#dg");
				 $grid=$dg.datagrid({
				//	url : "ppeTurnoverAppController/findAllPpeTurnoverApp.do",
					url : "goodsReg/findAllGoods.do",
					width : 'auto',
					height : $(this).height()-82,
					fitColumns:true,
					pagination:true,
					rownumbers:true,
					border:false,
					singleSelect:false,
					nowrap:true,
					multiSort:false,
					pageSize:10,
					pageList:[10,20,30,40],
					columns : [ [ 	
					                {field : 'registerName',title : '登记人',width : 120,align : 'center'},
						            {field : 'regDatetime',title : '登记时间',width : 120,align : 'center'},
				      				{field : 'turnoverUserName',title : '移交人',width : 120,align : 'center'},
						            {field : 'turnoverDeptName',title : '移交部门',width : 120,align : 'center'},
					                {field : 'turnoverDate',title : '移交日期',width : 80,align : 'center',
						            	formatter:function(value,row){
						            		return value.split(" ")[0];
						            	}},
					                {field : 'tackoverUserName',title : '接收人',width : 100,align : 'center'},
				      				{field : 'tackoverDeptName',title : '接收部门',width : 100,align : 'center'},
				      				{field : 'tackoverDate',        title : '接收日期',    width : 120, align:'center',
						            	formatter:function(value,row){
						            		return value.split(" ")[0];
						            }},
				    				{field : 'remark',      title : '备注', width : 120, align:'center'},
				    				{field : 'xiangqing',        title : '查看详情',    width : 120, align:'center',formatter:function(value,row,index){
				      					return "<a href='javascript:void(0);' onclick='toGoods("+ index + ");'>查看详情</a>";
				      					}
				      				},
					              ] ],toolbar:'#tb',
					              
				});
			});
			
			//根据index获取该行
			function getRowData(index){
				if (!$.isNumeric(index) || index < 0) {
					return undefined;
				}
				var rows = $("#dg").datagrid("getRows");
				return rows[index];
			}
			
			//删除
			function delRows(){
				 var rows = $('#dg').datagrid('getSelections');//获取选中的记录
				 if(rows!=null&&rows.length>=1){
					 var ids = new Array();
					 $.messager.confirm('删除', '删除该记录将不可恢复，确认删除吗?', function(d) {
						 if(d){
							 for(var i=0;i<rows.length;i++){
									ids.push(rows[i].gtrId);
								}
							 ids = ids.join(",");// 转换为字符串
							 $.ajax({
								    type:'post',
								    url : 'goodsReg/deleteGoods.do',
				    				data : "ids="+ids,
									success: function(data){
										$.messager.alert(data.title,data.message,'info');
										$("#dg").datagrid("load",{});
									},
									error:function(data){
										$.messager.alert(data.title,data.message,'info');
									}
							});
							 appNos = appNos.join(",");
						 }
					 })
				 }else{
					 $.messager.alert("提示","请至少选择一条记录!","warning");
				 }
			}
			//查看详情
			function toGoods(index){
				var row=getRowData(index);
				if (row) {
					$("#dd").dialog({
						title : '编辑',
						width : 900,
						height : 600,
						modal:true,
						href : "jsp/ad/goodsTackover/goodsTackoverForm.jsp",
						onLoad:function(){
							var f = $("#goodsTackoverForm");
							row.turnoverDate=row.turnoverDate.split(" ")[0];
							row.tackoverDate=row.tackoverDate.split(" ")[0];
							f.form("load", row);
							checkedIds2();
							$("textarea").attr({disabled:"disabled"});
							$("#onRow").attr("class","dis");
						},
						onClose:function(){
							$("#dg").datagrid("reload");
						}
					}); 
				} else{
					$.messager.alert('提示','请选择一行记录!','info');
				}
			}
			
			//弹窗修改
			function updRowsOpenDlg() {
				var row = $dg.datagrid("getSelected");
				var rows = $dg.datagrid("getSelections");
				if(row == null){
					$.messager.alert("提示","请选择一条记录进行修改!","warning");
					return false;
				}
				if(rows!=null && rows.length>1){
					$.messager.alert("提示","您只能选择一条记录进行修改!","warning");
					return false;
				}
				
					$("#dd").dialog({
						title : '编辑',
						width : 900,
						height : 600,
						modal:true,
						href : "jsp/ad/goodsTackover/goodsTackoverForm.jsp",
						onLoad:function(){
							resetVali();
							var f = $("#goodsTackoverForm");
							row.turnoverDate=row.turnoverDate.split(" ")[0];
							row.tackoverDate=row.tackoverDate.split(" ")[0];
							f.form("load", row);
							checkedIds();
						},
						onClose:function(){
							$("#dg").datagrid("reload");
						}
					}); 
				
			}
			
			function checkedIds(){
				$.ajax({
					url:"goodsReg/findGoodsStacfgIds.do",
					type:"POST",
					data:{"id":$("#gtrId").val()},
					success:function(msg){
						var ids=msg.split(",");
						$("input[type=checkbox]").each(function(){ 
							for(var i=0;i<ids.length;i++){
								if($(this).val()==ids[i]){
									$(this).attr({checked:"checked"});
								}
							}
						}); 
					}
				});
			}
			
			function checkedIds2(){
				$.ajax({
					url:"goodsReg/findGoodsStacfgIds.do",
					type:"POST",
					data:{"id":$("#gtrId").val()},
					success:function(msg){
						var ids=msg.split(",");
						$("input[type=checkbox]").each(function(){ 
							for(var i=0;i<ids.length;i++){
								if($(this).val()==ids[i]){
									$(this).attr({checked:"checked"});
								}
								$(this).attr({disabled:"disabled"});
							}
						}); 
					}
				});
			}
			//弹窗增加
			function addRowsOpenDlg() {
				$("#dd").dialog({
					title : '添加',
					iconCls:'icon-add',
					width : 910,
					height : 610,
					href : "jsp/ad/goodsTackover/goodsTackoverForm.jsp",
					onLoad:function(){
						$("#goodsTackoverForm").form('clear');
						resetVali();
					},
					onClose:function(){
						$("#dg").datagrid("reload");
					}
				});
			}
			
			function resetVali(){
				$("#turnoverDate").datebox({disabled:false});
				$("#tackoverDate").datebox({disabled:false});
				$("#turnoverUserName").combobox({disabled:false});
				$("#tackoverUserName").combobox({disabled:false});
				$("#turnoverDeptName").combo({disabled:false});
				$("#tackoverDeptName").combo({disabled:false});
			}
			//执行高级查询
			function doSearch(){			
				$("#dg").datagrid("load",{
					regDatetimeBefore:$('#regDatetimeBefore').datebox('getValue'),
					regDatetimeAfter:$('#regDatetimeAfter').datebox('getValue')
				}); 
			}
			
			//重置条件
			function clearAdvancedQueryConditions(){
				//1、清空高级查询各组件内容
				$("#searchForm").form("clear");
				//2、datagrid重新加载
				$("#dg").datagrid("load",{});
			}
			
		</script>
  </head>
  <style>
  .nkframe_position{padding-left:30px;margin-bottom:10px;border-bottom:1px solid #d2e7f8;height:24px;line-height:24px;background:url(extend/nk_position.gif) 5px center no-repeat;font-size:12px;font-weight:normal;}
  </style>
  <body>
      <div data-options="region:'center',border : false">
     <div class="position" style="margin-top: 5px;">您当前所在位置： 业务管理  &gt; 行政办公  &gt; 新开办分公司物品交接登记 </div>
     <div class="well well-small" style="margin-left: 5px;margin-top: 5px">
			<form id="searchForm" action="goodsReg/findAllGoods" method="post">
				<table cellpadding="0" cellspacing="1" border="0">
					<tr>
<!-- 						<td>所属部门：&nbsp;&nbsp;</td>
						<td><input name="appDept" id="appDept" type="text" class="easyui-textbox easyui-validatebox"  style="width: 170px"/>&nbsp;&nbsp;&nbsp;&nbsp;</td> -->
						<!-- <td>申请状态：&nbsp;&nbsp;</td>
						<td><select id="procStatus" class="easyui-combobox" name="procStatus"  style="width: 170px;"></select>&nbsp;&nbsp;&nbsp;&nbsp;</td> -->
						<td>登记日期：&nbsp;&nbsp;</td>
						<td><input name="regDatetimeBefore" id="regDatetimeBefore" class="easyui-datebox" editable="true" style="width:174px;" value=""  title="开始日期" /></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;到：&nbsp;&nbsp;</td>
						<td><input name="regDatetimeAfter" id="regDatetimeAfter" class="easyui-datebox" editable="true" style="width:174px;" value="" title="结束日期"/></td>
						<td width="70px"></td>
						<td colspan="4" align="right">
						    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="false" onclick="doSearch();">执行查询</a>&nbsp;&nbsp;&nbsp;&nbsp;
						    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-reload" plain="false" onclick="clearAdvancedQueryConditions()">条件重置</a>
						</td>	
					</tr>	
				</table>
			</form>			  			
		</div>
		<div id="tb" style="padding:2px 0">
			<table cellpadding="0" cellspacing="0">
				<tr>
					<td style="padding-left:2px">
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addRowsOpenDlg();">添加</a>
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updRowsOpenDlg();">编辑</a>
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRows();">删除</a>
					</td>
					<!-- <td style="padding-left:2px">
						<input id="searchbox" type="text"/>
					</td> -->
				</tr>
			</table>
		</div>
		
		<table id="dg" title="新开办分公司物品交接登记"></table>
		<div id="imageDialog"  class="easyui-dialog" title="流程图片" data-options="border:false,closed:true,fit:true">
			<img id="image" src="" >
		</div>
		<div id="optionsDialog"></div>
  	</div>
	<div id ="dd"></div>
  </body>
</html>
