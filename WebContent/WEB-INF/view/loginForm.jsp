<%@include file="/WEB-INF/view/common/taglibs.jspf" %>

<div class=container>
	<div class="login">
		<form id="login-form" class="login-form" method="post" action="/login" novalidate>
			<h2 class="title">Log In</h2>
			<a class="btn btn-block btn-social btn-facebook" href="#">
		   		<span class="fa fa-facebook"></span> Sign in with Facebook
		  	</a>
			<label for="email" class="sr-only">Email:</label>
			<input id="email" class="form-control validate" name="email" type="text" placeholder="Email address" autofocus>
			<label for="password" class="sr-only">Password:</label>
			<input id="password" class="form-control validate" name="password" type="password" placeholder="Password"/>
			<div>
				<div class="pull-left">
					<a href="/forgot-password">
						Forgot password?
					</a>
					<a href="/signup">
						Sign Up
					</a>
				</div>
				<div class="pull-right">
					<input id="login-submit" name="login-submit" class="btn btn-primary" type="submit" value="Log In" />
				</div> 
			</div>
		</form>
	</div>
</div>