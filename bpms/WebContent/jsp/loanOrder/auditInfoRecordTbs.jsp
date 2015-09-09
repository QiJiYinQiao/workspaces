<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<style type="text/css">
	#acceptTaskForm table input{border: none;}
	table {border-radius: 5px;}
	.linkSpan{
	  padding:5px;
	  display:-moz-inline-box;
	  display:inline-block;
	  width:40%; 
	  text-align: center;
	}
	.linkSpanS{
	  padding:5px;
	  display:-moz-inline-box;
	  display:inline-block;
	  width:10%; 
	  text-align: center;
	}
	a{text-decoration: none;}
	a:hover {
	 color: #FF0000;
	}
	.table th{
		text-align: right;
	}
	.table td{
		text-align: left;
	}	
	textarea{resize: none;}
</style>
<script type="text/javascript">
var contactSpouse;//联系人信息
var basicAuditInfo;//基本稽核信息
var companyAuditData;//公司稽核信息
var auditId;//稽核信息的ID
$(function(){
	//根据订单ID查询所有联系人信息及其稽核信息
	$.ajax({
		url : "contacts/contactsAction!findContactsByOid.action",
		data : {loanOrderId : $row.loanOrderId},
		type : "post",
		success : function(data) {
			if(data){
				$("#contacts-div").empty();
				$.each(data,function(i,item){
					//循环创建联系人表单
					if(item.relationship=="spouse"){
						contactSpouse = item;
					}else{
						createContactList(i,item);
					}
				});
			}
		}
	});
	
	//加载公司信息，稽核信息
	$.ajax({
		url : "company/companyAction!findCompanyByLoanerId.action",
		data : {loanOrderId : $row.loanOrderId},
		async: false,
		type : "post",
		success : function(data) {
			companyAuditData = data;
			$("#comitIversTel_upload_father_idDiv").empty();
			if(null!= companyAuditData && companyAuditData.length>0){
				$.each(companyAuditData,function(i,item){
					addACompany();
				});
				//获取所有 公司表单，填充数据 
				var comFormDivs = $("#comitIversTel_upload_father_idDiv").children();
				for(var i = 0 ; i < comFormDivs.length; i++){
					var formId = $("#"+comFormDivs[i].id+" form").attr("id");
					$("#"+formId+" input[name='comId']").val(companyAuditData[i].comId);
					$("#"+formId+" input[name='name']").val(companyAuditData[i].name);
					$("#"+formId+" input[name='regId']").val(companyAuditData[i].regId);
					$("#"+formId+" table input:eq(0)").attr({"style":"border\: none","readonly":"readonly"});
					$("#"+formId+" table input:eq(1)").attr({"style":"border\: none","readonly":"readonly"});
					var listId = $("#"+formId+" div:eq(1)").attr("id");
					//查询公司稽核信息，填充数据 
					var phoneAuditRecord = companyAuditData[i].phoneAuditRecord;
					var webAuditRecord = companyAuditData[i].webAuditRecord;
					var auditId =  companyAuditData[i].auditId;
					$("#"+formId+" textarea[name='phoneAuditRecord']").html(phoneAuditRecord);
					$("#"+formId+" textarea[name='webAuditRecord']").html(webAuditRecord);
					$("#"+formId+" input[name='auditId']").val(auditId);
					loadAttachmentList(listId,companyAuditData[i].auditId,$row.loanOrderId);
				}
			}else{
				addACompany();
			}
		}
	});  
	
	//查询稽核信息记录
	$.ajax({
		url : "auditInfoRecord/auditInfoRecordAction!findAIRByOid.action",
		data : {loanOrderId : $row.loanOrderId},
		type : "post",
		async: false,
		success : function(data) {
			basicAuditInfo = data;
			//循环对比稽核类型，加载对应表单稽核信息 
			if(basicAuditInfo){
				$.each(basicAuditInfo,function(i,item){
					if("audit_applicant"==item.auditItem){
						$("#loanerInfo-form").form('load',item);
					}
				});
			}
		}
	});
	
	
	
	var attachmentTypeCombo = $(".attachmentTypeCombo");
	for(var k = 0 ; k < attachmentTypeCombo.length;k++){
		var aTCId = attachmentTypeCombo[k].id;
		$("#"+aTCId+" input:first").combobox({
			valueField: 'code',
			textField: 'text',
			data : attempData,
			editable:false ,
		});
	}
	
	// 渲染稽核信息记录表
	$("#auditTabs").tabs({
		onSelect:function(title,index){
			if(index==0){
				$("#customerInfoAccordion").accordion({
					onSelect:function(title,index){
						if(index==0){
							// 渲染用户的名称
							$("#loanerInfo-form input[name='name']").val($row.name);
							// 渲染手机号码
							$("#loanerInfo-form input[name='contactMethod']").val($row.mobileTel);
							//获取客户附件
							auditId = $("#loanerInfo-form input:first").val();
							loadAttachmentList('customerAttList',auditId,$row.loanOrderId);
						}else if(index == 1){
							$("#spauseInfo-form").form("clear");
							$("#spauseInfo-form input[name='auditItem']").val("audit_spouse");
							//加载配偶信息
							if(null!=contactSpouse){
								$("#spauseInfo-form input[name='name']").val(contactSpouse.chName);
								$("#spauseInfo-form input[name='contactMethod']").val(contactSpouse.tel);
								if(null!=contactSpouse.auditId){
									$("#spauseInfo-form").form('load',contactSpouse);
								}
							}
							//获取配偶附件
							auditId = $("#spauseInfo-form input:first").val();
							loadAttachmentList('spauseAttList',auditId,$row.loanOrderId);
							
						}else if(index == 2){
							$("#contactWayInfoH-form").form("clear");
							$("#contactWayInfoC-form").form("clear");
							$("#contactWayInfoZP-form").form("clear");
							$("#contactWayInfoZT-form").form("clear");
							
							$("#contactWayInfoH-form input[name='auditItem']").val("audit_home_phone");
							$("#contactWayInfoC-form input[name='auditItem']").val("audit_workplace_phone");
							$("#contactWayInfoZP-form input[name='auditItem']").val("audit_cell_phone");
							$("#contactWayInfoZT-form input[name='auditItem']").val("audit_fix_phone");
							
							//渲染联系方式信息 - 用户住宅电话
							$("#contactWayInfoH-form input[name='contactMethod']").val($row.fixedTel);
							//渲染联系方式信息 - 征信手机 
							$("#contactWayInfoZP-form input[name='contactMethod']").val($row.mobileTel); 
							//渲染联系方式信息 - 征信电话 
							$("#contactWayInfoZT-form input[name='contactMethod']").val($row.fixedTel); 
							
							//循环对比稽核类型，加载对应表单稽核信息 
							if(basicAuditInfo){
								$.each(basicAuditInfo,function(i,item){
									if("audit_home_phone"==item.auditItem){
										$("#contactWayInfoH-form").form('load',item);
									}else if("audit_workplace_phone"==item.auditItem){
										$("#contactWayInfoC-form").form('load',item);
									}else if("audit_cell_phone"==item.auditItem){
										$("#contactWayInfoZP-form").form('load',item);
									}else if("audit_fix_phone"==item.auditItem){
										$("#contactWayInfoZT-form").form('load',item);
									}
								});
							}
							
							$("#contactWayInfoC-form input:eq(2)").val(companyAuditData[0].tele);
							
							//获取家庭固话附件
							auditId = $("#contactWayInfoH-form input:first").val();
							loadAttachmentList('contactWayHAttrList',auditId,$row.loanOrderId);
							
							//获取单位固话附件
							auditId = $("#contactWayInfoC-form input:first").val();
							loadAttachmentList('contactWayCAttrList',auditId,$row.loanOrderId);
							
							//获取征信手机附件
							auditId = $("#contactWayInfoZP-form input:first").val();
							loadAttachmentList('contactWayZPAttrList',auditId,$row.loanOrderId);
							
							//获取征信电话附件
							auditId = $("#contactWayInfoZT-form input:first").val();
							loadAttachmentList('contactWayZTAttrList',auditId,$row.loanOrderId);
							
						}
					}
				});
			}else if(index ==1 ){
				
			}else if(index == 2){
				$("#companyAccordion").accordion({
					onSelect:function(title,index){
						if(index == 0){
							
						}else if(index == 1){
							$("#companyAUP-form").form("clear");
							$("#companyADP-form").form("clear");
							//循环对比稽核类型，加载对应表单稽核信息 
							if(basicAuditInfo){
								$.each(basicAuditInfo,function(i,item){
									if("audit_upstream_partner"==item.auditItem){
										$("#companyAUP-form").form('load',item);
									}else if("audit_downstream_partner"==item.auditItem){
										$("#companyADP-form").form('load',item);
									}
								});
							}
							$("#companyAUP-form input[name='auditItem']").val("audit_upstream_partner");
							$("#companyADP-form input[name='auditItem']").val("audit_downstream_partner");
							
							//获取上游合作单位附件
							auditId = $("#companyAUP-form input:first").val();
							loadAttachmentList('companyAUPAttrList',auditId,$row.loanOrderId);
							
							//获取下游合作单位附件
							auditId = $("#companyADP-form input:first").val();
							loadAttachmentList('companyADPAttrList',auditId,$row.loanOrderId); 
						}
					}
				});
			}
		}
	});
});

	//创建联系人信息表单
	function createContactList(i,item){
		var formid = "contacts-form"+i;
		$("#contacts-form-model").clone()
		.attr("id",formid)
		.appendTo("#contacts-div");
		$("#"+formid+" span:first").remove();
		$("#"+formid+" input[name='name']").val(item.chName).attr({"class":"easyui-textbox easyui-validatebox","data-options":"required:true"});
		$("#"+formid+" input[name='contactMethod']").val(item.tel).attr({"class":"easyui-textbox easyui-validatebox"});
		$("#"+formid+" input[name='auditItem']").val("audit_"+item.relationship);
		$("#"+formid+" input[name='file']").attr("id","fileContacts"+i);
		$("#"+formid+" input[name='relationship']").val(item.relationshipName).attr("class","easyui-textbox");
		$("#"+formid+" textarea:eq(0)").attr({"class":"easyui-validatebox easyui-textbox","data-options":"required\:true"});
		$("#"+formid+" textarea:eq(1)").attr({"class":"easyui-validatebox easyui-textbox","data-options":"required\:true"});
		$("#"+formid+" div:eq(0)").children("a").attr({"class":"easyui-linkbutton","iconCls":"icon-save"});
		$("#"+formid+" div:eq(1)").attr("id","creditIversTelAttrList"+i);
		$("#"+formid+" div:eq(2)").attr("id","contacts_upload_add"+i);
		$("#"+formid+" div:eq(3)").attr("id","customerInfo_upload_father_idDiv"+i);
		$("#"+formid+" div:eq(4)").attr("id","contacts_upload_div"+i);
		$("#contacts_upload_div"+i+" a:first").attr({"class":"easyui-linkbutton","iconCls":"icon-add","plain":"true"});
		$("#contacts_upload_div"+i+" a:last").attr({"class":"easyui-linkbutton","iconCls":"icon-remove","plain":"true"});
		$("#contacts_upload_add"+i).next().children("a").attr("class","easyui-linkbutton");
		$("#"+formid+" textarea[name='phoneAuditRecord']").html(item.phoneAuditRecord);
		$("#"+formid+" textarea[name='webAuditRecord']").html(item.webAuditRecord);
		$("#"+formid+" input[name='auditId']").val(item.auditId);
		
		$.parser.parse('#'+formid); 
		
		$("#contacts_upload_div"+i+" input:first").attr("class","easyui-combobox");
		$("#contacts_upload_div"+i+" input:first").combobox({
			valueField : 'code',
			textField : 'text',
			data : attempData,
			editable:false ,
	    });
		
		//加载附件列表
		var listId = $("#"+formid+" div:eq(1)").attr("id");
		loadAttachmentList(listId,item.auditId,$row.loanOrderId); 
	}
			
	//增加一条公司信息
	function addACompany(obj) {
		j++;
		var divId = "companyInfoModelDiv_"+j;
		var formId = "companyInfoModel_"+j;
		$("#companyInfoModel").clone().attr({"id":divId,"style":""}).appendTo("#comitIversTel_upload_father_idDiv");
		$("#"+divId).children("form").attr("id",formId);
		$("#"+formId+" span:first").remove();
		$("#"+formId+" a:eq(0)").attr({"class":"easyui-linkbutton","iconCls":"icon-add","plain":"true"});			
		$("#"+formId+" a:eq(1)").attr({"class":"easyui-linkbutton","iconCls":"icon-remove","plain":"true"});			
		$("#"+formId+" table input:eq(0)").attr({"class":"easyui-textbox easyui-validatebox","data-options":"required:true"});			
		$("#"+formId+" table input:eq(1)").attr({"class":"easyui-textbox easyui-validatebox","data-options":"required:true"});			
		$("#"+formId+" table textarea:eq(0)").attr({"class":"easyui-validatebox easyui-textbox","data-options":"required\:true"});			
		$("#"+formId+" table textarea:eq(1)").attr({"class":"easyui-validatebox easyui-textbox","data-options":"required\:true"});			
		$("#"+formId+" a:eq(2)").attr({"class":"easyui-linkbutton","iconCls":"icon-save"});
		$("#"+formId+" div:eq(1)").attr("id","companyInfoAttrList"+j);
		$("#"+formId+" div:eq(2)").attr("id","companyInfo_upload_add"+j);
		$("#"+formId+" div:eq(3)").attr("id","companyInfo_upload_father_idDiv"+j);
		$("#"+formId+" div:eq(4)").attr("id","companyInfo_upload_div"+j);
		$("#companyInfo_upload_div"+j+" input:eq(0)").attr("class","easyui-combobox");
		$("#companyInfo_upload_div"+j+" input:eq(2)").attr({"class":"easyui-textbox","id":"fileCompanyInfo"+j});
		$("#companyInfo_upload_div"+j+" a:eq(0)").attr({"class":"easyui-linkbutton","iconCls":"icon-add","plain":"true"});			
		$("#companyInfo_upload_div"+j+" a:eq(1)").attr({"class":"easyui-linkbutton","iconCls":"icon-remove","plain":"true"});			
		$("#"+formId+" a:last").attr("class","easyui-linkbutton");
		
		$.parser.parse('#'+formId); 
		
	    $("#companyInfo_upload_div"+j+" input:eq(0)").combobox({
			valueField : 'code',
			textField : 'text',
			data : attempData,
			editable:false ,
	    }); 
	}

	//删除一条公司信息
	function removeACompany(obj) {
		var pDiv = obj.parentElement.parentElement;
		var ppDiv = $(obj).parent().parent().parent();
		var pFormId = $(obj).parent().attr("id");//当前formID
		var auditId = $("#"+pFormId+" input[name='auditId']").val();//当前稽核信息ID
		var comId = $("#"+pFormId+" input[name='comId']").val();//当前公司ID
		var attidList = "";//当前公司下的所有附件ID
		
		var divListId = $("#"+pFormId+" div:eq(1)").attr("id");
		var inputs = $("#"+divListId+" input");
		for(var i = 0 ; i < inputs.length; i++){
			var attid = inputs[i].value;
			attidList += attid+",";
		}
		
		if(comId==""){
			 $(pDiv).remove();
			 if (ppDiv.children().length == 0) {
    			   addACompany();
   			  }
		}else{
			$.messager.confirm('提示','确认删除公司信息及所有附件?',function(r){   
			    if (r){   
			    	$.ajax({
				    	   url:'auditInfoRecord/auditInfoRecordAction!delCompanyAuditAndAtt.action',
				    	   data:{"auditId":auditId,"comId":comId,"attidList":attidList,"loanOrderId":$row.loanOrderId},
				    	   type:"post",
				    	   success:function(data){
				    		   $.messager.alert("提示","删除成功!");
				    		   $(pDiv).remove();
				    		   if (ppDiv.children().length == 0) {
				    			   addACompany();
				   			   }
				    	   },
				    	   error:function(){
				    		   $.messager.alert("提示","删除失败!");
				    	   }
				       });
			    }   
			});
		}
		
	}
		
   //保存稽核信息
	function saveAuditInfo(obj) {
		var id = obj.parentElement.parentElement.id;
		$('#' + id).form('submit',{
			url : "auditInfoRecord/auditInfoRecordAction!saveAuditInfoRecord.action",
			onSubmit: function(param){
				var isValid = $(this).form('validate');
				if (isValid){
					param.loanOrderId = $row.loanOrderId;
				}
				return isValid;	//  返回false将停止form提交 
			},
			success : function(data) {
				if(data){
					data = $.parseJSON(data);
					$("#" + id + " input:first").val(data.data.air.auditId); 
					if(data.data.company){
						$("#" + id + " input[name='comId']").val(data.data.company.comId); 
					}
					$.messager.show({
						title:data.title,
						msg:data.message,
						timeout:5000,
						showType:'slide'
					});
				}
			}
		});
	}
	
	//填写稽核信息对话框关闭后刷新
	function airdClose(){
		$("#auditInfoRecordDialog").dialog('refresh');
	}
