<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/validate.js"></script>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
	var lrow;
	
	$(function(){
		
		aDisable();//判断上传附件
		//可选的借款申请
		findLoanAMT();
	});
	
	//查询个人可选的借款申请
	function findLoanAMT(){
		$("#jkAppNo").combobox({
			width:171,
			multiple:true,
			separator:",", 
			url:"ExpendVoucherApp/findUserLoanList.do",
			valueField:'code',
		 	textFiled:'text',
		 	onLoadSuccess:function(data){
		 		//加一个全部
		 		
		 		
		 	},
		 	onSelect:function(data){
		 		var grantExp=$("#grantExp").val();
		 		$("#grantExp").numberbox('setValue',parseFloat(data.text)+parseFloat(grantExp));
		 		
		 	},
		 	onUnselect:function(data){
		 		var grantExp=parseFloat(0);
		 		 var jkAppNos=$("#jkAppNo").combobox('getText');
		 		var jAppNos=jkAppNos.split(",");
		 		 if(jkAppNos!=""){
			 		for (var i = 0; i < jAppNos.length; i++) {
						grantExp+=parseFloat(jAppNos[i]);
					}
			 		$("#grantExp").numberbox('setValue',grantExp);
		 		 }else{
			 		$("#grantExp").numberbox('setValue',0);
		 		 }
		 	}
		 
		}); 
	}
	
	
	//查询个人已选的借款申请
	function findUserLoan(appNo,row){
		$("#jkAppNo").combobox({
			width:171,
			multiple:true,
			separator:",", 
			url:"ExpendVoucherApp/findSelectUserLoan.do?appNo="+appNo,
			valueField:'code',
		 	textFiled:'text',
		 	onLoadSuccess:function(data){
		 		//加一个全部
		 		//填写借款
				 var jkAppNos=[];
		 		
		 		if(row.jkAppNo!=null && row.jkAppNo!=""){
		 			
					 var jk=row.jkAppNo.split(",");
					 for(var i=0;i<jk.length;i++){
						 jkAppNos[i]=jk[i];
					 }
					 $("#jkAppNo").combobox('setValues',jkAppNos);
		 		}
		 		
		 	},
		 	onSelect:function(data){
		 		var grantExp=$("#grantExp").val();
		 		$("#grantExp").numberbox('setValue',parseFloat(data.text)+parseFloat(grantExp));
		 		
		 	},
		 	onUnselect:function(data){
		 		
		 		var grantExp=parseFloat(0);
		 		 var jkAppNos=$("#jkAppNo").combobox('getText');
		 		var jAppNos=jkAppNos.split(",");
		 		 if(jkAppNos!=""){
			 		for (var i = 0; i < jAppNos.length; i++) {
						grantExp+=parseFloat(jAppNos[i]);
					}
			 		$("#grantExp").numberbox('setValue',grantExp);
		 		 }else{
			 		$("#grantExp").numberbox('setValue',0);
		 		 }
		 	}
		}); 
	}
	

	function aDisable(){
		if($.trim($("#appNoAdd").val())==''){
			$("#upploadAttachment").attr("disabled",true);
			$("#checkAttachment").attr("disabled",true);
		}else{
			$("#upploadAttachment").removeClass("l-btn-disabled");
			$("#checkAttachment").removeClass("l-btn-disabled");
		}
	}
	

	
	//保存数据
	function toSaveBaseInfo(from){
		var isValid = $("#expendFrom").form('validate');
    	if(!isValid){
    		return false;
    	} 
		$.ajax({
			cache:true,
			type:'POST',
			url:"ExpendVoucherApp/saveExpendVoucherApp.do",
			data:$('#expendFrom').serialize(),
			async:false,
			dataType:'JSON',
			success:function(res){
				 if(res!=null){
					 var f = $("#expendFrom");
					 f.form("load", res); 
					 lrow=res;
					
					 //保存编辑按钮转换
					 $("#"+from+" a[iconCls = 'icon-edit']").show();
					 $("#"+from+" a[iconCls = 'icon-ok']").hide();
					 //禁用
					 $("#clause").attr({"disabled":"disabled"});
					 $("#total").attr({"disabled":"disabled"});
					 $("#remark").attr({"disabled":"disabled"});
					 
					 $("#jkAppNo").combobox("disable");
					 $("#jkAppNo").combobox("setText",res.jkAppNo);
					 
					 aDisable();
					 $.messager.alert("提示", '支出凭单申请信息保存成功!',"info")
				}else{
					$.messager.alert("提示", '出错了，保存失败!',"error")
				} 
			}
		});
	}

	function editForm(from){
		
		$("#jkAppNo").combobox("enable");
		findUserLoan($("#appNoAdd").val(),lrow); 
		
		$("#"+from+" a[iconCls^='icon-edit']").css('display','none');
		$("#"+from+" a[iconCls^='icon-ok']").css('display','inline-block');
		//解除禁用
		 $("#clause").attr("disabled",false);
		 $("#total").attr("disabled",false);
		 $("#remark").attr("disabled",false);
	}
	
	
    //上传附件
    $("#upploadAttachment").click(function(){
    	if($.trim($("#appNoAdd").val())!=''){
    		fileUploadsDlg($("#appNoAdd").val(),'');
    	}
	});
	//查看附件
	$("#checkAttachment").click(function(){
		if($.trim($("#appNoAdd").val())!=''){
			checkAttachementDetail($("#appNoAdd").val(),$("#applicantNo").val(),'');
    	}
	});
   
     
     
