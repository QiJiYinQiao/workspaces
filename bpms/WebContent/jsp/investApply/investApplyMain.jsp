<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<jsp:include page="../../layout/script.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>投资申请</title>
</head>
<script type="text/javascript">
	var provinceArr = jqueryUtil.getAreaTextArr(1);//获取省
	var relationshipArr =jqueryUtil.getTextArr("relationship_type");//与本人关系
	var sexArr = jqueryUtil.getTextArr("gender_type");//性别
	var degreeTypeArr = jqueryUtil.getTextArr("degree_type");//学历
	var jobTypeArr = jqueryUtil.getTextArr("job_type")//工作类型
	$(function(){
		createInvestApplyDataGrid();
	});
	
	//工具
	function getRowData(index) {
		if (!$.isNumeric(index) || index < 0) {
			return undefined;
		}
		var rows = $("#investApplyListDataGrid").datagrid("getRows");
		return rows[index];
	}	

	// 自动调整页面高度
 	$(window).resize(function(){  
            $("#investApplyListDataGrid").datagrid({  
            	height: $(window).height()-100,
            	width : 'auto'
            });                
        });
 
	//渲染投资申请列表
	function createInvestApplyDataGrid(){
		$("#investApplyListDataGrid").datagrid({
			/* url:'investOrder/investOrderAction!findListByInvestorAndInvestOrder.action', */
			url:'investApply/investApplyAction!findInvestApplyList.action',
			title:'投资申请',
			width: 'auto',
			height : $(window).height()-75,
			pagination:true,
			rownumbers:true,
			border:false,
			singleSelect:true,
			nowrap:true,//如果为true，则在同一行中显示数据。设置为true可以提高加载性能。
			pageSize:30,
			pageList:[10,20,30,40],
			remoteSort:false,//定义是否从服务器对数据进行排序。
			striped:true,//是否显示斑马线
			columns:[[
			        {field : 'chName',title : '客户姓名', width : parseInt($(this).width() * 0.06) , align : 'center',
		            	  formatter: function(value, row, index){
		            		  return "<a href=\"javascript:void(0)\" onclick=\"showInvestorView("+index+")\">"+value+"</a>";
		            	  }			        				        
			        },
	                {field : 'createDate',title : '投资申请生成日期', width : parseInt($(this).width() * 0.10)  ,align : 'center'},	                
	                {field : 'contractNo',title : '合同编号', width : parseInt($(this).width() * 0.06) ,align : 'center',
						  formatter: function(value, row, index){
							  if(row.contractNo == null || row.contractNo == ""){					            			  
					    		return "";					            			  					
							  }else{					            			  
							  	return "<a href=\"javascript:void(0)\" onclick=\"showInvestContractDetailsView("+index+")\">"+value+"</a>";					            			 
							  }
						  }	                		                
	                },
	                {field : 'bankName',title : '开户行名称', width : parseInt($(this).width() * 0.07) ,align : 'center'},
	                {field : 'actNo',title : '开户账号', width : parseInt($(this).width() * 0.06) ,align : 'center'},
	                {field : 'actName',title : '账户名称', width : parseInt($(this).width() * 0.06) ,align : 'center'},
	                {field : 'prodName',title : '理财产品', width : parseInt($(this).width() * 0.06) ,align : 'center',
		            	  formatter: function(value, row, index){
		            		  var result ="";
		            		  if(row.prodName == "" || row.prodName == "undefined" || row.prodName == null){
		            			  return result;  
		            		  }else{		            			  
		            		  	  return result ="<a href='javascript:void(0);' onclick='showInvestorAndInvestProductsDetailsView(\""+row.investOrderId+"\");'>"+row.prodName+"</a>";
		            		  }		      				  					            		  
		            	  }	                	
	                },
	                {field : 'investEdu',title : '投资金额', width : parseInt($(this).width() * 0.06) , align : 'center'},
	                {field : 'beginDate',title : '投资开始日期', width : parseInt($(this).width() * 0.06) , align : 'center'},
	                {field : 'interestDate',title : '计息日期', width : parseInt($(this).width() * 0.06) , align : 'center'},
	                {field : 'aa',title : '操作', width : parseInt($(this).width() * 0.14) , align : 'center',
	                	formatter:function(value,row,index){
		                	if(row.processStatus == "0"){
/* 		                		//在业务员提交投资申请之前，先判断客户的投资合同和理财产品等理财信息是否已经完善，
		                		//若理财信息不完善，则先完善信息，才能进行投资申请的提交。
		                		//(1)若理财信息不完善（比如没有填写合同信息或者没有选择理财产品），则先完善信息，之后才能进行投资申请的提交。
               	    			if((row.contractNo == null || row.contractNo == "") || (row.prodId ==null || row.prodId == "")){
             	    				return "<a href='javascript:void(0);' onclick='toDelete("+ index + ");'>删除</a>&nbsp;&nbsp;"		             	    				
             	    	       			 + "<a href='javascript:void(0);' onclick='completeInvestOrderInfo("+ index + ");'>完善理财信息</a>&nbsp;&nbsp;"
             	    	       			 + "<a href='javascript:void(0);' onclick='sumitInvestOrder("+ index + ");'>提交投资申请</a>";
             	    			}
             	    			//(2)若理财信息已经被完善，可以进行投资申请的提交。
             	    			else{	
             	    				return "<a href='javascript:void(0);' onclick='toDelete("+ index + ");'>删除</a>&nbsp;&nbsp;"		             	    				
             	    	       		     + "<a href='javascript:void(0);' onclick='sumitInvestOrder("+ index + ");'>提交投资申请</a>";
             	    			} */
		                				                		
          	    				return "<a href='javascript:void(0);' onclick='toDelete("+ index + ");'>删除</a>&nbsp;&nbsp;"		             	    				
    	    	       			     + "<a href='javascript:void(0);' onclick='completeInvestOrderInfo("+ index + ");'>完善理财信息</a>&nbsp;&nbsp;"
    	    	       			     + "<a href='javascript:void(0);' onclick='sumitInvestOrder("+ index + ");'>提交投资申请</a>";     	    	       			     		                		
		                		
		             	    }else if(row.processStatus == "1"){
		             	    	return "<a href='javascript:void(0);' onclick='checkProcessImg("+ index + ");'>查看流程图</a>";
		             	    }else{
		             	    	return "<a href='javascript:void(0);' onclick='checkInvestOrderOpinions("+ index + ");'>查看审批意见</a>";
		             	    }
	                }}
	                
		   ]],
		   toolbar:[{
			   iconCls: 'icon-add',
			   text:'新增投资申请',
			   handler:toAddInvestApply
		   }]
		});
		
	}
	/**
	 * 新增投资申请
	 */
	function toAddInvestApply(){
		createInvestordg();
		createContacts();
		$("#addDialog").dialog("open");
	}
	
	
	//查看该投资人的理财产品详情界面
	function showInvestorAndInvestProductsDetailsView(investOrderId){			
		$('#investorAndInvestProductsDialog').dialog({    
		    title: '理财产品详情',    
		    width: 800,    	
		    height: 350,    
		    closed: false,
		    closable: true,
		    cache: false,    
		    href: 'investOrder/investOrderAction!findInvestorAndInvestProductsDetails.action?investOrderId='+investOrderId,    
		    modal: true   
		});  													 					
	}		
	
	/**==完善客户及合同信息==*/
	function completeInvestOrderInfo(index){
		var row = getRowData(index);	
		var investOrderId = row.investOrderId;
		var row1; //用来接收下边通过Ajax请求查询订单详情时返回的数据。
		
		//查询地址
		var addr=new Array();
		$.ajax({
			url:'investor/investorAction!findAddressById.action',
			data:'addressId='+row["commAddr"],
			dataType:'json',
			async : false,
			success:function(data){
				addr=data;
			}
		}); 
			
		//打开dialog
		$("#dialog4CompleteInvestContractAndProductInfo").dialog({
			title : '完善理财客户合同信息',
			width : 1000,
			height : 600,
			modal:true,
			//href : "jsp/investOrder/investOrderEditForm.jsp?investOrderId="+investOrderId,
			href:'investorderAndProducts/investorderAndProductsAction!gotoCompleteOrderInfo.action?orderId='+investOrderId,
			onLoad:function(){
				/* 渲染基本客户信息 Tab页面*/
				var f = $("#basicInvestorClientInfoForm");
				if(addr!=null){
					row["provinceId"]=addr["provinceId"];
					row["cityId"]=addr["cityId"];
					row["areaId"]=addr["areaId"]; 
					row["addressDetails"]=addr["addrDetails"];
				}
				f.form("load", row);
				renderProvinceSelect('provinceId','cityId','areaId');
				$("#provinceId").combobox("setValue",row.provinceId);
		        $("#cityId").combobox("setValue",row.cityId);
		        $("#areaId").combobox("setValue",row.areaId);
		        
		        /* 渲染紧急联系人 Tab页面*/
		        //初始化紧急联系人列表
		        initLinkPeopleGrid(row.investorId);	  
		        
		        /* 渲染合同信息 Tab页面*/
		        //查询InvestOrder订单详情
		        $.ajax({
					url:'investOrder/investOrderAction!findByInvestOrderId.action',
					data:'investOrderId='+investOrderId,
					dataType:'json',
					async : false,
					success:function(data){
						row1 = data;
					}
				})
				$("#constractInfoForm").form("load",row1);
		        //保存流程状态OrderStatus对象。
		        if(row1.orderStatus != null){		        	
		        	$("#orderStatus").val(row1.orderStatus.statusCode);		        
		        }else{
		        	console.info("未提交流程。流程状态OrderStatus对象为NULL。");
			        console.info(row1);		        
		        }
			},
			onClose:function(){
				$("#investApplyListDataGrid").datagrid('reload');	
			}		
		}); 
	}
	
	
	//查看投资人详细信息
	function showInvestorView(index){
		var rows = $("#investApplyListDataGrid").datagrid("getRows");
		var row = rows[index];
		$('#investorView').dialog({    
		    title: '投资客户详情',    
		    width: 800,    
		    height: 550,    
		    closed: false,    
		    cache: false,    
		    href: 'investor/investorAction!findInvestorByInvestorId.action?investorId='+row.investorId,    
		    modal: true   
		}); 
	}	

	
	// 查看该投资人的"合同详情"
	function showInvestContractDetailsView(index){
		var row = this.getRowData(index);
		$('#contractInfoDialog').dialog({    
		    title: '合同详情',    
		    width: 600,    	
		    height: 600,    
		    closed: false,
		    closable: true,
		    cache: false,    
		    href: 'investOrder/investOrderAction!findInvestorOrderContractDetails.action?investOrderId='+row.investOrderId,    
		    modal: true   
		});				
	}	
	
	
	/**
	 * 渲染investordg投资人列表
	 */
	function createInvestordg(){
		$("#investordg").datagrid({
			url:'investor/investorAction!findAllInvestor.action',
			width: 885,
			height: 534,
			pagination:true,
			rownumbers:true,
			border:false,
			singleSelect:true,
			nowrap:true,//如果为true，则在同一行中显示数据。设置为true可以提高加载性能。
			pageSize:20,
			pageList:[10,20,30,40],
			remoteSort:false,//定义是否从服务器对数据进行排序。
			striped:true,//是否显示斑马线
			//单击事件，单击“投资人列表”中的某一行，将选中的某个投资人的investorId，
			//传入到紧急联系人列表中，即$("#contactsdg")这个页面中。			
	 		onClickRow:function(rowIndex,rowData){
				$("#contactsdg").datagrid("reload",{investorId:rowData.investorId});
			},
			columns:[[
			        {field : 'chName',title : '客户姓名',width :80,align : 'center'},
	                {field : 'idNo',title : '证件号码',width : 120,align : 'center'},
		            {field : 'genderType',title : '性别',width : 80,align : 'center',formatter:function(value,row,index){
		            	return jqueryUtil.showText(value,sexArr);
		            }},
	                {field : 'mobileTel',title : '移动电话',width :150,align : 'center'},
	                {field : 'industry',title : '所属行业',width : 150,align : 'center'},
	                {field : 'jobType',title : '职业',width :80,align : 'center',formatter:function(value,row,index){
	                	return jqueryUtil.showText(value,jobTypeArr);
	                }},
	                {field : 'yearsOfWork',title : '工作年限',width : 60,align : 'center'},
	                {field : 'degreeType',title : '学历',width : 60,align : 'center',formatter:function(value,row,index){
	                	return jqueryUtil.showText(value,degreeTypeArr);
	                }},
	                {field : 'birthday',title : '出生日期',width : 150,align : 'center'},
	                {field : 'email',title : '邮箱',width : 150,align : 'center'}
		   ]],
		   toolbar:[{
			   iconCls: 'icon-add',
			   text:'确定',
			   handler:toSave
		   }]
		});
	}
	
	//渲染紧急联系人列表
	function createContacts(){
		$("#contactsdg").datagrid({
			url:'contacts/contactsAction!findInvestorContactListByInvestorId.action',
			width: 885,
			height: 534,
			pagination:true,
			rownumbers:true,
			border:false,
			singleSelect:false,
			nowrap:true,//如果为true，则在同一行中显示数据。设置为true可以提高加载性能。
			pageSize:20,
			pageList:[10,20,30,40],
			remoteSort:false,//定义是否从服务器对数据进行排序。
			striped:true,//是否显示斑马线
			columns : [ [
			             {field : 'chName',title : '姓名',width : 80,rowspan:2,align : 'center'},
			             {field : 'relationship',title : '与本人关系',width : 80,rowspan:2,align : 'center',formatter:function(value,row,index){
			            	 return jqueryUtil.showText(value,relationshipArr);
			             }},
			             {field : 'tel',title : '移动电话',width : 140,rowspan:2,align : 'center'},
			             {field : 'idNo',title : '证件号码',width : 120,rowspan:2,align : 'center'},
			             {title : '通讯地址',width : 340,colspan:4,align : 'center'}
			],[
						 {field : 'compProvince',title : '省',width : 80,align : 'center',formatter:function(value,row,index){
							 return jqueryUtil.showText(value,provinceArr);
						 }},
						 {field : 'compCity',title : '市',width : 80,align : 'center',formatter:function(value,row,index){
							 var cityArr = jqueryUtil.getAreaTextArr(row.compProvince);
							 return jqueryUtil.showText(value,cityArr);
						 }},
						 {field : 'compArea',title : '县/区',width : 80,align : 'center',formatter:function(value,row,index){
							 var areaArr = jqueryUtil.getAreaTextArr(row.compCity);
							 return jqueryUtil.showText(value,areaArr);
						 }},
						 {field : 'compAddrDetails',title : '详细地址',width : 200,align : 'center'}
			]]
		})
	}
	
	
	//确定
	function toSave(){
		var row = $("#investordg").datagrid("getSelected");
		if(row == null){
			$.messager.alert("提示","请您选择一行记录!","warning");
			return;
		}
		//发送ajax
		$.ajax({
			type:'POST',
			url:'investOrder/investOrderAction!saveInvestOrder.action',
			data:'investorId='+row.investorId+'&idCrad='+row.idNo+'&mobTel='+row.mobileTel+"&investorName="+row.chName,
			dataType:'JSON',
			success:function(iJson){
				if(iJson.status){
					$("#investApplyListDataGrid").datagrid("reload");//刷新列表
					$("#addDialog").dialog("close");//关闭弹窗
				}
				parent.$.messager.show({
					title : iJson.title,
					msg : iJson.message,
					timeout : 4000 * 2
				}); 
			}
		});
	}
	//删除
	function toDelete(index){
		var row = getRowData(index);
		if(row == null){
			$.messager.alert("提示","请选择一条记录执行删除!","warning");
			return;
		}		
    	$.messager.confirm('删除', '执行删除后，数据将不可恢复,是否执行?', function(d) {
    		if (d) {
    			//发送ajax
    			$.ajax({
    				type:'POST',
    				url:'investOrder/investOrderAction!doDeleteInvestOrder.action',
    				data:'investOrderId='+row.investOrderId,
    				dataType:'JSON',
    				success:function(iJson){
    					if(iJson.status){
    						$("#investApplyListDataGrid").datagrid("deleteRow",index);
    					}
    					parent.$.messager.show({
    						title : iJson.title,
    						msg : iJson.message,
    						timeout : 4000 * 2
    					}); 
    				}
    			});
    		}
    	});				
	}
	
	//提交申请
	function sumitInvestOrder(index){
		var row = this.getRowData(index);
		
		if((row.contractNo == null || row.contractNo == "") || (row.prodId ==null || row.prodId == "")){
			if(row.contractNo == null || row.contractNo == ""){				
				$.messager.alert('警告','合同信息没有完善，不能提交投资申请！');
				return false;
			}else if(row.prodId ==null || row.prodId == ""){				
				$.messager.alert('警告','理财产品信息没有完善，不能提交投资申请！');
				return false;
			}
		}else{
			$.messager.confirm('确定','是否确定提交所选的数据吗？',	function(flag) {
				if(flag){			
					$.ajax({
						/* url : "investOrder/investOrderAction!saveStartProcessInstance.action", */ 
						url : "investApply/investApplyAction!saveStartProcessInstance.action", 
						data : {"investOrderId" : row.investOrderId},
						success : function(rsp) {
							if(rsp.status){
								parent.$.messager.show({
									title : rsp.title,
									msg : rsp.message,
									timeout : 1000 * 2
								});
								$("#investApplyListDataGrid").datagrid('reload');
							}else{
								parent.$.messager.alert(rsp.title,rsp.message,'error');
							}
						}
					});
				}		
			});	// End Of 是否确定提交所选的数据吗
			
		}		
	}
	
	
	//查看流程图
	function checkProcessImg(index){
		var rows = $("#investApplyListDataGrid").datagrid("getRows");
		var rowm = rows[index];//获取本条数据
		var src = "investApply/investApplyAction!checkWorkFlowImg.action?investOrderId=" + rowm.investOrderId;
		$('#imageDialog').dialog("open");
		$("#image").attr("src", src);
	}
	var row;
	
	
	//查看审批意见
	function checkInvestOrderOpinions(index){
		var rows = $("#investApplyListDataGrid").datagrid("getRows");
		row = rows[index];//获取本条数据
		$('#OpinionsDialog').dialog({    
		    title: '历史审查意见',    
		    width: 800,    
		    height: 500,    
		    closed: false,    
		    cache: false,    
		    href: 'jsp/investOrder/optionsList.jsp',    
		    modal: true   
		});   
	}
