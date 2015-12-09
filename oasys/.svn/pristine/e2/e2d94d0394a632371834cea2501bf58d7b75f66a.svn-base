<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>代办任务</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<jsp:include page="../../../layout/script.jsp"></jsp:include>
	<script type="text/javascript">
	
		/**
		 * 根据索引获取对象名称
		 * @param target Grid对象
		 * @param index 索引
		 * @returns 索引的对应行的信息
		 */
		//根据index获取该行
		//审批意见表
		var $$row;
		
		var $row;
		function getRowData(index){
			if (!$.isNumeric(index) || index < 0) {
				return undefined;
			}
			var rows = $("#dg").datagrid("getRows");
			return rows[index];
		}	
	
			var $grid;
			$(function() {
				
				$(window).resize(function(){  
		            $("#dg").datagrid({  
		            	height: $(window).height()-130  
		            });                
		        }); 
				
				$("#procStatus").combobox({
					valueField: 'value',   
			        textField: 'label',   
			        data: [{
						label: '全部',
						value: ''
					},{
						label: '初始化',
						value: '1'
					},{
						label: '审批中',
						value: '2'
					},{
						label: '已完成',
						value: '3'
					},{
						label: '已失效',
						value: '4'
					},{
						label: '已撤销',
						value: '5'
					}],
					editable:false ,
					onLoadSuccess : function(){
					userData = $(this).combobox("getData");
					for (var item in userData[0]) {
			                if (item == "value") {
			                    $(this).combobox("select", userData[0][item]);
			                }
			            }
					}
				});	
					
				<%
			    String definitionKey=request.getParameter("key");
			    %>
				
				$grid =$("#dg").datagrid({
					url : 'UseStampApp/findUseStampAppClimTask.do?definitionKey=<%=definitionKey%>',
					width: 'auto',
					height : $(this).height()-130,
					pagination:true,
					rownumbers:true,
					border:true,
					singleSelect:false,
					nowrap:true,
					multiSort:false,
					border:false,
					fitColumns:true,
					pageList:[10,20,30,40],
					columns : [ [
					        {field : 'appNo',title : '申请编号',width: parseInt($(this).width()*0.07),align : 'center'},
							{field : 'applicantName',title : '申请人',width:parseInt($(this).width()*0.06),align : 'center',
								formatter : function(value, row, index) {
									return "<a href=\"javascript:void(0)\" onclick=\"showView("+ index + ");\">" + value + "</a>";
								}
							},
					        {field : 'fullName',title : '申请部门',width: parseInt($(this).width()*0.07),align : 'center'},
							{field : 'dictName',title : '用章类型',width : parseInt($(this).width()*0.07),sortable : true,align : 'center',formatter:function(value,row,index){
			                	return row.dictName;
			                }},
			                {field : 'usQuantity',title : '用章数量(个)',width:parseInt($(this).width()*0.06),align : 'center'},
							{field : 'usBeginDate',title : '用章开始时间',width:parseInt($(this).width()*0.06),align : 'center'},
							{field : 'usEndDate',title : '用章结束时间',width:parseInt($(this).width()*0.06),align : 'center'},
							{field : 'appDate',title : '申请日期',width:parseInt($(this).width()*0.06),align : 'center'},   
							{field : 'usReason',title : '用章事由',width : parseInt($(this).width()*0.1),align : 'center'},
							{field : 'procStatus',title : '申请状态',width : parseInt($(this).width()*0.05),align : 'center',formatter:function(value,row,index){
			                	if(row.procStatus == "1"){
			                		return "初始状态";
			                	}else if(row.procStatus == "2"){
			                		return "审批中";
			                	}else if(row.procStatus == "3"){
			                		return "已完成";
			                	}else if(row.procStatus == "4"){
			                		return "已失效";
			                	}else{
			                		return "已撤销";
			                	}
			                }},
							{field : 'remark',title : '备注',width : parseInt($(this).width()*0.15),align : 'center'},
							{field : 'id',title : '操作',width:parseInt($(this).width()*0.16),align : 'center',
								formatter : function(value, row, index) {
									var result = ""; 
									if (row.assistant == null || row.assistant == "") {
										result += "<a href='javascript:void(0);' onclick='claimTask("+row.taskId+");'>签收任务</a>　　";
									}else{
										result += "<a href='javascript:void(0);' onclick='handleTaskDialog("+index+");'>受理任务</a>　　";
									}
									result += "<a href='javascript:void(0);' onclick='lookStampAppCommentDialog("+index+");'>查看审批意见</a>　　"
									result += "<a href='javascript:void(0);' onclick='showImage("+ index + ");'>查看当前流程图</a>"
									
									return result; 
									
								}
							} 
							] ],
							 onLoadSuccess:function(data){
						            $(this).datagrid("doCellTip",{'max-width':'100px'});
						           
							  }, 
					toolbar : '#tb'
				});
			});	
		
		//办理任务
		function handleTaskDialog(index){
			$row  = getRowData(index);
			$$row =$row;
			$.ajax({
				type:"POST",
				url:"workflowAction/findTaskFormKeyByTaskId.do",
				data:{"taskId":$row.taskId},
				async:false,
				onClose:function(){
					$("#dg").datagrid('reload');
				},
				success:function(jspName){
					 $banliDialog = $("<div></div>").dialog({
							title:'办理任务',
							width:830,
							height:800,
							modal:true,
							href:jspName,
							onLoad:function(){
								var taskFrom=$("#taskForm");
								$row.appDate=$row.appDate.split(" ")[0];
								taskFrom.form("load",$row);
								$("#taskForm #businessID").val($row.usaId);
						    	$("#taskForm #taskID").val($row.taskId);
						    	$("#taskForm #definitionKey").val("<%=definitionKey%>");
							},
							onClose:function(){
								$(this).dialog('destroy');
							}
					 }); 
				}
			});
		}	
		// 查看流程图片
		function  showImage(index) {
			var row = getRowData(index);
			var src = "UseStampApp/showProcessImg.do?taskId="+ row.taskId;
			$('#imageDialog').dialog("open");
			$("#image").attr("src", src);
		}
	    
		// 领取任务
		function claimTask(taskId) {
			//var row = getRowData($grid,index);
			$.messager.confirm('确定','是否确定签收该用章申请？',	function(flag) {
				if (flag) {
					$.ajax({
						url : "UseStampApp/taskUserClaim.do",
						data : {"taskId" : taskId},
						async:false,
						success : function(rsp) {
							if(rsp.status){
								parent.$.messager.alert(rsp.title,rsp.message,'info');
							}else{
								parent.$.messager.alert(rsp.title,rsp.message,'warning');
							}
							$grid.datagrid('reload');
						}
					});
				}
			});
		}
		
		 // 查看流程批注
		function lookStampAppCommentDialog(index){
			$$row=getRowData(index);
			$("<div></div>").dialog({
				title: '历史审批意见',    
			    width: 900,    
			    height: 500,    
			    closed: false,    
			    cache: false,    
			    href: 'jsp/ad/optionsList.jsp',    
			    modal: true,
			    onClose : function(){
			    	$(this).dialog('destroy');
			    	$$row = null;
		        }
			});
		}
		
		//执行查询
		function subCustomerRepaymentForm(){
			$("#dg").datagrid("load",{
				procStatus:$("#procStatus").combobox("getValue"),
				appNo:$("#appNoMain").val(),
				appDateS:$('#appDateS').datebox('getValue'),
				appDateE:$('#appDateE').datebox('getValue')
			});  
		} 
		
