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
<title>用章申请</title>
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
		 //财富端、jiekuanduanu
			  $("#myId").combobox({
					valueField: 'value',   
			        textField: 'label',   
			        data: [{
						label: '全部',
						value: ''
					},{
						label: '财富端',
						value: 'CF'
					},{
						label: '借款端',
						value: 'JK'
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
            $("#useStampAppgrid").datagrid({  
            	height: $(window).height()-83  
            });                
        }); 
		
		$grid =$("#useStampAppgrid").datagrid({
					url : 'UseStampApp/findUseStampAppAttList.do',
					width: 'auto',
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
					        {field : 'appNo',title : '申请编号',width: parseInt($(this).width()*0.09),align : 'center',
								formatter : function(value, row, index) {
									return "<a href=\"javascript:void(0)\" onclick=\"showUseStampApp("+ index + ");\">" + value + "</a>";
								}
					        },
							{field : 'applicantName',title : '申请人',width: parseInt($(this).width()*0.06),align : 'center',
								formatter : function(value, row, index) {
									return "<a href=\"javascript:void(0)\" onclick=\"showView("+ index + ");\">" + value + "</a>";
								}
							},
					        {field : 'fullName',title : '申请部门',width: parseInt($(this).width()*0.15),align : 'center'},
							{field : 'appDate',title : '申请日期',width: parseInt($(this).width()*0.06),align : 'center',
								formatter : function(value, row, index) {
									if(value!=null){
										return value.split(" ")[0];
									}else{
										return "";
									}
								}},   
							{field : 'turnoverDtime',title : '移交时间',width: parseInt($(this).width()*0.06),align : 'center'},   
							{field : 'useBgDate',title : '用章开始时间',width: parseInt($(this).width()*0.06),align : 'center',
								formatter : function(value, row, index) {
									if(value!=null){
										return value.split(" ")[0];
									}else{
										return "";
									}
								}},
							{field : 'useEdDate',title : '用章结束时间',width: parseInt($(this).width()*0.06),align : 'center',
									formatter : function(value, row, index) {
										if(value!=null){
											return value.split(" ")[0];
										}else{
											return "";
										}
									}},
							{field : 'useNature',title : '使用性质',width: parseInt($(this).width()*0.06),align : 'center',
										formatter : function(value, row, index) {
											if(value=='1'){
												return '本地使用';
											}else{
												return '外借';
											}
										}},   
							{field : 'stampType',title : '用章类型',width : 104,sortable : true,align : 'center'},
							{field : 'stampName',title : '印章名称',width: parseInt($(this).width()*0.06),align : 'center'},   
							{field : 'procStatus',title : '申请状态',width :  parseInt($(this).width()*0.05),align : 'center',formatter:function(value,row,index){
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
							{field : 'remark',title : '备注',width :  parseInt($(this).width()*0.1),align : 'center'},
							{field : 'id',title : '操作',width: parseInt($(this).width()*0.16),align : 'center',
								formatter : function(value, row, index) {
									//未提交和申请调整能看到修改申请
									if (row.procStatus == 1 ) {
										var result = "<a href='javascript:void(0);' onclick='saveOrUpdOpenDlg("+ index +");'>编辑</a>　　";
										result += "<a href='javascript:void(0);' onclick='delRows("+ index + ");'>删除</a>　　";
										result += "<a href='javascript:void(0);' onclick='sumitBadgeApp("+ index + ");'>提交申请</a>";
										return result;
									} else {
										var result = "<a href='javascript:void(0);' onclick='lookBadgeAppCommentDialog("+index+");'>查看审批意见</a>　　"
										if(row.procStatus==2){
											 result += "<a href='javascript:void(0);' onclick='showImage("+ index + ");'>查看当前流程图</a>"
										}
										
										return result;
									}
								}
							} 
							] ],
							 onLoadSuccess:function(data){
								 
								//合并其他
								var rows=data.rows; 
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
					        	   $(this).datagrid("autoMergeCells",['appNo']);
					        	   $(this).datagrid('mergeCells',{
					                   index: mergeMap[i].index,
					                   field: 'appNo',
					                   rowspan: mergeMap[i].rowspan
					               });
					        	   
					        	   
					               $(this).datagrid('mergeCells',{
					                   index: mergeMap[i].index,
					                   field: 'appDate',
					                   rowspan: mergeMap[i].rowspan
					               });
					             
					               $(this).datagrid('mergeCells',{
					                   index: mergeMap[i].index,
					                   field: 'procStatus',
					                   rowspan: mergeMap[i].rowspan
					               });
					               $(this).datagrid('mergeCells',{
					                   index: mergeMap[i].index,
					                   field: 'applicantName',
					                   rowspan: mergeMap[i].rowspan
					               });
					               $(this).datagrid('mergeCells',{
					                   index: mergeMap[i].index,
					                   field: 'remark',
					                   rowspan: mergeMap[i].rowspan
					               });
					               
					               $(this).datagrid('mergeCells',{
					                   index: mergeMap[i].index,
					                   field: 'fullName',
					                   rowspan: mergeMap[i].rowspan
					               });
					               $(this).datagrid('mergeCells',{
					                   index: mergeMap[i].index,
					                   field: 'turnoverDtime',
					                   rowspan: mergeMap[i].rowspan
					               });
					               $(this).datagrid('mergeCells',{
					                   index: mergeMap[i].index,
					                   field: 'useBgDate',
					                   rowspan: mergeMap[i].rowspan
					               });
					               $(this).datagrid('mergeCells',{
					                   index: mergeMap[i].index,
					                   field: 'useEdDate',
					                   rowspan: mergeMap[i].rowspan
					               });
					               $(this).datagrid('mergeCells',{
					                   index: mergeMap[i].index,
					                   field: 'useNature',
					                   rowspan: mergeMap[i].rowspan
					               });
					               $(this).datagrid('mergeCells',{
					                   index: mergeMap[i].index,
					                   field: 'id',
					                   rowspan: mergeMap[i].rowspan
					               });
					              
					           }    
							 
							 	//当鼠标放上该字段时，提示功能
					            $(this).datagrid("doCellTip",{'max-width':'100px'});
						           
							  }, 
					toolbar : '#tb'
				});
	});
	
	//查看用章申请详情信息
	function showUseStampApp(index){
		var row = this.getRowData($grid,index);
		$("#editView").dialog({
			title : '查看用章申请详情',
			iconCls: 'icon-edit',
			width : 955,
			height : 'auto',
			modal:true,
			resizable:false,
			shadow:false,
			href : "jsp/ad/UseStampApp/useStampAddForm.jsp",
			onLoad:function(){
					$("#editView").dialog("center",{});
				    
					if(row.appDate!=null){
						row.appDate=row.appDate.split(" ")[0];
					}
					if(row.useBgDate!=null){
						row.useBgDate=row.useBgDate.split(" ")[0];
					}
					if(row.useEdDate!=null){
						row.useEdDate=row.useEdDate.split(" ")[0];
					}
					
					var f = $("#useStampForm");
					f.form("load", row); 
					useStampGrid(row.appNo)
					 //隐藏列表中国的删除
					$("#useStampAtt").datagrid({"toolbar":""});
					$("#save").hide();
					$("#rset").hide();
					$("#useStampForm .easyui-datebox").datebox("disable");
					$("#remark").attr("disabled","disabled");
					
					
			}, 
			onClose:function(){
				$grid.datagrid('reload');
			} 
		}); 
	}
	
	
	/* 动态显示弹出的Dialog的标题,显示"添加"或者"修改"报废申请*/
	function changeMyTitle(index){
		if(null == index){
			return '添加用章申请';
		}else{
			return '修改用章申请';
		}
	}
	function changeMyicon(index){
		if(null == index ){
			return 'icon-add';
		}
			return 'icon-edit';
		
	}
  
	//执行查询
	function subCustomerRepaymentForm(){
		$("#useStampAppgrid").datagrid("load",{
			procStatus:$("#procStatus").combobox("getValue"),
			appNo:$("#appNoMain").val(),
			myId:$("#myId").combobox("getValue"),
			appDateS:$('#appDateS').datebox('getValue'),
			appDateE:$('#appDateE').datebox('getValue')
		});  
	}
	
	
	//添加弹窗修改
	function saveOrUpdOpenDlg(index) {
		//var row = $dg.datagrid('getSelected');
			var row = this.getRowData($grid,index);
			$("#editView").dialog({
				title : changeMyTitle(index),
				iconCls: changeMyicon(index),
				width : 955,
				height : 646,
				modal:true,
				resizable:false,
				href : "jsp/ad/UseStampApp/useStampAddForm.jsp",
				onLoad:function(){
					if(index!=null){
						if(row.appDate!=null){
							row.appDate=row.appDate.split(" ")[0];
						}
						if(row.useBgDate!=null){
							row.useBgDate=row.useBgDate.split(" ")[0];
						}
						if(row.useEdDate!=null){
							row.useEdDate=row.useEdDate.split(" ")[0];
						}
						
						var f = $("#useStampForm");
						f.form("load", row); 
						useStampGrid(row.appNo)
					}
				}, 
				onClose:function(){
					$grid.datagrid('reload');
				} 
			}); 
	}
	 // 查看流程批注
	 var $$row = "";
	function lookBadgeAppCommentDialog(index) {
		var rows = $("#useStampAppgrid").datagrid("getRows");
		 $$row = rows[index];//获取本条数据
		$("#optionsDialog").dialog('open').dialog('refresh');
	} 
  
	// 提交申请
	function sumitBadgeApp(index){
		var row = this.getRowData($grid,index);
		$.messager.confirm('确定','是否确定提交所选的数据吗？',	function(flag) {
			if (flag) {
				$.ajax({
						url : "UseStampTask/startUseStampApp.do",
						data : {"usaId" : row.usaId},
						async:false,
						success : function(rsp) {
							if(rsp.status){
								$grid.datagrid('reload');
							}
							parent.$.messager.alert(rsp.title,rsp.message,'info');
							
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
						url : "UseStampApp/deleteUseStampApp.do",
						data : {"appNo":row.appNo} ,
						dataType : 'JSON',
						async:false,
						success : function(rsp) {
							if (rsp.status) {
								//删除成功后刷新列表
								$grid.datagrid('reload');
								parent.$.messager.alert(rsp.title,rsp.message,'info');
							}else{
								parent.$.messager.alert(rsp.title,rsp.message,'error');
							}
						}
					});
				}
			});
		}
	}
	
	// 查看流程图片
	function showImage(index) {
		var row = this.getRowData($grid,index);
		var src = "UseStampApp/showStampProcess.do?usaId="+ row.usaId;
		$('#imageDialog').dialog("open");
		$("#image").attr("src", src);
	}
	
	
