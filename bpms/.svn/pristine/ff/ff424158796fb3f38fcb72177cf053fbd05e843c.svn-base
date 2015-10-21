<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>详情页面</title>
<style type="text/css">
.easyui-textbox {
	height: 18px;
	width: 170px;
	line-height: 16px;
	box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset;
	transition: border 0.2s linear 0s, box-shadow 0.2s linear 0s;
}
textarea:focus, input[type="text"]:focus {
	border-color: rgba(82, 168, 236, 0.8);
	box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px
		rgba(82, 168, 236, 0.6);
	outline: 0 none;
}
.table{
	background-color: transparent;
	border-collapse: collapse;
	border-spacing: 0;
	max-width: 100%;
}
input, textarea {
	font-weight: normal;
}

td {
	text-align: left;
	padding: 6px;
}
th{
    text-align: right;
	padding: 6px;
}
</style>
</head>
<script type="text/javascript">
$("#gender").val(jqueryUtil.showDicText("gender_type","${investor.genderType}"));//性别
$("#maritalStatus").val(jqueryUtil.showDicText("marriage_type","${investor.maritalStatus}"));//婚姻状况
$("#familyStatus").val(jqueryUtil.showDicText("has_child","${investor.familyStatus}"));//家庭情况
$("#famIncSta").val(jqueryUtil.showDicText("fam_inc_sta","${investor.famIncSta}"));//家庭收入情况
$("#idType").val(jqueryUtil.showDicText("id_type","${investor.idType}"));//证件类型
$("#degreeType").val(jqueryUtil.showDicText("degree_type","${investor.degreeType}"));//学历
$("#jobType").val(jqueryUtil.showDicText("job_type","${investor.jobType}"));//职业类型
$("#compScale").val(jqueryUtil.showDicText("company_scale_type","${investor.compScale}"));//单位规模
var provinceArr = jqueryUtil.getAreaTextArr(1);//获取省
var cityArr = jqueryUtil.getAreaTextArr("${investor.provinceId}");
var areaArr = jqueryUtil.getAreaTextArr("${investor.cityId}");
var addressText = jqueryUtil.showText("${investor.provinceId}",provinceArr)+"省"
                  +jqueryUtil.showText("${investor.cityId}",cityArr)
                  +jqueryUtil.showText("${investor.areaId}",areaArr)
                  +"${investor.addressDetails}";
$("#commAddr").val(addressText);//通讯地址  
</script>

<div class="well well-small" style="margin:5px;">
  <table width='100%' height='100%' class="table">
     <tr>
        <th align="right" width="25%">中文姓名:</th>
        <td width="25%"><input name="chName" class="easyui-textbox" value="${investor.chName}" disabled="disabled"/></td>
        <th align="right" width="25%">英文姓名:</th>
        <td width="25%"><input name="enName" class="easyui-textbox" value="${investor.enName}" disabled="disabled"/></td>
     </tr>
     <tr>
        <th align="right">性别:</th>
        <td><input id="gender" name="gender" class="easyui-textbox"  disabled="disabled"/></td>
        <th align="right">出生日期:</th>
        <td><input name="birthday" class="easyui-textbox" value="${investor.birthday}" disabled="disabled"/></td>
     </tr>
     <tr>
        <th align="right">国籍:</th>
        <td><input name="nationality" class="easyui-textbox" value="${investor.nationality}" disabled="disabled"/></td>
        <th align="right">婚姻状况:</th>
        <td><input id="maritalStatus" name="maritalStatus" class="easyui-textbox" disabled="disabled"/></td>
     </tr>
     <tr>
        <th align="right">家庭情况:</th>
        <td><input id="familyStatus" name="familyStatus" class="easyui-textbox" disabled="disabled"/></td>
        <th align="right">家庭收入情况:</th>
        <td><input id="famIncSta" name="famIncSta" class="easyui-textbox" disabled="disabled"/></td>
     </tr>
     <tr>
        <th align="right">证件类型:</th>
        <td><input id="idType" name="idType" class="easyui-textbox" disabled="disabled"/></td>
        <th align="right">证件号码:</th>
        <td><input name="idNo" class="easyui-textbox" value="${investor.idNo}" disabled="disabled"/></td>
     </tr>
     <tr>
        <th align="right">签发日期:</th>
        <td><input name="idIssueDate" class="easyui-textbox" value="${investor.idIssueDate}" disabled="disabled"/></td>
        <th align="right">失效日期:</th>
        <td><input name="idExpireDate" class="easyui-textbox" value="${investor.idExpireDate}" disabled="disabled"/></td>
     </tr>
     <tr>
        <th align="right">发证机构所在地:</th>
        <td><input name="idLocation" class="easyui-textbox" value="${investor.idLocation}" disabled="disabled"/></td>
        <th align="right">学历:</th>
        <td><input id="degreeType" name="degreeType" class="easyui-textbox" disabled="disabled"/></td>
     </tr>
     <tr>
        <th align="right">职业类型:</th>
        <td><input id="jobType" name="jobType" class="easyui-textbox" disabled="disabled"/></td>
        <th align="right">所属行业:</th>
        <td><input name="industry" class="easyui-textbox" value="${investor.industry}" disabled="disabled"/></td>
     </tr>
     <tr>
        <th align="right">单位名称:</th>
        <td><input name="companyName" class="easyui-textbox" value="${investor.companyName}" disabled="disabled"/></td>
        <th align="right">工作年限:</th>
        <td><input name="yearsOfWork" class="easyui-textbox" value="${investor.yearsOfWork}" disabled="disabled"/></td>
     </tr>
     <tr>
        <th align="right">单位规模:</th>
        <td><input id="compScale" name="compScale" class="easyui-textbox" disabled="disabled"/></td>
        <th align="right">职务:</th>
        <td><input name="jobTitle" class="easyui-textbox" value="${investor.jobTitle}" disabled="disabled"/></td>
     </tr>
     <tr>
        <th align="right">移动电话:</th>
        <td><input name="mobileTel" class="easyui-textbox" value="${investor.mobileTel}" disabled="disabled"/></td>
        <th align="right">固定电话:</th>
        <td><input name="fixedTel" class="easyui-textbox" value="${investor.fixedTel}" disabled="disabled"/></td>
     </tr>
     <tr>
        <th align="right">电子邮箱:</th>
        <td><input name="email" class="easyui-textbox" value="${investor.email}" disabled="disabled"/></td>
        <th align="right">邮政编码:</th>
        <td><input name="commAddr" class="easyui-textbox" value="${investor.zip}" disabled="disabled"/></td>
     </tr>
     <tr>
        <th align="right">通讯地址:</th>
        <td colspan="3"><textarea id="commAddr" name="commAddr" disabled="disabled" style="width: 549px;" rows="4" cols="35"></textarea></td>
     </tr>
<!--      <tr>
        <td colspan="4" style="text-align: right;">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#investorView').dialog('close')">关闭</a>
        </td>
     </tr> -->
  </table>
</div>
</html>