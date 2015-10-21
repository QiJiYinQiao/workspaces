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
	<style type="text/css">
		table{margin: 0px auto;}
		table td{
			font-size: 16px;border: 1px solid black;
		}
		table.gridtable {
			font-size: 18px;
			font-family: verdana,arial,sans-serif;
			color:#333333;
			border-width: 1px;
			border-color: #666666;
			border-collapse: collapse;
		}
		table.gridtable th {
			border-width: 1px;
			padding: 8px;
			border-style: solid;
			border-color: #666666;
			background-color: #dedede;
			font-size: 20px;
		}
		table.gridtable td {
			border-width: 1px;
			padding: 8px;
			border-style: solid;
			border-color: #666666;
			background-color: #ffffff;
		}
	</style>
	
</head>
<body class="easyui-layout">
	<!-- <script type="text/javascript">
		$(function(){
			var left = document.getElementById("dayinfo").getBoundingClientRect().left;
			var width = document.getElementById("dayinfo").offsetWidth;
			
			document.getElementById("totalin").style.left = left+width+"px";
			alert(left+":"+width+":");
		});
	</script> -->
	<div data-options="region:'center',title:'日经营情况'" scrolling="no" style="background-color:#F8F8FF;text-align: center;">
		<div style="text-align:right;height: 30px;display: block;width: 100%;">
			日期　<input id="time" name="time" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;" class="Wdate" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy/MM/dd',maxDate:'#F{$dp.$D(\'enddate\')}'})" type="text"></input>
			--<input id="enddate" name="enddate" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;width: 100px;" class="Wdate" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy/MM/dd',minDate:'#F{$dp.$D(\'time\')}'})" type="text"></input>　
			<a onclick="daymanageinfo();" class="easyui-linkbutton" iconCls="icon-search" style="font-size:16px;" >查询</a>
		</div>
		<div style="text-align: center;overflow: auto;">
			<span style="font-size: 20px;" id="dates"></span>
			<%-- <span id="totalin" style="font-size: 16px;height: 20px;">总收入:${totalin }</span><span id="totalout" style="font-size: 16px;margin-left: 20px;">总支出:${totalout }</span> --%>
			<table id="dayinfo" class="gridtable" >
				<tr><th width="100px;" >国家</th><th width="100px;">订单数</th><th width="100px;">收入</th><th width="100px;">支出</th><th width="100px;">收入-支出</th><th>预计明日支出</th></tr>
					<c:if test="${lists!=null }">
						<c:forEach items="${lists }" var="list">
							<tr><td>${list.country }</td><td>${list.nums }</td><td>${list.totalincome }</td><td>${list.totalpay }</td><td>${list.discount }</td><td>${list.tomorrowzhichu }</td></tr>
						</c:forEach>
					</c:if>
				<tfoot><tr><td>总计</td><td>${allcount }</td><td><span id="totalin" style="font-size: 16px;">${totalin }</span></td><td><span id="totalout" style="font-size: 16px;">${totalout }</span></td><td><span id="totalout" style="font-size: 16px;">${totaldis }</span></td><td><span id="totalout" style="font-size: 16px;">${totaltomorrowzhichu }</span></td></tr></tfoot>
			</table>
		</div>
		
	</div>
		<script type="text/javascript">
			function daymanageinfo(){
				var time = $("#time").val();
				var enddate = $("#enddate").val();
				var str = "";
				var totalin  = 0;
				var totalout = 0;
				var allcount = 0;
				var totaltomorrowzhichu = 0;
				jQuery.ajax({
					url:"daymanageinfo",
					data:{time:time,enddate:enddate},
					type:"post",
					error:function(){},
					success:function(data){
						data = $.parseJSON(data);
						$.each(data,function(i,item){
							str += "<tr><td>"+item.country+"</td><td>"+item.nums+"</td><td>"+item.totalincome+"</td><td>"+item.totalpay+"</td><td>"+(item.totalincome-item.totalpay)+"</td><td>"+item.tomorrowzhichu+"</td></tr>"
							totalin += item.totalincome;
							totalout += item.totalpay;
							allcount += item.nums;
							totaltomorrowzhichu += item.tomorrowzhichu
						});
						//alert(totalin+":"+totalout);
						$("#dayinfo").empty();
						$("#dayinfo").append("<tr><th width='100px;' >国家</th><th width='100px;'>订单数</th><th width='100px;'>收入</th><th width='100px;'>支出</th><th width='100px;'>收入-支出</th><th>预计明日支出</th></tr>");
						$("#dayinfo").append(str);
						$("#dayinfo").append("<tfoot><tr><td>总计</td><td>"+allcount+"</td><td><span id='totalin' style='font-size: 16px;height: 20px;'></span></td><td><span id='totalout' style='font-size: 16px;'></span></td><td><span id='totoldis' style='font-size: 16px;'></span></td><td><span id='totaltomorrowzhichu' style='font-size: 16px;'></span></td></tr></tfoot>")
						$("#totalin").html(totalin);
						$("#totalout").html(totalout);
						$("#totoldis").html(totalin-totalout);
						$("#totaltomorrowzhichu").html(totaltomorrowzhichu);
						var beingdate = new Array();
						begindate = time.split("/");
						//alert(begindate[2]);
						if(enddate==""){
							$("#dates").html(begindate[0]+"年"+begindate[1]+"月"+begindate[2]+"日 经营情况");
						}else{
							var endtime = new Array();
							endtime = enddate.split("/");
							$("#dates").html(begindate[0]+"年"+begindate[1]+"月"+begindate[2]+"日"+"   到      "+endtime[0]+"年"+endtime[1]+"月"+endtime[2]+"日"+"    经营情况 ");
						}
					}
				});
			}
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
	<script type="text/javascript">
		$(function(){
			$("#time").val(new Date().pattern("yyyy/MM/dd"));
			$("#dates").html(new Date().pattern("yyyy年MM月dd日")+"经营情况");
		});
	</script>
	
</body>
 
</html>