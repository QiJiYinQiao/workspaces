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
	<script type="text/javascript" src="../media/js/WdatePicker.js"></script>
	<script type="text/javascript" src="../media/js/jquery.min.js"></script>
	<script type="text/javascript" src="../media/js/jquery.easyui.min.js"></script>
	<style type="text/css">
			.datagrid-view{
			background-color: #F8F8FF;
			font-size: 16px;
			}
			.searchstyle{
				border: 1px solid #95b8e7;border-radius: 5px;height: 22px;width: 140px;
			}
			.file-box{ position:relative;width:340px}
			.txt{ height:22px; border:1px solid #cdcdcd; width:180px;}
			.btn{ background-color:#FFF; border:1px solid #CDCDCD;height:24px; width:70px;}
			.file{ position:absolute; top:25px; right:80px; height:24px; filter:alpha(opacity:0);opacity: 0;width:260px } 
	</style>
</head>
<body class="easyui-layout">
	<script type="text/javascript">
		/* $(function(){
			$("#dg").datagrid({
				url:'getAllLists',
			});
		}) */
		/*  $(function(){
			jQuery.ajax({
				url:"getAllLists",
				type:"post",
				error:function(){},
				success:function(data){
					data = $.parseJSON(data);
					 //初始化dategrid   
				    $('#dg').datagrid({   
				        loadData:data,
				    });  
				}
			});
		}); 
 */

	</script>
	<script type="text/javascript">
			$(function(){
				$("#dg").datagrid({
					url:"getAllLists?t="+new Date().toString(),
				});
			});
		</script>
	<div data-options="region:'center',title:'设备列表'" scrolling="no" style="overflow:hidden;background-color:#F8F8FF;">
		<div style="background-color:#F8F8FF;align:center;width: 100%;height: 110px;overflow: hidden;">
					<form id="conditionsOfEquipment" style="margin-left: 1%;text-align: left;width: 100%;">
						<table style="width: 100%;">
							<tr>
							<td width="10%">设备号</td><td width="15%"><input id="no" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;width: 140px;" name="no" value=""/></td>
							<td width="10%" >柜台</td>
							<td width="15%">
								<select id="counterid" class="searchstyle" name="counterid">
									<option value="${countercode }" selected="selected">${countername }</option>
									<option value="">全部</option>
									<c:if test="${counterlist!=null }">
										<c:forEach items="${counterlist }" var="counterlists">
											<option value="${counterlists.counter_code }">${counterlists.name }</option>
										</c:forEach>
									</c:if>
								</select>
							</td>
							<td width="10%">是否绑定</td>
							<td width="15%">
								<select id="sim_id" class="searchstyle" name="sim_id">
									<option value="">全部</option>
									<option value="0001">已绑定</option>
									<option value="0002">未绑定</option>
								</select>
							</td>
							<td width="10%">进货日期</td><td width="15%"><input id="stock_date" name="stock_date" style="border: 1px solid #95b8e7;border-radius: 5px;" value="" class="Wdate" type="text" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy-MM-dd'})"/></td>
							</tr>
							<tr>
							<td>租用日期</td><td><input id="rent_begindate" name="rent_begindate" value="" style="border: 1px solid #95b8e7;border-radius: 5px;" class="Wdate" type="text" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy-MM-dd'})"/></td>
							<td>预计返还日期</td><td><input id="rent_expectdate" name="rent_expectdate" value="" style="border: 1px solid #95b8e7;border-radius: 5px;" class="Wdate" type="text" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy-MM-dd'})"/> </td>
							<td>实际返还日期</td><td><input id="rent_enddate" name="rent_enddate" value="" style="border: 1px solid #95b8e7;border-radius: 5px;" class="Wdate" type="text" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy-MM-dd'})"/> </td>
							<td>报废日期</td><td><input id="scrap_date" name="scrap_date" value="" style="border: 1px solid #95b8e7;border-radius: 5px;" class="Wdate" type="text" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy-MM-dd'})"/></td>
							</tr>
							<tr>
								<td width="10%">是否有效</td>
									<td><select id="is_valid"  class="searchstyle" name="is_valid">
									<option value="" selected="selected">全部</option>
									<option value="0001" selected="selected">有效</option>
									<option value="0002">无效</option>
								</select></td>
								</td>
							
								<td width="10%">设备类型</td>
								<td><select id="equipment_type" class="searchstyle" name="equipment_type">
									<option value="">全部</option>
									<option value="0001" selected="selected">内部设备</option>
									<option value="0002">外部设备</option>
								</select></td>
							
								<td width="10%">状态</td>
								<td><select id="equipment_state" class="searchstyle" name="equipment_state">
									<option value="" selected="selected">全部</option>
									<option value="0001">可用</option>
									<option value="0002">已租</option>
									<option value="0003">预定</option>
								</select></td>
								
								<td>国家</td>
								<td>
									<select class="searchstyle" name="countrys" id="countrys">
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
						</table>
					</form>
					<div align="right" style="">
						<a href="downloadModel" class="easyui-linkbutton" style="margin-right: 5px;width: 100px;height: 25px;">下载设备模板</a>
						<a onclick="inport();" class="easyui-linkbutton" style="margin-right: 5px;width: 80px;height: 25px;">导入设备</a>
						<a onclick="checkdetail();" class="easyui-linkbutton" style="margin-right: 5px;width: 60px;height: 25px;">详情</a>
						<a onclick="edits();" class="easyui-linkbutton" style="margin-right: 5px;width: 60px;height: 25px;">编辑</a>
						<a onclick="addeqpt();" class="easyui-linkbutton" style="margin-right: 5px;width: 60px;height: 25px;">新增</a>
						<a onclick="serachWithCondition();" class="easyui-linkbutton" style="margin-right: 5px;width: 60px;height: 25px;">查询</a>
						<!-- <button onclick="serachWithCondition();" style="margin-right: 2%;width: 80px;height: 25px;">查询</button>
						<button onclick="checkdetail();" style="margin-right: 2%;width: 80px;height: 25px;">查看详情</button>
						<button onclick="inport();" style="margin-right: 2%;width: 80px;height: 25px;">导入</button> -->
					</div>
				</div>
		<!-- 表单开始 -->
				<table id="dg" style="height: 80%;background-color:#F8F8FF" class="easyui-datagrid"  
				data-options="rownumbers:true,pagination:true,singleSelect:true,method:'post',onDblClickRow:doubleclick,remoteSort:false,multiSort:true">
				<thead>
				<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'no',width:120,align:'center'">设备编号</th>
				<th data-options="field:'counterid',width:120,align:'center'">柜台名称</th>
				<th data-options="field:'rent_begindate',width:170,align:'center',sortable:true">租用日期</th>
				<th data-options="field:'rent_expectdate',width:170,align:'center',sortable:true">预计返还日期</th>
				<th data-options="field:'rent_enddate',width:170,align:'center',sortable:true">实际返还日期</th>
				<th data-options="field:'equipment_state',width:80,align:'center',styler: function(value,row,index){
								if (value=='可用'){
									return 'background-color:#F8F8FF;color:green;';
								}else{
									return 'background-color:#F8F8FF;color:red;';
								}
							}">设备状态</th>
				<th data-options="field:'is_valid',width:80,align:'center'">是否有效</th>
				<th data-options="field:'day_rent',width:80,align:'center'">日租金</th>
				<th data-options="field:'deposit',width:80,align:'center'">押金</th>
				<th data-options="field:'total_rent',width:80,align:'center'">设备租金</th>
				<th data-options="field:'equipment_type',width:80,align:'center'">设备类型</th>
				<th data-options="field:'sim_id',width:80,align:'center',styler:function(value,row,index){
					if(value=='未绑定'){
						return 'background-color:#F8F8FF;color:green;';
					}else{
						return 'background-color:#F8F8FF;color:red';
					}
				}">是否绑定</th>
				<th data-options="field:'stock_date',width:170,align:'center',sortable:true">进货日期</th>
				<th data-options="field:'scrap_date',width:170,align:'center',sortable:true">报废日期</th>
				<!-- <th data-options="field:'create_user',width:80,align:'center'">创建人</th>
				<th data-options="field:'create_time',width:170,align:'center',sortable:true">创建时间</th> -->
				<th data-options="field:'modify_user',width:80,align:'center'">操作员</th>
				<th data-options="field:'modify_time',width:170,align:'center',sortable:true">日期</th>
				<th data-options="field:'remark',width:200,align:'center'">备注</th>
				
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
	<!-- </div> -->
		<div id="checkdetail"  class="easyui-dialog" modal="true" title="设备详情"  closed="true" style="width:800px;height:400px;overflow:hidden;">
			<iframe id="detail" name="contents" src="" width="100%" height="100%" frameborder="0" style="overflow: auto;"></iframe>
		</div>
		<div id="edits" class="easyui-dialog" modal="true" style="width:800px;height:400px;overflow: hidden;" closed="true" buttons="#dlg-buttons" title="编辑设备">
        	<iframe id="edit" name="edit" src="" width="100%" height="100%" frameborder="0" ></iframe>
   		 </div>
   		 <div id="adds" modal="true" class="easyui-dialog" style="width:880px;height:380px;overflow:hidden;" closed="true" title=" ">
        	<iframe id="add" name="add" src="add" width="100%" height="100%" frameborder="0"></iframe>
   		 </div>
   		 <div id="inportdiv" modal="true" class="easyui-dialog" style="width:400px;height:200px;overflow:hidden;" closed="true" title=" ">
        	 <div class="file-box" style="text-align: center;"><!--  -->
				<form id="uploadform"  action="uploadfile" method="post" enctype="multipart/form-data">
				请选择柜台:
				<select class="searchstyle" name="counterid">
					<option value="${countercode }" selected="selected">${countername }</option>
					<option value="">全部</option>
					<c:if test="${counterlist!=null }">
						<c:forEach items="${counterlist }" var="counterlists">
							<option value="${counterlists.counter_code }">${counterlists.name }</option>
						</c:forEach>
					</c:if>
				</select>
				
				<input type='text' name='textfield' id='textfield' class='txt' />
				<input type='button' class='btn' value='浏览...' />
				<input type="file" name="resourcefile" class="file" id="fileField" size="28" onchange="document.getElementById('textfield').value=this.value" />
				<input type="submit" name="submit" class="btn" value="上传" />
				</form><!--onclick="uploadform();"  action="uploadfile" method="post" -->
			 </div> 
   		 </div>
   		 <div id="infolist"  class="easyui-dialog" modal="true" title="SIM卡详情"  closed="true" style="width:1000px;height:600px;overflow:hidden;left:120px; top:40px;">
			 <iframe id="simpicklist" name="simpicklist" src="simpicklist" width="100%" height="100%" frameborder="0" style="overflow: hidden;"></iframe> 
		</div>
	</div>
	 <script type="text/javascript">
		/* $(function(){
			$('#checkdetail').dialog('close');
			$('#adds').dialog('close');
		}); */
		
		function serachWithCondition(){
			//var params = $("#conditionsOfEquipment").serialize();
			//alert(params);
			//alert(typeof(params));
			  /* jQuery.ajax({
				url:"queryWithCondition",
				data:$("#conditionsOfEquipment").serialize(),
				type:"post",
				error:function(){
					alert("查询失败");
				},
				success:function(data){
					data = $.parseJSON(data);
					if(data.rows!=''){
						$('#dg').datagrid('loadData',data);
					}else{
						 $("#dg").datagrid('loadData',{'total':'','rows':''});
					}
				}
			});  */
			var no = $("#no").val();
			var counterid = $("#counterid").val();
			var sim_id = $("#sim_id").val();
			var stock_date = $("#stock_date").val();
			var rent_begindate = $("#rent_begindate").val();
			var rent_expectdate = $("#rent_expectdate").val();
			var rent_enddate = $("#rent_enddate").val();
			var scrap_date = $("#scrap_date").val();
			var is_valid = $("#is_valid").val();
			var equipment_type = $("#equipment_type").val();
			var equipment_state = $("#equipment_state").val();
			var countrys = $("#countrys").val();
			$("#dg").datagrid({
				url:"queryWithCondition",
				queryParams: {
					no:no,counterid:counterid,sim_id:sim_id,stock_date:stock_date,rent_begindate:rent_begindate,rent_expectdate:rent_expectdate,
					rent_enddate:rent_enddate,is_valid:is_valid,equipment_type:equipment_type,equipment_state:equipment_state,scrap_date:scrap_date,
					countrys:countrys,
				},

			});
			 var pager = $('#dg').datagrid().datagrid('getPager'); // get the pager of datagrid
			 $(pager).pagination({  
				 	beforePageText: '第',//页数文本框前显示的汉字  
			        afterPageText: '页    共 {pages} 页',  
			        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
			        buttons:[{
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
					}]       
			 });
		}
		/* 双击事件 */
		function doubleclick(index,field){
			var r = $("#dg").datagrid("getRows",index);
			document.getElementById("detail").src="detail?no="+field.no;
			$('#checkdetail').dialog('open');
		}
		/* 查看详情 */
		function checkdetail(){
			  var field = $("#dg").datagrid("getSelected");
			  if(field==null){
				  alert("请先选择一条数据");
				  return false;
			  }
			  document.getElementById("detail").src="detail?no="+field.no;
			  $('#checkdetail').dialog('open');
		}
		/* 编辑 */
		function edits(){
			var field = $("#dg").datagrid("getSelected");
			if(field==null){
				alert("请先选择一条数据");
			}
			document.getElementById("edit").src="edit?no="+field.no;
			$("#edits").dialog("open");
		}
		/* 新增 */
		function addeqpt(){
			$("#adds").dialog('open');
		}
		/* 设置分页中文显示 + 功能按钮*/
		$(function(){
			 var pager = $('#dg').datagrid().datagrid('getPager'); // get the pager of datagrid
			 $(pager).pagination({  
				 	beforePageText: '第',//页数文本框前显示的汉字  
			        afterPageText: '页    共 {pages} 页',  
			        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
			        buttons:[{
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
					}]
			    });  
			
			});
		
		function inport(){
			$("#inportdiv").dialog("open");
		}
	</script>
</body>
 
</html>