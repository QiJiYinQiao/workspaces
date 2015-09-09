<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<style type="text/css">
	#acceptTaskForm table input{border: none;}
	table {border-radius: 5px;}
	.linkSpan{
	  padding:5px;
	  display:-moz-inline-box;
	  display:inline-block;
	  width:40%; 
	  text-align: center;
	}
	.linkSpanS{
	  padding:5px;
	  display:-moz-inline-box;
	  display:inline-block;
	  width:10%; 
	  text-align: center;
	}
	a{text-decoration: none;}
	a:hover {
	 color: #FF0000;
	}
	.table th{
		text-align: right;
	}
	.table td{
		text-align: left;
	}	
	textarea{resize: none;}
</style>
<script type="text/javascript">
var row,row1;
var investorId='${investorId}';
var investOrderId='${investOrderId}';
var taskId='${taskId}';
$(function(){
	//查询投资人详细信息
		$.ajax({
			url:'investor/investorAction!findInvestorById.action',
			data:'investorId='+investorId,
			dataType:'json',
			async : false,
			success:function(data){
				row = data;
			}
		})
	// 渲染姓名
	$("#acceptTaskForm input[name='name']").val(row.chName);
	// 渲染身份证号
	$("#acceptTaskForm input[name='idNo']").val(row.idNo);
	// 查看申请状态
	$("#lookLoanOrderdg").datagrid({
		url : "investOrderHis/investOrderHisAction!findAllInvestOrderHisList.action?investOrderId="+investOrderId,
		width : 'auto',
		height : 240,
		pagination:true,
		rownumbers:true,
		border:true,
		singleSelect:true,
		nowrap:true,
		columns : [ [ 
		              {field : 'agentTime',title : '受理时间',width : parseInt($(this).width()*0.1),sortable:true},
		              {field : 'roleName',title : '受理角色',width : parseInt($(this).width()*0.1)},
		              {field : 'assigneeName',title : '受理人',width : parseInt($(this).width()*0.1),align : 'left'},
		              {field : 'comment',title : '审批意见',width :parseInt($(this).width()*0.1),align : 'left'},
		              {field : 'aa',title : '查看附件',width :parseInt($(this).width()*0.09),align : 'left',
			            	formatter:function(value,row,index){
			            		return "<a href='javascript:void(0);' onclick='lookAttachment("+index+");'>查看附件</a>　　" ;
			            	}  
		              }
		              ] ]
	});
	$("#upload_form_div input:first").combobox({
		valueField : 'code',
		textField : 'text',
		url:'common/commonAction!findTextArr.action?codeMyid=attachment_type_invest',
		onLoadSuccess : function(){
			attempData = $("#upload_form_div input:first").combobox("getData");
			var val = $(this).combobox("getData");
            for (var item in val[0]) {
                if (item == "code") {
                    $(this).combobox("select", val[0][item]);
                }
            }
		},
		editable:false ,
    });
	
	loadAttachmentList('attachmentList',investOrderId);
});	

	
	/**==完善客户及合同信息==*/
	function completeOrderInfo(){
		//查询地址
		var addr=new Array();
			$.ajax({
				url:'investor/investorAction!findAddressById.action',
				data:'addressId='+row["commAddr"],
				dataType:'json',
				async : false,
				success:function(data){
					addr=data;
				}
			}); 
		//打开dialog
		$("#dd").dialog({
			title : '编辑',
			width : 920,
			height : 600,
			modal:true,
			//href : "jsp/investOrder/investOrderEditForm.jsp?investOrderId="+investOrderId,
			href:'investorderAndProducts/investorderAndProductsAction!gotoCompleteOrderInfo.action?orderId='+investOrderId,
			onLoad:function(){
				var f = $("#baseInfoForm");
				if(addr!=null){
					row["provinceId"]=addr["provinceId"];
					row["cityId"]=addr["cityId"];
					row["areaId"]=addr["areaId"]; 
					row["addressDetails"]=addr["addrDetails"];
				}
				f.form("load", row);
				renderProvinceSelect('provinceId','cityId','areaId');
				$("#provinceId").combobox("setValue",row.provinceId);
		        $("#cityId").combobox("setValue",row.cityId);
		        $("#areaId").combobox("setValue",row.areaId); 
		        initLinkPeopleGrid(row.investorId);	
		        //查询订单详情
		        $.ajax({
					url:'investOrder/investOrderAction!findByInvestOrderId.action',
					data:'investOrderId='+investOrderId,
					dataType:'json',
					async : false,
					success:function(data){
						row1 = data;
					}
				})
				$("#constractInfoForm").form("load",row1);
		        $("#orderStatus").val(row1.orderStatus.statusCode);
			} 
		}); 
	}
	
	/**======审批通过或驳回=======*/
	function  submitTask(result,object) {
		// 验证备注信息是否已经填写
		if($("#comment").val()==""){
			$.messager.alert("提示","请填写备注信息后再进行提交!","warning")
			return false;
		}
		// 确认是否提交
		$.messager.confirm('提示', '点击按钮之后将进入下一个任务活动环节,此任务将对您不可见!', function(r){
			if (r){
				var data = {
					"comment" : $("#comment").val(),
					"result" :result,
					"investOrderId" :investOrderId,
					"taskId": taskId,
					"processingResult":result=="DeptAssistantAgree"?"A":"B"
				}
				$.ajax({
					type : "POST",
					url : "investOrder/investOrderAction!submitTask.action",
					data : data,
					success : function(msg) {
						parent.$.modalDialog.openner.datagrid('reload');
						parent.$.modalDialog.handler.dialog('close');
					}
				});
			}
		});
	}
	/**=============上传附件及显示已上传附件列表===================*/
	//上传附件方法
		function fileUploads(obj){
			var id = $(obj).parent().prev().children().attr("id");
			var listId = $(obj).parent().prev().prev().attr("id");
			var cDivClone = $("#"+id).children("div:first");
			var cDiv = $("#"+id).children();
			var att_types = ""; //附件类型
			var fileNames = ""; //附件名
			var fileIds = [];	//附件id
			for(var i = 0 ; i < cDiv.length; i++){
				var cDivId = cDiv[i].id;
				var att_type = $("#"+cDivId+" input:first").combobox("getValue");
				var fileName = $("#"+cDivId+" input[name='fileName']").val();
				var fileId = $("#"+cDivId+" input:last").attr("id");
				var fileValue = document.getElementById(fileId).value;
				if(""==att_type || ""==fileName || ""==fileValue){
					$.messager.alert("提示","请填写完整信息","info");
					return false;
				}
				att_types  += att_type + ",";
				fileNames += fileName + ",";
				fileIds[i] = fileId;
			}
			parent.$.messager.progress({
				title : '提示',
				text : '文件上传中，请稍后....'
			});
			$.ajaxFileUpload({
				url:'attachment/attachmentAction!saveInvestAttachment.action',
				data:{"fileName":fileNames,"attType":att_types,"investOrderId" : investOrderId},
				fileElementId:fileIds,
				secureuri:false,
				dataType:'text',
				async : false,
				success:function(data,status){
					parent.$.messager.progress('close');
					loadAttachmentList(listId,investOrderId);
					data = $.parseJSON(data);
					$.messager.alert("提示",data.message,"info");
					$("#"+id).empty().append(cDivClone);
					$(cDivClone).children("a:first").click();
					$(cDivClone).remove();
				},
				error: function(){
					
				}
			});
		}
		
		/**=========加载附件列表========*/ 
		function loadAttachmentList(listId,investOrderId){
			$("#"+listId).empty();
			var str = "<div id='firstDiv"+listId+"' style='width:50%;height:30px;float: left;'><span class='linkSpan'>附件名称</span></a><span class='linkSpan'>附件类型</span><span class='linkSpanS'>操作</span></div><div id='secondDiv"+listId+"' style='width:50%;height:30px;float: left;'><span class='linkSpan'>附件名称</span></a><span class='linkSpan'>附件类型</span><span class='linkSpanS'>操作</span></div>";
			$("#"+listId).append(str);
			$.ajax({
				url : "attachment/attachmentAction!findAttachmentByOrderTypeAndOrderId.action",
				data : {"orderType":"investorOrder","investOrderId":investOrderId},
				type : "post",
				async : false,
				success : function(data){
					if(data.length==0){
						$("#firstDiv"+listId).empty();
						$("#secondDiv"+listId).empty();
					}else if(data.length==1){
						$("#secondDiv"+listId).empty();
					} 
					if(data){
						var attId;
						var attName;
						var attType;
						var linkStr = "";
						var j = 0;
						$.each(data,function(i,item){
							attId = data[i].attId;
							attName = data[i].attName;
							attType = data[i].attType;
							attTypeName = data[i].attTypeName;
							linkStr = "<div style='width:50%;height:30px;float: left;'><input type='hidden' name='attId' value='"+attId+"' /><a target='_blank' href='jsp/openoffice/documentView.jsp?attId="+attId+"'><span class='linkSpan'>"+attName+"</span></a><span class='linkSpan'>"+attTypeName+"</span><a href='javascript:void(0);' class='attachBackLinkButton' onclick=\"deleteAttachment(this,'"+attId+"');\">删除</a><a href='javascript:void(0);' class='attachBackLinkButton' onclick=\"downloadAttachment('"+attId+"');\">　下载</a></div>";
							$("#"+listId).append(linkStr);
						});
					}
				},
				error : function(){
					
				}
			});
		}
	
		// 根据行索引获取行信息
		function getRowData (index) {
		    if (!$.isNumeric(index) || index < 0) { return undefined; }
		    var rows = $("#lookLoanOrderdg").datagrid("getRows");
		    return rows[index];
		}
		//查看附件信息
		function lookAttachment(index){
				var row = getRowData(index);
				$("#lookAttachmentList").datagrid({
					url : "attachment/attachmentAction!findAllAttachmentList.action",
					width : 'auto',
					height : 430,
					pagination:false,
					rownumbers:true,
					border:false,
					singleSelect:true,
					nowrap:true,
					queryParams:{"orderId":row.investOrderId,"userId":row.assignee,"orderType":"attachment_type_invest"},
					multiSort:false,
					columns : [ [ 
					              {field : 'attName',title : '附件名称',width : 200,sortable:true,align:'center'},
					              {field : 'attTypeName',title : '附件类型',width : 160,align:'center'},
					              {field : 'creatorName',title : '创建者',width : 170,align:'center'},
					              {field : 'id',title : '查看附件',width :220,align:'center',formatter:function(value,row,index){
					            		var result = "<a target='_blank' href='jsp/openoffice/documentView.jsp?attId="+row.attId+"'>在线预览</a>　　" ;
					            			result += "<a target='_blank' href='javascript:void(0);' onclick=\"downloadAttachment('"+row.attId+"');\">下载</a>　　" ;
					            		return result;
					              }}
				    ] ]
				});
				$('#lookInfo').accordion("select","附件信息"); 
		}
