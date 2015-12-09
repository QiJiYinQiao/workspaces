<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/validate.js"></script>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
	
	$(function(){
		 $("#usBeginDate").datebox({required:true});
		//判断用章类型
		addStampOther(); 
	});
	

	//申请人信息保存
	function toSaveBaseInfo(){
		//校验其他印章
		/*  if($("#radio5").is(":checked") && $.trim($("#stampTypeOther").val())==""){
			$.messager.alert("提示","请输入其他类型印章","info");
  			return false;
   	 	}  */
		var isValid = $("#useStampForm").form('validate');
    	if(!isValid){
    		return false;
    	} 

	    $.messager.confirm('确定','是否确定保存该数据吗？',	function(flag) {
			if (flag) {
				$.ajax({
					cache:true,
					type:'POST',
					url:"UseStampApp/saveUseStamp.do",
					data:$('#useStampForm').serialize(),
					async:false,
					dataType:'JSON',
					success:function(res){
						 if(res.status){
							$("#editView").dialog('close');					
							$.messager.alert("提示", '用章申请保存成功!',"info")
						}else{
							$.messager.alert("提示", '出错了，保存失败!',"error")
						} 
					}
				});
			}
		});
	} 
    
     //添加其他类型印章
     function addStampOther(){
    	 
    	 if($("#radio5").is(":checked")){
    		 $("#stampTypeOther").show();
    		 $("#stampTypeOther").validatebox({required:true});
    	 }else{
    		 $("#stampTypeOther").hide();
    		 $("#stampTypeOther").validatebox({required:false});
    	 }
    	
     }
     
  	
     
</script>
<div id="tt">
	<div title="用章申请详情">
	  <div class="well well-small" style="margin:5px;">
	     	<form id="useStampForm">
	          <input id="appNo" name="appNo" type="hidden"/><!-- 申请编号 -->
	      	  <input id="usaId" name="usaId" type="hidden"/><!-- 申请id -->
	          <input id="appStatus" name="appStatus" type="hidden"/><!-- 申请状态 -->
	          <input id="procStatus" name="procStatus" type="hidden"/><!-- 流程状态 -->
	          <input id="applicantNo" name="applicantNo" type="hidden"/><!-- 申请人id -->
	          <input id="deptNo" name="deptNo" type="hidden"/><!-- 部门id -->
	          <input id="appDate" name="appDate" type="hidden" /><!--申请时间 -->
	          
	         <table class="table">
				
				<tr>
					<th>印章类型:</th>
					<td height="22">
						<input type="radio" id="radio1" name="stampType" value="A" onclick="addStampOther();" checked="checked"/>公章
						<input type="radio" id="radio2" name="stampType" value="B" onclick="addStampOther();"/>合同章
						<input type="radio" id="radio3" name="stampType" value="C" onclick="addStampOther();"/>财务章
						<input type="radio" id="radio4" name="stampType" value="D" onclick="addStampOther();"/>法人章
						<input type="radio" id="radio5" name="stampType" value="E" onclick="addStampOther();">其他</input>
						<input type="text" id="stampTypeOther"   name="stampTypeOther" style="height: 11px;width: 162px" class="easyui-textbox easyui-validatebox "  data-options="editable:false,validType:'length[0,15]'"/>
					</td>
				</tr>
				<tr>
					<th >用章数量:</th>
					<td ><input id="usQuantity" name="usQuantity"  type="text" style="width: 50px;text-align: center;" value="0" class="easyui-textbox easyui-validatebox easyui-numberbox" data-options="required:true,editable:false"/>个</td>
				</tr>
				
				<tr>
					<th>用章时间:</th>
					<td colspan="2">
						<input id="usBeginDate" name="usBeginDate" placeholder="请选择起始日期" class="easyui-textbox  easyui-validatebox" data-options="editable:false"/>
						-
						<input id="usEndDate" name="usEndDate" placeholder="请选择结束日期" class="easyui-datebox" />
					</td>
				</tr>
				
				<tr>
					<th>用章事由:</th>
					<td colspan="3">
						<textarea id="usReason" name="usReason" class="easyui-textbox easyui-validatebox" maxlength="100"style="width: 440px;height: 80px;resize:none;" data-options="required:true,validType:'length[0,100]'"></textarea>
					</td>
				</tr>
				
				 <tr>
					<th>备注信息:</th>
					<td colspan="3">
					  <textarea id="remark" name="remark" class="easyui-textbox easyui-validatebox" maxlength="100" style="width: 440px; height: 80px;resize:none;" data-options="validType:'length[0,100]'"></textarea>
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
	   <div style="margin: 5px;">
	        <table id="appUserView"></table>
	   </div>
	</div>
	
</div>
