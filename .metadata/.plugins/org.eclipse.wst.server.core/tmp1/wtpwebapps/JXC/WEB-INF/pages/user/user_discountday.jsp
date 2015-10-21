<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>登陆首页</title>
	<link rel="stylesheet" type="text/css" href="../media/css/easyui.css">
	<link rel="stylesheet" type="text/css" href="../media/css/icon.css">
	<link rel="stylesheet" type="text/css" href="../media/css/demo.css">
	<script type="text/javascript" src="../media/js/jquery.min.js"></script>
	<script type="text/javascript" src="../media/js/jquery.easyui.min.js"></script>
	<style type="text/css">
		td{
			font-size:16px;text-align: center;
		}
		input{
			border: 1px solid #95b8e7;border-radius: 5px;height: 20px;font-size:16px;width:100px;
		}
	</style>
</head>
<body class="easyui-layout">
	<div data-options="region:'center',title:'修改优惠天数'" scrolling="no" style="overflow:hidden;background-color:#F8F8FF;">
			<!-- <input id="discountday" name="discountday" value=""> -->
			<form id="discountdayform">
				<table>
					<tr><td>舱位等级</td><td>优惠天数</td></tr>
					<c:if test="${list!=null }">
						<c:forEach items="${list }" var="lists">
							<tr><td>${lists.ITEM_TEXT }</td><td><input name="desc"  value="${lists.DESCRIPTION }"><input name="value" style="display: none;" value="${lists.ITEM_VALUE }"></td></tr>
						</c:forEach>
					</c:if>
				</table>
			</form>
			<!-- <button style="float: right;" onclick="userdiscountday()">提交</button> -->
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="userdiscountday()" style="width: 56px;float: right;margin-right: 20px;">提交</a>
	</div>
	<script type="text/javascript">
		function userdiscountday(){
			//var discountday = $("#discountday").val();
			jQuery.ajax({
				url:"discountday",
				type:"post",
				data:$("#discountdayform").serialize(),
				error:function(){},
				success:function(data){
					if(data==1){
						alert("修改成功!")
					}else{
						alert("修改失败!");
					}
				}
			});
			
		}
	</script>
</body>
 
</html>