<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="<%=basePath%>">
<title>信审报告</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<jsp:include page="../../layout/script.jsp"></jsp:include>
</head>

<style type="text/css">
	#acceptTaskForm table input{border: none;}
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
	// 信审报告的信息
	var $creditAuditReport = {};
	var count = 0;
	var loanOrderId = '<%=request.getParameter("loanOrderId")%>';
	var loanerId = '<%=request.getParameter("loanerId")%>';
	var appcicationReportData;
	var finalShow = '<%=request.getParameter("isFinalShow")%>';
	$(function(){
		$("input").attr("disabled","disabled");
		$("textarea").attr("disabled","disabled");
		$("#contractLoanAmount").removeAttr("disabled");
		// 获取申请报告的信息
		$.getJSON("creditAuditReport/creditAuditReportAction!findCreditAuditReportByLoanOrderId.action",
			{"loanOrderId":loanOrderId},
			function(data){
				$creditAuditReport = data;
		});
		
		
		$.ajax({
			url:"creditAuditReport/creditAuditReportAction!queryApplicationReportDetail.action",
			type:"post",
			data:{"loanerId":loanerId,"loanOrderId":loanOrderId},
			success:function(data){
				console.info(data);
				appcicationReportData = data;
				$("#loanerCARInfo-form").form("load",data);
			}
		});
		
		//点击TAB页签后加载当前TAB数据 
		$("#applicationReportTabs").tabs({
			 onSelect:function(title,index){
				 if(1==index){
					// 渲染信审报告的工商网和人法网信息
			 		loadCreditAuditReport(appcicationReportData);
			 		// 渲染贷款详情
					loadLoansDetails($creditAuditReport.loansDetails);
					// 渲染信用卡详情
		 			loadCreditCardsDetials($creditAuditReport.creditCardsDetails);
		 			// 渲染征询查询
					loadCreditInvestigations($creditAuditReport.creditInvestigations);
				 }else if(2==index){
					// 渲染财务情况
					loadAccountsJournals($creditAuditReport.accountsJournals);
					$("#accountsJournal-div input").attr("disabled","disabled");
					$("#accountsJournal-div textarea").attr("disabled","disabled");
				 }else if(3==index){
					// 渲染资产信息
					loadAssets($creditAuditReport.assets);
				 }else if(4==index){
					// 渲染资产分析
					loadFirstAuditReport(appcicationReportData)
				 }
			 }
		});

	});
	
	// 渲染信审报告的工商网和人法网信息
	function loadCreditAuditReport(data){
		$("#creditAuditReport-form").form("load",data)
	} 	
	
	// 渲染贷款详情
	function loadLoansDetails(data){
		if(!$.isEmptyObject(data)){
			$("#loansDetails-form").form("load",data);
		}
	}
	
	// 渲染信用卡详情
	function loadCreditCardsDetials(data){
		if(!$.isEmptyObject(data)){
			$("#creditCardsDetails-form").form("load",data);
		}
		
	} 
	
	// 渲染征询查询
	function  loadCreditInvestigations(data){
		if(!$.isEmptyObject(data)){
			for(var i=0;i<data.length;i++){
				var creditInvestigation = data[i];
	 			$("#creditInvestigation-form-"+creditInvestigation.queryInterval).form("load",creditInvestigation);
			}
		}
	}	
	
	// 渲染财务情况
	function loadAccountsJournals(data){
		// 对公
		var publicOriented =[];
		// 对私
		var privateOriented= [];
		// 判断对象是否为空,为空的时候显示默认的
		if(!$.isEmptyObject(data)){
			// 区分对公对私
			for(var i=0;i<data.length;i++){
				var accountsJournals = data[i];
				if (accountsJournals.bankaccountType == "corporate_oriented"){
					$("#accountsJournal-corporate-div").empty();
					publicOriented.push(accountsJournals);
				}else{
					$("#accountsJournal-private-div").empty();
					privateOriented.push(accountsJournals);
				}
			}
			
			// 对公
			for(var i=0;i<publicOriented.length;i++){
				var accountsJournals = publicOriented[i];
				var accountsJournalForm = getAccountsJournalForm(accountsJournals.bankaccountType,count)
			  	$("#accountsJournal-corporate-div").append(accountsJournalForm);
				var id="#accountsJournal-form-"+accountsJournals.bankaccountType+"-"+count;
			  	$.parser.parse(id);  
				$(id).form("load", accountsJournals);
				count++;
			}
			
			// 对私
			for(var i=0;i<privateOriented.length;i++){
				var accountsJournals = privateOriented[i];
				var accountsJournalForm = getAccountsJournalForm(accountsJournals.bankaccountType,count)
			  	$("#accountsJournal-private-div").append(accountsJournalForm);
				var id="#accountsJournal-form-"+accountsJournals.bankaccountType+"-"+count;
			  	$.parser.parse(id);  
				$(id).form("load", accountsJournals);
				count++;
			}
			
		}
	}
	
	// 渲染资产信息
	function loadAssets(data){
		if(!$.isEmptyObject(data)){
 			$("#assets-form").form("load",data);
		}
	}
	
	// 渲染资产分析
	function loadFirstAuditReport(data){
		if(!$.isEmptyObject(data)){
 			$("#firstAuditReport-form").form("load",data);
		}
		if("1"==finalShow){
			$("#finalAuditReportForm").form("load",appcicationReportData);
		}else{
			$("#finalAuditReportForm").remove();
		}
	}
	
	// 获取账务信息流水对Form-html
	function getAccountsJournalForm(type,index){
		var title = "";
		if(type=="private_oriented"){
			title="对私卡";
		}else{
			title="对公卡";
		}
		var accountsJournalFormStr= 
			"<form id='accountsJournal-form-"+type+"-"+index+"' method='post'>"
	  		+"	<input name='bankFlowId' hidden='true' class='easyui-validatebox'>"
		  	+"	<input name='bankaccountType' hidden='true' class='easyui-validatebox' value='"+type+"'>"
		  	+"	<table class='table' style='margin-top: 10px;width:100%;' cellpadding='5px;'>"
		  	+"		<tr>"
		  	+"			<td colspan='7'><span style='font-weight: bold;font-size: 14px;width:60px;'>[流水信息("+title+")]</span></td>"
		  	+"		</tr>"
		  	+"		<tr>"
		  	+"			<th>账号:</th>"
		  	+"			<td><input name='bankaccountNo' style='width: 100px;' class='easyui-validatebox easyui-textbox' data-options='required:true'/></td>"
		  	+"		</tr>"
		  	+"		<tr>"
		  	+"			<th>[入账月份:月]</th>"
		  	+"			<td><input name='monName01' style='width: 100px;'  class='easyui-validatebox' /></td>"
		  	+"			<td><input name='monName02' style='width: 100px;'  class='easyui-validatebox' /></td>"
		  	+"			<td><input name='monName03' style='width: 100px;'  class='easyui-validatebox' /></td>"
		  	+"			<td><input name='monName04' style='width: 100px;'  class='easyui-validatebox' /></td>"
		  	+"			<td><input name='monName05' style='width: 100px;'  class='easyui-validatebox' /></td>"
		  	+"			<td><input name='monName06' style='width: 100px;'  class='easyui-validatebox' /></td>"
		  	+"		</tr>"
		  	+"			<tr>"
		  	+"			<th>[入账金额:元]</th>"
		  	+"			<td><input name='income01' style='width: 100px;' class='easyui-validatebox easyui-numberbox' data-options='min:0,precision:2'/></td>"
		  	+"			<td><input name='income02' style='width: 100px;' class='easyui-validatebox easyui-numberbox' data-options='min:0,precision:2'/></td>"
		  	+"			<td><input name='income03' style='width: 100px;' class='easyui-validatebox easyui-numberbox' data-options='min:0,precision:2'/></td>"
		  	+"			<td><input name='income04' style='width: 100px;' class='easyui-validatebox easyui-numberbox' data-options='min:0,precision:2'/></td>"
		  	+"			<td><input name='income05' style='width: 100px;' class='easyui-validatebox easyui-numberbox' data-options='min:0,precision:2'/></td>"
		  	+"			<td><input name='income06' style='width: 100px;' class='easyui-validatebox easyui-numberbox' data-options='min:0,precision:2'/></td>"
		  	+"		</tr>"
		  	+"		<tr>"
		  	+"			<th>月均:</th>"
		  	+"			<td><input name='averageIncome' style='width: 100px;' class='easyui-validtebox easyui-numberbox' data-options='min:0,precision:2,required:true'/>元</td>"
		  	+"		</tr>"
		  	+"		<tr>"
		  	+"			<th>流水分析:</th>"
		  	+"			<td colspan='7'><textarea name='analysis' class='easyui-validatebox easyui-textbox' data-options='required:true' style='width: 95%;height:70px;'></textarea></td>"
		  	+"		</tr>"
		  	+"	</table>"
		  	+"</form>"
		  	
		return accountsJournalFormStr;
	}
	
	// 计算金额
	function calculate() {
		// 合同金额
		var contractLoanAmount = Number($("#contractLoanAmount").val());
		// 贷款期限
		var loanPeriodType = Number($("#loanPeriodType").val());
		// 月服务汇率
		var monthServiceFeeRate = Number(parseFloat($("#monthServiceFeeRate").val()) / 100.0);
		// 利息
		var loanInterestRate = Number($("#loanInterestRate").val());
		// 信访费用
		var visitFee = Number($("#visitFee").val());
		// 计算金额
		if (contractLoanAmount != "" && loanPeriodType != ""
				&& monthServiceFeeRate != "" && loanInterestRate != ""
				&& visitFee != "") {
			// 实放金额 = 合同金额-(合同金额*(服务汇率*贷款期限)+信访费用)
			var actualLoanAmount = contractLoanAmount
					- (contractLoanAmount
							* (monthServiceFeeRate * loanPeriodType) + visitFee);
			// 月还款额 = (合同金额/贷款期限)+合同金额*利息
			var monthRepay = (contractLoanAmount / loanPeriodType)
					+ contractLoanAmount * loanInterestRate;
			// 保留两位有效数字
			actualLoanAmount = Math.round(actualLoanAmount*100)/100;
			monthRepay = Math.round(monthRepay*100)/100;
			$("#actualLoanAmount").val(actualLoanAmount);
			$("#finalmonthRepay").val(monthRepay);
		}
	}
	
	// 保存资质分析的信息
	function updateFinalAuditReport(result,object){
		// 确认是否提交
		$.messager.confirm('提示', '您确定修改贷款申请额度？', function(r){
			if (r){
				$.post("finalAuditReportAction/finalAuditReportAction!updateFinalAuditReport.action", 
						{ finaId:$("#finaId").val(),
						  actualLoanAmount: $("#actualLoanAmount").val(), 
						  monthRepay: $("#finalmonthRepay").val(),
						  contractLoanAmount:$("#contractLoanAmount").val()},
						   function(data){
							if(data.status){
								$.messager.show({
									title:'提示',
									msg:data.message,
									timeout:5000,
									showType:'slide'
								});
							}else{
								$.messager.alert('提示',data.message,'error');
							}
				   });
			}
		});
	}

