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
			
			function createUserNameByDeptNo(){
				$("#deptName").combotree({
					width:171,
					url:"Organization/organizationList.do",
					idFiled:'id',
				 	textFiled:'name',
				 	valueFiled:'id',
				 	parentField:'pid',
				 	onLoadSuccess:function(data){
				 	},
				 	onChange:function(){
				 		$("#user").combogrid('setValue',null);
				 		$("#deptName").val($(this).combotree('getValue'))
				 	   $("#userName").combogrid({    
						    panelWidth:250,  
						    mode: 'remote',   
						    idField:'code',    
						    textField:'text',    
						    url:'callingCard/getUserInfo.do?q='+$("#deptName").val(),
						    columns:[[    
						        {field:'text',title:'用户名',width:120}
						    ]]
						}); 
				 	}
				});
			}
			
			/*展示数据*/
		 	$(function() {
		 		createUserNameByDeptNo();
		 		$(window).resize(function(){  
		            $("#dg").datagrid({  
		            	height: $(window).height()-105
		            });                
		        });
		 		//加载名片申请数据
				 $dg = $("#dg");
				 $grid=$dg.datagrid({
					url : "vehicleUsesReg/index.do",
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
					              {field : 'vurId',        title : '编号',    width : parseInt($(this).width()*0.04), align:'center',
					            	  formatter : function(value, row, index) {
											return "<a href=\"javascript:void(0)\" onclick=\"toInfo("+ index + ");\">" + value + "</a>";
								  }},
					              {field : 'user',        title : '使用人姓名',    width : parseInt($(this).width()*0.1), align:'center' },
					              {field : 'carNo',    title : '车牌号码',   width : parseInt($(this).width()*0.08), align:'center' },
					              {field : 'usesReson',      title : '使用事由', width : parseInt($(this).width()*0.08), align:'center'},
						          {field : 'usesDateTime',    title : '用车时间',    width :parseInt($(this).width()*0.16), align:'center' },
						          {field : 'gvDateTime',    title : '归还时间',    width : parseInt($(this).width()*0.16), align:'center' },
						          {field : 'origin',    title : '始发地',    width : parseInt($(this).width()*0.08), align:'center' },
						          {field : 'destination',    title : '目的地',    width : parseInt($(this).width()*0.08), align:'center' },
						          {field : 'totalAMT',    title : '合计金额',    width : parseInt($(this).width()*0.08), align:'center' },
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
				userName:$('#userName').combogrid("getValue"),
				countAmount:$('#countAmount').val(),
				dept:$('#deptName').combogrid("getValue")
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
			$('#addWindow').dialog({    
			    title: '新增',    
			    width: 920,    
			    height: 400,    
			    closed: false,    
			    cache: false, 
			    resizable:false,
			    href: 'jsp/ad/carRegister/carRegisterAdd.jsp',    
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
				$('#Info').dialog({    
				    title: '详情',    
				    width: 920,    
				    height: 400,    
				    closed: false,    
				    cache: false, 
				    resizable:false,
				    href: 'jsp/ad/carRegister/carRegisterInfo.jsp',    
				    modal: true, 
				    onLoad:function(){
				    	$("#investProductInputOrSaveFormInfo").form('load',rowdata);
				    },
				    onClose : function(){
				    	$("#dg").datagrid("reload");
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
		
		/*删除*/
		function toDel(){
			var ids = new Array();
			 rowdata = $('#dg').datagrid('getSelections');//获取选中的记录
			 if(rowdata==null || rowdata.length<=0){
					$.messager.alert("提示","请选择至少一条记录!","warning");
			 }else{
				 for(var i=0;i<rowdata.length;i++){
						ids.push(rowdata[i].vurId);
					}
				ids = ids.join(",");
				$.ajax({
					type: "POST",
					url:"vehicleUsesReg/delCarInfo.do",
					data:"id="+ids,
					async: false,//默认true设置下，所有请求均为异步请求
					cache: true,
					success: function(iJson) {    	    	
					 	if(iJson.status){
							$.messager.alert("提示","删除成功!","warning");
		//		 	 	   $("#addWindow").dialog("close");
						   $("#dg").datagrid("reload");//刷新表格    	    		
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
					$.messager.alert("提示","请选择一条记录!","warning");
					return false;
			 }else if(rowdata.length>1){
					$.messager.alert("提示","只能选择一条记录编辑!","warning");
					return false;
			 }else{
				 rowdata = $('#dg').datagrid('getSelected');//获取选中的记录
				$('#addWindow').dialog({    
				    title: '编辑',    
				    width: 920,    
				    height: 400,    
				    closed: false,    
				    cache: false, 
				    resizable:false,
				    href: 'jsp/ad/carRegister/carRegisterAdd.jsp',    
				    modal: true,
				    onLoad:function(){
						createSelect()
						if(rowdata!=null){
					   	 	$("#investProductInputOrSaveForm").form('load',rowdata);
				 	        $("#dept").combotree('setValue',rowdata.organizationId);
				 	     	$("#user").combogrid('setValue',rowdata.userId);
						}
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
						<td>使用部门：</td>
						<td><input name="deptName" id="deptName" class="easyui-textbox" editable="true" style="width:174px;"/></td>
						<td>使用人姓名：&nbsp;&nbsp;</td>
						<td><input name="userName" id="userName" class="easyui-textbox easyui-combogrid" editable="true" style="width:174px;"/></td>
						<td>停车总费用：&nbsp;&nbsp;</td>
						<td><input name="countAmount" id="countAmount" class="easyui-textbox" editable="true" style="width:174px;"/></td>
						<td align="right">
						    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="false" onclick="doSearch();">执行查询</a>&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
						<td>
							 <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-reload" plain="false" onclick="clearAdvancedQueryConditions()">条件重置</a>
						</td>
					</tr>
					<tr>
						<td>出车时间：&nbsp;&nbsp;</td>
						<td><input name="beginTime" id="beginTime" type="text" class="easyui-validatebox easyui-datebox" style="width: 170px" readonly="readonly"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td align="center">至：&nbsp;&nbsp;</td>
						<td><input name="endTime" id="endTime" type="text" class="easyui-validatebox easyui-datebox" style="width: 170px" readonly="readonly"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
					</tr>
					<tr>
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
