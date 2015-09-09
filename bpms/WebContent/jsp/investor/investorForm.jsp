<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/validate.js"></script>
<style>
.easyui-textbox {
	height: 18px;
	width: 170px;
	line-height: 16px;
	box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset;
	transition: border 0.2s linear 0s, box-shadow 0.2s linear 0s;
}

textarea:focus, input[type="text"]:focus {
	border-color: rgba(82, 168, 236, 0.8);
	box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px
		rgba(82, 168, 236, 0.6);
	outline: 0 none;
}

.table {
	background-color: transparent;
	border-collapse: collapse;
	border-spacing: 0;
	max-width: 100%;
}
input, textarea {
	font-weight: normal;
}

td {
	text-align: left;
	padding: 6px;
}
th{
    text-align: right;
	padding: 6px;
}
</style>
<script type="text/javascript">
    var provinceArr = jqueryUtil.getAreaTextArr(1);//获取省
    var relationshipArr;//与本人关系
	$(function(){
		$('#tt').tabs({    
		    border:false    
		}); 
		//性别
		$("input[name='genderType']").combobox({
			url : "common/commonAction!findTextArr.action?codeMyid=gender_type",
			valueField : 'code',
			textField : 'text'
		});
		//婚姻状况
	    $("input[name='maritalStatus']").combobox({
			url:"common/commonAction!findTextArr.action?codeMyid=marriage_type",
			valueField: 'code',
		 	textField: 'text'
	    });
 	    //证件类型
	    $("input[name='idType']").combobox({
			url:"common/commonAction!findTextArr.action?codeMyid=id_Type",
			valueField: 'code',
		 	textField: 'text'
	    }); 
	    //职业
	    $("input[name='jobType']").combobox({
			url:"common/commonAction!findTextArr.action?codeMyid=job_type",
			valueField: 'code',
		 	textField: 'text'
	    });
	    //学历
	    $("input[name='degreeType']").combobox({
			url:"common/commonAction!findTextArr.action?codeMyid=degree_Type",
			valueField: 'code',
		 	textField: 'text'
	    });
	    //单位规模
	    $("input[name='compScale']").combobox({
			url:"common/commonAction!findTextArr.action?codeMyid=company_scale_type",
			valueField: 'code',
		 	textField: 'text'
	    });
	    //是否有子女
	    $("input[name$='familyStatus']").combobox({
			url:"common/commonAction!findTextArr.action?codeMyid=has_child",
			valueField: 'code',
			textField: 'text'
		});
		//与本人关系
	    $("input[name='relationship']").combobox({
			url:"common/commonAction!findTextArr.action?codeMyid=relationship_type",
			valueField: 'code',
			textField: 'text',
			async: false,
			onLoadSuccess:function(){
				relationshipArr = $(this).combobox('getData');
			}
		});
	    //家庭收入情况
	    $("input[name$='famIncSta']").combobox({
			url:"common/commonAction!findTextArr.action?codeMyid=fam_inc_sta",
			valueField: 'code',
			textField: 'text'
		});
	    //渲染基本信息省市县
	    renderProvinceSelect("provinceId","cityId","areaId");
	    //渲染紧急联系人地址
	    renderProvinceSelect("linkPeopleProvinceId","linkPeopleCityId","linkPeopleAreaId");
	    //渲染紧急联系人列表
	    initLinkPeopleGrid();
	})
	//省
	function renderProvinceSelect(pid,cid,aid){
		$.ajax({
			type:"POST",
			url:'common/commonAction!findAreaDic.action?parentId=1',
			dataType:"json",
			async:false,
			success:function(iJson){
				var arr = eval(iJson);
				$('#'+pid).combobox({   
					data:arr,
		            valueField:'code',   
		            textField:'text',
		            editable:false,
		            onLoadSuccess:function(){
		            	var indexOneVal = $("#"+pid).combobox("getValue");//获取一级栏目值
						if(indexOneVal != null && indexOneVal != ""){
							//渲染二级栏目下拉框
							renderCitySelect(cid,aid,indexOneVal);
			            }
		            },
		            onChange:function(newvalue,oldvalue){
		                //渲染二级栏目下拉框
		            	renderCitySelect(cid,aid,newvalue);
		            	$("#"+cid).combobox("setValue","");
		            	$("#"+aid).combobox("setValue","");
					}
		        }); 
			}
		});
	}
	//市
	function renderCitySelect(cid,aid,pvalue){
		$.ajax({
			type:"POST",
			url: 'common/commonAction!findAreaDic.action?parentId='+pvalue,
			dataType:"json",
			async:false,
			success:function(iJson){
				var arr = eval(iJson);
				$('#'+cid).combobox({   
					data:arr,
		            valueField:'code',   
		            textField:'text',
		            editable:false,
		            onLoadSuccess:function(){
						var indexTwoVal = $("#"+cid).combobox("getValue");//获取二级栏目已选择的值
						if(indexTwoVal != null && indexTwoVal != ""){
							renderAreaSelect(aid,indexTwoVal);
						}
	                },
		            onChange:function(newvalue,oldvalue){
		            	renderAreaSelect(aid,newvalue);
		            	$("#"+aid).combobox("setValue","");
					}
		        }); 
			}
		});
	}
    //县
	function renderAreaSelect(aid,cvalue){
		$.ajax({
			type:"POST",
			url: 'common/commonAction!findAreaDic.action?parentId='+cvalue,
			dataType:"json",
			async:false,
			success:function(iJson){
				var arr = eval(iJson);
				$('#'+aid).combobox({   
					data:arr,
		            valueField:'code',   
		            textField:'text',
		            editable:false
		        }); 
			}
		});
	}
    //渲染紧急联系人列表
    function initLinkPeopleGrid(investorId){
    	var uri = "";
    	if(investorId != null){
    		uri = "contacts/contactsAction!findAllList.action?loanerId="+investorId
    	}else{
    		uri = "contacts/contactsAction!findAllList.action";
    	}
    	$("#linkPeopleGrid").datagrid({
    		url : uri,
    		width : 875,
    		height : 250,
    		pagination:false,
    		rownumbers:true,
    		border:true,
    		singleSelect:false,
    		nowrap:true,
    		multiSort:false,
    		columns : [ [
                         {field : 'ck',rowspan:2,checkbox : true},
    		             {field : 'chName',title : '姓名',width : 80,rowspan:2,align : 'center'},
    		             {field : 'relationship',title : '与本人关系',width : 80,rowspan:2,align : 'center',formatter:function(value,row,index){
    		            	 return jqueryUtil.showText(value,relationshipArr);
    		             }},
    		             {field : 'tel',title : '移动电话',width : 140,rowspan:2,align : 'center'},
    		             {field : 'idNo',title : '证件号码',width : 120,rowspan:2,align : 'center'},
    		             {title : '通讯地址',width : 340,colspan:4,align : 'center'}
    		],[
    					 {field : 'compProvince',title : '省',width : 80,align : 'center',formatter:function(value,row,index){
    						 return jqueryUtil.showText(value,provinceArr);
    					 }},
    					 {field : 'compCity',title : '市',width : 80,align : 'center',formatter:function(value,row,index){
    						 var cityArr = jqueryUtil.getAreaTextArr(row.compProvince);
    						 return jqueryUtil.showText(value,cityArr);
    					 }},
    					 {field : 'compArea',title : '县/区',width : 80,align : 'center',formatter:function(value,row,index){
    						 var areaArr = jqueryUtil.getAreaTextArr(row.compCity);
    						 return jqueryUtil.showText(value,areaArr);
    					 }},
    					 {field : 'compAddrDetails',title : '详细地址',width : 200,align : 'center'}
    		]],
    		toolbar: [{
    			iconCls: 'icon-edit',
    			text:'编辑',
    			handler: toEditContact
    		},"-",{
    			iconCls: 'icon-cut',
    			text:'删除',
    			handler:toDelete
    		},'-',{
    			iconCls: 'icon-save',
    			text:'保存',
    			handler: toSaveInvestorAndContacts
    		}]
    	});
    }
    //保存贷款人客户基本信息
    function saveInvestor(){
    	var isValid = $("#baseInfoForm").form('validate');
    	if(!isValid){
    		return false;
    	}
    	$.ajax({
    		type: "POST",
    		url:"investor/investorAction!persistenceInvestorDlg.action",
    		data:$('#baseInfoForm').serialize(),
    		async: false,//默认true设置下，所有请求均为异步请求
    		cache: true,
    	    success: function(iJson) {
    	    	if(iJson.status){
    	    		$("#investorId").val(iJson.data);//保存成功保存投资人id
    	    		disableForm();//禁用form
    	    	}
    	    	parent.$.messager.show({
    				title : '提示',
    				msg : iJson.message,
    				timeout : 4000 * 2
    			}); 
    	    }
    	});
    }
    //禁用form表单
    function disableForm(){
    	$("#saveId").hide();//保存按钮影藏
    	$("#editId").show();//修改按钮显示出来
    	$("#rsetId").hide();//重置按钮隐藏
    	$("#sign").val("save");//设为为保存状态
    	$("#baseInfoForm input[class^='easyui-']").each(function(i){
    		if($(this).hasClass("easyui-textbox easyui-validatebox")){
    			$(this).attr("disabled",true);
    		}else if($(this).hasClass("easyui-datebox")){
    			$(this).datebox({disabled: true});
    		}else if($(this).hasClass("easyui-combobox")){
    			$(this).combobox("disable");
    		}else if($(this).hasClass("easyui-numberbox")){
    			$(this).numberbox("disable");
    		}else{
    		}
    	})
    	$("#baseInfoForm select").combobox("disable");
    }
    //解禁form
    function editForm(){
    	$("#editId").hide();//修改按钮影藏
    	$("#saveId").show();//保存按钮显示出来
    	$("#rsetId").show();//重置按钮显示
    	$("#sign").val("edit");//设为为编辑状态
    	$("#baseInfoForm input[class^='easyui-']").each(function(i){
    		if($(this).hasClass("easyui-textbox easyui-validatebox")){
    			$(this).attr("disabled",false);
    		}else if($(this).hasClass("easyui-datebox")){
    			$(this).datebox({disabled: false});
    		}else if($(this).hasClass("easyui-combobox")){
    			$(this).combobox("enable");
    		}else if($(this).hasClass("easyui-numberbox")){
    			$(this).numberbox("enable");
    		}else{
    		}
    	})
    	$("#baseInfoForm select").combobox("enable");
    }
    //重置
    function clearForm(){
    	$("#baseInfoForm").form("clear");//form清空
    	$("#linkPeopleGrid").datagrid('reload',{loanerId:null});//刷新列表
    	$("#sign").val("edit");//设为编辑状态
    }
    //保存紧急联系人
    function saveLinkInfo(){
    	var $sign = $("#sign").val();
    	if("edit" == $sign){
    		$.messager.alert("提示","请您填写投资人客户基本资料并保存!","warning");return;
    	}
    	var isValid = $("#linkPeopleForm").form('validate');
    	if(!isValid){
    		return false;
    	}
    	var investorId = $("#investorId").val();//投资人id
    	$("#cusId").val(investorId);
    	$.ajax({
    		type: "POST",
    		url:"contacts/contactsAction!saveContacts.action",
    		data:$('#linkPeopleForm').serialize(),
    		async: false,//默认true设置下，所有请求均为异步请求
    		cache: true,
    	    success: function(iJson) {
    	    	if(iJson.status){//保存成功
    	    		$("#linkPeopleForm").form('clear');//清空表单
    	    		initLinkPeopleGrid(investorId)
    	    	//	$("#linkPeopleGrid").datagrid('reload',{loanerId:investorId});//刷新列表
    	    	}
    	    	parent.$.messager.show({
    				title : '提示',
    				msg : iJson.message,
    				timeout : 4000 * 2
    			}); 
    	    }
    	});
    }
    //紧急联系人删除
    function toDelete(){
    	var selected = $('#linkPeopleGrid').datagrid('getSelections');
    	if (selected.length <= 0) {
    		$.messager.alert("提示", "请至少选择一条记录执行删除!", "warning");
    		return;
    	}
    	var ids = new Array();
    	for(var i=0,len=selected.length; i<len; i++){
    		ids.push(selected[i].contactId);
    	}
    	ids = ids.join(",");
    	$.messager.confirm('删除', '执行删除后，数据将不可恢复,是否执行?', function(d) {
    		if (d) {
    			$.ajax( {
    				type : "POST",
    				url : 'contacts/contactsAction!deleteByContactId.action',
    				data : "contactIds="+ids,
    				dataType:'JSON',
    				success : function(iJson) {
    					if(iJson.status){
    						//投资人客户id
    						var investorId = $("#investorId").val();
    						//刷新列表
    						initLinkPeopleGrid(investorId);
    						//$("#linkPeopleGrid").datagrid("reload",{loanerId:investorId});
    					}
    					parent.$.messager.show({
    						title : iJson.title,
    						msg : iJson.message,
    						timeout : 4000 * 2
    					});
    				}
    			});
    		}
    	});
    }
    //保存紧急联系人与投资人的关系
    function toSaveInvestorAndContacts(){
    	//投资人客户id
		var investorId = $("#investorId").val();
    	var selected = $('#linkPeopleGrid').datagrid('getSelections');
    	if (selected.length <= 0) {
    		$.messager.alert("提示", "请至少勾选一个联系人作为本次投资的紧急联系人!", "warning");
    		return;
    	}
    	var ids = new Array();
    	for(var i=0;i<selected.length;i++){
    		ids.push(selected[i].contactId);
    	}
    	ids=ids.join(","); 
    	$.ajax( {
    		type : "POST",
    		url : 'InvestorAndContacts/InvestorAndContactsAction!saveInvestorAndContacts.action',
    		data: "contactIds="+ids+"&investorId="+investorId,
    		dataType:'JSON',
    		success : function(iJson) {
    			parent.$.messager.show({
    				title : iJson.title,
    				msg : iJson.message,
    				timeout : 4000 * 2
    			});
    		}
    	});
    }
    //编辑紧急联系人
    function toEditContact(){
    	var row = $("#linkPeopleGrid").datagrid("getSelected");
    	var rows = $('#linkPeopleGrid').datagrid('getSelections');
    	if (row == null) {
    		$.messager.alert("提示", "请选择一条记录进行编辑!", "warning");
    		return;
    	}
    	if(rows.length >1){
    		$.messager.alert("提示", "您只能选择一条记录执行编辑!", "warning");
    		return;
    	}
    	//将数据加载到表单中
    	$("#linkPeopleForm").form('load',row);
    	//渲染市区
        renderCitySelect('linkPeopleCityId','linkPeopleAreaId',row.compProvince);
        $("#linkPeopleCityId").combobox("setValue",row.compCity);
        $("#linkPeopleAreaId").combobox("setValue",row.compArea);
    }
