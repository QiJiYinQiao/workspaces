<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/validate.js"></script>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
	
	var lrow;
	
	$(function(){
		$("#user").combobox({required:true});
		$("#userDept").combotree({required:true});
		
		//初始化组织机构
		$("#userDept").combotree({
			width:171,
			url:"Organization/organizationList.do",
			idFiled:'id',
		 	textFiled:'name',
		 	valueFiled:'id',
		 	parentField:'pid',
		 	onLoadSuccess:function(data){
		 		//加一个全部
		 		
		 	},
		 	onChange:function(){
		 			$("#userDept").val($(this).combotree('getValue'));
		 			 RenderName($(this).combotree('getValue'))
		 	}
		});
		
		 //判断上传附件
		//申请试用固定资产项目列表
		ppeUsedAttGrid($("#appNoAdd").val());
	});
	
	// 用户名的下拉
	function RenderName(organizeId){
		//选中部门后下拉框
		
		$("#user").combobox({
			width:171,
			url:"PpeUsedConfirm/findUserList.do?organizeId="+organizeId,
			valueField:'code',
		 	textFiled:'text',
		 	onLoadSuccess:function(data){
		 		//加一个全部
		 		
		 	},
		}); 
	}

	
	
	//保存主表数据
	function toSaveBaseInfo(from){
		var isValid = $("#ppeUsedFrom").form('validate');
    	if(!isValid){
    		return false;
    	}
		
		$.ajax({
			cache:true,
			type:'POST',
			url:"PpeUsedConfirm/saveOrUpdPpeusedConfirm.do",
			data:$('#ppeUsedFrom').serialize(),
			async:false,
			dataType:'JSON',
			success:function(res){
				 if(res.status){
					 
					 var f = $("#ppeUsedFrom");
					 f.form("load", res.data);
					 lrow=res;
					 
					  
					 $.messager.alert(res.title, res.message,"info")
				}else{
					$.messager.alert(res.title, res.message,"error")
				} 
			}
		});
	}
	
 	 //申请试用固定资产项目项目列表
    function ppeUsedAttGrid(appNo){
    	$("#appUserView").datagrid({
    		url : "PpeUsedConfirm/findPpeUsedConAtt.do",
    		queryParams:{
    			"appNo":appNo,
    		},		
    		width : 680,
    		height : 350,
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
						{field : 'ppeNo',title : '资产编号',width:152,align : 'center'},
    		            {field : 'ppeName',title : '资产名称',width:159,align : 'center'},
						{field : 'appDate',title : '申请使用日期',width : 180,sortable : true,align : 'center'},
						{field : 'reverterName',title : '归还人',width:100,align : 'center',
							/* formatter : function(value, row, index) {
								if(value!=null && value!=""){
									return "<a href=\"javascript:void(0)\" onclick=\"showView("+ index + ");\">" + row.reverterName + "</a>";
								}
							} */
						},
						{field : 'givebackDate',title : '归还日期',width:140,align : 'center'},
						{field : 'usedReson',title : '使用用途',width:140,align : 'center'},
						{field : 'remark',title : '备注信息',width:140,align : 'center'},
    		]],
    		onLoadSuccess:function(data){
    			 $("#appUserView").datagrid("doCellTip",{'max-width':'100px'});
    			 
    			
    			if(data.total!=null && data.total!=0){
    				$("#userDept").combotree("disable");
    				$("#user").combobox("disable");
    				$("input[type='radio']").attr("disabled",true);
    			}else{
    				$("#userDept").combotree("enable");
    				$("#user").combobox("enable");
    				$("input[type='radio']").attr("disabled",false);
    			}
    			
			}, 
    		toolbar: [{
    			iconCls: 'icon-add',
    			text:'添加',
    			handler:addPpeUsedAtt
    		},
    		{
    			iconCls: 'icon-cancel',
    			text:'删除',
    			handler:toDelete
    		},{
    			iconCls: 'icon-edit',
    			text:'编辑',
    			handler:EditPpeUsedAtt
    		}]  
    	});
    	
    }
 	 
 	 //判断添加报销申请
 	 function changeMyTitle(row){
 		 if(row==null){
 			 return "添加固定资产项目";
 		 }else{
 			 return  "编辑固定资产项目"
 		 }
 	 }
 	 function changeMyicon(row){
 		 if(row==null){
 			 return "icon-add";
 		 }else{
 			 return "icon-edit";
 		 }
 	 }
 	 
 	 function addPpeUsedAtt(){
 		addOrEditPpeUsedAtt();
 		 
 	 }
 	 function EditPpeUsedAtt(){
 		var rows = $("#appUserView").datagrid("getSelections");
 		if(rows[0]==null || rows.length>1){
 			$.messager.alert("提示","您只能选择一条记录进行修改!","warning");
 			return false;
 		}else{
 			addOrEditPpeUsedAtt(rows[0])
 		}
 	 }
 	 
 	 //添加使用的固定资产信息
 	 function addOrEditPpeUsedAtt(rowse){
 		if($("#ppeUsedFrom input[name='appNo'][type='hidden']").val() == ""){
 			$.messager.alert("提示","请先保存基本信息!","warning");
 			return false;
 		}else{
 			
	 		$addOrEditDialog = $("<div></div>").dialog({
				title:changeMyTitle(rowse),
				iconCls: changeMyicon(rowse),
				width:680,
				height:280,
				modal:true,
				href:"jsp/ad/ppeUsedConfirm/ppeUsedConfirmAttAddForm.jsp",
				onLoad:function(){
					//编码
					$("#appNoAtt").val($("#appNoAdd").val());
					//使用性质
					$("#useNatureAtt").val($(":radio:checked").val());
					if(rowse!=null){
						var f = $("#ppeUsedConfirmAtt");
						f.form("load", rowse); 
					}
				},
				onClose:function(){
					$(this).dialog('destroy');
					ppeUsedAttGrid($("#appNoAdd").val());
				}
		 	});
 		}
 	 }
 	 
 	 
 	 
   
     //申请固定资产项目删除
    function toDelete(){
    	var selected = $('#appUserView').datagrid('getSelections');
    	if (selected.length <= 0) {
    		$.messager.alert("提示", "请至少选择一条记录执行删除!", "warning");
    		return;
    	}
    	var ids = new Array();
    	for(var i=0,len=selected.length; i<len; i++){
    		ids.push(selected[i].psaId);
    	}
    	ids = ids.join(",");
    	
    	$.messager.confirm('删除', '执行删除后，数据将不可恢复,是否执行?', function(d) {
    		if (d) {
    			$.ajax( {
    				type : "POST",
    				url : 'PpeUsedConfirm/deletePpeUsedConfirmAtt.do',
    				data : {
    					"ids":ids,
    					"appNo":$("#appNoAdd").val(),
    				},
    				async:false,
    				dataType:'JSON',
    				success : function(iJson) {
    					if(iJson.status){
    						//刷新列表
    						ppeUsedAttGrid($("#appNoAdd").val());
    					}
    					$.messager.alert(iJson.title,iJson.message,'info');	
    				}
    			});
    		}
    	});
    } 
     
     
