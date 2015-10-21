<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登陆首页</title>
	<link rel="stylesheet" type="text/css" href="media/css/easyui.css">
	<link rel="stylesheet" type="text/css" href="media/css/icon.css">
	<link rel="stylesheet" type="text/css" href="media/css/demo.css">
	<link rel="stylesheet" href="media/zTree/css/demo.css" type="text/css">
	<link rel="stylesheet" href="media/zTree/css/zTreeStyle/zTreeStyle.css"
	type="text/css">
	<script type="text/javascript" src="media/js/jquery.min.js"></script>
	<script type="text/javascript" src="media/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="media/zTree/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="media/zTree/js/jquery.ztree.excheck-3.5.js"></script>
	<script type="text/javascript" src="media/zTree/js/jquery.ztree.exedit-3.5.js"></script>
</head>

<script type="text/javascript">
	var setting = {
		async : {
			enable : true,
			url: "module/findAllModule.html",//异步获取json格局数据的路径
			autoParam : [ "code", "name=n", "level=lv" ], //异步加载时需要自动提交父节点属性的参数，多个用逗号分隔
			otherParam : {
				"otherParam" : "zTreeAsyncTest"
			}
		},
		/* check: {
			enable: true
		},	 */	
		data: {
			simpleData: {
				enable: true,
				idKey : "code",
				pIdKey : "parent",
				rootPId : 0
			},
			key: {
				name : "name"
	        }
		}
	};

	$(document).ready(function() {
		$.fn.zTree.init($("#treeDemo"), setting);
	});
</script>
</head>
<body>
	<div class="zTreeDemoBackground left">
		<ul id="treeDemo" class="ztree"></ul>
	</div>
	
</body>
</html>
</html>