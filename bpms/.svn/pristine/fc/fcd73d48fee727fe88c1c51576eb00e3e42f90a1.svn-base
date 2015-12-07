	<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
	<%
		String easyuiThemeName="metro";
		Cookie cookies[] =request.getCookies();
		if(cookies!=null&&cookies.length>0){
			for(Cookie cookie : cookies){
				if (cookie.getName().equals("cookiesColor")) {
					easyuiThemeName = cookie.getValue();
					break;
				}
			}
		}
	%>
	<link rel="stylesheet" type="text/css" href="themes/<%=easyuiThemeName %>/easyui.css">
	<script type="text/javascript" src="js/xheditor/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="js/xheditor/xheditor-1.1.14-zh-cn.min.js" charset="utf-8"></script>
	<link rel="stylesheet" type="text/css" href="themes/icon.css">
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="js/jqueryUtil.js"></script>
	<script type="text/javascript" src="js/json2.js"></script>
	<script type="text/javascript" src="js/ajaxfileupload.js"></script>
	<script type="text/javascript" src="js/FusionCharts/FusionCharts.js"></script>
	<script type="text/javascript" src="js/FusionCharts/FusionCharts.jqueryplugin.js"></script>
	<script type="text/javascript" src="js/FusionCharts/FusionChartsExportComponent.js"></script>
	<script type="text/javascript" src="js/validate.js"></script>
	<script type="text/javascript" src="js/common.js"></script>	
	<script type="text/javascript" src="js/WdatePicker/WdatePicker.js"></script>	
	<style type="text/css">
		.linkSpan{
		  padding:5px;
		  display:-moz-inline-box;
		  display:inline-block;
		  width:40%; 
		  text-align: center;
		}
		.linkSpanS{
		  padding:5px;
		  display:-moz-inline-box;
		  display:inline-block;
		  width:10%; 
		  text-align: center;
		}
		a{text-decoration: none;}
		a:hover {
		 color: #FF0000;
		}
		textarea{resize: none;}
	</style>
	<script type="text/javascript">
		var attempData;
		$(function(){
               $.ajax({
            	   url:"common/commonAction!findTextArr.action?codeMyid=attachment_type",
            	   type:"post",
            	   success:function(data){
            		   attempData = data;           		   
            	   }
               });
            });
		
		var j = 0;
		//文件上传对话框  连个参数：附件类型，订单ID
		function fileUploadsDlg(attType,loanOrderId,auditId){
			if(attType==""){
				$.messager.alert("提示","请先选择附件类型!","info");
				return false;
			}
			if(undefined==auditId){
				auditId = '';
			}
			window.open('jsp/loanOrder/loanOrderAttachmentForm.jsp?loanOrderId='+loanOrderId+'&attType='+attType+'&auditId='+auditId,
					"附件详情");
			//, "height="+$(window).height()*0.95+", width="+$(window).width()*0.95+", top=100, left=200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no"
		}
		
		//附件详情对话框 四个参数 ：稽核信息ID，订单ID，用户ID，是否是详情 1
		function checkAttachementDetail(auditId,loanOrderId,userId,isDetail){
			if(undefined==userId){
				userId = '';
			}
			if(undefined==isDetail){
				isDetail=='';
			}
			window.open('jsp/loanOrder/letter/attachementDetail.jsp?auditId='+auditId+'&loanOrderId='+loanOrderId+'&userId='+userId+'&isDetail='+isDetail,
					"附件详情");
			//, "height="+$(window).height()+", width="+$(window).width()*0.95+", top=100, left=200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no"
		}
		
		
		//附件详情对话框 四个参数 ：稽核信息ID，订单ID，用户ID，是否是详情 1
		function checkAttachementDetail4InvestOrder(auditId,investOrderId,userId,isDetail){
			if(undefined==userId){
				userId = '';
			}
			if(undefined==isDetail){
				isDetail=='';
			}
			window.open('jsp/investOrder/attachementDetail.jsp?auditId='+auditId+'&investOrderId='+investOrderId+'&userId='+userId+'&isDetail='+isDetail,
					"附件详情");
			//, "height="+$(window).height()+", width="+$(window).width()*0.95+", top=100, left=200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no"
		}		
				
		// 下载附件（投资业务相关的）
		function downloadAttachment4InvestOrder(attId){
			downFileByFormPost("attachment/attachmentAction!downloadAttachment4InvestOrder.action", {"attId":attId});
		}		
		
		//删除一条附件（投资业务相关的）
		function deleteAttachment4InvestOrder(obj,attId){
			$.messager.confirm('提示','确认删除此项?',function(r){   
			    if (r){   
			    	$.ajax({
				    	   url:'attachment/attachmentAction!delAttachment.action',
				    	   data:{"attId":attId},
				    	   type:"post",
				    	   success:function(data){
				    		   $.messager.alert("提示","删除成功!");
				    		   var pDiv = $(obj).parent().parent().children();
				    		   if(pDiv.length==3){
				    			   $(obj).parent().parent().empty();
				    		   }else if(pDiv.length==4){
				    			   $(obj).parent().parent().children("div:eq(1)").empty();
				    			   $(obj).parent().remove();
				    		   }else{
				    			   $(obj).parent().remove();
				    		   }
				    		  
				    	   },
				    	   error:function(){
				    		   $.messager.alert("提示","删除失败!");
				    	   }
				       });
			    }   
			});  
		}		
		
	</script>
	<style type="text/css">
		body {
		    font-family:helvetica,tahoma,verdana,sans-serif;
		    font-size:13px;
		    margin:0px 0px 0px 0px;
		    padding:0px 0px 0px 0px;
		}
	</style>