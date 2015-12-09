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
             <th>资产编号:</th>
             <td><input id="ppeNo" name="ppeNo" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'length[0,16]'" onchange="loadPpe()"/></td>
             <th>名称</th>
             <td><input id="ppeName" name="ppeName" class="easyui-textbox easyui-validatebox" readonly="readonly" data-options="required:true,validType:'length[0,16]'"/></td>
           </tr>
           <tr>
             <th>规格型号:</th>
             <td><input id="model" name="model" class="easyui-textbox easyui-validatebox" readonly="readonly" panelHeight="auto" editable="false" data-options="required:true"/></td>
             <th>数量:</th>
             <td><input id="qty" name="qty" class="easyui-textbox easyui-numberbox easyui-validatebox" editable="false"  readonly="readonly" data-options="required:true,min:0,max:9999"/></td>
           </tr>
           <tr>
             <th>单价:</th>
             <td><input id="ppeAmt" name="ppeAmt" class="easyui-textbox easyui-numberbox easyui-validatebox" readonly="readonly" panelHeight="auto" editable="false" data-options="required:true,min:0,max:999999,precision:2,groupSeparator:','"/></td>
           </tr>
         </table>
	    <!-- </fieldset> -->
    </form>
    