<%@include file="/WEB-INF/view/common/taglibs.jspf" %>
<!DOCTYPE html>
<html>
	<head>
		<%@include file="/WEB-INF/view/common/head.jspf" %>
		<title>Forgot passwrod?</title>
	</head>
	<body>
		<jsp:include page="/WEB-INF/view/common/header.jsp"/>
			<div class="container">
				<div class="forgot-password">
					<form id="forgot-password-form" class="forgot-password-form" method="post" action="/forgot-password" novalidate>
						<h2 class="title">Forgot Password?</h2>
						<h4>Enter your email address and we'll send you a link to reset your password</h4>
						<label for="email" class="sr-only">Email:</label>
						<input id="email" class="form-control validate" name="email" type="text" placeholder="Enter your email address" autofocus>
						<div class="pull-right">
							<input id="forgot-password-submit" name="forgot-password-submit" class="btn btn-primary" type="submit" value="Restore password" />
						</div> 
					</form>
				</div>
			</div>
		<jsp:include page="/WEB-INF/view/common/footer.jsp"/>
	</body>
</html>