<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<style type="text/css">
	td {
		padding-bottom: 5px;
}
</style>
<script type="text/javascript" src="js/validate.js"></script>

<script type="text/javascript">	
	function loadUser(){
		$("#user").combogrid({    
		    panelWidth:250,  
		    mode: 'remote',   
		    idField:'userId',    
		    textField:'name',    
		    url:'callingCard/getUserInfo.do?q='+$("#user").val(),
		    columns:[[    
		        {field:'name',title:'用户名',width:120},    
		        {field:'fullName',title:'所在部门',width:90}
		    ]],
		    onChange:function(n,o){
		    	$.ajax({
		    		type: "POST",
		    		url:"callingCard/loadUserInfo.do?q="+n,
		    		async: false,//默认true设置下，所有请求均为异步请求
		    		cache: true,
		    	    success: function(iJson) {
		     	    	$("#dept").val(iJson.fullName);
		    	    }
		    	});		
		    }
		}); 
	}
	
	function createSelect(){
		$("#dept").combotree({
			width:171,
			url:"Organization/organizationList.do",
			idFiled:'id',
		 	textFiled:'name',
		 	valueFiled:'id',
		 	parentField:'pid',
		 	onLoadSuccess:function(data){
		 	},
		 	onChange:function(){
		 		$("#user").combogrid('setValue',null);
		 		$("#dept").val($(this).combotree('getValue'));
// 		 	    RenderName($(this).combotree('getValue'));
		 	   $("#user").combogrid({    
				    panelWidth:250,  
				    mode: 'remote',   
				    idField:'code',    
				    textField:'text',    
				    url:'callingCard/getUserInfo.do?q='+$("#dept").val(),
				    columns:[[    
				        {field:'text',title:'用户名',width:120}
				    ]]
				}); 
		 	}
		});
	}
	
	$(function(){
		if(rowdata!=null){
			$("#save").hide();
		}
		createSelect()
	});
	/** 保存增加或者修改名片申请的数据**/
	function saveInvestProductInfo(index){
		$('#totalAMT').removeAttr("disabled"); 
		//校验输入的信息
		var isValid = $("#investProductInputOrSaveForm").form('validate');
		if(!isValid){
			return false;
		} 
		if(index==1){
			//保存用车数据
			$.ajax({
				type: "POST",
				url:"vehicleUsesReg/addCarInfo.do",
				data:$('#investProductInputOrSaveForm').serialize(),
				async: false,//默认true设置下，所有请求均为异步请求
				cache: true,
			    success: function(iJson) {
		 	    	if(iJson.status){
						$.messager.alert("提示","操作成功!","warning");
		 	    		$("#addWindow").dialog("close");
			    		$("#dg").datagrid("reload");//刷新表格    	
			    	}
			    }
			});		
		}else{
			$.ajax({
			type: "POST",
			url:"vehicleUsesReg/addCarInfo.do",
			data:$('#investProductInputOrSaveForm').serialize(),
			async: false,//默认true设置下，所有请求均为异步请求
			cache: true,
		    success: function(iJson) {    	    	
	 	    	if(iJson.status){
		    		$("#dg").datagrid("reload");//刷新表格    	    		
		    		/* disableForm();//禁用form */
		    	}
// 		    	parent.$.messager.show({
// 					title : '提示',
// 					msg : iJson.message,
// 					timeout : 4000 * 2
// 				}); 
		    }
		});		
		}
	};
	/** 清空新添加的的的数据**/
	function clearForm(){
		$("#investProductInputOrSaveForm").form("clear");		
	}	
	
	function sumAcount(){
		$("#totalAMT").val(function(){
			return Number($("#fuelCharge").val())+Number($("#roadToll").val())+Number($("#parkingFee").val());
		});
	}
	function closeWindow(){
		$('#addWindow').dialog('close');
		$("#dg").datagrid("reload");
	}
