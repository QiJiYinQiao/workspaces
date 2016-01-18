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
<title>固定资产采购申请主页面</title>
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

a{
	text-decoration:none;
}
</style>
</head>
<script type="text/javascript">
var $row;
var $$row;
var $datagrid;
$(function(){
	createPurchaseAppGrid();
})
/**
 * 创建固定资产采购申请列表
 */
function createPurchaseAppGrid(){
	$datagrid = $("#purchaseAppGrid").datagrid({
		url:'purchaseAppController/searchPurchaseAppList.do',
		title:'固定资产采购申请',
		width: 'auto',
		height : $(this).height()-88,
		pagination:true,
		rownumbers:true,
		border:false,
		queryParams: {"appType":'2'},
		singleSelect:true,
		nowrap:true,//如果为true，则在同一行中显示数据。设置为true可以提高加载性能。
		pageSize:30,
		pageList:[10,20,30,40],
		remoteSort:false,//定义是否从服务器对数据进行排序。
		striped:true,//是否显示斑马线
		columns:[[
		        {field : 'appNo',title : '申请编号',width :120,align : 'center'},
                {field : 'account',title : '申请人',width : 80,align : 'center'},
	            {field : 'fullName',title : '申请部门',width : 160,align : 'center'},
	            {field : 'myId',title : '所属业务端',width : 80,align : 'center',formatter:function(value,row,index){
	            	if("JK" == value){
	            		return "借款";
	            	}else if("CF" == value){
	            		return "财富";
	            	}else{
	            	}
	            }},
                {field : 'totalAmt',title : '总计金额(元)',width : 80,align : 'center'},
                {field : 'appDate',title : '申请日期',width : 80,align : 'center'},
                {field : 'planRecDate',title : '计划到货日期',width : 80,align : 'center'},
                {field : 'appStatusName',title : '申请状态',width : 100,align : 'center',formatter:function(value,row,index){
                	if(value == null || value == ""){
                		return "初始状态";
                	}
                	return value;
                }},
                {field : 'articleName',title : '物品名称',width : 100,align : 'center'},
                {field : 'model',title : '型号规格',width : 70,align : 'center'},
                {field : 'price',title : '单价(元)',width : 80,align : 'center'},
                {field : 'qty',title : '数量',width : 60,align : 'center'},
                {field : 'ztotalAmt',title : '合计价格(元)',width : 80,align : 'center'},
                {field : 'purpose',title : '用途',width : 100,align : 'center'},
                {field : 'userName',title : '使用人',width : 80,align : 'center'},
                {field : 'depositaryName',title : '保管人',width : 80,align : 'center'},
                {field : 'zremark',title : '备注信息',width : 100,align : 'center'},
                {field : 'caozuo',title : '操作',width :180,align : 'center',formatter:function(value,row,index){
                	if(row.procStatus == "1"){
             	    	return "<a href='javascript:void(0);' onclick='toEdit("+index+");'>编辑</a>&nbsp;&nbsp;"+"<a href='javascript:void(0);' onclick='toDelete("+ index + ");'>删除</a>&nbsp;&nbsp;"+"<a href='javascript:void(0);' onclick='sumitInvestOrder("+index+");'>提交申请</a>";
             	    }else if(row.procStatus == "2"){
             	    	return "<a href='javascript:void(0);' onclick='checkHistoryOpinions("+ index +");'>查看审批意见</a>&nbsp;&nbsp;"+"<a href='javascript:void(0);' onclick='showImage("+ index + ");'>查看流程图</a>";
             	    }else{
             	    	return "<a href='javascript:void(0);' onclick='checkHistoryOpinions("+ index +");'>查看审批意见</a>";
             	    }
                }}
	   ]],
	   onLoadSuccess:function(data){
		   var rows = data.rows;
           var mergeMap = {};
           if(rows){
           	for(var i=0;i<rows.length;i++){
           		var appNo = rows[i].appNo
           		if( appNo in mergeMap ){
           			mergeMap[appNo].rowspan++;
           		}else{
           			mergeMap[appNo]={"index":i,"rowspan":1}
           		}
           	}
           }
           for(var i in mergeMap){
           	$(this).datagrid('mergeCells',{
                   index: mergeMap[i].index,
                   field: 'appNo',
                   rowspan: mergeMap[i].rowspan
               });
           	$(this).datagrid('mergeCells',{
                   index: mergeMap[i].index,
                   field: 'account',
                   rowspan: mergeMap[i].rowspan
               });
               $(this).datagrid('mergeCells',{
                   index: mergeMap[i].index,
                   field: 'fullName',
                   rowspan: mergeMap[i].rowspan
               });
               $(this).datagrid('mergeCells',{
                   index: mergeMap[i].index,
                   field: 'totalAmt',
                   rowspan: mergeMap[i].rowspan
               });
               $(this).datagrid('mergeCells',{
                   index: mergeMap[i].index,
                   field: 'appDate',
                   rowspan: mergeMap[i].rowspan
               });
               $(this).datagrid('mergeCells',{
                   index: mergeMap[i].index,
                   field: 'planRecDate',
                   rowspan: mergeMap[i].rowspan
               });
               $(this).datagrid('mergeCells',{
                   index: mergeMap[i].index,
                   field: 'procStatus',
                   rowspan: mergeMap[i].rowspan
               });
               $(this).datagrid('mergeCells',{
                   index: mergeMap[i].index,
                   field: 'remark',
                   rowspan: mergeMap[i].rowspan
               });
               $(this).datagrid('mergeCells',{
                   index: mergeMap[i].index,
                   field: 'caozuo',
                   rowspan: mergeMap[i].rowspan
               });
               $(this).datagrid('mergeCells',{
                   index: mergeMap[i].index,
                   field: 'appStatusName',
                   rowspan: mergeMap[i].rowspan
               });
               $(this).datagrid('mergeCells',{
                   index: mergeMap[i].index,
                   field: 'myId',
                   rowspan: mergeMap[i].rowspan
               });
           }
           $(this).datagrid("doCellTip",{'max-width':'100px'});
	  }, 
	  toolbar:[{
		   iconCls: 'icon-add',
		   text:'添加',
		   handler:toAdd
	  }]
	});
}
/**
 * 新增
 */
