<%@include file="/WEB-INF/view/common/taglibs.jspf" %>

<div class="page-header">
	<h1>History</h1>
</div>

<div class="history-content">
	<c:forEach items="${user.compiledAudios }" var="audio">
		<div class="audio">
			<h4>
				<a href="/translation-details/${audio.fileId}">
					${audio.name }
				</a>
			</h4>
			<jsp:include page="/WEB-INF/view/common/player.jsp">
				<jsp:param name="id" value="${audio.fileId}"/>
				<jsp:param name="filePath" value="compiled/${audio.fileId}"/>
			</jsp:include>
			<c:forEach items="${audio.pairsIncluded}" var="pair">
				${pair.fileId}
			</c:forEach>
		</div>
	</c:forEach>
</div>