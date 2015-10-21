<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="<%=basePath%>">
<title>订单详情</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<jsp:include page="../../layout/script.jsp"></jsp:include>
<style type="text/css">
.textStyle{
	text-align: right;
	color: blue;
}
</style>
<style>
.easyui-textbox {
	height: 18px;
	width: 170px;
	line-height: 16px;
	/*border-radius: 3px 3px 3px 3px;*/
	box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset;
	transition: border 0.2s linear 0s, box-shadow 0.2s linear 0s;
}
textarea:focus, input[type="text"]:focus {
	border-color: rgba(82, 168, 236, 0.8);
	box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px
		rgba(82, 168, 236, 0.6);
	outline: 0 none;
}
input, textarea {
	font-weight: normal;
}
.easyui-aa{
}
.table {
	background-color: transparent;
	border-collapse: collapse;
	border-spacing: 0;
	max-width: 100%;
}

.table {
	text-align: left;
	padding: 6px 10px 6px 10px;
}

.table th {
	text-align: right;
	padding: 6px 10px 6px 10px;
}
.table td {
    text-align: left;
	padding: 6px 10px 6px 10px;
}
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
</style>
<script type="text/javascript" src="jsp/loanOrder/loanOrderBaseForm.js"></script>
<script type="text/javascript">
var provinceArr = jqueryUtil.getAreaTextArr(1);//获取省
var relationshipArr = jqueryUtil.getTextArr("relationship_type"); //与本人关系
//渲染单位地址datagrid
var corpNatureData = jqueryUtil.getTextArr("corp_nature");
var accountMediumArr = jqueryUtil.getTextArr("account_medium"); //账户介质
var bankAccountTypeArr = jqueryUtil.getTextArr("bankAccount_type"); //账户性质
var bankPrimaryBackup = jqueryUtil.getTextArr("primary_backup"); //主副银行卡
$(function(){
	// 获取要修改的用户的信息
	var $row = {"loanerId": '<%=request.getParameter("loanerId")%>',"loanOrderId":  '<%=request.getParameter("loanOrderId")%>'};
	// 渲染所有的下拉列表框信息
	RenderCombox();
    //紧急联系家庭地址
    renderProvinceSelect('familyProvince', 'familyCity', 'familyArea');
    //紧急联系人单位地址
    renderProvinceSelect('compProvince', 'compCity', 'compArea');
    //工作单位
    renderProvinceSelect('dwProvince', 'dwCity', 'dwArea');
	// 渲染修改订单信息的tab
	$("#tt").tabs({
		 onSelect:function(title,index){
			 if(0==index){
				 loadBaseInfo($row);//渲染基本信息
			 }else if(1==index){
				linkPeopleDatagrid();//紧急联系人列表
			 	$('#linkPeople').datagrid('options').url = "contacts/contactsAction!findAllListChacked.action";
	            $('#linkPeople').datagrid('reload',{"loanerId": $row.loanerId,"loanOrderId": $row.loanOrderId}); 
			 }else if(2==index){
				initDatagrid();//工作单位列表
			 	$('#dwDatagrid').datagrid('options').url = "company/companyAction!findAllListChacked.action";
	            $('#dwDatagrid').datagrid('reload',{"loanerId": $row.loanerId,"loanOrderId": $row.loanOrderId}); 
			 }else if(3==index){
				 loadLoanOrderInfo($row);//渲染订单的信息
			 }else if(4==index){
				initBankGrid();//开户行列表
			 	$('#bankGrid').datagrid('options').url = "accountInfo/accountInfoAction!findAllListChacked.action";
	            $('#bankGrid').datagrid('reload',{"loanerId": $row.loanerId,"loanOrderId": $row.loanOrderId}); 
			 }else if(5==index){
				
			 } else if(6==index){
				loadLoanerJoint($row);//渲染共同借款人的信息
			 }
		 }
	});
	
	$("#checkAttachment").click(function(){
		checkAttachementDetail('noauditId',$row.loanOrderId,'','1');
	});
	
});
		//加载附件
		function loadDetailAttachmentList(listId,auditId,loanOrderId){
			$("#"+listId).empty();
			var str = "<div id='firstDiv"+listId+"' style='width:50%;height:30px;float: left;'><span class='linkSpan'>附件名称</span></a><span class='linkSpan'>附件类型</span><span class='linkSpanS'>操作</span></div><div id='secondDiv"+listId+"' style='width:50%;height:30px;float: left;'><span class='linkSpan'>附件名称</span></a><span class='linkSpan'>附件类型</span><span class='linkSpanS'>操作</span></div>";
			$("#"+listId).append(str);
			$.ajax({
				url : "attachment/attachmentAction!findAttachmentByULA.action",
				data : {"orderType":"borrowerOrder","auditId":auditId,"loanOrderId":loanOrderId,"isDetail":"1"},
				type : "post",
				async : false,
				success : function(data){
					if(data.length==0){
						$("#firstDiv"+listId).empty();
						$("#secondDiv"+listId).empty();
					}else if(data.length==1){
						$("#secondDiv"+listId).empty();
					}
					if(data.length>0){
						$("#noAttachmentDetailList").remove();
					}
					if(data){
						var attId;
						var attName;
						var attType;
						var linkStr = "";
						var j = 0;
						$.each(data,function(i,item){
							attId = data[i].attId;
							attName = data[i].attName;
							attType = data[i].attType;
							attTypeName = data[i].attTypeName;
							linkStr = "<div style='width:50%;height:30px;float: left;'><input type='hidden' name='attId' value='"+attId+"' /><a target='_blank' href='jsp/openoffice/documentView.jsp?attId="+attId+"'><span class='linkSpan'>"+attName+"</span></a><span class='linkSpan'>"+attTypeName+"</span><a href='javascript:void(0);' class='attachBackLinkButton' onclick=\"downloadAttachment('"+attId+"');\">下载</a></div>";
							$("#"+listId).append(linkStr);
						});
					}
				},
				error : function(){
					
				}
			});
		}

	// 渲染用户的基本信息
	function loadBaseInfo(row){
		$("#loanOrderId").val(row.loanOrderId);
		$("#loanerId").val(row.loanerId);
		//发送ajax，查询该贷款人的基本信息
		$.ajax({
				url : 'loaner/loanerAction!queryLoaner.action',
				type : 'POST',
				data : {'loanerId': row.loanerId},
				dataType : 'JSON',
				success : function(row) {
					$("#baseForm").form("load", row);
			        renderProvinceSelect('hukouProvinceId','hukouCityId','hukouAreaId',row.hukouProvinceId,row.hukouCityId,row.hukouAreaId);
			        renderProvinceSelect('curProvinceId','curCityId','curAreaId',row.curProvinceId,row.curCityId,row.curAreaId);
				}
			});  
	}
	
	//渲染订单的信息
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
	
	//渲染紧急联系人列表
	function linkPeopleDatagrid() {
		$("#linkPeople").datagrid({
				width : parseInt($(window).width()*0.98),
				height : 290,
				pagination : false,
				rownumbers : true,
				border : true,
				singleSelect : false,
				nowrap : true,
				multiSort : false,
				fitColumns:true,
				columns : [[
							{field : 'ck',rowspan : 2,checkbox : true},
							{field : 'chName',title : '姓名',width : 80,rowspan : 2,align : 'center'},
							{field : 'relationship',title : '与本人关系',width : 80,rowspan : 2,align : 'center',
								formatter : function(value,row, index) {
									return jqueryUtil.showText(value,relationshipArr);
								}
							},
							{field : 'tel',title : '手机',width : 140,rowspan : 2,align : 'center'},
							{field : 'workplace',title : '工作单位',width : 150,rowspan : 2,align : 'center'},
							{title : '单地址位',width : 340,colspan : 4,align : 'center'} ],
						[
							{field : 'compProvince',title : '省',width : 80,align : 'center',
								formatter : function(value,row, index) {
									return jqueryUtil.showText(value, provinceArr);
								}
							},
							{field : 'compCity',title : '市',width : 80,align : 'center',
								formatter : function(value,row, index) {
									var cityArr = jqueryUtil.getAreaTextArr(row.compProvince);
									return jqueryUtil.showText(value, cityArr);
								}
							},
							{field : 'compArea',title : '县/区',width : 80,align : 'center',
								formatter : function(value,row, index) {
									var areaArr = jqueryUtil.getAreaTextArr(row.compCity);
									return jqueryUtil.showText(value, areaArr);
								}
							},
							{field : 'compAddrDetails',title : '详细地址',width : 200,align : 'center'}
						] ],
				onLoadSuccess : function(data) {
					for (var i = 0; i < data.rows.length; i++) {
						if ("checked" == data.rows[i].checkedLinkMan) {
							$("#linkPeople").datagrid("selectRow",i);
						}
					}
				}
		});
	}
	
	//渲染单位地址datagrid
	function initDatagrid() {
		$("#dwDatagrid").datagrid({
				width : parseInt($(window).width()*0.98),
				height : 370,
				pagination : false,
				rownumbers : true,
				border : true,
				singleSelect : false,
				nowrap : true,
				multiSort : false,
				fitColumns:true,
				columns : [[
				            {field : 'ck',rowspan : 2,checkbox : true},
				            {field : 'name',title : '单位名称',width : 80,rowspan : 2,align : 'center'},
				            {field : 'tele',title : '单位电话',width : 100,rowspan : 2,align : 'center'},
				             {field : 'corpNature',title : '单位性质',width : 140,rowspan:2,align : 'center',formatter:function(value,row,index){
								 return jqueryUtil.showText(value,corpNatureData);
							 	}
				             },
				            {field : 'foundedTime',title : '成立时间',width : 100,rowspan : 2,align : 'center',formatter:function(value,row,index){
				            	 if(null!=value){
				            		  var date = new Date(value);
				            		  return date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
				            	  }
				            	  return " ";
				             }},
				            {field : 'regCapital',title : '注册资本',width : 80,rowspan : 2,align : 'center'},
				            {field : 'areaSize',title : '营业面积',width : 80,rowspan : 2,align : 'center'},
				            {field : 'empAmount',title : '员工数量',width : 80,rowspan : 2,align : 'center'},
				            {title : '单地址位',width : 340,colspan : 4,align : 'center'}
			            ],
						[
							{field : 'dwProvince',title : '省',width : 80,align : 'center',
								formatter : function(value,
										row, index) {
									return jqueryUtil.showText(
											value, provinceArr);
								}
							},
							{field : 'dwCity',title : '市',width : 80,align : 'center',
								formatter : function(value,
										row, index) {
									var cityArr = jqueryUtil
											.getAreaTextArr(row.dwProvince);
									return jqueryUtil.showText(value, cityArr);
								}
							},
							{field : 'dwArea',title : '县/区',width : 80,align : 'center',
								formatter : function(value,row, index) {
									var areaArr = jqueryUtil.getAreaTextArr(row.dwCity);
									return jqueryUtil.showText(value, areaArr);
								}
							},
							{field : 'dwAddrDetails',title : '详细地址',width : 200,align : 'center'}
						] ],
				onLoadSuccess : function(data) {
					for (var i = 0; i < data.rows.length; i++) {
						if ("checked" == data.rows[i].stateChecked) {
							$("#dwDatagrid").datagrid("selectRow",i);
						}
					}
				}
			});
	}
	
	//开户行信息列表
	function initBankGrid() {
		$("#bankGrid").datagrid({
			width : parseInt($(window).width()*0.98),
			height : 420,
			pagination : false,
			rownumbers : true,
			border : true,
			singleSelect : false,
			nowrap : true,
			multiSort : false,
			fitColumns:true,
			columns : [ [    {field : 'ck',rowspan:2,checkbox : true},
				             {field : 'accountName',title : '开户名称',width : 100,align : 'center'},
				             {field : 'bankName',title : '开户行名称',width : 100,align : 'center'},
				             {field : 'accountNum',title : '开户账号',width : 180,align : 'center'},
				             {field : 'actMedium',title : '账户介质',width : 100,align : 'center',
				            	 formatter:function(value,row,index){
				            	 	return jqueryUtil.showText(value,accountMediumArr);
				            	 }
				             },
				             {field : 'actNature',title : '账户性质',width : 100,align : 'center',
				            	 formatter:function(value,row,index){
				            		 return jqueryUtil.showText(value,bankAccountTypeArr);
				             	}
				             },
				             {field : 'primaryBackup',title : '主副银行卡',width : 100,align : 'center',
				            	 formatter:function(value,row,index){
				            	 	return jqueryUtil.showText(value,bankPrimaryBackup);
				             	}
				             }
		              ] ],
			onLoadSuccess : function(data) {
				for (var i = 0; i < data.rows.length; i++) {
					if ("checked" == data.rows[i].stateChecked) {
						$("#bankGrid").datagrid("selectRow", i);
					}
				}
			}
		});
	}
	
	// 渲染共同借款人的信息
	function loadLoanerJoint(row){
		 $.ajax({
				type:"POST",
				url: "loanerJoint/loanerJointAction!findLoanerJointByOrderId.action",
				data:{"loanOrderId":row.loanOrderId},
				dataType:"json",
				success:function(iJson){
					if(iJson==null){
						$("#loanerJointNoInfo").show();
						$("#loanerJointInfo").hide();
						return;
					}else{
					   	$("#loanerJointForm").form("load",iJson);
					   //渲染共同贷款人户籍地址
					   renderProvinceSelect("residenceProvinceId","residenceCityId","residenceAreaId",iJson.residenceProvinceId,iJson.residenceCityId,iJson.residenceAreaId);
					   //渲染共同贷款人的当前地址
					   renderProvinceSelect("ljCurProvinceId","ljCurCityId","ljCurAreaId",iJson.curProvinceId,iJson.curCityId,iJson.curAreaId);
					   //渲染共同贷款人的单位地址
					   renderProvinceSelect("corpProvinceId","corpCityId","corpAreaId",iJson.corpProvinceId,iJson.corpCityId,iJson.corpAreaId);
					}
				}
		 });
	}

