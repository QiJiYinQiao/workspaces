<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/validate.js"></script>

<script type="text/javascript">		
	/** 清空新添加的理财产品的的数据**/
	var ppeTypeCodeArr=jqueryUtil.getTextArr('ppe_type_code');
	$(function(){
		//修改时 隐藏保存并新增按钮
		if($("#ppeScrapAttachForm #psaId").val() != ""){
			$("#ppeScrapAttachForm #save").remove();
		}
	});
	
	function clearForm(){
		$("#ppeScrapAttachForm").form("clear");		
	}	
	/** 增加或者修改理财产品的数据**/
	function savePpeScrapAttach(type){
		loadPpeScrapFunc();//刷新最新的资产信息
		//校验理财产品输入的信息
   		var isValid = $("#ppeScrapAttachForm").form('validate');
		var objData = $('#ppeScrapAttachForm').serialize();
		objData+="&appNo="+$('#appNo').val();
    	if(!isValid){
    		return false;
    	}  	
    	$.ajax({
    		type: "POST",
    		url:"ppeScrapAppAttachController/addPPEScrapAppAttach.do",
    		data:objData,
    		async: false,//默认true设置下，所有请求均为异步请求
    		cache: true,
    	    success: function(iJson) {    	    	
     	    	if(iJson.status){
    	    		$("#ppeScrapAttachGrid").datagrid("load",{appNo:$('#appNo').val()});//刷新表格  
    	    	}
     	    	$.messager.alert(iJson.title,iJson.message,'info');			
    	    }
    	});		
   		closeWindow();
	}
	
	function closeWindow(){
		$('#formWindow').dialog("close"); 
	}
	
	function getQueryString(name) { 
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
		var r = window.location.search.substr(1).match(reg); 
		if (r != null) return unescape(r[2]); return null; 
	} 
	
	function loadPpeScrapFunc(){
		$("#ppeNo").val($("#ppeNo").val().trim());
		var bl = false;
		if($("#ppeNo").val().length>0 && $("#ppeNo").val().trim().length>0){
			$.ajax({
	    		type: "POST",
	    		url:"ppeScrapAppAttachController/getPpeStockInfo.do",
	    		data:"ppeCode="+$("#ppeNo").val().trim()+"&ppeStatus=1,2",
	    		async: false,//默认true设置下，所有请求均为异步请求
	    		cache: true,
	    	    success: function(iJson) {    	    	
	     	    	if(null != iJson && iJson.ppeStock != null){
	     	    		$("#buyDateStr").datebox("setValue",iJson.purchaseDate);
	     	    		$("#ppeName").val(iJson.ppeStock.ppeName);//资产名称
	     	    		$("#ppeModel").val(jqueryUtil.showText(iJson.ppeStock.ppeTypeCode,ppeTypeCodeArr));//类型
	     	    		$("#ppeGross").val(iJson.ppeStock.originalValue);//原值
	     	    		$("#usedYear").val(iJson.ppeStock.useYears);//使用年限
	     	    		$("#alertStr").html("");
	     	    	}else{
	     	    		bl = true;
	     	    		$("#alertStr").html(iJson.resultStr);
	     	    	}
	    	    }
	    	});	
		}else{
			bl = true;
			$("#alertStr").html("(*请正确输入您的资产编号)");
		}
		if(bl){
    		$("#buyDateStr").datebox("setValue","");
    		$("#ppeName").val("");//资产名称
    		$("#ppeModel").val("");//类型
   			$("#ppeGross").val("");//原值
    		$("#usedYear").val("");//使用年限
		}
	}

	
	
</script>

	
	<div style="margin-left: 5px;margin-top: 5px;">
	   <form id="ppeScrapAttachForm" method="post">
	   <input type="hidden" id="psaId" name="psaId" class="easyui-textbox"  value="${ppeAttach.psaId}"/>
	   
			<table class="table"  width="95%">
				<tr>
					<th>资产编号:</th>
					<td colspan="5">
						<input id="ppeNo" name="ppeNo" value="${ppeAttach.ppeNo}" class="easyui-textbox easyui-validatebox " data-options="required:true"  onchange="loadPpeScrapFunc();"/>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<span style="color: red;" id="alertStr">(*请正确输入您的资产编号)</span>
					</td>
				</tr>
				<tr>
					<th>资产名称:</th>
					<td>
						<input id="ppeName" name="ppeName" value="${ppeAttach.ppeName}"  type="text" class="easyui-textbox easyui-validatebox easyui-textbox"  readonly="readonly" data-options="validType:'length[0,50]',required:true"/>
					</td>
					
					<th>品牌规格:</th>						
					<td colspan="3">
						<input id="ppeModel" name="ppeModel" value="${ppeAttach.ppeModel}" type="text" class="easyui-textbox easyui-validatebox easyui-textbox"  readonly="readonly" data-options="validType:'length[0,50]',required:true"/>
					</td>
				</tr>
				<tr>
					<th>购买时间:</th>
					<td>
						<input name="buyDateStr" id="buyDateStr" value="${ppeAttach.buyDate}" class="easyui-datebox easyui-textbox"   editable="false" data-options="validType:'Date'"/>
					</td>
					<th>使用年限:</th>
					<td colspan="3">
						<input id="usedYear" name="usedYear" value="${ppeAttach.usedYear}" class="easyui-textbox"  readonly="readonly"  type="text" />&nbsp;(月)
					</td>
				</tr>
				<tr>
					<th>资产原值:</th>
					<td><input id="ppeGross" name="ppeGross" value="${ppeAttach.ppeGross}"  class="easyui-textbox"  readonly="readonly" type="text" data-options="required:true" />&nbsp;(元)</td>										
				</tr>
				<tr>
					<th colspan="2">*资产报废原因(资产现状及解决方案):</th>
					<td colspan="4"></td>
				</tr>
				<tr>
					<td colspan="1"></td>
					<td colspan="5"><textarea name="scrapReson"  id="scrapReson"  class="easyui-textbox easyui-validatebox" style="width: 600px; height: 90px;" data-options="required:true,validType:'length[0,200]'">${ppeAttach.scrapReson }</textarea></td>
				</tr>
				<tr>
					<td colspan="3"></td>
				   <td colspan="3" align="right">
				      <a href="javascript:void(0)" id="saveAndClose" class="easyui-linkbutton" iconCls="icon-save" onclick="savePpeScrapAttach(1);">保存并关闭</a>
				      <a href="javascript:void(0)" id="close" class="easyui-linkbutton"  onclick="closeWindow();">关闭页面</a>
				   </td>
				</tr>					
			</table>
		</form>
	</div>				
