<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <form id="purchaseAppForm" method="post" style="width: 875px;margin-left:5px;">
    <fieldset>
    <legend><img src="extend/fromedit.png" style="margin-bottom: -3px;"/>基本信息</legend>
    <input name="totalAmt" type="hidden"/><!-- 合计金额 -->
    <input name="appNo" type="hidden"/><!-- 申请编号-->
    <input name="paId" type="hidden"/><!-- 主键id -->
    <input name="appType" value="1" type="hidden"/><!-- 申请类型 -->
    <input name="appTypeOther" type="hidden"/><!-- 其他类型 -->
    <input name="applicantNo" type="hidden"/><!-- 申请人id -->
    <input name="appDept" type="hidden"/><!-- 申请人部门 -->
    <input name="appStatus" type="hidden"/><!-- 申请状态 -->
    <input name="procStatus" type="hidden"/><!-- 流程状态 -->
    <input name="appDate" type="hidden"/><!-- 申请状态 -->
    <table class="table" style="width: 100%">
       <tr>
          <th class="textStyle">计划到货时间:</th>
          <td><input name="planRecDate" class="easyui-datebox" editable="false"/></td>
       </tr>
       <tr>
          <th class="textStyle">备注:</th>
	      <td>
	          <textarea name="remark" rows="5" cols="20" class="easyui-textbox easyui-validatebox" readonly="readonly" data-options="validType:'length[0,200]'" style="width: 720px;height: 50px;resize:none;"></textarea>
	      </td>
       </tr>
    </table>
    </fieldset>
    </form>
    <div style="width: 871px;margin-left:7px;margin-top: 2px;">
       <table id="purchaseAppAttachGrid"></table>
    </div>
    
   
<script type="text/javascript">
var $sonDialog;
var $rowdata;
var url = "purchaseAppAttachController/findAllPurchaseAppAttachList.do";
$(function(){
	refreshPurchaseAppAttachGrid();
})
function refreshPurchaseAppAttachGrid(){
	if($row!=null){
		$("#purchaseAppForm").form("load",$row);
		url = "purchaseAppAttachController/findAllPurchaseAppAttachList.do?appNo="+$row.appNo;
		createPurchaseAppAttachGrid(url);
	}else{
		createPurchaseAppAttachGrid(url);
	}
};
function createPurchaseAppAttachGrid(url){
	$("#purchaseAppAttachGrid").datagrid({
		url:url,
		width: 'auto',
		height : $(this).height()-480,
		pagination:false,
		rownumbers:true,
		border:true,
		singleSelect:false,
		nowrap:true,//如果为true，则在同一行中显示数据。设置为true可以提高加载性能。
		remoteSort:false,//定义是否从服务器对数据进行排序。
		striped:true,//是否显示斑马线
		columns:[[
                {field : 'articleNameText',title : '物品名称',width : 80,align : 'center'},
	            {field : 'model',title : '型号规格',width : 80,align : 'center'},
	            {field : 'price',title : '单价(元)',width :80,align : 'center'},
                {field : 'qty',title : '数量',width : 50,align : 'center'},
                {field : 'totalAmt',title : '合计金额(元)',width : 90,align : 'center'},
                {field : 'purpose',title : '用途',width : 90,align : 'center'},
                {field : 'remark',title : '备注',width : 150,align : 'center'},
                {field : 'caozuo',title : '操作',width : 200,align : 'center',formatter:function(value,row,index){
                	var appNo = row.appNo;
                	var psaId = row.psaId;
                	return "<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" onclick=\"fileUploadsDlg(\'"+appNo+"\',\'"+psaId+"\');\">上传附件</a>&nbsp;&nbsp;<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" onclick=\"checkAttachementDetail(\'"+appNo+"\','','',\'"+psaId+"\')\">查看附件</a>";
                }}
	   ]],
	   toolbar:[{
		   iconCls: 'icon-edit',
		   text:'编辑',
		   handler:editPurchaseAppAttach
	   }],
	   onLoadSuccess:function(data){
		   $(this).datagrid("doCellTip",{'max-width':'100px'});
	   }
	});
}
/**
 * 编辑物料
 */
