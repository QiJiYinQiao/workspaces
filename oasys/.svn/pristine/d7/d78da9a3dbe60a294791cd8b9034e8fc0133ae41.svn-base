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
    <title>日志管理</title>
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
				}] ;
			$(function() {
				$(window).resize(function(){  
	                $("#dg").datagrid({  
	                	height: $(window).height()-82  
	                });                
	            });  
				 $dg = $("#dg");
				 $grid=$dg.datagrid({
					/* url : "ppeTurnoverAppController/findAllPpeTurnover2.do", */
					url : "ppeTurnoverAppController/findAllPpeTurnoverApp.do",
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
				    				{field : 'ppeAmtStr',    title : '单价(元)',    width : 80, align:'center' },
				    				{field : 'ppeQty',    title : '资产总数量',    width : 80, align:'center',
							        	 formatter: function(value,row,index){
							        		 return row.ppeTurnoverApp.ppeQty;
						      				} },
				    				{field : 'ppeTotalAmtStr',title : '合计金额(元)',width : 80,align : 'center',
							        	 formatter: function(value,row,index){
							        		 return row.ppeTurnoverApp.ppeTotalAmtStr;
						      				}},
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
					                {field : 'remark',        title : '备注',    width : 120, align:'center' ,
							        	 formatter: function(value,row,index){
							        		 return row.ppeTurnoverApp.remark;
						      				}},
					                {field : 'aa',title : '操作',width :180,align : 'center',formatter:function(value,row,index){
					                	if(row.ppeTurnoverApp.procStatus == "1"){
					             	    	return "<a href='javascript:void(0);' onclick='updRowsOpenDlg("+index+");'>编辑</a>&nbsp;&nbsp;"+"<a href='javascript:void(0);' onclick='delRow("+index+");'>删除</a>&nbsp;&nbsp;"+"<a href='javascript:void(0);' onclick='sumitInvestOrder("+index+");'>提交申请</a>";
					             	    }else if(row.ppeTurnoverApp.procStatus == "2"){
					             	    	return "<a href='javascript:void(0);' onclick='checkInvestOrderOpinions("+ index + ");'>查看审批意见</a>&nbsp;&nbsp;"+"<a href='javascript:void(0);' onclick='showImage("+ index + ");'>查看流程图</a>";
					             	    }else{
					             	    	return "<a href='javascript:void(0);' onclick='checkInvestOrderOpinions("+ index + ");'>查看审批意见</a>";
					             	    }
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
					                          field: 'ppeTotalAmtStr',
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
			
			/**
			 * 提交申请
			 */
			function sumitInvestOrder(index){
				var row = this.getRowData(index);
				if(null==row.ppeTurnoverApp.ppeTotalAmt || ""==row.ppeTurnoverApp.ppeTotalAmt){
					$.messager.alert("提示", "请先完善申请信息!", "warning");
					return;
				}
				$.messager.confirm('确定','是否确定提交申请？',function(flag) {
					if (flag) {
						$.ajax({
								url : "ppeTurnoverAppController/saveStartProcess.do",
								data : {"ptaId" : row.ppeTurnoverApp.ptaId,"appNo":row.ppeTurnoverApp.appNo,"takeoverUser":row.ppeTurnoverApp.takeoverUser},
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
		    				url : 'ppeTurnoverAppController/delPpeTurnover.do',
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
			
			//删除
			function delRows(){
				 var rows = $('#dg').datagrid('getSelections');//获取选中的记录
				 if(rows!=null&&rows.length>=1){
					 var ids = new Array();
					 var appNos=new Array();
					 $.messager.confirm('删除', '删除该记录将不可恢复，确认删除吗?', function(d) {
						 if(d){
							 $.each(rows,function(i,row){
									if (row) {
										var rowIndex = $('#dg').datagrid('getRowIndex', row);
										$('#dg').datagrid('deleteRow', rowIndex);
										ids.push(row.ppeTurnoverApp.ptaId);//将ID放入数组中
										appNos.push(row.ppeTurnoverApp.appNo);
									}
							 });
							 ids = ids.join(",");// 转换为字符串
							 $.ajax({
								    type:'post',
								    url : 'ppeTurnoverAppController/delPpeTurnover.do',
				    				data : "ids="+ids,
									success: function(data){
										$.messager.alert(data.title,data.message,'info');
									},
									error:function(data){
										parent.$.messager.alert(data.title,data.message,'error');
									}
							});
							 appNos = appNos.join(",");
							 $.ajax({
								    type:'post',
								    url : 'ppeTurnoverAppAttachController/deleteByAppNos.do',
				    				data : "appNos="+appNos,
									success: function(data){
										$.messager.alert(data.title,data.message,'info');
									},
									error:function(data){
										$.messager.alert(data.title,data.message,'error');
									}
							});
						 }
					 })
				 }else{
					 $.messager.alert("提示","请至少选择一条记录!","warning");
				 }
			}
			//弹窗修改
			function updRowsOpenDlg(index) {
			var row = getRowData(index);
				if (row) {
					$("#dd").dialog({
						title : '编辑',
						width : 870,
						height : 600,
						modal:true,
						href : "jsp/ad/ppeTurnover/ppeTurnoverForm2.jsp",
						onLoad:function(){
							resetVali();
							var f = $("#baseForm");
							f.form("load", row.ppeTurnoverApp);
							initLinkPeopleGrid();
					      /*   initLinkPeopleGrid(row.ppeTurnoverApp.appNo);
					        $("#sign").val("save");
					        $("#appNo").val(row.ppeTurnoverApp.appNo);
					        $("#oneRow").attr("class",""); */
						},
						onClose:function(){
							$("#dg").datagrid("reload");
						},
						buttons : [ {
							text : '关闭',
							iconCls : 'icon-cancel',
							handler : function() {
								$("#dg").datagrid("reload");
								$("#dd").dialog('close');
							}
						}]
					}); 
				}/* else{
					parent.$.messager.show({
						title :"提示",
						msg :"请选择一行记录!",
						timeout : 1000 * 2
					});
				} */
			}
			//弹窗增加
			function addRowsOpenDlg() {
				$("#dd").dialog({
					title : '添加',
					iconCls:'icon-add',
					width : 870,
					height : 610,
					href : "jsp/ad/ppeTurnover/ppeTurnoverForm2.jsp",
					onLoad:function(){
						resetVali();
					},
					onDestroy:function(){
						$("#dg").datagrid("reload");
					},
					buttons : [ {
						text : '关闭',
						iconCls : 'icon-cancel',
						handler : function() {
							$("#dg").datagrid("reload");
							$("#dd").dialog('close');
						}
					}]
				});
			}
			
			function resetVali(){
				$("#concedeDate").datebox({disabled:false});
				$("#takeoverUserName").combobox({disabled:false});
				$("#takeoverDeptName").combo({disabled:false});
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
			
			//查看流程图
			function showImage(index){
				var row = getRowData(index);
				var src = "ppeTurnoverAppController/showProcessImg.do?ptaId="+row.ppeTurnoverApp.ptaId;
				$('#imageDialog').dialog("open");
				$("#image").attr("src", src);
			}
			
			//
			function checkInvestOrderOpinions(index){
				$$row = getRowData(index).ppeTurnoverApp;
				$("#optionsDialog").dialog({
					/* 动态显示Dialog的标题	*/
					width : 850,
					height : 450,					
					title : "查看审批意见",
					href : "jsp/ad/optionsList.jsp",
					onClose:function(){
						$$row=null;
					},
					modal:true,
					resizable:true,
					iconCls:'icon-add',
					closed: false
				});
			}
		</script>
  </head>
  <style>
  .nkframe_position{padding-left:30px;margin-bottom:10px;border-bottom:1px solid #d2e7f8;height:24px;line-height:24px;background:url(extend/nk_position.gif) 5px center no-repeat;font-size:12px;font-weight:normal;}
  </style>
  <body>
      <div data-options="region:'center',border : false">
     <div class="position" style="margin-top: 5px;">您当前所在位置： 业务管理  &gt; 行政办公  &gt; 固定资产移交 </div>
     <div class="well well-small" style="margin-left: 5px;margin-top: 5px">
			<form id="searchForm" action="ppeTurnoverAppController/findAllPpeTurnoverApp" method="post">
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
						<!-- <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updRowsOpenDlg();">修改</a>
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRows();">删除</a> -->
					</td>
					<!-- <td style="padding-left:2px">
						<input id="searchbox" type="text"/>
					</td> -->
				</tr>
			</table>
		</div>
		
		<table id="dg" title="固定资产移交"></table>
		<div id="imageDialog"  class="easyui-dialog" title="流程图片" data-options="border:false,closed:true,fit:true">
			<img id="image" src="" >
		</div>
		<div id="optionsDialog"></div>
  	</div>
	<div id ="dd"></div>
  </body>
</html>