</script>
<div id="tt" >
	<div title="支出凭单申请信息" >
	  <div class="well well-small" style="margin:5px;width:680px">
	     
	     	<form id="expendFrom" >
	     	 <input id="evaId" name="evaId" type="hidden"/><!-- 申请id -->
	         <input id="appNoAdd" name="appNo" type="hidden"/><!-- 申请编号 -->
	         <input id="applicantNo" name="applicantNo" type="hidden"/><!-- 申请人id-->
	         
	         <table class="table">
	          	<tr>
	         		<th>借款选项:</th>
					<td >
						<input name="jkAppNo" id="jkAppNo" type="text" class="easyui-textbox  easyui-combobox" style="width: 170px" />&nbsp;
					</td>
					<th style="width: 58px;">预借旅费:</th>
					<td>
						<input id="grantExp" name="grantExp"  type="text" style="width: 50px;text-align: center;" value="0" class="easyui-textbox easyui-validatebox easyui-numberbox" precision="2" data-options="required:true,editable:false">元</input>
					</td>
	         	 </tr>
				 <tr>
					<th>支付款项:</th>
					<td colspan="3">
					  <textarea id="clause" name="clause" class="easyui-textbox easyui-validatebox" style="width: 606px; height: 60px;resize:none;" data-options="required:true,validType:'length[0,100]'"></textarea>
					</td>
				 </tr>
				 
				 <tr>
	         	 	<th style="width: 58px;">合计金额:</th>
	         	 	<td>
	         	 		<input id="total" name="total"  type="text" style="width: 50px;text-align: center;" value="0" class="easyui-textbox easyui-validatebox easyui-numberbox" precision="2" data-options="required:true,editable:false">元</input>
	         	 	</td>
	         	 </tr>
				 
				 <tr>
					<th>备注信息:</th>
					<td colspan="3">
					  <textarea id="remark" name="remark" class="easyui-textbox easyui-validatebox" style="width: 606px; height: 60px;resize:none;" data-options="validType:'length[0,100]'"></textarea>
					</td>
				</tr>
				<tr>
				   <td colspan="6" style="text-align: right;">
				   	  <a id="upploadAttachment" href="javascript:void(0);" class="easyui-linkbutton"  >上传附件</a>	
					  <a id="checkAttachment" href="javascript:void(0);" class="easyui-linkbutton" >查看附件</a>
				   	  <a href="javascript:void(0)" id="save" class="easyui-linkbutton" iconCls="icon-ok" onclick="toSaveBaseInfo('expendFrom');">保存</a>
			      	  <a href="javascript:void(0)" id="save" class="easyui-linkbutton"  iconCls="icon-edit" style="display:none;" onclick="editForm('expendFrom')">编辑</a>
				   </td>
				</tr>
	         </table>
	       </form>
	  </div>
	</div>
	
</div>
