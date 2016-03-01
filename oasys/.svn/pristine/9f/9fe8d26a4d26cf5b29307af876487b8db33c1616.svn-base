<%@page import="com.oasys.util.Constants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/validate.js"></script>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
	
	$(function(){
		if($row==null){
			$("#editAtta").remove();
// 			badgePeopleGrid('');
		}else{
// 			$("#appPeoNo").val($row.appNo)
			//渲染申请人列表appPeoNo
// 			badgePeopleGrid($("#appPeoNo").val());
		}

		badgePeopleGrid($("#appPeoNo").val());
		$.ajax({
			cache:true,
			type:'POST',
			url:"callingCard/isHeadOrBranch.do",
			async:false,
			dataType:'JSON',
			success:function(flag){
				if(flag.status){//总部
					$(".table tr:eq(0)").remove();
				}else{//分部
					if($row==null){
						//初始化组织机构
						$("#deptNo1").combotree({
							width:171,
							url:"Organization/getOrganizationByUserId.do?userId="+<%=Constants.getCurrendUser().getUserId()%>,
							idFiled:'id',
						 	textFiled:'name',
						 	valueFiled:'id',
						 	parentField:'pid',
						 	onLoadSuccess:function(data){
						 		//加一个全部
						 		
						 	},
						 	onChange:function(){
					 			$("#deptNo1").val($(this).combotree('getValue'));
					 		    RenderName($(this).combotree('getValue'))
						 	}
						});
					}else{
						$(".table tr:eq(0)").remove();
					}
				}
			}
		});
// 		
// 		//渲染申请人列表appPeoNo
// 		if($("#appNofo").val()!=null && $("#appNofo").val()!=""){
// 			badgePeopleGrid($("#appNofo").val());
// 		}
		//禁用上传
		aDisable();
	});

	//禁用上传
	function aDisable(){
		if($.trim($("#appPeoNo").val())==''){
			$("#upploadAttachment").attr("disabled",true);
			$("#checkAttachment").attr("disabled",true);
		}
	}
	
 	// 用户名的下拉
	function RenderName(organizeId){
		//选中部门后下拉框
		$("#userName").combobox({
			width:171,
			multiple:true,
			separator:",", 
			url:"callingCard/getUserByDept.do?organizeId="+organizeId,
			valueField:'code',
		 	textFiled:'text',
		 	onLoadSuccess:function(data){
		 		//加一个全部
		 	},
		}); 
	}

	//申请人信息保存
	function toSaveBaseInfo(typt){
		if($("#userName").val()!=null){//总部去掉选择申请人
			if($("#userName").combogrid("getText")=="" || $("#userName").combogrid("getText")==null){
				$.messager.alert("提示","请填写联系人姓名!","info");
				return false;
			}
		}
		if($("#branchAddr").val()=="" || $("#branchAddr").val()==null){
			$.messager.alert("提示","请填写地址!","info");
			return false;
		}
		if($("#appQty").val()=="" || $("#appQty").val()==null){
			$.messager.alert("提示","请填写数量!","info");
			return false;
		}
		if($("#price").val()=="" || $("#price").val()==null){
			$.messager.alert("提示","请填写单价!","info");
			return false;
		}
		if($("#comUrl").val()=="" || $("#comUrl").val()==null){
			$.messager.alert("提示","请填写公司网址!","info");
			return false;
		}
		//保存并继续添加
		if(typt==0){
			$.messager.confirm('确定','是否保存数据并继续添加？',	function(flag) {
				if (flag) {
					saveOrDepBadge(typt);
				}
			});
		}
		//保存并关闭
		if(typt==1){
			$.messager.confirm('确定','是否保存数据并关闭页面？',	function(flag) {
				if (flag) {
					saveOrDepBadge(typt);
					}
			});
		}
		
		
	} 
	//保存数据
	function saveOrDepBadge(typt){
		$.ajax({
			cache:true,
			type:'POST',
			url:"callingCard/saveCardApp.do",
			data:$('#appPeopleForm').serialize(),
			async:false,
			dataType:'JSON',
			success:function(res){
				 if(res!=null){
					if(typt==1){
						$("#addWindow").dialog('close');
					}else{
						badgePeopleGrid(res.appNo);
						$("#appPeopleForm").form('clear');//清空表单
// 						$("#appUserView").datagrid("reload");
						/* if(res!=null){
							$("#appPeoNo").val(res.appNo);
							badgePeopleGrid(res.appNo);
						} */
						$("#appPeoNo").val(res.appNo);
// 						$("#caId").val(res.caId);
// 						$("#appPeopleForm").form("load",res)
					}
					$.messager.alert("提示", '名片申请保存成功!',"info");
				}else{
					$.messager.alert("提示", '出错了，保存失败!',"error")
				} 
			}
		});
	}

 	 //申请人列表
    function badgePeopleGrid(appNo){
 		 var deptNo=$("#deptNo1").val();
    	$("#appUserView").datagrid({
    		url : "callingCard/findCardAttList.do",
    		queryParams:{
    			"appNo":appNo,
    			"deptNo":deptNo
    		},		
    		width : 795,
    		height : 400,
    		pagination:true,
    		rownumbers:true,
    		border:true,
    		singleSelect:false,
    		nowrap:true,
    		columns : [ [
						{field : 'appNo',title : '申请编号',width:140,align : 'center'},
    		            {field : 'fullName',title : '申请部门',width:140,align : 'center'},
						{field : 'name',title : '申请人',width:100,align : 'center',
							formatter : function(value, row, index) {
								return "<a href=\"javascript:void(0)\" onclick=\"showView("+ index + ");\">" + value + "</a>";
							}
						},
						{field : 'positionName',title : '职位',width:140,align : 'center'},
						{field : 'appQty',title : '申请数量',width:140,align : 'center'},
						{field : 'price',title : '单价',width:140,align : 'center'},
						{field : 'remark',title : '备注',width:140,align : 'center'}
    		]],
    		onLoadSuccess:function(data){
    			if($.trim($("#appPeoNo").val())!=''){
	    			$("#upploadAttachment").removeClass("l-btn-disabled");
	    			$("#checkAttachment").removeClass("l-btn-disabled");
    			}
    			
				  var rows = data.rows;
		          if(rows.length>0 && rows!=null){
		        	  var mergeMap = {};
		        	  if(rows){
				           	for(var i=0;i<rows.length;i++){
				           		var appNo = rows[i].appNo
				           		if( appNo in mergeMap ){
				           			mergeMap[appNo].rowspan++;
				           		}else{
				           			mergeMap[appNo]={"index":i,"rowspan":1}
				           		}
				           	}
				           }
				           for(var i in mergeMap){
				        	  // $(this).datagrid("autoMergeCells",['appNo']);
				        	   $(this).datagrid('mergeCells',{
				                   index: mergeMap[i].index,
				                   field: 'appNo',
				                   rowspan: mergeMap[i].rowspan
				            	 });
				        	   //合并部门
				           		$(this).datagrid("autoMergeCells",['fullName']);
				           }
				           $(this).datagrid("doCellTip",{'max-width':'100px'});
		          }
			  }, 
    		/* toolbar: [{
    			iconCls: 'icon-cancel',
    			text:'删除',
    			handler:toDelete
    		},{
    			iconCls: 'icon-edit',
    			text:'编辑',
    			handler:toEditAtta
    		}]  */
			 toolbar:'#tb1'
    	});
    	
    }
 	 
 	 function toEditAtta(){
     	var selected = $('#appUserView').datagrid('getSelections');
    	if (selected.length <= 0 || selected.length > 1) {
    		$.messager.alert("提示", "请选择一条记录执行编辑!", "warning");
    		return;
    	}else{
         	selected = $('#appUserView').datagrid('getSelected');
	    	$("#appPeopleForm").form("load",selected);
	    	$("#caId").val(selected.caId);
	    	/* $.ajax({
				cache:true,
				type:'POST',
				url:"callingCard/saveCardApp.do",
				data:$('#appPeopleForm').serialize(),
				async:false,
				dataType:'JSON',
				success:function(res){
					if(res!=null){
							badgePeopleGrid(res.appNo);
							$("#appPeopleForm").form('clear');//清空表单
							$("#pnrId").val(res.pnrId);
							$("#appPeoNo").val(res.appNo);
							$("#appUserView").datagrid('reload');
					}else{
						$.messager.alert("提示", '出错了，修改失败!',"error")
					}
				}
			}); */
    	}
 	 }
 	 
    //上传附件
    $("#upploadAttachment").click(function(){
    	if($.trim($("#appPeoNo").val())!=''){
    		fileUploadsDlg($("#appPeoNo").val());
    	}
	});
	//查看附件
	$("#checkAttachment").click(function(){
		if($.trim($("#appPeoNo").val())!=''){
			checkAttachementDetail($("#appPeoNo").val(),'');
    	}
	});
     //申请人删除
    function toDelete(){
    	var selected = $('#appUserView').datagrid('getSelections');
    	if (selected.length <= 0) {
    		$.messager.alert("提示", "请至少选择一条记录执行删除!", "warning");
    		return;
    	}
    	var ids = new Array();
    	for(var i=0,len=selected.length; i<len; i++){
    		ids.push(selected[i].caId);
    	}
    	ids = ids.join(",");
    	$.messager.confirm('删除', '执行删除后，数据将不可恢复,是否执行?', function(d) {
    		if (d) {
    			$.ajax( {
    				type : "POST",
    				url : 'callingCard/delCardApp.do',
    				data : {
    					"ids":ids
    				},
    				dataType:'JSON',
    				success : function(iJson) {
    					if(iJson.status){
    						//刷新列表
    						badgePeopleGrid($("#appPeoNo").val());
    						$("#deptNo1").combotree("clear");
    					}
    					$.messager.alert("提示", '删除成功!',"info")
    				}
    			});
    		}
    	});
    } 
     
     
