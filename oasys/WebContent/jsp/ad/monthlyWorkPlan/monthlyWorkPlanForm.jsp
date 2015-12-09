<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <form id="monthlyWorkPlanForm" method="post" style="width: 885px;margin-left:5px;">
    <fieldset>
    <legend><img src="extend/fromedit.png" style="margin-bottom: -3px;"/>基本信息</legend>
    <input id="mwpId" name="mwpId" type="hidden"/>
    <input name="preparer" type="hidden"/>
    <input name="appDept" type="hidden"/>
    <input name="fillingDate" type="hidden"/>
    <table class="table">
       <tr>
          <th class="textStyle">填表人职位:</th>
          <td><input name="position" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'length[0,25]'"/></td>
       </tr>
       <tr>
          <th class="textStyle">本月未完成<br/>工作及原因:</th>
          <td>
              <textarea name="ufwReson" rows="5" cols="20" class="easyui-textbox easyui-validatebox" data-options="required:true" maxlength="100" style="width: 720px;height: 50px;"></textarea>
          </td>
       </tr>
       <tr>
          <th class="textStyle">备注:</th>
	      <td>
	          <textarea name="remark" rows="5" cols="20" class="easyui-textbox easyui-validatebox" data-options="required:true" maxlength="100" style="width: 720px;height: 50px;"></textarea>
	      </td>
       </tr>
       <tr>
         <td colspan="6" style="text-align: right;">
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveMonthlyWorkPlan()">保存</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" style="display:none;" onclick="newEditForm(this)">编辑</a>
         </td>
       </tr>
    </table>
    </fieldset>
    </form>
    <div style="width: 881px;margin-left:7px;margin-top: 2px;">
       <table id="monthlyWorkPlanAttachGrid"></table>
    </div>
    
   
<script type="text/javascript">
var $sonDialog;
var $rowdata;
var url = "monthlyWorkPlanAttachController/findAllMonthlyWorkPlanAttachList.do";
$(function(){
	refreshMonthlyWorkPlanAttachGrid();
})
function refreshMonthlyWorkPlanAttachGrid(){
	if($row!=null){
		$("#monthlyWorkPlanForm").form("load",$row);
		url = "monthlyWorkPlanAttachController/findAllMonthlyWorkPlanAttachList.do?mwpId="+$row.mwpId;
		createmonthlyWorkPlanAttachGrid(url);
	}else{
		createmonthlyWorkPlanAttachGrid(url);
	}
};
function createmonthlyWorkPlanAttachGrid(url){
	$("#monthlyWorkPlanAttachGrid").datagrid({
		url:url,
		width:'auto',
		height : $(this).height()-530,
		pagination:false,
		rownumbers:true,
		border:true,
		singleSelect:false,
		nowrap:true,//如果为true，则在同一行中显示数据。设置为true可以提高加载性能。
		remoteSort:false,//定义是否从服务器对数据进行排序。
		striped:true,//是否显示斑马线
		columns:[[{title : '本月总结',width : 480,align : 'center',colspan:5},
		          {title : '下月计划',width : 480,align : 'center',colspan:5}],
		        [
                {field : 'contSumup',title : '本月工作<br/>内容总结',width : 80,align : 'center'},
	            {field : 'completed1',title : '第一周(%)',width : 80,align : 'center'},
	            {field : 'completed2',title : '第二周(%)',width : 80,align : 'center'},
	            {field : 'completed3',title : '第三周(%)',width : 80,align : 'center'},
	            {field : 'completed4',title : '第四周(%)',width : 80,align : 'center'},
	            
                {field : 'contPlan',title : '下月工作<br/>内容计划',width : 80,align : 'center'},
                {field : 'planComp1',title : '第一周(%)',width : 80,align : 'center'},
                {field : 'planComp2',title : '第二周(%)',width : 80,align : 'center'},
                {field : 'planComp3',title : '第三周(%)',width : 80,align : 'center'},
                {field : 'planComp4',title : '第四周(%)',width : 80,align : 'center'},
                {field : 'fujian',title : '操作',width :130,align : 'center',formatter:function(value,row,index){
                	var mwpId = row.mwpId;
                	return "<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" onclick=\"fileUploadsDlg(\'"+mwpId+"\','');\">上传附件</a>&nbsp;&nbsp;<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" onclick=\"checkAttachementDetail(\'"+mwpId+"\','','','')\">查看附件</a>";
                }}
	   ]],
	   toolbar:[{
		   iconCls: 'icon-add',
		   text:'新增',
		   handler:toFormWindow
	   },"-",{
		   iconCls: 'icon-edit',
		   text:'编辑',
		   handler:editMonthlyWorkPlanAttach
	   },"-",{
		   iconCls: 'icon-cut',
		   text:'删除',
		   handler:delMonthlyWorkPlanAttach
	   }],
	   onLoadSuccess:function(data){
		   $(this).datagrid("doCellTip",{'max-width':'100px'});
	   }
	});
}
//新增子数据
function toFormWindow(){
	 $sonDialog = $("<div></div>").dialog({
			title: '新增',    
		    width: 580,    
		    height: 470,    
		    closed: false,    
		    cache: false,    
		    href: 'jsp/ad/monthlyWorkPlan/monthlyWorkPlanAttachForm.jsp',    
		    modal: true,
		    onClose:function(){
		    	$(this).dialog('destroy');
		    },
		    buttons:[{
		    	text:'保存并继续',
		    	iconCls:'icon-save',
				handler:function(){
					saveMonthlyWorkPlanAttach();
				}
		    },{
		    	text:'保存并关闭',
		    	iconCls:'icon-save',
				handler:function(){
					saveMonthlyWorkPlanAttach();
					$sonDialog.dialog('destroy');
				}
		    },{
		    	text:'取消',
		    	iconCls:'icon-no',
				handler:function(){
					$sonDialog.dialog('destroy');
				}
		    }]
		});
}
/**
 * 编辑子数据
 */
