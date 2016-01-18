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
    <title>特批申请</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<jsp:include page="../../../layout/script.jsp"></jsp:include>
	<style type="text/css">
		a{
			text-decoration:none;
		}
		.table {
			background-color: transparent;
			border-collapse: collapse;
			border-spacing: 0;
			max-width: 100%;
		}
	</style>
	<script type="text/javascript">
			var $dg;
			var $grid;
			var state = 0;
			var procStateJson = [{ 
				"id":"", 
				"text":"全部状态", 
				"selected":true 
				},{ 
				"id":1, 
				"text":"初始状态" 
				},{ 
				"id":2, 
				"text":"审批中" 
				},{ 
				"id":3, 
				"text":"已完成"
				},{ 
				"id":4, 
				"text":"已失效" 
				},{ 
				"id":5, 
				"text":"已撤销" 
				},{ 
				"id":6, 
				"text":"已拒绝" 
				}] ;
			$(function() {
				$(window).resize(function(){  
	                $("#dg").datagrid({  
	                	height: $(window).height()-82  
	                });                
	            });  
				 $dg = $("#dg");
				 $grid=$dg.datagrid({
					url : "specialRatifyApp/findSpecialRatifyAppList.do",
					width : 'auto',
					height : $(this).height()-82,
					fitColumns:true,
					pagination:true,
					rownumbers:true,
					border:false,
					singleSelect:true,
					nowrap:true,
					multiSort:false,
					pageSize:30,
					pageList:[10,20,30,40],
					columns : [ [ 	{field : 'appNo',title : '申请编号',width :120,align : 'center'},
				      				{field : 'appDate',title : '申请日期',width : 100,align : 'center'},
					                {field : 'userName',title : '申请人',width : 120,align : 'center'},
						            {field : 'deptName',title : '申请部门',width : 120,align : 'center'},
						            {field : 'contractNo',title : '贷款合同编号',width : 240,align : 'center'},
						            {field : 'speRatType',title : '特批类型',width : 120,align : 'center',
						            	formatter:function(value,row,index){
						            		if(row.speRatType=="1"){
						            			return "提前还款";
						            		}else{
						            			return "免罚息及违约金";
						            		}
						            	}},
						            {field : 'appRepaymentDate',title : '申请还款日期',width : 120,align : 'center'},
					                {field : 'procStatus',title : '流程状态',width : 80,align : 'center',formatter:function(value,row,index){
					                	if(row.procStatus == "1"){
					                		return "初始状态";
					                	}else if(row.procStatus == "2"){
					                		return "审批中";
					                	}else if(row.procStatus == "3"){
					                		return "已完成";
					                	}else if(row.procStatus == "4"){
					                		return "已失效";
					                	}else if(row.procStatus == "5"){
					                		return "已撤销";
					                	}else{
					                		return "已拒绝";
					                	}
					                }},
					                {field : 'remark',        title : '备注',    width : 120, align:'center'},
					                {field : 'aa',title : '操作',width :180,align : 'center',formatter:function(value,row,index){
					                	if(row.procStatus == "1"){
					             	    	return "<a href='javascript:void(0);' onclick='updRowsOpenDlg("+index+");'>编辑</a>&nbsp;&nbsp;"+"<a href='javascript:void(0);' onclick='delRow("+index+");'>删除</a>&nbsp;&nbsp;"+"<a href='javascript:void(0);' onclick='sumitInvestOrder("+index+");'>提交申请</a>";
					             	    }else if(row.procStatus == "2"){
					             	    	return "<a href='javascript:void(0);' onclick='checkInvestOrderOpinions("+ index + ");'>查看审批意见</a>&nbsp;&nbsp;"+"<a href='javascript:void(0);' onclick='showImage("+ index + ");'>查看流程图</a>";
					             	    }else{
					             	    	return "<a href='javascript:void(0);' onclick='checkInvestOrderOpinions("+ index + ");'>查看审批意见</a>";
					             	    }
					                }}
					              ] ],toolbar:'#tb',
					              onLoadSuccess:function(data){
					                  $(this).datagrid("doCellTip",{'max-width':'100px'});
					              }
				});
				 $('#procStatus').combobox({
						data:procStateJson,
						valueField:'id',
						textField:'text'
					});
			});
			
			/**
			 * 提交申请
			 */
			function sumitInvestOrder(index){
				var row = this.getRowData(index);
				$.messager.confirm('确定','是否确定提交申请？',function(flag) {
					if (flag) {
						$.ajax({
								url : "specialRatifyApp/saveStartProcess.do",
								data : {"sraId" : row.sraId,"applicantNo":row.applicantNo,"appNo":row.appNo},
								success : function(rsp) {
									if(rsp.status){
										$("#dg").datagrid('reload');
									}
									$.messager.alert(rsp.title,rsp.message,'info');
								}
							});
						}
					});
			}
			
			//根据index获取该行
			function getRowData(index){
				if (!$.isNumeric(index) || index < 0) {
					return undefined;
				}
				var rows = $("#dg").datagrid("getRows");
				return rows[index];
			}
			
			function delRow(index){
				var selectedRow = getRowData(index);
		    	$.messager.confirm('删除', '执行删除后，数据将不可恢复,是否执行?', function(d) {
		    		if (d) {
		    			$.ajax( {
		    				type : "POST",
		    				url : 'specialRatifyApp/delSpecialRatifyApp.do',
		    				data : "appNo="+selectedRow.appNo,
		    				dataType:'JSON',
		    				success : function(iJson) {		    					    				
		    					if(iJson.status){
		    						//刷新列表	
		    						$("#dg").datagrid("load",{});
		    					}
		    					$.messager.alert(iJson.title,iJson.message,'info');
		    				}
 		    			});
		    		}
		    	});		
			}
			//弹窗修改
			function updRowsOpenDlg(index) {
			var row = getRowData(index);
		//	alert(row.businessTripAttach.btDest);
				if (row) {
					$("#dd").dialog({
						title : '编辑',
						width : 650,
						height : 300,
						modal:true,
						href : "jsp/fd/specialRatifyApp/specialRatifyAppForm.jsp",
						onLoad:function(){
							$("#speRatType").combobox({
								url:"commonController/findDicList.do?codeMyid=spe_rat_type",
								valueField: 'code',
								textField: 'text',
								required:true,
								onSelect:function(rec){
									if(rec.code=='1'){
										$("#hiddenOne").attr("class","");
										$("#hiddenTwo").attr("class","");
										$("#appRepaymentDate").validatebox({required:true});
									}else{
										$("#hiddenOne").attr("class","dis");
										$("#hiddenTwo").attr("class","dis");
										$("#appRepaymentDate").validatebox({required:false});
										$("#appRepaymentDate").datebox("setValue","");
									}
								}
							});
							var f = $("#baseForm");
							f.form("load", row);
							var code=$("#speRatType").combobox('getValue');
							if(code=="1"){
								$("#hiddenOne").attr("class","");
								$("#hiddenTwo").attr("class","");
								$("#appRepaymentDate").validatebox({required:true});
							}
						},
						onClose:function(){
							$("#dg").datagrid("reload");
						},
						buttons : [ {
							text : '关闭',
							iconCls : 'icon-cancel',
							handler : function() {
								$("#dd").dialog('close');
							}
						}]
					}); 
				}
			}
			//弹窗增加
			function addRowsOpenDlg() {
				$("#dd").dialog({
					title : '添加',
					iconCls:'icon-add',
					width : 650,
					height : 300,
					href : "jsp/fd/specialRatifyApp/specialRatifyAppForm.jsp",
					onLoad:function(){
						$("#speRatType").combobox({
							url:"commonController/findDicList.do?codeMyid=spe_rat_type",
							valueField: 'code',
							textField: 'text',
							required:true,
							onSelect:function(rec){
								if(rec.code=='1'){
									$("#hiddenOne").attr("class","");
									$("#hiddenTwo").attr("class","");
									$("#appRepaymentDate").datebox({required:true});
								}else{
									$("#hiddenOne").attr("class","dis");
									$("#hiddenTwo").attr("class","dis");
									$("#appRepaymentDate").datebox({required:false});
									$("#appRepaymentDate").datebox("setValue","");
								}
							}
						});
					},
					onClose : function(){
				    	$("#dg").datagrid("reload");
				    },
					buttons : [ {
						text : '关闭',
						iconCls : 'icon-cancel',
						handler : function() {
							$("#dd").dialog('close');
						}
					}]
				});
			}
			
			/* function resetVali(){
				$("#dimissionDate").datebox({disabled:false});
				$("#takeoverUserName").combobox({disabled:false});
				$("#takeoverDeptName").combo({disabled:false});
			} */
			
			//执行高级查询
			function doSearch(){			
				$("#dg").datagrid("load",{
					appDept:$("#appDept").val(),
					procStatus:$("#procStatus").combobox("getValue"),
					appDateBefore:$('#appDateBefore').datebox('getValue'),
					appDateAfter:$('#appDateAfter').datebox('getValue')
				}); 
			}
			
			//重置条件
			function clearAdvancedQueryConditions(){
				//1、清空高级查询各组件内容
				$("#searchForm").form("clear");
				//2、datagrid重新加载
				$("#dg").datagrid("load",{});
			}
			
			//查看流程图
			function showImage(index){
				var row = getRowData(index);
				var src = "specialRatifyApp/showProcessImg.do?sraId="+row.sraId;
				$('#imageDialog').dialog("open");
				$("#image").attr("src", src);
			}
			
			//查看审批意见
			function checkInvestOrderOpinions(index){
				$$row = getRowData(index);
				var $optionsWindow = $("<div></div>").dialog({
					/* 动态显示Dialog的标题	*/
					width : 850,
					height : 450,					
					title : "查看审批意见",
					modal:true,
					closed: false,
					href : "jsp/pd/optionsList.jsp",
					onClose:function(){
						$(this).dialog('destroy');
						$$row=null;
					},
					buttons : [ {
						text : '关闭',
						iconCls : 'icon-cancel',
						handler : function() {
							$optionsWindow.dialog('close');
						}
					}]
				});
			}
		</script>
  </head>
  <style>
  .nkframe_position{padding-left:30px;margin-bottom:10px;border-bottom:1px solid #d2e7f8;height:24px;line-height:24px;background:url(extend/nk_position.gif) 5px center no-repeat;font-size:12px;font-weight:normal;}
  </style>
  <body>
      <div data-options="region:'center',border : false">
     <div class="position" style="margin-top: 5px;">您当前所在位置： 财务管理  &gt; 特批申请</div>
     <div class="well well-small" style="margin-left: 5px;margin-top: 5px">
			<form id="searchForm" action="businessTripApp/findBusinessTripAppList" method="post">
				<table cellpadding="0" cellspacing="1" border="0">
					<tr>
<!-- 						<td>所属部门：&nbsp;&nbsp;</td>
						<td><input name="appDept" id="appDept" type="text" class="easyui-textbox easyui-validatebox"  style="width: 170px"/>&nbsp;&nbsp;&nbsp;&nbsp;</td> -->
						<td>申请状态：&nbsp;&nbsp;</td>
						<td><select id="procStatus" class="easyui-combobox" name="procStatus"  style="width: 170px;"></select>&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td>申请日期：&nbsp;&nbsp;</td>
						<td><input name="appDateBefore" id="appDateBefore" class="easyui-datebox" editable="true" style="width:174px;" value=""  title="开始日期" /></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;到：&nbsp;&nbsp;</td>
						<td><input name="appDateAfter" id="appDateAfter" class="easyui-datebox" editable="true" style="width:174px;" value="" title="结束日期"/></td>
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
					</td>
				</tr>
			</table>
		</div>
		
		<table id="dg" title="特批申请"></table>
		<div id="imageDialog"  class="easyui-dialog" title="流程图片" data-options="border:false,closed:true,fit:true">
			<img id="image" src="" >
		</div>
		<div id="optionsDialog"></div>
  	</div>
	<div id ="dd"></div>
  </body>
</html>
