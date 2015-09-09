<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<style type="text/css">
	#acceptTaskForm table input{border: none;}
	.table th{ text-align: right;}
	.table td{ text-align: left;}
</style>
<script type="text/javascript">

	var $row;
	var investorId='${investorId}';
	var investOrderId='${investOrderId}';
	var taskId='${taskId}';	

	//查询投资人的详细信息
	$(function(){			
		//方式一：获得页面传来的参数的方式，最初的方式。
		//缺点：后期JSP页面若都被移动到"/WEB-INF"文件夹下边，则此种方式的代码修改两很大。
		/* $row = parent.$.modalDialog.openner.datagrid('getSelected'); */		
		
		$.ajax({
			type : "POST",
			url : "investor/investorAction!findInvestorById.action",
			data : "investorId="+investorId,
			dataType : "json",
			async : false,
			success : function(dataReturned){
				row = dataReturned;
			}
		
		});			

		// 渲染客户姓名
		$("#acceptTaskForm input[name='name']").val(row.chName);		
		// 渲染身份证号
		$("#acceptTaskForm input[name='idNo']").val(row.idNo);
		
		
		// 渲染查看历史审批意见的Datagrid
		$("#checkHistoryApprovalDatagrid").datagrid({
		    url:'investOrderHis/investOrderHisAction!findAllInvestOrderHisList.action',
			queryParams : {"investOrderId": investOrderId},		    
			width : 'auto',
			height : 200,
		   	pagination : true,
		   	rownumbers : true,
		   	border:true,
			singleSelect:true,
			nowrap : true,
			columns : [ [ 
			              {field : 'createDate',title : '订单申请时间',width : parseInt($(this).width()*0.1),sortable:true},			             
			              {field : 'agentTime',title : '受理时间',width : parseInt($(this).width()*0.1),sortable:true},
			              {field : 'roleName',title : '受理角色',width : parseInt($(this).width()*0.1)},
			              {field : 'assigneeName',title : '受理人',width : parseInt($(this).width()*0.1),align : 'left'},
			              {field : 'comment',title : '审批意见',width :parseInt($(this).width()*0.1),align : 'left'},
			          ] ]		
		});
	});
	
	
	
	/** ==============点击"审批通过"或者"审批驳回"按钮  =============**/
	function submitTask(result, Object){
		//1.判断审批意见是否填写（备注是否被填写）
		if($("#comment").val() == ""){
			$.messager.alert('提示','请填写备注信息后再进行提交!','warning');
			return false;
		}
		
		//2.在信息填写完整的基础上，判断是审批通过，还是审批驳回。		
		$.messager.confirm('提示', '点击按钮之后将进入下一个任务活动环节,此任务将对您不可见!', function(r){
			if (r){
			    // 退出操作;
			    var data = {
			    	"comment" : $("#comment").val(),
			    	"result" : result,
			    	"investOrderId" : investOrderId,
			    	"taskId" : taskId,
			    	"processingResult":result=="SalesCustCommAgree"?"A":"B"
			    };
			    
			    $.ajax({
			    	type : "POST",
			    	url : "investOrder/investOrderAction!submitTask.action",
			    	data : data,
			    	success : function(){
						parent.$.modalDialog.openner.datagrid('reload');
						parent.$.modalDialog.handler.dialog('close');			    		
			    	}
			    	
			    });
			}
		});
	}	
	
</script>


	<div data-options="region:'north',title:'North Title',split:true">
		<div style="height: 200px; overflow: auto;">
			<form id="acceptTaskForm" method="post">
				 <table class="table" cellpadding="5px;">
					 <tr>
					    <th>客户姓名:</th>
						<td><input name="name" readonly="readonly" type="text"/></td>
					</tr>
					<tr>
						<th>身份证号:</th>
						<td><input name="idNo" readonly="readonly" type="text"/></td>
					</tr>
					<tr>
					 	<th>备注:</th>
						<td colspan="3">
							<textarea id="comment" name="comment" class="easyui-validatebox easyui-textbox" style="width:500px;height:70px;"></textarea>
						</td>
					</tr>
				 </table>
			</form>
		</div>
		
		<div style="width: 880px;height:30px;">
			<a href="javascript:void(0);" class="easyui-linkbutton" onclick="submitTask('SalesCustCommAgree',this);">审批通过</a>
			<a href="javascript:void(0);" class="easyui-linkbutton" onclick="submitTask('SalesCustCommReject',this);">审批驳回</a>
		</div>
		
	    <div id="lookInfo" class="easyui-accordion" style="height: 250px;width: 980px; overflow: hidden">
		    <div title="查看历史审批意见" data-options="iconCls:'icon-cstbase',selected:true" >   
				<table id="checkHistoryApprovalDatagrid"></table>
			</div>
		</div>
		
	</div> 		
					