</script>

<!-- 申请报告 S -->
<div id="applicationReportTabs" class="easyui-tabs" style="fit:true;">
	<!-- 客户的基本信息 -->
	<div title="客户基本信息">
		<form id="loanerCARInfo-form" style="height:580px;">
			<table class="table" style="margin-top: 10px;width:100%;" cellpadding="5px;">
				<tr>
					<td colspan="2"><span style="font-weight: bold;font-size: 14px;">[客户基本信息详情]</span></td>
				</tr>						
				<tr>
					<th>客户姓名:</th>
					<td><input  name="name"  class="easyui-textbox" /></td>
					<th>身份证号:</th>
					<td><input  name="idNo" class="easyui-textbox" /></td>
				</tr>
				<tr>
					<th>申贷金额:</th>
					<td><input  name="loanAmount" class="easyui-textbox" />元</td>
					<th>贷款用途:</th>
					<td><input  name="purpose" class="easyui-textbox" /></td>
				</tr>
				<tr>
					<th>还款方式:</th>
					<td><input  name="repayMethodText" class="easyui-textbox" /></td>
				</tr>
			</table>
		</form>
	</div>
	
	<!-- 征询情况 -->
	<div title="征信情况">
		<div style="height:580px;overflow: auto;">
		<form id="creditAuditReport-form" method="post">
			<input name="carId" hidden="true" >
			<table class="table" style="margin-top: 10px;width:100%;" cellpadding="5px;">
				<tr>
					<td colspan="5"><span style="font-weight: bold;font-size: 14px;">[网查询]</span></td>
				</tr>
				<tr>
					<th>人法网:</th>
					<td><input name="lawQueryStatusText" class="easyui-textbox" /></td>
					<th>工商网:</th>
					<td><input name="comCreditStatusText"  class="easyui-textbox"/></td>
					<th>进件城市:</th>
					<td><input  name="loanCity" class="easyui-textbox" /></td>
				</tr>
			</table>
		</form>
		
		<hr>
		
		<!-- 贷款详情 -->
		<form id="loansDetails-form" method="post">
			<input name="existLoanId" hidden="true" class="">
			<table class="table" style="margin-top: 10px;width:100%;" cellpadding="5px;">
				<tr>
					<td colspan="6"><span style="font-weight: bold;font-size: 14px;">[贷款详情]</span></td>
				</tr>
				<tr>
					<th>贷款总笔数:</th>
					<td><input name="totalLoanCount" class=" easyui-textbox" /></td>
					<th>年内逾期:</th>
					<td><input name="yearOverdue" class=" easyui-textbox" /></td>
					<th>未结清贷款总额:</th>
					<td><input name="outstandingSum" class=" easyui-textbox" />元</td>
				</tr>
				<tr>
					<th>未结清笔数:</th>
					<td><input name="outstandingCount" class=" easyui-textbox" /></td>
					<th>累计逾期:</th>
					<td><input name="cumulativeOverdue" class=" easyui-textbox" /></td>
					<th>未结清贷款余额:</th>
					<td><input name="outstandingBalance" class=" easyui-textbox"/>元	</td>
				</tr>
				<tr>
					<th>逾期率:</th>
					<td><input name="overdueRate"  class="easyui-textbox" /></td>
					<th>月还额度:</th>
					<td><input name="monthRepay"  class=" easyui-textbox" /></td>
				</tr>
				<tr>
					<th>最近一笔贷款详情:</th>
					<td colspan="6"><textarea name="lastLoanSpace" class="easyui-textbox"  style="width: 95%;height:70px;"></textarea></td>
				</tr>
				<tr>
					<th>备注:</th>
					<td colspan="6"><textarea name="loanDetail" class="easyui-textbox"  style="width: 95%;height:70px;"></textarea></td>
				</tr>
			</table>
		</form>
		
		<hr>
		
		<!-- 信用卡详情 -->
		<form id="creditCardsDetails-form" method="post">
			<input name="cardInfoId" hidden="true" class="">
			<table class="table" style="margin-top: 10px;width:100%;" cellpadding="5px;">
				<tr>
					<td colspan="8"><span style="font-weight: bold;font-size: 14px;width:60px;">[信用卡详情]</span></td>
				</tr>
				<tr>
					<th>总卡数:</th>
					<td ><input name="cardCount" style="width: 100px;" class=" easyui-textbox" /></td>
					<th>在用卡数:</th>
					<td><input name="cardInUse" style="width: 100px;" class=" easyui-textbox" /></td>
					<th>逾期卡数:</th>
					<td><input name="overdueCardCount" style="width: 100px;" class=" easyui-textbox" /></td>
					<th >逾期比例:</th>
					<td ><input name="overdueRatio" style="width: 100px;" class="easyui-textbox" />%</td>
				</tr>
				<tr>
					<th>授信总额:</th>
					<td><input name="creditTotalAmount" style="width: 100px;" class=" easyui-textbox" />元</td>
					<th>使用额度:</th>
					<td><input name="creditLimit" style="width: 100px;" class=" easyui-textbox" />元</td>
					<th>最高额度:</th>
					<td><input name="maxLimit" style="width: 100px;" class=" easyui-textbox" />元</td>
					<th>月还额度:</th>
					<td><input name="monthRepay" style="width: 100px;" class=" easyui-textbox" />元</td>
				</tr>
				<tr>
					<th>年内逾期:</th>
					<td><input name="yearOverdue" style="width: 100px;"  class=" easyui-textbox" /></td>
					<th>累计逾期:</th>
					<td><input name="cumulativeOverdue" style="width: 100px;"  class=" easyui-textbox" /></td>
					<th>使用年限:</th>
					<td><input name="validateYear" style="width: 100px;"  class="easyui-textbox" /></td>
					<th>最高期数:</th>
					<td><input name="maxQishu" style="width: 100px;"  class=" easyui-textbox" /></td>
				</tr>
				<tr>
					<th>使用率:</th>
					<td><input name="useRate" style="width: 100px;"  class="easyui-textbox" />%</td>
					<th>逾期率:</th>
					<td><input name="overdueRate" style="width: 100px;"  class="easyui-textbox" />%</td>
				</tr>
				<tr>
					<th>明细:</th>
					<td colspan="8"><textarea name="detail" style="width: 95%;height:70px;" class=" " ></textarea></td>
				</tr>
			</table>
		</form>
		
		<hr>
		
		<!-- 征信查询3个月 -->
		<form id="creditInvestigation-form-A" method="post">
			<input name="creditRefId" hidden="true" class="">
			<input name="queryInterval" hidden="true" class="" value="A">
			<table class="table" style="margin-top: 10px;width:100%;" cellpadding="5px;">
				<tr>
					<td colspan="9"><span style="font-weight: bold;font-size: 14px;width:60px;">[征信查询3个月]</span></td>
				</tr>
				<tr>
					<th>[3个月]</th>
					<th>本人查询:</th>
					<td><input name="selfQuery" style="width: 100px;" class=" easyui-textbox" /></td>
					<th>贷款审批:</th>
					<td><input name="loanApproval" style="width: 100px;" class=" easyui-textbox"/></td>
					<th>信用卡审批:</th>
					<td><input name="creditCardApproval" style="width: 100px;" class=" easyui-textbox"/></td>
					<th>互联网查询:</th>
					<td><input name="internetQuery" style="width: 100px;" class=" easyui-textbox"/></td>
				</tr>
				<tr>
					<th>备注:</th>
					<td colspan="9"><textarea name="queryNotes" class="" style="width: 95%;height:70px;"></textarea></td>
				</tr>
			</table>
		</form>
		
		<hr>
		
		<!-- 征信查询6个月 -->
		<form id="creditInvestigation-form-B" method="post">
			<input name="creditRefId" hidden="true" class="">
			<input name="queryInterval" hidden="true" class="" value="B">
			<table class="table" style="margin-top: 10px;width:100%;" cellpadding="5px;">
				<tr>
					<td colspan="9"><span style="font-weight: bold;font-size: 14px;width:120px;">[征信查询6个月]</span></td>
				</tr>
				<tr>
					<th>[6个月]</th>
					<th>本人查询:</th>
					<td><input name="selfQuery" style="width: 100px;" class=" easyui-textbox" /></td>
					<th>贷款审批:</th>
					<td><input name="loanApproval" style="width: 100px;" class=" easyui-textbox" /></td>
					<th>信用卡审批:</th>
					<td><input name="creditCardApproval" style="width: 100px;" class=" easyui-textbox" /></td>
					<th>互联网查询:</th>
					<td><input name="internetQuery" style="width: 100px;" class=" easyui-textbox" /></td>
				</tr>
				<tr>
					<th>备注:</th>
					<td colspan="9"><textarea name="queryNotes" class="" style="width: 95%;height:70px;"></textarea></td>
				</tr>
			</table>
		</form>
		</div>
	</div>
	
	<!-- 财务情况 -->
	<div id="accountsJournal-div" title="财务情况">
		<div class="easyui-accordion" style="fit:true;height: 580px;">
		    <div title="流水信息(对公)" data-options="selected:true" style="overflow: auto;"> 
				<div id="accountsJournal-corporate-div">
					<div id="noMessage" style="width: 100%;height:250px;text-align: center;padding-top:250px;">
						<font size="6" style="font-weight: bold;box-shadow: 3px 3px 5px 3px;">
							暂无流水信息(对公)
						</font>
					</div>  
				</div>
			</div>
		    <div title="流水信息(对私)">  
				<div id="accountsJournal-private-div" style="overflow: auto;">
					<div id="noMessage" style="width: 100%;height:250px;text-align: center;padding-top:250px;">
						<font size="6" style="font-weight: bold;box-shadow: 3px 3px 5px 3px;">
							暂无流水信息(对私)
						</font>
					</div>  
				</div>
			</div>
		</div>
	</div>
	
	<!-- 资产 -->
	<div title="资产">
		<form id="assets-form" method="post" style="height:580px;">
			<input name="assetId" class="" hidden="true">
			<table class="table" style="margin-top: 10px;width:100%;" cellpadding="5px;">
				<tr>
					<td colspan="4"><span style="font-weight: bold;font-size: 14px;width:60px;">[资产详情]</span></td>
				</tr>
				<tr>
					<th>房产:</th>
					<td><input name="realEstate" class="easyui-textbox" /></td>
					<th>车产:</th>
					<td><input name="vehicle" class="easyui-textbox" /></td>
				</tr>
				<tr>
					<th>同行业:</th>
					<td><input name="theSameIndustry" class="easyui-textbox" /></td>
				</tr>
			</table>
		</form>
	</div>
	
	<!-- 资质分析 -->
	<div title="资质分析">
		<form id="firstAuditReport-form" method="post" style="height:580px;">
			<input name="firsId" hidden="true" class="">
			<table class="table" style="margin-top: 10px;width:100%;" cellpadding="5px;">
				<tr>
					<td colspan="6"><span style="font-weight: bold;font-size: 14px;width:60px;">[初审资质分析详情]</span></td>
				</tr>
				<tr>
					<th>行业类型:</th>
					<td><input name="industryType" style="width:100px;" class="" /></td>
					<th>经营年限:</th>
					<td><input name="comOperDuration" style="width:100px;" class="easyui-textbox" />年</td>
					<th>经营状态:</th>
					<td><input name="comOperStatusText" style="width: 100px;"class=" easyui-textbox"/></td>
				</tr>
				<tr>
					<th>婚姻情况:</th>
					<td><input name="marriageTypeText" style="width: 100px;" class=" easyui-textbox"/></td>
					<th>是否本地:</th>
					<td style="width: 100px;"><input name="isLocalResText" style="width: 100px;" class=" easyui-textbox"/></td>
					<th>信用情况:</th>
					<td><input name="creditStatusText" style="width: 100px;" class=" easyui-textbox" /></td>
				</tr>
				<tr>
					<th>电核情况:</th>
					<td><input name="phoneCheckStatusText" style="width: 100px;" class="easyui-textbox"/></td>
					<th>信访情况:</th>
					<td><input name="visitStatusText" style="width: 100px;" class="easyui-textbox"/></td>
					<th>资质总评:</th>
					<td><input name="qulificationStatusText" style="width: 100px;" class="easyui-textbox"/><!-- <a href="#">查看细则</a></td> -->
				</tr>
				<tr>
					<th>备注:</th>
					<td colspan="6"><textarea name="note" style="width:600px;height:70px;" class="easyui-textbox" ></textarea></td>
				</tr>
				<tr>
					<th>初审人:</th>
					<td>
						<input  name="firsPersonnelName" class="easyui-textbox" />
					</td>
					<th>初审日期</th>
					<td><input  name="firsDate"  class="easyui-textbox" /></td>
				</tr>
				<tr>
					<th>初审人员意见:</th>
					<td colspan="6"><textarea name="firsPersSugg" style="width:600px;height:70px;" class="easyui-textbox" ></textarea></td>
				</tr>
				<tr>
					<th>初审部门意见:</th>
					<td colspan="6"><textarea name="firsDepSugg" style="width:600px;height:70px;" class="easyui-textbox" ></textarea></td>
				</tr>
				
				<tr>
					<th>初审资质分析说明:</th>
					<td colspan="6"><textarea name="firstDescription" style="width:600px;height:70px;" class="easyui-textbox" ></textarea></td>
				</tr>
			</table>
		</form>
		
		<form id="finalAuditReportForm" method="post">
			<input id="finaId" name="finaId" hidden="true">
			<table class="table" style="margin-top: 10px;width:100%;" cellpadding="5px;">
				<tr>
					<td colspan="6"><span style="font-weight: bold;font-size: 14px;width:60px;">[终审资质分析详情]</span></td>
				</tr>
				<tr>
					<th>合同金额:</th>
					<td><input id="contractLoanAmount" name="contractLoanAmount"  class="easyui-textbox" onblur="calculate()"/>元</td>
					<th>贷款期限:</th>
					<td><input id="loanPeriodType" name="loanPeriodTypeText"  class="easyui-textbox"/>月</td>
					<th>月服务汇率:</th>
					<td><input id="monthServiceFeeRate" name="monthServiceFeeRateText" class="easyui-textbox"/></td>
				</tr>
				<tr>
					<th>利息:</th>
					<td><input id="loanInterestRate" name="loanInterestRate"  class="easyui-textbox" /></td>
					<th>信访费用:</th>
					<td><input id="visitFee" name="visitFee"  class="easyui-textbox"  />元</td>
				</tr>
				<tr>
					<th>实放金额:</th>
					<td><input id="actualLoanAmount" name="actualLoanAmount"  class="easyui-textbox" />元</td>
					<th>月还款额:</th>
					<td><input id="finalmonthRepay" name="finalmonthRepay"  class="easyui-textbox" /></td>
				</tr>
				<tr>
					<th>终审人:</th>
					<td>
						<input name="finaPersonnelName" class="easyui-textbox" />
					</td>
					<th>终审日期</th>
					<td><input name="finaDate"  class="easyui-textbox" /></td>
				</tr>
				<tr>
					<th>终审人员意见:</th>
					<td colspan="6"><textarea name="finaPersSugg" style="width:600px;height:70px;" class="easyui-textbox" ></textarea></td>
				</tr>
				<tr>
					<th>终审资质分析说明:</th>
					<td colspan="6"><textarea name="finalDescription" style="width:600px;height:70px;" class="easyui-textbox" ></textarea></td>
				</tr>
				<tr>
					<td colspan="6" style="text-align: right;">
						<a href="javascript:void(0);" onclick="updateFinalAuditReport();"	class="easyui-linkbutton" iconCls="icon-save">保存</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>
<!-- 申请报告 E -->