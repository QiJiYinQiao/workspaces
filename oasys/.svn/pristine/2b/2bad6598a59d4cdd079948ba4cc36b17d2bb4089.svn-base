<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <style>
		.resize{
			width: 700px;
			height: 50px;
			max-width: 700px;
			max-height: 50px;
			min-width: 700px;
			min-height: 50px;
		}
		.size{
			width: 400px;
			height: 70px;
			max-width: 400px;
			max-height: 70px;
			min-width: 400px;
			min-height: 70px;
		}
	</style> 
    <form id="baseForm" method="post" style="width: 850px;margin-left:5px;">
    <fieldset>
    <legend><img src="extend/fromedit.png" style="margin-bottom: -3px;"/>基本信息</legend>
    	  <input name="edaId" class="easyui-numberbox" type="hidden"/>
	      <input id="appNo" name="appNo" type="hidden"/><!-- 申请编号 -->
	      <input name="applicantNo" class="easyui-numberbox" type="hidden"/>
	      <input name="deptNo" class="easyui-numberbox" type="hidden"/>
	      <input name="deptName" type="hidden"/>
	      <input name="gender" type="hidden"/>
	      <input name="familyAddr" type="hidden"/>
	      <input name="mobile" type="hidden"/>
	      <input name="position" type="hidden"/>
	      <input name="entryDate" type="hidden"/>
	      <input name="appDate" type="hidden"/>
	      <input id="procStatus" name="procStatus" type="hidden"/>
	      <input name="appStatus" type="hidden"/> 
          <table>
             <tr>
                <th>离职日期:</th>
                <td><input id="dimissionDate" name="dimissionDate" class="easyui-textbox  easyui-validatebox" disabled="disabled"/></td>
             </tr>
             <tr>
		         <th class="textStyle">离职原因:</th>
		         <td colspan="5">
		            <textarea name="dimissionReson" rows="5" cols="20" class="easyui-textbox resize" data-options="validType:'length[0,250]'"></textarea>
		         </td>
		       </tr>
		     <tr>
		         <th class="textStyle">对公司看法及建议:</th>
		         <td colspan="5">
		            <textarea name="propose" rows="5" cols="20" class="easyui-textbox resize" data-options="validType:'length[0,500]'"></textarea>
		         </td>
		       </tr>
		     <tr>
		         <th class="textStyle">备注:</th>
		         <td colspan="5">
		            <textarea name="remark" rows="5" cols="20" class="easyui-textbox resize" data-options="validType:'length[0,200]'"></textarea>
		         </td>
		       </tr>
             <tr>
	             <td colspan="6" style="text-align: right;">
	                <a href="javascript:void(0)" id="rsetId" class="easyui-linkbutton" iconCls="icon-reload" onclick="clearForm();">重置</a>
	                <a href="javascript:void(0)" id="saveId" class="easyui-linkbutton" iconCls="icon-save" onclick="saveInvestor();">保存</a>
	             </td>
             </tr>
          </table>
    </fieldset>
    </form>
    <div style="margin: 5px;">
        <table id="linkPeopleGrid"></table>
    </div>

<script type="text/javascript">
$(function(){
//	initLinkPeopleGrid();
	
	
});


