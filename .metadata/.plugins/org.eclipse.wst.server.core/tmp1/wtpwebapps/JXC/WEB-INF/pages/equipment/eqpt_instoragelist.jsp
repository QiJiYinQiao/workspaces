<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>订单详情</title>
	<link rel="stylesheet" type="text/css" href="../media/css/easyui.css">
	<link rel="stylesheet" type="text/css" href="../media/css/icon.css">
	<link rel="stylesheet" type="text/css" href="../media/css/demo.css">
	<script type="text/javascript" src="../media/js/WdatePicker.js"></script>
	<script type="text/javascript" src="../media/js/jquery.min.js"></script>
	<script type="text/javascript" src="../media/js/jquery.easyui.min.js"></script>
</head>
<body class="easyui-layout" >
	<div class="easyui-dialog" modal="true" title="卡号列表"  style="width:400px;height:600px;overflow:auto;" >
		<form id="ruku" method="post" style="font-size: 16px;text-align: center;">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="ruku()" style="width: 70px;float: right;">确认入库</a>
		   <table>
				<tr><td width="80px;">序号</td><td width="320px;">卡号</td></tr>
				<c:if test="${equipmentnolist!=null }">
					<c:forEach items="${equipmentnolist }" var="quptlist" varStatus="status">
						<tr><td>${status.index+1 }</td><td><input name="eqptlist" value="${quptlist }" readonly="readonly" style="background-color: #F8F8FF;border: 0px solid #95b8e7;border-radius: 5px;height: 20px;"></td></tr>
					</c:forEach>
				</c:if>
			</table>
		</form>
	</div>
	<script type="text/javascript">
		function ruku(){
			jQuery.ajax({
				url:"eqptruku",
				data:$("#ruku").serialize(),
				type:"post",
				error:function(){},
				success:function(data){
					alert("入库成功!");
					window.location = "instorage?t="+new Date();
				}
			})
		}
	</script>
</body>
</html>