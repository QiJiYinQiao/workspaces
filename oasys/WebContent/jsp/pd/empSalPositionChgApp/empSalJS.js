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

// 根据索引获取每一行的基本信息
function getRowData (index) {
    if (!$.isNumeric(index) || index < 0) { return undefined; }
    var rows = $grid.datagrid("getRows");
    return rows[index];
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