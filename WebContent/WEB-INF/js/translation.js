if(TTS.Module === undefined || typeof TTS.Module !== "object") {
	TTS.Module = {};
}

TTS.Module.translation = (function() {
	
	var lang = {"en": "English", "ru": "Russian"},
		loaderBlock = '<div class="spinner hidden"><div class="rect1"></div><div class="rect2"></div><div class="rect3"></div><div class="rect4"></div><div class="rect5"></div></div>';
	
	function init() {
		$("#from-lang-selector-dropdown .lang-opt").on("click", function(e) {
			e.preventDefault();
			$("div#lang-from").data("lang", $(this).data("lang"));
			$("h4#lang-from").text(lang[$(this).data("lang")]);
			$("#lang-to").fadeIn("slow");
		});
		
		$("#to-lang-selector-dropdown .lang-opt").on("click", function(e) {
			e.preventDefault();
			$("div#lang-to").data("lang", $(this).data("lang"));
			$("h4#lang-to").text(lang[$(this).data("lang")]);
			$("#main-area").fadeIn("slow");
		});
		
		$("#btn-translate").on("click", function() {
			var toTranslate = $("#query-input").val(),
				translateToLang = $("div#lang-to").data("lang"),
				translateFromLang = $("div#lang-from").data("lang");
			$("div#audio-container").append(loaderBlock);
			$("div#audio-container").find("div.spinner")
				.css('opacity', 0)
				.slideDown(400)
				.animate({ opacity: 1 }, { queue: false, duration: 400 });
			$.post("/translate", {toTranslate: toTranslate, fromLang: translateFromLang, toLang: translateToLang}, function(response) {
				var player = $("<div/>").html(response).contents().find(".jp-jplayer"),
					audioSource = player.data("source"),
					playerId = player.data("id");
				$("div#audio-container").find("div.spinner")
					.css('opacity', 1)
					.slideUp(400)
					.animate({ opacity: 0 }, 
							{ queue: false, duration: 400, complete: function() {
									$("div#audio-container").find("div.spinner").remove();
								} 
							}
					);
				$("div#audio-container").append(response);
				TTS.Module.player.initPlayer($("#jquery_jplayer_" + playerId)[0]);
				$("html, body").animate({ scrollTop: $(document).height() - $(window).height() + 239 });
				$("#jquery_jplayer_" + playerId)
					.closest(".translation")
					.css('opacity', 0)
					.slideDown(400)
					.animate({ opacity: 1 }, { queue: false, duration: 400 });
				$("#query-input").val("").focus();
				checkCreateAudioAvailability();
			});
		});
		
		$("#btn-create-audio").on("click", function(){
			if($("#btn-create-audio").hasClass("disabled")) {
				return;
			}
			$("#create-audio-dialog").modal();
		});
		
		$(document).on("click", ".btn-remove-translation", function() {
			var playerContainer = $(this).closest("div.translation"),
				removeDialogBox = $("#remove-translation-dialog"),
				player = $(playerContainer).find(".jp-jplayer"),
				translationId = $(player).data("id");
			
			
			$(removeDialogBox).data("translation-id", translationId);
			$(removeDialogBox).modal();
		});
		
		$("#remove-translation-modal-btn").on("click", function() {
			var translationId = $("#remove-translation-dialog").data("translation-id"),
				playerContainer = $("#jquery_jplayer_" + translationId).closest("div.translation");
			
			$("#remove-translation-dialog").modal("hide");
			
			$(playerContainer)
				.css('opacity', 1)
				.slideUp(400)
				.animate({ opacity: 0 }, 
						{ queue: false, duration: 400, complete: function() {
								$(playerContainer).remove();
							} 
						}
				);
			checkCreateAudioAvailability();
		});
		
		$(document).on("click", ".btn-update-translation", function(){
			var updateBtn = $(this),
				translationBox = $(updateBtn).closest(".translation"),
				audioBox = $(translationBox).find(".audio"),
				toTranslate = $(translationBox).find("#to-translate-input").val(),
				translation = $(translationBox).find("#translation-input").val(),
				translateToLang = $("div#lang-to").data("lang"),
				translateFromLang = $("div#lang-from").data("lang");
			
			$(updateBtn).find("span").addClass("wait-spinner");
			$(audioBox)
				.css('opacity', 1)
				.slideUp(400)
				.animate({ opacity: 0 }, 
						{ queue: false, duration: 400, complete: function() {
								$(audioBox).remove();
							} 
						}
				);
			$.post("/update-translation", {toTranslate: toTranslate, translation: translation, fromLang: translateFromLang, toLang: translateToLang}, function(response) {
				var player = $("<div/>").html(response).contents().find(".jp-jplayer"),
					audioSource = player.data("source"),
					playerId = player.data("id");
				$(translationBox).append(response);
				TTS.Module.player.initPlayer($("#jquery_jplayer_" + playerId)[0]);
				$("html, body").animate({ scrollTop: $(document).height() - $(window).height() + 100 });
				$(updateBtn).find("span").removeClass("wait-spinner");
				$(translationBox).find(".audio")
					.css('opacity', 0)
					.slideDown(400)
					.animate({ opacity: 1 }, { queue: false, duration: 400 });
			});
		});
		
		$("#create-audio-modal-btn").on("click", function() {
			var resultBox = $("#compiled-audio-container"),
				fileName = $("#compiled-file-name").val(),
				translationIds = [];
			
			$("#create-audio-dialog").modal("hide");
			$(resultBox).append(loaderBlock);
			
			if($(resultBox).find(".audio").length){
				$(resultBox).find(".audio")
					.css('opacity', 1)
					.slideUp(400)
					.animate({ opacity: 0 }, 
						{queue: false, 
							duration: 400, 
							complete: function() {
								$(resultBox).find(".audio").remove();
							}
						}
					);
			} 
			$.each($("#audio-container .jp-jplayer"), function(index, element) {
				translationIds.push($(element).data("id"));
			});
			
			$(resultBox).find("div.spinner")
				.css('opacity', 0)
				.slideDown(400)
				.animate({ opacity: 1 }, 
						{ queue: false, 
							duration: 400,
							complete: function() {
								$.post("/compile-translations", {fileIds: translationIds, name: fileName}, function(response) {
									var player = $("<div/>").html(response).contents().find(".jp-jplayer"),
										audioSource = player.data("source"),
										playerId = player.data("id");
									$(resultBox).append(response);
									TTS.Module.player.initPlayer($("#jquery_jplayer_" + playerId)[0]);
									$("html, body").animate({ scrollTop: $(document).height() - $(window).height() + 239 });
									$(resultBox).find("div.spinner")
										.css('opacity', 1)
										.slideUp(400)
										.animate({ opacity: 0 }, 
											{ queue: false, duration: 400, complete: function() {
												$(resultBox).find("div.spinner").remove();
											}
										});
									$(resultBox)
										.find(".audio")
										.css('opacity', 0)
										.slideDown(400)
										.animate({ opacity: 1 }, { queue: false, duration: 400 });
								});
							}
						}
				);
		});
		
		function checkCreateAudioAvailability() {
			var createAudioButton = $("#btn-create-audio");
			
			if($("div.translation").length > 1) {
				$(createAudioButton).removeClass("disabled");
			} else {
				if(!$(createAudioButton).hasClass("disabled")) {
					$(createAudioButton).addClass("disabled");
				}
			}
		}
	};
	
	return {
		init: init
	};
})();