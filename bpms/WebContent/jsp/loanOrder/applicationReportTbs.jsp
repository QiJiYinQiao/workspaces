<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

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
	$(function(){
		
		// 获取申请报告的信息
		$.getJSON("creditAuditReport/creditAuditReportAction!findCreditAuditReportByLoanOrderId.action",
			{"loanOrderId":$row.loanOrderId},
			function(data){
				$creditAuditReport = data;
		});
		
		//点击TAB页签后加载当前TAB数据 
		$("#applicationReportTabs").tabs({
			 onSelect:function(title,index){
				 if(0==index){
					// 渲染客户的基本信息
					$("#loanerCARInfo-form").form("load",{
						"name":$row.name,
						"idNo":$row.idNo,
						"loanAmount":$row.loanAmount,
						"purpose":$row.purpose,
						"repayMethod":$row.repayMethod
					});
				 }
				 else if(1==index){
					// 渲染信审报告的工商网和人法网信息
			 		loadCreditAuditReport($creditAuditReport);
			 		// 渲染贷款详情
					loadLoansDetails($creditAuditReport.loansDetails);
					// 渲染信用卡详情
		 			loadCreditCardsDetials($creditAuditReport.creditCardsDetails);
		 			// 渲染征询查询
					loadCreditInvestigations($creditAuditReport.creditInvestigations);
				 }else if(2==index){
					// 渲染财务情况
					loadAccountsJournals($creditAuditReport.accountsJournals);
				 }else if(3==index){
					// 渲染资产信息
					loadAssets($creditAuditReport.assets);
				 }else if(4==index){
					// 渲染资产分析
					loadFirstAuditReport($creditAuditReport.firstAuditReport)
				 }
			 }
		});

	   // 渲染还款方式下拉框
	   $("input[name='repayMethod']").combobox({
			url : "common/commonAction!findTextArr.action?codeMyid=repay_method",
			valueField : 'code',
			textField : 'text',
			onLoadSuccess : function(){
            	var val = $(this).combobox("getData");
 				if(!$.isEmptyObject(val)){
                  	$(this).combobox("select", val[0]["code"]);
                }
            }
	   });
	   
	   // 渲染人法网下拉框
	   $("input[name='lawQueryStatus']").combobox({
			url : "common/commonAction!findTextArr.action?codeMyid=law_query_status",
			valueField : 'code',
			textField : 'text',
			onLoadSuccess : function(){
            	var val = $(this).combobox("getData");
 				if(!$.isEmptyObject(val)){
                  	$(this).combobox("select", val[0]["code"]);
                }
			}
	   });
	   
	   // 渲染工商网的下拉框
	   $("input[name='comCreditStatus']").combobox({
			url : "common/commonAction!findTextArr.action?codeMyid=com_credit_status",
			valueField : 'code',
			textField : 'text',
			onLoadSuccess : function(){
            	var val = $(this).combobox("getData");
 				if(!$.isEmptyObject(val)){
                  	$(this).combobox("select", val[0]["code"]);
                }
			}
	   });
		
	   // 渲染月份的下拉框
		$("input[name^='monName0']").combobox({
			valueField : 'id',
			textField : 'text',
			data : [ {"id" : 1,"text" : "1月"},
			         {"id" : 2,"text" : "2月"},
			         {"id" : 3,"text" : "3月"},
			         {"id" : 4,"text" : "4月"},
			         {"id" : 5,"text" : "5月"},
			         {"id" : 6,"text" : "6月"},
			         {"id" : 7,"text" : "7月"},
			         {"id" : 8,"text" : "8月"},
			         {"id" : 9,"text" : "9月"},
			         {"id" : 10,"text" : "10月"},
			         {"id" : 11,"text" : "11月"},
			         {"id" : 12,"text" : "12月"} ]
		});
		
	   // 渲染是否本地的下拉框
	   $("input[name='isLocalRes']").combobox({
			width:171,
			url:"common/commonAction!findTextArr.action?codeMyid=is_local_resident",
			valueField: 'code',
			textField: 'text',
			onLoadSuccess : function(){
            	var val = $(this).combobox("getData");
 				if(!$.isEmptyObject(val)){
                  	$(this).combobox("select", val[0]["code"]);
                }
             }
		});
	   
	   // 渲染婚姻状况的下拉框
	   $("input[name='marriageType']").combobox({
			width:171,
			url:"common/commonAction!findTextArr.action?codeMyid=marriage_type",
			valueField: 'code',
			textField: 'text',
			onLoadSuccess : function(){
            	var val = $(this).combobox("getData");
 				if(!$.isEmptyObject(val)){
                  	$(this).combobox("select", val[0]["code"]);
                }
			}
		});
	   
	   // 渲染经营状态的下拉框
	   $("input[name='comOperStatus']").combobox({
			width:171,
			url:"common/commonAction!findTextArr.action?codeMyid=com_oper_status",
			valueField: 'code',
			textField: 'text',
			onLoadSuccess : function(){
            	var val = $(this).combobox("getData");
 				if(!$.isEmptyObject(val)){
                  	$(this).combobox("select", val[0]["code"]);
                }
			}
		});
	   
	   // 渲染信用情况的下拉框
	   $("input[name='creditStatus']").combobox({
			width:171,
			url:"common/commonAction!findTextArr.action?codeMyid=credit_status",
			valueField: 'code',
			textField: 'text',
			onLoadSuccess : function(){
            	var val = $(this).combobox("getData");
 				if(!$.isEmptyObject(val)){
                  	$(this).combobox("select", val[0]["code"]);
                }
			}
		});
	   
	   // 渲染电核情况的下拉框
	   $("input[name='phoneCheckStatus']").combobox({
			width:171,
			url:"common/commonAction!findTextArr.action?codeMyid=phone_check_status",
			valueField: 'code',
			textField: 'text',
			onLoadSuccess : function(){
            	var val = $(this).combobox("getData");
 				if(!$.isEmptyObject(val)){
                  	$(this).combobox("select", val[0]["code"]);
                }
			}
		});
	   
	   // 渲染信访情况的下拉框
	   $("input[name='visitStatus']").combobox({
			width:171,
			url:"common/commonAction!findTextArr.action?codeMyid=visit_status",
			valueField: 'code',
			textField: 'text',
			onLoadSuccess : function(){
            	var val = $(this).combobox("getData");
 				if(!$.isEmptyObject(val)){
                  	$(this).combobox("select", val[0]["code"]);
                }
			}
		});
	   
	   // 渲染资质从平的下拉框
	   $("input[name='qulificationStatus']").combobox({
			width:171,
			url:"common/commonAction!findTextArr.action?codeMyid=qulification_status",
			valueField: 'code',
			textField: 'text',
			onLoadSuccess : function(){
            	var val = $(this).combobox("getData");
 				if(!$.isEmptyObject(val)){
                  	$(this).combobox("select", val[0]["code"]);
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
		//计算入账月份，订单开始日期往前倒推6个月 
		var months = $row.createDate;
		var m = parseInt(months.substring(5,7))-1;
		var i = 0 ;
		var month = new Array();
		for(; m >= 0; m--){
			if(m == 0){
				m = 12;
			}
			month[i] = m;
			i++;
			if(i == 6){
				break;
			}
		}
		// 对公
		var publicOriented =[];
		// 对私
		var privateOriented= [];
		// 判断对象是否为空,为空的时候显示默认的
		if(!$.isEmptyObject(data)){
			$("#accountsJournal-corporate-div").empty();
			$("#accountsJournal-private-div").empty();
			// 区分对公对私
			for(var i=0;i<data.length;i++){
				var accountsJournals = data[i];
				if (accountsJournals.bankaccountType == "corporate_oriented"){
					publicOriented.push(accountsJournals);
				}else{
					privateOriented.push(accountsJournals);
				}
			}
			
			// 对公
			for(var i=0;i<publicOriented.length;i++){
				var accountsJournals = data[i];
				var accountsJournalForm = getAccountsJournalForm(accountsJournals.bankaccountType,count,month)
			  	$("#accountsJournal-corporate-div").append(accountsJournalForm);
				var id="#accountsJournal-form-"+accountsJournals.bankaccountType+"-"+count;
			  	$.parser.parse(id);  
				$(id).form("load", accountsJournals);
				count++;
			}
			
			// 对私
			for(var i=0;i<privateOriented.length;i++){
				var accountsJournals = data[i];
				var accountsJournalForm = getAccountsJournalForm(accountsJournals.bankaccountType,count,month)
			  	$("#accountsJournal-private-div").append(accountsJournalForm);
				var id="#accountsJournal-form-"+accountsJournals.bankaccountType+"-"+count;
			  	$.parser.parse(id);  
				$(id).form("load", accountsJournals);
				count++;
			}
			
		}
		
		//	如果不存在对公,显示对公信息
		if(publicOriented.length == 0){
			$("#accountsJournal-corporate-div").empty();
			var accountsJournalForm= getAccountsJournalForm("corporate_oriented",count,month);
		  	$("#accountsJournal-corporate-div").append(accountsJournalForm);
			var id="#accountsJournal-form-corporate_oriented-"+count;
		  	$.parser.parse(id);  
		  	count++;
		}
		
		// 如果不存在对私,显示对私信息
		if(privateOriented.length == 0){
			$("#accountsJournal-private-div").empty();
			var accountsJournalForm= getAccountsJournalForm("private_oriented",count,month);
		  	$("#accountsJournal-private-div").append(accountsJournalForm);
			var id="#accountsJournal-form-private_oriented-"+count;
		  	$.parser.parse(id);  
		  	count++;
		}
	}
	
	// 获取账务信息流水对Form-html
	function getAccountsJournalForm(type,index,month){
		var title = "";
		if(type=="private_oriented"){
			title="对私卡";
		}else{
			title="对公卡";
		}
		var accountsJournalFormStr= 
			"<form id='accountsJournal-form-"+type+"-"+index+"' method='post'>"
			+"	<a  href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon-add' plain='true'  href='javascript:void(0);'  onclick=\"addAccountsJournalForm(this,'"+type+"');\">添加</a>"
			+"	<a  href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon-remove' plain='true'  href='javascript:void(0);'  onclick='removeAccountsJournalForm(this);'>删除</a>"
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
		  	+"			<td><input name='monName01' style='width: 100px;' readonly='readonly' class='easyui-validatebox' value='"+month[5]+"'/></td>"
		  	+"			<td><input name='monName02' style='width: 100px;' readonly='readonly' class='easyui-validatebox' value='"+month[4]+"'/></td>"
		  	+"			<td><input name='monName03' style='width: 100px;' readonly='readonly' class='easyui-validatebox' value='"+month[3]+"'/></td>"
		  	+"			<td><input name='monName04' style='width: 100px;' readonly='readonly' class='easyui-validatebox' value='"+month[2]+"'/></td>"
		  	+"			<td><input name='monName05' style='width: 100px;' readonly='readonly' class='easyui-validatebox' value='"+month[1]+"'/></td>"
		  	+"			<td><input name='monName06' style='width: 100px;' readonly='readonly' class='easyui-validatebox' value='"+month[0]+"'/></td>"
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
		  	+"		<tr>"
		  	+"			<td style='text-align: right;' colspan='7' align='right'>"
		  	+"               <a href='javascript:void(0);' onclick='saveAccountsJournal(this)' class='easyui-linkbutton' iconCls='icon-save'>保存</a>"
		  	+"			</td>"
		  	+"		</tr>"
		  	+"	</table>"
		  	+"</form>"
		  	
		return accountsJournalFormStr;
	}
	
	// 删除元素
	function removeAccountsJournalForm(obj){
		var size = $(obj).parent().parent().children("form").length;
		if(size == 1){
			$.messager.alert("提示","对公对私至少各一个!","warning")
			return false;
		}else{
			var bankFlowId = $(obj).parent().find("input[name='bankFlowId']").val();
			if(""==bankFlowId){
				$(obj).parent().remove();
			}else{
				$.ajax({
					url : "accountsJournal/accountsJournalAction!deleteAccountJournal.action",
					data : {"bankFlowId":bankFlowId},
					type : "post",
					success : function(data){
						if(data.status){
							$.messager.show({
								title:data.title,
								msg:data.message,
								timeout:3000
							});
							$(obj).parent().remove();
						}else{
							$.messager.alert(data.title,data.message,"error");
						}
					}
				});
			}
		}
	}
	
	// 增加
	function addAccountsJournalForm(obj,type){
		$(obj).parent().after(getAccountsJournalForm(type,count));
		var id="#accountsJournal-form-"+type+"-"+count;
	  	$.parser.parse(id);  
		count++;
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
	}
	
	// 提示信息
	function alertMsg(data) {
		if (data.status) {
			$.messager.show({
				title : data.title,
				msg : data.message,
				timeout : 5000,
				showType : 'slide'
			})
		} else {
			$.messager.alert(data.title, data.message, 'error');
		}

	}
	
	// 保存信申报告信息
	function saveCreditAuditReport(obj){
		$(obj).parents("form:first").form('submit', {
			url : "creditAuditReport/creditAuditReportAction!saveCreditAuditReport.action",
			onSubmit : function(param) {
				var isValid = $(this).form('validate');
				if (isValid){
					param.carId = $creditAuditReport.carId;
					param.loanOrderId = $row.loanOrderId;
				}
				return isValid; // 返回false终止表单提交
			},
			success : function(data) {
				data = $.parseJSON(data);
				loadCreditAuditReport(data.data);
				alertMsg(data);
				$.messager.progress('close'); // 如果提交成功则隐藏进度条
			}
		});
	}

	// 保存贷款信息
	function saveLoansDetails(obj) {
		$(obj).parents("form:first").form('submit', {
			url : "loansDetails/loansDetailsAction!saveLoanDetails.action",
			onSubmit : function(param) {
				var isValid = $(this).form('validate');
				if (isValid){
					param.carId = $creditAuditReport.carId;
				}
				return isValid; // 返回false终止表单提交
			},
			success : function(data) {
				data = $.parseJSON(data);
				loadLoansDetails(data.data);
				alertMsg(data);
				$.messager.progress('close'); // 如果提交成功则隐藏进度条
			}
		});

	}

	// 保存信用卡信息
	function saveCreditCardsDetails(obj) {
		$(obj).parents("form:first").form('submit',{
			url : "creditCardsDetails/creditCardsDetailsAction!saveCreditCardsDetails.action",
			onSubmit : function(param) {
				var isValid = $(this).form('validate');
				if (isValid){
					param.carId = $creditAuditReport.carId;
				}
				return isValid; // 返回false终止表单提交
			},
			success : function(data) {
				data = $.parseJSON(data);
				loadCreditCardsDetials(data.data);
				alertMsg(data);
				$.messager.progress('close'); // 如果提交成功则隐藏进度条
			}
		});

	}

	// 保存征询信息
	function saveCreditInvestigations(obj) {
		$(obj).parents("form:first").form('submit',{
			url : "creditInvestigation/creditInvestigationAction!saveCreditInvestigation.action",
			onSubmit : function(param) {
				var isValid = $(this).form('validate');
				if (isValid){
					param.carId = $creditAuditReport.carId;
				}
				return isValid; // 返回false终止表单提交
			},
			success : function(data) {
				data = $.parseJSON(data);
				loadCreditInvestigations(data.data);
				alertMsg(data);
				$.messager.progress('close'); // 如果提交成功则隐藏进度条
			}
		});
	}
	
	// 保存财务情况的信息
	function saveAccountsJournal(obj) {
		$(obj).parents("form:first").form('submit',{
			url : "accountsJournal/accountsJournalAction!saveAccountsJournal.action",
			onSubmit : function(param) {
				var isValid = $(this).form('validate');
				if (isValid){
					param.carId = $creditAuditReport.carId;
				}
				return isValid; // 返回false终止表单提交
			},
			success : function(data) {
				data = $.parseJSON(data);
				 $(this).form("load",data.data);
				alertMsg(data);
				$.messager.progress('close'); // 如果提交成功则隐藏进度条
			}
		});
	}
	
	// 保存资产情况的信息
	function saveAssets(obj) {
		$(obj).parents("form:first").form('submit',{
			url : "assets/assetsAction!saveAssets.action",
			onSubmit : function(param) {
				var isValid = $(this).form('validate');
				if (isValid){
					param.carId = $creditAuditReport.carId;
				}
				return isValid; // 返回false终止表单提交
			},
			success : function(data) {
				data = $.parseJSON(data);
				loadAssets(data.data);
				alertMsg(data);
				$.messager.progress('close'); // 如果提交成功则隐藏进度条
			}
		});
	}
	
	// 保存资产分析
	function saveFirstAuditReport(obj) {
		$(obj).parents("form:first").form('submit',{
			url : "firstAuditReport/firstAuditReportAction!saveFirstAuditReport.action",
			onSubmit : function(param) {
				var isValid = $(this).form('validate');
				if (isValid){
					param.carId = $creditAuditReport.carId;
					param.loanOrderId = $row.loanOrderId;
				}
				return isValid; // 返回false终止表单提交
			},
			success : function(data) {
				data = $.parseJSON(data);
				loadFirstAuditReport(data.data);
				alertMsg(data);
				$.messager.progress('close'); // 如果提交成功则隐藏进度条
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
					<td><input  name="name"  class="easyui-textbox easyui-validatebox" disabled="disabled"/></td>
					<th>身份证号:</th>
					<td><input  name="idNo" class="easyui-textbox easyui-validatebox" disabled="disabled"/></td>
				</tr>
				<tr>
					<th>申贷金额:</th>
					<td><input  name="loanAmount" class="easyui-numberbox easyui-validatebox" data-options="min:0,precision:2" disabled="disabled"/>元</td>
					<th>贷款用途:</th>
					<td><input  name="purpose" class="easyui-textbox easyui-validatebox" disabled="disabled"/></td>
				</tr>
				<tr>
					<th>还款方式:</th>
					<td><input  name="repayMethod" class="easyui-validatebox easyui-combobox" disabled="disabled"/></td>
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
					<td><input name="lawQueryStatus" class="easyui-textbox easyui-validatebox easyui-combobox" /></td>
					<th>工商网:</th>
					<td><input name="comCreditStatus"  class="easyui-textbox easyui-validatebox easyui-combobox"/></td>
					<th>进件城市:</th>
					<td><input  name="loanCity" class="easyui-textbox easyui-validatebox" data-options="required:true"/></td>
					<td  style="text-align: right;">
						<div style="width: 100%; height: 30px; text-align: right;">
							<a href="javascript:void(0);" onclick="saveCreditAuditReport(this)" class="easyui-linkbutton" iconCls="icon-save">保存</a>
						</div>
					</td>
				</tr>
			</table>
		</form>
		
		<hr>
		
		<!-- 贷款详情 -->
		<form id="loansDetails-form" method="post">
			<input name="existLoanId" hidden="true" class="easyui-validatebox">
			<table class="table" style="margin-top: 10px;width:100%;" cellpadding="5px;">
				<tr>
					<td colspan="6"><span style="font-weight: bold;font-size: 14px;">[贷款详情]</span></td>
				</tr>
				<tr>
					<th>贷款总笔数:</th>
					<td><input name="totalLoanCount" class="easyui-validatebox easyui-numberbox" data-options="required:true"/></td>
					<th>年内逾期:</th>
					<td><input name="yearOverdue" class="easyui-validatebox easyui-numberbox" data-options="required:true"/></td>
					<th>未结清贷款总额:</th>
					<td><input name="outstandingSum" class="easyui-validatebox easyui-numberbox" data-options="min:0,precision:2,required:true"/>元</td>
				</tr>
				<tr>
					<th>未结清笔数:</th>
					<td><input name="outstandingCount" class="easyui-validatebox easyui-numberbox" data-options="required:true"/></td>
					<th>累计逾期:</th>
					<td><input name="cumulativeOverdue" class="easyui-validatebox easyui-numberbox" data-options="required:true"/></td>
					<th>未结清贷款余额:</th>
					<td><input name="outstandingBalance" class="easyui-validatebox easyui-numberbox" data-options="min:0,precision:2,required:true"/>元	</td>
				</tr>
				<tr>
					<th>逾期率:</th>
					<td><input name="overdueRate"  class="easyui-validatebox easyui-textbox" data-options="validType:'mDouble',required:true"/></td>
					<th>月还额度:</th>
					<td><input name="monthRepay"  class="easyui-validatebox easyui-numberbox" data-options="min:0,precision:2,required:true"/></td>
				</tr>
				<tr>
					<th>最近一笔贷款详情:</th>
					<td colspan="6"><textarea name="lastLoanSpace" class="easyui-validatebox easyui-textbox" data-options="required:true" style="width: 95%;height:70px;"></textarea></td>
				</tr>
				<tr>
					<th>备注:</th>
					<td colspan="6"><textarea name="loanDetail" class="easyui-validatebox easyui-textbox" data-options="required:true" style="width: 95%;height:70px;"></textarea></td>
				</tr>
				<tr>
					<td style="text-align: right;" colspan="6" align="right"><a href="javascript:void(0);" onclick="saveLoansDetails(this)" class="easyui-linkbutton" iconCls="icon-save">保存</a></td>
				</tr>
			</table>
		</form>
		
		<hr>
		
		<!-- 信用卡详情 -->
		<form id="creditCardsDetails-form" method="post">
			<input name="cardInfoId" hidden="true" class="easyui-validatebox">
			<table class="table" style="margin-top: 10px;width:100%;" cellpadding="5px;">
				<tr>
					<td colspan="8"><span style="font-weight: bold;font-size: 14px;width:60px;">[信用卡详情]</span></td>
				</tr>
				<tr>
					<th>总卡数:</th>
					<td ><input name="cardCount" style="width: 100px;" class="easyui-validatebox easyui-numberbox" data-options="required:true"/></td>
					<th>在用卡数:</th>
					<td><input name="cardInUse" style="width: 100px;" class="easyui-validatebox easyui-numberbox" data-options="required:true"/></td>
					<th>逾期卡数:</th>
					<td><input name="overdueCardCount" style="width: 100px;" class="easyui-validatebox easyui-numberbox" data-options="required:true"/></td>
					<th >逾期比例:</th>
					<td ><input name="overdueRatio" style="width: 100px;" class="easyui-validatebox easyui-textbox" data-options="validType:'mDouble',required:true"/>%</td>
				</tr>
				<tr>
					<th>授信总额:</th>
					<td><input name="creditTotalAmount" style="width: 100px;" class="easyui-validatebox easyui-numberbox" data-options="min:0,precision:2,required:true"/>元</td>
					<th>使用额度:</th>
					<td><input name="creditLimit" style="width: 100px;" class="easyui-validatebox easyui-numberbox" data-options="min:0,precision:2,required:true"/>元</td>
					<th>最高额度:</th>
					<td><input name="maxLimit" style="width: 100px;" class="easyui-validatebox easyui-numberbox" data-options="min:0,precision:2,required:true"/>元</td>
					<th>月还额度:</th>
					<td><input name="monthRepay" style="width: 100px;" class="easyui-validatebox easyui-numberbox" data-options="min:0,precision:2,required:true"/>元</td>
				</tr>
				<tr>
					<th>年内逾期:</th>
					<td><input name="yearOverdue" style="width: 100px;"  class="easyui-validatebox easyui-numberbox" data-options="required:true"/></td>
					<th>累计逾期:</th>
					<td><input name="cumulativeOverdue" style="width: 100px;"  class="easyui-validatebox easyui-numberbox" data-options="required:true"/></td>
					<th>使用年限:</th>
					<td><input name="validateYear" style="width: 100px;"  class="easyui-validatebox easyui-textbox" data-options="required:true"/></td>
					<th>最高期数:</th>
					<td><input name="maxQishu" style="width: 100px;"  class="easyui-validatebox easyui-numberbox" data-options="required:true"/></td>
				</tr>
				<tr>
					<th>使用率:</th>
					<td><input name="useRate" style="width: 100px;"  class="easyui-validatebox easyui-textbox" data-options="validType:'mDouble',required:true"/>%</td>
					<th>逾期率:</th>
					<td><input name="overdueRate" style="width: 100px;"  class="easyui-validatebox easyui-textbox" data-options="validType:'mDouble',required:true"/>%</td>
				</tr>
				<tr>
					<th>明细:</th>
					<td colspan="8"><textarea name="detail" style="width: 95%;height:70px;" class="easyui-validatebox easyui-textbox" data-options="required:true"></textarea></td>
				</tr>
				<tr>
					<td style="text-align: right;" colspan="8" align="right">
						<a href="javascript:void(0);" onclick="saveCreditCardsDetails(this)" class="easyui-linkbutton" iconCls="icon-save">保存</a>
					</td>
				</tr>
			</table>
		</form>
		
		<hr>
		
		<!-- 征信查询3个月 -->
		<form id="creditInvestigation-form-A" method="post">
			<input name="creditRefId" hidden="true" class="easyui-validatebox">
			<input name="queryInterval" hidden="true" class="easyui-validatebox" value="A">
			<table class="table" style="margin-top: 10px;width:100%;" cellpadding="5px;">
				<tr>
					<td colspan="9"><span style="font-weight: bold;font-size: 14px;width:60px;">[征信查询3个月]</span></td>
				</tr>
				<tr>
					<th>[3个月]</th>
					<th>本人查询:</th>
					<td><input name="selfQuery" style="width: 100px;" class="easyui-validatebox easyui-numberbox" data-options="required:true"/></td>
					<th>贷款审批:</th>
					<td><input name="loanApproval" style="width: 100px;" class="easyui-validatebox easyui-numberbox"/></td>
					<th>信用卡审批:</th>
					<td><input name="creditCardApproval" style="width: 100px;" class="easyui-validatebox easyui-numberbox"/></td>
					<th>互联网查询:</th>
					<td><input name="internetQuery" style="width: 100px;" class="easyui-validatebox easyui-numberbox"/></td>
				</tr>
				<tr>
					<th>备注:</th>
					<td colspan="9"><textarea name="queryNotes" class="easyui-validatebox" style="width: 95%;height:70px;"></textarea></td>
				</tr>
				<tr>
					<td style="text-align: right;" colspan="9" align="right">
						<a href="javascript:void(0);" onclick="saveCreditInvestigations(this)" class="easyui-linkbutton" iconCls="icon-save">保存</a>
					</td>
				</tr>
			</table>
		</form>
		
		<hr>
		
		<!-- 征信查询6个月 -->
		<form id="creditInvestigation-form-B" method="post">
			<input name="creditRefId" hidden="true" class="easyui-validatebox">
			<input name="queryInterval" hidden="true" class="easyui-validatebox" value="B">
			<table class="table" style="margin-top: 10px;width:100%;" cellpadding="5px;">
				<tr>
					<td colspan="9"><span style="font-weight: bold;font-size: 14px;width:120px;">[征信查询6个月]</span></td>
				</tr>
				<tr>
					<th>[6个月]</th>
					<th>本人查询:</th>
					<td><input name="selfQuery" style="width: 100px;" class="easyui-validatebox easyui-numberbox" data-options="required:true"/></td>
					<th>贷款审批:</th>
					<td><input name="loanApproval" style="width: 100px;" class="easyui-validatebox easyui-numberbox" /></td>
					<th>信用卡审批:</th>
					<td><input name="creditCardApproval" style="width: 100px;" class="easyui-validatebox easyui-numberbox" /></td>
					<th>互联网查询:</th>
					<td><input name="internetQuery" style="width: 100px;" class="easyui-validatebox easyui-numberbox" /></td>
				</tr>
				<tr>
					<th>备注:</th>
					<td colspan="9"><textarea name="queryNotes" class="easyui-validatebox" style="width: 95%;height:70px;"></textarea></td>
				</tr>
				<tr>
					<td  style="text-align: right;" colspan="9" align="right">
						<a href="javascript:void(0);" onclick="saveCreditInvestigations(this)" class="easyui-linkbutton" iconCls="icon-save">保存</a>
					</td>
				</tr>
			</table>
		</form>
		</div>
	</div>
	
	<!-- 财务情况 -->
	<div id="accountsJournal-div" title="财务情况">
		<div class="easyui-accordion" style="fit:true;height: 580px;">
		    <div title="流水信息(对公)" data-options="selected:true" style="overflow: auto;">   
				<div id="accountsJournal-corporate-div"></div>
			</div>
		    <div title="流水信息(对私)">   
				<div id="accountsJournal-private-div" style="overflow: auto;"></div>
			</div>
		</div>
	</div>
	
	<!-- 资产 -->
	<div title="资产">
		<form id="assets-form" method="post" style="height:580px;">
			<input name="assetId" class="easyui-validatebox" hidden="true">
			<table class="table" style="margin-top: 10px;width:100%;" cellpadding="5px;">
				<tr>
					<td colspan="4"><span style="font-weight: bold;font-size: 14px;width:60px;">[资产详情]</span></td>
				</tr>
				<tr>
					<th>房产:</th>
					<td><input name="realEstate" class="easyui-textbox easyui-validatebox" data-options='required:true'/></td>
					<th>车产:</th>
					<td><input name="vehicle" class="easyui-textbox easyui-validatebox" data-options='required:true'/></td>
				</tr>
				<tr>
					<th>同行业:</th>
					<td><input name="theSameIndustry" class="easyui-textbox easyui-validatebox" data-options='required:true'/></td>
				</tr>
				<tr>
					<td  style="text-align: right;" colspan="4" align="right">
						<a href="javascript:void(0);" onclick="saveAssets(this)" class="easyui-linkbutton" iconCls="icon-save">保存</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<!-- 资质分析 -->
	<div title="资质分析">
		<form id="firstAuditReport-form" method="post" style="height:580px;">
			<input name="firsId" hidden="true" class="easyui-validatebox">
			<table class="table" style="margin-top: 10px;width:100%;" cellpadding="5px;">
				<tr>
					<td colspan="6"><span style="font-weight: bold;font-size: 14px;width:60px;">[初审资质分析详情]</span></td>
				</tr>
				<tr>
					<th>行业类型:</th>
					<td><input name="industryType" style="width:100px;" class="easyui-validatebox" data-options="required:true"/></td>
					<th>经营年限:</th>
					<td><input name="comOperDuration" style="width:100px;" class="easyui-validatebox easyui-numberbox" data-options="min:0,required:true"/>年</td>
					<th>经营状态:</th>
					<td><input name="comOperStatus" style="width: 100px;"class="easyui-validatebox easyui-combobox"/></td>
				</tr>
				<tr>
					<th>婚姻情况:</th>
					<td><input name="marriageType" style="width: 100px;" class="easyui-validatebox easyui-combobox"/></td>
					<th>是否本地:</th>
					<td style="width: 100px;"><input name="isLocalRes" style="width: 100px;" class="easyui-validatebox easyui-combobox"/></td>
					<th>信用情况:</th>
					<td><input name="creditStatus" style="width: 100px;" class="easyui-validatebox easyui-combobox" /></td>
				</tr>
				<tr>
					<th>电核情况:</th>
					<td><input name="phoneCheckStatus" style="width: 100px;" class="easyui-validatebox easyui-combobox"/></td>
					<th>信访情况:</th>
					<td><input name="visitStatus" style="width: 100px;" class="easyui-validatebox easyui-combobox"/></td>
					<th>资质总评:</th>
					<td><input name="qulificationStatus" style="width: 100px;" class="easyui-validatebox easyui-combobox"/><!-- <a href="#">查看细则</a></td> -->
				</tr>
				<tr>
					<th>备注:</th>
					<td colspan="6"><textarea name="note" style="width:600px;height:70px;" class="easyui-validatebox easyui-validatebox" data-options="required:true"></textarea></td>
				</tr>
				<tr>
					<th>初审人:</th>
					<td>
						<input class="easyui-validatebox easyui-textbox"  value="<shiro:principal property='name'/>" disabled="disabled"/>
						<input id="firsPersonnel" name="firsPersonnel" hidden="true"  class="easyui-validatebox easyui-textbox" value="<shiro:principal property="userId"/>"/>
					</td>
					<th>初审日期</th>
					<td><input id="finaDate" name="firsDate"  class="easyui-validatebox easyui-datetimebox" /></td>
				</tr>
				<tr>
					<th>初审人员意见:</th>
					<td colspan="6"><textarea name="firsPersSugg" style="width:600px;height:70px;" class="easyui-validatebox easyui-textbox" data-options="required:true"></textarea></td>
				</tr>
				<tr>
					<th>初审部门意见:</th>
					<td colspan="6"><textarea name="firsDepSugg" style="width:600px;height:70px;" class="easyui-validatebox easyui-textbox" data-options="required:true"></textarea></td>
				</tr>
				
				<tr>
					<th>初审资质分析说明:</th>
					<td colspan="6"><textarea name="description" style="width:600px;height:70px;" class="easyui-validatebox easyui-textbox" data-options="required:true"></textarea></td>
				</tr>
				<tr>
					<td style="text-align: right;" colspan="6" align="right">
						<a href="javascript:void(0);" onclick="saveFirstAuditReport(this)" class="easyui-linkbutton" iconCls="icon-save">保存</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>
<!-- 申请报告 E -->