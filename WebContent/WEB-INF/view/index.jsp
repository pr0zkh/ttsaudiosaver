<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@include file="/WEB-INF/view/common/header.jsp" %>
	<c:set var="helloWorldVar" value="Hell, world!" />
	${helloWorldVar}
	zzz
</body>
</html>