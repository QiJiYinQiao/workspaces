<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

	
	<!-- 1、判断客户是否已经选择了理财产品 : 若此时客户还没有选择理财产品-->
	<c:if test="${InvestorAndInvestProductRows == null || fn:length(InvestorAndInvestProductRows) <= 0}">
		<div align="center">
			<h1>对不起，当前客户还没有选择理财产品，请选择理产品。</h1>
		</div>
	</c:if>
	
	<!-- 2、判断客户是否已经选择了理财产品 : 若此时客户已经选择理财产品-->
	<c:if test="${InvestorAndInvestProductRows != null && fn:length(InvestorAndInvestProductRows) > 0}">
		<!-- 客户信息部分数据 -->
		<div class="well well-small" style="margin-left: 5px;margin-top: 5px">
			<span class="badge">[客户信息]</span><br/>
			
			<table align='center' width="100%">
			   <tr align="center">
			      <th>姓名:</th>
			      <td><input name="investorName" class="easyui-textbox" disabled="disabled" value="${InvestorAndInvestProductRows.get(0).investorName}"/></td>
			      <th>身份证号:</th>			      
			      <td><input name="idCrad" class="easyui-textbox" disabled="disabled" value="${InvestorAndInvestProductRows.get(0).idCrad}"/></td>
			      <th>联系方式:</th>
			      <td><input name="mobTel" class="easyui-textbox" disabled="disabled" value="${InvestorAndInvestProductRows.get(0).mobTel}"/></td>		      
			   </tr>
			</table>					
		</div>		
			
		<!-- 理财产品部分数据 -->
		<div class="well well-small" style="margin-left: 5px;margin-top: 20px">
     	  <span class="badge">[已购理财产品信息]</span><br/>			
			
		  <table align='center' width="100%">
		     <tr>
		        <th align="center">理财产品</th>
		        <td><input name="prodName" class="easyui-textbox" disabled="disabled" value="${InvestorAndInvestProductRows.get(0).prodName}"/></td>
		        <th align="center">出借周期（天）</th>
				<td><input name="lendingCycle" class="easyui-textbox" disabled="disabled" value="${InvestorAndInvestProductRows.get(0).lendingCycle}"/></td>		     
		     </tr>

		     <tr>
		        <th align="center">年化收益（%）</th>
	     		<td><input name="ars" class="easyui-textbox" disabled="disabled" value="${InvestorAndInvestProductRows.get(0).ars}%" /></td>	     																		
		        <th align="center">到期收益（%）</th>
	     		<td><input name="ytm" class="easyui-textbox" disabled="disabled" value="${InvestorAndInvestProductRows.get(0).ytm}%"/></td>
		     </tr>

		     <tr>
		        <th align="center">理财金额（元）</th>
	     		<td><input name="investEdu" class="easyui-textbox" disabled="disabled" value='<fmt:formatNumber value="${InvestorAndInvestProductRows.get(0).investEdu}" pattern="#.00"/>'/></td>

	     		<c:if test="${InvestorAndInvestProductRows.get(0).ifAdjustArs == 'Y'}">
			        <th align="center"><font color="red">新的年化收益（%）</font></th>
		     		<td><input name="newArs" class="easyui-textbox" disabled="disabled" value="${InvestorAndInvestProductRows.get(0).newArs}%" /></td>	     		
	     		</c:if>
		     </tr>

			<!-- 从数据字典中获取还款方式的中文字符 -->
<!-- 			<script type="text/javascript">
				$("#repaymentMode").val(jqueryUtil.showDicText("repayment_mode","${InvestorAndInvestProductRows.get(0).repaymentMode}"));//还款方式
			</script>	 -->				

		     <tr>
		        <th align="center">年化折标系数</th>
	     		<td><input name="msf" class="easyui-textbox" disabled="disabled" value="${InvestorAndInvestProductRows.get(0).msf}"/></td>		     
		        <th align="center">还款方式</th>		     	     				     	     		
 				<td>
 					<%-- <input id="repaymentMode" class="easyui-textbox" disabled="disabled" value="${InvestorAndInvestProductRows.get(0).repaymentMode}"/> --%> 					
		     		<c:if test="${InvestorAndInvestProductRows.get(0).repaymentMode == 'A'}">
			     		<input name="repaymentMode" id="repaymentMode" class="easyui-textbox" disabled="disabled" value="利随本清"/>	     		
		     		</c:if> 					
		     		<c:if test="${InvestorAndInvestProductRows.get(0).repaymentMode == 'B'}">
			     		<input name="repaymentMode" id="repaymentMode" class="easyui-textbox" disabled="disabled" value="按月回息"/>	     		
		     		</c:if> 					 					 					
 				</td> 			 								
		     </tr>

		     <tr>
		     </tr>
		        <th align="center">意向投资日期</th>
	     		<td><input name="beginDate" class="easyui-textbox" disabled="disabled" value='<fmt:formatDate value="${InvestorAndInvestProductRows.get(0).beginDate}" pattern="yyyy年MM月dd日"/>'/></td>		     		     		     
		        <th align="center">到期日期</th>
	     		<td><input name="endDate" class="easyui-textbox" disabled="disabled" value='<fmt:formatDate value="${InvestorAndInvestProductRows.get(0).endDate}" pattern="yyyy年MM月dd日"/>'/></td>
		     </tr>
			 <tr>
		        <td colspan="4" style="text-align: right;">
		        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#investorAndInvestProductsDialog').dialog('close')">关闭</a>
		        </td>
		     </tr>		     
		  </table>
		</div>		
	</c:if>	