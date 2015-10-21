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
			font-size: 16px;
		}
	</style>
</head>
<body class="easyui-layout">
	<div data-options="region:'center',title:'经营图表'" scrolling="no" style="overflow:hidden;background-color:#F8F8FF;">
		<%-- <form id="managephoto" action="managephoto" method="post">
			<table>
				<tr>
					<td>开始时间</td><td><input id="begindate" name="begindate" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;" class="Wdate" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy/MM/dd'})" type="text"></input></td>
					<td>结束时间</td><td><input id="enddate" name="enddate" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;" class="Wdate" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy/MM/dd'})" type="text"></input></td>
					<td>柜台</td>
					<td>
						<select id="countercode" name="countercode"  style="border: 1px solid #95b8e7;border-radius: 5px;height: 24px;width: 140px;" >
							<option value="" >全部</option>
							<c:if test="${counterlist!=null }">
								<c:forEach items="${counterlist }" var="counterlists">
									<option value="${counterlists.counter_code }">${counterlists.name }</option>
								</c:forEach>
							</c:if>
						</select>　
					</td>
					<a onclick="managephoto();" class="easyui-linkbutton" iconCls="icon-search" style="font-size:16px;float: right;" >查询</a>
				</tr>
			</table>
		</form> --%>
		<div id="chartContainer" style="width: 50%;float: left;height: 50%;text-align: center;">
			<iframe src="numbersOfOrdersPie" width="100%" height="100%" frameborder="0" style="overflow:hide;"></iframe>
		</div>
		<div id="chart-container" style="width: 50%;float: right;height: 50%;align: center;">
				<iframe src="everyDayOrderNums" width="100%" height="100%" frameborder="0" style="overflow:hide;"></iframe>
		</div>
		<div id="chartline" style="width: 50%;float: left;height: 50%;text-align: center;">
				<iframe src="dayInAndOut" width="100%" height="100%" frameborder="0" style="overflow:hide;"></iframe>
		</div>
		<div id="chartline" style="width: 50%;float: right;height: 50%;text-align: center;">
				<iframe src="dayInOutProportion" width="100%" height="100%" frameborder="0" style="overflow:hide;"></iframe>
		</div>
	</div>
		
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
				url:"managephoto",
				type:"post",
				data:{begindate:begindate,enddate:enddate,countercode:countercode},
				error:function(){},
				success:function(data){
					data = $.parseJSON(data);
					alert(data);
					columnChart(data);
					pieChart(data);
					//lineChart(data);
				}
			});
		}
		
		$(function(){
			$("#createtime").val(new Date().pattern("yyyy/MM/dd HH:mm:ss"));
		});
	</script>
	<script type="text/javascript">
		function columnChart(datas){
			var chart = "'chart': {'caption': 'Monthly revenue for last year','subCaption': 'Harrys SuperMart','xAxisName': 'Month','yAxisName': 'Revenues (In USD)','theme': 'zune'}";
			alert(chart);
			var data = "{'data':"+datas+","+chart+"}";
			alert(data);
			FusionCharts.ready(function(){
				var revenueChart = new FusionCharts({
				type: "column2d",
				renderAt: "chartContainer",
				width: "500",
				height: "300",
				dataFormat: "json",
				dataSource: data,
				});
				revenueChart.render("chartContainer");
				});
		}
		/* {
		"chart": {
			"caption": "Monthly revenue for last year",
			"subCaption": "Harry's SuperMart",
			"xAxisName": "Month",
			"yAxisName": "Revenues (In USD)",
			"theme": "zune"
			},
			"data": datas
	} */
		function pieChart(datas){
			var chart = "'chart': {" +
	            "'caption': 'Age profile of website visitors'," +
	            "'subCaption': 'Last Year'," +
	            "'startingAngle': '120'," +
	            "'showLabels': '0'," +
	            "'showLegend': '1'," +
	           " 'enableMultiSlicing': '0'," +
	            "'slicingDistance': '15'," +
	            //To show the values in percentage
	            "'showPercentValues': '1'," +
	            "'showPercentInTooltip': '0'," +
	            "'plotTooltext': 'Age group : $label<br>Total visit : $datavalue'," +
	            "'theme': 'fint'}" ;
	            
	          var data = "{ 'data':"+datas+","+chart+"}";
			FusionCharts.ready(function () {
			    var demographicsChart = new FusionCharts({
			        type: 'pie3d',
			        renderAt: 'chart-container',
			        width: '450',
			        height: '300',
			        dataFormat: 'json',
			        dataSource: data,
			    });
			    demographicsChart.render();
			});
		}
		/* {
    	"chart": {
            "caption": "Age profile of website visitors",
            "subCaption": "Last Year",
            "startingAngle": "120",
            "showLabels": "0",
            "showLegend": "1",
            "enableMultiSlicing": "0",
            "slicingDistance": "15",
            //To show the values in percentage
            "showPercentValues": "1",
            "showPercentInTooltip": "0",
            "plotTooltext": "Age group : $label<br>Total visit : $datavalue",
            "theme": "fint"
        },
        "data": datas
    } */
		function lineChart(datas){
			FusionCharts.ready(function () {
			    var stockPriceChart = new FusionCharts({
			        id: "stockRealTimeChart",
			        type: 'realtimeline',
			        renderAt: 'chartline',
			        width: '500',
			        height: '300',
			        dataFormat: 'json',
			        dataSource: datas,
			       "events": {
			           "initialized": function (e) {
			               function addLeadingZero(num){
			                   return (num <= 9)? ("0"+num) : num;
			               }
			               function updateData() {
			                    // Get reference to the chart using its ID
			                    var chartRef = FusionCharts("stockRealTimeChart"),
			                        // We need to create a querystring format incremental update, containing
			                        // label in hh:mm:ss format
			                        // and a value (random).
			                        currDate = new Date(),
			                        label = addLeadingZero(currDate.getHours()) + ":" +
			                                addLeadingZero(currDate.getMinutes()) + ":" +
			                                addLeadingZero(currDate.getSeconds()),
			                        // Get random number between 35.25 & 35.75 - rounded to 2 decimal places
			                        randomValue = Math.floor(Math.random()     
			                                                  * 50) / 100 + 35.25,
			                        // Build Data String in format &label=...&value=...
			                        strData = "&label=" + label 
			                                    + "&value=" 
			                                    + randomValue;
			                    // Feed it to chart.
			                    chartRef.feedData(strData);
			                }

			               var myVar = setInterval(function () {
			                   updateData();
			               }, 5000);
			           }
			       }
			    })
			    .render();
			});
		}
	</script>
</body>
 
</html>