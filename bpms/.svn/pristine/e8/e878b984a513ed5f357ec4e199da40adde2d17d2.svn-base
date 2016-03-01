<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<style type="text/css">
	textarea {
		height:80px;
		width:100%;
		resize: none;
	}
	th　{
		text-align: left;
	}
</style>
<script type="text/javascript">
	$(function(){
		$("#quest").empty();
		$("#loanOrderId").val($row.loanOrderId);
		$("#loanOrderIdS").val($row.loanOrderId);
		$("#isRationalDescN").attr("disabled","disabled").hide();
		//加载下拉框数据
		
		$("#isOwn").combobox({
			valueField : 'code',
			textField : 'text',
			editable:false ,
			required:true,
			url:"common/commonAction!findTextArr.action?codeMyid=yes_or_no",
		});
		
		$("#carIsGuar").combobox({
			valueField : 'code',
			textField : 'text',
			editable:false ,
			required:true,
			url:"common/commonAction!findTextArr.action?codeMyid=yes_or_no",
		});
		
		$("#houseIsGuar").combobox({
			valueField : 'code',
			textField : 'text',
			editable:false ,
			required:true,
			url:"common/commonAction!findTextArr.action?codeMyid=yes_or_no",
		});
		
		$("#isRational").combobox({
			valueField : 'code',
			textField : 'text',
			editable:false ,
			required:true,
			url:"common/commonAction!findTextArr.action?codeMyid=yes_or_no",
			onSelect:function(param) {
				if(param.code=="Y") {
					$("#isRationalDescY").removeAttr("disabled").show();
					$("#isRationalDescN").val(" ").attr("disabled","disabled").hide();
					$("#isRationalDescN").focus();
				} else {
					$("#isRationalDescN").removeAttr("disabled").show();
					$("#isRationalDescY").attr("disabled","disabled").hide();
				}
			}
		});
		
		$("#offSeason").combobox({
			valueField : 'code',
			textField : 'text',
			editable:false ,
			multiple:true,
			url:"common/commonAction!findTextArr.action?codeMyid=months",
			onChange:function(param){
				var vals = $("#offSeason").combobox("getValues");
				if(vals.length>0) {
					$("#offSeasonAmtN").hide();
					$("#offSeasonAmtY").removeAttr("disabled").show();
				} else {
					$("#offSeasonAmtY").val(0).attr("disabled","disabled").hide();
					$("#offSeasonAmtY").focus();
					$("#offSeasonAmtN").show();
				}
			}
		});
		
		$("#busySeason").combobox({
			valueField : 'code',
			textField : 'text',
			editable:false ,
			multiple:true,
			url:"common/commonAction!findTextArr.action?codeMyid=months",
			onChange:function(param){
				var vals = $("#busySeason").combobox("getValues");
				if(vals.length) {
					$("#buysSeasonAmtY").removeAttr("disabled").show();
					$("#buysSeasonAmtN").hide();
				} else {
					$("#buysSeasonAmtN").show();
					$("#buysSeasonAmtY").val(0).attr("disabled","disabled").hide();
					$("#buysSeasonAmtY").focus();
				}
			}
		});
		
		$("#shoulderSeason").combobox({
			valueField : 'code',
			textField : 'text',
			editable:false ,
			multiple:true,
			url:"common/commonAction!findTextArr.action?codeMyid=months",
			onChange:function(param){
				var vals = $("#shoulderSeason").combobox("getValues");
				if(vals.length) {
					$("#shoulderSeasonAmtY").removeAttr("disabled").show();
					$("#shoulderSeasonAmtN").hide();
				} else {
					$("#shoulderSeasonAmtN").show();
					$("#shoulderSeasonAmtY").val(0).attr("disabled","disabled").hide();
					$("#shoulderSeasonAmtY").focus();
				}
			}
		});
		
		//渲染调查人员信息
		$('#surveyer').combogrid({    
		    panelWidth:450,    
		    idField:'userId',    
		    textField:'name',
		    multiple:true,  
		    editable:false,
		    required:true,
			queryParams: {"roleCode":"IPCXiaoEDiaoCha"},
			url : "loanOrder/loanOrderAction!findCandidatePersonsByCode.action?t="+ new Date(),
			columns : [ [ 
			              {field : 'name',title : '用户名',width : 100,align : 'center'},
			              {field : 'email',title : '邮箱',width : 150,align : 'center'},
			              {field : 'tel',title : '电话',width :150,align : 'center'},
			              {field : 'organization',title : '组织',width :220,align : 'center',
			            	    formatter:function(value,row){
				            	  	return value.fullName;  
								}
						  }, 
			              {field : 'description',title : '描述',width : 570,align : 'left'}
		              ] ],
		});
		
		$('#summarySurveyer').combogrid({    
		    panelWidth:550,    
		    idField:'userId',    
		    textField:'name',
		    multiple:true,  
		    editable:false,
		    required:true,
			queryParams: {"roleCode":"IPCXiaoEDiaoCha"},
			url : "loanOrder/loanOrderAction!findCandidatePersonsByCode.action?t="+ new Date(),
			columns : [ [ 
			              {field : 'name',title : '用户名',width : 100,align : 'center'},
			              {field : 'email',title : '邮箱',width : 150,align : 'center'},
			              {field : 'tel',title : '电话',width :150,align : 'center'},
			              {field : 'organization',title : '组织',width :220,align : 'center',
			            	    formatter:function(value,row){
				            	  	return value.fullName;  
								}
						  }, 
			              {field : 'description',title : '描述',width : 570,align : 'left'}
		              ] ],
		});
		
		queryOutSurveyReport();
		findOutSurveyReportSummary();
		findQuestions();
	});

	//查询初审调查报告,存在则加载
	function queryOutSurveyReport(){
		$.ajax({
			url : 'outSurveyReport/outSurveyReportAction!findIpcOutSurveyReport.action',
			data : {"loanOrderId":$row.loanOrderId},
			type : "post",
			success : function(data){
				if(data) {
					if(data.surveyer){
						data.surveyer = data.surveyer.replace(/\s/g,'').split(",");
					}
					if(data.shoulderSeason){
						data.shoulderSeason = data.shoulderSeason.replace(/\s/g,'').split(",");
					}
					if(data.offSeason){
						data.offSeason = data.offSeason.replace(/\s/g,'').split(",");
					}
					if(data.busySeason){
						data.busySeason = data.busySeason.replace(/\s/g,'').split(",");
					}
					$("#ipcSurveyReportForm").form("load",data);
				}
			}
		});
	}
	
	//查询初审调查报告总结,存在则加载
	function findOutSurveyReportSummary(){
		$.ajax({
			url : 'outSurveyReportSummary/outSurveyReportSummaryAction!findIpcOutSurveyReportSummary.action',
			data : {"loanOrderId":$row.loanOrderId},
			type : "post",
			success : function(data){
				if(data){
					data.surveyer = data.surveyer.replace(/\s/g,'').split(",");
					$("#ipcSurveyReportSummaryForm").form("load",data);
				}
			}
		});
	}
	
	//查询初审问题并加载
	function findQuestions(){
		$.ajax({
			url : 'firstauditQuestioncollect/firstauditQuestioncollectAction!findByOrderId.action',
			data : {"loanOrderId":$row.loanOrderId},
			type : "post",
			success : function(data){
				if(data){
					var questionHtml = "";
					$.each(data,function(i,item){
						questionHtml += "<h4>问题 "+(i+1)+":　　"+item.questionDesc+"</h4>";
						questionHtml += "<input name='questions' type='hidden' value='"+item.questionId+"' />";
						if(item.answerId){
							questionHtml += "<input name='answerId' type='hidden' value='"+item.answerId+"' />";
							questionHtml += "<textarea name='answers' style='width:100%;height:40px;' class='easyui-validatebox easyui-textbox' data-options='required:true,validType:\"length[0,2000]\"'>"+item.answer+"</textarea>";
						}else{
							questionHtml += "<input name='answerId' type='hidden' />";
							questionHtml += "<textarea name='answers' style='width:100%;height:40px;' class='easyui-textbox easyui-validatebox' data-options='required:true,validType:\"length[0,2000]\"'></textarea>";
						}
					});
					$("#quest").append(questionHtml);
					$.parser.parse($("#quest"));
				}
			}
		});
	}
	
	//保存外访调查报告
	function saveSurveyReport(){
		$("#ipcSurveyReportForm").form("submit",{
			url : "outSurveyReport/outSurveyReportAction!saveIpcOutSurveyReport.action",
			onSubmit: function(){
				var validate = $(this).form("validate");
				return validate;
			},
			success : function(data){
				data = $.parseJSON(data);
				$("input[name='outsurveyReportId']").val(data.data.outsurveyReportId);
				$.messager.show({
					title:data.title,
					msg:data.message,
					timeout:1000,
					showType:'slide'
				});
			}
		});
	}
	
	//保存外访调查报告总结
	function saveSurveyReportSummary(){
		var outsurveyReportId = $("input[name='outsurveyReportId']").val();
		if(""==outsurveyReportId){
			$.messager.alert("提示","请先保存调查报告!","info");
			return false;
		}
		
		$("#ipcSurveyReportSummaryForm").form("submit",{
			url : "outSurveyReportSummary/outSurveyReportSummaryAction!saveIpcOutSurveyReportSummary.action",
			onSubmit: function(param){
				var validate = $(this).form("validate");
				if(validate){
					var answerDesc = "";
					var quesIDs = "";
					var answersIdList = "";
					var answer = $("textarea[name='answers']");
					var questions = $("input[name='questions']");
					var answerIds = $("input[name='answerId']");
					for(var i = 0 ; i < answer.length; i++){
						if(""==answer[i].value){
							answer[i].value = " ";
						}
						if(""==questions[i].value){
							questions[i].value = " ";
						}
						if(""!=answerIds[i].value){
							answersIdList += answerIds[i].value+"@@@";
						}
						answerDesc += answer[i].value+"@@@";
						quesIDs += questions[i].value+"@@@";
					}
					param.answerDesc = answerDesc;
					param.quesIDs = quesIDs;
					param.answersIdList = answersIdList;
				}
				return validate;
			},
			success : function(data){
				data = $.parseJSON(data);
				$("input[name='outsurveyReportSummaryId']").val(data.data[0].outsurveyReportSummaryId);
				var questionHtml = "";
				$.messager.show({
					title:data.title,
					msg:data.message,
					timeout:1000,
					showType:'slide'
				});
				$.each(data.data,function(i,item){
					questionHtml += "<h4>问题 "+(i+1)+":　　"+item.questionDesc+"</h4>";
					questionHtml += "<input name='questions' type='hidden' value='"+item.questionId+"' />";
					if(item.answerId){
						questionHtml += "<input name='answerId' type='hidden' value='"+item.answerId+"' />";
						questionHtml += "<textarea name='answers' style='width:100%;height:40px;'>"+item.answer+"</textarea>";
					}else{
						questionHtml += "<input name='answerId' type='hidden' />";
						questionHtml += "<textarea name='answers' style='width:100%;height:40px;'></textarea>";
					}
				});
				$("#quest").empty().append(questionHtml);
			}
		});
	}
