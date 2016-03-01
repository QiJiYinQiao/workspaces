<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
// 保存共同贷款人的信息
function saveLoanerJoint(){
	if(!checkIsSaveLoaner()) return false;
	if(!$("#loanerJointForm").form('validate')){return false;}
	$("#loanerJointForm input[name='loanOrderId']").val($("#loanOrderId").val());// 设置订单ID
		$.ajax({
			type:'POST',
			url:'loanerJoint/loanerJointAction!persistenceLoanerJoint.action',
			data:$('#loanerJointForm').serialize(),
			success:function(iJson){
				if(iJson.status){
					$.messager.show({
						title:iJson.title,
						msg:iJson.message,
						timeout:5000,
						showType:'slide'
					});
				}else{
					$.messager.alert("提示","修改失败！！","error");
			}
		}			
	});
}

// 渲染共同借款人的信息
function loadLoanerJoint(row){
	 $.ajax({
			type:"POST",
			url: "loanerJoint/loanerJointAction!findLoanerJointByOrderId.action",
			data:{"loanOrderId":row.loanOrderId},
			dataType:"json",
			success:function(iJson){
				if(iJson==null){
				   //渲染共同贷款人户籍地址
				   renderProvinceSelect("residenceProvinceId","residenceCityId","residenceAreaId");
				   //渲染共同贷款人的当前地址
				   renderProvinceSelect("ljCurProvinceId","ljCurCityId","ljCurAreaId");
				   //渲染共同贷款人的单位地址
				   renderProvinceSelect("corpProvinceId","corpCityId","corpAreaId");
					return;
				}else{
				   	$("#loanerJointForm").form("load",iJson);
				   //渲染共同贷款人户籍地址
				   renderProvinceSelect("residenceProvinceId","residenceCityId","residenceAreaId",iJson.residenceProvinceId,iJson.residenceCityId,iJson.residenceAreaId);
				   //渲染共同贷款人的当前地址
				   renderProvinceSelect("ljCurProvinceId","ljCurCityId","ljCurAreaId",iJson.curProvinceId,iJson.curCityId,iJson.curAreaId);
				   //渲染共同贷款人的单位地址
				   renderProvinceSelect("corpProvinceId","corpCityId","corpAreaId",iJson.corpProvinceId,iJson.corpCityId,iJson.corpAreaId);
				}
			}
	 });
}
</script>
<div class="well well-small" style="margin-left: 5px;margin-top: 5px;width: 850px;">
   	<form id="loanerJointForm" method="post">
   		 <!-- 共同贷款人的ID -->
		<input id="ljId" name="ljId" type="hidden" />
		 <!-- 订单id -->
		<input name="loanOrderId" type="hidden"/>
		 <!-- 公共贷款人的户籍地址 -->
		<input name="residenceAddressId" type="hidden">
		<input name="residenceAddressType" type="hidden">
		 <!-- 公共贷款人的当前地址 -->
		<input name="curAddressId" type="hidden">
		<input name="curAddressType" type="hidden">
		 <!-- 公共贷款人的单位地址 -->
		<input name="corpAddressId" type="hidden">
		<input name="corpAddressType" type="hidden">
		<table class="table">
			<tr>
				<th>姓名:</th>
				<td><input  name="name" class="easyui-validatebox easyui-textbox" type="text" data-options="validType:'length[0,100]'"/></td>
			    <th>身份证号:</th>
				<td><input  name="idNo" type="text" class="easyui-validatebox easyui-textbox" data-options="required:true,validType:'idcard'"/></td>
				<th>性别:</th>
				<td><input  name="gender" type="text"   editable='false' panelHeight="auto"/></td>
			</tr>
			<tr>
				<th>手机:</th>
				<td><input  name="mobileTel" type="text" class="easyui-validatebox easyui-textbox" data-options="required:true,validType:'mobile'"/></td>
				<th>住址电话:</th>
				<td><input  name="fixedTel" type="text" class="easyui-validatebox easyui-textbox" data-options="required:true,validType:'phone'"/></td>
				<th>家庭电话:</th>
				<td><input  name="familyTel" type="text" class="easyui-validatebox easyui-textbox" data-options="required:true,validType:'phone'"/></td>
			</tr>
			<tr>
				<th>年龄:</th>
				<td><input  name="age" type="text" class="easyui-validatebox easyui-textbox" data-options="validType:'age'"/></td>
				<th>婚姻状况:</th>
				<td><input  name="marriageType"   type="text"  editable='false' panelHeight="auto"/></td>
				<th>有无子女:</th>
				<td><input  name="hasChild" type="text"  editable='false' panelHeight="auto"/></td>
			</tr>
			<tr>
			    <th>邮箱:</th>
				<td>
				   <input  name="email" type="text" class="easyui-validatebox easyui-textbox" data-options="required:true,validType:'email'"/>
				</td>
				<th>QQ号:</th>
				<td><input name="qqNo" type="text" class="easyui-textbox easyui-validatebox" data-options="validType:'qq'"/></td>
			</tr>
			<tr>
				 <th>户籍地址:</th>
				 <td colspan="5">
				           省:<input id="residenceProvinceId" name="residenceProvinceId" type="text" class="easyui-combobox"  style="width: 100px;"/>
				           市:<input id="residenceCityId" name="residenceCityId" type="text" class="easyui-combobox" style="width: 100px;"/>
				    区/县:<input id="residenceAreaId" name="residenceAreaId" type="text" class="easyui-combobox" style="width: 100px;"/>
				       街道:<input name="residenceAddrDetails" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'length[0,250]'" style="width: 303px;"/>
				 </td>
			</tr>
			<tr>
				  <th>现住地址:</th>
				  <td colspan="5">
				                 省:<input id="ljCurProvinceId" name="curProvinceId" type="text" class="easyui-combobox" style="width: 100px;"/>
				                 市:<input id="ljCurCityId" name="curCityId" type="text" class="easyui-combobox" style="width: 100px;"/> 
				          区/县:<input id="ljCurAreaId" name="curAreaId" type="text" class="easyui-combobox" style="width: 100px;"/> 
				             街道:<input name="curAddrDetails" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'length[0,250]'" style="width: 303px;"/>     
				  </td>
			</tr>
			<tr>
				<th>工作单位:</th>
				<td><input  name="corpName" type="text" class="easyui-validatebox easyui-textbox" data-options="required:true,validType:'length[0,20]'"/></td>
				<th>单位电话:</th>
				<td><input  name="corpTel" type="text" class="easyui-validatebox easyui-textbox " data-options="required:true,validType:'phone'"/></td>
				<th>成立时间:</th>
				<td><input  name="foundedTime" type="text" class="easyui-datebox" data-options="editable:false"/></td>
			</tr>
			<tr>
				<th>营业面积:</th>
				<td><input  name="areaSize" type="text" class="easyui-textbox easyui-numberbox" data-options="required:true,validType:'mDouble'"/>m²</td>
				<th>单位性质:</th>
				<td><input  name="corpNature" type="text" class="easyui-validatebox"/></td>
				<th>注册资本:</th>
				<td><input  name="regCapital" type="text" class="easyui-textbox easyui-numberbox" data-options="required:true,validType:['mDouble','length[0,17]']"/>万元</td>
			</tr>
			<tr>
			    <th>员工数量:</th>
			    <td><input name="ljEmpAmt" class="easyui-validatebox easyui-textbox" data-options="required:true,validType:'mDouble'"/></td>
			</tr>
			<tr>
				 <th>单位地址:</th>
				 <td colspan="5">
				                省:<input id="corpProvinceId" name="corpProvinceId" type="text" class="easyui-combobox" style="width: 100px;"/>
				                市:<input id="corpCityId" name="corpCityId" type="text" class="easyui-combobox" style="width: 100px;"/> 
				         区/县:<input id="corpAreaId" name="corpAreaId" type="text" class="easyui-combobox" style="width: 100px;"/> 
				            街道:<input name="corpAddrDetails" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'length[0,250]'" style="width: 303px;"/>     
				 </td>
			</tr>
			<tr>
			   <td colspan="6" style="text-align: right;">
			      <a href="javascript:void(0)" id="rset" class="easyui-linkbutton" iconCls="icon-reload" onclick="clearFun('loanerJointForm')">重置</a>
			      <a href="javascript:void(0)" id="save" class="easyui-linkbutton" iconCls="icon-save" onclick="saveLoanerJoint();">保存</a>
			   </td>
			</tr>
		</table>
	</form>
</div>