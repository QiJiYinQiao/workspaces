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
<title>人员需求申请</title>
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
	var $dialog;
	var $datagrid;
	var $row;
	var $$row;
	$(function(){
		initProcStatusCombobox();  		//初始化高级查询中的“申请状态”
		createStaffRecruitAppInfoGrid();
	});
	
	function initProcStatusCombobox(){
		$("#procStatus").combobox({
			valueField: 'value',   
	        textField: 'label',   
	        data: [{
				label: '全部',
				value: ''
			},{
				label: '初始状态',
				value: '1'
			},{
				label: '审批中',
				value: '2'
			},{
				label: '已完成',
				value: '3'
			},{
				label: '已失效',
				value: '4'
			},{
				label: '已撤销',
				value: '5'
			}],	
			editable : false,
			panelHeight : 150,
			onLoadSuccess : function(){
				userData = $(this).combobox("getData");
				for (var item in userData[0]) {
	                if (item == "value") {
	                    $(this).combobox("select", userData[0][item]);
	                }
	            }				
			}
		});
	}

	function createStaffRecruitAppInfoGrid(){
		$datagrid = $("#staffRecruitAppInfoGrid").datagrid({
			url:'staffRecruitAppController/findStaffRecruitAppList.do',
			title:'人员需求申请',
			width: 'auto',
			height : $(this).height()-85,
			pagination:true,
			rownumbers:true,
			border:false,
			singleSelect:true,
			nowrap:true,//如果为true，则在同一行中显示数据。设置为true可以提高加载性能。
			pageSize:30,
			pageList:[10,20,30,40],
			remoteSort:false,//定义是否从服务器对数据进行排序。
			striped:true,//是否显示斑马线
			columns:[[
					{field : 'appNo',          title : '申请编号',     width : parseInt($(this).width()*0.06), align : 'center'},			         
			        {field : 'curLoginUserName', title : '申请人',      width : parseInt($(this).width()*0.04), align : 'center'},
			        {field : 'deptName',       title : '申请部门',     width : parseInt($(this).width()*0.05), align : 'center'},
		            {field : 'jobTitle',       title : '职位名称',     width : parseInt($(this).width()*0.06), align : 'center'},
	                {field : 'existingStaff',  title : '现有人员数量',  width : parseInt($(this).width()*0.05), align : 'center'},
	                {field : 'recruitQty',     title : '招聘数量',     width : parseInt($(this).width()*0.04), align : 'center'},
	                {field : 'appDate',        title : '申请日期',     width : parseInt($(this).width()*0.06), align : 'center'},
	                {field : 'jobDtime',       title : '上岗时间',     width : parseInt($(this).width()*0.06), align : 'center'},
	                {field : 'serviceLife',    title : '服务年限',     width : parseInt($(this).width()*0.04), align : 'center'},
	                {field : 'recruitType',    title : '招聘人员类型',  width : parseInt($(this).width()*0.05), align : 'center', formatter:function(value, row, index){	                	
	                	if(  value == "1"){
	                		return "正式员工";
	                	}else if(  value == "2"){
	                		return "季节工";
	                	}else if(  value == "3"){
	                		return "临时工";
	                	}else if(  value == "4"){
	                		return "计时工";
	                	}else{
	                		return "";
	                	}
	                }},
	                {field : 'isTargetInside', title : '是否部门目标内', width : parseInt($(this).width()*0.06), align : 'center', formatter:function(value, row, index){
	                	if(  value == "Y" || value == "y" ){
	                		return "是";
	                	}else if( value == "N" || value == "n" ){
	                		return "否";
	                	}else {
	                		return "";
	                	}	                	
	                }},
	                {field : 'recruitPlace',   title : '招聘渠道',     width : parseInt($(this).width()*0.06), align : 'center', formatter:function(value, row, index){
	                	if(  value == "1" ){
	                		return "从公司外部招聘";
	                	}else if( value == "2" ){
	                		return "从公司内部调配";
	                	}else {
	                		return "";
	                	}	                	
	                }},
	                {field : 'recruitPurpose', title : '招聘目的',     width : parseInt($(this).width()*0.05), align : 'center', formatter:function(value, row, index){
	                	if(  value == "1" ){
	                		return "补充新人";
	                	}else if( value == "2" ){
	                		return "替换现职员";
	                	}else {
	                		return "";
	                	}		                	
	                }},
	                {field : 'adviceSalRange', title : '建议薪资范围(元)', width : parseInt($(this).width()*0.08), align : 'center', formatter:function(value, row, index){
	                		return row.adviceSalLower+" -- "+row.adviceSalUpper;
	                	}
	                },
	                {field : 'jobDescription', title : '招聘岗位职责',   width : parseInt($(this).width()*0.08), align : 'center'},
	                {field : 'qualification', title : '岗位要求（教育、经验、技能）', width : parseInt($(this).width()*0.09), align : 'center'},
	                {field : 'appStatusName',      title : '申请状态',    width : parseInt($(this).width()*0.06), align : 'center', formatter:function(value,row,index){
	                	if(value == null || value == ""){
	                		return "初始状态";
	                	}
	                	return value;
	                }},
	                {field : 'remark',         title : '备注信息', width:parseInt($(this).width()*0.08), align : 'center'},
	                {field : 'operType',       title : '操作',    width:parseInt($(this).width()*0.10), align : 'center', formatter:function(value,row,index){
	                	if(row.procStatus == "1" || row.procStatus == null){
	             	    	return "<a href='javascript:void(0);' onclick='toEdit("+index+");'>编辑</a>&nbsp;&nbsp;"+
	             	    	       "<a href='javascript:void(0);' onclick='toDelete("+ index + ");'>删除</a>&nbsp;&nbsp;"+
	             	    	       "<a href='javascript:void(0);' onclick='startProcessStaffRecruitApp("+index+");'>提交申请</a>";
	             	    }else if(row.procStatus == "2"){
	             	    	return "<a href='javascript:void(0);' onclick='checkHistoryOpinions("+ index +");'>查看审批意见</a>&nbsp;&nbsp;"+"<a href='javascript:void(0);' onclick='showImage("+ index + ");'>查看流程图</a>";
	             	    }else{
	             	    	return "<a href='javascript:void(0);' onclick='checkHistoryOpinions("+ index +");'>查看审批意见</a>";
	             	    }
	                }}
		   ]],
		   onLoadSuccess:function(data){
	           $(this).datagrid("doCellTip",{'max-width':'100px'});
		   }, 
		   toolbar:[{
			   iconCls: 'icon-add',
			   text:'添加',
 			   handler:toOpenAddDialog
		   }]
		});
	}

	//执行人员招聘的高级搜索
	function staffRecruitAdvancdSearch(){
		var appNo2 = $("#appNo2").val();		
		var appDateStart = $("#appDateStart").datebox('getValue');		
		var appDateEnd = $("#appDateEnd").datebox('getValue');		
		$("#staffRecruitAppInfoGrid").datagrid('load',{
			appNo2 : appNo2,
			appDateStart : appDateStart,
			appDateEnd : appDateEnd
		});		
	}
	
	//
	function resetAdvancSearch(){
		$('#staffRecruitAdvancdSearchForm').form('reset');  //清空高级搜索条件。
		$("#staffRecruitAppInfoGrid").datagrid('load',{});  //重新查询数据。
	}

	//弹出 “新增” 对话框
	function toOpenAddDialog(){
		$dialog=$("<div></div>").dialog({
			title: '添加',    
		    width: 690,    
		    height: 500,    
		    closed: false,    
		    cache: false,    
		    href: 'jsp/pd/staffRecruitApp/staffRecruitAppForm.jsp',    
		    modal: true,
		    onClose:function(){
		    	//刷新列表
		    	$datagrid.datagrid('reload');
		    	//关闭弹窗
		    	$(this).dialog('destroy');
		    },
		    buttons:[{
		    	text:'保存',
		    	iconCls:'icon-save',
				handler:saveStaffRecruitApp
		    },{
		    	text:'关闭',
		    	iconCls:'icon-cancel',
				handler:function(){
					$dialog.dialog('close');
				}
		    }]
		});
	}

	//执行新增
	function saveStaffRecruitApp(){
		var isValid = $("#staffRecruitAppForm").form('validate');
		console.info("isValid = "+isValid);
		console.info($("#staffRecruitAppForm").serialize());
		if(!isValid){
			return false;
		}
		$.ajax({
			type:'POST',
			url:'staffRecruitAppController/saveStaffRecruitApp.do', 
			data:$("#staffRecruitAppForm").serialize(),
			dataType:'JSON',
			success:function(msg){
			   if(msg.status){
				   $("#staffRecruitAppInfoGrid").datagrid('reload');//刷新列表
				   $dialog.dialog('destroy'); //销毁弹窗
			   }
			   //弹出提示信息
			   $.messager.alert(msg.title,msg.message,'info');
			}
		});
	}

	
	
	//跳转到编辑（修改）“员工招聘申请”对话框
	function toEdit(index){
		//获得当前选择行的数据
		var allRows = $("#staffRecruitAppInfoGrid").datagrid('getRows');		
		$row = allRows[index];
		$dialog = $("<div></div>").dialog({
		    title: '修改',    
		    width: 690,    
		    height: 500,    
		    closed: false,    
		    cache: false, 
		    resizable:true,
		    href:'jsp/pd/staffRecruitApp/staffRecruitAppForm.jsp',    
		    modal: true,
		    onClose : function(){
		    	//弹框销毁
		    	$(this).dialog('destroy');
		    	//列表刷新
		    	$datagrid.datagrid("reload");
		    	$row = null;
		    },		    
		    buttons : [ {
		    	text:'保存',
		    	iconCls : 'icon-save',
				handler:saveEditStaffRecruitApp
		     },{
				text : '关闭',
				iconCls : 'icon-cancel',
				handler : function() {
					$dialog.dialog('close');
					$row = null;
				}
			}]
						
		});
	}
		
	
	//执行编辑
	function saveEditStaffRecruitApp(){
		$.ajax({
			type:'POST',
			url:'staffRecruitAppController/saveStaffRecruitApp.do', 			
			data:$("#staffRecruitAppForm").serialize(),
			dataType:'JSON',
			success:function(msg){
			   if(msg.status){
				   $("#staffRecruitAppInfoGrid").datagrid('reload');//刷新列表
				   $dialog.dialog('close');//销毁弹窗
			   }
			   //弹出提示信息
			   $.messager.alert(msg.title,msg.message,'info');
			}
		});
	}

	
	
	//删除
	function toDelete(index){
		var allRowsData = $("#staffRecruitAppInfoGrid").datagrid('getRows');
		$row = allRowsData[index];
		console.info("$row.mraId = "+$row.mraId);
		
		$.messager.confirm('确定','是否确定删除所选的数据吗?',function(b) {
			$.ajax({
				type:'POST',
				url:'staffRecruitAppController/deleteStaffRecruitAppByMraID.do',
				data:{"mraId":$row.mraId},
		        async: false,
		        dataType:'JSON',
			    success: function(msg){
				   if(msg.status){
					   $datagrid.datagrid("reload");
					   $row = null;
				   }
				   $.messager.alert(msg.title,msg.message,'info');
			    }				
			});			
		});		
	}
	
	//提交流程申请
	function startProcessStaffRecruitApp(index){
		//获得行数据
		var rows = $("#staffRecruitAppInfoGrid").datagrid("getRows");
		var oneRow = rows[index];
		console.info("mraId = "+oneRow.mraId+",   appNo = "+oneRow.appNo);
		
		//确认提交
 		$.messager.confirm('确定','是否确定提交所选的数据吗?',function(flag) {
			if(flag){
				$.ajax({
					type : 'POST',
					url : 'staffRecruitAppController/startProcessStaffRecruitApp.do',
					data : {"mraId" : oneRow.mraId, "appNo": oneRow.appNo},					
					dataType : 'JSON',
					success : function(msg){
					   if(msg.status){
						   $datagrid.datagrid("reload");
					   }
					   $.messager.alert(msg.title,msg.message,'info');						
					}
				});
			}
		});				
 	}

	//查看流程图
	function showImage(index){
		var rows = $("#staffRecruitAppInfoGrid").datagrid("getRows");
		var row = rows[index];
		var src = "staffRecruitAppController/showProcessImg.do?mraId="+row.mraId;
		$('#imageDialog').dialog("open");
		$("#image").attr("src", src);
	}


	//查看历史审批意见
	function checkHistoryOpinions(index){
		var rows = $("#staffRecruitAppInfoGrid").datagrid("getRows");
		$$row = rows[index];//获取本条数据
		$("<div></div>").dialog({
			title: '历史审批意见',    
		    width: 850,    
		    height: 500,    
		    closed: false,    
		    cache: false,    
		    href: 'jsp/ad/optionsList.jsp',    
		    modal: true,
		    onClose : function(){
		    	$(this).dialog('destroy');
		    	$$row = null;
	        }
		});
	}
