$(function() {
	$(window).resize(function(){  
        $("#process_definition").datagrid({  
        	height: $(window).height()-110
        });                
    });
	var procdef_datagrid = $('#process_definition').datagrid({
		// 获取数据
		url : 'workflowAction/getAllProcessDefinition.do',
		width : 'auto',
		height : $(this).height()-110,
		pagination:true,
		rownumbers:true,
		border:false,
		singleSelect:true,
		fitColumns:true,
		nowrap:true,
		columns : [ [ 
		   {field : 'id',title : 'ID',	width : 100,	align : 'center'}
		  ,{field : 'name',title : '名称',	width : 100,	align : 'center'}
		  ,{field : 'key',title : 'KEY',	width : 100,	align : 'center'}
		  ,{field : 'version',title : '版本',	width : 100,	align : 'center'}
		  ,{field : 'resourceName',title : '规则文件名称(bpmn)',	width : 100,	align : 'center'}
		  ,{field : 'diagramResourceName',title : '规则图片名称(png)',	width : 100,	align : 'center',
			  formatter: function(value,row,index){
				  return 	"<a href='javascript:void(0);' onclick=\"procrep_toolbar.showImage(\'"+value+"\',\'"+row.deploymentId+"\');\">"+value+"</a>";
				}

		  }
		  ,{field : 'deploymentId',title : '部署ID',	width : 100,	align : 'center'}
		  ] ]
	});
	
	//操作方法
	procrep_toolbar={
			//查询
			search:function(){
				procdef_datagrid.datagrid('load',{
					name:$.trim($('#name').val()),
					key:$.trim($('#key').val()),
					/*version:$.trim($('#version').val()),*/
					deploymentId:$.trim($('#deploymentId').val())
				});
			},
			//重置
			resetting:function(){
				$('#name').val("");
				$('#key').val("");
				$('#version').val("");
				$('#deploymentId').val("");
				procdef_datagrid.datagrid('load',{});
			},
			// 查看图片
			showImage:function(resourceName,deploymentId){
				/*var imageRow = procdef_datagrid.datagrid('getSelected');
				var src = "workflow/workflowAction!getDiagramResource.action?resourceName="+imageRow.diagramResourceName+"&deploymentId="+imageRow.deploymentId;*/
				var src = "workflowAction/getDiagramResource.do?resourceName="+resourceName+"&deploymentId="+deploymentId;
				$('#imageDialog').dialog("open"); 
				$("#image").attr("src",src);
			}
	}

});