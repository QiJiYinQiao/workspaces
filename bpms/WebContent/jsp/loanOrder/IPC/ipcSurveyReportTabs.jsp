<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
		+ request.getServerName() + ":" + request.getServerPort()
		+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../../../layout/script.jsp"></jsp:include>
<title>资产负债表</title>
<style type="text/css">
	table {border-radius: 5px;}
	.table th{
		text-align: right;
	}
	.table td{
		text-align: left;
	}	
	textarea{resize: none;}
	form{height:540px;}
	.subDiv{
		width: 98%; height: 30px; text-align: right;position: absolute;margin-top:5px;
	}
</style>
</head>
<body>
<!-- 授信业务调查报告 -->
<script type="text/javascript">
	$(function(){
		//申请期限
		$("#adviceLoanPeriod").combobox({
			valueField : 'code',
			textField : 'text',
			required:true,
			url:'common/commonAction!findTextArr.action?codeMyid=loan_period_type',
			editable:false ,
			onLoadSuccess : function(){
				var val = $(this).combobox("getData");
				if(!$.isEmptyObject(val)){
					userData = val;
	                $(this).combobox("select", val[0]["code"]);
				}
			},
	    });
	});
</script>

<!-- 申请报告 S -->
<div id="applicationReportTabs" class="easyui-tabs" style="fit:true;overflow: auto;">
	<div title="基本信息">
		<form >
			<table cellpadding="5px;">
				<tr>
					<th>
						主办客户经理
					</th>
					<td>
						<input class="easyui-textbox" name="" >
					</td>
					
					<th>
						协办客户经理
					</th>
					<td>
						<input class="easyui-textbox" name="" >
					</td>
					
					<th>
						调查日期
					</th>
					<td>
						<input class="easyui-datebox" data-options="editable:false" name="" >
					</td>
				</tr>
				
				<tr>
					<th>
						主调
					</th>
					<td>
						<input class="easyui-textbox" name="" >
					</td>
					
					<th>
						辅调
					</th>
					<td>
						<input class="easyui-textbox" name="" >
					</td>
					
				</tr>
			</table>
		</form>
			<div class="subDiv">
				<a href="javascript:void(0);" class="easyui-linkbutton" onclick="">保存</a>
			</div>
	</div>
	
	<div title="贷款信息">
		<form >
			<table cellpadding="5px;">
				<tr>
					<th>
						贷款类型
					</th>
					<td>
						<input class="easyui-combobox" data-options="
															valueField: 'label',
															textField: 'value',
															data: [{
																label: '1',
																value: '新贷款'
															},{
																label: '2',
																value: '续贷'
															}]" />
					</td>
					<th>
						申请金额(万)
					</th>
					<td>
						<input class="easyui-textbox">
					</td>
					
					<th>
						申请期限(月)
					</th>
					<td>
						<input id="adviceLoanPeriod">
					</td>
				</tr>
				<tr>
					<th>
						我公司贷款余额(元)
					</th>
					<td>
						<input class="easyui-textbox">
					</td>
					<th>
						我公司贷款未还款期数
					</th>
					<td>
						<input class="easyui-textbox">
					</td>
					<th>
						建议贷款金额(万)
					</th>
					<td>
						<input class="easyui-textbox">
					</td>
				</tr>
				<tr>
					<th>
						建议贷款期限(月)
					</th>
					<td>
						<input class="easyui-textbox">
					</td>
					<th>
						建议担保方式
					</th>
					<td>
						<input class="easyui-textbox">
					</td>
					<th>
						建议贷款利率
					</th>
					<td>
						<input class="easyui-textbox">
					</td>
				</tr>
				<tr>
					<th>
						其他
					</th>
					<td>
						<input class="easyui-textbox">
					</td>
				</tr>
				<tr>
					<th>
						1、主营业务
					</th>
					<td colspan="5">
						<input class="easyui-textbox" style="width:100%;">
					</td>
				</tr>
				<tr>
					<th>
						2、经营地址
					</th>
					<td colspan="5">
						<input class="easyui-textbox" style="width:100%;">
					</td>
				</tr>
				<tr>
					<th>
						3、借款用途
					</th>
					<td colspan="5">
						<input class="easyui-textbox" style="width:100%;">
					</td>
				</tr>
			</table>
		</form>
		<div class="subDiv">
			<a href="javascript:void(0);" class="easyui-linkbutton" onclick="">保存</a>
		</div>		
	</div>
	
	<div title="个人信息">
		<form style="width:100%;height:525px;">
			<table cellpadding="5px;">
				<tr>
					<th colspan="8">
						1、基本信息
					</th>
				</tr>
				<tr>
					<th>
						借款人姓名
					</th>
					<td>
						<input class="easyui-textbox">
					</td>
					<th>
						性别
					</th>
					<td>
						<input class="easyui-textbox">
					</td>
					<th>
						年龄
					</th>
					<td>
						<input class="easyui-textbox">
					</td>
					<th>
						电话
					</th>
					<td>
						<input class="easyui-textbox">
					</td>
				</tr>
				
				<tr>
					<th>
						身份证
					</th>
					<td>
						<input class="easyui-textbox">
					</td>
					<th>
						婚否
					</th>
					<td>
						<input class="easyui-combobox" data-options="
																valueField: 'label',
																textField: 'value',
																data: [{
																	label: '1',
																	value: '已婚'
																},{
																	label: '2',
																	value: '未婚'
																},{
																	label: '3',
																	value: '离异'
																},{
																	label: '4',
																	value: '丧偶'
																}]" />
					</td>
					<th>
						教育程度
					</th>
					<td>
						<input class="easyui-combobox" data-options="
																valueField: 'label',
																textField: 'value',
																data: [{
																	label: '1',
																	value: '小学'
																},{
																	label: '2',
																	value: '初中'
																},{
																	label: '3',
																	value: '高中'
																},{
																	label: '4',
																	value: '大专'
																},{
																	label: '5',
																	value: '大学及以上'
																}]" />
					</td>
					<th>
						是否本地人
					</th>
					<td>
						<input class="easyui-combobox" data-options="
																valueField: 'label',
																textField: 'value',
																data: [{
																	label: '1',
																	value: '是'
																},{
																	label: '2',
																	value: '否'
																}]" />
					</td>
				</tr>
				
				<tr>
					<th>
						房产信息
					</th>
					<td>
					  <input class="easyui-combobox" data-options="
															valueField: 'label',
															textField: 'value',
															data: [{
																label: '1',
																value: '原件'
															},{
																label: '2',
																value: '复印件'
															},{
																label: '3',
																value: '客户口述'
															}]" />
					</td>
				</tr>
				
				<tr>
					<th colspan="8">
						房产
					</th>
				</tr>
				
				<tr>
					<td></td>
					<th>
						自建房
					</th>
					<th colspan="2">
						回迁房
					</th>
					<th colspan="2">
						租赁
					</th>
					<th colspan="2">
						商品房
					</th>
				</tr>
				
				<tr>
					<th>
						房产面积
					</th>
					<td>
						<input class="easyui-textbox" >
					</td>
					<td colspan="2">
						<input class="easyui-textbox" >
					</td>
					<td colspan="2">
						<input class="easyui-textbox" >
					</td>
					<td colspan="2">
						<input class="easyui-textbox" >
					</td>
				</tr>
				
				<tr>
					<th>
						房产地址
					</th>
					<td>
						<input class="easyui-textbox" >
					</td>
					<td colspan="2">
						<input class="easyui-textbox" >
					</td>
					<td colspan="2">
						<input class="easyui-textbox" >
					</td>
					<td colspan="2">
						<input class="easyui-textbox" >
					</td>
				</tr>
				
				<tr>
					<th>
						房产证号
					</th>
					<td>
						<input class="easyui-textbox" >
					</td>
					<td colspan="2">
						<input class="easyui-textbox" >
					</td>
					<td colspan="2">
						<input class="easyui-textbox" >
					</td>
					<td colspan="2">
						<input class="easyui-textbox" >
					</td>
				</tr>
				
				<tr>
					<th>
						所有人
					</th>
					<td>
						<input class="easyui-textbox" >
					</td>
					<td colspan="2">
						<input class="easyui-textbox" >
					</td>
					<td colspan="2">
						<input class="easyui-textbox" >
					</td>
					<td colspan="2">
						<input class="easyui-textbox" >
					</td>
				</tr>
				
				<tr>
					<th>
						所有权形式
					</th>
					<td>
						<input class="easyui-textbox" >
					</td>
					<td colspan="2">
						<input class="easyui-textbox" >
					</td>
					<td colspan="2">
						<input class="easyui-textbox" >
					</td>
					<td colspan="2">
						<input class="easyui-textbox" >
					</td>
				</tr>
				
				<tr>
					<th>
						现居地址
					</th>
					<td colspan="7">
						<input class="easyui-textbox" >
					</td>
				</tr>
				
				<tr>
					<th>
						车产信息
					</th>
					<td>
					  <input class="easyui-combobox" data-options="
															valueField: 'label',
															textField: 'value',
															data: [{
																label: '1',
																value: '原件'
															},{
																label: '2',
																value: '复印件'
															},{
																label: '3',
																value: '客户口述'
															}]" />
					</td>
				</tr>
				
				<tr>
					<th colspan="8">
						车产
					</th>
				</tr>
				
				<tr>
					<th>
						型号
					</th>
					<th colspan="2">
						牌照
					</th>
					<th colspan="2">
						所有人
					</th>
					<th colspan="2">
						购买时间
					</th>
					<th >
						金额
					</th>
				</tr>
				
				<tr>
					<td>
						<input class="easyui-textbox">
					</td>
					<td colspan="2">
						<input class="easyui-textbox">
					</td>
					<td colspan="2">
						<input class="easyui-textbox">
					</td>
					<td colspan="2">
						<input class="easyui-textbox">
					</td>
					<td >
						<input class="easyui-textbox">
					</td>
				</tr>
				
				<tr>
					<th>
						其他资产信息
					</th>
					<td>
					  <input class="easyui-combobox" data-options="
															valueField: 'label',
															textField: 'value',
															data: [{
																label: '1',
																value: '原件'
															},{
																label: '2',
																value: '复印件'
															},{
																label: '3',
																value: '客户口述'
															}]" />
					</td>
				</tr>
				
				<tr>
					<th>
						1
					</th>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
				</tr>
				
				<tr>
					<th>
						2
					</th>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
				</tr>
				
				<tr>
					<th colspan="8">
						家庭成员情况(需附联系方式)
					</th>
				</tr>
				
				<tr>
					<th>
						基本信息
					</th>
					<th>
						姓名
					</th>
					<th>
						电话
					</th>
					<th>
						性别
					</th>
					<th>
						年龄
					</th>
					<th>
						工作地址/单位
					</th>
					<th>
						身份证
					</th>
					<th>
						教育程度
					</th>
					<th>
						是否本地人
					</th>
				</tr>
				
				<tr>
					<th>
						配偶
					</th>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
				</tr>
				
				<tr>
					<th>
						子女
					</th>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
				</tr>
				
				<tr>
					<th colspan="8">
						征信情况
					</th>
				</tr>
				
				<tr>
					<th colspan="8">
						(1)信用卡情况(包括但不限于信用卡总数，总授信额度，平均使用额度，逾期总次数等)
					</th>
				</tr>
				
				<tr>
					<th>
						开卡行
					</th>
					<th>
						开卡时间
					</th>
					<th>
						信用额度
					</th>
					<th>
					 	已使用额度
					</th>
					<th>
						状态
					</th>
					<th>
					 	逾期期数
					</th>
					<th colspan="2">
						逾期原因
					</th>
				</tr>
				
				<tr>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td colspan="2">
						<input class="easyui-textbox">
					</td>
				</tr>
				
				<tr>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td colspan="2">
						<input class="easyui-textbox">
					</td>
				</tr>
				
				
				<tr>
					<th colspan="8">
						(2)贷款情况
					</th>
				</tr>
				
				<tr>
					<th>
						
					</th>
					<th>
						贷款机构
					</th>
					<th>
						贷款目的
					</th>
					<th>
					 	贷款金额(万)
					</th>
					<th>
						贷款起止日期
					</th>
					<th>
					 	还款方式
					</th>
					<th>
						贷款余额
					</th>
					<th>
						担保方式
					</th>
					<th>
						逾期次数
					</th>
				</tr>
				
				<tr>
					<th>
					 	1
					</th>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td >
						<input class="easyui-textbox">
					</td>
					<td >
						<input class="easyui-textbox">
					</td>
				</tr>
				
				<tr>
				 	<th>
					 	2
					</th>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td >
						<input class="easyui-textbox">
					</td>
					<td >
						<input class="easyui-textbox">
					</td>
				</tr>
				
				<tr>
					<th colspan="8">
						(3)对外担保情况
					</th>
				</tr>
				
				<tr>
					<th>
						
					</th>
					<th>
						与担保人的关系
					</th>
					<th>
						贷款机构
					</th>
					<th>
					 	担保额度
					</th>
					<th>
						担保起止日期
					</th>
					<th>
					 	担保方式
					</th>
					<th>
						担保余额
					</th>
					<th>
						逾期次数
					</th>
					<th>
						其他
					</th>
				</tr>
				
				<tr>
					<th>
					 	1
					</th>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td >
						<input class="easyui-textbox">
					</td>
					<td >
						<input class="easyui-textbox">
					</td>
				</tr>
				
				<tr>
				 	<th>
					 	2
					</th>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td >
						<input class="easyui-textbox">
					</td>
					<td >
						<input class="easyui-textbox">
					</td>
				</tr>
				
			</table>
		</form>
		<div class="subDiv" style="margin-top: 20px;">
			<a href="javascript:void(0);" class="easyui-linkbutton" onclick="microcreditOpinionSubmit('microcreditOpinionForm','microcreditOpinionDlg');">保存</a>
		</div>
	</div>
	
	<div title="经营信息">
		<form >
			<table cellpadding="5px;">
				<tr>
					<td>
					</td>
				</tr>
			</table>
		</form>
		<div class="subDiv">
			<a href="javascript:void(0);" class="easyui-linkbutton" onclick="">保存</a>
		</div>
	</div>
	
	<div title="优势劣势分析">
		<form >
			<table cellpadding="5px;">
				<tr>
					<th>
						优势
					</th>
					<td>
						<textarea style="width:750px;height:220px;" class="easyui-textbox"></textarea>
					</td>
				</tr>
				
				<tr>
					<th>
						劣势
					</th>
					<td>
						<textarea style="width:750px;height:220px;" class="easyui-textbox"></textarea>
					</td>
				</tr>
			</table>
		</form>
		<div class="subDiv">
			<a href="javascript:void(0);" class="easyui-linkbutton" onclick="">保存</a>
		</div> 		
	</div>
	
	<div title="客户软信息描述">
		<form >
			<table cellpadding="5px;">
				<tr>
					<th>
						1.客户整体形象：（可以就客户衣着、整洁程度描述。例如：客户衣着是否干净整洁）
					</th>
				</tr>
				<tr>
					<td>
						<textarea style="width:850px;height:100px;" class="easyui-textbox"></textarea>
					</td>
				</tr>
				
				<tr>
					<th>
						2.客户的生活习惯：（例：在调查的时，客户一直在抽烟，烟瘾大。还有那些坏习惯都可以列举）
					</th>
				</tr>
				<tr>
					<td>
						<textarea style="width:850px;height:100px;" class="easyui-textbox"></textarea>
					</td>
				</tr>
				
				<tr>
					<th>
						3.客户的对人态度：（例：可以注意客户对员工家人以及朋友说话的语气，对我们调查人员的态度）
					</th>
				</tr>
				<tr>
					<td>
						<textarea style="width:850px;height:100px;" class="easyui-textbox"></textarea>
					</td>
				</tr>
				
				<tr>
					<th>
						4.客户的行为举止：（例：客户的是言谈粗鲁或温文尔雅等）
					</th>
				</tr>
				<tr>
					<td>
						<textarea style="width:850px;height:100px;" class="easyui-textbox"></textarea>
					</td>
				</tr>
			</table>
		</form>
		<div class="subDiv">
			<a href="javascript:void(0);" class="easyui-linkbutton" onclick="">保存</a>
		</div>
	</div>
	
</div>
</body>
</html>