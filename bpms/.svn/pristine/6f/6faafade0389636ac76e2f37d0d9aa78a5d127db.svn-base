<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
//渲染单位地址datagrid
var corpNatureData = jqueryUtil.getTextArr("corp_nature");
//渲染单位地址datagrid
function initDatagrid() {
	$("#dwDatagrid").datagrid({
			width : 863,
			height : 370,
			pagination : false,
			rownumbers : true,
			border : true,
			singleSelect : false,
			nowrap : true,
			multiSort : false,
			columns : [[
			            {field : 'ck',rowspan : 2,checkbox : true},
			            {field : 'name',title : '单位名称',width : 80,rowspan : 2,align : 'center'},
			            {field : 'tele',title : '单位电话',width : 100,rowspan : 2,align : 'center'},
			             {field : 'corpNature',title : '单位性质',width : 140,rowspan:2,align : 'center',formatter:function(value,row,index){
							 return jqueryUtil.showText(value,corpNatureData);
						 	}
			             },
			            {field : 'foundedTime',title : '成立时间',width : 100,rowspan : 2,align : 'center',formatter:function(value,row,index){
			            	 if(null!=value){
			            		  var date = new Date(value);
			            		  return date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
			            	  }
			            	  return " ";
			             }},
			            {field : 'regCapital',title : '注册资本',width : 80,rowspan : 2,align : 'center'},
			            {field : 'areaSize',title : '营业面积',width : 80,rowspan : 2,align : 'center'},
			            {field : 'empAmount',title : '员工数量',width : 80,rowspan : 2,align : 'center'},
			            {title : '单地址位',width : 340,colspan : 4,align : 'center'}
		            ],
					[
						{field : 'dwProvince',title : '省',width : 80,align : 'center',
							formatter : function(value,
									row, index) {
								return jqueryUtil.showText(
										value, provinceArr);
							}
						},
						{field : 'dwCity',title : '市',width : 80,align : 'center',
							formatter : function(value,
									row, index) {
								var cityArr = jqueryUtil
										.getAreaTextArr(row.dwProvince);
								return jqueryUtil.showText(value, cityArr);
							}
						},
						{field : 'dwArea',title : '县/区',width : 80,align : 'center',
							formatter : function(value,row, index) {
								var areaArr = jqueryUtil.getAreaTextArr(row.dwCity);
								return jqueryUtil.showText(value, areaArr);
							}
						},
						{field : 'dwAddrDetails',title : '详细地址',width : 200,align : 'center'}
					] ],
			toolbar : [ {
				iconCls : 'icon-edit',
				text : '编辑',
				handler : doEditDw
			}, "-", {
				iconCls : 'icon-cut',
				text : '删除',
				handler : doDeleteDw
			}, '-', {
				iconCls : 'icon-save',
				text : '保存',
				handler : doSaveDw
			} ],
			onLoadSuccess : function(data) {
				for (var i = 0; i < data.rows.length; i++) {
					if ("checked" == data.rows[i].stateChecked) {
						$("#dwDatagrid").datagrid("selectRow",i);
					}
				}
			}
		});
}

