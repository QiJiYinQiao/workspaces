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
	<script type="text/javascript" src="jsp/ad/ppeScrap/ppeScrapJs.js"></script>
	<style type="text/css">
		a{
			text-decoration:none;
		}
	</style>
	<script type="text/javascript">
			 	$(function() {
				//加载固定资产报废申请的数据
				$dg = $("#dg");
				$(window).resize(function(){  
		            $("#dg").datagrid({  
		            	height: $(window).height()-100,
		            	width : 'auto'
		            });                
		        });
				 $grid=$dg.datagrid({
					url : "ppeScrapAppController/findAllppeScrap.do",
					title: "固定资产报废申请",
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
					pageSize:10,
					pageList:[10,20,30,40],
					columns : [ [
								  {field : 'psaId',        title : '编号',    width : 0, align:'center',
									        	 formatter: function(value,row,index){
									        		 return row.ppeApp.psaId;
								      				}},
								  {field : 'appNo',        title : '申请编号',    width : parseInt($(this).width() * 0.1), align:'center',
									        	 formatter: function(value,row,index){
									        		 return row.ppeApp.appNo;
								      				}},
								  {field : 'userName',        title : '申请人',    width : parseInt($(this).width() * 0.04), align:'center',
									        	 formatter: function(value,row,index){
									        		 return row.ppeApp.userName;
								      				}},
								  {field : 'orgName',        title : '申请部门',    width : parseInt($(this).width() * 0.06), align:'center',
									        	 formatter: function(value,row,index){
									        		 return row.ppeApp.orgName;
								      				}},
								  {field : 'appDate',        title : '申请时间',    width : parseInt($(this).width() * 0.07), align:'center',
											        	 formatter: function(value,row,index){
											        		 return row.ppeApp.appDate;
										      				}},
			    				{field : 'ppeNo',        title : '资产编号',    width : parseInt($(this).width() * 0.06), align:'center' },
			    				{field : 'ppeName',      title : '资产名称', width : parseInt($(this).width() * 0.07), align:'center'},
			    				{field : 'ppeModel',    title : '资产规格',    width : parseInt($(this).width() * 0.06), align:'center' },
			    				{field : 'buyDate',    title : '购买时间',    width : parseInt($(this).width() * 0.06), align:'center' },
			    				{field : 'usedYear',    title : '使用年限(年)',    width : parseInt($(this).width() * 0.07), align:'center' },
			    				{field : 'ppeGross',    title : '资产原值(元)',    width : parseInt($(this).width() * 0.07), align:'center' },
			    				{field : 'ppeNet',    title : '资产净值(元)',    width : parseInt($(this).width() * 0.07), align:'center' },
			    				{field : 'ppeSalvageVal',    title : '资产残值(元)',    width : parseInt($(this).width() * 0.07), align:'center' },
			    				{field : 'scrapReson',    title : '资产报废原因',    width : parseInt($(this).width() * 0.075), align:'center' },												      				
						         {field : 'sqzt',    title : '申请状态',    width : parseInt($(this).width() * 0.055), align:'center',
						        	 formatter: function(value,row,index){
						        		 var result = "";
											if (row.ppeApp.procStatus == 1) {
												result = "初始状态";
											} else if (row.ppeApp.procStatus == 3) {
												result = "审批完成";
											} else if (row.ppeApp.procStatus == 5) {
												result = "审批撤销";
											} else {
												result = "审批中";
											}
											return result;
					      				}
						        	 },
						          {field : 'caozuo',    title : '操作',    width : parseInt($(this).width() * 0.12), align:'center',
						        	  formatter: function(value,row,index){
						        		  	var result = ""; 
											if (row.ppeApp.procStatus == 1) {
												result += "<a href='javascript:void(0);' onclick='toSaveOrUpdatePpeScrap("+ row.ppeApp.psaId + ");'>编辑</a>&nbsp;&nbsp;&nbsp;";
												result += "<a href='javascript:void(0);' onclick='deletePpeScrapApp("+ index + ");'>删除</a>&nbsp;&nbsp;&nbsp;";
												result += "<a href='javascript:void(0);' onclick='sumitLoanOrder("+index+");'>提交申请</a>";
											} else if (row.ppeApp.procStatus == 3 || row.ppeApp.procStatus == 5) {
												result += "<a href='javascript:void(0);' onclick='showInvestProductDetails("+ index + ")';>查看审批意见</a>&nbsp;&nbsp;&nbsp;"
											} else {
												result += "<a href='javascript:void(0);' onclick='showInvestProductDetails("+ index + ");'>查看审批意见</a>&nbsp;&nbsp;&nbsp;"
												result += "<a href='javascript:void(0);' onclick='showImage("+index+");'>查看流程图</a>"
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
		                  		var appNo = rows[i].ppeApp.appNo
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
	                          field: 'psaId',
	                          rowspan: mergeMap[i].rowspan
	                      });
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
				 $('#dg').datagrid('hideColumn','psaId'); 
				$('#procStatus').combobox({
					data:procStateJson,
					valueField:'id',
					textField:'text'
				});
			});  
		 	
		 	
		 	
		 	/** 删除固定资产报废申请  **/
			function deletePpeScrapApp(index){
				var selectedRow = getRowData(index);
		    	$.messager.confirm('删除', '执行删除后，数据将不可恢复,是否执行?', function(d) {
		    		if (d) {
		    			$.ajax( {
		    				type : "POST",
		    				url : 'ppeScrapAppController/delPPEScrap.do',
		    				data : "appNo="+selectedRow.ppeApp.appNo,
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
		 	
		
		/* 动态显示弹出的Dialog的标题,显示"添加"或者"修改"报废申请*/
		function changeMyTitle(index){
			if(null == index || '' == index){
				return '添加固定资产报废申请单';					
			}else{
				return '修改固定资产报废申请单';
			}
		}
		
		function syncEm(){
			$("#dg").datagrid("load",{syncEm:"true"});
		}

		// 提交申请
		function sumitLoanOrder(index){
			var selectedRow = getRowData(index);
	 		//获取taskID
	 		$.ajax( {
				type : "POST",
				url : 'ppeScrapAppController/findAttachCount.do',
				data : "appNo="+selectedRow.ppeApp.appNo,
				dataType:'JSON',
				success : function(iJson) {		
					if(iJson.status){
						jQuery.messager.alert('提示:','请添加报废申请项','warning'); 
					}else{
						$.messager.confirm('提交', '是否提交所选择的申请?', function(d) {
				    		if (d) {
				    			ajaxLoading("loading...");
				    			$.ajax( {
				    				type : "POST",
				    				url : 'ppeScrapAppController/startProcessPpeScrap.do',
				    				data : "psaId="+selectedRow.ppeApp.psaId,
				    				dataType:'JSON',
				    				success : function(iJson) {		    					    				
				    					if(iJson.status){
				    						doSearch();
			 	    						$("#dg").datagrid("load",{}); 
				    					}
				    					ajaxLoadEnd();
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
		function toSaveOrUpdatePpeScrap(psaId){
			$dialog=$("<div></div>").dialog({
				width : ppeListWidth,
				height : parseInt($(window).height() * 0.7),
				title : '调整固定资产报废项',
				href : "ppeScrapAppController/toAddPPEScrapApp.do?psaId="+ psaId,
				modal:false,
				resizable:true,
				iconCls:'icon-add',
				onClose:function(){
					$$row=null;
					$dialog.dialog('destroy');
					$("#dg").datagrid("reload");
				},
				buttons : [{
					text : '关闭',
					iconCls : 'icon-cancel',
					handler : function() {
						$dialog.dialog('close');
					}
				}],
				closed: false
			}); 
		}
		
		function importExcel(){
			$("#dd").dialog({
				title:'导入Excel',
				width:600,
				height:400,
				closed: false,    
			    modal: true   
			})
		}
		
		//上传并导入excel
		function doImportExcel(){
			var actionurl=$("#formId").attr('action');//提交路径
			$("#formId").form({
				url:actionurl,
				onSubmit:function(){
					$.messager.progress({
						title:'提示',
						msg:'正在导入中...',
						interval:1000
					});
				},
				success : function(result){
					$.messager.progress('close');
					result = $.parseJSON(result);
					if (result.status) {
						$.messager.alert(result.title,result.message);
						$("#formId").form("clear");
						$("#dd").dialog('close');
						$dg.datagrid("reload");
					}else{
						$.messager.alert(result.title,result.message);
					}
				}
			}).submit();
		}
		
	</script>
  </head>
  <body>
      <div data-options="region:'center',border : false">
      	<div class="position" style="margin-top: 5px;">您当前所在位置： 行政办公  &gt; 固定资产报废申请</div>
		<!-- 高级查询栏区域 -->
  		<div class="well well-small" style="margin-left: 5px;margin-top: 5px">
			<form id="searchForm" action="ppeScrapAppController/findAllppeScrap.do" method="post">
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
			<a id="id4ExportReports" class="easyui-linkbutton" data-options="iconCls:'icon-add'" plain="true" onclick="toSaveOrUpdatePpeScrap('');">添加</a>
			<!-- <a id="id4ExportReports" class="easyui-linkbutton" data-options="iconCls:'icon-add'" plain="true" onclick="syncEm();">同步263接口</a>
			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-excel" plain="true" onclick="importExcel();">导入</a> -->
			
		</div>
		<!-- 表格区域 -->
		<table id="dg"  width="100%"></table>
  	</div>	
  	<div id="imageDialog"  class="easyui-dialog" title="流程图片" data-options="border:false,closed:true,fit:true">
	<img id="image" src="" >
	</div>
	

	
			<div id="dd" class="easyui-dialog" closed="true">
			<form id="formId" action="ppeScrapAppController/importDataToDB.do" method="post" enctype="multipart/form-data">
				<table>
				<tr>
					<td>
						<input type="file" id="file" name="file"  size=100/>
					</td>
					<td>
					   	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="doImportExcel()">上传文件</a>
					</td>
				</tr>
				</table>
			</form>
		</div>
	
	
  </body>
</html>
