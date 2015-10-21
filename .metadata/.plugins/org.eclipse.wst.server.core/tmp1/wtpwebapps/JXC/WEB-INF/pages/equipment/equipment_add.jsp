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
			    left: 120px;
			    position: absolute;
			    top: 4px;
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
				var left = document.getElementById("sim_id").getBoundingClientRect().left;
				var top = document.getElementById("sim_id").getBoundingClientRect().top;
				var width = document.getElementById("sim_id").offsetWidth;
				
				document.getElementById("search_img").style.left = left+width-25+"px";
				document.getElementById("search_img").style.top = top+2+"px";
			}); 
	</script>
	<div data-options="region:'center',title:'添加设备'" scrolling="no" style="overflow:hidden;background-color:#F8F8FF;">
		<!-- 表单开始 action="add" method="POST"-->
		<form id="equipment"  >
		<input id="existno" style="display:none;">
		<table cellpadding="12" align="center" width="100%" style="margin-left: 2%;margin-top: 2%;">
		<!-- 一行四条信息 -->
		
		<tr>
		<td width="10%">设备号<span style="color: red;">*</span> </td>		
		<td width="17%"><input id="no" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;" type="text" name="no"  value="" ></input></td>
	
		<!-- <td width="10%">3G卡号<span style="color: red;">*</span></td>
		<td width="17%"><input id="sim_id" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;" type="text" name="sim_id" ></input></td> -->
		<td  width="10%">进货日期
		</td>
		<td width="17%"><div class="container">
		<input id="stock_date" width="15%"  type="text" name="stock_date" value="${stock_date }"  class="Wdate" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy/MM/dd HH:mm:ss'})"></input>
		</td>
		
		<td width="10%">柜台<span id="counterid" style="color: red;">*</span></td>
		<td width="17%">
			<select id="counterid" name="counterid"  style="border: 1px solid #95b8e7;border-radius: 5px;height: 25px;width: 150px;" >
				<c:if test="${counterlist!=null }">
					<c:forEach items="${counterlist }" var="counterlists">
						<option value="${counterlists.counter_code }">${counterlists.name }</option>
					</c:forEach>
				</c:if>
			</select>　
		</td>
		</tr>
		
		<!-- <td>报废日期<span style="color: red;">*</span></td>
		<td>
		<input id="scrap_date" readonly="readonly" style="border: 1px solid #95b8e7;border-radius: 5px;" class="Wdate" type="text" name="scrap_date" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy/MM/dd HH:mm:ss',minDate:'#F{$dp.$D(\'txtDate\',{d:+4})}',onpicking:diffDate()})"/>
		</td> -->
		<!-- <td>是否有效<span style="color: red;">*</span></td>
		<td>
		<input id="is_valid" readonly="readonly" style="border: 1px solid #95b8e7;border-radius: 5px;"  type="text" name="is_valid" />
		</td> -->

		<tr>
			<td >SIM卡</td>
			<td>
			<input id="sim_id" type="text" name="sim_id" value="" ></input>
			<div id="search_img" class="search"></div>
			</td>
			
			<td>日期</td>
			<td>
			<input style="border: 1px solid #95b8e7;border-radius: 5px;background-color:#B0C4DE;" type="text" id="create_time" name="create_time"   value=""></input>
			</td>
			
			<td >操作员</td>
			<td>
			<input id="create_user" type="text" name="create_user" value="${realname }" style="background-color:#B0C4DE;"></input>
			</td>
		</tr>
		
		<tr>
			<td >备注 </td>
			<td colspan="7">
			<textarea id="remark"  style="border: 1px solid #95b8e7;border-radius: 5px;height: 100px;width:90%;resize:none;overflow-Y:scroll;font-size: 18px;" resize="none"  name="remark"></textarea>
			</td>
		</tr>
		</table>
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
		<div id="infolist"  class="easyui-dialog" modal="true" title="SIM卡详情"  closed="true" style="width:1000px;height:600px;overflow:hidden;left:120px; top:40px;">
			 <iframe id="simpicklist" name="simpicklist" src="simpicklist" width="100%" height="100%" frameborder="0" style="overflow: hidden;"></iframe> 
		</div>
	</div>
	<!-- 处理租用日期 返还日期 -->
	<script type="text/javascript">
		//设置租用日期的初始时间为当前时间
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
		$("#no").val("");
		//$("#stock_date").val("");
		$("#remark").val("");
		$("#counterid").val("");
		
	}
	
	$("#no").blur(function(){
		//alert("equipmentno");
		var no = $("#no").val();
		jQuery.ajax({
			url:"ifSelfEqptExist",
			data:{no:no},
			type:"post",
			error:function(){},
			success:function(data){
				if(data=="1"){
					//alert("设备已存在!");
					$.messager.alert("提示","设备已存在!");
					$("#existno").val("1");
					return false;
				}else{
					$("#existno").val("0");
				}
			}
		})
	});
	
	function submitForm(){
		if($("#forbid").val()==1){
			alert("请勿重复提交!");
			return false;
		}
		var no = $("#no").val();
		var countercode = $("#counterid").val();
		if(no==""){
			alert("请输入设备号");
			return false;
		}
		var existno = $("#existno").val();
		if(existno==1){
			$.messager.alert("提示","此设备已存在!");
			return false;
		}
		//$("#equipment").submit();		
		$("#forbid").val("1");
		jQuery.ajax({
			url:"add",
			data:$("#equipment").serialize(),
			type:"post",
			error:function(){
				
			},
			success:function(data){
				if(data=='1'){
					alert("添加成功");
					$("#no").val("");
					$("#remark").val("");
					$("#forbid").val("2");
				}
			}
		});
	}
	$(function(){
		$("#start").val(new Date().pattern("yyyy/MM/dd hh:mm:ss"));
		
		/* document.onkeydown=function(event){
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
        };  */
		
	});
        
	$("#search_img").click(function(){
		//alert("search_img.click");
		$("#infolist").dialog("open");
	})
</script>
</body>
</html>