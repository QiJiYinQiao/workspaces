<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div>
    <form id="goodsTackoverForm"  method="post" style="width: 875px;margin-left:5px;">
    <input type="hidden" id="ids" name="ids">
    <fieldset>
    <legend><img src="extend/fromedit.png" style="margin-bottom: -3px;"/>基本信息</legend>
    	<input id="gtrId" name="gtrId" type="hidden">
    	<input name="registrantNo" type="hidden">
    	<input name="regDatetime"  type="hidden">
		<table class="table"  width="95%">
			<tr>
				<th>移交部门:</th>
                <td><input id="turnoverDeptName" name="turnoverDeptName" class="easyui-textbox" required disabled="disabled"/>
                	<input id="turnoverDept" name="turnoverDept" type="hidden"/>
                </td>
				<th>移交人:</th>
                <td><input id="turnoverUserName" name="turnoverUserName" class="easyui-textbox easyui-combobox easyui-validatebox" disabled="disabled"/>
                	<input id="turnoverUser" name="turnoverUser" type="hidden"/>
                </td>
				<th>移交日期:</th>
                <td><input id="turnoverDate" name="turnoverDate" class="easyui-textbox easyui-validatebox " editable="false" disabled="disabled"/></td>

			</tr>
			<tr>
				<th>接收部门:</th>
                <td><input id="tackoverDeptName" name="tackoverDeptName" class="easyui-textbox" required disabled="disabled"/>
                	<input id="tackoverDept" name="tackoverDept" type="hidden"/>
                </td>
                <th>接收人:</th>
                <td><input id="tackoverUserName" name="tackoverUserName" class="easyui-textbox easyui-combobox easyui-validatebox" disabled="disabled"/>
                	<input id="tackoverUser" name="tackoverUser" type="hidden"/>
                </td>
				<th>交接日期:</th>
                <td><input id="tackoverDate" name="tackoverDate" class="easyui-textbox easyui-validatebox"  editable="false" disabled="disabled"/></td>
			</tr>
			
			
		</table>
		<table>
			<tr>
				<th>交接物品:</th>
				<td></td>
			</tr>
			<tr id="NO11">
				<td id="NO1">1、POS机:</td>
			</tr>
			<tr id="NO22">
				<td id="NO2">2、印刷物料:</td>
			</tr>
			<tr id="NO33">
				<td id="NO3">3、其他:</td>
			</tr>
		</table>
		<table>
			<tr>
				<th align="right">备注:</th>
			   <td align="left">
			      	<textarea name="remark" id="remark" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,200]'" style="width: 600px; height: 90px;" />
			   </td>
			</tr>
			<tr id="onRow" class="">
				<td colspan="3"></td>
			   <td colspan="1" align="right" >
			      <a href="javascript:void(0)" id="save" class="easyui-linkbutton" iconCls="icon-save" onclick="saveGoodsTackover();">保存</a>
			      <a href="javascript:void(0)" id="close" class="easyui-linkbutton"  onclick="closeWindow();">关闭页面</a>
			   </td>
			</tr>	
		</table>
    </fieldset>
    </form>
</div>
<script type="text/javascript">
$(function(){
	createCheckBox();
	//初始化组织机构
	$("#turnoverDeptName").combotree({
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
	 			$("#turnoverDept").val($(this).combotree('getValue'));
	 			 RenderName($(this).combotree('getValue'),0);
	 	}
	}); 
	
	//初始化组织机构
	$("#tackoverDeptName").combotree({
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
	 			$("#tackoverDept").val($(this).combotree('getValue'));
	 			 RenderName($(this).combotree('getValue'),1);
	 	}
	}); 
	
	$("#turnoverUserName").combobox({required:true});
	$("#tackoverUserName").combobox({required:true});
	$("#turnoverDate").datebox({required:true});
	$("#tackoverDate").datebox({required:true});
});

//用户名的下拉
function RenderName(organizeId,type){
	//选中部门后下拉框
	if(type==0){
		$("#turnoverUserName").combobox({
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
		 		$("#turnoverUser").val($(this).combotree('getValue'));
		 	}
		}); 
	}else{
		$("#tackoverUserName").combobox({
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
		 		$("#tackoverUser").val($(this).combotree('getValue'));
		 	}
		}); 
	}

}
function createCheckBox(){
	$.ajax({
		   url: "goodsStacfg/findAllGoodsStacfg.do",
		   type: "POST",
		   success: function(msg){
			   for(var i=0;i<msg.length;i++){
				if(msg[i].goodsType=="1"){
					$("#NO1").after('<td><input type="checkbox" id='+msg[i].gtsId+' name='+msg[i].goodsName+' value='+msg[i].gtsId+'>'+msg[i].goodsName+'</input></td>');
				}
				if(msg[i].goodsType=="2"){
					$("#NO2").after('<td><input type="checkbox" id='+msg[i].gtsId+' name='+msg[i].goodsName+' value='+msg[i].gtsId+'>'+msg[i].goodsName+'</input></td>');
				}
				if(msg[i].goodsType=="3"){
					$("#NO3").after('<td><input type="checkbox" id='+msg[i].gtsId+' name='+msg[i].goodsName+' value='+msg[i].gtsId+'>'+msg[i].goodsName+'</input></td>');
				}
			   }
		   }
		});
}



function saveGoodsTackover(){
	var isValid = $("#goodsTackoverForm").form('validate');
	if(!isValid){
		return false;
	}
	var elements=$("input:checked");
	var ids="";
	for(var i=0;i<elements.length;i++){
		ids=ids+elements[i].id+",";
	}
	if(""==ids){
		jQuery.messager.alert('提示:','请选择物品','warning');
	}else{
		$("#ids").val(ids);
		$.ajax({
			   url: "goodsReg/addGoodsTackoverReg.do",
			   type: "POST",
			   data:$('#goodsTackoverForm').serialize(),
	          dataType:'JSON',
			   success: function(msg){
				   if(msg.status){
					   $("#dg").datagrid("load",{});
					   $("#goodsTackoverForm").form('clear');//清空表单
				   }
				   //弹出提示信息
				   parent.$.messager.show({
		    			title : msg.title,
		    			msg : msg.message,
		    			timeout : 4000 * 2
		    	   }); 
			   }
			});
	}
}
function closeWindow(){
	$('#dd').dialog('close');
	$("#dg").datagrid("reload");
}

</script>