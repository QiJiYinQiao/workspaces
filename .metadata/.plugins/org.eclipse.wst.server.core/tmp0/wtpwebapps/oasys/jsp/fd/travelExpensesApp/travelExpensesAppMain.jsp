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
<title>差旅报销申请</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<jsp:include page="../../../layout/script.jsp"></jsp:include>
<style type="text/css">
a{
			text-decoration:none;
		}
</style>
<script type="text/javascript">

		

	   $(function() {
		$("#procStatus").combobox({
			valueField: 'value',   
	        textField: 'label',   
	        data: [{
				label: '全部',
				value: ''
			},{
				label: '初始化',
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
			},{
				label: '已拒绝',
				value: '6'
			}],
			editable:false ,
			onLoadSuccess : function(){
			userData = $(this).combobox("getData");
			for (var item in userData[0]) {
	                if (item == "value") {
	                    $(this).combobox("select", userData[0][item]);
	                }
	            }
			}
		});	
	  }); 

  	//申请列表
  	var $grid;
	$(function() {
		$(window).resize(function(){  
            $("#travelExpensesAppgrid").datagrid("resize",{  
            	height: $(this).height()-90  
            });                
        });
		
		$grid =$("#travelExpensesAppgrid").datagrid({
					url : 'TravelExpensesApp/findTravelExpensesAppList.do',
					width: 'auto',
					height : $(window).height()-90,
					pagination:true,
					rownumbers:true,
					border:true,
					singleSelect:false,
					nowrap:true,
					multiSort:false,
					border:false,
					fitColumns:false,
					pageList:[10,20,30,40],
					columns : [ [
					        {field : 'appNo',title : '申请编号',width:110,align : 'center',formatter:function(value,row,index){
					        	return "<a href=\"javascript:void(0)\" onclick=\"showTravelExpensesApp("+ index + ");\">" + value + "</a>";
			                }},
							{field : 'applicantName',title : '申请人',width:80,align : 'center',
								/* formatter : function(value, row, index) {
									return "<a href=\"javascript:void(0)\" onclick=\"showView("+ index + ");\">" + row.applicantName + "</a>";
								} */
							},
					        {field : 'fullName',title : '所属部门',width:150,align : 'center'},
							{field : 'appDate',title : '申请日期',width:80,align : 'center'}, 
							{field : 'btDays',title : '出差时间(天)',width : 90,align : 'center'},
							{field : 'subsidyAmt',title : '出差补助(元)',width :90,align : 'center',
								 formatter : function(value, row, index) {
										if(value.toString().indexOf(".")<0){
											 return value.toString().replace(/(\d)(?=(\d{3})+$)/g,"$1,")+".00"
										}else{
											 return value.toString().split(".")[0].replace(/(\d)(?=(\d{3})+$)/g,"$1,")+"."+value.toString().split(".")[1];;
										}
								}	},
							{field : 'grantExp',title : '预借旅费(元)',width : 90,align : 'center',
									 formatter : function(value, row, index) {
											if(value.toString().indexOf(".")<0){
												 return value.toString().replace(/(\d)(?=(\d{3})+$)/g,"$1,")+".00"
											}else{
												 return value.toString().split(".")[0].replace(/(\d)(?=(\d{3})+$)/g,"$1,")+"."+value.toString().split(".")[1];;
											}
									}	},
							{field : 'expAmt',title : '报销总额(元)',width : 90,align : 'center',
										 formatter : function(value, row, index) {
												if(value.toString().indexOf(".")<0){
													 return value.toString().replace(/(\d)(?=(\d{3})+$)/g,"$1,")+".00"
												}else{
													 return value.toString().split(".")[0].replace(/(\d)(?=(\d{3})+$)/g,"$1,")+"."+value.toString().split(".")[1];;
												}
										}	},
							{field : 'supplyAmt',title : '补领金额(元)',width : 90,align : 'center',
											 formatter : function(value, row, index) {
													if(value.toString().indexOf(".")<0){
														 return value.toString().replace(/(\d)(?=(\d{3})+$)/g,"$1,")+".00"
													}else{
														 return value.toString().split(".")[0].replace(/(\d)(?=(\d{3})+$)/g,"$1,")+"."+value.toString().split(".")[1];;
													}
											}	},
							{field : 'givebackAmt',title : '退还金额(元)',width :90,align : 'center',
												 formatter : function(value, row, index) {
														if(value.toString().indexOf(".")<0){
															 return value.toString().replace(/(\d)(?=(\d{3})+$)/g,"$1,")+".00"
														}else{
															 return value.toString().split(".")[0].replace(/(\d)(?=(\d{3})+$)/g,"$1,")+"."+value.toString().split(".")[1];;
														}
												}	},
							{field : 'btReason',title : '出差事由',width : 150,align : 'center'},
							{field : 'procStatus',title : '申请状态',width :80,align : 'center',formatter:function(value,row,index){
			                	if(row.procStatus == "1"){
			                		return "初始状态";
			                	}else if(row.procStatus == "2"){
			                		return "审批中";
			                	}else if(row.procStatus == "3"){
			                		return "已完成";
			                	}else if(row.procStatus == "4"){
			                		return "已失效";
			                	}else if(row.procStatus == "5"){
			                		return "已撤销";
			                	}else{
			                		return "已拒绝";
			                	}
			                }},
							{field : 'remark',title : '备注',width : 150,align : 'center'},
							{field : 'id',title : '操作',width:300,align : 'center',
								formatter : function(value, row, index) {
									//未提交和申请调整能看到修改申请
									if (row.procStatus == 1 ) {
										var result="";
											result += "<a href='javascript:void(0);' onclick='saveOrUpdOpenDlg("+ index + ");'>编辑</a>　";
											result += "<a href='javascript:void(0);' onclick='delRows("+ index + ");'>删除</a>　";
											result += "<a href='javascript:void(0);' onclick='sumitTravelApp("+ index + ");'>提交申请</a>　";
										result+="<a href=\"javascript:void(0)\" onclick=\"lookTravelAttment("+ index + ");\">查看附件</a>";
										return result;
										
									} else {
										var result = "<a href='javascript:void(0);' onclick='lookTravelAppCommentDialog("+index+");'>查看审批意见</a>　"
										if(row.procStatus==2){
											result += "<a href='javascript:void(0);' onclick='showImage("+ index + ");'>查看当前流程图</a>　";
										}
										result+="<a href=\"javascript:void(0)\" onclick=\"lookTravelAttment("+ index + ");\">查看附件</a>";
										return result;
									}
								}
							} 
							] ],
							 onLoadSuccess:function(data){
								 	//当鼠标放上该字段时，提示功能
						            $("#travelExpensesAppgrid").datagrid("doCellTip",{'max-width':'100px'});
						           
							  }, 
					toolbar : '#tb'
				});
	});
	
	//查看详情页
	function showTravelExpensesApp(index){
		var row = this.getRowData($grid,index);
		 $saveOrUpdbanliDialog = $("<div></div>").dialog({
				title:'查看差旅报销申请详情页',
				iconCls: 'icon-edit',
				width:718,
				height:$(window).height()*0.81,
				modal:true,
				shadow:false,
				href:"jsp/fd/travelExpensesApp/travelAppAddForm.jsp",
				onLoad:function(){
					//弹窗居中
					$saveOrUpdbanliDialog.dialog("center",{});
					
					if(index!=null){
						var f = $("#travelFrom");
						f.form("load", row); 
						//其他费用项目
						travelAppGrid(row.appNo);
						//交通费用列表
						businessgrid(row.appNo);
						//禁用表单
						disableForm("travelFrom");
						disableForm("businessFrom");
						 $("#btReason").attr({"disabled":"disabled"});
						 $("#remark").attr({"disabled":"disabled"});
						//隐藏按钮
						$("#upploadAttachment").hide();
						$("#checkAttachment").hide();
						$("#save").hide();
						$("#savebusin").hide();
						$("#appUserView").datagrid({"toolbar":""});
						$("#appBusView").datagrid({"toolbar":""});
						
						$("#jkAppNo").combobox({"value":row.jkAppNo});
						$("#jkAppNo").combobox("disable");
						$(".easyui-datetimebox").datetimebox("disable");
					}
				},
				onClose:function(){
					$(this).dialog('destroy');
					$grid.datagrid('reload');
				}
		 }); 	
		
	}
	
	
  
	//执行查询
	function subCustomerRepaymentForm(){
		$("#travelExpensesAppgrid").datagrid("load",{
			procStatus:$("#procStatus").combobox("getValue"),
			appNo:$("#appNoMain").val(),
			appDateS:$('#appDateS').datebox('getValue'),
			appDateE:$('#appDateE').datebox('getValue')
		});  
	}
	
	/* 动态显示弹出的Dialog的标题,显示"添加"或者"修改"报废申请*/
	function changeMyTitle(index){
		if(null == index){
			return '新增差旅报销申请';
		}else{
			return '编辑差旅报销申请';
		}
	}
	function changeMyicon(index){
		if(null == index ){
			return 'icon-add';
		}
			return 'icon-edit';
		
	}
	
	//弹窗修改
	function saveOrUpdOpenDlg(index) {
		var row = this.getRowData($grid,index);
		 $saveOrUpdbanliDialog = $("<div></div>").dialog({
				title:changeMyTitle(index),
				iconCls: changeMyicon(index),
				width:718,
				height:$(window).height()*0.82,
				modal:true,
				shadow:false,
				href:"jsp/fd/travelExpensesApp/travelAppAddForm.jsp",
				onLoad:function(){
					 $saveOrUpdbanliDialog.dialog("center",{});
					if(index!=null){
						var f = $("#travelFrom");
						f.form("load", row); 
						//其他费用项目
						travelAppGrid(row.appNo);
						//交通费用列表
						businessgrid(row.appNo);
						//设置交通项目编号
						$("#appNoBus").val(row.appNo);
						//可选借款申请
						
						if(row.grantExp!=null && row.grantExp!=0){
							findUserLoan(row.appNo,row);
						}else{
							findLoanAMT();
						}
							
						
					}
				},
				onClose:function(){
					$(this).dialog('destroy');
					$grid.datagrid('reload');
				}
		 }); 	
	}
	
	 // 查看流程批注
	 var $$row = "";
	function lookTravelAppCommentDialog(index) {
		var rows = $("#travelExpensesAppgrid").datagrid("getRows");
		 $$row = rows[index];//获取本条数据
		$("#optionsDialog").dialog('open').dialog('refresh');
	} 
  
	// 提交申请
	function sumitTravelApp(index){
		var row = this.getRowData($grid,index);
		$.messager.confirm('确定','是否确定提交所选的数据吗？',	function(flag) {
			if (flag) {
				$.ajax({
						url : "TravelTaskApp/startTravelExpensesApp.do",
						data : {"teaId" : row.teaId},
						async:false,
						success : function(iJson) {
							if(iJson.status){
								subCustomerRepaymentForm();
 	    						$("#travelExpensesAppgrid").datagrid("load",{}); 
	    						$.messager.alert(iJson.title,iJson.message,'info');  	
	    					}else{
	    						$.messager.alert('提示','请重新填写申请！','error');  	
	    					}
						}
					});
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
	
	//删除请求
	function delRows(index) {
		var row = getRowData($grid,index);
		if (row) {
			$.messager.confirm('提示','是否确定删除所选的数据吗？',function(flag) {
				if (flag) {
					$.ajax({
						url : "TravelExpensesApp/deleteTravelExpensesApp.do",
						data : {"appNo":row.appNo} ,
						dataType : 'JSON',
						async:false,
						success : function(rsp) {
							if (rsp.status) {
								//删除成功后刷新列表
								$grid.datagrid('reload');
							}
							$.messager.alert(rsp.title, rsp.message,'info');
						}
					});
				}
			});
		}
	}
	
	// 查看流程图片
	function showImage(index) {
		var row = this.getRowData($grid,index);
		var src = "TravelExpensesApp/showBadgeProcess.do?teaId="+ row.teaId;
		$('#imageDialog').dialog("open");
		$("#image").attr("src", src);
	}
	
	//查看附件
	function lookTravelAttment(index){
		//主页面查看时，明细为申请id
		var row = this.getRowData($grid,index);
		checkAttachementDetail(row.appNo,row.applicantNo,"1");
	};
	
</script>
</head>
<body>
		<div class="position" style="margin-top: 5px;">您当前所在位置：财务管理  &gt; 财务报销  &gt; 差旅报销申请</div>
		<div  class="well well-small" style="margin-left: 5px;margin-top: 5px;" >
			<form id="customerRepaymentForm"  method="post" style="min-width: 1200px;">
				<table >
					<tr>
					      <th>申请编号：</th>
					      <td><input name="appNo" id="appNoMain" class="easyui-textbox"/></td>
					      <th>申请状态：</th>
					      <td>
					      	<input id="procStatus" name="procStatus" class="easyui-textbox easyui-validatebox" />
					      </td>
					      <th align="right">申请日期：</th>
						      <td>
						      	 <input id="appDateS" name="appDateS" placeholder="请选择起始日期" class="easyui-textbox easyui-datebox" data-options="editable:false" /> &nbsp;&nbsp;至&nbsp;&nbsp;
						      </td>
						      <td>
						    	 <input id="appDateE" name="appDateE" placeholder="请选择截止日期" class="easyui-textbox easyui-datebox" data-options="editable:false"/>
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
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="saveOrUpdOpenDlg();">添加</a></td>
				</tr>
			</table>
		</div>
		
		<table id="travelExpensesAppgrid" title="差旅报销申请"></table>
		<!-- 展示客户信息详情 -->
		<div id="applyView"></div>
		<div id="attachmentList">
			<table id="lookAttachmentList" title="申请附件的信息"></table>
		</div>
		<div id="saveOrUpdateInvestProductDialog"></div>
		<div id="imageDialog" class="easyui-dialog" title="流程图片" data-options="border:false,closed:true,fit:true">
			<img id="image" src="">
		</div>
		<!-- 意见 -->
		<div id="optionsDialog" class="easyui-dialog" title="历史审批意见" style="width:900px;height:500px;" closed="true" data-options="href:'jsp/ad/optionsList.jsp',resizable:true,modal:true"></div>
</body>
</html>