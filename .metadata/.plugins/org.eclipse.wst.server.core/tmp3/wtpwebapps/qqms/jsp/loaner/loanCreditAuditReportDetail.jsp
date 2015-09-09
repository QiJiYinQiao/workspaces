<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	.easyui-textbox{
		height: 18px;
		width: 170px;
		line-height: 16px;
	    /*border-radius: 3px 3px 3px 3px;*/
	    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset;
	    transition: border 0.2s linear 0s, box-shadow 0.2s linear 0s;
	}
	
	textarea:focus, input[type="text"]:focus{
	    border-color: rgba(82, 168, 236, 0.8);
	    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(82, 168, 236, 0.6);
	    outline: 0 none;
		}
		table {
	    background-color: transparent;
	    border-collapse: collapse;
	    border-spacing: 0;
	    max-width: 100%;
	}

	fieldset {
	    border: 0 none;
	    margin: 0;
	    padding: 0;
	}
	legend {
	    -moz-border-bottom-colors: none;
	    -moz-border-left-colors: none;
	    -moz-border-right-colors: none;
	    -moz-border-top-colors: none;
	    border-color: #E5E5E5;
	    border-image: none;
	    border-style: none none solid;
	    border-width: 0 0 1px;
	    color: #999999;
	    line-height: 20px;
	    display: block;
	    margin-bottom: 10px;
	    padding: 0;
	    width: 100%;
	}
	input, textarea {
	    font-weight: normal;
	}
	.table ,th,td{
		text-align:left;
		padding: 6px;
	}
