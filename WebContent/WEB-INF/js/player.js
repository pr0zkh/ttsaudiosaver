if(TTS.Module === undefined || typeof TTS.Module !== "object") {
	TTS.Module = {};
}

TTS.Module.player = (function() {
	
	function init() {
		$.each($(".jp-jplayer"), function(index, element){
			initPlayer(element);
		});
	};
	
	function initPlayer(player) {
		var id = $(player).data("file-id"),
			playerData,
			fixFlash_mp4,// Flag: The m4a and m4v Flash player gives some old currentTime values when changed.
			fixFlash_mp4_id, // Timeout ID used with fixFlash_mp4
			ignore_timeupdate, // Flag used with fixFlash_mp4
			options = {
				ready: function(event) {
					// Hide the volume slider on mobile browsers. ie., They have no effect.
					if(event.jPlayer.status.noVolume) {
						// Add a class and then CSS rules deal with it.
						$(".jp-gui").addClass("jp-no-volume");
					}
					// Determine if Flash is being used and the mp4 media type is supplied. BTW, Supplying both mp3 and mp4 is pointless.
					fixFlash_mp4 = event.jPlayer.flash.used && /m4a|m4v/.test(event.jPlayer.options.supplied);
					// Setup the player with media.
					var player = $(this),
						source = player.data("source");
					
					player.jPlayer("setMedia", {
						mp3: source
					});
				},
				timeupdate: function(event) {
					if(!ignore_timeupdate) {
						myControl.progress.slider("value", event.jPlayer.status.currentPercentAbsolute);
					}
				},
				volumechange: function(event) {
					if(event.jPlayer.options.muted) {
						myControl.volume.slider("value", 0);
					} else {
						myControl.volume.slider("value", event.jPlayer.options.volume);
					}
				},
				cssSelectorAncestor: "#jp_container_" + id,
		        swfPath: "/assets/swf",
		        supplied: "mp3",
		        useStateClassSkin: true,
		        autoBlur: false,
		        smoothPlayBar: true,
		        remainingDuration: true,
		        toggleDuration: true
			},
			myControl = {
				progress: $(options.cssSelectorAncestor + " .jp-progress-slider"),
				volume: $(options.cssSelectorAncestor + " .jp-volume-slider"),
				play: $(options.cssSelectorAncestor + " .jp-play"),
				pause: $(options.cssSelectorAncestor + " .jp-pause")
			};
			
		// Instance jPlayer
		$(player).jPlayer(options);
		
		// A pointer to the jPlayer data object
		playerData = $(player).data("jPlayer");
		
		// Define hover states of the buttons
		$('.jp-gui ul li').hover(
			function() { $(this).addClass('ui-state-hover'); },
			function() { $(this).removeClass('ui-state-hover'); }
		);
		
		// Create the progress slider control
		myControl.progress.slider({
			animate: "fast",
			max: 100,
			range: "min",
			step: 0.1,
			value : 0,
			slide: function(event, ui) {
				
				var sp = playerData.status.seekPercent;
				if(sp > 0) {
					// Apply a fix to mp4 formats when the Flash is used.
					if(fixFlash_mp4) {
						ignore_timeupdate = true;
						clearTimeout(fixFlash_mp4_id);
						fixFlash_mp4_id = setTimeout(function() {
							ignore_timeupdate = false;
						},1000);
					}
					// Move the play-head to the value and factor in the seek percent.
					$(player).jPlayer("playHead", ui.value * (100 / sp));
				} else {
					// Create a timeout to reset this slider to zero.
					setTimeout(function() {
						myControl.progress.slider("value", 0);
					}, 0);
				}
			}
		});
		
		// Create the volume slider control
		myControl.volume.slider({
			animate: "fast",
			max: 1,
			range: "min",
			step: 0.01,
			value : $.jPlayer.prototype.options.volume,
			slide: function(event, ui) {
				$(player).jPlayer("option", "muted", false);
				$(player).jPlayer("option", "volume", ui.value);
			}
		});
		
		myControl.play.on("click", function(e) {
			if($(".jp-state-playing")) {
				$(".jp-state-playing").find(".jp-pause").click();
			}
		});
	};
	
	return {
		init: init,
		initPlayer : initPlayer
	};
})();