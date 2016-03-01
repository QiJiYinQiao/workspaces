<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<style type="text/css">
 		textarea{
			width: 800px;
			height:30px;
		}
		/* form{height:605px;} */
		table th{text-align:right;}
	</style>
	<script type="text/javascript">
		$(function(){
			
		});
		
		//提交按钮
		function saveQuestions(){
			$.messager.confirm('提示', '点击按钮之后将进入下一个任务活动环节,此任务将对您不可见!', function(r){
				if (r){
					var questions = "";
					var txer = $("textarea[name='questions']");
					for(var i = 0 ; i < txer.length; i++){
						if("" != txer[i].value){
 							questions += txer[i].value + "@@@";
						}
					}
					$.ajax({
						url : "firstauditQuestioncollect/firstauditQuestioncollectAction!saveOuboundVerify.action",
						type : "post",
						data : {"loanOrderId":$row.loanOrderId,"questions":questions},
						success : function(data){
							console.info(data);
							$.messager.show({
								title:"提示",
								msg:data.message,
								timeout:1000,
								showType:"slide"
							});
							$quesDlg.dialog("close");
							subTask();
						}
					});
				}
			});
		}
		
		function subTask(){
			var data = {
				"comment" : $("#comment").val(),
				"title" :$("#title").val(),
				"result" : $result,
				"loanOrderId" : $row.loanOrderId,
				"taskId" :$row.taskId,
				"processingResult":$result=="IPCInitialAuditThrough"?"A":"B"
			}
			$.ajax({
				type : "POST",
				url : "loanOrder/loanOrderAction!submitTask.action",
				data : data,
				success : function(msg) {
					$grid.datagrid('reload');
					$taskFormDialog.dialog('close');
				}
			});
		}
	</script>
	
	<div style="width: 99.5%;height: 99.5%;overflow: auto;">
		<form action="post" style="width: auto;height: auto;">
			<table>
				<tr>
					<th colspan="2" style="text-align: left;">
						<font size="5">问题</font>
					</th>
				</tr>
				
				<tr>
					<th>
						1
					</th>
					<td>
						<textarea name="questions" class="easyui-textbox"></textarea>
					</td>
				</tr>
				
				<tr>
					<th>
						2
					</th>
					<td>
						<textarea name="questions"  class="easyui-textbox"></textarea>
					</td>
				</tr>
				
				<tr>
					<th>
						3
					</th>
					<td>
						<textarea name="questions"  class="easyui-textbox"></textarea>
					</td>
				</tr>
				
				<tr>
					<th>
						4
					</th>
					<td>
						<textarea name="questions"  class="easyui-textbox"></textarea>
					</td>
				</tr>
				
				<tr>
					<th>
						5
					</th>
					<td>
						<textarea name="questions"  class="easyui-textbox"></textarea>
					</td>
				</tr>
				
				<tr>
					<th>
						6
					</th>
					<td>
						<textarea name="questions"  class="easyui-textbox"></textarea>
					</td>
				</tr>
				
				<tr>
					<th>
						7
					</th>
					<td>
						<textarea name="questions"  name="questions"  class="easyui-textbox"></textarea>
					</td>
				</tr>
				
				<tr>
					<th>
						8
					</th>
					<td>
						<textarea name="questions"  class="easyui-textbox"></textarea>
					</td>
				</tr>
				
				<tr>
					<th>
						9
					</th>
					<td>
						<textarea name="questions"  class="easyui-textbox"></textarea>
					</td>
				</tr>
				
				<tr>
					<th>
						10
					</th>
					<td>
						<textarea name="questions"  class="easyui-textbox"></textarea>
					</td>
				</tr>
				
				<tr>
					<th>
						11
					</th>
					<td>
						<textarea name="questions"  class="easyui-textbox"></textarea>
					</td>
				</tr>
				
				<tr>
					<th>
						12
					</th>
					<td>
						<textarea name="questions"  class="easyui-textbox"></textarea>
					</td>
				</tr>
				
				<tr>
					<th>
						13
					</th>
					<td>
						<textarea name="questions"  class="easyui-textbox"></textarea>
					</td>
				</tr>
				
				<tr>
					<th>
						14
					</th>
					<td>
						<textarea name="questions"  class="easyui-textbox"></textarea>
					</td>
				</tr>
				<tr>
					<th colspan="2" align="right">
						<a href="javascript:void(0);" class="easyui-linkbutton" onclick="saveQuestions();">提交</a>
					</th>
				</tr>
				
			</table>
		</form>
	</div>
