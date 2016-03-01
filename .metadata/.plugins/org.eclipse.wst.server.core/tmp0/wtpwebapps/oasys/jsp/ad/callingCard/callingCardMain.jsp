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
		//提交名片申请
		function saveCardApply(index){
			var rows = $("#dg").datagrid("getRows");
			var row = rows[index];
			$.messager.confirm('提交申请', '确定要提交名片申请吗?', function(d) {
	    		if (d) {
	    			$.ajax({
	    				type : "POST",
	    				url : 'callingCard/saveCardApply.do',
	    				data : "id="+row.caID,
	    				dataType:'JSON',
	    				success : function(iJson) {
	    					$.messager.alert(iJson.title,iJson.message,'info'); 
// 		    					//刷新列表		    						
		    					$("#dg").datagrid("reload");
	    				},error:function(){
	    					alert("失败了");
	    				}
		    		});
	    		}
	    	});		
		}	
		//审批状态
		var procStateJson = [{ 
			"id":"",
			"text":"全部",
			"selected":true
			},{
			"id":1, 
			"text":"初始状态"
			},{ 
			"id":2, 
			"text":"审批中" 
			},{ 
			"id":3, 
			"text":"已完成"
			},{ 
			"id":4, 
			"text":"已失效" 
			},{ 
			"id":5, 
			"text":"已撤销" 
			},{
			"id": '6',
			"text": '已拒绝'
			}] ;
			var $dg;
			var $grid;
			var $row;
			var $row1;//判断总部分部
		 	$(function() {
		 		$(window).resize(function(){
		            $("#dg").datagrid("resize",{  
		            	height: $(window).height()-125,
		            	width : 'auto'
		            });                
		        });
		 		//加载名片申请数据
				 $dg = $("#dg");
				 $grid=$dg.datagrid({
					url : "callingCard/index.do",
					width : 'auto',
					height : $(this).height()-100,
					pagination:true,
					rownumbers:true,
					border:true,
					singleSelect:true,
					nowrap:true,//如果为true，则在同一行中显示数据。设置为true可以提高加载性能。
					pageSize:10,
					pageList:[10,20,30,40],
					remoteSort:false,//定义是否从服务器对数据进行排序。
					striped:true,//是否显示斑马线
					columns : [ [ 
					              {field : 'appNo',        title : '申请编号',    width : parseInt($(this).width()*0.1), align:'center' },
					              {field : 'userName',        title : '姓名',    width :parseInt($(this).width()*0.08), align:'center', 
					            	  formatter : function(value, row, index) {
											return "<a href=\"javascript:void(0)\" onclick=\"toInfo("+ index + ");\">" + value + "</a>";
										}  
					              },
					              {field : 'deptName',    title : '申请部门',    width :parseInt($(this).width()*0.06), align:'center' },
						          {field : 'positionName',    title : '职务',    width :parseInt($(this).width()*0.06), align:'center' },
						          {field : 'price',    title : '单价',    width :parseInt($(this).width()*0.06), align:'center' },
						          {field : 'appQty',    title : '数量(盒)',    width :parseInt($(this).width()*0.06), align:'center' },
						          {field : 'subTotalAMT',    title : '合计金额',    width :parseInt($(this).width()*0.06), align:'center',
						        	  formatter: function(value,row,index){
						        		  str=value.toString();
						        		  if(/[^0-9\.]/.test(str)) return "invalid str";
						        	        str=str.replace(/^(\d*)$/,"$1.");
						        	        str=(str+"00").replace(/(\d*\.\d\d)\d*/,"$1");
						        	        str=str.replace(".",",");
						        	        var re=/(\d)(\d{3},)/;
						        	        while(re.test(str))
						        	                str=str.replace(re,"$1,$2");
						        	        str=str.replace(/,(\d\d)$/,".$1");
						        	        return "￥" + str.replace(/^\./,"0.")
						        	  }
								  },
						          {field : 'procStatusInfo',    title : '流程状态',    width :parseInt($(this).width()*0.06), align:'center' },
						          {field : 'appDate',    title : '申请日期',    width :parseInt($(this).width()*0.15), align:'center' },
						          {field : 'sumAppQty',    title : '总和(数量)',    width :parseInt($(this).width()*0.08), align:'center' },
						          {field : 'totalAMT',    title : '合计金额(总)',    width :parseInt($(this).width()*0.08), align:'center',
						        	 formatter:function(value,row,index){
					        		  str=value.toString();
					        		  if(/[^0-9\.]/.test(str)) return "invalid str";
					        	        str=str.replace(/^(\d*)$/,"$1.");
					        	        str=(str+"00").replace(/(\d*\.\d\d)\d*/,"$1");
					        	        str=str.replace(".",",");
					        	        var re=/(\d)(\d{3},)/;
					        	        while(re.test(str))
					        	                str=str.replace(re,"$1,$2");
					        	        str=str.replace(/,(\d\d)$/,".$1");
					        	        return "￥" + str.replace(/^\./,"0.")
						         	 }
						          },
						          {field : 'remark',    title : '备注',    width :parseInt($(this).width()*0.08), align:'center' },
						          {field : 'caozuo',    title : '操作',    width :parseInt($(this).width()*0.25), align:'center',
						        	  formatter: function(value,row,index){
						        		  var result='';
						        		  if(row.procStatusInfo=="初始状态"){
						        			  result="<a href='javascript:void(0);'  onclick='toEdit("+index+");'>编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;";				            			  
				            			  	  result+="<a href='javascript:void(0);' onclick='deleteInvestProduct(\""+index+"\");'>删除</a>&nbsp;&nbsp;&nbsp;&nbsp;";
				            			  	  result+="<a href='javascript:void(0);' id='saveApply' onclick='saveCardApply(\""+index+"\");'>提交申请</a>&nbsp;&nbsp;&nbsp;&nbsp;";
				            			  	  result+="<a href='javascript:void(0);' id='queryAccessory' onclick='lookAttachment("+index+");'>查看附件</a>";
						        		  }else{
						        			  result+="<a href='javascript:void(0);' id='queryApplyView' onclick='checkHistoryOpinions("+index+");'>查看审批意见</a>&nbsp;&nbsp;&nbsp;&nbsp;";
						        			  if(row.procStatusInfo=="审批中"){
						        				  result+="<a href='javascript:void(0);' id='queryApplyFlow' onclick='showImage("+index+");'>查看申请流程</a>&nbsp;&nbsp;&nbsp;&nbsp;";
						        			  }
						        			  result+="<a href='javascript:void(0);' id='queryAccessory' onclick='lookAttachment("+index+");'>查看附件</a>";
						        		  }
			      						  return result;					            		  					           		  
					      				}
						          }
					              ] ],
		              toolbar:'#tb',
		              onClickCell:function(rowIndex, field, value){
		            	  $(this).datagrid("selectRow","rowIndex");
		              },
		       	   onLoadSuccess:function(data){
		    		   var rows = data.rows;
		               var mergeMap = {};
		               if(rows){
		               	for(var i=0;i<rows.length;i++){
		               		var appNo = rows[i].appNo;
		               		if( appNo in mergeMap ){
		               			mergeMap[appNo].rowspan++;
		               		}else{
		               			mergeMap[appNo]={"index":i,"rowspan":1}
		               		}
		               	}
		               }
		               for(var i in mergeMap){
		               	$(this).datagrid('mergeCells',{
		                       index: mergeMap[i].index,
		                       field: 'appNo',
		                       rowspan: mergeMap[i].rowspan
		                   });
		              	   $(this).datagrid('mergeCells',{
		                       index: mergeMap[i].index,
		                       field: 'sumAppQty',
		                       rowspan: mergeMap[i].rowspan
		                   });
		              	   $(this).datagrid('mergeCells',{
		                       index: mergeMap[i].index,
		                       field: 'totalAMT',
		                       rowspan: mergeMap[i].rowspan
		                   });
		              	   //由于分公司和总部共用一个页面,分公司一次提交多个人，备注只填写一次，总部个人提交只需提交一条申请，备注一次，总部保存并添加为了上传附件，所以合并备注列
		              	   $(this).datagrid('mergeCells',{
		                       index: mergeMap[i].index,
		                       field: 'remark',
		                       rowspan: mergeMap[i].rowspan
		                   });
		                   $(this).datagrid('mergeCells',{
		                       index: mergeMap[i].index,
		                       field: 'caozuo',
		                       rowspan: mergeMap[i].rowspan
		                   });
		               }
		               $(this).datagrid("doCellTip",{'max-width':'100px'});
		    	  }
				});
					$('#orgId').combobox({
						data:procStateJson,
						valueField:'id',
						textField:'text',
						editable :false
					});
			}); 

		 	
		 	/** 删除主表的名片申请数据  **/
			function deleteInvestProduct(index){
		 		var rows,row,caId;
// 		 		if(id==2){//删除主表
				rows = $("#dg").datagrid("getRows");
				row = rows[index];
				caId=row.caID
// 		 		}else{
// 					rows = $("#lookLoanOrderdgAdd").datagrid("getRows");
// 					row = rows[index];
// 					caId=row.caId;
// 		 		}
		    	$.messager.confirm('删除', '执行删除后，数据将不可恢复,是否执行?', function(d) {
		    		if (d) {
		    			$.ajax( {
		    				type : "POST",
		    				url : 'callingCard/removeCard.do',
		    				data : "id="+caId,
		    				dataType:'JSON',
		    				success : function(iJson) {
		    					if(iJson.status){
		    						$.messager.alert("提示","删除申请成功","warning");
	// 		    					//刷新列表		 
			    					$("#dg").datagrid("reload");
// 			    					parent.$.messager.show({
// 			    						title : iJson.title,
// 			    						msg : iJson.message,
// 			    						timeout : 4000 * 2
// 			    					});
		    					}else{
		    						$.messager.alert("提示","删除申请失败","warning");
		    					}
		    				}
 		    			});
		    		}
		    	});		
			}	
		 	
		//执行高级查询
		function doSearch(){			
			$("#dg").datagrid("load",{
				procStatus:$("#orgId").combobox("getValue"),
				appDateBefore:$('#queryDateBeginId').datebox('getValue'),
				appDateAfter:$('#queryDateEndId').datebox('getValue'),
				appApplyDateBefore:$('#queryApplyDateBeginId').datebox('getValue'),
				appApplyDateAfter:$('#queryApplyDateEndId').datebox('getValue')
				});
		}

		//重置条件
		function clearAdvancedQueryConditions(){
			//1、清空高级查询各组件内容
			$("#searchForm").form("clear");
			//2、datagrid重新加载
			$("#dg").datagrid("load",{});
		}
		
		/* 动态显示弹出的Dialog的标题,显示"添加"或者"修改"报废申请*/
		function changeMyTitle(selectedRow){
			if(null != selectedRow){
				return '修改名片申请';					
			}else{
				return '添加名片申请';
			}
		}
		
		// 根据索引获取每一行的基本信息
		function getRowData (index) {
	        if (!$.isNumeric(index) || index < 0) { return undefined; }
	        var rows = $grid.datagrid("getRows");
	        return rows[index];
	    }
		//查看历史审批意见
		function checkHistoryOpinions(index){
			$$row = getRowData(index);
			$dialog=$("<div></div>").dialog({
			/* 动态显示Dialog的标题	*/
			width : 800,
			height : 450,					
			title : "查看审批意见",
			href : "jsp/ad/optionsList.jsp",
			onClose:function(){
				$$row=null;
		    	$(this).dialog('destroy');
			},
			modal:true,
			resizable:false,
			iconCls:'icon-add',
			closed: false
		});
		}
		//查看流程图
		function showImage(index){
			var rowdata = getRowData(index);
			var src = "callingCard/showProcessImg.do?caId="+rowdata.caID;
			$('#imageDialog').dialog("open");
			$("#image").attr("src", src);
		}
		//编辑
		function toEdit(index){
			var rows = $("#dg").datagrid("getRows");
			$row = rows[index];
			$('#addWindow').dialog({    
			    title: '修改',    
			    width: 850,    
			    height: $(this).height()*0.7,    
			    closed: false,    
			    cache: false, 
			    resizable:false,
				href:'jsp/ad/callingCard/callingCardAppAddForm.jsp',
			    modal: true,
			    onLoad : function(){
			    	if(index!=null){
			    		$("#appPeoNo").val($row.appNo);
						 badgePeopleGrid($row.appNo);
// 						 $("#appPeopleForm").form("load",row);
					 }
			    },
			    onClose : function(){
			    	$("#dg").datagrid("reload");
			    	$row = null;
			    },
			    buttons : [ {
					text : '关闭',
					iconCls : 'icon-cancel',
					handler : function() {
						$('#addWindow').dialog('close');
						$("#dg").datagrid("reload");
						$row = null;
					}
				}]
			    
			});
		}
		/**
		 * 新增
		 */
		function toAdd(){
			$('#addWindow').dialog({    
			    title: '新增',    
			    width: 850,    
			    height: $(this).height()*0.7,    
			    closed: false,    
			    cache: false, 
			    resizable:false,
			    href: "jsp/ad/callingCard/callingCardAppAddForm.jsp",    
			    modal: true,
			    onClose : function(){
			    	$("#dg").datagrid("reload");
			    	$row = null;
			    },
			    buttons : [ {
					text : '关闭',
					iconCls : 'icon-cancel',
					handler : function() {
						$('#addWindow').dialog('close');
						$("#dg").datagrid("reload");
					}
				}]
			});
		}
		//查看附件
		function lookAttachment(index){
			//主页面查看时，明细为申请id
			var row = this.getRowData(index);
			checkAttachementDetail(row.appNo,row.registrantNo,row.caID);
		};
		/*详情*/
		function toInfo(index){
			 var rowdata = getRowData(index);//获取选中的记录
			 if(rowdata==null){
					$.messager.alert("提示","请选择至少一条记录!","warning");
			 }else{
				$('#Info').dialog({    
				    title: '详情',    
				    width: 920,    
				    height: 400,    
				    closed: false,    
				    cache: false, 
				    resizable:true,
				    href: 'jsp/ad/callingCard/callingCardInfo.jsp',    
				    modal: true, 
				    onLoad:function(){
				    	$("#investProductInputOrSaveFormInfo").form('load',rowdata);
				    },
				    onClose : function(){
				    	$("#dg").datagrid("reload");
				    	rowdata=null;
				    },
				    buttons : [ {
						text : '关闭',
						iconCls : 'icon-cancel',
						handler : function() {
							$('#Info').dialog('close');
							$("#dg").datagrid("reload");
						}
					}]
				});
			 }
		}
	</script>
  </head>
  <body>
      <div data-options="region:'center',border : false">
      	<div class="position" style="margin-top: 5px;">您当前所在位置：行政办公  &gt; 名片申请</div>
		<!-- 高级查询栏区域 -->
  		<div class="well well-small" style="margin-left: 5px;margin-top: 5px">
			<form id="searchForm" action="callingCard/index.do" method="post">
				<table cellpadding="0" cellspacing="1" border="0">
					<tr>
						<td>申请状态：&nbsp;&nbsp;</td>
						<td><input name="orgId" id="orgId" type="text" class="easyui-textbox easyui-validatebox" style="width: 170px" readonly="readonly"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td>登记时间：&nbsp;&nbsp;</td>
						<td><input name="queryDateBegin" id="queryDateBeginId" class="easyui-datebox" editable="false" style="width:174px;"/></td>
						<td>至</td>
						<td><input name="queryDateEnd" id="queryDateEndId" class="easyui-datebox" editable="false" style="width:174px;"/></td>	
						<td align="right" style="float:right;text-align: right;">
						    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="false" onclick="doSearch();">执行查询</a>&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
						<td>  
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-reload" plain="false" onclick="clearAdvancedQueryConditions()">条件重置</a>
						</td>
					</tr>
					<tr>
						<td>申请时间：&nbsp;&nbsp;</td>
						<td><input name="queryApplyDateBegin" id="queryApplyDateBeginId" class="easyui-datebox" editable="false" style="width:174px;"/></td>
						<td align="center">至&nbsp;&nbsp;</td>
						<td><input name="queryApplyDateEnd" id="queryApplyDateEndId" class="easyui-datebox" editable="false" style="width:174px;"/></td>
					</tr>									
				</table>
			</form>			  			
		</div>
		

		<div id="tb" style="padding:2px 0">
			<table cellpadding="0" cellspacing="0">
				<tr>
					<td>
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="toAdd();">添加</a>
					</td>
				</tr>
			</table>
		</div>
		<!-- 名片申请数据表格区域 -->
		<table id="dg"  width="100%"></table>
		<!-- 查看附件弹框 -->
		<div id="queryaccessory"></div>
		<!-- 新增弹框 -->
		<div id="addWindow"></div>
		<div id="imageDialog"  class="easyui-dialog" title="流程图片" data-options="border:false,closed:true,fit:true">
			<img id="image" src="" >
		</div>
		<!-- 查看历史审批意见弹框 -->
		<div id="optionsDialog"></div>
		<!-- 详情弹框 -->
		<div id="Info"></div>
  	</div>	
  </body>
</html>
