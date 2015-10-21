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
		#special input{
			color:red;
		}
		td{
			font-size: 16px;
			padding:5px;margin:5px;
		}
		table{border-collapse:collapse;border-spacing:0;}
	</style>
</head>
<body class="easyui-layout" >
	<div data-options="region:'center',title:'设备退还'" scrolling="no" style="overflow:hidden;background-color:#F8F8FF;">
	<!-- <div data-options="region:'east',split:true,collapsed:true,title:'通知'" style="width:100px;padding:10px;">通知</div>
	<div data-options="region:'south',border:false" style="height:5px;background:#A9FACD;padding:10px;"></div>
			<div style="margin:20px 0;"></div>
			<div id="tb" style="padding:5px;height:auto"> -->
			<!-- <div style="margin-bottom:5px">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"></a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true"></a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true"></a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cut" plain="true"></a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a>
			</div> -->
			<div align="right;height:26px;" style="border-bottom-style:1px solid black;">
			<form id="searchform" action="search" method="post" >
			<div style="text-align: right;margin-right: 4%;font-size: 16px;">
				<span id="fourday" style="color: red;font-size: 16px;float: left;">*如实际出行天数不满五天，租金按五天计算!</span>
				<span style="color:red;font-size:16px;">${message }</span>
				设备号： <input class="easyui-textbox" style="width:140px" type="text" name="equipment_no" value="${equn }">
				手机号：<input class="easyui-textbox" style="width:120px;font-size: 16px;" type="text" name="rent_p_tel" value="${rent}">
				<a onclick="searcheqpt();" class="easyui-linkbutton" iconCls="icon-search" style="font-size:16px;" >查询</a>
			</div>
			</form>
			</div>
		<!-- 表单开始 -->
		<%-- <c:if test="${order!=null }"> --%>
		<form id="order_search" > 
		<input name="useid" value="${id }" style="display: none;">
		<input name="types" value="1" style="display:none;">
		 <!-- 起始优惠天数 -->
	    <input id="saleday" value="${saleday }" style="display:none;">
		<table cellpadding="12" align="center" width="100%" style="margin-left: 4%;">
		<tr>
			<td width="10%">订单号</td>
			<td width="17%">
			<input id="order_num" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;background-color:#B0C4DE;" type="text" readonly="readonly" name="order_num"   value="${order.order_num }"></input>
			</td>
			
			<td width="12%">姓名</td>
			<td width="17%"><input style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;background-color:#B0C4DE;" type="text" readonly="readonly" name="rent_p_name"   value="${order.rent_p_name }"></input></td>
			
			<td width="12%">手机号</td>
			<td  width="17%">
			<input style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;background-color:#B0C4DE;" type="text" readonly="readonly" name="rent_p_tel"   value="${order.rent_p_tel }"></input>
			</td>
		</tr>
		
		<tr>
			<td >设备号</td>
			<td>
			<input style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;background-color:#B0C4DE;" type="text" readonly="readonly" name="equipment_no"   value="${order.equipment_no }"></input>
			</td>
			
			<td >出发地</td>
			<td>
			<input style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;background-color:#B0C4DE;" type="text" readonly="readonly" name="s_country"   value="${order.s_country }"></input>
			</td>
			
			<td >目的地</td>
			<td>
			<input style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;background-color:#B0C4DE;" type="text" readonly="readonly" name="d_country"   value="${order.d_country }"></input>
			</td>
			
		</tr>
		
		<tr>
			<td>租用日期</td>
			<td>
			<input id="rent_date" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;background-color:#B0C4DE;" type="text" readonly="readonly"  name="rent_begindate" value="${rent_begindate }"></input>
			</td>
			
			<td>预计返还日期</td>
			<td id="special">
			<input id="rent_expectdate" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;background-color:#B0C4DE;" type="text" readonly="readonly" name="rent_expectdate"   value="${rent_expectdate }"></input>
			</td>
		
			<td>实际返还日期<span style="color: red;">*</span></td>
			<td><input id="real_return" readonly="readonly" style="border: 1px solid #95b8e7;border-radius: 5px;" class="Wdate" type="text" name="rent_enddate" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy/MM/dd HH:mm:ss',onpicking:onchoose(),minDate:'#F{$dp.$D(\'rent_date\',{d:+0})}'})"/></td>
			
		</tr>
		
		<tr>
			<td>预收押金</td>
			<td>
			<input id="cost_return" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;background-color:#B0C4DE;" type="text" readonly="readonly" name="cost_return"  value="${deposit }" ></input>
			</td>
			
			<td>预收总金额</td>
			<td>
			<input id="upfront_sum" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;background-color:#B0C4DE;" type="text" readonly="readonly" name="upfront_sum"   value="${order.upfront_sum }"></input>
			</td>
			
		
			<td>支付方式</td>
			<td><input style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;background-color:#B0C4DE;" type="text" readonly="readonly" name="payment"   value="${order.payment }"></input></td>
		
		</tr>
		<tr>
			<!-- 保存预计返还日期和实际返还日期的时间差实际返还-预计返还 -->
			<input id="date_diffrcpt" name="date_diffrcpt" style="display:none;" />
			<!-- 保存出租日期-预计返还日期 -->
			<input id="fdates" name="fdate" value="" style="display:none;" />
			<td>日租金</td>
			<td>
			<input id="rent_day" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;background-color:#B0C4DE;" readonly="readonly"  type="text" name="rent_day"  value="${order.rent_day }"></input>
			</td>
			
			<td>实际出行天数</td>
			<td>
			<input id="daycounts" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;background-color:#B0C4DE;" readonly="readonly" type="text" name="daycounts"  value=""></input>
			</td>
			<td>租赁费用(原价)</td>
			<td>
			<input id="3g_cost" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;background-color:#B0C4DE;text-decoration:line-through;" type="text" readonly="readonly" name="cost_rent_3g" value=""></input>
			<%-- <input id="3g_cost" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;background-color:#B0C4DE;text-decoration:line-through;" type="text" readonly="readonly" name="cost_rent_3g" value="${order.cost_rent_3g }"></input> --%>
			</td>
		</tr>
		
		<tr>
			<td>优惠金额<span style="color: red;">*</span></td>
			<td>
			<input id="cost_discount" onkeyup="discountkeyup(this.value);" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;"  name="cost_discount"  value="0"></input>
			<span id="cost_discount_span"></span>
			<input id="cost_discunt_type" value="" style="display: none;">
			</td>
			
			<td>退租金额</td>
			<td><!-- onkeyup="keyupreturn(this.value);" -->
			<input id="cost_return_discount" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;background-color:#B0C4DE;color: red;" readonly="readonly" type="text" name="cost_return_discount"  value="${deposit }"></input>
			</td>
			
			<td>实收金额</td>
			<td>
			<input id="cost_sum" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;background-color:#B0C4DE;" type="text" readonly="readonly" name="cost_sum"   value=""></input>
			</td>
		</tr>
		
		
		<tr>
			<td >舱位等级</td>
			<td ><input style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;background-color:#B0C4DE;" type="text" readonly="readonly" name="position_level"   value="${order.position_level }"></input></td>
			
			<td >身份证号</td>
			<td ><input style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;" type="text"  name="rent_p_idnumber"   value="${order.rent_p_idnumber }"></input></td>
			
			<td>护照号</td>
			<td><input style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;" type="text"  name="rent_p_passportno"   value="${order.rent_p_passportno }"></input></td>
			
		</tr>
		
		<tr>
			<td>国籍</td>
			<td>
			<input style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;background-color:#B0C4DE;" type="text" readonly="readonly" name="rent_p_nationality"   value="${order.rent_p_nationality }"></input>
			</td>
			<td>日期</td>
			<td>
			<input id="modify_time" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;background-color:#B0C4DE;" type="text" readonly="readonly" name="modify_time"  value=""></input>
			</td>
			
			<td>操作员</td>
			<td>
			<input style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;background-color:#B0C4DE;" type="text" readonly="readonly" name="modify_user"   value="${realname}"></input>
			</td>
			
			<!-- <td colspan="2"><span id="fourday" style="color: red;display: none;font-size: 16px;">*实际出行天数不满五天，租金按五天天计算!</span></td> -->
		</tr>
		
		<tr>
			<td>备注</td>
			<td colspan="7">
			<textarea  style="border: 1px solid #95b8e7;border-radius: 5px;height: 100px;width:95%;resize:none;overflow-Y:scroll;font-size: 18px;" resize="none"  name="remark">${order.remark }</textarea>
			</td>
		</tr>
		</table>
		<%-- </c:if> --%>
		</form>
		<!-- 表单结束 -->
		<div style="text-align:right;padding:5px;margin-right: 2%;">
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style="width: 50px;">提交</a>&nbsp;&nbsp;
		<!-- <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()" style="width: 50px;">清空</a> -->
		</div>
	</div>
	<script type="text/javascript">
	$(function(){
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
	</script>
	
	<script type="text/javascript">
		function submitForm(){
			if($("#order_num").val()==""){
				alert("请先选择设备!");
				return false;
			}
			if($("#rent_expectdate").val()==""){
				alert("此设备暂未租用!");
				return false;
			}
			if($("#cost_discunt_type").val()=="1"){
				alert("优惠金额填写有误!")
				return false;
			}
			
			//$('#order_search').submit();
			jQuery.ajax({
				url:"update_order",
				data:$("#order_search").serialize(),
				type:"post",
				error:function(){alert("操作失败!")},
				success:function(data){
					if(data=='1'){
						alert("操作成功!")
						top.document.getElementById("countents").src = "order/list?types=1";
					}else{
						alert("操作失败!")
					}
				}
			});
			onchoose();
			count_money();
		}
		function clearForm(){
		$('#order_search').form('clear');
		}
		//这里需要修改
		function searcheqpt(){
			$("#searchform").submit();
			$("#rent_date").val("0");
			var rentdate = $("#rent_date").val();
			
		}
		
		function Swith(strdate){
			strdate = strdate.replace(/-/g, "/");
			var d1;
		    if (strdate == "") {
		      d1 = new Date();
		    } else {
		      d1 = new Date(strdate);
		    }
		    return d1;
		}
		
		//====================计算两个日期间的时间差（天数）开始===============================
		function onchoose(){
			var d1 = $("#real_return").val();
			var d2 = $("#rent_date").val();
			var d3 = $("#rent_expectdate").val();
			
			var date1=null;
			var date2=new Date();
			var daycounts = ((Date.parse(Swith(d1))-Date.parse(Swith(d2))) / 86400000);/* 日期默认加一，减去优惠天数 */
			//实际返还-租用日期天数
			daycounts = Math.round(Math.abs(daycounts)) + 1;
			if(d2==""){
				$("#daycounts").val('0');
			}else{
				$("#daycounts").val(daycounts);
			}
			
			//实际返还-预计返还天数
			var date_diffrcpt = ((Date.parse(Swith(d1))-Date.parse(Swith(d3))) / 86400000);
			date_diffrcpt = Math.round(date_diffrcpt);
			if(d3==""){
				$("#date_diffrcpt").val("0");
			}else{
				$("#date_diffrcpt").val(date_diffrcpt);
			}
			
			//租用日期-预计返还日期
			var fdates = ((Date.parse(Swith(d2))-Date.parse(Swith(d3))) / 86400000);
			//alert("fdates="+fdates);
			fdates = Math.round(fdates);
			$("#fdates").val(fdates);
			count_money();
			
		}	
		
		//设置实际返还日期的初始时间为当前时间
		$(function(){
			$("#real_return").val(new Date().pattern("yyyy/MM/dd HH:mm:ss"));
			$("#modify_time").val(new Date().pattern("yyyy/MM/dd HH:mm:ss"));
		});
		
		//====================计算两个日期间的时间差（天数）结束===============================
		 function discountkeyup(costrent){
				if(costrent.length==1){
					if(!/^(\-?\d?)$/.test(costrent)){
						$("#cost_discount_span").html("*请输入数字").css("color","red");
						$("#cost_discunt_type").val("1");
					}else{
						$("#cost_discount_span").empty();
						$("#cost_discunt_type").val("");
					}
				}
				if(costrent.length>=2){
					if(!/^(\-*\d+)(\.|\.\d+)?$/.test(costrent)){
						//if(/^\-$/.test(costrent)){}
						costrent = costrent.substring(0,costrent.length-1);
						$("#cost_discount_span").html("*请输入数字").css("color","red");
						$("#cost_discunt_type").val("1");
					}else{
						$("#cost_discount_span").empty();
						$("#cost_discunt_type").val("");
					}
				}
				costrent = parseInt(costrent);
				if(isNaN(costrent)){
					costrent = 0;
				}
				count_money();
		 }
		
		function count_money(){
			//预收押金
			var cost_return = $("#cost_return").val();
			cost_return = parseInt(cost_return);
			//日租金
			var day_rent = parseInt($("#rent_day").val());
			//实际返还日期-预计返还日期
			var date_diffrcpt = $("#date_diffrcpt").val();
			date_diffrcpt = parseInt(date_diffrcpt);
			//实际出行天数
			var daycounts = $("#daycounts").val();
			daycounts=parseInt(daycounts);
			//优惠费用
			var costrent = $("#cost_discount").val();
			//起始优惠费用
			var saleday = $("#saleday").val();
			saleday = parseInt(saleday);
			costrent = parseInt(costrent);
			if(isNaN(costrent)){
				costrent = 0;
			}
			//alert("日租金="+day_rent+"实际-预计天数="+date_diffrcpt+"实际出行天数="+daycounts);
			var simcost = day_rent*daycounts;
			if(isNaN(simcost)){
				$("#3g_cost").val(0);
			}else{
				$("#3g_cost").val(simcost);
			}
			var fdate = $("#fdates").val();
			fdate = parseInt(fdate);
			//alert(fdate);
			if(daycounts<saleday){
				fdate = Math.round(fdate)-1+saleday;
			}else{
				fdate = date_diffrcpt;
			}
			//alert(date_diffrcpt);
			var costli = fdate*day_rent;
			//alert("fdate=="+fdate+"---day_rent=="+day_rent+"---date_diffrcpt=="+date_diffrcpt+"-----discountday=="+daycounts+"-----saleday=="+saleday);
			//退租金额=押金-fdate*日租金+优惠金额
			var cost_return_discount = cost_return-costli+costrent;
			//alert(cost_return_discount);
			//alert("退租金额="+cost_return_discount);
			$("#cost_return_discount").val(cost_return_discount);
			
			//预收总金额
			var cost = $("#upfront_sum").val();
			
			if(isNaN(cost_return_discount)){
				$("#cost_return_discount").val(0);
			}
			//实收金额=预收总金额-退租金额
			$("#cost_sum").val(cost-cost_return_discount);
			
			if(isNaN($("#cost_sum").val())){
				$("#cost_sum").val(0);
			}
		}
		
		$(function(){
			onchoose();			
		});
</script>

<script language="javascript" type="text/javascript">  
		   
		/*     
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
		    
		$("#cost_equipment").blur(function(){
			alert("ok");
		});
		//var date = new Date();     
		//window.alert(date.pattern("yyyy-MM-dd hh:mm:ss"));  
		// 
</script>
	<!-- 自定义提示框 开始 -->
<script type="text/javascript">
	function showinfos(id){
		var showinfospan = "<span id='"+id+"' style='display:none;position:absolute;z-index:500;'></span>"
		document.write(showinfospan);
		function showhint(obj,info)
		{
		    var top=obj.offsetTop;
		    var showtype="up";
		    var topimg="/ControlsTest/images/hint/hintuptop.gif";
		    var bottomimg="/ControlsTest/images/hint/hintupbottom.gif";
		    var hintimg="/ControlsTest/images/hint/ydot.png";
		    if(top<200)
		    {
		        showtype="down";
		        topimg="/ControlsTest/images/hint/hintdowntop.gif";
		        bottomimg="/ControlsTest/images/hint/hintdownbottom.gif";
		    }
		    showhintinfo(obj,0,0,'提示',info,0,showtype,topimg,bottomimg,hintimg);
		}
		function showhintinfo(obj, objleftoffset,objtopoffset, title, info , objheight, showtype ,topimg,bottomimg,hintimg)
		{
		    var p = getposition(obj);
		    if((showtype==null)||(showtype ==""))
		    {
		        showtype =="up";
		    }
		    //以下是自己修改
		    var html=" <div style='position:absolute; visibility: visible; width:140px;z-index:501;'> <p style='margin:0; padding:0;'> </p> <div style='overflow:hidden; zoom:1;  padding:3px 10px;  text-align:left; word-break:break-all;letter-break:break-all;font: 12px/160% Tahoma, Verdana,snas-serif; color:red; background:#FFFFE1 no-repeat;margin-top:-5px;margin-bottom:-5px;'> <span id='hintinfoup'>"+info+"</span> </div> <p style='margin:0; padding:0;'>  </p> </div> <iframe id='hintiframe' style='position:absolute;z-index:100;width:276px;scrolling:none;' frameborder='0'></iframe>";
		    //以上是自己修改
//		    document.getElementById('hintiframe'+showtype).style.height= objheight + "px";
//		    var frame;
//		    frame=document.getElementById('hintiframe'+showtype).style.height;
//		    document.getElementById('hintinfo'+showtype).innerHTML = info;
//		    document.getElementById('hintdiv'+showtype).style.display='block';
		    document.getElementById(id).style.display='block';

		        if(objtopoffset == 0)
		        {
		            document.getElementById(id).innerHTML=html;
		            if(showtype=="up")
		            {
		                document.getElementById('hintiframe').style.height= objheight + "px";
		                document.getElementById(id).style.top=(p['y']-document.getElementById('hintinfo'+showtype).offsetHeight-43)+"px";
		            }
		            else
		            {
		                document.getElementById('hintiframe').style.height= objheight + "px";
		                document.getElementById(id).style.top=p['y']+obj.offsetHeight+3+"px";
		            }
		        }
		        else
		        {
		            document.getElementById(id).style.top=p['y']+objtopoffset+"px";
		        }

		    document.getElementById(id).style.left=p['x']+objleftoffset+"px";
		}
		    
		function hidehintinfo()
		{
		    document.getElementById(id).style.display='none';
//		    document.getElementById('hintdivdown').style.display='none';
		}
		function getposition(obj)
		{
		    var r = new Array();
		    r['x'] = obj.offsetLeft;
		    r['y'] = obj.offsetTop;
		    while(obj = obj.offsetParent)
		    {
		        r['x'] += obj.offsetLeft;
		        r['y'] += obj.offsetTop;
		    }
		    return r;
		}
		
	}
	/*
	使用方法：
	          直接调用showhint()方法即可，showhint()方法中参数说明：obj为要显示提示信息的控件对象，info为提示内容
	          例：
	          onmouseover="showhint(this,'这是地球人都知道的东西，没什么好提示的。')"
	          onmouseout="hidehintinfo()"
	*/
	//"<span id='hintdiv' style='display:none;position:absolute;z-index:500;'></span>"
	var showinfospan = "<span id='"+"hintdiv"+"' style='display:none;position:absolute;z-index:500;'></span>"
	document.write(showinfospan);
	function showhint(obj,info)
	{
	    var top=obj.offsetTop;
	    var showtype="up";
	    var topimg="/ControlsTest/images/hint/hintuptop.gif";
	    var bottomimg="/ControlsTest/images/hint/hintupbottom.gif";
	    var hintimg="/ControlsTest/images/hint/ydot.png";
	    if(top<200)
	    {
	        showtype="down";
	        topimg="/ControlsTest/images/hint/hintdowntop.gif";
	        bottomimg="/ControlsTest/images/hint/hintdownbottom.gif";
	    }
	    showhintinfo(obj,0,0,'提示',info,0,showtype,topimg,bottomimg,hintimg);
	}
	function showhintinfo(obj, objleftoffset,objtopoffset, title, info , objheight, showtype ,topimg,bottomimg,hintimg)
	{
	    var p = getposition(obj);
	    if((showtype==null)||(showtype ==""))
	    {
	        showtype =="up";
	    }
	    //以下是自己修改
	    var html=" <div style='position:absolute; visibility: visible; width:140px;z-index:501;'> <p style='margin:0; padding:0;'> </p> <div style='overflow:hidden; zoom:1;  padding:3px 10px;  text-align:left; word-break:break-all;letter-break:break-all;font: 12px/160% Tahoma, Verdana,snas-serif; color:red; background:#FFFFE1 no-repeat;margin-top:-5px;margin-bottom:-5px;'> <span id='hintinfoup'>"+info+"</span> </div> <p style='margin:0; padding:0;'>  </p> </div> <iframe id='hintiframe' style='position:absolute;z-index:100;width:276px;scrolling:none;' frameborder='0'></iframe>";
	    //以上是自己修改
//	    document.getElementById('hintiframe'+showtype).style.height= objheight + "px";
//	    var frame;
//	    frame=document.getElementById('hintiframe'+showtype).style.height;
//	    document.getElementById('hintinfo'+showtype).innerHTML = info;
//	    document.getElementById('hintdiv'+showtype).style.display='block';
	    document.getElementById('hintdiv').style.display='block';

	        if(objtopoffset == 0)
	        {
	            document.getElementById("hintdiv").innerHTML=html;
	            if(showtype=="up")
	            {
	                document.getElementById('hintiframe').style.height= objheight + "px";
	                document.getElementById('hintdiv').style.top=(p['y']-document.getElementById('hintinfo'+showtype).offsetHeight-43)+"px";
	            }
	            else
	            {
	                document.getElementById('hintiframe').style.height= objheight + "px";
	                document.getElementById('hintdiv').style.top=p['y']+obj.offsetHeight+3+"px";
	            }
	        }
	        else
	        {
	            document.getElementById('hintdiv').style.top=p['y']+objtopoffset+"px";
	        }

	    document.getElementById('hintdiv').style.left=p['x']+objleftoffset+"px";
	}
	    
	function hidehintinfo()
	{
	    document.getElementById('hintdiv').style.display='none';
//	    document.getElementById('hintdivdown').style.display='none';
	}
	function getposition(obj)
	{
	    var r = new Array();
	    r['x'] = obj.offsetLeft;
	    r['y'] = obj.offsetTop;
	    while(obj = obj.offsetParent)
	    {
	        r['x'] += obj.offsetLeft;
	        r['y'] += obj.offsetTop;
	    }
	    return r;
	}
	

</script>
<!-- 自定义提示框 结束-->    
</body>
</html>