<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>登陆首页</title>
	<link rel="stylesheet" type="text/css" href="../media/css/easyui.css">
	<link rel="stylesheet" type="text/css" href="../media/css/icon.css">
	<link rel="stylesheet" type="text/css" href="../media/css/demo.css">
	<script type="text/javascript" src="../media/js/WdatePicker.js"></script>
	<script type="text/javascript" src="../media/js/jquery.min.js"></script>
	<script type="text/javascript" src="../media/js/jquery.easyui.min.js"></script>
	<style type="text/css">
		input{
			border: 1px solid #95b8e7;border-radius: 5px;height: 20px;
		}
		td{
			font-size: 16px;
		}
		
		 .container{
                position:relative;
        }
        .search{
                background-image:url(../media/css/icons/search.png);
                background-repeat:no-repeat;
                height: 30px;
			    left: 120px;
			    position: absolute;
			    top: 4px;
			    width: 30px;
			    z-index: 99;
        }
	</style>
	<style>
		.lightbox{width:300px;background:#FFFFFF;border:1px solid #ccc;line-height:25px; top:20%; left:20%;}
		.lightbox dt{background:#f4f4f4; padding:5px;}
	</style>
</head>
<body class="easyui-layout" >
	<script type="text/javascript">
			/* $(function(){
				var left = document.getElementById("sim_id").getBoundingClientRect().left;
				var top = document.getElementById("sim_id").getBoundingClientRect().top;
				var width = document.getElementById("sim_id").offsetWidth;
				
				document.getElementById("search_img").style.left = left+width-25+"px";
				document.getElementById("search_img").style.top = top+2+"px";
			});  */
	</script>
		<!-- 表单开始 action="add" method="POST"-->
		<form id="equipment"  >
		<table cellpadding="5" align="center" width="100%" style="margin-left: 2%;margin-top: 1%;">
		<!-- 一行四条信息 -->
		<tr>
		<td width="10%">日期<span style="color: red;">*</span> </td>		
		<td width="17%"><input id="create_time" style="border: 1px solid #95b8e7;border-radius: 5px;background-color:#B0C4DE;" readonly="readonly" type="text" name="no"  ></input></td>
	
		<td  width="10%">盘点人</td>
		<td width="17%">
		<input id="inventory_user" width="15%"  type="text" style="border: 1px solid #95b8e7;border-radius: 5px;background-color:#B0C4DE;" readonly="readonly" name="inventory_user" value="${realname }"  ></input>
		</td>
		
		<td width="10%">时间</td>
		<td width="17%"><input id="inventory_time" width="15%" style="border: 1px solid #95b8e7;border-radius: 5px;background-color:#B0C4DE;"  readonly="readonly"  type="text" name="inventory_time" value=""  ></input></td>
		</tr>
		<tr>
			<td >备注 </td>
			<td colspan="7">
			<textarea id="remark"  style="border: 1px solid #95b8e7;border-radius: 5px;height: 100px;width:90%;resize:none;overflow-Y:scroll;font-size: 18px;" resize="none"  name="remark"></textarea>
			</td>
		</tr>
		</table>
		</form>
	<!-- 处理租用日期 返还日期 -->
	<script type="text/javascript">
		//设置租用日期的初始时间为当前时间
		$(function(){
			$("#create_time").val(new Date().pattern("yyyy/MM/dd"));
			$("#inventory_time").val(new Date().pattern("yyyy/MM/dd hh:mm:ss"));
		});
	</script>
	
	<script language="javascript" type="text/javascript">  
			   /**     
				 * 对Date的扩展，将 Date 转化为指定格式的String     
				 * 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q) 可以用 1-2 个占位符     
				 * 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)     
				 * eg:     
				 * (new Date()).pattern("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423     
				 * (new Date()).pattern("yyyy-MM-dd E HH:mm:ss") ==> 2009-03-10 二 20:09:04     
				 * (new Date()).pattern("yyyy-MM-dd EE hh:mm:ss") ==> 2009-03-10 周二 08:09:04     
				 * (new Date()).pattern("yyyy-MM-dd EEE hh:mm:ss") ==> 2009-03-10 星期二 08:09:04     
				 * (new Date()).pattern("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18     
				 */       
				Date.prototype.pattern=function(fmt) {        
				    var o = {        
				    "M+" : this.getMonth()+1, //月份        
				    "d+" : this.getDate(), //日        
				    "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时        
				    "H+" : this.getHours(), //小时        
				    "m+" : this.getMinutes(), //分        
				    "s+" : this.getSeconds(), //秒        
				    "q+" : Math.floor((this.getMonth()+3)/3), //季度        
				    "S" : this.getMilliseconds() //毫秒        
				    };        
				    var week = {        
				    "0" : "\日",        
				    "1" : "\一",        
				    "2" : "\二",        
				    "3" : "\三",        
				    "4" : "\四",        
				    "5" : "\五",        
				    "6" : "\六"       
				    };        
				    if(/(y+)/.test(fmt)){        
				        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));        
				    }        
				    if(/(E+)/.test(fmt)){        
				        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "\星\期" : "\周") : "")+week[this.getDay()+""]);        
				    }        
				    for(var k in o){        
				        if(new RegExp("("+ k +")").test(fmt)){        
				            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));        
				        }        
				    }        
				    return fmt;        
				}      
				    
</script>    
</body>
</html>