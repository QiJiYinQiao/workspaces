<%@page import="com.bpms.util.Constants"%>
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
    <title>待办任务</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<jsp:include page="../../layout/script.jsp"></jsp:include>
	<script type="text/javascript">
			var $grid;
			$(function() {
				$(window).resize(function(){  
		            $("#dg").datagrid("resize",{  
						height :$(window).height()-40,
		            	width : 'auto'
		            });                
		        });
				
				 $grid=$("#dg").datagrid({
					url : "loanOrder/loanOrderAction!findAllUnClaimTask.action",
					width : 'auto',
					height : $(window).height()-40,
					pagination:true,
					rownumbers:true,
					border:true,
					singleSelect:true,
					nowrap:true,
					multiSort:false,
					border:false,
					fitColumns:false,
					pageSize:10,
					pageList:[10,20,30,40],
					columns : [ [ {field : 'name',title : '客户姓名',width : parseInt($(this).width()*0.06),align : 'center'},
					              {field : 'idNo',title : '身份证号',width : parseInt($(this).width()*0.1),align : 'center'},
					              {field : 'age',title : '年龄',width : parseInt($(this).width()*0.03),align : 'center'},
					              {field : 'annualSalary',title : '年收入(单位:元)',width : parseInt($(this).width()*0.06),align : 'center'},
					              {field : 'mortgageStatus',title : '居住情况',width : parseInt($(this).width()*0.1),align : 'center'},
					              {field : 'loanAmount',title : '申请贷款额度(单位:元)',width : parseInt($(this).width()*0.08),align : 'center'},
					              {field : 'loanMin',title : '最低接受额度(单位:元)',width : parseInt($(this).width()*0.08),align : 'center'},
					              {field : 'loanPeriod',title : '申请贷款期限',width : parseInt($(this).width()*0.1),align : 'center'},
					              {field : 'repayMethod',title : '还款方式',width : parseInt($(this).width()*0.1),align : 'center'},
					              {field : 'belongTo',title : '所属业务',width : parseInt($(this).width()*0.05),align : 'center'},
					              {field : 'loanInfo',title : '进件情况',width : parseInt($(this).width()*0.05),align : 'center',
					            	  formatter:function(value,row,index){
					            		  if(value=="1"){
					            			  return "第一次申请";
					            		  }else if(value=="2"){
					            			  return "循环贷";
					            		  }else if(value =="3"){
					            			  return "二次进件"
					            		  }else{
					            			  return ""
					            		  }
				            	 	 }
					              },
					              {field : 'purpose',title : '贷款用途',width : parseInt($(this).width()*0.1),align : 'center'},
					              {field : 'orderStatus',title : '订单状态',width : parseInt($(this).width()*0.1),align : 'center',
					            	  formatter: function(value,row,index){
											return value.statusName;
					            	  }
					              }, 
					              {field : 'areaName',title : '业务所属地区',width : parseInt($(this).width()*0.05),align : 'center'},
					              {field : 'companyName',title : '业务所属公司',width : parseInt($(this).width()*0.1),align : 'center'},
					              {field : 'deptName',title : '营业部',width : parseInt($(this).width()*0.1),align : 'center'},
					              {field : 'operate',title : '操作',width : parseInt($(this).width()*0.3),align : 'center',
					            	  formatter: function(value,row,index){
										var result="<a href='javascript:void(0);' onclick='loanOrderInfo("+ index + ");'>查看申请详情</a>　 ";
		      							result +=  "<a href='javascript:void(0);' onclick='lookLoanOrderProcessCommentDialog("+index+");'>查看审批意见</a>　　";
		      							result +="<a href='javascript:void(0);' onclick='showImage("+index+");'>查看审批流程</a>　　";
		      							result +="<a href='javascript:void(0);' onclick='claimTask("+index+");'>签收任务</a>";
		      							return result;
					      			}
					              }
					              ] ],toolbar:'#tb'
				});
			});
		
		//查看详情
		function loanOrderInfo(index) {
			var row = getRowData($grid,index);
			window.open("jsp/loanOrder/order/loanOrderDetailsForm.jsp?loanerId="+row.loanerId+"&loanOrderId="+row.loanOrderId,
					"详情", "height="+($(window).height()*0.8)+", width=900, top=100, left=200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no")
		}
		
		// 查看流程图片
		function  showImage(index) {
			var row = getRowData($grid,index);
			var src = "loanOrder/loanOrderAction!getDiagramResourceByTaskId.action?taskId="+ row.taskId;
			$('#imageDialog').dialog("open");
			$("#image").attr("src", src);
		}
	    
		// 领取任务
		function claimTask(index) {
			var row = getRowData($grid,index);
			$.ajax({
				url : "loanOrder/loanOrderAction!saveClaimTask.action",
				data : {"taskId" : row.taskId},
				success : function(rsp) {
					if(rsp.status){
						parent.$.messager.show({
							title : rsp.title,
							msg : rsp.message,
							timeout : 1000 * 2
						});
					}else{
						parent.$.messager.alert(rsp.title,rsp.message,'warning');
					}
					$grid.datagrid('reload');
				}
			});
		}
		
		// 查看流程批注
		function lookLoanOrderProcessCommentDialog(index) {
			var row = getRowData($grid,index);
			parent.$.modalDialog.openner= $grid;
			parent.$.modalDialog({
				title : '审批意见查看',
				width : 900,
				height : $(window).height()*0.8,
				href : "jsp/loanOrder/loanOrderProcessComment.jsp"});
		}
		
/* 		// 此处方法就是为了ipc挑拣准备的默认执行方法
		function  submitChooseTask(row) {
			var data = {
				"comment" : "IPC挑拣完毕",
				"result"  :   "IPCInvestigationDeptChooseThrough",
				"loanOrderId" : row.loanOrderId,
				"taskId": row.taskId,
				"processingResult":"A"
			}
			$.ajax({
				type : "POST",
				url : "loanOrder/loanOrderAction!submitTask.action",
				data : data,
				success : function(msg) {
				}
			});
		} */

	</script>
  </head>
  <body>
      <div>
  		<div class="well well-small" style="margin-left: 5px;margin-top: 5px">
					业务管理-->贷款业务管理-->任务办理-->待办任务
		</div>
		<table id="dg"></table>
	    <div id="imageDialog"  class="easyui-dialog" title="流程图片" data-options="border:false,closed:true,fit:true">
			<img id="image" src="" >
		</div>
  	</div>	
  </body>
</html>
