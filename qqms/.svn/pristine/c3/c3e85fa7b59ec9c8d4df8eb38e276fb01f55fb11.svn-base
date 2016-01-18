<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<script>
$(function(){
	// createTreegrid($userId,$roleId); 
	$jointOrganCity=$('#tt').tree({    
	    url:'orgz/organizationAction!findOrganizationListTree.action',
	    method:'post',
	    checkbox:true,
	    cascadeCheck:true,
	    onLoadSuccess:function(){
	    	$.ajax({
				url:'user/userAction!getJointOrganCity.action',
				type:'post',
				data:'userId='+$userId+'&roleId='+$roleId,
				success:function(data){
					if(data){
						data=data.substring(1,data.length-1);
						var aa=data.split("@");
						for(var i in aa){
							var node = $('#tt').tree('find', aa[i]);
							$('#tt').tree('check', node.target);
						}
					}
				}
			})
	    }
	});  
})
</script>
	<!-- 选择对接地区 -->
	<div id="orgSelect">
		<!-- <table id="jointOrganCity"></table> -->
		<div id="tt"></div>
	</div>