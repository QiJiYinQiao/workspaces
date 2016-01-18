<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
	<script type="text/javascript" src="js/jquery.md5.js"></script>
	<script type="text/javascript" src="js/validate.js"></script>
<script type="text/javascript">
	$(function() {
		//组织机构
		$("#organizeId").combotree({
			width:171,
			url:"orgz/organizationAction!findOrganizationList.action",
			idFiled:'id',
		 	textFiled:'name',
		 	parentField:'pid',
		 	onSelect:function(node){
		 		//$("#organizeName").val(node.text);
		 	}
		});
		//有字典的下拉框
		$("#gender").combobox({
			url:'baseAction!getDicText.action?code=gender_type',
			valueField:"dictCode",
			textField:"dictName"
		})
		$("#nation").combobox({
			url:'baseAction!getDicText.action?code=nation',
			valueField:"dictCode",
			textField:"dictName"
		})
		$("#healthStatus").combobox({
			url:'baseAction!getDicText.action?code=health_status',
			valueField:"dictCode",
			textField:"dictName"
		})
		$("#domiType").combobox({
			url:'baseAction!getDicText.action?code=domi_type',
			valueField:"dictCode",
			textField:"dictName"
		})
		$("#maritalStatus").combobox({
			url:'baseAction!getDicText.action?code=marital_status',
			valueField:"dictCode",
			textField:"dictName"
		})
		$("#education").combobox({
			url:'baseAction!getDicText.action?code=degree_type',
			valueField:"dictCode",
			textField:"dictName"
		})
		$("#ecRelation").combobox({
			url:'baseAction!getDicText.action?code=relationship_type',
			valueField:"dictCode",
			textField:"dictName"
		})
		
		$("#form").form({
			url :"user/userAction!persistenceUsersDig.action",
			onSubmit : function() {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				var isValid = $(this).form('validate');
				if (!isValid) {
					parent.$.messager.progress('close');
					return false;
				}
				//20150512 前台修改用户信息,MD5操作
				//若密码作了修改
				var row = parent.$.modalDialog.openner.datagrid('getSelected');
				if(row!=null){
					if($("#password").val() != row.password){
						$("#password").val($.md5($("#password").val()).toUpperCase());
					}
				}else{
					$("#password").val($.md5($("#password").val()).toUpperCase());
				}
				return true;
				
			},
			success : function(result) {
				parent.$.messager.progress('close');
				result = $.parseJSON(result);
				if (result.status) {
					parent.reload;
					parent.$.modalDialog.openner.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_datagrid这个对象，是因为role.jsp页面预定义好了
					parent.$.modalDialog.handler.dialog('close');
					parent.$.messager.show({
						title : result.title,
						msg : result.message,
						timeout : 1000 * 2
					});
				}else{
					parent.$.messager.show({
						title :  result.title,
						msg : result.message,
						timeout : 1000 * 2
					});
				}
			}
		});
	});
	
</script>
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
	table ,th,td{
		text-align:left;
		padding: 6px;
	}