</script>
<!-- 稽核信息记录 S -->
<div id="auditTabs" class="easyui-tabs" style="fit: true">
	<div title="客户信息核查记录">
		<div id="customerInfoAccordion" class="easyui-accordion" style="fit: true;height:580px;">
			<div id="defaultAccordion" title="【客户信息】" data-options="selected:true" style="overflow: auto; padding: 10px;">
				<form id="loanerInfo-form" method="post">
					<input name="auditId" type="hidden"/>
					<input name="auditItem" value="audit_applicant" type="hidden" />
					<table class="table">
						<tr>
							<th>姓名:</th>
							<td><input style="border: none;" disabled="disabled" type="text" name="name" class="easyui-textbox easyui-validatebox"/></td>
							<th>手机号:</th>
							<td><input style="border: none;" disabled="disabled" type="text" class="easyui-textbox easyui-validatebox" name="contactMethod"/></td>
						</tr>
						<tr>
							<th>电核内容记录:</th>
							<td colspan="3">
								<textarea name="phoneAuditRecord" style="width: 550px; height: 80px;" class="easyui-validatebox easyui-textbox" data-options="required:true"></textarea>
							</td>
						</tr>
						<tr>
							<th>网核内容记录:</th>
							<td colspan="3">
								<textarea name="webAuditRecord" style="width: 550px; height: 80px;" class="easyui-validatebox easyui-textbox" data-options="required:true"></textarea>
							</td>
						</tr>
					</table>
					<div style="width: 100%; height: 30px; text-align: right;float:left;">
						<a href="javascript:void(0);" onclick="saveAuditInfo(this);" class="easyui-linkbutton" iconCls="icon-save">保存</a>
					</div>
					<div id="customerAttList" style="width:100%;display:block;float:left;">
					</div>
					<div id="customerInfo_upload_add">
						<div id="customerInfo_upload_father_idDiv">
							<div id="customerInfo_upload_div" class="attachmentTypeCombo">
								<font size="2" style="font-weight: bold;"><font size="2" style="font-weight: bold;">上传附件</font></font>
								<input class="easyui-combobox"/>
								<input name="fileName" type="text" placeholder="请输入附件名">
								<input id="fileCustomer" name="file" type="file" onchange="fileChange(this);" style="width:205px;"> 
								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addACredential(this);">添加</a>
								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeACredential(this);">删除</a> 
							</div>
						</div>
					</div>
					<div style="width: 100%; height: 30px; text-align: right;">
						<a href="javascript:void(0);" class="easyui-linkbutton" onclick="fileUploads(this)">上传附件</a>
					</div>
				</form>
			</div>
			
			<!-- 客户的配偶信息 -->
			<div title="【客户配偶信息】" style="padding: 10px;">
				<form id="spauseInfo-form" method="post">
					<input name="auditId" type="hidden"/>
					<input name="auditItem" value="audit_spouse" type="hidden" />
					<table class="table">
						<tr>
							<th>姓名:</th>
							<td><input style="border: none;background-color:#EBEBE4;" readonly="readonly"  data-options="required:true" type="text" name="name" class="easyui-textbox easyui-validatebox" /></td>
							<th>手机号:</th>
							<td><input style="border: none;background-color:#EBEBE4;" readonly="readonly"  type="text" name="contactMethod" class="easyui-textbox easyui-validatebox" data-options="required:true"/></td>
						</tr>
						<tr>
							<th>电核内容记录:</th>
							<td colspan="3"><textarea name="phoneAuditRecord" style="width: 550px; height: 80px;" class="easyui-validatebox easyui-textbox" data-options="required:true"></textarea></td>
						</tr>
						<tr>
							<th>网核内容记录:</th>
							<td colspan="3"><textarea name="webAuditRecord" style="width: 550px; height: 80px;" class="easyui-validatebox easyui-textbox" data-options="required:true"></textarea></td>
						</tr>
					</table>
					<div style="width: 100%; height: 30px; text-align: right;">
						<a href="javascript:void(0);" onclick="saveAuditInfo(this);" class="easyui-linkbutton" iconCls="icon-save">保存</a>
					</div>
					<div id="spauseAttList" style="width:100%;display:block;float:left;">
					</div>
					<div id="mateInfo_upload_add">
						<div id="mateInfo_upload_father_idDiv">
							<div id="mateInfo_upload_div" class="attachmentTypeCombo">
								<font size="2" style="font-weight: bold;"><font size="2" style="font-weight: bold;">上传附件　　　</font></font>
								<input class="easyui-combobox"/>
								<input name="fileName" type="text" placeholder="请输入附件名">
								<input id="fileMateInfo" name="file" type="file"  onchange="fileChange(this);" style="width:205px;"> 
								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addACredential(this);">添加</a>
								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeACredential(this);">删除</a>
							</div>
						</div>
					</div>
					<div style="width: 100%; height: 30px; text-align: right;">
						<a href="javascript:void(0);" class="easyui-linkbutton" onclick="fileUploads(this)">上传附件</a>
					</div>
				</form>
			</div>

			<!-- 联系方式 -->
			<div title="【联系方式信息】" style="padding: 10px;">
				<form id="contactWayInfoH-form" method="post">
					<input name="auditId" type="hidden"/>
					<input name="auditItem" value="audit_home_phone" type="hidden" />
					<table class="table">
						<tr>
							<th>1、家庭固话:</th>
							<td><input style="border: none;background-color:#EBEBE4;" readonly="readonly" data-options="required:true" name="contactMethod" type="text" class="easyui-textbox easyui-validatebox" /></td>
						</tr>
						<tr>
							<th>电核内容记录:</th>
							<td colspan="3"><textarea name="phoneAuditRecord" style="width: 550px;" class="easyui-validatebox easyui-textbox" data-options="required:true"></textarea></td>
						</tr>
						<tr>
							<th>网核内容记录:</th>
							<td colspan="3"><textarea name="webAuditRecord" style="width: 100%;" class="easyui-validatebox easyui-textbox" data-options="required:true"></textarea></td>
						</tr>
					</table>
					<div style="width: 100%; height: 30px; text-align: right;">
						<a href="javascript:void(0);" onclick="saveAuditInfo(this);" class="easyui-linkbutton" iconCls="icon-save">保存</a>
					</div>
					<div id="contactWayHAttrList" style="width:100%;display:block;float:left;"></div>
					<div id="homeInfo_upload_add">
						<div id="homeInfo_upload_father_idDiv">
							<div id="homeInfo_upload_div" class="attachmentTypeCombo">
								<font size="2" style="font-weight: bold;">上传附件　　　</font>
								<input class="easyui-combobox"/>
								<input name="fileName" type="text" placeholder="请输入附件名">
								<input id="fileHomeInfo" name="file" type="file"  onchange="fileChange(this);" style="width:205px;"> 
								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addACredential(this);">添加</a>
								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeACredential(this);">删除</a>
							</div>
						</div>
					</div>
					<div style="width: 100%; height: 30px; text-align: right;">
						<a href="javascript:void(0);" class="easyui-linkbutton" onclick="fileUploads(this)">上传附件</a>
					</div>
				</form>
				<hr>
				<form id="contactWayInfoC-form" method="post">
					<input name="auditId" type="hidden"/>
					<input name="auditItem" value="audit_workplace_phone" type="hidden" />
					<table class="table">
						<tr>
							<th>2、单位固话:</th>
							<td><input style="border: none;background-color:#EBEBE4;" readonly="readonly" data-options="required:true" name="contactMethod" type="text" class="easyui-textbox easyui-validatebox" /></td>
							<th>114查询记录:</th>
							<td><textarea class="easyui-textbox" name="query114" style="width: 300px;"></textarea>
							</td>
						</tr>
						<tr>
							<th>电核内容记录:</th>
							<td colspan="3"><textarea name="phoneAuditRecord" style="width: 550px;" class="easyui-validatebox easyui-textbox" data-options="required:true"></textarea></td>
						</tr>
						<tr>
							<th>网核内容记录:</th>
							<td colspan="3"><textarea name="webAuditRecord" style="width: 550px;" class="easyui-validatebox easyui-textbox" data-options="required:true"></textarea></td>
						</tr>
					</table>
					<div style="width: 100%; height: 30px; text-align: right;">
						<a href="javascript:void(0);" onclick="saveAuditInfo(this);" class="easyui-linkbutton" iconCls="icon-save">保存</a>
					</div>
					<div id="contactWayCAttrList" style="width:100%;display:block;float:left;"></div>
					<div id="comPho_upload_add">
						<div id="comPho_upload_father_idDiv">
							<div id="comPho_upload_div" class="attachmentTypeCombo">
								<font size="2" style="font-weight: bold;">上传附件　　　</font>
								<input class="easyui-combobox"/>
								<input name="fileName" type="text" placeholder="请输入附件名">
								<input id="fileComPho" name="file" type="file"  onchange="fileChange(this);" style="width:205px;"> 
								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addACredential(this);">添加</a>
								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeACredential(this);">删除</a>
							</div>
						</div>
					</div>
					<div style="width: 100%; height: 30px; text-align: right;">
						<a href="javascript:void(0);" class="easyui-linkbutton" onclick="fileUploads(this)">上传附件</a>
					</div>
				</form>
				<hr>
				<form id="contactWayInfoZP-form" method="post">
					<input name="auditId" type="hidden"/>
					<input name="auditItem" value="audit_cell_phone" type="hidden" />
					<table class="table">
						<tr>
							<th>3、征信手机:</th>
							<td><input name="contactMethod" type="text" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'mobile'"/></td>
						</tr>
						<tr>
							<th>电核内容记录:</th>
							<td colspan="3"><textarea name="phoneAuditRecord" style="width: 550px;" class="easyui-validatebox easyui-textbox" data-options="required:true"></textarea></td>
						</tr>
						<tr>
							<th>网核内容记录:</th>
							<td colspan="3"><textarea name="webAuditRecord" style="width: 550px;" class="easyui-validatebox easyui-textbox" data-options="required:true"></textarea></td>
						</tr>
					</table>
					<div style="width: 100%; height: 30px; text-align: right;">
						<a href="javascript:void(0);" onclick="saveAuditInfo(this);" class="easyui-linkbutton" iconCls="icon-save">保存</a>
					</div>
					<div id="contactWayZPAttrList" style="width:100%;display:block;float:left;"></div>
					<div id="creditInversPho_upload_add">
						<div id="creditInversPho_upload_father_idDiv">
							<div id="creditInversPho_upload_div" class="attachmentTypeCombo">
							<font size="2" style="font-weight: bold;">上传附件　　　</font>
							<input class="easyui-combobox"/>
							<input name="fileName" type="text" placeholder="请输入附件名">
							<input id="fileCreditInversPho" name="file" type="file"  onchange="fileChange(this);" style="width:205px;"> 
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addACredential(this);">添加</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeACredential(this);">删除</a>
							</div>
						</div>
					</div>
					<div style="width: 100%; height: 30px; text-align: right;">
						<a href="javascript:void(0);" class="easyui-linkbutton" onclick="fileUploads(this)">上传附件</a>
					</div>
				</form>
				<hr>
				<form id="contactWayInfoZT-form" method="post">
					<input name="auditId" type="hidden"/>
					<input name="auditItem" value="audit_fix_phone" type="hidden" />
					<table class="table">
						<tr>
							<th>4、征信电话:</th>
							<td><input name="contactMethod" type="text" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'phone'"/></td>
						</tr>
						<tr>
							<th>电核内容记录:</th>
							<td colspan="3"><textarea  name="phoneAuditRecord" style="width: 550px;" class="easyui-textbox easyui-validatebox" data-options="required:true"></textarea></td>
						</tr>
						<tr>
							<th>网核内容记录:</th>
							<td colspan="3"><textarea name="webAuditRecord" style="width: 550px;" class="easyui-textbox easyui-validatebox" data-options="required:true"></textarea></td>
						</tr>
					</table>
					<div style="width: 100%; height: 30px; text-align: right;">
						<a href="javascript:void(0);" onclick="saveAuditInfo(this);" class="easyui-linkbutton" iconCls="icon-save">保存</a>
					</div>
					<div id="contactWayZTAttrList" style="width:100%;display:block;float:left;"></div>
					<div id="creditInversTel_upload_add">
						<div id="creditInversTel_upload_father_idDiv">
							<div id="creditInversTel_upload_div" class="attachmentTypeCombo">
								<font size="2" style="font-weight: bold;">上传附件　　　</font>
								<input class="easyui-combobox"/>
								<input name="fileName" type="text" placeholder="请输入附件名">
								<input id="fileCreditInversTel" name="file" type="file"  onchange="fileChange(this);" style="width:205px;"> 
								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addACredential(this);">添加</a>
								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeACredential(this);">删除</a>
							</div>
						</div>
					</div>
					<div style="width: 100%; height: 30px; text-align: right;">
						<a href="javascript:void(0);" class="easyui-linkbutton" onclick="fileUploads(this)">上传附件</a>
					</div>
				</form>
			</div>
		</div>
	</div>

	<div title="联系人信息核查记录">
		<div id="contacts-div" style="width:98%;height:560px;padding:10px 5px;overflow: auto;">
			<div style="width: 100%;height:300px;text-align: center;padding-top:250px;">
				<font size="6" style="font-weight: bold;box-shadow: 3px 3px 5px 3px;">
					暂无联系人信息
				</font>
			</div>
		</div>
		<!-- 联系人信息模板 S -->
		<div id="contacts-form-model-div" style="display: none;">
			<!-- 表单模板 -->
			<form id="contacts-form-model" method="post">
				<input name="auditId" type="hidden"/>
				<table class="table" cellpadding="5px;">
					<tr>
						<th>姓名:</th>
						<td><input style="border: none;background-color:#EBEBE4;" readonly="readonly" type="text" name="name" /></td>
						<th>手机号:</th>
						<td><input style="border: none;background-color:#EBEBE4;" readonly="readonly" type="text" name="contactMethod" /></td>
						<th>与申请人关系:</th>
						<td>
							<input style="border: none;background-color:#EBEBE4;" readonly="readonly"  name="relationship" />
							<input type="hidden" name="auditItem" />
						</td>
					</tr>
					<tr>
						<th>电核内容记录:</th>
						<td colspan="5"><textarea name="phoneAuditRecord" style="width: 100%;"></textarea></td>
					</tr>
					<tr>
						<th>网核内容记录:</th>
						<td colspan="5"><textarea name="webAuditRecord" style="width: 100%;"></textarea></td>
					</tr>
				</table>
				<div style="width: 100%; height: 30px; text-align: right;">
					<a href="javascript:void(0);" onclick="saveAuditInfo(this);">保存</a>
				</div>
				<div id="creditIversTelAttrList" style="width:100%;display:block;float:left;"></div>
				<div id="contacts_upload_add">
					<div id="contacts_upload_father_idDiv">
						<div id="contacts_upload_div" class="attachmentTypeCombo">
							<font size="2" style="font-weight: bold;">上传附件　　　</font>
							<input />
							<input name="fileName" type="text" placeholder="请输入附件名">
							<input id="fileContacts" name="file" type="file"  onchange="fileChange(this);" style="width:205px;" > 
							<a href="javascript:void(0);" onclick="addACredential(this);">添加</a>
							<a href="javascript:void(0);" onclick="removeACredential(this);">删除</a>
						</div>
					</div>
				</div>
				<div style="width: 100%; height: 30px; text-align: right;">
					<a href="javascript:void(0);" onclick="fileUploads(this)">上传附件</a>
				</div>
			</form>
		</div>
	<!-- 联系人信息模板 E -->
	</div>

	<!-- 企业信息核查记录 -->
	<div title="企业信息核查记录">
		<div id="companyAccordion" class="easyui-accordion" style="fit: true;height:580px;">
			<div title="【公司信息】"　style="padding:10px;">
				<div id="comitIversTel_upload_add">
					<div id="comitIversTel_upload_father_idDiv">
					</div>
				</div>
			</div>
			<div title="【合作单位信息】" style="padding:10px;">
				<form id="companyAUP-form" method="post">
					<input name="auditId" type="hidden"/>
					<input name="auditItem" value="audit_upstream_partner" type="hidden" />
					<table class="table" cellpadding="5px;">
						<tr>
							<th>上游合作单位名称:</th>
							<td><input class="easyui-textbox easyui-validatebox" data-options="required:true" type="text" name="name" /></td>
							<th>单位固话:</th>
							<td><input class="easyui-textbox easyui-validatebox" data-options="required:true" type="text" name="contactMethod" /></td>
						</tr>
						<tr>
							<th>电核内容记录:</th>
							<td colspan="5"><textarea name="phoneAuditRecord" style="width: 100%;" class="easyui-validatebox easyui-textbox" data-options="required:true"></textarea></td>
						</tr>
						<tr>
							<th>网核内容记录:</th>
							<td colspan="5"><textarea name="webAuditRecord" style="width: 100%;" class="easyui-validatebox easyui-textbox" data-options="required:true"></textarea></td>
						</tr>
					</table>
					<div style="width: 100%; height: 30px; text-align: right;">
						<a href="javascript:void(0);" onclick="saveAuditInfo(this);" class="easyui-linkbutton" iconCls="icon-save">保存</a>
					</div>
					<div id="companyAUPAttrList" style="width:100%;display:block;float:left;"></div>
					<div id="companyAUP_upload_add">
						<div id="companyAUP_upload_father_idDiv">
							<div id="companyAUP_upload_div" class="attachmentTypeCombo">
								<font size="2" style="font-weight: bold;">　　上传附件　　　</font>
								<input class="easyui-combobox"/>
								<input name="fileName" type="text" placeholder="请输入附件名">
								<input id="fileCompanyAUP" name="file" type="file"  onchange="fileChange(this);" style="width:205px;"> 
								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addACredential(this);">添加</a>
								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeACredential(this);">删除</a>
							</div>
						</div>
					</div>
					<div style="width: 100%; height: 30px; text-align: right;">
						<a href="javascript:void(0);" class="easyui-linkbutton" onclick="fileUploads(this)">上传附件</a>
					</div>
				</form>
				
				<hr>

				<form id="companyADP-form" method="post">
					<input name="auditId" type="hidden"/>
					<input name="auditItem" value="audit_downstream_partner"
						type="hidden" />
					<table class="table" cellpadding="5px;">
						<tr>
							<th>下游合作单位名称:</th>
							<td><input class="easyui-textbox easyui-validatebox" data-options="required:true" type="text" name="name" /></td>
							<th>单位固话:</th>
							<td><input class="easyui-textbox easyui-validatebox" data-options="required:true" type="text" name="contactMethod" /></td>
						</tr>
						<tr>
							<th>电核内容记录:</th>
							<td colspan="5"><textarea  name="phoneAuditRecord" class="easyui-validatebox easyui-textbox" data-options="required:true" 
									style="width: 100%;"></textarea></td>
						</tr>
						<tr>
							<th>网核内容记录:</th>
							<td colspan="5"><textarea  name="webAuditRecord" class="easyui-validatebox easyui-textbox" data-options="required:true" 
									style="width: 100%;"></textarea></td>
						</tr>
					</table>
					<div style="width: 100%; height: 30px; text-align: right;">
						<a href="javascript:void(0);" onclick="saveAuditInfo(this);" class="easyui-linkbutton" iconCls="icon-save">保存</a>
					</div>
					<div id="companyADPAttrList" style="width:100%;display:block;float:left;"></div>
					<div id="companyADP_upload_add">
						<div id="companyADP_upload_father_idDiv">
							<div id="companyADP_upload_div" class="attachmentTypeCombo">
								<font size="2" style="font-weight: bold;">　　上传附件　　　</font>
								<input class="easyui-combobox"/>
								<input name="fileName" type="text" placeholder="请输入附件名">
								<input id="fileCompanyADP" name="file" type="file"  onchange="fileChange(this);" style="width:205px;"> 
								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addACredential(this);">添加</a>
								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeACredential(this);">删除</a>
							</div>
						</div>
					</div>
					<div style="width: 100%; height: 30px; text-align: right;">
						<a href="javascript:void(0);" class="easyui-linkbutton" onclick="fileUploads(this)">上传附件</a>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<!-- 稽核信息记录 E -->		

