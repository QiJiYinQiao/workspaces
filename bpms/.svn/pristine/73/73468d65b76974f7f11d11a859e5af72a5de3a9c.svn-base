<%@page import="com.bpms.shiro.ShiroUser"%>
<%@page import="com.bpms.util.Constants"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
ShiroUser user = Constants.getCurrendUser();
session.setAttribute("user", String.valueOf(user.getUserId()));
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
	// websocket对象
	var websocket;
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
	
	// 初始化菜单
	function initMenu(){
			$("#menuTree").tree({
					url : 'systemAction!findAllFunctionList.action',
					method : 'get',
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
							// 点击代办任务的菜单的时候将任务的个数清零
							if(node.id=='168'|| node.id=='169'|| node.id=='151'||node.id=='152'){
								removeMessage(node);
								toTaskIdsEmpty(node);
							}
							// 如果为代办任务和受理任务时，根据ID区分Tab页卡的名称，防止重复
							var nodeText = node.text;
							if(node.id=='168'|| node.id=='169'){
								nodeText +="(贷款)"; 
							}else if(node.id=='151'||node.id=='152'){
								nodeText +="(财富)";
							}
							var effort = nodeText + "||" + node.iconCls+ "||" + node.attributes.url;
							// 加载Tab页卡
							addTab(effort);
						}
					}
				});
	}
	
	// 装载新任务的列表
	var unClaimLoanTaskIds = [];
	var claimLoanTaskIds = [];
	var unClaimInvestTaskIds = [];
	var claimInvestTaskIds = [];
	//初始话WebSocket
	function initWebSocket() {
		websocket = new WebSocket(encodeURI('ws://'+window.location.host+'/bpms/message'));
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
				if (message.dataType== 'unClaimLoanOrder') {//接受贷款代办任务的提示信息
	                handleTaskIds(unClaimLoanTaskIds,message);
	                showMessage(unClaimLoanTaskIds.length,$("#menuTree").tree('find',"168"));
	         	}else if (message.dataType== 'claimLoanOrder') {// 接受贷款受理人的提示信息
	               	handleTaskIds(claimLoanTaskIds,message);
	                showMessage(claimLoanTaskIds.length,$("#menuTree").tree('find',"169"));
	         	}else if (message.dataType== 'unClaimInvestOrder') {//接受财富代办任务的提示信息
	         		handleTaskIds(unClaimInvestTaskIds,message);
	                showMessage(unClaimInvestTaskIds.length,$("#menuTree").tree('find',"151"));
                }else if(message.dataType== 'claimInvestOrder') {// 接受财富受理任务的提示信息
                	handleTaskIds(claimInvestTaskIds,message);
	                showMessage(claimInvestTaskIds.length,$("#menuTree").tree('find',"152"));
                }
			}
		}
	};
	
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
		if(menu.id=="168"){
			unClaimLoanTaskIds = [];
		}else if(menu.id=="169"){
			claimLoanTaskIds = [];
		}else if(menu.id=="151"){
			unClaimInvestTaskIds = [];
		}else if(menu.id=="152"){
			claimInvestTaskIds = [];
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
	
	.show_message{
	        background-color: #ff0000;
		    width: 28px;
		    height: 18px;
		    border-radius: 40%;
		    line-height: 20px;
		    color: #fff;
		    text-align: center;
		    border-top: 0;
		    position: absolute;
		    margin-left: 140px;
		    margin-top: -18px;
         }
	
	</style>
  </head>
 <body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:71px; padding:10px;overflow: hidden;"  href="layout/north.jsp"></div>
	<div data-options="region:'west',split:true,title:'导航菜单'" style="width:200px;">
			<ul id="menuTree"></ul>
	</div> 
	<div data-options="region:'south',border:false" style="height:25px;background:#EEE;padding:5px;" href="layout/south.jsp"></div>
	<div data-options="region:'center',plain:true,title:'欢迎来到钱钱金融风控管理系统'" style="overflow: hidden;"  href="layout/center.jsp"></div>
<%--	<jsp:include page="user/loginAndReg.jsp"></jsp:include>--%>
</body>
</html>
