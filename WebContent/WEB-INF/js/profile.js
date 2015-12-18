if(TTS.Module === undefined || typeof TTS.Module !== "object") {
	TTS.Module = {};
}

TTS.Module.profile = (function() {
	
	function init() {
		initChangePasswordForm();
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
					console.log("success");
				} else if(responseObj.status == "error") {
					console.log("error");
				}
			});
		});
	}
	
	return {
		init: init
	};
})();