<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:forEach items="${userList}" var="user" varStatus="status">
		${user.id}
		${user.name}
	</c:forEach>
	===============================================================<br />
	${user_info.id}${user_info.name}<br />
	===============================================================<br />
	<a href="${pageContext.request.contextPath}/user/insert_user.do">添加一条会员信息</a><br />
	${success}
</body>
</html>