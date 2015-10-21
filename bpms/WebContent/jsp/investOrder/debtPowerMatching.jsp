<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<style type="text/css">
 th{
    text-align: right;
    padding: 6px 10px 6px 10px;
 }
 .table td{
    text-align: left;
     padding: 6px 10px 6px 10px; 
 }
 .table{
	border-collapse: collapse;
	border-spacing: 0;
	max-width: 100%;
	width: 600px;
 }
</style>
</head>
<script type="text/javascript">
  var repayMethodArr = jqueryUtil.getTextArr("repay_method");//还款方式
  var investOrderIds = "";
  $(function(){
	  $("#repayMethod").html(jqueryUtil.showText('${loanOrderModel.repayMethod}',repayMethodArr));
	  createInvestOrderGrid();
  });
  function createInvestOrderGrid(){
	  $("#investOrderGrid").datagrid({
		    url:'debtMatching/debtMatchingAction!getAutoDebtMatchingInvestOrders.action',
			width: 'auto',
			height: 300,
			height: $(this).height()-375,
			pagination:false,
			rownumbers:true,
			border:true,
			singleSelect:true,
			nowrap:true,//如果为true，则在同一行中显示数据。设置为true可以提高加载性能。
			remoteSort:false,//定义是否从服务器对数据进行排序。
			striped:true,//是否显示斑马线
			columns:[[
			        {field : 'investorName',title : '投资客户',width :100,align : 'center'},
	                {field : 'idCrad',title : '身份证号码',width : 180,align : 'center'},
		            {field : 'matchEdu',title : '匹配金额(元)',width : 140,align : 'center'},
	                {field : 'usableEdu',title : '可用余额(元)',width : 140,align : 'center'},
	                {field : 'investEdu',title : '账户总额(元)',width : 140,align : 'center'},
	                {field : 'endDate',title : '到期日期',width : 120,align : 'center'},
	                {field : 'contractNo',title : '合同编号',width : 120,align : 'center',hidden:true},
	                {field : 'investOrderId',title : '操作',width :100,align : 'center',formatter:function(value,row,index){
	                		return "<a href=\"javascript:void(0)\">删除</a>";
	                }}
		   ]],
		   onClickCell:function(rowIndex, field, value){
	  			if(field=="investOrderId"){
	  				shanchu(rowIndex);
	  			}
	  	   }, 
	  	   toolbar:[{
			   text:'添加出借人',
			   iconCls:'icon-add',
			   handler:openInvestOrderDialog
		   },'-',{
			   text:'自动匹配',
			   iconCls:'icon-add',
			   handler:autoMatching
		   },'-',{
			   text:'清空列表',
			   iconCls:'icon-cut',
			   handler:toDeleteAll
		   },'-',{
			   text:'匹配成功并保存',
			   iconCls:'icon-save',
			   handler:confirmMachingSuccessAndSave
		   }]
	  });
  }
  
   //弹出自动进行债券匹配的窗口 
  function autoMatching(){	  
	  var loanOrderId = $("#loanOrderId").val();  //获得贷款订单ID
	  $("#investOrderGrid").datagrid('load', {
		  loanOrderId : loanOrderId
	  });
	  //自动还是手动标志
	  $("#sign").val("zidong");
  }
  //匹配成功并保存
  function confirmMachingSuccessAndSave(){
	  var rows = $("#investOrderGrid").datagrid('getRows');
	  if(rows.length <= 0){
		  $.messager.alert("提示", "请匹配出借人!","warning");
		  return null;
	  }
	  var keyongyue = new Array();//可用余额数组
	  var ids = new Array();//投资订单id
	  var touzijine = new Array();//匹配金额
	  var count = 0;
	  $.each(rows,function(i,row){
		  keyongyue.push(row.usableEdu);
		  ids.push(row.investOrderId);
		  touzijine.push(row.matchEdu);
		  count += (Number)(row.matchEdu);
	  });
	  keyongyue = keyongyue.join(",");
	  investOrderIds = ids.join(",");
	  touzijine = touzijine.join(",");
	  var contractNo = $("#contractNo").val();//贷款合同编号
      var loanEdu = $("#loanEdu").val();//贷款金额
	  if(count!=loanEdu){
		  $.messager.alert("提示", "匹配金额不准确!","warning");
		  return null;
	  } 
	  $.ajax({
		  type:'POST',
		  url:'debtMatching/debtMatchingAction!toAddObligationMatch.action',
		  data:'usableEdus='+keyongyue+"&investOrderIds="+investOrderIds+"&contractNo="+contractNo+"&matchingEdus="+touzijine,
		  dataType:'JSON',
		  success:function(iJson){
			  if(iJson.status){
				  $("#debtPowerMatchingDialog").dialog("close");
				  $("#loanOrderGrid").datagrid("reload");
			  }
			  parent.$.messager.show({
					title : '提示',
					msg : iJson.message,
					timeout : 4000 * 2
			  });
		  }
	  });
  }
  
  function openInvestOrderDialog(){
	  getInvestOrderIds();
	  $('#investOrderDialog').dialog('open');
  }
  function toDeleteAll(){
	  $('#investOrderGrid').datagrid('loadData',{total:0,rows:[]});
  }
  //删除
  function shanchu(index){
	  $("#investOrderGrid").datagrid("deleteRow",index);
	  getInvestOrderIds();
  }
  //获取id字符串
  function getInvestOrderIds(){
	  var rows = $("#investOrderGrid").datagrid('getRows');
	  if(rows.length > 0){
		  var ids = new Array();
		  $.each(rows,function(i,row){
			  ids.push(row.investOrderId);
		  });
		  investOrderIds = ids.join(",");
	  }else if(rows.length == 0){
		  investOrderIds = "";
	  }else{
	  } 
  }
