<%@include file="/WEB-INF/view/common/taglibs.jspf" %>
<!DOCTYPE html>
<html>
	<head>
		<%@include file="/WEB-INF/view/common/head.jspf" %>
		<title>Login</title>
	</head>
	<body>
		<script src="/js/fb-sdk.js"></script>
		<jsp:include page="/WEB-INF/view/common/header.jsp"/>
		<jsp:include page="/WEB-INF/view/loginForm.jsp"/>
		<jsp:include page="/WEB-INF/view/common/footer.jsp"/>
	</body>
</html>