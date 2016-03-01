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
    <title>日志管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<jsp:include page="../../layout/script.jsp"></jsp:include>
	<script type="text/javascript">
			var $dg;
			var $grid;
			var state = 0;
			var sexArr = jqueryUtil.getTextArr("gender_type");//性别
			var idtypeArr = jqueryUtil.getTextArr("id_type");//证件类型
			var jobtypeArr = jqueryUtil.getTextArr("job_type");//职业
			var degreeTypeArr = jqueryUtil.getTextArr("degree_type");//学历
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
					url : "investor/investorAction!findAllInvestor.action",
					width : 'auto',
					height : $(window).height()-40,
					pagination:true,
					rownumbers:true,
					border:false,
					singleSelect:true,
					nowrap:true,
					multiSort:false,
					columns : [ [ {field : 'chName',title : '客户姓名', width : parseInt($(this).width() * 0.05) ,align : 'center'},
					              {field : 'idType',title : '证件类型', width : parseInt($(this).width() * 0.05) ,align : 'center',formatter:function(value, row, index){
								        return jqueryUtil.showText(value,idtypeArr);
							         }  
		                          },
		                          {field : 'idNo',title : '证件号码', width : parseInt($(this).width() * 0.09) ,align : 'center'},
					              {field : 'genderType',title : '性别', width : parseInt($(this).width() * 0.04) ,align : 'center',
									formatter:function(value, row, index){
										return jqueryUtil.showText(value,sexArr);
									}
								  },
					              {field : 'mobileTel',title : '移动电话', width : parseInt($(this).width() * 0.07) ,align : 'center'},
					              {field : 'industry',title : '所属行业', width : parseInt($(this).width() * 0.05) ,align : 'center'},
					              {field : 'jobType',title : '职业', width : parseInt($(this).width() * 0.06) ,align : 'center',
					            	  formatter:function(value, row, index){
											return jqueryUtil.showText(value,jobtypeArr);
									  }  
					              },
					              {field : 'yearsOfWork',title : '工作年限', width : parseInt($(this).width() * 0.05) ,align : 'center'},
					              {field : 'degreeType',title : '学历', width : parseInt($(this).width() * 0.05) ,align : 'center',formatter:function(value, row, index){
					            	  return jqueryUtil.showText(value,degreeTypeArr);
					              }},
					              {field : 'birthday',title : '出生日期', width : parseInt($(this).width() * 0.07) ,align : 'center'},
					              {field : 'email',title : '邮箱', width : parseInt($(this).width() * 0.09) ,align : 'center'},
					              {field : 'companyName',title : '单位名称', width : parseInt($(this).width() * 0.1) ,align : 'center'}
					              ] ],toolbar:'#tb'
				});
				//搜索框
				$("#searchbox").searchbox({ 
					 menu:"#mm", 
					 prompt :'模糊查询',
				    searcher:function(value,name){   
				    	var str="{\"searchName\":\""+name+"\",\"searchValue\":\""+value+"\"}";
			            var obj = eval('('+str+')');
			            $dg.datagrid('reload',obj); 
				    }
				   
				}); 
			});
			
			//删除
			function delRows(){
				 var rows = $('#dg').datagrid('getSelections');//获取选中的记录
				 if(rows!=null&&rows.length>=1){
					 var ids = new Array();
					 $.messager.confirm('删除', '删除该记录将不可恢复，确认删除吗?', function(d) {
						 if(d){
							 $.each(rows,function(i,row){
									if (row) {
										var rowIndex = $('#dg').datagrid('getRowIndex', row);
										$('#dg').datagrid('deleteRow', rowIndex);
										ids.push(row.investorId);//将ID放入数组中
									}
							 });
							 ids = ids.join(",");// 转换为字符串
							 $.ajax({
								    type:'post',
									url:"investor/investorAction!delInvestor.action",
									data: "IDS="+ids,
									success: function(data){
										$.messager.show({
											title : data.title,
											msg : data.message,
											timeout : 1000 * 2
										});
									},
									error:function(data){
										$.messager.show({
											title : data.title,
											msg : data.message,
											timeout : 1000 * 2
										});
									}
							});
						 }
					 })
				 }else{
					 $.messager.alert("提示","请至少选择一条记录!","warning");
				 }
			}
			//弹窗修改
			function updRowsOpenDlg() {
				var row = $dg.datagrid('getSelected');
				var addr=new Array();
				if (row) {
					$.ajax({
						url:'investor/investorAction!findAddressById.action',
						data:'addressId='+row["commAddr"],
						dataType:'json',
						async : false,
						success:function(data){
							addr=data;
						},
						error:function(data){}
					});
					
					$("#dd").dialog({
						title : '编辑',
						width : 900,
						height : 600,
						modal:true,
						href : "jsp/investor/investorForm.jsp",
						onLoad:function(){
							var f = $("#baseInfoForm");
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
					        initLinkPeopleGrid(row.investorId);
						},
						onClose:function(){
							$("#dg").datagrid("reload");
						}
					}); 
				}else{
					parent.$.messager.show({
						title :"提示",
						msg :"请选择一行记录!",
						timeout : 1000 * 2
					});
				}
			}
			//弹窗增加
			function addRowsOpenDlg() {
				parent.$.modalDialog({
					title : '添加',
					iconCls:'icon-add',
					width : 910,
					height : 610,
					href : "jsp/investor/investorForm.jsp",
					onDestroy:function(){
						$("#dg").datagrid("reload");
					}
				});
			}
		</script>
  </head>
  <style>
  .nkframe_position{padding-left:30px;margin-bottom:10px;border-bottom:1px solid #d2e7f8;height:24px;line-height:24px;background:url(extend/nk_position.gif) 5px center no-repeat;font-size:12px;font-weight:normal;}
  </style>
  <body>
      <div data-options="region:'center',border : false">
     <div class="position" style="margin-top: 5px;">您当前所在位置： 业务管理  &gt; 财富业务管理  &gt; 客户管理 </div>
		<div id="tb" style="padding:2px 0">
			<table cellpadding="0" cellspacing="0">
				<tr>
					<td style="padding-left:2px">
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addRowsOpenDlg();">添加</a>
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updRowsOpenDlg();">修改</a>
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRows();">删除</a>
					</td>
					<td style="padding-left:2px">
						<input id="searchbox" type="text"/>
					</td>
				</tr>
			</table>
		</div>
		<div id="mm">
				<div name="chName">名称</div>
				<div name="idNo">身份证号</div>
				<div name="mobileTel">手机</div>
		</div>
		<table id="dg" title="客户管理"></table>
  	</div>
	<div id ="dd"></div>
  </body>
</html>
