<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/validate.js"></script>

<script type="text/javascript">		
	/** 清空新添加的理财产品的的数据**/
	function clearForm(){
		$("#consumablesAppAttachForm").form("clear");		
	}	
	/** 增加或者修改理财产品的数据**/
	function savePpeScrapAttach(){
		//校验理财产品输入的信息
   		var isValid = $("#consumablesAppAttachForm").form('validate');
		var objData = $('#consumablesAppAttachForm').serialize();
		objData = objData.replace("&appNo=&","&appNo="+$('#appNo').val()+"&");
    	if(!isValid){
    		return false;
    	}  	
    	$.ajax({
    		type: "POST",
    		url:"consumablesAppAttach/addConsumablesAppAttach.do",
    		data:objData,
    		async: false,//默认true设置下，所有请求均为异步请求
    		cache: true,
    	    success: function(iJson) {    	    	
     	    	if(iJson.status){
    	    		$("#consumablesAppAttachGrid").datagrid("load",{appNo:$('#appNo').val()});//刷新表格  
    	    		clearForm();
    	    	}
    	    	$.messager.alert(iJson.title,iJson.message,'info');
    	    }
    	});		
	}
	
	function closeWindow(){
		$('#formWindow').dialog("close"); 
	}
	
	function getQueryString(name) { 
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
		var r = window.location.search.substr(1).match(reg); 
		if (r != null) return unescape(r[2]); return null; 
	} 
	
	function changeFunc(){
		var v = $('#price').val();
		if(v<=0 || v>=9999){
			$.messager.alert("错误","请输入0~9999的数值!",'error');
		}

	}
	
	function changeFunc2(){
		var v = $('#qty').val();
		if(v<=0 || v>=999){
			$.messager.alert("错误","请输入0~999的数值!",'error');
		}
	}
	
</script>
	<div style="margin-left: 5px;margin-top: 5px;">
	   <form id="consumablesAppAttachForm" method="post">
	   <input type="hidden" id="caaId" name="caaId" value="${consumablesAppAttach.caaId}"/>
	   <input type="hidden" id="appNo" name="appNo" value="${consumablesAppAttach.appNo}"/>
	   
			<table class="table"  width="95%">
				<tr>
					<th>资产名称:</th>
					<td>
						<input id="goodsName" name="goodsName" value="${consumablesAppAttach.goodsName}"  type="text" class="easyui-textbox easyui-validatebox" data-options="required:true" maxlength="30"/>
					</td>
					
					<th>品牌规格:</th>						
					<td>
						<input id="规格型号" name="model" value="${consumablesAppAttach.model}" type="text" class="easyui-textbox easyui-validatebox" data-options="required:true" maxlength="50"/>
					</td>
				</tr>
				<tr>
					<th>单价:</th>
					<td><input id="price" name="price" value="${consumablesAppAttach.price}"  class="easyui-textbox easyui-numberbox easyui-validatebox" data-options="groupSeparator:',',required:true,min:0,max:9999" precision="2"  type="text"  onkeyup="changeFunc()"/></td>
					<th>数量:</th>
					<td><input id="qty" name="qty" value="${consumablesAppAttach.qty}"  class="easyui-textbox easyui-numberbox easyui-validatebox" data-options="required:true,min:0,max:999" type="text" onkeyup="changeFunc2()" /></td>				
				</tr>
				<tr>
					<%-- <th>合计:</th>
					<td><input id="totalAmt" name="totalAmt"  value="${consumablesAppAttach.totalAmt}"  class="easyui-textbox easyui-numberbox"  precision="2"  type="text" /></td>										 --%>
				   <td colspan="2"></td>
				   <td colspan="2" align="center">
				      <a href="javascript:void(0)" id="save" class="easyui-linkbutton" iconCls="icon-save" onclick="savePpeScrapAttach();">保存</a>
				      <a href="javascript:void(0)" id="rset" class="easyui-linkbutton" onclick="closeWindow();">关闭</a>
				   </td>
				</tr>					
			</table>
		</form>
	</div>				
