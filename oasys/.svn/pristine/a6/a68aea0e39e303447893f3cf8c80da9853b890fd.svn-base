<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/validate.js"></script>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
	
	$(function(){
		//判断添加还是修改
		addOrEditval();
		$("#borrower").combobox({required:true});
		
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
		 			RenderName("#borrower",$(this).combotree('getValue'));
		 			 
		 	}
		});
		
		//初始化组织机构,归还人
		$("#reverterDeptNo").combotree({
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
		 			$("#reverterDeptNo").val($(this).combotree('getValue'));
		 			RenderName("#reverter",$(this).combotree('getValue'));
		 	}
		});
		
		
	});
	
	// 用户名的下拉
	function RenderName(name,organizeId){
		
		$(name).combobox({
			width:171,
			url:"PpeBorrowReg/findOrgUserList.do?organizeId="+organizeId,
			valueField:'code',
		 	textFiled:'text',
		 	onLoadSuccess:function(data){
		 		//加一个全部
		 		
		 	},
		}); 
	}
	
	//判断是添加还是修改
	function addOrEditval(){
		var pbrId=$("#pbrId").val();
		if($.trim(pbrId)!=''){
			$("#rev").show();
			$("#reverter").combobox({required:true});
		}else{
			$("#rev").hide();
			$("#reverter").combobox({required:false});
		}
	}
	

	//申请人信息保存
	function toSaveBaseInfo(){
		
		var isValid = $("#ppeBorrowRegForm").form('validate');
    	if(!isValid){
    		return false;
    	} 
	    
	    
		$.ajax({
			cache:true,
			type:'POST',
			url:"PpeBorrowReg/savePpeBorrowReg.do",
			data:$('#ppeBorrowRegForm').serialize(),
			async:false,
			dataType:'JSON',
			success:function(res){
				 if(res.status){
					//关闭窗口
					$("#editView").dialog('close');
					$.messager.alert("提示", '登记信息保存成功!',"inifo")
				}else{
					$.messager.alert("提示", '出错了，保存失败!',"error")
				} 
			}
		});
	} 
    
  
  
</script>
<div id="tt">
	<div title="固定资产借用详情">
	  <div class="well well-small" style="margin:5px;">
	     	<form id="ppeBorrowRegForm">
	      	  <input id="pbrId" name="pbrId" type="hidden"/><!-- 固定资产借用登记id -->
	          <input id="registrantNo" name="registrantNo" type="hidden"/><!-- 登记人id -->
	          <input id="regDatetime1" name="regDatetime" type="hidden" /><!-- 借用日期 -->  
	      	  <input id="revDatetime1" name="revDatetime" type="hidden"/><!-- 归还日期 -->  
	          
	         <table class="table">
	         	<tr>
	         		<th>固定资产编号：</th>
	         		<td>
	         			<input id="ppeNo" name="ppeNo" class="easyui-textbox easyui-validatebox " data-options="required:true,editable:false" style="width: 168px" "/>
	         		</td>
	         		<th>固定资产名称:</th>
	         		<td>
	         			<input id="ppeName" name="ppeName" class="easyui-textbox easyui-validatebox " data-options="required:true,editable:false" style="width: 168px"/>
	         		</td>
	         		<th>固定资产型号:</th>
	         		<td>
	         			<input id="model" name="model" class="easyui-textbox easyui-validatebox " data-options="required:true,editable:false" style="width: 168px"/>
	         		</td>
	         	</tr>
	         	
				<tr >
						<th>借用部门:</th>
						<td style="width:196px"><input name="deptNo" id="deptNo" type="text" readonly="readonly" class="easyui-textbox easyui-validatebox" style="width: 170px"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<th>借用人:</th>
						<td><input name="borrower" id="borrower" type="text" readonly="readonly" class="easyui-textbox easyui-validatebox easyui-combobox" data-options="editable:false" style="width: 170px"/>&nbsp;</td>
				</tr>
				
				<tr id="rev">
						<th>归还部门:</th>
						<td style="width:196px"><input name="reverterDeptNo" id="reverterDeptNo" type="text" readonly="readonly" class="easyui-textbox easyui-validatebox" style="width: 170px"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<th>归还人:</th>
						<td><input name="reverter" id="reverter" type="text" readonly="readonly" class="easyui-textbox easyui-validatebox easyui-combobox" data-options="editable:false" style="width: 170px"/>&nbsp;</td>
				</tr>
				
				<tr>
					<th>借用事由:</th>
					<td colspan="6">
						<textarea id="borReson" name="borReson" class="easyui-textbox easyui-validatebox" style="width: 744px;height: 80px;resize:none;"  data-options="required:true,validType:'length[0,100]'"></textarea>
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
