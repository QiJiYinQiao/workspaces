<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>权限编辑</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<jsp:include page="../../layout/script.jsp"></jsp:include>
	<script type="text/javascript">
		var $role;
		var $organization;
		var flag = false;//是否选中分部的标志
		var deptId;//部门id
		var dj=[];
		$(function() {
			$("#panel").panel({   
				   width:'auto',   
				   height:$(this).height(),   
				   title: '角色分配',   
			});
			$organization = $("#user");
			 $organization=$organization.treegrid({
					width : 'auto',
					height : $(this).height()-90,
					url : "orgz/organizationAction!findOrganizationListTreeGrid.action",
					rownumbers:true,
					animate: true,
					collapsible: true,
					fitColumns: true,
					striped:true,
					border:true,
					//singleSelect:false,
					idField: 'organizationId',
					treeField: 'fullName',
					 frozenColumns:[[
					                 {title:'组织名称',field:'fullName',width:parseInt($(this).width()*0.2),
					                  formatter:function(value){
					                   return '<span style="color:purple">'+value+'</span>';
					                  }
					                 }
					    ]],
					columns : [ [ 
					              {field : 'myid',title : '编码',width : parseInt($(this).width()*0.1)},
					              {field : 'regionType',title : '区域类型',width : parseInt($(this).width()*0.1),align : 'left',
					            	  formatter:function(value,row){
					            		  if(value=='0'){
					            			  return "地区";
					            		  }else if(value=='1'){
					            			  return "直辖市";
					            		  }else if(value=='2'){
					            			  return "省份";
					            		  }else if(value=='3'){
					            			  return "城市";
					            		  }else if(value=='4'){
					            			  return "其他";
					            		  }
					            	  }},
					              {field : 'description',title : '组织描述',width : parseInt($(this).width()*0.2),align : 'left'}
					              ] ],toolbar:'#tb',
					              onDblClickRow:function(row){
					            	  //判断是否选中了分部
					            		deptId=row.organizationId;
					            	  if(row.deptLevel=="1"){
					            		  flag=true;
					            	  }else{
					            		  flag=false;
					            	  }
					            	  getRoles(row);
					              }
				});
			$role = $("#role");
			$role.datagrid({
					url : "permission/permissionAssignmentAction!findAllRoleListNotPage.action",
					width : 'auto',
					height : $(this).height()-120,
					pagination:false,
					border:false,
					rownumbers:true,
					singleSelect:false,
					striped:true,
					idField: 'roleId',
					columns : [ [ {field:'ck',checkbox:true},
					              {field : 'name',title : '角色名称',width :  parseInt($(this).width()*0.1),align : 'center',editor : {type:'validatebox',options:{required:true}}},
					              //{field : 'sort',title : '排序',width : parseInt($(this).width()*0.1),align : 'center',editor : "numberbox"},
					              {field : 'description',title : '角色描述',width :  parseInt($(this).width()*0.1),align : 'center',editor : "text"}
					              ] ],
					toolbar:"#tbRole",
					onDblClickRow:function(index,row){
						if(flag==true&&row.roleType=="2"){//分部职能端可以查看对接部门
							///查看对接部门
							$.ajax({
								url:'orgz/organizationAction!getJointOrganId.action',
								type:'post',
								data:'deptId='+deptId+'&roleId='+row.roleId,
								async:false,
								success:function(data){
									$("#jointOrganId").combotree("setValue",data)
								}
							})
								//选对接部门
								$("#orgSelect").dialog({
									title: '选择对接部门',    
								    width: 400,    
								    height: 200,    
								    closed: false,    
								    cache: false,    
								    modal: true,
								    buttons:[ {
										text : '确定',
										iconCls : 'icon-ok',
										handler : function(){
											 var isValid = $("#orgSelectForm").form('validate');
												if (!isValid) {
													return false;
												}
											var roleJoinOrgId=deptId+"-"+row.roleId+"-"+$("#jointOrganId").combotree("getValue");
											//若之前已选则删除原来的数据重新选择
											for(var i in dj){
												if(dj[i].split("-")[1]==row.roleId){
													dj.splice(i, 1)
												}
											}
											 dj.push(roleJoinOrgId);
											$("#jointOrganId").combotree("setValue","")
											$("#orgSelect").dialog('close')
										}
									}, {
										text : '关闭',
										iconCls : 'icon-cancel',
										handler : function() {
											//清空当前所选并关闭弹窗
											$("#jointOrganId").combotree("setValue","")
											$("#orgSelect").dialog('close')
										}
									}
									]
								})
						}else{
							$.messager.alert("提示信息","该部门角色无对接部门！");
						}
					}
			});//datagrid end
			//搜索框
			$("#searchbox").searchbox({ 
				 menu:"#mm", 
				 prompt :'模糊查询',
			    searcher:function(value,name){   
			    	var str="{\"searchName\":\""+name+"\",\"searchValue\":\""+value+"\"}";
		            var obj = eval('('+str+')');
		            $organization.datagrid('reload',obj); 
			    }
			   
			});
			//搜索框
			$("#searchbox2").searchbox({ 
				 menu:"#mm2", 
				 prompt :'模糊查询',
			    searcher:function(value,name){   
			    	var str="{\"searchName\":\""+name+"\",\"searchValue\":\""+value+"\"}";
		            var obj = eval('('+str+')');
		            $role.datagrid('reload',obj); 
			    }
			   
			});
			
			$("#jointOrganId").combotree({
				width:171,
				url:"orgz/organizationAction!findOrganizationList.action",
				idFiled:'id',
			 	textFiled:'name',
			 	parentField:'pid',
			 	required:true,
			 	onSelect:function(node){
			 		//$("#organizeName").val(node.text);
			 	}
			});
		});//$(function{}) end
		//数组去重
		function unique(arr) {
		    var result = [], hash = {};
		    for (var i = 0, elem; (elem = arr[i]) != null; i++) {
		        if (!hash[elem]) {
		            result.push(elem);
		            hash[elem] = true;
		        }
		    }
		    return result;
		}
		//保存部门、角色、对接部门之间的关系
		 function saveOrganizationRoles(){
			 var selectRow=$organization.datagrid("getSelected");
			 var selectRows=$role.datagrid("getSelections");
			 var isCheckedIds=[];
			 $.each(selectRows,function(i,e){
				 isCheckedIds.push(selectRow.organizationId+"-"+e.roleId+"-0");
			 }); 
			 dj=unique(dj);
			 //替换有对接部门的
			 for(var i in dj){
				 for(var j in isCheckedIds){
					 if(isCheckedIds[j].split("-")[1]==dj[i].split("-")[1]){
						 isCheckedIds.splice(j,1,dj[i]);//删除并替换
					 }
				 }
			 }
			 //console.info("保存后："+isCheckedIds)
			 if(selectRow){
				 $.ajax({
						url:"orgz/organizationAction!saveOrganizationRoles.action",
						data: "organizationId="+selectRow.organizationId+"&isCheckedIds="+isCheckedIds,
						success: function(rsp){
								parent.$.messager.show({
									title :rsp.title,
									msg :rsp.message,
									timeout : 1000 * 2
								});
								dj=[];
						},
						error:function(){
							parent.$.messager.show({
								title :"提示",
								msg :"保存部门角色失败！",
								timeout : 1000 * 2
							});
						}

					});
			 }else{
				 parent.$.messager.show({
						title :"提示",
						msg :"请选择部门！",
						timeout : 1000 * 2
					});
			 }
			 
		 }
		 function getRoles(rowData){
			 $.post("orgz/organizationAction!findOrganizationRolesList.action", {organizationId:rowData.organizationId}, function(rsp) {
					 $role.datagrid("unselectAll");
				 if(rsp.length!=0){
					 $.each(rsp,function(i,e){
						 $role.datagrid("selectRecord",e.roleId);
						 dj.push(e.organizationId+"-"+e.roleId+"-"+e.jointOrganId);
					 });
					 dj=unique(dj);
					 //console.info("保存前："+dj)
				 }else{
					 parent.$.messager.show({
							title :"提示",
							msg : "该部门暂无角色！",
							timeout : 1000 * 2
						});
				 }
				}, "JSON").error(function() {
					 parent.$.messager.show({
							title :"提示",
							msg : "获取部门角色失败！",
							timeout : 1000 * 2
						});
				});
		}
	</script>
  </head>
  <body>
  
   <div id="panel"  data-options="border:false">
		<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'north',border:false" title="" style="height: 82px; overflow: hidden; padding: 5px;">
			<div class="well well-small">
				<span class="badge">提示</span>
				<p>
					为部门分配角色，请<span class="label-info"><strong>双击部门</strong></span>查看所属角色！
					<span class="label-info"><strong>双击角色</strong></span>选择部门角色对接部门！
				</p>
			</div>
		</div>
			<div data-options="region:'west',split:true,border:true" style="width:800px;">
				<div id="tbUser" style="padding:2px 0">
					<table cellpadding="0" cellspacing="0">
						<tr>
							<td style="padding-left:2px">
									<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-config" plain="true" onclick="saveOrganizationRoles();">保存设置</a>
							</td>
							<td style="padding-left:2px">
								<input id="searchbox" type="text"/>
							</td>
						</tr>
					</table>
				</div>
				<div id="mm">
						<div name="fullName">组织名称</div>
						<div name="description">组织描述</div>
				</div>
				<table id="user" title="部门"></table>
			</div>
			<div data-options="region:'center',border:true">
				<div id="tbRole" style="padding:2px 0">
					<table cellpadding="0" cellspacing="0">
						<tr>
							<td style="padding-left:4px;padding-bottom:4px;">
								<input id="searchbox2" type="text"/>
							</td>
						</tr>
					</table>
				</div>
				<div id="mm2">
						<div name="name">角色名称</div>
						<div name="description">角色描述</div>
				</div>
				<table id="role" title="角色"></table>
			</div>
		</div>
	</div>
	
	<div id="orgSelect" class="easyui-dialog" closed="true">
	<form id="orgSelectForm">
	<table>
	<tr>
	<th>对接部门</th>
	<td>
		<!-- 	对接部门 -->
		<input id="jointOrganId" name="jointOrganId" type="text" class="easyui-textbox easyui-validatebox"/></td>
	</tr>
	</table>
	</form>
	</div>
  </body>
</html>
