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
		
		
		var taskOATaskIds=[];
		//初始话WebSocket
		function initWebSocket() {
			websocket = new WebSocket(encodeURI('ws://'+window.location.host+'/oasys/message'));
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
					/*************************************菜单消息提示*************************************/
					if (message.dataType== 'taskOA') {//接受oa任务管理的提示信息
	                handleTaskIds(taskOATaskIds,message);
	                showMessage(taskOATaskIds.length,$menuTree.tree('find',"180"));
	                showTotalTaskOACount(message);
                    }
					/*************************************菜单消息提示*************************************/
				}
			}
		}
		
		// 计算当前任务的个数
		function handleTaskIds(taskIds,message){
			// 如果订单id为空不进行任何操作
			if(message.taskId==null || message.taskId=="") return;
			
			// 如果为空则默认返回0
			if(message.calculateType == null || message.calculateType =="") return;
			
			if(message.calculateType =="add"){// 任务增加
				taskIds.push(message.taskId);
			}else if(message.calculateType =="subtract"){// 任务减少
				// 如果为空则直接返回
				if($.isEmptyObject(taskIds)) return;
				var index = $.inArray(message.taskId,taskIds);
				if(index!=-1){
					taskIds.splice(index,1);
				}
			}
		}
		
		// 显示任务个数
		function showMessage(messageCount,menu){
			removeMessage(menu);
			if(messageCount>0){
				$(menu.target).append("<div class='show_message'>"+messageCount+"<div>");
			}
		}
		
		// 隐藏任务的个数
		function removeMessage(menu){
			$(menu.target).find(".show_message").remove();
		}
		
		// 根据点击菜单将任务的个数至为零
		function toTaskIdsEmpty(menu){
			if(menu.id=="180"){
				taskOATaskIds = [];
			}
		}
		
		// 渲染OA的任务提示的总个数
		function showTotalTaskOACount(message){
	        $("#taskIframePage").contents().find("#taskOA").html(message.taskOACount);
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
	<div data-options="region:'south',border:false" style="height:25px;background:#EEE;padding:5px;" href="layout/south.jsp"></div>
	<div data-options="region:'center',plain:true,title:'欢迎来到【钱钱金融】OA办公系统'" style="overflow: hidden;"  href="layout/center.jsp"></div>
<%--	<jsp:include page="user/loginAndReg.jsp"></jsp:include>--%>
</body>
</html>
