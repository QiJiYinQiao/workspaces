<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/validate.js"></script>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
	
	$(function(){
		$("#user").combobox({required:true});
		
		
		//初始化组织机构,借用人
		$("#deptNo").combotree({
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
		 			$("#deptNo").val($(this).combotree('getValue'));
		 			 RenderName($(this).combotree('getValue'));
		 			 
		 	}
		});
		
		
		
	});
	
	// 用户名的下拉
	function RenderName(organizeId){
		
		$("#user").combobox({
			width:171,
			url:"UseReg/findOrgUserList.do?organizeId="+organizeId,
			valueField:'code',
		 	textFiled:'text',
		 	onLoadSuccess:function(data){
		 		//加一个全部
		 		
		 	},
		}); 
	}
	

	//申请人信息保存
	function toSaveBaseInfo(){
		
		var isValid = $("#UseRegForm").form('validate');
    	if(!isValid){
    		return false;
    	} 
	   
		$.ajax({
			cache:true,
			type:'POST',
			url:"UseReg/saveUseReg.do",
			data:$('#UseRegForm').serialize(),
			async:false,
			dataType:'JSON',
			success:function(res){
				 if(res.status){
					//关闭窗口
					$("#editView").dialog('close');
					$.messager.alert("提示", '该登记信息保存成功',"info")
				}else{
					$.messager.alert("提示", '出错了，保存失败!',"error")
				} 
			}
		});
	} 
    
  		
  	
  
</script>
<div id="tt">
	<div title="固定资产领用详情">
	  <div class="well well-small" style="margin:5px;">
	  			<input id="resUrId" name="resUrId" type="hidden"/><!-- 固定资产借用登记id -->
	     	<form id="UseRegForm">
	      	  <input id="urId" name="urId" type="hidden"/><!-- 固定资产借用登记id  -->
	          <input id="registrantNo" name="registrantNo" type="hidden"/><!-- 登记人id -->
	          <input id="regDatetime" name="regDatetime" type="hidden" /><!-- 借用日期 -->  
	          
	         <table class="table">
	         	<tr>
	         		<th>固定资产编号：</th>
	         		<td>
	         			<input id="ppeNo" name="ppeNo" class="easyui-textbox easyui-validatebox " data-options="required:true,editable:false"/>
	         		</td>
	         		<th>固定资产名称:</th>
	         		<td>
	         			<input id="ppeName" name="ppeName"  style="width: 165px" class="easyui-textbox easyui-validatebox " data-options="required:true,editable:false"/>
	         		</td>
	         	</tr>
	         	<tr>
	         		<th>领用数量:</th>
	         		<td>
	         			<input id="useQty" name="useQty" style="width: 50px;text-align: center;" value="0"  align="middle" class="easyui-textbox easyui-validatebox easyui-numberbox" data-options="editable:false"/>（个）
	         		</td>
	         	</tr>
	         	
				<tr >
						<th>领用部门:</th>
						<td style="width:311px"><input name="deptNo" id="deptNo" type="text" readonly="readonly" class="easyui-textbox easyui-validatebox" style="width: 170px" />&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<th>领用人:</th>
						<td><input name="user" id="user" type="text" readonly="readonly" class="easyui-textbox easyui-validatebox easyui-combobox" style="width: 170px" data-options="editable:false"/>&nbsp;</td>
				</tr>
				
				<tr>
					<th>领用用途:</th>
					<td colspan="3">
						<textarea id="useReson" name="useReson" class="easyui-textbox easyui-validatebox" style="width: 744px;height: 80px;resize:none;" data-options="required:true,validType:'length[0,100]'"></textarea>
					</td>
				</tr>
				
				 <tr>
					<th>备注信息:</th>
					<td colspan="6">
					  <textarea id="remark" name="remark" class="easyui-textbox easyui-validatebox" style="width: 744px; height: 80px;resize:none;" data-options="validType:'length[0,100]'"></textarea>
					</td>
				</tr>
				<tr>
				   <td colspan="6" style="text-align: right;">
				   	  <a href="javascript:void(0)" id="save" class="easyui-linkbutton" iconCls="icon-save" onclick="toSaveBaseInfo();">保存</a>
				   </td>
				</tr>
	         </table>
	       </form>
	  </div>
	</div>
	
</div>
