<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
// 上传附件
function checkOrderID(obj){
	   if(checkIsSaveLoaner()){
		   //上传附件所需订单ID
		   fileUploads(obj,$("#loanOrderId").val());
	   }
}
</script>
<div class="well well-small" style="margin-left: 5px;margin-top: 5px;width: 850px;">
   <form id="fujianxinxi" method="post">
		<input name="auditId" type="hidden" value="noauditId"/>		
		<div id="attachmentList" style="width:100%;display:block;float:left;">
		</div>
		<div id="upload_form_div_adds">
			<div id="upload_form_father_idDivs" style="width:100%;">
				<div id="upload_form_divs">
					　上传附件　　
					<input class="easyui-textbox easyui-combobox" type="text" />
					<input name="fileName" type="text" placeholder="请输入附件名">
					<input id="fileIs" name="file" type="file"  onchange="fileChange(this);" > 
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addACredential(this);">添加</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeACredential(this);">删除</a> 
				</div>
			</div>
		</div>
		<div id="upload_form" style="width: 100%; height: 30px; text-align: right;">
			<a href="javascript:void(0);" class="easyui-linkbutton" onclick="checkOrderID(this);">上传附件</a>
		</div> 
	</form>
</div>