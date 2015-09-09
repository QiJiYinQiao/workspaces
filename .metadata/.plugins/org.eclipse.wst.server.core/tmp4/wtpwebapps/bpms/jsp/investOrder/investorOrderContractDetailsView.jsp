<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>合同详情页面</title>
	</head>

	<!-- 1、判断投资订单investOrder是否存在，若investOrder不存在，则合同信息就更不可能存在了-->
	<c:if test="${investOrderObj == null}">
		<div align="center">
			<h1>对不起，无法找到当前用户的合同信息。</h1>
		</div>
	</c:if>
	
	<!-- 2、判断投资订单investOrder是否存在，若investOrder存在，则开始渲染页面-->
	<c:if test="${investOrderObj != null }">
	
		<!-- 第一部分：合同信息部分数据 -->
		<div class="well well-small" style="margin-left: 5px;margin-top: 5px">
			<span>[合同信息]</span><br/>
			
			<table width='100%' height='100%' style="border-collapse:collapse; font-size: 12px;background-color:#F4f4f4;" cellpadding='5' border='1' align='center'>
			   <tr align="center">
			      <th>合同编号:</th>
			      <td>${investOrderObj.contractNo}</td>
			      <th>合同签署地:</th>
			      <td>${investOrderObj.signSite}</td>
			   </tr> 
			   
			   <tr align="center">
			      <th>合同签署日期:</th>
			      <td><fmt:formatDate value="${investOrderObj.signDate}" pattern="yyyy年MM月dd日"/></td>
			      <th>理财经理:</th>
			      <td>${investOrderObj.financingMgr}</td>		      
			   </tr>
			   
			   <tr align="center">
			      <th>理财经理电话:</th>
			      <td>${investOrderObj.fmPhone}</td>
			      <th>理财经理签字日期:</th>
			      <td><fmt:formatDate value="${investOrderObj.fmSignDate}" pattern="yyyy年MM月dd日"/></td>		      
			   </tr>
			   
			   <tr align="center">
			      <th>部门主管:</th>
			      <td>${investOrderObj.deptMgr}</td>
			      <th>部门主管签字日期:</th>
			      <td><fmt:formatDate value="${investOrderObj.dmSignDate}" pattern="yyyy年MM月dd日"/></td>		      
			   </tr>
			</table>					
		</div><br/>		
		
		
		<!-- 第二部分：开户行信息部分数据 -->
		<div class="well well-small" style="margin-left: 5px;margin-top: 5px">
			<span>[开户行信息]</span><br/>
			
			<table width='100%' height='100%' style="border-collapse:collapse; font-size: 12px;background-color:#F4f4f4;" cellpadding='5' border='1' align='center'>
			   <tr align="center">
			      <th>账户介质:</th>			      
				  <td>
					<c:if test="${investOrderObj.actMedium == 'A'}">
						存折					
					</c:if>
					<c:if test="${investOrderObj.actMedium == 'B'}">
						银行卡					
					</c:if>	
				  </td>				      			      			      
			      <th>账户性质:</th>			      
				  <td>
					<c:if test="${investOrderObj.actNature == 'A'}">
						对公账户					
					</c:if>
					<c:if test="${investOrderObj.actNature == 'B'}">
						对私账户					
					</c:if>	
				  </td>				      			      			      
			   </tr>
			   
			   <tr align="center">
			      <th>开户行名称:</th>
			      <td>${investOrderObj.bankName}</td>
			      <th>账户名称:</th>
			      <td>${investOrderObj.actName}</td>
			   </tr> 	
			   
			   <tr align="center">
			      <th>开户行帐号:</th>
			      <td colspan="3">${investOrderObj.actNo}</td>
			   </tr> 				   				   			    		
			</table>					
		</div><br/>		
			

		<!-- 第三部分：理财产品信息部分数据 -->
		<div class="well well-small" style="margin-left: 5px;margin-top: 5px">
			<span>[理财产品信息]</span><br/>
			
			<!-- 1、判断客户是否已经选择了理财产品 : 若此时客户还没有选择理财产品-->
			<c:if test="${investProductList == null || fn:length(investProductList) <= 0}">
				<div align="center">
					<h4>对不起，当前客户还没有选择理财产品，请选择理产品。</h4>
				</div>			
			</c:if>
	
			<!-- 2、判断客户是否已经选择了理财产品 : 若此时客户已经选择理财产品-->
			<c:if test="${investProductList != null && fn:length(investProductList) > 0}">
				<table width='100%' height='100%' style="border-collapse:collapse; font-size: 12px;background-color:#F4f4f4;" cellpadding='5' border='1' align='center'>
				   <tr align="center">
				      <th>理财产品:</th>
				      <td colspan="2">${investProductList.get(0).prodName}</td>
				      <th>模式特点:</th>
				      <td colspan="2">${investProductList.get(0).prodDesc}</td>
				   </tr> 
				   
				   <tr align="center">
				      <th>年化收益率:</th>
				      <td>${investProductList.get(0).ars}%</td>
				      <th>到期收益率:</th>
				      <td>${investProductList.get(0).ytm}%</td>
				      <th>还款方式:</th>
					  <td style="text-align: center;">
						<c:if test="${investProductList.get(0).repaymentMode == 'A'}">
							利随本清					
						</c:if>
						<c:if test="${investProductList.get(0).repaymentMode == 'B'}">
							按月回息					
						</c:if>	
					  </td>				      
				   </tr> 				   
				   
				   <tr align="center">
				      <th>最低出借金额:</th>
				      <td>
				      	<c:if test="${investProductList.get(0).lowLendEdu != null }">
				      		<fmt:formatNumber value="${investProductList.get(0).lowLendEdu}" pattern="#.00"/>元				      		
				      	</c:if>
				      	<c:if test="${investProductList.get(0).lowLendEdu  == null }">
				      		无
				      	</c:if>				      
				      </td>
				      <th>最高出借金额:</th>
				      <td>				      				     
				      	<c:if test="${investProductList.get(0).higLendEdu != null}">
				      		<fmt:formatNumber value="${investProductList.get(0).lowLendEdu}" pattern="#.00"/>元
				      	</c:if>
				      	<c:if test="${investProductList.get(0).higLendEdu == null}">
				      		无
				      	</c:if>						      				      				      				      				      
				      </td>
				      <th>出借周期:</th>
				      <td>${investProductList.get(0).lendingCycle}天</td>
				   </tr> 
				   
				   <tr align="center">
				      <th>意向投资日期:</th>
				      <td><fmt:formatDate value="${investProductList.get(0).beginDate}" pattern="yyyy年MM月dd日"/></td>
				      <th>到日期:</th>
				      <td><fmt:formatDate value="${investProductList.get(0).endDate}" pattern="yyyy年MM月dd日"/></td>
				      <th>投资金额:</th>
				      <td><fmt:formatNumber value="${investProductList.get(0).investEdu}" pattern="#.00"/>元</td>
				   </tr> 				   				   
				   
				</table>					
			</c:if>				
		</div><br/>


		<!-- 第四部分：备注信息部分数据 -->
		<div class="well well-small" style="margin-left: 5px;margin-top: 5px">
			<span>[备注信息]</span><br/>
			
			<table width='100%' height='100%' style="border-collapse:collapse; font-size: 12px;background-color:#F4f4f4;" cellpadding='5' border='1' align='center'>
			   <tr align="center">
			      <th>备注信息:</th>
			      <td>${investOrderObj.orderDesc}</td>		      
			   </tr>
			</table>					
		</div><br/>
			
	</c:if>	
	
	<!-- 渲染关闭按钮 -->		
     <div style="margin-left: ">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#contractInfoDialog').dialog('close')">关闭</a>
     </div>
</html>