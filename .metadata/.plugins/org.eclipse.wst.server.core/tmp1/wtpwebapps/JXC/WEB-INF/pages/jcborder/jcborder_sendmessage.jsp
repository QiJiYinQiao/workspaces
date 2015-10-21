<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>发送短信详情</title>
	<link rel="stylesheet" type="text/css" href="../media/css/easyui.css">
	<link rel="stylesheet" type="text/css" href="../media/css/icon.css">
	<link rel="stylesheet" type="text/css" href="../media/css/demo.css">
	<script type="text/javascript" src="../media/js/WdatePicker.js"></script>
	<script type="text/javascript" src="../media/js/jquery.min.js"></script>
	<script type="text/javascript" src="../media/js/jquery.easyui.min.js"></script>
	<style type="text/css">
		input{
			border: 1px solid #95b8e7;border-radius: 5px;height: 20px;background-color: rgb(240,248,255);
		}
		td{
			font-size: 16px;
		}
		
		 .container{
                position:relative;
        }
        table{border-collapse:collapse;border-spacing:0;}
        td{padding:5px;margin:5px;} 
        table tr td input{
        	border: 1px solid #95b8e7;border-radius: 5px;background-color: rgb(240,248,255);
        	readonly:readonly;
        }
	</style>
	<style>
		.lightbox{width:300px;background:#FFFFFF;border:1px solid #ccc;line-height:25px; top:20%; left:20%;}
		.lightbox dt{background:#f4f4f4; padding:5px;}
	</style>
</head>
<body class="easyui-layout" >
	<div class="indiv" style="height:100%;width:100%;background-color: #F8F8FF;overflow: hidden;" >
		<table align="center" width="100%" style="margin-left: 20px;margin-top: 40px;">
			<tr>
				<td align="right" width="17%">手机：</td>
				<td><input type="text" id="phone" name="phone" value="${phone}" width="100"></input></td>
			</tr>
			<tr>
				<td align="right">内容：</td>
				<td><textarea rows="7" id="content" cols="40" style="font-size: 15px;">${content}</textarea></td>
			</tr>
			<tr>
				<td align="right"></td> 
				<td align="left">
					<a onclick="confirmEdit();" class="easyui-linkbutton"  style="margin-right: 20px;margin-top:10px;width: 60px;height: 25px;float: none;">确认</a>
					<a onclick="cancelEdit();" class="easyui-linkbutton" style="margin-left:60px;margin-right: 20px;margin-top:10px;width: 60px;height: 25px;float: none;">取消</a>
					
				</td>
			</tr>
			
		</table>
		
	</div>
			
		<script type="text/javascript">
			function confirmEdit(){
					var phone = $("#phone").val();
					var content = $("#content").val();
					jQuery.ajax({
						url:"sendMessage",
						type:"post",
						data:{phone:phone,content:content},
						error:function(){},
						success:function(data){
							//alert(data);
							if(data==1){
								alert("发送成功!");
								parent.$("#send").dialog("close");
								parent.$("#jcbOrderTable").datagrid("reload");
							}else{
								alert("发送失败!");
							}
						}
					})
			} 
			function cancelEdit(){
				parent.$("#send").dialog("close");
			}
		</script>
</body>
</html>