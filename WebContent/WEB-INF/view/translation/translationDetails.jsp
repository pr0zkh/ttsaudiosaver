<%@include file="/WEB-INF/view/common/taglibs.jspf" %>
<!DOCTYPE html>
<html>
	<head>
		<%@include file="/WEB-INF/view/common/head.jspf" %>
		<title>Details - ${audio.name}</title>
	</head>
	<body>
		<jsp:include page="/WEB-INF/view/common/header.jsp"/>
		<div class=container>
			<div id="complete-audio" class="audio-wrapper">
				<div class="page-header">
					<h1>${audio.name}</h1>
					<h4>Translated from ${audio.pairsIncluded[0].translateFromLang} to ${audio.pairsIncluded[0].translateToLang}</h4>
					<div class="translation" id="compiled-translation" data-translated-from="${audio.pairsIncluded[0].translateFromLang}" data-translated-to="${audio.pairsIncluded[0].translateToLang}">
						<div class="audio">
							<jsp:include page="/WEB-INF/view/common/player.jsp">
								<jsp:param name="id" value="${audio.compiledAudioId}"/>
								<jsp:param name="filePath" value="compiled/${audio.fileId}"/>
								<jsp:param name="fileId" value="${audio.fileId}"/>
							</jsp:include>
						</div>
					</div>
				</div>
				<div id="audio-container">
					<c:forEach items="${audio.pairsIncluded}" var="translationPair">
						<div class="translation">
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
									<jsp:param name="fileId" value="${translationPair.fileId}"/>
								</jsp:include>
							</div>
						</div>
					</c:forEach>
				</div>
				<div class="query-controls">
					<div class="input-group query-input">
						<input type="text" class="form-control" id="query-input" placeholder="Enter word to translate">
						<div class="input-group-btn">
						    <button class="btn btn-default" id="btn-add-translation" type="button">Add translation</button>
						</div>
				    </div>
			    </div>
				<div class="pull-right">
					<button type="button" class="btn btn-default" id="cancel-update-audio">Cancel</button>
					<button type="button" class="btn btn-danger" id="remove-audio">Remove</button>
					<button type="button" class="btn btn-primary" id="update-audio">Update</button>
				</div>
			</div>
		</div>
		<jsp:include page="/WEB-INF/view/common/footer.jsp"/>
	</body>
</html>