function initLinkPeopleGrid(){
	$("#linkPeopleGrid").datagrid({
		url : "empDimissionTakeoverInfoController/findAllList.do?appNo="+$("#appNo").val(),
		width: 'auto',
		height : $(this).height()-500,
		pagination:false,
		rownumbers:true,
		border:true,
		singleSelect:false,
		nowrap:true,//如果为true，则在同一行中显示数据。设置为true可以提高加载性能。
		remoteSort:false,//定义是否从服务器对数据进行排序。
		striped:true,//是否显示斑马线
		columns : [ [
                     /* {field : 'ck',rowspan:2,checkbox : true}, */
		             {field : 'content',title : '交接内容',width : 80,rowspan:2,align : 'center',formatter:function(value,row,index){
		                	if(row.takeoverGoods == "1"){
		                		return "资料书籍";
		                	}else if(row.takeoverGoods == "2"){
		                		return "设备";
		                	}else if(row.takeoverGoods == "3"){
		                		return "办公用品";
		                	}else if(row.takeoverGoods == "4"){
		                		return "财务状况";
		                	}else if(row.takeoverGoods == "5"){
		                		return "考勤工资";
		                	}else if(row.takeoverGoods == "6"){
		                		return "工装扣款";
		                	}else if(row.takeoverGoods == "7"){
		                		return "社保情况";
		                	}else{
		                		return "其它";
		                	}
		                }},
		             {field : 'receiverName',title : '接管人',width : 80,rowspan:2,align : 'center'},
		             {field : 'receiveDate',title : '接管日期',width : 120,rowspan:2,align : 'center'},
		             {field : 'takeoverDetail',title : '交接情况',width : 120,rowspan:2,align : 'center',formatter:function(value,row,index){
		                	if(row.takeoverDetail == "1"){
		                		return "已交接";
		                	}else{
		                		return "未交接";
		                	}
		             }},
		             {field : 'remark',title : '备注',width : 120,rowspan:2,align : 'center'}
		             
		]],
		toolbar: [{
			   iconCls: 'icon-add',
			   text:'新增',
			   handler:toFormWindow
		   },"-",{
			iconCls: 'icon-edit',
			text:'编辑',
			handler: toEditContact
		},"-",{
			iconCls: 'icon-cut',
			text:'删除',
			handler:toDelete
		}]
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
		url:"empDimissionAppController/saveEmpDimissionApp.do",
		data:$('#baseForm').serialize(),
		async: false,//默认true设置下，所有请求均为异步请求
		cache: true,
	    success: function(iJson) {
	    	if(iJson.status){
	    		$("#appNo").val(iJson.data);//
	    	}
	    	parent.$.messager.alert(iJson.title,iJson.message,'info');
	    }
	});
}
function toFormWindow(){
	var appNo=$("#appNo").val();
	if(null==appNo || ""==appNo){
		$.messager.alert("提示", "请先保存基本信息!", "warning");
		return;
	}
	$sonDialog = $("<div></div>").dialog({
		title: '新增资产',    
		width: 600,    
	    height: 300,    
	    closed: false,    
	    cache: false,    
	    href: 'jsp/hr/empDimissionApp/empDimissionTakeoverInfoForm.jsp',    
	    modal: true,
	    onLoad:function(){
	    	$("#takeoverGoods").combobox({
				url:"commonController/findDicList.do?codeMyid=takeover_goods",
				valueField: 'code',
				textField: 'text',
				required:true
			});
	    	createUser('linkPeopleForm');
	    	$("#takeoverDetail").combobox({
				url:"commonController/findDicList.do?codeMyid=takeover_status",
				valueField: 'code',
				textField: 'text',
				required:true
			});
	    	$("#receiveDate").datebox({disabled:false,required:true});
	    },
	    onClose:function(){
	    	$(this).dialog('close');
	    },
	    buttons:[{
	    	text:'保存并继续',
	    	iconCls:'icon-save',
			handler:function(){
				saveLinkInfo();
			}
	    },{
	    	text:'保存并关闭',
	    	iconCls:'icon-save',
			handler:function(){
				saveLinkInfo();
				$sonDialog.dialog('destroy');
			}
	    },{
	    	text:'关闭页面',
	    	iconCls:'icon-no',
			handler:function(){
				$sonDialog.dialog('destroy');
			}
	    }]
	});
}
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
	$sonDialog = $("<div></div>").dialog({
		title: '移交物品',    
	    width: 600,    
	    height: 300,    
	    closed: false,    
	    cache: false,    
	    href: 'jsp/hr/empDimissionApp/empDimissionTakeoverInfoForm.jsp',    
	    modal: true,
	    onLoad:function(){
	    	$("#takeoverGoods").combobox({
				url:"commonController/findDicList.do?codeMyid=takeover_goods",
				valueField: 'code',
				textField: 'text',
				required:true
			});
	    	createUser('linkPeopleForm');
	    	$("#takeoverDetail").combobox({
				url:"commonController/findDicList.do?codeMyid=takeover_status",
				valueField: 'code',
				textField: 'text',
				required:true
			});
	    	$("#receiveDate").datebox({disabled:false,required:true});
	    	var f = $("#linkPeopleForm");
			f.form("load", row);
	    },
	    onClose:function(){
	    	$(this).dialog('close');
	    	row = null;
	    },
	    buttons:[{
	    	text:'保存',
	    	iconCls:'icon-save',
			handler:function(){
				saveLinkInfo();
				$sonDialog.dialog('destroy');
			} 
	    },{
	    	text:'取消',
	    	iconCls:'icon-no',
			handler:function(){
				$sonDialog.dialog('destroy');
				row = null;
			}
	    }]
	});
}

//保存附加表信息
function saveLinkInfo(){
	var isValid = $("#linkPeopleForm").form('validate');
	if(!isValid){
		return false;
	}
	var appNo = $("#appNo").val();
	$("#appNum").val(appNo);
	$.ajax({
		type: "POST",
		url:"empDimissionTakeoverInfoController/saveEmpDimissionTakeoverInfo.do",
		data:$('#linkPeopleForm').serialize(),
		async: false,//默认true设置下，所有请求均为异步请求
		cache: true,
	    success: function(iJson) {
	    	if(iJson.status){//保存成功
	    		$("#linkPeopleForm").form('clear');//清空表单
	    		initLinkPeopleGrid()
	    	}
	    	parent.$.messager.alert(iJson.title,iJson.message,'info');
	    }
	});
}

function toDelete(){
	var selected = $('#linkPeopleGrid').datagrid('getSelections');
	if (selected.length <= 0) {
		$.messager.alert("提示", "请至少选择一条记录执行删除!", "warning");
		return;
	}
	var ids = new Array();
	for(var i=0,len=selected.length; i<len; i++){
		ids.push(selected[i].daiId);
	}
	ids = ids.join(",");
	$.messager.confirm('删除', '执行删除后，数据将不可恢复,是否执行?', function(d) {
		if (d) {
			$.ajax( {
				type : "POST",
				url : 'empDimissionTakeoverInfoController/deleteByDaiIds.do',
				data : "daiIds="+ids,
				dataType:'JSON',
				success : function(iJson) {
					if(iJson.status){
						//投资人客户id
						var appNo = $("#appNo").val();
						$("#linkPeopleGrid").datagrid("reload");
					}
					parent.$.messager.alert(iJson.title,iJson.message,'info');
				}
			});
		}
	});
}


function createUser(formId){
	$("#"+formId+" input[name='receiverNo']").combogrid({    
	    panelWidth:250,  
	    mode: 'remote',   
	    idField:'userId',    
	    textField:'name', 
	    required:true,
	    url:'empDimissionTakeoverInfoController/createCombogrid.do',
	    columns:[[    
	        {field:'name',title:'用户名',width:120},    
	        {field:'fullName',title:'所在部门',width:90}   
	    ]],
		/* onSelect: function(index,row){ 
			$("#"+formId+" input[name='takeoverDeptName']").val(row.fullName);
			$("#"+formId+" input[name='takeoverDept']").val(row.organizeId);
			
		} */
	}); 
}
</script>