$(function(){
	//上传附件
	$("#upploadAttachment").click(function(){
		fileUploadsDlg($selRow.stampTurnover.appNo);
	});
	//查看附件
	$("#checkAttachment").click(function(){
		checkAttachementDetail($selRow.stampTurnover.appNo);
	});
});

// 根据索引获取每一行的基本信息
function getRowData (index) {
    if (!$.isNumeric(index) || index < 0) { return undefined; }
    var rows = $grid.datagrid("getRows");
    return rows[index];
}

//查看流程图
function showImage(index){
	var selectedRow = getRowData(index);
	//获取taskID
	$.ajax( {
	type : "POST",
	url : 'workflowAction/getTaskIDByBusID.do',
	data : "busID="+selectedRow.stampTurnover.staId+"&definitionKey="+selectedRow.stampTurnover.definitionKey,
	dataType:'JSON',
	success : function(iJson) {		  
		var src = "workflowAction/showProcessImg.do?taskId="+iJson+"&imgName="+selectedRow.stampTurnover.resourcesName;
 		$('#imageDialog').dialog("open");
 		$("#image").attr("src", src);	
	}
	});
}

//重置条件
function clearAdvancedQueryConditions(){
	//1、清空高级查询各组件内容
	$("#searchForm").form("clear");
	//2、datagrid重新加载
	$("#dg").datagrid("load",{});
}

/** 增加或者修改理财产品的数据**/
function saveTaskFunc(isSuccess){
	//校验理财产品输入的信息
	var remark = $("#taskComment").val();
	$("#result").val(isSuccess);
	$("#isSuccess").val(isSuccess);
	var data1 = $('#taskForm').serialize();
	if(data1.indexOf("&appNo")==-1){
		data1+=("&appNo="+$$row.appNo);
	}
	if((isSuccess == 0 || isSuccess == 2)  && remark == ""){
		$.messager.alert("提示","请填写备注信息!","info");
		return false;
	}
	 $.ajax({
		type: "POST",
		url:"stampTurnoverAppController/saveTaskStampTurnoverApp.do",
		data:data1,
		async: false,//默认true设置下，所有请求均为异步请求
		cache: true,
	    success: function(iJson) {    	    	
 	    	if(iJson.status){
 	    		$dialog.dialog("close");//关闭弹窗
	    		$("#dg").datagrid("reload");//刷新表格
	    	}
 	    	$.messager.alert(iJson.title,iJson.message,'info');
	    } 
	});
}

	
	function toSaveOrUpdateInvestProductOpenDlg(){
		var staId = $('#businessID').val();
		$dialog=$("<div></div>").dialog({
			width : 930,
			height : 650,					
			title : '调整印章移交申请',
			href : "stampTurnoverAppController/toAddStampTurnoverApp.do?staId="+ staId,
			modal:false,
			resizable:true,
			iconCls:'icon-add',
			onClose:function(){
				$$row=null;
		    	$(this).dialog('destroy');
			},
			buttons : [{
				text : '关闭',
				iconCls : 'icon-cancel',
				handler : function() {
					$dialog.dialog('close');
				}
			}],
			closed: false
		}); 
	}
	
	//查看审批意见 
	function showInvestProductDetails(index){
		$$row = getRowData(index);
		$dialog=$("<div></div>").dialog({
			/* 动态显示Dialog的标题	*/
			width : 800,
			height : 450,					
			title : "查看审批意见",
			href : "jsp/ad/optionsList.jsp",
			onClose:function(){
				$$row=null;
		    	$(this).dialog('destroy');
			},
			modal:true,
			resizable:false,
			iconCls:'icon-add',
			closed: false
		});
	}