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
    <title>客户还款管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<jsp:include page="../../layout/script.jsp"></jsp:include>
 
<style type="text/css">
	#customerRepaymentDetailForm input{
		border: none;
	}
</style>
<script type="text/javascript">
var $lcid;
$(function(){
	$(window).resize(function(){  
        $("#customerRepaymentTab").datagrid("resize",{  
			height : $(window).height()-160,
        	width : 'auto'
        });                
    });
	
	// 查看申请状态
	$datagrid = $("#customerRepaymentTab").datagrid({
		url : "loanCustRepaymentDetail/loanCustRepaymentDetailAction!findLoanCustRepaymentDetail.action",
		width : 'auto',
		height : $(window).height()-160,
		pagination:true,
		rownumbers:true,
		border:true,
		singleSelect:true,
		nowrap:true,
		multiSort:false,
		columns : [ [ 
		              {field : 'loanCity',title : '进件城市',width : 60,align : 'center'},
		              {field : 'contractNo',title : '合同编号',width :280,align : 'center',
			            	formatter:function(value,row,index){
			            		return "<a href='javascript:void(0);' onclick='checkContractDetail("+index+");'>"+value+"</a>　　" ;
			            	}  
		              },
		              {field : 'contractSignDate',title : '合同签署日期',width : 100,align : 'center'},
		              {field : 'loanName',title : '客户姓名',width : 100,align : 'center'},
		              {field : 'loanIdNo',title : '身份证号码',width : 150,align : 'center'},
		              {field : 'loanMobileTel',title : '联系方式',width : 120,align : 'center'},
		              {field : 'loanerBankName',title : '开户行名称',width : 120,align : 'center'},
		              {field : 'loanerActNum',title : '开户行账号',width : 150,align : 'center'},
		              {field : 'loanBgDate',title : '放款日期',width : 100,align : 'center'},
		              {field : 'monthlyRepaymentDate',title : '月还款日',width : 60,align : 'center'},
		              {field : 'repaymentBgDate',title : '还款开始日期',width : 100,align : 'center'},
		              {field : 'repaymentEndDate',title : '还款截止日期',width : 100,align : 'center'},
		              {field : 'loanEdu',title : '贷款金额',width : 100,align : 'center'},
		              {field : 'monthlyRepayment',title : '月还金额(元)',width : 100,align : 'center'},
		              {field : 'now',title : '当前日期',width : 100,align : 'center'},
		              {field : 'loanPeriods',title : '贷款期数(期)',width : 80,align : 'center'},
		              {field : 'currentLoanPeriods',title : '当前期数(期)',width : 80,align : 'center'},
		              {field : 'paidLoanPeriods',title : '已还期数(期)',width : 80,align : 'center'},
		              {field : 'overdueTimes',title : '逾期次数(次)',width : 80,align : 'center'},
		              {field : 'overdueDays',title : '逾期天数(天)',width : 80,align : 'center'},
		              {field : 'lateFee',title : '滞纳金(元)',width : 80,align : 'center'},
		              {field : 'defaultFnterest',title : '罚息(元)',width : 80,align : 'center'},
		              {field : 'realRepmtAmt',title : '实际还款(元)',width : 100,align : 'center'},
		              {field : 'salesMan',title : '业务员',width : 100,align : 'center'},
		              {field : 'teamManger',title : '团队经理',width : 100,align : 'center'},
		              {field : 'operator',title : '操作',width :100,align : 'center',
			            	formatter:function(value,row,index){
			            		return "<a href='javascript:void(0);' onclick='checkRepaymentDetail("+index+");'>还款详情</a>　　" ;
			            	}  
		              }
		              ] ]
	});
	
	//loadOrganization();
	
	$("#isOverdue").combobox({
			valueField: 'value',   
	        textField: 'label',   
	        data: [{
				label: '全部',
				value: '0'
			},{
				label: '当期正常还款',
				value: '1'
			},{
				label: '逾期还款',
				value: '2'
			}],
			editable:false ,
			onLoadSuccess : function(){
			userData = $(this).combobox("getData");
			for (var item in userData[0]) {
	                if (item == "value") {
	                    $(this).combobox("select", userData[0][item]);
	                }
	            }
			}
	});
});

//获取所属地区
/* function loadOrganization(){
	$("#organizationId").combobox({
		valueField : 'code',
		textField : 'text',
		url:"orgz/organizationAction!findOrganizationCombo.action",
		editable:false,
	});
} */

