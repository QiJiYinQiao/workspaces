<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/validate.js"></script>

<script type="text/javascript">	
	var selectRow;
	var selectedCheckbox='';
	var appNo=null;
	// 渲染下拉列表框
	createSelect();
	/** 清空新添加的的的数据**/
	function clearForm(){
		$("#investProductInputOrSaveForm").form("clear");		
	}
	$(function(){
		$("#feeTypeOther").hide();
		$("#feeTypeOtherName").hide();
		if($row!=null){
			appendHtml($row.feeType);
			$("#feeType").combobox('setValue',$row.feeType);
		}
	});
	
	function createSelect(){
		//初始化组织机构
		$("#feeType").combobox({
			
			data:[{ 
				"id":0, 
				"text":"请选择要申请的费用类型",
				"selected":true
				},{ 
					"id":1, 
					"text":"加油费",
				},{ 
					"id":2, 
					"text":"停车费",
				},{ 
					"id":3, 
					"text":"高速费",
				},{ 
					"id":4, 
					"text":"维修费",
				},{ 
					"id":5, 
					"text":"保养费",
				},{ 
					"id":6, 
					"text":"保险费",
				},{ 
					"id":7, 
					"text":"其他",
				}],
			valueField:'id',
			textField:'text',
			editable :false,
			width:170,
			onChange: function(n,o){
				appendHtml(n);
			}
		});
	}
	
	function appendHtml(n){
		if(n==7){
			$("#tr1,#tr2,#tr3").remove();
			$("#feeTypeOther").show();
			$("#feeTypeOtherName").show();
		}else{
			$("#tr1,#tr2,#tr3").remove();
			$("#feeTypeOther").hide();
			$("#feeTypeOtherName").hide();
			if(n==1){
				$("#feeTypeTr").after("<tr id='tr1'><th>申请金额:</th><td><input type=\"text\" id=\"appAmtJiaYou\" name=\"appAmtJiaYou\" class=\"easyui-textbox easyui-numberbox\" precision='2' data-options=\"validType:'length[0,100]', required:true\"/></td><th>启程公里数:</th><td><input type=\"text\" id=\"bgMileageJiaYou\" name=\"bgMileageJiaYou\" class=\"easyui-textbox easyui-numberbox\" data-options=\"validType:'length[0,100]', required:true\"/></td></tr><tr  id='tr2'><th>结束公里数:</th><td><input type=\"text\" id=\"edMileageJiaYou\" name=\"edMileageJiaYou\" class=\"easyui-textbox easyui-numberbox\" data-options=\"validType:'length[0,100]', required:true\"/></td><th>上次申请时间:</th><td><input type=\"text\" id=\"prevAppDate\" name=\"prevAppDate\" class=\"easyui-datebox\" data-options=\"validType:'length[0,100]', required:true\" editable='false'/></td></tr><tr id='tr3'><th>卡内余额:</th><td><input type=\"text\" id=\"cardBalance\" name=\"cardBalance\" class=\"easyui-textbox easyui-numberbox\" precision='2' data-options=\"validType:'length[0,100]', required:true\"/></td></tr>");
			}
			if(n==2){
				$("#feeTypeTr").after("<tr id='tr1'><th>停车费:</th><td><input type=\"text\" id=\"appAmtTingChe\" name=\"appAmtTingChe\" class=\"easyui-textbox easyui-numberbox\" precision='2' data-options=\"validType:'length[0,100]', required:true\"/></td><th>停车时间:</th><td><input type=\"text\" id=\"parkingDtime\" name=\"parkingDtime\" class=\"easyui-datebox\" data-options=\"validType:'length[0,100]', required:true\" editable='false'/></td></tr><tr id='tr2'><th>停车地点:</th><td><input type=\"text\" id=\"parkingPlace\" name=\"parkingPlace\" class=\"easyui-textbox\" data-options=\"validType:'length[0,100]', required:true\"/></td></tr>");
			}
			if(n==3){
				$("#feeTypeTr").after("<tr id='tr1'><th>申请金额:</th><td><input type=\"text\" id=\"appAmtGaoSu\" name=\"appAmtGaoSu\" class=\"easyui-textbox easyui-numberbox\" precision='2' data-options=\"validType:'length[0,100]', required:true\"/></td><th>缴费日期:</th><td><input type=\"text\" id=\"paymentDate\" name=\"paymentDate\" class=\"easyui-datebox\" data-options=\"validType:'length[0,100]', required:true\" editable='false'/></td></tr><tr id='tr2'><th>收费站名称:</th><td><input type=\"text\" id=\"tollgateName\" name=\"tollgateName\" class=\"easyui-textbox\" data-options=\"validType:'length[0,100]', required:true\"/></td></tr>");
			}
			if(n==4){
				$("#feeTypeTr").after("<tr id='tr1'><th>申请金额:</th><td><input type=\"text\" id=\"appAmtWeiXiu\" name=\"appAmtWeiXiu\" class=\"easyui-textbox easyui-numberbox\" precision='2' data-options=\"validType:'length[0,100]', required:true\"/></td><th>维修项目:</th><td><input type=\"text\" id=\"repairItem\" name=\"repairItem\" class=\"easyui-textbox\" data-options=\"validType:'length[0,100]', required:true\"/></td></tr><tr id='tr2'><th>维修原因:</th><td><input type=\"text\" id=\"repairReson\" name=\"repairReson\" class=\"easyui-textbox\" data-options=\"validType:'length[0,100]', required:true\"/></td></tr>");
			}
			if(n==5){
				$("#feeTypeTr").after("<tr id='tr1'><th>申请金额:</th><td><input type=\"text\" id=\"appAmtBaoYang\" name=\"appAmtBaoYang\" class=\"easyui-textbox easyui-numberbox\" precision='2' data-options=\"validType:'length[0,100]', required:true\"/></td><th>启程公里数:</th><td><input type=\"text\" id=\"bgMileageBaoYang\" name=\"bgMileageBaoYang\" class=\"easyui-textbox easyui-numberbox\" data-options=\"validType:'length[0,100]', required:true\"/></td></tr><tr id='tr2'><th>结束公里数:</th><td><input type=\"text\" id=\"edMileageBaoYang\" name=\"edMileageBaoYang\" class=\"easyui-textbox easyui-numberbox\" data-options=\"validType:'length[0,100]', required:true\"/></td><th>上次申请费用时间:</th><td><input type=\"text\" id=\"prevAppDateBaoYang\" name=\"prevAppDateBaoYang\" class=\"easyui-datebox\" data-options=\"validType:'length[0,100]', required:true\" editable='false' /></td></tr>");
			}
			if(n==6){
				$("#feeTypeTr").after("<tr id='tr1'><th>申请金额:</th><td><input type=\"text\" id=\"appAmtBaoXian\" name=\"appAmtBaoXian\" class=\"easyui-textbox easyui-numberbox\" precision='2' data-options=\"validType:'length[0,100]', required:true\"/></td><th>启程公里数:</th><td><input type=\"text\" id=\"bgMileageBaoXian\" name=\"bgMileageBaoXian\" class=\"easyui-textbox easyui-numberbox\" data-options=\"validType:'length[0,100]', required:true\"/></td></tr><tr id='tr2'><th>结束公里数:</th><td><input type=\"text\" id=\"edMileageBaoXian\" name=\"edMileageBaoXian\" class=\"easyui-textbox easyui-numberbox\" data-options=\"validType:'length[0,100]', required:true\"/></td><th>保险开始时间:</th><td><input type=\"text\" id=\"insuranceBgDtime\" name=\"insuranceBgDtime\" class=\"easyui-datebox\" data-options=\"validType:'length[0,100]', required:true\" editable='false'/></td></tr><tr id='tr3'><th>保险结束时间:</th><td><input type=\"text\" id=\"insuranceEdDtime\" name=\"insuranceEdDtime\" class=\"easyui-datebox easyui-numberbox\" data-options=\"validType:'length[0,100]', required:true\" editable='false'/></td><th>保险公司名称:</th><td><input type=\"text\" id=\"icName\" name=\"icName\" class=\"easyui-textbox\" data-options=\"validType:'length[0,100]', required:true\"/></td></tr>");
			}
			
			$.parser.parse('#investProductInputOrSaveForm');
		}
	}
	
	/** 保存增加或者修改名片申请的数据**/
	function saveApp(){
		if($("#carNo").val()==null || $("#carNo").val()==""){
			$.messager.alert("提示","请输入车牌号码!","warning");
			return;
		}
		if($("#bankName").val()==null || $("#bankName").val()==""){
			$.messager.alert("提示","请输入银行名称!","warning");
			return;
		}
		if($("#intoAct").val()==null || $("#intoAct").val()==""){
			$.messager.alert("提示","请输入转入账号!","warning");
			return;
		}
		if($("#actName").val()==null || $("#actName").val()==""){
			$.messager.alert("提示","请输入账户名称!","warning");
			return;
		}
		if($("#feeType").combobox("getValue")==0){
			$.messager.alert("提示","请输入要申请的费用类型!","warning");
			return;
		}
		if($("#feeType").combobox("getValue")==1){
			if($("#appAmtJiaYou").val()==null || $("#appAmtJiaYou").val()==""){
				$.messager.alert("提示","请输入申请的金额!","warning");
				return;
			}
			if($("#bgMileageJiaYou").val()==null || $("#bgMileageJiaYou").val()==""){
				$.messager.alert("提示","请输入启程公里数!","warning");
				return;
			}
			if($("#edMileageJiaYou").val()==null || $("#edMileageJiaYou").val()==""){
				$.messager.alert("提示","请输入结束公里数!","warning");
				return;
			}
			if($("#prevAppDate").datebox('getValue')==null || $("#prevAppDate").datebox('getValue')==""){
				$.messager.alert("提示","请输入上次申请费用时间!","warning");
				return;
			}
			if($("#cardBalance").val()==null || $("#cardBalance").val()==""){
				$.messager.alert("提示","请输入卡内余额!","warning");
				return;
			}
		}
		if($("#feeType").combobox("getValue")==2){
			if($("#appAmtTingChe").val()==null || $("#appAmtTingChe").val()==""){
				$.messager.alert("提示","请输入申请的金额!","warning");
				return;
			}
			if($("#parkingDtime").datebox('getValue')==null || $("#parkingDtime").datebox('getValue')==""){
				$.messager.alert("提示","请输入停车时间!","warning");
				return;
			}
			if($("#parkingPlace").val()==null || $("#parkingPlace").val()==""){
				$.messager.alert("提示","请输入停车地点!","warning");
				return;
			}
		}
		if($("#feeType").combobox("getValue")==3){
			if($("#appAmtGaoSu").val()==null || $("#appAmtGaoSu").val()==""){
				$.messager.alert("提示","请输入申请的金额!","warning");
				return;
			}
			if($("#paymentDate").datebox('getValue')==null || $("#paymentDate").datebox('getValue')==""){
				$.messager.alert("提示","请输入缴费日期!","warning");
				return;
			}
			if($("#tollgateName").val()==null || $("#tollgateName").val()==""){
				$.messager.alert("提示","请输入收费站名称!","warning");
				return;
			}
		}
		if($("#feeType").combobox("getValue")==4){
			if($("#appAmtWeiXiu").val()==null || $("#appAmtWeiXiu").val()==""){
				$.messager.alert("提示","请输入申请的金额!","warning");
				return;
			}
			if($("#repairItem").val()==null || $("#repairItem").val()==""){
				$.messager.alert("提示","请输入维修的项目!","warning");
				return;
			}
			if($("#repairReson").val()==null || $("#repairReson").val()==""){
				$.messager.alert("提示","请输入维修原因!","warning");
				return;
			}
		}
		if($("#feeType").combobox("getValue")==5){
			if($("#appAmtBaoYang").val()==null || $("#appAmtBaoYang").val()==""){
				$.messager.alert("提示","请输入申请的金额!","warning");
				return;
			}
			if($("#bgMileageBaoYang").val()==null || $("#bgMileageBaoYang").val()==""){
				$.messager.alert("提示","请输入启程公里数!","warning");
				return;
			}
			if($("#edMileageBaoYang").val()==null || $("#edMileageBaoYang").val()==""){
				$.messager.alert("提示","请输入结束公里数!","warning");
				return;
			}
			if($("#prevAppDateBaoYang").datebox('getValue')==null || $("#prevAppDateBaoYang").datebox('getValue')==""){
				$.messager.alert("提示","请输入上次申请费用时间!","warning");
				return;
			}
		}
		if($("#feeType").combobox("getValue")==6){
			if($("#appAmtBaoXian").val()==null || $("#appAmtBaoXian").val()==""){
				$.messager.alert("提示","请输入申请的金额!","warning");
				return;
			}
			if($("#bgMileageBaoXian").val()==null || $("#bgMileageBaoXian").val()==""){
				$.messager.alert("提示","请输入启程公里数!","warning");
				return;
			}
			if($("#edMileageBaoXian").val()==null || $("#edMileageBaoXian").val()==""){
				$.messager.alert("提示","请输入结束公里数!","warning");
				return;
			}
			if($("#insuranceBgDtime").datebox('getValue')==null || $("#insuranceBgDtime").datebox('getValue')==""){
				$.messager.alert("提示","请输入保险开始时间!","warning");
				return;
			}
			if($("#insuranceEdDtime").datebox('getValue')==null || $("#insuranceEdDtime").datebox('getValue')==""){
				$.messager.alert("提示","请输入保险结束时间!","warning");
				return;
			}
			if($("#icName").val()==null || $("#icName").val()==""){
				$.messager.alert("提示","请输入保险公司名称!","warning");
				return;
			}
		}
		var data ="";
		if($row!=null){
			if($row.feeType==3){
				data=$('#investProductInputOrSaveForm').serialize()+"&feeType="+$("#feeType").combobox('getValue')+"&paymentDate="+$("#paymentDate").datebox('getValue');
			}
			if($row.feeType==1){
				data=$('#investProductInputOrSaveForm').serialize()+"&feeType="+$("#feeType").combobox('getValue')+"&prevAppDate="+$("#prevAppDate").datebox('getValue');
			}
			if($row.feeType==5){
				data=$('#investProductInputOrSaveForm').serialize()+"&feeType="+$("#feeType").combobox('getValue')+"&prevAppDateBaoYang="+$("#prevAppDateBaoYang").datebox('getValue');
			}
			if($row.feeType==2){
				data=$('#investProductInputOrSaveForm').serialize()+"&feeType="+$("#feeType").combobox('getValue')+"&parkingDtime="+$("#parkingDtime").datebox('getValue');
			}
			if($row.feeType==6){
				data=$('#investProductInputOrSaveForm').serialize()+"&feeType="+$("#feeType").combobox('getValue')+"&insuranceBgDtime="+$("#insuranceBgDtime").datebox('getValue')+"&insuranceEdDtime="+$("#insuranceEdDtime").datebox('getValue');
			}
		}
		
		$.ajax({
			cache:true,
			type:'POST',
			url:"VehicleExpenses/addVehicleExpenses.do",
			data:$('#investProductInputOrSaveForm').serialize(),
			async:false,
			dataType:'JSON',
			success:function(iJson){
				if(iJson!=null){
					appNo=iJson;
					$.messager.alert("提示", '车辆费用申请保存成功!',"info");
					//添加编辑后刷新页面为添加时的费用类别
					$("#travelExpensesAppgrid").datagrid("reload");
					$("#travelExpensesAppgrid").datagrid("load",{
						type:$('#feeType').combobox('getValue')
					});  
					$("#upploadAttachment").linkbutton("enable");
					$("#checkAttachment").linkbutton("enable");
				}
			}
		}); 
		
	};
	
	//上传附件
    $("#upploadAttachment").click(function(){
    	if(appNo!=null){
    		fileUploadsDlg(appNo);
    	}
	});
	//查看附件
	$("#checkAttachment").click(function(){
		if(appNo!=null){
			checkAttachementDetail(appNo,'');
    	}
	});
	
