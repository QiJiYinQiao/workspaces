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
	<style type="text/css">
		a{
			text-decoration:none;
		}
	</style>
	<script type="text/javascript" src="jsp/ad/stampTurnover/stampTurnoverJs.js"></script>
	<script type="text/javascript">
			//声明一个全局变量row
			//该变量可以在基于此Main页面上的弹出页面中使用，比如optionList.jsp页面。
			var $dg;
			var $grid;
			var $selRow;
			var $$row;
			var $dialog;
			<%String definitionKey=request.getParameter("key");%>//流程变量
		 	$(function() {
				//加载印章移交申请的数据
				 $dg = $("#dg");
				$(window).resize(function(){  
		            $("#dg").datagrid({  
		            	height: $(window).height()-100,
		            	width : 'auto'
		            });                
		        });				
				 $grid=$dg.datagrid({
					url : "stampTurnoverAppController/findStampTurnoverTask.do?definitionKey=<%=definitionKey%>",
					title: "印章移交申请",
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
								  {field : 'staId',        title : '编号',    width : $(this).width * 0.1, align:'center',
									        	 formatter: function(value,row,index){
									        		 return row.stampTurnover.staId;
								      				}},
								  {field : 'taskID',        title : 'TASK_ID',    width : $(this).width * 0.1, align:'center',
								        	 formatter: function(value,row,index){
								        		 return row.stampTurnover.taskID;
							      				}},
							 	 {field : 'formKey',        title : 'FORM_KEY',    width : 60, align:'center' ,
									        	 formatter: function(value,row,index){
									        		 return row.stampTurnover.formKey;
								      				}},								      				
								  {field : 'appNo',        title : '申请编号',    width : $(this).width * 0.1, align:'center',
									        	 formatter: function(value,row,index){
									        		 return row.stampTurnover.appNo;
								      				}},
								  {field : 'userName',        title : '申请人',    width : $(this).width * 0.1, align:'center',
									        	 formatter: function(value,row,index){
									        		 return row.stampTurnover.userName;
								      				}},
								  {field : 'orgName',        title : '申请部门',    width : $(this).width * 0.1, align:'center',
									        	 formatter: function(value,row,index){
									        		 return row.stampTurnover.orgName;
								      				}},
								  {field : 'appDate',        title : '申请时间',    width : $(this).width * 0.1, align:'center',
											        	 formatter: function(value,row,index){
											        		 return row.stampTurnover.appDate;
										      				}},			
								  {field : 'receiverUserName',        title : '接收人',    width : $(this).width * 0.1, align:'center',
						        	 formatter: function(value,row,index){
						        		 return row.stampTurnover.receiverUserName;
					      				}},
								  {field : 'receiverOrgName',        title : '接收部门',    width : $(this).width * 0.1, align:'center',
									        	 formatter: function(value,row,index){
									        		 return row.stampTurnover.receiverOrgName;
								      				}},
								  {field : 'receiverDate',        title : '接收时间',    width : $(this).width * 0.1, align:'center',
											        	 formatter: function(value,row,index){
											        		 return row.stampTurnover.receiverDate;
								   }}/* ,		
								   
								  {field : 'superviserUserName',        title : '监交人',    width : $(this).width * 0.1, align:'center',
						        	 formatter: function(value,row,index){
						        		 return row.stampTurnover.superviserUserName;
					      				}},
								  {field : 'superviserOrgName',        title : '监交部门',    width : $(this).width * 0.1, align:'center',
									        	 formatter: function(value,row,index){
									        		 return row.stampTurnover.superviserOrgName;
								      				}} 
								  {field : 'superviserDate',        title : '监交时间',    width : $(this).width * 0.1, align:'center',
											        	 formatter: function(value,row,index){
											        		 return row.stampTurnover.superviserDate;
								   }},*/,					
								   
								  {field : 'turnoverDate',        title : '移交日期',    width : $(this).width * 0.1, align:'center',
						        	 formatter: function(value,row,index){
						        		 return row.stampTurnover.turnoverDate;
					      				}},							
			    				{field : 'stampName',    title : '印章名称',    width : 60, align:'center' },
			    				{field : 'turnoverReson',    title : '印章移交原因',    width : 60, align:'center' },
			    				{field : 'isGiveback',    title : '是否归还',    width : 875*0.1, align:'center',
			    					formatter: function(value,row,index){
			    						var isGiveBackStr="";
			    	       		 		if(row.isGiveback == 0){
			    	       		 			isGiveBackStr="是";
			    	       		 		}else{
			    	       		 			isGiveBackStr="否";
			    	       		 		}
			    	       		 		return  isGiveBackStr;
			    	       		 	}
			    				},
			    				{field : 'givebackDatetime',    title : '归还时间',    width : 875*0.1, align:'center',formatter: 
			    					function(value,row,index){
			    					if(value != null && value.length >= 10){
			    						return value.substring(0,10);
			    					}
			    				} }, 
			    				{field : 'sqzt',    title : '申请状态',    width : $(this).width * 0.1, align:'center',
						        	 formatter: function(value,row,index){
						        		 var result = "";
											if (row.stampTurnover.procStatus == 1) {
												result = "初始状态";
											} else if (row.stampTurnover.procStatus == 3) {
												result = "审批完成";
											} else if (row.stampTurnover.procStatus == 5) {
												result = "审批撤销";
											} else {
												result = "审批中";
											}
											return result;
					      				}
						        	 },
						          {field : 'caozuo',    title : '操作',    width : $(this).width * 0.1, align:'center',
						        	  formatter: function(value,row,index){
						        		  	var result = ""; 
						        		  	result += "<a href='javascript:void(0);' onclick='showInvestProductDetails("+ index + ");'>查看审批意见</a>　　"
						        		  	if (row.stampTurnover.assistant == null || row.stampTurnover.assistant == "") {
												result += "<a href='javascript:void(0);' onclick='singForTask("+row.stampTurnover.taskID+");'>签收任务</a>　　";
											}else{
												result += "<a href='javascript:void(0);' onclick='saveTask("+index+");'>受理任务</a>　　";
											}
											return result;
					      				}
						          }
					              ] ],
		              toolbar:'#tb',
		              onClickCell:function(rowIndex, field, value){
		            	  $(this).datagrid("selectRow","rowIndex");
		              },onLoadSuccess:function(data){
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
		                          field: 'userName',
		                          rowspan: mergeMap[i].rowspan
		                      });
		                      $(this).datagrid('mergeCells',{
		                          index: mergeMap[i].index,
		                          field: 'orgName',
		                          rowspan: mergeMap[i].rowspan
		                      });
		                      $(this).datagrid('mergeCells',{
		                          index: mergeMap[i].index,
		                          field: 'appDate',
		                          rowspan: mergeMap[i].rowspan
		                      });
		                      $(this).datagrid('mergeCells',{
		                          index: mergeMap[i].index,
		                          field: 'turnoverDate',
		                          rowspan: mergeMap[i].rowspan
		                      });		                      
		                      $(this).datagrid('mergeCells',{
		                          index: mergeMap[i].index,
		                          field: 'superviserOrgName',
		                          rowspan: mergeMap[i].rowspan
		                      });
		                      $(this).datagrid('mergeCells',{
		                          index: mergeMap[i].index,
		                          field: 'superviserUserName',
		                          rowspan: mergeMap[i].rowspan
		                      });
		                      $(this).datagrid('mergeCells',{
		                          index: mergeMap[i].index,
		                          field: 'superviserDate',
		                          rowspan: mergeMap[i].rowspan
		                      });
		                      
		                      
		                      $(this).datagrid('mergeCells',{
		                          index: mergeMap[i].index,
		                          field: 'receiverOrgName',
		                          rowspan: mergeMap[i].rowspan
		                      });
		                      $(this).datagrid('mergeCells',{
		                          index: mergeMap[i].index,
		                          field: 'receiverUserName',
		                          rowspan: mergeMap[i].rowspan
		                      });
		                      $(this).datagrid('mergeCells',{
		                          index: mergeMap[i].index,
		                          field: 'receiverDate',
		                          rowspan: mergeMap[i].rowspan
		                      });
		                      
		                      
		                      $(this).datagrid('mergeCells',{
		                          index: mergeMap[i].index,
		                          field: 'caozuo',
		                          rowspan: mergeMap[i].rowspan
		                      });
		                      $(this).datagrid('mergeCells',{
		                          index: mergeMap[i].index,
		                          field: 'sqzt',
		                          rowspan: mergeMap[i].rowspan
		                      });
		                  }
		              }
				});
				 $('#dg').datagrid('hideColumn','staId');
				$('#dg').datagrid('hideColumn','taskID');
 				$('#dg').datagrid('hideColumn','formKey');
				 $('#dg').datagrid("doCellTip",{'max-width':'100px'});				 
			});  
		 	
		 	function saveTask(index){
				var selectedRow = getRowData(index);
				$selRow = selectedRow;
				$$row = $selRow.stampTurnover;
				var formKey = selectedRow.stampTurnover.formKey;
				$dialog=$("<div></div>").dialog({
					/* 动态显示Dialog的标题	*/
					width : 850,
					height : $(this).height()*0.7,					
					title : '受理任务',
					href : formKey,
				    onLoad:function(){
				    	var taskForm = $("#taskForm");
				    	taskForm.form("load",$$row);
				    	$("#taskForm #businessID").val(selectedRow.stampTurnover.staId);
				    	$("#taskForm #taskID").val(selectedRow.stampTurnover.taskID);
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
			
		//签收任务
		function singForTask(taskID){
   			$.ajax( {
   				type : "POST",
   				url : 'stampTurnoverAppController/signForTask.do',
   				data : "taskID="+taskID,
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
		
	</script>
  </head>
  <body>
      <div data-options="region:'center',border : false">
      	<div class="position" style="margin-top: 5px;">您当前所在位置： 业务管理 -> 行政办公 -> 印章移交申请</div>
		<!-- 高级查询栏区域 -->
  		<div class="well well-small" style="margin-left: 5px;margin-top: 5px">
			<form id="searchForm" action="stampTurnoverAppController/findAllStampTurnover.do" method="post">
				<table cellpadding="0" cellspacing="1" border="0">
					<tr>
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
		<!-- 表格区域 -->
		<table id="dg"  width="100%"></table>
  	</div>	
  </body>
</html>
