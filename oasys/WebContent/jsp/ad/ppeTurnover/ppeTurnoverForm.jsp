<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/validate.js"></script>
<style>
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

.table {
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
.dis{
	display: none;
}
</style>
<script type="text/javascript">
	$(function(){
		$('#tt').tabs({    
		    border:false    
		}); 
		//性别
		//渲染下拉表格
		createUser('baseForm');
	    initLinkPeopleGrid();
	    $("#concedeDate").datebox({required:true});
	    
	})
	
	  function initLinkPeopleGrid(appNo){
	    	var uri = "";
	    	if(appNo != null){
	    		uri = "ppeTurnoverAppAttachController/findAllList.do?appNo="+appNo;
	    	}else{
	    		uri = "ppeTurnoverAppAttachController/findAllList.do";
	    	}
	    	$("#linkPeopleGrid").datagrid({
	    		url : uri,
	    		width : 875,
	    		height : 250,
	    		pagination:false,
	    		rownumbers:true,
	    		border:true,
	    		singleSelect:false,
	    		nowrap:true,
	    		multiSort:false,
	    		columns : [ [
	                         {field : 'ck',rowspan:2,checkbox : true},
	    		             {field : 'ppeNo',title : '资产编号',width : 80,rowspan:2,align : 'center'},
	    		             {field : 'ppeName',title : '名称',width : 80,rowspan:2,align : 'center'},
	    		             {field : 'model',title : '规格型号',width : 120,rowspan:2,align : 'center'},
	    		             {field : 'qty',title : '数量',width : 120,rowspan:2,align : 'center'},
	    		             {field : 'ppeAmt',title : '单价',width : 120,rowspan:2,align : 'center'}
	    		             
	    		]],
	    		toolbar: [{
	    			iconCls: 'icon-edit',
	    			text:'编辑',
	    			handler: toEditContact
	    		},"-",{
	    			iconCls: 'icon-cut',
	    			text:'删除',
	    			handler:toDelete
	    		}/* ,'-',{
	    			iconCls: 'icon-save',
	    			text:'保存',
	    			handler: toSaveInvestorAndContacts
	    		} */]
	    	});
	    }
	
    //保存申请基本信息
    function saveInvestor(){
    	var isValid = $("#baseForm").form('validate');
    	if(!isValid){
    		return false;
    	}
    	$.ajax({
    		type: "POST",
    		url:"ppeTurnoverAppController/addPpeTurnoverApp.do",
    		data:$('#baseForm').serialize(),
    		async: false,//默认true设置下，所有请求均为异步请求
    		cache: true,
    	    success: function(iJson) {
    	    	if(iJson.status){
    	    		$("#appNo").val(iJson.data);//
    	    		disableForm();//禁用form
    	    	}
    	    	parent.$.messager.show({
    				title : '提示',
    				msg : iJson.message,
    				timeout : 4000 * 2
    			}); 
    	    }
    	});
    }
    //禁用form表单
    function disableForm(){
    	$("#saveId").hide();//保存按钮影藏
    	$("#editId").show();//修改按钮显示出来
    	$("#rsetId").hide();//重置按钮隐藏
    	$("#sign").val("save");//设为为保存状态
    	$("#baseForm input[class^='easyui-']").each(function(i){
    		if($(this).hasClass("easyui-textbox easyui-validatebox")){
    			$(this).attr("disabled",true);
    		}else if($(this).hasClass("easyui-datebox")){
    			$(this).datebox({disabled: true});
    		}else if($(this).hasClass("easyui-combobox")){
    			$(this).combobox("disable");
    		}else if($(this).hasClass("easyui-numberbox")){
    			$(this).numberbox("disable");
    		}else{
    		}
    	})
    	$("#baseForm select").combobox("disable");
    }
    //解禁form
    function editForm(){
    	$("#editId").hide();//修改按钮影藏
    	$("#saveId").show();//保存按钮显示出来
    	$("#rsetId").show();//重置按钮显示
    	$("#sign").val("edit");//设为为编辑状态
    	$("#baseForm input[class^='easyui-']").each(function(i){
    		if($(this).hasClass("easyui-textbox easyui-validatebox")){
    			$(this).attr("disabled",false);
    		}else if($(this).hasClass("easyui-datebox")){
    			$(this).datebox({disabled: false});
    		}else if($(this).hasClass("easyui-combobox")){
    			$(this).combobox("enable");
    		}else if($(this).hasClass("easyui-numberbox")){
    			$(this).numberbox("enable");
    		}else{
    		}
    	})
    	$("#baseForm select").combobox("enable");
    }
    //重置
    function clearForm(){
    	$("#baseForm").form("clear");//form清空
    	$("#linkPeopleGrid").datagrid('reload',{loanerId:null});//刷新列表
    	$("#sign").val("edit");//设为编辑状态
    }
    //保存附加表信息
    function saveLinkInfo(){
    	var $sign = $("#sign").val();
    	if("edit" == $sign){
    		$.messager.alert("提示","请您填写申请单基本信息并保存!","warning");return;
    	}
    	var isValid = $("#linkPeopleForm").form('validate');
    	if(!isValid){
    		return false;
    	}
    	var appNo = $("#appNo").val();//投资人id
    	$("#appNum").val(appNo);
    	$.ajax({
    		type: "POST",
    		url:"ppeTurnoverAppAttachController/saveTurnoverAppAttach.do",
    		data:$('#linkPeopleForm').serialize(),
    		async: false,//默认true设置下，所有请求均为异步请求
    		cache: true,
    	    success: function(iJson) {
    	    	if(iJson.status){//保存成功
    	    		$("#linkPeopleForm").form('clear');//清空表单
    	    		initLinkPeopleGrid(appNo)
    	    	//	$("#linkPeopleGrid").datagrid('reload',{loanerId:appNo});//刷新列表
    	    	}
    	    	parent.$.messager.show({
    				title : '提示',
    				msg : iJson.message,
    				timeout : 4000 * 2
    			}); 
    	    }
    	});
    }
    //紧急联系人删除
    function toDelete(){
    	var selected = $('#linkPeopleGrid').datagrid('getSelections');
    	if (selected.length <= 0) {
    		$.messager.alert("提示", "请至少选择一条记录执行删除!", "warning");
    		return;
    	}
    	var ids = new Array();
    	for(var i=0,len=selected.length; i<len; i++){
    		ids.push(selected[i].ptaId);
    	}
    	ids = ids.join(",");
    	$.messager.confirm('删除', '执行删除后，数据将不可恢复,是否执行?', function(d) {
    		if (d) {
    			$.ajax( {
    				type : "POST",
    				url : 'ppeTurnoverAppAttachController/deleteByPtaIds.do',
    				data : "ptaIds="+ids+"&appNo="+$("#appNo").val(),
    				dataType:'JSON',
    				success : function(iJson) {
    					if(iJson.status){
    						//投资人客户id
    						var appNo = $("#appNo").val();
    						//刷新列表
    						initLinkPeopleGrid(appNo);
    						//$("#linkPeopleGrid").datagrid("reload",{loanerId:appNo});
    					}
    					parent.$.messager.show({
    						title : iJson.title,
    						msg : iJson.message,
    						timeout : 4000 * 2
    					});
    				}
    			});
    		}
    	});
    }
    //保存紧急联系人与投资人的关系
    function toSaveInvestorAndContacts(){
    	//投资人客户id
		var appNo = $("#appNo").val();
    	var selected = $('#linkPeopleGrid').datagrid('getSelections');
    	if (selected.length <= 0) {
    		$.messager.alert("提示", "请至少勾选一个联系人作为本次投资的紧急联系人!", "warning");
    		return;
    	}
    	var ids = new Array();
    	for(var i=0;i<selected.length;i++){
    		ids.push(selected[i].contactId);
    	}
    	ids=ids.join(","); 
    	$.ajax( {
    		type : "POST",
    		url : 'InvestorAndContacts/InvestorAndContactsAction!saveInvestorAndContacts.action',
    		data: "ptaIds="+ids+"&appNo="+appNo,
    		dataType:'JSON',
    		success : function(iJson) {
    			parent.$.messager.show({
    				title : iJson.title,
    				msg : iJson.message,
    				timeout : 4000 * 2
    			});
    		}
    	});
    }
    
    function createUser(formId){
    	$("#"+formId+" input[name='takeoverUser']").combogrid({    
    	    panelWidth:250,  
    	    mode: 'remote',   
    	    idField:'userId',    
    	    textField:'account', 
    	    required:true,
    	    url:'ppeTurnoverAppAttachController/createCombogrid.do',
    	    columns:[[    
    	        {field:'account',title:'用户名',width:120},    
    	        {field:'fullName',title:'所在部门',width:90}   
    	    ]],
    		onSelect: function(index,row){ 
    		//	alert(row.fullName+row.organizeId);
    			$("#"+formId+" input[name='takeoverDeptName']").val(row.fullName);
    			$("#"+formId+" input[name='takeoverDept']").val(row.organizeId);
    			
    		}
    	}); 
    }
    
    //编辑紧急联系人
    function toEditContact(){
    	var row = $("#linkPeopleGrid").datagrid("getSelected");
    	var rows = $('#linkPeopleGrid').datagrid('getSelections');
    	if (row == null) {
    		$.messager.alert("提示", "请选择一条记录进行编辑!", "warning");
    		return;
    	}
    	if(rows.length >1){
    		$.messager.alert("提示", "您只能选择一条记录执行编辑!", "warning");
    		return;
    	}
    	//将数据加载到表单中
    	$("#linkPeopleForm").form('load',row);
    	//渲染市区
        renderCitySelect('linkPeopleCityId','linkPeopleAreaId',row.compProvince);
        $("#linkPeopleCityId").combobox("setValue",row.compCity);
        $("#linkPeopleAreaId").combobox("setValue",row.compArea);
    }
</script>
<div id="tt">
  <div title="申请基本信息">
	  <div class="well well-small" style="margin:5px;">
	    <form id="baseForm">
	    <input name="ptaId" class="easyui-numberbox" type="hidden"/>
	      <input id="appNo" name="appNo" type="hidden"/><!-- 申请编号 -->
	      <input id="sign" type="hidden" value="edit"/><!-- 修改or保存状态标志 -->
	      <input name="applicantNo" class="easyui-numberbox" type="hidden"/>
	      <input name="appDept" class="easyui-numberbox" type="hidden"/>
	      <input id="procStatus" name="procStatus" type="hidden"/>
	      <input name="appStatus" type="hidden"/> 
          <table>
          	 <tr id="oneRow" class="dis">
          	 	<th>申请日期:</th>
          	 	<td><input name="appDate" class="easyui-textbox easyui-datebox" editable="false" disabled="disabled"/></td>
          	 	<th>申请人:</th>
          	 	<td><input id="name" name="name" class="easyui-textbox easyui-validatebox" disabled="disabled"/></td>
          	 	<th>申请部门:</th>
          	 	<td><input name="appDeptName" class="easyui-textbox easyui-validatebox" disabled="disabled"/></td>
          	 </tr> 
             <tr>
                <th>交接日期:</th>
                <td><input id="concedeDate" name="concedeDate" class="easyui-textbox easyui-validatebox easyui-validatebox"/></td>
                <th>接收人:</th>
                <td><input name="takeoverUser" class="easyui-textbox easyui-combogrid easyui-validatebox"/></td>
                <th>接收部门:</th>
                <td><input name="takeoverDeptName" class="easyui-textbox easyui-validatebox" required/>
                	<input name="takeoverDept" class="easyui-numberbox" type="hidden"/>
                </td>
             </tr>
             <tr>
		         <th class="textStyle">备注:</th>
		         <td colspan="5">
		            <textarea name="remark" rows="5" cols="20" class="easyui-textbox" style="width: 720px;height: 70px;"></textarea>
		         </td>
		       </tr>
             <tr>
	             <td colspan="6" style="text-align: right;">
	                <a href="javascript:void(0)" id="rsetId" class="easyui-linkbutton" iconCls="icon-reload" onclick="clearForm();">重置</a>
	                <a href="javascript:void(0)" id="editId" class="easyui-linkbutton" iconCls="icon-edit" style="display: none;" onclick="editForm()">编辑</a>
	                <a href="javascript:void(0)" id="saveId" class="easyui-linkbutton" iconCls="icon-save" onclick="saveInvestor();">保存</a>
	             </td>
             </tr>
          </table>
      </form>
	  </div>
  </div>
  <div title="移交资产信息">
    <div class="well well-small" style="margin:5px">
       <form id="linkPeopleForm">
       	<input id="appNum" name="appNo" type="hidden">
       	<input id="ptaId" name="ptaId" type="hidden">
         <table class="table">
           <tr>
             <th>资产编号:</th>
             <td><input name="ppeNo" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'length[0,16]'"/></td>
             <th>名称</th>
             <td><input name="ppeName" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'length[0,16]'"/></td>
             <th>规格型号:</th>
             <td><input name="model" class="easyui-textbox easyui-validatebox" panelHeight="auto" editable="false" data-options="required:true"/></td>
           </tr>
           <tr>
             <th>数量:</th>
             <td><input name="qty" class="easyui-textbox easyui-numberbox easyui-validatebox" editable="false" data-options="required:true"/></td>
             <th>单价:</th>
             <td><input  name="ppeAmt" class="easyui-textbox easyui-numberbox easyui-validatebox" panelHeight="auto" editable="false" data-options="required:true"/></td>
           </tr>
           
           <tr>
             <td colspan="6" style="text-align: right;">
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="saveLinkInfo();">保存</a>
             </td>
           </tr>
         </table>
       </form>
    </div>
    <div style="margin: 5px;">
        <table id="linkPeopleGrid"></table>
    </div>
  </div>
</div>