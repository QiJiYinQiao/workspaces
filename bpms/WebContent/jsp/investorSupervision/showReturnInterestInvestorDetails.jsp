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
	<jsp:include page="../../layout/script.jsp"></jsp:include>	
	<style type="text/css">
		a{
			text-decoration:none;
		}
	</style>
	<script type="text/javascript">
	
			//声明一个全局变量row
			//该变量可以在基于此Main页面上的弹出页面中使用，比如optionList.jsp页面。
			var row;
	
			var $dg;
			var $grid;
			var excelurl = "investorSupervision/investorSupervisionAction!doExportExcelTwo.action";
			$(function() {
				
				//初始化高级查询区域的组件
				//初始化理财产品列表				
				$("#productCombobox").combobox({
				    url:'investProduct/investProductAction!getAllInvestProducts4Combobox.action',    
				    valueField:'code',    
				    textField:'text' 					
				});

				//加载返息客户的数据
				 $dg = $("#dg");
				 $grid=$dg.datagrid({
					url : "investorSupervision/investorSupervisionAction!findReturnInterestInvestorInfoDetails.action",
					width : 'auto',
					height : $(this).height()-141,
					pagination:true,
					rownumbers:true,
					pageSize:30,
					border:false,
					singleSelect:true,
					nowrap:true,
					multiSort:false,
					columns : [ [ 
					              {field : 'contractNo',  title : '合同编号',    width : 100, align:'center', 
					            	  formatter: function(value, row, index){
					            		  if(row.contractNo == null || row.contractNo == ""){					            			  
						            		return "";					            			  					
					            		  }else{					            			  
										  	return "<a href=\"javascript:void(0)\" onclick=\"showInvestContractDetailsView("+index+")\">"+value+"</a>";					            			 
					            		  }
					            	  }		
					              },
					              {field : 'signDate',        title : '合同签署日期',    width : 100, align:'center' },
					              {field : 'investorName',        title : '客户姓名',    width : 100, align:'center',
					            	  formatter: function(value, row, index){
					            		  return "<a href=\"javascript:void(0)\" onclick=\"showInvestorView("+index+")\">"+value+"</a>";
					            	  }					            	  
					              },
					              {field : 'idCrad',        title : '身份证号码',    width : 160, align:'center' },
					              {field : 'mobTel',    title : '联系方式',    width : 100, align:'center' },
					              {field : 'bankName',      title : '开户行名称', width : 100, align:'center' },
					              {field : 'actNo',    title : '开户行账号',    width : 180, align:'center' },
					              {field : 'prodName',      title : '理财产品',   width : 60, align:'center',
					            	  formatter: function(value, row, index){
									      var result ="<a href='javascript:void(0);' onclick='showInvestorAndInvestProductsDetailsView(\""+row.investOrderId+"\");'>详情</a>";
					      				  return result;					            		  
					            	  }					            		  
					              },
					              {field : 'lendingCycle',  title : '理财天数（天）',   width : parseInt($(this).width()*0.07), align:'center' },
					              {field : 'ars', title : '年化收益率（%）',   width : parseInt($(this).width()*0.07), align:'center' },
					              {field : 'investEdu',      title : '理财金额（元）',   width : parseInt($(this).width()*0.07), align:'center' },
					              {field : 'totalInterest',  title : '利息总额（元）',       width : parseInt($(this).width()*0.07), align:'center' },
					              {field : 'interestPerMonth', title : '月付利息（元）',       width : parseInt($(this).width()*0.07), align:'center' },
					              {field : 'interestDate',   title : '计息日期',       width : parseInt($(this).width()*0.06), align:'center' },
					              {field : 'endDate',        title : '到期日期',       width : parseInt($(this).width()*0.06), align:'center' },
					              {field : 'daysToPayInterestCurMonth',  title : '本月计息天数（截至到本月30号）',       width : parseInt($(this).width()*0.11), align:'center' }
					              ] ],
		              toolbar:[{
		            	  text:'导出excel',
		            	  iconCls:'icon-excel',
		            	  handler:toExportExcel
		              }],
		              onClickCell:function(rowIndex, field, value){
		            	  $(this).datagrid("selectRow","rowIndex");
		              }					              
				});				 				 				 
			});

		// 根据索引获取行的基本信息的函数
		function getRowData (index) {
	        if (!$.isNumeric(index) || index < 0) { return undefined; }
	        var rows = $dg.datagrid("getRows");
	        return rows[index];
	    }
		
		
		// 查看该投资人的"合同详情"
		function showInvestContractDetailsView(index){
			var row = this.getRowData(index);
			$('#contractInfoDialog').dialog({    
			    title: '合同详情',    
			    width: 700,    	
			    height: 600,    
			    closed: false,
			    closable: true,
			    cache: false,    
			    href: 'investOrder/investOrderAction!findInvestorOrderContractDetails.action?investOrderId='+row.investOrderId,    
			    modal: true   
			});				
		}		
		
		//查看投资人详细信息
		function showInvestorView(index){
			var rows = $("#dg").datagrid("getRows");
			var row = rows[index];
			$('#investorView').dialog({    
					    title: '投资客户详情',    
					    width: 800,    
					    height: 450,    
					    closed: false,    
					    cache: false,    
					    href: 'investor/investorAction!findInvestorByInvestorId.action?investorId='+row.investorId,    
					    modal: true   
					}); 
		}
			
		
		//查看该投资人的理财产品详情界面
		function showInvestorAndInvestProductsDetailsView(investOrderId){
			$('#investorAndInvestProductsDialog').dialog({    
			    title: '理财产品详情',    
			    width: 1000,    	
			    height: 250,    
			    closed: false,
			    closable: true,
			    cache: false,    
			    href: 'investOrder/investOrderAction!findInvestorAndInvestProductsDetails.action?investOrderId='+investOrderId,    
			    modal: true   
			});						
		}
		
		//执行高级查询
		function doSearch(){			
			//1、收集高级查询数据。			
			var contractNo = $("#contractNo").val();        //获取合同编号
			var investorName = $("#investorName").val();    //获取客户姓名
			var prodId = $("#productCombobox").combobox('getValue');  //获取理财产品的ID
			
			var selectedYearAndMonh = $("#selectedYearAndMonh").val();
			var rtnInterestMonSelect = "";
			var rtnInterestYearSelect = "";
			
			//2、获取每个月份对应的的整数，比如12月份对应11，1月份对应0
			if( selectedYearAndMonh != ""){
				rtnInterestMonSelect = parseInt(selectedYearAndMonh.substr(4,2)) - 1;
				rtnInterestYearSelect = selectedYearAndMonh.substr(0,4);
			}
					
			//3、执行高级查询
			$("#dg").datagrid("load",{
				contractNo : contractNo,
				investorName : investorName,
				prodId : prodId,
				rtnInterestMonSelect : rtnInterestMonSelect,
				rtnInterestYearSelect : rtnInterestYearSelect
			});

			//设置导出报表的url
			excelurl = "investorSupervision/investorSupervisionAction!doExportExcelTwo.action?contractNo="+contractNo+"&investorName="+ encodeURI(encodeURI(investorName))+"&prodId="+prodId+"&rtnInterestMonSelect="+rtnInterestMonSelect+"&rtnInterestYearSelect="+rtnInterestYearSelect;
		}

		//充值条件
		function clearAdvancedQueryConditions(){
			//1、清空高级查询各组件内容
			$("#searchForm").form("clear");
			//2、datagrid重新加载
			$("#dg").datagrid("load",{});
		}
		//导出excel
		function toExportExcel(){
			window.location.href = excelurl;
		}
		
	</script>
  </head>
  <body>
      <div data-options="region:'center',border : false">
      	<div class="position" style="margin-top: 5px;">您当前所在位置： 业务管理  &gt; 财务监控管理  &gt; 投资客户监管 &gt; 返息客户明细</div>
		
		
		<!-- 高级查询栏区域 -->
  		<div class="well well-small" style="margin-left: 5px;margin-top: 5px">
			<form id="searchForm" action="" method="post">
				<table cellpadding="0" cellspacing="1" border="0">
					<tr>
						<td>合同编号：&nbsp;&nbsp;</td>
						<td><input name="contractNo" id="contractNo" type="text" class="easyui-textbox easyui-validatebox" style="width: 170px"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td>客户姓名：&nbsp;&nbsp;</td>
						<td><input name="investorName" id="investorName" type="text" class="easyui-textbox easyui-validatebox" style="width: 170px"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td>理财产品：&nbsp;&nbsp;</td>
						<td><input name="prodId" id="productCombobox" class="easyui-combobox" style="width: 170px"/></td>										
					</tr>
				
					<tr>	
						<td>请选择要查询的返息客户的月份：&nbsp;&nbsp;</td>
						<td>
							<input id="selectedYearAndMonh" class="Wdate" style="width: 170px" type="text" onFocus="WdatePicker({dateFmt:'yyyyMM',maxDate:'#F{\'2120-10-01\'}',isShowClear:false})"/>				
						</td>
						
						<td colspan="4" align="right">
						    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="false" onclick="doSearch();">执行查询</a>&nbsp;&nbsp;&nbsp;&nbsp;
						    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-reload" plain="false" onclick="clearAdvancedQueryConditions()">条件重置</a>
						</td>						
					</tr>										
				</table>
			</form><br>
			注：若不输入任何查询条件，默认查询所有投资客户的<b><font color="red">当月</font></b>返息信息			  			
		</div>
		
		<!-- 到期客户明细数据表格区域 -->
		<table id="dg" ></table>		

		<!-- 合同信息对话框区域 -->
		<div id="contractInfoDialog"></div>
		
		<!-- 投资客户数据对话框区域 -->
		<div id="investorView"></div>

		<!-- 理财产品详情对话框区域 -->
		<div id="investorAndInvestProductsDialog"></div>

  	</div>	
  </body>
</html>
