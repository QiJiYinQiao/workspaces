<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<style type="text/css">
	td {
		padding-bottom: 5px;
}
</style>
<script type="text/javascript" src="js/validate.js"></script>

<script type="text/javascript">	
	function loadDept(){
		$("#signDept").combogrid({    
		    panelWidth:250,  
		    mode: 'remote',   
		    idField:'id',    
		    textField:'name',    
		    url:'penaltyNoticeReg/loadDept.do',
		    columns:[[    
		        {field:'name',title:'所在部门',width:140}
		    ]]
		}); 
	}
	
	
		$('#penaltyReson').combobox({
			data:ptType,
			valueField:'id',
			textField:'text',
			editable :false,
			onSelect:function(n,o){
				if(n.text=="其他"){
					$(".table tr:eq(2)").after("<tr id='penaltyResonOtherTr'><th>其他受罚原因:</th><td colspan='4'><textarea name='penaltyResonOther' id='penaltyResonOther' class='easyui-textbox' style='width:600px;'></textarea></td></tr>");
				}else{
					$("#penaltyResonOtherTr").remove();
				}
			}
		});
	
	//加载受罚对象

		$('#ptType').combobox({
			data:fineType,
			valueField:'id',
			textField:'text',
			editable :false,
			onSelect:function(n,o){
				if(n.id==1){
					$("#pt").combogrid("setValue",null);
					$("#pt").combogrid({    
					    panelWidth:250,  
					    mode: 'remote',   
					    idField:'id',    
					    textField:'name',    
					    url:"penaltyNoticeReg/loadFineInfo.do?id="+n.id,
					    columns:[[
					        {field:'name',width:140}
					    ]]
					});	
				}else{
					$("#pt").combogrid("setValue",null);
					$("#pt").combogrid({    
					    panelWidth:250,  
					    mode: 'remote',   
					    idField:'userId',    
					    textField:'name',    
					    url:"penaltyNoticeReg/loadFineInfo.do?id="+n.id,
					    columns:[[
					        {field:'name',width:140}
					    ]]
					});	
				}
			}
		});
	
	$(function(){
		loadDept();
		if(rowdata!=null){
			$("#save").hide();
			$("#ptType").attr("disabled","disabled");
			$("#pt").attr("disabled","disabled");
			if(rowdata.ptType=="受罚部门"){
				$("#pt").combogrid({    
				    panelWidth:250,  
				    mode: 'remote',   
				    idField:'id',    
				    textField:'name',    
				    url:"penaltyNoticeReg/loadFineInfo.do?id="+1,
				    columns:[[
				        {field:'name',width:140}
				    ]]
				});	
			}else{
				$("#pt").combogrid({    
				    panelWidth:250,  
				    mode: 'remote',   
				    idField:'userId',    
				    textField:'name',    
				    url:"penaltyNoticeReg/loadFineInfo.do?id="+2,
				    columns:[[
				        {field:'name',width:140}
				    ]]
				});	
			}
		}
		if(rowdata!=null){
    		$("#signDept").combogrid('setValue',rowdata.signDept);
    		if(rowdata.ptType=="受罚部门"){
        		$("#pt").combogrid('setValue',rowdata.pt);
    		}else{
        		$("#pt").combogrid('setValue',rowdata.pt);
    		}
		}
	});
	/** 保存增加或者修改名片申请的数据**/
	function saveInvestProductInfo(index){
		//校验输入的信息
		var isValid = $("#investProductInputOrSaveForm").form('validate');
		if(!isValid){
			return false;
		} 
		if(index==1){
		//保存用车数据
			$.ajax({
				type: "POST",
				url:"penaltyNoticeReg/addFineInfo.do",
				data:$('#investProductInputOrSaveForm').serialize(),
				async: false,//默认true设置下，所有请求均为异步请求
				cache: true,
			    success: function(iJson) {    	    	
		 	    	if(iJson.status){
						$.messager.alert("提示","操作成功!","warning");
		 	    		$("#addWindow").dialog("close");
			    		$("#dg").datagrid("reload");//刷新表格    	    		
			    		/* disableForm();//禁用form */
			    	}
// 			    	parent.$.messager.show({
// 						title : '提示',
// 						msg : iJson.message,
// 						timeout : 4000 * 2
// 					}); 
			    }
			});		
		}else{
			$.ajax({
			type: "POST",
			url:"penaltyNoticeReg/addFineInfo.do",
			data:$('#investProductInputOrSaveForm').serialize(),
			async: false,//默认true设置下，所有请求均为异步请求
			cache: true,
		    success: function(iJson) {    	    	
	 	    	if(iJson.status){
		    		$("#dg").datagrid("reload");//刷新表格    	    		
		    		/* disableForm();//禁用form */
		    	}
//			    	parent.$.messager.show({
//						title : '提示',
//						msg : iJson.message,
//						timeout : 4000 * 2
//					}); 
		    }
		});	
		}
	};
	/** 清空新添加的的的数据**/
	function clearForm(){
		$("#investProductInputOrSaveForm").form("clear");		
	}
	function closeWindow(){
		$('#addWindow').dialog('close');
		$("#dg").datagrid("reload");
	}
