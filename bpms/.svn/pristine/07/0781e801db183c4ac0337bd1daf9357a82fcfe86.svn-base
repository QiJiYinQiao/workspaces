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
	table {border-right: 1px solid black; border-bottom: 1px solid black;}
	table th{border-left: 1px solid black;border-top: 1px solid black;}
	table td{border-left: 1px solid black; border-top: 1px solid black;}
	textarea{resize: none;}
	form{height:570px;text-align: center;}
	.subDiv{
		width: 98%; height: 30px; text-align: right;position: absolute;margin-top:5px;
	}
	font{font-weight: bold;}
	.easyui-textbox{width:100%;}
	.subDiv{
		width: 98%; height: 30px; text-align: right;position: absolute;margin-top:5px;
	}
</style>
</head>
<body>
	<div style="overflow: auto;">
		<form action="post">
			<div style="width:100%:">
				<font size="3">授信业务调查报告</font>
			</div>
			<table cellpadding="5">
				<tr>
					<th colspan="5" style="text-align: right;">
						资产负债表 日期
					</th>		
					<td>
						<input class="easyui-datebox" data-options="editable:false">
					</td>		
				</tr>
				
				<tr>
					<th colspan="6" style="text-align: left;">
						现金和银行贷款
					</th>
				</tr>
				
				<tr>
					<th>
						项目
					</th>
					<th colspan="3">
						备注(存放位置、账号)
					</th>
					<th>
						金额
					</th>
					<th>
						占比
					</th>
				</tr>
				
				<tr>
					<td>
						<input class="easyui-textbox">
					</td>
					<td colspan="3">
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
					<th>总计</th>
					<td colspan="3"></td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
				</tr>
				
				<tr>
					<th colspan="6" style="text-align: left;">
						应收账款
					</th>
				</tr>
				
				<tr>
					<th>
						项目
					</th>
					<th>
						发生日
					</th>
					<th colspan="2">
						到期日
					</th>
					<th>
						金额
					</th>
					<th>
						占比
					</th>
				</tr>
				
				<tr>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-datebox">
					</td>
					<td colspan="2">
						<input class="easyui-datebox">
					</td>
					<td>
						<input class="easyui-textbox" >
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
				</tr>
				
				<tr>
					<th>总计</th>
					<td colspan="3"></td>
					<td>
						<input class="easyui-textbox" value="0">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
				</tr>
				
				<tr>
					<th colspan="6" style="text-align: left;">
						预付账款
					</th>
				</tr>
				
				<tr>
					<th>
						项目
					</th>
					<th>
						发生日
					</th>
					<th colspan="2">
						到期日
					</th>
					<th>
						金额
					</th>
					<th>
						占比
					</th>
				</tr>
				
				<tr>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-datebox">
					</td>
					<td colspan="2">
						<input class="easyui-datebox">
					</td>
					<td>
						<input class="easyui-textbox" >
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
				</tr>
				
				<tr>
					<th>总计</th>
					<td colspan="3"></td>
					<td>
						<input class="easyui-textbox" value="0">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
				</tr>
				
				<tr>
					<th colspan="6">
						存货
					</th>
				</tr>
				
				<tr>
					<th>
						项目
					</th>
					<th colspan="3">
						数量
					</th>
					<th>
						金额
					</th>
					<th>
						占比
					</th>
				</tr>
				
				<tr>
					<th>总计</th>
					<td colspan="3"></td>
					<td>
						<input class="easyui-textbox" value="0">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
				</tr>
				
				<tr>
					<th style="text-align: left;">流动资产总计(1):</th>
					<td colspan="3"></td>
					<td>
						<input class="easyui-textbox" value="0">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
				</tr>
				
				<tr>
					<th colspan="6" style="text-align: left;">
						固定资产
					</th>
				</tr>
				
				<tr>
					<th>明细</th>
					<th>购置日</th>
					<th>原值</th>
					<th>折旧</th>
					<th>现值</th>
					<th>占比</th>
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
				</tr>
				
				<tr>
					<th style="text-align:left;">固定资产总计(2):</th>
					<td colspan="3"></td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
				</tr>
				
				<tr>
					<th style="text-align:left;">总资产(1)+(2):</th>
					<td colspan="3"></td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
				</tr>
				
				<tr>
					<th colspan="6" style="text-align:left;">其他非经营资产或表外资产</th>
				</tr>
				
				<tr>
					<th>项目</th>
					<th colspan="3">备注</th>
					<th colspan="2">金额</th>
				</tr>
				
				<tr>
					<td>
						<input class="easyui-textbox">
					</td>
					<td colspan="3">
						<input class="easyui-textbox">
					</td>
					<td colspan="2">
						<input class="easyui-textbox">
					</td>
				</tr>
				
				<tr>
					<th style="text-align:left;">总计</th>
					<td colspan="3"></td>
					<td colspan="2">
						<input class="easyui-textbox">
					</td>
				</tr>
				
				<tr>
					<th colspan="6" style="text-align:left;">应付账款</th>
				</tr>
				
				<tr>
					<th>项目</th>
					<th>发生日</th>
					<th colspan="2">到期日</th>
					<th>金额</th>
					<th>占比</th>
				</tr>
				
				<tr>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-datebox">
					</td>
					<td colspan="2"> 
						<input class="easyui-datebox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
				</tr>
				
				<tr>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-datebox">
					</td>
					<td colspan="2"> 
						<input class="easyui-datebox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
				</tr>
				
				<tr>
					<th>总计</th>
					<td colspan="3"></td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
				</tr>
				
				<tr>
					<th style="text-align:left;" colspan="6">预收账款</th>
				</tr>
				
				<tr>
					<th>项目</th>
					<th colspan="3">备注</th>
					<th>金额</th>
					<th>占比</th>
				</tr>
				
				<tr>
					<td>
						<input class="easyui-textbox">
					</td>
					<td colspan="3">
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
					<td>
						<input class="easyui-textbox">
					</td>
					<td colspan="3">
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
					<th>总计</th>
					<td colspan="3"></td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
				</tr>
				
				<tr>
					<th style="text-align:left;" colspan="6">银行借款</th>
				</tr>
				
				<tr>
					<th>项目</th>
					<th>银行</th>
					<th>账单日</th>
					<th>还款日</th>
					<th>平均使用额度</th>
					<th>占比</th>
				</tr>
				
				<tr>
					<th rowspan="3">信用卡</th>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-datebox">
					</td>
					<td>
						<input class="easyui-datebox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
				</tr>
				
				<tr>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-datebox">
					</td>
					<td>
						<input class="easyui-datebox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
				</tr>
				
				<tr>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-datebox">
					</td>
					<td>
						<input class="easyui-datebox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
				</tr>
				
				<tr>
					<th>总计</th>
					<td colspan="3"></td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
				</tr>
				
				<tr>
					<th>项目</th>
					<th>银行</th>
					<th>发生日</th>
					<th>到期日</th>
					<th>剩余本金</th>
					<th>占比</th>
				</tr>
				
				<tr>
					<th>短期贷款</th>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-datebox">
					</td>
					<td>
						<input class="easyui-datebox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
				</tr>
				
				<tr>
					<th>长期贷款</th>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-datebox">
					</td>
					<td>
						<input class="easyui-datebox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
				</tr>
				
				<tr>
					<th>总计</th>
					<td colspan="3"></td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
				</tr>
				
				<tr>
					<th style="text-align:left;">银行借款合计</th>
					<td colspan="3"></td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
				</tr>
				
				<tr>
					<th colspan="6" style="text-align:left;">应付其他账款(私人借款)</th>
				</tr>
				
				<tr>
					<th>项目</th>
					<th>发生日</th>
				 	<th colspan="2">到期日</th>
					<th>金额</th>
					<th>占比</th>
				</tr>
				
				<tr>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-datebox">
					</td>
					<td>
						<input class="easyui-datebox">
					</td>
					<td>
						<input class="easyui-datebox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
				</tr>
				
				<tr>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-datebox">
					</td>
					<td>
						<input class="easyui-datebox">
					</td>
					<td>
						<input class="easyui-datebox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
				</tr>
				
				<tr>
					<th>总计</th>
					<td colspan="3"></td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
				</tr>
				
				<tr>
					<th style="text-align:left;">总负债</th>
					<td colspan="3"></td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
				</tr>
				
				<tr>
					<th style="text-align:left;">所有者权益</th>
					<td colspan="3"></td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
				</tr>
				
				<tr>
					<th style="text-align:left;">负债及所有者权益合计</th>
					<td colspan="3"></td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
				</tr>
				
				<tr>
					<th >其他</th>
					<td colspan="4"></td>
					<th >占比</th>
				</tr>
				
				<tr>
					<th >申请金额</th>
					<td>
						<input class="easyui-textbox">
					</td>
					<th >对外担保金额</th>
					<td colspan="2">
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
				</tr>
				
				<tr>
					<th colspan="2">短期资产负债率</th>
					<td colspan="4">
						<input class="easyui-textbox">
					</td>
				</tr>
				
				<tr>
					<th colspan="2">短期资产负债率</th>
					<td colspan="4">
						<input class="easyui-textbox">
					</td>
				</tr>
				
				<tr>
					<th style="text-align:left;" colspan="6">
						权益的交叉检验
					</th>
				</tr>
				
				<tr>
					<th colspan="2" style="text-align:left;">
						权益检验表
					</th>
					<th>
						起始
					</th>
					<td>
						<input class="easyui-datebox">
					</td>
					<th>
						终止
					</th>
					<td>
						<input class="easyui-datebox">
					</td>
				</tr>
				
				<tr>
					<th rowspan="7">初始投资</th>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<th rowspan="7">期间收入</th>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
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
				</tr>
				
				<tr>
					<td></td>
					<th>合计</th>
					<td><input class="easyui-textbox" value="0"></td>
					<td></td>
					<th>合计</th>
					<td><input class="easyui-textbox" value="0"></td>
				</tr>
				
				<tr>
					<th colspan="6">大项开销</th>
				</tr>
				
				<tr>
					<th rowspan="4">已核实</th>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<th rowspan="4">未核实</th>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
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
				</tr>
				
				<tr>
					<td></td>
					<th>合计</th>
					<td><input class="easyui-textbox" value="0"></td>
					<td></td>
					<th>合计</th>
					<td><input class="easyui-textbox" value="0"></td>
				</tr>
				
				<tr>
					<th>应有权益</th>
					<td>
						<input class="easyui-textbox">
					</td>
					
					<th>差异值</th>
					<td>
						<input class="easyui-textbox">
					</td>
					
					<th>差异率</th>
					<td>
						<input class="easyui-textbox">
					</td>
				</tr>
			</table>
		</form>
		<div class="subDiv">
			<a href="javascript:void(0);" class="easyui-linkbutton" onclick="">保存</a>
		</div>
	</div>
</body>
</html>