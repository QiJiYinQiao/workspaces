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
		//增加一条同类型附件
		function addACredential(obj){
			j++;
			var pDiv = obj.parentElement;
			var pDivId = pDiv.id.split("_")[0];
			$(pDiv.parentElement).append("<div id='"+pDivId+"_"+j+"'>"+$(pDiv).html()+"</div>");
			$("#"+pDivId+"_"+j+" input:first").combobox({
				valueField : 'code',
				textField : 'text',
				data : attempData,
				editable : false,
		    });
			var fileId = $("#"+pDivId+"_"+j+" input:last").attr("id");
			$("#"+pDivId+"_"+j+" input:last").attr("id",fileId+""+j);
			$("#"+pDivId+"_"+j+" span:eq(3)").remove();
		}
		
		//删除一条同类型附件
		function removeACredential(obj){
			var pDiv = obj.parentElement;
			var pDivid = pDiv.id;
			var ppDiv = $(obj).parent().parent();
			if(ppDiv.children().length==1){
				$.messager.alert("提示","最后一条无法删除!","info");
				return false;
			}
			$(pDiv).remove();
		}
		
		//删除一条附件
		function deleteAttachment(obj,attId){
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
		
		//上传附件方法
		function fileUploads(obj,loanOrderId){
			if(loanOrderId==undefined){
				loanOrderId = $row.loanOrderId;
			}
			var id = $(obj).parent().prev().children().attr("id");
			var listId = $(obj).parent().prev().prev().attr("id");
			var cDivClone = $("#"+id).children("div:first");
			var auditFormId = $("#"+id).parent().parent().attr("id");
			var auditId = $("#"+auditFormId+" input[name='auditId']").val();
			if(""==auditId){
				$.messager.alert("提示","请填先填写稽核信息!","info");
				return false;
			}
			var cDiv = $("#"+id).children();
			var att_types = ""; //附件类型
			var fileNames = ""; //附件名
			var fileIds = [];	//附件id
			for(var i = 0 ; i < cDiv.length; i++){
				var cDivId = cDiv[i].id;
				var att_type = $("#"+cDivId+" input:first").combobox("getValue");
				var fileName = $("#"+cDivId+" input[name='fileName']").val();
				var fileId = $("#"+cDivId+" input:last").attr("id");
				var fileValue = document.getElementById(fileId).value;
				if(""==att_type || ""==fileName || ""==fileValue){
					$.messager.alert("提示","请填写完整信息","info");
					return false;
				}
				att_types  += att_type + ",";
				fileNames += fileName + ",";
				fileIds[i] = fileId;
			}
			parent.$.messager.progress({
				title : '提示',
				text : '文件上传中，请稍后....'
			});
			$.ajaxFileUpload({
				url:'attachment/attachmentAction!saveAttachment.action',
				data:{"fileName":fileNames,"attType":att_types,"loanOrderId" : loanOrderId,"auditId":auditId},
				fileElementId:fileIds,
				secureuri:false,
				dataType:'text',
				async : false,
				success:function(data,status){
					parent.$.messager.progress('close');
					loadAttachmentList(listId,auditId,loanOrderId);
					data = $.parseJSON(data);
					$.messager.alert("提示",data.message,"info");
					$("#"+id).empty().append(cDivClone);
					$(cDivClone).children("a:first").click();
					$(cDivClone).remove();
				},
				error: function(){
					
				}
			});
		}
		
		//加载附件列表 
		function loadAttachmentList(listId,auditId,loanOrderId){
			$("#"+listId).empty();
			var str = "<div id='firstDiv"+listId+"' style='width:50%;height:30px;float: left;'><span class='linkSpan'>附件名称</span></a><span class='linkSpan'>附件类型</span><span class='linkSpanS'>操作</span></div><div id='secondDiv"+listId+"' style='width:50%;height:30px;float: left;'><span class='linkSpan'>附件名称</span></a><span class='linkSpan'>附件类型</span><span class='linkSpanS'>操作</span></div>";
			$("#"+listId).append(str);
			$.ajax({
				url : "attachment/attachmentAction!findAttachmentByULA.action",
				data : {"orderType":"borrowerOrder","auditId":auditId,"loanOrderId":loanOrderId},
				type : "post",
				async : false,
				success : function(data){
					if(data.length==0){
						$("#firstDiv"+listId).empty();
						$("#secondDiv"+listId).empty();
					}else if(data.length==1){
						$("#secondDiv"+listId).empty();
					} 
					if(data){
						var attId;
						var attName;
						var attType;
						var linkStr = "";
						var j = 0;
						$.each(data,function(i,item){
							attId = data[i].attId;
							attName = data[i].attName;
							attType = data[i].attType;
							attTypeName = data[i].attTypeName;
							linkStr = "<div style='width:50%;height:30px;float: left;'><input type='hidden' name='attId' value='"+attId+"' /><a target='_blank' href='jsp/openoffice/documentView.jsp?attId="+attId+"'><span class='linkSpan'>"+attName+"</span></a><span class='linkSpan'>"+attTypeName+"</span><a href='javascript:void(0);' class='attachBackLinkButton' onclick=\"deleteAttachment(this,'"+attId+"');\">删除</a><a href='javascript:void(0);' class='attachBackLinkButton' onclick=\"downloadAttachment('"+attId+"');\">　下载</a></div>";
							$("#"+listId).append(linkStr);
						});
					}
				},
				error : function(){
					
				}
			});
		}	
		
		//上传文件大小控制
		function fileChange(target){  
		    var ext,idx;   
		    //检测上传文件的大小        
		    var isIE = /msie/i.test(navigator.userAgent) && !window.opera;  
		    var fileSize = 0;           
		    if (isIE && !target.files){       
		        var filePath = target.value;       
		        var fileSystem = new ActiveXObject("Scripting.FileSystemObject");          
		        var file = fileSystem.GetFile (filePath);       
		        fileSize = file.Size;
		        //alert("wenjiandaxiaoweni"+fileSize/1024+"kb");      
		    } else {      
		        fileSize = target.files[0].size;  
		        //alert("wenjiandaxiaowei"+fileSize/1024+"kb");        
		    }     
		
		    var size = fileSize / 1024*1024;   
		
		    if(size>(1024*1024*15)){    
		        //alert("文件大小不能超过15MB,请重新选择文件!");  
		        $.messager.alert("提示","文件大小不能超过15MB,请重新选择文件!","info");
		        $(target).val("");
		    }else{
		    }    
		}   
		
		
		// 下载附件
		function downloadAttachment(attId){
			downFileByFormPost("attachment/attachmentAction!downloadAttachment.action", {"attId":attId});
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