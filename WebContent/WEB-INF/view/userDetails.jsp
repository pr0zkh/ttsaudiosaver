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
					</div>
					<ul class="nav nav-pills nav-stacked">
						 <li class="active" role="presentation" data-toggle="tab" data-target="#change-profile-details">
						 	<a href="">Profile details</a>
					 	</li>
						 <li role="presentation" data-toggle="tab" data-target="#change-password">
						 	<a href="">Change password</a>
					 	</li>
						 <li role="presentation" data-toggle="tab" data-target="#history">
						 	<a href="">History</a>
					 	</li>
					</ul>
				</div>
				<div class="content tab-content">
					<div class="tab-pane active" id="change-profile-details" role="tabpanel">
						<jsp:include page="/WEB-INF/view/profile/profileDetails.jsp"/>
					</div>
					<div class="tab-pane" id="change-password" role="tabpanel">
						<jsp:include page="/WEB-INF/view/profile/changePasswordForm.jsp"/>
					</div>
					<div class="tab-pane" id="history" role="tabpanel">
						History
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="/WEB-INF/view/common/footer.jsp"/>
	</body>
</html>