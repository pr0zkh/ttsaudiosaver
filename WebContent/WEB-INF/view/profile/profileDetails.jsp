<%@include file="/WEB-INF/view/common/taglibs.jspf" %>

<div class="page-header">
	<h1>Change profile details</h1>
</div>

<form id="change-profile-details" class="change-password-form" novalidate autocomplete="off">
	<div class="form-group">
		<label for="username">Username</label>
		<input type="text" id="username" class="form-control" placeholder="Username" value="${user.username}">
	</div>
	<div class="form-group">
		<label for="email">Email</label>
		<input type="text" id="email" class="form-control" placeholder="Email" value="${user.email}">
	</div>
	
	<input id="change-details" name="change-details" class="btn btn-primary" type="submit" value="Change details" />
	
	<div class="pull-right spinner hidden">
		<div class="rect1"></div>
		<div class="rect2"></div>
		<div class="rect3"></div>
		<div class="rect4"></div>
		<div class="rect5"></div>
	</div>
</form>