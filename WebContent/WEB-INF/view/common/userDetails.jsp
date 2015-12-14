<%@include file="/WEB-INF/view/common/taglibs.jspf" %>

<ul class="nav navbar-nav navbar-right">
	<c:choose>
		<c:when test="${user.isLoggedIn}">
			<li id="account">
				<a href="#">Account Details</a>
			</li>
			<li id="logout">
				<a href="#">Logout</a>
			</li>
		</c:when>
		<c:otherwise>
			<li id="sign-in">
				<a href="/login">Sign In</a>
			</li>
			<li id="sign-up">
				<a href="/signup">Sign Up</a>
			</li>
		</c:otherwise>
	</c:choose>
</ul>