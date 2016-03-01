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
		var width = parseInt($(window).width()) / 2 - parseInt($(window).width()* 0.07) ;
		// 渲染任务提示窗
			$('#taskMessagePanel').panel({    
		  	  width:width,    
		  	  height:400,    
		  	  title: '待办任务', 
		  	  iconCls:'icon-tasktodo',
		  	  collapsible:true
	  	}); 
			$('#myAppPanel').panel({    
		  	  width:width,    
		  	  height:400,    
		  	  title: '我的申请', 
		  	  iconCls:'icon-tasktodo',
		  	  collapsible:true
	  	}); 
			$('#messagePanel').panel({    
		  	  width:width,    
		  	  height:400,    
		  	  title: '通知公告', 
		  	  iconCls:'icon-tasktodo',
		  	  collapsible:true
	  	}); 
			$('#sharePanel').panel({    
		  	  width:width,    
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
	  				   var taskOACount=0;
	  				   var sortTaskHtml="其中：<br/>";
	  				   for(var i=1;i<data.length;i++){
	  					 	taskOACount+=data[i].taskCount;
	  					 	sortTaskHtml+=""+data[i].processName+""+"(<font style='font-weight: bold;font-size: 15px;' color='red'><a style=\"color:red;text-decoration:none;\" onmouseover=\"this.style.color='blue'\" onmouseout=\"this.style.color='red'\" href=\"javascript:void(0)\" onclick=\"doTakFunc('"+data[i].listURL+"','"+data[i].processName+"','"+data[i].processKey+"')\">"+data[i].taskCount+"</a></font>)个"+"<br/>";
	  				   }
	  				 if(taskOACount==0){
	  					sortTaskHtml=""
	  				 }
	  				$("#taskOA").html(taskOACount);
	  				$("#sortTask").html(sortTaskHtml);
	  			   }
	  			});
			//查询我的申请
  			 $.ajax({
	  			   url: "myAppController /findMyAppList.do",
	  			   type: "post",
	  			   success: function(data){
	  				   var countMyApp=0;
	  				   var myAppHtml="其中：<br/>";
		  				   for(var i in data){
		  					 countMyApp+=data[i].countSort;
		  					 myAppHtml+=""+data[i].appName+""+"(<font style='font-weight: bold;font-size: 15px;' color='red'><a style=\"color:red;text-decoration:none;\" onmouseover=\"this.style.color='blue'\" onmouseout=\"this.style.color='red'\" href=\"javascript:void(0)\" onclick=\"toMyAppSort('"+data[i].businesskeyPre+"','"+data[i].appName+"')\">"+data[i].countSort+"</a></font>)个"+"<br/>";
		  				   }
		  				 if(countMyApp==0){
		  					myAppHtml="";
		  				 }
		  				$("#countMyApp").html(countMyApp);
		  				$("#sortMyApp").html(myAppHtml);
	  			   	}
	  			}); 
	  			
	  			//查询我收到的通知
  			 $.ajax({
			   url: "noticeInfoController/findNoticeReceiveList.do?page=1&rows=10",
			   type: "post",
			   success: function(data){
				   var noticeHtml="";
				   data=data.rows;
				   if(data.length>0){
					   for(var i in data){
						    noticeHtml+="<li style='font-weight: bold;font-size: 15px;'><a href='javascript:void(0)>' onclick=\"toDetail('"+data[i].noticeNo+"');\" onmouseover=\"this.style.color='red'\" onmouseout=\"this.style.color='blue'\" style='text-decoration:none'>"+data[i].topic+"</a></li>";
						   }
				   }else{
					  noticeHtml+="暂无通知公告！";
				   }
				   
				   $("#notice").html(noticeHtml);
			   }
			}); 
	})
		//任务办理
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
	//我的申请具体分类
	function toMyAppSort(key,appName){
/* 		 var $dlg=$("<div></div>").dialog({
			title:appName,
			href:'jsp/workflow/myApp/myAppList.jsp?key='+key,
			width:800,
			height:400,
			modal:true,
			onClose:function(){
				$dlg.dialog("destroy");
			}
		})  */
		 $.ajax({
			url:'myAppController/myAppSort.do',
			type:'post',
			data:'appName='+appName,
			async:false,
			success:function(data){
				var dd =data.split("@")
				parent.addTab(appName.substring(0,appName.indexOf("申请")+2)+"||"+""+"||"+dd[0]+"?appStartDate='"+dd[1]+"'");
			}
		}) 
	}
	
	//根据通知编号查询通知详情
	function toDetail(noticeNo){
		 var row;
		 $.ajax({
			   url: "noticeInfoController/getModelByNoticeNo.do",
			   type: "post",
			   data:"noticeNo="+noticeNo,
			   async:false,
			   success: function(data){
				   row=data;
			   }
			}); 
		var $dialog;
		$dialog=$("<div></div>").dialog({
			title:'通知详情',
			href:'jsp/pd/notice/noticeReceiveEditDlg.jsp',
			width:800,
			height:700,
			modal:true,
			onLoad:function(){
				$("#form").form('load',row);
				if(row.isNeedReceipt=='0'){//需要回执
					  if(row.isReceipted!='0'){
						  $("#receipt").show();
					  }
				  } 
				disableForm("form");
			},
			buttons : [ {
				text : '关闭',
				iconCls : 'icon-cancel',
				handler : function() {
					$dialog.dialog('destroy');
				}
			}
			]
		}); 
	}
	//查看更多通知
	function seeMore(){
		effort = "收件箱||" + "" + "||" +"jsp/pd/notice/noticeReceiveMain.jsp";
		// 加载Tab页卡
		parent.addTab(effort);
	}
	</script>
  </head>
  <body>
	<div style="margin-left: 30px;margin-top: 10px;float:left" > 
	    <div id="taskMessagePanel">
	    <ul>
	    <li>
					<span style="font-size: 10px;font-weight: bold;">*OA待办任务</span>
					<dl>
						<dt>
							到目前为止您的<font style="font-weight: bold;font-size: 15px;" color="blue">当前任务</font>共有:
							<font id="taskOA" style="font-weight: bold;font-size: 20px;margin: 10px;" color="red">10</font>个。
							<font id="sortTask"></font>
							
						</dt>
					</dl>
		</li>
	    </ul>
	    </div>
	   </div>
	 <div style="margin-left: 30px;margin-top: 10px; float:left">
	    <div id="myAppPanel">
	    <ul>
	    <li>
					<span style="font-size: 10px;font-weight: bold;">*我的申请：</span>
					<dl>
						<dt>
							您的申请共有:
							<font id="countMyApp" style="font-weight: bold;font-size: 20px;margin: 10px;" color="red">10</font>个。
							<font id="sortMyApp"></font>
						</dt>
					</dl>
		</li>
	    </ul>
	    </div>
	 </div>
     <div style="margin-left: 30px;margin-top: 10px; float:left">
	    <div id="messagePanel">
	    <ol id="notice">
	    </ol>
	    <a href="javascript:void(0)" onclick="seeMore()" target="_blank"><font style="float:right; margin-right: 50px">查看更多>></font></a>
	    </div>
	  </div>
	   <div style="margin-left: 30px;margin-top: 10px;float:left">
	    <div id="sharePanel">
	    </div>
	</div>  
</body>