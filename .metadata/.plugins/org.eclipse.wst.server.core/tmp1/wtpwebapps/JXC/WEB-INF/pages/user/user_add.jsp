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
		table td{
			font-size: 16px;
		}
	</style>
</head>
<body class="easyui-layout">
	<div data-options="region:'center',title:'新增用户'" scrolling="no" style="overflow:hidden;background-color:#F8F8FF;">
			<form id="useraddform">
				<table cellpadding="8" align="center" width="100%" style="margin-left: 2%;margin-top: 2%;">
					<tr>
						<td width="10%">用户名<span style="color: red;">*</span></td>
						<td width="15%">
							<input id="username" name="username" onblur="queryIsUserNameExist(this.value)" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;"  type="text"  value=""></input>
						</td>
						<td width="10%">密码<span style="color: red;">*</span></td><td width="15%"><input id="password" name="password" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;" type="text"  value=""></input></td>
						<td width="10%">确认密码<span style="color: red;">*</span></td><td width="15%"><input id="repassword" name="repassword" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;"  type="text"  value=""></input></td>
						<td width="10%">真实姓名</td><td width="15%"><input id="name" name="name" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;"  type="text"  value=""></input></td>
					</tr>
					<tr>
						<td>性别</td>
						<td>
							<!-- <input id="sex" name="sex" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;" type="text"  value=""></input> -->
							<select id="sex" name="sex" style="border: 1px solid #95b8e7;border-radius: 5px;height: 24px;width:140px;">
									<option value="0">男</option>
									<option value="1">女</option>
							</select>	
						</td>
						<td>柜台</td>
						<td>
							<select id="counterid" name="counterid" style="border: 1px solid #95b8e7;border-radius: 5px;height: 24px;width:140px;" class="easyui-combobox" data-options="multiple:true,multiline:true">
								<c:if test="${counterlist!=null }">
									<c:forEach items="${counterlist }" var="counterlists">
										<option value="${counterlists.counter_code }">${counterlists.name }</option>
									</c:forEach>
								</c:if>
							</select>
						</td>
						<td>身份证号</td><td><input id="idnumber" name="idnumber" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;" type="text"  value=""></input></td>
						<td>出生日期</td><td><input id="birth" name="birth" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;" class="Wdate" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy/MM/dd'})" type="text"></input></td>
					</tr>
					<tr>
						<td>联系人姓名</td><td><input id="contactsname" name="contactsname" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;"  type="text"  value=""></input></td>
						<td>联系人电话</td><td><input id="contactstel" name="contactstel" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;" type="text"  value=""></input></td>
						<td>入职日期</td><td><input id="entrydate" name="entrydate" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;" class="Wdate" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy/MM/dd'})" type="text"></input></td>
						<td>联系地址</td><td><input id="address" name="address" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;"  type="text"  value=""></input></td>
					</tr>
					<tr>
						<td>用户级别</td>
						<td>
							<!-- <input id="gradetype" name="gradetype" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;" type="text"  value=""></input> -->
							<select name="gradetype" style="border: 1px solid #95b8e7;border-radius: 5px;height: 24px;width:140px;">
								<option value="0001">普通员工</option>
								<option value="0002">柜台主管</option>
								<option value="0000">管理员</option>
							</select>
						</td>
						<td>日期</td><td><input id="createtime" name="createtime" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;background-color:#B0C4DE;"  type="text"  value=""></input></td>
						<td>操作员</td><td><input id="createuser" name="createuser" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;background-color:#B0C4DE;"  type="text"  value="${realname }"></input></td>
					</tr>
					<tr>
						<td>备注</td>
							<!-- <input id="remark" name="remark" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;" type="text"  value=""></input> -->
						<td colspan="7">
							<textarea  style="border: 1px solid #95b8e7;border-radius: 5px;height: 100px;width:96%;resize:none;overflow-Y:scroll;font-size: 18px;" resize="none"  name="remark"></textarea>
						</td>
					</tr>
				</table><!-- type="submit" -->
			</form>
			<!-- <button style="float: right;" onclick="useraddsubmit()">提交</button> -->
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="useraddsubmit()" style="width: 56px;float: right;margin-right: 20px;">提交</a>
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
		function useraddsubmit(){
 			
			var username = $("#username").val();
			var password = $("#password").val();
			var repassword = $("#repassword").val();
			//alert(username+":"+password+":"+repassword);
			if(username=="" || password=="" || repassword==""){
				alert("信息填写不完整!请检查!");
				return false;
			}
			if(password!=repassword){
				alert("密码输入不一致,请重新输入!");
				return false;
			}
			//alert("onk");{username:username,password:password,repassword:repassword},
		
			jQuery.ajax({
				url:"add",
				type:"post",
				data:$("#useraddform").serialize(),
				error:function(){},
				success:function(data){
					if(data==1){
						alert("操作成功!");
						window.location.reload(true);
					}					
				}
			});
		}
		
		function queryIsUserNameExist(username){
			jQuery.ajax({
				url:"queryIsUserNameExist",
				type:"post",
				data:{username:username},
				error:function(){},
				success:function(data){
					if(data=="0"){
						alert("用户名已存在!");
						return false;
					}
				}
			});
		}
		
		$(function(){
			$("#createtime").val(new Date().pattern("yyyy/MM/dd HH:mm:ss"));
		});
	</script>
	
</body>
 
</html>