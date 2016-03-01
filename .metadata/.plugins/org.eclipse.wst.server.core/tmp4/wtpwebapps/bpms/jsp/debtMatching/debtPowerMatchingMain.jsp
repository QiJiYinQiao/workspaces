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
<title>债权匹配</title>
</head>
<script type="text/javascript">
var repayMethodArr = jqueryUtil.getTextArr("repay_method");//还款方式
var mortgageStatusArr = jqueryUtil.getTextArr("mortgage_status");//房屋居住情况
var row;
$(function(){
	createLoanOrderGrid();
});

// 自动调整页面高度
	$(window).resize(function(){  
        $("#loanOrderGrid").datagrid({  
        	height: $(window).height()-100,
        	width : 'auto'
        });                
    });

//贷款订单列表
function createLoanOrderGrid(){
	$("#loanOrderGrid").datagrid({
		url:'loanOrder/loanOrderAction!findLoanOrderListByOrderStatus.action',
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
		      {field : 'name',title : '客户姓名', width : parseInt($(this).width() * 0.06),align:'center'},
              {field : 'idNo',title : '身份证号', width : parseInt($(this).width() * 0.06),align:'center'},
              {field : 'age',title : '年龄', width : parseInt($(this).width() * 0.06),align:'center'},
              {field : 'annualSalary',title : '年收入(单位:元)', width : parseInt($(this).width() * 0.06),align:'center'},
              {field : 'mortgageStatus',title : '居住情况',width:120,align:'center',formatter:function(value,row,index){
            	  return jqueryUtil.showText(value,mortgageStatusArr);
              }},
              {field : 'loanAmount',title : '申请贷款额度(单位:元)', width : parseInt($(this).width() * 0.06),align:'center'},
              {field : 'loanMin',title : '最低接受额度(单位:元)', width : parseInt($(this).width() * 0.06),align:'center'},
              {field : 'loanBgDate',title : '贷款开始日期', width : parseInt($(this).width() * 0.06),align:'center'},
              {field : 'loanEdDate',title : '贷款结束日期', width : parseInt($(this).width() * 0.06),align:'center'},
              {field : 'loanPeriod',title : '申请贷款期限(月)', width : parseInt($(this).width() * 0.06),align:'center'},
              {field : 'repayMethod',title : '还款方式', width : parseInt($(this).width() * 0.06),align:'center',formatter:function(value,row,index){
            	  return jqueryUtil.showText(value,repayMethodArr);
              }},
              {field : 'purpose',title : '贷款用途', width : parseInt($(this).width() * 0.06),align:'center'},
              {field : 'caozuo',title : '操作', width : parseInt($(this).width() * 0.06),align:'center',formatter:function(value,row,index){
            	  return "<a href=\"javascript:void(0)\" onclick=\"showDebtPowerMatchingDialog("+index+");\">债权匹配</a>";
              }}
	   ]]
	});
}
//债权匹配弹框
function showDebtPowerMatchingDialog(index){
	var rows = $("#loanOrderGrid").datagrid("getRows");
	row = rows[index];
	$('#debtPowerMatchingDialog').dialog({    
	    title: '债权匹配',    
	    width: 1000,    
	    height: 600,    
	    closed: false,    
	    cache: false,    
	    href: 'debtMatching/debtMatchingAction!findLoanOrderByLoanOrderId.action?loanOrderId='+row.loanOrderId,    
	    modal: true   
	}); 
}
</script>
<body>
	<div class="position" style="margin-top: 5px;">您当前所在位置： 业务管理  &gt; 财富业务管理  &gt; 债权匹配 </div>
   <table id="loanOrderGrid"></table>
   <!-- 债权匹配弹框 -->
   <div id="debtPowerMatchingDialog"></div>
</body>
</html>