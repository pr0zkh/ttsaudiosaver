<%@include file="/WEB-INF/view/common/taglibs.jspf" %>

<div class="translation hidden">
	<div class="translation-controls">
		<input type="text" class="form-control" id="to-translate-input" placeholder="To be translated (current - ${translationPair.toTranslate})" value="${translationPair.toTranslate}">
		<input type="text" class="form-control" id="translation-input" placeholder="Translation result (current - ${translationPair.translationResult})" value="${translationPair.translationResult}">
		<button class="btn btn-update-translation" type="button">
			<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>
		</button>
		<button class="btn btn-remove btn-remove-translation" type="button">
			<span class="glyphicon glyphicon-remove-sign" aria-hidden="true"></span>
		</button>
	 </div>
	<div class="audio">
		<jsp:include page="/WEB-INF/view/common/player.jsp">
			<jsp:param name="id" value="${translationPair.fileId}"/>
			<jsp:param name="filePath" value="${translationPair.fileId}"/>
		</jsp:include>
	</div>
</div>