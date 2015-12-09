<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="<%=basePath%>">
<title>固定资产借用登记</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<jsp:include page="../../../layout/script.jsp"></jsp:include>
<style type="text/css">
.textStyle{
	text-align: right;
	color: blue;
}
a{
			text-decoration:none;
		}
</style>
<script type="text/javascript">


  	//固定资产借用登记
  	var $grid;
	$(function() {
		$(window).resize(function(){  
            $("#ppeBorrowReggrid").datagrid({  
            	height: $(window).height()-83  
            });                
        });  

		
		$grid =$("#ppeBorrowReggrid").datagrid({
					url : 'PpeBorrowReg/findPpeBorrowRegList.do',
					width : 'auto',
					height : $(this).height()-83,
					pagination:true,
					rownumbers:true,
					border:true,
					singleSelect:false,
					nowrap:true,
					multiSort:false, 
					border:false,
					fitColumns:true,
					pageList:[10,20,30,40],
					columns : [ [
							{field : 'borrowerName',title : '借用人',width :  parseInt($(this).width()*0.09),sortable : true,align : 'center'},
			                {field : 'fullName',title : '借用部门',width: parseInt($(this).width()*0.09),align : 'center'},
							{field : 'regDatetime',title : '借用日期',width: parseInt($(this).width()*0.1),align : 'center'},
							{field : 'reverterName',title : '归还人',width :  parseInt($(this).width()*0.09),align : 'center'},
							{field : 'revDatetime',title : '归还日期',width: parseInt($(this).width()*0.1),align : 'center'},   
					        {field : 'ppeNo',title : '固定资产编号',width: parseInt($(this).width()*0.09),align : 'center'},
					        {field : 'ppeName',title : '固定资产名称',width: parseInt($(this).width()*0.09),align : 'center'},
							{field : 'model',title : '固定资产型号',width: parseInt($(this).width()*0.07),align : 'center'},
							{field : 'borReson',title : '借用事由',width:parseInt($(this).width()*0.2),align : 'center'},
							{field : 'remark',title : '备注信息',width : parseInt($(this).width()*0.2),align : 'center'}
							] ],
							 onLoadSuccess:function(data){
								 	//当鼠标放上该字段时，提示功能
						            $(this).datagrid("doCellTip",{'max-width':'100px'});
						           
							  }, 
					toolbar : '#tb'
				});
	});
	
	//执行查询
	function subCustomerRepaymentForm(){
		$("#ppeBorrowReggrid").datagrid("load",{
			ppeNo:$("#appNoMain").val(),
			appDateS:$('#appDateS').datebox('getValue'),
			appDateE:$('#appDateE').datebox('getValue')
		});  
	}
	
	function changeMyTitle(index){
		if(null == index){
			return '新增固定资产借用登记';
		}else{
			return '编辑固定资产借用登记';
		}
	}
	function changeMyicon(index){
		if(null == index ){
			return 'icon-add';
		}
			return 'icon-edit';
		
	}
	function changeMyheight(index){
		if(null == index ){
			return 320;
		}
			return 350;
		
	}
	
	//弹窗修改
	function saveOrUpdOpenDlg(typt) {
			var $row=$('#ppeBorrowReggrid').datagrid('getSelections');
			if(typt==1){
				if($row==null || $row.length<=0){
					$.messager.alert("提示","请选择一条记录进行编辑!","warning");
					return false;
				}else if($row.length > 1){
					$.messager.alert("提示","只能选择一条记录编辑!","warning");
					return false;
				}
			}	
			$("#editView").dialog({
				title : changeMyTitle($row[0]),
				iconCls: changeMyicon($row[0]),
				width : 920,
				height : changeMyheight($row[0]),
				modal:true,
				href : "jsp/ad/ppeBorrowReg/ppeBorrowRegAddForm.jsp",
				onLoad:function(){
					if($row[0]!=null){
						var f = $("#ppeBorrowRegForm");
						f.form("load", $row[0]); 
						//借用人
						$("#borrower").combobox({
							url:"PpeBorrowReg/findOrgUserList.do?organizeId="+$row[0].deptNo,
							valueField:'code',
						 	textFiled:'text',
						 	onLoadSuccess:function(date){
						 		$("#borrower").combobox('setValue',$row[0].borrower)
						 	}
						});
						//归还人
						if($row[0].reverter!=null){
							$("#reverter").combobox({
								url:"PpeBorrowReg/findOrgUserList.do?organizeId="+$row[0].reverterDeptNo,
								valueField:'code',
							 	textFiled:'text',
							 	onLoadSuccess:function(date){
							 		$("#reverter").combobox('setValue',$row[0].reverter)
							 	}
							});
						}
						
						addOrEditval();
					}
				}, 
				onClose:function(){
					$grid.datagrid('reload');
				} 
			}); 
	}
	/**
	 * 根据索引获取对象名称
	 * @param target Grid对象
	 * @param index 索引
	 * @returns 索引的对应行的信息
	 */
	function getRowData (target,index) {
		if (!$.isNumeric(index) || index < 0) { return undefined; }
		if ($.isEmptyObject(target)) { return undefined; }
	    var rows = target.datagrid("getRows");
	    return rows[index];
	}
	
	
	  //批量删除
    function delRows(){
    	var selected = $('#ppeBorrowReggrid').datagrid('getSelections');
    	if (selected.length <= 0) {
    		$.messager.alert("提示", "请至少选择一条记录执行删除!", "warning");
    		return;
    	}
    	var ids = new Array();
    	for(var i=0,len=selected.length; i<len; i++){
    		ids.push(selected[i].pbrId);
    	}
    	ids = ids.join(",");
    	$.messager.confirm('删除', '执行删除后，数据将不可恢复,是否执行?', function(d) {
    		if (d) {
    			$.ajax( {
    				type : "POST",
    				url : 'PpeBorrowReg/delPpeBorrowRegList.do',
    				data : {
    					"ids":ids
    				},
    				dataType:'JSON',
    				success : function(iJson) {
    					if(iJson.status){
    						$grid.datagrid('reload');
	    					parent.$.messager.alert('提示','该记录删除成功！','info');
    					}else{
    						parent.$.messager.alert('提示','该记录删除失败！','info');
    					}
    				}
    			});
    		}
    	});
    } 
	
	
