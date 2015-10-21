<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>设备订购</title>
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
	<script type="text/javascript">
		$(function(){
			$("#d_country").attr("disabled","disabled");
		});
	</script>
	<!-- <div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;padding:10px">龙在天下</div>
	<div data-options="region:'west',split:true,title:'菜单栏'" style="width:150px;padding:10px;">
		<div id="orders">订单管理
			<div style="display:none;" id="order">
				<a href="order/add">增加订单</a>
			</div>
		</div>
		<dl>
			<dt>订单管理</dt>
			<dd>增加订单</dd>
		</dl>
		
	</div>
	<div data-options="region:'east',split:true,collapsed:true,title:'通知'" style="width:100px;padding:10px;">通知</div>
	<div data-options="region:'south',border:false" style="height:5px;background:#A9FACD;padding:10px;"></div>
	<div data-options="region:'center',title:'列表'"> -->
	<div data-options="region:'center',title:'设备订购(上海柜台)'"  style="background-color:#F8F8FF;overflow: hidden;">
		<div style="font-size: 16px;margin-left: 20px;color: red;height: 20px;">
		说明:&nbsp;
			1.三天起租&nbsp;&nbsp;
			2.五天开始优惠&nbsp;&nbsp;
			3.特殊情况添加备注&nbsp;
		</div>
	    <!-- 起始优惠天数 -->
	    <input id="saleday" value="${saleday }" style="display:none;">
		<!-- 表单开始 -->
		<form id="ff" action="add" method="POST" >
		<input name="useid" value="${id }" style="display: none;">
		<input id="simtype" name="simtype" value="" style="display:none;">
		<table cellpadding="12" align="center" width="100%" style="margin-left: 20px;margin-top: 20px;">
		<!-- 一行四条信息 -->
		
		<tr>
			<td width="10%">订单号</td>
			<td width="22%"><input style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;background-color:#B0C4DE;" readonly="readonly" type="text" name="order_num"  value="${defaultdate}" ></input></td>
			
			<td width="10%">姓名<span style="color: red;">*</span></td>
			<td width="22%">
			<input id="rent_p_name" onblur="rentpname()" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;" type="text" name="rent_p_name"   <c:if test="${wxorder.uname!=null }">value='${wxorder.uname }'</c:if>></input>
			<span id="nameinfo" style="color:red;display:none ;">请填写姓名!</span>
			</td>
			
			<td width="12%">手机号<span id="cellphone" style="color: red;">*</span></td>
			<td width="22%"><!-- checkMobile(this.value) -->
			<input style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;" type="text" id="rent_p_tel" name="rent_p_tel" onblur="checkMobile(this)" <c:if test="${wxorder.uphone!=null }">value='${wxorder.uphone }'</c:if>></input>
			<span id="telinfo" style="color:red;display: none;">请填写手机号!</span>
			<input id="tel_test" style="display:none;"  value="">
			</td>
		
		
		<!-- <td>设备号 :</td>upassport
		<td>
		<input class="easyui-textbox" type="text" name="equipment_no" ></input>
		</td> -->
		</tr>
		
		<tr>
			<td >设备号<span style="color: red;">*</span> 
			</td>
			<td><div class="container">
			<input id="equipment_no" width="15%"  type="text" onblur="equipment_blur()" name="equipment_no" ></input>
			<div id="search_img" class="search"></div><span id="noinfo" style="color:red;display: none;">请填写设备号!</span></div>
			<input id="isvalids" style="display: none;">
			</td>
			
			
			<td>租用日期<span style="color: red;">*</span></td>
			<td>
			<!-- <input id="txtDate" class="easyui-datetimebox" type="text" name="rent_begindate" ></input> ,onpicked:function() {$dp.$('date_diff').value=$dp.cal.date.y;} -->
			<input id="txtDate" onblur="startinfo()" readonly="readonly" style="border: 1px solid #95b8e7;border-radius: 5px;" class="Wdate" type="text" name="rent_begindate" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy/MM/dd HH:mm:ss',maxDate:'#F{$dp.$D(\'rent_expectdate\',{d:-2})}',onpicking:diffDate()})"/>
			<span id="startinfo" style="color:red;display: none;">请选择日期!</span>
			</td>
			<td>预计返还日期<span style="color: red;">*</span></td>
			<td>
			<!-- <input id="txtDate" class="easyui-datetimebox" type="text" name="rent_expectdate" ></input> -->
			<input id="rent_expectdate" onblur="expectinfo()" readonly="readonly" style="border: 1px solid #95b8e7;border-radius: 5px;" class="Wdate" type="text" name="rent_expectdate"  onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy/MM/dd HH:mm:ss',minDate:'#F{$dp.$D(\'txtDate\',{d:+2})}',onpicking:diffDate()})"/>
			<span id="expectinfo" style="color:red;display: none;">请选择日期!</span>
			</td>
		</tr>

		<tr>
			<!-- 保存租用日期和预计返还日期的时间差 时间差 -->
			<input id="dayc" name="dayc" style="display:none;" />
			
			<!-- 保存租用日期和预计返还日期的减去优惠天数 时间差-优惠天数-->
			<input id="date_diff" name="date_diff" style="display:none;" />
			
			<td >目的地<span style="color: red;">*</span></td>
			<td>
			<!-- <input id="d_country" type="text" name="d_country" ></input>destination -->
			<select id="d_country" style="border: 1px solid #95b8e7;border-radius: 5px;height: 24px;width: 140px;" name="d_country" >
				<option id="dcountryvalue" selected='selected' value=""></option>
				<c:if test="${country!=null }">
					<c:forEach items="${country }" var="countrys">
						<option value="${countrys.ITEM_VALUE }">${countrys.ITEM_TEXT }</option>
					</c:forEach>
				</c:if>
			</select>
			</td>
			
			<td>出发地 </td>
			<td>
				<input class="easyui-textbox" type="text" id="s_country" name="s_country"  value="${counters.country}"></input>
			</td>
			
			<td>舱位等级<span style="color: red;">*</span></td>
			<td>
			<select id="positionlevel" onchange="levelchange(this.value)" style="border: 1px solid #95b8e7;border-radius: 5px;height: 24px;width: 140px;" name="position_level">
				<c:if test="${discountday!=null }">
					<c:forEach items="${discountday }" var="clist">
						<option value="${clist.ITEM_VALUE }">${clist.ITEM_TEXT }</option>
					</c:forEach>
				</c:if>
			</select>
			<input id="positionlevelDesc" value="${positionlevelDesc }" style="display: none;" >
		</tr>
		
		<tr>
			<td>柜台<span style="color: red;">*</span></td>
			<td>
			<select id="counterid" style="border: 1px solid #95b8e7;border-radius: 5px;height: 24px;width: 140px;" name="counterid">
				<c:if test="${counterlists!=null }">
					<c:forEach items="${counterlists }" var="clist">
						<option value="${clist.counter_code }">${clist.name }</option>
					</c:forEach>
				</c:if>
			</select>
			</td>
			<td>支付方式<span style="color: red;">*</span></td>
			<td>
			<select id="payment" style="border: 1px solid #95b8e7;border-radius: 5px;height: 24px;width: 140px;" name="payment">
				<c:if test="${paymentlist!=null }">
					<c:forEach items="${paymentlist }" var="payment">
						<option value="${payment.ITEM_VALUE }">${payment.ITEM_TEXT }</option>
					</c:forEach>
				</c:if>
			</select>
			</td>
			<td>租赁费用 </td>
			<td><!-- onkeyup="keyups(this.value)"  -->
			<input id="cost_rent" readonly="readonly" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;background-color:#B0C4DE;" type="text" name="cost_rent_3g" value="0" ></input>
			</td>
			<input id="day_rent" name="rent_day" style="display:none;"/>
			
		
		</tr>	
			
		<tr>
			<td>押金 </td>
			<td>
			<input id="deposit" name="deposit" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;background-color:#B0C4DE;" readonly="readonly" type="text"  value="0"></input>
			</td>
			
			<td>预收总金额 </td>
			<td>
			<input id="upfront_sum" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;background-color:#B0C4DE;" type="text" readonly="readonly" name="upfront_sum" ></input>
			</td>
			
			<td width="10%">POS金额<span style="color: red;">*</span></td>
			<td width="15%"><input onblur="posinfo(this.value);" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;" type="text" name="pos_money" id="pos_money" onkeyup="value=value.replace(/[^\d.]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d.]/g,''))" ></input>
			<span id="posinfo" style="color:red;display: none;">请填写POS金额!</span>
			</td>
		</tr>
		
		<tr>
			<td width="10%">现金<span style="color: red;">*</span></td>
			<td width="15%"><input onblur="cashinfo(this.value);" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;" type="text" name="cash_money" id="cash_money" onkeyup="value=value.replace(/[^\d.]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d.]/g,''))" ></input>
			<span id="cashinfo" style="color:red;display: none;">请填写现金金额!</span>
			</td>
			
			<td width="10%">身份证号</td>
			<td width="15%"><input class="easyui-textbox" type="text" name="rent_p_idnumber" ></input></td>
		
			<td width="10%">护照号</td>
			<td width="15%"><input class="easyui-textbox" type="text" name="rent_p_passportno" ></input></td>
		</tr>
		<tr>
			<td>JCB刷卡日期 </td>
			<td>
			<input id="jcbdate" readonly="readonly" style="border: 1px solid #95b8e7;border-radius: 5px;" class="Wdate" type="text" name="jcbdate" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy/MM/dd'})"/></td>
			<td>JCB刷卡金额 </td>
			<td>
			<input style="border: 1px solid #95b8e7;border-radius: 5px;"  type="text" name="jcbcardmoney"  value="" onkeyup="value=value.replace(/[^\d.]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d.]/g,''))" ></input>
			</td>
			<td>JCB卡号 </td>
			<td>
			<input style="border: 1px solid #95b8e7;border-radius: 5px;" type="text" name="jcbcardnumber"  value=""></input>
			</td>
		</tr>
		<tr>
			<td>国籍<span style="color: red;">*</span></td>
			<td>
			<select id="rent_p_nationality" style="border: 1px solid #95b8e7;border-radius: 5px;height: 24px;width: 140px;" name="rent_p_nationality" >
				<c:if test="${nationality!=null }">
					<c:forEach items="${nationality }" var="nation">
						<option value="${nation.ITEM_VALUE }">${nation.ITEM_TEXT }</option>
					</c:forEach>
				</c:if>
			</select>
			</td>
			<td>日期 </td>
			<td>
			<!-- <input id="txtDate" class="easyui-datetimebox" type="text" name="create_time" ></input> -->
			<!--class="Wdate" onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})" -->
			<input id="start" style="border: 1px solid #95b8e7;border-radius: 5px;background-color:#B0C4DE;" readonly="readonly" type="text" name="create_time" />
			<!-- readonly="true"  class="Wdate" onfocus="new WdatePicker(this)" -->
			</td>
		
			<td>操作员 </td>
			<td>
			<input style="border: 1px solid #95b8e7;border-radius: 5px;background-color:#B0C4DE;" readonly="readonly" type="text" name="create_user"  value="${realname}"></input>
			</td>
		</tr>
		<tr>
			<td >备注 </td>
			<td colspan="7">
			<textarea  style="border: 1px solid #95b8e7;border-radius: 5px;height: 100px;width:90%;resize:none;overflow-Y:scroll;font-size: 18px;" resize="none"  name="remark"></textarea>
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
		<!-- 表单结束 -->
	</div>
	<!-- 遮罩层开始 -->
		<!-- <script type="text/javascript">
			$("#idBoxOpen").click(function (){
				alert("tijiao");
				var screen = $("#screen");
				//alert(screen.id);
				parent.$("#screen").css("display","block");
			});
		</script> -->
	<!-- 遮罩层结束 -->
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
		function posinfo(str){
			if(str!=""){
				$("#posinfo").css("display","none");
			}else{
				$("#posinfo").css("display","");
			}
		}
		function cashinfo(str){
			if(str!=""){
				$("#cashinfo").css("display","none");
			}else{
				$("#cashinfo").css("display","");
			}
		}
		
		function dc(){
			alert('请勿双击!');
			return false;
		}
	</script>
	<!-- 处理租用日期 返还日期 -->
	<script type="text/javascript">
		function diffDate(str1, str2) {
			$("#expectinfo").css("display","none");
			str1 = $("#txtDate").val();
			str2 = $("#rent_expectdate").val();
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
		   	// 实际收费天数
		    diffday = diffday.toFixed(2) / 86400000 +1;
		   	$("#dayc").val(diffday);
		   	count_rent();
		   	
		  }
		
		  //输入设备号获取设备日租金
		   function equipment_blur(eqptno){
			  // alert("equipment blur");
		    	var eqptno = $("#equipment_no").val();
		    	if(eqptno!=""){
		    		$("#noinfo").css("display","none");
		    	}
		    	var testeqpt =/^[A-Za-z0-9]{11}$/;
		    	if(testeqpt.test(eqptno)){
		    		//alert("外部设备");
		    		 jQuery.ajax({
				        	/* url:"../info/queryD_country", */
				        	url:"../equipment/isExist",
				        	data:{equipment_no:eqptno},
				        	type:"POST",
				        	error:function(request){
				        		//alert("no data");
				        	},
				        	success:function(data){
				        		//alert(data);
								if(data=="0"){
									//var obs = document.getElementById("equipment_no");
					        		//showhint(obs,"设备已存在");
					        		document.getElementById("noinfo").innerHTML="此设备已存在";
			        				$("#noinfo").css("display","");
					        		$("#isvalids").val("003");
					        		return false;
								}else{
					        		$("#isvalids").val("");
					        		hidehintinfo();
					        	}				        		
				        	}
				        	});
		    		$("#equipment_type").val("0002")
		    	}
		    	var equipment_type = $("#equipment_type").val();
		    	if(eqptno==""){
		    		return false;
		    	}
		       
		        if(!testeqpt.test(eqptno)){
		        jQuery.ajax({
		        	/* url:"../info/queryD_country", */
		        	url:"../equipment/dayRent",
		        	data:{equipment_no:eqptno,equipment_type:equipment_type},
		        	type:"POST",
		        	error:function(request){
		        		//alert("no data");
		        	},
		        	success:function(data){
		        		data  = $.parseJSON(data);//没有这句话返回的不是对象
		        		
			        	if(data[0]==null){
			        		//var obs = document.getElementById("equipment_no");
			        		//showhint(obs,"设备号无效");
			        		document.getElementById("noinfo").innerHTML="设备号无效";
			        		$("#noinfo").css("display","");
			        		$("#isvalids").val("001");
			        		$("#cost_rent").val(0);
			        		$("#upfront_sum").val(0);
			        		return false;
			        	}else if(data[0].is_valid != '0001'){
			        		document.getElementById("noinfo").innerHTML="设备号无效";
			        		$("#noinfo").css("display","");
			        		$("#isvalids").val("001");
			        		return false;
			        	}else{
			        		$("#isvalids").val("");
			        		hidehintinfo();
			        	}
			        	//alert(data[0].day_rent);
			        	if(data[0].day_rent==null){
			        		$("#day_rent").val("0");
			        	}
			        	//$("#d_country").val(data[1]);
			        	//alert(data[1].ITEM_VALUE);
			        	//alert(data[0]);
			        	//var options = "<option selected='selected' value='"+data[1].ITEM_VALUE+"'>"+data[1].ITEM_TEXT+"</option>";
			        	//$("#d_country").append(options);
			        	//$("#deposit").val(data[0].deposit);
			        	$("#day_rent").val(data[0].day_rent); 
			        	$("#deposit").val(data[2]);
			        	//var options = "<option selected='selected' value='"+data[1].ITEM_VALUE+"'>"+data[1].ITEM_TEXT+"</option>";
			        	document.getElementById("dcountryvalue").value=data[1].ITEM_VALUE;
			        	document.getElementById("dcountryvalue").innerHTML=data[1].ITEM_TEXT
				       // $("#d_country").append(options);
			        	//alert(data);
			        	count_rent(); 
		        	}
		        });}else if(testeqpt.test(eqptno)){//外部设备
		        	//alert("waibushebei");
		        	jQuery.ajax({
			        	/* url:"../info/queryD_country", */
			        	url:"../equipment/dayRent",
			        	data:{equipment_no:eqptno,equipment_type:equipment_type},
			        	type:"POST",
			        	error:function(request){
			        		//alert("no data");
			        	},
			        	success:function(data){
			        	data  = $.parseJSON(data);//没有这句话返回的不是对象
		        		//alert(data[0]);
		        		//alert(data[0].DESCRIPTION);
		        		//alert(data[0].ITEM_VALUE);
			        	if(data[0]==null){
			        		//var obs = document.getElementById("equipment_no");
			        		//showhint(obs,"设备号无效");
			        		document.getElementById("noinfo").innerHTML="设备号无效";
			        		$("#noinfo").css("display","");
			        		$("#isvalids").val("001");
			        		return false;
			        	}else{
			        		$("#deposit").val(data[1]);
			        		if(data[0]!=null){
			        			$("#day_rent").val(data[0].DESCRIPTION);
				        		
				        		//alert(data[1].DESCRIPTION);
				        		//var options = "<option selected='selected' value='"+data[0].ITEM_VALUE+"'>"+data[0].ITEM_TEXT+"</option>";
				        		document.getElementById("dcountryvalue").value=data[0].ITEM_VALUE;
					        	document.getElementById("dcountryvalue").innerHTML=data[0].ITEM_TEXT
					        	//$("#d_country").append(options);
					        	count_rent(); 
			        		}
			        	}
		        	}});
		      /* $.get("queryDcountry?equipment_no="+eqptno,function(data){
		        	$("#d_country").val(data);
		        }); */
		    }
		        };
		    
		    //计算租金的方法
		    function count_rent(){
		    	var dayrent = $("#day_rent").val();
		    	var days = $("#positionlevelDesc").val();
		    	var rentday = $("#dayc").val();
		    	var saleday = $("#saleday").val();
		    	saleday = parseInt(saleday);
		    	//alert("优惠天数="+days+"租用天数="+rentday);
		    	var cost_rent = Math.round(dayrent)*Math.round(rentday);
		    	if(rentday>=saleday){
		    		cost_rent = Math.round(cost_rent) - Math.round(days*dayrent);
		    	}
	     		var cost_return = $("#deposit").val();
	        	$("#cost_rent").val(cost_rent);
	        	var upfront_sum = parseFloat(cost_rent)+parseFloat(cost_return);
	        	//var upfront_sum = parseInt(cost_rent)+parseInt(cost_return);
	        	$("#upfront_sum").val(upfront_sum);
		    }
		
		function levelchange(day){
			//alert(day);
			jQuery.ajax({
				url:"getDiscountDay",
				data:{itemvalue:day},
				type:"post",
				error:function(){},
				success:function(data){
					var youhui = data;
					var shijiday = $("#dayc").val();
					//alert(shijiday>4);
					if(shijiday>4){
						var costday = shijiday - youhui;
						$("#date_diff").val(costday);
						$("#positionlevelDesc").val(data);
						//alert(costday);
						var data = $("#day_rent").val();
						var cost_rent = costday*data;
						var cost_return = $("#deposit").val();
						var exday = $("#rent_expectdate").val();
						if(exday==""){
							$("#cost_rent").val(0);
						}else{
							$("#cost_rent").val(cost_rent);
							var upfront_sum = parseFloat(cost_rent)+parseFloat(cost_return);
							$("#upfront_sum").val(upfront_sum);
						}
					}
				}
			});
		}
		    
		//设置租用日期的初始时间为当前时间
		$(function(){
			$("#txtDate").val(new Date().pattern("yyyy/MM/dd HH:mm:ss"));
			$("#jcbdate").val(new Date().pattern("yyyy/MM/dd"));
		});
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
				    
			/* 	$("#cost_equipment").blur(function(){
					alert("ok");
				}); */
				
				/* function keyups(costrent){
					if(!/^(-?\d+)(\.|\.\d+)?$/.test(costrent)){
						$("#cost_rent_span").html("*请输入数字").css("color","red");
					}else{
						$("#cost_rent_span").empty();
					}
					
					var cost_rent = $("#cost_rent").val();
					cost_rent = parseInt(cost_rent);
					var cost_equipment = $("#cost_equipment").val();
					cost_equipment = parseInt(cost_equipment);
					var cost_return = $("#cost_return").val();
					cost_return = parseInt(cost_return);
					var upfront_sum = cost_rent+cost_equipment+cost_return;
					$("#upfront_sum").val(upfront_sum);
				}
				
				function keyups1(costrent){
					if(!/^(-?\d+)(\.|\.\d+)?$/.test(costrent)){
						$("#costequipment").html("*请输入数字").css("color","red");
					}else{
						$("#costequipment").empty();
					}
					
					var cost_rent = $("#cost_rent").val();
					cost_rent = parseInt(cost_rent);
					var cost_equipment = $("#cost_equipment").val();
					cost_equipment = parseInt(cost_equipment);
					var cost_return = $("#cost_return").val();
					cost_return = parseInt(cost_return);
					var upfront_sum = cost_rent+cost_equipment+cost_return;
					$("#upfront_sum").val(upfront_sum);
				} */
