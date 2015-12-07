<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<link href="/css/styles.css" rel="stylesheet">
	</head>
	<body>
		<jsp:include page="/WEB-INF/view/common/header.jsp"/>
		
		<div class="content">
			<div class="fb-login test">
				Here will be Faceboock login widget
				<button class="fb-login">FACEBOOCK LOGIN WHO NEEDS IT????</button>
			</div>
			
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
						<div class="inline">
							<input id="password" class="input validate" name="password" type="password"/>
							
							<a href="/forgot-password">
								<span class="action">
									Forgot password?
								</span>
							</a>
						</div>
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