function editPurchaseAppAttach(){
	$rowdata = $("#purchaseAppAttachGrid").datagrid("getSelected");
	var rows = $("#purchaseAppAttachGrid").datagrid("getSelections");
	if($rowdata == null){
		$.messager.alert("提示","请选择一条记录进行修改!","info");
		return false;
	}
	if(rows!=null && rows.length>1){
		$.messager.alert("提示","您只能选择一条记录进行修改!","info");
		return false;
	}
	$sonDialog = $("<div></div>").dialog({
		title: '编辑物料',    
		width: $(window).width()*0.36,    
	    height: $(window).height()*0.53,    
	    closed: false,    
	    cache: false,    
	    href: 'jsp/ad/purchaseApp/saveTask/purchaseAppAttachForm.jsp',    
	    modal: true,
	    onClose:function(){
	    	$(this).dialog('destroy');
	    	$rowdata = null;
	    },
	    buttons:[{
	    	text:'保存',
	    	iconCls:'icon-save',
			handler:function(){
				savePurchaseAppAttach();
			} 
	    },{
	    	text:'取消',
	    	iconCls:'icon-cancel',
			handler:function(){
				$sonDialog.dialog('close');
			}
	    }]
	});
}

/**
 * 保存物料申请
 */
function savePurchaseApp(formId){
	var isValid = $("#purchaseAppForm").form('validate');
	if(!isValid){
		return;
	}
	 $.ajax({
		   url: "purchaseAppController/savePurchaseApp.do",
		   type: "POST",
		   data:$('#purchaseAppForm').serialize(),
           dataType:'JSON',
		   success: function(msg){
			   if(msg.status){
				   $("#purchaseAppForm").form('load',msg.data);
				   disableForm(formId);
			   }
			   //弹出提示信息
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
		}else{
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
			$(this).attr("disabled",false);
		}else if($(this).hasClass("easyui-datebox")){
			$(this).datebox("enable");
		}else if($(this).hasClass("easyui-combobox")){
			$(this).combobox("enable");
		}else if($(this).hasClass("easyui-numberbox")){
			$(this).numberbox("enable");
		}else if($(this).hasClass("easyui-combogrid")){
			$(this).combogrid("enable");
		}else{
		}
	})
}
/**
 * 保存物料明细
 */
function savePurchaseAppAttach(){
	var isValid = $("#form_0").form('validate');
	if(!isValid){
		return;
	}
	var appNoValue = $("#purchaseAppForm input[name='appNo']").val();
	$("#form_0 input[name='appNo']").val(appNoValue);
	/****计算总金额 start****/
	var count = 0;
	var regS = new RegExp("\\,","g");
	var TotalAmt = Number($("#form_0 input[name='totalAmt']").val());
	var rows = $("#purchaseAppAttachGrid").datagrid('getRows'); 
	if(rows==null || rows.length<=0){
		count = TotalAmt;
	}else{
		for(var i=0;i<rows.length;i++){
			var row = rows[i];
			var amt = row.totalAmt+"";
			count += Number(amt.replace(regS,""));
		}
		var psaId = $("#form_0 input[name='psaId']").val();
		if(psaId==""){
			count += TotalAmt;
		}else{
			var oldTotalAmt = $("#form_0 input[name^='oldTotalAmt']").val();
			oldTotalAmt = oldTotalAmt.replace(regS,"");
			count -= Number(oldTotalAmt);
			count += TotalAmt;
		}
	}
	var count = Math.round(count*100)/100; 
	/****计算总金额 end****/
	
	$("#purchaseAppForm input[name='totalAmt']").val(count);
	$.ajax({
		   type: "POST",
		   url: "purchaseAppAttachController/savePurchaseAppAttach.do?count="+count,
		   data:$("#form_0").serialize(),
           async: false,
           dataType:'JSON',
		   success: function(msg){
			   if(msg.status){
				 if($row != null){//说明此刻是编辑
					 $("#purchaseAppAttachGrid").datagrid("reload");
				 }else{//说明此刻是新增
					 $("#purchaseAppAttachGrid").datagrid("reload",{"appNo":appNoValue});
				 }
				 $rowdata = null;
				 $sonDialog.dialog('close');
			   }
			   $.messager.alert(msg.title,msg.message,'info');
		   }
	});
}
/**
 * 计算总价，单价*数量
 */
function sumPrice(){
	var price = $("#price").val();
	var qty = $("#qty").val();
    if(qty!="" && qty.indexOf(".")!=-1){
    	var dian = parseFloat(qty.substring(qty.indexOf(".")));
    	qty = parseFloat(qty.substring(0,qty.indexOf(".")));
    	if(!isNaN(qty) && dian >= 0.5){
    		qty = qty+1;
    	}
    }
	if(price == ""){
		return false;
	}
	if(qty == "" || isNaN(qty)){
		return false;
	}
	var price = parseFloat(price.replace(",",""));
	var sumNum;
    if(price > 999999.99){
    	sumNum = parseFloat(999999.99) * qty;
	}else if(qty > 999){
		sumNum = price * 999;
	}else{
		sumNum = price * qty;
	}
	sumNum = Math.round(sumNum*100)/100;
	sumNum = sumNum.toString();
	$("#totalAmt").numberbox('setValue',sumNum);
}
</script>