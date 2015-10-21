<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>SIM卡详情</title>
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
<body class="easyui-layout" ><!-- cellpadding="2" -->
	<div class="indiv" style="height:100%;width:100%;background-color: #F8F8FF;overflow: hidden;" >
				<table  align="center" style="width: 100%;height: 100%;" cellspacing="2">
				<!-- 一行四条信息 -->
				<tr>
					<td width="10%">卡号</td>
					<td width="15%"><input type="text" readonly="readonly" name="operators"  value="${info.id }"></input></td>
					
					<td width="10%">运营商</td>
					<td width="15%"><input type="text" readonly="readonly" name="operators"  value="${info.operators }"></input></td>
					
					<td width="10%">国家</td>
					<td width="15%"><input readonly="readonly"  type="text" name="country" value="${info.country }" ></input></td>
					
					<td width="10%">日租金 </td>
					<td>
					<input width="15%"  type="text" readonly="readonly"  name="day_rent" value="${info.day_rent }"></input>
					</td>
				
				</tr>
				
				<tr>
					<td width="10%">是否有效</td>
					<td width="15%">
					<input id="d_country" type="text" readonly="readonly"  name="is_valid" value="${info.is_valid }"></input>
					</td>
					
					<td>开卡时间</td>
					<td>
					<input id="equipment_no" width="15%"  readonly="readonly" type="text" name="day_begin" value="${info.day_begin }"></input>
					</td>
					
					<td>卡到期时间 </td>
					<td>
					<input  type="text" name="day_end"  readonly="readonly" value="${info.day_end }"></input>
					</td>
					
					<td>操作员</td>
					<td>
					<input id="rent_date" type="text" readonly="readonly"  name="modify_user" value="${info.modify_user }" />
				</tr>
				
				<tr>
					<td>日期</td>
					<td>
					<input id="rent_expectdate"  type="text" readonly="readonly"  name="modify_time" value="${info.modify_time }" />
					</td>
				</tr>
				<tr>
				<td >备注 </td>
				<td colspan="7">
				<textarea name="remark" style="background-color:#F0F8FF;border: 1px solid #95b8e7;border-radius: 5px;height: 60px;width:98%;resize:none;font-size: 18px;color: black;" resize="none"  readonly="readonly"  id="remark" >${info.remark }</textarea>
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
					var d1 = $("#real_return").val();
					var d2 = $("#rent_date").val();
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
				});	
			</script>
</body>
</html>