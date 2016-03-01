<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<title>流程部署</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<jsp:include page="../../layout/script.jsp"></jsp:include>
<script type="text/javascript" src="<%=basePath %>js/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=basePath %>jsp/workflow/proc_dep.js"></script>
<script type="text/javascript">
var $deptTreeGrid;
var $userDataGrid;
$(function(){
	$deptTreeGrid = $('#deptTreeGrid').treegrid({    
	    url:'orgz/organizationAction!findOrganizationByMyId.action',
	    width:"auto",
		height : $(window).height()-120,
		rownumbers:true,
		animate: true,
		collapsible: true,
		fitColumns: true,
		border:false,
		striped:true,
		singleSelect:true,
		cascadeCheck:true,
		deepCascadeCheck:true,
		idField: 'id',
		treeField: 'name',
		parentField : 'pid',
		animate :true,
		columns:[[    
		        {title:'组织机构名称',field:'name',width:$(this).width()*0.06}
	    ]],onDblClickRow:function(row){
        	$userDataGrid.datagrid("clearSelections");
	    	$.ajax({type: "POST",
	                url: "userOrg/userAndOrganization!findBindingUsersByOrganizationId.action",
	                data: {organizationId:row.id},
	                dataType: "json",
	                success: function(data){
	                	var list = data.rows;
	                	if(list !=null && list.length>0){
	                		for(var i=0;i<list.length;i++){
	                			var index = $userDataGrid.datagrid("getRowIndex",list[i].userId);
	                			console.info(index);
	                			$userDataGrid.datagrid("selectRow",index);
	                		}
	                	}
	                }
                });
	    },toolbar:[{
            text:'保存',
            iconCls:'icon-save',
            handler:function(){
            	var node = $deptTreeGrid.treegrid("getSelected");
            	// 判断是否选中了节点，如果选中了节点判断是不是叶子（营业部信息），如果不是叶子给出相应的提示信息
            	if(node == null){
            		$.messager.alert('提示','请选择要外援的营业部！','waring');
            		return;
            	}else{
            		var childrens = $deptTreeGrid.treegrid("getChildren",node.id);
            		if(childrens !=null && childrens.length > 0 ){
                		$.messager.alert('提示','请选择要外援的营业部！','waring');
                		return;
            		}
            	}
            	
            	// 判断是否选中了外援人员 信息
            	var users = $userDataGrid.datagrid("getSelections");
            	var userIds = [];
            	if(users ==null || users.length == 0){
            		$.messager.confirm('提示','您是否取消组织机构和外援的绑定关系？',function(r){    
            		    if (r){ 
                    		saveBindingUserAndOrganization(node, userIds)
            		    }    
            		});  
            	}else{
            		for(var i=0;i<users.length;i++){
            			userIds.push(users[i].userId);
            		}
            		saveBindingUserAndOrganization(node, userIds)
            	}
           	}
	    }]
	});
	
	$userDataGrid = $('#userDataGrid').datagrid({    
	    url:'users/usersAction!findUsesrsByRoleCode.action', 
	    queryParams:{roleCode:"IPCXiaoEDiaoCha"},
	    width:"auto",
		height : $(window).height()-120,
		fitColumns:true,
		idField:"userId",
	    columns:[[    
	        {field:'areaName',title:'地区',width:$(this).width()*0.03,align:'right'},  
	        {field:'companyName',title:'公司信息',width:$(this).width()*0.04,align:'right'},  
	        {field:'deptName',title:'营业部',width:$(this).width()*0.06,align:'right'},   
	        {field:'userName',title:'姓名',width:$(this).width()*0.04},    
	        {field:'email',title:'邮箱',width:$(this).width()*0.06,align:'right'},   
	        {field:'tel',title:'电话',width:$(this).width()*0.06,align:'right'} 
	    ]],toolbar:[{
            text:'全选',
            iconCls:'icon-undo',
            handler:function(){
            	$userDataGrid.datagrid("selectAll");
           	}
        },{
            text:'取消',
            iconCls:'icon-cancel',
            handler:function(){
            	$userDataGrid.datagrid("clearSelections");
           	}
        }]
	    ,onLoadSuccess:function(data){
		 	var rows = data.rows;
            var areaMergeMap = {};
            var companyMergeMap = {};
            var deptMergeMap = {};
            if(rows){
            	for(var i=0;i<rows.length;i++){
            		var areaName = rows[i].areaName;
            		if( areaName in areaMergeMap ){
            			areaMergeMap[areaName].rowspan++;
            		}else{
            			areaMergeMap[areaName]={"index":i,"rowspan":1}
            		}
            		
            		var companyName = rows[i].companyName;
            		if(companyName in companyMergeMap){
            			companyMergeMap[companyName].rowspan++;
            		}else{
            			companyMergeMap[companyName]={"index":i,"rowspan":1}
            		}
            		
            		var deptName = rows[i].deptName;
            		if(deptName in deptMergeMap){
            			deptMergeMap[deptName].rowspan++;
            		}else{
            			deptMergeMap[deptName]={"index":i,"rowspan":1}
            		}
            	}
            }
            
            for(var i in areaMergeMap){
                $(this).datagrid('mergeCells',{
                    index: areaMergeMap[i].index,
                    field: 'areaName',
                    rowspan: areaMergeMap[i].rowspan
                });
            }
            
            for(var i in companyMergeMap){
                $(this).datagrid('mergeCells',{
                    index: companyMergeMap[i].index,
                    field: 'companyName',
                    rowspan: companyMergeMap[i].rowspan
                });
            }
            
            for(var i in deptMergeMap){
                $(this).datagrid('mergeCells',{
                    index: deptMergeMap[i].index,
                    field: 'deptName',
                    rowspan: deptMergeMap[i].rowspan
                });
            }
		}
	});  
});

/**
 * 绑定组织机构和外援人员方法
 */
function saveBindingUserAndOrganization(node,userIds){
	// 保存绑定用户的信息
	$.ajax({type: "POST",
        url: "userOrg/userAndOrganization!saveBindingUserAndOrganization.action",
        data: {"organizationId":node.id,"userIds":userIds.join(",")},
        dataType: "json",
        success: function(data){
        	if(data.status){
            	$.messager.show({
            		title:'提示',
            		msg:'保存成功。',
            		timeout:5000,
            		showType:'slide'
            	});
        	}else{
        		$.messager.alert("提示","保存失败",'error');
        	}
        }
	});
}

</script>
</head>
<body>
    <!-- 提示信息区域 -->
	<div class="well well-small" style="margin-left: 5px; margin-top: 5px;">
		<span class="badge">提示</span>
		<p>
			在此你可以对<span class="label-info"><strong>流程</strong></span>进行更新、部署、删除、查询等操作!
		</p>
		<div id="cc" class="easyui-layout" style="width:1000px;height:750px;">   
			<div data-options="region:'west',split:true,border:true" style="width:300px;">
		    	<table id="deptTreeGrid" ></table>
		    </div>   
		    <div data-options="region:'center',title:'IPC小额调查人员信息列表'">
		    	<div id="userDataGrid" ></div>
		    </div>   
		</div>
	</div>
</body>
</html>