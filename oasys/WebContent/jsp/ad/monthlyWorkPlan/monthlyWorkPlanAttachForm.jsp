<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<script type="text/javascript">
	$(function(){
		//如果是编辑就填充form
		fullForm();
	})
	function fullForm(){
		if($rowdata!=null){
			$("#monthlyWorkPlanAttachForm").form("load",$rowdata);
		}
	}
	</script>
     <form id="monthlyWorkPlanAttachForm" style="width: 98%;margin-left:5px;">
	    <fieldset>
		    <legend><img src="extend/fromedit.png" style="margin-bottom: -3px;"/>工作计划表信息</legend>
		    <input name="mwpaId" type="hidden"/>
		    <input name="mwpId" type="hidden"/>
		    <table class="table">
		       <tr>
		          <th class="textStyle">本月工作<br/>计划总结:</th>
		          <td colspan="3">
		               <textarea name="contSumup" rows="5" cols="20" class="easyui-textbox easyui-validatebox" maxlength="100" style="width: 424px;height: 50px;"></textarea>
		          </td>
		       </tr>
		       <tr>
		          <th class="textStyle">第一周(%):</th>
		          <td>
		               <input name="completed1" class="easyui-textbox easyui-numberbox" data-options="max:100,min:0,precision:2"/>
		          </td>
		          <th class="textStyle">第二周(%):</th>
		          <td>
		               <input name="completed2" class="easyui-textbox easyui-numberbox" data-options="max:100,min:0,precision:2"/>
		          </td>
		       </tr>
		       <tr>
		          <th class="textStyle">第三周(%):</th>
		          <td>
		               <input name="completed3" class="easyui-textbox easyui-numberbox" data-options="max:100,min:0,precision:2"/>
		          </td>
		          <th class="textStyle">第四周(%):</th>
		          <td>
		               <input name="completed4" class="easyui-textbox easyui-numberbox" data-options="max:100,min:0,precision:2"/>
		          </td>
		       </tr>
		       <tr>
		          <th class="textStyle">下月工作<br/>内容计划:</th>
		          <td colspan="3">
		               <textarea name="contPlan" rows="5" cols="20" class="easyui-textbox easyui-validatebox" maxlength="100" style="width: 424px;height: 50px;"></textarea>
		          </td>
		       </tr>
		       <tr>
		          <th class="textStyle">第一周(%):</th>
		          <td>
		               <input name="planComp1" class="easyui-textbox easyui-numberbox" data-options="max:100,min:0,precision:2"/>
		          </td>
		          <th class="textStyle">第二周(%):</th>
		          <td>
		               <input name="planComp2" class="easyui-textbox easyui-numberbox" data-options="max:100,min:0,precision:2"/>
		          </td>
		       </tr>
		       <tr>
		          <th class="textStyle">第三周(%):</th>
		          <td>
		               <input name="planComp3" class="easyui-textbox easyui-numberbox" data-options="max:100,min:0,precision:2"/>
		          </td>
		          <th class="textStyle">第四周(%):</th>
		          <td>
		               <input name="planComp4" class="easyui-textbox easyui-numberbox" data-options="max:100,min:0,precision:2"/>
		          </td>
		       </tr>
		       <tr>
		         <th class="textStyle">备注:</th>
		         <td colspan="3">
		            <textarea name="remark" rows="5" cols="20" class="easyui-textbox easyui-validatebox" maxlength="100" style="width: 424px;height: 50px;"></textarea>
		         </td>
		       </tr>
		    </table>
	    </fieldset>
    </form>
    