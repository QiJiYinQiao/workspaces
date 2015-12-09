<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
$(function(){
	fillInForm();
});
//编辑时，填充form表单
function fillInForm(){
	if($row!=null){
		$("#outAppForm").form('load',$row);
	}
}
</script>

<form id="outAppForm">
   <fieldset>
   <legend><img src="extend/fromedit.png" style="margin-bottom: -3px;"/>基本信息</legend>
   <input name="outId" type="hidden"/>
   <input name="appNo" type="hidden"/>
   <input name="applicantNo" type="hidden"/>
   <input name="deptNo" type="hidden"/>
   <input name="appDate" type="hidden"/>
   <input name="appStatus" type="hidden"/>
   <input name="procStatus" type="hidden"/>
   <input name="planOutCnt" type="hidden"/>
   <input name="realBgDtime" type="hidden"/>
   <input name="realEdDtime" type="hidden"/>
   <input name="realOutCnt" type="hidden"/>
   <table class="table">
     <tr>
        <th>计划外出<br/>开始时间:</th>
        <td>
           <input id="planBgDtime" name="planBgDtime" class="easyui-textbox easyui-validatebox easyui-datetimebox"/>
        </td>
        <th>计划外出<br/>结束时间:</th>
        <td>
           <input name="planEdDtime" class="easyui-textbox easyui-validatebox easyui-datetimebox" validType="equalDate['#planBgDtime']"/>
        </td>
     </tr>
     <tr>
        <th>外出事由:</th>
        <td colspan="3">
           <textarea name="outReason" rows="5" cols="20" class="easyui-textbox easyui-validatebox" maxlength="250" style="width: 510px;height: 60px;"></textarea>
        </td>
     </tr>
     <tr>
        <th>备注:</th>
        <td colspan="3">
           <textarea name="remark" rows="5" cols="20" class="easyui-textbox easyui-validatebox" maxlength="250" style="width: 510px;height: 60px;"></textarea>
        </td>
     </tr>
   </table>
  </fieldset>
</form>
  