</script>
<div id="tt" style="height: auto;">
	<div title="固定资产使用申请信息" >
	  <div class="well well-small" style="margin:5px;width:680px">
	     
	     	<form id="ppeUsedFrom" >
	         <input id="appNoAdd" name="appNo" type="hidden"/><!-- 申请编号 -->
	         
	         <table class="table">
	         	 <tr>
						<th>使用部门:</th>
						<td colspan="1"><input  name="userDept" id="userDept" type="text" class="easyui-textbox easyui-validatebox" style="width: 170px"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<th>使用人:</th>
						<td colspan="2"><input name="user" id="user" type="text" class="easyui-textbox  easyui-combobox easyui-validatebox"  style="width: 170px" />&nbsp;</td>
				</tr>
				 
				 <tr>
					<th>使用性质:</th>
					<td >
						<input type="radio" id="radio1" name="useNature" value="1" checked="checked">借用</input>
						<input type="radio" id="radio1" name="useNature" value="2" >领用</input>
					</td>
				 </tr>
				 <tr>
					<th>备注信息:</th>
					<td colspan="3">
					  <textarea id="remark" name="remark" class="easyui-textbox easyui-validatebox" style="width: 555px; height: 60px;resize:none;" data-options="validType:'length[0,200]'"></textarea>
					</td>
				</tr>
				<tr>
				   <td colspan="6" style="text-align: right;">
				   	  <a href="javascript:void(0)" id="save" class="easyui-linkbutton" iconCls="icon-ok" onclick="toSaveBaseInfo();">保存</a>
				   </td>
				</tr>
	         </table>
	       </form>
	  </div>
	  <!-- 费用项目附加表-->
	  <div class="well well-small" style="margin: 5px;width:680px;height:357px">
	        <table id="appUserView"></table>
	  </div>
	</div>
	
	
</div>
