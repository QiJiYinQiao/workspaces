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

fieldset {
    border: 0 none;
    margin: 0;
    padding: 0;
}
legend {
    -moz-border-bottom-colors: none;
    -moz-border-left-colors: none;
    -moz-border-right-colors: none;
    -moz-border-top-colors: none;
    border-color: #E5E5E5;
    border-image: none;
    border-style: none none solid;
    border-width: 0 0 1px;
    color: #999999;
    line-height: 20px;
    display: block;
    margin-bottom: 10px;
    padding: 0;
    width: 100%;
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
	    //渲染合同理财产品列表
	    initProductGrid();
	    //订单id'${orderId}'
	    
	    $("input[name='investOrderId']").val('${orderId}');
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
/*=================================== 以下是完善合同js===================================*/
		$("#actMedium").combobox({
			url:"common/commonAction!findTextArr.action?codeMyid=account_medium",
			valueField: 'code',
			textField: 'text'
		});
		$("#actNature").combobox({
			url:"common/commonAction!findTextArr.action?codeMyid=bankAccount_type",
			valueField: 'code',
			textField: 'text'
		});
    /*保存合同信息*/
    function saveInvestContract(){
    	var isValid = $("#constractInfoForm").form('validate');
    	if(!isValid){
    		return false;
    	}
    	$.ajax({
    		url:'investOrder/investOrderAction!saveInvestOrder.action',
    		data:$("#constractInfoForm").serialize(),
    		type:"post",
    		dataType:'json',
    		success:function(data){
    			$.messager.alert("提示信息",data.message);
    			disableContractForm();
    		},
    		error:function(data){
    			$.messager.alert("提示信息",data.message);
    		}
    	})
    }
    
    //禁用form表单
    function disableContractForm(){
    	$("#saveBtn").hide();//保存按钮影藏
    	$("#editBtn").show();//修改按钮显示出来
    	$("#rsetBtn").hide();//重置按钮隐藏
    	$("#signContract").val("save");//设为为保存状态
    	$("#constractInfoForm input[class^='easyui-']").each(function(i){
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
    	$("#constractInfoForm select").combobox("disable");
    	$("#constractInfoForm textarea").attr("disabled",true);
    }
    //解禁form
    function editContractForm(){
    	$("#editBtn").hide();//修改按钮影藏
    	$("#saveBtn").show();//保存按钮显示出来
    	$("#rsetBtn").show();//重置按钮显示
    	$("#signContract").val("edit");//设为为编辑状态
    	$("#constractInfoForm input[class^='easyui-']").each(function(i){
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
    	$("#constractInfoForm select").combobox("enable");
    	$("#constractInfoForm textarea").attr("disabled",false);
    }
    //重置
    function clearContractForm(){
    	$("#constractInfoForm").form("clear");//form清空
    	$("#signContract").val("edit");//设为编辑状态
    }

    $('#prodId').combogrid({
    	delay:1000,
		panelWidth:500,
    	mode:"remote", //输入的值，作为一个参数：q 传递到后台
        url: 'investProduct/investProductAction!getAllInvestProductsCombogrid.action',    
        idField: 'prodId',    
        textField: 'prodName', 
        pagination:true,
		rownumbers:true,
		required:true,
        columns: [[    
            {field:'prodName',title:'产品名称',width:400,sortable:true}    
        ]],
        onClickRow: function(index,row){
        	$("#investProductForm").form("clear");
        	 $("input[name='investOrderId']").val('${orderId}');
        	$('#prodId').combogrid("setValue",row.prodId);
        	$("#investProductForm").form("load",row);
        	$("#repaymentMode").val(jqueryUtil.showText(row.repaymentMode, jqueryUtil.getTextArr("repay_method")));
    	}
    });  

    /**建立订单与理财产品的关系(增加或编辑)*/
    function saveOrderAndProduct(){
    	var isValid = $("#investProductForm").form('validate');
    	if(!isValid){
    		return false;
    	}
    	//验证申请金额是否在最低出借金额和最高出借金额之间
    	var lowLendEdu=Number($("#lowLendEdu").val());
    	var higLendEdu=Number($("#higLendEdu").val());
    	var investEdu=Number($("#investEdu").val());
    	if(higLendEdu==0){
    		if(investEdu<lowLendEdu){
        		$.messager.alert("提示信息","申请金额必须在理财产品最低出借金额与最高出借金额之间！","warning")
        		return false;
        	}else{
        		checkProd();
        	}
    	}else{
    		if(investEdu<lowLendEdu || investEdu>higLendEdu){
        		$.messager.alert("提示信息","申请金额必须在理财产品最低出借金额与最高出借金额之间！","warning")
        		return false;
        	}else{
        		checkProd();
        	}
    	}
    	
    }
    /**持久化订单与理财产品的关系*/
    function checkProd(){
    	$("#usableEdu").val($("#investEdu").val());
    	$.ajax({
    		type: "POST",
    		url:"investorderAndProducts/investorderAndProductsAction!persistenceInvestorderAndProducts.action",
    		data:$('#investProductForm').serialize(),
    		async: false,//默认true设置下，所有请求均为异步请求
    		cache: true,
    	    success: function(iJson) {
    	    	if(iJson.status){//保存成功
    	    		$("#investProductForm").form('clear');//清空表单
    	    		$("input[name='investOrderId']").val('${orderId}');//给订单赋值
    	    		$.messager.show({
        	    		title:'提示信息',
        	    		msg:"勾选理财产品更新成功！",
        	    		showType:'slide',
        	    	});
    				initProductGrid();
    	    	}
    	    }
    	});
    }
    /**订单合同里的理财产品列表*/
    function initProductGrid(){
    	var investOrderId='${orderId}';
    	$("#pg").datagrid({
    		url : "investorderAndProducts/investorderAndProductsAction!findInvestorderAndProductsList.action?orderId="+investOrderId,
    		width : 920,
    		height : 250,
    		pagination:false,
    		rownumbers:true,
    		border:true,
    		singleSelect:false,
    		nowrap:true,
    		multiSort:false,
    		fitColumns:true,
    		columns : [ [
                         {field : 'ck',checkbox : true},
    		             {field : 'product',title : '理财产品',width : 150,align : 'center',
                        	 formatter:function(value,row){
                        		 return row.product.prodName;
                        	 }},
    		             {field : 'beginDate',title : '划扣日期',width : 150,align : 'center'},
    		             {field : 'interestDate',title : '计息日',width : 150,align : 'center'},
    		             {field : 'endDate',title : '到期日',width : 150,align : 'center'},
    		             {field : 'investEdu',title : '申请金额(元)',width : 150,align : 'center'}
    		]],
    		toolbar: [/* {
    			iconCls: 'icon-edit',
    			text:'编辑',
    			handler: toEditProd
    		},"-", */{
    			iconCls: 'icon-cut',
    			text:'删除',
    			handler:toDeleteProd
    		}
    		 ]
    	});
    }
    
    //理财产品编辑
    function toEditProd(){
    	var row = $("#pg").datagrid("getSelected");
    	var rows = $('#pg').datagrid('getSelections');
    	if (row == null) {
    		$.messager.alert("提示", "请选择一条记录进行编辑!", "warning");
    		return;
    	}
    	if(rows.length >1){
    		$.messager.alert("提示", "您只能选择一条记录执行编辑!", "warning");
    		return;
    	}
    	//加载选中理财产品详细信息
    	$.ajax({
    		type : "POST",
			url : "investProduct/investProductAction!findInvestProductById.action",
			data : "productId="+row.product.prodId,
			dataType:'JSON',
			async:false,
			success:function(data){
    			$("#investProductForm").form('load',data);
    			$("#prodId").combogrid("setValue",row.product.prodId);
			}
    	})
    	//将数据加载到表单中
    	$("#investProductForm").form('load',row);
    }
    
    //删除理财产品
    function toDeleteProd(){
    	var selected = $('#pg').datagrid('getSelections');
    	if (selected.length <= 0) {
    		$.messager.alert("提示", "请至少选择一条记录执行删除!", "warning");
    		return;
    	}
    	var ids = new Array();
    	for(var i=0,len=selected.length; i<len; i++){
    		ids.push(selected[i].id);
    	}
    	ids = ids.join(",");
    	$.messager.confirm('删除', '执行删除后，数据将不可恢复,是否执行?', function(d) {
    		if (d) {
    			$.ajax( {
    				type : "POST",
    				url : "investorderAndProducts/investorderAndProductsAction!deleteInvestorderAndProducts.action",
    				data : "ids="+ids,
    				success : function(iJson) {
    					if(iJson.status){
    						//刷新列表
    						$("#pg").datagrid('reload');
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
    /**根据划扣日期自动计算计息日与到期日*/
    function calInterestDate(beginDate){
    	var lendingCycle = $("#lendingCycle").val();//出界周期
    	var datas;
    	//计算划扣日期的下一个工作日
    	$.ajax({
    		url:"investorderAndProducts/investorderAndProductsAction!findInterestDateAndEndDateByBeginDate.action",
    		data:{"huakouDate":beginDate,"lendingCycle":lendingCycle},
    		type:"POST",
    		async:false,
    		success:function(data){
    			datas=data;
    		},
    		error:function(data){}
    	})
    	$("#interestDate").val(datas[0]);//计息日
    	$("#endDate").datebox("setValue",datas[1]);//到期日
    	
    }
</script>
<div id="tt">
  <div title="客户基本信息">
	  <div class="well well-small" style="margin:5px;">
	    <form id="baseInfoForm">
	      <input id="investOrderId" name="investOrderId" type="hidden"/>
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
                <td><input name="chName" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,25]'" required/></td>
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
                <td><input name="idNo" class="easyui-textbox easyui-validatebox" data-options="validType:'idcard'" required/></td>
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
                <td><input name="mobileTel" class="easyui-textbox easyui-validatebox" data-options="validType:'mobile'" required/></td>
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
                                                      省:<input id="provinceId" name="provinceId" type="text" class="easyui-combobox" style="width: 100px;" required/>
			                  市:<input id="cityId" name="cityId" type="text" class="easyui-combobox" style="width: 100px;" required/> 
			           区/县:<input id="areaId" name="areaId" type="text" class="easyui-combobox" style="width: 100px;" required/> 
			              街道:<input name="addressDetails" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,250]'" style="width: 340px;" required/>     
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
             <td><input name="idType" class="easyui-textbox easyui-validatebox" panelHeight="auto" editable="false"/></td>
             <th>证件号码:</th>
             <td><input name="idNo" class="easyui-textbox easyui-validatebox" data-options="validType:'idcard'"/></td>
           </tr>
           <tr>
             <th>移动电话:</th>
             <td><input name="tel" class="easyui-textbox easyui-validatebox" data-options="validType:'mobile'" required/></td>
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
                                                     省:<input id="linkPeopleProvinceId" name="compProvince" type="text" class="easyui-combobox" style="width: 100px;" required/>
			                  市:<input id="linkPeopleCityId" name="compCity" type="text" class="easyui-combobox" style="width: 100px;" required/> 
			           区/县:<input id="linkPeopleAreaId" name="compArea" type="text" class="easyui-combobox" style="width: 100px;" required/> 
			              街道:<input name="compAddrDetails" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,250]'" style="width: 280px;" required/>     
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
  <div title="合同信息">
  <form id = "constractInfoForm" action="" method="post"> 
  	<input id="investOrderId" name="investOrderId" type="hidden"/>
  	<input id="investorId" name="investorId" type="hidden"/>
  	<input id="investorName" name="investorName" type="hidden"/>
  	<input id="orderStatus" name="orderStatus.statusCode" type="hidden"/>
  	<input id="mobTel" name="mobTel" type="hidden"/>
  	<input id="idCrad" name="idCrad" type="hidden"/>
  	<input id="creator" name="creator" type="hidden"/>
  	<input id="createDate" name="createDate" type="hidden"/>
  	<input id="organizationId" name="organizationId" type="hidden"/>
  	<input id="processStatus" name="processStatus" type="hidden"/>
  	<input id="signContract" type="hidden" value="edit"/><!-- 修改or保存状态标志 -->
  <fieldset>
  	<legend>开户行信息</legend>
  	<div title="开户行信息" style="padding:10px">
  	<table class="table">
  	 <tr>
       <th>账户介质:</th>
       <td><select id="actMedium" name="actMedium" class="easyui-textbox easyui-validatebox"></select></td>
       <th>账户性质:</th>
       <td><select id="actNature" name="actNature" class="easyui-textbox easyui-validatebox"></select></td>
       <th>开户行名称:</th>
       <td><input id="bankName" name="bankName" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,16]'" required/></td>
     </tr>
     <tr>
       <th>账户名称:</th>
       <td><input id="actName" name="actName" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,16]'" required/></td>
       <th>开户行账号:</th>
       <td><input id="actNo" name="actNo" class="easyui-textbox easyui-numberbox"" required/></td>
     </tr>
  	</table>
  	</div>
  </fieldset>
  <fieldset>
  	<legend>合同信息</legend>
  	<div title="合同信息" style="padding:10px">
  	<table class="table">
  	 <tr>
       <th>合同编号:</th>
       <td><input id="contractNo" name="contractNo" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,16]'" required/></td>
       <th>合同签署地:</th>
       <td><input id="signSite" name="signSite" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,64]'" required/></td>
       <th>合同签署日期:</th>
       <td><input id="signDate" name="signDate" class="easyui-textbox easyui-datebox"/></td>
     </tr>
     <tr>
       <th>理财经理:</th> 
       <td><input id="financingMgr" name="financingMgr" value="${users.name }" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,20]'" readonly/></td>
       <th>理财经理电话:</th>
       <td><input id="fmPhone" name="fmPhone" value="${users.tel }" class="easyui-textbox easyui-numberbox" readonly/></td>
       <th>理财经理签字日期:</th>
       <td><input id="fmSignDate" name="fmSignDate" class="easyui-textbox easyui-datebox"/></td>
     </tr>
     <tr>
       <th>部门主管:</th>
       <td><input id="deptMgr" name="deptMgr" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,20]'"/></td>
       <th>部门主管签字日期:</th>
       <td><input id="dmSignDate" name="dmSignDate" class="easyui-textbox easyui-datebox"/></td>
     </tr>
     </table>
  	</div>
  </fieldset>
  <fieldset>
  	<legend>备注信息</legend>
  	<div title="备注信息" style="padding: 10px">
		<textarea id="orderDesc" name="orderDesc" class="easyui-validatebox easyui-textbox" style="width:550px;height:70px;" data-options="validType:'length[0,100]'"></textarea>
	</div>
  </fieldset>
  <fieldset>
  <legend></legend>
  	<div align="right" style="margin-right:45px">
  		   <a href="javascript:void(0)" id="rsetBtn" class="easyui-linkbutton" iconCls="icon-reload" onclick="clearContractForm();">重置</a>
	       <a href="javascript:void(0)" id="editBtn" class="easyui-linkbutton" iconCls="icon-edit" style="display: none;" onclick="editContractForm()">编辑</a>
           <a href="javascript:void(0)" id="saveBtn" class="easyui-linkbutton" iconCls="icon-save" onclick="saveInvestContract()">保存</a>

  </div>
  </fieldset>
  </form>
   <fieldset style="margin-top:10px">
 	<legend>选择理财产品</legend>
 	<div title="投资申请">
 	<!-- <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addACredential(this);">增加投资产品</a>
<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeACredential(this);">取消投资产品</a>  -->
 	<form id="investProductForm">
 	<input name="id" type="hidden"/>
 	<input name="investOrderId" type="hidden"/>
 	<input id="interestDate" name="interestDate" type="hidden"/>
 	<input id="usableEdu" name ="usableEdu" type="hidden"/>
 	<table class="table">
 	<tr>
      <th>理财产品:</th>
      <td><input id="prodId" name="product.prodId" class="easyui-textbox easyui-validatebox"/></td>
      <th>年化收益率(%):</th>
      <td><input id="ars" name="ars" class="easyui-textbox easyui-validatebox" readonly/></td>
      <th>到期收益率(%):</th>
      <td><input id="ytm" name="ytm" class="easyui-textbox easyui-validatebox" readonly/></td>
    </tr>
    <tr>
    <th>模式特点:</th>
      <td>
      <textarea id="prodDesc" name="prodDesc" class="easyui-textbox easyui-validatebox" style="width:170px;height:60px;" readonly></textarea>
      </td>
      <th>最低出借金额(元):</th>
      <td><input id="lowLendEdu" name="lowLendEdu" class="easyui-textbox easyui-validatebox" readonly/></td>
      <th>最高出借金额（元）:</th>
      <td><input id="higLendEdu" name="higLendEdu" class="easyui-textbox easyui-validatebox" readonly/></td>
    </tr>
    <tr>
    	<th>还款方式:</th>
      <td><input id="repaymentMode" name="repaymentMode" class="easyui-textbox easyui-validatebox" readonly/></td>
      <th>出借周期（天）:</th>
      <td><input id="lendingCycle" name="lendingCycle" class="easyui-textbox easyui-validatebox" readonly/></td>
    </tr>
    <tr>
      <th>投资期限:</th>
      <td colspan="3"><input id=beginDate name=beginDate class="easyui-textbox easyui-datebox" data-options="onChange:function(date){calInterestDate(date)}" required/>
      至
      <input id="endDate" name="endDate" class="easyui-textbox easyui-datebox"/></td>
    </tr>
    <tr>
      <th>申请金额:</th>
      <td><input id="investEdu" name="investEdu" class="easyui-textbox easyui-validatebox" data-options="validType:'mDouble1'" required/>元</td>
    </tr>
 	<tr>
 	<td colspan="6" style="text-align: right">
 		<a href="javascript:void(0)" id="saveinfo" class="easyui-linkbutton" iconCls="icon-save" onclick="saveOrderAndProduct();">确定</a>
 	</td>
 	</tr>
 	</table>
 	</form>
 	<!-- 列表暂不显示 -->
 	<div style="margin-top:5px">
 		<table id="pg"></table>
 	</div>
 	</div>
 </fieldset>
  </div>
</div>