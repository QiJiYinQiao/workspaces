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
			.datagrid-view{
				background-color: #F8F8FF;
				font-size: 16px;
			}
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
<script>
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
</script>
<script type="text/javascript">
					$(function(){
						$("#infotable").datagrid({
							url:"getlist?t="+new Date(),
						});
						var simnotype = $("#simnotype").val();
						if(simnotype == 1){
							$("#kaika").dialog("open");
						}
					});
				</script>
	<div data-options="region:'center',title:'数据卡管理'"> 
		<div style="background-color:#F8F8FF;align:center;width: 100%;height: 85px;overflow: hidden;">
		 <input id="simnotype" value="${simnotype }" style="display:none;">
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
							
							<td width="10%">开卡时间</td><td width="15%"><input id="day_begin" name="day_begin" style="border: 1px solid #95b8e7;border-radius: 5px;" value="" class="Wdate" type="text" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy-MM-dd'})"/></td>
							</tr>
							<tr>
							<td>卡到期时间</td><td><input id="day_end" name="day_end" value="" style="border: 1px solid #95b8e7;border-radius: 5px;" class="Wdate" type="text" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy-MM-dd'})"/></td>
							<td width="10%">是否有效</td>
									<td><select id="is_valid" class="searchstyle" name="is_valid">
									<option value="" selected="selected">全部</option>
									<option value="0001" selected="selected">有效</option>
									<option value="0002">无效</option>
								</select></td>
								</td>
							</tr>
						</table>
					</form>
					<div align="right" style="">
						<a href="downloadModel" class="easyui-linkbutton" style="margin-right: 5px;width: 80px;height: 25px;">下载模板</a>
						<a onclick="inportSimCard();" class="easyui-linkbutton" style="margin-right: 5px;width: 60px;height: 25px;">导入</a>
						<a onclick="cardopen();" class="easyui-linkbutton" style="margin-right: 5px;width: 60px;height: 25px;">开卡</a>
						<a onclick="chongzhi();" class="easyui-linkbutton" style="margin-right: 5px;width: 60px;height: 25px;">充值</a>
						<a onclick="checkdetail();" class="easyui-linkbutton" style="margin-right: 5px;width: 60px;height: 25px;">详情</a>
						<a onclick="edit();" class="easyui-linkbutton" style="margin-right: 5px;width: 60px;height: 25px;">编辑</a>
						<a onclick="queryInfoWithCondition();" class="easyui-linkbutton" style="margin-right:5px;width: 60px;height: 25px;">查询</a>
						<!-- <button onclick="queryInfoWithCondition();" style="margin-right: 2%;width: 80px;height: 25px;">查询</button>
						<button onclick="chongzhi();" style="margin-right: 2%;width: 80px;height: 25px;">充值</button>
						<button onclick="cardopen();" style="margin-right: 2%;width: 80px;height: 25px;">开卡</button>
						<button onclick="checkdetail();" style="margin-right: 2%;width: 80px;height: 25px;">查看详情</button>
						<button onclick="inportSimCard();" style="margin-right: 2%;width: 80px;height: 25px;">导入</button> -->
					</div>
				</div>
				<!-- 表单开始 -->
				<table id="infotable" style="height: 85%;" class="easyui-datagrid"  
				data-options="rownumbers:true,pagination:true,singleSelect:false,method:'post',onDblClickRow:doubleclick,remoteSort:false,multiSort:true">
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
								}">状态</th>
				<th data-options="field:'modify_user',width:110,align:'center'">操作员</th>
				<th data-options="field:'modify_time',width:160,align:'center',sortable:true">日期</th>
				<th data-options="field:'remark',width:220,align:'center'">备注</th>
				</tr>
				</thead>
				
				</table>
		<!-- 表单结束 -->
		
		<div id="detaildiv"  class="easyui-dialog" modal="true" title="数据卡详情"  closed="true" style="width:1000px;height:600px;overflow:hidden;left:120px; top:40px;">
			<iframe id="detail" name="contents" src="" width="100%" height="32%" frameborder="0" style="overflow: hidden;"></iframe>
			<iframe id="detaillist" name="contents" src="" width="100%" height="68%" frameborder="0" style="overflow: auto;"></iframe>
		</div>
		
		<div id="editdiv"  class="easyui-dialog" modal="true" title="编辑数据卡"  closed="true" style="width:1000px;height:300px;overflow:hidden;left:120px; top:100px;">
			<iframe id="edit" name="contents" src="" width="100%" height="100%" frameborder="0" style="overflow: auto;"></iframe>
		</div>
		
		<div id="chongzhidiv"  class="easyui-dialog" modal="true" title="充值/续费"  closed="true" style="width:280px;height:200px;overflow:auto;left:420px; top:100px;background-color: #F8F8FF;text-align: center;">
				<!-- id="xufeiform" onclick="subxufei();"display: none;-->
				<form action="xufei" method="post" style="text-align: center;display: none;" id = "xufeiform" >
				</form>
				<table id="xufei">
						<tr><td>续费后到期日期</td><td><input id="ex_updatedate" value="" style="display: none;" class='Wdate'/><input id='updatedates'  name='updatedates'  class='Wdate' value=' ' onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy-MM-dd',onpicking:updatedatespick(),minDate:'#F{$dp.$D(\'ex_updatedate\',{d:+1})}'})" ></input></td></tr>
						<tr><td>备注</td><td><textarea id="remarks" name='remarks' resize='none' onblur="remarkblur();"> </textarea></td></tr>
				</table>
				<div style="position: absolute;right: 20px;top: 150px;"><button onclick="quxiao();"  style="float: right;width: 50px;height: 30px;">取消</button><button onclick="xufeiformsubmit();"  style="float: right;width: 50px;height: 30px;">提交</button></div>
		</div>
		
		<div id="inportinfodiv" modal="true" class="easyui-dialog" style="width:400px;height:250px;overflow:hidden;top:100px;background-color: #F8F8FF;" closed="true" title=" ">
        	 <div class="file-box" style="margin-left: 20px;"><!--  -->
				<form id="uploadinfoform"  action="uploadfileForInfo" method="post" enctype="multipart/form-data">
				<table style="margin-left: 40px;"> 
					<tr>
						<td>请选择国家: </td>
						<td> 
							<select id="countryinport" onchange="counterychange();" style="border: 1px solid #95b8e7;border-radius: 5px;height: 24px;width:140px;" name="country">
								<option value="">请选择</option>
								<c:if test="${country!=null }">
									<c:forEach items="${country }" var="countrys">
										<option value="${countrys.ITEM_VALUE }">${countrys.ITEM_TEXT }</option>
									</c:forEach>
								</c:if>
							</select>
						</td>
					</tr>
					<tr>
						<td>请选择运营商: </td>
						<td> 
							<select id="operatorsinport" style="border: 1px solid #95b8e7;border-radius: 5px;height: 24px;width:140px;" name="operators">
								<option  value="">请选择</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>日租金: </td>
						<td>
							<input id="day_rent" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;" readonly="readonly" type="text" name="day_rent" value="" ></input>
						</td>
					</tr>
					<tr>
						<td>卡到期时间: </td>
						<td>
							<input  width="15%"  type="text" name="day_end" value="" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;" class="Wdate" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy/MM/dd HH:mm:ss'})"></input>
				 		</td>
					</tr>
					<tr>
						<td>操作人: </td>
						<td>
							<input  width="15%"  type="text" name="create_user" value="${realname }" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;background-color:#B0C4DE;" readonly="readonly"></input>
				 		</td>
					</tr>
					<tr>
						<td>操作时间: </td>
						<td>
							<input id="createtime" width="15%"  type="text" name="create_time" value=""  style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;background-color:#B0C4DE;" readonly="readonly"></input>
				 		</td>
					</tr>
				</table>
				<input type='text' name='textfield' id='textfield' class='txt' />
				<input type='button' class='btn' value='浏览...' />
				<input type="file" name="resourcefile" class="file" id="fileField" size="28" onchange="document.getElementById('textfield').value=this.value" />
				<input id="submitinfo" type="button" class="btn" value="上传" />
				</form><!--onclick="uploadform();"  action="uploadfile" method="post" -->
			 </div> 
   		 </div>
   		 
   		<!--  <div id="simnolist"  class="easyui-dialog" modal="true" title="数据卡信息"  closed="true" style="width:1000px;height:300px;overflow:hidden;left:120px; top:40px;">
			<table>
				<tr><td>序号</td><td>卡号</td></tr>
			</table>
		</div> -->
		
		 <%-- <div id="kaika"  class="easyui-dialog" modal="true" title="数据卡开卡" closed="true"  style="width:400px;height:600px;overflow:hidden;left:120px; top:40px;">
			<div style="height: 40px;">
				<span style="float:left;font-size: 16px; ">本次共导入${simnosnum }条数据</span>
				<button onclick="kaikaform()" style="float: right;height: 30px;width:80px;">确认导入</button>
				<button onclick="kaikacancel()" style="float: right;height: 30px;width:80px;">取消</button>
			</div>
			<div>
				<form id="kaikaform" action="kaika" method="post" style="font-size: 16px;text-align: center;">
				   <table>
						<tr><td width="80px;">序号</td><td width="320px;">卡号</td></tr>
						<c:if test="${simnos!=null }">
							<c:forEach items="${simnos }" var="simno" varStatus="status">
								<tr><td>${status.index+1 }</td><td><input name="simno" value="${simno }"></td></tr>
							</c:forEach>
						</c:if>
					</table>
				</form>
			</div>
		</div> --%>
   		 
	</div>
	
	 <div id="simnolist"  class="easyui-dialog" modal="true" title="数据卡开卡"  closed="true" style="width:630px;height:300px;overflow:hidden;background-color: #F8F8FF;">
			<form id="simopen" action="simopen" method="post">
				<table>
					<tr>
						<td>开卡时间: </td>
						<td>
							<input  width="15%"  type="text" name="day_begin" value="" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;"  class="Wdate" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy/MM/dd'})"></input>
				 		</td>
						<td>卡到期时间: </td>
						<td>
							<input id="lastdayend" class="Wdate" style="display:none;">
							<input  width="15%"  type="text" name="day_end" value="${dayend }" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;" class="Wdate" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy/MM/dd',minDate:'#F{$dp.$D(\'lastdayend\',{d:+1})}'})"></input>
				 		</td>
				 		<td>操作人: </td>
						<td>
							<input  width="15%"  type="text" name="modify_user" style="background-color:#B0C4DE;border: 1px solid #95b8e7;border-radius: 5px;height: 20px;" value="${realname }" readonly="readonly" ></input>
				 		</td>
					</tr>
					<tr>
						<td>操作时间: </td>
						<td>
							<input id="createtimes" width="15%"  type="text" name="modify_time" value="" style="background-color:#B0C4DE;border: 1px solid #95b8e7;border-radius: 5px;height: 20px;"  readonly="readonly"></input>
				 		</td>
					</tr>
					<tr>
						<td >备注 </td>
						<td colspan="5">
						<textarea id="remark"  style="border: 1px solid #95b8e7;border-radius: 5px;height: 100px;width:100%;resize:none;overflow-Y:scroll;font-size: 18px;" resize="none"  name="remark"></textarea>
						</td>
					</tr>
				</table>
				<div style="text-align:right;padding-right:10px;width:100%;">
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style="width: 56px;">提交</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</div>
			</form>
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
		buttons:[/* {
		iconCls:'icon-search',
		handler:function(){
		alert('search');
		}
		},{
		iconCls:'icon-add',
		handler:function(){
		alert('add');
		}
		}, */{
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
		//双击事件
		function doubleclick(index,field){
			var row = $("#infotable").datagrid("getRows",index);
			//alert(row);
			document.getElementById("detail").src = "detail?id="+field.id;
			document.getElementById("detaillist").src = "detaillist?id="+field.id;
			$("#detaildiv").dialog("open");
		}
		//查看详情
		function checkdetail(){
			  var field = $("#infotable").datagrid("getSelected");
			  if(field==null){
				  alert("请先选择一条数据");
			  }
			  document.getElementById("detail").src = "detail?id="+field.id;
			  document.getElementById("detaillist").src = "detaillist?id="+field.id;
			  $('#detaildiv').dialog('open');
		}
		
		function queryInfoWithCondition(){
			/* jQuery.ajax({
				url:"queryInfoWc",
				data:$("#conditionsOfinfo").serialize(),
				type:"post",
				error:function(){
					alert("查询失败!")
				},
				success:function(data){
					//alert(data);
					data = $.parseJSON(data);
					$("#infotable").datagrid({
						url:"",
					});
					$("#infotable").datagrid('loadData',data);
					var pager = $('#infotable').datagrid().datagrid('getPager'); // get the pager of datagrid
					 $(pager).pagination({  
						 	beforePageText: '第',//页数文本框前显示的汉字  
					        afterPageText: '页    共 {pages} 页',  
					        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
					    });  
				}
			}); */
			var id = $("#id").val();
			var operators = $("#operators").val();
			var country = $("#country").val();
			var day_begin = $("#day_begin").val();
			var day_end = $("#day_end").val();
			var is_valid = $("#is_valid").val();
			$("#infotable").datagrid({
				url:"queryInfoWc",
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
		//编辑按钮
		function edit(){
			var field = $("#infotable").datagrid("getSelected");
			  if(field==null){
				  alert("请先选择一条数据");
			  }
			document.getElementById("edit").src="infoedit?id="+field.id;
			$("#editdiv").dialog("open");
		}
		
		function chongzhi(){
			 //$("#xufei").empty();
			 var field = $("#infotable").datagrid("getSelections");
			 if(field==''){
				 alert("请先选择一条数据!");
				 return false;
			 }
			 var inputs = "";
			 /* var inputs = "";
			 var datapicker = "dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\\'ex_updatedate\\',{d:+1})}'"
			 for(var i = 0 ;i<field.length;i++){
				var datapicker = "dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\\'ex_updatedate"+i+"\\',{d:+1})}'"
				inputs += "<tr><td><input name='card_id' value='"+field[i].id+"' readonly='readonly'/></td><td><input id='ex_updatedate"+i+"' name='ex_updatedate' value='"+field[i].day_end+"' readonly='readonly'  class='Wdate'/></td><td><input id='updatedate'  name='updatedate'  class='Wdate' value=' ' onFocus=WdatePicker({doubleCalendar:true,"+datapicker+"}) ></input></td><td><textarea name='remark' resize='none'> </textarea></td></tr>";
			 }
			 $("#xufei").append("<tr><td>卡号</td><td>到期日期</td><td>续费后到期日期</td><td>备注</td></tr>");
			 $("#xufei").append(inputs); */
			 $("#xufeiform").empty();
			 var dates = field[0].day_end;
			 for(var i = 0 ;i<field.length;i++){
				 	//alert(field[0].country);
				 	var date1 = field[i].day_end;
				 	/* alert(dates+":"+date1+":");
				 	alert(dates<date1); */
				 	if(dates<date1){
				 		dates = date1;
				 	}
				 	if(field[0].country!=field[i].country){
				 		alert("您选择的不是同一国家，请重新选择!")
				 		return false;
				 	}else if(field[0].day_end!=field[i].day_end){
				 		if(confirm("您选择的卡到期日期不同，确定要继续吗？")){
				 			$("#ex_updatedate").val(dates);
				 			inputs += "<input name='card_id' value='"+field[i].id+"' readonly='readonly'/><input id='ex_updatedate"+i+"' name='ex_updatedate' value='"+field[i].day_end+"' readonly='readonly'  class='Wdate'/><input id='updatedate'  name='updatedate'  class='Wdate' value=' ' onFocus=WdatePicker({doubleCalendar:true}) ></input><textarea id='remark' name='remark' resize='none'>续费前到期日期为:"+field[i].day_end+"</textarea>";
				 		}else{
				 			$("#chongzhidiv").dialog("close");
				 			return false;
				 		}
				 	}else{
				 		$("#ex_updatedate").val(field[0].day_end);
				 		inputs += "<input name='card_id' value='"+field[i].id+"' readonly='readonly'/><input id='ex_updatedate"+i+"' name='ex_updatedate' value='"+field[i].day_end+"' readonly='readonly'  class='Wdate'/><input id='updatedate'  name='updatedate'  class='Wdate' value=' ' onFocus=WdatePicker({doubleCalendar:true}) ></input><textarea id='remark' name='remark' resize='none'> </textarea>";
				 	}
				 }
			 $("#xufeiform").append(inputs);
			 $("#chongzhidiv").dialog("open");
		}
		
		function updatedatespick(){
			var updates = $("#updatedates").val();
			$("#updatedate").val(updates);
			
		}
		
		function remarkblur(){
			//alert("hhaha");
			var remark = $("#remarks").val();
			$("#remark").val(remark);
		}
		
		function xufeiformsubmit(){
			$("#xufeiform").submit();
		}
		
		function quxiao(){
			$("#chongzhidiv").dialog("close");
		}
		
		function inportSimCard(){
			$("#inportinfodiv").dialog("open");			
		}
		
		function counterychange(){
			var country = $("#countryinport").val();
			//alert(country);
			if(country!=''){
				$("#countryspan").css("display","none");
			}
			//alert(country);
			jQuery.ajax({
				url:"queryOperatorByC",
				data:{country:country},
				type:"post",
				error:function(){
					alert("查询失败!");
				},
				success:function(data){
					data = $.parseJSON(data);
					//alert(data);
					$("#operatorsinport").empty();
					//alert(data.dayrent);
					$("#day_rent").val(data.dayrent);
					//var operators = document.getElementById("operators");
					$.each(data.list,function(item,value){
						//alert(value.ITEM_TEXT);
						var options = "<option value = '"+value.ITEM_VALUE+"'>"+value.ITEM_TEXT+"</option>";
						//alert(options);
						$("#operatorsinport").append(options);
					});
				}
			}); 
		}
		
		$("#submitinfo").click(function(){
			if($("#textfield").val()==""){
				alert("请先选择导入文件！");
				return false;
			}
			$("#inportinfodiv").dialog("close");
			
			//$("#simnolist").dialog("open");
			$("#uploadinfoform").submit();
		});
		
		
		function cardopen(){
			$("#createtimes").val(new Date().pattern("yyyy/MM/dd hh:mm:ss"));
			 var field = $("#infotable").datagrid("getSelections");
			 if(field==''){
				 alert("请先选择一条数据!");
				 return false;
			 }
			 var input = "";
			 var dates = field[0].day_end;
			 for(var i = 0 ; i < field.length; i++){
				 var date1 = field[i].day_end;
				 if(dates<date1){
					 dates = date1;
				 }
				 input += "<input name='ids' value = '"+field[i].id+"' style='display:none;'>";
			 }
			 $("#lastdayend").val(dates);
			 $("#simopen").append(input);
			 $("#simnolist").dialog("open");
		}
		
		 function submitForm(){
	        	$("#simopen").submit();
	        }
		/* function kaikaform(){
			$("#kaikaform").submit();
		}
		
		function kaikacancel(){
			$("#kaika").dialog("close");
		} */
		
		function subxufei(){
			//var datas = $("#xufeiform").serialize();
			
			//alert(datas);
			/* jQuery.ajax({
				url:"xufei",
				type:"post",
				async:"false",
				data:{id:123},
				error:function(){},
				success:function(data){
					alert("success");
				}
			});  */
		}
	</script>
</body>
 
</html>