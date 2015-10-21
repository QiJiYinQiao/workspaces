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
			    /* left: 365px; */
			    position: absolute;
			    /* top: 180px;  */
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
		$(function(){
			var left = document.getElementById("euipmentno").getBoundingClientRect().left;
			var top = document.getElementById("euipmentno").getBoundingClientRect().top;
			var width = document.getElementById("euipmentno").offsetWidth;
			
			document.getElementById("search_img").style.left = left+width-25+"px";
			document.getElementById("search_img").style.top = top+2+"px";
		}); 
	</script>
	<div data-options="region:'center',title:'添加数据卡'" scrolling="no" style="overflow:hidden;background-color:#F8F8FF;">
		<!-- 表单开始 -->
		<form id="infoadd" >
		<table cellpadding="12" align="center" width="100%" style="margin-left: 2%;margin-top: 2%;">
		<!-- 一行四条信息 -->
		
		<tr>
			<td width="10%">数据卡编号<span style="color: red;">*</span> </td>		
			<td width="17%">
				<input id="id" onblur="checkIsExist(this);" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;" type="text" name="id"  value="" ></input>
			</td>
			
			<td  width="10%">国家<span style="color: red;">*</span>
			</td>
			<td width="17%">
				<select id="country" onchange="counterychange();" style="border: 1px solid #95b8e7;border-radius: 5px;height: 24px;width:140px;background-color:#B0C4DE;" name="country">
					<option value="">请选择</option>
					<c:if test="${country!=null }">
						<c:forEach items="${country }" var="countrys">
							<option value="${countrys.ITEM_VALUE }">${countrys.ITEM_TEXT }</option>
						</c:forEach>
					</c:if>
				</select>
				 <span id="countryspan" style="color: red;display: none;">请选择国家!</span> 
			</td>
			
			<td  width="10%">运营商
			</td>
			<td width="17%">
				<select id="operators" style="border: 1px solid #95b8e7;border-radius: 5px;height: 24px;width:140px;background-color:#B0C4DE;" name="operators">
					<option  value="">请选择</option>
					<%-- <c:if test="${operatorlists!=null }">
						<c:forEach items="${operatorlists }" var="operators">
							<option value="${operators.ITEM_VALUE }">${operators.ITEM_TEXT }</option>
						</c:forEach>
					</c:if> --%>
				</select>
			</td>
		</tr>
		
		<tr>
			<td>日租金</td>		
			<td><input style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;background-color:#B0C4DE;" readonly="readonly" type="text" name="day_rent" id="day_rent" value="" ></input></td>
			
			<td>开卡时间
			</td>
			<td><div class="container">
			<input id="day_begin" width="15%"  type="text" name="day_begin" value=""  class="Wdate" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy/MM/dd HH:mm:ss',maxDate:'#F{$dp.$D(\'day_end\',{d:-1})}'})"></input>
			</td>
			
			<td >卡到期时间
			</td>
			<td >
			<input id="day_end" width="15%"  type="text" name="day_end" value=""  class="Wdate" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy/MM/dd HH:mm:ss',minDate:'#F{$dp.$D(\'day_begin\',{d:+1})}'})"></input>
			</td>
		</tr>
		
		<tr>
			<td>设备</td>
			<td><input style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;background-color:#B0C4DE;" readonly="readonly" type="text" name="euipmentno" id="euipmentno" value="" ></input>
				<div id="search_img" class="search"></div>
			</td>
			<td>日期</td>
			<td><input id="create_time" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;background-color: #B0C4DE;" readonly="readonly" type="text" name="create_time"  value="" ></input></td>
			<td>操作人</td>
			<td><input style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;background-color: #B0C4DE;" readonly="readonly" type="text" name="create_user"  value="${realname }" ></input></td>
		</tr>
		<tr>
			<td >备注 </td>
			<td colspan="7">
			<textarea id="remark"  style="border: 1px solid #95b8e7;border-radius: 5px;height: 100px;width:90%;resize:none;overflow-Y:scroll;font-size: 18px;" resize="none"  name="remark"></textarea>
			</td>
		</tr>
		</table>
		<!-- SIM卡是否存在标识  1标识已存在-->
		<input type="text" style="display: none;" name="isexist" value="" id="isexist">
		</form>
		
		<div style="text-align:right;padding-right:10px;width:100%;">
		<div style="width:94%"><!-- onclick="submitForm()" -->
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style="width: 56px;">提交</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()" style="width: 56px;">清空</a>
		</div>
		
		<!-- 记录第一次提交 -->
		<input id="forbid" value="2" style="display:none;">
		
		</div>
		<!-- 表单结束 -->
		<div id="eqptlist"  class="easyui-dialog" modal="true" title="设备详情"  closed="true" style="width:1000px;height:600px;overflow:hidden;left:120px; top:40px;">
			 <iframe id="unbindequpt" name="contents" src="" width="100%" height="100%" frameborder="0" style="overflow: auto;"></iframe> 
		</div>
		
	</div>
	<!-- 处理租用日期 返还日期 -->
	<script type="text/javascript">
		//设置日期的初始时间为当前时间
		$(function(){
			$("#create_time").val(new Date().pattern("yyyy/MM/dd hh:mm:ss"));
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

<script>
	function clearForm(){
		$("#id").val("");
		$("#operator").val("");
		$("#day_begin").val("");
		$("#day_end").val("");
		$("#remark").val("");
	}
	
	function submitForm(){
		if($("#forbid").val()==1){
			alert("请勿重复提交!");
			return false;
		}
		//$("#info").submit();	
		var country = $("#country").val();
		var isexist = $("#isexist").val();
		if(isexist==1){
			alert("此数据卡已存在,请重新输入!");
			return false;
		}
		if($("#id").val()==''){
			alert("数据卡卡号不能为空 ！");
			//$.messager.alert("","数据卡卡号不能为空 ！");
			return false;
		}
		if(country==''){
			$("#countryspan").css("display","");
			return false;
		}
		$("#forbid").val("1");
		jQuery.ajax({
			url:"addinfo",
			data:$("#infoadd").serialize(),
			type:"post",
			error:function(){
				alert("添加失败");
			},
			success:function(data){
				if(data=="1"){
					alert("添加成功");
					window.location.reload(true);
				}else{
					alert("添加失败");
				}				
			}
		});
	}
	/* $(function(){
		document.onkeydown=function(event){
            var e = event || window.event || arguments.callee.caller.arguments[0];
            if(e && e.keyCode==27){ // 按 Esc 
                //要做的事情
              }
            if(e && e.keyCode==113){ // 按 F2 
                 //要做的事情
               }            
             if(e && e.keyCode==13){ // enter 键
            	 submitForm();
            }
        }; 
		
	}); */
    
	$("#search_img").click(function(){
		document.getElementById("unbindequpt").src = "queryUnbindEqpt";
		$("#eqptlist").dialog("open");
	});
	
	function counterychange(){
		var country = $("#country").val();
		if(country!=''){
			$("#countryspan").css("display","none");
		}
		//alert(country);
		jQuery.ajax({
			url:"queryOperatorByC",
			data:{country:country},
			type:"post",
			error:function(){
				alert("查询失败!");
			},
			success:function(data){
				data = $.parseJSON(data);
				$("#operators").empty();
				//alert(data.dayrent);
				$("#day_rent").val(data.dayrent);
				//var operators = document.getElementById("operators");
				$.each(data.list,function(item,value){
					//alert(value.ITEM_TEXT);
					var options = "<option value = '"+value.ITEM_VALUE+"'>"+value.ITEM_TEXT+"</option>";
					//alert(options);
					$("#operators").append(options);
				});
			}
		}); 
	}
	
	function checkIsExist(id){
		var id = id.value;
		jQuery.ajax({
			url:"checkIsExist",
			data:{id:id},
			type:"post",
			error:function(){
				alert("ERROR!");
			},
			success:function(data){
				if(data=="1"){
					alert("此SIM卡已经存在,请重新输入!");
					$("#isexist").val("1");
				}else{
					$("#isexist").val("");
				}
			}
		});
	}
	
</script>
</body>
</html>