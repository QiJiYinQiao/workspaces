var $dg;
var $grid;
var $dialog;
var $$row;
var empSalWidth = parseInt($(window).width() * 0.8);
if(empSalWidth > 1000){
	empSalWidth = 1000;
}

//保存方法
function saveEmpSal(){
	var isValid = $("#EmpSalAppForm").form('validate');
   	if(!isValid){
   		return false;
   	}  	
	$.ajax({
		   url: "empSalPositionChgAppController/addOrUpdateEmpSalPositionChgApp.do",
		   type: "POST",
		   data:$('#EmpSalAppForm').serialize(),
          dataType:'JSON',
		   success: function(msg){
			   if(msg.status){
				   $dialog.dialog('close');
			   }
			   $.messager.alert(msg.title,msg.message,'info');
		   }
	});
}


//签收任务
function singForTask(taskID){
	$.ajax( {
		type : "POST",
		url : 'empSalPositionChgAppController/signForTask.do',
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


// 根据索引获取每一行的基本信息
function getRowData (index) {
    if (!$.isNumeric(index) || index < 0) { return undefined; }
    var rows = $grid.datagrid("getRows");
    return rows[index];
}

function saveTask(index){
	var selectedRow = getRowData(index);
	$selRow = selectedRow;
	$$row = $selRow;
	$dialog=$("<div></div>").dialog({
		width : empSalWidth,
		height : $(this).height()*0.7,					
		title : '受理任务',
		href : selectedRow.formKey,
	    onLoad:function(){
	    	var taskForm = $("#taskForm");
	    	taskForm.form("load",selectedRow);
	    	$("#taskForm #businessID").val(selectedRow.efaId);
	    	$("#taskForm #taskID").val(selectedRow.taskID);
	    },
		modal:true,
		resizable:false,
		iconCls:'icon-add',
		closed: false,
	    buttons : [{
				text : '关闭',
				iconCls : 'icon-cancel',
				handler : function() {
					$dialog.dialog('close');
			}
		}],onClose:function(){
	    	//刷新列表
	    	$("#dg").datagrid("reload");
	    	//关闭弹窗
	    	$(this).dialog('destroy');
	    }
	}); 
}

//查看审批意见 
	function showInvestProductDetails(index){
		$$row = getRowData(index);
		$dialog=$("<div></div>").dialog({
		/* 动态显示Dialog的标题	*/
		width : empSalWidth,
		height : $(this).height()*0.5,					
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
	
	
//查看流程图
function showImage(index){
	var selectedRow = getRowData(index);
	//获取taskID
	$.ajax( {
		type : "POST",
		url : 'empSalPositionChgAppController/getProcDefKey.do',
		data : "busID="+selectedRow.efaId,
		dataType:'JSON',
		success : function(iJson) {		  
	 		var src = "workflowAction/showProcessImg.do?taskId="+iJson.taskID+"&imgName="+iJson.procDefKeyDes;
	 		$('#imageDialog').dialog("open");
	 		$("#image").attr("src", src);
		}
	});
}	