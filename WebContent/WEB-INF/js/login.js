if(TTS.Module === undefined || typeof TTS.Module !== "object") {
	TTS.Module = {};
}

TTS.Module.login = (function() {
	
	function init() {
		$("a#fb-login").on("click", function(e) {
			console.log("fb login!!!");
			FB.login(function(response){
				console.log(response);
				if(response.status === "connected") {
					console.log("connected");
				} else if(response.status === "not_authorized") {
					console.log("not_authorized");
				} else {
					
				}
			}, {scope: 'public_profile,email'});
		});
	};
	
	return {
		init: init
	};
})();