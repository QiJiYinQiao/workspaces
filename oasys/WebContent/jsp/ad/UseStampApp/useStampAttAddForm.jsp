<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/validate.js"></script>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
	
	$(function(){
		 
		 //印章类型
		 findSysNameList();
		 
		//初始化组织机构
		$("#useDept").combotree({
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
		 		//查询该部门下的印章
		  		findstampList();
		 	}
		});
		
		//加载印章
		findstampList();
		
		 $("#stampName").combobox({required:true});
		 
	});
	
	 //添加其他休假类型
    function addLeTypeOther(){
  	  if($("#useStampAttForm :input[type='radio']").last().is(":checked")){
  		  	$("#stampName").combobox({required:false});
  		  	$("#stampName").combobox("disable");
  		    $("#useDept").combotree("disable"); 	
  		    
  		  	$("#stampTypeOther").show();
  		  	$("#stampTypeOther").validatebox({required:true});
  	  }else{
	  		$("#stampTypeOther").hide();
	  		$("#stampTypeOther").validatebox({required:false});
	  		$("#useDept").combotree("enable"); 		  
			$("#stampName").combobox("enable");
	  		$("#stampName").combobox({required:true});
	  		//查询该分类下的印章
	  		findstampList();
  	  }
   } 
	
	//查询印章类型
	function findSysNameList(){
		 //添加申请时，首先添加主表信息
		$.ajax({
			cache:true,
			type:'POST',
			url:'UseStampApp/findDictNameList.do',
			async:false,
			success:function(res){
				for(var i=0;i<res.length;i++){
					$("#st").append("<input type='radio' id='radio"+i+"' name='stampType' value='"+res[i].dictCode+"'  onchange='addLeTypeOther();'/>"+res[i].dictName+"");
				}
				//默认选中第一个
				$("#radio0").attr("checked","checked");
				//其他休假类型
	    		$("#st").append("&nbsp;&nbsp;<input  id='stampTypeOther' name='stampTypeOther' hidden='true' style='width: 170px;height:11px' class='easyui-textbox easyui-validatebox' data-options='editable:false'/>");
			}
		}); 
	}
	
	//判断其他类型的印章是否归还
	$("#stampTypeOther").change(function(){
		$.ajax({
			cache:true,
			type:'POST',
			url:"UseStampApp/validateStampOther.do",
			data:{
				"appNo":$("#appAttNo").val(),
				"stampTypeOther":$("#stampTypeOther").val(),
			},
			async:false,
			dataType:'JSON',
			success:function(res){
				if(res!=null){
					$("#stampTypeOther").val("");					
					$.messager.alert(res.title, res.message,"error")
				}
					
			}
		});
	});
	
	
	
	//印章下拉框,印章分类，部门id
	function findstampList(){
		$("#stampName").combobox({
			width:171,
			url:"UseStampApp/findStampLIst.do?organizeId="+$("#useDept").combotree("getValue")+"&stampType="+$("#useStampAttForm :input[type='radio']:checked").val()+"&appNo="+$("#appAttNo").val(),
			valueField:'code',
		 	textFiled:'text',
		 	onLoadSuccess:function(data){
		 		//加一个全部
		 		
		 	},
		});		
		
	}
	
	function toSaveBaseAtt(typt){
		//保存并继续添加
		if(typt==0){
			$.messager.confirm('确定','是否保存数据并继续添加？',	function(flag) {
				if (flag) {
					saveOrDepStamp(typt);
					}
			});
		}
		//保存并关闭
		if(typt==1){
			$.messager.confirm('确定','是否保存数据并关闭页面？',	function(flag) {
				if (flag) {
					saveOrDepStamp(typt);
					}
			});
		}
	} 
	

	//申请人信息保存
	function saveOrDepStamp(typt){
		
		var isValid = $("#useStampAttForm").form('validate');
    	if(!isValid){
    		return false;
    	} 

		$.ajax({
			cache:true,
			type:'POST',
			url:"UseStampApp/saveUseStamp.do",
			data:$('#useStampAttForm').serialize(),
			async:false,
			dataType:'JSON',
			success:function(res){
				 if(res.status){
						if(typt==1){
							useStampGrid(res.data);
							$addOrEditDialog.dialog('destroy');
						}else{
							useStampGrid(res.data)
							$("#useStampAttForm").form('clear');//清空表单
							$("#appAttNo").val(res.data);
							$("#radio0").attr("checked","checked");
							findstampList();
						} 
						
						$.messager.alert(res.title, res.message,"info")
					}else{
						$.messager.alert(res.title, res.message,"error")
					}
			}
		});
	}
		
    
     //添加其他类型印章
     function addStampOther(){
    	 
    	 if($("#radio5").is(":checked")){
    		 $("#stampTypeOther").show();
    		 $("#stampTypeOther").validatebox({required:true});
    	 }else{
    		 $("#stampTypeOther").hide();
    		 $("#stampTypeOther").validatebox({required:false});
    	 }
    	
     }
     
  	
     
