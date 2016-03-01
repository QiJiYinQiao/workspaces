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
    <title>代办任务</title>
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
	</style>	
	<script type="text/javascript">
			var $grid;
			var $selRow;
			var $$row;
			var $dialog;
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
				$(window).resize(function(){  
		 	        $("#dg").datagrid("resize",{  
		 	        	height: $(window).height()-82
		 	        });                
		 	    });
				<%
			    String definitionKey=request.getParameter("key");
			    %>
				 $grid=$("#dg").datagrid({
					url : "ppeTurnoverAppController/findAllPpeTurnoverAppTaskList.do?definitionKey=<%=definitionKey %>",
					width : 'auto',
					height : $(this).height()-82,
					pagination:true,
					rownumbers:true,
					border:true,
					singleSelect:false,
					nowrap:true,
					multiSort:false,
					fitColumns:true,
					columns : [ [
					{field : 'ck',title:'ck',checkbox:true},
		            {field : 'appDeptName',title : '申请部门',width : 120,align : 'center',
							        	 formatter: function(value,row,index){
							        		 return row.ppeTurnoverApp.appDeptName;
						      				}},
					{field : 'appNo',title : '申请编号',width :120,align : 'center',
			        	 formatter: function(value,row,index){
			        		 return row.ppeTurnoverApp.appNo;
		      				}},
	                {field : 'name',title : '申请人',width : 120,align : 'center',
					        	 formatter: function(value,row,index){
					        		 return row.ppeTurnoverApp.name;
				      				}},
      				/* {field : 'takeoverUserName',title : '接收人',width : 120,align : 'center',
			        	 formatter: function(value,row,index){
			        		 return row.ppeTurnoverApp.takeoverUserName;
		      				}},
		            {field : 'takeoverDeptName',title : '接收部门',width : 120,align : 'center',
							        	 formatter: function(value,row,index){
							        		 return row.ppeTurnoverApp.takeoverDeptName;
						      				}}, */
	                
	                /* {field : 'ppeQty',title : '资产总数量',width : 80,align : 'center',
									        	 formatter: function(value,row,index){
									        		 return row.ppeTurnoverApp.ppeQty;
								      				}}, */
	                {field : 'appDate',title : '申请日期',width : 100,align : 'center',
			        	 formatter: function(value,row,index){
			        		 return row.ppeTurnoverApp.appDate;
		      				}},
      				{field : 'ppeNo',        title : '资产编号',    width : 120, align:'center' },
    				{field : 'ppeName',      title : '资产名称', width : 120, align:'center'},
    				{field : 'model',    title : '资产规格',    width : 120, align:'center' },
    				{field : 'ppeAmtStr',    title : '单价',    width : 80, align:'center' },
    				{field : 'ppeTotalAmtStr',title : '小计金额(元)',width : 80,align : 'center',
			        	 formatter: function(value,row,index){
			        		 return row.ppeTurnoverApp.ppeTotalAmtStr;
		      				}},
    				{field : 'heji',title : '合计(元)',width : 80,align : 'center'},
	                {field : 'procStatus',title : '流程状态',width : 80,align : 'center',formatter:function(value,row,index){
	                	if(row.ppeTurnoverApp.procStatus == "1"){
	                		return "初始状态";
	                	}else if(row.ppeTurnoverApp.procStatus == "2"){
	                		return "审批中";
	                	}else if(row.ppeTurnoverApp.procStatus == "3"){
	                		return "已完成";
	                	}else{
	                		return "已撤销";
	                	}
	                }},
	                {field : 'caozuo',title : '操作',width :250,align : 'center',formatter:function(value,row,index){
	                	var result = ""; 
					    if (row.ppeTurnoverApp.assistant == null || row.ppeTurnoverApp.assistant == "") {
							result += "<a href='javascript:void(0);' onclick='singForTask("+row.ppeTurnoverApp.taskID+");'>签收任务</a>　　";
						}else{
							result += "<a href='javascript:void(0);' onclick='saveTask("+index+");'>办理任务</a>　　";
						} 
					    result += "<a href='javascript:void(0);' onclick='checkHistoryOpinions("+ index +");'>查看审批意见</a>　　";
					    result += "<a href='javascript:void(0);' onclick='showImage("+ index + ");'>查看流程图</a>";
					    return result;
	                }}
					] ],toolbar:'#tb',
	              onLoadSuccess:function(data){
	            	  var paramObj = {rowStr:'ppeTurnoverApp.appDeptName,ppeTurnoverApp.appNo',//按照哪列进行分组
	      					rowGroup:'appDeptName,heji:ck,appNo,ppeTotalAmtStr,name,appDate,caozuo,procStatus' ,//对应rowStr的需要合并单元格的字段
	      					hejiColumn:'ppeAmtStr', //需要计算的合计值的所在列
	      					hejiResult:'heji'};//计算后将合计值更新到该列中 
	      		   	  dataGirdSumMergeFunc($(this),paramObj);
	            	  
	                  $(this).datagrid("doCellTip",{'max-width':'100px'});
	              }
				});
				 $('#procStatus').combobox({
						data:procStateJson,
						valueField:'id',
						textField:'text'
					});
			});
		
			//批量处理任务
			function signTask(){
				var rows = $grid.datagrid("getSelections");
				if(rows==null || rows.length<=0){
					$.messager.alert("提示","请选择至少一条记录!","warning");
					return false;
				}
				ajaxLoading("正在处理 请稍后...");
				var businessID = new Array();
				var formKey = "";
				var taskID = new Array();
				var appNo = new Array;
				
				for(var i=0;i<rows.length;i++){
					if($.inArray(rows[i].ppeTurnoverApp.ptaId,businessID)==-1){
						businessID.push(rows[i].ppeTurnoverApp.ptaId);
						formKey=rows[i].ppeTurnoverApp.formKey;
						taskID.push(rows[i].ppeTurnoverApp.taskID);
						appNo.push(rows[i].ppeTurnoverApp.appNo);
					}
				}
				var data1 = "appNo="+appNo.join(",")+"&businessID="+businessID.join(",")+"&taskID="+taskID.join(",")+"&formKey="+formKey;
			 	  $.ajax({
					type: "POST",
					url:"ppeTurnoverAppController/saveTaskPpeTurnoverAppBatch.do",
					data:data1,
				    success: function(iJson) {
				    	$("#dg").datagrid("load",{});
				    	ajaxLoadEnd();
			 	    	$.messager.alert(iJson.title,iJson.message,'info');
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
		
		//签收任务
		function singForTask(taskID){
			$.messager.confirm('签收任务', '是否确认签收任务?', function(d) {
	    		if (d) {
	    			$.ajax( {
	    				type : "POST",
	    				url : 'ppeTurnoverAppController/signForTask.do',
	    				data : "taskID="+taskID,
	    				dataType:'JSON',
	    				success : function(iJson) {		    					    				
	    					if(iJson.status){
	    						//刷新列表		    						
	    						$("#dg").datagrid("load",{});
	    					}
	    					parent.$.messager.alert(iJson.title,iJson.message,'info');
	    				}
		    			});
	    		}
	    	});		
		}	
		function saveTask(index){
			var selectedRow = getRowData(index);
			$selRow = selectedRow.ppeTurnoverApp;
			$$row = $selRow;
			var formKey = selectedRow.ppeTurnoverApp.formKey;
			$dialog=$("<div></div>").dialog({
				/* 动态显示Dialog的标题	*/
				width : 850,
				height : 450,					
				title : '受理任务',
				href : formKey,
			    onLoad:function(){
			    	var saveOrUpdateForm = $("#taskForm");
			    	saveOrUpdateForm.form("load",$selRow);
			    	$("#taskForm #businessID").val(selectedRow.ppeTurnoverApp.ptaId);
			    	$("#taskForm #taskID").val(selectedRow.ppeTurnoverApp.taskID);
			    },
			    modal:true,
				resizable:false,
				iconCls:'icon-add',
				closed: false,
			    buttons : [{
						text : '关闭',
						iconCls : 'icon-cancel',
						handler : function() {
							$dialog.dialog('close');
					}
				}],onClose:function(){
			    	//刷新列表
			    	$("#dg").datagrid("reload");
			    	//关闭弹窗
			    	$(this).dialog('destroy');
			    }
			});
		}
		
		//查看历史审批意见
		function checkHistoryOpinions(index){
			$$row = getRowData(index).ppeTurnoverApp;
			var $optionsWindow = $("<div></div>").dialog({
				title: '历史审批意见',    
			    width: 900,    
			    height: 500,    
			    closed: false,    
			    cache: false,    
			    href: 'jsp/ad/optionsList.jsp',    
			    modal: true,
			    onClose : function(){
			    	$(this).dialog('destroy');
			    	$$row = null;
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
		
		//查看流程图
		function showImage(index){
			var row = getRowData(index);
			var src = "ppeTurnoverAppController/showProcessImg.do?ptaId="+row.ppeTurnoverApp.ptaId;
			$('#imageDialog').dialog("open");
			$("#image").attr("src", src);
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
	</script>
  </head>
  <body>
      <div data-options="region:'center',border : false">
  		<div class="position" style="margin-top: 5px;">您当前所在位置： 行政办公  &gt; 固定资产移交 </div>
     <div class="well well-small" style="margin-left: 5px;margin-top: 5px">
			<form id="searchForm" action="ppeTurnoverAppController/findAllPpeTurnoverAppTaskList.do" method="post" style="min-width: 1200px;">
				<table cellpadding="0" cellspacing="1" border="0">
					<tr>
<!-- 						<td>所属部门：&nbsp;&nbsp;</td>
						<td><input name="appDept" id="appDept" type="text" class="easyui-textbox easyui-validatebox"  style="width: 170px"/>&nbsp;&nbsp;&nbsp;&nbsp;</td> -->
						<td>申请状态：&nbsp;&nbsp;</td>
						<td><select id="procStatus" class="easyui-combobox" name="procStatus" editable="false"  style="width: 170px;"></select>&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td>申请日期：&nbsp;&nbsp;</td>
						<td><input name="appDateBefore" id="appDateBefore" class="easyui-datebox" editable="false" style="width:174px;" value=""  title="开始日期" /></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;</td>
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
		<!-- 新增弹框 -->
		<div id="addWindow"></div>
		<table id="dg"></table>
		<div id="tb" style="padding:2px 0">
			<a id="id4ExportReports" class="easyui-linkbutton" data-options="iconCls:'icon-add'" plain="true" onclick="signTask();">批量受理任务</a>
		</div>
	    <div id="imageDialog"  class="easyui-dialog" title="流程图片" data-options="border:false,closed:true,fit:true">
			<img id="image" src="" >
		</div>
  	</div>	
  </body>
</html>
