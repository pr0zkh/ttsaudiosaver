<%@include file="/WEB-INF/view/common/taglibs.jspf" %>

<div class="audio hidden">
	<jsp:include page="/WEB-INF/view/common/player.jsp">
		<jsp:param name="id" value="${translationPair.fileId}"/>
		<jsp:param name="filePath" value="${translationPair.fileId}"/>
		<jsp:param name="fileId" value="${translationPair.fileId}"/>
	</jsp:include>
</div>
