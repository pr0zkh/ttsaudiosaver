if(TTS.Module === undefined || typeof TTS.Module !== "object") {
	TTS.Module = {};
}

TTS.Module.alert = (function() {
	var alertBox,
		alertContent;
	
	function success(msg) {
		showAlert(msg, "success", 3000);
	}
	
	function error(msg) {
		showAlert(msg, "error");
	}
	
	function showAlert(msg, type, duration) {
		initModal();
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
		$(alertBox).modal();
		if(duration) {
			setTimeout(function() {
				$(alertBox).modal("hide");
			}, duration);
		}
	}
	
	function initModal() {
		$(alertContent).removeClass("alert-danger");
		$(alertContent).removeClass("alert-success");
		$(alertContent).removeClass("alert-info");
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