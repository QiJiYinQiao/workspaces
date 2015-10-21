<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
var provinceArr = jqueryUtil.getAreaTextArr(1);//获取省
var relationshipArr = jqueryUtil.getTextArr("relationship_type"); //与本人关系
//渲染紧急联系人列表
function linkPeopleDatagrid() {
	$("#linkPeople").datagrid({
			width : 863,
			height : 290,
			pagination : false,
			rownumbers : true,
			border : true,
			singleSelect : false,
			nowrap : true,
			multiSort : false,
			columns : [[
						{field : 'ck',rowspan : 2,checkbox : true},
						{field : 'chName',title : '姓名',width : 80,rowspan : 2,align : 'center'},
						{field : 'relationship',title : '与本人关系',width : 80,rowspan : 2,align : 'center',
							formatter : function(value,row, index) {
								return jqueryUtil.showText(value,relationshipArr);
							}
						},
						{field : 'tel',title : '手机',width : 140,rowspan : 2,align : 'center'},
						{field : 'workplace',title : '工作单位',width : 150,rowspan : 2,align : 'center'},
						{title : '单地址位',width : 340,colspan : 4,align : 'center'} ],
					[
						{field : 'compProvince',title : '省',width : 80,align : 'center',
							formatter : function(value,row, index) {
								return jqueryUtil.showText(value, provinceArr);
							}
						},
						{field : 'compCity',title : '市',width : 80,align : 'center',
							formatter : function(value,row, index) {
								var cityArr = jqueryUtil.getAreaTextArr(row.compProvince);
								return jqueryUtil.showText(value, cityArr);
							}
						},
						{field : 'compArea',title : '县/区',width : 80,align : 'center',
							formatter : function(value,row, index) {
								var areaArr = jqueryUtil.getAreaTextArr(row.compCity);
								return jqueryUtil.showText(value, areaArr);
							}
						},
						{field : 'compAddrDetails',title : '详细地址',width : 200,align : 'center'}
					] ],
			toolbar : [ {
				iconCls : 'icon-edit',
				text : '编辑',
				handler : doEdit
			}, "-", {
				iconCls : 'icon-cut',
				text : '删除',
				handler : doDelete
			}, '-', {
				iconCls : 'icon-save',
				text : '保存',
				handler : doSave
			} ],
			onLoadSuccess : function(data) {
				for (var i = 0; i < data.rows.length; i++) {
					if ("checked" == data.rows[i].checkedLinkMan) {
						$("#linkPeople").datagrid("selectRow",i);
					}
				}
			}
	});
}
//新增紧急联系人
function toSaveLinkPeople(){
	//客户基本信息是否保存的标志
	if(!checkIsSaveLoaner()) return false;
	if(!$("#LinkPeopleForm").form('validate')){return false;}
	//贷款人客户id
	var $loanerId = $("#loanerId").val();
	$("#cusId").val($loanerId);
	//发送请求保存紧急联系人
	$.ajax({
		cache: true,
		type: "POST",
		url:"contacts/contactsAction!saveContacts.action",
		data:$('#LinkPeopleForm').serialize(),
		async: false,
	    error: function(request) {
	        alert("Connection error");
	    },
	    success: function(res) {
	    	if(res.status){
	    		$("#LinkPeopleForm").form('clear');
	    		$("#LinkPeopleForm input[name='idType']").val("A");
	    		
		    	$("#linkPeople").datagrid("reload",{loanerId:$loanerId});
	    	}
	    	parent.$.messager.show({
				title : '提示',
				msg : res.message,
				timeout : 4000 * 2
			});
	    }
	});
}
//选择紧急联系人，作为本次借款的联系人
function doSave(){
	//客户基本信息是否保存的标志
	if(!checkIsSaveLoaner()) return false;
	//订单id
	var $loanOrderId = $("#loanOrderId").val();
	var row = $("#linkPeople").datagrid("getSelected");
	var selected = $('#linkPeople').datagrid('getSelections');
	if (row == null) {
		$.messager.alert("提示", "请至少勾选一个联系人作为本次借款的紧急联系人!", "warning");
		return;
	}
	var ids = new Array();
	for(var i=0;i<selected.length;i++){
		ids.push(selected[i].contactId);
	}
	ids=ids.join(","); 
	$.ajax( {
		type : "POST",
		url : 'loanorderAndContacts/loanorderAndContactsAction!saveLoanorderAndContacts.action',
		data: "contactIds="+ids+"&loanOrderId="+$loanOrderId,
		dataType:'json',
		success : function(iJson) {
			var res = JSON.parse(iJson);
			parent.$.messager.show({
				title : '提示',
				msg : res.msg,
				timeout : 4000 * 2
			});
		}
	});
}
//紧急联系人删除
function doDelete(){
	//客户基本信息是否保存的标志
	if(!checkIsSaveLoaner()) return false;
	//获取订单id
	var $loanOrderId = $("#loanOrderId").val();
	var selected = $('#linkPeople').datagrid('getSelections');
	if (selected.length <= 0) {
		$.messager.alert("提示", "请至少选择一条记录执行删除!", "warning");
		return;
	}
	var ids = new Array();
	for(var i=0,len=selected.length; i<len; i++){
		ids.push(selected[i].contactId);
	}
	ids = ids.join(",");
	$.messager.confirm('删除', '执行删除后，数据将不可恢复,是否执行?', function(d) {
		if (d) {
			$.ajax( {
				type : "POST",
				url : 'contacts/contactsAction!doDeleteById.action',
				data : "contactIds="+ids+"&loanOrderId="+$loanOrderId,
				dataType:'JSON',
				success : function(iJson) {
					var res = JSON.parse(iJson);
					if(res.state){
						//贷款人客户id
						var $loanerId = $("#loanerId").val();
						//刷新列表
						$("#linkPeople").datagrid("reload",{loanerId:$loanerId});
					}
					parent.$.messager.show({
						title : '提示',
						msg : res.msg,
						timeout : 4000 * 2
					});
				}
			});
		}
	});
}
//编辑紧急联系人
function doEdit(){
	//客户基本信息是否保存的标志
	if(!checkIsSaveLoaner()) return false;
	var row = $("#linkPeople").datagrid("getSelected");
	var rows = $('#linkPeople').datagrid('getSelections');
	if (row == null) {
		$.messager.alert("提示", "请选择一条记录进行编辑!", "warning");
		return;
	}
	if(rows.length >1){
		$.messager.alert("提示", "您只能选择一条记录执行编辑!", "warning");
		return;
	}
	//获取订单id
	var $loanOrderId = $("#loanOrderId").val();
	$.ajax( {
		type : "POST",
		url : 'contacts/contactsAction!doEdit.action',
		data : "contactId="+row.contactId+"&loanOrderId="+$loanOrderId,
		dataType:'JSON',
		success : function(iJson) {
			var res = JSON.parse(iJson);
			if(res.state){
				$("#LinkPeopleForm").form('load',row);
				//渲染市区
				renderProvinceSelect('compProvince','compCity','compArea',row.compProvince,row.compCity,row.compArea);
				renderProvinceSelect('familyProvince','familyCity','familyArea',row.familyProvince,row.familyCity,row.familyArea);
			}else{
				parent.$.messager.show({
					title : '提示',
					msg : res.msg,
					timeout : 4000 * 2
				});
			}
		}
	});
}
</script>
<div class="well well-small" style="margin-left: 5px;margin-top: 5px;width: 850px;">
	<form id="LinkPeopleForm">
	   <input id="cusId" name="cusId" type="hidden"/><!--贷款人id -->
	   <input id="contactId" name="contactId" type="hidden"/><!-- 紧急联系人id -->
	   <input name="cusType" type="hidden"/><!-- 客户类型 -->
	   <input name="contactStatus" type="hidden"/><!-- 联系人的状态 -->
	   <input name="enName" type="hidden"/><!-- 联系人的英文名字 -->
	   <input name="idType" value="A" type="hidden"/><!-- 证件类型 -->
	   <input name="zip" type="hidden"/><!-- 邮政编码 -->
	   <input name="creator" type="hidden"/><!-- 创建人 -->
	   <input name="createDate" type="hidden"/><!-- 创建时间 -->
	   <input name="email" type="hidden"/><!-- 邮箱 -->
	   <input name="birthday" type="hidden"/><!-- 出生日期 -->
		<table class="table">
			<tr>
				<th>姓名:</th>
				<td><input name="chName" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'length[0,16]'" type="text" /></td>
				<th>性别:</th>
				<td><input id="genderType" name="genderType" type="text"
					class="easyui-textbox easyui-validatebox"  panelHeight="auto" editable='false'/></td>
				<th>身份证号:</th>
				<td><input name="idNo" type="text" class="easyui-textbox easyui-validatebox" data-options="validType:'idcard'"/></td>
			</tr>
			<tr>
				<th>年龄:</th>
				<td><input name="age" type="text" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'age'"/></td>
				<th>手机:</th>
				<td><input id="tel" name="tel" type="text" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'mobile'"/></td>
				<th>家庭电话:</th>
				<td><input id="fixedTel" name="fixedTel" type="text" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'phone'"/></td>
			</tr>
			<tr>
				<th>工作单位:</th>
				<td><input name="workplace" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,50]'"/></td>
				<th>单位电话:</th>
				<td><input name="compTel" class="easyui-textbox easyui-validatebox" data-options="validType:'phone'"/></td>
				<th>职务:</th>
				<td><input name="jobTitle" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,100]'"/></td>
			</tr>
			<tr>
				<th>年收入(元):</th>
				<td><input name="annualSalary" class="easyui-textbox easyui-validatebox" data-options="validType:'mDouble'"/></td>
				<th>工作年限(年):</th>
				<td><input name="yearsOfWork" class="easyui-textbox easyui-validatebox" data-options="validType:['integer','length[0,2]']"/></td>
				<th>与本人关系:</th>
				<td>
				  <input id="relationship" name="relationship" class="easyui-textbox easyui-validatebox"  panelHeight="auto" editable='false'/>
				</td>
			</tr>
			<tr>
				<th>单位地址:</th>
				<td colspan="5">
				            省:<input id="compProvince" name="compProvince" class="easyui-combobox" style="width: 100px;"/> 
					市:<input id="compCity" name="compCity" class="easyui-combobox" style="width: 100px;"/> 
					县:<input id="compArea" name="compArea" class="easyui-combobox" style="width: 100px;"/>
					详细地址:<input name="compAddrDetails" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,250]'" style="width: 308px;"/>
				</td>
			</tr>
			<tr>
				<th>家庭地址:</th>
				<td colspan="5">
				        省:<input id="familyProvince" name="familyProvince" class="easyui-combobox" style="width: 100px;" /> 
				        市:<input id="familyCity" name="familyCity" class="easyui-combobox"  style="width: 100px;"/> 
				        县:<input id="familyArea" name="familyArea" class="easyui-combobox"  style="width: 100px;"/>
				        详细地址:<input name="familyAddrDetails" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,250]'" style="width: 308px;"/>
				</td>
			</tr>
			<tr>
				<td colspan="6" style="text-align: right;">
				  <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" onclick="$('#LinkPeopleForm').form('clear');">重置</a>
				  <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="toSaveLinkPeople();">保存</a>
				</td>
			</tr>
		</table>
	</form>
</div>
<div style="margin-left: 5px;margin-top: 5px;">
  <table id="linkPeople"></table>
</div>