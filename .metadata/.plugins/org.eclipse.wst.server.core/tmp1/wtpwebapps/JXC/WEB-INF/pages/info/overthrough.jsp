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
	<script type="text/javascript" src="../media/js/WdatePicker.js"></script>
	<script type="text/javascript" src="../media/js/jquery.min.js"></script>
	<script type="text/javascript" src="../media/js/jquery.easyui.min.js"></script>
	<style type="text/css">
		td{
			font-size: 16px;
		}
		input{
			font-size: 16px;
			text-align: center;
		}
	</style>
</head>
<body class="easyui-layout" >
	<script type="text/javascript">
		$(function(){
			$("#createtime").val(new Date().pattern("yyyy/MM/dd hh:mm:ss"));
			var simnotype = $("#simnotype").val();
			if(simnotype == 1){
				$("#kaika").dialog("open");
			}
		});
	</script>
	<input type="text" style="display:none;" id="status" value="${status }">
	 <div id="simnolist"  class="easyui-dialog" modal="true" title="数据卡开卡"  closed="true" style="width:630px;height:300px;overflow:hidden;left:120px; top:40px;background-color: #F8F8FF;">
			<form id="simopen" action="simopen" method="post">
				<table>
					<tr>
						<td width="10%">开卡时间: </td>
						<td>
							<input  width="15%"  type="text" name="day_begin" value="" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;"  class="Wdate" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy/MM/dd HH:mm:ss'})"></input>
				 		</td>
						<td width="10%">卡到期时间: </td>
						<td>
							<input  width="15%"  type="text" name="day_end" value="${dayend }" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;" class="Wdate" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy/MM/dd HH:mm:ss'})"></input>
				 		</td>
				 		<td width="10%">操作人: </td>
						<td>
							<input  width="15%"  type="text" name="modify_user" style="background-color:#B0C4DE;border: 1px solid #95b8e7;border-radius: 5px;height: 20px;" value="${realname }" readonly="readonly" ></input>
				 		</td>
					</tr>
					<tr>
						<td width="10%">操作时间: </td>
						<td>
							<input id="createtime" width="15%"  type="text" name="modify_time" value="" style="background-color:#B0C4DE;border: 1px solid #95b8e7;border-radius: 5px;height: 20px;"  readonly="readonly"></input>
				 		</td>
					</tr>
					<tr>
						<td width="10%">备注 </td>
						<td colspan="5">
						<textarea id="remark"  style="border: 1px solid #95b8e7;border-radius: 5px;height: 100px;width:100%;resize:none;overflow-Y:scroll;font-size: 18px;" resize="none"  name="remark"></textarea>
						</td>
					</tr>
				</table>
				<div style="text-align:right;padding-right:10px;width:100%;">
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style="width: 56px;">提交</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</div>
			</form>
	</div>
	<input id="simnotype" value="${simnotype }" style="display:none;">
	<div id="kaika"  class="easyui-dialog" modal="true" title="数据卡导入" closed="true"  style="width:350px;height:600px;overflow:hidden;top:40px;background-color: #F8F8FF;overflow: auto;">
			<div style="height: 40px;">
				<span style="float:left;font-size: 16px; ">本次共导入${simnosnum }条数据</span>
				<!-- <button onclick="kaikaform()" style="float: right;height: 30px;width:80px;">确认导入</button>
				<button onclick="kaikacancel()" style="float: right;height: 30px;width:80px;">取消</button> -->
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="kaikaform()" style="width: 70px;float: right;">确认导入</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="kaikacancel()" style="width: 70px;float: right;">取消</a>&nbsp;&nbsp;
			</div>
			<div>
				<form id="kaikaform" action="kaika" method="post" style="font-size: 16px;text-align: center;">
				   <table>
						<tr><td width="80px;">序号</td><td width="320px;">卡号</td></tr>
						<c:if test="${simnos!=null }">
							<c:forEach items="${simnos }" var="simno" varStatus="status">
								<tr><td>${status.index+1 }</td><td><input name="simno" value="${simno }" readonly="readonly" style="background-color: #F8F8FF;border: 0px solid #95b8e7;border-radius: 5px;height: 20px;"></td></tr>
							</c:forEach>
						</c:if>
					</table>
				</form>
			</div>
		</div>
	
	<script>
        window.onload = function() {
        	var status = $("#status").val();
            if (status == "1") {
            alert("修改成功!");
            $("#editdiv").dialog("close");
            parent.window.location = "list";
        } else if (status == "2") {
        	alert("充值成功!");
        	$("#chongzhidiv").dialog("close");
        	window.location = "list";
        }else if(status == "-1"){
        	alert("导入失败!")
        	window.location = "list";
        }else if(status == "3"){
        	alert("导入失败，表中有重复数据!")
        	window.location = "list";
        }else if(status=="0"){
        	if(confirm("导入成功，是否开卡？")){
        		$("#simnolist").dialog("open");
        	}else{
        		window.location = "list";
        	}
        }else if(status=="4"){
        	alert("开卡成功!");
        	window.location="list";
        }
       
}
        
        function submitForm(){
        	$("#simopen").submit();
        }
        
        function kaikaform(){
			$("#kaikaform").submit();
		}
        
        function kaikacancel(){
			$("#kaika").dialog("close");
			window.location = "list";
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