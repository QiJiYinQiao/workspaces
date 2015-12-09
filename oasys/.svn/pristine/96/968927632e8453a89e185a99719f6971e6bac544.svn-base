<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String attId = (String)request.getParameter("attId");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<base href="<%=basePath%>">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=basePath %>js/openoffice/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/openoffice/flexpaper.js"></script>
<script type="text/javascript" src="<%=basePath %>js/openoffice/flexpaper_handlers.js"></script>
<script type="text/javascript"> 
	$(function(){
		$.ajax({
			type:'POST',
			url:'openOfficeController/openOffice.do',
			data:{'attachmentId':'<%=attId%>'},
			success:function(data){
				$("#onlineShow").empty();
				// 文档不存在/转换时异常
				if(data && !data.status){
					$.messager.alert(data.title,data.message,"error");
					return;
				}
				// 在线预览图片
				if(data && data.status && data.data.fileType=='0'){
					imageViewer(data.data.filePath);
					return;
				}
				// 在线支持文档
				if(data && data.status && data.data.fileType=='1'){
					documentViewer(data.data.filePath);
					return;
				}
				// 其他类型，不支持在线预览
				if(data && data.status && data.data.fileType=='2'){
					otherViewer();
					return;
				}
			}
		});
		
	});
	
	// 在线预览文档
	function documentViewer(filePath){
	  $("#onlineShow").append("<div id='documentViewer' class='flexpaper_viewer' style='width:1500px;height:1000px'></div>");
	  var startDocument = "Paper";
        $('#documentViewer').FlexPaperViewer(
               { config : {
                   SWFFile :escape(filePath),
                   Scale : 0.6,
                   ZoomTransition : 'easeOut',
                   ZoomTime : 0.5,
                   ZoomInterval : 0.2,
                   FitPageOnLoad : true,
                   FitWidthOnLoad : false,
                   FullScreenAsMaxWindow : false,
                   ProgressiveLoading : false,
                   MinZoomSize : 0.2,
                   MaxZoomSize : 5,
                   SearchMatchAll : false,
                   InitViewMode : 'Portrait',
                   RenderingOrder : 'flash',
                   StartAtPage : '',

                   ViewModeToolsVisible : true,
                   ZoomToolsVisible : true,
                   NavToolsVisible : true,
                   CursorToolsVisible : true,
                   SearchToolsVisible : true,
                   WMode : 'window',
                   localeChain: 'zh_CN'
               }}
        );
	}
	
	// 预览图片
	function  imageViewer(filePath){
	  $("#onlineShow").append("<img id='image'>");
	  $("#image").attr("src",filePath);
	}
	
	// 其他不支持在线预览的类型
	function otherViewer(){
		  $("#onlineShow").append("<span><font id='main' size='8' style='font-weight: bold;box-shadow: 3px 3px 5px 3px;position:absolute;'>此类型附件不支持在线预览！！</font></span>");
		// 获取浏览器窗口 
			var windowScreen = document.documentElement; 
			// 获取main的font元素 
			var main_div = document.getElementById("main"); 
			// 通过窗口宽高和div宽高计算位置 
			var main_left = (windowScreen.clientWidth - main_div.clientWidth)/2 + "px"; 
			var main_top = (windowScreen.clientHeight - main_div.clientHeight)/2 + "px"; 
			// 位置赋值 
			main_div.style.left = main_left; 
			main_div.style.top = main_top; 
	}
</script>  
<style type="text/css" media="screen"> 
	html, body	{ height:100%; }
	body { margin:0; padding:0; overflow:auto; }   
	#flashContent { display:none; }
</style> 
<title>文档在线预览系统</title>
</head>
<body> 
	<div id="onlineShow" style="position:absolute;text-align: center;width:100%;height:100%;" ></div>
</body>
</html>