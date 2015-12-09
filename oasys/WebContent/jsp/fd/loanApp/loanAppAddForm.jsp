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
		//获取该部门下的人
		RenderName();
	});
	
	// 用户名的下拉
	function RenderName(){
		$("#payerNo").combobox({
			width:171,
			url:"LoanApp/findOrgUserList.do",
			valueField:'code',
		 	textFiled:'text',
		 	onLoadSuccess:function(data){
		 		//加一个全部
		 		
		 		
		 	},
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
		var isValid = $("#loanFrom").form('validate');
    	if(!isValid){
    		return false;
    	} 
		$.ajax({
			cache:true,
			type:'POST',
			url:"LoanApp/saveLoanApp.do",
			data:$('#loanFrom').serialize(),
			async:false,
			dataType:'JSON',
			success:function(res){
				 /* if(res!=null){
					 var f = $("#loanFrom");
					 f.form("load", res); 
					 
					
					 //保存编辑按钮转换
					 $("#"+from+" a[iconCls = 'icon-edit']").show();
					 $("#"+from+" a[iconCls = 'icon-ok']").hide();
					 //禁用
					 $("#loanAmt").attr({"disabled":"disabled"});
					 $("#loanReson").attr({"disabled":"disabled"});
					 $("#remark").attr({"disabled":"disabled"});
					 $(":radio").attr({"disabled":"disabled"});
					 
					 $("#payerNo").combobox("disable");
					 $("#payerNo").combobox("setValue",res.payerNo);
					 
					 aDisable();
					 $.messager.alert("提示", '借款申请信息保存成功!',"info")
				}else{
					$.messager.alert("提示", '同一个付款人最多申请两个同一种未报销的借款用途!',"error")
				}  */
				if(res.status){
					 var f = $("#loanFrom");
					 f.form("load", res.data); 
					 
					
					 //保存编辑按钮转换
					 $("#"+from+" a[iconCls = 'icon-edit']").show();
					 $("#"+from+" a[iconCls = 'icon-ok']").hide();
					 //禁用
					 $("#loanAmt").attr({"disabled":"disabled"});
					 $("#loanReson").attr({"disabled":"disabled"});
					 $("#remark").attr({"disabled":"disabled"});
					 $(":radio").attr({"disabled":"disabled"});
					 
					 $("#payerNo").combobox("disable");
					 $("#payerNo").combobox("setValue",res.data.payerNo);
					 
					 aDisable();
				}else{
					$("#loanAmt").numberbox("setValue",0);
				}
				$.messager.alert(res.title, res.message,'info');
				
			}
		});
	}

	function editForm(from){
		
		$("#payerNo").combobox("enable");
		
		$("#"+from+" a[iconCls^='icon-edit']").css('display','none');
		$("#"+from+" a[iconCls^='icon-ok']").css('display','inline-block');
		//解除禁用
		 $("#loanAmt").attr("disabled",false);
		 $("#loanReson").attr("disabled",false);
		 $("#remark").attr("disabled",false);
		 $(":radio").attr("disabled",false);
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
	<div title="借款申请信息" >
	  <div class="well well-small" style="margin:5px;width:680px">
	     
	     	<form id="loanFrom" >
	     	 <input id="btaId" name="evaId" type="hidden"/><!-- 申请id -->
	         <input id="appNoAdd" name="appNo" type="hidden"/><!-- 申请编号 -->
	         <input id="applicantNo" name="applicantNo" type="hidden"/><!-- 申请人id-->
	         
	         <table class="table">
	        	<tr>
					<th>付款人:</th>
					<td colspan="2">
						<input name="payerNo" id="payerNo" type="text" class="easyui-textbox  easyui-combobox" style="width: 170px" />&nbsp;
					</td>
				</tr>
	          	<tr>
	         		<th>借款用途:</th>
					<td >
						<input type="radio" id="radio1" name="loanPurpose" value="1"  checked="checked"/>出差
						<input type="radio" id="radio2" name="loanPurpose" value="2" />其它支出
					</td>
	         	 </tr>
	         	 
	         	 <tr>
	         		<th>付款方式:</th>
					<td >
						<input type="radio" id="radio1" name="payMode" value="0"  checked="checked"/>支票
						<input type="radio" id="radio2" name="payMode" value="1" />现金
					</td>
	         	 </tr>
	         	 
				 <tr>
					<th style="width: 58px;">借款金额:</th>
					<td>
						<input id="loanAmt" name="loanAmt"  type="text" style="width: 50px;text-align: center;" value="0" class="easyui-numberbox easyui-validatebox " precision="2" data-options="required:true,editable:false">元</input>
					</td>
				 </tr>
				 
				<tr>
					<th>借款理由:</th>
					<td colspan="3">
					  <textarea id="loanReson" name="loanReson" class="easyui-textbox easyui-validatebox" style="width: 606px; height: 60px;resize:none;" data-options="required:true,validType:'length[0,100]'"></textarea>
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
				   	  <a href="javascript:void(0)" id="save" class="easyui-linkbutton" iconCls="icon-ok" onclick="toSaveBaseInfo('loanFrom');">保存</a>
			      	  <a href="javascript:void(0)" id="save" class="easyui-linkbutton"  iconCls="icon-edit" style="display:none;" onclick="editForm('loanFrom')">编辑</a>
				   </td>
				</tr>
	         </table>
	       </form>
	  </div>
	</div>
	
</div>
