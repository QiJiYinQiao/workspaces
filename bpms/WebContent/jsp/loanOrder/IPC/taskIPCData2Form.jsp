<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!-- IPC数据（IPC调查员通过后）-->
<style type="text/css">
	#acceptTaskForm table input{border: none;}
	.table th{ text-align: right;}
	.table td{ text-align: left;}
</style>
<script type="text/javascript">
var $row;
var $datagrid;
$(function(){
	// 查看申请状态
	$row = $grid.datagrid('getSelected');
	$datagrid = $("#lookLoanOrderdg").datagrid({
		url : "loanOrderHis/loanOrderHisAction!findAllLoanOrderHis.action",
		width : 'auto',
		height : 340,
		pagination:false,
		rownumbers:true,
		border:true,
		singleSelect:true,
		nowrap:true,
		queryParams:{"loanOrderId":$row.loanOrderId},
		multiSort:false,
		fitColumns:true,
		columns : [ [ 
		              {field : 'agentTime',title : '受理时间',width : parseInt($(this).width()*0.1),sortable:true},
		              {field : 'roleName',title : '受理角色',width : parseInt($(this).width()*0.1)},
		              {field : 'assigneeName',title : '受理人',width : parseInt($(this).width()*0.1),align : 'left'},
		              {field : 'title',title : '审批简述',width :parseInt($(this).width()*0.1),align : 'left'},
		              /* {field : 'comment',title : '审批详情',width :parseInt($(this).width()*0.1),align : 'left'}, */
		              {field : 'id',title : '查看附件',width :parseInt($(this).width()*0.08),align : 'left',
			            	formatter:function(value,row,index){
			            		return "<a href='javascript:void(0);' onclick='lookAttachment("+index+");'>查看附件</a>　　" ;
			            	}  
		              }
		              ] ]
	});
	// 渲染姓名
	$("#acceptTaskForm input[name='name']").val($row.name);
	// 渲染身份证号
	$("#acceptTaskForm input[name='idNo']").val($row.idNo);

	$("#attType").combobox({
		valueField : 'code',
		textField : 'text',
		url:'common/commonAction!findTextArr.action?codeMyid=attachment_type',
		onLoadSuccess : function(){
			var val = $(this).combobox("getData");
			if(!$.isEmptyObject(val)){
				$attempData = val;
                $(this).combobox("select", $attempData[0]["code"]);
			}
		},
		editable:false
    });
	
	//查看附件
	$("#checkAttachment").click(function(){
		checkAttachementDetail('noauditId',$row.loanOrderId,'');
	});
	
	//上传附件
	$("#upploadAttachment").click(function(){
		var attType = $("#attType").combobox("getValue");
		fileUploadsDlg(attType,$row.loanOrderId,'');
	});	
	
	//渲染贷审委的信息
	$('#ipclrcUser01').combogrid({    
	    panelWidth:450,    
	    idField:'userId',    
	    textField:'name',  
		queryParams: {"loanOrderId": $row.loanOrderId,"roleCode":"IPCDaiShenWei1"},
		url : "loanOrder/loanOrderAction!findCandidatePersons.action?t="+ new Date(),
		columns : [ [ 
		              {field : 'name',title : '用户名',width : 100,align : 'center'},
		              {field : 'email',title : '邮箱',width : 150,align : 'center'},
		              {field : 'tel',title : '电话',width :150,align : 'center'},
		              {field : 'organization',title : '组织',width :220,align : 'center',
		            	    formatter:function(value,row){
			            	  	return value.fullName;  
							}
					  }, 
		              {field : 'description',title : '描述',width : 570,align : 'left'}
	              ] ]
	});
	
	
	//渲染贷审委的信息
	$('#ipclrcUser02').combogrid({    
	    panelWidth:450,    
	    idField:'userId',    
	    textField:'name',  
		queryParams: {"loanOrderId": $row.loanOrderId,"roleCode":"IPCDaiShenWei2"},
		url : "loanOrder/loanOrderAction!findCandidatePersons.action?t="+ new Date(),
		columns : [ [ 
		              {field : 'name',title : '用户名',width : 100,align : 'center'},
		              {field : 'email',title : '邮箱',width : 150,align : 'center'},
		              {field : 'tel',title : '电话',width :150,align : 'center'},
		              {field : 'organization',title : '组织',width :220,align : 'center',
		            	    formatter:function(value,row){
			            	  	return value.fullName;  
							}
					  }, 
		              {field : 'description',title : '描述',width : 570,align : 'left'}
	              ] ]
	});
	
	//渲染贷审委的信息
	$('#ipclrcUser03').combogrid({    
	    panelWidth:450,    
	    idField:'userId',    
	    textField:'name',  
		queryParams: {"loanOrderId": $row.loanOrderId,"roleCode":"IPCDaiShenWei3"},
		url : "loanOrder/loanOrderAction!findCandidatePersons.action?t="+ new Date(),
		columns : [ [ 
		              {field : 'name',title : '用户名',width : 100,align : 'center'},
		              {field : 'email',title : '邮箱',width : 150,align : 'center'},
		              {field : 'tel',title : '电话',width :150,align : 'center'},
		              {field : 'organization',title : '组织',width :220,align : 'center',
		            	    formatter:function(value,row){
			            	  	return value.fullName;  
							}
					  }, 
		              {field : 'description',title : '描述',width : 570,align : 'left'}
	              ] ]
	});
});
	
	// 提交表单信息
	function  submitTask(result) {
		// 判断是否含有贷审委
		if($("#ipclrcUser01").combogrid("getValue")==""
				||$("#ipclrcUser02").combogrid("getValue")==""
				||$("#ipclrcUser03").combogrid("getValue")==""){
			$.messager.alert("提示","请指定贷审委人员!","warning")
			return false;	
		}
		// 验证备注信息是否已经填写
		if($("#comment").val()=="" || $("#title").val() == ""){
			$.messager.alert("提示","请填写完备注信息后再进行提交!","warning")
			return false;
		}
		
		
		// 确认是否提交
		$.messager.confirm('提示', '点击按钮之后将进入下一个任务活动环节,此任务将对您不可见!', function(r){
			if (r){
				var data = {
					"comment" : $("#comment").val(),
					"title" :$("#title").val(),
					"result" :result,
					"loanOrderId" : $row.loanOrderId,
					"taskId": $row.taskId,
					"processingResult":"A",
					"ipclrcUser01":$("#ipclrcUser01").combogrid("getValue"),
					"ipclrcUser02":$("#ipclrcUser02").combogrid("getValue"),
					"ipclrcUser03":$("#ipclrcUser03").combogrid("getValue")
				}
				$.ajax({
					type : "POST",
					url : "loanOrder/loanOrderAction!submitTask.action",
					data : data,
					success : function(msg) {
						$grid.datagrid('reload');
						$taskFormDialog.dialog('close');
					}
				});
			}
		});
	}
	
	// 查看附件
	function lookAttachment(index){
		var row = getRowData($datagrid,index);
		// 附件信息
		checkAttachementDetail('noauditId',$row.loanOrderId,row.assignee,'2');
	}
	
	//查看稽核信息
	function checkAuditReportDetail(){
		window.open("jsp/loanOrder/IPC/ipcAuditInfoRecordDetail.jsp?loanOrderId="+$row.loanOrderId,
				"稽核信息详情", 'height=650, width=1000, top=200, left=400, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no')
	}
	
	//查看信审报告 
	function checkApplicationReportDetail(){
		window.open("jsp/loanOrder/IPC/ipcApplicationReportDetail.jsp?loanOrderId="+$row.loanOrderId+"&loanerId="+$row.loanerId,
				"稽核信息详情", 'height=650, width=1000, top=200, left=400, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no')
	}
	
	//查看呈报意见表
	function ipcMicrocreditOpinionDailog(){
		window.open("jsp/loanOrder/IPC/ipcMicrocreditOpinion.jsp?loanOrderId="+$row.loanOrderId+"&loanerName="+$row.name+"&loanerIdNo="+$row.idNo+"&purpose="+$row.purpose,
				"呈报意见详情", 'height=650, width=1000, top=200, left=400, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no')
	}

