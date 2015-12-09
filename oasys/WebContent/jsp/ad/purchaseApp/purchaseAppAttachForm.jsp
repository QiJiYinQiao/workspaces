<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<script type="text/javascript">
	$(function(){
		//如果是编辑就填充form
		fullForm();
		//渲染下拉表格
		createUser('form_0');
		//渲染保管人下拉列表
		createDepositary('form_0');
		//下拉框
		//createSelect();
	})
	function fullForm(){
		if($rowdata!=null){
			$("#form_0").form("load",$rowdata);
			$("#form_0 input[name^='oldTotalAmt']").val($rowdata.totalAmt);
		}
	}
	//渲染使用人下拉列表
	function createUser(formId){
		$("#user").combogrid({    
		    panelWidth:250,  
		    mode: 'remote',   
		    idField:'userId',    
		    textField:'account',    
		    url:'purchaseAppAttachController/createCombogrid.do',
		    columns:[[    
		        {field:'account',title:'用户名',width:120},    
		        {field:'fullName',title:'所在部门',width:90}   
		    ]]
		}); 
	}
	//渲染保管人下拉列表
	function createDepositary(formId){
		$("#depositary").combogrid({    
		    panelWidth:250,  
		    mode: 'remote',   
		    idField:'userId',    
		    textField:'account',    
		    url:'purchaseAppAttachController/createCombogrid.do',
		    columns:[[    
		        {field:'account',title:'用户名',width:120},    
		        {field:'fullName',title:'所在部门',width:90}  
		    ]]
		}); 
	}
	//物品名称
	$("#articleName").combobox({
		url:'commonController/findDicList.do?codeMyid=article_name',    
	    valueField:'code',    
	    textField:'text',
	    onLoadSuccess:function(){
	    	if($rowdata == null){
	    		var data = $(this).combobox('getData');
		    	if(data.length > 0){
		    		$(this).combobox('setValue',data[0].code);
		    		getModel("article_name",data[0].code);
		    	} 
	    	} 
	    },
        onChange:function(newValue,oldValue){
        	getModel("article_name",newValue);
	    }
	});
	//单位
	$("#unit").combobox({
		url:'commonController/findDicList.do?codeMyid=unit_type',    
	    valueField:'code',    
	    textField:'text',
	    onSelect:function(record){
	    	if(record.code == '0'){
	    		$("#trOne").show();
	    	}else{
	    		$("#trOne").hide();
	    	}
	    },
	    onLoadSuccess:function(){
	    	var val = $(this).combobox('getData');
	    	if($rowdata != null && $rowdata.unit == '0'){//说明是编辑
	    		$("#trOne").show();
	    	}
	    }
	});
	
	//获取型号规格
	function getModel(codeMyid,parentId){
		$.ajax({
			type:'POST',
			url:'commonController/findDicByParentId.do',
			data:{'codeMyid':codeMyid,'parentId':parentId},
			dataType:'JSON',
			success:function(data){
				if(data!=null){
					$("#model").val(data.text);
				}
			}
		});
	}
	</script>
     <form id="form_0" style="width: 98%;margin-left:5px;">
	    <fieldset>
		    <legend><img src="extend/fromedit.png" style="margin-bottom: -3px;"/>物料信息</legend>
		    <input name="appNo" type="hidden"/><!-- 申请编号 -->
		    <input name="psaId" type="hidden"/><!-- 物料申请附加表id -->
		    <input name="oldTotalAmt" type="hidden"/><!-- 旧金额  -->
		    <table class="table">
		       <tr>
		          <th class="textStyle">物品名称:</th>
		          <td><input id="articleName" name="articleName" class="easyui-textbox" editable="false"/></td>
		          <th class="textStyle">型号规格:</th>
		          <td><input id="model" name="model" class="easyui-textbox easyui-validatebox" readonly="readonly" data-options="validType:'length[0,25]'"/></td>
		       </tr>
		       <tr>
		          <th class="textStyle">单价:</th>
		          <td><input id="price" name="price" class="easyui-textbox easyui-validatebox easyui-numberbox" data-options="required:true,min:0,max:999999,precision:2,groupSeparator:','" onkeyup="sumPrice(this.value,'qty',this);"/>(元)</td>
		          <th class="textStyle">数量:</th>
		          <td><input id="qty" name="qty" class="easyui-textbox easyui-validatebox easyui-numberbox" data-options="required:true,max:9999" onkeyup="sumPrice(this.value,'price',this);"/></td>
		       </tr>
		       <tr>
		          <th class="textStyle">合计价格(元):</th>
		          <td><input id="totalAmt" name="totalAmt" class="easyui-textbox  easyui-numberbox" data-options="min:0,precision:2,groupSeparator:','" disabled="disabled"/>(元)</td>
		          <th>单位:</th>
		          <td><input id="unit" name="unit" class="easyui-textbox" editable="false" panelHeight="auto"/></td>
		       </tr>
		       <tr id="trOne" style="display: none;">
		          <th>其他单位:</th>
		          <td colspan="3"><input name="unitOther" class="easyui-textbox"/></td>
		       </tr>
		       <tr>
		          <th class="textStyle">使用人:</th>
		          <td><input id="user" name="user" class="easyui-textbox easyui-combogrid"/></td>
		          <th class="textStyle">保管人:</th>
		          <td><input id="depositary" name="depositary" class="easyui-textbox easyui-combogrid"/></td>
		       </tr>
		       <tr>
	              <th class="textStyle">用途:</th>
		          <td colspan="3"><input name="purpose" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'length[0,100]'"/></td>
		       </tr>
		       <tr>
		         <th class="textStyle">备注:</th>
		         <td colspan="3">
		            <textarea name="remark" rows="5" cols="20" class="easyui-textbox easyui-validatebox" maxlength="100" style="width: 450px;height: 70px;"></textarea>
		         </td>
		       </tr>
		    </table>
	    </fieldset>
    </form>
    