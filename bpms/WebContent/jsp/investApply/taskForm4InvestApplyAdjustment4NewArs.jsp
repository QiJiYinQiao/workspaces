<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<style type="text/css">
	table {border-radius: 5px;}
	.linkSpan{
	  padding:5px;
	  display:-moz-inline-box;
	  display:inline-block;
	  width:40%; 
	  text-align: center;
	}
	.linkSpanS{
	  padding:5px;
	  display:-moz-inline-box;
	  display:inline-block;
	  width:10%; 
	  text-align: center;
	}
	a{text-decoration: none;}
	a:hover {
	 color: #FF0000;
	}
	.table th{
		text-align: right;
	}
	.table td{
		text-align: left;
	}	
	textarea{resize: none;}
</style>

<script type="text/javascript">
	var row,row1;
	var investorId='${investorId}';
	var investOrderId='${investOrderId}';
	var taskId='${taskId}';
	$(function(){
		//查询投资人详细信息
		$.ajax({
			url:'investApply/investApplyAction!findInvestApplyInfo4AdjustArs.action',
			data:'investOrderId='+investOrderId,
			dataType:'json',
			async : false,
			success:function(data){
				row = data;
			}
		});
			
		//渲染页面数据——理财经理的营业部名称
		$("#id4OrgFullName").html(row.orgFullName);
		//渲染页面数据——投资者姓名		
		$("#id4ChName").html(row.chName);
		$("#id4ChName").css({ color: "#ff0011"});
		//渲染页面数据——投资起始日期
		$("#id4BeginDate").html(row.beginDate);
		$("#id4BeginDate").css({ color: "#ff0011"});
		//渲染页面数据——理财产品名称
		$("#id4ProdName").html(row.prodName);
		$("#id4ProdName").css({ color: "#ff0011"});
		//渲染页面数据——理财金额
		$("#id4InvestEdu").html(row.investEdu);
		$("#id4InvestEdu").css({ color: "#ff0011"});
		//渲染页面数据——年化收益率
		$("#id4Ars").html(row.ars);
		$("#id4Ars").css({ color: "#ff0011"});
		//渲染页面数据——新的年化收益率
		$("#id4NewArs").html(row.newArs);
		$("#id4NewArs").css({ color: "#ff0011"});
		//渲染页面数据——申请人姓名(理财经理姓名)
		$("#id4UserName").html(row.userName);
		$("#id4UserName").css({ color: "#ff0011"});
		//渲染页面数据——申请日期
		$("#id4FmSignDate").html(row.fmSignDate);
		$("#id4FmSignDate").css({ color: "#ff0011"});
		
		// 查看投资申请备注信息DataGrid
		$("#investApplyMemoDataGrid").datagrid({
			url : "investOrderHis/investOrderHisAction!findAllInvestOrderHisList.action?investOrderId="+investOrderId,
			width : 'auto',
			height : 240,
			pagination:true,
			rownumbers:true,
			border:true,
			singleSelect:true,
			nowrap:true,
			columns : [ [ 
			              {field : 'agentTime',title : '受理时间',width : parseInt($(this).width()*0.1),sortable:true},
			              {field : 'roleName',title : '受理角色',width : parseInt($(this).width()*0.1)},
			              {field : 'assigneeName',title : '受理人',width : parseInt($(this).width()*0.1),align : 'left'},
			              {field : 'comment',title : '审批意见',width :parseInt($(this).width()*0.1),align : 'left'}
			          ] ]
		});										
	});	
	
	
	/**======审批通过或驳回=======*/
	function  submitTask(result,object) {
		// 验证备注信息是否已经填写
		if($("#comment").val()==""){
			$.messager.alert("提示","请填写备注信息后再进行提交!","warning")
			return false;
		}
		// 确认是否提交
		$.messager.confirm('提示', '点击按钮之后将进入下一个任务活动环节,此任务将对您不可见!', function(r){
			if (r){
				var data = {
					"comment" : $("#comment").val(),
					"result" :result,
					"investOrderId" :investOrderId,
					"taskId": taskId,
					"newArs": $("#newArs").val()    //获取理财客户新输入的理财收益率
				}
				$.ajax({
					type : "POST",
					url : "investApply/investApplyAction!reSubmitTask4AdjustArs.action",
					data : data,
					success : function(msg) {
						parent.$.modalDialog.openner.datagrid('reload');
						parent.$.modalDialog.handler.dialog('close');
					}
				});
			}
		});
	}
	
</script>

<!-- 受理任务 S -->
<div data-options="region:'north',title:'North Title',split:true">

	<!-- 理财收益调整申请单文本界面 -->	
	<div style="height: 250px; overflow: auto; border-color: black; border: 1px;" align="center">
		<h1><font color="red">理财收益调整申请单</font></h1>
		<div align="left" style="margin-left: 160px; font-size: 16px;">		
			<span id="id4OrgFullName"></span>理财客户<span id="id4ChName"></span>于<span id="id4BeginDate"></span>投资理财，<br>
			产品<span id="id4ProdName"></span>，金额为<span id="id4InvestEdu"></span>元整，原年化收益率为<span id="id4Ars"></span>%，现申请调整为<span id="id4NewArs"/></span>%。<br>
			特此申请，望批准！<br><br><br>
			<div style="margin-left: 470px">
				申请人：<span id="id4UserName"></span><br>
				申请日期：<span id="id4FmSignDate"></span>			
			</div>
		</div>			
	</div>
	
	<!-- 工作人员填写备注的区域 -->
	<div style="margin-left: 160px;">
		<form id="acceptTaskForm" method="post">
			 <input name="id" id="id"  type="hidden"/>
			 <input name="auditId" type="hidden" value="noauditId"/>			
			 <table class="table" cellpadding="5px;">
				<tr>
				 	<th>新的理财收益率：</th>
					<td><input id="newArs" name="newArs" class="easyui-validatebox easyui-textbox" style="width:100px;height:20px;">%</td>
				</tr>									 
				<tr>
				 	<th>审批意见（必填）:</th>
					<td><textarea id="comment" name="comment" class="easyui-validatebox easyui-textbox" style="width:500px;height:25px;"></textarea></td>
				</tr>						
			 </table>								
		</form>
	</div>	
		
	<div style="width: 880px;height:30px;">		
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-redo" onclick="submitTask('InvestApplyResubmit4NewArs',this);">订单重提</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" onclick="submitTask('InvestApplyRevoke4NewArs',this);">订单撤销</a>								
	</div>
    <div id="lookInfo" class="easyui-accordion" style="height: 300px;width: 980px;overflow: hidden;">
	    <div title="备注信息" data-options="iconCls:'icon-cstbase',selected:true" >   
			<table id="investApplyMemoDataGrid" title="申请备注的信息"></table>
		</div>
	</div>
	
</div>   
<!-- 受理任务 E -->		
<div id="dd"></div>	