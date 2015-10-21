<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/validate.js"></script>
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
</style>
<script type="text/javascript" src="jsp/loanOrder/loanOrderBaseForm.js"></script>
<script type="text/javascript">
   var areaComboxUtil;
   var $addRow;
   //页面加载函数
   $(function(){
	    //渲染所有的公用的下拉框
	    RenderCombox();
	    //渲染基本信息姓名下拉框
	    RenderName();
	   //渲染基本信息户籍地址省市县
	   renderProvinceSelect("hukouProvinceId","hukouCityId","hukouAreaId");
	   //渲染基本信息现住地址省市县
	   renderProvinceSelect("curProvinceId","curCityId","curAreaId");
	   //紧急联系家庭地址
	   renderProvinceSelect('familyProvince', 'familyCity', 'familyArea');
	   //紧急联系人单位地址
	   renderProvinceSelect('compProvince', 'compCity', 'compArea');
	   //工作单位
	   renderProvinceSelect('dwProvince', 'dwCity', 'dwArea');
	   //渲染共同贷款人户籍地址
	   renderProvinceSelect("residenceProvinceId","residenceCityId","residenceAreaId");
	   //渲染共同贷款人的当前地址
	   renderProvinceSelect("ljCurProvinceId","ljCurCityId","ljCurAreaId");
	   //渲染共同贷款人的单位地址
	   renderProvinceSelect("corpProvinceId","corpCityId","corpAreaId");
		//选中客户姓名后 ， 点击对应TAB页再加载对应数据，否则不自动加载	
		$("#tt").tabs({
			onSelect:function(title,index){
				 if($addRow!=null){
					if(1==index){
						linkPeopleDatagrid();//紧急联系人列表
					 	$('#linkPeople').datagrid('options').url = "contacts/contactsAction!findAllList.action";
			            $('#linkPeople').datagrid('reload',{"loanerId": $addRow.loanerId}); 
					}else if(2==index){
						initDatagrid();//工作单位列表
					 	$('#dwDatagrid').datagrid('options').url = "company/companyAction!findListByLoanerId.action";
			            $('#dwDatagrid').datagrid('reload',{"loanerId": $addRow.loanerId}); 
					}else if(4==index){
						initBankGrid();//开户行列表
					 	$('#bankGrid').datagrid('options').url = "accountInfo/accountInfoAction!findListByLoanerId.action";
			            $('#bankGrid').datagrid('reload',{"loanerId": $addRow.loanerId}); 
					}
				  }
			   }
		 });
		
		$("#loanOrderattType").combobox({
			valueField : 'code',
			textField : 'text',
			url:'common/commonAction!findTextArr.action?codeMyid=attachment_type',
			required:true,
			editable:false 
	    });
		
   });
   
   // 用户名的下拉grid
   function RenderName(){
		$("#baseForm input[name='name']").combogrid({    
		    panelWidth:250,  
		    mode: 'remote',   
		    idField:'name',    
		    textField:'name',    
		    url:'loaner/loanerAction!findCombgridLoaner.action',
		    columns:[[    
		        {field:'name',title:'姓名',width:100},    
		        {field:'idNo',title:'身份证号',width:150},    
		    ]] ,
		    onClickRow:function(index,row){
		        $("#baseForm").form('load',row); 
		        //渲染市区
		        renderProvinceSelect('hukouProvinceId','hukouCityId','hukouAreaId',row.hukouProvinceId,row.hukouCityId,row.hukouAreaId);
		        renderProvinceSelect('curProvinceId','curCityId','curAreaId',row.curProvinceId,row.curCityId,row.curAreaId);
		        $addRow = row;
		    }
		}); 
	}
	
	//基本信息保存
	function toSaveBaseInfo(idh,ids){
		if($("#name").combogrid("getText")==""){
			$.messager.alert("提示","请填写联系人姓名!","info");
			return false;
		}
		if(!$("#baseForm").form('validate')){return false;}
		$.ajax({
			cache:true,
			type:'POST',
			url:'loaner/loanerAction!persistenceLoaner.action',
			data:$('#baseForm').serialize(),
			async:false,
			dataType:'JSON',
			success:function(res){
				if(res.state){
					$("#loanerId").val(res.loanerId);
					$("#loanOrderId").val(res.loanOrderId);
					$("#createDate").val(res.createDate);
					$("#hukouAddr").val(res.hukouAddr);
					$("#curAddr").val(res.curAddr);
					$addRow = res;
					parent.$.messager.show({
						title : '提示',
						msg : '恭喜你，保存成功!',
						timeout : 4000 * 2
					});
				}else{
					$.messager.alert("提示", '出错了，保存失败!',"error")
				}
			}
		});
	}
	
	//查看附件
	$("#checkAttachment").click(function(){
		var loanOrderId = $("#loanOrderId").val();
		if(''==loanOrderId){
			$.messager.alert("提示","请先保存基本信息!","info");
			return false;
		}
		checkAttachementDetail('noauditId',loanOrderId,'','');
	});
	
	//上传附件
	$("#upploadAttachment").click(function(){
		var attType = $("#loanOrderattType").combobox("getValue");
		var loanOrderId = $("#loanOrderId").val();
		if(''==loanOrderId){
			$.messager.alert("提示","请先保存基本信息!","info");
			return false;
		}
		fileUploadsDlg(attType,loanOrderId,'');
	});
	
