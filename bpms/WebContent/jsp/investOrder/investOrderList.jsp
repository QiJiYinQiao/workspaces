<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
$(function(){
	createInvestOrderGrid1();
	$('#touzijine').numberbox({    
	    min:0,    
	    precision:2    
	});  
});
function createInvestOrderGrid1(){
	var loanBgDate = $("#loanBgDate").val();
	$("#investOrderGrid1").datagrid({
		url:"debtMatching/debtMatchingAction!findListByOrderStatus.action?loanBgDate="+loanBgDate+"&investOrderIds="+investOrderIds,
		width: 'auto',
		height: $(this).height()-270,
		pagination:false,
		rownumbers:true,
		border:true,
		singleSelect:true,
		nowrap:true,//如果为true，则在同一行中显示数据。设置为true可以提高加载性能。
		pageSize:30,
		pageList:[10,20,30,40],
		remoteSort:false,//定义是否从服务器对数据进行排序。
		striped:true,//是否显示斑马线
		columns:[[
		        {field : 'investorName',title : '投资客户',width :100,align : 'center'},
                {field : 'idCrad',title : '身份证号',width : 180,align : 'center'},
	            {field : 'prodName',title : '理财产品',width : 150,align : 'center'},
                {field : 'investEdu',title : '理财金额(元)',width : 100,align : 'center'},
                {field : 'usableEdu',title : '可用余额(元)',width : 150,align : 'center'},
                {field : 'endDate',title : '到期日期',width : 130,align : 'center'},
                {field : 'contractNo',title : '合同编号',width : 120,align : 'center',hidden:true},
                {field : 'caozuo',title : '操作',width : 100,align : 'center',formatter:function(value,row,index){
                	return "<a href=\"javascript:void(0)\" onclick=\"touzi("+index+");\">投资</a>&nbsp;&nbsp;"+"<a href=\"javascript:void(0)\" onclick=\"quanxuan("+index+");\">全选</a>";
                }}
	   ]]
  });
}
var rowdata;
/**
 * 弹出输入金额弹框
 */
function touzi(index){
	$("#myFormId").form("clear");
	$("#touzitankuang").dialog("open");
	var rows = $("#investOrderGrid1").datagrid("getRows");
	rowdata = rows[index];
}
/**
 * 输入金额确定
 */
function queding(){
	var touzijine = $("#touzijine").val();
	if(touzijine == null || touzijine == ""){
		$.messager.alert("提示","请输入投资金额!","warning");
		return;
	}
	//匹配金额
	rowdata['matchEdu'] = touzijine;
	//可用余额
	var usableEdu = rowdata['usableEdu'];
	if(parseFloat(usableEdu) < parseFloat(touzijine)){
		$.messager.alert("提示","投资金额必须小于可用余额!","warning");
		return;
	}
	rowdata['usableEdu'] = parseFloat(usableEdu) - parseFloat(touzijine);
	//手动还是自动标志
	var sign = $("#sign").val();
	if(sign=="zidong"){
		toDeleteAll();
		$("#sign").val("shoudong");
	}
	$("#investOrderGrid").datagrid("appendRow",rowdata);
	$("#touzitankuang").dialog("close");
	$("#investOrderDialog").dialog("close");
}
/**
 * 搜索
 */
function toSearch(){
	//投资客户
	var investorName = $("#investorName").val();
	//可用余额
	var usableEdu = $("#usableEdu").val();
	//到期日期
	var endDate01 = $("#endDate01").datebox('getValue');
	var endDate02 = $("#endDate02").datebox('getValue');
	if(usableEdu==""){
		$("#investOrderGrid1").datagrid("reload",{"investorAndInvestProductModel.investorName":investorName,"investorAndInvestProductModel.endDate01":endDate01,"investorAndInvestProductModel.endDate02":endDate02});
		return;
	}
	$("#investOrderGrid1").datagrid("reload",{"investorAndInvestProductModel.investorName":investorName,"investorAndInvestProductModel.usableEdu":usableEdu,"investorAndInvestProductModel.endDate01":endDate01,"investorAndInvestProductModel.endDate02":endDate02});
}
/**
 * 全选
 */
function quanxuan(index){
	var rows = $("#investOrderGrid1").datagrid("getRows");
	var data = rows[index];
	data['matchEdu'] = data['usableEdu'];
	data['usableEdu'] = 0;
	$("#investOrderGrid").datagrid("appendRow",data);
	$("#investOrderDialog").dialog("close");
}
</script>
<div style="margin: 1px;">
   <div class="well well-small" style="margin-left: 5px; margin-top: 5px">
	<form id="searchForm">
	   <table>
	     <tr>
	       <td>投资客户:</td>
	       <td><input id="investorName" name="investorName" class="easyui-textbox" style="width: 120px;"/></td>
	       <td>可用余额:</td>
	       <td><input id="usableEdu" name="usableEdu" class="easyui-textbox easyui-numberbox" min="0" precision="2" style="width: 120px;"/></td>
	       <td>到期日期:</td>
	       <td>
	       <input id="endDate01" name="endDate01" class="easyui-textbox easyui-datebox" editable="false" style="width: 120px;"/>
	       -
	       <input id="endDate02" name="endDate02" class="easyui-textbox easyui-datebox" editable="false" style="width: 120px;"/>
	       </td>
	       <td>
	         <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="toSearch();">搜索</a>&nbsp;&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" onclick="$('#searchForm').form('clear')">清空</a>
	       </td>
	     </tr>
	   </table>
	</form>
   </div>
   <table id="investOrderGrid1"></table>
   <div id="touzitankuang" class="easyui-dialog" title="输入投资金额" style="width:400px;height:200px;padding-top: 40px;padding-left: 35px;" data-options="iconCls:'icon-save',closed: true,resizable:true,modal:true">   
      <form id="myFormId">
        <table>
	         <tr>
	            <th>请输入投资金额:</th>
	            <td>
	              <input id="touzijine" name="touzijine"/>
	            </td>
	         </tr> 
	         <tr>
	           <td colspan="2" align="right">
	              <a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-ok' onclick="queding();">确定</a>
	              <a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-cancel' onclick="$('#touzitankuang').dialog('close')">取消</a>
	           </td>
	         </tr>
        </table>
      </form>
   </div>
</div>