</script>
<div id="" class="easyui-tabs" style="fit:true;">
	<div title="小额调查报告">
		<form id="ipcSurveyReportForm" method="post" style="width:100%;height:580px;overflow: auto;">
			<input type="hidden" name="outsurveyReportId">
			<input id="loanOrderId" type="hidden" name="loanOrderId">
			<table>
				<tr>
					<th>
						调查人员
					</th>
					<td>
						<input id="surveyer" name="surveyer">
					</td>
					<th>
						调查时间
					</th>
					<td>
						<input name="surveyDate" class="easyui-datebox easyui-validatebox" data-options="editable:false">
					</td>
					<th>
						往返里程(km)
					</th>
					<td>
						<input name="distance" class="easyui-numberbox easyui-validatebox" data-options="min:0,max:999999,required:true" >
					</td>
				</tr>
				
				<tr>
					<th>
						客户姓名
					</th>
					<td>
						<input name="customerName"  class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'length[0,20]'" >
					</td>
					<th>
						经营地址
					</th>
					<td colspan="3">
						<input name="comAddress" class="easyui-textbox easyui-validatebox" style="width:100%;" data-options="validType:'length[0,256]',required:true">
					</td>
					<th>
						是否本地人
					</th>
					<td>
						<input name="isLocal" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,25]',required:true">
					</td>
				</tr>
				
				<tr>
					<th>
						家庭地址
					</th>
					<td colspan="5">
						<input name="homeAddress" class="easyui-textbox easyui-validatebox" style="width:100%;" data-options="validType:'length[0,256]',required:true">
					</td>
					<th>
						是否自有
					</th>
					<td>
						<input id="isOwn" name="isOwn" class="easyui-combobox" >
					</td>
				</tr>
				
				<tr>
					<th>
						房产信息
					</th>
					<td colspan="3">
						<input name="houseInfo" class="easyui-textbox easyui-validatebox" style="width:100%;" data-options="validType:'length[0,200]',required:true" >
					</td>
					<th>
						是否抵押
					</th>
					<td>
						<input id="houseIsGuar" name="houseIsGuar" class="easyui-combobox" >
					</td>
					<th>
						其他
					</th>
					<td>
						<input name="houseInfoOther" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,200]',required:true">
					</td>
				</tr>
				
				<tr>
					<th>
						车产信息
					</th>
					<td colspan="3">
						<input name="carInfo" class="easyui-textbox easyui-validatebox" style="width:100%;" data-options="validType:'length[0,200]',required:true" >
					</td>
					<th>
						是否抵押
					</th>
					<td>
						<input id="carIsGuar" name="carIsGuar" class="easyui-combobox" >
					</td>
					<th>
						其他
					</th>
					<td>
						<input name="carInfoother" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,200]',required:true">
					</td>
				</tr>
				
				<tr>
					<th>
						实际贷款目的
					</th>
					<td colspan="7">
						<input name="actPurpose" class="easyui-textbox easyui-validatebox" style="width:100%;" data-options="validType:'length[0,200]',required:true">
					</td>
				</tr>
				
				<tr>
					<th>
						是否合理
					</th>
					<td>
						<input id="isRational" name="isRational" class="easyui-combobox easyui-validatebox" style="width:100%;">
					</td>
					
					<th>
						理由
					</th>
					<td colspan="5">
						<input id="isRationalDescY" name="isRationalDesc" class="easyui-textbox easyui-validatebox" style="width:100%;" data-options="validType:'length[0,512]'">
						<input id="isRationalDescN" name="isRationalDesc" class="easyui-textbox easyui-validatebox" style="width:100%;" data-options="validType:'length[0,512]',required:true">
					</td>
				</tr>

				<tr>
					<th>
						实际资金需求
					</th>
					<td colspan="2">
						<input name="actualAmt" class="easyui-textbox easyui-validatebox" data-options="validType:'mDouble1',required:true">万元
					</td>
				</tr>
				
				<tr>
					<th>
						客户口述员工总数
					</th>
					<td >
						<input name="oralEmployee" class="easyui-textbox easyui-validatebox" data-options="validType:['integer','length[0,5]'],required:true">
					</td>
					<th>
						可见员工数
					</th>
					<td>
						<input name="inviewEmployee" class="easyui-textbox easyui-validatebox" data-options="validType:['integer','length[0,5]'],required:true">
					</td>
					<th>
						顾客数
					</th>
					<td>
						<input name="inviewCustomer" class="easyui-textbox easyui-validatebox" data-options="validType:['integer','length[0,5]'],required:true">
					</td>
				</tr>
				
				<tr>
					<th rowspan="2">
						年营业额
					</th>
					<th  rowspan="2">
						淡旺季
					</th>
					<th>
						淡季(月份)
					</th>
					<td>
						<input id="offSeason" name="offSeason" >
					</td>
					<th>
						旺季(月份)
					</th>
					<td>
						<input id="busySeason" name="busySeason" >
					</td>
					<th>
						平季(月份)
					</th>
					<td>
						<input id="shoulderSeason" name="shoulderSeason" >
					</td> 
				</tr>
				
				<tr>
					<th>
						淡季营业额(万元)
					</th>
					<td>
						<input id="offSeasonAmtY" name="offSeasonAmt" style="display:none;" disabled="disabled" class="easyui-textbox easyui-validatebox" data-options="validType:'mDouble',required:true">
						<input id="offSeasonAmtN" name="offSeasonAmt" disabled="disabled" class="easyui-textbox easyui-validatebox" data-options="validType:'mDouble'">
					</td>
					<th>
						旺季营业额(万元)
					</th>
					<td>
						<input id="buysSeasonAmtY" name="buysSeasonAmt" style="display:none;" disabled="disabled" class="easyui-textbox easyui-validatebox" data-options="validType:'mDouble',required:true">
						<input id="buysSeasonAmtN" name="buysSeasonAmt" disabled="disabled" class="easyui-textbox easyui-validatebox" data-options="validType:'mDouble'">
					</td>
					<th>
						平季营业额(万元)
					</th>
					<td>
						<input id="shoulderSeasonAmtY" name="shoulderSeasonAmt" style="display:none;" disabled="disabled" class="easyui-textbox easyui-validatebox" data-options="validType:'mDouble',required:true">
						<input id="shoulderSeasonAmtN" name="shoulderSeasonAmt" disabled="disabled" class="easyui-textbox easyui-validatebox" data-options="validType:'mDouble'">
					</td>
				</tr>
				
				<tr>
					<th>
						(选填) 毛利率
					</th>
					<td>
						<input name="grossMargin" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,5]',required:true" >%
					</td>
					<th>
						净利率
					</th>
					<td>
						<input name="netMargin" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,5]',required:true">%
					</td>
					<th>
						加价率
					</th>
					<td>
						<input name="increaseMargin" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,5]',required:true">%
					</td>
				</tr>
				
				<tr>
					<th>
						(选填) 口述毛利润(万元)
					</th>
					<td>
						<input name="oralGrossMargin" class="easyui-textbox easyui-validatebox" data-options="validType:'mDouble',required:true" >
					</td>
					<th>
						口述净利润(万元)
					</th>
					<td>
						<input name="oralNetmargin" class="easyui-textbox easyui-validatebox" data-options="validType:'mDouble',required:true">
					</td>
				</tr>
				
				<tr>
					<th colspan="8">
						简述经营历史
					</th>
				</tr>
				<tr>
					<td colspan="8">
						<textarea name="businessHis" rows="" cols="" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'length[0,1024]'"></textarea>
					</td>
				</tr>
				
				<tr>
					<th colspan="8">
						现经营模式/情况
					</th>
				</tr>
				<tr>
					<td colspan="8">
						<textarea name="businessModel" rows="" cols="" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'length[0,1024]'"></textarea>
					</td>
				</tr>
				
				<tr>
					<th colspan="8">
						上下游情况(包括结款方式及占比)
					</th>
				</tr>
				<tr>
					<td colspan="8">
						<textarea name="updownSituation" rows="" cols="" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'length[0,512]'"></textarea>
					</td>
				</tr>
				
				<tr>
					<th colspan="8">
						生意资产情况
					</th>
				</tr>
				<tr>
					<td colspan="8">
						<textarea name="businessAssetSitutaion" rows="" cols="" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'length[0,512]'"></textarea>
					</td>
				</tr>
				
				<tr>
					<th colspan="8">
						贷款情况(公司/银行、期数、金额、月还款金额)
					</th>
				</tr>
				<tr>
					<td colspan="8">
						<textarea name="loanSituation" rows="" cols="" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'length[0,512]'"></textarea>
					</td>
				</tr>
				
				<tr>
					<th colspan="8">
						经营基本费用
					</th>
				</tr>
				<tr>
					<td colspan="8">
						<textarea name="manageStaticFee" rows="" cols="" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'length[0,512]'"></textarea>
					</td>
				</tr>
				
				<tr>
					<th colspan="8">
						其它经营项目(具体项目、收支等情况)
					</th>
				</tr>
				<tr>
					<td colspan="8">
						<textarea name="otherBusinessProject" rows="" cols="" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'length[0,512]'"></textarea>
					</td>
				</tr>
				
				<tr>
					<th colspan="8">
						家庭情况(家庭成员收入开支等)
					</th>
				</tr>
				<tr>
					<td colspan="8">
						<textarea name="familySituation" rows="" cols="" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'length[0,200]'"></textarea>
					</td>
				</tr>
				
				<tr>
					<th colspan="8">
						调查中其他需说明情况
					</th>
				</tr>
				<tr>
					<td colspan="8">
						<textarea name="otherSituation" rows="" cols="" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'length[0,1024]'"></textarea>
					</td>
				</tr>
				
				<tr>
					<th colspan="8">
						交叉检验
					</th>
				</tr>
				<tr>
					<td colspan="8">
						<textarea name="crossTest" rows="" cols="" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'length[0,1024]'"></textarea>
					</td>
				</tr>
				
				<tr>
					<th colspan="8">
						拍照相关：企业证照、特许经营证、资产证明、销售证明(销售凭证、上下游合同其他相关证明等)、家访等实地照片等
					</th>
				</tr>
				
				<tr>
					<td colspan="8">
						<div style="float:right;margin-right: 10px;">
							<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="saveSurveyReport();">保存</a>
						</div>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<div title="小额调查报告总结">
		<form id="ipcSurveyReportSummaryForm" method="post" style="width:100%;height:580px;overflow: auto;">
			<input name="outsurveyReportSummaryId" type="hidden">
			<input id="loanOrderIdS" type="hidden" name="loanOrderId">
			<table cellpadding="6" style="width:98%;">
				<tr>
					<td colspan="4"></td>
					<th>
						调查人员
					</th>
					<td colspan="2">
						<input id="summarySurveyer" name="surveyer"> 
					</td>
					<!-- <th>
						报告时间
					</th>
					<td>
						<input name="reportingTime" class="easyui-datebox easyui-validatebox " data-options="editable:false">
					</td> -->
				</tr>
				
				<tr>
					<th colspan="8">
						初审问题及答疑
					</th>
				</tr>
				
				<tr>
					<td colspan="8">
						<div id="quest">
						</div>
					</td>
				</tr>
				
				<tr>
					<th colspan="8">
						调查中存在的疑问点
					</th>
				</tr>
				
				<tr>
					<td colspan="8">
						<textarea name="doubtfulPoint" rows="" cols="" class="easyui-validatebox easyui-textbox" data-options="required:true,validType:'length[0,2500]'"></textarea>
					</td>
				</tr>
				
				<tr>
					<th colspan="8">
						客户优劣势分析 (包括但不限于企业的经营情况、企业背景、软信息、财务信息等)
					</th>
				</tr>
				
				<tr>
					<td colspan="8">
						<textarea name="customerAnaly" rows="" cols="" class="easyui-validatebox easyui-textbox" data-options="required:true,validType:'length[0,2500]'"></textarea>
					</td>
				</tr>
				
				<tr>
					<th>建议放款额度(万元)</th>
					<td>
						<input name="suggestAmt" class="easyui-textbox easyui-validatebox" data-options="validType:'mDouble',required:true">
					</td>
				</tr>
				
				<tr>
					<th colspan="8">
						备注(包括风控措施并给出相应理由)
					</th>
				</tr>
				
				<tr>
					<td colspan="8">
						<textarea name="reason"  rows="" cols="" class="easyui-validatebox easyui-textbox" data-options="required:true,validType:'length[0,1024]'"></textarea>
					</td>
				</tr>
				
				<tr>
					<td colspan="8">
						<div style="float:right;margin-right: 10px;">
							<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="saveSurveyReportSummary();">保存</a>
						</div>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>
