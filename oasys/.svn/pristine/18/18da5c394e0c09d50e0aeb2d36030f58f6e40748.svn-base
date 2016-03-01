<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<script type="text/javascript">
	$(function(){
		//初始化组织机构
		$("#receiverDeptName").combotree({
			width:'auto',
			url:"Organization/organizationList.do",
			idFiled:'id',
		 	textFiled:'name',
		 	valueFiled:'id',
		 	parentField:'pid',
		 	onLoadSuccess:function(data){
		 		//加一个全部
		 	},
		 	onChange:function(){
		 			$("#receiverDept").val($(this).combotree('getValue'));
		 			 RenderName($(this).combotree('getValue'));
		 	}
		}); 
		
		$("#receiverName").combobox({required:true});
	});
	
	//用户名的下拉
	function RenderName(organizeId){
		//选中部门后下拉框
			$("#receiverName").combobox({
				width:'auto',
				multiple:false,
				separator:",", 
				url:"BadgeApp/findOrgUserList.do?organizeId="+organizeId,
				valueField:'code',
			 	textFiled:'text',
			 	onLoadSuccess:function(data){
			 		//加一个全部
			 	},
			 	onChange:function(){
			 		$("#receiverNo").val($(this).combotree('getValue'));
			 	}
			}); 
	}
	</script>
     <form id="linkPeopleForm" style="width: 90%;height:90%;margin-left:15px;margin-top: 20px">
	    <!-- <fieldset>
		    <legend><img src="extend/fromedit.png" style="margin-bottom: -3px;"/>物料信息</legend> -->
		    <input id="appNum" name="appNo" type="hidden">
       	<input id="daiId" name="daiId" type="hidden">
         <table class="table">
           <tr>
             <th>交接内容:</th>
             <td><input id="takeoverGoods" name="takeoverGoods" class="easyui-textbox easyui-validatebox" editable="false" data-options="validType:'length[0,16]'"/></td>
           	 <th>接管部门:</th>
             <td><input id="receiverDeptName" name="receiverDeptName" class="easyui-textbox" required  data-options="editable:false"/>
             	<input id="receiverDept" name="receiverDept" type="hidden"/>
             </td>
           </tr>
           <tr>
             <th>接管人</th>
             <td>
             <input id="receiverName" name="receiverName" class="easyui-textbox easyui-combobox easyui-validatebox" data-options="editable:false"/>
             <input id="receiverNo" name="receiverNo" type="hidden"/>
             </td>
             <th>接管日期:</th>
             <td><input id="receiveDate" name="receiveDate" class="easyui-textbox  easyui-validatebox" editable="false" disabled="disabled"/></td>
           </tr>
           <tr>
             <th>交接情况:</th>
             <td><input id="takeoverDetail" name="takeoverDetail" class="easyui-textbox easyui-validatebox" editable="false"/></td>
           </tr>
           <tr>
		         <th class="textStyle">备注:</th>
		         <td colspan="5">
		            <textarea name="remark" rows="5" cols="20" class="easyui-textbox size" data-options="validType:'length[0,200]'"></textarea>
		         </td>
		       </tr>
             <tr>
         </table>
	    <!-- </fieldset> -->
    </form>
    