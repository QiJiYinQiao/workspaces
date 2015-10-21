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
        .datagrid-view{
			background-color: #F8F8FF;
			font-size: 16px;
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
      		 .file-box{ position:relative;width:340px}
			.txt{ height:22px; border:1px solid #cdcdcd; width:180px;}
			.btn{ background-color:#FFF; border:1px solid #CDCDCD;height:24px; width:70px;}
			.file{ position:absolute; top:0px; right:80px; height:24px; filter:alpha(opacity:0);opacity: 0;width:260px }
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
			$(function(){
				$("#dg").datagrid({
					url:"getinventorylist",
				})
				 var pager = $('#dg').datagrid().datagrid('getPager'); // get the pager of datagrid
				 $(pager).pagination({  
					 	beforePageText: '第',//页数文本框前显示的汉字  
				        afterPageText: '页    共 {pages} 页',  
				        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
				 });
			})
	</script>
	<div data-options="region:'center',title:'盘点管理'" scrolling="no" style="overflow:auto;background-color:#F8F8FF;">
		<div style="height: 30px;background-color: rgb(248,248,255);text-align: right;">
					<form id="searchform" action="search" method="post">
					<%-- <span style="color:red;font-size:16px;">${message }</span> --%>
					设备号　<input class="easyui-textbox" style="width:120px;" type="text" id="equipemnt_no" name="equipemnt_no" value="">
					<!-- <input class="easyui-checkbox" style="width:120px;font-size: 16px;" type="text" id="equipment_state" name="equipment_state" value="${equipment_state}"> -->
					　柜台　
					<select id="counter_id" style="border: 1px solid #95b8e7;border-radius: 5px;height: 24px;width:100px;" name="counter_id">
								<option value="">全部</option>
								<c:if test="${listeounter!=null }">
									<c:forEach items="${listeounter}"  var="counterlist">
										<option value="${counterlist.id }">${counterlist.name }</option>
									</c:forEach>
								</c:if>
					</select>
					　盘点人　
					  <input class="easyui-textbox" style="width:120px;" type="text" id="username" name="username" value="">
					 　盘点时间
					  <input id="inventory_time_from" name="inventory_time_from" style="border: 1px solid #95b8e7;border-radius: 5px;width: 100px;" value="" class="Wdate" type="text" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy-MM-dd'})"/>--<input id="inventory_time_to" name="inventory_time_to" style="border: 1px solid #95b8e7;border-radius: 5px;width: 100px;" value="" class="Wdate" type="text" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy-MM-dd'})"/>
				    <a onclick="searchs();" class="easyui-linkbutton" iconCls="icon-search" style="font-size:16px;height: 25px;width: 60px;" >查询</a>
					</form>
					</div>
		<textarea id = "eqptlistjson" style="display: none;">${eqptlistjson}</textarea>
		<table id="dg" style="height: 95%;background-color:#F8F8FF" class="easyui-datagrid"  
				data-options="rownumbers:true,pagination:true,singleSelect:true,method:'post',remoteSort:false,multiSort:true,fitColumns:true ">
				<thead>
				<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'inventory_user',width:120,align:'center'">盘点人</th>
				<th data-options="field:'inventory_time',width:120,align:'center'">盘点日期</th>
				<th data-options="field:'modify_time',width:120,align:'center'">盘点时间</th>
				<th data-options="field:'remark',width:200,align:'center'">备注</th>
				</tr>
				</thead>
		</table>
		<div id="inportdiv" modal="true" class="easyui-dialog" style="width:400px;height:200px;overflow:hidden;" closed="true" title=" ">
        	 <div class="file-box" style="text-align: center;"><!--  -->
				<form id="uploadform"  action="inventoryInport" method="post" enctype="multipart/form-data">
				<input type='text' name='textfield' id='textfield' class='txt' />
				<input type='button' class='btn' value='浏览...' />
				<input type="file" name="inventoryfile" class="file" id="fileField" size="28" onchange="document.getElementById('textfield').value=this.value" />
				<input type="submit" name="submit" class="btn" value="上传" />
				</form>
			 </div> 
   		 </div>
	</div>
	
	<script type="text/javascript">
		function searchs(){
			var inventory_time_from = $("#inventory_time_from").val();
			var inventory_time_to = $("#inventory_time_to").val();
			var username = $("#username").val();
			var counter_id = $("#counter_id").val();
			var equipemnt_no = $("#equipemnt_no").val();
			$("#dg").datagrid({
				url:"getinventorylist",
				queryParams:{
					inventory_time_from:inventory_time_from,inventory_time_to:inventory_time_to,username:username,counter_id:counter_id,equipemnt_no:equipemnt_no,
				}
			});
			 var pager = $('#dg').datagrid().datagrid('getPager'); // get the pager of datagrid
			 $(pager).pagination({  
				 	beforePageText: '第',//页数文本框前显示的汉字  
			        afterPageText: '页    共 {pages} 页',  
			        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
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
</body>
</html>