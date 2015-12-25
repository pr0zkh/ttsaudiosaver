if(TTS.Module === undefined || typeof TTS.Module !== "object") {
	TTS.Module = {};
}

TTS.Module.translation = (function() {
	
	function init() {
		$("#from-lang-selector-dropdown .lang-opt").on("click", function(e) {
			e.preventDefault();
			$("div#lang-from").data("lang", $(this).data("lang"));
			$("h4#lang-from").text($(this).data("lang"));
			$("#lang-to").fadeIn("slow");
		});
		$("#to-lang-selector-dropdown .lang-opt").on("click", function(e) {
			e.preventDefault();
			$("div#lang-to").data("lang", $(this).data("lang"));
			$("h4#lang-to").text($(this).data("lang"));
			$("#main-area").fadeIn("slow");
		});
		$("#btn-translate").on("click", function() {
			var toTranslate = $("#to-translate-input").val(),
				translateToLang = $("div#lang-to").data("lang"),
				translateFromLang = $("div#lang-from").data("lang");
			$.post("/translate", {toTranslate: toTranslate, fromLang: translateFromLang, toLang: translateToLang}, function(response) {
				var player = $("<div/>").html(response).contents().find(".jp-jplayer"),
					audioSource = player.data("source"),
					playerId = player.data("id");
				$("div#audio-container").append(response);
				TTS.Module.player.initPlayer($("#jquery_jplayer_" + playerId)[0]);
			});
		});
	};
	
	return {
		init: init
	};
})();