</script>

<body>
	<div class="position" style="margin-top: 5px;">您当前所在位置：人力资源规划  &gt; 招聘配置 &gt; 人员需求申请</div>

	<!-- 高级查询区域 -->	
	<div  class="well well-small" style="margin-left: 5px;margin-top: 5px">
		<form id="staffRecruitAdvancdSearchForm"  method="post">
			<table >
				<tr>
<!-- 			      
				  <th>申请状态:</th>
			      <td>
			      	<input id="procStatus" name="procStatus" class="easyui-textbox easyui-validatebox" />
			      </td>
 -->			      
 				  <th>申请日期:</th>
			      <td>
			      	 <input id="appDateStart" name="appDateStart" placeholder="请选择起始日期" class="easyui-textbox easyui-datebox" data-options="editable:false" />
				　                             　至　　
					 <input id="appDateEnd" name="appDateEnd" placeholder="请选择截止日期" class="easyui-textbox easyui-datebox" data-options="editable:false"/>			      	
			      </td>
			      <td>
			         <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="staffRecruitAdvancdSearch();">搜索</a>
			         <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" onclick="resetAdvancSearch()">重置</a>
			      </td>
			   </tr>										
			</table>
		</form>
	</div>
	
	<!-- 员工招聘信息网格区域 -->
	<table id="staffRecruitAppInfoGrid"></table>

	<!-- 显示流程图区域 -->
	<div id="imageDialog"  class="easyui-dialog" title="流程图片" data-options="border:false,closed:true,fit:true">
		<img id="image" src="" >
	</div>
</body>
</html>