</script>
<body>
<div class="position" style="margin-top: 5px;">您当前所在位置： 业务管理  &gt; 财富业务管理  &gt; 投资申请</div>
   <div class="well well-small" style="margin-left: 5px;margin-top: 5px">
		<span class="badge">提示</span>
			在此你可以新增<span class="label-info"><strong>投资订单</strong></span>并开启流程!
   </div>

   <!-- 投资申请管理数据列表 -->
   <table id="investApplyListDataGrid" style="margin: 0px;padding: 0px;overflow: auto;"></table>

   <!-- 新增投资申请DIV区域 -->
   <div id="addDialog" class="easyui-dialog" style="width:900px;height:600px;" title="新增投资申请" data-options="modal:true,resizable:true,iconCls:'icon-add',closed: true">
      <div id="tt" class="easyui-tabs" data-options="border:false">   
	    <div title="投资客户">   
	       <table id="investordg" style="margin: 0px;padding: 0px;"></table>
	    </div>   
	    <div title="紧急联系人">   
	       <table id="contactsdg" style="margin: 0px;padding: 0px;"></table>    
	    </div>   
	 </div> 
   </div>
   
	<!-- 投资客户信息对话框区域 -->
	<div id="investorView"></div>   
	
	<!-- 合同详情信息对话框区域 -->
	<div id="contractInfoDialog"></div>	
   
	<!-- 理财产品详情对话框区域 -->
	<div id="investorAndInvestProductsDialog"></div>   
   
	<!-- 流程图片弹框 -->
	<div id="imageDialog" class="easyui-dialog" title="流程图片"
			data-options="border:false,closed:true,fit:true">
			<img id="image" src="">
	</div>
   
	<!-- 完善合同信息对话框 -->
	<div id="dialog4CompleteInvestContractAndProductInfo"></div>	
   
	<!-- 审查意见 -->
	<div id="OpinionsDialog"></div>
	
</body>
</html>