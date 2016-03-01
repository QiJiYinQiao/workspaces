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
	
	//渲染附件类型下拉列表
	$("#attType").combobox({
		valueField : 'code',
		textField : 'text',
		url:'common/commonAction!findTextArr.action?codeMyid=attachment_type_invest',
		onLoadSuccess : function(){
			var val = $(this).combobox("getData");
			if(!$.isEmptyObject(val)){
                $(this).combobox("select", val[0]["code"]);
			}
		},
		editable:false 
    });	
		
	// 渲染附件列表
	//查看附件
	$("#checkAttachment").click(function(){		
		console.info("")
		checkAttachementDetail4InvestOrder('noauditId',investOrderId,row.assignee,'2');
	});
	
	//上传附件
	$("#upploadAttachment").click(function(){
		var attType = $("#attType").combobox("getValue");
		investfileUploadsDlg(attType,investOrderId);
	});			
			
	//弹出新引入的第三方文件上传组件，附件类型，订单ID，附件名称
	function investfileUploadsDlg(attType, investOrderId){
		if(attType==""){
			$.messager.alert("提示","请先选择附件类型!","info");
			return false;
		}
		window.open('jsp/investOrder/investOrderAttachmentForm.jsp?investOrderId='+investOrderId+'&attType='+attType,
				"附件详情", "height="+$(window).height()*0.95+", width="+$(window).width()*0.95+", top=100, left=200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");
	}			
});	
	
	
	
	/**==完善客户及合同信息==*/
	function completeOrderInfo(){
		console.info("completeOrderInfo ----> investOrderId = "+investOrderId);
		console.info(row);
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
			title : '完善理财客户合同信息',
			width : 920,
			height : 600,
			modal:true,
			//href : "jsp/investOrder/investOrderEditForm.jsp?investOrderId="+investOrderId,
			href:'investorderAndProducts/investorderAndProductsAction!gotoCompleteOrderInfo.action?orderId='+investOrderId,
			onLoad:function(){
				/* 渲染基本客户信息 Tab页面*/
				var f = $("#basicInvestorClientInfoForm");
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
		        
		        /* 渲染紧急联系人 Tab页面*/
		        //初始化紧急联系人列表		        
		        initLinkPeopleGrid(row.investorId);
		        
		        /* 渲染合同信息 Tab页面*/
		        //查询InvestOrder订单详情
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
		        /* $("#orderStatus").val(row1.orderStatus.statusCode); */
		        //保存流程状态OrderStatus对象。
		        if(row1.orderStatus != null){		        	
		        	$("#orderStatus").val(row1.orderStatus.statusCode);		        
		        }else{
		        	console.info("未提交流程。流程状态OrderStatus对象为NULL。");
			        console.info(row1);		        
		        }		        
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
					"taskId": taskId
				}
				$.ajax({
					type : "POST",
/* 					url : "investOrder/investOrderAction!submitTask.action", */
					url : "investApply/investApplyAction!submitTask.action",
					data : data,
					success : function(msg) {
						parent.$.modalDialog.openner.datagrid('reload');
						parent.$.modalDialog.handler.dialog('close');
					}
				});
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
				/* url : "attachment/attachmentAction!findAllAttachmentList.action", */
				url : "attachment/attachmentAction!findAllAttachmentList4BanLiRenwu.action",
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
				            			result += "<a target='_blank' href='javascript:void(0);' onclick=\"downloadAttachment4InvestOrder('"+row.attId+"');\">下载</a>　　" ;
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
					 
					 <!-- 显示客户基本信息区域 -->
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
						<tr>
							<th>附件类型:</th>
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
			
			<div style="width: 880px;height:30px;">
				<!-- <a href="javascript:void(0);" class="easyui-linkbutton" onclick="completeOrderInfo()">完善客户及合同信息</a> -->
				<a href="javascript:void(0);" class="easyui-linkbutton" onclick="submitTask('JieSuanZhuanYuanTongGuo',this);">审批通过</a>
				<a href="javascript:void(0);" class="easyui-linkbutton" onclick="submitTask('JieSuanZhuanYuanBoHui',this);">审批驳回</a>
				<a href="javascript:void(0);" class="easyui-linkbutton" onclick="submitTask('JieSuanZhuanYuanJuJue',this);">审批拒绝</a>
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