</script>
<div id="tt">
  <div title="客户基本信息">
	  <div class="well well-small" style="margin:5px;">
	    <form id="baseInfoForm">
	      <input id="investorId" name="investorId" type="hidden"/><!-- 投资人id -->
	      <input name="investorStatus" type="hidden"/><!-- 投资人状态 -->
	      <input name="commAddr" type="hidden"/><!-- 通讯地址 -->
	      <input id="sign" type="hidden" value="edit"/><!-- 修改or保存状态标志 -->
	      <input name="createDate" type="hidden"/>
	      <input name="creator" type="hidden"/>
	      <input name="updateDate" type="hidden"/>
	      <input name="updator" type="hidden"/>
          <table>
             <tr>
                <th>姓名(中文):</th>
                <td><input name="chName" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,25]'"/></td>
                <th>姓名(英文):</th>
                <td><input name="enName" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,25]'"/></td>
                <th>性别:</th>
                <td><input name="genderType" class="easyui-textbox easyui-combobox" panelHeight="auto" editable="false"/></td>
             </tr>
             <tr>
                <th>出生日期:</th>
                <td><input name="birthday" class="easyui-textbox easyui-datebox" editable="false"/></td>
                <th>国籍:</th>
                <td><input name="nationality" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,25]'"/></td>
                <th>婚姻状况:</th>
                <td><input name="maritalStatus" class="easyui-textbox easyui-combobox" panelHeight="auto" editable="false"/></td>
             </tr>
             <tr>
                <th>证件类型:</th>
                <td><select name="idType" class="easyui-textbox easyui-combobox" panelHeight="auto" editable="false">
                	<option value="A">身份证</option>
                </select></td>
                <th>证件号码:</th>
                <td><input name="idNo" class="easyui-textbox easyui-validatebox" data-options="validType:'idcard'"/></td>
                <th>签发日期:</th>
                <td><input name="idIssueDate"  class="easyui-textbox easyui-datebox" editable="false"/></td>
             </tr>
             <tr>
                <th>失效日期:</th>
                <td><input name="idExpireDate" class="easyui-textbox easyui-datebox" editable="false"/></td>
                <th>发证机构所在地:</th>
                <td><input name="idLocation" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,127]'"/></td>
                <th>学历:</th>
                <td><input name="degreeType" class="easyui-textbox easyui-combobox" panelHeight="auto" editable="false"/></td>
             </tr>
             <tr>
                <th>职业:</th>
                <td><input name="jobType" class="easyui-textbox easyui-combobox" panelHeight="auto" editable="false"/></td>
                <th>行业:</th>
                <td><input name="industry" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,50]'"/></td>
                <th>单位名称:</th>
                <td><input name="companyName" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,127]'"/></td>
             </tr>
             <tr>
                <th>工作年限(年):</th>
                <td><input name="yearsOfWork" class="easyui-textbox easyui-numberbox" data-options='max:100'/></td>
                <th>单位规模:</th>
                <td><input name="compScale" class="easyui-textbox easyui-combobox" panelHeight="auto" editable="false"/></td>
                <th>职务:</th>
                <td><input name="jobTitle" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,100]'"/></td>
             </tr>
             <tr>
                <th>移动电话:</th>
                <td><input name="mobileTel" class="easyui-textbox easyui-validatebox" data-options="validType:'mobile'"/></td>
                <th>固定电话:</th>
                <td><input name="fixedTel" class="easyui-textbox easyui-validatebox" data-options="validType:'phone'"/></td>
                <th>电子邮箱:</th>
                <td><input name="email" class="easyui-textbox easyui-validatebox" data-options="validType:'email'"/></td>
             </tr>
             <tr>
                <th>邮编:</th>
                <td><input name="zip" class="easyui-textbox easyui-validatebox" data-options="validType:'zip'"/></td>
                <th>家庭情况:</th>
                <td><input name="familyStatus" class="easyui-textbox easyui-combobox" panelHeight="auto" editable="false"/></td>
                <th>家庭收入情况:</th>
                <td><input name="famIncSta" class="easyui-textbox easyui-combobox" panelHeight="auto" editable="false"/></td>
             </tr>
             <tr>
                <th>通讯地址:</th>
                <td colspan="5">
                                                      省:<input id="provinceId" name="provinceId" type="text" class="easyui-combobox" style="width: 100px;"/>
			                  市:<input id="cityId" name="cityId" type="text" class="easyui-combobox" style="width: 100px;"/> 
			           区/县:<input id="areaId" name="areaId" type="text" class="easyui-combobox" style="width: 100px;"/> 
			              街道:<input name="addressDetails" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,250]'" style="width: 340px;"/>     
                </td>
             </tr>
             <tr>
                <th>客户类型:</th>
                <td colspan="5">
                   <select class="easyui-combobox" name="investorType" panelHeight="auto" editable="false" style="width:170px">   
				    <option value="A">个人</option>   
				   </select>
			    </td>
             </tr>
             <tr>
	             <td colspan="6" style="text-align: right;">
	                <a href="javascript:void(0)" id="rsetId" class="easyui-linkbutton" iconCls="icon-reload" onclick="clearForm();">重置</a>
	                <a href="javascript:void(0)" id="editId" class="easyui-linkbutton" iconCls="icon-edit" style="display: none;" onclick="editForm()">编辑</a>
	                <a href="javascript:void(0)" id="saveId" class="easyui-linkbutton" iconCls="icon-save" onclick="saveInvestor();">保存</a>
	             </td>
             </tr>
          </table>
      </form>
	  </div>
  </div>
  <div title="紧急联系人">
    <div class="well well-small" style="margin:5px">
       <form id="linkPeopleForm">
         <input name="contactId" type="hidden"><!-- 紧急联系人id -->
         <input id="cusId" name="cusId" type="hidden"/><!-- 投资人客户id -->
         <input name="cusType" type="hidden"/><!-- 客户类型 -->
         <input name="contactStatus" type="hidden"/><!-- 紧急联系人状态 -->
         <input name="age" type="hidden"/><!-- 年龄 -->
         <input name="jobTitle" type="hidden"/><!-- 职务 -->
        <!-- <input name="annualSalary" type="hidden"/> 年收入 -->
         <input name="yearsOfWork" type="hidden"/><!-- 工作年限 -->
         <input name="compTel" type="hidden"/><!-- 单位电话 -->
         <input name="compAddr" type="hidden"/><!-- 单位地址 -->
         <input name="currAddr" type="hidden"/><!-- 现住地址 -->
         <input name="workplace" type="hidden"/><!-- 工作单位 -->
         <input name="creator" type="hidden"/><!-- 创建人 -->
         <input name="createDate" type="hidden"/><!-- 创建日期 -->
         <table class="table">
           <tr>
             <th>姓名(中文):</th>
             <td><input name="chName" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,16]'"/></td>
             <th>姓名(英文)</th>
             <td><input name="enName" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,16]'"/></td>
             <th>性别:</th>
             <td><input name="genderType" class="easyui-textbox easyui-validatebox" panelHeight="auto" editable="false"/></td>
           </tr>
           <tr>
             <th>出生日期:</th>
             <td><input name="birthday" class="easyui-textbox easyui-validatebox easyui-datebox" editable="false"/></td>
             <th>证件类型:</th>
             <td><input  name="idType" class="easyui-textbox easyui-validatebox" panelHeight="auto" editable="false"/></td>
             <th>证件号码:</th>
             <td><input name="idNo" class="easyui-textbox easyui-validatebox" data-options="validType:'idcard'"/></td>
           </tr>
           <tr>
             <th>移动电话:</th>
             <td><input name="tel" class="easyui-textbox easyui-validatebox" data-options="validType:'mobile'"/></td>
             <th>固定电话:</th>
             <td><input name="fixedTel" class="easyui-textbox easyui-validatebox" data-options="validType:'phone'"/></td>
             <th>电子邮箱:</th>
             <td><input name="email" class="easyui-textbox easyui-validatebox" data-options="validType:'email'"/></td>
           </tr>
           <tr>
             <th>与您的关系:</th>
             <td><input name="relationship" class="easyui-textbox easyui-validatebox" panelHeight="auto" editable="false"/></td>
             <th>邮编:</th>
             <td><input name="zip" class="easyui-textbox easyui-validatebox" data-options="validType:'zip'"/></td>
           </tr>
           <tr>
             <th>通讯地址:</th>
             <td colspan="5">
                                                     省:<input id="linkPeopleProvinceId" name="compProvince" type="text" class="easyui-combobox" style="width: 100px;"/>
			                  市:<input id="linkPeopleCityId" name="compCity" type="text" class="easyui-combobox" style="width: 100px;"/> 
			           区/县:<input id="linkPeopleAreaId" name="compArea" type="text" class="easyui-combobox" style="width: 100px;"/> 
			              街道:<input name="compAddrDetails" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,250]'" style="width: 280px;"/>     
             </td>
           </tr>
           <tr>
             <td colspan="6" style="text-align: right;">
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="saveLinkInfo();">保存</a>
             </td>
           </tr>
         </table>
       </form>
    </div>
    <div style="margin: 5px;">
        <table id="linkPeopleGrid"></table>
    </div>
  </div>
</div>
