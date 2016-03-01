<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <style>
.resize {
	width: 700px;
	height: 50px;
	max-width: 700px;
	max-height: 50px;
	min-width: 700px;
	min-height: 50px;
}

.size {
	width: 400px;
	height: 70px;
	max-width: 400px;
	max-height: 70px;
	min-width: 400px;
	min-height: 70px;
}

.dis {
	display: none;
}

.table {
	background-color: transparent;
	border-collapse: collapse;
	border-spacing: 0;
	max-width: 100%;
}

.table td {
	padding: 6px;
}

.table th {
	text-align: right;
	padding: 6px;
}
</style> 
    <form id="baseForm" method="post" style="width: 870px;height:350px;margin-left:5px;">
    <fieldset>
    <legend><img src="extend/fromedit.png" style="margin-bottom: -3px;"/>基本信息</legend>
    	  <input id="btaId" name="btaId" class="easyui-numberbox" type="hidden"/>
	      <input id="appNo" name="appNo" type="hidden"/><!-- 申请编号 -->
	      <input id="applicantNo" name="applicantNo" class="easyui-numberbox" type="hidden"/>
	      <input id="deptNo" name="deptNo" class="easyui-numberbox" type="hidden"/>
	      <input id="deptName" name="deptName" type="hidden"/>
	      <input id="position" name="position" type="hidden"/>
	      <input id="appDate" name="appDate" type="hidden"/>
	      <input id="procStatus" name="procStatus" type="hidden"/>
	      <input id="appStatus" name="appStatus" type="hidden"/> 
          <table  id="startIn" class="table">
             <tr>
                <th>出差事由:</th>
                <td><input id="btReason" name="btReason" class="easyui-textbox easyui-validatebox" required="required"/></td>
                <th>职务代理人:</th>
                <td><input id="agentNo" name="agentNo" class="easyui-textbox" editable="false"/></td>
             </tr>
             <tr>
		        <th>出差始发地:</th>
                <td><input id="btOrig" name="btOrig" class="easyui-textbox easyui-validatebox" required="required"/></td>
                <th>出差目的地:</th>
                <td><input id="btDest" name="btDest" class="easyui-textbox easyui-validatebox" required="required"/></td>
             </tr>
		     <tr>
		        <th>计划出差开始时间:</th>
                <td><input id="planBgDtime" name="planBgDtime" class="easyui-datetimebox" editable="false" required="required"/></td>
                <th>计划出差结束时间:</th>
                <td><input id="planEdDtime" name="planEdDtime" class="easyui-datetimebox" editable="false"  required="required"/></td>
                <!-- <th>计划出差天数:</th>
                <td><input id="planBtDays" name="planBtDays" class="easyui-textbox easyui-numberbox easyui-validatebox" required="required" data-options="min:0,max:9999"/></td> -->
             </tr>
             <tr>
		        <th>交通工具:</th>
                <td><input id="vehicle" name="vehicle" class="easyui-textbox" editable="false" required="required"/></td>
                <th id="hiddenOne" class="dis">其它交通工具:</th>
                <td id="hiddenTwo" class="dis"><input id="vehicleOther" name="vehicleOther" class="easyui-validatebox easyui-textbox"/></td>
                <input name="btaId" type="hidden"/>
                <input name="appNo" type="hidden"/>
             </tr>
             <!-- <tr>
		        <th>实际出差开始时间:</th>
                <td><input id="realBgDtime" name="realBgDtime" class="easyui-textbox easyui-datebox"/></td>
                <th>实际出差结束时间:</th>
                <td><input id="realEdDtime" name="realEdDtime" class="easyui-textbox easyui-datebox"/></td>
                <th>实际出差天数:</th>
                <td><input id="realBtDays" name="realBtDays" class="easyui-textbox easyui-numberbox easyui-validatebox" data-options="min:0,max:9999"/></td>
             </tr> -->
             <!-- <tr>
                <th>单据张数:</th>
                <td><input id="bills" name="bills" class="easyui-textbox easyui-numberbox" data-options="min:0,max:9999"/></td>
                <th>合计金额:</th>
                <td><input id="total" name="total" class="easyui-textbox easyui-numberbox" data-options="min:0,max:999999,precision:2,groupSeparator:','"/></td>
                <th>补贴金额:</th>
                <td><input id="subsidyAmt" name="subsidyAmt" class="easyui-textbox easyui-numberbox" data-options="min:0,max:999999,precision:2,groupSeparator:','"/></td>
             </tr> -->
		     <tr>
		         <th class="textStyle">备注:</th>
		         <td colspan="5">
		            <textarea name="remark" rows="5" cols="20" class="easyui-textbox resize" data-options="validType:'length[0,200]'"></textarea>
		         </td>
		       </tr>
             <tr>
	             <td colspan="6" style="text-align: right;">
	                <a href="javascript:void(0)" id="rsetId" class="easyui-linkbutton" iconCls="icon-reload" onclick="clearForm();">重置</a>
	                <a href="javascript:void(0)" id="saveId" class="easyui-linkbutton" iconCls="icon-save" onclick="saveInvestor();">保存</a>
	             </td>
             </tr>
          </table>
    </fieldset>
    </form>

