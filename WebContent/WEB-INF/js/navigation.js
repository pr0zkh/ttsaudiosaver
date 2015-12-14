if(TTS.Module === undefined || typeof TTS.Module !== "object") {
	TTS.Module = {};
}

TTS.Module.navigation = (function() {
	
	function init() {
		var navToUrlMap = [
       			{urlTemplate: /login/, id: "sign-in"},
       			{urlTemplate: /signup/, id: "sign-up"}
       		], 
       		$topNav = $("#top-nav");
		
		if($topNav) {
			var loc = window.location.href;
			$.each(navToUrlMap, function(index, element) {
				if(element.urlTemplate.test(loc)) {
					$topNav.find("li.active").removeClass("active");
					$topNav.find("#" + element.id).addClass("active");
					return false;
				}
			});
		}
	};
	
	return {
		init: init
	};
})();