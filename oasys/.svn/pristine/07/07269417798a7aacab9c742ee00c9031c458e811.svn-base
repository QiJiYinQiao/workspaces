<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
	.easyui-textbox{
		height: 18px;
		width: 170px;
		line-height: 16px;
	    /*border-radius: 3px 3px 3px 3px;*/
	    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset;
	    transition: border 0.2s linear 0s, box-shadow 0.2s linear 0s;
	}
	
	textarea:focus, input[type="text"]:focus{
	    border-color: rgba(82, 168, 236, 0.8);
	    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(82, 168, 236, 0.6);
	    outline: 0 none;
		}
		table {
	    background-color: transparent;
	    border-collapse: collapse;
	    border-spacing: 0;
	    max-width: 100%;
	}

	fieldset {
	    border: 0 none;
	    margin: 0;
	    padding: 0;
	}
	legend {
	    -moz-border-bottom-colors: none;
	    -moz-border-left-colors: none;
	    -moz-border-right-colors: none;
	    -moz-border-top-colors: none;
	    border-color: #E5E5E5;
	    border-image: none;
	    border-style: none none solid;
	    border-width: 0 0 1px;
	    color: #999999;
	    line-height: 20px;
	    display: block;
	    margin-bottom: 10px;
	    padding: 0;
	    width: 100%;
	}
	input, textarea {
	    font-weight: normal;
	}
	.table ,th,td{
		text-align:left;
		padding: 6px;
	}
</style>
<script type="text/javascript">

//组织机构
$("#deptName").combotree({
	width:171,
	url:"Organization/organizationList.do",
	idFiled:'id',
 	textFiled:'name',
 	parentField:'pid',
 	onChange:function(newValue,oldValue){
 		//$("#organizeName").val(node.text);
 		//级联部门下的用户
 		createCombobox(newValue)
 	}
});
//创建下拉列表
function createCombobox(orgId){
	$("#userId").combobox({
	    valueField:'code',    
	    textField:'text', 
	    mode:'remote',
	    url:'empRosterRegController/getUsersByOrgId.do?organizeId='+orgId,    
	    required:true,
	    onChange:function(newValue, oldValue){
	    	//自动load form表单里的用户数据
	    	$.ajax({
	    		url:'empRosterRegController/getUsersByUserId.do',
	    		type:'post',
	    		data:'userId='+newValue,
	    		async:false,
	    		success:function(data){
		    		$("#empRosterRegForm").form('load',data);
	    		},
	    		error:function(data){}
	    	})
	    }
	});
}

//有字典的下拉框
$("input[name='gender']").combobox({
	url:'commonController/findDicList.do?codeMyid=gender_type',
	valueField:"code",
	textField:"text",
	disabled:true
})
$("input[name='maritalStatus']").combobox({
	url:'commonController/findDicList.do?codeMyid=marital_status',
	valueField:"code",
	textField:"text",
	disabled:true
})

