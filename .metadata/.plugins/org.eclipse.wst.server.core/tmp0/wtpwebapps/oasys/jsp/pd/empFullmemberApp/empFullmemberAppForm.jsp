<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">
$(function(){
	fullForm();
	createSelect();
});
//编辑的时候填充form表单
function fullForm(){
	if($row != null){
		$("#empFullmemberAppForm").form('load',$row);
	}else{
		$.ajax({
			type:'POST',
			url:'empFullmemberAppController/createCombogrid.do',
			dataType:'JSON',
			success:function(r){
				$("#empFullmemberAppForm").form('load',r);
			}
		});
	}
}
//创建下拉列表框
function createSelect(){
	//学历
	$('#education').combobox({    
	    url:'commonController/findDicList.do?codeMyid=degree_type',    
	    valueField:'code',    
	    textField:'text'
	}); 
	//甄选方式
	$('#chooseMode').combobox({    
	    url:'commonController/findDicList.do?codeMyid=choose_mode',    
	    valueField:'code',    
	    textField:'text'
	});
}
</script>
<form id="empFullmemberAppForm" method="post" style="width: auto;margin-left:5px;">
    <fieldset>
    <legend><img src="extend/fromedit.png" style="margin-bottom: -3px;"/>基本信息</legend>
        <input name="efaId" type="hidden"/>
        <input name="appNo" type="hidden"/>
        <input name="appDate" type="hidden"/>
        <input name="deptNo" type="hidden"/>
        <input name="appStatus" type="hidden"/>
        <input name="procStatus" type="hidden"/>
        <input name="education" type="hidden"/>
        
        <input name="applicantNo" type="hidden"/>
        <input name="position" type="hidden"/>
        <input name="graduateSchool" type="hidden"/>
        <input name="major" type="hidden"/>
        <input name="educationName" type="hidden"/>
        <input name="age" type="hidden"/>
        <input name="regularSal" type="hidden" class="easyui-textbox easyui-validatebox easyui-numberbox" data-options="precision:2,groupSeparator:','"/>
        <input name="bgProbDate" type="hidden"/>
	    <input name="edProbDate" type="hidden"/>
	    <input name="entryDate" type="hidden"/>
	    <table class="table">
	      <tr>
	        <th>申请人:</th>
	        <td><input name="userName" class="easyui-textbox easyui-validatebox" readonly="readonly"/></td>
	        <th>部门名称:</th>
	        <td><input id="deptName" name="deptName" class="easyui-textbox" readonly="readonly"/></td>
	      </tr>
	      <tr>
	        <th>工作经验年限:</th>
	        <td><input name="workYears" class="easyui-textbox easyui-numberbox" data-options="min:0,max:100"/>年</td>
	        <th>相关工作<br/>经验年限:</th>
	        <td><input name="corWorkYears" class="easyui-textbox easyui-numberbox" data-options="min:0,max:100"/>年</td>
	      </tr>
	      <tr>
	        <th>非相关工作<br/>经验年限:</th>
	        <td><input name="nocorWorkYears" class="easyui-textbox easyui-numberbox" data-options="min:0,max:100"/>年</td>
	        <th>甄选方式:</th>
	        <td><input id="chooseMode" name="chooseMode" class="easyui-textbox" panelHeight="auto" editable="false"/></td>
	      </tr>
	      <tr>
	        <th>工作说明:</th>
	        <td colspan="3"><textarea name="workExplain" rows="5" cols="20" class="easyui-textbox easyui-validatebox" maxlength="100" style="width: 470px;height: 50px;"></textarea></td>
	      </tr>
	    </table>
    </fieldset>
</form>