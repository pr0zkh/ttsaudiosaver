<%@include file="/WEB-INF/view/common/taglibs.jspf" %>
<!DOCTYPE html>
<html>
	<head>
		<%@include file="/WEB-INF/view/common/head.jspf" %>
		<title>Signup</title>
	</head>
	<body>
		<jsp:include page="/WEB-INF/view/common/header.jsp"/>
			<div class="container">
				<div class="signup">
					<form id="signup-form" class="signup-form" method="post" action="/signup" novalidate>
						<h2 class="title">Sign Up</h2>
						<a class="btn btn-block btn-social btn-facebook" href="#">
					   		<span class="fa fa-facebook"></span> Sign up with Facebook
					  	</a>
						<label for="username" class="sr-only">Username:</label>
						<input id="username" class="form-control validate" name="username" type="text" placeholder="Username" autofocus>
						<label for="email" class="sr-only">Email:</label>
						<input id="email" class="form-control validate" name="email" type="text" placeholder="Email address">
						<label for="password" class="sr-only">Password:</label>
						<input id="password" class="form-control validate" name="password" type="password" placeholder="Password"/>
						<label for="confirm-password" class="sr-only">Password:</label>
						<input id="confirm-password" class="form-control validate" name="confirm-password" type="password" placeholder="Confirm password"/>
						<div>
							<div class="pull-left">
								<a href="/login">
									Sign In
								</a>
							</div>
							<div class="pull-right">
								<input id="signup-submit" name="signup-submit" class="btn btn-primary" type="submit" value="Sign Up" />
							</div> 
						</div>
					</form>
				</div>
			</div>
		<jsp:include page="/WEB-INF/view/common/footer.jsp"/>
	</body>
</html>