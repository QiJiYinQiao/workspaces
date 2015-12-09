/** ------------- 变量、json、基础查询和方法的javascript脚本 -------------*/
var $dg;
var $grid;
var $selRow;
var $$row;
var $dialog;
var $dialog1;
var procStateJson = [{ 
				"id":"", 
				"text":"全部状态", 
				"selected":true 
				},{ 
				"id":1, 
				"text":"初始状态" 
				},{ 
				"id":2, 
				"text":"审批中" 
				},{ 
				"id":3, 
				"text":"已完成"
				},{ 
				"id":4, 
				"text":"已失效" 
				},{ 
				"id":5, 
				"text":"已撤销" 
				}] ;

//执行高级查询
function doSearch(){			
	$("#dg").datagrid("load",{
		appDept:$("#appDept").val(),
		procStatus:$("#procStatus").combobox("getValue"),
		appDateBefore:$('#appDateBefore').datebox('getValue'),
		appDateAfter:$('#appDateAfter').datebox('getValue')
	}); 
}

// 根据索引获取每一行的基本信息
function getRowData (index) {
    if (!$.isNumeric(index) || index < 0) { return undefined; }
    var rows = $grid.datagrid("getRows");
    return rows[index];
}

//重置条件
function clearAdvancedQueryConditions(){
	//1、清空高级查询各组件内容
	$("#searchForm").form("clear");
	//2、datagrid重新加载
	$("#dg").datagrid("load",{});
}


/** ------------- 增删改查操作javascript -------------*/

function savePPEScrapApp(){
	$.ajax({
		   url: "ppeScrapAppController/addOrUpdatePPEScrapApp.do",
		   type: "POST",
		   data:$('#ppeScrapAppForm').serialize(),
          dataType:'JSON',
		   success: function(msg){
			   if(msg.status){
				   $("#ppeScrapAppForm input[name='psaId'][type='hidden']").val(msg.data[0]);
				   $("#ppeScrapAppForm input[name='appNo'][type='hidden']").val(msg.data[1]);
				   disableForm('ppeScrapAppForm');
			   }
			   $.messager.alert(msg.title,msg.message,'info');
		   }
		});
}


//禁用form表单
function disableForm(formId){
	$("#"+formId+" a[iconCls = 'icon-edit']").show();
	$("#"+formId+" a[iconCls = 'icon-ok']").hide();
	$("#"+formId+" [class^='easyui-']").each(function(i){
		if($(this).hasClass("easyui-textbox")){
			$(this).attr("disabled",true);
		}else if($(this).hasClass("easyui-datebox")){
			$(this).datebox("disable");
		}else if($(this).hasClass("easyui-combobox")){
			$(this).combobox('disable');
		}else if($(this).hasClass("easyui-numberbox")){
			$(this).numberbox('disable');
		}else if($(this).hasClass("easyui-combogrid")){
			$(this).combogrid("disable");
		}else if($(this).hasClass("easyui-textarea")){
			$(this).attr("disabled",true);
		}
	});
}
//解禁form
function editForm(obj){
	var formId = $(obj).parents("form").attr("id");
	$("#"+formId+" a[iconCls^='icon-edit']").css('display','none');
	$("#"+formId+" a[iconCls^='icon-ok']").css('display','inline-block');
	$("#"+formId+" [class^='easyui-']").each(function(i){
		if($(this).hasClass("easyui-textbox")){
			//$(this).attr("disabled",false);
		}else if($(this).hasClass("easyui-datebox")){
			$(this).datebox("enable");
		}else if($(this).hasClass("easyui-combobox")){
			$(this).combobox("enable");
		}else if($(this).hasClass("easyui-numberbox")){
			$(this).numberbox("enable");
		}else if($(this).hasClass("easyui-combogrid")){
			$(this).combogrid("enable");
		}else if($(this).hasClass("easyui-textarea")){
			$(this).attr("disabled",false);
		}
	})
}