<script type="text/javascript">
$(function(){
	/* var beginTime="";
	var endTime="";
	$("#planBgDtime").datetimebox({
		required:true, 
	    onSelect: function(date){
	    	var today = new Date()
	    	today.setHours(0,0,0)
	    	if(date<today){
	    		$.messager.alert("提示","请修改日期",'warning');
	    	}else{
	   		beginTime = $('#planBgDtime').datetimebox('getValue');
	    	if(endTime!=""){
	    		$.ajax({
	    			type: "POST",
	    			url:"businessTripApp/sumDays.do",
	    			data:{"beginTime":beginTime,"endTime":endTime},
	    		    success: function(iJson) {
	    		    	if(iJson.status){
	    		    		alert(iJson.data);
	    		    		if(iJson.data<=0){
	    		    			$.messager.alert("提示","请修改日期",'warning');
	    		    		}else{
	    						$("#planBtDays").numberbox('setValue',iJson.data);
	    		    		}
	    		    	}
	    		    }
	    		});
	    	}
	    		
	    	}
	    }
	});
	$("#planEdDtime").datetimebox({
		required:true, 
	    onSelect: function(date){
	   		endTime = $('#planEdDtime').datetimebox('getValue');
	    	if(beginTime!=""){
	    		$.ajax({
	    			type: "POST",
	    			url:"businessTripApp/sumDays.do",
	    			data:{"beginTime":beginTime,"endTime":endTime},
	    		    success: function(iJson) {
	    		    	if(iJson.status){
	    		    		if(iJson.data<=0){
	    		    			$.messager.alert("提示","请修改日期",'warning');
	    		    		}else{
	    						$("#planBtDays").numberbox('setValue',iJson.data);
	    		    		}
	    		    	}
	    		    }
	    		});
	    	}
	    }
	}); */
});


//保存申请基本信息
function saveInvestor(){
	
	var begin=$("#planBtDays").val();
	var isValid = $("#baseForm").form('validate');
	if(!isValid){
		return false;
	}
	
//	alert($("#startIn").find("input").length);
	$("#startIn").find("input").each(function(){ 
		var name=$(this).attr("name");
		if(name != undefined && name!="btReason" && name!="agentNo"){
			var inputName="businessTripAttach."+name;
			$(this).attr({name:inputName});
		//	alert($(this).attr("name")+"--"+$(this).val());
		}
	});
//	alert("-----------");
 	$.ajax({
		type: "POST",
		url:"businessTripApp/saveBusinessTripApp.do",
		data:$('#baseForm').serialize(),
		async: false,//默认true设置下，所有请求均为异步请求
		cache: true,
	    success: function(iJson) {
	    	if(iJson.status){
	    		$("#appNo").val(iJson.data);//
	    		parent.$.messager.alert(iJson.title,iJson.message,'warning');
	    	}else{
	    		parent.$.messager.alert(iJson.title,iJson.message,'error');
	    	}
	    }
	});
}


function createUser(formId){
	$("#"+formId+" input[name='agentNo']").combogrid({    
	    panelWidth:250,  
	    mode: 'remote',   
	    idField:'userId',    
	    textField:'name', 
	    required:true,
	    url:'empDimissionTakeoverInfoController/createCombogrid.do',
	    columns:[[    
	        {field:'name',title:'用户名',width:120},    
	        {field:'fullName',title:'所在部门',width:90}   
	    ]],
		/* onSelect: function(index,row){ 
			$("#"+formId+" input[name='takeoverDeptName']").val(row.fullName);
			$("#"+formId+" input[name='takeoverDept']").val(row.organizeId);
			
		} */
	}); 
}

function clearForm(){
	$("#baseForm").form('clear');
}
</script>