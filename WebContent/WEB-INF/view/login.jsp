<%@include file="/WEB-INF/view/common/taglibs.jspf" %>
<!DOCTYPE html>
<html>
	<head>
		<%@include file="/WEB-INF/view/common/head.jspf" %>
		<script src="/js/fbSDK.js"></script>
		<script src="/js/login.js"></script>
		<title>Login</title>
	</head>
	<body>
		<jsp:include page="/WEB-INF/view/common/header.jsp"/>
		<jsp:include page="/WEB-INF/view/loginForm.jsp"/>
		<jsp:include page="/WEB-INF/view/common/footer.jsp"/>
	</body>
</html>