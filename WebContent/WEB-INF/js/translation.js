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
			var toTranslate = $("#query-input").val(),
				translateToLang = $("div#lang-to").data("lang"),
				translateFromLang = $("div#lang-from").data("lang");
			$.post("/translate", {toTranslate: toTranslate, fromLang: translateFromLang, toLang: translateToLang}, function(response) {
				var player = $("<div/>").html(response).contents().find(".jp-jplayer"),
					audioSource = player.data("source"),
					playerId = player.data("id");
				$("div#audio-container").append(response);
				TTS.Module.player.initPlayer($("#jquery_jplayer_" + playerId)[0]);
				if($("#btn-create-audio").hasClass("disabled")) {
					$("#btn-create-audio").removeClass("disabled");
				}
				$("#jquery_jplayer_" + playerId)
					.closest(".translation")
					.css('opacity', 0)
					.slideDown(400)
					.animate({ opacity: 1 }, { queue: false, duration: 400 });
				$("#query-input").val("").focus();
			});
		});
		
		$("#btn-create-audio").on("click", function(){
			if($("#btn-create-audio").hasClass("disabled")) {
				return;
			}
			$("#create-audio-dialog").modal();
		});
		
		$("#create-audio-modal-btn").on("click", function() {
			console.log("creating new audio");
			$("#create-audio-dialog").modal("hide");
		});
	};
	
	return {
		init: init
	};
})();