$("input[name='domiType']").combobox({
	url:'commonController/findDicList.do?codeMyid=domi_type',
	valueField:"code",
	textField:"text",
	disabled:true
})
$("input[name='isMakeOverall']").combobox({
	url:'commonController/findDicList.do?codeMyid=t_or_f',
	valueField:"code",
	textField:"text"
})
$("input[name='isSignContract']").combobox({
	url:'commonController/findDicList.do?codeMyid=t_or_f',
	valueField:"code",
	textField:"text"
})
</script>
<form id="empRosterRegForm" method="post" style="width: auto;margin-left:5px;">
	<input name="errId" type="hidden"/>
	<input name="isRegular" type="hidden"/>
	<input name="isChg" type="hidden"/>
	<input name="regularDate" type="hidden"/>
	<input name="resignDate" type="hidden"/>
	<input name="resignReason" type="hidden"/>
	<input name="workedDays" type="hidden"/>
    	<fieldset>
    	<legend>员工基本信息</legend>
	    <table class="table">
	      <tr>
	        <th>部门名称:</th>
	        <td><input id="deptName" name="deptName" class="easyui-textbox" readonly="readonly"/></td>
	        <th>姓名:</th>
	        <td><input id="userId" name="userId" class="easyui-textbox easyui-combobox" required/></td>
	        <th>性别:</th>
	        <td><input id="gender" name="gender" class="easyui-textbox" readonly="readonly"/></td>
	      </tr>
	     <!--  <tr>
	        <th>婚姻状况:</th>
	        <td><input name="maritalStatus" class="easyui-textbox easyui-cobmbobox" readonly="readonly"/></td>
	        <th>文化程度:</th>
	        <td><input name="education" class="easyui-textbox" readonly="readonly"/></td>
	        <th>出生日期:</th>
	        <td><input name="birthday" class="easyui-datebox" readonly="readonly" disabled/></td>
	      </tr>
	      <tr>
	        <th>毕业院校:</th>
	        <td><input name="graduateSchool" class="easyui-textbox easyui-numberbox" readonly="readonly" data-options="min:0,max:100"/></td>
	        <th>专业:</th>
	        <td><input name="major" class="easyui-textbox easyui-numberbox" readonly="readonly" data-options="min:0,precision:2,groupSeparator:','"/></td>
	        <th>籍贯:</th>
	        <td><input name="origo" class="easyui-textbox" readonly="readonly"/></td>
	      </tr>
	      <tr>
	        <th>联系方式:</th>
	        <td><input name="mobile" class="easyui-textbox" readonly="readonly"/></td>
	        <th>身份证号:</th>
	        <td><input name="idCard" class="easyui-textbox" readonly="readonly"/></td>
	        <th>身份证地址:</th>
	        <td><input name="idCardAddr" class="easyui-textbox easyui-numberbox" data-options="min:0,max:100" readonly="readonly"/></td>
	      </tr> -->
	      <tr>
	        <!-- <th>工资卡号:</th>
	        <td><input name="salCardNo" class="easyui-textbox" readonly="readonly"/></td> -->
	        <th>职务:</th>
	        <td><input name="duty" class="easyui-textbox easyui-validatebox"/></td>
	        <th>当前职务<br/>起始日期:</th>
	        <td><input name="dutyBgDate" class="easyui-datebox"/></td>
	        <th>表彰情况:</th>
	        <td><input name="commendInfo" class="easyui-textbox easyui-validatebox"/></td>
	      </tr>
	      <tr>
	        <th>入职时间:</th>
	        <td><input name="entryDate" class="easyui-textbox easyui-datebox"/></td>
	        <th>试用期<br/>期限:</th>
	        <td><input name="trialTlimit" class="easyui-textbox"/>个月</td>
	        <th>试用期<br/>结束时间:</th>
	        <td><input name="trialEdDate" class="easyui-textbox easyui-datebox"/></td>
	      </tr>
	      <tr>
	        <th>是否做工服:</th>
	        <td><input name="isMakeOverall" class="easyui-textbox"/></td>
	        <th>工服<br/>定做时间:</th>
	        <td><input name="overallsMakeDate" class="easyui-textbox easyui-datebox"/></td>
	      </tr>
	      </table>
	      </fieldset>
	      <fieldset>
	      <legend>工资情况</legend>
	    <table>
	      <tr>
	        <th>试用期<br/>基本工资:</th>
	        <td><input name="trialBaseSal" class="easyui-textbox easyui-numberbox" min="0"/></td>
	        <th>试用期<br/>岗位工资:</th>
	        <td><input name="trialPostSal" class="easyui-textbox easyui-validatebox easyui-numberbox" min="0"/></td>
	        <th>试用期<br/>绩效工资:</th>
	        <td><input name="trialPerfSal" class="easyui-textbox easyui-numberbox" min="0"/></td>
	      </tr>
	      <tr>
	        <th>转正<br/>基本工资:</th>
	        <td><input name="regularBaseSal" class="easyui-textbox easyui-validatebox easyui-numberbox" min="0"/></td>
	        <th>转正<br/>岗位工资:</th>
	        <td><input name="regularPostSal" class="easyui-textbox easyui-validatebox easyui-numberbox" min="0"/></td>
	        <th>转正<br/>绩效工资:</th>
	        <td><input name="regularPerfSal" class="easyui-textbox easyui-validatebox easyui-numberbox" min="0"/></td>
	      </tr>
	      <tr>
	        <th>本月最终<br/>薪资合计:</th>
	        <td><input name="curMonthSalCnt" class="easyui-textbox easyui-numberbox" min="0"/></td>
	       </tr>
	      </table>
	      </fieldset>
	      <fieldset>
	     	<legend>劳动合同情况</legend>
	     <table>
	      <tr>
	        <th>劳动合同<br/>编号:</th>
	        <td><input name="contractNo" class="easyui-textbox easyui-validatebox"/></td>
	        <th>劳动合同<br/>期限:</th>
	        <td><input name="contractPeriod" class="easyui-textbox easyui-numberbox"/>年</td>
	      </tr>
	      <tr>
	        <th>劳动合同<br/>开始时间:</th>
	        <td><input name="contractBgDate" class="easyui-textbox easyui-datebox"/></td>
	        <th>劳动合同<br/>结束时间:</th>
	        <td><input name="contractEdDate" class="easyui-datebox"/></td>
	        <th>是否签订<br/>劳动合同:</th>
	        <td><input name="isSignContract" class="easyui-textbox"/></td>
	      </tr>
	      </table>
	      </fieldset>
	      <fieldset>
	      <legend>五险一金缴纳情况</legend>
	      <table>
	      <tr>
	        <th>养老保险:</th>
	        <td><input name="endowmentIns" class="easyui-textbox easyui-validatebox easyui-numberbox" min="0"/></td>
	        <th>医疗保险:</th>
	        <td><input name="medicalIns" class="easyui-textbox easyui-numberbox" min="0"/></td>
	        <th>失业保险:</th>
	        <td><input name="unemploymentIns" class="easyui-textbox easyui-numberbox" min="0"/></td>
	      </tr>
	      <tr>
	        <th>工伤保险:</th>
	        <td><input name="workInjuryIns" class="easyui-textbox" min="0"/></td>
	        <th>生育保险:</th>
	        <td><input name="maternityIns" class="easyui-textbox easyui-validatebox easyui-numberbox" min="0"/></td>
	        <th>社保公积金:</th>
	        <td><input name="housingFund" class="easyui-textbox easyui-numberbox" min="0"/></td>
	      </tr>
	    </table>
	    </fieldset>
	    <fieldset>
	    <legend>备注</legend>
	    <table>
	      <tr>
	        <th>备注信息</th>
	        <td colspan="3"><textarea name="remark" rows="5" cols="20" class="easyui-textbox easyui-validatebox" maxlength="100" style="width: 470px;height: 50px;"></textarea></td>
	      </tr>
	    </table>
	    </fieldset>
</form>