//新增保存单位
function toSaveDw(){
	//客户基本信息是否保存的标志
	if(!checkIsSaveLoaner()) return false;
	if(!$("#dwform").form('validate')){return false;}
	//贷款人客户id
	var $loanerId = $("#loanerId").val();
	//发送请求保存紧急联系人
	$.ajax({
		cache: true,
		type: "POST",
		url:"company/companyAction!saveCompany.action?loanerId="+$loanerId,
		data:$('#dwform').serialize(),
		async: false,
	    error: function(request) {
	        alert("Connection error");
	    },
	    success: function(iJson) {
	    	var res = JSON.parse(iJson);
			if(res.state){
				$("#dwDatagrid").datagrid('reload',{loanerId:$loanerId});//刷新列表
				$('#dwform').form('clear');//清空form表单
			}
			parent.$.messager.show({
				title : '提示',
				msg : res.msg,
				timeout : 4000 * 2
			});
	    }
	});
}
//保存订单与工作单位关系
function doSaveDw(){
	//客户基本信息是否保存的标志
	if(!checkIsSaveLoaner()) return false;
	//订单id
	var $loanOrderId = $("#loanOrderId").val();
	var row = $("#dwDatagrid").datagrid("getSelected");
	var rows = $('#dwDatagrid').datagrid('getSelections');
	if (row == null) {
		$.messager.alert("提示", "请至少勾选一条记录作为本次借款的工作单位!", "warning");
		return;
	}
	var ids = new Array();
	for(var i=0;i<rows.length;i++){
		ids.push(rows[i].comId);
	}
	ids=ids.join(","); 
	$.ajax( {
		type : "POST",
		url : 'loanorderAndCompany/loanorderAndCompanyAction!saveLoanorderAndCompany.action',
		data: "comIds="+ids+"&loanOrderId="+$loanOrderId,
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
//编辑
function doEditDw(){
	//客户基本信息是否保存的标志
	if(!checkIsSaveLoaner()) return false;
	var row = $("#dwDatagrid").datagrid("getSelected");
	var rows = $('#dwDatagrid').datagrid('getSelections');
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
		url : 'company/companyAction!doEdit.action',
		data : "comId="+row.comId+"&loanOrderId="+$loanOrderId,
		dataType:'JSON',
		success : function(iJson) {
			var res = JSON.parse(iJson);
			if(res.state){
				$("#dwform").form('load',row);
				//渲染市区
				renderProvinceSelect('dwProvince','dwCity','dwArea',row.dwProvince,row.dwCity,row.dwArea);
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
//删除
function doDeleteDw(){
	//客户基本信息是否保存的标志
	if(!checkIsSaveLoaner()) return false;
	//获取订单id
	var $loanOrderId = $("#loanOrderId").val();
	var selected = $('#dwDatagrid').datagrid('getSelections');
	if (selected.length <= 0) {
		$.messager.alert("提示", "请至少选择一条记录执行删除!", "warning");
		return;
	}
	var ids = new Array();
	for(var i=0,len=selected.length; i<len; i++){
		ids.push(selected[i].comId);
	}
	ids = ids.join(",");
	$.messager.confirm('删除', '执行删除后，数据将不可恢复,是否执行?', function(d) {
		if (d) {
			$.ajax( {
				type : "POST",
				url : 'company/companyAction!doDeleteById.action',
				data : "comIds="+ids+"&loanOrderId="+$loanOrderId,
				dataType:'JSON',
				success : function(iJson) {
					var res = JSON.parse(iJson);
					if(res.state){
						//贷款人客户id
						var $loanerId = $("#loanerId").val();
						//刷新列表
						$("#dwDatagrid").datagrid("reload",{loanerId:$loanerId});
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
</script>
<div class="well well-small" >
	  <form id="dwform">
	    <input name="comId" type="hidden"/><!-- 公司id -->
	    <input name="creator" type="hidden"/><!-- 创建人 -->
	    <input name="createDate" type="hidden"/><!-- 创建时间 -->
		<table class="table">
			<tr>
				<th>工作单位:</th>
				<td><input name="name" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'length[0,16]'" type="text" />
				<th>单位电话:</th>
				<td><input name="tele" type="text" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'phone'"/></td>
				<th>注册号:</th>
				<td><input name="regId" type="text" class="easyui-textbox easyui-validatebox" data-options="required:true"/></td>
				</tr>
			<tr>
				<th>营业面积(m²):</th>
				<td><input name="areaSize" type="text" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:['integer','length[0,10]']"/></td>
				<th>注册资本(元):</th>
				<td><input name="regCapital" type="text" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'mDouble'"/></td>
				<th>成立时间:</th>
				<td><input name="foundedTime" type="text" class="easyui-datebox"  editable='false'/></td>
			</tr>
			<tr>
				<th>单位性质:</th>
				<td><input name="corpNature" type="text" class="easyui-textbox"/></td>
				<th>员工数量:</th>
				<td><input name="empAmount" type="text" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'integer'"/></td>
				<th>经营范围:</th>
				<td colspan="3"><input name="businessScope" type="text" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'length[0,100]'"/></td>
			</tr>
			<tr>
			    <th>单位地址:</th>
			    <td colspan="5">
			                        省:<input id="dwProvince" name="dwProvince" class="easyui-combobox" style="width: 100px;" /> 
					市:<input id="dwCity" name="dwCity" class="easyui-combobox" style="width: 100px;"/> 
					县:<input id="dwArea" name="dwArea" class="easyui-combobox" style="width: 100px;"/>
					详细地址:<input name="dwAddrDetails" class="easyui-textbox easyui-validatebox" data-options="required:true" style="width: 300px;"/>
			    </td>
			</tr>
			<tr>
			    <td colspan="6" style="text-align: right;">
			       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" onclick="$('#dwform').form('clear');">重置</a>
			       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="toSaveDw();">保存</a>
			    </td>
			</tr>
		</table>
	</form>
</div>
<div >
  <table id="dwDatagrid"></table>
</div>