<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/validate.js"></script>

<script type="text/javascript">	
	var selectRow;
	var selectedCheckbox='';
	// 渲染下拉列表框
	createSelect();
	/** 清空新添加的的的数据**/
	function clearForm(){
		$("#investProductInputOrSaveForm").form("clear");		
	}
	
	function createSelect(){
		//初始化组织机构
		$("#isLetOutCaiWuZhang,#isLetOutFaRenZhang,#isLetOutFaRenShenFenZheng,#isLetOutHeTong,#isLetOutZiZhi,#isLetOutQiTa").combobox({
			data:[{
				"id":0, 
				"text":"是"
				},{ 
				"id":1, 
				"text":"否",
				"selected":true
				}],
			valueField:'id',
			textField:'text',
			editable :false,
			width:60
		});
		
		$("#isOriginalHeTong,#isOriginalZiZhi,#isOriginalQiTa").combobox({
			value:"0",
			data:[{
				"id":0, 
				"text":"原件"
				},{ 
				"id":1, 
				"text":"非原件" 
				}],
			valueField:'id',
			textField:'text',
			editable :false,
			width:80
		});
	}
	//根据选中的复选框是否禁用对应文本框
	function removeDisable(){
		 $('input[type="checkbox"]:checked').each(function(){
	         var checkStr=$(this).val();
	         $("#useDesc"+checkStr).removeAttr("disabled");
	         $("#isLetOut"+checkStr).combobox("enable");
	         $("#isOriginal"+checkStr).combobox("enable");
	         $("#"+checkStr+"Name").removeAttr("disabled");
	         selectedCheckbox+=checkStr+",";
		});
		 $("input:checkbox").not("input:checked").each(function(){
	         var checkStr=$(this).val();
	         $("#useDesc"+checkStr).attr("disabled",true);
	         $("#isLetOut"+checkStr).combobox("disable");
	         $("#isOriginal"+checkStr).combobox("disable");
	         $("#"+checkStr+"Name").attr("disabled",true);
		});
	}
	
	/** 保存增加或者修改名片申请的数据**/
	function saveApp(){
		$("#selectedCheckbox").val(selectedCheckbox);
		$('#investProductInputOrSaveForm').submit();
	};
	//展示附表数据
	$(function(){
		$("input:checkbox").not("input:checked").each(function(){
	         var checkStr=$(this).val();
	         $("#useDesc"+checkStr).attr("disabled",true);
	         $("#isLetOut"+checkStr).combobox("disable");
	         $("#isOriginal"+checkStr).combobox("disable");
	         $("#"+checkStr+"Name").attr("disabled",true);
		});
		// 渲染form
		$('#investProductInputOrSaveForm').form({    
			url:"credentials/addCredetialsApp.do",
		    onSubmit: function(param){   
		    	//校验输入的信息
				var isValid = $("#investProductInputOrSaveForm").form('validate');
				if(!isValid){
					return false;
				} 
				     	    	
				var lowLendEdu = $("#lowLendEdu").val();
				if(lowLendEdu == "" ){
					$("#lowLendEdu").val(null);
				}
			
				var higLendEdu = $("#higLendEdu").val();
				if(higLendEdu == "" ){
					$("#higLendEdu").val(null);
				} 
		    },    
		    success:function(data){
				$("#selectedCheckbox").val("");
				$.messager.alert("提示","操作成功","warning");
				$("#travelExpensesAppgrid").datagrid("reload");	 
				$("#saveDialog").dialog('close');
		    }    
		});
		<%if(request.getParameter("appNo")!=null && !"".equals(request.getParameter("appNo"))){%>
			$("#credentialsAttachDatagrid").datagrid({
				url : 'credentials/getAttachList.do?appNo='+<%=request.getParameter("appNo")%>,
				width: 'auto',
				height : 200,
				pagination:true,
				rownumbers:true,
				border:true,
				singleSelect:false,
				nowrap:true,
				multiSort:false,
				border:false,
				fitColumns:true,
				pageList:[10,20,30,40],
				columns : [ [
				        {field : 'appNo',title : '申请编号',width:parseInt($(this).width()*0.07),align : 'center'},
						{field : 'csTypeName',title : '证章类型',width : parseInt($(this).width()*0.06),align : 'center'},
						{field : 'csDesc',title : '证章描述',width : parseInt($(this).width()*0.06),align : 'center'},
						{field : 'isOriginalName',title : '是否原件',width : parseInt($(this).width()*0.06),align : 'center'},
						{field : 'planUseDate',title : '计划使用日期',width : parseInt($(this).width()*0.06),align : 'center'},
						{field : 'realUseDateStr',title : '实际使用日期',width : parseInt($(this).width()*0.06),align : 'center'},
						{field : 'csPurpose',title : '证章用途',width : parseInt($(this).width()*0.06),align : 'center'},
						{field : 'isLetOutName',title : '是否外借',width : parseInt($(this).width()*0.06),align : 'center'},
						{field : 'planRestDate',title : '计划归还日期',width : parseInt($(this).width()*0.06),align : 'center'},
						{field : 'realRestDateStr',title : '实际归还日期',width : parseInt($(this).width()*0.06),align : 'center'}
						] ],
				toolbar : '#tb1'
			});
		<%}%>
	});
	
	/*编辑*/
	function toEditAttach(){
		 var row = $('#credentialsAttachDatagrid').datagrid('getSelections');//获取选中的记录
		 if(row==null || row.length<=0){
			$.messager.alert("提示","请选择一条记录!","warning");
			return false;
		 }else if(row.length>1){
			$.messager.alert("提示","只能选择一条记录编辑!","warning");
			return false;
		 }else{
			row = $('#credentialsAttachDatagrid').datagrid('getSelected');//获取选中的记录  
			var checkStr=null;
			if(row.csType==1){
				checkStr="CaiWuZhang";
			}else if(row.csType==2){
				checkStr="FaRenZhang";
			}else if(row.csType==3){
				checkStr="FaRenShenFenZheng";
			}else if(row.csType==4){
				checkStr="ZiZhi";
			}else if(row.csType==5){
				checkStr="HeTong";
			}else if(row.csType==6){
				checkStr="QiTa";
			}
			$("#investProductInputOrSaveForm input[type='checkbox']").attr('checked',false);
			var f = $("#investProductInputOrSaveForm");
			$("#"+checkStr).attr("checked",true);
			removeDisable();
			$("#useDesc"+checkStr).val(row.csPurpose);//根据不同的证章对应不同的使用原因
			$("#"+checkStr+"Name").val(row.csDesc);//证章说明
			$("#isLetOut"+checkStr).combobox("setText",row.isLetOut==0?"是":"否");//是否借出
			$("#isLetOut"+checkStr).combobox("setValue",row.isLetOut);//是否借出
			$("#isOriginal"+checkStr).combobox("setText",row.isOriginal==0?"是":"否");//是否原件
			$("#isOriginal"+checkStr).combobox("setValue",row.isOriginal);//是否原件
			f.form("load", row);
		 }
	}
	
	function delSelectedRows(){
		var ids = new Array();
		 var rowdata = $('#credentialsAttachDatagrid').datagrid('getSelections');//获取选中的记录
		 if(rowdata==null || rowdata.length<=0){
				$.messager.alert("提示","请选择至少一条记录!","warning");
		 }else{
			 for(var i=0;i<rowdata.length;i++){
					ids.push(rowdata[i].auqaId);
				}
			ids = ids.join(",");
			$.messager.confirm('删除', '执行删除后，数据将不可恢复,是否执行?', function(d) {
	    		if (d) {
	    			$.ajax({
	    				type : "POST",
	    				url : 'credentials/delCredentialsAttach.do',
	    				data : "id="+ids,
	    				dataType:'JSON',
	    				success : function(iJson) {
	    					if(iJson.status){
	    						$.messager.alert("提示","删除申请成功","warning");
//		    					//刷新列表	
		    					$("#credentialsAttachDatagrid").datagrid("reload");
	    					}else{
	    						$.messager.alert("提示","删除申请失败","warning");
	    					}
	    				}
		    		});
	    		}
	    	});			
		 }
	}
