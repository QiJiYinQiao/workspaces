<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>主页面</title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<jsp:include page="../layout/script.jsp"></jsp:include>
	<script type="text/javascript" src="extend/jquery.portal.js"></script>
  	<link rel="stylesheet" href="extend/portal.css" type="text/css"></link>
	<script type="text/javascript">
	$(function(){
		// 渲染任务提示窗
			$('#taskMessagePanel').panel({    
		  	  width:600,    
		  	  height:400,    
		  	  title: '待办任务', 
		  	  iconCls:'icon-tasktodo',
		  	  collapsible:true
	  	}); 
			$('#messagePanel').panel({    
		  	  width:600,    
		  	  height:400,    
		  	  title: '通知公告', 
		  	  iconCls:'icon-tasktodo',
		  	  collapsible:true
	  	}); 
			$('#myAppPanel').panel({    
		  	  width:600,    
		  	  height:400,    
		  	  title: '我的申请', 
		  	  iconCls:'icon-tasktodo',
		  	  collapsible:true
	  	}); 
			$('#sharePanel').panel({    
		  	  width:600,    
		  	  height:400,    
		  	  title: '共享文档', 
		  	  iconCls:'icon-tasktodo',
		  	  collapsible:true
	  	}); 
			//查询oa待办任务个数及分类
  			$.ajax({
	  			   url: "workFlowTaskAction/getProcNameList.do",
	  			   type: "post",
	  			   success: function(data){
	  				   console.info(data)
	  				   var taskOACount=0;
	  				   var sortTaskHtml="";
	  				   for(var i=1;i<data.length;i++){
	  					 	taskOACount+=data[i].taskCount;
	  					 	sortTaskHtml+=""+data[i].processName+""+"<font style='font-weight: bold;font-size: 15px;' color='red'>(<a style=\"color:blue;text-decoration:none;\" onmouseover=\"this.style.color='red'\" onmouseout=\"this.style.color='blue'\" href=\"javascript:void(0)\" onclick=\"doTakFunc('"+data[i].listURL+"','"+data[i].processName+"','"+data[i].processKey+"')\">"+data[i].taskCount+"</a>)</font>个"+"<br/>";
	  				   }
	  				 $("#taskOA").html(taskOACount);
	  				$("#sortTask").html(sortTaskHtml);
	  			   }
	  			});
	})
	
		function doTakFunc(url,processName,processKey){
 		 		var taskURL = url;
 		 		if(taskURL == ''){
 		 			alert('请选择待办任务种类');
 		 		}else{
 		 			var effort="";
 		 			if(taskURL.indexOf("?")>-1){
 	 		 		 	effort = processName+ "任务||" + "" + "||" +taskURL+"&key="+processKey ;
 		 			}else{
 		 				effort = processName+ "任务||" + "" + "||" +taskURL+"?key="+processKey ;
 		 			}
 		 			// 加载Tab页卡
 					parent.addTab(effort);
 		 		}

 		 	}
	</script>
  </head>
  <body>
	<div style="margin-left: 30px;margin-top: 10px;float:left" >   
	    <div id="taskMessagePanel">
	    <ul>
	    <li id="investTaskMessage">
					<span style="font-size: 10px;font-weight: bold;">*OA待办任务</span>
					<dl>
						<dt>
							到目前为止您的<font style="font-weight: bold;font-size: 15px;" color="blue">当前任务</font>共有:
							<font id="taskOA" style="font-weight: bold;font-size: 20px;margin: 10px;" color="red">10</font>个。
							其中：<br/>
							<font id="sortTask"></font>
							
						</dt>
					</dl>
		</li>
	    </ul>
	    </div>
	   </div>
     <div style="margin-left: 30px;margin-top: 10px; float:left">
	    <div id="messagePanel">
	    </div>
	  </div>
	  <div style="margin-left: 30px;margin-top: 10px; float:left">
	    <div id="myAppPanel">
	    </div>
	   </div>
	   <div style="margin-left: 30px;margin-top: 10px;float:left">
	    <div id="sharePanel">
	    </div>
	</div>  
</body>