</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'center',border:false" title="">
			<div id="tt">
				<div title="客户基本资料" data-options="iconCls:'icon-cstbase'"
					style="padding: 10px">
					<div class="well well-small" style="margin-left: 5px;margin-top: 5px;width: 850px;">
					   <form id="baseForm" method="post">
							<input id="loanerId" name="loanerId" type="hidden"/><!-- 贷款人id -->
							<input id="loanOrderId" name="loanOrderId" type="hidden"/><!-- 订单id -->
							<input id="createDate" name="createDate" type="hidden"/> <!-- 创建时间 -->
							<input id="sign" type="hidden" value="edit"/><!-- 修改or保存状态标志 -->
							<table class="table">
							   <tr>
									<th>姓名:</th>
									<td><input id="name" name="name"
										class="easyui-textbox easyui-validatebox"
										type="text" data-options="required:true,validType:'length[0,100]'" readonly="readonly" disabled="disabled"/></td>
								     <th>身份证号:</th>
									<td><input id="idNo" name="idNo" type="text" class="easyui-textbox"  readonly="readonly" disabled="disabled"/></td>
									<th>性别:</th>
									<td><input name="genderType" type="text" class="easyui-textbox easyui-validatebox easyui-aa" editable='false' panelHeight="auto"  readonly="readonly" disabled="disabled"/></td> 
								</tr>
								<tr>
									<th>手机:</th>
									<td><input id="mobileTel" name="mobileTel" type="text"
										class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'mobile'" readonly="readonly" disabled="disabled"/></td>
									<th>住址电话:</th>
									<td><input id="fixedTel" name="fixedTel" type="text"
										class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'phone'" readonly="readonly" disabled="disabled"/></td>
									<th>家庭电话:</th>
									<td><input id="familyTel" name="familyTel" type="text"
										class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'phone'" readonly="readonly" disabled="disabled"/></td>
								</tr>
								<tr>
									<th>年龄:</th>
									<td><input id="age" name="age" type="text"
										class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'age'" readonly="readonly" disabled="disabled"/></td>
									<th>婚姻状况:</th>
									<td><input id="marriageType" name="marriageType"
										type="text" class="easyui-textbox easyui-validatebox easyui-aa" editable='false' panelHeight="auto" readonly="readonly" disabled="disabled"/></td>
									<th>有无子女:</th>
									<td><input id="hasChild" name="hasChild" class="easyui-textbox easyui-validatebox easyui-aa"type="text" editable='false' panelHeight="auto" readonly="readonly" disabled="disabled"/></td>
								</tr>
								<tr>
									<th>年收入(元):</th>
									<td><input id="annualSalary" name="annualSalary"
										type="text" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'mDouble'" readonly="readonly" disabled="disabled"/></td>
									<th>收入来源:</th>
									<td><input id="incomeSrc" name="incomeSrc" type="text"
										class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'length[0,100]'" readonly="readonly" disabled="disabled"/></td>
									<th>居住情况:</th>
									<td><input id="mortgageStatus" name="mortgageStatus"
										type="text" class="easyui-textbox easyui-validatebox easyui-aa" editable='false' panelHeight="auto" readonly="readonly" disabled="disabled"/></td>
								</tr>
								<tr>
								    <th>月供(元):</th>
								    <td>
								       <input name="houseInstallPay" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'mDouble'" readonly="readonly" disabled="disabled"/>
								    </td>
								    <th>房租(元):</th>
								    <td>
								       <input name="rent" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'mDouble'" readonly="readonly" disabled="disabled"/>
								    </td>
								    <th>邮箱:</th>
									<td>
									   <input id="email" name="email" type="text"
										class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'email'" readonly="readonly" disabled="disabled"/>
									</td>
								</tr>
								<tr>
									<th>QQ号:</th>
									<td><input id="qqNo" name="qqNo" type="text"
										class="easyui-textbox easyui-validatebox" data-options="validType:'qq'" readonly="readonly" disabled="disabled"/></td>
								</tr>
								<tr>
								  <th>户籍地址:</th>
								  <td colspan="5">
								            省:<input id="hukouProvinceId" name="hukouProvinceId" type="text" class="easyui-combobox" style="width: 100px;" readonly="readonly" disabled="disabled"/>
								            市:<input id="hukouCityId" name="hukouCityId" type="text" class="easyui-combobox" style="width: 100px;" readonly="readonly" disabled="disabled"/>
								     区/县:<input id="hukouAreaId" name="hukouAreaId" type="text" class="easyui-combobox" style="width: 100px;" readonly="readonly" disabled="disabled"/>
								        街道:<input name="hukouAddrDetails" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'length[0,250]'" style="width: 303px;" readonly="readonly" disabled="disabled"/>
								  </td>
								</tr>
								<tr>
								   <th>现住地址:</th>
								   <td colspan="5">
								                  省:<input id="curProvinceId" name="curProvinceId" type="text" class="easyui-combobox" style="width: 100px;" readonly="readonly" disabled="disabled"/>
								                  市:<input id="curCityId" name="curCityId" type="text" class="easyui-combobox" style="width: 100px;" readonly="readonly" disabled="disabled"/> 
								           区/县:<input id="curAreaId" name="curAreaId" type="text" class="easyui-combobox" style="width: 100px;" readonly="readonly" disabled="disabled"/> 
								              街道:<input name="curAddrDetails" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'length[0,250]'" style="width: 303px;" readonly="readonly" disabled="disabled"/>     
								   </td>
								</tr>
							</table>
					</form>
			        </div>
				</div>
				<div title="紧急联系人" data-options="iconCls:'icon-help'" style="padding: 10px">
					<div style="margin-left: 5px;margin-top: 5px;">
					  <table id="linkPeople"></table>
					</div>
				</div>
				<div title="工作单位" data-options="iconCls:'icon-help'" style="padding: 10px">
					<div >
					  <table id="dwDatagrid"></table>
					</div>
				</div>
				<div title="申请贷款信息" data-options="iconCls:'icon-help'"
					style="padding: 10px">
					<div class="well well-small" style="margin-left: 5px;margin-top: 5px;width:99%;">
					  <form id="loanInfo">
					    <table class="table">
							<tr>
								<th>申请额度(元):</th>
								<td><input name="loanAmount" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'mDouble'" type="text" readonly="readonly" disabled="disabled"/>
								<th>最低接受额度(元):</th>
								<td><input name="loanMin" type="text" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'mDouble'" readonly="readonly" disabled="disabled"/></td>
							</tr>
							<tr>
								<th>申请贷款期限(月):</th>
								<td><input name="loanPeriod" type="text" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'integer'" readonly="readonly" disabled="disabled"/></td>
								<th>还款方式:</th>
								<td><input id="repayMethod" name="repayMethod" type="text" class="easyui-textbox easyui-validatebox easyui-aa"  panelHeight="auto" editable="false" readonly="readonly" disabled="disabled"/></td>
							</tr>
							<tr>
							    <th>贷款类型:</th>
							    <td>
							       <input id="loanType" name="loanType" type="text" class="easyui-textbox easyui-validatebox easyui-aa"  panelHeight="auto" editable="false" readonly="readonly" disabled="disabled"/>
							    </td>
							    <th>业务员:</th>
								<td><input name="salesMan" class="easyui-textbox easyui-validatebox" data-options="required:true" readonly="readonly" disabled="disabled"/></td>
							</tr>
							<tr>
								<th>贷款用途:</th>
								<td colspan="3">
								  <textarea name="purpose" class="easyui-textbox easyui-validatebox" style="width: 470px; height: 80px;" data-options="required:true" readonly="readonly" disabled="disabled"></textarea>
								</td>
							</tr>
						</table>
					  </form>
					</div>
				</div>
				<div id="banks" title="开户行信息" data-options="iconCls:'icon-help'" style="padding: 10px">
					<div style="margin-left: 5px;margin-top: 5px;">
					  <table id="bankGrid"></table>
					</div>
				</div>
				<div title="附件信息" data-options="iconCls:'icon-help'" style="padding: 10px">
					<a id="checkAttachment" href="javascript:void(0);" class="easyui-linkbutton">查看附件</a>	
					<!-- <div id="noAttachmentDetailList" style="width: 100%;height:280px;text-align: center;padding-top:250px;">
						<font size="6" style="font-weight: bold;box-shadow: 3px 3px 5px 3px;">
							暂无附件信息
						</font>
					</div>
					<div id="attachmentDetailList" style="width:100%;display:block;float:left;">
					</div> -->
				</div>
				<div title="共同贷款人" data-options="iconCls:'icon-help'" style="padding: 10px">
					<div id="loanerJointNoInfo" style="width: 100%;height:280px;text-align: center;padding-top:250px;display:none;">
						<font size="6" style="font-weight: bold;box-shadow: 3px 3px 5px 3px;">
							暂无共同贷款人信息
						</font>
					</div>
					<div id="loanerJointInfo" class="well well-small" style="margin-left: 5px;margin-top: 5px;width: 850px;">
					   <form id="loanerJointForm" method="post">
					   		 <!-- 共同贷款人的ID -->
							<input id="ljId" name="ljId" type="hidden" />
							 <!-- 订单id -->
							<input name="loanOrderId" type="hidden"/>
							 <!-- 公共贷款人的户籍地址 -->
							<input name="residenceAddressId" type="hidden">
							<input name="residenceAddressType" type="hidden">
							 <!-- 公共贷款人的当前地址 -->
							<input name="curAddressId" type="hidden">
							<input name="curAddressType" type="hidden">
							 <!-- 公共贷款人的单位地址 -->
							<input name="corpAddressId" type="hidden">
							<input name="corpAddressType" type="hidden">
							<table class="table">
								<tr>
									<th>姓名:</th>
									<td><input  name="name" class="easyui-validatebox easyui-textbox" type="text" data-options="validType:'length[0,100]'"  readonly="readonly" disabled="disabled"/></td>
								    <th>身份证号:</th>
									<td><input  name="idNo" type="text" class="easyui-validatebox easyui-textbox" data-options="required:true,validType:'idcard'" readonly="readonly" disabled="disabled"/></td>
									<th>性别:</th>
									<td><input  name="gender" type="text"   editable='false' panelHeight="auto" readonly="readonly" disabled="disabled"/></td>
								</tr>
								<tr>
									<th>手机:</th>
									<td><input  name="mobileTel" type="text" class="easyui-validatebox easyui-textbox" data-options="required:true,validType:'mobile'" readonly="readonly" disabled="disabled"/></td>
									<th>住址电话:</th>
									<td><input  name="fixedTel" type="text" class="easyui-validatebox easyui-textbox" data-options="required:true,validType:'phone'" readonly="readonly" disabled="disabled"/></td>
									<th>家庭电话:</th>
									<td><input  name="familyTel" type="text" class="easyui-validatebox easyui-textbox" data-options="required:true,validType:'phone'" readonly="readonly" disabled="disabled"/></td>
								</tr>
								<tr>
									<th>年龄:</th>
									<td><input  name="age" type="text" class="easyui-validatebox easyui-textbox" data-options="validType:'age'" readonly="readonly" disabled="disabled"/></td>
									<th>婚姻状况:</th>
									<td><input  name="marriageType"   type="text"  editable='false' panelHeight="auto" readonly="readonly" disabled="disabled"/></td>
									<th>有无子女:</th>
									<td><input  name="hasChild" type="text"  editable='false' panelHeight="auto" readonly="readonly" disabled="disabled"/></td>
								</tr>
								<tr>
								    <th>邮箱:</th>
									<td>
									   <input  name="email" type="text" class="easyui-validatebox easyui-textbox" data-options="required:true,validType:'email'" readonly="readonly" disabled="disabled"/>
									</td>
									<th>QQ号:</th>
									<td><input name="qqNo" type="text" class="easyui-textbox easyui-validatebox" data-options="validType:'qq'" readonly="readonly" disabled="disabled"/></td>
								</tr>
								<tr>
									 <th>户籍地址:</th>
									 <td colspan="5">
									           省:<input id="residenceProvinceId" name="residenceProvinceId" type="text" class="easyui-combobox"  style="width: 100px;" readonly="readonly" disabled="disabled"/>
									           市:<input id="residenceCityId" name="residenceCityId" type="text" class="easyui-combobox" style="width: 100px;" readonly="readonly" disabled="disabled"/>
									    区/县:<input id="residenceAreaId" name="residenceAreaId" type="text" class="easyui-combobox" style="width: 100px;" readonly="readonly" disabled="disabled"/>
									       街道:<input name="residenceAddrDetails" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'length[0,250]'" style="width: 303px;" readonly="readonly" disabled="disabled"/>
									 </td>
								</tr>
								<tr>
									  <th>现住地址:</th>
									  <td colspan="5">
									                 省:<input id="ljCurProvinceId" name="curProvinceId" type="text" class="easyui-combobox" style="width: 100px;" readonly="readonly" disabled="disabled"/>
									                 市:<input id="ljCurCityId" name="curCityId" type="text" class="easyui-combobox" style="width: 100px;" readonly="readonly" disabled="disabled"/> 
									          区/县:<input id="ljCurAreaId" name="curAreaId" type="text" class="easyui-combobox" style="width: 100px;" readonly="readonly" disabled="disabled"/> 
									             街道:<input name="curAddrDetails" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'length[0,250]'" style="width: 303px;" readonly="readonly" disabled="disabled"/>     
									  </td>
								</tr>
								<tr>
									<th>工作单位:</th>
									<td><input  name="corpName" type="text" class="easyui-validatebox easyui-textbox" data-options="required:true,validType:'length[0,20]'" readonly="readonly" disabled="disabled"/></td>
									<th>单位电话:</th>
									<td><input  name="corpTel" type="text" class="easyui-validatebox easyui-textbox " data-options="required:true,validType:'phone'" readonly="readonly" disabled="disabled"/></td>
									<th>成立时间:</th>
									<td><input  name="foundedTime" type="text" class="easyui-datebox" data-options="editable:false" readonly="readonly" disabled="disabled"/></td>
								</tr>
								<tr>
									<th>营业面积:</th>
									<td><input  name="areaSize" type="text" class="easyui-textbox easyui-numberbox" data-options="required:true,validType:'mDouble'" readonly="readonly" disabled="disabled"/></td>
									<th>单位性质:</th>
									<td><input  name="corpNature" type="text" class="easyui-validatebox" readonly="readonly" disabled="disabled"/></td>
									<th>注册资本:</th>
									<td><input  name="regCapital" type="text" class="easyui-textbox easyui-numberbox" data-options="required:true,validType:['mDouble','length[0,17]']" readonly="readonly" disabled="disabled"/></td>
								</tr>
								<tr>
								    <th>员工数量:</th>
								    <td><input name="ljEmpAmt" class="easyui-validatebox easyui-textbox" data-options="required:true,validType:'mDouble'" readonly="readonly" disabled="disabled"/></td>
								</tr>
								<tr>
									 <th>单位地址:</th>
									 <td colspan="5">
									                省:<input id="corpProvinceId" name="corpProvinceId" type="text" class="easyui-combobox" style="width: 100px;" readonly="readonly" disabled="disabled"/>
									                市:<input id="corpCityId" name="corpCityId" type="text" class="easyui-combobox" style="width: 100px;" readonly="readonly" disabled="disabled"/> 
									         区/县:<input id="corpAreaId" name="corpAreaId" type="text" class="easyui-combobox" style="width: 100px;" readonly="readonly" disabled="disabled"/> 
									            街道:<input name="corpAddrDetails" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'length[0,250]'" style="width: 303px;" readonly="readonly" disabled="disabled"/>     
									 </td>
								</tr>
							</table>
						</form>
			        </div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>