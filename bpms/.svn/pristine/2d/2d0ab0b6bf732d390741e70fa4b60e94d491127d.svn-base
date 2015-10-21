<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


	<!-- 1、判断投资订单investOrder是否存在，若investOrder不存在，则合同信息就更不可能存在了-->
	<c:if test="${investOrderObj == null}">
		<div align="center">
			<h1>对不起，无法找到当前用户的合同信息。</h1>
		</div>
	</c:if>
	
	<!-- 2、判断投资订单investOrder是否存在，若investOrder存在，则开始渲染页面-->
	<c:if test="${investOrderObj != null }">
		
		<!-- 第一部分：合同信息部分数据 -->
		<!-- class="well well-small" -->
		<div style="margin-left: 5px;margin-top: 5px">
			<span class="badge">[合同信息]</span><br/>
			
			<table width='100%' align='center'>
			   <tr align="center">
			      <th>合同编号:</th>			      
			      <td><input name="contractNo" class="easyui-textbox" disabled="disabled" value="${investOrderObj.contractNo}"/></td>
			      <th>合同签署地:</th>
			      <td><input name="signSite" class="easyui-textbox" disabled="disabled" value="${investOrderObj.signSite}"/></td>
			   </tr> 
			   
			   <tr align="center">
			      <th>合同签署日期:</th>			      
			      <td><input name="signDate" class="easyui-textbox" disabled="disabled" value='<fmt:formatDate value="${investOrderObj.signDate}" pattern="yyyy年MM月dd日"/>'/></td>
			      <th>理财经理:</th>			      
			      <td><input name="financingMgr" class="easyui-textbox" disabled="disabled" value="${investOrderObj.financingMgr}"/></td>		      
			   </tr>
			   
			   <tr align="center">
			      <th>理财经理电话:</th>
			      <td><input name="fmPhone" class="easyui-textbox" disabled="disabled" value="${investOrderObj.fmPhone}"/></td>
			      <th>理财经理签字日期:</th>
			      <td><input name="fmSignDate" class="easyui-textbox" disabled="disabled" value='<fmt:formatDate value="${investOrderObj.fmSignDate}" pattern="yyyy年MM月dd日"/>'/></td>		      
			   </tr>
			   
			   <tr align="center">			   
			      <th>部门主管:</th>
			      <td><input name="deptMgr" class="easyui-textbox" disabled="disabled" value="${investOrderObj.deptMgr}"/></td>
			      <th>部门主管签字日期:</th>			      
			      <td><input name="dmSignDate" class="easyui-textbox" disabled="disabled" value='<fmt:formatDate value="${investOrderObj.dmSignDate}" pattern="yyyy年MM月dd日"/>'/></td>		      
			   </tr>
			</table>					
		</div><br/>		
		
		
		<!-- 第二部分：开户行信息部分数据 -->
		<!-- 从数据字典中获取  账户介质,账户性质 的中文字符 -->
		<script type="text/javascript">
			//账户介质 				 
			$("#actMedium").val(jqueryUtil.showDicText("account_medium","${investOrderObj.actMedium}")); 				
			//账户性质  actNature
			$("#actNature").val(jqueryUtil.showDicText("bankAccount_type","${investOrderObj.actNature}"));			
		</script>	
		
		<div style="margin-left: 5px;margin-top: 5px">
			<span class="badge">[开户行信息]</span><br/>
			
			<table width='100%' align='center'>
			   <tr align="center">
			      <th>账户介质:</th>			      
				  <td><input id="actMedium" class="easyui-textbox" disabled="disabled" value="${investOrderObj.actMedium}"/></td>				      			      			      
			      <th>账户性质:</th>			      
				  <td><input id="actNature" class="easyui-textbox" disabled="disabled" value="${investOrderObj.actNature}"/>
				  </td>				      			      			      
			   </tr>
			   
			   <tr align="center">
			      <th>开户行名称:</th>
			      <td><input name="bankName" class="easyui-textbox" disabled="disabled" value="${investOrderObj.bankName}"/></td>
			      <th>账户名称:</th>
			      <td><input name="actName" class="easyui-textbox" disabled="disabled" value="${investOrderObj.actName}"/></td>
			   </tr> 	
			   
			   <tr align="center">
			      <th>开户行帐号:</th>
			      <td><input name="actNo" class="easyui-textbox" disabled="disabled" value="${investOrderObj.actNo}"/></td>
			   </tr> 				   				   			    		
			</table>					
		</div><br/>		
			

		<!-- 第三部分：理财产品信息部分数据 -->
		<!-- 从数据字典中获取  还款方式的中文字符 -->
		<script type="text/javascript">
			//还款方式			 			 
			$("#repaymentMode").val(jqueryUtil.showDicText("repayment_mode","${investProductList.get(0).repaymentMode}"));			 						
		</script>	
		
		<div style="margin-left: 5px;margin-top: 5px">
			<span class="badge">[理财产品信息]</span><br/>
			
			<!-- 1、判断客户是否已经选择了理财产品 : 若此时客户还没有选择理财产品-->
			<c:if test="${investProductList == null || fn:length(investProductList) <= 0}">
				<div align="center">
					<h4>对不起，当前客户还没有选择理财产品，请选择理产品。</h4>
				</div>			
			</c:if>
	
			<!-- 2、判断客户是否已经选择了理财产品 : 若此时客户已经选择理财产品-->
			<c:if test="${investProductList != null && fn:length(investProductList) > 0}">
				<table width="100%" align='center'>
				   <tr align="center">
				      <th>理财产品:</th>										      
				      <td><input name="prodName" class="easyui-textbox" disabled="disabled" value="${investProductList.get(0).prodName}"/></td>
				      <th>年化收益率:</th>										      				      
				      <td><input name="ars" class="easyui-textbox" disabled="disabled" value="${investProductList.get(0).ars}%"/></td>
				   </tr> 
				   
				   <tr align="center">
				      <th>到期收益率:</th>				      
				      <td><input name="ytm" class="easyui-textbox" disabled="disabled" value="${investProductList.get(0).ytm}%"/></td>
				      <th>还款方式:</th>
					  <td><input id="repaymentMode" class="easyui-textbox" disabled="disabled" value="${investProductList.get(0).repaymentMode}"/></td>				      
				   </tr> 				   
				   
				   <tr align="center">
				      <th>最低出借金额:</th>
				      <td>										      
				      	<c:if test="${investProductList.get(0).lowLendEdu != null }">
				      		<input name="lowLendEdu" class="easyui-textbox" disabled="disabled" value='<fmt:formatNumber value="${investProductList.get(0).lowLendEdu}" pattern="#.00"/>元'/>				      						      	
				      	</c:if>
				      	<c:if test="${investProductList.get(0).lowLendEdu  == null }">
							<input name="lowLendEdu" class="easyui-textbox" disabled="disabled" value='无'/>				      		
				      	</c:if>				      
				      </td>
				      <th>最高出借金额:</th>
				      <td>				      				     
				      	<c:if test="${investProductList.get(0).higLendEdu != null}">
				      		<input name="higLendEdu" class="easyui-textbox" disabled="disabled" value='<fmt:formatNumber value="${investProductList.get(0).higLendEdu}" pattern="#.00"/>元'/>				      	
				      	</c:if>
				      	<c:if test="${investProductList.get(0).higLendEdu == null}">				      		
							<input name="higLendEdu" class="easyui-textbox" disabled="disabled" value='无上限'/>				      		
				      	</c:if>						      				      				      				      				      
				      </td>
				   </tr>
				   
				   <tr align="center">
				      <th>出借周期:</th>				      
				      <td><input name="lendingCycle" class="easyui-textbox" disabled="disabled" value="${investProductList.get(0).lendingCycle}天"/></td>
				      <th>意向投资日期:</th>				      				     
				      <td><input name="beginDate" class="easyui-textbox" disabled="disabled" value='<fmt:formatDate value="${investProductList.get(0).beginDate}" pattern="yyyy年MM月dd日"/>'/></td>
				   </tr> 
				   
				   <tr align="center">
				      <th>到日期:</th>				     
				      <td><input name="endDate" class="easyui-textbox" disabled="disabled" value='<fmt:formatDate value="${investProductList.get(0).endDate}" pattern="yyyy年MM月dd日"/>'/></td>
				      <th>投资金额:</th>				      
				      <td><input name="investEdu" class="easyui-textbox" disabled="disabled" value='<fmt:formatNumber value="${investProductList.get(0).investEdu}" pattern="#.00"/>元'/></td>
				   </tr> 
				   				   				   
				   <tr align="center">
				      <th>模式特点:</th>				      
				      <td colspan="3"><textarea id="prodDesc" name="prodDesc" style="width: 90%;"  disabled="disabled">${investProductList.get(0).prodDesc}</textarea></td>				      				   
				   </tr>
				   				   
				</table>					
			</c:if>				
		</div><br/>


		<!-- 第四部分：备注信息部分数据 -->
		<div style="margin-left: 5px;margin-top: 5px">
			<span class="badge">[备注信息]</span><br/>
			
			<table width='100%' align='center'>
			   <tr align="center">
			      <th>备注信息:</th>								      			      
				  <td><textarea id="orderDesc" name="orderDesc" style="width: 95%;"  disabled="disabled">${investOrderObj.orderDesc}</textarea></td>				      				   			      			      		      
			   </tr>
			</table>					
		</div><br/>			
	</c:if>	
	
	<!-- 渲染关闭按钮 -->		
     <div align="right">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#contractInfoDialog').dialog('close')">关闭</a>
     </div>
