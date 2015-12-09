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
			$(function() {
				$(window).resize(function(){  
		 	        $("#dg").datagrid({  
		 	        	height: $(window).height()-35
		 	        });                
		 	    });
				<%
			    String definitionKey=request.getParameter("key");
			    %>
				 $grid=$("#dg").datagrid({
					url : "ppeTurnoverAppController/findAllPpeTurnoverAppTaskList.do?definitionKey=<%=definitionKey %>",
					width : 'auto',
					height : $(this).height()-35,
					pagination:true,
					rownumbers:true,
					border:true,
					singleSelect:true,
					nowrap:true,
					multiSort:false,
					fitColumns:true,
					columns : [ [ 	{field : 'appNo',title : '申请编号',width :120,align : 'center',
			        	 formatter: function(value,row,index){
			        		 return row.ppeTurnoverApp.appNo;
		      				}},
	                {field : 'name',title : '申请人',width : 120,align : 'center',
					        	 formatter: function(value,row,index){
					        		 return row.ppeTurnoverApp.name;
				      				}},
		            {field : 'appDeptName',title : '申请部门',width : 120,align : 'center',
							        	 formatter: function(value,row,index){
							        		 return row.ppeTurnoverApp.appDeptName;
						      				}},
      				 {field : 'takeoverUserName',title : '接收人',width : 120,align : 'center',
			        	 formatter: function(value,row,index){
			        		 return row.ppeTurnoverApp.takeoverUserName;
		      				}},
		            {field : 'takeoverDeptName',title : '接收部门',width : 120,align : 'center',
							        	 formatter: function(value,row,index){
							        		 return row.ppeTurnoverApp.takeoverDeptName;
						      				}},
	                {field : 'ppeTotalAmt',title : '合计金额(元)',width : 80,align : 'center',
									        	 formatter: function(value,row,index){
									        		 return row.ppeTurnoverApp.ppeTotalAmt;
								      				}},
	                {field : 'ppeQty',title : '资产总数量',width : 80,align : 'center',
									        	 formatter: function(value,row,index){
									        		 return row.ppeTurnoverApp.ppeQty;
								      				}},
	                {field : 'appDate',title : '申请日期',width : 100,align : 'center',
			        	 formatter: function(value,row,index){
			        		 return row.ppeTurnoverApp.appDate;
		      				}},
      				{field : 'concedeDate',title : '交接日期',width : 100,align : 'center',
			        	 formatter: function(value,row,index){
			        		 return row.ppeTurnoverApp.concedeDate;
		      				}},
      				{field : 'concedeReson',title : '交接事由',width : 100,align : 'center',
			        	 formatter: function(value,row,index){
			        		 return row.ppeTurnoverApp.concedeReson;
		      				}},
      				{field : 'concedeAddr',title : '交接地点',width : 100,align : 'center',
			        	 formatter: function(value,row,index){
			        		 return row.ppeTurnoverApp.concedeAddr;
		      				}},
      				{field : 'ppeNo',        title : '资产编号',    width : 120, align:'center' },
    				{field : 'ppeName',      title : '资产名称', width : 120, align:'center'},
    				{field : 'model',    title : '资产规格',    width : 120, align:'center' },
    				{field : 'qty',    title : '资产数量',    width : 80, align:'center' },
    				{field : 'ppeAmt',    title : '单价',    width : 80, align:'center' },
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
					                {field : 'caozuo',title : '操作',width :180,align : 'center',formatter:function(value,row,index){
					                	var result = ""; 
									    if (row.ppeTurnoverApp.assistant == null || row.ppeTurnoverApp.assistant == "") {
											result += "<a href='javascript:void(0);' onclick='singForTask("+row.ppeTurnoverApp.taskID+");'>签收任务</a>　　";
										}else{
											result += "<a href='javascript:void(0);' onclick='saveTask("+index+");'>办理任务</a>　　";
										} 
									    result += "<a href='javascript:void(0);' onclick='checkHistoryOpinions("+ index +");'>查看审批意见</a>　　";
										return result;
					                }}
					              ] ],toolbar:'#tb',
					              onLoadSuccess:function(data){
					            	  var rows = data.rows;
					                  var mergeMap = {};
					                  if(rows){
					                  	for(var i=0;i<rows.length;i++){
					                  		var appNo = rows[i].appNo
					                  		if( appNo in mergeMap ){
					                  			mergeMap[appNo].rowspan++;
					                  		}else{
					                  			mergeMap[appNo]={"index":i,"rowspan":1}
					                  		}
					                  	}
					                  }
					                  for(var i in mergeMap){
					                  	$(this).datagrid('mergeCells',{
					                          index: mergeMap[i].index,
					                          field: 'appNo',
					                          rowspan: mergeMap[i].rowspan
					                      });
					                  	$(this).datagrid('mergeCells',{
					                          index: mergeMap[i].index,
					                          field: 'name',
					                          rowspan: mergeMap[i].rowspan
					                      });
					                      $(this).datagrid('mergeCells',{
					                          index: mergeMap[i].index,
					                          field: 'appDeptName',
					                          rowspan: mergeMap[i].rowspan
					                      });
					                      $(this).datagrid('mergeCells',{
					                          index: mergeMap[i].index,
					                          field: 'takeoverUserName',
					                          rowspan: mergeMap[i].rowspan
					                      });
					                      $(this).datagrid('mergeCells',{
					                          index: mergeMap[i].index,
					                          field: 'takeoverDeptName',
					                          rowspan: mergeMap[i].rowspan
					                      });
					                      $(this).datagrid('mergeCells',{
					                          index: mergeMap[i].index,
					                          field: 'ppeTotalAmt',
					                          rowspan: mergeMap[i].rowspan
					                      });
					                      $(this).datagrid('mergeCells',{
					                          index: mergeMap[i].index,
					                          field: 'ppeQty',
					                          rowspan: mergeMap[i].rowspan
					                      });
					                      $(this).datagrid('mergeCells',{
					                          index: mergeMap[i].index,
					                          field: 'appDate',
					                          rowspan: mergeMap[i].rowspan
					                      });
					                      $(this).datagrid('mergeCells',{
					                          index: mergeMap[i].index,
					                          field: 'concedeDate',
					                          rowspan: mergeMap[i].rowspan
					                      });
					                      $(this).datagrid('mergeCells',{
					                          index: mergeMap[i].index,
					                          field: 'concedeReson',
					                          rowspan: mergeMap[i].rowspan
					                      });
					                      $(this).datagrid('mergeCells',{
					                          index: mergeMap[i].index,
					                          field: 'concedeAddr',
					                          rowspan: mergeMap[i].rowspan
					                      });
					                      $(this).datagrid('mergeCells',{
					                          index: mergeMap[i].index,
					                          field: 'caozuo',
					                          rowspan: mergeMap[i].rowspan
					                      });
					                      $(this).datagrid('mergeCells',{
					                          index: mergeMap[i].index,
					                          field: 'procStatus',
					                          rowspan: mergeMap[i].rowspan
					                      });
					                  }
					                  $(this).datagrid("doCellTip",{'max-width':'100px'});
					              }
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
/* 		//签收任务
		function holdWorkTask(index){
			var row = getRowData(index);
			$.ajax({
				type:"POST",
				url:"ppeTurnoverAppController/holdWorkTask.do",
				data:{"taskID" : row.ppeTurnoverApp.taskID},
				dataType:"JSON",
				success:function(rsp){
					if(rsp.status){
						$.messager.alert(rsp.title,rsp.message,'info');
					}else{
						$.messager.alert(rsp.title,rsp.message,'warning');
					}
					$grid.datagrid('reload');
				}
			});
		} */
		//办理任务
		function handleTaskDialog(index){
			$row  = getRowData(index).ppeTurnoverApp;
			$.ajax({
				type:"POST",
				url:"workflowAction/findTaskFormKeyByTaskId.do",
				data:{"taskId":$row.taskID},
				success:function(jspName){
					 $("#addWindow").dialog({
							title:'办理任务',
							width:800,
							height:500,
							modal:true,
							href:"jsp/ad/ppeTurnover/"+jspName
					 }); 
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
		
		
	</script>
  </head>
  <body>
      <div data-options="region:'center',border : false">
  		<div class="well well-small" style="margin-left: 5px;margin-top: 5px">
				业务管理-->贷款业务管理-->代办任务
		</div>
		<!-- 新增弹框 -->
		<div id="addWindow"></div>
		<table id="dg"></table>
	    <div id="imageDialog"  class="easyui-dialog" title="流程图片" data-options="border:false,closed:true,fit:true">
			<img id="image" src="" >
		</div>
  	</div>	
  </body>
</html>