function toSaveOrUpdateInvestProductOpenDlg(){
	var psaId = $('#businessID').val();
	var hrefStr = "ppeScrapAppController/toAddPPEScrapApp.do?psaId="+psaId;
	var str = "调整固定资产报废项";
	if(null != $("#isCwzg").val()){
		hrefStr = "ppeScrapAppController/toAddPPEScrapAppCwzg.do?psaId="+psaId;
		str="调整固定资产报废项资产净值残值";
	}
	$dialog=$("<div></div>").dialog({
		width : 900,
		height : 650,					
		title : str,
		href : hrefStr,
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

/** ------------- 工作流相关页面的javascript脚本 -------------*/
//受理任务
function saveTask(index){
	var selectedRow = getRowData(index);
	$selRow = selectedRow;
	$$row = $selRow.ppeApp;
	var formKey = selectedRow.ppeApp.formKey;
	$dialog1=$("<div></div>").dialog({
		/* 动态显示Dialog的标题	*/
		width : 830,
		height : $(this).height()*0.8,					
		title : '受理任务',
		href : formKey,
	    onLoad:function(){
	    	var taskForm = $("#taskForm");
	    	taskForm.form("load",selectedRow.ppeApp);
	    	taskForm.form("load",selectedRow);
	    	$("#taskForm #businessID").val(selectedRow.ppeApp.psaId);
	    	$("#taskForm #taskID").val(selectedRow.ppeApp.taskID);
	    },
		modal:true,
		resizable:false,
		iconCls:'icon-add',
	    buttons : [{
			text : '关闭',
			iconCls : 'icon-cancel',
			handler : function() {
				$dialog1.dialog('close');
		}
		}],onClose:function(){
	    	//刷新列表
	    	$("#dg").datagrid("reload");
	    	//关闭弹窗
	    	$(this).dialog('destroy');
	    }
	}); 
}


//签收任务
function singForTask(taskID){
		$.ajax( {
			type : "POST",
			url : 'ppeScrapAppController/signForTask.do',
			data : "taskID="+taskID,
			dataType:'JSON',
			success : function(iJson) {		    					    				
				if(iJson.status){
					//刷新列表		    						
					$("#dg").datagrid("load",{});
				}
				$.messager.alert(iJson.title,iJson.message,'info');   					
			}
		});
}		

//查看流程图
function showImage(index){
	ajaxLoading("loading...");
	var selectedRow = getRowData(index);
	//获取taskID
	$.ajax( {
	type : "POST",
	url : 'workflowAction/getTaskIDByBusID.do',
	data : "busID="+selectedRow.ppeApp.psaId+"&definitionKey="+selectedRow.ppeApp.definitionKey,
	dataType:'JSON',
	success : function(iJson) {		  
 		var src = "workflowAction/showProcessImg.do?taskId="+iJson+"&imgName="+selectedRow.ppeApp.resourcesName;
 		$('#imageDialog').dialog("open");
 		$("#image").attr("src", src);			
 		ajaxLoadEnd();
	}
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



/** 增加或者修改理财产品的数据**/
function saveTaskFunc(isSuccess){
	//校验理财产品输入的信息
	var isValid = $("#taskForm").form('validate');
	if(!isValid){
		return false;
	}else{
		var remark = $("#taskComment").val();
		$("#result").val(isSuccess);
		$("#isSuccess").val(isSuccess);
		var data1 = $('#taskForm').serialize();
		if(data1.indexOf("&appNo")==-1){
			data1+=("&appNo="+$selRow.ppeApp.appNo);
		}
		if((isSuccess == 0 || isSuccess == 2)  && remark == ""){
			$.messager.alert("提示","请填写备注信息!","info");
			return false;
		}
		$.ajax({
			type: "POST",
			url:"ppeScrapAppController/saveTaskPPEScrapApp.do",
			data:data1,
			success: function(iJson) {
				if(iJson.status){
					$dialog1.dialog("close");//关闭弹窗
				}
				$.messager.alert(iJson.title,iJson.message,'info');
			}
		}); 
	}  	
}

