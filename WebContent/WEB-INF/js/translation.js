if(TTS.Module === undefined || typeof TTS.Module !== "object") {
	TTS.Module = {};
}

TTS.Module.translation = (function() {
	
	function init() {
		$("#from-lang-selector-dropdown .lang-opt").on("click", function(e) {
			e.preventDefault();
			$("div#lang-from").data("lang", $(this).data("lang"));
			$("#lang-to").fadeIn("slow");
		});
		$("#to-lang-selector-dropdown .lang-opt").on("click", function(e) {
			e.preventDefault();
			$("div#lang-to").data("lang", $(this).data("lang"));
			$("#main-area").fadeIn("slow");
		});
		$("#btn-translate").on("click", function() {
			console.log("123");
			var toTranslate = $("#to-translate-input").val(),
				translateToLang = $("div#lang-to").data("lang"),
				translateFromLang = $("div#lang-from").data("lang");
			console.log(toTranslate);
			console.log(translateToLang);
			console.log(translateFromLang);
			$.post("/translate", {toTranslate: toTranslate, fromLang: translateFromLang, toLang: translateToLang}, function(response) {
				console.log(response);
				$("div#audio-container").append(response);
			});
		});
	};
	
	return {
		init: init
	};
})();