</script>
		<!-- 受理任务 S -->
		<div data-options="region:'north',title:'North Title',split:true">
			<div style="height: 280px;overflow: auto;" >
			<form id="acceptTaskForm" method="post">
				 <input name="id" id="id"  type="hidden"/>
				 <input name="auditId" type="hidden" value="noauditId"/>
				 <table class="table" cellpadding="5px;">
					 <tr>
					    <th>客户姓名:</th>
						<td><input name="name" readonly="readonly" type="text"/></td>
					</tr>
					<tr>
						<th>身份证号:</th>
						<td><input name="idNo" readonly="readonly" type="text"/></td>
					</tr>
					<tr>
					 	<th>备注:</th>
						<td colspan="3">
							<textarea id="comment" name="comment" class="easyui-validatebox easyui-textbox" style="width:300px;height:70px;"></textarea>
						</td>
					</tr>
				 </table>
				<div id="attachmentList" style="width:100%;display:block;float:left;">
				</div>
				<div id="upload_form_div_add">
					<div id="upload_form_father_idDiv" style="width:100%;">
						<div id="upload_form_div">
							<font size="2" style="font-weight: bold;">　上传附件:&nbsp;</font>
							<input class="easyui-textbox easyui-combobox" type="text" />
							<input name="fileName" type="text" placeholder="请输入附件名">
							<input id="file" name="file" type="file"  onchange="fileChange(this);" > 
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addACredential(this);">添加</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeACredential(this);">删除</a> 
						</div>
					</div>
				</div>
				<div id="upload_form" style="width: 100%; height: 30px; text-align: right;">
					<a href="javascript:void(0);" class="easyui-linkbutton" onclick="fileUploads(this)">上传附件</a>
				</div> 
			</form>
			</div>
			
			<div style="width: 880px;height:30px;">
				<a href="javascript:void(0);" class="easyui-linkbutton" onclick="completeOrderInfo()">完善客户及合同信息</a>
				<a href="javascript:void(0);" class="easyui-linkbutton" onclick="submitTask('DeptAssistantAgree',this);">审批通过</a>
				<a href="javascript:void(0);" class="easyui-linkbutton" onclick="submitTask('DeptAssistantReject',this);">审批驳回</a>
			</div>
		    <div id="lookInfo" class="easyui-accordion" style="height: 300px;width: 980px;overflow: hidden;">
			    <div title="备注信息" data-options="iconCls:'icon-cstbase',selected:true" >   
					<table id="lookLoanOrderdg" title="申请备注的信息"></table>
				</div>
			    <div title="附件信息" data-options="iconCls:'icon-cstbase'" >   
					<table id="lookAttachmentList" title="申请附件的信息"></table>
				</div>
			</div>
			
		</div>   
		<!-- 受理任务 E -->		
		<div id="dd"></div>	