</script>
<div id="tt">
	<div title="用章申请详情">
	  <div class="well well-small" style="margin:5px;height: 270px" >
	     	<form id="useStampAttForm">
	          <input id="appAttNo" name="appNo" type="hidden"/><!-- 申请编号 -->
	      	  <input id="saaId" name="saaId" type="hidden"/><!-- 申请id -->
	          <input id="isGiveback" name="isGiveback" type="hidden"/><!-- 是否归还-->
	          <input id="receiverDtime" name="receiverDtime" type="hidden"/><!-- 接受时间-->
	          <input id="givebackDtime" name="givebackDtime" type="hidden"/><!-- 归还时间-->
	          <input id="emsNo" name="emsNo" type="hidden"/><!-- 快递单号 -->
	          <input id="postAddr" name="postAddr" type="hidden" /><!--邮寄地址 -->
	          <input id="contactWay" name="contactWay" type="hidden" /><!--联系方式 -->
	          
	         <table class="table">
				
				<tr>
					<th>印章类型:</th>
					<td id="st"  colspan="3" height="22" width="540">
					
					</td>
				</tr>
				<tr>
					<th>使用部门:</th>
						<td colspan="1">
							<input  name="useDept" id="useDept" type="text" class="easyui-textbox easyui-combotree" style="width: 170px"/>&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
						<th>印章名称:</th>
						<td colspan="3">
							<input name="stampName" id="stampName" type="text" class="easyui-textbox  easyui-combobox easyui-validatebox"  style="width: 170px" />&nbsp;
						</td>
				</tr>
				
				<tr>
					<th>用章事由:</th>
					<td colspan="3">
						<textarea id="useReason" name="useReason" class="easyui-textbox easyui-validatebox" maxlength="100"style="width: 540px;height: 80px;resize:none;" data-options="required:true,validType:'length[0,100]'"></textarea>
					</td>
				</tr>
				
				 <tr>
					<th>备注信息:</th>
					<td colspan="3">
					  <textarea id="attRemark" name="remark" class="easyui-textbox easyui-validatebox" maxlength="100"  style="width: 540px; height: 80px;resize:none;" data-options="validType:'length[0,100]'"></textarea>
					</td>
				</tr>
				<tr>
				   <td colspan="6" style="text-align: right;">
				   	  <a href="javascript:void(0)" id="save" class="easyui-linkbutton" iconCls="icon-save" onclick="toSaveBaseAtt('0');">保存并继续添加</a>
				   	  <a href="javascript:void(0)" id="save" class="easyui-linkbutton" iconCls="icon-save" onclick="toSaveBaseAtt('1');">保存并关闭页面</a>
				   </td>
				</tr>
	         </table>
	       </form>
	  </div>
	</div>
	
</div>
