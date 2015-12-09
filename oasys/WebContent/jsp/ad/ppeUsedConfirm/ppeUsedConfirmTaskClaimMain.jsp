<%@page import="com.oasys.util.Constants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>固定资产使用代办任务</title>
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
		 var $$row;
		 var $row;
		 //判断是否是行政经理登录
		 var uType=false;
		 
		function getRowData(index){
			if (!$.isNumeric(index) || index < 0) {
				return undefined;
			}
			var rows = $("#dg").datagrid("getRows");
			return rows[index];
		}	
	
	var $grid;
	$(function() {
		//财富端、jiekuanduanu
		  $("#myId").combobox({
				valueField: 'value',   
		        textField: 'label',   
		        data: [{
					label: '全部',
					value: ''
				},{
					label: '财富端',
					value: 'CF'
				},{
					label: '借款端',
					value: 'JK'
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

		//窗口自适应	
		$(window).resize(function(){  
            $("#dg").datagrid({  
            	height: $(window).height()-130  
            });                
        }); 
		
		//获取key值
		<%
	    String definitionKey=request.getParameter("key");
	    %>
	    //待办任务列表
		badgegrid();
	});
				
	function badgegrid(){
		
		//获取key值
		$grid =$("#dg").datagrid({
					url : 'PpeUsedTask/findPpeUsedTaskList.do?definitionKey=<%=definitionKey%>',
					width: 'auto',
					height :$(this).height()-130,
					pagination : true,
					rownumbers : true,
					border : true,
					singleSelect : true,
					pageList:[10,20,30,40],
					nowrap : true,
					multiSort : false,
					fitColumns : true,
					columns : [ [
							        {field : 'appNo',title : '申请编号',width:parseInt($(this).width()*0.07),align : 'center'},
							        {field : 'appDeptName',title : '申请部门',width:parseInt($(this).width()*0.07),align : 'center'},
							        {field : 'applicantName',title : '申请人',width:parseInt($(this).width()*0.07),align : 'center',
										formatter : function(value, row, index) {
											return "<a href=\"javascript:void(0)\" onclick=\"showView("+ index + ");\">" + value + "</a>";
										}
									},
									{field : 'userDeptName',title : '使用部门',width:parseInt($(this).width()*0.07),align : 'center'},
							        {field : 'userName',title : '使用人',width:parseInt($(this).width()*0.07),align : 'center',
										formatter : function(value, row, index) {
											return "<a href=\"javascript:void(0)\" onclick=\"showView("+ index + ");\">" + value + "</a>";
										}
									},
									{field : 'useNature',title : '使用性质',width : parseInt($(this).width()*0.05),sortable : true,align : 'center',formatter:function(value,row,index){
					                	if(row.useNature == "1"){
					                		return "借用";
					                	}else{
					                		return "领用";
					                	}
					                }},
									{field : 'ppeNo',title : '固定资产编号',width:parseInt($(this).width()*0.07),align : 'center'},
									{field : 'ppeName',title : '固定资产名称',width:parseInt($(this).width()*0.09),align : 'center'},  
									{field : 'usedQty',title : '固定资产数量',width:parseInt($(this).width()*0.09),align : 'center'},  
									{field : 'appDate',title : '申请日期',width:parseInt($(this).width()*0.07),align : 'center'},   
									{field : 'procStatus',title : '流程状态',width : parseInt($(this).width()*0.06),align : 'center',formatter:function(value,row,index){
					                	if(row.procStatus == "1"){
					                		return "初始状态";
					                	}else if(row.procStatus == "2"){
					                		return "审批中";
					                	}else if(row.procStatus == "3"){
					                		return "已完成";
					                	}else if(row.procStatus == "4"){
					                		return "已失效";
					                	}else if(row.procStatus == "5"){
					                		return "已撤销";
					                	}else{
					                		return "已拒绝";
					                	}
					                }},
									{field : 'remark',title : '备注',width : parseInt($(this).width()*0.15),align : 'center'},
									{field : 'caozuo',    title : '操作',    width : parseInt($(this).width()*0.2), align:'center',
						        	 	formatter: function(value,row,index){
						        		   	var result = ""; 
						        		   	
											if (row.assistant == null || row.assistant == "") {
												result += "<a href='javascript:void(0);' onclick='claimTask("+row.taskID+");'>签收任务</a>　";
											}else{
												result += "<a href='javascript:void(0);' onclick='handleTaskDialog("+index+");'>受理任务</a>　";
											}
											result += "<a href='javascript:void(0);' onclick='lookBadgeAppCommentDialog("+index+");'>查看审批意见</a>　"
											result += "<a href='javascript:void(0);'  onclick='showImage("+ index + ");'>查看当前流程图</a>　"
											result+="<a href=\"javascript:void(0)\" onclick=\"lookBadgeAttment("+ index + ");\">查看附件</a>";
											return result; 
					      				}
						            } 
									] ],
									 onLoadSuccess:function(data){
										 var rows=data.rows;
								            var mergeMap = {};
								            if(rows){
									           	for(var i=0;i<rows.length;i++){
									           		var taskID = rows[i].taskID
									           		if( taskID in mergeMap ){
									           			mergeMap[taskID].rowspan++;
									           		}else{
									           			mergeMap[taskID]={"index":i,"rowspan":1}
									           		}
									           	}
								           } 
								           
								           for(var i in mergeMap){
								        	 //  $(this).datagrid("autoMergeCells",['appNo']);
								        	   $(this).datagrid('mergeCells',{
								                   index: mergeMap[i].index,
								                   field: 'appNo',
								                   rowspan: mergeMap[i].rowspan
								               });
								        	   
								        	   //$(this).datagrid("autoMergeCells",['fullName']);
								               $(this).datagrid('mergeCells',{
								                   index: mergeMap[i].index,
								                   field: 'appDeptName',
								                   rowspan: mergeMap[i].rowspan
								               });
								             
								               $(this).datagrid('mergeCells',{
								                   index: mergeMap[i].index,
								                   field: 'applicantName',
								                   rowspan: mergeMap[i].rowspan
								               });
								               $(this).datagrid('mergeCells',{
								                   index: mergeMap[i].index,
								                   field: 'userDeptName',
								                   rowspan: mergeMap[i].rowspan
								               });
								               $(this).datagrid('mergeCells',{
								                   index: mergeMap[i].index,
								                   field: 'userName',
								                   rowspan: mergeMap[i].rowspan
								               });
								               
								              // $(this).datagrid("autoMergeCells",['fullName']);
								               $(this).datagrid('mergeCells',{
								                   index: mergeMap[i].index,
								                   field: 'useNature',
								                   rowspan: mergeMap[i].rowspan
								               });
								               $(this).datagrid('mergeCells',{
								                   index: mergeMap[i].index,
								                   field: 'usedQty',
								                   rowspan: mergeMap[i].rowspan
								               });
								               $(this).datagrid('mergeCells',{
								                   index: mergeMap[i].index,
								                   field: 'appDate',
								                   rowspan: mergeMap[i].rowspan
								               });
								               $(this).datagrid('mergeCells',{
								                   index: mergeMap[i].index,
								                   field: 'procStatus',
								                   rowspan: mergeMap[i].rowspan
								               });
								               $(this).datagrid('mergeCells',{
								                   index: mergeMap[i].index,
								                   field: 'remark',
								                   rowspan: mergeMap[i].rowspan
								               });
								               $(this).datagrid('mergeCells',{
								                   index: mergeMap[i].index,
								                   field: 'caozuo',
								                   rowspan: mergeMap[i].rowspan
								               });
								           }    
										 
								           $(this).datagrid("doCellTip",{'max-width':'100px'});
									  }, 
							toolbar : '#tb'
						});
				}
			
			
		//查看附件
		function lookBadgeAttment(index){
			//主页面查看时，明细为申请id
			var row = this.getRowData(index);
			checkAttachementDetail(row.appNo,row.registrantNo,'');
		};
		
		//办理任务
		function handleTaskDialog(index){
			$row  = getRowData(index);
			$$row =$row;
			
			
			$.ajax({
				type:"POST",
				url:"workflowAction/findTaskFormKeyByTaskId.do",
				data:{"taskId":$row.taskID},
				onClose:function(){
					$("#dg").datagrid('reload');
				},
				async:false,
				success:function(jspName){
					 $banliDialog = $("<div></div>").dialog({
							title:'办理任务',
							width:830,
							height:800,
							modal:true,
							href:jspName,
							onLoad:function(){
								var badgeTaskFrom=$("#taskForm");
								badgeTaskFrom.form("load",$row);
								$("#taskForm #businessID").val($row.psaId);
						    	$("#taskForm #taskID").val($row.taskID);
						    	$("#taskForm #definitionKey").val("<%=definitionKey%>");
						    	//使用性质
						    	$("#taskForm #useNature").val($row.useNature);
						    	//归还人
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
			var src = "PpeUsedTask/showProcessImg.do?taskId="+ row.taskID;
			$('#imageDialog').dialog("open");
			$("#image").attr("src", src);
		}
	    
		// 领取任务
		function claimTask(taskId) {
			//var row = getRowData($grid,index);
			$.messager.confirm('签收任务', '是否确认签收任务?', function(d) {
	    		if (d) {
					$.ajax({
						url : "PpeUsedTask/taskUserClaim.do",
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
		function lookBadgeAppCommentDialog(index){
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
				myId:$("#myId").combobox("getValue"),
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
				<div id="task" class="position" style="margin-top: 5px;">您当前所在位置：业务管理-->固定资产使用申请-->代办任务</div>
		</div>
		<!-- 高级查询栏区域 -->
  		<div  class="well well-small" style="margin-left: 5px;margin-top: 5px">
				<form id="customerRepaymentForm"  method="post">
					<table cellpadding="10px;"> 
						<tr>
						  <th>申请编号:</th>
					      <td><input name="appNo" id="appNoMain" class="easyui-textbox"  /></td>
					       <th>所属业务端:</th>
					      <td>
					      	<input id="myId" name="myId" class="easyui-textbox easyui-validatebox" />
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
		<!-- 胡总表 -->
		<table id="collec"></table>
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
