if(TTS.Module === undefined || typeof TTS.Module !== "object") {
	TTS.Module = {};
}

TTS.Module.translation = (function() {
	
	function init() {
		$("#from-lang-selector-dropdown .lang-opt").on("click", function(e) {
			e.preventDefault();
			$("#lang-to").fadeIn("slow");
		});
		$("#to-lang-selector-dropdown .lang-opt").on("click", function(e) {
			e.preventDefault();
			$("#main-area").fadeIn("slow");
		});
	};
	
	return {
		init: init
	};
})();