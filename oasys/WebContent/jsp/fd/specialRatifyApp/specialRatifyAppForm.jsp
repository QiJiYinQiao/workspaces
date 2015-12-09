<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <style>
		.resize{
			width: 500px;
			height: 50px;
			max-width: 500px;
			max-height: 50px;
			min-width: 500px;
			min-height: 50px;
		}
		.size{
			width: 400px;
			height: 70px;
			max-width: 400px;
			max-height: 70px;
			min-width: 400px;
			min-height: 70px;
		}
		.dis{
			display: none;
		}
		
	</style> 
    <form id="baseForm" method="post" style="width: 620px;height:200px;margin-left:5px;">
    <fieldset>
    <legend><img src="extend/fromedit.png" style="margin-bottom: -3px;"/>基本信息</legend>
    	  <input id="sraId" name="sraId" class="easyui-numberbox" type="hidden"/>
	      <input id="appNo" name="appNo" type="hidden"/><!-- 申请编号 -->
	      <input id="applicantNo" name="applicantNo" class="easyui-numberbox" type="hidden"/>
	      <input id="deptNo" name="deptNo" class="easyui-numberbox" type="hidden"/>
	      <input id="appDate" name="appDate" type="hidden"/>
	      <input id="procStatus" name="procStatus" type="hidden"/>
	      <input id="appStatus" name="appStatus" type="hidden"/> 
          <table  id="startIn" class="table">
             <tr>
                <th>贷款合同编号:</th>
                <td><input id="contractNo" name="contractNo" class="easyui-textbox easyui-validatebox" required="required"/></td>
             </tr>
             <tr>
                <th>特批类型:</th>
                <td><input id="speRatType" name="speRatType" class="easyui-textbox easyui-validatebox"/></td>
                <th id="hiddenOne" class="dis">申请还款日期:</th>
                <td id="hiddenTwo" class="dis"><input id="appRepaymentDate" name="appRepaymentDate" class="easyui-datebox"/></td>
             </tr>
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
});


//保存申请基本信息
function saveInvestor(){
	var isValid = $("#baseForm").form('validate');
	if(!isValid){
		return false;
	}
 	$.ajax({
		type: "POST",
		url:"specialRatifyApp/saveSpecialRatifyApp.do",
		data:$('#baseForm').serialize(),
		async: false,//默认true设置下，所有请求均为异步请求
		cache: true,
	    success: function(iJson) {
	    	if(iJson.status){
	    		$("#appNo").val(iJson.data);//
	    		parent.$.messager.alert(iJson.title,iJson.message,'info');
	    	}else{
	    		parent.$.messager.alert(iJson.title,iJson.message,'error');
	    	}
	    }
	});
}

function clearForm(){
	$("#baseForm").form('clear');
}
</script>