//重置查询表单
function resetCustomerRepaymentForm(){
	$("#customerRepaymentForm").form("clear");
	$("#customerRepaymentTab").datagrid('load',{});
}

//执行查询
function subCustomerRepaymentForm(){
	$("#customerRepaymentTab").datagrid('load',{
			"contractNo":$("#contractNo").val(),
			"loanCity" :$("#loanCity").val(),
			"loanName":$("#loanName").val(),
			"planRepmtDate":$("#planRepmtDate").datebox("getValue"),
			"contractSignDateS":$("#contractSignDateS").datebox("getValue"),
			"contractSignDateE":$("#contractSignDateE").datebox("getValue"),
			"isOverdue":$("#isOverdue").combobox("getValue")
			});
}

// 导出逾期客户列表
function exportOverPeriodCustomerRepayment(){
	downFileByFormPost("loanCustRepaymentDetail/loanCustRepaymentDetailAction!exportLoanCustRepaymentDetail.action",
			{
			"contractNo":$("#contractNo").val(),
			"loanCity" :$("#loanCity").val(),
			"loanName":$("#loanName").val(),
			"planRepmtDate":$("#planRepmtDate").datebox("getValue"),
			"contractSignDateS":$("#contractSignDateS").datebox("getValue"),
			"contractSignDateE":$("#contractSignDateE").datebox("getValue"),
			"isOverdue":'2'
			});
}

//导出当期客户列表
function exportCurrentPeriodCustomerRepayment(){
	downFileByFormPost("loanCustRepaymentDetail/loanCustRepaymentDetailAction!exportLoanCustRepaymentDetail.action",
			{
			"contractNo":$("#contractNo").val(),
			"loanCity" :$("#loanCity").val(),
			"loanName":$("#loanName").val(),
			"planRepmtDate":$("#planRepmtDate").datebox("getValue"),
			"contractSignDateS":$("#contractSignDateS").datebox("getValue"),
			"contractSignDateE":$("#contractSignDateE").datebox("getValue"),
			"isOverdue":'1'
			});
}

//查看合同详情
function checkContractDetail(index){
	var rows = $("#customerRepaymentTab").datagrid("getRows");
	$lcid = rows[index].lcid;
	$("<div></div>").dialog({
		title:"合同详情",
		width: 1050,   
	    height: 600,   
	    closed: false,   
	    cache: false,   
	    closable:true,
	    modal: true,
	    href:"jsp/loanerMonitoring/loanContractDetail.jsp",
	    onClose:function(){
	    	$(this).dialog("destroy");
	    }
	});
}

//查询还款详情
function checkRepaymentDetail(index){
	var rows = $("#customerRepaymentTab").datagrid("getRows");
	var contractNo = rows[index].contractNo;
	$("#customerRepaymentDetailDlg").css("display","block").dialog({
		width:1150,
		height:680,
		closed:false,
		closable:true,
		modal:true,
		title:"还款详情",
		onOpen:function(){
			$("#customerRepaymentDetailForm").form("load",rows[index]);
			loadCustomerRepaymentDetailTab(contractNo);
		}
	});
}

