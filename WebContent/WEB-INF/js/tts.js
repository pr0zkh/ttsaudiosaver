var TTS = {
    Module: {}
};

TTS.init = function() {
	if(this.Module) {
		$.each(TTS.Module, function(name, funcname){
			//If the object has an init function then run it
			funcname = (typeof funcname === "object") ? this['init'] : funcname;
			if(typeof funcname === "function") {
				funcname.call(this);
			}
		});
	}
};

$(document).ready(function() {
	TTS.init();
});