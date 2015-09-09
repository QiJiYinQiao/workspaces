<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

	<div data-options="region:'center',border : false">
		
		<!-- <div title="理财产品详情界面" data-options="iconCls:'icon-cstbase'" style="padding:10px"> -->
		<div class="well well-small"">
			 <table class="table" border="1" cellpadding="2" cellspacing="0">
				 <tr>
				    <th width="25%" style="text-align: left; label-info">产品名称</th>
					<td width="25%" style="text-align: center;">${investProduct.prodName}</td>
					<th width="25%" style="text-align: left;">出借周期</th>
					<td width="25%" style="text-align: center;">${investProduct.lendingCycle}天</td>
				 </tr>
				 <tr>
				    <th style="text-align: left;">年化收益率</th>
					<td style="text-align: center;">${investProduct.ars}</td>
				    <th style="text-align: left;">到期收益率</th>
					<td style="text-align: center;">${investProduct.ytm}%</td>
				 </tr>
				 <tr>
					<th style="text-align: left;">最低出借金额</th>
					<td style="text-align: center;">
						<c:if test="${investProduct.lowLendEdu == null }">
							0元
						</c:if>
						<c:if test="${investProduct.lowLendEdu != null }">
							<fmt:formatNumber value="${investProduct.lowLendEdu}" pattern="#.00"/>元
						</c:if>
					</td>
					<th style="text-align: left;">最高出借金额</th>
					<td style="text-align: center;">
						<c:if test="${investProduct.higLendEdu == null || investProduct.higLendEdu == ''}">
							无上限
						</c:if>
						<c:if test="${investProduct.higLendEdu != null && investProduct.higLendEdu != ''}">
							<fmt:formatNumber value="${investProduct.higLendEdu}" pattern="#.00"/>元							
						</c:if>						
					</td>
				</tr>
				 <tr>
					<th style="text-align: left;">还款方式</th>
					<c:if test="${investProduct.repaymentMode == 'A'}">
						<td style="text-align: center;">利随本清</td>					
					</c:if>
					<c:if test="${investProduct.repaymentMode == 'B'}">
						<td style="text-align: center;">按月回息</td>					
					</c:if>	

					<th style="text-align: left;">产品状态</th>
					<c:if test="${investProduct.prodStatus == 'A'}">
						<td style="text-align: center;">产品有效</td>					
					</c:if>
					<c:if test="${investProduct.prodStatus == 'B'}">
						<td style="text-align: center;">产品无效</td>					
					</c:if>																									
				</tr>				
				 <tr>
					<th style="text-align: left;">年化折标系数</th>
					<td style="text-align: center;">${investProduct.msf}</td>
				 
					<th style="text-align: left;">产品描述</th>
					<td style="text-align: center;">${investProduct.prodDesc}</td>
				</tr>
			 </table>
		</div>
	</div>
	
