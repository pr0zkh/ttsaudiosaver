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
								
								if($("#compiled-translation").length) {
									var translationIds = [];
									
									$.each($("#audio-container .jp-jplayer"), function(index, element) {
										translationIds.push($(element).data("id"));
									});
									
									updateCompiledAudio(translationIds);
								} else {
									checkCreateAudioAvailability();
								}
							} 
						}
				);
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
								
								if($("#compiled-translation").length) {
									translateToLang = $("div#compiled-translation").data("translated-to");
									translateFromLang = $("div#compiled-translation").data("translated-from");
								}
								
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
									if($("#compiled-translation").length) {
										var translationIds = [];
										
										$.each($("#audio-container .jp-jplayer"), function(index, element) {
											translationIds.push($(element).data("id"));
										});
										console.log(translationIds);
										updateCompiledAudio(translationIds);
									}
								});
							} 
						}
				);
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
		
		$("#btn-add-translation").on("click", function() {
			var compiledTranslation = $("div#compiled-translation"),
				translationsContainer = $("div#audio-container"),
				toLang = $(compiledTranslation).data("translated-to"),
				fromLang = $(compiledTranslation).data("translated-from"),
				toTranslate = $("#query-input").val();
				
			
			$(translationsContainer).append(loaderBlock);
			slideDown($(translationsContainer).find(".spinner"), function() {
				$.post("/translate", {toTranslate: toTranslate, fromLang: fromLang, toLang: toLang}, function(response) {
					var player = $("<div/>").html(response).contents().find(".jp-jplayer"),
						audioSource = player.data("source"),
						playerId = player.data("id"),
						translationIds = [];
					
					$(translationsContainer).append(response);
					TTS.Module.player.initPlayer($("#jquery_jplayer_" + playerId)[0]);
					
					$.each($("#audio-container .jp-jplayer"), function(index, element) {
						translationIds.push($(element).data("id"));
					});
					
					updateCompiledAudio(translationIds);
					
					slideUp($(translationsContainer).find(".spinner"), function() {
						$(translationsContainer).find(".spinner").remove();
						$("html, body").animate({ scrollTop: $(document).height() - $(window).height() + 239 });
						slideDown($("#jquery_jplayer_" + playerId).closest(".translation"), function(){});
						$("#query-input").val("").focus();
					});
				});
			});
		});
		
		$("#cancel-update-audio").on("click", function(){
			$("#cancel-update-audio-dialog").modal();
		});
		
		$("#cancel-update-audio-modal-btn").on("click", function(){
			window.location.href = "/user-details";
		});
		
		$("#remove-audio").on("click", function(){
			$("#remove-audio-dialog").modal();
		});
		
		$("#remove-audio-modal-btn").on("click", function(){
			var audioId = $("#compiled-translation").find(".jp-jplayer").data("id");
			
			$.post("/remove-audio", {id: audioId}, function(response){
				var responseObj = $.parseJSON(response);
				
				if(responseObj["status"] == "success") {
					window.location.href = "/user-details";
				} else {
					$("#remove-audio-dialog").modal("hide");
					TTS.Module.alert.error("Sorry, we're currently unable to process your request. Try again later.");
				}
			});
		});
		
		$("#update-audio").on("click", function(){
			$("#update-audio-dialog").modal();
		});
		
		$("#update-audio-modal-btn").on("click", function(){
			var audioId = $("#compiled-translation").find(".jp-jplayer").data("id"),
				fileId = $("#compiled-translation").find(".jp-jplayer").data("file-id"),
				translationIds = [];
			
			$.each($("#audio-container .jp-jplayer"), function(index, element) {
				translationIds.push($(element).data("id"));
			});
			
			$.post("/update-existing-audio", {id: audioId, fileId: fileId, fileIds: translationIds}, function(response){
				var responseObj = $.parseJSON(response);
				
				if(responseObj["status"] == "success") {
					window.location.href = "/user-details";
				} else {
					$("#remove-audio-dialog").modal("hide");
					TTS.Module.alert.error("Sorry, we're currently unable to process your request. Try again later.");
				}
			});
		});
		
		function updateCompiledAudio(translationIds) {
			$.post("/compile-audio", {fileIds: translationIds}, function(response) {
				var responseObj = $.parseJSON(response),
					compiledFileId = responseObj["data"],
					player = $("#compiled-translation").find(".jp-jplayer");
				
				console.log(compiledFileId);
				player.jPlayer("setMedia", {
					mp3: "http://localhost:8081/output/compiled/" + compiledFileId + ".mp3"
				});
				player.data("file-id", compiledFileId);
			});
		}
		
		function checkCreateAudioAvailability() {
			var createAudioButton = $("#btn-create-audio");
			
			if($("div.translation").length > 1) {
				$(createAudioButton).removeClass("disabled");
			} else {
				if(!$(createAudioButton).hasClass("disabled")) {
					$(createAudioButton).addClass("disabled");
				}
			}
		};
		
		function slideDown(element, callback) {
			$(element)
				.css('opacity', 0)
				.slideDown(400)
				.animate({ opacity: 1 },
							{ queue: false, 
								duration: 400,
								complete: callback
							}
						);
		};
		
		function slideUp(element, callback) {
			$(element)
			.css('opacity', 1)
			.slideUp(400)
			.animate({ opacity: 0 },
						{ queue: false, 
							duration: 400,
							complete: callback
						}
					);
		};
	};
	
	return {
		init: init
	};
})();