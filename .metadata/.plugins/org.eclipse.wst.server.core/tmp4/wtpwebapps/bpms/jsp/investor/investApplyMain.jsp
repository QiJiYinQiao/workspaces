<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<jsp:include page="../../layout/script.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>投资申请</title>
</head>
<script type="text/javascript">
var provinceArr = jqueryUtil.getAreaTextArr(1);//获取省
var relationshipArr =jqueryUtil.getTextArr("relationship_type");//与本人关系
var sexArr = jqueryUtil.getTextArr("gender_type");//性别
var degreeTypeArr = jqueryUtil.getTextArr("degree_type");//学历
var jobTypeArr = jqueryUtil.getTextArr("job_type")//工作类型
$(function(){
	createAppdg();
});
//投资申请列表
function createAppdg(){
	$("#dg").datagrid({
		url:'investOrder/investOrderAction!findListByInvestorAndInvestOrder.action',
		title:'投资申请',
		width: 'auto',
		height : $(this).height()-75,
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
		        {field : 'chName',title : '客户姓名',width :80,align : 'center'},
                {field : 'idNo',title : '证件号码',width : 180,align : 'center'},
	            {field : 'genderType',title : '性别',width : 80,align : 'center',formatter:function(value,row,index){
	            	return jqueryUtil.showText(value,sexArr);
	            }},
                {field : 'mobileTel',title : '移动电话',width :100,align : 'center'},
                {field : 'industry',title : '所属行业',width : 150,align : 'center'},
                {field : 'jobType',title : '职业',width :80,align : 'center',formatter:function(value,row,index){
                	return jqueryUtil.showText(value,jobTypeArr);
                }},
                {field : 'yearsOfWork',title : '工作年限',width : 60,align : 'center'},
                {field : 'degreeType',title : '学历',width : 60,align : 'center',formatter:function(value,row,index){
                	return jqueryUtil.showText(value,degreeTypeArr);
                }},
                {field : 'birthday',title : '出生日期',width : 150,align : 'center',formatter:function(value,row,index){
                	if(value==null || value==""){
                		return "";
                	}
                    return value.split(" ")[0]; 
                }},
                {field : 'email',title : '邮箱',width : 150,align : 'center'},
                {field : 'contractNo',title : '合同编号',width :100,align : 'center'},
                {field : 'bankName',title : '开户行名称',width :100,align : 'center'},
                {field : 'actNo',title : '账号',width :100,align : 'center'},
                {field : 'actName',title : '账户名称',width :100,align : 'center'},
                {field : 'aa',title : '操作',width :180,align : 'center',formatter:function(value,row,index){
                	if(row.processStatus == "0"){
             	    	return "<a href='javascript:void(0);' onclick='toDelete("+ index + ");'>删除</a>&nbsp;&nbsp;"+"<a href='javascript:void(0);' onclick='sumitInvestOrder("+ index + ");'>提交投资申请</a>";
             	    }else if(row.processStatus == "1"){
             	    	return "<a href='javascript:void(0);' onclick='checkProcessImg("+ index + ");'>查看流程图</a>";
             	    }else{
             	    	return "<a href='javascript:void(0);' onclick='checkInvestOrderOpinions("+ index + ");'>查看审批意见</a>";
             	    }
                }}
                
	   ]],
	   toolbar:[{
		   iconCls: 'icon-add',
		   text:'新增',
		   handler:toAdd
	   }]
	});
}
/**
 * 新增
 */
function toAdd(){
	createInvestordg();
	createContacts();
	$("#addDialog").dialog("open");
}
/**
 * 渲染investordg投资人列表
 */
