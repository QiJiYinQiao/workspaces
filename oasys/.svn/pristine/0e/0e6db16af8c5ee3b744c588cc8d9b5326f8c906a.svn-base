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
	<script type="text/javascript" src="jsp/ad/stampTurnover/stampTurnoverJs.js"></script>
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
				//加载印章移交申请的数据
				 $dg = $("#dg");
				$(window).resize(function(){  
		            $("#dg").datagrid({  
		            	height: $(window).height()-100,
		            	width : 'auto'
		            });                
		        });				
				 $grid=$dg.datagrid({
					url : "stampTurnoverAppController/findAllStampTurnover.do",
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
								  {field : 'staId',        title : '编号',    width :0, align:'center',
									        	 formatter: function(value,row,index){
									        		 return row.stampTurnover.staId;
								      				}},
								  {field : 'appNo',        title : '申请编号',    width : parseInt($(this).width()*0.07), align:'center',
									        	 formatter: function(value,row,index){
									        		 return row.stampTurnover.appNo;
								      				}},
								  {field : 'userName',        title : '申请人',    width : parseInt($(this).width()*0.05), align:'center',
									        	 formatter: function(value,row,index){
									        		 return row.stampTurnover.userName;
								      				}},
								  {field : 'orgName',        title : '申请部门',    width : parseInt($(this).width()*0.05), align:'center',
									        	 formatter: function(value,row,index){
									        		 return row.stampTurnover.orgName;
								      				}},
								  {field : 'appDate',        title : '申请时间',    width : parseInt($(this).width()*0.05), align:'center',
											        	 formatter: function(value,row,index){
											        		 return row.stampTurnover.appDate;
										      				}},			
								  {field : 'receiverUserName',        title : '接收人',width : parseInt($(this).width()*0.05), align:'center',
						        	 formatter: function(value,row,index){
						        		 return row.stampTurnover.receiverUserName;
					      				}},
								  {field : 'receiverOrgName',        title : '接收部门',    width : parseInt($(this).width()*0.05), align:'center',
									        	 formatter: function(value,row,index){
									        		 return row.stampTurnover.receiverOrgName;
								      				}},
								  {field : 'receiverDate',        title : '接收时间',    width : parseInt($(this).width()*0.05), align:'center',
											        	 formatter: function(value,row,index){
											        		 return row.stampTurnover.receiverDate;
								   }},		
								   
								 /*  {field : 'superviserUserName',        title : '监交人',    width : parseInt($(this).width()*0.05), align:'center',
						        	 formatter: function(value,row,index){
						        		 return row.stampTurnover.superviserUserName;
					      				}},
								  {field : 'superviserOrgName',        title : '监交部门',    width : parseInt($(this).width()*0.05), align:'center',
									        	 formatter: function(value,row,index){
									        		 return row.stampTurnover.superviserOrgName;
								      				}}, 
								  {field : 'superviserDate',        title : '监交时间',    width : parseInt($(this).width()*0.05), align:'center',
											        	 formatter: function(value,row,index){
											        		 return row.stampTurnover.turnoverDate;
								   }},	*/				
								   
								  {field : 'turnoverDate',        title : '移交日期',    width : parseInt($(this).width()*0.05), align:'center',
						        	 formatter: function(value,row,index){
						        		 return row.stampTurnover.turnoverDate;
					      				}},							
			    				{field : 'stampName',    title : '印章名称',    width : parseInt($(this).width()*0.06), align:'center' },
			    				{field : 'turnoverReson',    title : '印章移交原因',    width : parseInt($(this).width()*0.08), align:'center' },
			    				{field : 'isGiveback',    title : '是否归还',    width : parseInt($(this).width()*0.04), align:'center',
			    					formatter: function(value,row,index){
			    						var isGiveBackStr="";
			    	       		 		if(row.isGiveback == '0'){
			    	       		 			isGiveBackStr="是";
			    	       		 		}else{
			    	       		 			isGiveBackStr="否";
			    	       		 		}
			    	       		 		return  isGiveBackStr;
			    	       		 	}
			    				},
			    				{field : 'givebackDatetime',    title : '归还时间',    width : parseInt($(this).width()*0.06), align:'center',formatter: 
			    					function(value,row,index){
			    					if(value != null && value.length >= 10){
			    						return value.substring(0,10);
			    					}
			    			}  }, 
			    				{field : 'sqzt',    title : '申请状态',    width : parseInt($(this).width()*0.06), align:'center',
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
						          {field : 'caozuo',    title : '操作',    width : parseInt($(this).width()*0.11), align:'center',
						        	  formatter: function(value,row,index){
						        		  	var result = ""; 
											if (row.stampTurnover.procStatus == 1) {
												result += "<a href='javascript:void(0);' onclick='toSaveOrUpdateStampTurnover("+ row.stampTurnover.staId + ");'>编辑</a>&nbsp;&nbsp;";
												result += "<a href='javascript:void(0);' onclick='deleteStampTurnoverApp("+ index + ");'>删除</a>&nbsp;&nbsp;";
												result += "<a href='javascript:void(0);' onclick='sumitLoanOrder("+index+");'>提交申请</a>　";
											} else if (row.stampTurnover.procStatus == 3 || row.stampTurnover.procStatus == 5) {
												result += "<a href='javascript:void(0);' onclick='showInvestProductDetails("+ index + ")';>查看审批意见</a>　"
											} else {
												result += "<a href='javascript:void(0);' onclick='showInvestProductDetails("+ index + ");'>查看审批意见</a>　　"
												result += "<a href='javascript:void(0);' onclick='showImage("+index+");'>查看当前流程图</a>"
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
				 $('#dg').datagrid("doCellTip",{'max-width':'100px'});
				 $('#dg').datagrid('hideColumn','staId'); 
				$('#procStatus').combobox({
					data:procStateJson,
					valueField:'id',
					textField:'text'
				});
			});  
		 	
		 	
		 	/** 删除印章移交申请  **/
			function deleteStampTurnoverApp(index){
				var selectedRow = getRowData(index);
		    	$.messager.confirm('删除', '执行删除后，数据将不可恢复,是否执行?', function(d) {
		    		if (d) {
		    			$.ajax( {
		    				type : "POST",
		    				url : 'stampTurnoverAppController/delStampTurnover.do',
		    				data : "appNo="+selectedRow.stampTurnover.appNo,
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


		
		/* 动态显示弹出的Dialog的标题,显示"添加"或者"修改"报废申请*/
		function changeMyTitle(index){
			if(null == index || '' == index){
				return '添加印章移交申请单';					
			}else{
				return '修改印章移交申请单';
			}
		}
		
	

		// 提交申请
		function sumitLoanOrder(index){
			var selectedRow = getRowData(index);
			$.ajax( {
				type : "POST",
				url : 'stampTurnoverAppController/findAttachCount.do',
				data : "appNo="+selectedRow.stampTurnover.appNo,
				dataType:'JSON',
				success : function(iJson) {		
					if(iJson == 0){
						jQuery.messager.alert('提示:','请添加印章项','warning'); 
					}else{
						$.messager.confirm('提交', '是否提交所选择的申请?', function(d) {
				    		if (d) {
				    			$.ajax( {
				    				type : "POST",
				    				url : 'stampTurnoverAppController/startProcessStampTurnover.do',
				    				data : "staId="+selectedRow.stampTurnover.staId,
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
				}
			});
		}
		
		/** 点击按钮，新增或者修改报废申请 **/			
		function toSaveOrUpdateStampTurnover(staId){
			$dialog=$("<div></div>").dialog({
				/* 动态显示Dialog的标题	*/
				width : 915,
				height : 650,					
				title : changeMyTitle(staId),
				href : "stampTurnoverAppController/toAddStampTurnoverApp.do?staId="+ staId,
				modal:true,
				resizable:false,
				iconCls:'icon-add',
				closed: false,
				onClose:function(){
					$$row=null;
			    	$(this).dialog('destroy');
				},
				buttons : [{
					text : '关闭',
					iconCls : 'icon-cancel',
					handler : function() {
						$dialog.dialog('close');
						$("#dg").datagrid("reload");
					}
				}]
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
			<a id="id4ExportReports" class="easyui-linkbutton" data-options="iconCls:'icon-add'" plain="true" onclick="toSaveOrUpdateStampTurnover('');">添加</a>
		</div>
		<!-- 表格区域 -->
		<table id="dg"  width="100%"></table>
  	</div>	
  	<div id="imageDialog"  class="easyui-dialog" title="流程图片" data-options="border:false,closed:true,fit:true">
	<img id="image" src="" >
	</div>
  </body>
</html>
