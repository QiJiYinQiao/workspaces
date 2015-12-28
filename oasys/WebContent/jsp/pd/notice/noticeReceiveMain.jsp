<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>人事通知</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<jsp:include page="../../../layout/script.jsp"></jsp:include>
	<style type="text/css">
	a{
	text-decoration: none
	}
	</style>
	<script type="text/javascript">
			var $dg;
			var $grid;
			$(function() {
				 $dg = $("#dg");
				 $grid=$dg.datagrid({
					url : "noticeInfoController/findNoticeReceiveList.do",
					width : 'auto',
					height : $(this).height()-40,
					pagination:true,
					rownumbers:true,
					border:true,
					striped:true,
					singleSelect:false,
					columns : [ [ {field:'ck',checkbox:true},
					              {field : 'noticeNo',title : '通知编号',width : parseInt($(this).width()*0.1),align:'center'},
					              {field : 'topic',title : '主题',width : parseInt($(this).width()*0.15),align:'center',
					            	  formatter:function(value,row,index){
					            		  return "<a href='javascript:void(0)' onclick=\"toDetail('"+index+"')\">"+value+"</a>";
					            	  }},
					              {field : 'noticeContent',title : '通知内容',width : parseInt($(this).width()*0.2),align:'center'},
					              {field : 'noticeGrade',title : '通知级别',width : parseInt($(this).width()*0.08),align:'center',
					            	  formatter:function(value,row,index){
					            	  	if(value=="1"){
					            	  		return "一般";
					            	  	}else if(value=="2"){
					            	  		return "重要";
					            	  	}else if(value==3){
					            	  		return "紧急";
					            	  	}else{
					            	  		return "未知级别";
					            	  	}
					            	  }},
					              {field : 'sendName',title : '发件人',width : parseInt($(this).width()*0.1),align:'center'},
					              {field : 'sendDtime',title : '发件时间',width : parseInt($(this).width()*0.1),align:'center'},
					              {field : 'isHaveAtt',title : '是否包含附件',width : parseInt($(this).width()*0.1),align:'center',
					            	  formatter:function(value,row,index){
					            		  if(value=="0"){
					            			  return "是";
					            		  }else{
					            			  return "否";
					            		  }
					            	  }},
					              {field : 'cz',title : '操作',width : parseInt($(this).width()*0.13),align:'center',
					            	  formatter:function(value,row,index){
					            		  if(row.isNeedReceipt=='0'){//需要回执
					            			  if(row.isReceipted!='0'){
						            			  return "<a href='javascript:void(0);' class='easyui-linkbutton' plain='false' onclick=\"receipt('"+row.noticeNo+"');\">回执</a>";
					            			  }else{
					            				  return "<font color='red'>已回执</font>";
					            			  }
					            		  		//回执完毕改为已回执
					            		  		
					            		  }else{
					            			  return "<font color='red'>无需回执</font>";
					            		  }
					            	  }}
					              ] ],toolbar:'#tb',onLoadSuccess:function(data){
									 	//当鼠标放上该字段时，提示功能
							            $("#dg").datagrid("doCellTip",{'max-width':'100px'});
								  }
				});
			});
			//删除通知信息
			function delRows(){
				 var rows = $('#dg').datagrid('getSelections');//获取选中的记录
				 if(rows!=null&&rows.length>=1){
					 var ids = new Array();
					 $.messager.confirm('删除', '删除该记录将不可恢复，确认删除吗?', function(d) {
						 if(d){
							 $.each(rows,function(i,row){
									if (row) {
										var rowIndex = $('#dg').datagrid('getRowIndex', row);
										$('#dg').datagrid('deleteRow', rowIndex);
										var niaId;
										//根据主表通知编号和收件人查询附加表数据
										$.ajax({
											url:'noticeInfoController/findNoticeInfoAttachByNoticeNo.do',
											type:'post',
											data:'noticeNo='+row.noticeNo,
											async:false,
											success:function(data){
												if(data){
													niaId=data[0].niaId;
												}
											}
										})
										ids.push(niaId);
									}
							 });
						 ids = ids.join(",");// 转换为字符串
						 $.ajax({
							    type:'post',
								url:"noticeInfoController/delNoticeInfoAttach.do",
								data: "ids="+ids,
								success: function(rsp){
									$.messager.show({
										title : rsp.title,
										msg : rsp.message,
										timeout : 1000 * 2
									});
									$('#dg').datagrid('reload');
								}
						});
						}
					 })
				 }else{
					 $.messager.alert("提示","请至少选择一条记录!","warning");
				 }
			}
			///回执通知信息，更改主表状态并判断是否已全部回执
			function receipt(noticeNo){
				//该条通知可以回执
				//修改附加表状态为已回执,,//查询是否全部回执，并更改主表全部回执状态
				$.messager.confirm('回执', '确认回执此通知吗?', function(d){
					if(d){
						$.ajax({
							url:'noticeInfoController/receiptByNoticeNo.do',
							type:'post',
							data:'noticeNo='+noticeNo,
							async:false,
							success:function(data){
								$.messager.alert("提示","回执成功！");
								$('#dg').datagrid('reload');
							},error:function(data){
								$.messager.alert("提示","回执失败！");
								$('#dg').datagrid('reload');
							}
						})
					}
				})

			}
			//根据通知编号查询通知详情
			function toDetail(index){
				var rows = $("#dg").datagrid("getRows");
				var row = rows[index];//获取本条数据
				var $dialog;
				$dialog=$("<div></div>").dialog({
					title:'通知详情',
					href:'jsp/pd/notice/noticeReceiveEditDlg.jsp',
					width:800,
					height:700,
					modal:true,
					onClose:function(){
						$dialog.dialog('destroy');
					},
					onLoad:function(){
						$("#form").form('load',row)
						 if(row.isNeedReceipt=='0'){//需要回执
							  if(row.isReceipted!='0'){
								  $("#receipt").show();
							  }
						  } 
						disableForm("form");
					},
					buttons : [ {
						text : '关闭',
						iconCls : 'icon-cancel',
						handler : function() {
							$dialog.dialog('close');
						}
					}
					]
				});
			}
		</script>
  </head>
  <body>
      <div data-options="region:'center',border : false">
      <div style="margin-left: 5px;margin-top: 5px">
			<div class="position" style="margin-top: 5px;">您当前所在位置：人力资源规划 &gt; 人事通知 &gt; 收件箱</div>
		</div>
		<div id="tb" style="padding:2px 0">
			<table cellpadding="0" cellspacing="0">
				<tr>
					<td style="padding-left:2px">
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRows();">删除</a>
					</td>
				</tr>
			</table>
		</div>
		<table id="dg" title="收件箱列表"></table>
  	</div>	
  </body>
</html>