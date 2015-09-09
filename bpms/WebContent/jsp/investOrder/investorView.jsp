<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>详情页面</title>
</head>
<script type="text/javascript">
$("#gender").html(jqueryUtil.showDicText("gender_type","${investor.genderType}"));//性别
$("#maritalStatus").html(jqueryUtil.showDicText("marriage_type","${investor.maritalStatus}"));//婚姻状况
$("#familyStatus").html(jqueryUtil.showDicText("has_child","${investor.familyStatus}"));//家庭情况
$("#famIncSta").html(jqueryUtil.showDicText("fam_inc_sta","${investor.famIncSta}"));//家庭收入情况
$("#idType").html(jqueryUtil.showDicText("id_type","${investor.idType}"));//证件类型
$("#degreeType").html(jqueryUtil.showDicText("degree_type","${investor.degreeType}"));//学历
$("#jobType").html(jqueryUtil.showDicText("job_type","${investor.jobType}"));//职业类型
$("#compScale").html(jqueryUtil.showDicText("company_scale_type","${investor.compScale}"));//单位规模
var provinceArr = jqueryUtil.getAreaTextArr(1);//获取省
var cityArr = jqueryUtil.getAreaTextArr("${investor.provinceId}");
var areaArr = jqueryUtil.getAreaTextArr("${investor.cityId}");
var addressText = jqueryUtil.showText("${investor.provinceId}",provinceArr)+"省"
                  +jqueryUtil.showText("${investor.cityId}",cityArr)
                  +jqueryUtil.showText("${investor.areaId}",areaArr)
                  +"${investor.addressDetails}";
$("#commAddr").html(addressText);//通讯地址  
</script>

<div style="margin: 5px;">
  <table width='100%' height='100%' style="border-collapse:collapse; font-size: 12px;background-color:#F4f4f4;" cellpadding='5' border='1' align='center'>
     <tr>
        <th align="right" width="25%">中文姓名:</th>
        <td width="25%">${investor.chName}</td>
        <th align="right" width="25%">英文姓名:</th>
        <td width="25%">${investor.enName}</td>
     </tr>
     <tr>
        <th align="right">性别:</th>
        <td id="gender"></td>
        <th align="right">出生日期:</th>
        <td>${investor.birthday}</td>
     </tr>
     <tr>
        <th align="right">国籍:</th>
        <td>${investor.nationality}</td>
        <th align="right">婚姻状况:</th>
        <td id="maritalStatus"></td>
     </tr>
     <tr>
        <th align="right">家庭情况:</th>
        <td id="familyStatus"></td>
        <th align="right">家庭收入情况:</th>
        <td id="famIncSta"></td>
     </tr>
     <tr>
        <th align="right">证件类型:</th>
        <td id="idType"></td>
        <th align="right">证件号码:</th>
        <td>${investor.idNo}</td>
     </tr>
     <tr>
        <th align="right">签发日期:</th>
        <td>${investor.idIssueDate}</td>
        <th align="right">失效日期:</th>
        <td>${investor.idExpireDate}</td>
     </tr>
     <tr>
        <th align="right">发证机构所在地:</th>
        <td>${investor.idLocation}</td>
        <th align="right">学历:</th>
        <td id="degreeType"></td>
     </tr>
     <tr>
        <th align="right">职业类型:</th>
        <td id="jobType"></td>
        <th align="right">所属行业:</th>
        <td>${investor.industry}</td>
     </tr>
     <tr>
        <th align="right">单位名称:</th>
        <td>${investor.companyName}</td>
        <th align="right">工作年限:</th>
        <td>${investor.yearsOfWork}</td>
     </tr>
     <tr>
        <th align="right">单位规模:</th>
        <td id="compScale"></td>
        <th align="right">职务:</th>
        <td>${investor.jobTitle}</td>
     </tr>
     <tr>
        <th align="right">移动电话:</th>
        <td>${investor.mobileTel}</td>
        <th align="right">固定电话:</th>
        <td>${investor.fixedTel}</td>
     </tr>
     <tr>
        <th align="right">电子邮箱:</th>
        <td>${investor.email}</td>
        <th align="right">通讯地址:</th>
        <td id="commAddr"></td>
     </tr>
     <tr>
        <th align="right">邮政编码:</th>
        <td colspan="3">${investor.zip}</td>
     </tr>
<!--      <tr>
        <td colspan="4" style="text-align: right;">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#investorView').dialog('close')">关闭</a>
        </td>
     </tr> -->
  </table>
</div>
</html>