</script>
	<div style="margin-left: 5px;margin-top: 5px;" data-options="iconCls:'icon-cstbase'">
	   <form id="investProductInputOrSaveForm"  method="post">
 	   		<input id="vurId" name="vurId" type="hidden"/>
  	   		<input id="appNo" name=appNo type="hidden"/><!--理财产品的ID   -->
  	   		<input id="unit" name=unit type="hidden"/><!--理财产品的ID -->
			<table class="table" width="100%">
				<tr>
					<th>部门:</th>
					<td>
						<input name="dept" id="dept" class="easyui-textbox" readonly="true" data-options="validType:'length[0,100]', required:true"/>
					</td>
					<th>使用人:</th>
					<td id="name">
						<input name="user" id="user" class="easyui-textbox easyui-combogrid" data-options="validType:'length[0,100]', required:true"/>
					</td>
				</tr>
				<tr>
					<th>车牌号码:</th>
					<td>
						<input name="carNo" id="carNo" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,100]', required:true"/>
					</td>
					<th>使用事由:</th>
					<td>
						<input name="usesReson" id="usesReson" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,100]', required:true"/>
					</td>
				</tr>
				<tr>
 					<th>用车时间:</th>
 					<td>
  						<input name="usesDateTime" id="usesDateTime" class="easyui-datetimebox" data-options="validType:'length[0,100]', required:true"/>
  					</td>
					<th>始发地:</th>
					<td>
						<input name="origin" id="origin" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,100]', required:true"/>
					</td>
				</tr>
				<tr>
 					<th>目的地:</th>
 					<td> 
 						<input id="destination" name="destination" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,100]', required:true"/> 
 					</td><th>启程公里数:</th>
 					<td> 
 						<input id="bgKilometer" name="bgKilometer" type="text" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,100]', required:true"/> 
 					</td> 
 				</tr> 
 				<tr>
 					<th>结束公里数:</th>
 					<td> 
 						<input id="edKilometer" name="edKilometer" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,100]', required:true"/> 
  					</td><th>归还时间:</th>
  					<td>
  						<input id="gvDateTime" name="gvDateTime" type="text" class="easyui-datetimebox" data-options="validType:'length[0,100]', required:true"/>
  					</td>
 				</tr> 
 				<tr> 
 					<th>加油费:</th> 
 					<td> 
 						<input id="fuelCharge" name="fuelCharge" class="easyui-textbox easyui-numberbox" onblur="sumAcount()" min="0.01" max="999" precision="2"/> 
 					</td><th>路桥费:</th> 
 					<td> 
 						<input id="roadToll" name="roadToll" type="text" class="easyui-textbox easyui-numberbox" onblur="sumAcount()" min="0.01" max="999" precision="2"/> 
 					</td> 
 				</tr> 
 				<tr> 
 					<th>停车费:</th> 
 					<td> 
 						<input id="parkingFee" name="parkingFee" class="easyui-textbox easyui-numberbox" onblur="sumAcount()" min="0.01" max="999" precision="2"/> 
 					</td><th>合计金额:</th> 
 					<td> 
 						<input id="totalAMT" name="totalAMT" type="text" class="easyui-textbox" min="0.01" max="999" precision="2" readonly="true"/> 
 					</td> 
 				</tr> 
 				<tr> 
 					<th>备注信息:</th> 
 					<td colspan="3"> 
 						<textarea id="remark" name="remark" class="easyui-textbox" style="width: 637px;"></textarea> 
 					</td> 
 				</tr> 
				<tr>
					<td colspan="2"></td>
				   <td colspan="2">
				      <!-- <a href="javascript:void(0)" id="rset" class="easyui-linkbutton" iconCls="icon-reload" onclick="clearForm();">重置</a>
				      <a href="javascript:void(0)" id="save" class="easyui-linkbutton" iconCls="icon-save" onclick="saveInvestProductInfo();">保存</a> -->
				       <a href="javascript:void(0)" id="save" class="easyui-linkbutton" iconCls="icon-save" onclick="saveInvestProductInfo(0);">保存并继续添加</a>
				      <a href="javascript:void(0)" id="saveAndClose" class="easyui-linkbutton" iconCls="icon-save" onclick="saveInvestProductInfo(1);">保存并关闭</a>
				      <a href="javascript:void(0)" id="close" class="easyui-linkbutton"  onclick="closeWindow();">关闭页面</a>
				   </td>
				</tr>					
			</table>
		</form>
	</div>	