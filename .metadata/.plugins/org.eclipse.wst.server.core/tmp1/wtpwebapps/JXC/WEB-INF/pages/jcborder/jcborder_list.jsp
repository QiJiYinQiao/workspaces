<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragrma","no-cache");
response.setDateHeader("Expires",0);
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta HTTP-EQUIV="pragma" CONTENT="no-cache">
	<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
	<meta HTTP-EQUIV="expires" CONTENT="0">
	<title>登陆首页</title>
	<link rel="stylesheet" type="text/css" href="../media/css/easyui.css">
	<link rel="stylesheet" type="text/css" href="../media/css/icon.css">
	<link rel="stylesheet" type="text/css" href="../media/css/demo.css">
	<script type="text/javascript" src="../media/js/WdatePicker.js"></script>
	<script type="text/javascript" src="../media/js/jquery.min.js"></script>
	<script type="text/javascript" src="../media/js/jquery.easyui.min.js"></script>
	<style type="text/css">
		.indiv input{
			border: 1px solid #95b8e7;
			border-radius: 5px;
			height: 20px;
			/* background-color:#B0C4DE; */
			font-size:16px;
			background-color:#F0F8FF;
			
		}
		td span{
			font-size: 16px;
		}
		#screens td{
			color: black;
			font-size: 16px;
		}
		#screens td input{
			color: black;
		}
		.datagrid-view{
			background-color: #F8F8FF;
			font-size: 16px;
		}
		.searchinput{
			border: 1px solid #95b8e7;border-radius: 5px;height:20px;
		}
	
		</style>
</head>
<body class="easyui-layout">
<script >
		 $(function(){
			 $('#jcbOrderTable').datagrid({
				url:'getlist?t='+new Date(),
				queryParams:{
					state:5,
				},
				pageSize: 20,
			 });
		}); 
