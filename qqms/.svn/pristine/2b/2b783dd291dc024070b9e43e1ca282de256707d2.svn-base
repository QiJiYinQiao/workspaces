<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="<%=basePath%>">
<title>贷款订单管理</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<jsp:include page="../../layout/script.jsp"></jsp:include>
<script type="text/javascript">
	var $dg;
	var $grid;	
	// 从数据字典中查找出贷款类型的数据。
	var loanTypeArr=jqueryUtil.getTextArr("loan_type");	
	// 从数据字典中查找出还款方式。
	var repayMethodArr=jqueryUtil.getTextArr("repay_method");
	
	$(function() {	
		/* 初始化贷款订单状态下拉列表 */
		$('#orderStatus').combobox({    
		    url:'orderStatus/orderStatusAction!findAllOrderStatus.action',    
		    valueField:'code',    
		    textField:'text'   
		});		
		
		
		$dg = $("#dg");
		$grid = $dg.datagrid({
			url : "loanOrder/loanOrderAction!findAllLoanOrderList.action",
			width : 'auto',
			height : $(this).height() - 85,
			pagination : true,
			rownumbers : true,
			border : true,
			striped : true,
			singleSelect : true,
			columns : [ [ 
			       { field : 'loanOrderId', title : '贷款订单编号',  width : 150, align : 'center'}, 			       
			       { field : 'name',        title : '贷款人',      width : 80,  align : 'center',
 						formatter:function(value,row){
							return "<a style=\"color:blue;text-decoration:none;\" onmouseover=\"this.style.color='red'\" onmouseout=\"this.style.color='blue'\" href=\"javascript:void(0)\" onclick=\"showLoanerDetail('"+row.loaner.loanerId+"');\">"+value+"</a>";
					 	}
			       }, 
			       
			       { field : 'idNo',        title : '身份证号',     width : 150, align : 'center'}, 
			       { field : 'loanType',    title : '贷款类型',     width : 80,  align : 'center', 
						formatter:function(value,row){
		            		  return jqueryUtil.showText(value,loanTypeArr); 
						}			    	   
			       }, 
			       { field : 'loanAmount',  title : '申请贷款额度',  width : 150, align : 'center'}, 
			       { field : 'loanMin',     title : '最低接受额度',  width : 150, align : 'center'}, 
			       { field : 'applyDate',  title : '贷款申请日期',  width : 150, align : 'center'}, 
			       { field : 'objectId',    title : '订单处理员工',  width : 100, align : 'center'}, 
			       { field : 'repayMethod', title : '还款方式',     width : 150, align : 'center',
						formatter:function(value,row){
		            		  return jqueryUtil.showText(value, repayMethodArr); 
						}				    	   
			       }, 
			       { field : 'orderStatus', title : '贷款订单状态',  width : 150, align : 'center',
						formatter:function(value,row){
								if (row.orderStatus){
									return row.orderStatus.statusName;
								} else {
									return value;
								}							 
						 }			    	   
			       }, 
			       { field : 'a', title : '查看信审详情',  width : 150, align : 'center',
						 formatter:function(value,row){
							 return "<a style=\"color:blue;text-decoration:none;\" onmouseover=\"this.style.color='red'\" onmouseout=\"this.style.color='blue'\" href=\"javascript:void(0)\" onclick=\"showCreditAuditDetail('"+row.loanOrderId+"');\">点击查看订单信审详情</a>";							 
						 }		    	   			       
			       }
			    	   
			 ] ]
		});
	});
	

	/**根据贷款人id,查看贷款客户详情*/
	function showLoanerDetail(loanerId){
		parent.$.modalDialog({
			title : "贷款客户信息详情",
			width : 1000,
			height : 900,
			href : "loaner/loanerAction!forLastLoanerDetail.action?loanerId="+loanerId,
			buttons : [ {
				text : '关闭',
				iconCls : 'icon-cancel',
				handler : function() {
					parent.$.modalDialog.handler.dialog('destroy');
					parent.$.modalDialog.handler = undefined;
				}
			}]
		});
	}	

	/** 根据查看订单的信审详情  **/
	function showCreditAuditDetail(loanOrderId){				
		parent.$.modalDialog({
		    title: '贷款订单信审报告',    
		    width: 1120,    
		    height: 900,    
		    closed: false,    
		    cache: false,    
		    href : "loaner/loanerAction!forLoanCreditAuditReportDetail.action?loanOrderId="+loanOrderId,   
		    modal: true,
		    buttons : [ {
				text : '关闭',
				iconCls : 'icon-cancel',
				handler : function() {
					parent.$.modalDialog.handler.dialog('destroy');
					parent.$.modalDialog.handler = undefined;
				}
			}
			]
		});		
	}		
	
	
	/**高级查询*/	
	function doSearch(){
 		var loanOrderId = $("#loanOrderId").val(); //贷款订单编号 		
		var loanerName = $("#loanerName").val(); //贷款人姓名
		var idNo = $("#idNo").val(); //贷款人身份证编号
		var loanType = $("#loanType").combobox('getValue');//贷款类型：工薪贷 or 事业贷 		
		var orderStatus = $("#orderStatus").combobox('getValue'); //贷款订单状态。
		//订单处理员，暂无查询实现，此处备忘。
		var applyDate = $("#applyDate").datebox('getValue'); //贷款申请日期，开始日期范围
		var applyDate02 = $("#applyDate02").datebox('getValue'); //贷款申请日期，结束日期范围
		
		var loanAmount = $("#loanAmount").val(); //贷款申请额度
		var loanMin = $("#loanMin").val(); //最低接受额度
						
		$("#dg").datagrid('load', 
			{			
				loanOrderId : loanOrderId,
				loanerName : loanerName,
				idNo : idNo,
				loanType : loanType,			
				orderStatus : orderStatus, 
				applyDate : applyDate,    
				applyDate02 : applyDate02,
				loanAmount : loanAmount,
				loanMin : loanMin
			}
		); 
	}
	
	
	/** 清空高级查询条件 ，重新加载贷款订单数据**/
	function clearAdvancedQueryConditions(){
		$("#searchForm").form("clear");		
		$("#dg").datagrid('load',{});
	}
	
