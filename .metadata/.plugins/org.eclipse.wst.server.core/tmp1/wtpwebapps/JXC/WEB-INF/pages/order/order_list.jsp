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
		/* $(function(){
			$.ajax({
				type: "GET",
				url: "static/cache.js",
				dataType: "text",
				cache:false,
				ifModified :true
				});
		}); */

		function submitForm(){
		$('#ff').submit();
		}
		function clearForm(){
		$('#ff').form('clear');
		}
		$(function(){
			$('#dg').datagrid('hideColumn','remark');			
			$('#dg').datagrid('hideColumn','rent_p_idnumber');	
			$('#dg').datagrid('hideColumn','rent_p_passportno');	
		});
		 $(function(){
			 var types = $("#types").val();
			 var t = new Date();
			 $('#dg').datagrid({
				url:'getlist',
				queryParams:{types:types,t:t}
			 });
		}); 
</script>
<!-- 	<div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;padding:10px">龙在天下</div>
	<div data-options="region:'west',split:true,title:'菜单栏'" style="width:150px;padding:10px;">
		<div id="orders">订单管理
			<div style="display:none;" id="order">
				<a href="order/add">增加订单</a>
			</div>
		</div>
		<dl>
			<dt>人员管理</dt>
			<dd>人员列表</dd>
		</dl>
		<dl>
			<dt>柜台管理</dt>
			<dd>柜台列表</dd>
		</dl>
		<dl>
			<dt>设备管理</dt>
			<dd>设备管理列表</dd>
		</dl>
		<dl>
			<dt>国际漫游卡</dt>
			<dd>国际漫游卡列表</dd>
		</dl>
		
	</div>
	<div data-options="region:'east',split:true,collapsed:true,title:'通知'" style="width:100px;padding:10px;">通知</div>
	<div data-options="region:'south',border:false" style="height:5px;background:#A9FACD;padding:10px;"></div> -->
	<div data-options="region:'center',title:'订单管理'">
		<input id="types" value="${types }" style="display:none;">
		<div style="background-color:#F8F8FF;align:center;width: 100%;height: 110px;overflow: hidden;">
					<!-- <script type="text/javascript">  
					    function qq(value,name){   
					        alert(value+":"+name)   
					    }   
					</script>  
					  
					<input id="ss" class="easyui-searchbox" style="width:300px"  
					        data-options="searcher:qq,prompt:'请输入查询条件',menu:'#mm'"></input>  
					           
					<div id="mm" style="width:120px">  
					    <div data-options="name:'id',iconCls:'icon-ok'">订单号</div>
					    <div data-options="name:'rent_p_name'">租用人姓名</div>
					    <div data-options="name:'rent_p_tel'">手机号</div>
					    <div data-options="name:'equipment_no'">设备号</div>
					    <div data-options="name:'rent_begindate'">租用日期</div>
					    <div data-options="name:'rent_enddate'">返还日期</div>
					    <div data-options="name:'rent_p_nationality'">国籍</div>
					    <div data-options="name:'d_country'">目的地</div>
					</div>  
					<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true"></a> -->
					<!-- <a href="#" onclick="searchs();" class="easyui-linkbutton" iconCls="icon-save" plain="true"></a>
					<a href="add" class="easyui-linkbutton" iconCls="icon-add" plain="true"></a> -->
					<form id="queryBy" action="queryByCondition" method="post" style="margin-left: 1%;text-align: left;width: 100%;">
						<table style="width: 100%;">
							<tr>
							<td width="10%">订单号</td><td width="15%"><input class="searchinput" name="order_num" id="order_num" value=""/></td>
							<td width="10%" > 租用人姓名</td><td width="15%"><input class="searchinput" id="rent_p_names" name="rent_p_name" value=""/></td>
							<td width="10%">手机号</td><td width="15%"><input class="searchinput" id="rent_p_tels" name="rent_p_tel" value=""/></td>
							<td width="10%">设备号</td><td width="15%"><input class="searchinput" id="equipment_nos" name="equipment_no" value=""/></td>
							</tr>
							<tr>
							<td>租用日期</td><td><input id="rent_begindates" name="rent_begindate" value="" style="border: 1px solid #95b8e7;border-radius: 5px;" class="Wdate" type="text" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy-MM-dd'})"/></td>
							<td>预计返还日期</td><td><input id="rent_expectdates" name="rent_expectdate" value="" style="border: 1px solid #95b8e7;border-radius: 5px;" class="Wdate" type="text" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy-MM-dd'})"/> </td>
							<td>实际返还日期</td><td><input id="rent_enddates" name="rent_enddate" value="" style="border: 1px solid #95b8e7;border-radius: 5px;" class="Wdate" type="text" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy-MM-dd'})"/> </td>
							<td> 目的地</td>
							<td><!-- <input id="d_countrys" name="d_country" value=""/> -->
								<select id="d_countrys" style="border: 1px solid #95b8e7;border-radius: 5px;height: 24px;width: 140px;" name="countrys">
									<option value="" selected="selected">全部</option>
									<c:if test="${country!=null }">
										<c:forEach items="${country }" var="countrys">
											<option value="${countrys.ITEM_VALUE }">${countrys.ITEM_TEXT }</option>
										</c:forEach>
									</c:if>
								</select>
							</td>
							
							</tr>
							<tr>
							<td width="10%">支付方式</td>
							<td><select id="payments" style="border: 1px solid #95b8e7;border-radius: 5px;height: 24px;width: 140px;" name="payment">
								<option value="" selected="selected">全部</option>
								<c:if test="${paymentlist!=null }">
									<c:forEach items="${paymentlist }" var="payment">
										<option value="${payment.ITEM_VALUE }">${payment.ITEM_TEXT }</option>
									</c:forEach>
								</c:if>
							</select></td>
							
								<td width="10%">状态</td>
								<td><select id="order_state" style="border: 1px solid #95b8e7;border-radius: 5px;height: 24px;width: 140px;" name="order_state">
									<option value="" selected="selected">全部</option>
									<option value="0001">进行中</option>
									<option value="0002">已完成</option>
								</select></td>
							</tr>
						</table>
					</form>
					<div align="right" style="margin-bottom: 5px;">
						<a onclick="submits();" class="easyui-linkbutton" style="margin-right: 5px;width: 60px;height: 25px;">查询</a>
						<a onclick="checkdetail();" class="easyui-linkbutton" style="margin-right: 20px;width: 60px;height: 25px;">详情</a>
						<!-- <button onclick="submits();" style="margin-right: 2%;width: 80px;height: 25px;">查询</button>
						<button onclick="checkdetail();" style="margin-right: 2%;width: 80px;height: 25px;">查看详情</button> -->
					</div>
				</div>
		<!-- 表单开始 -->
				<table id="dg" style="overflow: auto;height: 82%;" class="easyui-datagrid" 
				data-options="rownumbers:true,pagination:true,singleSelect:true,method:'post',onDblClickRow:doubleclick, remoteSort:false,multiSort:false, showFooter: true">
				<!--  class="easyui-datagrid" title="DataGrid with Toolbar" style="width:700px;height:250px"
					  data-options="rownumbers:true,pagination:true,singleSelect:false,url:'getlist',method:'get',toolbar:toolbar" -->
				
				<thead>
				<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'order_num',width:150,align:'center'">订单号</th>
				<th data-options="field:'rent_p_name',width:80,align:'center'">姓名</th>
				<th data-options="field:'rent_p_tel',width:110,align:'center'">手机号</th>
				<th data-options="field:'equipment_no',width:120,align:'center'">设备号</th>
				
				<th data-options="field:'order_state',width:80,align:'center',styler: function(value,row,index){
								if (value=='已完成'){
									return 'background-color:#F8F8FF;color:green;';
								}else{
									return 'color:red';
								}
							}">订单状态</th>
				<th data-options="field:'rent_begindate',width:170,align:'center',sortable:true">租用日期</th>
				<th data-options="field:'rent_expectdate',width:100,align:'center',sortable:true">预计返还日期</th>
				<th data-options="field:'rent_enddate',width:170,align:'center',sortable:true">实际返还日期</th>
				<th data-options="field:'cost_rent_3g',width:80,align:'center'">租赁费用</th>
				<th data-options="field:'upfront_sum',width:80,align:'center'">预收总金额</th>
				<th data-options="field:'cost_sum',width:80,align:'center'">实际结算金额</th>
				<th data-options="field:'cost_return_discount',width:112,align:'center'">退还押金费用</th>
				
				<th data-options="field:'cost_discount',width:80,align:'center',styler: function(value,row,index){
								if (value!=null){
									return 'background-color:#F8F8FF;color:red;';
								}
							}">优惠费用</th>
				
				<th data-options="field:'d_country',width:80,align:'center'">目的地</th>
				<!-- <th data-options="field:'s_country',width:80,align:'center'">出发地</th> -->
				<!-- <th data-options="field:'rent_p_nationality',width:80,align:'center'">国籍</th> -->
				
				<th data-options="field:'payment',width:80,align:'center'">支付方式</th>
				<th data-options="field:'counterid',width:80,align:'center'">柜台</th>
				<th data-options="field:'position_level',width:80,align:'center'">舱位等级</th>
				<th data-options="field:'modify_time',width:170,align:'center',sortable:true">日期</th>
				<th data-options="field:'modify_user',width:80,align:'center'">操作员</th>
				<th data-options="field:'remark',align:'center'">备注</th>
				<th data-options="field:'rent_p_idnumber',align:'center'">身份证号</th>
				<th data-options="field:'rent_p_passportno',align:'center'">护照号</th>
				</tr>
				</thead>
				<tbody>
					<%-- <c:if test="${ss!=null}">
						<c:forEach items="${ss}" var="list">
							<tr><td>${list.id}</td></tr>
						</c:forEach>
					</c:if> --%>
					<tr><td></td></tr>
					<tr><td></td></tr>
					<tr><td></td></tr>
				</tbody>
				</table>
				<!-- <div style="margin:10px 0; display: none;" >
				<span>Selection Mode: </span>
				<select onchange="$('#dg').datagrid({singleSelect:(this.value==0)})">
				<option value="0">Single Row</option>
				<option value="1">Multiple Rows</option>
				</select><br/>
				SelectOnCheck: <input type="checkbox" checked onchange="$('#dg').datagrid({selectOnCheck:$(this).is(':checked')})"><br/>
				CheckOnSelect: <input type="checkbox" checked onchange="$('#dg').datagrid({checkOnSelect:$(this).is(':checked')})">
			  </div> -->
		<!-- 表单结束 -->
	</div>
	<!-- 列表弹出框开始 -->
		<!-- <div id="screens" style="display: none; z-index: 1000; top: 0px; left: 0px; position: fixed; height: 100%; width: 100%;opacity:0.95; background-color:#fff;overflow: auto;">
			background:#87CEFA;
			<iframe id="detail" name="contents" src="" width="100%" height="100%" frameborder="0" style="overflow:hide;"></iframe>
		</div> -->
		 <div id="dlg"  class="easyui-dialog" modal="true" title="订单详情"  style="width:880px;height:450px;overflow:hidden;">
			<iframe id="detail" name="contents" src="" width="100%" height="100%" frameborder="0" style="overflow:hidden"></iframe>
		</div>
		
		<script type="text/javascript">
			$(function(){
				$('#dlg').dialog('close');
				//var rows = $("#dg").datagrid('getRows');
			/* 	$('#dg').datagrid({
					columns:[[
						{field:'cost_discount', width:80, align:'right',
							styler: function(value,row,index){
								if (value==null){
									return 'background-color:#ffee00;color:red;';
								}
							}
						}
					]]
				}); */
				});
			function submits(){
				//$("#queryBy").submit();
				var order_num = $('#order_num').val();
				var rent_p_name = $('#rent_p_names').val();
				var rent_p_tel = $('#rent_p_tels').val();
				var equipment_no = $('#equipment_nos').val();
				var rent_begindate = $('#rent_begindates').val();
				var rent_expectdate = $('#rent_expectdates').val();
				var rent_enddate = $('#rent_enddates').val();
				var d_country = $('#d_countrys').val();
				var payment = $('#payments').val();
				var order_state = $("#order_state").val();
				//alert("queryByCondition?id="+id+"&rent_p_name="+rent_p_name+"&rent_p_tel="+rent_p_tel+"&equipment_no="+equipment_no+"&rent_begindate="+rent_begindate+"&rent_expectdate="+rent_expectdate+"&rent_enddate="+rent_enddate+"&d_country="+d_country+"&payment="+payment);
				 $('#dg').datagrid({
					//url:"queryByCondition?order_num="+order_num+"&rent_p_name="+rent_p_name+"&rent_p_tel="+rent_p_tel+"&equipment_no="+equipment_no+"&rent_begindate="+rent_begindate+"&rent_expectdate="+rent_expectdate+"&rent_enddate="+rent_enddate+"&d_country="+d_country+"&payment="+payment+"&order_state="+order_state
					url:"queryByCondition",
					queryParams:{
						order_num:order_num,rent_p_name:rent_p_name,rent_p_tel:rent_p_tel,equipment_no:equipment_no,rent_begindate:rent_begindate,
						rent_expectdate:rent_expectdate,rent_enddate:rent_enddate,d_country:d_country,payment:payment,order_state:order_state
						
					},
					 pageList:[10,20,50,100,200],
				 });  
				 var pager = $('#dg').datagrid().datagrid('getPager'); // get the pager of datagrid
				 $(pager).pagination({  
					 	beforePageText: '第',//页数文本框前显示的汉字  
				        afterPageText: '页    共 {pages} 页',  
				        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
				        pageList:[10,20,50,100,200],
				        /*onBeforeRefresh:function(){ 
				            $(this).pagination('loading'); 
				            alert('before refresh'); 
				            $(this).pagination('loaded'); 
				        }*/ 
				    });  
			}
		</script>
	<!-- 列表弹出框结束 -->
	 <script type="text/javascript">
		$(function(){
		var pager = $('#dg').datagrid().datagrid('getPager'); // get the pager of datagrid
		 $(pager).pagination({  
			 	beforePageText: '第',//页数文本框前显示的汉字  
		        afterPageText: '页    共 {pages} 页',  
		        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
		        pageList:[10,20,50,100,200],
		        /* buttons:[{
					iconCls:'icon-search',
					handler:function(){
						 var field = $("#dg").datagrid("getSelected");
						  if(field==null){
							  alert("请先选择一条数据");
						  }
						  document.getElementById("detail").src="detail?no="+field.no;
					      $('#checkdetail').dialog('open');
					}
				},{
					iconCls:'icon-add',
					handler:function(){
						$("#adds").dialog('open');
					}
				},{
					iconCls:'icon-edit',
					handler:function(){
						var field = $("#dg").datagrid("getSelected");
						if(field==null){
							alert("请先选择一条数据");
						}
						document.getElementById("edit").src="edit?no="+field.no;
						$("#edits").dialog("open");
					}
				}] */
		    });  
		
		});
		
		function checkdetail(){
			  var field = $("#dg").datagrid("getSelected");
			  if(field==null){
				  alert("请先选择一条数据");
			  }
			  document.getElementById("detail").src="detail?order_num="+field.order_num;
			  $('#dlg').dialog('open');
			  /* $('#dlg').dialog('open');
			  var field = $("#dg").datagrid("getSelected");   
			  //alert(field);
			  if(field==null){
				  alert("请先选择一条数据");
			  }
			  //从后台获取数据方法
			  $("#order_num").val(field.order_num);
			  $("#rent_p_name").val(field.rent_p_name);
			  $("#rent_p_tel").val(field.rent_p_tel);
			  $("#equipment_no").val(field.equipment_no);
			  
			  $("#rent_begindate").val(field.rent_begindate);
			  $("#rent_enddate").val(field.rent_enddate);
			  $("#d_country").val(field.d_country);
			  $("#cost_rent_3g").val(field.cost_rent_3g);
			  
			  $("#cost_return").val(field.cost_return);
			  $("#upfront_sum").val(field.upfront_sum);
			  $("#rent_p_idnumber").val(field.rent_p_idnumber);
			  $("#rent_p_passportno").val(field.rent_p_passportno);
			  
			  $("#rent_p_nationality").val(field.rent_p_nationality);
			  $("#s_country").val(field.s_country);
			  $("#create_time").val(field.create_time);
			  $("#create_user").val(field.create_user);
			  $("#remark").val(field.remark);
			  $("#screens").fadeIn("1500"); */
		}
		
		function doubleclick(index,field){
			 // parent.menus.window.document.getElementBmenu_shadow
			  var r = $("#dg").datagrid("getRows",index);
			  document.getElementById("detail").src="detail?order_num="+field.order_num;
			  $('#dlg').dialog('open');
			  //alert(field.equipment_no);
			  //alert(r[0].rent_p_tel);
			  //searchs();
			  
			  //从后台获取数据方法
			/*   $("#order_num").val(field.order_num);
			  $("#rent_p_name").val(field.rent_p_name);
			  $("#rent_p_tel").val(field.rent_p_tel);
			  $("#equipment_no").val(field.equipment_no);
			  
			  $("#rent_begindate").val(field.rent_begindate);
			  $("#rent_enddate").val(field.rent_enddate);
			  $("#d_country").val(field.d_country);
			  $("#cost_rent_3g").val(field.cost_rent_3g);
			  
			  $("#cost_return").val(field.cost_return);
			  $("#upfront_sum").val(field.upfront_sum);
			  $("#rent_p_idnumber").val(field.rent_p_idnumber);
			  $("#rent_p_passportno").val(field.rent_p_passportno);
			  
			  $("#rent_p_nationality").val(field.rent_p_nationality);
			  $("#s_country").val(field.s_country);
			  $("#create_time").val(field.create_time);
			  $("#create_user").val(field.create_user);
			  $("#remark").val(field.remark); */
			  //alert(document.getElementById("detail").src);
			 // $("#screens").fadeIn("1500");
			 
			 /*  alert(r);
			  alert(r.rent_p_tel); 
			  window.location = "search?equipment_no="+equipmentno+"&rent_p_tel="+rentptel; */
		}
		
		function searchs(){
			  //alert("ok");
			  var r = $("#dg").datagrid('getSelected');	// 得到选择行
			  //alert(r.equipment_no);
			  //alert(r.rent_p_tel);
			  var equipmentno = r.equipment_no;
			  var rentptel = r.rent_p_tel;
			 /*  alert(r);
			  alert(r.rent_p_tel); */
			  window.location = "search?equipment_no="+equipmentno+"&rent_p_tel="+rentptel;
			/* for (var i = 0; rows && i < rows.length; i++) {
			     var row = rows[i];
			     var index = $("#dbgrid").datagrid("getRowIndex", row);    // 获取该行的索引
				 alert(index);
			 }   */
		}
	</script>
</body>
 
</html>