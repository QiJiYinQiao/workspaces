/**
 * 通过表单提交数据并根据服务器响应头，下载文件
 * @param url 请求地址
 * @param data 请求数据 Object(type)如果没有参数请传递空对象{}
 */
function downFileByFormPost ( url, data ) {
	var $form, framentTemp = [], frament = [];
	if ($.type(data)!="object") data = {};
	// 创建一个form元素
	$form = $('<form>').prop({
		method: "post",
		action: url,
		//target: "_blank"
	});
	$form.appendTo(document.body);
	// 组织数据
	var domStr = '';
	if(data){
		for(var k in data){
			domStr += '<input type="text" name="'+k+'" value="'+data[k]+'" />';
		}
		$form.append(domStr);
	}
	// 提交
	$form.submit();
	$form.remove();
}

/**
 * 根据索引获取对象名称
 * @param target Grid对象
 * @param index 索引
 * @returns 索引的对应行的信息
 */
function getRowData (target,index) {
	if (!$.isNumeric(index) || index < 0) { return undefined; }
	if ($.isEmptyObject(target)) { return undefined; }
    var rows = target.datagrid("getRows");
    return rows[index];
}


//禁用form表单
function disableForm(id){
	$("#"+id+" input,select").each(function(i){
		if($(this).hasClass('easyui-textbox')) {
			$(this).attr({"disabled":"disabled"});
		}else if($(this).hasClass("easyui-validatebox")){
			$(this).attr({"disabled":"disabled"});
	    }else if($(this).hasClass('easyui-combobox')){
	    	$(this).combobox("disable");
	    }else if($(this).hasClass('easyui-combogrid')){
	    	$(this).combogrid("disable");
	    }else if($(this).hasClass('easyui-combotree')){
	    	$(this).combotree("disable");
	    }else if($(this).hasClass('easyui-numberbox')){
	    	$(this).numberbox("disable");
	    }else if($(this).hasClass('easyui-datetimebox')){
	    	$(this).datetimebox("disable");
	    }else if($(this).hasClass('easyui-datebox')){
	    	$(this).datebox("disable");
		}else{
			$(this).attr({"disabled":"disabled"});
		}
	})
}


//启用form表单
function enableForm(id){
	$("#"+id+" input[class^='easyui-']").each(function(i){
		if($(this).hasClass("easyui-textbox easyui-validatebox")){
			$(this).attr("disabled",false);
		}else if($(this).hasClass("easyui-datebox")){
			$(this).datebox("enable");
		}else if($(this).hasClass("easyui-combobox")){
			$(this).combobox("enable");
		}else if($(this).hasClass("easyui-numberbox")){
			$(this).numberbox("enable");
		}else{
			$(this).attr("disabled",false);
		}
	})
}
/**===============================文件上传到图片服务器===============================================*/
//文件上传对话框  参数：申请编号,子项id
function fileUploadsDlg(appNo,detailId){
	if(undefined==detailId){
		detailId ='';
	}
	window.open('jsp/attachment/attachmentForm.jsp?appNo='+appNo+'&detailId='+detailId,
			"附件详情");
	//, "height="+$(window).height()*0.95+", width="+$(window).width()*0.95+", top=100, left=200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no"
}
		
//附件详情对话框 3个参数 ：申请编号(appNo)，用户ID(userId)，是否是详情 (isdetail:1代表true,子项id(detailId)
function checkAttachementDetail(appNo,userId,isDetail,detailId){
	if(undefined==userId){
		userId = '';
	}
	if(undefined==isDetail){
		isDetail ='';
	}
	if(undefined==detailId){
		detailId ='';
	}
	window.open('jsp/attachment/attachementDetail.jsp?appNo='+appNo+'&userId='+userId+'&isDetail='+isDetail+'&detailId='+detailId,
			"附件详情");
	//, "height="+$(window).height()+", width="+$(window).width()*0.95+", top=100, left=200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no"
}

/**===============================文件上传到图片服务器end===============================================*/