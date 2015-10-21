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
	</style>
	<style>
		.lightbox{width:300px;background:#FFFFFF;border:1px solid #ccc;line-height:25px; top:20%; left:20%;}
		.lightbox dt{background:#f4f4f4; padding:5px;}
	</style>
</head>
<body class="easyui-layout" >
	<div class="indiv" style="height:100%;width:100%;background-color: #F8F8FF;overflow: hidden;" >
				<table cellpadding="12" align="center" width="100%">
				<!-- 一行四条信息 -->
				<tr>
				<td width="10%">设备编号</td>
				<td width="15%"><input type="text" readonly="readonly" id="order_num"  value="${equipment.no }"></input></td>
				
				<td width="15%">SIM卡</td>
				<td width="15%"><input id="rent_p_name"  readonly="readonly"  type="text" name="rent_p_name" value="${equipment.sim_id }" ></input></td>
				
				<td width="15%">柜台 </td>
				<td>
				<input width="15%"  type="text" readonly="readonly"  id="rent_p_tel" value="${equipment.counterid }"></input>
				</td>
				</tr>
				
				<tr>
				<td width="10%">租用日期</td>
				<td>
				<input id="equipment_no" width="15%"  readonly="readonly" type="text" name="equipment_no" value="${equipment.rent_begindate }"></input>
				</td>
				
				<td>预计返还日期 </td>
				<td>
				<input  type="text" id="s_country"  readonly="readonly" value="${equipment.rent_expectdate }"></input>
				</td>
				
				<td >实际返还日期</td>
				<td>
				<input id="d_country" type="text" readonly="readonly"  name="d_country" value="${equipment.rent_enddate }"></input>
				</td>
				</tr>
				
				<tr>
				<td>进货日期</td>
				<td>
				<input id="rent_date" type="text" readonly="readonly"  id="rent_begindate" value="${equipment.stock_date }" />
				<td>报废日期</td>
				<td>
				<input id="rent_expectdate"  type="text" readonly="readonly"  id="rent_expectdate" value="${equipment.scrap_date }" />
				</td>
				<td>日租金</td>
				<td>
				<input id="real_return"  readonly="readonly"   type="text" name="rent_enddate"  value="${equipment.day_rent }" />
				</td>
				</tr>
				
				<tr>
					<td>押金 </td>
					<td>
					<input  type="text" readonly="readonly"  name="cost_rent_3g"  value="${equipment.deposit }"></input>
					</td>
					<td>设备租金 </td>
					<td>
					<input type="text" readonly="readonly"  name="cost_rent_3g"  value="${equipment.total_rent}"></input>
					</td>
					<td>设备类型 </td>
					<td>
					<input  type="text"  readonly="readonly"  name="upfront_sum" value="${equipment.equipment_type }"></input>
					</td>
				</tr>
				
				<tr>
					<td>状态</td>
					<td>
					<input  type="text" readonly="readonly"  name=""  value="${equipment.equipment_state }"></input>
					</td>
					<td>是否有效 </td>
					<td>
					<input  type="text" readonly="readonly"  name="rent_day"  value="${equipment.is_valid }"></input>
					</td>
				</tr>
				<tr>
					<td>创建人 </td>
					<td>
					<input type="text" readonly="readonly"  name="cost_discount"  value="${equipment.create_user }"></input>
					</td>
					<td>创建时间 </td>
					<td>
					<input type="text" readonly="readonly"  name="cost_discount"  value="${equipment.create_time }"></input>
					</td>
				</tr>
				
				<tr>
				<td >备注 </td>
				<td colspan="7">
				<textarea  style="background-color:#F0F8FF;border: 1px solid #95b8e7;border-radius: 5px;height: 100px;width:98%;resize:none;font-size: 18px;color: black;" resize="none"  readonly="readonly"  id="remark" >${equipment.remark }</textarea>
				</td>
				</tr>
				</table>	
			</div>
			<script type="text/javascript">
				function  hide(){
					alert(document.getElementById("screens"));
					alert(top.document.getElementById("screens"));
					top.document.getElementById("screens").fadeOut("1500");
				}
				
			</script>
</body>
</html>