</script>    

<script>
	//验证手机号1开头11位
	 function checkMobile(obj) {
		//alert(str);
		//alert("haha");
		if($("#telinfo").val()==""){
			$("#telinfo").css("display","");
		}
		var str =obj.value;
	    var re = /^1\d{10}$/;
	    if (!re.test(str)) {
	    	//alert("wrong");
	       //showhint(obj,"手机号格式有误!");
	       document.getElementById("telinfo").innerHTML="手机号格式有误!";
	       $("#tel_test").val("1");
	       return false;
	    }else{
	       //$("#cellphone1").html("");
	       //hidehintinfo();
	       $("#tel_test").val("");
	       $("#telinfo").css("display","none");
	       //return true;
	    }
	}
		
	function submitForm(){
		if($("#forbid").val()==1){
			alert("请勿重复提交!");
			return false;
		}
		//alert("submit");
		//checkMobile();
		if($("#rent_p_name").val()==""){
			var ob = document.getElementById("rent_p_name");
			//showhint(ob,"姓名不能为空!");
			$("#nameinfo").css("display","");
			return false;
		}else if($("#rent_p_tel").val()==""){
			var ob = document.getElementById("rent_p_tel");
			$("#telinfo").css("display","");
			//showhint(ob,"手机号不能为空!");
		}else if($("#tel_test").val()=="1"){
			// var obs = document.getElementById("rent_p_tel");
		    // showhint(obs,"手机号格式有误!");
		    document.getElementById("telinfo").innerHTML="手机号格式有误!";
		    return false;
		}else if($("#equipment_no").val()==""){
			//var ob = document.getElementById("equipment_no");
			//showhint(ob,"设备号不能为空!");
			$("#noinfo").css("display","");
			return false;
		}else if($("#txtDate").val()==""){
			//var ob = document.getElementById("txtDate");
			//showhint(ob,'租用日期不能为空!');
			$("#startinfo").css("display","");
			return false;
		}else if($("#rent_expectdate").val()==""){
			//var ob = document.getElementById("rent_expectdate");
			//showhint(ob,"截止日期不能为空!");
			$("#expectinfo").css("display","");
			return false;
		}else if($("#isvalids").val()=="001"){
			//var ob = document.getElementById("equipment_no");
			//showhint(ob,"设备号无效!");
			document.getElementById("noinfo").innerHTML="设备号无效!";
			return false;
		}else if($("#isvalids").val()=="003"){
			//var ob = document.getElementById("equipment_no");
			//showhint(ob,"设备已存在!");
			document.getElementById("noinfo").innerHTML="此设备已存在!";
			return false;
		}else if($("#pos_money").val()==""){
			$("#posinfo").css("display","");
			return false;
		}else if($("#cash_money").val()==""){
			$("#cashinfo").css("display","");
			return false;
		}
		
		if($("#rent_p_name").val()!="" && $("#equipment_no").val()!="" && $("#rent_p_tel").val()!="" && $("#txtDate").val()!="" && $("#rent_expectdate").val()!=""){
			//parent.$("#screen").css("display","block");
			$("#d_country").removeAttr("disabled");
			//$.messager.alert("","添加成功");
			//var a = setTimeout($('#ff').submit(),500);
			$("#forbid").val("1");
			jQuery.ajax({
				url:"add",
				data:$('#ff').serialize(),
				type:"POST",
				error:function(){
					$.messager.alert("","添加失败");
				},
				success:function(data){
					if(data=="1"){
						//$.messager.alert("","添加成功");
						alert("添加成功");
						window.location.reload(true);
					}else{
						$.messager.alert("","添加失败");
						//setTimeout(clearForm(),1000);
					}
					
				}
			});
			//parent.$("#screen").css("display","block");
		}
		
	}
	
	function clearForm(){
	/* $('#ff').form('clear'); */
		parent.frames["countents"].src = "order/add";
		
	}
	$(function(){
		$("#start").val(new Date().pattern("yyyy/MM/dd HH:mm:ss"));
		
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
	/*  $(function () {
            $("#txtDate").datetimebox({
                formatter: function (date) {
                    var y = date.getYear();
                    var m = date.getMonth() + 1;
                    var d = date.getDate();
                    var h = date.getHours();
                    var M = date.getMinutes();
                    var s = date.getSeconds();
                    return y + "/" + (m < 10 ? ("0" + m) : m) + "/" + (d < 10 ? ("0" + d) : d)+" "+(h < 10 ? ("0" + h) : h)+":"+(M < 10 ? ("0" + M) : M)+":"+(s < 10 ? ("0" + s) : s) ;
                }
            });
        }); */
        
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