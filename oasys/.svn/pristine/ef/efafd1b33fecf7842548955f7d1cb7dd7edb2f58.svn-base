<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">
$(function(){
	loadOriginFormDataWhenUpdate();
	initAllComboBoxs();  //初始化各种下拉列表框
});

//若$row 不为空，则说明此时是修改数据，则在form表单初始化时，加载原有的表单数据
function loadOriginFormDataWhenUpdate(){
	if($row !=null){
		$("#staffRecruitAppForm").form('load', $row);
	}
}

//初始化各种下拉列表框
function initAllComboBoxs(){
	
	//加载页面时，初始化招聘目的
	$("#recruitPurpose").combobox({
		url:"commonController/findDicList.do?codeMyid=recruit_purpose",		
		valueField: 'code',
		textField: 'text',
		panelHeight: 50
	});
	
 	//加载页面时，初始化招聘人员类型 
	$("#recruitType").combobox({
		url:"commonController/findDicList.do?codeMyid=recruit_type",
		valueField: 'code',
		textField: 'text',
		panelHeight: 100
	});
	
	//加载页面时，初始化招聘渠道 
	$("#recruitPlace").combobox({
		url:"commonController/findDicList.do?codeMyid=recruit_place",
		valueField: 'code',
		textField: 'text',
		panelHeight: 50
	});	 	
}

</script>

<form id="staffRecruitAppForm" method="post" style="width: auto;margin-left:5px;">
    <fieldset>
    <legend><img src="extend/fromedit.png" style="margin-bottom: -3px;"/>招聘基本信息</legend>    
        <input name="mraId" type="hidden"/>          <!-- 主键 -->
        <input name="appNo" type="hidden"/>          <!-- 申请编号 -->
        <input name="applicantNo" type="hidden"/>    <!-- 申请人 -->
        <input name="deptNo" type="hidden"/>         <!-- 所属部门-->
        <input name="procStatus" type="hidden"/>     <!-- 流程状态 -->
	    <table class="table">
	      <tr>
	        <th>职位名称:</th>
	        <td><input id="jobTitle" name="jobTitle" class="easyui-textbox"/></td>
	        <th>现有人员数量:</th>
	        <td><input id="existingStaff" name="existingStaff" class="easyui-textbox" /></td>
	      </tr>
	      <tr>
	        <th>招聘数量:</th>
	        <td><input id="recruitQty" name="recruitQty" class="easyui-textbox easyui-numberbox" data-options="min:0,max:5"/></td>
	        <th>服务年限:</th>
	        <td><input id="serviceLife" name="serviceLife" class="easyui-textbox easyui-numberbox"  data-options="min:0,max:100"/></td>	        
	      </tr>
	      <tr>
	        <th>申请日期:</th>
	        <td><input id="appDate" name="appDate" class="easyui-datebox"/></td>
	        <th>上岗时间:</th>
	        <td><input id="jobDtime" name="jobDtime" class="easyui-datebox"/></td>	        
	      </tr>
	      <tr>
	        <th>是否部门目标内:</th>
	        <td>	
				<select id="isTargetInside" name="isTargetInside" class="easyui-combobox" style="width:150px;" data-options="panelHeight:50">   
				    <option value="Y">是</option>   
				    <option value="N">否</option>   
				</select>
			</td>					              
	        <th>招聘目的:</th>
	        <td><input id="recruitPurpose" name="recruitPurpose" class="easyui-textbox"/></td>	        
	      </tr>

	      <tr>
	        <th>招聘人员类型:</th>
	        <td><input id="recruitType" name="recruitType" class="easyui-textbox"/></td>					              
	        <th>招聘渠道:</th>
	        <td><input id="recruitPlace" name="recruitPlace" class="easyui-textbox"/></td>	        
	      </tr>
	      
	      <tr>
	        <th>建议薪资下限:</th>
	        <td><input id="adviceSalLower" name="adviceSalLower" class="easyui-textbox easyui-numberbox" data-options="min:0,precision:2,groupSeparator:',',width:100"/>元</td>	      
	        <th>建议薪资上限:</th>
	        <td><input id="adviceSalUpper" name="adviceSalUpper" class="easyui-textbox easyui-numberbox" data-options="min:0,precision:2,groupSeparator:',',width:100"/>元</td>
	      </tr>
	      <tr>
<!-- 	        <th>申请状态:</th>
	        <td><input id="appStatus" name="appStatus" class="easyui-textbox" /></td>
 -->	        
 			<th>岗位职责:</th>
	        <td colspan="3"><textarea id="jobDescription" name="jobDescription" rows="5" cols="20" class="easyui-textbox easyui-validatebox" maxlength="200" style="width: 470px;height: 50px;"></textarea></td>			       
	      </tr>
	      <tr>
	        <th>任职要求<br/>（教育、经验、技能）</th>
			<td colspan="3"><textarea id="qualification" name="qualification" rows="5" cols="20" class="easyui-textbox easyui-validatebox" maxlength="200" style="width: 470px;height: 50px;"></textarea></td>	        
	      </tr>
	      <tr>
	        <th>备注信息</th>
	        <td colspan="3"><textarea name="remark" rows="5" cols="20" class="easyui-textbox easyui-validatebox" maxlength="200" style="width: 470px;height: 50px;"></textarea></td>
	      </tr>
	    </table>
    </fieldset>
</form>