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
				<form id="editform" action="editOfInfo" method="post">
					<input name="id" style="display: none;" value="${info.id }">
					<table cellpadding="12" align="center" width="100%">
					<!-- 一行四条信息 -->
					<tr>
						<td width="10%">运营商</td>
						<td width="15%"><input type="text" readonly="readonly" name="operators"  value="${info.operators }"></input></td>
						
						<td width="10%">国家</td>
						<td width="15%"><input readonly="readonly"  type="text" name="country" value="${info.country }" ></input></td>
						
						<td width="10%">日租金 </td>
						<td>
						<input width="15%"  type="text" readonly="readonly"  name="day_rent" value="${info.day_rent }"></input>
						</td>
						
						<%-- <td width="10%">是否有效</td>
						<td width="15%">
						<input type="text" readonly="readonly"  name="is_valid" value="${info.is_valid }"></input>
						</td> --%>
						<td width="10%">是否有效 </td>
						<td width="15%">
						<select name="is_valid" style="border: 1px solid #95b8e7;border-radius: 5px;height: 24px;width:130px;" >
							<option value="${is_valid }" selected="selected">${info.is_valid }</option>
							<option value="0001">有效</option>
							<option value="0002">无效</option>
						</select>
						</td>
					</tr>
					
					<tr>
						<td>开卡时间</td>
						<td>
						<input width="15%"  readonly="readonly" type="text" name="day_begin" value="${info.day_begin }"></input>
						</td>
						
						<td>卡到期时间 </td>
						<td>
						<input  type="text" name="day_end" style="background-color: white;" value="${info.day_end }" class="Wdate" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy/MM/dd HH:mm:ss'})"></input>
						</td>
						
						<td>操作员</td>
						<td>
						<input type="text" readonly="readonly"  name="modify_user" value="${realname }" />
						<td>时间</td>
						<td>
						<input id="time" type="text" readonly="readonly"  name="modify_time" value="" />
						</td>
					</tr>
					
					<tr>
						<td >备注 </td>
						<td colspan="7">
						<textarea name="remark" style="background-color:white;border: 1px solid #95b8e7;border-radius: 5px;height: 80px;width:98%;resize:none;font-size: 18px;color: black;" resize="none"  id="remark" >${info.remark }</textarea>
						</td>
					</tr>
					</table>	
						<button style="float: right;margin-right: 10px;width: 60px;height:30px;" onclick="quedingOfInfo();">确定</button>
				</form>
			</div>
			<script type="text/javascript">
				function quedingOfInfo(){
					$("#editform").submit();
					//alert("queding");
				    /* jQuery.ajax({
						url:"editOfInfo",
						data:$("#editform").serialize(),
						type:"post",
						error:function(){alert("error");return false;},
						success:function(data){
							alert(data);
						}
					}); try absolute path*/
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
				    
				 $(function(){
						$("#time").val(new Date().pattern("yyyy/MM/dd hh:mm:ss"));
					});
</script>    
	 	
</body>
</html>