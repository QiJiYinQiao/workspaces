<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
//申请贷款信息
function toSaveLoanInfo(){
	if(!checkIsSaveLoaner()) return false;
	if(!$("#loanInfo").form('validate'))return false;
	var loanOrderId = $("#loanOrderId").val();
	$.ajax({
		cache: true,
		type: "POST",
		url:"loanOrder/loanOrderAction!saveLoanInfo.action?loanOrderId="+loanOrderId,
		data:$('#loanInfo').serialize(),
		async: false,
	    error: function(request) {
	        alert("Connection error");
	    },
	    success: function(iJson) {
	    	var res = JSON.parse(iJson);
	    	if(res.state){
	    		parent.$.messager.show({
	    			title : '提示',
	    			msg : res.msg,
	    			timeout : 4000 * 2
	    		});
	    	}else{
				$.messager.alert("提示", '出错了，保存失败!',"error")
			}
	    }
	});
}

// 渲染订单的信息
function loadLoanOrderInfo(row){
	//申请贷款信息
	$.ajax({
		url : 'loanOrder/loanOrderAction!queryLoanOrderById.action',
		type : 'POST',
		data : {'loanOrderId': row.loanOrderId },
		async : false,
		dataType : 'JSON',
		success : function(iJson) {
			if(iJson.loanAmount){
				$("#loanInfo").form('load', iJson);
			}
		}
	});
}
</script>
<div class="well well-small" style="margin-left: 5px;margin-top: 5px;width: 700px;">
	  <form id="loanInfo">
	    <table class="table">
			<tr>
				<th>申请额度(元):</th>
				<td><input name="loanAmount" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:['mDouble','length[0,20]']" type="text" />
				<th>最低接受额度(元):</th>
				<td><input name="loanMin" type="text" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:['mDouble','length[0,20]']"/></td>
			</tr>
			<tr>
				<th>申请贷款期限(月):</th>
				<td><input name="loanPeriod" type="text" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:['integer','length[0,2]']"/></td>
				<th>还款方式:</th>
				<td><input id="repayMethod" name="repayMethod" type="text" class="easyui-textbox easyui-validatebox easyui-aa"  panelHeight="auto" editable="false"/></td>
			</tr>
			<tr>
			    <th>贷款类型:</th>
			    <td>
			       <input id="loanType" name="loanType" type="text" class="easyui-textbox easyui-validatebox easyui-aa"  panelHeight="auto" editable="false"/>
			    </td>
			    <th>业务员:</th>
				<td><input name="salesMan" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'length[0,40]'"/></td>
			</tr>
			<tr>
				<th>贷款用途:</th>
				<td colspan="3">
				  <textarea name="purpose" class="easyui-textbox easyui-validatebox" style="width: 470px; height: 80px;" data-options="required:true,validType:'length[0,100]'"></textarea>
				</td>
			</tr>
			<tr>
			    <td colspan="4" style="text-align: right;">
			      <a href="javascript:void(0)" id="saveinfo" class="easyui-linkbutton" iconCls="icon-save" onclick="toSaveLoanInfo();">保存</a>
			      <a href="javascript:void(0)" id="editinfo" class="easyui-linkbutton" iconCls="icon-edit" style="display: none;" onclick="showSavebut();">修改</a>
			    </td>
			</tr>
		</table>
	  </form>
</div>