</script>
	<div style="margin-left: 5px;margin-top   : 5px;" data-options="iconCls:'icon-cstbase'">
	   <form id="investProductInputOrSaveForm"  method="post">
	   		<input type="text" id="veaId" name="veaId" hidden="hidden"/>
	   		<input type="text" id="efiId" name="efiId" hidden="hidden"/>
	   		<input type="text" id="ffiId" name="ffiId" hidden="hidden"/>
	   		<input type="text" id="mfiId" name="mfiId" hidden="hidden"/>
	   		<input type="text" id="pfiId" name="pfiId" hidden="hidden"/>
	   		<input type="text" id="rfiId" name="rfiId" hidden="hidden"/>
	   		<input type="text" id="ifiId" name="ifiId" hidden="hidden"/>
	   		<input type="text" id="appNo" name="appNo" hidden="hidden"/>
			<table class="table" width="100%">
				<tr>
					<th>车牌号码:</th>
					<td>
						<input type="text" id="carNo" name="carNo" class="easyui-textbox" data-options="validType:'length[0,100]', required:true"/>
					</td>
					<th>银行名称:</th>
					<td>
						<input type="text" id="bankName" name="bankName" class="easyui-textbox" data-options="validType:'length[0,100]', required:true"/>
					</td>
				</tr>
				<tr>
					<th>转入账号:</th>
					<td>
						<input type="text" id="intoAct" name="intoAct" class="easyui-textbox easyui-numberbox" data-options="validType:'length[0,100]', required:true"/>
					</td>
					<th>账户名称:</th>
					<td>
						<input type="text" id="actName" name="actName" class="easyui-textbox" data-options="validType:'length[0,100]', required:true"/>
					</td>
				</tr>
				<tr id="feeTypeTr">
					<th>费用类型:</th>
					<td>
						<input type="text" id="feeType" name="feeType" class="easyui-textbox" data-options="validType:'length[0,100]', required:true"/>
					</td>
					<th id="feeTypeOtherName">其他费用类型:</th>
					<td>
						<input type="text" id="feeTypeOther" name="feeTypeOther" class="easyui-textbox" data-options="validType:'length[0,100]', required:true"/>
					</td>
				</tr>
				<tr>
					<th>备注:</th>
					<td colspan="4">
						<textarea id="remark" name="remark" class="easyui-textbox" data-options="validType:'length[0,400]', required:true" style="width: 690px;"></textarea>
					</td>
				</tr>
				<tr align="right">
				   <td colspan="4">
				   	  <a id="upploadAttachment" href="javascript:void(0);" class="easyui-linkbutton"  >上传附件</a>	
					  <a id="checkAttachment" href="javascript:void(0);" class="easyui-linkbutton" >查看附件</a>		
				      <a href="javascript:void(0)" id="rset" class="easyui-linkbutton" iconCls="icon-reload" onclick="clearForm();">重置</a>
				   	  <a href="javascript:void(0)" id="save" class="easyui-linkbutton" iconCls="icon-save" onclick="saveApp();">保存</a>
				   </td>
				</tr>
			</table>
		</form>
	</div>
	<!--查看附件-->
	<div id="saveOrUpdateInvestProductDialog"></div>		
	<div id="credentialsAttachDatagrid"></div>		