function createInvestordg(){
	$("#investordg").datagrid({
		url:'investor/investorAction!findAllInvestor.action',
		width: 885,
		height: 534,
		pagination:true,
		rownumbers:true,
		border:false,
		singleSelect:true,
		nowrap:true,//如果为true，则在同一行中显示数据。设置为true可以提高加载性能。
		pageSize:20,
		pageList:[10,20,30,40],
		remoteSort:false,//定义是否从服务器对数据进行排序。
		striped:true,//是否显示斑马线
		onClickRow:function(rowIndex,rowData){//单击事件
			$("#contactsdg").datagrid("reload",{loanerId:rowData.investorId});
		},
		columns:[[
		        {field : 'chName',title : '客户姓名',width :80,align : 'center'},
                {field : 'idNo',title : '证件号码',width : 120,align : 'center'},
	            {field : 'genderType',title : '性别',width : 80,align : 'center',formatter:function(value,row,index){
	            	return jqueryUtil.showText(value,sexArr);
	            }},
                {field : 'mobileTel',title : '移动电话',width :150,align : 'center'},
                {field : 'industry',title : '所属行业',width : 150,align : 'center'},
                {field : 'jobType',title : '职业',width :80,align : 'center',formatter:function(value,row,index){
                	return jqueryUtil.showText(value,jobTypeArr);
                }},
                {field : 'yearsOfWork',title : '工作年限',width : 60,align : 'center'},
                {field : 'degreeType',title : '学历',width : 60,align : 'center',formatter:function(value,row,index){
                	return jqueryUtil.showText(value,degreeTypeArr);
                }},
                {field : 'birthday',title : '出生日期',width : 150,align : 'center'},
                {field : 'email',title : '邮箱',width : 150,align : 'center'}
	   ]],
	   toolbar:[{
		   iconCls: 'icon-add',
		   text:'确定',
		   handler:toSave
	   }]
	});
}
//紧急联系人列表
function createContacts(){
	$("#contactsdg").datagrid({
		url:'contacts/contactsAction!findAllList.action',
		width: 885,
		height: 534,
		pagination:true,
		rownumbers:true,
		border:false,
		singleSelect:false,
		nowrap:true,//如果为true，则在同一行中显示数据。设置为true可以提高加载性能。
		pageSize:20,
		pageList:[10,20,30,40],
		remoteSort:false,//定义是否从服务器对数据进行排序。
		striped:true,//是否显示斑马线
		columns : [ [
		             {field : 'chName',title : '姓名',width : 80,rowspan:2,align : 'center'},
		             {field : 'relationship',title : '与本人关系',width : 80,rowspan:2,align : 'center',formatter:function(value,row,index){
		            	 return jqueryUtil.showText(value,relationshipArr);
		             }},
		             {field : 'tel',title : '移动电话',width : 140,rowspan:2,align : 'center'},
		             {field : 'idNo',title : '证件号码',width : 120,rowspan:2,align : 'center'},
		             {title : '通讯地址',width : 340,colspan:4,align : 'center'}
		],[
					 {field : 'compProvince',title : '省',width : 80,align : 'center',formatter:function(value,row,index){
						 return jqueryUtil.showText(value,provinceArr);
					 }},
					 {field : 'compCity',title : '市',width : 80,align : 'center',formatter:function(value,row,index){
						 var cityArr = jqueryUtil.getAreaTextArr(row.compProvince);
						 return jqueryUtil.showText(value,cityArr);
					 }},
					 {field : 'compArea',title : '县/区',width : 80,align : 'center',formatter:function(value,row,index){
						 var areaArr = jqueryUtil.getAreaTextArr(row.compCity);
						 return jqueryUtil.showText(value,areaArr);
					 }},
					 {field : 'compAddrDetails',title : '详细地址',width : 200,align : 'center'}
		]]
	})
}
//确定
function toSave(){
	var row = $("#investordg").datagrid("getSelected");
	if(row == null){
		$.messager.alert("提示","请您选择一行记录!","warning");
		return;
	}
	//发送ajax
	$.ajax({
		type:'POST',
		url:'investOrder/investOrderAction!saveInvestOrder.action',
		data:'investorId='+row.investorId+'&idCrad='+row.idNo+'&mobTel='+row.mobileTel+"&investorName="+row.chName,
		dataType:'JSON',
		success:function(iJson){
			if(iJson.status){
				$("#dg").datagrid("reload");//刷新列表
				$("#addDialog").dialog("close");//关闭弹窗
			}
			parent.$.messager.show({
				title : iJson.title,
				msg : iJson.message,
				timeout : 4000 * 2
			}); 
		}
	});
}
//删除
function toDelete(index){
	var row = getRowData(index);
	if(row == null){
		$.messager.alert("提示","请选择一条记录执行删除!","warning");
		return;
	}
	//发送ajax
	$.ajax({
		type:'POST',
		url:'investOrder/investOrderAction!doDeleteInvestOrder.action',
		data:'investOrderId='+row.investOrderId,
		dataType:'JSON',
		success:function(iJson){
			if(iJson.status){
				$("#dg").datagrid("deleteRow",index);
			}
			parent.$.messager.show({
				title : iJson.title,
				msg : iJson.message,
				timeout : 4000 * 2
			}); 
		}
	});
}

