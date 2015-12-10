<%@include file="/WEB-INF/view/common/taglibs.jspf" %>
<!DOCTYPE html>
<html>
	<head>
		<%@include file="/WEB-INF/view/common/head.jspf" %>
		<title>Login</title>
	</head>
	<body>
		<jsp:include page="/WEB-INF/view/common/header.jsp"/>
		
		<div class=container>
			<div class="fb-login test">
				Here will be Faceboock login widget
				<button class="fb-login">FACEBOOCK LOGIN WHO NEEDS IT????</button>
			</div>
			<br/>
			
			<div class="login-form">
				<form id="login-form" method="post" action="/login" novalidate>
					<div class="row">
						<label for="email">
							Email:
						</label>
						<input id="email" class="input validate" name="email" type="text"/>
					</div>
					
					<div class="row">
						<label for="password">
							Password:
						</label>
						<input id="password" class="input validate" name="password" type="password"/>
						
						<a href="/forgot-password">
							<span class="action">
								Forgot password?
							</span>
						</a>
					</div>
					
					<div class="row">
						<div class="inline">
							<a href="/sign-up">
								<span class="action">
									Sign Up
								</span>
							</a>
							
							<input id="login-submit" name="login-submit" class="button submit" type="submit" value="Log In" /> 
						</div>
					</div>
				
				</form>
			</div>
		</div>

		<jsp:include page="/WEB-INF/view/common/footer.jsp"/>
	</body>
</html>