<%@page import="com.oasys.util.Constants"%>
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
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<jsp:include page="../../../layout/script.jsp"></jsp:include>
	<script type="text/javascript" src="jsp/pd/empSalPositionChgApp/empSalJS.js"></script>
	<style type="text/css">
		a{
			text-decoration:none;
		}
	</style>
	<script type="text/javascript">
			//声明一个全局变量row
			//该变量可以在基于此Main页面上的弹出页面中使用，比如optionList.jsp页面。
			var $dg;
			var $grid;
			var $dialog;
			var $$row;
			
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
				}] ;
		 	$(function() {
				//加载员工薪资岗位变动申请的数据
				 $dg = $("#dg");
				$(window).resize(function(){  
		            $("#dg").datagrid({  
		            	height: $(window).height()-100,
		            	width : 'auto'
		            });                
		        });
				 $grid=$dg.datagrid({
					url : "empSalPositionChgAppController/findAllEmpSalApp.do",
					title: "员工薪资岗位变动申请",
					width : 'auto',
					height : $(this).height()-83,
					pagination:true,
					rownumbers:true,
					border:true,
					singleSelect:true,
					nowrap:true,
					multiSort:false,
					border:false,
					fitColumns:true,
					pageSize:30,
					pageList:[10,20,30,40],
					columns : [ [
								{field : 'efaId',        title : '编号',    width : 0, align:'center'},
								{field : 'appNo',        title : '申请编号',    width : parseInt($(this).width() * 0.08), align:'center'},
								{field : 'userName',        title : '申请人',    width : parseInt($(this).width() * 0.06), align:'center'},
								{field : 'appDate',        title : '申请时间',    width : parseInt($(this).width() * 0.06), align:'center'},
			    				{field : 'curDeptName',        title : '目前部门名称',    width : parseInt($(this).width() * 0.06), align:'center' },
			    				{field : 'curPosition',      title : '目前所在职位', width : parseInt($(this).width() * 0.07), align:'center'}, 
			    				{field : 'curSal',    title : '目前薪资(元)',    width : parseInt($(this).width() * 0.06), align:'center' },
			    				{field : 'entryDate',    title : '入职日期',    width : parseInt($(this).width() * 0.06), align:'center' },
			    				{field : 'chgTypeName',    title : '异动事由',    width : parseInt($(this).width() * 0.06), align:'center' },
			    				{field : 'aftDeptName',    title : '调整后部门名称',    width : parseInt($(this).width() * 0.08), align:'center' },
			    				{field : 'aftPosition',    title : '调整后所在职位',    width : parseInt($(this).width() * 0.08), align:'center' },
			    				{field : 'aftSal',    title : '调整后薪资',    width : parseInt($(this).width() * 0.06), align:'center' },
			    				{field : 'chgResonName',    title : '变动理由',    width : parseInt($(this).width() * 0.08), align:'center',
			    					formatter:function(value,row,index){
			    						//判断如果为其他 则显示 其他变动理由 字段
			    						if(row.chgReson == "4"){
			    							return row.chgResonOther;
			    						}else{
			    							return row.chgResonName;
			    						}
			    					} },
			    				{field : 'salChgTypeName',    title : '薪资调整形势',    width : parseInt($(this).width() * 0.08), align:'center' },
			    				{field : 'effectiveDate',    title : '申请生效日期',    width : parseInt($(this).width() * 0.08), align:'center' },
			    				{field : 'trialPeriods',    title : '试岗期限(月)',    width : parseInt($(this).width() * 0.06), align:'center' },
			    				{field : 'trialSal',    title : '试岗月薪(元)',    width : parseInt($(this).width() * 0.06), align:'center' },
						         {field : 'sqzt',    title : '申请状态',    width : parseInt($(this).width() * 0.05), align:'center',
						        	 formatter: function(value,row,index){
						        		 var result = "";
											if (row.procStatus == 1) {
												result = "初始状态";
											} else if (row.procStatus == 3) {
												result = "审批完成";
											} else if (row.procStatus == 5) {
												result = "审批撤销";
											} else {
												result = "审批中";
											}
											return result;
					      				}
						        	 },
						          {field : 'caozuo',    title : '操作',    width : parseInt($(this).width() * 0.13), align:'center',
						        	  formatter: function(value,row,index){
						        		  	var result = ""; 
											if (row.procStatus == 1) {
												result += "<a href='javascript:void(0);' onclick='toSaveOrUpdateEmpSal("+ row.efaId + ");'>编辑</a>&nbsp;&nbsp;&nbsp;";
												result += "<a href='javascript:void(0);' onclick='deleteEmpSalApp("+ index + ");'>删除</a>&nbsp;&nbsp;&nbsp;";
												result += "<a href='javascript:void(0);' onclick='sumitLoanOrder("+index+");'>提交申请</a>";
											} else if (row.procStatus == 3 || row.procStatus == 5) {
												result += "<a href='javascript:void(0);' onclick='showInvestProductDetails("+ index + ")';>查看审批意见</a>&nbsp;&nbsp;&nbsp;"
											} else {
												result += "<a href='javascript:void(0);' onclick='showInvestProductDetails("+ index + ");'>查看审批意见</a>&nbsp;&nbsp;&nbsp;"
												result += "<a href='javascript:void(0);' onclick='showImage("+index+");'>查看当前流程图</a>"
											}
											return result;
					      				}
						          }
					              ] ],
		              toolbar:'#tb',
		              onClickCell:function(rowIndex, field, value){
		            	  $(this).datagrid("selectRow","rowIndex");
		              }
				});
				 $('#dg').datagrid("doCellTip",{'max-width':'100px'});
				 $('#dg').datagrid('hideColumn','efaId'); 
				$('#procStatus').combobox({
					data:procStateJson,
					valueField:'id',
					textField:'text'
				});
			});  
		 	
		 	//查看流程图
		 	function showImage(index){
		 		var selectedRow = getRowData(index);
		 		//获取taskID
		 		$.ajax( {
    				type : "POST",
    				url : 'empSalPositionChgAppController/getProcDefKey.do',
    				data : "busID="+selectedRow.efaId,
    				dataType:'JSON',
    				success : function(iJson) {		  
    			 		var src = "workflowAction/showProcessImg.do?taskId="+iJson.taskID+"&imgName="+iJson.procDefKeyDes;
    			 		$('#imageDialog').dialog("open");
    			 		$("#image").attr("src", src);
    				}
	    		});
		 	}
		 	
		 	
		 	/** 删除员工薪资岗位变动申请  **/
			function deleteEmpSalApp(index){
				var selectedRow = getRowData(index);
		    	$.messager.confirm('删除', '执行删除后，数据将不可恢复,是否执行?', function(d) {
		    		if (d) {
		    			$.ajax( {
		    				type : "POST",
		    				url : 'empSalPositionChgAppController/delPPEScrap.do',
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
		
		/* 动态显示弹出的Dialog的标题,显示"添加"或者"修改"报废申请*/
		function changeMyTitle(index){
			if(null == index || '' == index){
				return '添加员工薪资岗位变动申请单';					
			}else{
				return '修改员工薪资岗位变动申请单';
			}
		}
		


		// 提交申请
		function sumitLoanOrder(index){
			var selectedRow = getRowData(index);
			$.messager.confirm('提交', '是否提交所选择的申请?', function(d) {
	    		if (d) {
	    			$.ajax( {
	    				type : "POST",
	    				url : 'empSalPositionChgAppController/startProcessEmpSal.do',
	    				data : "efaId="+selectedRow.efaId,
	    				dataType:'JSON',
	    				success : function(iJson) {		    					    				
	    					if(iJson.status){
	    						doSearch();
 	    						$("#dg").datagrid("load",{}); 
	    					}
	    					$.messager.alert(iJson.title,iJson.message,'info');  					
	    				}
	    			});
	    		}
	    	});
		}
		
		/** 点击按钮，新增或者修改报废申请 **/			
		function toSaveOrUpdateEmpSal(efaId){
			$dialog=$("<div></div>").dialog({
				/* 动态显示Dialog的标题	*/
				width : 900,
				height : 650,					
				title : changeMyTitle(efaId),
				href : "empSalPositionChgAppController/toAddEmpSalPositionChgApp.do?efaId="+ efaId,
				modal:true,
				resizable:false,
				iconCls:'icon-add',
				closed: false,
			    onClose:function(){
			    	//刷新列表
			    	$("#dg").datagrid("reload");
			    	//关闭弹窗
			    	$(this).dialog('destroy');
			    },
			    buttons : [ {
			    	text:'保存',
			    	iconCls:'icon-save',
					handler:saveEmpSal
			    },{
					text : '关闭',
					iconCls : 'icon-cancel',
					handler : function() {
						$dialog.dialog('close');
					}
				}]
			});
		}
	
		
	</script>
  </head>
  <body>
      <div data-options="region:'center',border : false">
      	<div class="position" style="margin-top: 5px;">您当前所在位置： 业务管理 -> 人力资源规划 -> 员工薪资岗位变动申请</div>
		<!-- 高级查询栏区域 -->
  		<div class="well well-small" style="margin-left: 5px;margin-top: 5px">
			<form id="searchForm" action="empSalPositionChgAppController/findAllEmpSalApp.do" method="post">
			<input type="hidden"  name="appNoShow" id="appNoShow" />
				<table cellpadding="0" cellspacing="1" border="0">
					<tr>
<!-- 						<td>所属部门：&nbsp;&nbsp;</td>
						<td><input name="appDept" id="appDept" type="text" class="easyui-textbox easyui-validatebox"  style="width: 170px"/>&nbsp;&nbsp;&nbsp;&nbsp;</td> -->
						<td>申请状态：&nbsp;&nbsp;</td>
						<td><select id="procStatus" class="easyui-combobox" name="procStatus"  editable="false"  style="width: 170px;"></select>&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td>申请日期：&nbsp;&nbsp;</td>
						<td><input name="appDateBefore" id="appDateBefore" class="easyui-datebox" editable="false" style="width:174px;" value=""  title="开始日期" /></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;到：&nbsp;&nbsp;</td>
						<td><input name="appDateAfter" id="appDateAfter" class="easyui-datebox" editable="false" style="width:174px;" value="" title="结束日期"/></td>
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
			<a id="id4ExportReports"  class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="toSaveOrUpdateEmpSal('');">添加</a>
		</div>
		<!-- 表格区域 -->
		<table id="dg"  width="100%"></table>	
		
		<!-- 查看历史审批区域 -->
		<div id="EmpSalHisDialog"></div>
  	</div>	
  	<div id="imageDialog"  class="easyui-dialog" title="流程图片" data-options="border:false,closed:true,fit:true">
	<img id="image" src="" >
	</div>
  </body>
</html>
