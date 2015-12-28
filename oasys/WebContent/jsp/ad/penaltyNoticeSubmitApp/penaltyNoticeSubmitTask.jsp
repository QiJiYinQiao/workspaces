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
    <title>罚款申请</title>
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
			var $dg;
			var $grid;
			var $$row;
			var $selRow;
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
				<%
			    String definitionKey=request.getParameter("key");
			    %>
				 $dg = $("#dg");
				 $grid=$dg.datagrid({
					url : "penaltyNoticeSubmitAppController/findPenaltyNoticeSubmitAppTask.do?definitionKey=<%=definitionKey %>",
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
					columns : [ [ 	{field : 'appNo',title : '申请编号',width :120,align : 'center',
			        	 formatter: function(value,row,index){
			        		 return row.penaltyNoticeSubmitApp.appNo;
		      				}},
      				{field : 'appDate',title : '申请日期',width : 100,align : 'center',
			        	 formatter: function(value,row,index){
			        		 return row.penaltyNoticeSubmitApp.appDate;
		      				}},
	                {field : 'userName',title : '申请人',width : 120,align : 'center',
					        	 formatter: function(value,row,index){
					        		 return row.penaltyNoticeSubmitApp.userName;
				      				}},
      				{field : 'ptName',        title : '受罚人',    width : 120, align:'center' },
      				{field : 'ptDeptName',        title : '受罚部门',    width : 120, align:'center' },
    				{field : 'penaltyDate',      title : '受罚日期', width : 120, align:'center'},
      				{field : 'penaltyReson',title : '受罚原因',width : 80,rowspan:2,align : 'center',formatter:function(value,row,index){
	                	if(row.penaltyReson == "1"){
	                		return "未带工牌";
	                	}else if(row.penaltyReson == "2"){
	                		return "未按公司要求着装";
	                	}else if(row.penaltyReson == "3"){
	                		return "办公桌摆放凌乱";
	                	}else if(row.penaltyReson == "4"){
	                		return "公共区吸烟";
	                	}else if(row.penaltyReson == "5"){
	                		return "上班时间看电影、睡觉";
	                	}else
	                		return "其它";
	                	}
	                },
    				{field : 'penaltyResonOther',      title : '其他受罚原因', width : 120, align:'center'},
    				{field : 'penaltyAmt',      title : '罚款金额', width : 120, align:'center'},
    				{field : 'signDeptName',      title : '罚款金额', width : 120, align:'center'},
					                {field : 'procStatus',title : '流程状态',width : 80,align : 'center',formatter:function(value,row,index){
					                	if(row.penaltyNoticeSubmitApp.procStatus == "1"){
					                		return "初始状态";
					                	}else if(row.penaltyNoticeSubmitApp.procStatus == "2"){
					                		return "审批中";
					                	}else if(row.penaltyNoticeSubmitApp.procStatus == "3"){
					                		return "已完成";
					                	}else if(row.penaltyNoticeSubmitApp.procStatus == "4"){
					                		return "已失效";
					                	}else if(row.penaltyNoticeSubmitApp.procStatus == "5"){
					                		return "已撤销";
					                	}else{
					                		return "已拒绝";
					                	}
					                }},
					                {field : 'remark',        title : '备注',    width : 120, align:'center' ,
							        	 formatter: function(value,row,index){
							        		 return row.penaltyNoticeSubmitApp.remark;
						      				}},
				      				{field : 'aa',    title : '操作',    width : $(this).width * 0.1, align:'center',
						        	 	formatter: function(value,row,index){
						        		   	var result = ""; 
											if (row.penaltyNoticeSubmitApp.assistant == null || row.penaltyNoticeSubmitApp.assistant == "") {
												result += "<a href='javascript:void(0);' onclick='singForTask("+row.penaltyNoticeSubmitApp.taskID+");'>签收任务</a>　　";
											}else{
												result += "<a href='javascript:void(0);' onclick='saveTask("+index+");'>办理任务</a>　　";
											}
											result += "<a href='javascript:void(0);' onclick='checkInvestOrderOpinions("+ index +");'>查看审批意见</a>　　";
											return result; 
					      				}
						          }
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
					                          field: 'userName',
					                          rowspan: mergeMap[i].rowspan
					                      });
					                      $(this).datagrid('mergeCells',{
					                          index: mergeMap[i].index,
					                          field: 'appDate',
					                          rowspan: mergeMap[i].rowspan
					                      });
					                      $(this).datagrid('mergeCells',{
					                          index: mergeMap[i].index,
					                          field: 'aa',
					                          rowspan: mergeMap[i].rowspan
					                      });
					                      $(this).datagrid('mergeCells',{
					                          index: mergeMap[i].index,
					                          field: 'remark',
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
				 $('#procStatus').combobox({
						data:procStateJson,
						valueField:'id',
						textField:'text'
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
			
			
			//查看审批意见
			function checkInvestOrderOpinions(index){
				$$row = getRowData(index).penaltyNoticeSubmitApp;
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
			
			//签收任务
			function singForTask(taskID){
				$.messager.confirm('签收任务', '是否确认签收任务?', function(d) {
		    		if (d) {
		    			$.ajax( {
		    				type : "POST",
		    				url : 'penaltyNoticeSubmitAppController/signForTask.do',
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
		    	});		
			}
			
			function saveTask(index){
				var selectedRow = getRowData(index);
				$selRow = selectedRow.penaltyNoticeSubmitApp;
				$$row=$selRow;
				var formKey = selectedRow.penaltyNoticeSubmitApp.formKey;
				$dialog=$("<div></div>").dialog({
					/* 动态显示Dialog的标题	*/
					width : 850,
					height : 450,					
					title : '受理任务',
					href : formKey,
				    onLoad:function(){
				    	var saveOrUpdateForm = $("#taskForm");
				    	saveOrUpdateForm.form("load",$selRow);
				    	$("#taskForm #businessID").val(selectedRow.penaltyNoticeSubmitApp.pnrId);
				    	$("#taskForm #taskID").val(selectedRow.penaltyNoticeSubmitApp.taskID);
				    },
					modal:true,
					resizable:true,
					iconCls:'icon-add',
					closed: false,
					buttons : [{
						text : '关闭',
						iconCls : 'icon-cancel',
						handler : function() {
							$dialog.dialog('close');
							$dialog.dialog('destroy');
					}
				}],onClose:function(){
			    	//刷新列表
			    	$("#dg").datagrid("reload");
			    	//关闭弹窗
			    	$(this).dialog('destroy');
			    }
				});
			}
		</script>
  </head>
  <style>
  .nkframe_position{padding-left:30px;margin-bottom:10px;border-bottom:1px solid #d2e7f8;height:24px;line-height:24px;background:url(extend/nk_position.gif) 5px center no-repeat;font-size:12px;font-weight:normal;}
  </style>
  <body>
      <div data-options="region:'center',border : false">
     <div class="position" style="margin-top: 5px;">您当前所在位置： 业务管理  &gt; 人力资源  &gt; 罚款申请 </div>
     <div class="well well-small" style="margin-left: 5px;margin-top: 5px">
			<form id="searchForm" action="penaltyNoticeSubmitAppController/findPenaltyNoticeSubmitAppList.do" method="post">
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
		
		<table id="dg" title="罚款申请"></table>
		<div id="imageDialog"  class="easyui-dialog" title="流程图片" data-options="border:false,closed:true,fit:true">
			<img id="image" src="" >
		</div>
		<div id="optionsDialog"></div>
  	</div>
	<div id ="dd"></div>
  </body>
</html>
