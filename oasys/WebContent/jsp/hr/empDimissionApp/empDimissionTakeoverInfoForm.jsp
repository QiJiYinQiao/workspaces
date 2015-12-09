<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<script type="text/javascript">
	
	</script>
     <form id="linkPeopleForm" style="width: 90%;height:90%;margin-left:15px;margin-top: 20px">
	    <!-- <fieldset>
		    <legend><img src="extend/fromedit.png" style="margin-bottom: -3px;"/>物料信息</legend> -->
		    <input id="appNum" name="appNo" type="hidden">
       	<input id="ptaId" name="ptaId" type="hidden">
         <table class="table">
           <tr>
             <th>交接内容:</th>
             <td><input id="takeoverGoods" name="takeoverGoods" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,16]'"/></td>
             <th>接管人</th>
             <td><input name="receiverNo" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,16]'"/></td>
           </tr>
           <tr>
             <th>接管日期:</th>
             <td><input id="receiveDate" name="receiveDate" class="easyui-textbox  easyui-validatebox" disabled="disabled"/></td>
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
    