function editMonthlyWorkPlanAttach(){
	$rowdata = $("#monthlyWorkPlanAttachGrid").datagrid("getSelected");
	var rows = $("#monthlyWorkPlanAttachGrid").datagrid("getSelections");
	if($rowdata == null){
		$.messager.alert("提示","请选择一条记录进行修改!","warning");
		return false;
	}
	if(rows!=null && rows.length>1){
		$.messager.alert("提示","您只能选择一条记录进行修改!","warning");
		return false;
	}
	$sonDialog = $("<div></div>").dialog({
		title: '编辑',    
	    width: 580,    
	    height: 470,    
	    closed: false,    
	    cache: false,    
	    href: 'jsp/ad/monthlyWorkPlan/monthlyWorkPlanAttachForm.jsp',    
	    modal: true,
	    onClose:function(){
	    	$(this).dialog('destroy');
	    	$rowdata = null;
	    },
	    buttons:[{
	    	text:'保存',
	    	iconCls:'icon-save',
			handler:function(){
				saveMonthlyWorkPlanAttach();
				$sonDialog.dialog('destroy');
			}
	    },{
	    	text:'取消',
	    	iconCls:'icon-no',
			handler:function(){
				$sonDialog.dialog('destroy');
				$rowdata = null;
			}
	    }]
	});
}
//删除子数据
function delMonthlyWorkPlanAttach(){
	var rows = $("#monthlyWorkPlanAttachGrid").datagrid("getSelections");
	var ids = new Array();
	if(rows==null || rows.length<=0){
		$.messager.alert("提示","请至少选择一条记录!","warning");
		return false;
	}
	for(var i=0;i<rows.length;i++){
		ids.push(rows[i].mwpaId);
	}
	ids = ids.join(",");
	//主数据id
	var mwpId = $("#mwpId").val();
	$.ajax({
	   type: "POST",
	   url: "monthlyWorkPlanAttachController/delMonthlyWorkPlanAttach.do",
	   data:{"ids":ids},
       async: false,
	   success: function(msg){
		   if(msg.status){
			   if($row != null){//说明是编辑
				   $("#monthlyWorkPlanAttachGrid").datagrid("reload");
			   }else{//新增
				   $("#monthlyWorkPlanAttachGrid").datagrid("reload",{"mwpId":mwpId});
			   }
			   
		   }
		   $.messager.alert(msg.title,msg.message,'info');
	   }
	});
}
/**
 * 保存主表信息
 */
