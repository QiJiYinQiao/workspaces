<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
function saveStampTurnover(formId){
	var isValid = $("#StampTurnoverAppForm").form('validate');
   	if(!isValid){
   		return false;
   	}  	
	$.ajax({
		   url: "stampTurnoverAppController/addOrUpdateStampTurnoverApp.do",
		   type: "POST",
		   data:$('#StampTurnoverAppForm').serialize(),
          dataType:'JSON',
		   success: function(msg){
			   if(msg.status){
				   $("#StampTurnoverAppForm input[name='staId'][type='hidden']").val(msg.data[0]);
				   $("#StampTurnoverAppForm input[name='appNo'][type='hidden']").val(msg.data[1]);
				   //disableForm(formId);
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
		}else if($(this).hasClass("easyui-combotree")){
			$(this).combotree('disable');
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
			$(this).attr("disabled",false);
		}else if($(this).hasClass("easyui-datebox")){
			$(this).datebox("enable");
		}else if($(this).hasClass("easyui-combobox")){
			$(this).combobox("enable");
		}else if($(this).hasClass("easyui-combotree")){
			$(this).combotree('enable');
		}else if($(this).hasClass("easyui-numberbox")){
			$(this).numberbox("enable");
		}else if($(this).hasClass("easyui-combogrid")){
			$(this).combogrid("enable");
		}else if($(this).hasClass("easyui-textarea")){
			$(this).attr("disabled",false);
		}
	});
}

</script>
    <form id="StampTurnoverAppForm"  method="post" style="width: 875px;margin-left:5px;">
    <fieldset>
    <legend><img src="extend/fromedit.png" style="margin-bottom: -3px;"/>基本信息</legend>
 	<input type="hidden" id="appNo" name="appNo" value="${stampTurnover.appNo }" /><!-- 外键appNo -->
	<input type="hidden" id="staId" name="staId"  value="${stampTurnover.staId }" /> <!-- id标识 判断是否为新增 -->
	<input type="hidden" id="appDept" name="appDept" value="${stampTurnover.appDept }" /><!-- 申请部门 -->
	<input type="hidden" id="appDate" name="appDate" value="${stampTurnover.appDate }" /><!-- 申请时间 -->
	<input type="hidden" id="applicantNo" name="applicantNo" value="${stampTurnover.applicantNo }" /> <!-- 申请人 -->
	
	<input type="hidden" id="superviserDept" name="superviserDept" value="${stampTurnover.superviserDept }" /><!-- 监交部门 -->
	<input type="hidden" id="superviserNo" name="superviserNo" value="${stampTurnover.superviserNo }" /> <!-- 监交人 -->
	
	<input type="hidden" id="receiverDept" name="receiverDept" value="${stampTurnover.receiverDept }" /><!-- 接收部门 -->
	<input type="hidden" id="receiverNo" name="receiverNo" value="${stampTurnover.receiverNo }" /> <!-- 接收人 -->
	
	<input type="hidden" id="appStatus" name="appStatus" value="${stampTurnover.appStatus }" /><!-- 申请状态 -->
	
	<input type="hidden" id="procStatus" name="procStatus" value="${stampTurnover.procStatus }" /><!-- 申请状态 -->
	
		<table class="table"  width="95%">
			<tr>
				<th align="right">接收部门:</th>
				<td>
					<input name="receiverOrgName1" id="receiverOrgName1" type="text" class="easyui-textbox"  editable="false"  style="width: 170px" data-options="required:true" value="${stampTurnover.receiverOrgName }"/>
				</td>				
				<th align="right">接收人:</th>
				<td>
					<input name="receiverUserName1" id="receiverUserName1" type="text" class="easyui-textbox" readonly="readonly"  editable="false"  style="width: 170px" data-options="required:true" value="${stampTurnover.receiverUserName }"/>
				</td>
				<th align="right">接收时间:</th>	
				<td colspan="3">
					<input name="receiverDate" id="receiverDate"  class="easyui-datebox"  editable="false"  data-options="validType:'Date',required:true" value="${stampTurnover.receiverDate }"/>
				</td>
			</tr>
			<!-- 
			<tr>
				<th align="right">监交部门:</th>
				<td>
					<input name="superviserOrgName1" id="superviserOrgName1" type="text" editable="false"    class="easyui-textbox"  style="width: 170px" data-options="required:true" value="${stampTurnover.superviserOrgName }"/>
				</td>				
				<th align="right">监交人:</th>
				<td>
					<input name="superviserUserName1" id="superviserUserName1" type="text"  readonly="readonly"  class="easyui-textbox" style="width: 170px" data-options="required:true" value="${stampTurnover.superviserUserName }"/>
				</td>
				<th align="right">监交时间:</th>	
				<td colspan="3">
					<input name="superviserDate" id="superviserDate"  class="easyui-datebox"  editable="false"  data-options="validType:'Date',required:true" value="${stampTurnover.superviserDate }"/>
				</td>
			</tr> -->
			<tr>
				<th align="right" colspan="1">移交日期:</th>
			   <td colspan="5" align="left">
				   	<input name="turnoverDate" id="turnoverDate" editable="false"   class="easyui-datebox" style="width: 170px" data-options="validType:'Date',required:true" value="${stampTurnover.turnoverDate }"/>
			   </td>
			</tr>	
			<tr>
				<th align="right" colspan="1">备注:</th>
			   <td colspan="5" align="left">
			      	<textarea name="remark"  id="remark"  class="easyui-textarea easyui-validatebox" style="width: 600px; height: 90px;" data-options="validType:'length[0,200]'">${stampTurnover.remark }</textarea>
			   </td>
			</tr>	
			
			<tr>
				<td colspan="5"></td>
			   <td colspan="1" align="right" >
			      <a href="javascript:void(0)" id="save" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveStampTurnover('StampTurnoverAppForm');">保存</a>
<!-- 			      <a href="javascript:void(0)" id="save" class="easyui-linkbutton"  iconCls="icon-edit" style="display:none;" onclick="saveStampTurnover('StampTurnoverAppForm');">编辑</a> -->
			   </td>
			</tr>	
		</table>
    </fieldset>
    </form>
  <div style="width: 875px;margin-left:5px;margin-top: 2px;">
       <table id="stampTurnoverAttachGrid"></table>
       <div id="formWindow" class="easyui-dialog" title="新增印章移交" closed="true" ></div>
    </div>
    
   
<script type="text/javascript">
//初始化构建列表
$(function(){
		createStampTurnoverAttachGrid();
		//初始化组织机构
		$("#receiverOrgName1").combotree({
			width:171,
			url:"Organization/organizationList.do",
			idFiled:'id',
		 	textFiled:'name',
		 	valueFiled:'id',
		 	parentField:'pid',
		 	onLoadSuccess:function(data){
		 		//加一个全部
		 	},
		 	onChange:function(){
		 			$("#receiverDept").val($(this).combotree('getValue'));
		 			 RenderName($(this).combotree('getValue'),0);
		 	}
		}); 
		
		/* //初始化组织机构
		$("#superviserOrgName1").combotree({
			width:171,
			url:"Organization/organizationList.do",
			idFiled:'id',
		 	textFiled:'name',
		 	valueFiled:'id',
		 	parentField:'pid',
		 	onLoadSuccess:function(data){
		 		//加一个全部
		 	},
		 	onChange:function(){
		 			$("#superviserDept").val($(this).combotree('getValue'));
		 			 RenderName($(this).combotree('getValue'),1);
		 	}
		}); */
})

//用户名的下拉
function RenderName(organizeId,type){
	//选中部门后下拉框
	if(type==0){
		$("#receiverUserName1").val("");
		$("#receiverUserName1").combobox({
			width:171,
			multiple:false,
			separator:",", 
			url:"BadgeApp/findOrgUserList.do?organizeId="+organizeId,
			valueField:'code',
		 	textFiled:'text',
		 	onLoadSuccess:function(data){
		 		//加一个全部
		 	},
		 	onChange:function(){
		 		$("#receiverNo").val($(this).combotree('getValue'));
		 	}
		}); 
	}else{
		$("#superviserUserName1").val("");
		$("#superviserUserName1").combobox({
			width:171,
			multiple:false,
			separator:",", 
			url:"BadgeApp/findOrgUserList.do?organizeId="+organizeId,
			valueField:'code',
		 	textFiled:'text',
		 	onLoadSuccess:function(data){
		 		//加一个全部
		 	},
		 	onChange:function(){
		 		$("#superviserNo").val($(this).combotree('getValue'));
		 	}
		}); 
	}

}

/**
 * 编辑印章移交项
 */
function editStampTurnoverAppAttach(){
	var row = $("#stampTurnoverAttachGrid").datagrid("getSelected");
	var rows = $("#stampTurnoverAttachGrid").datagrid("getSelections");
	if(row == null){
		$.messager.alert("提示","请选择一条记录进行修改!","warning");
		return false;
	}
	if(rows!=null && rows.length>1){
		$.messager.alert("提示","您只能选择一条记录进行修改!","warning");
		return false;
	}
	$('#formWindow').dialog({
        title: '编辑印章移交',
        inline:false,
        closed:true,
        width: 850,    
        height: 550,                
        cache: false,
        resizable:false,
        shadow:false,
        modal: true ,
        href:'StampTurnoverAppAttachController/toAddStampTurnoverAppAttach.do?taaId='+row.taaId
	});
	$('#formWindow').dialog("open");
}
//构建列表方法
function createStampTurnoverAttachGrid(){
	$("#stampTurnoverAttachGrid").datagrid({
		url:'StampTurnoverAppAttachController/findAllStampTurnoverAttach.do?appNo='+$('#StampTurnoverAppForm #appNo').val(),
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
			{field : 'taaId',        title : 'TAA_ID',    width : 60, align:'center' },
			{field : 'stampName',      title : '印章名称', width : 915*0.2, align:'center'},
			{field : 'turnoverReson',    title : '印章移交原因',    width : 915*0.2, align:'center' },
			{field : 'isGiveback',    title : '是否归还',    width : 915*0.17, align:'center',
				formatter: function(value,row,index){
					var isGiveBackStr="";
       		 		if(row.isGiveback == 0){
       		 			isGiveBackStr="是";
       		 		}else{
       		 			isGiveBackStr="否";
       		 		}
       		 		return  isGiveBackStr;
       		 	}
			},
			{field : 'givebackDatetime',    title : '归还时间',    width : 915*0.17, align:'center',formatter: 
				function(value,row,index){
 					/* if(value != null && value.length >= 10){
						return value.substring(0,10);
					}  */
					return value;
			}},
			{field : 'caozuo',    title : '操作',    width : 915*0.18, align:'center',
	        	 formatter: function(value,row,index){
	        			var result = "&nbsp;&nbsp;&nbsp;<a href='javascript:void(0);' class='easyui-linkbutton'  onclick='fileUploadsDlg(\""+row.appNo+"\","+row.taaId+");'>上传附件</a>&nbsp;&nbsp;&nbsp;"
						+"<a href='javascript:void(0);' class='easyui-linkbutton'  onclick='checkAttachementDetail(\""+row.appNo+"\",null,null,"+row.taaId+");'>查看附件</a>&nbsp;&nbsp;&nbsp;";
						return result;
			}}
	   ]],
	   toolbar:[{
		   iconCls: 'icon-add',
		   text:'新增',
		   handler:toFormWindow
	   },"-",{
		   iconCls: 'icon-edit',
		   text:'编辑',
		   handler:editStampTurnoverAppAttach
	   },"-",{
		   iconCls: 'icon-cut',
		   text:'删除',
		   handler:delStampTurnoverAppAttach
	   }]
	});
	$('#stampTurnoverAttachGrid').datagrid('hideColumn','taaId');
}
//跳转新增
function toFormWindow(){
	if($("#StampTurnoverAppForm input[name='appNo'][type='hidden']").val() == ""){
		$.messager.alert("提示","请先保存基本信息","warning");
	}else{ 
		$('#formWindow').dialog({
	        title: '新增印章移交',
	        inline:false,
	        closed:true,
	        width: 850,    
	        height: 400,                
	        cache: false,
	        resizable:false,
	        shadow:false,
	        modal: true ,
	        href:'jsp/ad/stampTurnover/stampTurnoverAttachAdd.jsp',
	        onOpen:function(){
	        	var appNo =$("#StampTurnoverAppForm input[name='appNo'][type='hidden']").val();
				$('#appNo').val(appNo); 
			}
		});
		$('#formWindow').dialog("open");
	 }
}


function delStampTurnoverAppAttach(){
	var rows = $("#stampTurnoverAttachGrid").datagrid("getSelections");
	var ids = new Array();
	if(rows==null || rows.length<=0){
		$.messager.alert("提示","请至少选择一条记录!","warning");
		return false;
	}
	for(var i=0;i<rows.length;i++){
		ids.push(rows[i].taaId);
	}
	ids = ids.join(",");
	var appNo = $("#StampTurnoverAppForm input[name='appNo']").val();
	$.messager.confirm('删除', '是否删除该'+rows.length+'条记录', function(d) {
	if (d) {
		$.ajax({
		   type: "POST",
		   url: "StampTurnoverAppAttachController/delPurchaseAppAttach.do",
		   data:{"ids":ids},
	       async: false,
		   success: function(msg){
			   if(msg.status){
				   createStampTurnoverAttachGrid();
			   }
			   $.messager.alert(msg.title,msg.message,'info');
		   }
		});
	}});
}
</script>