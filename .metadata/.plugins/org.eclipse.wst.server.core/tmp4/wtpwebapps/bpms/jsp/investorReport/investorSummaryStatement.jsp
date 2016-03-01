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
    <title>客户详情汇总</title>
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
	// 查看申请状态
	$datagrid = $("#investorRelativeTab").datagrid({
		url : "investorRelativeTabsExport/investorRelativeTabsExportAciton!queryInvestorSummaryStatement.action",
		width : 'auto',
		height : parseInt($(this).height()*0.79),
		pagination:true,
		rownumbers:true,
		border:true,
		singleSelect:true,
		nowrap:true,
		multiSort:false,
		columns : [ [ 
		              {field : 'contractNo',title : '合同序号',width :100,align : 'center'},
		              {field : 'name',title : '客户姓名',width : 100,align : 'center'},
		              {field : 'idNo',title : '身份证号码',width : 150,align : 'center'},
		              {field : 'mobilePhone',title : '联系方式',width : 120,align : 'center'},
		              {field : 'address',title : '家庭住址',width : 200,align : 'center'},
		              {field : 'zipCode',title : '邮编',width : 70,align : 'center'},
		              {field : 'email',title : '邮箱',width : 170,align : 'center'},
		              {field : 'bankName',title : '划扣银行',width : 100,align : 'center'},
		              {field : 'cardNo',title : '银行卡号',width : 120,align : 'center'},
		              {field : 'transferPlatform',title : '划扣平台',width : 100,align : 'center'},
		              {field : 'investAmt',title : '理财金额',width : 100,align : 'center'},
		              {field : 'yearAmt',title : '年化金额',width : 100,align : 'center'},
		              {field : 'productName',title : '产品名称',width : 80,align : 'center'},
		              {field : 'ars',title : '年化收益率(%)',width : 90,align : 'center'},
		              {field : 'interestDate',title : '计息日期',width : 80,align : 'center'},
		              {field : 'dueDate',title : '到期日期',width : 80,align : 'center'},
		              {field : 'pstatus',title : '状态',width : 100,align : 'center'},
		              {field : 'repaymentMethod',title : '还款方式',width : 80,align : 'center'},
		              {field : 'interestAmt',title : '利息总额',width : 80,align : 'center'},
		              {field : 'interestPayable',title : '应付利息',width : 80,align : 'center'},
		              {field : 'amtPayable',title : '应付金额',width : 80,align : 'center'},
		              {field : 'montyInterest',title : '月付利息',width : 80,align : 'center'},
		              {field : 'transferDate',title : '划扣日期',width : 80,align : 'center'},
		              {field : 'financialAdvisor',title : '理财顾问',width : 100,align : 'center'},
		              {field : 'teamManager',title : '团队经理',width : 100,align : 'center'},
		              {field : 'groupManager',title : '大团经理',width : 100,align : 'center'},
		              {field : 'businessDepartment',title : '营业部',width : 100,align : 'center'},
		              {field : 'cusNo',title : '客户编号',width : 100,align : 'center'},
		              {field : 'sendWay',title : '派送方式',width : 150,align : 'center'},
		              {field : 'remark',title : '备注',width : 100,align : 'center'},
		              {field : 'isPay',title : '是否付息',width : 100,align : 'center'},
		              ] ]
	});
	
	$("#transferPlatform").combobox({
		editable:false,
		valueField: 'label',
		textField: 'value',
		data:[{label:'1',value:'富友'},{label:'2',value:'好易联'}]
	});
	
	$("#repaymentMethod").combobox({
		valueField : 'code',
		textField : 'text',
		url:"common/commonAction!findTextArr.action?codeMyid=repayment_mode",
		editable:false,
		method:"get"
	});
	
	$("#pstatus").combobox({
		valueField : 'code',
		textField : 'text',
		url:"common/commonAction!findTextArr.action?codeMyid=prod_status",
		editable:false,
		method:"get"
	});
	
	$("#businessDepartment").combobox({
		valueField : 'code',
		textField : 'text',
		url:"orgz/organizationAction!findOrganizationDept.action?treeLevel=05",
		method:"get",
		filter: function(q, row){
			var opts = $(this).combobox('options');
			return row[opts.textField].indexOf(q) != -1;
		}
	});
	
	$('#productName').combobox({
		valueField : 'code',
		textField : 'text',
	    url:'investProduct/investProductAction!getAllProductCombobox.action',
	    method:"get",
	    filter: function(q, row){
			var opts = $(this).combobox('options');
			return row[opts.textField].indexOf(q) != -1;
		}
    });   
	
});


