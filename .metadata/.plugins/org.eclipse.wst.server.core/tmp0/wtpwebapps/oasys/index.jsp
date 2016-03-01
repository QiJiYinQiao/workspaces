<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>欢迎</title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<jsp:include page="layout/script.jsp"></jsp:include>
	<script type="text/javascript">
		$(function(){
			initMenu();
			initWebSocket();
			if (jqueryUtil.isLessThanIe8()) {
				$.messager.show({
					title : '警告',
					msg : '您使用的浏览器版本太低！<br/>建议您使用谷歌浏览器来获得更快的页面响应效果！',
					timeout : 1000 * 30
				});
			}
		});

		//初始化菜单
		
		function initMenu(){
			$("#menuTree").tree({
				url:"findMenuList.do",
				method : 'post',
				animate : true,
				onContextMenu : function(e, node) {
					e.preventDefault();
					$(this).tree('select', node.target);
					$('#mm').menu('show', {
						left : e.pageX,
						top : e.pageY
					});
				},
				onClick : function(node) {
					if (node.attributes.url != "javascript:void(0);") {
						var parent = $(this).tree("getParent",node.target);
						var effort = node.text + "||" + node.iconCls+ "||" + node.attributes.url;
						// 加载Tab页卡
						addTab(effort);
					}
				}
			});
			
		}
		
		
		//初始话WebSocket
		function initWebSocket() {
			websocket = new WebSocket(encodeURI('ws://'+window.location.host+'/message'));
			websocket.onopen = function() {
				//连接成功
				console.info("链接成功");
			}
			websocket.onerror = function() {
				//连接失败
				console.info("链接失败");
			}
			websocket.onclose = function() {
				//连接断开
				console.info("链接断开");
			}
			//消息接收
			websocket.onmessage = function(message) {
				 var message = JSON.parse(message.data);
				if(message.type=='message'){
					if (message.dataType== 'taskOA') {//接受oa任务管理的提示信息
	                showTotalTaskOACount(message.processNameList);
                    }
					if (message.dataType== 'noticeOA') {//接受oa任务管理的提示信息
	                showTotalNoticeOAList(message.infoList);
                    }
				} 
			}
		}
		
		// 渲染OA的任务提示的总个数
		function showTotalTaskOACount(data){
			if(data){
				 var taskOACount=0;
				 var sortTaskHtml="";
				   for(var i=0;i<data.length;i++){
					 	taskOACount+=data[i].taskCount;
					 	sortTaskHtml+=""+data[i].processName+""+"<font style='font-weight: bold;font-size: 15px;' color='red'>(<a style=\"color:blue;text-decoration:none;\" onmouseover=\"this.style.color='red'\" onmouseout=\"this.style.color='blue'\" href=\"javascript:void(0)\" onclick=\"doTakFunc('"+data[i].listURL+"','"+data[i].processName+"','"+data[i].processKey+"')\">"+data[i].taskCount+"</a>)</font>个"+"<br/>";
				   }
				 $("#taskOA").html(taskOACount);
				$("#sortTask").html(sortTaskHtml);
		        $("#taskIframePage").contents().find("#taskOA").html(taskOACount);
		        $("#taskIframePage").contents().find("#sortTask").html(sortTaskHtml);
			}
		}
		/**首页通知列表*/
		function showTotalNoticeOAList(data){
			if(data){
				 var noticeHtml="";
				   if(data.length>0){
					   for(var i in data){
						    noticeHtml+="<li style='font-weight: bold;font-size: 15px;'><a href='javascript:void(0)>' onclick=\"toDetail('"+data[i].noticeNo+"');\" onmouseover=\"this.style.color='red'\" onmouseout=\"this.style.color='blue'\" style='text-decoration:none'>"+data[i].topic+"</a></li>";
						   }
				   }else{
					  noticeHtml+="暂无通知公告！";
				   }
				   
				   $("#taskIframePage").contents().find("#notice").html(noticeHtml);
			}
		}
	</script>
	<style type="text/css">
	#menuAccordion a.l-btn span span.l-btn-text {
	    display: inline-block;
	    height: 14px;
	    line-height: 14px;
	    margin: 0px 0px 0px 10px;
	    padding: 0px 0px 0px 10px;
	    vertical-align: baseline;
	    width: 128px;
	}
	#menuAccordion 	a.l-btn span span.l-btn-icon-left {
	    background-position: left center;
	    padding: 0px 0px 0px 20px;
	}
	#menuAccordion .panel-body {
		padding:5px;
	}
	#menuAccordion span:focus{
		outline: none;
	}
	</style>
  </head>
 <body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:60px;padding:10px;overflow: hidden;"  href="layout/north.jsp"></div>
	<div data-options="region:'west',split:true,title:'主要菜单'" style="width:220px;">
			<div id="menuTree"></div>
	</div> 
	<div data-options="region:'south',border:false" style="height:25px;background:#EEE;padding:5px;">
		<div style="text-align: center;"><strong>Copyright © </strong>&nbsp; 钱钱金融信息服务（北京）有限公司</div>
	</div>
	<div data-options="region:'center',plain:true,title:'欢迎来到【钱钱金融】OA办公系统'" style="overflow: hidden;"  href="layout/center.jsp"></div>
<%--	<jsp:include page="user/loginAndReg.jsp"></jsp:include>--%>
</body>
</html>
