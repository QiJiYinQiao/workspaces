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
    <title>理财产品</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<jsp:include page="../../layout/script.jsp"></jsp:include>
	<script type="text/javascript">
	
			// 从数据字典中查找出"理财产品状态"的数据。
			var prodStatusArray=jqueryUtil.getTextArr("prod_status");	
		
			var $dg;
			var $grid;
			$(function() {				
				 $dg = $("#dg");
				 
					// 自动调整页面高度
				 	$(window).resize(function(){  
				            $("#dg").datagrid({  
				            	height: $(window).height()-100,
				            	width : 'auto'
				            });                
				        });				 
				 
				 $grid=$dg.datagrid({
					url : "investProduct/investProductAction!getAllInvestProducts.action",
					width : 'auto',
					height : $(window).height()-127,
					pagination:true,
					rownumbers:true,
					border:false,
					singleSelect:true,
					nowrap:true,
					multiSort:false,
					columns : [ 
					            [ {field : 'prodName',            title : '产品名称',           width : parseInt($(this).width()*0.1),  align : 'center'},
					              {field : 'lowLendEdu',          title : '最低出借金额（元）',   width : parseInt($(this).width()*0.1),  align : 'center',  sortable:true},
					              {field : 'higLendEdu',             title : '最高出借金额（元）',   width : parseInt($(this).width()*0.1),  align : 'center',  sortable:true},
					              {field : 'lendingCycle',         title : '出借周期（天）',       width : parseInt($(this).width()*0.08),  align : 'center',  sortable:true},
					              {field : 'ars',  title : '年化收益率（%）',          width : parseInt($(this).width()*0.08),  align : 'center'},
					              {field : 'ytm',           title : '到期收益率（%）',          width :parseInt($(this).width()*0.1),   align : 'center'},
					              {field : 'msf',           title : '年化折标系数',  width :parseInt($(this).width()*0.07),   align : 'center'},
					              {field : 'prodStatus',    title : '产品状态',           width :parseInt($(this).width()*0.04),   align : 'center',
										formatter:function(value,row){
						            		  return jqueryUtil.showText(value,prodStatusArray); 
										}				            		  					              	
					              },
					              {field : 'prodDesc',title : '产品描述',                   width : parseInt($(this).width()*0.15),  align : 'center'},
					              {field : 'operType4Product',title : '操作',width : parseInt($(this).width()*0.12),align : 'center',
					            	  formatter: function(value,row,index){
				            			  var result="<a href='javascript:void(0);' onclick='toSaveOrUpdateInvestProductOpenDlg("+index+");'>修改产品</a>&nbsp;&nbsp;&nbsp;&nbsp;";				            			  
				            			  	  result+="<a href='javascript:void(0);' onclick='deleteInvestProduct(\""+row.prodId+"\");'>删除产品</a>&nbsp;&nbsp;&nbsp;&nbsp;" 
				            			  	  result+="<a href='javascript:void(0);' onclick='showInvestProductDetails(\""+row.prodId+"\");'>详情</a>"
			      						  return result;					            		  					            		  
					      				}
								   }
					              
					              ] 
					           ],
					 toolbar : '#tb'					           					          
				});			 
				 
				var queryItemData = [{text : "产品名称", value : "prodName"}, 
					                 {text : "年化收益率", value : "ars"},
					                 {text : "到期收益率", value : "ytm"},
					                 {text : "最低出借金额", value : "lowLendEdu"},
					                 {text : "最高出借金额", value : "higLendEdu"},
					                 {text : "出借周期", value : "lendingCycle"},						                    				                     
					                 {text : "产品状态", value : "prodStatus"}];
				
				var options01 = [{text : "等于", value : "eq"}, 
				                 {text : "不等于", value : "ne"}];
				
				var options02 = [{text : "等于", value : "eq"}, 
				                 {text : "大于且等于", value : "ge"},
				                 {text : "大于", value : "gt"},
				                 {text : "小于且等于", value : "le"},
				                 {text : "小于", value : "lt"},
				                 {text : "不等于", value : "ne"}];															 
				 
				 //初始化查询项目的下拉列表
				 $("#queryItem").combobox({
					 valueField: 'value',
					 textField: 'text',					 					 
					 data : queryItemData,
					 panelHeight:170,	
					//注册Easy-UI, combobox的onSeclete事件	
					//目的：实现理财产品中，高级查询的“运算条件”随着“查询项目”的改变而发生联动。
					onSelect : function(){
							var myOptValue = $("#queryItem").combobox("getValue") ;
							
							//1.清空原来的operType这个combobox中的选项
							$("#operType").combobox("clear");
							
							//2.动态添加"操作类型"的下拉列表框的option							
							if( myOptValue != null && (myOptValue == 'prodName' || myOptValue == 'prodStatus') ){
								$("#operType").combobox({
									panelHeight:50,
									data : options01
								});
							}else{
								$("#operType").combobox({
									panelHeight:140,
									data : options02								
								});
							}

							//3.清空文本输入框——用户输入的条件							
							$("#userInputCondition").val("");
						} 					 
				 });					
				
				 //初始化operType的下拉列表
				 $("#operType").combobox({
					 valueField: 'value',
					 textField: 'text',					 					 
					 data : options02,
					 panelHeight:140,					 
				 });					 
			});
			
			//点击执行查询
			function doSearch(){
				var queryItem = $("#queryItem").combobox('getValue');
				var operType = $("#operType").combobox('getValue');
				var userInputCondition = $("#userInputCondition").val();												
				
				//如果用户选择的查询项目是“产品状态”
				if(queryItem == "prodStatus"){
					if(userInputCondition == "产品有效"  || userInputCondition == "有效"){
						userInputCondition = 'A';
					}
					if(userInputCondition == "产品无效"  || userInputCondition == "无效" ){
						userInputCondition = 'B';
					}
				}
				
				$("#dg").datagrid('load', {			
							queryItem : queryItem,
							operType : operType,
							userInputCondition : userInputCondition});								
			}
			
			
			/** 清空高级查询条件 ，重新加载贷款订单数据**/
			function clearAdvancedQueryConditions(){
				$("#searchForm").form("clear");		
				$("#dg").datagrid('load',{});
			}
			
			
			// 根据索引获取每一行的基本信息
			function getRowData (index) {
		        if (!$.isNumeric(index) || index < 0) { return undefined; }
		        var rows = $grid.datagrid("getRows");
		        return rows[index];
		    }			
			
			
			/* 动态显示弹出的Dialog的标题,显示"添加"或者"修改"理财产品数据*/
			function changeMyTitle(selectedRow){
				if(null != selectedRow){
					return '修改理财产品';					
				}else{
					return '添加理财产品';
				}
			}			
			
			
			/** 点击按钮，新增或者修改理财产品 **/			
			function toSaveOrUpdateInvestProductOpenDlg(index){
				var selectedRow = getRowData(index);									 
				
				$("#saveOrUpdateInvestProductDialog").dialog({
					/* 动态显示Dialog的标题	*/
					width : 700,
					height : 350,					
					title : changeMyTitle(selectedRow),
					href : "jsp/investproducts/addOrUpdateInvestProduct.jsp",
				    onLoad:function(){
				    	var saveOrUpdateForm = $("#investProductInputOrSaveForm");				    	
				    	saveOrUpdateForm.form("load",selectedRow);			    	
				    },					
					modal:true,
					resizable:true,
					iconCls:'icon-add',
					closed: false
				});
			}
						
			/** 删除理财产品  **/
			function deleteInvestProduct(prodId){
		    	$.messager.confirm('删除', '执行删除后，数据将不可恢复,是否执行?', function(d) {
		    		if (d) {
		    			$.ajax( {
		    				type : "POST",
		    				url : 'investProduct/investProductAction!deleteInvestProductById.action',
		    				data : "prodId="+prodId,
		    				dataType:'JSON',
		    				success : function(iJson) {		    					    				
		    					if(iJson.status){
		    						//刷新列表		    						
		    						$("#dg").datagrid("load",{});
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
			
			
			/** 显示理财产品的详情  **/												
			function showInvestProductDetails(prodId){				
 				$("#investProductsDialogDialog").dialog({
				    title: '理财产品详情',    
				    width: 750,    	
				    height: 200,    
				    closed: false,
				    closable: true,
				    cache: false,    
				    href: "investProduct/investProductAction!findInvestProductDetailsById.action?prodId="+prodId,   
				    modal: true   					
				});
 			}						
		</script>
  </head>
  <body>
      <div data-options="region:'center',border : false">
      <div class="position" style="margin-top: 5px;">您当前所在位置： 业务管理  &gt; 财富业务管理  &gt; 理财产品 </div>
  		<div class="well well-small" style="margin-left: 5px;margin-top: 5px">
			<span class="badge">高级查询</span>
			<p>
				<form id="searchForm" action="" method="post">
					<table cellpadding="0" cellspacing="1" border="0">
						<tr>	
							<td>选择查询项目：</td>						
							<td><input id="queryItem" name="queryItem" class="easyui-combobox" style="width:130px;" ></td>
							<!-- 用户选择运算条件 -->											
							<td><input id="operType" name="operType" class="easyui-combobox" style="width:90px;" data-options="panelHeight:150"/></td>
							<!-- 用户输入文本条件 -->
							<td><input id="userInputCondition" name="userInputCondition" type="text" style="width:200px;"></input></td>
							
							<td>&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="false" onclick="doSearch();">执行查询</a>&nbsp;&nbsp;
								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-reload" plain="false" onclick="clearAdvancedQueryConditions()">清空</a>
							</td>
																									
						</tr>						
					</table>
				</form>			
			</p>									
		</div>
		
		
		<div id="tb" style="padding: 2px 0">
			<table cellpadding="0" cellspacing="0">
				<tr>
					<td style="padding-left: 10px">
 						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="false" onclick="toSaveOrUpdateInvestProductOpenDlg();">新增理财产品</a>&nbsp;&nbsp;
 					</td> 
				</tr>
			</table>
		</div>
		
		<!-- 理财产品列表区域 -->
		<table id="dg" title="理财产品列表"></table>
		
		<!-- 增加或修改理财对话框区域 -->
		<div id="saveOrUpdateInvestProductDialog"></div>	
								
		<!-- 理财产品详情对话框区域 -->
		<div id="investProductsDialogDialog"></div>		
		
  	</div>
  </body>
</html>
