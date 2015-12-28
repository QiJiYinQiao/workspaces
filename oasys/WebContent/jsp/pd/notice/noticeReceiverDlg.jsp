<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<script type="text/javascript">
var orgData;
$(function(){
	$("#panel").panel({   
		   width:'auto',   
		   height:$(this).height(),   
	});
	/**根据id回显组织名称*/
		$.ajax({
			url:'noticeInfoController/showOrganizationName.do',
			type:'post',
			async:false,
			dataType:'json',
			success:function(data){
				orgData=data;
			}
		});
	 $("#receiverGrid").datagrid({
		url : "noticeInfoController/findAllUserList.do",
		width : 800,
		height : 500,
		pagination:false,
		rownumbers:true,
		striped:true,
		singleSelect:false,
		fitColumns:true,
		idField: 'userId',
		columns : [ [ {field:'ck',checkbox:true},
		              /* {field : 'userId',title : '用户id',width : 100,align:'center'}, */ 
		              {field : 'organizeId',title : '部门',width : 200,align:'center',
						 formatter:function(value,row){
			         	  	return showOrgName(orgData,value);  
							}  
		              },
		              {field : 'name',title : '用户名',width : 200,align:'center'}
		              ] ],toolbar:'#tb',
		              onCheck:function(index,data){
		            	  if ( $("#r"+data.userId).length <= 0 ){
			            	 var rHtml="<span id ='r"+data.userId+"'>"+data.name+"<br/></span>";
			            	  $("#selectedReceivers").append(rHtml);
		            	  }
		              },onUncheck:function(index,data){
		            	  $("span").remove("#r"+data.userId);
		              },onCheckAll:function(rows){
		            	  $("#selectedReceivers").html("已选收件人:<br/>已勾选所有用户！");
		              },onUncheckAll:function(rows){
		            	  $("#selectedReceivers").html("已选收件人:<br/>已取消选择所有用户！");
		              },onLoadSuccess:function(){
		            	 var selects= userIds.split(",")
		            	 for(var i in selects){
							$("#receiverGrid").datagrid('selectRecord',selects[i]);
		            	 }
		              }
	});
	 
	 createSelect();
})

	function showOrgName(arr,orgId){
				var orgName = "";
				for (var i in arr) {
					if(arr[i].organizationId == orgId){
						orgName += arr[i].fullName;
					}
				}
				return orgName;
			}

	function createSelect(){
		//初始化组织机构
		$("#deptNo").combotree({
			width:171,
			url:"Organization/organizationList.do",
			idFiled:'id',
		 	textFiled:'name',
		 	valueFiled:'id',
		 	parentField:'pid',
		 	onLoadSuccess:function(data){
		 	}/* ,
		 	onChange:function(){
		 		$("#applicantNo").combobox({
		 			width:171,
		 			multiple:true,
		 			separator:",",
		 			editable:false, 
		 			url:'callingCard/getUserInfo.do?q='+$(this).combotree('getValue'),
		 			valueField:'code',
		 		 	textFiled:'text',
		 		 	onLoadSuccess:function(data){
		 		 		//加一个全部
		 		 	}
		 		});
		 	} */
		});
	}
	
	function toSearch(){
		$("#receiverGrid").datagrid('load',{
			organizeId:$("#deptNo").combotree('getValue'),
			name:$("#userName").val()
		});
	}
</script>
   <div id="panel" data-options="border:false">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'west',split:true,border:true" style="width:800px;">
				<div id="searchDiv" style="padding:2px 0">
				<form id="searchForm">
					<table cellpadding="0" cellspacing="0">
						<tr>
							<th>部门:</th>
							<td>
								<input name="deptNo" id="deptNo" class="easyui-textbox" readonly="true" data-options="validType:'length[0,100]'"/>
							</td>
							<th>姓名:</th>
							<td id="name">
								<input name="userName" id="userName" class="easyui-textbox" data-options="validType:'length[0,100]'"/>
							</td>
							<td>
					         	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="toSearch();">搜索</a>
					        	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" onclick="$('#searchForm').form('clear')">重置</a>
					    </td>
						</tr>
					</table>
				</form>
				</div>
				<table id="receiverGrid" title="用户列表"></table>
			</div>
			<div id="selectedReceivers" data-options="region:'center',border:true" style="overflow:scroll;">
				 已选收件人:<br/>
			</div>
		</div>
	</div>
