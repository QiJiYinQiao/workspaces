$(function(){
	//上传附件
	$("#upploadAttachment").click(function(){
		fileUploadsDlg($selRow.appNo);
	});
	//查看附件
	$("#checkAttachment").click(function(){
		checkAttachementDetail($selRow.appNo);
	});
	//处理formKey中传递过来的json字符 动态生成操作按钮 以及跳转规则
	var formKey = $selRow.formKey;
	if(null != formKey && "" != formKey && formKey.indexOf("?") != -1){
		var jsonStr = formKey.substring(formKey.indexOf("?")+1);
		var formKeyJson = JSON.parse(jsonStr);//截取formKey中json对象
		$("#formKeyJson").val(jsonStr);//将对象存入隐藏域中 在受理任务方法中 对财富端、借款端 或 角色比对提供条件
		//获取type类型 0 为判断财富端借款端 1 为比对角色以上或以下职级 2为申请调整标识
		var type = formKeyJson.hasOwnProperty("type")?formKeyJson.type:"";
		//0为只有通过和驳回操作  1为只有通过操作 2为具有申请调整按钮和通过按钮
		var btType = formKeyJson.hasOwnProperty("btType")?formKeyJson.btType:"";
		//只有申请调整时才显示重新申请等按钮
		if(btType != 2){
			if(type == 2){
				$("#saveTaskDiv").remove();
			}else{	
				$("#applyDiv").remove();
			}
		}
		//根据type进行隐藏相应的操作按钮
		switch (btType) {
			case 0:
				$("#refusalButton").remove();
				break;
			case 1:
				$("#rejectButton").remove();
				$("#refusalButton").remove();
				break;
			case 2:	
				$("#rejectButton").remove();
				$("#refusalButton").remove();
				$("#applyForAdjustmentSubmit").remove();
				$("#applyForAdjustmentBack").remove();
				$("#upploadAttachment").remove();
				$("#checkAttachment").remove();
				break;
			case 3:			//通过和拒绝及上传附件查看附件
				$("#rejectButton").remove();
				$("#applyForAdjustmentTZ").remove();
				$("#applyForAdjustmentSubmit").remove();
				$("#applyForAdjustmentBack").remove();
				break;
			default:
				break;
		}
	}else{
		$("#applyDiv").remove();
	}
});