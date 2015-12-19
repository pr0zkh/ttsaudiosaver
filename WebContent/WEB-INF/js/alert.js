if(TTS.Module === undefined || typeof TTS.Module !== "object") {
	TTS.Module = {};
}

TTS.Module.alert = (function() {
	var alertBox,
		alertContent;
	
	function success(msg, focusTo) {
		showAlert(msg, "success", 3000, focusTo);
	}
	
	function error(msg, focusTo) {
		showAlert(msg, "error", undefined, focusTo);
	}
	
	function showAlert(msg, type, duration, focusTo) {
		initModal(focusTo);
		switch(type) {
			case "success":
				$(alertContent).addClass("alert-success");
				break;
			case "error":
				$(alertContent).addClass("alert-danger");
				break;
			default:
				$(alertContent).addClass("alert-info");
				break
		}
		$(alertContent).text(msg);
		$(alertBox).modal({keyboard: true});
		if(duration) {
			setTimeout(function() {
				$(alertBox).modal("hide");
			}, duration);
		}
	}
	
	function initModal(focusTo) {
		$(alertContent).removeClass("alert-danger");
		$(alertContent).removeClass("alert-success");
		$(alertContent).removeClass("alert-info");
		
		$(alertBox).on('hidden.bs.modal', function (){
			if(focusTo) {
				$(focusTo).focus();
			}
		});
	}
	
	function init() {
		alertBox = $("#alert-box");
		alertContent = $("#alert-content");
	}
	
	return {
		error: error,
		success: success,
		init: init
	};
})();