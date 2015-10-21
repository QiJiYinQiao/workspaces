<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="../media/css/easyui.css">
	<link rel="stylesheet" type="text/css" href="../media/css/icon.css">
	<link rel="stylesheet" type="text/css" href="../media/css/demo.css">
	<script type="text/javascript" src="../media/js/jquery.min.js"></script>
	<script type="text/javascript" src="../media/js/WdatePicker.js"></script>
	<script type="text/javascript" src="../media/js/jquery.easyui.min.js"></script>
	<style type="text/css">
		.datagrid-view{
			background-color: #F8F8FF;
			font-size: 16px;
		}
		input{
			border: 1px solid #95b8e7;
			border-radius: 5px;
			height: 20px;
			width:120px;
			font-size:16px;
		}
	</style>
</head>
<body class="easyui-layout">
		<script type="text/javascript">
			$(function(){
				var begindate = new Date().pattern("yyyy-MM-dd");
				$("#rent_begindate").val(new Date().pattern("yyyy-MM-dd"));
				$("#dg").datagrid({
					url:"queryPassengerDetail",
					queryParams:{
						rent_begindate:begindate,
					}
				})
			});
		</script>
		<!-- 表单开始 -->
		<div data-options="region:'center',title:'用户列表'" style="overflow:hidden;left:1px; top:1px;background-color: #F8F8FF;text-align: center;width: 100%;height: 100%;">
				<div style="background-color:#F8F8FF;align:center;width: 100%;height: 85px;overflow: hidden;">
					<form id="queryBy" action="queryByCondition" method="post" style="margin-left: 1%;text-align: left;width: 100%;">
						<table style="width: 100%;"><!-- 旅客姓名 旅客电话 旅客护照号 出行航班 出行日期  返回航班 返回日期 代理商 -->
							<tr>
								<td>旅客姓名</td>
								<td><input id="username" name="username" style="border: 1px solid #95b8e7;border-radius: 5px;"></td>
								<td>代理商</td>
								<td><input id="transforname" name="transforname" value="" style="border: 1px solid #95b8e7;border-radius: 5px;"  />
								</td>
								<td>旅客电话</td>
								<td><input id="usertel" name="usertel" style="border: 1px solid #95b8e7;border-radius: 5px;"></td>
								<td>旅客护照号</td>
								<td><input id="userpassport" name="userpassport" value="" style="border: 1px solid #95b8e7;border-radius: 5px;" />
								</td>
							</tr>
							<tr>
								<td>出行航班</td>
								<td><input id="flightb" name="flightb" style="border: 1px solid #95b8e7;border-radius: 5px;"></td>
								<td>出行日期</td>
								<td><input id="rent_begindate" name="rent_begindate" value="" class="Wdate" style="border: 1px solid #95b8e7;border-radius: 5px;" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy-MM-dd'})" />
								</td>
								<td>返回航班</td>
								<td><input id="flighte" name="flighte" style="border: 1px solid #95b8e7;border-radius: 5px;"></td>
								<td>返回日期</td>
								<td><input id="rent_enddate" name="rent_enddate" value="" class="Wdate" style="border: 1px solid #95b8e7;border-radius: 5px;" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy-MM-dd'})" />
								</td>
							</tr>
						</table>
					</form>
					<div align="right" style="margin-bottom: 5px;">
						<!-- <a onclick="checkdetail();" class="easyui-linkbutton" style="margin-right: 5px;width: 60px;height: 25px;">详情</a> -->
						<a onclick="submits();" class="easyui-linkbutton" style="margin-right: 5px;width: 60px;height: 25px;">查询</a>
					</div>
				</div>
					
				<table id="dg" class="easyui-datagrid" style="height: 80%;"  
				data-options="rownumbers:true,pagination:true,singleSelect:true,method:'post',onDblClickRow:doubleclick">
				<thead>
				<tr><!-- 旅客姓名 旅客电话 旅客护照号 出行航班 出行日期  返回航班 返回日期 代理商  -->
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'username',width:100,align:'center'">旅客姓名</th>
				<th data-options="field:'transforname',width:200,align:'center'">代理商</th>
				<th data-options="field:'usertel',width:100,align:'center'">旅客电话</th>
				<th data-options="field:'userpassport',width:100,align:'center'">旅客护照号</th>
				<th data-options="field:'flightb',width:110,align:'center'">出行航班</th>
				<th data-options="field:'rent_begindate',width:160,align:'center'">出行日期</th>
				<th data-options="field:'flighte',width:100,align:'center'">返回航班</th>
				<th data-options="field:'rent_enddate',width:160,align:'center'">返回日期</th>
				<th data-options="field:'modify_user',width:100,align:'center'">操作员</th>
				<th data-options="field:'modify_time',width:160,align:'center'">日期</th>
				</tr>
				</thead>
				</table>
		<!-- 表单结束 -->
		
			<div id="dlg"  class="easyui-dialog" modal="true" closed="true" title="订单详情"  style="width:880px;height:450px;overflow:hidden;">
				<iframe id="detail" name="contents" src="" width="100%" height="100%" frameborder="0" style="overflow:hidden"></iframe>
			</div>
		</div>
	 <script type="text/javascript">
		$(function(){
			var pager = $('#dg').datagrid().datagrid('getPager'); // get the pager of datagrid
			 $(pager).pagination({  
				 	beforePageText: '第',//页数文本框前显示的汉字  
			        afterPageText: '页    共 {pages} 页',  
			        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
			        pageList:[10,20,50,100,200],
			    });  
		})
		
		
		function submits(){
			var username = $("#username").val();
			var transforname = $("#transforname").val();
			var usertel = $("#usertel").val();
			var userpassport = $("#userpassport").val();
			var flightb = $("#flightb").val();
			var rent_begindate = $("#rent_begindate").val();
			var flighte = $("#flighte").val();
			var rent_enddate = $("#rent_enddate").val();
			
			$("#dg").datagrid({
				url:"queryPassengerDetail",
				queryParams:{
					username:username,transforname:transforname,usertel:usertel,userpassport:userpassport,
					flightb:flightb,rent_begindate:rent_begindate,flighte:flighte,rent_enddate:rent_enddate,
				}
			});
			var pager = $('#dg').datagrid().datagrid('getPager'); // get the pager of datagrid
			 $(pager).pagination({  
				 	beforePageText: '第',//页数文本框前显示的汉字  
			        afterPageText: '页    共 {pages} 页',  
			        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
			        pageList:[10,20,50,100,200],
			 });  
		}
		
		
		function checkdetail(){
			  var field = $("#dg").datagrid("getSelected");
			  if(field==null){
				  alert("请先选择一条数据");
			  }
			  document.getElementById("detail").src="detail?id="+field.id;
			  $('#dlg').dialog('open');
		}
		
		function doubleclick(index,field){
			 // parent.menus.window.document.getElementBmenu_shadow
			  var r = $("#dg").datagrid("getRows",index);
			  document.getElementById("detail").src="detail?id="+field.id;
			  $('#dlg').dialog('open');
		}
	</script>
	<script type="text/javascript">
		//$("#begindate").val(new Date().pattern("yyyy-MM-dd"));
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