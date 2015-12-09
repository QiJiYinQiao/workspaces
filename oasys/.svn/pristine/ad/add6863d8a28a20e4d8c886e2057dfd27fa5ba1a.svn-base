<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">



//初始化构建列表
$(function(){
		//setComboTreeFunc($("#curDeptName1"),$("#curDeptNo"),$("#curDeptName"));
		setComboTreeFunc($("#aftDeptName1"),$("#aftDeptNo"),$("#aftDeptName"));
		setComboboxFunc($("#chgTypeName"),$("#chgType"),'chg_type');
		setComboboxFunc($("#chgResonName"),$("#chgReson"),'chg_reson');
		setComboboxFunc($("#salChgTypeName"),$("#salChgMode"),'sal_chg_type');
		if($("#chgResonOther").val() != ""){
			hideOtherFunc(0);
		}else{
			hideOtherFunc(1);
		}
});

function hideOtherFunc(type){
	if(type==0){
			$("#chgOtherDiv").css("display","block");
			$("#chgDiv").css("display","none"); 
	}else{
		$("#chgOtherDiv").css("display","none");
		$("#chgResonOther").val(""); 
		$("#chgDiv").css("display","block");
	}
}

function setComboboxFunc(combox,hiInput,code){
	//异动事由
	combox.combobox({
		width:171,
		url:"systemCodeController/findSysDictListByCode.do?code="+code,
		valueField:'dictCode',
		textField:'dictName',
	 	onLoadSuccess:function(data){
	 	},
	 	onChange:function(){
	 		//变动理由下拉框选择其他时 将下拉框改为文本框 输入显示
	 		if(code=='chg_reson' && $(this).combobox('getValue') == '4'){
	 			hideOtherFunc(0);
	 		}else{
	 			hideOtherFunc(1);
	 		}
	 		hiInput.val($(this).combobox('getValue'));
	 	}
	}); 		
}

function setComboTreeFunc(comboTree,val,vname){
	//调整后部门
	comboTree.combotree({
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
	 	   	var t = comboTree.combotree('tree'); // 得到树对象  
	 	    var n = t.tree('getSelected'); // 得到选择的节点  
	 	   	val.val($(this).combotree('getValue'));
	 	   	vname.val(n.text);
	 	   	
	 	   	if(vname != 'chg_type'  && vname != 'chg_reson' && vname != 'sal_chg_type'){
		 	  RenderName($(this).combotree('getValue'));
	 	   	}
	 	}
	}); 		
}