<!-- 公司信息模板S -->
<div id="companyInfoModel" style="display: none;">
	<form id="companyInfo-formasdf" method="post">
		<input name="auditId" type="hidden"/>
		<input name="auditItem" value="audit_company" type="hidden" />
		<input name="comId" type="hidden" />
		<a href="javascript:void(0);" onclick="addACompany(this);">添加公司信息</a>
		<a href="javascript:void(0);" onclick="removeACompany(this);">删除公司信息</a>
		<table  class="table" cellpadding="5px;">
			<tr>
				<th>公司名称:</th>
				<td><input name="name"/></td>
				<th>注册号:</th>
				<td><input type="text" name="regId" /></td>
			</tr>
			<tr>
				<th>电核内容记录:</th>
				<td colspan="5"><textarea  name="phoneAuditRecord" style="width: 650px;"></textarea></td>
			</tr>
			<tr>
				<th>网核内容记录:</th>
				<td colspan="5"><textarea  name="webAuditRecord" style="width: 650px;"></textarea></td>
			</tr>
		</table>
		<div style="width: 100%; height: 30px; text-align: right;">
			<a href="javascript:void(0);" onclick="saveAuditInfo(this);">保存</a>
		</div>
		<div id="companyInfoAttr" style="width:100%;display:block;float:left;"></div>
		<div id="companyInfos_upload_add">
			<div id="companyInfos_upload_father_idDiv">
				<div id="companyInfos_upload_div" >
					<font size="2" style="font-weight: bold;">上传附件　　　</font>
					<input />
					<input name="fileName" type="text" placeholder="请输入附件名">
					<input id="fileCompanyInfos" name="file" type="file"  onchange="fileChange(this);" style="width:205px;"> 
					<a href="javascript:void(0);"  onclick="addACredential(this);">添加</a>
					<a href="javascript:void(0);"  onclick="removeACredential(this);">删除</a>
				</div>
			</div>
		</div>
		<div style="width: 100%; height: 30px; text-align: right;">
			<a href="javascript:void(0);" onclick="fileUploads(this)">上传附件</a>
		</div>
	</form>
</div>
<!-- 公司信息模板E -->
