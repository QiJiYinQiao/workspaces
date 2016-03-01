<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/validate.js"></script>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
	
	$(function(){
		//查询费用项目
		$.ajax({
			cache:true,
			type:'POST',
			url:'TravelExpensesApp/findSysDictList.do',
			async:false,
			success:function(res){
				for(var i=0;i<res.length;i++){
					$("#exp").append("<input type='radio' id='attradio"+i+"' name='expItemNo' value='"+res[i].dictCode+"' />"+res[i].dictName+"");
				}
				//默认选中第一个
				$("#attradio0").attr("checked","checked");
			}
		}); 
		
	});
	

	function toSaveBaseInfo(typt){
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
		var isValid = $("#travelOtherFrom").form('validate');
	    	if(!isValid){
	    		return false;
	    	}
		
		$.ajax({
			cache:true,
			type:'POST',
			url:"TravelExpensesApp/saveTravelOther.do",
			data:$('#travelOtherFrom').serialize(),
			async:false,
			dataType:'JSON',
			success:function(res){
				 if(res!=null){
					if(typt==1){
						travelAppGrid($("#appNoAdd").val());
						$addOrEditDialog.dialog('destroy');
					}else{
						travelAppGrid(res)
						$("#travelOtherFrom").form('clear');//清空表单
						$("#appNoOther").val(res);
					} 
					
					$.messager.alert("提示", '差旅报销信息保存成功!',"info")
				}else{
					$.messager.alert("提示", '出错了，保存失败!',"error")
				} 
			}
		});
	}

	 //校验单据张数
	   function valiBillnum(){
		   var v=$("#billsAtt").val();
		   if(parseFloat(v)<0){
			   $.messager.alert('提示', '单据张数不能小于0张！','error');
		   }else if(parseFloat(v)>99999999999){
			   $.messager.alert('提示', '单据张数不能大于99999999999张！','error');
		   }
	   }
	   
	   //校验合计金额
	   function valitotalnumber(){
		   var v=$("#totalAtt").val(); 
		   	var val=parseFloat(v);
		   	if(val<0 ){
		   		$.messager.alert('提示', '合计金额不能小于0元！','error');
		   	}else{
		   	 $.ajax({
					type:'POST',
					url:"TravelExpensesApp/findTravelExpAmt.do",
					data:{
						"appNo":$("#appNoAdd").val(),
					},
					async:false,
					dataType:'JSON',
					success:function(res){
						var t=(parseFloat(99999999.99)*100-parseFloat(res)*100)/100;
						if(val>parseFloat(t)){
				   			$("#totalAtt").numberbox("setValue",t);
				   			$.messager.alert('提示', "合计金额不能大于"+t+"元！",'error');
				   		}
					}
				});
		   	}
	   }

</script>
<div id="other" >
	<div title="差旅报销项目" >
	  <div class="well well-small" style="margin:5px;width:500px">
	     <form id="travelOtherFrom" >
	     	 <input id="teoId" name="teoId" type="hidden"/><!-- 申请id -->
	     	 <input id="appNoOther" name="appNo" type="hidden"/><!-- 申请编号 -->
	         
	         <table class="table">
				 <tr>
				 	<th>费用项目：</th>
				 	<td id="exp" colspan="3">
				 	
				 	</td>
				 </tr>
				 
				 <tr>
				 	<th>单据张数：</th>
				 	<td colspan="3">
				 		<input id="billsAtt" name="bills"  type="text" style="width: 100px;text-align: center;" value="0" class="easyui-textbox easyui-validatebox easyui-numberbox"  data-options="required:true,editable:false,min:0,max:99999999999" onchange="valiBillnum();">&nbsp;张</input>
				 	</td>
				 </tr>
				 <tr>
				 	 <th>合计金额：</th>
					 <td>
					 	<input id="totalAtt" name="total"  type="text" style="width: 100px;text-align: center;" value="0" precision="2" class="easyui-textbox easyui-validatebox easyui-numberbox" data-options="required:true,editable:false,min:0,max:99999999.99,groupSeparator:','" onchange="valitotalnumber();">&nbsp;元</input>
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
	</div>
</div>