</script>
	<div data-options="region:'center',title:'网站订单管理'">
		<div style="background-color:#F8F8FF;align:center;width: 100%;height: 140px;overflow: hidden;">
					<form method="post" style="margin-left: 1%;text-align: left;width: 100%;">
						<table style="width: 100%;">
							<tr>
							<td width="10%">JCB卡号</td><td width="15%"><input class="searchinput" name="jbc" id="jbc" value=""/></td>
							<td width="10%" > 租用人姓名</td><td width="15%"><input class="searchinput" id="name" name="name" value=""/></td>
							<td width="10%">手机号</td><td width="15%"><input class="searchinput" id="phone" name="phone" value=""/></td>
							<td width="10%">目的地</td>
							<td width="15%">
								<select id="goodname" style="border: 1px solid #95b8e7;border-radius: 5px;height: 24px;width: 140px;" name="goodname">
									<option value="" selected="selected">全部</option>
									<c:if test="${goodslist!=null }">
										<c:forEach items="${goodslist }" var="goods">
											<option value="${goods.goodname }">${goods.goodname }</option>
										</c:forEach>
									</c:if>
								</select>
							</td>
							</tr>
							<tr>
							<td>支付方式</td>
							<td><!-- 1:全款支付 2：只付租金 3：未支付  4:支付失败  -->
								<select id="payment" style="border: 1px solid #95b8e7;border-radius: 5px;height: 24px;width: 140px;" name="payment">
									<option value="" selected="selected">全部</option>
									<option value="1" >全款支付</option>
									<option value="2" >只付租金</option>
									<option value="3" >未支付</option>
									<option value="4" >支付失败</option>
								</select>
							</td>
							<td>订单状态</td>
							<td><!-- 0:订单已取消   1：自取  状态：待付款-待取货-设备已领-完成   1-2-3-4   5:预约  -->
								<select id="state" style="border: 1px solid #95b8e7;border-radius: 5px;height: 24px;width: 140px;" name="state">
									<option value="" >全部</option>
									<option value="0" >订单已取消</option>
									<option value="1" >待付款</option>
									<option value="2" >待取货</option>
									<option value="3" >设备已领</option>
									<option value="4" >完成</option>
									<option value="5" selected="selected">预约</option>
								</select>
							</td>
							<td>取货方式</td>
							<td><!-- 0:邮寄   1：自取  -->
								<select id="way" style="border: 1px solid #95b8e7;border-radius: 5px;height: 24px;width: 140px;" name="way">
									<option value="" selected="selected">全部</option>
									<option value="0" >邮寄</option>
									<option value="1" >自取</option>
								</select>
							</td>
							<td>是否电话确认</td>
							<td><!-- 0:邮寄   1：自取  -->
								<select id="isphoneconfirm" style="border: 1px solid #95b8e7;border-radius: 5px;height: 24px;width: 140px;" name="isphoneconfirm">
									<option value="" selected="selected">全部</option>
									<option value="1" >是</option>
									<option value="0" >否</option>
								</select>
							</td>
							</tr>
							<tr>
							<td width="10%">出发城市</td>
								<td width="15%">
									<select id="cfcity" style="border: 1px solid #95b8e7;border-radius: 5px;height: 24px;width: 140px;" name="cfcity">
										<option value="" selected="selected">全部</option>
										<c:if test="${citylist!=null }">
											<c:forEach items="${citylist }" var="citys">
												<option value="${citys.city }">${citys.city }</option>
											</c:forEach>
										</c:if>
									</select>
								</td>
							<td>下单日期</td><td colspan="3"><input id="createtime" name="createtime" value="" style="border: 1px solid #95b8e7;border-radius: 5px;" class="Wdate" type="text" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'createtime_end\')}'})"/>　　　--
							　　　<input id="createtime_end" name="createtime_end" value="" style="border: 1px solid #95b8e7;border-radius: 5px;" class="Wdate" type="text" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'createtime\')}'})"/> </td>
								
							</tr>
							<tr>
								<td>租用日期</td><td colspan="3"><input id="cfdate" name="cfdate" value="" style="border: 1px solid #95b8e7;border-radius: 5px;" class="Wdate" type="text" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'cfdate_end\')}'})"/>　　　--
								　　　<input id="cfdate_end" name="cfdate_end" value="" style="border: 1px solid #95b8e7;border-radius: 5px;" class="Wdate" type="text" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'cfdate\')}'})"/> </td>
								<td>返还日期</td><td colspan="3"><input id="returndate" name="returndate" value="" style="border: 1px solid #95b8e7;border-radius: 5px;" class="Wdate" type="text" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'returndate_end\')}'})"/>　　　--
								　　　<input id="returndate_end" name="returndate_end" value="" style="border: 1px solid #95b8e7;border-radius: 5px;" class="Wdate" type="text" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'returndate\')}'})"/> </td>
								</tr>
						</table>
					</form>
					<div align="right" style="margin-bottom: 5px;">
						<a onclick="sendmassage()" class="easyui-linkbutton" style="margin-right: 5px;width: 85px;height: 25px;">发送订单信息</a>
						<a onclick="exportform();" href="exportform" class="easyui-linkbutton" style="margin-right: 5px;width: 80px;height: 25px;">导出报表</a>
						<a onclick="phoneconfirm();" class="easyui-linkbutton" style="margin-right: 5px;width: 60px;height: 25px;">电话确认</a>
						<a onclick="checkdetail();" class="easyui-linkbutton" style="margin-right: 5px;width: 60px;height: 25px;">详情</a>
						<a onclick="queryOrder();" class="easyui-linkbutton" style="margin-right: 20px;width: 60px;height: 25px;">查询</a>
					</div>
				</div>
		<!-- 表单开始 -->
				<table id="jcbOrderTable" style="overflow: auto;height: 77%;" class="easyui-datagrid" 
				data-options="rownumbers:true,pagination:true,onDblClickRow:doubleclick,singleSelect:true,method:'post',showFooter: true,remoteSort:false,multiSort:false">
				<thead>
				<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'goodname',width:120,align:'center'">目的地</th>
				<th data-options="field:'name',width:75,align:'center'">姓名</th>
				<th data-options="field:'phone',width:110,align:'center'">手机号</th>
				<!-- <th data-options="field:'cfcity',width:80,align:'center'">城市</th>
				<th data-options="field:'qhaddress',width:280,align:'center'">柜台</th> -->
				<th data-options="field:'qhwangdian',width:330,align:'left'">取货网点</th>
				<th data-options="field:'cfdate',width:90,align:'center',sortable:true">租用日期</th>
				<th data-options="field:'returndate',width:90,align:'center',sortable:true">返还日期</th>
				<th data-options="field:'rent',width:65,align:'center'">租金</th>
				<th data-options="field:'ordersyajin',width:65,align:'center'">押金</th>
				<th data-options="field:'allprice',width:65,align:'center'">总价</th>
				<th data-options="field:'jbc',width:140,align:'center'">JCB卡号</th>
				<th data-options="field:'cardtype',width:120,align:'center'">发卡行</th>
				<th data-options="field:'isphoneconfirm',width:80,align:'center'">是否电话确认</th>
				<th data-options="field:'state',width:80,align:'center'">订单状态</th>
				<th data-options="field:'createtime',width:110,align:'center',sortable:true">下单日期</th>
				<th data-options="field:'payment',width:70,align:'center'">支付方式</th>
				<th data-options="field:'way',width:70,align:'center'">取货方式</th>
				<th data-options="field:'username',width:110,align:'center'">用户名</th>
				<th data-options="field:'passport',width:100,align:'center'">护照号</th>
				<th data-options="field:'remark',width:200,align:'center'">顾客留言</th>
				<th data-options="field:'phoneremark',width:200,align:'center'">电话备注</th>
				</tr>
				</thead>
				</table>
		<!-- 表单结束 -->
	</div>
		 <div id="dlg"  class="easyui-dialog" modal="true" closed="true" title="网站订单详情"  style="width:850px;height:500px;overflow:hidden;">
			<iframe id="jcbdetail" name="contents" src="" width="100%" height="100%" frameborder="0" style="overflow:hidden"></iframe>
		</div>
		
		<div id="send" class="easyui-dialog" class="easyui-dialog" modal="true" closed="true" title="发送短信"  style="width:570px;height:370px;overflow:hidden;">
			<iframe id="message" name="messages" src="" width="100%" height="100%" frameborder="0" style="overflow:hidden"></iframe>
		</div>
		<script type="text/javascript">
			 $(function(){
				 var pager = $('#jcbOrderTable').datagrid().datagrid('getPager'); // get the pager of datagrid
				 $(pager).pagination({  
					 	beforePageText: '第',//页数文本框前显示的汉字  
				        afterPageText: '页    共 {pages} 页',  
				        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
				        pageList:[20,50,100,200],
				    });  
			 });
		
			function queryOrder(){
				var jbc = $("#jbc").val();
				var name = $("#name").val();
				var phone = $("#phone").val();
				var goodname = $("#goodname").val();
				var cfdate = $("#cfdate").val();
				var cfdate_end = $("#cfdate_end").val();
				var returndate = $("#returndate").val();
				var returndate_end = $("#returndate_end").val();
				var payment = $("#payment").val();
				var state = $("#state").val();
				var way = $("#way").val();
				var createtime = $("#createtime").val();
				var createtime_end = $("#createtime_end").val();
				var isphoneconfirm = $("#isphoneconfirm").val();
				var cfcity = $("#cfcity").val();
				
				$("#jcbOrderTable").datagrid({
					url:"queryByCondition",
					queryParams:{
						jbc:jbc,name:name,phone:phone,goodname:goodname,cfdate:cfdate,cfdate_end:cfdate_end,returndate:returndate,returndate_end:returndate_end,
						payment:payment,state:state,way:way,createtime:createtime,createtime_end:createtime_end,isphoneconfirm:isphoneconfirm,cfcity:cfcity,
					},
					pageSize: 20,
					pageList:[20,50,100,200],
				})
				 var pager = $('#jcbOrderTable').datagrid().datagrid('getPager'); // get the pager of datagrid
				 $(pager).pagination({  
					 	beforePageText: '第',//页数文本框前显示的汉字  
				        afterPageText: '页    共 {pages} 页',  
				        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
				        pageList:[20,50,100,200],
				    });  
			}
		</script>
		<script type="text/javascript">
			/* $(function(){
				$('#dlg').dialog('close');
				});
			function submits(){
				//$("#queryBy").submit();
				var order_num = $('#order_num').val()+"";
				var rent_p_name = $('#rent_p_names').val()+"";
				var rent_p_tel = $('#rent_p_tels').val()+"";
				var equipment_no = $('#equipment_nos').val()+"";
				var rent_begindate = $('#rent_begindates').val()+"";
				var rent_expectdate = $('#rent_expectdates').val()+"";
				var rent_enddate = $('#rent_enddates').val()+"";
				var d_country = $('#d_countrys').val()+"";
				var payment = $('#payments').val()+"";
				var order_state = $("#order_state").val()+"";
				//alert("queryByCondition?id="+id+"&rent_p_name="+rent_p_name+"&rent_p_tel="+rent_p_tel+"&equipment_no="+equipment_no+"&rent_begindate="+rent_begindate+"&rent_expectdate="+rent_expectdate+"&rent_enddate="+rent_enddate+"&d_country="+d_country+"&payment="+payment);
				 $('#dg').datagrid({
					url:"queryByCondition?order_num="+order_num+"&rent_p_name="+rent_p_name+"&rent_p_tel="+rent_p_tel+"&equipment_no="+equipment_no+"&rent_begindate="+rent_begindate+"&rent_expectdate="+rent_expectdate+"&rent_enddate="+rent_enddate+"&d_country="+d_country+"&payment="+payment+"&order_state="+order_state
					});  
				 var pager = $('#dg').datagrid().datagrid('getPager'); // get the pager of datagrid
				 $(pager).pagination({  
					 	beforePageText: '第',//页数文本框前显示的汉字  
				        afterPageText: '页    共 {pages} 页',  
				        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
				        pageList:[10,20,50,100,200],
				    });  
			} */
		</script>
	<!-- 列表弹出框结束 -->
	 <script type="text/javascript">
		 /*
		function phoneconfirm(){
			  var field = $("#dg").datagrid("getSelected");
			  if(field==null){
				  alert("请先选择一条数据");
			  }
			  document.getElementById("detail").src="detail?order_num="+field.order_num;
			  $('#dlg').dialog('open');
		}
		
		function doubleclick(index,field){
			 // parent.menus.window.document.getElementBmenu_shadow
			  var r = $("#dg").datagrid("getRows",index);
			  document.getElementById("detail").src="detail?order_num="+field.order_num;
			  $('#dlg').dialog('open');
		}
		
		function searchs(){
			  var r = $("#dg").datagrid('getSelected');	// 得到选择行
			  var equipmentno = r.equipment_no;
			  window.location = "search?equipment_no="+equipmentno+"&rent_p_tel="+rentptel;
		} */
		
		
		function sendmassage(){
			var field = $("#jcbOrderTable").datagrid("getSelected");
			  if(field==null){
				  alert("请先选择一条订单 ");
			  }
			  var name = field.name;
			  var phone = field.phone;
			  var cfdate = field.cfdate;
			  var qhaddress = field.cfcity+field.qhaddress;
			  var jcbNo = field.jbc.substring(10, 17);
			  var content = name+"，您好 。您在龙在天下预订的WIFI订单已确认，请您在登机前到"+qhaddress+"处领取，并随身带好您的JCB卡，以备客服做好相关登记。祝您旅途愉快！";
			  document.getElementById("message").src="jcbordersend?phone="+field.phone+"&content="+content;
			 /*  frames['messages'].document.getElementById("phone").value=field.phone;
			  frames['messages'].document.getElementById("content").value=field.content; */
			  $('#send').dialog('open');
		}
		
		function phoneconfirm(){
			  var field = $("#jcbOrderTable").datagrid("getSelected");
			  if(field==null){
				  alert("请先选择一条数据");
				  return false;
			  }
			  //document.getElementById("jcbdetail").src="jcborderdetail?name="+field.name+"&phone="+field.phone;
			  document.getElementById("jcbdetail").src="jcborderdeconfirm?id="+field.orderid;
			  $('#dlg').dialog('open');
		} 
		
		function checkdetail(){
			 var field = $("#jcbOrderTable").datagrid("getSelected");
			  if(field==null){
				  alert("请先选择一条数据");
				  return false;
			  }
			  //document.getElementById("jcbdetail").src="jcborderdetail?name="+field.name+"&phone="+field.phone;
			  document.getElementById("jcbdetail").src="jcborderdetail?id="+field.orderid;
			  $('#dlg').dialog('open');
		}
		
		function doubleclick(index,field){
			//document.getElementById("jcbdetail").src="jcborderdetail?name="+field.name+"&phone="+field.phone;
			document.getElementById("jcbdetail").src="jcborderdetail?id="+field.orderid;
			$('#dlg').dialog('open');
		}
		
		
		function exportform(){
			var jbc = $("#jbc").val();
			var name = $("#name").val();
			var phone = $("#phone").val();
			var goodname = $("#goodname").val();
			var cfdate = $("#cfdate").val();
			var cfdate_end = $("#cfdate_end").val();
			var returndate = $("#returndate").val();
			var returndate_end = $("#returndate_end").val();
			var payment = $("#payment").val();
			var state = $("#state").val();
			var way = $("#way").val();
			var createtime = $("#createtime").val();
			var createtime_end = $("#createtime_end").val();
			var isphoneconfirm = $("#isphoneconfirm").val();
			var cfcity = $("#cfcity").val();
			
			jQuery.ajax({
				url:'exportform',
				type:'post',
				data:{jbc:jbc,name:name,phone:phone,goodname:goodname,cfdate:cfdate,cfdate_end:cfdate_end,returndate:returndate,returndate_end:returndate_end,payment:payment,state:state,way:way,createtime:createtime,createtime_end:createtime_end,isphoneconfirm:isphoneconfirm,cfcity:cfcity,},
				async:false,
				error:function(){},
				success:function(){
				}
			});
		}
		
	</script>
</body>
 
</html>