</style>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 10px;">
		<form id="form" method="post">
			<fieldset>
				<legend><img src="extend/fromedit.png" style="margin-bottom: -3px;"/> 用户编辑</legend>
				<input name="userId" id="userId"  type="hidden"/>
				<input name="created" id="created"  type="hidden"/>
				<input name="creater" id="creater"  type="hidden"/>
				<input name="status" id="status"  type="hidden"/>
				 <table>
				 <tr>
				 <th colspan="5">注:标<span style="color: red">*</span>为必填项</th>
				 </tr>
					 <tr>
					    <td style="text-align: right;"><span style="color: red">*</span>用户编码:</td>
						<td><input name="myid" id="myid" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'length[0,25]'"/></td>
						<td style="text-align: right;"><span style="color: red">*</span>用户账号:</td>
						<td><input name="account"  class="easyui-textbox easyui-validatebox" id="account" data-options="required:true,validType:'length[0,25]'"/></td>
					    <td style="text-align: right;"><span style="color: red">*</span>用户名:</td>
						<td><input name="name" id="name" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'length[0,25]'"/></td>
					 </tr>
					 <tr>
						<td style="text-align: right;"><span style="color: red">*</span>用户密码:</td>
						<td><input id="password" name="password" type="password" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'length[0,64]'"/></td>
					    <td style="text-align: right;"><span style="color: red">*</span>重复密码:</td>
					    <td>
					      <input id="rpwd" name="rpwd" type="password" class="easyui-textbox easyui-validatebox" required="required" validType="same['password']"/>  
					    </td>
					    <td style="text-align: right;">邮箱:</td>
						<td><input id="email" name="email" type="text" class="easyui-textbox easyui-validatebox" data-options="validType:'email'"/></td>
					 </tr>
					 <tr>
					 	<td style="text-align: right;">固定电话:</td>
						<td><input id="tel" name="tel" class="easyui-textbox easyui-numberbox" data-options="validType:'length[0,20]'"/></td>
						<td style="text-align: right;">移动电话:</td>
						<td><input id="mobile" name="mobile" class="easyui-textbox easyui-numberbox" data-options="validType:'mobile'"/></td>
					    <td style="text-align: right;">组织部门:</td>
						<td colspan="3"><input id="organizeId" name="organizeId" type="text" class="easyui-textbox easyui-validatebox"/></td>
					 </tr>
					  <tr>
						<td style="text-align: right;">年龄:</td>
						<td><input name="age" id="age" class="easyui-textbox easyui-numberbox"/></td>
						<td style="text-align: right;">性别:</td>
						<td><input id="gender" name="gender" class="easyui-textbox easyui-validatebox"/></td>
					    <td style="text-align: right;">民族:</td>
						<td><input id="nation" name="nation" type="text" class="easyui-textbox easyui-validatebox"/></td>
					 </tr>
					  <tr>
						<td style="text-align: right;">籍贯:</td>
						<td><input id="origo" name="origo" class="easyui-textbox easyui-validatebox"/></td>
						<td style="text-align: right;">出生日期:</td>
						<td><input id="birthday" name="birthday" class="easyui-textbox easyui-datebox"/></td>
						<td style="text-align: right;">身高（cm）:</td>
						<td><input id="height" name="height" class="easyui-textbox easyui-numberbox"/></td>
					 </tr>
					  <tr>
						<td style="text-align: right;">体重(kg):</td>
						<td><input id="weight" name="weight" class="easyui-textbox easyui-validatebox"/></td>
					    <td style="text-align: right;">健康状况:</td>
						<td><input id="healthStatus" name="healthStatus" type="text" class="easyui-textbox easyui-validatebox"/></td>
						<td style="text-align: right;">户籍性质:</td>
						<td><input id="domiType" name="domiType" class="easyui-textbox easyui-validatebox"/></td>
					 </tr>
					  <tr>
						<td style="text-align: right;">身份证号码:</td>
						<td><input id="idCard" name="idCard" class="easyui-textbox easyui-validatebox" data-options="validType:'idcard'"/></td>
					    <td style="text-align: right;">身份证地址:</td>
						<td><input id="idCardAddr" name="idCardAddr" type="text" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,256]'"/></td>
						<td style="text-align: right;">婚姻状况:</td>
						<td><input id="maritalStatus" name="maritalStatus" class="easyui-textbox easyui-validatebox"/></td>
					 </tr>
					  <tr>
						<td style="text-align: right;">最高学历:</td>
						<td><input id="education" name="education" class="easyui-textbox easyui-validatebox"/></td>
					    <td style="text-align: right;">毕业院校:</td>
						<td><input id="graduateSchool" name="graduateSchool" type="text" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,50]'"/></td>
						<td style="text-align: right;">专业:</td>
						<td><input id="major" name="major" class="easyui-textbox easyui-validatebox"/></td>
					 </tr>
					  <tr>
						<td style="text-align: right;">毕业时间:</td>
						<td><input id="graduateDate" name="graduateDate" class="easyui-textbox easyui-datebox"/></td>
					    <td style="text-align: right;">参加工作时间:</td>
						<td><input id="takejobDate" name="takejobDate" type="text" class="easyui-textbox easyui-datebox"/></td>
						<td style="text-align: right;">户口所在地:</td>
						<td><input id="domiAddr" name="domiAddr" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,256]'"/></td>
					 </tr>
					  <tr>
						<td style="text-align: right;">通讯地址:</td>
						<td><input id="postalAddr" name="postalAddr" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,256]'"/></td>
						<td style="text-align: right;">工资卡号:</td>
						<td><input id="salCardNo" name="salCardNo" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,256]'"/></td>
						<td style="text-align: right;">紧急联系人:</td>
						<td><input id="ecContactsName" name="ecContactsName" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,256]'"/></td>
					 </tr>
					  <tr>
						<td style="text-align: right;">紧急联系人<br/>联系方式:</td>
						<td><input id="ecLandline" name="ecLandline" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,256]'"/></td>
						<td style="text-align: right;">与本人关系:</td>
						<td><input id="ecRelation" name="ecRelation" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,256]'"/></td>
					 </tr>
					 
					 <tr>
						<td style="text-align: right;">备注:</td>
						<td colspan="5"><textarea class="easyui-textbox" name="description"  style="width: 615px;height: 100px;" maxlength="100"></textarea></td>
					</tr>
				 </table>
			</fieldset>
		</form>
	</div>
</div>
