<%@page import="com.sun.xml.internal.bind.CycleRecoverable.Context"%>
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
	<script type="text/javascript" src="../media/js/jquery.min.js"></script>
	<script type="text/javascript" src="../media/js/WdatePicker.js"></script>
	<script type="text/javascript" src="../media/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="../media/fusioncharts/fusioncharts.js"></script>
	<script type="text/javascript" src="../media/fusioncharts/themes/fusioncharts.theme.zune.js"></script>
	<style type="text/css">
		table td{
			font-size: 10px;
		}
	</style>
</head>
<body class="easyui-layout" style="background-color:#F8F8FF;">
		<script type="text/javascript">
				$(function(){
					var left = document.body.clientWidth;
					var top = document.body.clientHeight;
					
					document.getElementById("shadow").style.left = "0px";
					document.getElementById("shadow").style.top = top-25+"px";
				}); 
		</script>
		<form id="managephoto" action="managephoto" method="post" style="float:right;">
			<table>
				<tr>
					<td>日期:</td><td><input id="begindate" name="begindate" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;width: 100px;" class="Wdate" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy/MM/dd',maxDate:'#F{$dp.$D(\'enddate\')}'})" type="text"></input></td>
					<td>--</td><td><input id="enddate" name="enddate" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;width: 100px;" class="Wdate" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy/MM/dd',minDate:'#F{$dp.$D(\'begindate\')}'})" type="text"></input></td>
					<td>柜台</td>
					<td>
						<select id="countercode" name="countercode"  style="border: 1px solid #95b8e7;border-radius: 5px;height: 22px;width: 120px;" >
							<option value="" >全部</option>
							<c:if test="${counterlist!=null }">
								<c:forEach items="${counterlist }" var="counterlists">
									<option value="${counterlists.counter_code }">${counterlists.name }</option>
								</c:forEach>
							</c:if>
						</select>　
					</td>
				<td><a onclick="managephoto();" class="easyui-linkbutton" iconCls="icon-search" style="font-size:16px;float: right;" >查询</a></td>
				</tr>
			</table>
		</form>
		<div id="dayInOutProportion" style=""></div>
		<div id="shadow" style="width:140px;height:40px;background-color: #F8F8FF;position:absolute; z-index:99999;"></div>
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
	<script type="text/javascript">
		function managephoto(){
			/* $("#managephoto").submit(); */
			var begindate = $("#begindate").val();
			var enddate = $("#enddate").val();
			var countercode = $("#countercode").val();
			jQuery.ajax({
				url:"dayInOutProportions",
				type:"post",
				data:{begindate:begindate,enddate:enddate,countercode:countercode},
				error:function(){},
				success:function(data){
					// alert(data);
					dayInOutProportion(data);
				}
			});
		}
		
		$(function(){
			var today = Date.parse(new Date());
			var diffday = 30*24*60*60*1000;
			var begin = today-diffday;
			var begindate = new Date(begin).pattern("yyyy/MM/dd");
			$("#enddate").val(new Date().pattern("yyyy/MM/dd"));
			$("#begindate").val(begindate);
			var enddate = $("#enddate").val();
			var countercode = $("#countercode").val();
			jQuery.ajax({
				url:"dayInOutProportions",
				type:"post",
				data:{begindate:begindate,enddate:enddate,countercode:countercode},
				error:function(){},
				success:function(data){
					dayInOutProportion(data);
				}
			});
		});
	</script>
	<script type="text/javascript">
		function dayInOutProportion(data){
			var width =  document.body.scrollWidth-40;
			var height =  document.body.scrollHeight-40;
			FusionCharts.ready(function(){
		        var myChart = new FusionCharts({
		            "type": "scrollcombidy2d",
		            "dataFormat": "xml",
		            "renderAt" : "dayInOutProportion",
			        "width": width,
			        "height": height,
		            "dataSource":data
		            });
		        myChart.render();
		        });
		}
    </script>
</body>
 
</html>