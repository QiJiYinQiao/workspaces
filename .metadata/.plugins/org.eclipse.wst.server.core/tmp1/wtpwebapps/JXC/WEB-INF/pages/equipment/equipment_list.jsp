<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>设备列表</title>
	<link rel="stylesheet" type="text/css" href="../media/css/easyui.css">
	<link rel="stylesheet" type="text/css" href="../media/css/icon.css">
	<link rel="stylesheet" type="text/css" href="../media/css/demo.css">
	<script type="text/javascript" src="../media/js/jquery.min.js"></script>
	<script type="text/javascript" src="../media/js/jquery.easyui.min.js"></script>
</head>
<body class="easyui-layout">
		<script type="text/javascript">
			$(function(){
				$("#dg").datagrid({
					url:"getlist?t="+new Date(),
				});
			});
		</script>
		<!-- 表单开始 data-options="region:'center',title:'设备列表'" scrolling="no" -->
		<div style="overflow:hidden;background-color:#F8F8FF;height: 100%;">
				<div style="height: 30px;background-color: rgb(248,248,255);text-align: right;">
					<form id="searchform" action="search" method="post">
					<%-- <span style="color:red;font-size:16px;">${message }</span> --%>
					设备号　<input class="easyui-textbox" style="width:15%;" type="text" id="no" name="no" value="${no }">
					<!-- <input class="easyui-checkbox" style="width:120px;font-size: 16px;" type="text" id="equipment_state" name="equipment_state" value="${equipment_state}"> -->
					　状　态　
					<select id="equipment_state" style="border: 1px solid #95b8e7;border-radius: 5px;height: 24px;width:15%;" name="equipment_state">
								<option value="">全部</option>
								<option value="0001"  selected="selected">可用</option>
								<option value="0002">已租</option>
					</select>
					　国　家　
					<select id="countrys" style="border: 1px solid #95b8e7;border-radius: 5px;height: 24px;width: 15%;" name="countrys">
					<%-- <input class="easyui-textbox" style="width:120px;font-size: 16px;" type="text" id="countrys" name="countrys" value="${countrys}"> --%>
						<option value="" selected="selected">全部</option>
						<c:if test="${country!=null }">
							<c:forEach items="${country }" var="countrys">
								<option value="${countrys.ITEM_VALUE }">${countrys.ITEM_TEXT }</option>
							</c:forEach>
						</c:if>
					</select>
					　柜　台　
					<!-- <input class="easyui-textbox" style="width:120px;font-size: 16px;" type="text" id="counterid" name="counterid" value="">${counters.counter_code} -->
					<select id="countercode" name="countercode"  style="border: 1px solid #95b8e7;border-radius: 5px;height: 24px;width: 15%;" >
						<c:if test="${gradetype=='0000' }">
							<option value="" selected="selected">全部</option>
						</c:if>
						<c:if test="${gradetype!='0000' }">
							<option value="${counters.counter_code }" selected="selected">${counters.name }</option>
							<option value="" >全部</option>
						</c:if>
						<!-- <option value="" >全部</option> -->
						<c:if test="${counterlist!=null }">
							<c:forEach items="${counterlist }" var="counterlists">
								<option value="${counterlists.counter_code }">${counterlists.name }</option>
							</c:forEach>
						</c:if>
					</select>　
				    <a onclick="searchs();" class="easyui-linkbutton" iconCls="icon-search" style="font-size:16px;height: 25px;width: 6%;" >查询</a>
					<a onclick="sub();" class="easyui-linkbutton"  style="font-size:16px;width: 6%;" >确认</a>
					<!-- <button onclick="searchs()" style="width:6%;height:25px;">查询</button>
					<button onclick="sub()" style="width:6%;height:25px;">确认</button> -->
					</form>
					</div>
				<table id="dg" style="height: 90%;font-size: 16px;overflow: hidden;" class="easyui-datagrid"  
				data-options="rownumbers:true,pagination:true,singleSelect:true,method:'get',onDblClickRow:sub,remoteSort:false,multiSort:true">
				<thead>
				<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'no',width:160,align:'center'">设备编号</th>
				<!-- <th data-options="field:'stock_date',width:170,align:'center'">进货日期</th>
				<th data-options="field:'scrap_date',width:170,align:'center'">报废日期</th>
				<th data-options="field:'is_valid',width:80,align:'center'">是否有效</th>
				<th data-options="field:'create_user',width:80,align:'center'">创建人</th>
				<th data-options="field:'create_time',width:170,align:'center'">创建时间</th>
				<th data-options="field:'modify_user',width:80,align:'center'">修改人</th>
				<th data-options="field:'modify_time',width:170,align:'center'">修改时间</th> -->
				<th data-options="field:'d_country',width:100,align:'center'">国家</th>
				<th data-options="field:'day_rent',width:100,align:'center'">日租金</th>
				<th data-options="field:'deposit',width:100,align:'center'">押金</th>
				<th data-options="field:'rent_begindate',width:179,align:'center',sortable:true">租用日期</th>
				<!-- <th data-options="field:'rent_expectdate',width:170,align:'center'">预计返还日期</th> -->
				<th data-options="field:'rent_enddate',width:190,align:'center',sortable:true">返还日期</th>
				<th data-options="field:'counterid',width:100,align:'center'">柜台名称</th>
				<!-- <th data-options="field:'total_rent',width:80,align:'center'">设备租金</th>
				<th data-options="field:'equipment_tupe',width:80,align:'center'">设备类型</th>
				<th data-options="field:'remark',width:200,align:'center'">备注</th> -->
				
				</tr>
				</thead>
				</table>
				<!-- <div style="margin:10px 0;">
				<span>Selection Mode: </span>
				<select onchange="$('#dg').datagrid({singleSelect:(this.value==0)})">
				<option value="0">Single Row</option>
				<option value="1">Multiple Rows</option>
				</select><br/>
				SelectOnCheck: <input type="checkbox" checked onchange="$('#dg').datagrid({selectOnCheck:$(this).is(':checked')})"><br/>
				CheckOnSelect: <input type="checkbox" checked onchange="$('#dg').datagrid({checkOnSelect:$(this).is(':checked')})">
			  </div> -->
		<!-- 表单结束 -->
				<!-- 记录柜台类型  上海或者其它 -->
				<input id="countertype" value="${countertype }" type="hidden">
		</div>
	<!-- </div> -->
	 <script type="text/javascript">
		$(function(){
		var pager = $('#dg').datagrid().datagrid('getPager'); // get the pager of datagrid
		});
		/* 查询 */
		function searchs(){
			//alert("ok");
			var no = $("#no").val();
			var countercode = $("#countercode").val();
			var countrys = $("#countrys").val();
			var equipment_state = $("#equipment_state").val();
			//alert(no+":"+counterid+":"+countrys);
			$('#dg').datagrid({
				url:"query?no="+no+"&countercode="+countercode+"&countrys="+countrys+"&equipment_state="+equipment_state,
				});
			var pager = $('#dg').datagrid().datagrid('getPager'); // get the pager of datagrid
			 $(pager).pagination({  
				 	beforePageText: '第',//页数文本框前显示的汉字  
			        afterPageText: '页    共 {pages} 页',  
			        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
			        /*onBeforeRefresh:function(){ 
			            $(this).pagination('loading'); 
			            alert('before refresh'); 
			            $(this).pagination('loaded'); 
			        }*/ 
			    });  
		}
		/* 确定按钮 */
		function sub(){
			//alert(parent.document.getElementById("detail"));
			//alert(rows.no);
			var rows = $("#dg").datagrid("getSelected"); 
			if(rows==null){
				alert("请先选择一条数据!");
				return false;
			}
			var countryname = rows.d_country;
			var countertype = $("#countertype").val();
			if(countertype==1){
				/* jQuery.ajax({
					url:"queryDepositByNo",
					data:{countryname:countryname},
					type:"post",
					error:function(){},
					success:function(data){ */
					parent.document.getElementById("equipment_no").value=rows.no;
					parent.document.getElementById("d_country").value=rows.d_country;
					parent.document.getElementById("deposit").value=rows.deposit;
					parent.document.getElementById("day_rent").value=rows.day_rent;
					parent.document.getElementById("noinfo").innerHTML="";
					parent.discountchange();
					parent.count_rent();
					parent.$('#dlg').dialog('close');
				/* 	}
				}); */
			}else{
				var equipmentno = rows.no;
				jQuery.ajax({
					url:"querydayend",
					data:{equipmentno:equipmentno},
					type:"post",
					error:function(){},
					success:function(data){
						if(data=="0"){
							alert("此设备绑定的SIM卡已过期,请重新选择!")
							return false;
						}else{
							if(data==2){
								parent.document.getElementById("simtype").value=1;
							}
							parent.document.getElementById("equipment_no").value=rows.no;
							parent.document.getElementById("noinfo").innerHTML="";
							var eqptno = rows.no;
							parent.equipment_blur(eqptno);
							parent.document.getElementById("d_country").value=rows.d_country;
				        	parent.document.getElementById("deposit").value=rows.deposit;
				        	parent.document.getElementById("day_rent").value=rows.day_rent;
				        	var data = parent.document.getElementById("day_rent").value;
					    	var days = parent.document.getElementById("date_diff").value;
				        	var cost_rent = days*data;
				     		var cost_return = parent.document.getElementById("deposit").value;
				     		cost_rent = Math.round(cost_rent*100)/100;
				     		parent.document.getElementById("cost_rent").value = cost_rent;
				        	var upfront_sum = parseFloat(cost_rent)+parseFloat(cost_return);
				        	upfront_sum = Math.round(upfront_sum*100)/100;
				        	parent.document.getElementById("upfront_sum").value = upfront_sum;
						    parent.$('#dlg').dialog('close');
						}
					}
				});
			}
			//parent.document.getElementById("equipment_no").value=rows.no;
			//parent.closd();
			//$('#dlg').dialog('close');
			//window.opener.document.getElementById("equipment_no").value=rows.no;
			//var eqptno = rows.no;
			//parent.equipment_blur(eqptno);
			 /* jQuery.ajax({
	        	url:"../equipment/dayRent",
	        	data:{equipment_no:eqptno},
	        	type:"POST",
	        	error:function(request){
	        		//alert("no data");
	        	},
	        	success:function(data){
	        		data  = $.parseJSON(data);//没有这句话返回的不是对象
	        		//alert(data);
		        	//alert(data.day_rent==null);
		        	if(data[0].day_rent==null){
		        		window.opener.document.getElementById("day_rent").value="0";
		        	}
		        	parent.document.getElementById("d_country").value=rows.d_country;
		        	parent.document.getElementById("deposit").value=rows.deposit;
		        	parent.document.getElementById("day_rent").value=rows.day_rent;
		        	var data = parent.document.getElementById("day_rent").value;
			    	var days = parent.document.getElementById("date_diff").value;
		        	var cost_rent = days*data;
		     		var cost_return = parent.document.getElementById("deposit").value;
		     		cost_rent = Math.round(cost_rent*100)/100;
		     		parent.document.getElementById("cost_rent").value = cost_rent;
		        	var upfront_sum = parseFloat(cost_rent)+parseFloat(cost_return);
		        	upfront_sum = Math.round(upfront_sum*100)/100;
		        	parent.document.getElementById("upfront_sum").value = upfront_sum;
	        	}
	        });  */
	      /*   parent.document.getElementById("d_country").value=rows.d_country;
        	parent.document.getElementById("deposit").value=rows.deposit;
        	parent.document.getElementById("day_rent").value=rows.day_rent;
        	var data = parent.document.getElementById("day_rent").value;
	    	var days = parent.document.getElementById("date_diff").value;
        	var cost_rent = days*data;
     		var cost_return = parent.document.getElementById("deposit").value;
     		cost_rent = Math.round(cost_rent*100)/100;
     		parent.document.getElementById("cost_rent").value = cost_rent;
        	var upfront_sum = parseFloat(cost_rent)+parseFloat(cost_return);
        	upfront_sum = Math.round(upfront_sum*100)/100;
        	parent.document.getElementById("upfront_sum").value = upfront_sum;
			 parent.$('#dlg').dialog('close'); */
			//window.close();
		}
		
		$(function(){
			var pager = $('#dg').datagrid().datagrid('getPager'); // get the pager of datagrid
			 $(pager).pagination({  
				 	beforePageText: '第',//页数文本框前显示的汉字  
			        afterPageText: '页    共 {pages} 页',  
			        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
			    });  
			
			}); 
	</script>
</body>
 
</html>