</script>
<div class="well well-small" style="margin: 5px">
   <input id="loanOrderId" name="loanOrderId" value="${loanOrderModel.loanOrderId}" type="hidden"/>
   <input id="loanerId" name="loanerId" value="${loanOrderModel.loanerId}" type="hidden"/>
   <input id="contractNo" name="contractNo" value="${loanOrderModel.contractNo}" type="hidden"/><!-- 贷款合同编号 -->
   <input id="loanBgDate" name="loanBgDate" value="${loanOrderModel.loanBgDate}" type="hidden"/><!-- 贷款开始日期 -->
   <input id="loanEdu" name="loanEdu" value="${loanOrderModel.loanEdu}" type="hidden"/><!-- 贷款金额 -->
   <input id="sign" name="sign" value="zidong" type="hidden"/><!-- 自动还是手动匹配标志 -->
   <table class="table">
     <tr>
        <th>客户名称:</th>
        <td>${loanOrderModel.name}</td>
        <th>证件号码:</th>
        <td>${loanOrderModel.idNo}</td>
     </tr>
     <tr>
        <th>贷款金额:</th>
        <td>${loanOrderModel.loanEdu}</td>
        <th>贷款期限(月):</th>
        <td>${loanOrderModel.loanPeriod}</td>
        <th>到期日期:</th>
        <td>${loanOrderModel.loanEdDate}</td>
     </tr>
     <tr>
        <th>利息率(%):</th>
        <td>${loanOrderModel.rate}</td>
        <th>还款方式:</th>
        <td id="repayMethod"></td>
     </tr>
   </table>
</div>
<div style="margin: 5px">
 <table id="investOrderGrid"></table>
</div>
<div id="investOrderDialog" class="easyui-dialog" title="投资订单信息" style="width:1000px;height:600px;" closed="true"  
        data-options="iconCls:'icon-save',resizable:true,modal:true,href:'jsp/investOrder/investOrderList.jsp'"></div>
</html>