</script>
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
						<input id="hukouAddr" name="hukouAddr" type="hidden"/> <!-- 户籍地址id -->
						<input id="curAddr" name="curAddr" type="hidden"/> <!-- 现住地址id -->
						<input id="sign" type="hidden" value="edit"/><!-- 修改or保存状态标志 -->
						<table class="table">
							<tr>
								<th>姓名:</th>
								<td><input id="name" name="name"
									class="easyui-validatebox easyui-textbox easyui-combogrid"
									type="text" data-options="validType:'length[0,100]'"/></td>
							    <th>身份证号:</th>
								<td><input id="idNo" name="idNo" type="text"
									class="easyui-validatebox easyui-textbox" data-options="required:true,validType:'idcard'"/></td>
								<th>性别:</th>
								<td><input name="genderType" type="text"
									class="easyui-validatebox easyui-textbox easyui-combobox"  editable='false' panelHeight="auto"/></td>
							</tr>
							<tr>
								<th>手机:</th>
								<td><input id="mobileTel" name="mobileTel" type="text"
									class="easyui-validatebox easyui-textbox" data-options="required:true,validType:'mobile'"/></td>
								<th>住址电话:</th>
								<td><input id="fixedTel" name="fixedTel" type="text"
									class="easyui-validatebox easyui-textbox" data-options="required:true,validType:'phone'"/></td>
								<th>家庭电话:</th>
								<td><input id="familyTel" name="familyTel" type="text"
									class="easyui-validatebox easyui-textbox" data-options="required:true,validType:'phone'"/></td>
							</tr>
							<tr>
								<th>年龄:</th>
								<td><input id="age" name="age" type="text"
									class="easyui-validatebox easyui-textbox" data-options="validType:'age'"/></td>
								<th>婚姻状况:</th>
								<td><input id="marriageType" name="marriageType"
									type="text" class="easyui-validatebox easyui-textbox easyui-combobox"  editable='false' panelHeight="auto"/></td>
								<th>有无子女:</th>
								<td><input id="hasChild" name="hasChild" type="text"
									class="easyui-validatebox easyui-textbox easyui-combobox"  editable='false' panelHeight="auto"/></td>
							</tr>
							<tr>
								<th>年收入(元):</th>
								<td><input id="annualSalary" name="annualSalary"
									type="text" class="easyui-validatebox easyui-textbox" data-options="required:true,validType:'mDouble'"/></td>
								<th>收入来源:</th>
								<td><input id="incomeSrc" name="incomeSrc" type="text"
									class="easyui-validatebox easyui-textbox " data-options="required:true,validType:'length[0,100]'"/></td>
								<th>居住情况:</th>
								<td><input id="mortgageStatus" name="mortgageStatus"
									type="text" class="easyui-textbox easyui-validatebox easyui-combobox"  editable='false' panelHeight="auto"/></td>
							</tr>
							<tr>
							    <th>月供(元):</th>
							    <td>
							       <input name="houseInstallPay" class="easyui-validatebox easyui-textbox" data-options="required:true,validType:'mDouble'"/>
							    </td>
							    <th>房租(元):</th>
							    <td>
							       <input name="rent" class="easyui-validatebox easyui-textbox" data-options="required:true,validType:'mDouble'"/>
							    </td>
							    <th>邮箱:</th>
								<td>
								   <input id="email" name="email" type="text"
									class="easyui-validatebox easyui-textbox" data-options="required:true,validType:['email','length[0,300]']"/>
								</td>
							</tr>
							<tr>
								<th>QQ号:</th>
								<td><input id="qqNo" name="qqNo" type="text"
									class="easyui-textbox easyui-validatebox" data-options="validType:'qq'"/></td>
							</tr>
							<tr>
							  <th>户籍地址:</th>
							  <td colspan="5">
							            省:<input id="hukouProvinceId" name="hukouProvinceId" type="text" class="easyui-combobox"  style="width: 100px;"/>
							            市:<input id="hukouCityId" name="hukouCityId" type="text" class="easyui-combobox" style="width: 100px;"/>
							     区/县:<input id="hukouAreaId" name="hukouAreaId" type="text" class="easyui-combobox" style="width: 100px;"/>
							        街道:<input name="hukouAddrDetails" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'length[0,250]'" style="width: 303px;"/>
							  </td>
							</tr>
							<tr>
							   <th>现住地址:</th>
							   <td colspan="5">
							                  省:<input id="curProvinceId" name="curProvinceId" type="text" class="easyui-combobox" style="width: 100px;"/>
							                  市:<input id="curCityId" name="curCityId" type="text" class="easyui-combobox" style="width: 100px;"/> 
							           区/县:<input id="curAreaId" name="curAreaId" type="text" class="easyui-combobox" style="width: 100px;"/> 
							              街道:<input name="curAddrDetails" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'length[0,250]'" style="width: 303px;"/>     
							   </td>
							</tr>
							<tr>
							   <td colspan="6" style="text-align: right;">
							      <a href="javascript:void(0)" id="rset" class="easyui-linkbutton" iconCls="icon-reload" onclick="$('#baseForm').form('clear');">重置</a>
							      <a href="javascript:void(0)" id="save" class="easyui-linkbutton" iconCls="icon-save" onclick="toSaveBaseInfo('save','edit');">保存</a>
							   </td>
							</tr>
						</table>
				</form>
		        </div>
			</div>
			<div title="紧急联系人" data-options="iconCls:'icon-help'" style="padding: 10px">
				<jsp:include page="loanOrderLinkPeople.jsp"></jsp:include>
			</div>
			<div title="工作单位" data-options="iconCls:'icon-help'" style="padding: 10px">
				<jsp:include page="loanOrderWorkUnit.jsp"></jsp:include>
			</div>
			<div title="申请贷款信息" data-options="iconCls:'icon-help'" style="padding: 10px">
				<jsp:include page="loanOrderInfoForm.jsp"></jsp:include>
			</div>
			<div id="banks" title="开户行信息" data-options="iconCls:'icon-help'" style="padding: 10px">
				<jsp:include page="loanOrderBankForm.jsp"></jsp:include>
			</div>
			<div title="附件信息" data-options="iconCls:'icon-help'" style="padding: 10px">
					<span style="font-weight: bold;margin-left: 30px;">附件类型:</span>
					<input id="loanOrderattType" class="easyui-textbox easyui-combobox" />
					<a id="checkAttachment" href="javascript:void(0);" class="easyui-linkbutton">查看附件</a>	
					<a id="upploadAttachment" href="javascript:void(0);" class="easyui-linkbutton" >上传附件</a>	
				<%-- <jsp:include page="loanOrderAttachmentForm.jsp"></jsp:include> --%>
			</div> 
			<div title="共同贷款人" data-options="iconCls:'icon-help'" style="padding: 10px">
				<jsp:include page="loanOrderJointForm.jsp"></jsp:include>
			</div>
		</div>
	</div>
</div>