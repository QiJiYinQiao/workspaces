<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String loanOrderId = request.getParameter("loanOrderId");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="<%=basePath%>">
<title>稽核信息详情</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<jsp:include page="../../../layout/script.jsp"></jsp:include>
</head>
<style type="text/css">
	table {
		text-align:center;
		border-left: 1px solid black;border-top: 1px solid black;
	}
	table td{
		border-right: 1px solid black; border-bottom: 1px solid black;
	}
	table th{
		border-right: 1px solid black; border-bottom: 1px solid black;
	}
</style>
<body>
	<script type="text/javascript">
		$(function(){
			var tableStr = "";
			$.ajax({
				url:"auditInfoRecord/auditInfoRecordAction!findAuditInfoDetailByOid.action",
				data:{"loanOrderId":'<%=request.getParameter("loanOrderId")%>'},
				type:"post",
				success:function(data){
					console.info(data);
					if(data){
						tableStr += "<tr><th>姓名</th><th>联系方式</th><th>稽核对象</th><th>电核</th><th>网核</th><th>操作</th></tr>";
						$.each(data,function(i,item){
							tableStr += "<tr>"
							tableStr += "<td><div style='width:80px;text-align:center;'>"+item.name+"</div></td><td><div style='width=100px;border:none;'>"+item.contactMethod+"</div></td><td><div style='width=120px;border:none;'>"+item.auditItemName+"</div></td>";
							tableStr += "<td><textarea readonly='readonly' style='width:260px;height:80px;resize:none;border:none;'>"+item.phoneAuditRecord+"</textarea></td>";
							tableStr += "<td><textarea readonly='readonly' style='width:260px;height:80px;resize:none;border:none;'>"+item.webAuditRecord+"</textarea></td>"
							tableStr += "<td><a style='width:120px;' href='javascript:void(0);' onclick=\"checkAttachment('"+item.auditId+"')\">查看附件</a></td>";
							tableStr += "</tr>"; 
						}); 
						$(tableStr).appendTo("#auditReportDetailTable");
					}else{
						$("#noMessage").css("display","block");
					}
				}
			});
		});
		
		function checkAttachment(auditId){
			var loanOrderId = $("#loanOrderId").val();
			checkAttachementDetail(auditId,loanOrderId,'','1');
			/* $.ajax({
				url:"attachment/attachmentAction!findAttachmentByAuditId.action",
				data:{"auditId":auditId},
				type:"post",
				success:function(data){
					console.info(data);
				}
			}); */
		}
	</script>
	<div style="width:100%;height:100%;overflow:auto;text-align: center;">
		<input type="hidden" id="loanOrderId" value="<%=loanOrderId%>">
		<div id="noMessage" style="width: 100%;height:300px;text-align: center;padding-top:250px;display: none;">
			<font size="6" style="font-weight: bold;box-shadow: 3px 3px 5px 3px;">
				暂无稽核信息
			</font>
		</div>
		<table cellpadding="5px;" id="auditReportDetailTable" style="width:100%;">
			
		</table>
	</div>
</body>
</html>