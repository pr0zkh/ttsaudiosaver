<%@include file="/WEB-INF/view/common/taglibs.jspf" %>
<!DOCTYPE html>
<html>
	<head>
		<%@include file="/WEB-INF/view/common/head.jspf" %>
		<title>Signup</title>
	</head>
	<body>
		<jsp:include page="/WEB-INF/view/common/header.jsp"/>
		<div class=container>
			<div class="signup-form">
				<form id="signup-form" method="post" action="/signup" novalidate>
					<div class="row">
						<label for="username">Username:</label>
						<input id="username" class="input validate" name="username" type="text"/>
					</div>
					<div class="row">
						<label for="email">Email:</label>
						<input id="email" class="input validate" name="email" type="text"/>
					</div>
					<div class="row">
						<label for="password">Password:</label>
						<input id="password" class="input validate" name="password" type="password"/>
						<a href="/forgot-password">
							<span class="action">Forgot password?</span>
						</a>
					</div>
					<div class="row">
						<label for="confirm-password">Confirm password:</label>
						<input id="confirm-password" class="input validate" name="confirm-password" type="password"/>
					</div>
					<div class="row">
						<div class="inline">
							<a href="/login">
								Login
							</a>
							<input id="signup-submit" name="signup-submit" class="button submit" type="submit" value="Create an account" /> 
						</div>
					</div>
				</form>
			</div>
			<div class="fb-signup">
				Here will be facebook widget for registration
			</div>			
		</div>
		<jsp:include page="/WEB-INF/view/common/footer.jsp"/>
	</body>
</html>