<%@include file="/WEB-INF/view/common/taglibs.jspf" %>

<div class="audio hidden">
	<h4>${audio.name}</h4>
	<jsp:include page="/WEB-INF/view/common/player.jsp">
		<jsp:param name="id" value="${audio.fileId}"/>
		<jsp:param name="filePath" value="compiled/${audio.fileId}"/>
	</jsp:include>
</div>