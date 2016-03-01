<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/validate.js"></script>

<script type="text/javascript">		
	$(function(){
		//加载页面时，初始化还款方式下拉列表
		$('#repaymentMode').combobox({    			    
			url:"common/commonAction!findTextArr.action?codeMyid=repayment_mode",
			valueField: 'code',
			textField: 'text'			    			    
		});
		
		//加载页面时，初始化还产品状态列表
		$('#prodStatus').combobox({    			    
			url:"common/commonAction!findTextArr.action?codeMyid=prod_status",
			valueField: 'code',
			textField: 'text'			    			    
		});	
	});		
			
	
	/** 清空新添加的理财产品的的数据**/
	function clearForm(){	
		$.messager.confirm('警告', '是否确定重置此款理财产品的信息？</br>这会将本款理财产品的信息清空!!!', function(flag) {
			if(flag){
				$("#investProductInputOrSaveForm").form("clear");								
			}
		});
	}	
	
	
	/** 增加或者修改理财产品的数据**/
	function saveInvestProductInfo(){
		//校验理财产品输入的信息
   		var isValid = $("#investProductInputOrSaveForm").form('validate');
    	if(!isValid){
    		return false;
    	} 
    	     	    	
    	var lowLendEdu = $("#lowLendEdu").val();
    	if(lowLendEdu == "" ){
    		$("#lowLendEdu").val(null);
    	}

    	var higLendEdu = $("#higLendEdu").val();
    	if(higLendEdu == "" ){
    		$("#higLendEdu").val(null);
    	}    	
    	    
    	$.ajax({
    		type: "POST",
    		url:"investProduct/investProductAction!persistenceInvestProduct.action",
    		data:$('#investProductInputOrSaveForm').serialize(),
    		async: false,//默认true设置下，所有请求均为异步请求
    		cache: true,
    	    success: function(iJson) {    	    	
     	    	if(iJson.status){
    	    		$("#prodId").val(iJson.data);//成功保存理财产品ID    	    		
    	    		$("#saveOrUpdateInvestProductDialog").dialog("close");//关闭弹窗
    	    		$("#dg").datagrid("reload");//刷新表格    	    		
    	    		/* disableForm();//禁用form */
    	    	}
    	    	parent.$.messager.show({
    				title : '提示',
    				msg : iJson.message,
    				timeout : 4000 * 2
    			}); 
    	    }
    	});		
	}
	
	
	/**根据用户填入的理财产品周期，动态的设置本页面中的年化折标系数
		年化折标系数：
			45T->0.2
			90T->0.25 1/(360/90)
			180T->0.5 1/(360/180)
			365T->1 1(/360/360)
	**/
	function decideMsfByLendingCycle(){
		var lendingCyle = $("#lendingCycle").val();		
		
		//判断用户输入是否为NULL或者空字符
		if(lendingCyle =="" || lendingCyle == null){
			$.messager.alert("提示", "请输入出借天数","warning");
			$("#lendingCycle").focus();
		}else{
			
			if(lendingCyle == '45'){
				$("#msf").val("0.2");    
			}else if(lendingCyle == '90'){
				$("#msf").val("0.25");   
			}else if(lendingCyle == '180'){
				$("#msf").val("0.5");   
			}else if(lendingCyle == '365'){
				$("#msf").val("1");   
			}else{
				$("#msf").val("");
			}			
		}
	}
	
</script>

	
	<div style="margin-left: 5px;margin-top: 5px;">
	   <form id="investProductInputOrSaveForm" method="post">
	   		<input id="prodId" name="prodId" type="hidden"/><!--理财产品的ID -->
			<table class="table" width="80%">
				<tr>
					<th>产品名称:</th>
					<td>
						<input id="prodName" name="prodName" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,100]', required:true"/>
					</td>
				    <th>出借周期(天):</th>
					<td>
						<input id="lendingCycle" name="lendingCycle" type="text" class="easyui-textbox easyui-validatebox" data-options="validType:'integer',required:true" onblur="decideMsfByLendingCycle()" onchange="decideMsfByLendingCycle()"/>
					</td>
				</tr>
				<tr>
					<th>年化收益率:</th>
					<td>
						<input id="ars" name="ars" type="text" class="easyui-textbox easyui-validatebox" data-options="validType:'intOrFloat',required:true"/>%
					</td>
					
					<th>到期收益率:</th>						
					<td>
						<input id="ytm" name="ytm" type="text" class="easyui-textbox easyui-validatebox" data-options="validType:'intOrFloat',required:true"/>%
					</td>
				</tr>
				<tr>
					<th>最低出借金额(元):</th>
					<td>
						<input id="lowLendEdu" name="lowLendEdu" type="text" class="easyui-textbox easyui-validatebox" data-options="validType:'intOrFloat',required:true"/>
					</td>
					<th>最高出借金额(元):</th>
					<td>
						<input id="higLendEdu" name="higLendEdu" type="text" class="easyui-textbox easyui-validatebox" data-options="validType:'intOrFloat'"/>
					</td>
				</tr>
				<tr>
					<th>还款方式:</th>
					<td><input id="repaymentMode" name="repaymentMode" class="easyui-combobox easyui-validatebox" data-options="panelHeight:50"/></td>
					
					<th>产品状态:</th>
					<td><input id="prodStatus" name="prodStatus" class="easyui-combobox easyui-validatebox" data-options="panelHeight:50"/></td>														
				</tr>
				<tr>
				    <th>产品描述:</th>
				    <td><input name="prodDesc" class="easyui-textbox" data-options="validType:'length[0,100]'"/></td>				    
				    <th>年化折标系数：</th>
				    <td>
						<input id="msf" name="msf" class="easyui-textbox" style="width:150px;" readonly="readonly"/>   
				    </td>			    
				</tr>
				
				<tr>
				   <td colspan="4" align="right">
				      <a href="javascript:void(0)" id="rset" class="easyui-linkbutton" iconCls="icon-reload" onclick="clearForm();">重置</a>
				      <a href="javascript:void(0)" id="save" class="easyui-linkbutton" iconCls="icon-save" onclick="saveInvestProductInfo();">保存</a>
				   </td>
				</tr>					
			</table>
		</form>
	</div>				
