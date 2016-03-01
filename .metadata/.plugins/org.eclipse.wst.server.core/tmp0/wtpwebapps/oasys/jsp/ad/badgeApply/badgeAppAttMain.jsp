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
<title>工牌申请</title>
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

		/**
		 * 根据索引获取对象名称
		 * @param target Grid对象
		 * @param index 索引
		 * @returns 索引的对应行的信息
		 */
		function getRowData (index) {
			if (!$.isNumeric(index) || index < 0) { return undefined; }
			var rows = $("#badgeAppGrid").datagrid("getRows");
		    return rows[index];
		}
		
		var rows;

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
	  }); 
	  

  	//工牌申请列表
  	var $grid;
	$(function() {
		$(window).resize(function(){  
            $("#badgeAppGrid").datagrid("resize",{  
            	height: $(window).height()-110,
            });                
        });
		$grid =$("#badgeAppGrid").datagrid({
					url : 'BadgeApp/findBadgeAppAttList.do',
					width: 'auto',
					height : $(window).height()-110,
					pagination:true,
					rownumbers:true,
					border:false,
					singleSelect:true,
					nowrap:true,
					fitColumns:false,
					pageList:[10,20,30,40],
					columns : [ [
								{field : 'appNo',title : '申请编号',width:120,align : 'center'},
							        {field : 'fullName',title : '申请部门',width:200,align : 'center'},
							        {field : 'registrantNanme',title : '登记人',width:80,align : 'center',
										/* formatter : function(value, row, index) {
											return "<a href=\"javascript:void(0)\" onclick=\"showView("+ index + ");\">" + value + "</a>";
										} */
									},
									{field : 'name',title : '申请人',width:80,align : 'center',
										/* formatter : function(value, row, index) {
											return "<a href=\"javascript:void(0)\" onclick=\"showView("+ index + ");\">" + value + "</a>";
										} */
									},
									{field : 'namePinyin',title : '英文姓名',width :130,sortable : true,align : 'center'},
									{field : 'positionName',title : '职位',width:130,align : 'center'},
									{field : 'regDatetime',title : '登记时间',width:130,align : 'center'},  
									{field : 'appDate',title : '申请日期',width:90,align : 'center'},   
									{field : 'procStatus',title : '流程状态',width : 80,align : 'center',formatter:function(value,row,index){
					                	if(row.procStatus == "1"){
					                		return "初始状态";
					                	}else if(row.procStatus == "2"){
					                		return "审批中";
					                	}else if(row.procStatus == "3"){
					                		return "已完成";
					                	}else if(row.procStatus == "4"){
					                		return "已失效";
					                	}else{
					                		return "已撤销";
					                	}
					                }},
									{field : 'remark',title : '备注',width :200,align : 'center'},
									{field : 'id',    title : '操作',    width :350, align:'center',
								 	formatter: function(value,row,index){
								 		//未提交和申请调整能看到修改申请
									if (row.procStatus == 1 ) {
										var result = "<a href='javascript:void(0);' onclick='updRowsOpenDlg("+ index + ");'>编辑</a>　";
										result += "<a href='javascript:void(0);' onclick='delRows("+ index + ");'>删除</a>　";
										result += "<a href='javascript:void(0);' onclick='sumitBadgeApp("+ index + ");'>提交申请</a>　";
										result+="<a href=\"javascript:void(0)\" onclick=\"lookBadgeAttment("+ index + ");\">查看附件</a>";
										return result;
									} else {
										var result = "<a href='javascript:void(0);' onclick='lookBadgeAppCommentDialog("+index+");'>查看审批意见</a>　";
										if(row.procStatus==2){
											result += "<a href='javascript:void(0);' onclick='showImage("+ index + ");'>查看当前流程图</a>　";
										}
										result+="<a href=\"javascript:void(0)\" onclick=\"lookBadgeAttment("+ index + ");\">查看附件</a>";
										return result;
									}
								}
							} 
							] ],
							 onLoadSuccess:function(data){
								 //合并部门
								   var rows=data.rows; 
								   var merdept={};
								    if(rows){
								    	for(var i=0;i<rows.length;i++){
								    		 
							           		var appDN=rows[i].appNo+rows[i].deptNo;
								    		if(appDN in merdept){
								    			merdept[appDN].rowspan++;
								    		}else{
								    			merdept[appDN]={"index":i,"rowspan":1};
								    		}
							           	}
								    	
								    }
								    for(var k in merdept){
							               $(this).datagrid('mergeCells',{
							                   index: merdept[k].index,
							                   field: 'fullName',
							                   rowspan: merdept[k].rowspan
							               });
								    } 
								 	//合并其他
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
						                   field: 'registrantNanme',
						                   rowspan: mergeMap[i].rowspan
						               });
						        	   
						        	   //$(this).datagrid("autoMergeCells",['fullName']);
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
						                   field: 'regDatetime',
						                   rowspan: mergeMap[i].rowspan
						               });
						               $(this).datagrid('mergeCells',{
						                   index: mergeMap[i].index,
						                   field: 'remark',
						                   rowspan: mergeMap[i].rowspan
						               });
						               
						              // $(this).datagrid("autoMergeCells",['fullName']);
						               $(this).datagrid('mergeCells',{
						                   index: mergeMap[i].index,
						                   field: 'contractSignDate',
						                   rowspan: mergeMap[i].rowspan
						               });
						               $(this).datagrid('mergeCells',{
						                   index: mergeMap[i].index,
						                   field: 'id',
						                   rowspan: mergeMap[i].rowspan
						               });
						              
						           }    
						           $(this).datagrid("doCellTip",{'max-width':'100px'});
							  }, 
					toolbar : '#tb'
				});
	});
	
	
	//查看附件
	function lookBadgeAttment(index){
		//主页面查看时，明细为申请id
		var row = this.getRowData(index);
		checkAttachementDetail(row.appNo,row.registrantNo,"1");
	};
	//执行查询
	function subCustomerRepaymentForm(){
		$("#badgeAppGrid").datagrid("load",{
			procStatus:$("#procStatus").combobox("getValue"),
			appNo:$("#appNoMain").val(),
			myId:$("#myId").combobox("getValue"),
			appDateS:$('#appDateS').datebox('getValue'),
			appDateE:$('#appDateE').datebox('getValue')
		});  
	}
	/* 动态显示弹出的Dialog的标题,显示"添加"或者"修改"报废申请*/
	function changeMyTitle(index){
		if(null == index){
			return '新增工牌申请';
		}else{
			return '编辑工牌申请';
		}
	}
	function changeMyicon(index){
		if(null == index ){
			return 'icon-add';
		}
			return 'icon-edit';
		
	}
	
	//弹窗修改
	function updRowsOpenDlg(index) {
		//var row = $dg.datagrid('getSelected');
			var row = this.getRowData(index);
			$("#applyView").dialog({
				title : changeMyTitle(index),
				iconCls:changeMyicon(index),
				width : 830,
				height : $(window).height()*0.75,
				modal:true,
				href : "jsp/ad/badgeApply/badgeAppAddForm.jsp",
				 onLoad:function(){
					 if(index!=null){
						 badgePeopleGrid(row.appNo);
						 $("#appPeopleForm").form("load",row);
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
		var rows = $("#badgeAppGrid").datagrid("getRows");
		 $$row = rows[index];//获取本条数据
		$("#optionsDialog").dialog('open').dialog('refresh');
	} 
  
	// 提交申请
	function sumitBadgeApp(index){
		var row = this.getRowData(index);
		$.messager.confirm('确定','是否确定提交所选的数据吗？',	function(flag) {
			if (flag) {  
				$.ajax({
						url : "BadgeTask/startBadgeApp.do",
						data : {"pnrId" : row.pnrId},
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
	
	
	
	//删除请求
	function delRows(index) {
		var row = getRowData(index);
		if (row) {
			$.messager.confirm('提示','是否确定删除所选的数据吗？',function(flag) {
				if (flag) {
					$.ajax({
						url : "BadgeApp/deleteBadgeApp.do",
						data : {"appNo":row.appNo} ,
						dataType : 'JSON',
						async:false,
						success : function(rsp) {
							if (rsp.status) {
								//删除成功后刷新列表
								$grid.datagrid('reload');
								parent.$.messager.alert('提示','该工牌申请删除成功！','info');
							}else{
								parent.$.messager.alert('提示','该工牌申请删除失败！','error');
							}
						}
					});
				}
			});
		}
	}
	
	// 查看流程图片
	function showImage(index) {
		var row = this.getRowData(index);
		var src = "BadgeTask/showBadgeProcess.do?pnrId="+ row.pnrId;
		$('#imageDialog').dialog("open");
		$("#image").attr("src", src);
	}
	
	
	
</script>
</head>
<body >
			<div class="position" style="margin-top: 5px;">您当前所在位置：行政办公 &gt; 工牌申请</div>
			<div class="well well-small" style="margin-left: 5px;margin-top: 5px" >
				<form id="customerRepaymentForm"  method="post" style="min-width: 1200px;">
					<table cellpadding="0px;">
						<tr>
							  <th>申请编号：</th>
						      <td><input name="appNo" id="appNoMain" class="easyui-textbox"/></td>
						       <th>所属业务端：</th>
						      <td>
						      	<input id="myId" name="myId" class="easyui-textbox easyui-validatebox" />
						      </td>
					      </tr>
					      <tr>
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
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="updRowsOpenDlg();">添加</a></td>
					</tr>
				</table>
			</div>
			<table id="badgeAppGrid" title="工牌申请管理" fit="false"></table>

		<!-- 展示客户信息详情 -->
		<div id="applyView"></div>
		<div id="attachmentList">
			<table id="lookBadgeAttmentList" title="申请附件的信息"></table>
		</div>
		<div id="saveOrUpdateInvestProductDialog"></div>
		<div id="imageDialog" class="easyui-dialog" title="流程图片" data-options="border:false,closed:true,fit:true">
			<img id="image" src="">
		</div>
		<div id="optionsDialog" class="easyui-dialog" title="历史审批意见" style="width:900px;height:500px;" closed="true" data-options="href:'jsp/ad/optionsList.jsp',resizable:true,modal:true"/>
</body>
</html>