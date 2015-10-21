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
			.searchstyle{
				border: 1px solid #95b8e7;border-radius: 5px;height: 22px;width: 140px;
			}
			/* .datagrid-view{
				
				 font-size: 16px; 
			} */
			#xufei input{
			border: 1px solid #95b8e7;border-radius: 5px;height: 20px;width:150px;
			}
			#xufei textarea{
				border: 1px solid #95b8e7;border-radius: 5px;height: 80px;width:230;resize:none;overflow-Y:scroll; 
			}
			.file-box{ position:relative;width:340px}
			.txt{ height:22px; border:1px solid #cdcdcd; width:180px;}
			.btn{ background-color:#FFF; border:1px solid #CDCDCD;height:24px; width:70px;}
			.file{ position:absolute; top:165px; right:80px; height:24px; filter:alpha(opacity:0);opacity: 0;width:260px } 
	</style>
</head>
<body class="easyui-layout">
<script type="text/javascript">
					$(function(){
						$("#infotable").datagrid({
							url:"../info/getlist?t="+new Date(),
						});
						var pager = $('#infotable').datagrid().datagrid('getPager'); // get the pager of datagrid
						pager.pagination({  
						 	beforePageText: '第',//页数文本框前显示的汉字  
						    afterPageText: '页    共 {pages} 页',  
						    displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
						}); 
					});
				</script>
	<div data-options="region:'center'"> 
		<div style="background-color:#F8F8FF;align:center;width: 100%;height: 85px;overflow: hidden;">
				<input id="simpicklisttype" style="display:none;">
					<form id="conditionsOfinfo" style="margin-left: 1%;text-align: left;width: 100%;">
						<table style="width: 100%;">
							<tr>
							<td width="10%">卡号</td><td width="15%"><input id="id" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;width: 140px;" name="id" value=""/></td>
							<td width="10%" >运营商</td>
							<td width="15%">
								<select id="operators" class="searchstyle" name="operators">
									<option value="">全部</option>
									<c:if test="${operatorlist!=null }">
										<c:forEach items="${operatorlist }" var="operators">
											<option value="${operators.ITEM_VALUE }">${operators.ITEM_TEXT }</option>
										</c:forEach>
									</c:if>
								</select>
							</td>
							
							<td width="10%">国家</td>
								<td width="15%">
									<select id="country" class="searchstyle" name="country">
									<%-- <input class="easyui-textbox" style="width:120px;font-size: 16px;" type="text" id="countrys" name="countrys" value="${countrys}"> --%>
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
							<td width="10%">开卡时间</td><td width="15%"><input id="day_begin" name="day_begin" style="border: 1px solid #95b8e7;border-radius: 5px;" value="" class="Wdate" type="text" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy-MM-dd'})"/></td>
							<td>卡到期时间</td><td><input id="day_end" name="day_end" value="" style="border: 1px solid #95b8e7;border-radius: 5px;" class="Wdate" type="text" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy-MM-dd'})"/></td>
							<td width="10%">是否有效</td>
									<td><select id="is_valid" class="searchstyle" name="is_valid">
									<option value="" selected="selected">全部</option>
									<option value="0001" selected="selected">有效</option>
									<option value="0002">无效</option>
								</select></td>
							</tr>
						</table>
					</form>
					<div align="right" style="">
						<a onclick="queryInfoWithCondition();" class="easyui-linkbutton" style="margin-right: 2%;width: 80px;">查询</a>
						<a onclick="sub();" class="easyui-linkbutton" style="margin-right: 2%;width: 80px;">确定</a>
					</div>
				</div>
				<!-- 表单开始 -->
				<table id="infotable" style="height: 84%;font-size: 16px;background-color: #F8F8FF;" 
				data-options="rownumbers:true,pagination:true,singleSelect:true,method:'post',onDblClickRow:doubleclick,remoteSort:false,multiSort:true">
				<thead>
				<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'id',width:150,align:'center'">卡号</th>
				<th data-options="field:'operators',width:60,align:'center'">运营商</th>
				<th data-options="field:'country',width:60,align:'center'">国家</th>
				<th data-options="field:'day_rent',width:60,align:'center'">日租金</th>
				<th data-options="field:'day_begin',width:160,align:'center',sortable:true">开卡时间</th>
				<th data-options="field:'day_end',width:160,align:'center',sortable:true">到期日期</th>
				<th data-options="field:'is_valid',width:80,align:'center',styler: function(value,row,index){
								if (value=='有效'){
									return 'background-color:#F8F8FF;color:green;';
								}else{
									return 'background-color:#F8F8FF;color:red;';
								}
								}">是否有效</th>
				<th data-options="field:'modify_user',width:110,align:'center'">操作员</th>
				<th data-options="field:'modify_time',width:160,align:'center',sortable:true">日期</th>
				<th data-options="field:'remark',width:200,align:'center'">备注</th>
				</tr>
				</thead>
				
				</table>
		<!-- 表单结束 -->
	</div>
	
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
	 <script type="text/javascript">
	 	$(function(){
			$("#createtime").val(new Date().pattern("yyyy/MM/dd hh:mm:ss"));
		});
		$(function(){
		var pager = $('#infotable').datagrid().datagrid('getPager'); // get the pager of datagrid
		pager.pagination({
		beforePageText: '第',//页数文本框前显示的汉字  
	    afterPageText: '页    共 {pages} 页',  
	    displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
		buttons:[{
		iconCls:'icon-edit',
		handler:function(){
			 var field = $("#infotable").datagrid("getSelected");
			  if(field==null){
				  alert("请先选择一条数据");
			  }
			document.getElementById("edit").src="infoedit?id="+field.id;
			$("#editdiv").dialog("open");
		}
		}]
		});
		})
	
		//查看详情
		/* function checkdetail(){
			  var field = $("#infotable").datagrid("getSelected");
			  if(field==null){
				  alert("请先选择一条数据");
			  }
			  document.getElementById("detail").src = "detail?id="+field.id;
			  document.getElementById("detaillist").src = "detaillist?id="+field.id;
			  $('#detaildiv').dialog('open');
		} */
		
		function queryInfoWithCondition(){
			var id = $("#id").val();
			var operators = $("#operators").val();
			var country = $("#country").val();
			var day_begin = $("#day_begin").val();
			var day_end = $("#day_end").val();
			var is_valid = $("#is_valid").val();
			$("#infotable").datagrid({
				url:"../info/queryInfoWc",
				queryParams: {
					id:id,operators:operators,country:country,day_begin:day_begin,day_end:day_end,is_valid:is_valid,
				},
			});
			var pager = $('#infotable').datagrid().datagrid('getPager'); // get the pager of datagrid
			 $(pager).pagination({  
				 	beforePageText: '第',//页数文本框前显示的汉字  
			        afterPageText: '页    共 {pages} 页',  
			        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
			    });  
		}
		
		 function submitForm(){
	        	$("#simopen").submit();
	        }
		
		 function doubleclick(item,field){
			 //alert(field.id);
			 var simpicklisttype = $("#simpicklisttype").val();
			 //alert(simpicklisttype);
			 if(simpicklisttype=="1"){
				 //alert(parent.frames['simpicklist'].document.getElementById("simpicklisttype"));
				 //alert(parent.frames['edit']);
				 parent.frames['edit'].document.getElementById("simid").value=field.id;		
				 parent.$('#infolist').dialog('close');
			 }else{
				 parent.document.getElementById("sim_id").value=field.id;
				 parent.$('#infolist').dialog('close');
			 }
		 }
		 
		 /* 确定按钮 */
			function sub(){
				var rows = $("#infotable").datagrid("getSelected"); 
				var simpicklisttype = $("#simpicklisttype").val();
				//alert(rows);
				if(rows==null){
					alert("请先选择一条数据!");
					return false;
				}
				var infono = rows.id;
				//var dayrent = row.day_rent;
				//alert(rows.no);
				if(simpicklisttype=="1"){
					 //alert( parent.frames['edit'].document.getElementById("simid"));
					 parent.frames['edit'].document.getElementById("simid").value=infono;		
					 parent.$('#infolist').dialog('close');
					 var id = infono;
					 jQuery.ajax({
							url:"../info/checkIsBind",
							data:{id:id},
							type:"post",
							error:function(){alert("ERROR!")},
							success:function(data){
								//alert(data);
								if(data==1){
									if(confirm("此SIM卡已经绑定,确定要更新其绑定设备吗?")){
										parent.frames['edit'].$("#isbinding").val("1");
									}
								}
							}
						});
				 }else{
					 parent.document.getElementById("sim_id").value=infono;
					 //parent.equipment_blur(eqptno);
					 parent.$('#infolist').dialog('close');
				 }
				}
	</script>
</body>
 
</html>