</style>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div class="well well-small" data-options="region:'center',border:false" title="">
				<div title="客户基本信息" data-options="iconCls:'icon-cstbase'" style="padding:10px">
						<fieldset>
							<legend>客户基本信息</legend>
							 <table class="table" width="100%" border="1">
								 <tr>
								    <th style="text-align: center;">客户姓名</th>
									<td style="text-align: center;">${baseObj[0] }</td>
									<th style="text-align: center;">身份证号</th>
									<td style="text-align: center;">${baseObj[1] }</td>
									<th style="text-align: center;">人法网</th>
									<td style="text-align: center;" colspan="3">${baseObj[2] }</td>
								 </tr>
								 <tr>
								    <th style="text-align: center;">贷款类型</th>
									<td style="text-align: center;">${baseObj[3] }</td>
								    <th style="text-align: center;">申贷金额</th>
									<td style="text-align: center;">${baseObj[4] }</td>
									<th style="text-align: center;">贷款用途</th>
									<td style="text-align: center;">${baseObj[5] }</td>
									<th style="text-align: center;">进件城市</th>
									<td style="text-align: center;">${baseObj[6] }</td>
								 </tr>
								  <tr>
									<th style="text-align: center;">公司名称</th>
									<td style="text-align: center;" colspan="3">${baseObj[7] }</td>
									<th style="text-align: center;">工商网</th>
									<td style="text-align: center;" colspan="3">${baseObj[8] }</td>
								 </tr>
							   </table>
						</fieldset>
				</div>
				<div title="征信情况--贷款" data-options="iconCls:'icon-help'" style="padding:10px">
						<fieldset>
							<legend>征信情况--贷款</legend>
						 <table class="table"  width="100%" border="1">
							   <tr>
								    <th width="15%" style="text-align: center;">贷款总笔数</th>
									<td style="text-align: center;">${loanObj[0] }</td>
									<th width="15%" style="text-align: center;">未结清总笔数</th>
									<td style="text-align: center;">${loanObj[1] }</td>
									<th width="15%" style="text-align: center;">未结清贷款总额</th>
									<td style="text-align: center;">${loanObj[2] }</td>
									<th width="15%" style="text-align: center;">未结清贷款余额</th>
									<td style="text-align: center;">${loanObj[3] }</td>
								 </tr>
								 <tr>
								    <th style="text-align: center;">年内逾期</th>
									<td style="text-align: center;">${loanObj[4] }</td>
									<th style="text-align: center;">累计逾期</th>
									<td style="text-align: center;">${loanObj[5] }</td>
								    <th style="text-align: center;">逾期率</th>
									<td style="text-align: center;">${loanObj[6] }</td>
									<th style="text-align: center;">月还款额度</th>
									<td style="text-align: center;">${loanObj[7] }</td>
								 </tr>
								 <tr>
									 <th style="text-align: center;">最近一笔贷款详情</th>
									 <td colspan="8">${loanObj[8] }</td>
								 </tr>
								 <tr>
									 <th style="text-align: center;">贷款明细</th>
									 <td colspan="8">${loanObj[9] }</td>
								 </tr>
						</table>
						</fieldset>
				</div>
				<div title="征信情况--信用卡" data-options="iconCls:'icon-help'" style="padding:10px">
						<fieldset>
							<legend>征信情况--信用卡</legend>
						 <table class="table" width="100%" border="1">
							<tr>
							    <th style="text-align: center;">总卡数</th>
								<th style="text-align: center;">在用卡数</th>
								<th style="text-align: center;">授信总额</th>
								<th style="text-align: center;">使用额度</th>
								<th style="text-align: center;">年内逾期</th>
								<th style="text-align: center;">使用年限</th>
								<th style="text-align: center;">使用率</th>
								<th style="text-align: center;">信用卡明细</th>
							</tr>
							<tr>
								<td style="text-align: center;">${creditCardObj[0] }</td>
								<td style="text-align: center;">${creditCardObj[1] }</td>
								<td style="text-align: center;">${creditCardObj[2] }</td>
								<td style="text-align: center;">${creditCardObj[3] }</td>
								<td style="text-align: center;">${creditCardObj[4] }</td>
								<td style="text-align: center;">${creditCardObj[5] }</td>
								<td style="text-align: center;">${creditCardObj[6] }</td>
								<td rowspan="3">${creditCardObj[14] }</td>
							</tr>
							<tr>
							    <th style="text-align: center;">逾期卡数</th>
								<th style="text-align: center;">逾期比例</th>
								<th style="text-align: center;">最高额度</th>
								<th style="text-align: center;">月还额度</th>
								<th style="text-align: center;">累计逾期</th>
								<th style="text-align: center;">最高期数</th>
								<th style="text-align: center;">逾期率</th>
							</tr>
							<tr>
								<td style="text-align: center;">${creditCardObj[7] }</td>
								<td style="text-align: center;">${creditCardObj[8] }</td>
								<td style="text-align: center;">${creditCardObj[9] }</td>
								<td style="text-align: center;">${creditCardObj[10] }</td>
								<td style="text-align: center;">${creditCardObj[11] }</td>
								<td style="text-align: center;">${creditCardObj[12] }</td>
								<td style="text-align: center;">${creditCardObj[13] }</td>
							</tr>
						</table>
						</fieldset>
				</div>
				<div title="征信情况--征信查询" data-options="iconCls:'icon-help'" style="padding:10px">
							<fieldset>
							<legend>征信情况--征信查询</legend>
							  <table class="table" width="100%" border="1">
								<tr>
									<th style="text-align: center;">时间间隔</th>
									<th style="text-align: center;">本人查询</th>
									<th style="text-align: center;">贷款审批</th>
									<th style="text-align: center;">信用卡审批</th>
									<th style="text-align: center;">互联网查询</th>
									<th style="text-align: center;">查询情况备注</th>
								</tr>
								<c:if test="${!empty creditList }">
								<c:forEach items="${creditList }" var ="creditObj">
								<tr>
									<th style="text-align: center;">${creditObj[0] }</th>
									<td style="text-align: center;">${creditObj[1] }</td>
									<td style="text-align: center;">${creditObj[2] }</td>
									<td style="text-align: center;">${creditObj[3] }</td>
									<td style="text-align: center;">${creditObj[4] }</td>
									<td>${creditObj[5] }</td>
								</tr>
								</c:forEach>
								</c:if>
							  </table>
						</fieldset>
				</div>
							
				<div title="财务情况" data-options="iconCls:'icon-help'" style="padding:10px">
					<fieldset>
							<legend>财务情况</legend>
							 <table class="table" width="100%" border="1">
							 <tr>
							 <th style="text-align: center;">账号后四位</th>
							 <th style="text-align: center;">账号类型</th>
							 <th style="text-align: center;">第一个月份</th>
							 <th style="text-align: center;">入账金额</th>
						 	 <th style="text-align: center;">第二个月份</th>
							 <th style="text-align: center;">入账金额</th>
							 <th style="text-align: center;">第三个月份</th>
							 <th style="text-align: center;">入账金额</th>
							 <th style="text-align: center;">第四个月份</th>
							 <th style="text-align: center;">入账金额</th>
						 	 <th style="text-align: center;">第五个月份</th>
							 <th style="text-align: center;">入账金额</th>
							 <th style="text-align: center;">第六个月份</th>
							 <th style="text-align: center;">入账金额</th>
							 <th style="text-align: center;">月均/万</th>
							 <th style="text-align: center;">流水分析</th>
							 </tr>
							 <c:if test="${!empty accountList }">
							 <c:forEach items="${accountList }" var="accountObj">
							 <tr>
							 <td style="text-align: center;">${accountObj.accountNo}</td>
							 <td style="text-align: center;">${accountObj.accountType}</td>
							 <td style="text-align: center;">${accountObj.month01 }</td>
							 <td style="text-align: center;">${accountObj.income01 }</td>
						 	 <td style="text-align: center;">${accountObj.month02 }</td>
							 <td style="text-align: center;">${accountObj.income02 }</td>
							 <td style="text-align: center;">${accountObj.month03 }</td>
							 <td style="text-align: center;">${accountObj.income03 }</td>
							 <td style="text-align: center;">${accountObj.month04 }</td>
							 <td style="text-align: center;">${accountObj.income04 }</td>
						 	 <td style="text-align: center;">${accountObj.month05 }</td>
							 <td style="text-align: center;">${accountObj.income05 }</td>
							 <td style="text-align: center;">${accountObj.month06 }</td>
							 <td style="text-align: center;">${accountObj.income06 }</td>
							 <td style="text-align: center;">${accountObj.avemonIncome }</td>
							 <td style="text-align: center;">${accountObj.analysis }</td>
							 </tr>
							 </c:forEach>
							 </c:if>
							 </table>
						</fieldset>
				</div>
				<div title="资产信息" data-options="iconCls:'icon-help'" style="padding:10px">
						<fieldset>
							<legend>资产信息</legend>
						 <table class="table" width="100%" border="1">
						 	<tr>
						 	<th style="text-align: center;" width="20%">房产</th>
						 	<td>${assetsObj[1] }</td>
						 	</tr>
						 	<tr>
						 	<th style="text-align: center;">车产</th>
						 	<td>${assetsObj[2] }</td>
						 	</tr>
						 	<tr>
						 	<th style="text-align: center;">同行业</th>
						 	<td>${assetsObj[3] }</td>
						 	</tr>
						</table>
						</fieldset>
				</div>
				<div title="初审资质分析" data-options="iconCls:'icon-help'" style="padding:10px">
						<fieldset>
							<legend>初审资质分析</legend>
						 <table class="table" border="1">
						 <tr>
						 <th style="text-align: center;">分析描述</th>
						 <td colspan="9">${firstAuditObj[0] }</td>
						 </tr>
						 <tr>
						 <th width="5%" style="text-align: center;">本地</th>
						 <th style="text-align: center;">婚姻</th>
						 <th style="text-align: center;">行业类型</th>
						 <th style="text-align: center;">经营年限</th>
						 <th style="text-align: center;">经营状态</th>
						 <th style="text-align: center;">信用情况</th>
						 <th style="text-align: center;">电核情况</th>
						 <th style="text-align: center;">信访情况</th>
						 <th style="text-align: center;">资质总评</th>
						 <th style="text-align: center;">备注</th>
						 </tr>
						 <tr>
						 <td style="text-align: center;">${firstAuditObj[1] }</td>
						 <td style="text-align: center;">${firstAuditObj[2] }</td>
						 <td style="text-align: center;">${firstAuditObj[3] }</td>
						 <td style="text-align: center;">${firstAuditObj[4] }</td>
						 <td style="text-align: center;">${firstAuditObj[5] }</td>
						 <td style="text-align: center;">${firstAuditObj[6] }</td>
						 <td style="text-align: center;">${firstAuditObj[7] }</td>
						 <td style="text-align: center;">${firstAuditObj[8] }</td>
						 <td style="text-align: center;">${firstAuditObj[9] }</td>
						 <td>${firstAuditObj[10] }</td>
						 </tr>
						 <tr>
						 <th style="text-align: center;" colspan="3">初审人员意见</th>
						 <td style="text-align: center;" colspan="2">${firstAuditObj[11] }</td>
						 <th style="text-align: center;">初审人</th>
						 <td style="text-align: center;">${firstAuditObj[12] }</td>
						 <th style="text-align: center;">审核日期</th>
						 <td style="text-align: center;" colspan="2">${firstAuditObj[13] }</td>
						 </tr>
						 <tr>
						 <th style="text-align: center;" colspan="3">初审部门意见</th>
						 <td colspan="7">${firstAuditObj[14] }</td>
						 </tr>
						</table>
						</fieldset>
				</div>
				<div title="终审资质分析" data-options="iconCls:'icon-help'" style="padding:10px">
						<fieldset>
							<legend>终审资质分析</legend>
						  <table class="table" border="1">
						 <tr>
						 <th style="text-align: center;">分析描述</th>
						 <td colspan="9">${finalAuditObj[0] }</td>
						 </tr>
						 <tr>
						<th style="text-align: center;" colspan="2">终审人</th>
						<td style="text-align: center;" colspan="2">${finalAuditObj[1] }</td>
						<th style="text-align: center;">终审日期</th>
						<td style="text-align: center;" colspan="4">${finalAuditObj[2] }</td>
						 </tr>
						 <tr>
						<th width="5%" style="text-align: center;" rowspan="2">终审结果</th>
						<th style="text-align: center;">合同金额</th>
						<th style="text-align: center;">实放金额</th>
						<th style="text-align: center;">贷款期限(月)</th>
						<th style="text-align: center;">月服务费率</th>
						<th style="text-align: center;">利息</th>
						<th style="text-align: center;">信访费用</th>
						<th style="text-align: center;">月还款额</th>
						 </tr>
						 <tr>
						<td style="text-align: center;">${finalAuditObj[3] }</td>
						<td style="text-align: center;">${finalAuditObj[4] }</td>
						<td style="text-align: center;">${finalAuditObj[5] }</td>
						<td style="text-align: center;">${finalAuditObj[6] }</td>
						<td style="text-align: center;">${finalAuditObj[7] }</td>
						<td style="text-align: center;">${finalAuditObj[8] }</td>
						<td style="text-align: center;">${finalAuditObj[9] }</td>
						 </tr>
						</table>
						</fieldset>
				</div>
		  </div>
</div>