</script>
</head>

<body>
	<style>
		.nkframe_position {
			padding-left: 30px;
			margin-bottom: 10px;
			border-bottom: 1px solid #d2e7f8;
			height: 24px;
			line-height: 24px;
			background: url(images/nk_position.gif) 5px center no-repeat;
			font-size: 12px;
			font-weight: normal;
		}
	</style>
	<h1 class="nkframe_position" style="margin-top: 5px;">您当前所在位置：
		贷款业务管理 &gt; 贷款订单管理</h1>		
	
	<div class="well well-small">
		<span class="badge">高级查询</span>
		<form id="searchForm" action="" method="post">
			<table cellpadding="0" cellspacing="1" border="0">
				<tr>
				    <td width="3%" style="text-align: right">贷款订单编号&nbsp;&nbsp;</td>	
					<td width="5%"><input name="loanOrderId" id="loanOrderId" type="text" class="easyui-textbox easyui-validatebox" style="width: 200px"/></td>
					
					<td width="3%" style="text-align: right">贷款人姓名&nbsp;&nbsp;</td>
					<td width="5%"><input id="loanerName" name="loanerName" type="text" class="easyui-textbox easyui-validatebox"/></td>
					
					<td width="3%" style="text-align: right">贷款人身份证&nbsp;&nbsp;</td>
					<td width="16%"><input id="idNo" name="idNo" type="text" class="easyui-textbox easyui-validatebox"/></td>
										
					<td width="3%" style="text-align: right">贷款类型&nbsp;&nbsp;</td>
					<td width="5%">
						<select id="loanType" name="loanType" class="easyui-combobox" style="width:150px;"> 
							<option value="">请选择</option>  
						    <option value="A">事业贷</option>    
						    <option value="B">工薪贷</option>   
						</select>  
					</td>				
				</tr>


				<tr>					
					<td style="text-align: right">贷款订单状态&nbsp;&nbsp;</td>&nbsp;&nbsp;
					<td><input id="orderStatus" name="orderStatus" type="text"></input></td>

					<td style="text-align: right">订单处理员&nbsp;&nbsp;</td>
					<td><input id="" name="" type="text" class="easyui-textbox easyui-validatebox"/></td>

				    <td style="text-align: right">贷款申请日期&nbsp;&nbsp;</td>
					<td>从&nbsp;
						<input name="applyDate" id="applyDate" class="easyui-datebox" editable="true"/>&nbsp;&nbsp;到&nbsp;
						<input name="applyDate02" id="applyDate02" class="easyui-datebox" editable="true"/>
					</td>					
					<td style="text-align: right">贷款申请额度&nbsp;&nbsp;</td>
					<td ><input id="loanAmount" name="loanAmount" type="text" class="easyui-textbox easyui-validatebox"/></td>						 													
				</tr>

				<tr>				
					<td style="text-align: right">最低接受额度&nbsp;&nbsp;</td>
					<td ><input id="loanMin" name="loanMin" type="text" class="easyui-textbox easyui-validatebox"/></td>
				
					<td colspan="6" align="right">
					    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="false" onclick="doSearch();">搜索</a>&nbsp;&nbsp;&nbsp;&nbsp;
					    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-reload" plain="false" onclick="clearAdvancedQueryConditions()">清空</a>
					</td>						
				</tr>
			</table>
		</form>
	</div>	
	
	<div>
		<table id="dg" title="贷款订单管理"></table>
	</div>
	<div id="dd"></div>
</body>
</html>
