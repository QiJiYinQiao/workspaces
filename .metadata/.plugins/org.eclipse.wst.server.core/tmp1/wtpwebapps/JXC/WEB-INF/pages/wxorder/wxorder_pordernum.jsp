<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>个人订单量</title>
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
        table{border-collapse:collapse;border-spacing:0;text-align: center;}
        td{padding:5px;margin:5px;} 
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
		}
		table.gridtable td {
			border-width: 1px;
			padding: 8px;
			border-style: solid;
			border-color: #666666;
			background-color: #ffffff;
		}
	</style>
	<style>
		.lightbox{width:300px;background:#FFFFFF;border:1px solid #ccc;line-height:25px; top:20%; left:20%;}
		.lightbox dt{background:#f4f4f4; padding:5px;}
	</style>
</head>
<body class="easyui-layout" >
	<div data-options="region:'center',title:'个人订单量'">
	<div class="indiv" style="height:100%;width:100%;background-color: #F8F8FF;overflow: hidden;" >
			<form id="managephoto" action="managephoto" method="post" style="float:right;">
					<table>
						<tr>
							<td>日期:</td><td><input id="begindate" name="begindate" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;width: 100px;" class="Wdate" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy/MM/dd',maxDate:'#F{$dp.$D(\'enddate\')}'})" type="text"></input></td>
							<td>--</td><td><input id="enddate" name="enddate" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;width: 100px;" class="Wdate" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy/MM/dd',minDate:'#F{$dp.$D(\'begindate\')}'})" type="text"></input></td>
							<td><a onclick="querys();" class="easyui-linkbutton" iconCls="icon-search" style="font-size:16px;float: right;" >查询</a></td>
						</tr>
					</table>
				</form>
				<table id="tableinput" class="gridtable" cellpadding="12" align="center" width="100%">
					${str }
				</table>	
			</div>
	</div>
			<script type="text/javascript">
				function querys(){
					var begindate = $("#begindate").val();
					var enddate = $("#enddate").val();
					jQuery.ajax({
						url:"pordernumstr",
						type:"post",
						data:{begindate:begindate,enddate:enddate},
						error:function(){},
						success:function(data){
							$("#tableinput").empty();
							data = $.parseJSON(data);
							//alert(data);
							var topss = "''"+data+"'";
							//$("#tableinput").append(tops);
							$("#tableinput").append(topss);
						}
					})
					
				}
				
			</script>
			<script type="text/javascript">
			$(function(){
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
				
				$("#begindate").val(new Date().pattern("yyyy/MM/dd"));
			});
			</script>
</body>
</html>