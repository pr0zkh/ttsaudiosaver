if(TTS.Module === undefined || typeof TTS.Module !== "object") {
	TTS.Module = {};
}

TTS.Module.translation = (function() {
	
	function init() {
		var $langSelector = $("#lang-selector"),
			$translationContainer = $("#translations");
		
		$("#lang-selector-dropdown a").on("click", function(e) {
			e.preventDefault();
			console.log("123");
			$translationContainer.hide().removeClass("hidden").fadeIn("slow");
		});
	};
	
	return {
		init: init
	};
})();