function toAdd(){
	var $AddDialog = $("<div></div>").dialog({    
	    title: '新增固定资产采购申请',    
	    width: 920,    
	    height: 600,    
	    closed: false,    
	    cache: false, 
	    resizable:true,
	    href: 'purchaseAppController/toAddWindow2.do',    
	    modal: true,
	    onClose : function(){
	    	$datagrid.datagrid("reload");
	    	$(this).dialog('destroy');
	    },
	    buttons : [ {
			text : '关闭',
			iconCls : 'icon-cancel',
			handler : function() {
				$AddDialog.dialog('close');
			}
		}]
	});
}
//删除
function toDelete(index){
	var row = getRowData(index);
	$.messager.confirm('确定','是否确定删除所选的数据吗?',function(b) {
		if(b){
			$.ajax({
				   type: "POST",
				   url: "purchaseAppController/delPurchaseApp.do?paId="+row.paId+"&appNo="+row.appNo,
			       async: false,
			       dataType:'JSON',
				   success: function(msg){
					   if(msg.status){
						   $datagrid.datagrid("reload");
					   }
					   $.messager.alert(msg.title,msg.message,'info');
				   }
			});
		}
	});
}
//根据index获取该行
function getRowData(index){
	if (!$.isNumeric(index) || index < 0) {
		return undefined;
	}
	var rows = $datagrid.datagrid("getRows");
	return rows[index];
}
//高级搜索
function toSearch(){
	var appNo = $("#searchForm input[name='appNo']").val();
	var appDateMini = $("#appDateMini").datebox('getValue');
	var appDateMax = $("#appDateMax").datebox('getValue'); 
	$datagrid.datagrid("reload",{"appNo":appNo,"appDateMini":appDateMini,"appDateMax":appDateMax});
};
//编辑
function toEdit(index){
	$row = getRowData(index);
	var $EditDialog = $("<div></div>").dialog({    
	    title: '修改固定资产采购申请',    
	    width: 920,    
	    height: 600,    
	    closed: false,    
	    cache: false, 
	    resizable:true,
	    href:'jsp/ad/purchaseApp/purchaseApp2Form.jsp',    
	    modal: true,
	    onClose : function(){
	    	$datagrid.datagrid("reload");
	    	$EditDialog.dialog('destroy');
	    	$row = null;
	    },
	    buttons : [ {
			text : '关闭',
			iconCls : 'icon-cancel',
			handler : function() {
				$EditDialog.dialog('close');
			}
		}]
	});
}
/**
 * 提交申请
 */
function sumitInvestOrder(index){
	var row = this.getRowData(index);
	var regS = new RegExp("\\,","g");
	var totalAmt = row.totalAmt;
	totalAmt = Number(totalAmt.replace(regS,""));
	$.messager.confirm('确定','是否确定提交所选的数据吗?',function(flag) {
		if (flag) {
			$.ajax({
					url : "purchaseAppController/startWorkflow.do",
					data : {"paId":row.paId,"appNo":row.appNo,"totalAmt":totalAmt},
					success : function(msg) {
						if(msg.status){
							$datagrid.datagrid('reload');
						}
						$.messager.alert(msg.title,msg.message,'info');
					}
				});
			}
	});
}
//查看历史审批意见
function checkHistoryOpinions(index){
	var rows = $datagrid.datagrid("getRows");
	$$row = rows[index];//获取本条数据
	var $optionsWindow = $("<div></div>").dialog({
		title: '历史审批意见',    
	    width: 800,    
	    height: 550,    
	    closed: false,    
	    cache: false,    
	    href: 'jsp/ad/optionsList.jsp',    
	    modal: true,
	    onClose : function(){
	    	$(this).dialog('destroy');
	    	$$row = null;
        },
        buttons : [ {
			text : '关闭',
			iconCls : 'icon-cancel',
			handler : function() {
				$optionsWindow.dialog('close');
			}
		}]
	});
}
//查看流程图
function showImage(index){
	var rowdata = getRowData(index);
	var src = "purchaseAppController/showProcessImg.do?paId="+rowdata.paId;
	$('#imageDialog').dialog("open");
	$("#image").attr("src", src);
}
</script>
<body>
<div class="position" style="margin-top: 5px;">您当前所在位置： 行政办公  &gt; 固定资产采购申请</div>
<div class="well well-small" style="margin-left: 5px;margin-top: 5px">
	<form id="searchForm">
	  <table>
	    <tr>
	      <td>申请编号:</td>
	      <td><input name="appNo" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,12]'"/></td>
	      <td>申请日期:</td>
	      <td>
	         <input id="appDateMini" name="appDateMini" class="easyui-textbox easyui-datebox" editable="false"/>
	         -
	         <input id="appDateMax" name="appDateMax" class="easyui-textbox easyui-datebox" editable="false"/>
	      </td>
	      <td>
	         <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="toSearch();">搜索</a>
	         <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" onclick="$('#searchForm').form('clear')">重置</a>
	      </td>
	    </tr>
	  </table>
	</form>
</div>
<table id="purchaseAppGrid"></table>
<div id="imageDialog"  class="easyui-dialog" title="流程图片" data-options="border:false,closed:true,fit:true">
	<img id="image" src="" >
</div>
<div id="optionsDialog"></div>
</body>
</html>