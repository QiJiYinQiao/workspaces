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
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<jsp:include page="../../../layout/script.jsp"></jsp:include>
	<style type="text/css">
		a{
			text-decoration:none;
		}
	</style>
	<script type="text/javascript">
			//声明一个全局变量row
			//该变量可以在基于此Main页面上的弹出页面中使用，比如optionList.jsp页面。
			var $dg;
			var $grid;
			var ppeTypeCodeArr=jqueryUtil.getTextArr('ppe_type_code');
			var addWayCodeArr=jqueryUtil.getTextArr('add_way_code');
			var useStatusCodeArr=jqueryUtil.getTextArr('use_status_code');
			var depreciationWayCodeArr=jqueryUtil.getTextArr('depreciation_way_code');
			var ppeStatusArr=jqueryUtil.getTextArr('ppe_status');
			
		 	$(function() {
				$(window).resize(function(){  
		            $("#dg").datagrid("resize",{  
		            	height: $(window).height()-100,
		            	width : 'auto'
		            });                
		        });
				//加载固定资产报废申请的数据
				 $dg = $("#dg");
				 $grid=$dg.datagrid({
					url : "ppeStockInfoController/findPpeStockInfoList.do",
					title: "固定资产库存信息",
					width : 'auto',
					height : $(this).height()-83,
					pagination:true,
					rownumbers:true,
					border:true,
					singleSelect:true,
					nowrap:true,
					multiSort:false,
					border:false,
					fitColumns:false,
					pageSize:30,
					pageList:[10,20,30,40],
					columns : [ [
									{field : 'ppeCode',        title : '固定资产编码',    width : parseInt($(this).width()*0.07), align:'center'},
									{field : 'ppeName',        title : '固定资产名称',    width : parseInt($(this).width()*0.12), align:'center'},
									{field : 'ppeTypeCode',        title : '固定资产类型',    width : parseInt($(this).width()*0.1), align:'center',
										formatter:function(value,row,index){
											return jqueryUtil.showText(value,ppeTypeCodeArr);
										} },
									{field : 'organizationCode',        title : '部门编码',    width : parseInt($(this).width()*0.1), align:'center'},
									{field : 'addWayCode',    title : '增加方式编码',    width : parseInt($(this).width()*0.12), align:'center' ,
										formatter:function(value,row,index){
											return jqueryUtil.showText(value,addWayCodeArr);
										} },
									{field : 'useStatusCode',    title : '使用状况编码',    width : parseInt($(this).width()*0.12), align:'center',
										formatter:function(value,row,index){
											return jqueryUtil.showText(value,useStatusCodeArr);
										} },
									{field : 'useYears',    title : '使用年限(月份)',    width : parseInt($(this).width()*0.1), align:'center' },
									{field : 'usePercent',    title : '使用比例',    width : parseInt($(this).width()*0.1), align:'center' },
									{field : 'depreciationWayCode',    title : '折旧方法编码',   width : parseInt($(this).width()*0.12), align:'center' ,
										formatter:function(value,row,index){
											return jqueryUtil.showText(value,depreciationWayCodeArr);
										} },
									{field : 'beginUseDate',    title : '开始使用日期',   width : parseInt($(this).width()*0.12), align:'center' },
									{field : 'currency',    title : '币种',   width : parseInt($(this).width()*0.12), align:'center' },
									{field : 'exchangeRate',    title : '汇率',   width : parseInt($(this).width()*0.12), align:'center' },
									{field : 'originalValue',    title : '原值',   width : parseInt($(this).width()*0.12), align:'center' },
									{field : 'ppeSubstractValue',    title : '期初减值准备',   width : parseInt($(this).width()*0.12), align:'center' },
									{field : 'ppeGroupCode',    title : '资产组编码',   width : parseInt($(this).width()*0.12), align:'center' },
									{field : 'size',    title : '规格型号',   width : parseInt($(this).width()*0.12), align:'center' },
									{field : 'place',    title : '存放地点',   width : parseInt($(this).width()*0.12), align:'center' },
									{field : 'sumDepreciation',    title : '累计折旧',   width : parseInt($(this).width()*0.12), align:'center' },
									{field : 'projectClass',    title : '项目大类',   width : parseInt($(this).width()*0.12), align:'center' },
									{field : 'workTotal',    title : '工作总量',   width : parseInt($(this).width()*0.12), align:'center' },
									{field : 'sumWork',    title : '累计工作量',   width : parseInt($(this).width()*0.12), align:'center' },
									{field : 'unit',    title : '工作量单位',   width : parseInt($(this).width()*0.12), align:'center' },
									{field : 'usedMonths',    title : '已使用月份',   width : parseInt($(this).width()*0.12), align:'center' },
									{field : 'forCurrencryOriValue',    title : '外币原值',   width : parseInt($(this).width()*0.12), align:'center' },
									{field : 'scrapValueRate',    title : '净残值率',   width : parseInt($(this).width()*0.12), align:'center' },
									{field : 'scrapValue',    title : '净残值',   width : parseInt($(this).width()*0.12), align:'center' },
									{field : 'depreciationProjectCode',    title : '折旧科目编码',   width : parseInt($(this).width()*0.12), align:'center' },
									{field : 'projectCode',    title : '项目编码',   width : parseInt($(this).width()*0.12), align:'center' },
									{field : 'motorRate',    title : '电机功率',   width : parseInt($(this).width()*0.12), align:'center' },
									{field : 'motorNum',    title : '电机数量',   width : parseInt($(this).width()*0.12), align:'center' },
									{field : 'roomNum',    title : '间数',   width : parseInt($(this).width()*0.12), align:'center' },
									{field : 'area',    title : '建筑面积',   width : parseInt($(this).width()*0.12), align:'center' },
									{field : 'enteringDate',    title : '录入日期',   width : parseInt($(this).width()*0.12), align:'center' },
									{field : 'enteringor',    title : '录入员',   width : parseInt($(this).width()*0.12), align:'center' },
									{field : 'valueAddedTax',    title : '增值税',   width : parseInt($(this).width()*0.12), align:'center' },
									{field : 'custodian',    title : '保管人',   width : parseInt($(this).width()*0.12), align:'center' },
									{field : 'capitalConstitutionCode',    title : '资金构成编码',   width : parseInt($(this).width()*0.12), align:'center' },
									{field : 'capitalConstitutionPercent',    title : '资金构成比例',   width : parseInt($(this).width()*0.12), align:'center' },
									{field : 'supplierCode',    title : '供应商编码',   width : parseInt($(this).width()*0.12), align:'center' },
									{field : 'suplierName',    title : '供应商名称',   width : parseInt($(this).width()*0.12), align:'center' },
									{field : 'purchaseDate',    title : '采购日期',   width : parseInt($(this).width()*0.12), align:'center' },
									{field : 'serializeNo',    title : '序列号',   width : parseInt($(this).width()*0.12), align:'center' },
									{field : 'goodsCode',    title : '商品码',   width : parseInt($(this).width()*0.12), align:'center' },
									{field : 'ppeStatus',    title : '固定资产状态',   width : parseInt($(this).width()*0.12), align:'center' ,
										formatter:function(value,row,index){
											return jqueryUtil.showText(value,ppeStatusArr);
										}}
					              ] ],
					              onLoadSuccess:function(data){
									 	//当鼠标放上该字段时，提示功能
							            $(this).datagrid("doCellTip",{'max-width':'100px'});
								  }, 
		              toolbar:'#tb',
		              onClickCell:function(rowIndex, field, value){
		            	  $(this).datagrid("selectRow","rowIndex");
		              }
				});
				 
				 $("#ppeStatus").combobox({
					 data:ppeStatusArr,
					 valueField:'code',
					 textFiled:'text'
				 })
			});  
		 	
		 	
		//执行高级查询
		function doSearch(){			
			$("#dg").datagrid("load",{
				ppeCode:$('#ppeCode').val(),
				ppeName:$('#ppeName').val(),
				ppeStatus:$("#ppeStatus").combobox('getValue')
			}); 
		}

		//重置条件
		function clearAdvancedQueryConditions(){
			//1、清空高级查询各组件内容
			$("#searchForm").form("clear");
			//2、datagrid重新加载
			$("#dg").datagrid("load",{});
		}
		
		
		function importExcel(){
			$("#dd").dialog({
				title:'导入Excel',
				width:600,
				height:400,
				closed: false,    
			    modal: true   

			})
		}
		//上传并导入excel
		function doImportExcel(){
			var actionurl=$("#formId").attr('action');//提交路径
			$("#formId").form({
				url:actionurl,
				onSubmit:function(){
					$.messager.progress({
						title:'提示',
						msg:'正在导入中...',
						interval:1000
					});
				},
				success : function(result){
					$.messager.progress('close');
					result = $.parseJSON(result);
					if (result.status) {
						$.messager.alert(result.title,result.message);
						$("#formId").form("clear");
						$("#dd").dialog('close');
						$dg.datagrid("reload");
					}else{
						$.messager.alert(result.title,result.message);
					}
				}
			}).submit();
		}
	</script>
  </head>
  <body>
      <div data-options="region:'center',border : false">
      	<div class="position" style="margin-top: 5px;">您当前所在位置： 行政办公  &gt; 固定资产库存</div>
		<!-- 高级查询栏区域 -->
  		<div class="well well-small" style="margin-left: 5px;margin-top: 5px">
			<form id="searchForm" action="ppeRegController/findAllppeScrap.do" method="post">
			<input type="hidden"  name="appNoShow" id="appNoShow" />
				<table cellpadding="0" cellspacing="1" border="0">
					<tr>
						<td>固定资产编码:&nbsp;</td>
						<td><input name="ppeCode" id="ppeCode" class="easyui-textbox"  style="width:134px;" value=""  />&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td>固定资产名称:&nbsp;</td>
						<td><input name="ppeName" id="ppeName" class="easyui-textbox"  style="width:134px;" value=""  /></td>
						<td>固定资产状态:&nbsp;</td>
						<td><input name="ppeStatus" id="ppeStatus" class="easyui-textbox"  style="width:134px;"/>
						&nbsp;&nbsp;&nbsp;&nbsp;</td>
						
						<td width="5"></td>
						<td align="right">
						    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="false" onclick="doSearch();">执行查询</a>&nbsp;&nbsp;&nbsp;&nbsp;
						    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-reload" plain="false" onclick="clearAdvancedQueryConditions()">条件重置</a>
						</td>	
					</tr>	
				</table>
			</form>			  			
		</div>
		
			<div id="tb" style="padding: 2px 0">
				<table cellpadding="0" cellspacing="0">
					<tr>
						<td style="padding-left: 2px">
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-excel" plain="true" onclick="importExcel();">导入</a>
						</td>
					</tr>
				</table>
			</div>

		<!-- 表格区域 -->
		<table id="dg"  width="100%"></table>	
		
		<div id="dd" class="easyui-dialog" closed="true">
			<form id="formId" action="ppeStockInfoController/importDataToDB.do" method="post" enctype="multipart/form-data">
				<table>
				<tr>
					<td>
						<input type="file" id="file" name="file"  size=100/>
					</td>
					<td>
					   	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="doImportExcel()">上传文件</a>
					</td>
				</tr>
				</table>
			</form>
		</div>
  	</div>	
  </body>
</html>
