<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
$(function(){
	fillInForm();
});
//编辑时，填充form表单
function fillInForm(){
	if($row!=null){
		$("#empForgetPluginAppForm").form('load',$row);
	}
}
</script>

<form id="empForgetPluginAppForm">
   <fieldset>
   <legend><img src="extend/fromedit.png" style="margin-bottom: -3px;"/>基本信息</legend>
   <input name="fpaId" type="hidden"/>
   <input name="appNo" type="hidden"/>
   <input name="applicantNo" type="hidden"/>
   <input name="deptNo" type="hidden"/>
   <input name="appDtime" type="hidden"/>
   <input name="appStatus" type="hidden"/>
   <input name="procStatus" type="hidden"/>
   <table class="table">
     <tr>
        <th width="20%">忘打卡事由:</th>
        <td>
           <textarea name="forgetPluginReason" rows="5" cols="20" class="easyui-textbox easyui-validatebox" data-options="required:true" maxlength="250" style="width: 510px;height: 60px;"></textarea>
        </td>
     </tr>
     <tr>
        <th width="20%">备注:</th>
        <td>
           <textarea name="remark" rows="5" cols="20" class="easyui-textbox easyui-validatebox" maxlength="250" style="width: 510px;height: 60px;"></textarea>
        </td>
     </tr>
   </table>
  </fieldset>
</form>
  