//重置查询表单
function resetCustomerRepaymentForm(){
	$("#investorRelativeForm").form("clear");
	$("#investorRelativeTab").datagrid('load',{});
}

//执行查询
function executeQuery(){
	$("#investorRelativeTab").datagrid('load',{
			"name":$("#name").val(),
			"transferPlatform" :$("#transferPlatform").combobox("getValue"),
			"bankName":$("#bankName").val(),
			"productName":$("#productName").combobox("getValue"),
			"transferDateStart":$("#transferDateStart").datebox("getValue"),
			"transferDateEnd":$("#transferDateEnd").datebox("getValue"),
			"dueDateStart":$("#dueDateStart").datebox("getValue"),
			"dueDateEnd":$("#dueDateEnd").datebox("getValue"),
			"repaymentMethod":$("#repaymentMethod").combobox("getValue"),
			"mobilePhone":$("#mobilePhone").val(),
			"idNo":$("#idNo").val(),
			"email":$("#email").val(),
			"pstatus":$("#pstatus").combobox("getValue"),
			"contractNo":$("#contractNo").val(),
			"cardNo":$("#cardNo").val(),
			"businessDepartment":$("#businessDepartment").combobox("getValue")
			});
}

// 导出客户详情汇总
function exportInvestorRelativeTab(){
	downFileByFormPost("investorRelativeTabsExport/investorRelativeTabsExportAciton!exportInvestorSummaryStatement.action",
			{
			"name":$("#name").val(),
			"transferPlatform" :$("#transferPlatform").combobox("getValue"),
			"bankName":$("#bankName").val(),
			"productName":$("#productName").combobox("getValue"),
			"transferDateStart":$("#transferDateStart").datebox("getValue"),
			"transferDateEnd":$("#transferDateEnd").datebox("getValue"),
			"dueDateStart":$("#dueDateStart").datebox("getValue"),
			"dueDateEnd":$("#dueDateEnd").datebox("getValue"),
			"repaymentMethod":$("#repaymentMethod").combobox("getValue"),
			"mobilePhone":$("#mobilePhone").val(),
			"idNo":$("#idNo").val(),
			"email":$("#email").val(),
			"pstatus":$("#pstatus").combobox("getValue"),
			"contractNo":$("#contractNo").val(),
			"cardNo":$("#cardNo").val(),
			"businessDepartment":$("#businessDepartment").combobox("getValue"),
			"businessDepartmentName":$("#businessDepartment").combobox("getText")
			});
}

// 导出理财支出预算
function exportInvestExpenditureBudget(){
	downFileByFormPost("investorRelativeTabsExport/investorRelativeTabsExportAciton!exportInvestExpenditureBudget.action",
			{
			"name":$("#name").val(),
			"transferPlatform" :$("#transferPlatform").combobox("getValue"),
			"bankName":$("#bankName").val(),
			"productName":$("#productName").combobox("getValue"),
			"transferDateStart":$("#transferDateStart").datebox("getValue"),
			"transferDateEnd":$("#transferDateEnd").datebox("getValue"),
			"dueDateStart":$("#dueDateStart").datebox("getValue"),
			"dueDateEnd":$("#dueDateEnd").datebox("getValue"),
			"repaymentMethod":$("#repaymentMethod").combobox("getValue"),
			"mobilePhone":$("#mobilePhone").val(),
			"idNo":$("#idNo").val(),
			"email":$("#email").val(),
			"pstatus":$("#pstatus").combobox("getValue"),
			"contractNo":$("#contractNo").val(),
			"cardNo":$("#cardNo").val(),
			"businessDepartment":$("#businessDepartment").combobox("getValue")
			});
}