//加载客户还款信息详情列表
function loadCustomerRepaymentDetailTab(contractNo){
	$("#customerRepaymentDetailTab").datagrid({
		url : "loanCustRepaymentDetail/loanCustRepaymentDetailAction!findRepaymentDetailByCno.action",
		width : 'auto',
		height : 520,
		pagination:true,
		rownumbers:false,
		border:true,
		singleSelect:true,
		nowrap:true,
		multiSort:false,
		queryParams:{"contractNo":contractNo},
		columns : [ [ 
		              {field : 'periods',title : '期数',width : 30,align : 'center'},
		              {field : 'planRepmtDate',title : '应还款日期',width :100,align : 'center'},
		              {field : 'planRepmtAmt',title : '应还款金额(元)',width : 100,align : 'center'},
		              {field : 'realRepmtDate',title : '最后一次还款日期',width : 120,align : 'center'},
		              {field : 'realRepmtAmt',title : '实际还款金额(元)',width : 100,align : 'center'},
		              {field : 'repmtAct',title : '还款账号',width : 120,align : 'center'},
		              {field : 'overdueDays',title : '逾期天数(天)',width : 80,align : 'center'},
		              {field : 'lateFee',title : '滞纳金(元)',width : 100,align : 'center'},
		              {field : 'defaultInterest',title : '罚息(元)',width : 100,align : 'center'},
		              {field : 'freeInterestFee',title : '免息金额(元)',width : 100,align : 'center'},
		              {field : 'rpmtStatusName',title : '还款状态',width : 80,align : 'center'},
		              {field : 'operator',title : '操作人员',width : 80,align : 'center'}
		              ] ]
	})
}
</script>
 </head>
	<body>
		<div>
			<div style="margin-left: 5px;margin-top: 5px">
				业务管理-->财务监控管理-->贷款客户监管-->还款明细导出
			</div>
			<div style="padding-top:5px;">
				<font size="3em">[查询条件]</font>
				<form id="customerRepaymentForm" method="post">
					<table cellpadding="10px;">
						<tr>
							<th>
								进件城市
							</th>
							<td>
								<input id="loanCity" name="loanCity" class="easyui-textbox" placeholder="请输入进件地区"/>
							</td>
							<th>
								合同编号
							</th>
							<td>
								<input id="contractNo" name="contractNo" class="easyui-textbox"  placeholder="请输入合同编号"/>
							</td>
							<th>
								客户姓名
							</th>
							<td>
								<input id="loanName" name="loanName" class="easyui-textbox" placeholder="请输入客户姓名"/>
							</td>
							<th>
								月还款日期
							</th>
							<td> 
								<input id="planRepmtDate" name="planRepmtDate" class="easyui-textbox easyui-datebox" data-options="editable:false" />
							</td>
						</tr>
						
						<tr>
							<th>
								合同签署日期
							</th>
							<td colspan="3"> 
								<input id="contractSignDateS" name="contractSignDateS" placeholder="请选择起始日期" class="easyui-textbox easyui-datebox" data-options="editable:false" />
							　　至　　
								<input id="contractSignDateE" name="contractSignDateE" placeholder="请选择截止日期" class="easyui-textbox easyui-datebox" data-options="editable:false"/>
							</td>
							<th>
								是否逾期
							</th>
							<td> 
								<input id="isOverdue" name="isOverdue" class="easyui-textbox" />  
							</td>
						</tr>
					</table>
				</form>
				<div style="text-align:right;">
					<span style="color:red;float:left;padding-top:6px;">
						注:不输入任何查询条件，默认查询所有客户还款详情
					</span>
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="subCustomerRepaymentForm();">执行查询</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-reload" onclick="resetCustomerRepaymentForm();">重置条件</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" onclick="exportOverPeriodCustomerRepayment();">导出逾期客户还款明细</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" onclick="exportCurrentPeriodCustomerRepayment();">导出当期客户还款明细</a>
				</div>
			</div>
			
			<div>
				<table id="customerRepaymentTab" title="客户还款管理"></table>
			</div>
		</div>
		
			<!-- 还款详情 -->
			<div id="customerRepaymentDetailDlg" style="display:none;">
			<div style="height:120px;width:100%">
				<form id="customerRepaymentDetailForm">
					<table cellpadding="8px;"> 
						<tr>
							<th>合同编号</th>
							<td><input name="contractNo" readonly="readonly"/></td>
							
							<th>所属地区</th>
							<td><input name="organizationName" readonly="readonly"/></td>
							
							<th>合同签署日期</th>
							<td><input name="contractSignDate" readonly="readonly"/></td>
							
							<th>客户姓名</th>
							<td><input name="loanName" readonly="readonly"/></td>
						</tr>
						
						<tr>
							<th>身份证号</th>
							<td><input name="loanIdNo" readonly="readonly"/></td>
							
							<th>贷款类型</th>
							<td><input name="loanTypeName" readonly="readonly"/></td>
							
							<th>贷款金额</th>
							<td><input name="loanEdu" readonly="readonly"/></td>
							
							<th>贷款期数</th>
							<td><input name="loanPeriods" readonly="readonly"/></td>
						</tr>
						
						<tr>
							<th>月还金额</th>
							<td><input name="monthlyRepayment" readonly="readonly"/></td>
						
							<th>放款日期</th>
							<td><input name="loanBgDate" readonly="readonly"/></td>
							
							<th>当前日期</th>
							<td><input name="now" readonly="readonly"/></td>
						</tr>
					</table>
				</form>
			</div>
			<div style="height:520px;width:100%">
				<table id="customerRepaymentDetailTab"></table>
			</div>
		</div>
	</body>
</html>				
