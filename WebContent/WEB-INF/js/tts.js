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
	$("#fakeLoader").fakeLoader({
		//timeToHide:1200, //Time in milliseconds for fakeLoader disappear
		//zIndex:"999",//Default zIndex
		//spinner:"spinner1"//Options: 'spinner1', 'spinner2', 'spinner3', 'spinner4', 'spinner5', 'spinner6', 'spinner7'
		//bgColor:"#2ecc71" //Hex, RGB or RGBA colors
		//imagePath:"yourPath/customizedImage.gif" //If you want can you insert your custom image
		});
});