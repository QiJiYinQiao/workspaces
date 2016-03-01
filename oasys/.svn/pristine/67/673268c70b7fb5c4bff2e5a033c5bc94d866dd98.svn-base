<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<script type="text/javascript">
	$(function(){
		//如果是编辑就填充form
		fullForm();
	})
	function fullForm(){
		if($rowdata!=null){
			$("#form_0").form("load",$rowdata);
			$("#form_0 input[name^='oldTotalAmt']").val($rowdata.totalAmt);
		}
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
		          <td><input id="price" name="price" class="easyui-textbox easyui-validatebox easyui-numberbox" data-options="required:true,min:0,max:999999.99,precision:2,missingMessage:'单价范围为0.00-999999.99',groupSeparator:','" onkeyup="sumPrice();"/>(元)</td>
		          <th class="textStyle">数量:</th>
		          <td><input id="qty" name="qty" class="easyui-textbox easyui-validatebox easyui-numberbox" data-options="required:true,max:999,min:0,precision:0,missingMessage:'数量范围为0-999'" onkeyup="sumPrice();"/></td>
		       </tr>
		       <tr>
		          <th class="textStyle">合计价格:</th>
		          <td><input id="totalAmt" name="totalAmt" class="easyui-textbox  easyui-numberbox" data-options="min:0,precision:2,groupSeparator:','" disabled="disabled"/>(元)</td>
		          <th>单位:</th>
		          <td><input id="unit" name="unit" class="easyui-textbox" editable="false" panelHeight="auto"/></td>
		       </tr>
		       <tr id="trOne" style="display: none;">
		          <th>其他单位:</th>
		          <td colspan="3"><input name="unitOther" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,20]'"/></td>
		       </tr>
		       <tr>
	              <th class="textStyle">用途:</th>
		          <td colspan="3">
		            <textarea name="purpose" rows="5" cols="20" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,200]'" style="width: 450px;height: 70px;resize:none;"></textarea>
		          </td>
		       </tr>
		       <tr>
		         <th class="textStyle">备注:</th>
		         <td colspan="3">
		            <textarea name="remark" rows="5" cols="20" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,200]'" style="width: 450px;height: 70px;resize:none;"></textarea>
		         </td>
		       </tr>
		    </table>
	    </fieldset>
    </form>
    