</script>
</head>
<body>
	<div data-options="region:'center',border : false">
		<div class="position" style="margin-top: 5px;">您当前所在位置：行政办公   &gt; 用章申请</div>
		<div class="well well-small" style="margin-left: 5px;margin-top: 5px">
			<form id="customerRepaymentForm"  method="post">
				<table cellpadding="0" cellspacing="1" border="0">
					<tr>
					      <th>申请编号:</th>
					      <td><input name="appNo" id="appNoMain" /></td>
					      <th>所属业务端:</th>
					      <td>
					      	<input id="myId" name="myId" class="easyui-textbox easyui-validatebox" />
					      </td>
					      <th>申请状态:</th>
					      <td>
					      	<input id="procStatus" name="procStatus" class="easyui-textbox easyui-validatebox" />
					      </td>
					      <th>申请日期:</th>
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
			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="saveOrUpdOpenDlg();">添加</a></td>
		</div>
		
		<table id="useStampAppgrid" title="用章申请"  width="100%"></table>

		<div id="imageDialog" class="easyui-dialog" title="流程图片" data-options="border:false,closed:true,fit:true">
			<img id="image" src="">
		</div>
		<div id ="editView" style="height: auto"></div>
		
		<div id="optionsDialog" class="easyui-dialog" title="历史审批意见" style="width:900px;height:500px;" closed="true" data-options="href:'jsp/ad/optionsList.jsp',resizable:true,modal:true"></div>
	</div>
</body>
</html>