</script>
  </head>
  <body>
      <div data-options="region:'center',border : false">
  		<div class="well well-small" style="margin-left: 5px;margin-top: 5px">
				<div class="position" style="margin-top: 5px;">您当前所在位置：业务管理-->用章申请-->代办任务</div>
		</div>
		<!-- 高级查询栏区域 -->
  		<div class="well well-small" style="margin-left: 5px;margin-top: 5px">
				<form id="customerRepaymentForm"  method="post">
					<table cellpadding="10px;">
						<tr>
						  <th>申请编号:</th>
					      <td>
					     	 <input name="appNo" id="appNoMain" class="easyui-textbox"/>
					      </td>
					      <th>申请日期:</th>
					      <td>
					      	 <input id="appDateS" name="appDateS" placeholder="请选择起始日期" class="easyui-textbox easyui-datebox" data-options="editable:false" />
						　                             　至　　
							 <input id="appDateE" name="appDateE" placeholder="请选择截止日期" class="easyui-textbox easyui-datebox" data-options="editable:false"/>
					      	
					      </td>
					      <td>
					         <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="subCustomerRepaymentForm();">搜索</a>
					         <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" onclick="$('#customerRepaymentForm').form('reset');">重置</a>
					      </td>
						</tr>
					</table>
				</form>
			</div>
		<table id="dg"></table>
	    <div id="imageDialog"  class="easyui-dialog" title="流程图片" data-options="border:false,closed:true,fit:true">
			<img id="image" src="" >
		</div>
		 <div id="imageDialog"  class="easyui-dialog" title="流程图片" data-options="border:false,closed:true,fit:true">
			<img id="image" src="" >
		</div>
		<!-- 跳转办理任务 -->
		<div id="mgrTaskView"></div>
  	</div>	
  </body>
</html>
