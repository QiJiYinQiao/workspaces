<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>网讯订单</title>
	<link rel="stylesheet" type="text/css" href="../media/css/easyui.css">
	<link rel="stylesheet" type="text/css" href="../media/css/icon.css">
	<link rel="stylesheet" type="text/css" href="../media/css/demo.css">
	<script type="text/javascript" src="../media/js/WdatePicker.js"></script>
	<script type="text/javascript" src="../media/js/jquery.min.js"></script>
	<script type="text/javascript" src="../media/js/jquery.easyui.min.js"></script>
	<style type="text/css">
		input{
			border: 1px solid #95b8e7;border-radius: 5px;height: 20px;width: 122px;
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
			    left: 116px;
			    position: absolute;
			    top: 4px;
			    width: 30px;
			    z-index: 99;
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
	<div data-options="region:'center',title:'设备预订'"  style="background-color:#F8F8FF;overflow: hidden;">
		<div style="font-size: 16px;margin-top: 10px;margin-left: 20px;color: red;">
		说明:
			1.提前五天预定;&nbsp;&nbsp;
			2.五天起租;&nbsp;&nbsp;
			3.特殊情况添加备注;
		</div>
	    <!-- 起始优惠天数 -->
	    <input id="saleday" value="${saleday }" style="display:none;">
		<!-- 表单开始 -->
		<form id="ff" action="saveorder" method="POST" >
		<input name="useid" value="${id }" style="display: none;">
		<input id="simtype" name="simtype" value="" style="display:none;">
		<input type="text" name="orderid"  value="${defaultdate}" style="display: none;"></input>
		<table cellpadding="4" align="center" width="100%" style="margin-left: 20px;margin-top: 20px;">
		<!-- 一行四条信息 -->
		<tr>
			<td width="8%">预定方式<span style="color: red;">*</span></td>
			<td width="14%">
				<select style="border: 1px solid #95b8e7;border-radius: 5px;height: 24px;width: 120px;" name="schedule_order" >
					<c:if test="${orderway!=null }">
						<c:forEach items="${orderway }" var="orderway">
							<option value="${orderway.orderwaycode }">${orderway.orderwayname }</option>
						</c:forEach>
					</c:if>
				</select>
			</td>
			
			<td >优惠<span style="color: red;">*</span></td>
			<td>
			<select onchange="discountchange();" id="discountway" style="border: 1px solid #95b8e7;border-radius: 5px;height: 24px;width: 120px;" name="discountway" >
				<c:if test="${youhuilist!=null }">
					<c:forEach items="${youhuilist }" var="youhui">
						<option value="${youhui.code }">${youhui.name }</option>
					</c:forEach>
				</c:if>
			</select>
			</td>
			
			<td width="8%">姓名<span style="color: red;">*</span></td>
			<td width="14%">
			<input id="rent_p_name" onblur="rentpname()" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;" type="text" name="uname" ></input>
			</td>
			
			<td width="8%">手机号<span id="cellphone" style="color: red;">*</span></td>
			<td width="14%"><!-- checkMobile(this.value) -->
			<input style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;" type="text" id="rent_p_tel" name="uphone" onblur="checkMobile(this)"></input>
			<input id="tel_test" style="display:none;"  value="">
			</td>
			
		</tr>
		<tr>
			<td width="10%">护照号</td>
			<td width="15%"><input class="easyui-textbox" type="text" name="upassport" ></input></td>
		
			<td>国籍<span style="color: red;">*</span></td>
			<td>
			<select id="rent_p_nationality" style="border: 1px solid #95b8e7;border-radius: 5px;height: 24px;width: 120px;" name="nationality" >
				<c:if test="${nationality!=null }">
					<c:forEach items="${nationality }" var="nation">
						<option value="${nation.ITEM_VALUE }">${nation.ITEM_TEXT }</option>
					</c:forEach>
				</c:if>
			</select>
			</td>
			
			<td>取货地<span style="color: red;">*</span></td>
			<td>
			<select id="takeorderplace" onchange="toplace(this.value)" style="border: 1px solid #95b8e7;border-radius: 5px;height: 24px;width: 120px;" name="city">
				<option value="0" selected="selected">请选择</option>
				<c:if test="${takeorderplace!=null }">
					<c:forEach items="${takeorderplace }" var="toplace">
						<option value="${toplace.countycode }">${toplace.countryname }</option>
					</c:forEach>
				</c:if>
			</select>
			</td>
			<td colspan="2">
			<select id="takeordercplace" style="border: 1px solid #95b8e7;border-radius: 5px;height: 24px;width: 210px;" name="counter">
			</select>
			</td>
		</tr>
		<tr>
			<td >目的地<span style="color: red;">*</span></td>
			<td>
			<select id="destionations" onchange="distionationchange();" style="border: 1px solid #95b8e7;border-radius: 5px;height: 24px;width: 120px;" name="destination" >
				<option value="0" selected="selected">请选择</option>
				<c:if test="${destionation!=null }">
					<c:forEach items="${destionation }" var="destionation">
						<option onclick="destionationchange(${destionation.deposit })" value="${destionation.countrycode }">${destionation.countryname }</option>
					</c:forEach>
				</c:if>
			</select>
			</td>
			
			
			<td>租用日期<span style="color: red;">*</span></td>
			<td>
			<input id="txtDate" onblur="startinfo()" readonly="readonly" style="border: 1px solid #95b8e7;border-radius: 5px;" class="Wdate" type="text" name="rentdate" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy/MM/dd',minDate:'#F{$dp.$D(\'start\',{d:5})}',maxDate:'#F{$dp.$D(\'rent_expectdate\',{d:-4})}',onpicking:diffDate()})"/>
			</td>
			
			<td>返还日期<span style="color: red;">*</span></td>
			<td>
			<input id="rent_expectdate" onblur="expectinfo()" readonly="readonly" style="border: 1px solid #95b8e7;border-radius: 5px;" class="Wdate" type="text" name="enddate" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy/MM/dd',minDate:'#F{$dp.$D(\'txtDate\',{d:+4})}',onpicking:diffDate()})"/>
			</td>
			
			<td>出行天数</td>
			<td width="22%"><input id="dayc" name="outday" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;background-color:#B0C4DE;" readonly="readonly" type="text"  value="0" ></input></td>
		
		</tr>
		
		<tr>
			<td>个数<span style="color: red;">*</span></td>
			<td>
			<input id="ordernum" onkeyup="ordernumkeyup(this.value)" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;" type="text" id="s_country" name="ordernum"  value="1"></input>
			</td>
			
			<td>日租金</td>
			<td>
			<input id="dayrent" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;background-color:#B0C4DE;" readonly="readonly"  type="text" name="dayrent" value="0" ></input>
			</td>
			
			<td>租赁费用 </td>
			<td>
			<input id="rent_cost" onkeyup="rentcostchange(this.value)" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;" type="text" name="rent_cost" value="0" ></input>
			</td>
			
			<td>押金 </td>
			<td>
			<input id="deposit" name="deposit" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;background-color:#B0C4DE;" readonly="readonly" type="text"  value=""></input>
			<input id="originaldeposit" value="" type="hidden">
			</td>
			
		</tr>	
		<tr>
			<td>应收总金额 </td>
			<td>
			<input id="shouldgetcost" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;background-color:#B0C4DE;" readonly="readonly"  type="text"  name="shouldgetcost" value="0" ></input>
			</td>
			
			<td>已收金额<span style="color: red;">*</span></td>
			<td>
			<input id="hadgetcost" onkeyup="hadgetcodekeyup(this.value)" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;" type="text"  name="hadgetcost"  value="0"></input>
			</td>
			
			<td>待收金额 </td>
			<td>
			<input id="pregetcost" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;background-color:#B0C4DE;" type="text" name="pregetcost" value="0" ></input>
			</td>
			
		</tr>
		<tr>
			<td>日期 </td>
			<td>
			<input id="start" style="border: 1px solid #95b8e7;border-radius: 5px;background-color:#B0C4DE;" readonly="readonly" type="text" name="createtime" />
			</td>
		
			<td>操作员 </td>
			<td>
			<input style="border: 1px solid #95b8e7;border-radius: 5px;background-color:#B0C4DE;" readonly="readonly" type="text" name="createuser"  value="${realname}"></input>
			</td>
		</tr>
		
		<tr>
			<td >收货地址 </td>
			<td colspan="7">
			<textarea  style="border: 1px solid #95b8e7;border-radius: 5px;height: 40px;width:90%;resize:none;overflow-Y:scroll;font-size: 18px;" resize="none"  name="takeorderaddress"></textarea>
			</td>
		</tr>
		<tr>
			<td >备注 </td>
			<td colspan="7">
			<textarea  style="border: 1px solid #95b8e7;border-radius: 5px;height: 60px;width:90%;resize:none;overflow-Y:scroll;font-size: 18px;" resize="none"  name="remark"></textarea>
			</td>
		</tr>
		</table>
		<input style="display: none;" name="equipment_type" id="equipment_type" value="0001"/>
		</form>
		
		<div style="text-align:right;padding-right:10px;width:100%;">
		<div style="width:90%"><!-- onclick="submitForm()" -->
		<a href="javascript:void(0)" class="easyui-linkbutton" ondblclick="dc()" onclick="submitForm()" style="width: 56px;">提交</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()" style="width: 56px;">清空</a>
		</div>
		</div>
		<!-- 记录第一次提交 防止重复提交-->
		<input id="forbid" value="2" style="display:none;">
		<!-- 存放日租金 -->
		<!-- <input id="dayrent" value="0" style="display:none;"> -->
		<!-- 表单结束 -->
	</div>
	<!-- 弹出框 width:76%;height:65%;-->
		<div id="dlg"  class="easyui-dialog" modal="true"  title="设备列表"  style="overflow: hidden;width:1000px;height:450px;">
			<iframe id="eqlist" name="contents" src="" width="100%" height="100%" frameborder="0" style="overflow:hide;"></iframe>
		</div>

	<!-- 提示信息，失去焦点隐藏 -->
	<script type="text/javascript">
		$(function(){
			$('#dlg').dialog('close');
		});
		function rentpname(){
			if(!$("#rent_p_name").val()=="")
				$("#nameinfo").css("display","none");
		}
		function startinfo(){
			if($("#startinfo").val()!=""){
				$("#startinfo").css("display","none");
			}
		}
		function expectinfo(){
			if($("#expectinfo").val()!=""){
				$("#expectinfo").css("display","none");
			}
		}
		
		function dc(){
			alert('请勿双击!');
			return false;
		}
		
		function toplace(countrycode){
			//alert(countrycode);
			jQuery.ajax({
				url:"queryctakeorderplace",
				data:{countrycode:countrycode},
				type:"post",
				error:function(){},
				success:function(data){
					$("#takeordercplace").empty();
					$("#takeordercplace").append("<option value='000'>快递</option>");
					$("#takeordercplace").append(data);
				}
			});
		}
		
		/* function deschange(countrycode){
			jQuery.ajax({
				url:"queryDayRent",
				data:{countrycode:countrycode},
				type:"post",
				error:function(){},
				success:function(data){
					$("#dayrent").val(data);
					count_rent();
				}
			});
		} */
		
		function ordernumkeyup(ordernum){
			//alert(ordernum=="");
			if(ordernum==""){
				ordernum = 1;
			}else{
				var deposit = $("#originaldeposit").val();
				deposit = parseInt(deposit)*parseInt(ordernum);
				$("#deposit").val(deposit);
			}
			count_rent();
		}
		
		function hadgetcodekeyup(){
			var rcost = $("#rent_cost").val();
			count_rent(rcost);
		}
		//目的地改变
		function distionationchange(){
			//alert("sdf");
			var discount = $("#discountway").val(); 
			var mainid = $("#destionations").val();
				jQuery.ajax({
					url:"queryDiffDayrent",
					type:"post",
					data:{discount:discount,mainid:mainid},
					error:function(){},
					success:function(data){
						//alert(data);
						$("#dayrent").val(data);
						var ordernum = $("#ordernum").val();
						var deposit = $("#deposit").val();
						deposit = parseInt(ordernum)*parseInt(deposit);
						$("#deposit").val(deposit);
						count_rent();
					}
				})
				
		}
		
		//优惠方式改变
		function discountchange(){
			/* var upid = $("#destionations").val();*/
			var discount = $("#discountway").val(); 
			var mainid = $("#destionations").val();
				jQuery.ajax({
					url:"queryDiffDayrent",
					type:"post",
					data:{discount:discount,mainid:mainid},
					error:function(){},
					success:function(data){
						//alert(data);
						$("#dayrent").val(data);
						count_rent();
					}
				})
			//alert(discountway);
		}
		
		function destionationchange(deposit){
			$("#deposit").val(deposit);
			$("#originaldeposit").val(deposit);
		}
	</script>
	<!-- 处理租用日期 返还日期 -->
	<script type="text/javascript">
		function diffDate(str1, str2) {
			$("#expectinfo").css("display","none");
			str1 = $("#txtDate").val();
			str2 = $("#rent_expectdate").val();
			if(str1=="" || str2==""){
				return false;
			}
		    str1 = str1.replace(/-/g, "/");
		    str2 = str2.replace(/-/g, "/");
		    var d1;
		    var d2;
		    var diffday = 0;
		    if (str1 == "") {
		      d1 = new Date();
		    } else {
		      d1 = new Date(str1);
		    }
		    if (str2 == "") {
		      d2 = new Date();
		    } else {
		      d2 = new Date(str2);
		    }
		    diffday = Date.parse(d2) - Date.parse(d1);
		   	// 出行天数
		    diffday = diffday.toFixed(2) / 86400000 +1;
		   	$("#dayc").val(diffday);
		   	count_rent();
		  }
		
		    
		    //计算租金的方法
		    function count_rent(rcost){
		    	var rentday = $("#dayc").val();
		    	var dayrent = $("#dayrent").val();
		    	var ordernum = $("#ordernum").val();
		    	var deposit = $("#deposit").val();
		    	var hadgetcost = $("#hadgetcost").val();
		    	var rentcost;
		    	hadgetcost = parseInt(hadgetcost);
		    	deposit = parseInt(deposit);
		    	if(rcost==null){
		    		rentcost = parseInt(rentday)*parseInt(dayrent)*parseInt(ordernum);
		    	}else{
		    		rentcost = parseInt(rcost);
		    	}
		    	//var rentcost = parseInt(rentday)*parseInt(dayrent)*parseInt(ordernum);
		    	if(rentday == "" || dayrent =="" || ordernum ==""){
		    		return false;
		    	} 
		    	$("#rent_cost").val(rentcost);
		    	$("#shouldgetcost").val(deposit+rentcost);
		    	$("#pregetcost").val(deposit+rentcost-hadgetcost);
		    }
		
		 //租金改变
		 function rentcostchange(rentcost){
			 //alert("=="+rentcost);
			 var hadgetcost = $("#hadgetcost").val();
			 var deposit = $("#deposit").val();
			 hadgetcost = parseInt(hadgetcost);
		     deposit = parseInt(deposit);
		     rentcost = parseInt(rentcost);
		     $("#shouldgetcost").val(deposit+rentcost);
		     $("#pregetcost").val(deposit+rentcost-hadgetcost);
		 }
		 
		//设置租用日期的初始时间为当前时间
		/* $(function(){
			$("#txtDate").val(new Date().pattern("yyyy/MM/dd HH:mm:ss"));
		}); */
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
	//验证手机号1开头11位
	 function checkMobile(obj) {
		if($("#telinfo").val()==""){
			$("#telinfo").css("display","");
		}
		var str =obj.value;
	    var re = /^1\d{10}$/;
	    if (!re.test(str)) {
	       alert("手机号格式有误!");
	       return false;
	    }
	}
		
	function submitForm(){
		if($("#forbid").val()==1){
			alert("请勿重复提交!");
			return false;
		}else if($("#rent_p_name").val()==""){
			alert("姓名不能为空!")
			return false;
		}else if(!/^1\d{10}$/.test($("#rent_p_tel").val())){
			alert("手机号格式有误!");
			return false;
		}else if($("#takeorderplace").val()=="0"){
			alert("请选择取货地");
			return false
		}else if($("#destionations").val()=="0"){
			alert("请选择目的地！")
			return false;
		}else if($("#txtDate").val()==""){
			alert("请选择租用日期!")
			return false;
		}else if($("#rent_expectdate").val()==""){
			alert("请选择返回日期!")
			return false;
		}else if($("#ordernum").val()==""){
			alert("请输入数量！")
			return false;
		}else if($("#hadgetcost").val()==""){
			alert("输入已收金额！")
			return false;
		}else{
			$("#forbid").val("1");
			jQuery.ajax({
				url:"saveorder",
				data:$('#ff').serialize(),
				type:"POST",
				error:function(){
					$.messager.alert("","添加失败");
				},
				success:function(data){
					if(data=="1"){
						alert("添加成功");
						window.location.reload(true);
					}else{
						$.messager.alert("","添加失败");
					}
					
				}
			});
		}
		
	}
	
	function clearForm(){
	/* $('#ff').form('clear'); */
		parent.frames["countents"].src = "wxorder/add";
		
	}
	$(function(){
		$("#start").val(new Date().pattern("yyyy/MM/dd HH:mm:ss"));
		
	});
</script>
<script type="text/javascript">
	$(function(){
		$("#search_img").click(function(){
			 document.getElementById("eqlist").src="../equipment/list";
			 $('#dlg').dialog('open');
			//window.open("../equipment/list", "设备列表",'height=500,width=1000,top=100,left=300,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no')
		});
	});
</script>
</body>
</html>