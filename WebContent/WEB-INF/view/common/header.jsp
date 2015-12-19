<%@include file="/WEB-INF/view/common/taglibs.jspf" %>

<nav id="top-nav" class="navbar navbar-default navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="/">
				<span class="glyphicon glyphicon-home" aria-hidden="true"></span>
			</a>
		</div>
		<div class="collapse navbar-collapse" id="navbar">
			<ul class="nav navbar-nav">
				<li>
					<a href="#">Translate</a>
				</li>
				<li>
					<a href="#">About</a>
				</li>
				<li>
					<a href="#">Contact</a>
				</li>
			</ul>
			<jsp:include page="/WEB-INF/view/common/userDetails.jsp"/>
		</div>
	</div>
</nav>