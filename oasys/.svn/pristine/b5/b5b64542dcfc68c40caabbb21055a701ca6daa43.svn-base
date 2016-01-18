$(function() {
	$(window).resize(function(){  
        $("#process_deploy").datagrid({  
        	height: $(window).height()-140
        });                
    });  
	var processdeploy_datagrid = $('#process_deploy').datagrid({
		// 获取数据
		url : 'workflowAction/getAllProcessDeployment.do',
		width : 'auto',
		height : $(this).height()-140,
		pageSize:10,
		pagination:true,
		rownumbers:true,
		border:false,
		singleSelect:true,
		striped:true,
		fitColumns:true,
		toolbar:'#procrep_toolbar',
		columns : [ [ 
		           {	field : 'id',title : '流程部署编号',width :300,align : 'center'}
	               , {	field : 'name',	title : '流程部署名称',	width : 500, align : 'center'}
	               , {	field : 'deploymentTime',	title : '部署时间',	width : 300, align : 'center' } 
	               ] ]
	});
	
	//操作方法
	procrep_toolbar={
			
			//查询
			search:function(){
				processdeploy_datagrid.datagrid('load',{
					deploymentId:$.trim($("#deploymentId").val()),
					deploymentName:$.trim($('#deploymentName').val()),
				});
			},
			//重置
			resetting:function(){
				$('#deploymentId').val('');
				$('#deploymentName').val('');
				processdeploy_datagrid.datagrid('load',{});
			},
			//删除
			remove:function(){
				var row=processdeploy_datagrid.datagrid('getSelected');
				if(!$.isEmptyObject(row)){
					$.messager.confirm('确定','是否确定删除所选的 <strong>'+row.name+'</strong> 数据吗？',function(flag){
						if(flag){
							//ajax提交
							$.ajax({
								type:'POSt',
								url:'workflowAction/delProcessDeployment.do',
								data:{'deploymentId':row.id},
								beforSend:function(){
									processdeploy_datagrid.datagrid('loading');
								},
								success:function(data){
									if(data.status){
										processdeploy_datagrid.datagrid('loaded');
										processdeploy_datagrid.datagrid('reload');
										processdeploy_datagrid.datagrid('unselectAll');
										$.messager.show({
											title:'提示',
											msg:data.message
										})
									}
								}
							});
						}
					});
				}else{
					$.messager.alert('警告','流程删除操作至少需要选定一条数据！','warning');
				}
			},
			upload:function(){
				if($("#file").val()=="" || $('#fileName').val() ==""){
					$.messager.alert("提示","请选填写完整信息!!",'error');
					return false;
				}
				$.ajaxFileUpload({
					url:'workflowAction/deployProcess.do',
					data:{"fileName":$('#fileName').val()},
					fileElementId:['file'],
					secureuri:false,
					dataType:'text',
					success:function(data,status){
						data = $.parseJSON(data);
						if(data.status){
							$.messager.show({
								title:data.title,
								msg:data.message,
								timeout:3000,
								showType:'slide'
							});	
						}else{
							$.messager.alert(data.title,data.message,'error');
						}
						processdeploy_datagrid.datagrid("reload");
						$('#file').val('');
						$('#fileName').val('');
					}
				});
			},
			upload2:function(){
				if($("#file").val()=="" || $('#fileName').val() ==""){
					$.messager.alert("提示","请选填写完整信息!!",'error');
					return false;
				}
				$("#formId").submit(function(){  
		            $(this).ajaxSubmit({  
		                type:"post",  //提交方式  
		                dataType:"json", //数据类型  
		                success:function(data){ //提交成功的回调函数  
		                    alert(data.result);  
		                }  
		            });  
		            return false; //不刷新页面  
		        }); 
			},
	};
	
		/*调用jquery.form.js的ajaxSubmit方法完成异步表单提交和文件上传*/
		$('#sub').click(function() {
			if($("#file").val()=="" || $('#fileName').val() ==""){
				$.messager.alert("提示","请选填写完整信息!!",'error');
				return false;
			}
			
			$('#formId').ajaxSubmit(function(data) {
				var data = $.parseJSON(data);
				if(data.status){
					$.messager.alert(data.title,data.message,'info');
				}else{
					$.messager.alert(data.title,data.message,'error');
				}
				processdeploy_datagrid.datagrid("reload");
				$('#file').val('');
				$('#fileName').val('');
			});
	});

	
});