//加载职位下拉框
function RenderName(organizeId){
	$("#aftPosition1").val("");
	$("#aftPosition1").combobox({
		width:171,
		multiple:false,
		separator:",", 
		url:"Role/findRoleListByOrgID.do?orgID="+organizeId,
		valueField:'code',
	 	textFiled:'text',
	 	onLoadSuccess:function(data){
	 		//加一个全部
	 	},
	 	onChange:function(){
	 		$("#aftPosition").val($(this).combotree('getValue'));
	 	}
	}); 
}
</script>
    <form id="EmpSalAppForm"  method="post" style="width: 880px;margin-left:5px;">
      
    <input id="curDeptNo" name="curDeptNo" type="hidden"  value="${empSal.curDeptNo }" />
    <input id="efaId" name="efaId" type="hidden"  value="${empSal.efaId }" />
    <input id="appNo" name="appNo" type="hidden"  value="${empSal.appNo }" />
    <input id="procStatus" name="procStatus" type="hidden"  value="${empSal.procStatus }" />
    <input id="appDate" name="appDate" type="hidden"  value="${empSal.appDate }" />
    <input id="aftDeptNo" name="aftDeptNo" type="hidden"  value="${empSal.aftDeptNo }" />
    <input id="aftDeptName" name="aftDeptName"  type="hidden" value="${empSal.aftDeptName }" />
    <input id="aftPosition" name="aftPosition"  type="hidden" value="${empSal.aftPosition }" />
    <input id="curPosition" name="curPosition"  type="hidden" value="${empSal.curPosition }" />
    <input id="applicantNo" name="applicantNo"  type="hidden" value="${empSal.applicantNo }" />
    <input type="hidden" id="chgType" name="chgType" value="${empSal.chgType }" />
    <input type="hidden" id="salChgMode" name="salChgMode" value="${empSal.salChgMode }" />
    <input type="hidden" id="chgReson" name="chgReson" value="${empSal.chgReson }" />
     
 		<table class="table"  width="95%">
				<tr>
					<th align="right">入职日期:</th>
					<td colspan="5">
						<input id="entryDate" name="entryDate" value="${empSal.entryDate}"  type="text" class="easyui-datebox "  editable="false" data-options="required:true"/>
					</td>
				</tr>
				<tr>
					<th align="right">目前部门:</th>
					<td><input name="curDeptName" id="curDeptName"  class="easyui-textbox "  type="text"  style="width: 170px" editable="false" data-options="required:true" value="${empSal.curDeptName }" readonly="readonly"/></td>
					<th align="right">目前所在职位:</th>
					<td><input name="curPositionStr" id="curPositionStr" type="text" class="easyui-textbox  easyui-validatebox"  style="width: 170px"  data-options="validType:'length[0,50]',required:true" readonly="readonly" value="${empSal.curPositionStr }"/></td>
					<th align="right">目前薪资:</th>
					<td>
						<input name="curSal" id="curSal" value="${empSal.curSal}"  type="text"   class="easyui-numberbox easyui-textbox  "  data-options="validType:'length[0,10]',validType:'int',required:true"/>
					</td>				
				</tr>
				<tr>
					<th align="right" colspan="1">目前工作内容:</th>
					<td colspan="5">
						<textarea name="curWorking"  id="curWorking"  class="easyui-textarea easyui-validatebox" style="width: 600px; height: 60px;" data-options="required:true,validType:'length[0,250]'">${empSal.curWorking }</textarea>
					</td>
				</tr>	
				<tr>
					<th align="right">调整后部门:</th>
					<td><input name="aftDeptName1" id="aftDeptName1"  class="easyui-textbox"  editable="false" type="text"  style="width: 170px" data-options="required:true" value="${empSal.aftDeptName }" readonly="readonly"  /></td>
					<th align="right">调整后职位:</th>
					<td><input name="aftPosition1" id="aftPosition1" type="text" class="easyui-textbox"  style="width: 170px" data-options="required:true" value="${empSal.aftPositionStr }"  readonly="readonly"/></td>
					<th align="right">调整后薪资:</th>
					<td>
						<input name="aftSal" id="aftSal" value="${empSal.aftSal}"  type="text"   class="easyui-numberbox easyui-textbox  "  data-options="validType:'length[0,10]',validType:'int',required:true"/>
					</td>				
				</tr>	
				<tr>
					<th align="right" colspan="1">调整后工作内容:</th>
					<td colspan="5">
						<textarea name="aftWorking"  id="aftWorking"  class="easyui-textarea easyui-validatebox" style="width: 600px; height: 60px;" data-options="required:true,validType:'length[0,250]'">${empSal.aftWorking }</textarea>
					</td>
				</tr>		
				<tr>
					<th align="right">异动事由:</th>						
					<td>
						<input id="chgTypeName" name="chgTypeName" value="${empSal.chgTypeName}" editable="false" type="text"  class="easyui-textbox" type="text"  style="width: 170px"  data-options="required:true"/>
					</td>	
					<th align="right">薪资调整形式:</th>						
					<td>
						<input id="salChgTypeName" name="salChgTypeName" value="${empSal.salChgTypeName}" editable="false" class="easyui-textbox" type="text"  style="width: 170px"  data-options="required:true"/>
					</td>	
					<th align="right">变动理由:</th>						
					<td>
						<div id="chgDiv">
						<input id="chgResonName" name="chgResonName" value="${empSal.chgResonName}" type="text"  editable="false" class="easyui-textbox"  type="text"  style="width: 170px" data-options="required:true"/>
						</div>
						<div id="chgOtherDiv" style="vertical-align: middle;" >
						<input id="chgResonOther" name="chgResonOther"   value="${empSal.chgResonOther}" type="text"   editable="false" class="easyui-textbox"  type="text"  style="width: 170px" data-options="required:true" />
						<img src="img/back.png"  onclick="javascript:hideOtherFunc(1);"/>
						</div>
					</td>				
				</tr>															
				<tr>
					<th align="right">申请生效日期:</th>
					<td>
						<input id="effectiveDate" name="effectiveDate" value="${empSal.effectiveDate}"  type="text" class="easyui-datebox"  editable="false" data-options="required:true"/>
					</td>
					<th align="right">试岗期限:</th>
					<td>
						<input id="trialPeriods" name="trialPeriods" value="${empSal.trialPeriods}"  type="text" class="easyui-textbox easyui-numberbox  "  data-options="validType:'length[0,2]',required:true"/>&nbsp;(月)
					</td>		
					<th align="right">试岗月薪:</th>
					<td>
						<input id="trialSal" name="trialSal" value="${empSal.trialSal}"  type="text" class="easyui-textbox easyui-numberbox "  data-options="validType:'length[0,10]',required:true"/>&nbsp;(元)
					</td>					
				</tr>						
				<tr>
					<th colspan="1" align="right">备注:</th>
					<td colspan="5"><textarea name="remark"  id="remark"  class="easyui-textarea " style="width: 600px; height: 90px;" data-options="validType:'length[0,200]'">${empSal.remark }</textarea></td>
				</tr>
				<tr><td colspan="6" height="10"></td></tr>
				<tr>
					<td colspan="3"></td>
<!-- 				   <td colspan="3" align="right">
				      <a href="javascript:void(0)" id="empSave" class="easyui-linkbutton" iconCls="icon-no" onclick="saveEmpSal();">保存并关闭</a>
				      <a href="javascript:void(0)" id="empClose" class="easyui-linkbutton"  onclick="closeWindow();">关闭页面</a>
				   </td> -->
				</tr>					
			</table>
    </form>