// 导出临期兑付合同信息
function exportAdventOfPaymentContract(){
	downFileByFormPost("investorRelativeTabsExport/investorRelativeTabsExportAciton!exportAdventOfPaymentContract.action",
			{
			"name":$("#name").val(),
			"transferPlatform" :$("#transferPlatform").combobox("getValue"),
			"bankName":$("#bankName").val(),
			"productName":$("#productName").combobox("getValue"),
			"transferDateStart":$("#transferDateStart").datebox("getValue"),
			"transferDateEnd":$("#transferDateEnd").datebox("getValue"),
			"dueDateStart":$("#dueDateStart").datebox("getValue"),
			"dueDateEnd":$("#dueDateEnd").datebox("getValue"),
			"repaymentMethod":$("#repaymentMethod").combobox("getValue"),
			"mobilePhone":$("#mobilePhone").val(),
			"idNo":$("#idNo").val(),
			"email":$("#email").val(),
			"pstatus":$("#pstatus").combobox("getValue"),
			"contractNo":$("#contractNo").val(),
			"cardNo":$("#cardNo").val(),
			"businessDepartment":$("#businessDepartment").combobox("getValue")
			});
}

</script>
 </head>
	<body>
		<div>
			<div style="padding-top:5px;">
				<font size="3em">[查询条件]</font>
				<form id="investorRelativeForm" method="post">
					<table cellpadding="5px;">
						<tr>
							<th>
								客户姓名
							</th>
							<td>
								<input id="name" name="name" class="easyui-textbox" placeholder="请输入客户姓名"/>
							</td>
							<th>
								手机号
							</th>
							<td>
								<input id="mobilePhone" name="mobilePhone" class="easyui-textbox" placeholder="请输入手机号"/>
							</td>
							<th>
								身份证号
							</th>
							<td>
								<input id="idNo" name="idNo" class="easyui-textbox" placeholder="请输入身份证号" />
							</td>
							<th>
								邮箱
							</th>
							<td>
								<input id="email" name="email" class="easyui-textbox" placeholder="请输入邮箱号"/>
							</td>
							<th>
								理财产品
							</th>
							<td> 
								<input id="productName" name="productName"/>
							</td>
							<th>
								产品状态
							</th>
							<td> 
								<input id="pstatus" name="pstatus" />
							</td>
						</tr>
						
						<tr>
							<th>
								合同编号
							</th>
							<td> 
								<input id="contractNo" name="contractNo" class="easyui-textbox" placeholder="请输入合同编号"/>
							</td>
							<th>
								银行卡号
							</th>
							<td> 
								<input id="cardNo" name="cardNo" class="easyui-textbox" placeholder="请输入银行卡号"/>
							</td>
							<th>
								划扣银行
							</th>
							<td>
								<input id="bankName" name="bankName" class="easyui-textbox" placeholder="请输入银行名称"/>
							</td>
							<th>
								划扣平台
							</th>
							<td>
								<input id="transferPlatform" name="transferPlatform"  />
							</td>
							<th>
								还款方式
							</th>
							<td> 
								<input id="repaymentMethod" name="repaymentMethod" />
							</td>
							<th>
								营业部门
							</th>
							<td> 
								<input id="businessDepartment" name="businessDepartment" />
							</td>
						</tr>
						
						<tr>
							<th>
								划扣日期
							</th>
							<td colspan="3"> 
								<input id="transferDateStart" name="transferDateStart" class="easyui-textbox easyui-datebox" data-options="editable:false" />
							　　至　　
								<input id="transferDateEnd" name="transferDateEnd" class="easyui-textbox easyui-datebox" data-options="editable:false"/>
							</td>
							
							<th>
								到期日期
							</th>
							<td colspan="3"> 
								<input id="dueDateStart" name="dueDateStart" class="easyui-textbox easyui-datebox" data-options="editable:false" />
							　　至　　
								<input id="dueDateEnd" name="dueDateEnd" class="easyui-textbox easyui-datebox" data-options="editable:false"/>
							</td>
						</tr>
					</table>
				</form>
				<div style="text-align:right;">
					<span style="color:red;float:left;padding-top:6px;">
						注:不输入任何查询条件，默认查询所有客户详情
					</span>
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="executeQuery();">执行查询</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-reload" onclick="resetCustomerRepaymentForm();">重置条件</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" onclick="exportInvestorRelativeTab();">导出客户详情汇总</a>
				</div>
			</div>
			
			<div>
				<table id="investorRelativeTab" title="客户详情汇总"></table>
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
