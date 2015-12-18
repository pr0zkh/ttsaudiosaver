<%@include file="/WEB-INF/view/common/taglibs.jspf" %>
<!DOCTYPE html>
<html>
	<head>
		<%@include file="/WEB-INF/view/common/head.jspf" %>
		<title>${user.username}</title>
	</head>
	<body>
		<jsp:include page="/WEB-INF/view/common/header.jsp"/>
		<div class=container>
			<div class="user-details">
				<div class="left-nav pull-left">
					<div>
						<div class="profile-pic">
							<img src="${user.profilePicUrl}"/>
						</div>
						<h3>${user.username}</h3>
					</div>
					<ul class="nav nav-pills nav-stacked">
						 <li class="active" role="presentation" data-toggle="tab" data-target="#profile-details">
						 	<a href="">Profile details</a>
					 	</li>
						 <li role="presentation" data-toggle="tab" data-target="#change-profile-details">
						 	<a href="">Change profile details</a>
					 	</li>
						 <li role="presentation" data-toggle="tab" data-target="#change-password">
						 	<a href="">Change password</a>
					 	</li>
						 <li role="presentation" data-toggle="tab" data-target="#history">
						 	<a href="">History</a>
					 	</li>
						 <li role="presentation" data-toggle="tab" data-target="#some-info">
						 	<a href="">Some info</a>
					 	</li>
						 <li role="presentation" data-toggle="tab" data-target="#some-more-info">
						 	<a href="">Some more info</a>
					 	</li>
					</ul>
				</div>
				<div class="content tab-content">
					<div class="tab-pane active" id="profile-details" role="tabpanel">
						Profile details
					</div>
					<div class="tab-pane" id="change-profile-details" role="tabpanel">
						Change profile details
					</div>
					<div class="tab-pane" id="change-password" role="tabpanel">
						<jsp:include page="/WEB-INF/view/profile/changePasswordForm.jsp"/>
					</div>
					<div class="tab-pane" id="history" role="tabpanel">
						History
					</div>
					<div class="tab-pane" id="some-info" role="tabpanel">
						Some info
					</div>
					<div class="tab-pane" id="some-more-info" role="tabpanel">
						Some more info
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="/WEB-INF/view/common/footer.jsp"/>
	</body>
</html>