<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../../layout/script.jsp"></jsp:include>
<title>待办任务</title>
<style type="text/css">
body{
   margin: 0px;
   padding: 0px;
}
</style>
</head>
<script type="text/javascript">
$(function(){
	createMyTaskGrid();
});

// 自动调整页面高度
$(window).resize(function(){  
       $("#taskList").datagrid({  
       	height: $(window).height()-100,
       	width : 'auto'
       });                
   });

//创建列表
function createMyTaskGrid(){
	$("#taskList").datagrid({
		url:'investRedeem/investRedeemAction!findAllUnclaimedTaskList.action',	//获取当前角色中所有没有被签收（指定）的任务	
		title:'待办任务',
		width: 'auto',
		height: $(window).height()-40,
		pagination:true,
		rownumbers:true,
		border:false,
		singleSelect:true,
		nowrap:true,//如果为true，则在同一行中显示数据。设置为true可以提高加载性能。
		pageSize:30,
		pageList:[10,20,30,40],
		remoteSort:false,//定义是否从服务器对数据进行排序。
		striped:true,//是否显示斑马线
		columns:[[
		        {field : 'investorName',title : '客户姓名', width : parseInt($(this).width() * 0.05) ,align : 'center',formatter:function(value,row,index){
		        	return "<a href=\"javascript:void(0)\" onclick=\"showInvestorView("+index+")\">"+value+"</a>";
		        }},
                {field : 'idCrad',title : '证件号码', width : parseInt($(this).width() * 0.08) ,align : 'center'},
	            {field : 'mobTel',title : '手机号码', width : parseInt($(this).width() * 0.06) ,align : 'center'},
	            {field : 'licaichanping',title : '理财产品', width : parseInt($(this).width() * 0.05) ,align : 'center',formatter:function(value,row,index){
               	 var result ="<a href='javascript:void(0);' onclick='showInvestorAndInvestProductsDetailsView(\""+row.investOrderId+"\");'>详情</a>";
    				 return result;	
                }},
                {field : 'financingMgr',title : '理财经理', width : parseInt($(this).width() * 0.05) ,align : 'center', 
                	formatter : function(value, row, index){
                		return row.financingMgr.name;
                	}
                },
                {field : 'fmPhone',title : '理财经理电话', width : parseInt($(this).width() * 0.06) ,align : 'center'},
                {field : 'bankName',title : '开户行名称', width : parseInt($(this).width() * 0.06) ,align : 'center'},
                {field : 'actNo',title : '开户行账号', width : parseInt($(this).width() * 0.10) ,align : 'center'},
                {field : 'actName',title : '账户名称', width : parseInt($(this).width() * 0.05) ,align : 'center'},
                {field : 'fmSignDate',title : '理财经理签署日期', width : parseInt($(this).width() * 0.08) ,align : 'center'},
                {field : 'caozuo',title : '操作', width : parseInt($(this).width() * 0.12) ,align : 'center',formatter:function(value,row,index){
                	return "<a href=\"javascript:void(0)\" onclick=\"checkInvestOrderOpinions("+index+")\">查看审批意见</a>&nbsp;&nbsp;&nbsp;"+
                	       "<a href=\"javascript:void(0)\" onclick=\"checkProcessImg("+index+")\">查看流程图</a>&nbsp;&nbsp;&nbsp;"+
                	       "<a href=\"javascript:void(0)\" onclick=\"claimTask("+index+")\">签收任务</a>";
                }}
	   ]]
	});
}
//签收任务
function claimTask(index){
	var rows = $("#taskList").datagrid("getRows");
	var row = rows[index];
	//发送Ajax
	$.ajax({
		type:'POST',
		url:'investRedeem/investRedeemAction!pickMyTask.action',
		data:'taskId='+row.taskId,
		dataType:'JSON',
		success:function(iJson){
			if(iJson.status){
				$("#taskList").datagrid("reload");//刷新列表
			}
			parent.$.messager.show({
				title : iJson.title,
				msg : iJson.message,
				timeout : 1000 * 2
			});
		}
	});
}
//查看流程图
function checkProcessImg(index){
	var rows = $("#taskList").datagrid("getRows");
	var row = rows[index];
	var src = "investRedeem/investRedeemAction!checkWorkFlowImg4InvestRedeemByInvestOrderId.action?investOrderId=" + row.investOrderId;
	$('#imageDialog').dialog("open");
	$("#image").attr("src", src);
}
//查看投资人详细信息
function showInvestorView(index){
	var rows = $("#taskList").datagrid("getRows");
	var row = rows[index];
	$('#investorView').dialog({    
			    title: '详情',    
			    width: 720,    
			    height: 545,    
			    closed: false,    
			    cache: false,    
			    href: 'investor/investorAction!findInvestorByInvestorId.action?investorId='+row.investorId,    
			    modal: true   
			}); 
}

var row4OpinionsDialog;  //注意，此行代码必须添加，否则，在新弹出的“历史审查意见”窗口是无法获得传入的investOrderId的，进而无法查询历史订单数据，
//查看审批意见
function checkInvestOrderOpinions(index){
	var rows = $("#taskList").datagrid("getRows");
	row4OpinionsDialog = rows[index];//获取本条数据
	$('#OpinionsDialog').dialog({    
	    title: '历史审查意见',    
	    width: 750,    
	    height: 500,    
	    closed: false,    
	    cache: false,    
	    href: 'jsp/investRedeem/optionsList4InvestRedeem.jsp',    
	    modal: true   
	});   
}

//查看该投资人的理财产品详情界面
function showInvestorAndInvestProductsDetailsView(investOrderId){
	$('#investorAndInvestProductsView').dialog({    
	    title: '理财产品详情',    
	    width: 700,    	
	    height: 350,    
	    closed: false,
	    closable: true,
	    cache: false,    
	    href: 'investOrder/investOrderAction!findInvestorAndInvestProductsDetails.action?investOrderId='+investOrderId,    
	    modal: true   
	});						
}
</script>
<body>
<div class="position" style="margin-top: 5px;">您当前所在位置： 业务管理  &gt; 财富业务管理  &gt; 任务办理 &gt; 待办任务</div>
<table id="taskList" style="margin: 0px;padding: 0px;"></table>
<!-- 流程图片弹框 -->
<div id="imageDialog" class="easyui-dialog" title="流程图片" data-options="border:false,closed:true,fit:true">
   <img id="image" src="">
</div>
<!-- 投资人详细信息 -->
<div id="investorView"></div>
<!-- 审查意见 -->
<div id="OpinionsDialog"></div>
<!-- 理财产品详情数据区域 -->
<div id="investorAndInvestProductsView"></div>
</body>
</html>