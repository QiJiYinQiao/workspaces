<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
    <form id="ppeScrapAppForm"  method="post" style="width: 875px;margin-left:5px;">
    <fieldset>
    <legend><img src="extend/fromedit.png" style="margin-bottom: -3px;"/>基本信息</legend>
	<input type="hidden" id="appNo" name="appNo" value="${ppeScrap.appNo }" /><!-- 外键appNo -->
	<input type="hidden" id="psaId" name="psaId" value="${ppeScrap.psaId }" /> <!-- id标识 判断是否为新增 -->
	<input type="hidden" id="appDept" name="appDept" value="${ppeScrap.appDept }" /><!-- 申请部门 -->
	<input type="hidden" id="applicantNo" name="applicantNo" value="${ppeScrap.applicantNo }" /> <!-- 申请人 -->
	<input type="hidden" id="appStatus" name="appStatus" value="${ppeScrap.appStatus }" /><!-- 申请状态 -->
	<input type="hidden" id="procStatus" name="procStatus" value="${ppeScrap.procStatus }" /><!-- 流程状态 -->
	<input type="hidden" id="appDate" name="appDate" value="${ppeScrap.appDate }" /><!-- 流程状态 -->
	
		<table class="table"  width="95%">
			<tr>
				<th align="right" >备注:</th>
			   <td  align="left">
			   	<textarea name="remark"  id="remark"  class="easyui-textarea easyui-validatebox" style="width: 600px; height: 90px;" data-options="validType:'length[0,200]'" disabled="disabled">${ppeScrap.remark }</textarea>
			   </td>
			</tr>	
		</table>
    </fieldset>
    </form>
  <div style="width: 875px;margin-left:5px;margin-top: 2px;">
       <table id="ppeScrapAttachGrid"></table>
       <div id="formWindow" class="easyui-dialog" title="新增固定资产" closed="true" ></div>
    </div>
    
   
<script type="text/javascript">
//初始化构建列表
$(function(){
	$(window).resize(function(){  
        $("#ppeScrapAttachGrid").datagrid({  
        	height: $(window).height()-100,
        	width : 'auto'
        });                
    });
	createPpeScrapAttachGrid();
})
/**
 * 编辑固定资产项
 */
function editPPEScrapAppAttach(){
	var row = $("#ppeScrapAttachGrid").datagrid("getSelected");
	var rows = $("#ppeScrapAttachGrid").datagrid("getSelections");
	if(row == null){
		$.messager.alert("提示","请选择一条记录进行修改!","warning");
		return false;
	}
	if(rows!=null && rows.length>1){
		$.messager.alert("提示","您只能选择一条记录进行修改!","warning");
		return false;
	}
	$('#formWindow').dialog({
        title: '编辑固定资产',
        inline:false,
        closed:true,
        width: 850,    
        height: 550,                
        cache: false,
        resizable:false,
        shadow:false,
        modal: true ,
        href:'ppeScrapAppAttachController/toAddPPEScrapAppAttachCwzg.do?psaId='+row.psaId
	});
	$('#formWindow').dialog("open");
}
//构建列表方法
function createPpeScrapAttachGrid(){
	$("#ppeScrapAttachGrid").datagrid({
		url:'ppeScrapAppAttachController/findAllppeScrapAttach.do?appNo='+$('#appNo').val(),
		width: 'auto',
		height : $(this).height()-480,
		pagination:false,
		rownumbers:true,
		border:true,
		singleSelect:false,
		fitColumns:true,
		nowrap:true,//如果为true，则在同一行中显示数据。设置为true可以提高加载性能。
		remoteSort:false,//定义是否从服务器对数据进行排序。
		striped:true,//是否显示斑马线
		columns:[[
			{field : 'psaId',        title : 'PSA_ID',    width : 0, align:'center' },
			{field : 'ppeNo',        title : '资产编号',    width : 900*0.08, align:'center' },
			{field : 'ppeName',      title : '资产名称', width : 900*0.08, align:'center'},
			{field : 'ppeModel',    title : '资产规格',    width : 900*0.07, align:'center' },
			{field : 'buyDate',    title : '购买时间',    width : 900*0.1, align:'center',formatter: 
				function(value,row,index){
					if(value != null && value.length >= 10){
						return value.substring(0,10);
					}
			} },
			{field : 'usedYear',    title : '使用年限(年)',    width : 900*0.09, align:'center' },
			{field : 'ppeGross',    title : '资产原值(元)',    width : 900*0.083, align:'center' },
			{field : 'ppeNet',    title : '资产净值(元)',    width : 900*0.083, align:'center' },
			{field : 'ppeSalvageVal',    title : '资产残值(元)',    width : 900*0.083, align:'center' },
			{field : 'scrapReson',    title : '资产报废原因',    width : 900*0.13, align:'center' },
			{field : 'caozuo',    title : '操作',    width : 900*0.14, align:'center',
	        	 formatter: function(value,row,index){
	        			var result ="<a href='javascript:void(0);' class='easyui-linkbutton'  onclick='checkAttachementDetail(\""+row.appNo+"\",null,null,"+row.psaId+");'>查看附件</a>&nbsp;&nbsp;&nbsp;";
						return result;
      				}}
	   ]],
	   toolbar:[{
		   iconCls: 'icon-edit',
		   text:'编辑',
		   handler:editPPEScrapAppAttach
	   }]
	});
	$("#ppeScrapAttachGrid").datagrid("doCellTip",{'max-width':'100px'});
	$('#ppeScrapAttachGrid').datagrid('hideColumn','psaId');
}
</script>