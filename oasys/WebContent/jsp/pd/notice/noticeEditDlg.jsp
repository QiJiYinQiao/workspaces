<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<script type="text/javascript">
	$(function() {
		CKEDITOR.replace('noticeContent');
		$("#form").form({
			url :"noticeInfoController/persistenceNoticeInfo.do",
			onSubmit : function() {
				var  abc=CKEDITOR.instances.noticeContent.getData();
				$("#noticeContent").val(abc);
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				var isValid = $(this).form('validate');
				if (!isValid) {
					parent.$.messager.progress('close');
				}
				return isValid;
			},
			success : function(result) {
				parent.$.messager.progress('close');
				result = $.parseJSON(result);
				if (result.status) {
					parent.reload;
					parent.$.modalDialog.openner.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_datagrid这个对象，是因为role.jsp页面预定义好了
					parent.$.modalDialog.handler.dialog('close');
					parent.$.messager.show({
						title : result.title,
						msg : result.message,
						timeout : 1000 * 2
					});
				}else{
					parent.$.messager.show({
						title :  result.title,
						msg : result.message,
						timeout : 1000 * 2
					});
				}
			}
		});
	});
	//收件人
	$("#userIds").combobox({
		width:171,
		multiple:true,
		separator:",",
		required:true,
		url:"noticeInfoController/findUserList.do",
		valueField:'code',
	 	textFiled:'text'
	}); 	
	//上传附件,明细存储taskID
	$("#upploadAttachment").click(function(){
		fileUploadsDlg('${noticeNo }');
	});
	//查看附件
	$("#checkAttachment").click(function(){
		checkAttachementDetail('${noticeNo }');
	});
	//添加收件人
	var userIds;
	function addReceiver(){
		var $dialog;
		userIds=new Array();
		if($("#noiId").val!=null){
			//查询收件人
				$.ajax({
					url:'noticeInfoController/findReceiversByNoticeNo.do',
					type:'post',
					data:'noticeNo='+'${noticeNo }',
					async:false,
					success:function(rsp){
						$.each(rsp,function(i,e){
							userIds.push(e.receiver)
			    	  });
					},
					error:function(rsp){
						
					}
				})
			}
		userIds=userIds.join(",");
		$dialog = $("<div></div>").dialog({
			title:'添加收件人',
			href:'jsp/pd/notice/noticeReceiverDlg.jsp?',
			width:1200,
			height:600,
			modal:true,
			onClose:function(){
				$dialog.dialog('destroy');
			},
			buttons : [ {
				text : '确定',
				iconCls : 'icon-ok',
				handler : function() {
					//收件人选择完毕
					var selections = $("#receiverGrid").datagrid('getSelections');
					if(selections.length<=0){
						$.messager.alert("提示信息","请至少勾选一个收件人！");
						return false;
					}
					var checkedIds=[];
					$.each(selections,function(i,e){
						checkedIds.push(e.userId);
					});
					checkedIds = checkedIds.join(",");
					$("input[name='userIds']").val(checkedIds);
					$dialog.dialog('close');
				}
			},{
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
<style>
	.easyui-textbox{
		height: 18px;
		width: 170px;
		line-height: 16px;
	    /*border-radius: 3px 3px 3px 3px;*/
	    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset;
	    transition: border 0.2s linear 0s, box-shadow 0.2s linear 0s;
	}
	textarea:focus, input[type="text"]:focus{
	    border-color: rgba(82, 168, 236, 0.8);
	    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(82, 168, 236, 0.6);
	    outline: 0 none;
		}
		table {
	    background-color: transparent;
	    border-collapse: collapse;
	    border-spacing: 0;
	    max-width: 100%;
	}

	fieldset {
	    border: 0 none;
	    margin: 0;
	    padding: 0;
	}
	legend {
	    -moz-border-bottom-colors: none;
	    -moz-border-left-colors: none;
	    -moz-border-right-colors: none;
	    -moz-border-top-colors: none;
	    border-color: #E5E5E5;
	    border-image: none;
	    border-style: none none solid;
	    border-width: 0 0 1px;
	    color: #999999;
	    line-height: 20px;
	    display: block;
	    margin-bottom: 10px;
	    padding: 0;
	    width: 100%;
	}
	input, textarea {
	    font-weight: normal;
	}

</style>
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 10px;">
		<form id="form" method="post">
			<fieldset>
				<legend><img src="extend/fromedit.png" style="margin-bottom: -3px;"/> 人事通知</legend>
				<input name="noiId" id="noiId" type="hidden"/>
				<input name="noticeNo" id="noticeNo" type="hidden" value="${noticeNo }"/>
				<input name="sender" id="sender"  type="hidden"/>
				<input name="sendDtime" id="sendDtime"  type="hidden"/>
				<input name="isHaveAtt" id="isHaveAtt"  type="hidden"/>
				<input name="isAllReceipted" id="isAllReceipted"  type="hidden"/>
				<input name="userIds" type="hidden"/>
				 <table>
					 <tr>
					    <th>主题</th>
						<td><input name="topic" id="topic" placeholder="请输入通知主题" class="easyui-textbox easyui-validatebox" data-options="required:true" type="text"/></td>
						<th>收件人</th>
						<!-- <td><input name="userIds" id="userIds" type="text"  class="easyui-combobox"/></td> -->
						<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="false" onclick="addReceiver();">添加收件人</a></td>
					 </tr>
					 <tr>
						<th>通知级别</th>
						<td><select name="noticeGrade" id="noticeGrade" type="text"  class="easyui-textbox easyui-combobox" data-options="required:true">
							<option value="1">一般</option>
							<option value="2">重要</option>
							<option value="3">紧急</option>
						</select></td>
						<th>是否需要回执</th>
						<td><select name="isNeedReceipt" id="isNeedReceipt" type="text"  class="easyui-textbox easyui-combobox" data-options="required:true">
							<option value="1">不需要</option>
							<option value="0">需要</option>
						</select></td>
					 </tr>
					 <tr>
						<th>通知内容</th>
						<td colspan="3"><textarea id="noticeContent" name="noticeContent"></textarea></td>
					</tr>
				 </table>
				 <div style="float: right">
				 	<a id="upploadAttachment" href="javascript:void(0);" class="easyui-linkbutton" >上传附件</a>
					<a id="checkAttachment" href="javascript:void(0);" class="easyui-linkbutton">查看附件</a>	
				</div>
			</fieldset>
		</form>
	</div>