</script>
<!-- 受理任务 S -->
<div data-options="region:'north',title:'North Title',split:true">
	<div style="width: 980px;height: 190px;overflow: auto;">
		<form id="acceptTaskForm" method="post">
				<input name="id" id="id"  type="hidden"/>
				<input name="auditId" type="hidden" value="noauditId"/>
				 <table cellpadding="5px;">
					 <tr>
					    <th>客户姓名:</th>
						<td><input name="name" readonly="readonly" type="text" class="easyui-textbox easyui-validatebox" data-options="required:true"/></td>
						
						<th>身份证号:</th>
						<td><input name="idNo" readonly="readonly" type="text" class="easyui-textbox easyui-validatebox" data-options="validType:'idcard'"/></td>
					</tr>
					<tr>
						<th>贷审委1:</th>
						<td><input id="ipclrcUser01" name="ipclrcUsesr01 " readonly="readonly" type="text"/></td>

						<th>贷审委2:</th>
						<td><input id="ipclrcUser02" name="ipclrcUser02 " readonly="readonly" type="text"/></td>
						
						<th>贷审委3:</th>
						<td><input id="ipclrcUser03" name="ipclrcUser03" readonly="readonly" type="text"/></td>
					</tr>
					<tr>
					 	<th>备注简述:</th>
						<td colspan="5">
							<textarea id="title" name="title" class="easyui-validatebox easyui-textbox" style="width:100%;height:15px;"></textarea>
						</td>
					</tr>
					<tr>
					 	<th>备注详情:</th>
						<td colspan="5">
							<textarea id="comment" name="comment" class="easyui-validatebox easyui-textbox" style="width:100%;height:70px;"></textarea>
						</td>
					</tr>
					<tr>
						<th>
							附件类型:
						</th>
						<td>
							<input id="attType" class="easyui-textbox easyui-combobox" />
						</td>
						<td colspan="2">
							<a id="checkAttachment" href="javascript:void(0);" class="easyui-linkbutton">查看附件</a>	
							<a id="upploadAttachment" href="javascript:void(0);" class="easyui-linkbutton" >上传附件</a>	
						</td>
					</tr>
				 </table>
		</form>
	</div>

	<div style="width:980px;height:30px;">
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="checkApplicationReportDetail();">查看信审报告</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="checkAuditReportDetail();">查看稽核信息</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="ipcMicrocreditOpinionDailog();">查看呈报意见</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="submitTask('IPCData2Through',this);" >IPC审查员通过</a>
	</div>
	
	<div id="lookInfo" class="easyui-accordion" style="height: 380px;width: 980px;overflow: hidden;">
			<div title="备注信息" data-options="iconCls:'icon-cstbase',selected:true" >   
				<table id="lookLoanOrderdg" title="申请备注的信息"></table>
			</div>
	</div>
</div>   
<!-- 受理任务 E -->					

		