</script>
	<div style="margin-left: 5px;margin-top: 5px;" data-options="iconCls:'icon-cstbase'">
	   <form id="investProductInputOrSaveForm"  method="post">
 	   		<input id="pnrId" name="pnrId" type="hidden"/>
<!--  	   		<input id="appNo" name=appNo type="hidden"/>理财产品的ID -->
<!--  	   		<input id="unit" name=unit type="hidden"/>理财产品的ID -->
			<table class="table" width="100%">
				<tr>
					<th>受罚对象类型:</th>
					<td id="name">
						<input name="ptType" id="ptType" class="easyui-textbox easyui-combogrid" data-options="validType:'length[0,100]', required:true"/>
					</td>
					<th>受罚对象:</th>
					<td>
						<input name="pt" id="pt" class="easyui-combogrid" data-options="validType:'length[0,100]', required:true"/>
					</td>
				</tr>
				<tr>
					<th>受罚日期:</th>
					<td>
						<input name="penaltyDate" id="penaltyDate" class="easyui-datebox" data-options="validType:'length[0,100]', required:true"/>
					</td>
					<th>罚款金额:</th>
					<td>
						<input name="penaltyAMT" id="penaltyAMT" class="easyui-textbox easyui-validatebox easyui-numberbox" data-options="validType:'intOrFloat',required:true" min="0.01" max="999" precision="2"/>
					</td>
				</tr>
				<tr>
					<th>受罚原因:</th>
					<td>
						<input name="penaltyReson" id="penaltyReson" class="easyui-textbox" data-options="validType:'length[0,100]', required:true"/>
					</td>
					<th>罚款单签发部门:</th>
					<td>
						<input name="signDept" id="signDept" class="easyui-combogrid" data-options="validType:'length[0,100]', required:true"/>
					</td>
				</tr>
<!-- 				<tr style="display: none;" id="penaltyResonOtherTr"> -->
<!-- 					<th>其他受罚原因:</th> -->
<!-- 					<td colspan="4"> -->
<!-- 						<textarea name="penaltyResonOther" id="penaltyResonOther" class="easyui-textbox" style="width:600px;"></textarea> -->
<!-- 					</td> -->
<!-- 				</tr> -->
				<tr>
					<th>备注信息:</th>
					<td colspan="3">
						<textarea id="remark" name="remark" class="easyui-textbox" style="width:620px;" maxlength="100"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2"></td>
				   <td colspan="2">
				      <a href="javascript:void(0)" id="save" class="easyui-linkbutton" iconCls="icon-save" onclick="saveInvestProductInfo(0);">保存并继续添加</a>
				      <a href="javascript:void(0)" id="saveAndClose" class="easyui-linkbutton" iconCls="icon-save" onclick="saveInvestProductInfo(1);">保存并关闭</a>
				      <a href="javascript:void(0)" id="close" class="easyui-linkbutton"  onclick="closeWindow();">关闭页面</a>
				   </td>
				</tr>					
			</table>
		</form>
	</div>	