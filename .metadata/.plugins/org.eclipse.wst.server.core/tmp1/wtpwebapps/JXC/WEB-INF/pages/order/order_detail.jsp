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
				<td width="15%">订单号</td>
				<td width="15%"><input type="text" readonly="readonly" id="order_num"  value="${orderdetail.order_num }"></input></td>
				
				<td width="15%">姓名</td>
				<td width="15%"><input id="rent_p_name"  readonly="readonly"  type="text" name="rent_p_name" value="${orderdetail.rent_p_name }" ></input></td>
				
				<td width="15%">手机号 </td>
				<td>
				<input width="15%"  type="text" readonly="readonly"  id="rent_p_tel" value="${orderdetail.rent_p_tel }"></input>
				</td>
				</tr>
				
				<tr>
				<td width="10%">设备号</td>
				<td>
				<input id="equipment_no" width="15%"  readonly="readonly" type="text" name="equipment_no" value="${orderdetail.equipment_no }"></input>
				</td>
				
				<td>出发地 </td>
				<td>
				<input  type="text" id="s_country"  readonly="readonly" value="${orderdetail.s_country }"></input>
				</td>
				
				<td >目的地</td>
				<td>
				<input id="d_country" type="text" readonly="readonly"  name="d_country" value="${orderdetail.d_country }"></input>
				</td>
				</tr>
				
				<tr>
				<td>租用日期</td>
				<td>
				<input id="rent_date" type="text" readonly="readonly"  id="rent_begindate" value="${orderdetail.rent_begindate }" />
				<td>预计返还日期</td>
				<td>
				<input id="rent_expectdate"  type="text" readonly="readonly"  id="rent_expectdate" value="${orderdetail.rent_expectdate }" />
				</td>
				<td>实际返还日期</td>
				<td>
				<input id="real_return"  readonly="readonly"   type="text" name="rent_enddate"  value="${orderdetail.rent_enddate }" />
				</td>
				</tr>
				
				<tr>
					<td>预收押金 </td>
					<td>
					<input type="text" readonly="readonly"  name="cost_rent_3g"  value="${deposit }"></input>
					</td>
					<td>预收总金额 </td>
					<td>
					<input id="upfront_sum"  type="text"  readonly="readonly"  name="upfront_sum" value="${orderdetail.upfront_sum }"></input>
					</td>
					<td>支付方式 </td>
					<td>
					<input  type="text"  readonly="readonly"  name="payment" value="${orderdetail.payment }"></input>
					</td>
				</tr>
				
				<tr>
					<td>日租金 </td>
					<td>
					<input id="cost_rent_3g"  type="text" readonly="readonly"  name="rent_day"  value="${orderdetail.rent_day }"></input>
					</td>
					<td>实际出行天数</td>
					<td>
					<input id="daycounts"  type="text" readonly="readonly"  name=""  value=""></input>
					</td>
					<td>租赁费用</td>
					<td>
					<input type="text" readonly="readonly"  name="cost_rent_3g"  value="${orderdetail.cost_rent_3g }"></input>
					</td>
					
				</tr>
				<tr>
					<td>优惠金额 </td>
					<td>
					<input type="text" readonly="readonly"  name="cost_discount"  value="${orderdetail.cost_discount }"></input>
					</td>
					<td>退租金额 </td>
					<td>
					<input   type="text"  readonly="readonly"  name="COST_RETURN_DISCOUNT" value="${orderdetail.cost_return_discount }"></input>
					</td>
					<td>实收金额 </td>
					<td>
					<input type="text" readonly="readonly"  name="cost_sum"  value="${orderdetail.cost_sum }"></input>
					</td>
					
				</tr>
				
				<tr>
					<td >舱位等级</td>
					<td ><input type="text"  name="position_level"  readonly="readonly"  value="${orderdetail.position_level }"></input></td>
			
					<td width="10%">身份证号</td>
					<td width="15%"><input  type="text" id="rent_p_idnumber" readonly="readonly"  value="${orderdetail.rent_p_idnumber }"></input></td>
					<td width="10%">护照号</td>
					<td width="15%"><input type="text" id="rent_p_passportno"  readonly="readonly" value="${orderdetail.rent_p_passportno }"></input></td>
				</tr>
				
				<tr>
					<td>国籍 　</td>
					<td>
					<input  type="text" id="rent_p_nationality"  readonly="readonly" value="${orderdetail.rent_p_nationality }"></input>
					</td>
					<td>日期 </td>
					<td>
					<input type="text" id="modify_time"  readonly="readonly" value="${orderdetail.modify_time }"/>
					</td>
					<td>操作员 </td>
					<td>
					<input type="text" id="modify_user"  readonly="readonly"  value="${orderdetail.modify_user }"></input>
					</td>
				</tr>
				
				<tr>
					<td >备注 </td>
					<td colspan="7">
					<textarea  style="background-color:#F0F8FF;border: 1px solid #95b8e7;border-radius: 5px;height: 100px;width:98%;resize:none;font-size: 18px;color: black;" resize="none"  readonly="readonly"  id="remark" >${orderdetail.remark }</textarea>
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
				
				//====================计算两个日期间的时间差（天数）开始===============================
				$(function(){
					var realreturn = $("#real_return").val();
					var rentdate = $("#rent_date").val();
					var d3 = $("rent_expectdate").val();
					
					var date1=null;
					var date2=new Date();
					function Swith(strdate){
					var strYear=strdate.substring(0,4);
					var strMonth=strdate.substring(5,7); 
					var strDay=strdate.substring(8,10);
					/* var strHours=strdate.substring(11,13);
					var strMinutes=strdate.substring(14,16); ,strHours,strMinutes*/
					return new Date(strYear,strMonth,strDay); 
					}
					
					function GetTime(dateM,datetype){
					     var s;                            // 声明变量。
					     var MinMilli = 1000 * 60;         // 初始化变量。
					     var HrMilli = MinMilli * 60;
					     var DyMilli = HrMilli * 24;
					     //s="";
					     if (datetype=="d"){
					     s=Math.round(Math.abs(dateM/DyMilli));
					     }else if (datetype=="h"){
					    s +=Math.round(Math.abs(dateM/HrMilli))+"小时"
					     }else if (datetype=="m"){
					    s +=Math.round(Math.abs(dateM/MinMilli))+"分";
					     } else{
					    s +=Math.round(Math.abs(dateM/1000))+"秒"
					     }
					     return(s);                        // 返回结果。
					}
					if(realreturn!=""){
						var daycounts = GetTime((Date.parse(Swith(realreturn))-Date.parse(Swith(rentdate))),"d")+1;
						$("#daycounts").val(daycounts);
					}
				});	
			</script>
</body>
</html>