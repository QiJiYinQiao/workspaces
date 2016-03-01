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
<title>人事花名册管理</title>
</head>
<style type="text/css">
a{
text-decoration: none
}
</style>
<script type="text/javascript">
var $dialog;
var $datagrid;
$(function(){
	$datagrid = $("#empRosterRegGrid").datagrid({
		url:'empRosterRegController/findEmpRosterRegList.do',
		title:'人事花名册管理',
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
		fitColumns:false,
		columns:[[
		        {field : 'deptName',title : '部门',width :200,align : 'center',rowspan:2},
                {field : 'userName',title : '姓名',width : 100,align : 'center',rowspan:2},
                {field : 'mobile',title : '联系方式',width : 100,align : 'center',rowspan:2},
                {field : 'idCard',title : '身份证号码',width : 150,align : 'center',rowspan:2},
                {field : 'idCardAddr',title : '身份证地址',width : 200,align : 'center',rowspan:2},
                {field : 'salCardNo',title : '工资卡号',width : 200,align : 'center',rowspan:2},
                {field : 'duty',title : '职务',width : 100,align : 'center',rowspan:2},
                {field : 'isChg',title : '是否异动',width : 100,align : 'center',rowspan:2,
                	formatter:function(value,row,index){
                		if(value=="0"){
                			return "<a href='javascript:void(0);' onclick='checkHistoryOpinions("+ index +");'>是</a>";
                		}else{
                			return "否";
                		}
                	}},
                {field : 'isRegular',title : '是否转正',width : 100,align : 'center',rowspan:2,
                	formatter:function(value,row,index){
                		if(value=="0"){
                			return "<a href='javascript:void(0);' onclick='checkHistoryOpinions("+ index +");'>是</a>";
                		}else{
                			return "否";
                		}
                	}},
                {title : '试用期工资',width : 300,align : 'center',colspan:3},
                {title : '转正工资',width : 300,align : 'center',colspan:3},
                {field : 'curMonthSalCnt',title : '本月最终薪资总计',width : 200,align : 'center',rowspan:2},
                {title : '劳动合同情况',width : 400,align : 'center',colspan:4},
                {field : 'isSignContract',title : '是否签订劳动合同',width : 150,align : 'center',rowspan:2,
                	formatter:function(value,row,index){
                		if(value=="0"){
                			return "<a href='javascript:void(0);' onclick='checkHistoryOpinions("+ index +");'>是</a>";
                		}else{
                			return "否";
                		}
                	}},
                {title : '五险一金缴纳情况',width : 600,align : 'center',colspan:6} 
                ],
               [
                {field : 'trialBaseSal',title : '试用期基本工资',width : 100,align : 'center'},
                {field : 'trialPostSal',title : '试用期岗位工资',width : 100,align : 'center'},
                {field : 'trialPerfSal',title : '试用期绩效工资',width : 100,align : 'center'},
                {field : 'regularBaseSal',title : '转正基本工资',width : 100,align : 'center'},
                {field : 'regularPostSal',title : '转正岗位工资',width : 100,align : 'center'},
                {field : 'regularPerfSal',title : '转正绩效工资',width : 100,align : 'center'},
                {field : 'contractNo',title : '劳动合同编号',width : 100,align : 'center'},
                {field : 'contractPeriod',title : '劳动合同期限',width : 100,align : 'center'},
                {field : 'contractBgDate',title : '劳动合同开始时间',width : 100,align : 'center'},
                {field : 'contractEdDate',title : '劳动合同结束时间',width : 100,align : 'center'},
                {field : 'endowmentIns',title : '养老保险',width : 100,align : 'center'},
                {field : 'medicalIns',title : '医疗保险',width : 100,align : 'center'},
                {field : 'unemploymentIns',title : '失业保险',width : 100,align : 'center'},
                {field : 'workInjuryIns',title : '工伤保险',width : 100,align : 'center'},
                {field : 'maternityIns',title : '生育保险',width : 100,align : 'center'},
                {field : 'housingFund',title : '住房公积金',width : 100,align : 'center'}
	   ] ],
	   onLoadSuccess:function(data){
           $(this).datagrid("doCellTip",{'max-width':'100px'});
	   }, 
	   toolbar:'#tb'
	});
	
	$("#organizationId").combotree({
		width:171,
		url:"Organization/organizationList.do",
		idFiled:'id',
	 	textFiled:'name',
	 	parentField:'pid',
	 	onChange:function(newValue,oldValue){
	 		//$("#organizeName").val(node.text);
	 	}
	});
});

//新增弹框
function toOpenAdd(){
	$dialog=$("<div></div>").dialog({
		title: '添加员工花名册',    
	    width: 960,    
	    height: $(window).height()*0.9,    
	    closed: false,    
	    cache: false,    
	    href: 'jsp/pd/empRosterReg/empRosterRegMainForm.jsp',    
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
			handler:saveEmpRosterReg
	    },{
	    	text:'关闭',
	    	iconCls:'icon-cancel',
			handler:function(){
				$dialog.dialog('close');
			}
	    }]
	});
}

