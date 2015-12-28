<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/saveTaskJS/pd/saveTaskJavaScript.js"></script>
<!-- 需要加载的属性 -->
<input id="taskID" name="taskID" type="hidden"  value="" />
<input id="appNo" name="appNo" type="hidden"  value="" />
<input id="businessID" name="businessID" type="hidden"  value="" />
<input id="formKey" name="formKey" type="hidden"  value="" />
<!-- 以下属性为方法传递属性  -->
<input id="result" name="result" type="hidden"  value="" /> <!-- 传递的result表达式值 -->
<input id="isSuccess" name="isSuccess" type="hidden"  value="" /><!-- 1通过 0驳回 2拒绝 操作标识 -->
<input id="formKeyJson" name="formKeyJson" type="hidden"  value="" /><!-- 根据formKey取出对应的formKeyJson -->

<div id="applyDiv">
   	<a href="javascript:void(0)" id="applyForAdjustmentTZ" class="easyui-linkbutton"  onclick="toSaveOrUpdateInvestProductOpenDlg();">申请调整</a>
	<a href="javascript:void(0)" id="applyForAdjustmentSubmit" class="easyui-linkbutton"  onclick="saveTaskFunc(0);">申请重提</a>
   	<a href="javascript:void(0)" id="applyForAdjustmentBack" class="easyui-linkbutton"  onclick="saveTaskFunc(1);">申请撤销</a>
</div>
<div id="saveTaskDiv">
	<a id="upploadAttachment" href="javascript:void(0);" class="easyui-linkbutton" >上传附件</a>
	<a id="checkAttachment" href="javascript:void(0);" class="easyui-linkbutton">查看附件</a>	
	<a href="javascript:void(0)" id="saveButton"  name="saveButton" class="easyui-linkbutton" onclick="saveTaskFunc(1);">申请通过</a>
	<a href="javascript:void(0)" id="rejectButton" name="rejectButton" class="easyui-linkbutton" onclick="saveTaskFunc(0);">申请驳回</a>
	<a href="javascript:void(0)" id="refusalButton" name="refusalButton" class="easyui-linkbutton"  onclick="saveTaskFunc(2);">申请拒绝</a>
</div>