function saveMonthlyWorkPlan(){
	var isValid = $("#monthlyWorkPlanForm").form('validate');
	if(!isValid){
		return;
	}
	 $.ajax({
		   url: "monthlyWorkPlanController/saveMonthlyWorkPlan.do",
		   type: "POST",
		   data:$('#monthlyWorkPlanForm').serialize(),
           dataType:'JSON',
		   success: function(msg){
			   if(msg.status){
				   $("#monthlyWorkPlanForm").form('load',msg.data);
				   newDisableForm("monthlyWorkPlanForm");
			   }
			   //弹出提示信息
			   $.messager.alert(msg.title,msg.message,'info');
		   }
		});
}
//禁用form表单
function newDisableForm(formId){
	$("#"+formId+" a[iconCls = 'icon-edit']").show();
	$("#"+formId+" a[iconCls = 'icon-ok']").hide();
	$("#"+formId+" [class^='easyui-']").each(function(i){
		if($(this).hasClass("easyui-datebox")){
			$(this).datebox("disable");
		}else if($(this).hasClass("easyui-combobox")){
			$(this).combobox('disable');
		}else if($(this).hasClass("easyui-numberbox")){
			$(this).numberbox('disable');
		}else if($(this).hasClass("easyui-combogrid")){
			$(this).combogrid("disable");
		}else if($(this).hasClass("easyui-textbox")){
			$(this).attr("disabled",true);
		}
	});
}
//解禁form
function newEditForm(obj){
	var formId = $(obj).parents("form").attr("id");
	$("#"+formId+" a[iconCls^='icon-edit']").css('display','none');
	$("#"+formId+" a[iconCls^='icon-ok']").css('display','inline-block');
	$("#"+formId+" [class^='easyui-']").each(function(i){
		if($(this).hasClass("easyui-textbox")){
			$(this).attr("disabled",false);
		}else if($(this).hasClass("easyui-datebox")){
			$(this).datebox("enable");
		}else if($(this).hasClass("easyui-combobox")){
			$(this).combobox("enable");
		}else if($(this).hasClass("easyui-numberbox")){
			$(this).numberbox("enable");
		}else if($(this).hasClass("easyui-combogrid")){
			$(this).combogrid("enable");
		}else{
		}
	})
}
/**
 * 保存子数据
 */
function saveMonthlyWorkPlanAttach(){
	var isValid = $("#monthlyWorkPlanAttachForm").form('validate');
	if(!isValid){
		return false;
	}
	//主数据id
	var mwpId = $("#mwpId").val();
	if(mwpId == ""){
		$.messager.alert("提示","请先填写基本信息并保存!","warning");
		return false;
	}
	$("#monthlyWorkPlanAttachForm input[name^='mwpId']").val(mwpId);
	$.ajax({
		   type: "POST",
		   url: "monthlyWorkPlanAttachController/saveMonthlyWorkPlanAttach.do",
		   data:$("#monthlyWorkPlanAttachForm").serialize(),
           async: false,
           dataType:'JSON',
		   success: function(msg){
			   if(msg.status){
				 if($row != null){//说明是编辑
					 $("#monthlyWorkPlanAttachGrid").datagrid("reload");
				 }else{
					 $("#monthlyWorkPlanAttachGrid").datagrid("reload",{"mwpId":mwpId});
				 }
				 $rowdata = null;
			   }
			   $.messager.alert(msg.title,msg.message,'info');
		   }
	});
}
</script>