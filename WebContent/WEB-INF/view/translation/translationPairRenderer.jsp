<%@include file="/WEB-INF/view/common/taglibs.jspf" %>

${translationPair.toTranslate} - ${translationPair.translationResult}
<div class="audio">
	<jsp:include page="/WEB-INF/view/common/player.jsp">
		<jsp:param name="id" value="${translationPair.translationPairId}"/>
		<jsp:param name="fileId" value="${translationPair.fileId}"/>
	</jsp:include>
</div>