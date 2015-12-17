if(TTS.Module === undefined || typeof TTS.Module !== "object") {
	TTS.Module = {};
}

TTS.Module.login = (function() {
	
	function init() {
		$("a#fb-login").on("click", function(e) {
			var loader = $(this).next(".spinner");
			loader.removeClass("hidden");
			FB.login(function(response){
				if(response.status === "connected") {
					FB.api("/me", {fields: "email,name", access_token: response.authResponse.accessToken}, function(response) {
						var user = response;
						FB.api("/me/picture?height=200&width=200", function(response) {
							$.post("/fb-login", {username: user.name, email: user.email, profilePicUrl: response.data.url}, function(response) {
								console.log(response);
								loader.addClass("hidden");
								window.location = "/";
							});
						});
					});
				} else if(response.status === "not_authorized") {
				} else {
					
				}
			}, {scope: 'public_profile,email'});
		});
	};
	
	return {
		init: init
	};
})();