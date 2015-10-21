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
	</script>
	<div data-options="region:'center'" scrolling="no" style="overflow:auto;background-color:#F8F8FF;">
		<div style="background-color:#F8F8FF;align:center;width: 100%;height: 40px;overflow: hidden;">
			<div align="right" style="">
				<a onclick="instorage();" class="easyui-linkbutton" style="margin-right: 5px;width: 80px;height: 25px;">导入</a>
			</div>
		</div>
		
		<textarea id = "eqptlistjson" style="display: none;">${eqptlistjson}</textarea>
		<table id="dg" style="height: 90%;background-color:#F8F8FF" class="easyui-datagrid"  
				data-options="rownumbers:true,pagination:false,singleSelect:true,method:'post',remoteSort:false,multiSort:true,fitColumns:true ">
				<thead>
				<tr>
				<!-- <th data-options="field:'ck',checkbox:true"></th> -->
				<th data-options="field:'no',width:120,align:'center'">设备编号</th>
				<th data-options="field:'d_country',width:120,align:'center'">国家</th>
				<th data-options="field:'equipment_state',width:80,align:'center'">设备状态</th>
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
	<!-- 处理租用日期 返还日期 -->
	<script type="text/javascript">
		//设置租用日期的初始时间为当前时间
		$(function(){
			$("#create_time").val(new Date().pattern("yyyy/MM/dd"));
			$("#inventory_time").val(new Date().pattern("yyyy/MM/dd hh:mm:ss"));
			
			var data = $("#eqptlistjson").val();
			//alert(data);
			if(data!=""){
				data = $.parseJSON(data);
				$("#dg").datagrid("loadData",data);
			}
		});
		function instorage(){
			$("#inportdiv").dialog("open");
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

<script>
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
        
</script>
</body>
</html>