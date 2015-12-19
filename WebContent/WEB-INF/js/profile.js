if(TTS.Module === undefined || typeof TTS.Module !== "object") {
	TTS.Module = {};
}

TTS.Module.profile = (function() {
	
	function init() {
		initChangePasswordForm();
		initForgotPasswordForm();
		initUpdateProfileForm();
	};
	
	function initChangePasswordForm() {
		$("form#change-password").on("submit", function(e) {
			e.preventDefault();
			if($(this).hasClass(".disabled")) {
				return;
			}
			var form = $(this),
				loader = $(form).find(".spinner"),
				oldPassword = $(form).find("#old-pass").val(),
				newPassword = $(form).find("#new-pass").val();
			
			$(loader).removeClass("hidden");
			$(form).addClass("disabled");
			$(form).find("input").addClass("disabled");

			$.post("/change-password", {oldPassword: oldPassword, newPassword: newPassword}, function(response) {
				var responseObj = $.parseJSON(response);
				$(loader).addClass("hidden");
				$(form).removeClass("disabled");
				$(form).find("input").removeClass("disabled");
				if(responseObj.status == "success") {
					$(form).find("input[type=password]").val("");
					TTS.Module.alert.success("You have successfully updated your password", 
							$(form).find(".form-group").first().find("input"));
				} else if(responseObj.status == "error") {
					$(form).find(".form-group").addClass("has-error");
					TTS.Module.alert.error(responseObj.errorMsg,
							$(form).find(".form-group").first().find("input"));
				}
			});
		});
		
		$("form#change-password").find("input[type=password]").on("change", function(e) {
			$("form#change-password").find(".form-group").removeClass("has-error");
		});
	}
	
	function initForgotPasswordForm() {
		$("form#forgot-password-form").on("submit", function(e){
			e.preventDefault();
			var form = $(this),
				loader = $(form).find(".spinner"),
				emailInput = $("input#email");
			
			loader.removeClass("hidden");
			$.post("/restore-password", {email: emailInput.val()}, function(response) {
				var responseObj = $.parseJSON(response);
				loader.addClass("hidden");
				if(responseObj.status == "success") {
					$(emailInput).val("");
					TTS.Module.alert.success("You have successfully restored your password",
							emailInput);
				} else if(responseObj.status == "error") {
					$(emailInput).parent("div.form-group").addClass("has-error");
					TTS.Module.alert.error(responseObj.errorMsg,
							emailInput);
				}
			});
		});
		
		$("form#forgot-password-form").find("input#email").on("change", function(e) {
			$("form#forgot-password-form").find("input#email").parent("div.form-group").removeClass("has-error");
		});
	}
	
	function initUpdateProfileForm() {
		$("form#change-profile-details").on("submit", function(e) {
			e.preventDefault();
			var form = $(this),
				loader = $(form).find(".spinner"),
				usernameInput = $(form).find("#username"),
				emailInput = $(form).find("#email");
			
			loader.removeClass("hidden");
			$.post("/update-profile", {username: usernameInput.val(), email: emailInput.val()}, function(response) {
				var responseObj = $.parseJSON(response);
				loader.addClass("hidden");
				if(responseObj.status == "success") {
					$(usernameInput).val("");
					$(emailInput).val("");
					TTS.Module.alert.success("You have successfully updated your profile details",
							usernameInput);
				} else if(responseObj.status == "error") {
					$(usernameInput).parent("div.form-group").addClass("has-error");
					$(emailInput).parent("div.form-group").addClass("has-error");
					TTS.Module.alert.error(responseObj.errorMsg,
							usernameInput);
				}
			});
		});
		
		$("form#forgot-password-form").find("input").on("change", function(e) {
			$("form#forgot-password-form").find("input").parent("div.form-group").removeClass("has-error");
		});
	}
	
	return {
		init: init
	};
})();