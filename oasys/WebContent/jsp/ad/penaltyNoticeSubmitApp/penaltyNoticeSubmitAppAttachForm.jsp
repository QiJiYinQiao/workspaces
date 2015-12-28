<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<script type="text/javascript">
	
	</script>
     <form id="linkPeopleForm" style="width: 90%;height:90%;margin-left:15px;margin-top: 20px">
	    <!-- <fieldset>
		    <legend><img src="extend/fromedit.png" style="margin-bottom: -3px;"/>物料信息</legend> -->
		    <input id="appNum" name="appNo" type="hidden">
       	<input id="saaId" name="saaId" type="hidden">
       	<input id="ptDept" name="ptDept" type="hidden"/>
       	<input id="ptNo" name="ptNo" type="hidden"/>
       	<input id="signDeptNo" name="signDeptNo" type="hidden"/>
         <table class="table">
           <tr>
             <th>受罚部门:</th>
             <td><input id="ptDeptName" name="ptDeptName" class="easyui-textbox easyui-validatebox"/></td>
             <th>受罚人:</th>
             <td><input id="ptName" name="ptName" class="easyui-textbox easyui-validatebox"/></td>
             <th>受罚日期:</th>
             <td><input id="penaltyDate" name="penaltyDate" class="easyui-textbox  easyui-validatebox" disabled="disabled"/></td>
           </tr>
           <tr>
             <th>罚款金额:</th>
             <td><input id="penaltyAmt" name="penaltyAmt" class="easyui-numberbox easyui-textbox easyui-validatebox" data-options="required:true" precision="2" editable="false"/></td>
           	 <th>罚款单签发部门:</th>
             <td><input id="signDeptName" name="signDeptName" class="easyui-textbox easyui-validatebox"/></td>
           </tr>
           <tr>
           	 <th>受罚愿因:</th>
           	 <td><input id="penaltyReson" name="penaltyReson" class="easyui-textbox"/></td>
           	 <th id="hiddenOne" class="dis">其它受罚愿因:</th>
           	 <td id="hiddenTwo" class="dis"><input id="penaltyResonOther" name="penaltyResonOther" class="easyui-validatebox easyui-textbox"/></td>
           </tr>
           <tr>
		         <th class="textStyle">备注:</th>
		         <td colspan="6">
		            <textarea name="remark" rows="5" cols="20" class="easyui-textbox size" data-options="validType:'length[0,200]'"></textarea>
		         </td>
		       </tr>
             <tr>
         </table>
	    <!-- </fieldset> -->
    </form>
    