</script>
<div id="tt" >
	<div title="申请人信息" >
	  <div class="well well-small" style="margin:5px;width:795px">
	     	<form id="appPeopleForm" >
	     	 <input id="caId" name="caId" type="hidden"/><!-- 申请id -->
	         <input id="appPeoNo" name="appNo" type="hidden"/><!-- 申请编号 -->
	         
	         <table class="table">
				<tr>
					<th>申请部门:</th>
					<td colspan="1"><input  name="deptNo" id="deptNo1" type="text" class="easyui-textbox easyui-validatebox" style="width: 170px"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<th>申请人:</th>
					<td colspan="2"><input name="name" id="userName" type="text" class="easyui-textbox  easyui-combobox" style="width: 170px" editable="false"/>&nbsp;</td>
				</tr>
				<tr>
					<th>地址:</th>
					<td>
						<input id="branchAddr" name="branchAddr" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,100]', required:true" maxlength="100"/>
					</td><th>数量:</th>
					<td>
						<input id="appQty" name="appQty" type="text" class="easyui-textbox easyui-validatebox easyui-numberbox" data-options="validType:'integer',required:true" min="1" max="9999"/>
					</td>
				</tr>
				<tr>
					<th>单价:</th>
					<td>
						<input id="price" name="price" type="text" class="easyui-textbox easyui-numberbox easyui-validatebox" precision='2'  data-options="validType:'length[0,100]', required:true" min="1" max="999.99"/>
					</td>
					<th>公司网址:</th>
					<td>
						<input id="comUrl" name="comUrl" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,255]'" maxlength="255"/>
					</td>
				</tr>
				 <tr>
					<th>备注:</th>
					<td colspan="3">
					  <textarea id="remark" name="remark" class="easyui-textbox easyui-validatebox" style="width: 700px; height: 80px;resize:none;" data-options="validType:'length[0,200]'" maxlength="200"></textarea>
					</td>
				</tr>
				<tr>
				   <td colspan="6" style="text-align: right;">
				   	  <a href="javascript:void(0)" id="save" class="easyui-linkbutton" iconCls="icon-save" onclick="toSaveBaseInfo(0);">保存并继续添加</a>
				      <a href="javascript:void(0)" id="rset" class="easyui-linkbutton" iconCls="icon-no" onclick="toSaveBaseInfo(1);">保存并关闭</a>
				      
				   </td>
				</tr>
	         </table>
	       </form>
	  </div>
	  <!-- 附件上传 -->
	  <div class="well well-small" style="margin:5px;width:795px">
	  	<table class="table" cellpadding="5px;">
			<tr>
				<td colspan="2">
					<a id="upploadAttachment" href="javascript:void(0);" class="easyui-linkbutton"  >上传附件</a>	
					<a id="checkAttachment" href="javascript:void(0);" class="easyui-linkbutton" >查看附件</a>		
				</td>
			</tr>
		</table>
	  </div>
	  <div id="tb1" style="padding:2px 0">
				<table cellpadding="0" cellspacing="0">
					<tr>
						<td>
							<a href="javascript:void(0);" class="easyui-linkbutton" id="removeAtta" iconCls="icon-remove" plain="true" onclick="toDelete();">删除</a>
						</td>
						<td>
							<a href="javascript:void(0);" class="easyui-linkbutton" id="editAtta" iconCls="icon-edit" plain="true" onclick="toEditAtta();">编辑</a>
						</td>
					</tr>
				</table>
			</div>
	   <div class="well well-small" style="margin: 5px;width:795px">
	        <table id="appUserView"></table>
	   </div>

	 	</div>
</div>
