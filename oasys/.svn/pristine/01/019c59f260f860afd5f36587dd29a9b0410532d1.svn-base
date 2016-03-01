<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
$(function(){
	createSelect();
	fillInForm();
});
//编辑时，填充form表单
function fillInForm(){
	if($row!=null){
		$("#expensesAppForm").form('load',$row);
		if($row.payMode == '2'){
			$("#tr1").show();
			$("#tr2").show();
		}
		if($row.billType == '3'){
			$("#tr3").show();
		}
	}
}
//渲染下拉框
function createSelect(){
	//费用类型
   $("#expType").combobox({
		url:"commonController/findDicList.do?codeMyid=cost_type",
		valueField: 'code',
		textField: 'text'
   });
	//支付方式
   $("#payMode").combobox({
		url:"commonController/findDicList.do?codeMyid=pay_mode",
		valueField: 'code',
		textField: 'text',
		onSelect:function(record){
			if(record.code=='2'){
				$("#tr1").show();
				$("#tr2").show();
			}else if(record.code=='1'){
				$("#tr1").hide();
				$("#tr2").hide();
			}else{
			}
		},
		onLoadSuccess:function(){
			var val = $(this).combobox("getData");
			if($row != null){//说明是编辑
				 $(this).combobox("select", $row.payMode);
			}else{
				if(!$.isEmptyObject(val)){
	                $(this).combobox("select", val[0]["code"]);
				} 	
			}
		}
   });
	//票据类型
   $("#billType").combobox({
		url:"commonController/findDicList.do?codeMyid=bill_type",
		valueField: 'code',
		textField: 'text',
		onSelect:function(record){
			if(record.code=='3'){
				$("#tr3").show();
			}else{
				$("#tr3").hide();
			}
		},
		onLoadSuccess : function(){
			var val = $(this).combobox("getData");
			if($row != null){//说明是编辑
				 $(this).combobox("select", $row.billType);
			}else{
				if(!$.isEmptyObject(val)){
	                $(this).combobox("select", val[0]["code"]);
				} 	
			}
		}
  });
}

</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="">
		<div id="tt" class="easyui-tabs" border="false">
		   <div title="费用申请信息" data-options="iconCls:'icon-cstbase'" style="padding: 10px">
		      <form id="expensesAppForm" novalidate="true">
		         <input name="btaId" type="hidden"/>
		         <input name="appNo" type="hidden"/>
		         <input name="applicantNo" type="hidden"/>
		         <input name="deptNo" type="hidden"/>
		         <input name="appStatus" type="hidden"/>
		         <input name="procStatus" type="hidden"/>
		         <input name="appDate" type="hidden"/>
		         <table class="table">
		           <tr>
		              <th>费用类型:</th>
		              <td><input id="expType" name="expType" class="easyui-textbox easyui-validatebox" editable="false" panelHeight="auto"/></td>
		              <th>单价:</th>
		              <td><input name="price" class="easyui-textbox easyui-validatebox easyui-numberbox" data-options="min:0,precision:2,max:999999.99,missingMessage:'单价范围为0.00-999999.99',groupSeparator:','"/>(元)</td>
		           </tr>
		           <tr>
		              <th>规格型号:</th>
		              <td><input name="model" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,40]'"/></td>
		              <th>申请数量:</th>
		              <td><input name="appQty" class="easyui-textbox easyui-validatebox easyui-numberbox" data-options="min:0,max:9999"/></td>
		           </tr>
		           <tr>
		              <th width="25%">申请金额:</th>
		              <td><input name="appAmt" class="easyui-textbox easyui-validatebox easyui-numberbox" data-options="required:true,min:0,precision:2,max:999999.99,missingMessage:'单价范围为0.00-999999.99',groupSeparator:','"/>(元)</td>
		              <th>支付方式:</th>
		              <td><input id="payMode" name="payMode" class="easyui-textbox easyui-validatebox" editable="false" panelHeight="auto"/></td>
		           </tr>
		           <tr id="tr1" style="display: none;">
		              <th>银行名称:</th>
		              <td><input name="bankName" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,40]'" /></td>
		              <th>转入账号:</th>
		              <td><input name="intoAct" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,25]'"/></td>
		           </tr>
		           <tr id="tr2" style="display: none;">
		              <th>账户名称:</th>
		              <td colspan="3"><input name="actName" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,7]'"/></td>
		           </tr>
		           <tr>
		              <th>现有数量:</th>
		              <td><input name="alreadyQty" class="easyui-textbox easyui-validatebox easyui-numberbox" data-options="min:0,max:99999"/></td>
		              <th>票据类型:</th>
		              <td><input id="billType" name="billType" class="easyui-textbox easyui-validatebox" editable="false" panelHeight="auto"/></td>
		           </tr>
		           <tr id="tr3" style="display: none;">
		              <th>其他票据<br/>类型名称:</th>
		              <td colspan="3"><input name="billTypeOther" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,10]'"/></td>
		           </tr>
		           <tr>
		              <th>费用用途:</th>
		              <td colspan="3">
		                  <textarea name="expReson" rows="5" cols="20" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'length[0,200]'" style="width: 510px;height: 50px;"></textarea>
		              </td>
		           </tr>
		           <tr>
		              <th>备注:</th>
		              <td colspan="3">
		                 <textarea name="remark" rows="5" cols="20" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,200]'" style="width: 510px;height: 50px;"></textarea>
		              </td>
		           </tr>
		         </table>
		      </form>
		   </div>
		</div>
	</div>
</div>