</script>
</head>
<body>
		<div style="margin-left: 5px;margin-top: 5px">
			<div class="position" style="margin-top: 5px;">您当前所在位置：业务管理-->行政办公-->固定资产借用登记</div>
		</div>
		
		<div  class="well well-small" style="margin-left: 5px;margin-top: 5px">
			<form id="customerRepaymentForm"  method="post">
				<table >
					<tr>
					      <th>固定资产编号:</th>
					      <td><input name="appNo" id="appNoMain" class="easyui-textbox easyui-validatebox" data-options="required:true,editable:false"/></td>
					      <th>借用日期:</th>
					      <td>
					      	 <input id="appDateS" name="appDateS" placeholder="请选择起始日期" class="easyui-textbox easyui-datebox easyui-validatebox" data-options="editable:false" />
						　                             　至　　
							 <input id="appDateE" name="appDateE" placeholder="请选择截止日期" class="easyui-textbox easyui-datebox easyui-validatebox" data-options="editable:false"/>
					      	
					      </td>
					      <td>
					         <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="subCustomerRepaymentForm();">搜索</a>
					         <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" onclick="$('#customerRepaymentForm').form('reset');">重置</a>
					      </td>
				   </tr>
					
					
				</table>
			</form>
		</div>
			
	
			<div id="tb" style="padding: 2px 0">
				<table cellpadding="0" cellspacing="0">
					<tr>
						<td style="padding-left: 2px">
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="saveOrUpdOpenDlg(0);">添加</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="saveOrUpdOpenDlg(1);">编辑</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRows();">删除</a>
						</td>
					</tr>
				</table>
			</div>
		
		<table id="ppeBorrowReggrid" title="固定资产借用登记"></table>
		<!-- 固定资产增加修改页面 -->
		<div id="editView"></div>
</body>
</html>
