<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../../../layout/script.jsp"></jsp:include>
<title>管理中心月度工作计划表</title>
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
.table {
	background-color: transparent;
	border-collapse: collapse;
	border-spacing: 0;
	max-width: 100%;
}
fieldset {
	    border-width: 1px;
	    margin-top: 5px;
	    border-color:#F5F5F5;
	    -moz-border-radius:8px;
}
input, textarea {
	font-weight: normal;
}

.table td {
	padding: 6px;
}
.table th{
    text-align: right;
	padding: 6px;
}
.textStyle{
  text-align: right;
}
</style>
</head>
<script type="text/javascript">
var $AddDialog;
var $EditDialog;
var $row;
var $$row;
var datagrid;
$(function(){
	createMonthlyWorkPlanGrid();
});
function createMonthlyWorkPlanGrid(){
	datagrid = $("#monthlyWorkPlanGrid").datagrid({
		url:'monthlyWorkPlanController/findMonthlyWorkPlanList.do',
		title:'管理中心月度工作计划表',
		width: 'auto',
		height : $(this).height()-85,
		pagination:true,
		rownumbers:true,
		border:false,
		singleSelect:false,
		nowrap:true,//如果为true，则在同一行中显示数据。设置为true可以提高加载性能。
		pageSize:30,
		pageList:[10,20,30,40],
		remoteSort:false,//定义是否从服务器对数据进行排序。
		striped:true,//是否显示斑马线
		columns:[[
               // {field : 'checkbox',checkbox:true,rowspan:2},
		        {field : 'preparerName',title : '填表人',width :120,align : 'center',rowspan:2},
                {field : 'appDeptName',title : '所属部门',width : 100,align : 'center',rowspan:2},
	            {field : 'position',title : '填表人职位',width : 100,align : 'center',rowspan:2},
                {field : 'fillingDate',title : '填表日期',width : 150,align : 'center',rowspan:2},
                {field : 'ufwReson',title : '未完成原因',width : 80,align : 'center',rowspan:2},
                {title : '本月总结',width : 450,align:'center',colspan:5},
                {title : '下月计划',width : 450,align:'center',colspan:5},
                {field : 'sonRemark',title : '备注信息',width : 150,align : 'center',rowspan:2},
                {field : 'fujian',title : '附件',width :100,align : 'center',rowspan:2,formatter:function(value,row,index){
                	var mwpId = row.mwpId;
                	return "<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" onclick=\"checkAttachementDetail(\'"+mwpId+"\','','','')\">查看附件</a>";
                }}
	   ],[
			{field : 'contSumup',title : '本月工作内容总结',width : 100,align : 'center'},
			{field : 'completed1',title : '第一周(%)',width : 80,align : 'center'},
			{field : 'completed2',title : '第二周(%)',width : 80,align : 'center'},
			{field : 'completed3',title : '第三周(%)',width : 80,align : 'center'},
			{field : 'completed4',title : '第四周(%)',width : 80,align : 'center'},
			
			{field : 'contPlan',title : '下月工作内容计划',width : 100,align : 'center'},
			{field : 'planComp1',title : '第一周(%)',width : 80,align : 'center'},
			{field : 'planComp2',title : '第二周(%)',width : 80,align : 'center'},
			{field : 'planComp3',title : '第三周(%)',width : 80,align : 'center'},
			{field : 'planComp4',title : '第四周(%)',width : 80,align : 'center'}
	   ]],
	   onLoadSuccess:function(data){
		   var rows = data.rows;
           var mergeMap = {};
           if(rows){
           	for(var i=0;i<rows.length;i++){
           		var mwpId = rows[i].mwpId
           		if( mwpId in mergeMap ){
           			mergeMap[mwpId].rowspan++;
           		}else{
           			mergeMap[mwpId]={"index":i,"rowspan":1}
           		}
           	}
           }
           for(var i in mergeMap){
        	$(this).datagrid('mergeCells',{
                   index: mergeMap[i].index,
                   field: 'checkbox',
                   rowspan: mergeMap[i].rowspan
            });
           	$(this).datagrid('mergeCells',{
                   index: mergeMap[i].index,
                   field: 'preparerName',
                   rowspan: mergeMap[i].rowspan
            });
           	$(this).datagrid('mergeCells',{
                   index: mergeMap[i].index,
                   field: 'appDeptName',
                   rowspan: mergeMap[i].rowspan
               });
               $(this).datagrid('mergeCells',{
                   index: mergeMap[i].index,
                   field: 'position',
                   rowspan: mergeMap[i].rowspan
               });
               $(this).datagrid('mergeCells',{
                   index: mergeMap[i].index,
                   field: 'fillingDate',
                   rowspan: mergeMap[i].rowspan
               });
               $(this).datagrid('mergeCells',{
                   index: mergeMap[i].index,
                   field: 'ufwReson',
                   rowspan: mergeMap[i].rowspan
               });
               $(this).datagrid('mergeCells',{
                   index: mergeMap[i].index,
                   field: 'remark',
                   rowspan: mergeMap[i].rowspan
               });
           } 
           $(this).datagrid("doCellTip",{'max-width':'100px'});
	   }, 
	   toolbar:[{
		   iconCls: 'icon-add',
		   text:'添加',
		   handler:toOpenAddDialog
	   },'-',{
		   iconCls: 'icon-edit',
		   text:'编辑',
		   handler:toEdit
	   },'-',{
		   iconCls: 'icon-cut',
		   text:'删除',
		   handler:toDelete
	   }]
	}); 
}
//新增弹框
function toOpenAddDialog(){
	$AddDialog=$("<div></div>").dialog({
		title: '新增',    
	    width: 920,    
	    height: 625,    
	    closed: false,    
	    cache: false,    
	    href: 'jsp/ad/monthlyWorkPlan/monthlyWorkPlanForm.jsp',    
	    modal: true,
	    onClose:function(){
	    	datagrid.datagrid('reload');//刷新列表
	    	$(this).dialog('destroy');
	    },
	    buttons:[{
	    	text:'关闭',
	    	iconCls:'icon-cancel',
			handler:function(){
				$AddDialog.dialog('close');
			}
	    }]
	});
}
//执行编辑
function saveEditmonthlyWorkPlan(){
	$.ajax({
		type:'POST',
		url:'monthlyWorkPlanController/savemonthlyWorkPlan.do',
		data:$("#monthlyWorkPlanForm").serialize(),
		dataType:'JSON',
		success:function(msg){
		   if(msg.status){
			   $("#monthlyWorkPlanGrid").datagrid('reload');//刷新列表
			   $EditDialog.dialog('destroy');//销毁弹窗
		   }
		   $.messager.alert(msg.title,msg.message,'info');
		}
	});
}
//删除
function toDelete(index){
	var rows = $("#monthlyWorkPlanGrid").datagrid("getSelections");
	if(rows!=null && rows.length <= 0){
		$.messager.alert("提示","请至少选择一条记录执行删除!","warning");
		return false;
	}
	var ids = new Array();
	for(var i=0;i<rows.length;i++){
		ids.push(rows[i].mwpId);
	}
	ids = ids.join(",");
	$.messager.confirm('确定','是否确定删除所选的数据吗?',function(b) {
		if(b){
			$.ajax({
				   type: "POST",
				   url: "monthlyWorkPlanController/delMonthlyWorkPlanbyMwpId.do",
				   data:{"ids":ids},
			       async: false,
			       dataType:'JSON',
				   success: function(msg){
					   if(msg.status){
						   $("#monthlyWorkPlanGrid").datagrid("reload");
					   }
					   $.messager.alert(msg.title,msg.message,'info');
				   }
			});
		}
	});
}
//高级搜索
function toSearch(){
	var data = $("#searchForm").serializeObject();
	$("#monthlyWorkPlanGrid").datagrid("reload",data);
};
//跳转编辑页面
function toEdit(index){
	var rows = $("#monthlyWorkPlanGrid").datagrid("getSelections");
	if(rows!=null && rows.length > 1){
		$.messager.alert("提示","您只能选择一条记录进行修改!","warning");
		return false;
	}
	$row = $("#monthlyWorkPlanGrid").datagrid("getSelected");
	if($row == null){
		$.messager.alert("提示","请选择一条记录进行修改!","warning");
		return false;
	}
	$EditDialog = $("<div></div>").dialog({    
	    title: '修改',    
	    width: 920,    
	    height: 620,    
	    closed: false,    
	    cache: false, 
	    resizable:true,
	    href:'jsp/ad/monthlyWorkPlan/monthlyWorkPlanForm.jsp',    
	    modal: true,
	    onClose : function(){
	    	datagrid.datagrid('reload');//刷新列表
	    	$(this).dialog('destroy');
	    	$row = null;
	    },
	    buttons:[{
	    	text:'关闭',
	    	iconCls:'icon-cancel',
			handler:function(){
				$EditDialog.dialog('close');
			}
	    }]
	});
}
</script>
<body>
<div class="position" style="margin-top: 5px;">您当前所在位置： 行政办公  &gt; 管理中心月度工作计划表</div>
<div class="well well-small" style="margin-left: 5px;margin-top: 5px">
	<form id="searchForm">
	  <table>
	    <tr>
	      <td>填表日期:</td>
	      <td>
	         <input id="appDateMini" name="appDateMini" class="easyui-textbox easyui-datebox" editable="false"/>
	         -
	         <input id="appDateMax" name="appDateMax" class="easyui-textbox easyui-datebox" editable="false"/>
	      </td>
	      <td>备注信息:</td>
	      <td><input name="remark" class="easyui-textbox"/></td>
	      <td>
	         <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="toSearch();">搜索</a>
	         <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" onclick="$('#searchForm').form('clear')">重置</a>
	      </td>
	    </tr>
	  </table>
	</form>
</div>
<table id="monthlyWorkPlanGrid"></table>
<div id="imageDialog"  class="easyui-dialog" title="流程图片" data-options="border:false,closed:true,fit:true">
	<img id="image" src="" >
</div>
</body>
</html>