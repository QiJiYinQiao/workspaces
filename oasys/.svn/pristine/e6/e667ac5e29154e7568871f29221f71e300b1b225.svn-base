<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<script type="text/javascript">
	$(function() {
		CKEDITOR.replace('noticeContent');
		CKEDITOR.config.readOnly = true;
	});
	//收件人
	$("#userIds").combobox({
		width:171,
		multiple:true,
		separator:",", 
		url:"noticeInfoController/findUserList.do",
		valueField:'code',
	 	textFiled:'text',
	 	editable:false
	}); 	
	//查看附件
	$("#checkAttachment").click(function(){
		checkAttachementDetail($("input[name='noticeNo']").val(),$("input[name='sender']").val());
	});
	$("#receipt").click(function(){
		receipt($("input[name='noticeNo']").val());
	});
	
	///回执通知信息，更改主表状态并判断是否已全部回执
	function receipt(noticeNo){
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
						$("#receipt").hide();
					},error:function(data){
						$.messager.alert("提示","回执失败！");
					}
				})
			}
		})
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
	table ,th,td{
		text-align:left;
		padding: 6px;
	}
</style>
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 10px;">
		<form id="form" method="post">
			<fieldset>
				<legend><img src="extend/fromedit.png" style="margin-bottom: -3px;"/> 人事通知</legend>
				<input name="sender" type="hidden"/>
				<input name="isNeedReceipt" type="hidden"/>
				<input name="isReceipted" type="hidden"/>
				 <table>
					 <tr>
					    <th>通知编号</th>
						<td><input name="noticeNo" id="noticeNo" class="easyui-textbox easyui-validatebox" type="text"/></td>
					    <th>主题</th>
						<td><input name="topic" id="topic" placeholder="请输入通知主题" class="easyui-textbox easyui-validatebox" data-options="required:true" type="text"/></td>
					 </tr>
					 <tr>
						<th>通知级别</th>
						<td><select name="noticeGrade" id="noticeGrade" type="text"  class="easyui-textbox easyui-combobox" data-options="required:true">
							<option value="1">一般</option>
							<option value="2">重要</option>
							<option value="3">紧急</option>
						</select></td>
						<th>发件人</th>
						<td><input name="sendName" id="sendName" class="easyui-textbox easyui-validatebox" type="text"/></td>
					 </tr>
					 <tr>
						<th>通知内容</th>
						<td colspan="3"><textarea id="noticeContent" name="noticeContent"></textarea></td>
					</tr>
				 </table>
				 <div id="btn" style="float: right">
					<a id="checkAttachment" href="javascript:void(0);" class="easyui-linkbutton">查看附件</a>	
					<a id="receipt" href="javascript:void(0);" class="easyui-linkbutton" style="display: none">回执</a>	
				</div>
			</fieldset>
		</form>
	</div>