//工具
function getRowData(index) {
	if (!$.isNumeric(index) || index < 0) {
		return undefined;
	}
	var rows = $("#dg").datagrid("getRows");
	return rows[index];
}
//提交申请
function sumitInvestOrder(index){
	var row = this.getRowData(index);
	$.messager.confirm('确定','是否确定提交所选的数据吗？',	function(flag) {
		if (flag) {
			$.ajax({
					url : "investOrder/investOrderAction!saveStartProcessInstance.action",
					data : {"investOrderId" : row.investOrderId},
					success : function(rsp) {
						if(rsp.status){
							parent.$.messager.show({
								title : rsp.title,
								msg : rsp.message,
								timeout : 1000 * 2
							});
							$("#dg").datagrid('reload');
						}else{
							parent.$.messager.alert(rsp.title,rsp.message,'error');
						}
					}
				});
			}
		});
}
//查看流程图
function checkProcessImg(index){
	var rows = $("#dg").datagrid("getRows");
	var rowm = rows[index];//获取本条数据
	var src = "investOrder/investOrderAction!checkWorkFlowImg.action?investOrderId=" + rowm.investOrderId;
	$('#imageDialog').dialog("open");
	$("#image").attr("src", src);
}
var row;
//查看审批意见
function checkInvestOrderOpinions(index){
	var rows = $("#dg").datagrid("getRows");
	row = rows[index];//获取本条数据
	$('#OpinionsDialog').dialog({    
	    title: '历史审查意见',    
	    width: 800,    
	    height: 500,    
	    closed: false,    
	    cache: false,    
	    href: 'jsp/investOrder/optionsList.jsp',    
	    modal: true   
	});   
}
</script>
<body>
<div class="position" style="margin-top: 5px;">您当前所在位置： 业务管理  &gt; 财富业务管理  &gt; 投资申请</div>
   <div class="well well-small" style="margin-left: 5px;margin-top: 5px">
		<span class="badge">提示</span>
			在此你可以新增<span class="label-info"><strong>投资订单</strong></span>并开启流程!
   </div>
   <table id="dg" style="margin: 0px;padding: 0px;overflow: auto;"></table>
   <!-- 新增div -->
   <div id="addDialog" class="easyui-dialog" style="width:900px;height:600px;" title="新增" data-options="modal:true,resizable:true,iconCls:'icon-add',closed: true">
      <div id="tt" class="easyui-tabs" data-options="border:false">   
	    <div title="投资客户">   
	       <table id="investordg" style="margin: 0px;padding: 0px;"></table>
	    </div>   
	    <div title="紧急联系人">   
	       <table id="contactsdg" style="margin: 0px;padding: 0px;"></table>    
	    </div>   
	 </div> 
   </div>
   <!-- 流程图片弹框 -->
   <div id="imageDialog" class="easyui-dialog" title="流程图片"
			data-options="border:false,closed:true,fit:true">
			<img id="image" src="">
   </div>
   <!-- 审查意见 -->
   <div id="OpinionsDialog"></div>
</body>
</html>