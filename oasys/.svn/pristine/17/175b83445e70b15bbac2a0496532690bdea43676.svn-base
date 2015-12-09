<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/validate.js"></script>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
	
	$(function(){
		
		$("#appDate").datebox({required:true})
		
	});
	

	function toSaveBaseInfo(typt){
		//保存并继续添加
		if(typt==0){
			$.messager.confirm('确定','是否保存数据并继续添加？',	function(flag) {
				if (flag) {
					saveOrDepBadge(typt);
					}
			});
		}
		//保存并关闭
		if(typt==1){
			$.messager.confirm('确定','是否保存数据并关闭页面？',	function(flag) {
				if (flag) {
					saveOrDepBadge(typt);
					}
			});
		}
	} 
	//保存数据
	function saveOrDepBadge(typt){
		var isValid = $("#ppeUsedConfirmAtt").form('validate');
	    	if(!isValid){
	    		return false;
	    	}
		
		$.ajax({
			cache:true,
			type:'POST',
			url:"PpeUsedConfirm/saveOrUpdPpeusedConAtt.do",
			data:$('#ppeUsedConfirmAtt').serialize(),
			async:false,
			dataType:'JSON',
			success:function(res){
				 if(res.status){
					if(typt==1){
						ppeUsedAttGrid(res.data);
						$addOrEditDialog.dialog('destroy');
					}else{
						ppeUsedAttGrid(res.data)
						$("#ppeUsedConfirmAtt").form('clear');//清空表单
						$("#appNoAtt").val(res.data);
						$("#useNatureAtt").val($("#ppeUsedFrom :radio:checked").val());
					} 
					
					$.messager.alert(res.title, res.message,"info")
				}else{
					$.messager.alert(res.title, res.message,"error")
				} 
			}
		});
	}

	//根据固定资产编号，查询固定资产名称
	$("#ppeNo").change(function(){
		$.ajax({
			cache:true,
			type:'POST',
			url:"PpeUsedConfirm/findPpeStock.do",
			data:{
				"ppeNo":$("#ppeNo").val()
			},
			async:false,
			dataType:'JSON',
			success:function(res){
				 if(res!=null && res!=""){
					$("#ppeName").val(res);				
				}else{
					$.messager.alert("提示","该固定资产不存在！","error")
				} 
			}
		});
		
	});
	
	// 用户名的下拉
	function RenderAttName(organizeId){
		//选中部门后下拉框
		
		$("#reverter").combobox({
			width:171,
			url:"PpeUsedConfirm/findUserList.do?organizeId="+organizeId,
			valueField:'code',
		 	textFiled:'text',
		 	onLoadSuccess:function(data){
		 		//加一个全部
		 		
		 	},
		}); 
	}
	

</script>
<div id="other" >
	<div title="申请固定资产详情" >
	  <div class="well well-small" style="margin:5px;">
	     <form id="ppeUsedConfirmAtt" >
	     	 <input id="psaId" name="psaId" type="hidden"/><!-- 申请id -->
	     	 <input id="appNoAtt" name="appNo" type="hidden"/><!-- 申请编号 -->
	         <input id="givebackDate" name="givebackDate" type="hidden"/><!-- 归还日期 -->
	         <input id="useNatureAtt" name="useNature" type="hidden"/><!-- 使用性质 -->
	         <table class="table">
				 <tr>
				 	<th>固定资产编号：</th>
				 	<td style="width: 180">
				 		<input name="ppeNo" id="ppeNo" type="text" class="easyui-textbox  easyui-validatebox"  style="width: 170px" data-options="required:true"/>
				 	</td>
				 	<th>固定资产名称：</th>
				 	<td>
				 		<input name="ppeName" id="ppeName" type="text" class="easyui-textbox  easyui-validatebox"  style="width: 170px"  data-options="required:true"/>
				 	</td>
				 </tr>
				 
				 <tr>
				 	<th>申请使用日期：</th>
				 	<td>
						<input id="appDate" name="appDate" placeholder="请选择申请使用日期" class="easyui-textbox easyui-datebox easyui-validatebox" data-options="editable:false" />
					</td>
				 </tr>
				 
				 <tr>
					<th>使用用途:</th>
					<td colspan="3">
					  <textarea id="usedReson" name="usedReson" class="easyui-textbox easyui-validatebox" style="width: 500px; height: 60px;resize:none;" data-options="validType:'length[0,100]'"></textarea>
					</td>
				</tr>
				 <tr>
					<th>备注信息:</th>
					<td colspan="3">
					  <textarea id="remarkAtt" name="remark" class="easyui-textbox easyui-validatebox" style="width: 500px; height: 60px;resize:none;" data-options="validType:'length[0,100]'"></textarea>
					</td>
				</tr>
				
				<tr>
				   <td colspan="6" style="text-align: right;">
				   	  <a href="javascript:void(0)" id="save" class="easyui-linkbutton" iconCls="icon-save" onclick="toSaveBaseInfo(0);">保存并继续添加</a>
				      <a href="javascript:void(0)" id="rset" class="easyui-linkbutton" iconCls="icon-no" onclick="toSaveBaseInfo(1);">保存并关闭</a>
				   </td>
				</tr>
	         </table>
	     </form>
	  </div>
	</div>
</div>
