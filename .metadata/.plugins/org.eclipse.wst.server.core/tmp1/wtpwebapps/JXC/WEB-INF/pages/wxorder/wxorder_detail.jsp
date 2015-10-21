<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>订单详情</title>
	<link rel="stylesheet" type="text/css" href="../media/css/easyui.css">
	<link rel="stylesheet" type="text/css" href="../media/css/icon.css">
	<link rel="stylesheet" type="text/css" href="../media/css/demo.css">
	<script type="text/javascript" src="../media/js/WdatePicker.js"></script>
	<script type="text/javascript" src="../media/js/jquery.min.js"></script>
	<script type="text/javascript" src="../media/js/jquery.easyui.min.js"></script>
	<style type="text/css">
		input{
			border: 1px solid #95b8e7;border-radius: 5px;height: 20px;background-color: rgb(240,248,255);
		}
		td{
			font-size: 16px;
		}
		
		 .container{
                position:relative;
        }
        table{border-collapse:collapse;border-spacing:0;}
        td{padding:5px;margin:5px;} 
        table tr td input{
        	border: 1px solid #95b8e7;border-radius: 5px;background-color: rgb(240,248,255);
        	readonly:readonly;
        }
	</style>
	<style>
		.lightbox{width:300px;background:#FFFFFF;border:1px solid #ccc;line-height:25px; top:20%; left:20%;}
		.lightbox dt{background:#f4f4f4; padding:5px;}
	</style>
</head>
<body class="easyui-layout" >
	<div class="indiv" style="height:100%;width:100%;background-color: #F8F8FF;overflow: hidden;" >
		<table cellpadding="4" align="center" width="100%" style="margin-left: 20px;margin-top: 20px;">
		<!-- 一行四条信息 -->
		<tr>
			<%-- <td width="8%">订单号</td>
			<td width="14%"></td>
			 --%>
			<td width="8%">预定方式<span style="color: red;">*</span></td>
			<td width="14%">
				<input style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;" type="text" readonly="readonly" name="schedule_order" value="${wxoe.schedule_order }" ></input>
			</td>
			
			<td width="8%">姓名<span style="color: red;">*</span></td>
			<td width="14%">
			<input style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;" type="text" readonly="readonly"  name="uname" value="${wxoe.uname }"></input>
			</td>
			
			<td width="8%">手机号<span id="cellphone" style="color: red;">*</span></td>
			<td width="14%"><!-- checkMobile(this.value) -->
			<input style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;" type="text"  readonly="readonly" id="rent_p_tel" name="uphone" value="${wxoe.uphone }"></input>
			</td>
			
			<td width="10%">身份证号</td>
			<td width="15%"><input  type="text" name="uidcard" value="${wxoe.uidcard }" readonly="readonly" ></input></td>
		</tr>
		<tr>
			<td >护照号</td>
			<td ><input  type="text" name="upassport" value="${wxoe.upassport }" readonly="readonly" ></input></td>
		
			<td>国籍<span style="color: red;">*</span></td>
			<td ><input  type="text" name="upassport" value="${wxoe.nationality }" readonly="readonly" ></input></td>
			
			<td>取货地<span style="color: red;">*</span></td>
			<td colspan="3"><input style="width: 300px;" type="text" name="upassport" value="${wxoe.city }　　${wxoe.counter}" readonly="readonly" ></input></td>
		</tr>
		<tr>
			<td >目的地<span style="color: red;">*</span></td>
			<td width="15%"><input  type="text" name="upassport" value="${wxoe.destination }" readonly="readonly" ></input></td>
			
			<td>租用日期<span style="color: red;">*</span></td>
			<td>
			<input readonly="readonly" type="text" name="rentdate" value="${wxoe.rentdate }" readonly="readonly" />
			</td>
			
			<td>返还日期<span style="color: red;">*</span></td>
			<td>
			<input readonly="readonly" type="text" name="enddate" value="${wxoe.enddate }" readonly="readonly" />
			</td>
			
			<td>出行天数</td>
			<td ><input id="dayc" name="outday"  readonly="readonly" type="text"  value="${wxoe.outday }" ></input></td>
		
		</tr>
		
		<tr>
			<td>个数<span style="color: red;">*</span></td>
			<td>
			<input  style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;" type="text"  name="ordernum"  readonly="readonly"  value="${wxoe.ordernum }"></input>
			</td>
			
			<td>租赁费用 </td>
			<td><!-- onkeyup="keyups(this.value)"  -->
			<input  style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;"  readonly="readonly" type="text" name="rent_cost" value="${wxoe.rent_cost }" ></input>
			</td>
			
			<td>押金 </td>
			<td>
			<input id="deposit" name="deposit" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;" readonly="readonly" type="text"  value="${wxoe.deposit }"></input>
			</td>
			
			<td>应收总金额 </td>
			<td>
			<input id="shouldgetcost" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;" type="text"  readonly="readonly"  name="shouldgetcost" value="${wxoe.shouldgetcost }" ></input>
			</td>
		</tr>	
		<tr>
			<td>已收金额<span style="color: red;">*</span></td>
			<td>
			<input id="hadgetcost" onkeyup="hadgetcodekeyup(this.value)" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;" type="text"  readonly="readonly"  name="hadgetcost"  value="${wxoe.hadgetcost }"></input>
			</td>
			
			<td>待收金额 </td>
			<td>
			<input id="pregetcost" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;" type="text" readonly="readonly"  name="pregetcost" value="${wxoe.pregetcost }" ></input>
			</td>
			<%-- 
			<td>支付方式 </td>
			<td>
			<input  style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;" type="text" value="${wxoe.payment }"  readonly="readonly" ></input>
			</td> --%>
			
			<td>优惠方式 </td>
			<td>
			<input  style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;" type="text" value="${wxoe.discountway }" readonly="readonly"  ></input>
			</td>
		</tr>
		<tr>
			<td>日期 </td>
			<td>
			<input style="border: 1px solid #95b8e7;border-radius: 5px;" readonly="readonly" type="text" name="modifytime" value="${wxoe.modifytime }" />
			</td>
		
			<td>操作员 </td>
			<td>
			<input style="border: 1px solid #95b8e7;border-radius: 5px;" readonly="readonly" type="text" name="modifyuser"  value="${wxoe.modifyuser}"></input>
			</td>
		</tr>
		<tr>
			<td >收货地址 </td>
			<td colspan="7">
			<textarea  style="border: 1px solid #95b8e7;border-radius: 5px;height: 40px;width:90%;resize:none;overflow-Y:scroll;font-size: 18px;background-color: rgb(240,248,255);" resize="none"  readonly="readonly"  name="takeorderaddress">${wxoe.takeorderaddress }</textarea>
			</td>
		</tr>
		<tr>
			<td >备注 </td>
			<td colspan="7">
			<textarea  style="border: 1px solid #95b8e7;border-radius: 5px;height: 60px;width:90%;resize:none;overflow-Y:scroll;font-size: 18px;background-color: rgb(240,248,255);" resize="none"  readonly="readonly"  name="remark">${wxoe.remark }</textarea>
			</td>
		</tr>
		</table>	
			</div>
			
</body>
</html>