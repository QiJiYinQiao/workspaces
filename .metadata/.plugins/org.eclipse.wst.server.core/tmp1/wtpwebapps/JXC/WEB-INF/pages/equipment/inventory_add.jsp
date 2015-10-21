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
			/* $(function(){
				var left = document.getElementById("sim_id").getBoundingClientRect().left;
				var top = document.getElementById("sim_id").getBoundingClientRect().top;
				var width = document.getElementById("sim_id").offsetWidth;
				
				document.getElementById("search_img").style.left = left+width-25+"px";
				document.getElementById("search_img").style.top = top+2+"px";
			});  */
	</script>
	<div data-options="region:'center',title:'新增盘点'" scrolling="no" style="overflow:hidden;background-color:#F8F8FF;">
		<iframe id="inventory_adds" name="inventory_adds" src="inventory_adds" width="100%" height="28%" frameborder="0" style="overflow:hide;"></iframe>
		<iframe id="inventory_list" name="inventory_list" src="inventory_list" width="100%" height="67%" frameborder="0" style="overflow:hide;"></iframe>
		<div style="text-align:right;padding-right:10px;width:100%;height: 5%">
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style="width: 56px;">提交</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()" style="width: 56px;">清空</a>
		</div>
	</div>
	<!-- 处理租用日期 返还日期 -->
	<script type="text/javascript">
		//设置租用日期的初始时间为当前时间
		$(function(){
			$("#create_time").val(new Date().pattern("yyyy/MM/dd"));
			$("#inventory_time").val(new Date().pattern("yyyy/MM/dd hh:mm:ss"));
			
			/* document.getElementById("inventory_add").src="inventory_add";
			document.getElementById("inventory_list").src="inventory_list"; */
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
		var rows = frames['inventory_list'].$("#dg").datagrid("getRows");
		/* alert(rows==null);
		alert(rows==""); */
		if(rows==""){
			alert("请先导入设备!")
			return false;
		}else{
			var epqtidlist = "";
			for(var i = 0 ; i < rows.length;i++){
				//alert(rows[i].id);
				epqtidlist += rows[i].id+",";
			}
			var inventorydate = frames['inventory_adds'].document.getElementById("create_time").value;
			var inventorytime = frames['inventory_adds'].document.getElementById("inventory_time").value;
			var inventoryname = frames['inventory_adds'].document.getElementById("inventory_user").value;
			var inventoryremark = frames['inventory_adds'].document.getElementById("remark").value;
			//alert(inventorydate+":"+inventorydate+":"+inventoryname+":"+inventoryremark);
			//alert(list);
			jQuery.ajax({
				url:"inventory_add_all",
				type:"post",
				data:{epqtidlist:epqtidlist,inventorydate:inventorydate,inventorytime:inventorytime,inventoryname:inventoryname,inventoryremark:inventoryremark},
				error:function(){},
				success:function(data){
					alert("盘点成功");
					window.location.reload();
				}
			});
		} 
	}
	
	function clearForm(){
		frames['inventory_adds'].document.getElementById("remark").value = "";
		frames['inventory_list'].$("#dg").datagrid("reload");
	}
        
	$("#search_img").click(function(){
		//alert("search_img.click");
		$("#infolist").dialog("open");
	})
</script>
</body>
</html>