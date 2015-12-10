<%@include file="/WEB-INF/view/common/taglibs.jspf" %>
<!DOCTYPE html>
<html>
	<head>
		<%@include file="/WEB-INF/view/common/head.jspf" %>
		<title>Text To Speech</title>
	</head>
	<body>
		<jsp:include page="/WEB-INF/view/common/header.jsp"/>
		<div class=container>
			<div class="row tall">
				<select class="lang">
					<option>ru</option>
					<option>en</option>
				</select>
			</div>
		</div>
		<jsp:include page="/WEB-INF/view/common/footer.jsp"/>
	</body>
</html>