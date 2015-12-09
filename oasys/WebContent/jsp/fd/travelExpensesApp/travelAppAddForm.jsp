<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/validate.js"></script>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
	
	var lrow;
	
	$(function(){
		$('#tt').tabs({    
		    border:false,    
		    onSelect:function(title){    
		    	
		    }    
		}); 
		//借款金额
		findLoanAMT();
		
		$("#realBgDtime").datetimebox({required:true});
		$("#realEdDtime").datetimebox({required:true});
		
		aDisable();//判断上传附件
		//其他费用项目列表
		travelAppGrid($("#appNoAdd").val());
		//交通费用列表
		businessgrid($("#appNoAdd").val());
		//交通类型
		findSysNameList();
	});

	//查询个人可选的借款申请
	function findLoanAMT(){
		$("#jkAppNo").combobox({
			width:171,
			multiple:true,
			separator:",", 
			url:"TravelExpensesApp/findUserLoanList.do",
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
			url:"TravelExpensesApp/findSelectUserLoan.do?appNo="+appNo,
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
	
	//附件按钮
	function aDisable(){
		if($.trim($("#appNoAdd").val())==''){
			$("#upploadAttachment").attr("disabled",true);
			$("#checkAttachment").attr("disabled",true);
		}else{
			$("#upploadAttachment").removeClass("l-btn-disabled");
			$("#checkAttachment").removeClass("l-btn-disabled");
		}
	}
	
	//交通类型
	function findSysNameList(){
		 //添加申请时，首先添加主表信息
		$.ajax({
			cache:true,
			type:'POST',
			url:'TravelExpensesApp/findBusinVehicleLIst.do',
			async:false,
			success:function(res){
				for(var i=0;i<res.length;i++){
					$("#ve").append("<input type='radio' id='radio"+i+"' name='vehicle' value='"+res[i].dictCode+"' onclick='addvehicleOtherr();'/>"+res[i].dictName+"");
				}
				//默认选中第一个
				$("#radio0").attr("checked","checked");
	    		$("#ve").append("<input  id='vehicleOther' name='vehicleOther' hidden='true' style='width: 120px;height:11px' class='easyui-textbox easyui-validatebox' data-options='editable:false'/>");

			}
		}); 
	}

	//判断交通工具类型
	function addvehicleOtherr(){
		if($("#radio5").is(":checked")){
	  		 $("#vehicleOther").show();
	  		 $("#vehicleOther").validatebox({required:true});
	  	 }else{
	  		 $("#vehicleOther").hide();
	  		 $("#vehicleOther").validatebox({required:false});
	  	 } 
	}
	
	
	//保存主表数据
	function toSaveBaseInfo(from){
		var isValid = $("#travelFrom").form('validate');
    	if(!isValid){
    		return false;
    	}
		
		$.ajax({
			cache:true,
			type:'POST',
			url:"TravelExpensesApp/saveTravelExpensesApp.do",
			data:$('#travelFrom').serialize(),
			async:false,
			dataType:'JSON',
			success:function(res){
				 if(res!=null){
					 
					 var f = $("#travelFrom");
					 f.form("load", res);
					 lrow=res;
					 
					 //交通申请
					 $("#appNoBus").val(res.appNo);
					 //保存编辑按钮转换
					 $("#"+from+" a[iconCls = 'icon-edit']").show();
					 $("#"+from+" a[iconCls = 'icon-ok']").hide();
					 //禁用
					 $("#btDays").attr({"disabled":"disabled"});
					 $("#subsidyAmt").attr({"disabled":"disabled"});
					 $("#btReason").attr({"disabled":"disabled"});
					 $("#remark").attr({"disabled":"disabled"});
					 $("#grantExp").attr({"disabled":"disabled"});
					 //禁用借款下拉					 
					 $("#jkAppNo").combobox("disable");
					 $("#jkAppNo").combobox("setText",res.jkAppNo);
					 
					 aDisable();
					 $.messager.alert("提示", '差旅报销申请信息保存成功!',"info")
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
		 $("#btDays").attr("disabled",false);
		 $("#subsidyAmt").attr("disabled",false);
		 $("#btReason").attr("disabled",false);
		 $("#remark").attr("disabled",false);
		 $("#grantExp").attr("disabled",false);
	}
	
	
 	 //费用项目列表
    function travelAppGrid(appNo){
    	$("#appUserView").datagrid({
    		url : "TravelExpensesApp/findTravelOtherList.do",
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
						{field : 'appNo',title : '申请编号',width:152,align : 'center'},
    		            {field : 'expItemName',title : '费用项目',width:159,align : 'center'},
						{field : 'bills',title : '单据张数',width : 180,sortable : true,align : 'center'},
						{field : 'total',title : '总计金额',width:140,align : 'center'},
    		]],
    		onLoadSuccess:function(data){
    			 $("#appUserView").datagrid("doCellTip",{'max-width':'100px'});
    			aDisable();
			}, 
    		toolbar: [{
    			iconCls: 'icon-add',
    			text:'添加',
    			handler:addTravelOther
    		},
    		{
    			iconCls: 'icon-cancel',
    			text:'删除',
    			handler:toDelete
    		},{
    			iconCls: 'icon-edit',
    			text:'编辑',
    			handler:EditTravelOther
    		}]  
    	});
    	
    }
 	 
 	 //判断添加报销申请
 	 function changeMyTitle(row){
 		 if(row==null){
 			 return "添加差旅报销项目";
 		 }else{
 			 return  "编辑差旅报销项目"
 		 }
 	 }
 	 function changeMyicon(row){
 		 if(row==null){
 			 return "icon-add";
 		 }else{
 			 return "icon-edit";
 		 }
 	 }
 	 
 	 function addTravelOther(){
 		addOrEditTravelOther();
 		 
 	 }
 	 function EditTravelOther(){
 		var rows = $("#appUserView").datagrid("getSelections");
 		if(rows[0]==null || rows.length>1){
 			$.messager.alert("提示","您只能选择一条记录进行修改!","warning");
 			return false;
 		}else{
 			addOrEditTravelOther(rows[0])
 		}
 	 }
 	 
 	 //添加报销项目信息
 	 function addOrEditTravelOther(rowse){
 		
 		if($("#travelFrom input[name='appNo'][type='hidden']").val() == ""){
 			$.messager.alert("提示","请先保存基本信息!","warning");
 			return false;
 		}else{
 			
	 		$addOrEditDialog = $("<div></div>").dialog({
				title:changeMyTitle(rowse),
				iconCls: changeMyicon(rowse),
				width:538,
				height:160,
				modal:true,
				href:"jsp/fd/travelExpensesApp/travelOtherAddForm.jsp",
				onLoad:function(){
					$("#appNoOther").val($("#appNoAdd").val());
					if(rowse!=null){
						var f = $("#travelOtherFrom");
						f.form("load", rowse); 
					}
				},
				onClose:function(){
					$(this).dialog('destroy');
					travelAppGrid($("#appNoAdd").val());
				}
		 	});
 		}
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
   
     //报销项目删除
    function toDelete(){
    	var selected = $('#appUserView').datagrid('getSelections');
    	if (selected.length <= 0) {
    		$.messager.alert("提示", "请至少选择一条记录执行删除!", "warning");
    		return;
    	}
    	var ids = new Array();
    	for(var i=0,len=selected.length; i<len; i++){
    		ids.push(selected[i].teoId);
    	}
    	ids = ids.join(",");
    	$.messager.confirm('删除', '执行删除后，数据将不可恢复,是否执行?', function(d) {
    		if (d) {
    			$.ajax( {
    				type : "POST",
    				url : 'TravelExpensesApp/deleteTravelOther.do',
    				data : {
    					"ids":ids,
    					"appNo":$("#appNoAdd").val()
    				},
    				async:false,
    				dataType:'JSON',
    				success : function(iJson) {
    					if(iJson.status){
    						//刷新列表
    						travelAppGrid($("#appNoAdd").val());
    					}
    					$.messager.alert(iJson.title,iJson.message,'info');	
    				}
    			});
    		}
    	});
    } 
     
     
     //交通费用类表
     function businessgrid(appNo){
    	 $("#appBusView").datagrid({
     		url : "TravelExpensesApp/findBusinessAttList.do",
     		queryParams:{
     			"appNo":appNo,
     		},		
     		width : 680,
     		height : 400,
     		pagination:true,
     		rownumbers:true,
     		border:true,
     		singleSelect:false,
     		pageList:[10,20,30,40],
     		nowrap:true,
     		columns : [ [
 						{field : 'btOrig',title : '出差始发地',width:90,align : 'center'},
 						{field : 'btDest',title : '出差目的地',width:90,align : 'center'},
 						{field : 'realBgDtime',title : '出差开始时间',width:100,align : 'center'},
 						{field : 'realEdDtime',title : '出差结束时间',width:100,align : 'center'},
     		            {field : 'vehicleName',title : '交通工具',width:100,align : 'center'},
 						{field : 'bills',title : '单据张数',width : 60,sortable : true,align : 'center'},
 						{field : 'total',title : '总计金额',width:90,align : 'center'},
     		]],
     		onLoadSuccess:function(data){
     			 $("#appBusView").datagrid("doCellTip",{'max-width':'100px'});
     			aDisable();
 			}, 
     		toolbar: [{
     			iconCls: 'icon-cancel',
     			text:'删除',
     			handler:deleteBusiness
     		},{
     			iconCls: 'icon-edit',
     			text:'编辑',
     			handler:EditBusiness
     		}] 
     	});
    	 
     }
     
     //更新时间
    function valdate(date){
		var nd=date.getFullYear()+"-"+(date.getMonth()+1)+"-"+ date.getDate()+" "+date.getHours()+":00:00";
		return nd;
	}
     
     //删除交通工具项目
     function deleteBusiness(){
    	 var selected = $('#appBusView').datagrid('getSelections');
     	if (selected.length <= 0) {
     		$.messager.alert("提示", "请至少选择一条记录执行删除!", "warning");
     		return;
     	}
     	var ids = new Array();
     	for(var i=0,len=selected.length; i<len; i++){
     		ids.push(selected[i].btaId);
     	}
     	ids = ids.join(",");
     	$.messager.confirm('删除', '执行删除后，数据将不可恢复,是否执行?', function(d) {
     		if (d) {
     			$.ajax( {
     				type : "POST",
     				url : 'TravelExpensesApp/deleteBusinessTripAtt.do',
     				data : {
     					"ids":ids,
     					"appNo":$("#appNoAdd").val()
     				},
     				async:false,
     				dataType:'JSON',
     				success : function(iJson) {
     					if(iJson.status){
     						//刷新列表
     						businessgrid($("#appNoAdd").val());
     					}
     					$.messager.alert(iJson.title,iJson.message,'info');	
     				}
     			});
     		}
     	});
     }
     
     //编辑交通工具项目
     function EditBusiness(){
    	 var rows = $("#appBusView").datagrid("getSelections");
  		if(rows[0]==null || rows.length>1){
  			$.messager.alert("提示","您只能选择一条记录进行修改!","warning");
  			return false;
  		}else{
  			if($("#travelFrom input[name='appNo'][type='hidden']").val() == ""){
  	 			$.messager.alert("提示","请先保存基本信息!","warning");
  	 		}else{
				var f = $("#businessFrom");
				f.form("load", rows[0]); 
  	 		}
  		}
    	 
     }
     
   //保存交通申请数据
 	function toSaveBusinessAtt(){
 		if($("#businessFrom input[name='appNo'][type='hidden']").val() == ""){
 			$.messager.alert("提示","请先保存基本信息!","warning");
 			return false;
 		}else{
 			var isValid = $("#businessFrom").form('validate');
 	    	if(!isValid){
 	    		return false;
 	    	}
 	    	
	 		$.messager.confirm('确定','是否保存数据？',	function(flag) {
					if (flag) {
						$.ajax({
				 			cache:true,
				 			type:'POST',
				 			url:"TravelExpensesApp/saveBusinessTripAtt.do",
				 			data:$('#businessFrom').serialize(),
				 			async:false,
				 			dataType:'JSON',
				 			success:function(res){
				 				 if(res!=null){
				 						$("#businessFrom").form('clear');//清空表单
				 						$("#appNoBus").val(res);
				 						 businessgrid(res);
				 					$.messager.alert("提示", '交通费用信息保存成功!',"info")
				 				}else{
				 					$.messager.alert("提示", '出错了，保存失败!',"error")
				 				} 
				 			}
				 		});
					}
			});
 		}
 		
 	}
     
   
   $("#btDays").blur(function(){
	   //修正数值，
	   $("#btDays").numberbox("fix");
	   
	   var btDays=0;
	   if($("#btDays").val()!=null && $("#btDays").val()!=""){
		   btDays=$("#btDays").val();
	   }else{
		   $("#btDays").val(0.0);
	   }
	   
	   $.ajax({
			cache:true,
			type:'POST',
			url:"TravelExpensesApp/setTravelSubsidyAmt.do",
			data:{
				"btDays":btDays,
			},
			async:false,
			dataType:'JSON',
			success:function(res){
				$("#subsidyAmt").numberbox('setValue',res);
			}
		});
	   
   });
     
</script>
<div id="tt" style="height: auto;">
	<div title="差旅报销申请信息" >
	  <div class="well well-small" style="margin:5px;width:680px">
	     
	     	<form id="travelFrom" >
	     	 <input id="teaId" name="teaId" type="hidden"/><!-- 申请id -->
	         <input id="appNoAdd" name="appNo" type="hidden"/><!-- 申请编号 -->
	         <input id="applicantNo" name="applicantNo" type="hidden"/><!-- 申请人id-->
	         
	         <table class="table">
	         	 <tr>
	         		<th >借款选项:</th>
					<td >
						<input name="jkAppNo" id="jkAppNo" type="text" class="easyui-textbox  easyui-combobox" style="width: 170px" />&nbsp;
					</td>
					<th style="width: 58px;">预借旅费:</th>
					<td>
						<input id="grantExp" name="grantExp"  type="text" style="width: 50px;text-align: center;" value="0" class="easyui-textbox easyui-validatebox easyui-numberbox" precision="2"  data-options="editable:false">元</input>
					</td>
	         	 </tr>
	         	 <tr>
	         	 	<th>出差天数:</th>
	         	 	<td>
	         	 		<input id="btDays" name="btDays"  type="text" style="width: 50px;text-align: center;" value="0" class=" easyui-validatebox easyui-numberbox" data-options="editable:false" precision="1">天</input>
	         	 	</td>
	         	 	<th style="width: 58px;">补贴金额:</th>
	         	 	<td>
	         	 		<input id="subsidyAmt" name="subsidyAmt"  type="text" style="width: 50px;text-align: center;" value="0" class="easyui-textbox easyui-validatebox easyui-numberbox" precision="2" data-options="editable:false">元</input>
	         	 	</td>
	         	 </tr>
				 
				 <tr>
					<th>出差事由:</th>
					<td colspan="3">
					  <textarea id="btReason" name="btReason" class="easyui-textbox easyui-validatebox" style="width: 606px; height: 60px;resize:none;" data-options="required:true,validType:'length[0,100]'"></textarea>
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
				   	  <a href="javascript:void(0)" id="save" class="easyui-linkbutton" iconCls="icon-ok" onclick="toSaveBaseInfo('travelFrom');">保存</a>
			      	  <a href="javascript:void(0)" id="save" class="easyui-linkbutton"  iconCls="icon-edit" style="display:none;" onclick="editForm('travelFrom')">编辑</a>
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
	
	<div title="交通费用">
	  <div class="well well-small" style="margin:5px;width:680px">
		<form id="businessFrom">
			<input id="btaId" name="btaId" type="hidden"/>		
			<input id="appNoBus" name="appNo" type="hidden"/>
			
			<table class="table" >
				<tr>
					<th>出差事发地：</th>
					<td>
						<input  name="btOrig" id="btOrig" class="easyui-textbox easyui-validatebox "  data-options="required:true,editable:false" type="text" style="width: 167px"/>&nbsp;
					</td>
					
					<th>出差目的地：</th>
					<td>
						<input  name="btDest" id="btDest" class="easyui-textbox easyui-validatebox "  data-options="required:true,editable:false" type="text" style="width: 167px"/>&nbsp;
					</td>
				</tr>
				
				<tr>
					<th>出发时间：</th>
					<td>
						<input id="realBgDtime" name="realBgDtime" placeholder="请选择起始日期" class="easyui-textbox easyui-datetimebox easyui-validatebox" data-options="formatter:valdate,editable:false" />
					</td>
					
					<th>到达时间：</th>
					<td>
						<input id="realEdDtime" name="realEdDtime" placeholder="请选择起始日期" class="easyui-textbox easyui-datetimebox easyui-validatebox" data-options="formatter:valdate,editable:false" />
					</td>
				</tr>
				
				<tr>
					<th>交通工具：</th>
					<td id="ve"  colspan="3" style="height: 22px">
						
					</td>
				</tr>
				
				<tr>
					<th>单据张数：</th>
					<td>
						<input id="bills" name="bills"  type="text" style="width: 50px;text-align: center;" value="0" class="easyui-textbox easyui-validatebox easyui-numberbox" data-options="required:true,editable:false">张</input>
					</td>
				</tr>
			
				<tr>
				 	 <th>合计金额：</th>
					 <td>
					 	<input id="total" name="total"  type="text" style="width: 50px;text-align: center;" value="0" class="easyui-textbox easyui-validatebox easyui-numberbox" precision="2" data-options="required:true,editable:false" >元</input>
					 </td>
				</tr>
				
				<tr>
				   <td colspan="6" style="text-align: right;">
				   	  <a href="javascript:void(0)" id="savebusin" class="easyui-linkbutton" iconCls="icon-save" onclick="toSaveBusinessAtt();">保存</a>
				   </td>
				</tr>
			</table>
		</form>
	  </div>
	  
	  <!-- 交通费用附加表-->
	  <div class="well well-small" style="margin: 5px;width:680px;height: auto;">
	        <table id="appBusView"></table>
	  </div>
	</div>
	
</div>