//编辑
function toOpenEdit(){
	var row = $("#empRosterRegGrid").datagrid("getSelected");
	if(row){
		$dialog = $("<div></div>").dialog({    
		    title: '编辑员工花名册',    
		    width: 960,    
		    height: $(window).height()*0.9,    
		    closed: false,    
		    cache: false, 
		    resizable:true,
		    href:'jsp/pd/empRosterReg/empRosterRegMainForm.jsp',    
		    modal: true,
		    onLoad:function(){
		    	//填充表单
		    	$("#empRosterRegForm").form('load',row);
		    	//load 部门和姓名
		    	$("#deptName").combotree("setValue",row.deptId);
		    	$("#userId").combobox("setValue",row.userId);
		    },
		    onClose : function(){
		    	//弹框销毁
		    	$(this).dialog('destroy');
		    	//列表刷新
		    	$datagrid.datagrid("reload");
		    },
		    buttons : [ {
		    	text:'保存',
		    	iconCls : 'icon-save',
				handler:saveEmpRosterReg
		     },{
				text : '关闭',
				iconCls : 'icon-cancel',
				handler : function() {
					$dialog.dialog('close');
					$row = null;
				}
			}]
		});
	}else{
		$.messager.alert("提示信息","请选择一行进行编辑！");
	}
}

//执行新增
function saveEmpRosterReg(){
	var isValid = $("#empRosterRegForm").form('validate');
	if(!isValid){
		return false;
	}
	$.ajax({
		type:'POST',
		url:'empRosterRegController/saveEmpRosterReg.do',
		data:$("#empRosterRegForm").serialize(),
		dataType:'JSON',
		success:function(msg){
		   if(msg.status){
			   $("#empRosterRegGrid").datagrid('reload');//刷新列表
			   $dialog.dialog('destroy');//销毁弹窗
		   }
		   //弹出提示信息
		   $.messager.alert(msg.title,msg.message,'info');
		}
	});
}

//删除
function toDelete(){
	 var rows = $datagrid.datagrid('getSelections');//获取选中的记录
	 if(rows!=null&&rows.length>=1){
		 var ids = new Array();
		 $.messager.confirm('删除', '删除该记录将不可恢复，确认删除吗?', function(d) {
			 if(d){
				 $.each(rows,function(i,row){
						if (row) {
							var rowIndex = $datagrid.datagrid('getRowIndex', row);
							$datagrid.datagrid('deleteRow', rowIndex);
							ids.push(row.errId);//将ID放入数组中
						}
				 });
			 ids = ids.join(",");// 转换为字符串
			 $.ajax({
				    type:'post',
					url:"empRosterRegController/delEmpRosterReg.do",
					data: "ids="+ids,
					success: function(rsp){
						$.messager.show({
							title : rsp.title,
							msg : rsp.message,
							timeout : 1000 * 2
						});
					}
			});
			}
		 })
	 }else{
		 $.messager.alert("提示","请至少选择一条记录!","warning");
	 }
}

//执行高级查询
function doSearch(){			
	$datagrid.datagrid("load",{
		deptId:$("#organizationId").combotree('getValue'),
		userName:$('#userName').val()
	}); 
}

//重置条件
function clearAdvancedQueryConditions(){
	//1、清空高级查询各组件内容
	$("#searchForm").form("clear");
	//2、datagrid重新加载
	$datagrid.datagrid("load",{});
}
</script>
<body>
<div class="position" style="margin-top: 5px;">您当前所在位置：人力资源规划  &gt; 员工关系  &gt; 人事花名册管理</div>
<!-- 高级查询栏区域 -->
  		<div class="well well-small" style="margin-left: 5px;margin-top: 5px">
			<form id="searchForm" action="ppeRegController/findAllppeScrap.do" method="post">
				<table cellpadding="0" cellspacing="1" border="0">
					<tr>
						<td>组织部门:&nbsp;</td>
						<td><input name="organizationId" id="organizationId" class="easyui-textbox"  style="width:134px;" value=""  />&nbsp;&nbsp;&nbsp;&nbsp;</td>
						
						<td>姓名:&nbsp;</td>
						<td><input name="userName" id="userName" class="easyui-textbox"  style="width:134px;" value=""  /></td>
						<td width="5"></td>
						<td align="right">
						    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="false" onclick="doSearch();">执行查询</a>&nbsp;&nbsp;&nbsp;&nbsp;
						    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-reload" plain="false" onclick="clearAdvancedQueryConditions()">条件重置</a>
						</td>	
					</tr>	
				</table>
			</form>			  			
		</div>
<table id="empRosterRegGrid"></table>
<div id="tb" style="padding: 2px 0">
			<table cellpadding="0" cellspacing="0">
				<tr>
					<td style="padding-left: 2px">
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="toOpenAdd()">添加</a>
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="toOpenEdit()">编辑</a>
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="toDelete()">删除</a>
					</td>
				</tr>
			</table>
		</div>
</body>
</html>