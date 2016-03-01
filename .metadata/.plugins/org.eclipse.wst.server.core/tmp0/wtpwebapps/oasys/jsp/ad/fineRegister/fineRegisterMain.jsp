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
	<script type="text/javascript">
			var $dg;
			var $grid;
			var $row;
			var rows;
			var rowIndex;
			var rowdata;
			var fineType;
			var ptType;
			
			function formatterdate(val, row) {
				
				if (val != null) {
				var date = new Date(val);
				return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
				+ date.getDate();;
				}
			}
			

			fineType = [{
				"id":1, 
				"text":"受罚部门"
				},{ 
				"id":2, 
				"text":"受罚人" 
				}] ;
			//加载受罚类型
			ptType = [{
				"id":1, 
				"text":"未带工牌"
				},{ 
				"id":2, 
				"text":"未按公司要求着装" 
				},{ 
				"id":3, 
				"text":"办公桌摆放凌乱" 
				},{ 
				"id":4, 
				"text":"公共区吸烟" 
				},{ 
				"id":5, 
				"text":"上班时间看电影、睡觉" 
				},{ 
				"id":6, 
				"text":"其他" 
				}];
			/*展示数据*/
		 	$(function() {
		 		$('#fineInfo').combobox({
					data:ptType,
					valueField:'id',
					textField:'text',
					editable :false
		 		});
		 		$('#fineType').combobox({
					data:fineType,
					valueField:'id',
					textField:'text',
					editable :false,
					onSelect:function(n,o){
						if(n.id==1){
							$("#fineUserDept").combogrid("setValue",null);
							$("#fineUserDept").combogrid({    
							    panelWidth:250,  
							    mode: 'remote',   
							    idField:'id',    
							    textField:'name',    
							    url:"penaltyNoticeReg/loadFineInfo.do?id="+n.id,
							    columns:[[
							        {field:'name',width:140}
							    ]]
							});	
						}else{
							$("#fineUserDept").combogrid("setValue",null);
							$("#fineUserDept").combogrid({    
							    panelWidth:250,  
							    mode: 'remote',   
							    idField:'userId',    
							    textField:'name',    
							    url:"penaltyNoticeReg/loadFineInfo.do?id="+n.id,
							    columns:[[
							        {field:'name',width:140}
							    ]]
							});	
						}
					}
		 		});
		 		
		 		$(window).resize(function(){  
		            $("#dg").datagrid({  
		            	height: $(window).height()-105
		            });                
		        });
		 		//加载名片申请数据
				 $dg = $("#dg");
				 $grid=$dg.datagrid({
					url : "penaltyNoticeReg/index.do",
					width : 'auto',
					height : $(this).height()-105,
					pagination:true,
					rownumbers:true,
					border:false,
					singleSelect:false,
					nowrap:true,//如果为true，则在同一行中显示数据。设置为true可以提高加载性能。
					pageSize:10,
					pageList:[10,20,30,40],
					remoteSort:false,//定义是否从服务器对数据进行排序。
					striped:true,//是否显示斑马线
					columns : [ [ 
					              {field : 'pnrId',        title : '编号',    width : parseInt($(this).width()*0.04), align:'center',
					            	  formatter : function(value, row, index) {
											return "<a href=\"javascript:void(0)\" onclick=\"toInfo("+ index + ");\">" + value + "</a>";
										}
					              },
					              {field : 'pt',        title : '受罚对象',    width : parseInt($(this).width()*0.1), align:'center' },
					              {field : 'ptType',    title : '受罚对象类型',   width :parseInt($(this).width()*0.1), align:'center' },
					              {field : 'penaltyDate',      title : '受罚日期', width : parseInt($(this).width()*0.2), align:'center',formatter : formatterdate},
						          {field : 'penaltyReson',    title : '受罚原因',    width : parseInt($(this).width()*0.16), align:'center' },
						          {field : 'penaltyAMT',    title : '罚款金额(元)',    width : parseInt($(this).width()*0.1), align:'center' },
						          {field : 'signDept',    title : '罚款单签发部门编号',    width : parseInt($(this).width()*0.15), align:'center' },
						          {field : 'remark',    title : '备注',    width : parseInt($(this).width()*0.1), align:'center' }
					              ] ],
		              toolbar:'#tb',
		              onClickCell:function(rowIndex, field, value){
		            	  $(this).datagrid("selectRow","rowIndex");
		              }
				});
			}); 
		 	
		//执行高级查询
		function doSearch(){
			$("#dg").datagrid("load",{
				beginTime:$("#beginTime").datebox("getValue"),
				endTime:$('#endTime').datebox('getValue'),
				fineInfo:$('#fineInfo').combogrid("getValue"),
				fineType:$('#fineType').combogrid("getValue"),
				fineUserDept:$('#fineUserDept').combogrid("getValue")
			});
		}

		//重置条件
		function clearAdvancedQueryConditions(){
			//1、清空高级查询各组件内容
			$("#searchForm").form("clear");
			//2、datagrid重新加载
			$("#dg").datagrid("load",{});
		}
		
		/*添加*/
		function toAdd(){
			rowdata=null;
			$('#addWindow').dialog({    
			    title: '新增',    
			    width: 920,    
			    height:$(this).height()*0.7,    
			    closed: false,    
			    cache: false, 
			    resizable:true,
			    href: 'jsp/ad/fineRegister/fineRegisterAdd.jsp',    
			    modal: true,
			    onClose : function(){
			    	$("#dg").datagrid("reload");
			    },
			    buttons : [ {
					text : '关闭',
					iconCls : 'icon-cancel',
					handler : function() {
						$('#addWindow').dialog('close');
						$("#dg").datagrid("reload");
					}
				}]
			});
		}

		/*详情*/
		function toInfo(index){
			 rowdata = getRowData(index);//获取选中的记录
			 if(rowdata==null){
					$.messager.alert("提示","请选择至少一条记录!","warning");
			 }else{
				$('#Info').dialog({    
				    title: '详情',    
				    width: 920,    
				    height: $(this).height()*0.7,    
				    closed: false,    
				    cache: false, 
				    resizable:true,
				    href: 'jsp/ad/fineRegister/FineRegisterInfo.jsp',    
				    modal: true, 
				    onLoad:function(){
				    	$("#investProductInputOrSaveFormInfo").form('load',rowdata);
				    },
				    onClose : function(){
				    	$("#dg").datagrid("reload");
				    	rowdata=null;
				    },
				    buttons : [ {
						text : '关闭',
						iconCls : 'icon-cancel',
						handler : function() {
							$('#Info').dialog('close');
							$("#dg").datagrid("reload");
						}
					}]
				});
			 }
		}
		
		/*删除*/
		function toDel(){
			var ids = new Array();
			 rowdata = $('#dg').datagrid('getSelections');//获取选中的记录
			 if(rowdata==null || rowdata.length<=0){
					$.messager.alert("提示","请选择至少一条记录!","warning");
			 }else{
				 for(var i=0;i<rowdata.length;i++){
						ids.push(rowdata[i].pnrId);
					}
				ids = ids.join(",");
				$.ajax({
					type: "POST",
					url:"penaltyNoticeReg/delPenaltyNoticeReg.do",
					data:"id="+ids,
					async: false,//默认true设置下，所有请求均为异步请求
					cache: true,
					success: function(iJson) {    	    	
					 	if(iJson.status){
		//		 	 	   $("#addWindow").dialog("close");
						   $("#dg").datagrid("reload");//刷新表格
						   $.messager.alert("提示","删除成功!","warning");
						   /* disableForm();//禁用form */
					}
// 						parent.$.messager.show({
// 						title : '提示',
// 						msg : iJson.message,
// 						timeout : 4000 * 2
// 						}); 
					}
				 });	
			 }
		}
		
		/*编辑*/
		function toEdit(){
			 rowdata = $('#dg').datagrid('getSelections');//获取选中的记录
			 if(rowdata==null || rowdata.length<=0){
					$.messager.alert("提示","请选择至少一条记录!","warning");
					return false;
			 }else if(rowdata.length>1){
					$.messager.alert("提示","只能选择一条记录编辑!","warning");
					return false;
			 }else{
				 rowdata = $('#dg').datagrid('getSelected');//获取选中的记录
				$('#addWindow').dialog({    
				    title: '编辑',    
				    width: 920,    
				    height: $(this).height()*0.7,    
				    closed: false,    
				    cache: false, 
				    resizable:true,
					async: false,//默认true设置下，所有请求均为异步请求
				    href: 'jsp/ad/fineRegister/fineRegisterAdd.jsp',    
				    modal: true,
				    onLoad:function(){
				    	$("#investProductInputOrSaveForm").form('load',rowdata);
				    	$("#signDept").combogrid("setValue",rowdata.organizationId);//
				    	$('#penaltyReson').combobox("setValue",rowdata.penaltyResonName);
				    	$('#ptType').combobox("setValue",rowdata.ptTypeName);
				    	$("#pt").combogrid("setValue",rowdata.ptName);//
				    },
				    onClose : function(){
				    	$("#dg").datagrid("reload");
				    	rowdata=null;
				    },
				    buttons : [ {
						text : '关闭',
						iconCls : 'icon-cancel',
						handler : function() {
							$('#addWindow').dialog('close');
							$("#dg").datagrid("reload");
						}
					}]
				});
			 }
		}
		
		// 根据索引获取每一行的基本信息
		function getRowData (index) {
	        if (!$.isNumeric(index) || index < 0) { return undefined; }
	        var rows = $grid.datagrid("getRows");
	        return rows[index];
	    }
	</script>
  </head>
  <body>
      <div data-options="region:'center',border : false">
      	<div class="position" style="margin-top: 5px;">您当前所在位置： 业务管理 -> 行政办公 -> 车辆日常使用登记</div>
      	<div id="tb" style="padding:2px 0">
			<table cellpadding="0" cellspacing="0">
				<tr>
					<td>
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="toAdd();">添加</a>
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="toEdit();">编辑</a>
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="toDel();">删除</a>
					</td>
				</tr>
			</table>
		</div>
		<!-- 高级查询栏区域 -->
  		<div class="well well-small" style="margin-left: 5px;margin-top: 5px">
			<form id="searchForm" action="vehicleUsesReg/index.do" method="post">
				<table cellpadding="0" cellspacing="1" border="0">
					<tr>
						<td>受罚对象类型：</td>
						<td>&nbsp;&nbsp;<input name="fineType" id="fineType" type="text" class="easyui-combogrid" style="width: 170px" readonly="readonly"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td>受罚对象:</td>
						<td><input name="fineUserDept" id="fineUserDept" type="text" class="easyui-combogrid" style="width: 170px" readonly="readonly"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td>受罚原因：&nbsp;&nbsp;</td>
						<td><input name="fineInfo" id="fineInfo" type="text" class="easyui-textbox easyui-combogrid" style="width: 170px" readonly="readonly"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td>
						    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="false" onclick="doSearch();">执行查询</a>&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
						<td>
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-reload" plain="false" onclick="clearAdvancedQueryConditions()">条件重置</a>
						</td>
					</tr>
					<tr>
						<td>受罚日期：&nbsp;&nbsp;</td>
						<td>&nbsp;&nbsp;<input name="beginTime" id="beginTime" type="text" class="easyui-validatebox easyui-datebox" style="width: 170px" readonly="readonly"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td align="center">至：&nbsp;&nbsp;</td>
						<td><input name="endTime" id="endTime" type="text" class="easyui-validatebox easyui-datebox" style="width: 170px" readonly="readonly"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td colspan="6"></td>
					</tr>							
				</table>
			</form>			  			
		</div>
		<!-- 车辆登记数据表格区域 -->
		<table id="dg"  width="100%"></table>
		<!-- 新增弹框 -->
		<div id="addWindow"></div>
		<!-- 详情弹框 -->
		<div id="Info"></div>
  	</div>	
  </body>
</html>