</script>
	<div style="margin-left: 5px;margin-top   : 5px;" data-options="iconCls:'icon-cstbase'">
	<div id="tb1" style="padding: 2px 0">
		<table cellpadding="0" cellspacing="0">
			<tr>
				<td style="padding-left: 2px"><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="toEditAttach();">编辑</a></td>
				<td style="padding-left: 2px"><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delSelectedRows();">删除</a></td>
			</tr>
		</table>
	</div>
	   <form id="investProductInputOrSaveForm"  method="post">
			<table class="table" width="100%">
				<tr>
					<th>印章类型:</th>
					<td>
						<input type="text" id="selectedCheckbox" name="selectedCheckbox" hidden="hidden"/>
						<input type="text" id="uqaId" name="uqaId" hidden="hidden"/>
						<input type="text" id="auqaId" name="auqaId" hidden="hidden"/>
						<input type="text" id="appNo" name="appNo" hidden="hidden"/>
						<input type="checkbox" id="CaiWuZhang" name="CaiWuZhang" value="CaiWuZhang" onclick="removeDisable()"/><a style="margin-right: 44px;">财务章</a>
						是否外借:<input name="isLetOutCaiWuZhang" id="isLetOutCaiWuZhang" class="easyui-textbox" readonly="true" data-options="validType:'length[0,100]', required:true"/>
						<a style="margin-left: 20px;">使用原因:</a><input name="useDescCaiWuZhang" id="useDescCaiWuZhang" class="easyui-textbox" data-options="validType:'length[0,100]', required:true"/>
					</td>
				</tr>
				<tr>
					<th></th>
					<td>
						<input type="checkbox" id="FaRenZhang" name="FaRenZhang" value="FaRenZhang" onclick="removeDisable()"/><a style="margin-right: 44px;" onclick="removeDisable()">法人章</a>
						是否外借:<input name="isLetOutFaRenZhang" id="isLetOutFaRenZhang" class="easyui-textbox" readonly="true" data-options="validType:'length[0,100]', required:true"/>
					
						<a style="margin-left: 20px;">使用原因:</a><input name="useDescFaRenZhang" id="useDescFaRenZhang" class="easyui-textbox" data-options="validType:'length[0,100]', required:true"/>
					</td>
				</tr>
				<tr>
					<th>证件资质:</th>
					<td>
						<input type="checkbox" id="FaRenShenFenZheng" name="FaRenShenFenZheng" value="FaRenShenFenZheng" onclick="removeDisable()"/><a style="margin-right: 20px;">法人身份证</a>
						是否外借:<input name="isLetOutFaRenShenFenZheng" id="isLetOutFaRenShenFenZheng" class="easyui-textbox" readonly="true" data-options="validType:'length[0,100]', required:true"/>
						<a style="margin-left: 20px;">使用原因:</a><input name="useDescFaRenShenFenZheng" id="useDescFaRenShenFenZheng" class="easyui-textbox" data-options="validType:'length[0,100]', required:true"/>
					</td>
				</tr>
				<tr>
					<th></th>
					<td>									
						<input type="checkbox" id="HeTong" name="HeTong" value="HeTong" onclick="removeDisable()"/><a style="margin-right: 20px;">合同</a>
						<input name="isOriginalHeTong" id="isOriginalHeTong" class="easyui-textbox" readonly="true" data-options="validType:'length[0,100]', required:true"/>
						<input name="HeTongName" id="HeTongName" class="easyui-textbox" data-options="validType:'length[0,100]', required:true"/>
						<a style="margin-left: 20px;">是否外借:</a><input name="isLetOutHeTong" id="isLetOutHeTong" class="easyui-textbox" readonly="true" data-options="validType:'length[0,100]', required:true"/>
						<a style="margin-left: 20px;">使用原因:</a><input name="useDescHeTong" id="useDescHeTong" class="easyui-textbox" data-options="validType:'length[0,100]', required:true"/>
					</td>
				</tr>
				<tr>
					<th></th>
					<td>
						<input type="checkbox" id="ZiZhi" name="ZiZhi" value="ZiZhi" onclick="removeDisable()"/><a style="margin-right: 20px;">资质</a>
						<input name="isOriginalZiZhi" id="isOriginalZiZhi" class="easyui-textbox" readonly="true" data-options="validType:'length[0,100]', required:true"/>
						<input name="ZiZhiName" id="ZiZhiName" class="easyui-textbox" data-options="validType:'length[0,100]', required:true"/>
						<a style="margin-left: 20px;">是否外借:</a><input name="isLetOutZiZhi" id="isLetOutZiZhi" class="easyui-textbox" readonly="true" data-options="validType:'length[0,100]', required:true"/>
						<a style="margin-left: 20px;">使用原因:</a><input name="useDescZiZhi" id="useDescZiZhi" class="easyui-textbox" data-options="validType:'length[0,100]', required:true"/>
					</td>
				</tr>
				<tr>
					<th></th>
					<td>
						<input type="checkbox" id="QiTa" name="QiTa" value="QiTa" onclick="removeDisable()"/><a style="margin-right: 20px;">其他</a>
						<input name="isOriginalQiTa" id="isOriginalQiTa" class="easyui-textbox" readonly="true" data-options="validType:'length[0,100]', required:true"/>
						<input name="QiTaName" id="QiTaName" class="easyui-textbox" data-options="validType:'length[0,100]', required:true" data-options="validType:'length[0,100]', required:true"/>
						<a style="margin-left: 20px;">是否外借:</a><input name="isLetOutQiTa" id="isLetOutQiTa" class="easyui-textbox" readonly="true" data-options="validType:'length[0,100]', required:true"/>
						<a style="margin-left: 20px;">使用原因:</a><input name="useDescQiTa" id="useDescQiTa" class="easyui-textbox" data-options="validType:'length[0,100]', required:true"/>
					</td>
				</tr>
			    <tr>
					<th>计划使用日期:</th>
					<td>
						<input name="planUseDate" id="planUseDate" class="easyui-datebox" editable="true"/>
					</td>
				</tr>
				<tr>
					<th>计划归还日期:</th>
					<td>
						<input name="planRestDate" id="planRestDate" class="easyui-datebox" editable="true"/>
					</td>
				</tr>
				<tr>
					<th>备注:</th>
					<td>
						<textarea id="remark" name="remark" class="easyui-textbox" data-options="validType:'length[0,400]', required:true" style="width: 690px;"></textarea>
					</td>
				</tr>
				<tr align="right">
				   <td colspan="2">
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