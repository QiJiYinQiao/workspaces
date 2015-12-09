<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
//初始化构建列表
$(function(){
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
		 			$("#useDept").val($(this).combotree('getValue'));
		 			 RenderName($(this).combotree('getValue'));
		 	}
		}); 
		//修改时 隐藏保存并新增按钮
		if($("#prId").val() != ""){
			$("#save").remove();
		}
})

function closeWindow(){
	$('#saveOrUpdateExpressDialog').dialog('close');
	$("#dg").datagrid("reload");
}

//用户名的下拉
function RenderName(organizeId){
	//选中部门后下拉框
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
	 		$("#user").val($(this).combotree('getValue'));
	 	}
	}); 
}




function savePpeReg(type){
	var isValid = $("#ppeRegForm").form('validate');
   	if(!isValid){
   		return false;
   	}  	
	$.ajax({
	   url: "ppeRegController/addPpeReg.do",
	   type: "POST",
	   data:$('#ppeRegForm').serialize(),
         dataType:'JSON',
	   success: function(msg){
	    	if(msg.status){
	    		$("#dg").datagrid("load",{});//刷新表格   
	    	}
	    	$.messager.alert(msg.title,msg.message,'info');
	   }
	});
	//为1时 保存并关闭当前窗口
	if(type == 1){
		closeWindow();
	}
}
</script>
    <form id="ppeRegForm"  method="post" style="width: 875px;margin-left:5px;">
    <div style="margin-left: 5px;margin-top: 5px;">
	   <input type="hidden" id="prId" name="prId" value="${ppeReg.prId}"/>
	   <input type="hidden" id="registrantNo" name="registrantNo" value="${ppeReg.registrantNo}"/>
	   <input type="hidden" id="user" name="user" value="${ppeReg.user}"/>
	   <input type="hidden" id="useDept" name="useDept" value="${ppeReg.useDept}"/>
	   <input type="hidden" id="regDatetime" name="regDatetime" value="${ppeReg.regDatetime}""/>
	   
			<table class="table"  width="95%">
				<tr>
					<th align="right">使用部门:</th>
					<td><input name="receiverOrgName1" id="receiverOrgName1" type="text"  style="width: 170px" data-options="required:true" value="${ppeReg.ppeOrgName }"/></td>
					<th align="right">使用人:</th>
					<td><input name="receiverUserName1" id="receiverUserName1" type="text" class="easyui-combobox" editable="false" style="width: 170px" data-options="required:true" value="${ppeReg.ppeUserName }"/></td>				
				</tr>		
				<tr>
					<th align="right">数量:</th>
					<td>
						<input name="qty" id="qty" value="${ppeReg.qty}"  type="text"   class="easyui-textbox easyui-validatebox easyui-numberbox"  max="99999999999"  editable="false"   data-options="validType:'int',required:true"/>
					</td>
					<th align="right">购买价格:</th>
					<td colspan="3">
						<input id="price" name="price" value="${ppeReg.price}"  class="easyui-textbox easyui-validatebox easyui-numberbox" min="0.01"   max="999999999"  precision="2"   type="text"  data-options="validType:'intOrFloat',required:true"/>&nbsp;(元)
					</td>
				</tr>					
				<tr>
					<th align="right">固定资产名称:</th>
					<td>
						<input id="ppeName" name="ppeName" value="${ppeReg.ppeName}"  type="text" class="easyui-textbox easyui-validatebox"  data-options="validType:'length[0,50]',required:true"/>
					</td>
					<th align="right">固定资产规格:</th>						
					<td>
						<input id="ppeModel" name="ppeModel" value="${ppeReg.ppeModel}" type="text"  class="easyui-textbox easyui-validatebox"  data-options="validType:'length[0,50]',required:true"/>
					</td>
					<th align="right">固定资产编号:</th>						
					<td>
						<input id="ppeNo" name="ppeNo" value="${ppeReg.ppeNo}" type="text"  class="easyui-textbox easyui-validatebox"  data-options="validType:'length[0,20]',required:true"/>
					</td>						
				</tr>						
				<tr>
					<th colspan="1" align="right">备注:</th>
					<td colspan="5"><textarea name="remark"  id="remark"  class="easyui-textarea easyui-validatebox" style="width: 600px; height: 90px;" data-options="validType:'length[0,200]'">${ppeReg.remark }</textarea></td>
				</tr>
				<tr><td colspan="6" height="10"></td></tr>
				<tr>
					<td colspan="3"></td>
				   <td colspan="3" align="right">
				      <a href="javascript:void(0)" id="save" class="easyui-linkbutton" iconCls="icon-save" onclick="savePpeReg(0);">保存并继续添加</a>
				      <a href="javascript:void(0)" id="saveAndClose" class="easyui-linkbutton" iconCls="icon-save" onclick="savePpeReg(1);">保存并关闭</a>
				      <a href="javascript:void(0)" id="close" class="easyui-linkbutton"  onclick="closeWindow();">关闭页面</a>
				   </td>
				</tr>					
			</table>
	</div>			
    </form>
