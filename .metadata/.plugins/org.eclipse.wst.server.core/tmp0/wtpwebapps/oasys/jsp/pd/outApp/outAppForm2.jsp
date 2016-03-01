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
   <input name="planBgDtime" type="hidden"/>
   <input name="planEdDtime" type="hidden"/>
   <input name="outReason" type="hidden"/>
   <input name="remark" type="hidden"/>
   <table class="table">
     <tr>
        <th>实际外出<br/>开始时间:</th>
        <td>
           <input name="realBgDtime" class="easyui-textbox easyui-validatebox easyui-datetimebox"/>
        </td>
        <th>实际外出<br/>结束时间:</th>
        <td>
           <input name="realEdDtime" class="easyui-textbox easyui-validatebox easyui-datetimebox"/>
        </td>
     </tr>
   </table>
  </fieldset>
</form>
  
