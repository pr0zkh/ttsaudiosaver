<%@include file="/WEB-INF/view/common/taglibs.jspf" %>

<div class="page-header">
	<h1>Change password</h1>
</div>

<form id="change-password" class="change-password-form">
	<div class="form-group has-error">
		<input type="password" id="old-pass" class="form-control" placeholder="Old password">
	</div>
	<div class="form-group">
		<input type="password" id="new-pass" class="form-control" placeholder="New password">
	</div>
	<div class="form-group">
		<input type="password" id="new-pass-repeat" class="form-control" placeholder="Repeat new password">
	</div>
	
	<input id="change-password" name="change-password" class="btn btn-primary" type="submit" value="Change password" />
	
	<div class="pull-right spinner hidden">
		<div class="rect1"></div>
		<div class="rect2"></div>
		<div class="rect3"></div>
		<div class="rect4"></div>
		<div class="rect5"></div>
	</div>
</form>