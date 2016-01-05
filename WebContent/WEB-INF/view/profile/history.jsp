<%@include file="/WEB-INF/view/common/taglibs.jspf" %>

<div class="page-header">
	<h1>History</h1>
</div>

<div class="history-content">
	<c:choose>
		<c:when test="${empty user.compiledAudios}">
			<h4>
				Sorry, you have no audio files created yet. You can create a new one <a href="/">here</a>.
			</h4>
		</c:when>
		<c:otherwise>
			<c:forEach items="${user.compiledAudios }" var="audio">
				<div class="audio">
					<h2>
						${audio.name}
						<span class="pull-right history-controls">
							<a href="http://localhost:8081/output/compiled/${audio.fileId}.mp3" download="${audio.name}.mp3">
								Download<span class="glyphicon glyphicon-download-alt" aria-hidden="true" style="margin-left: 5px;"></span>
							</a>
							<a href="/translation-details/${audio.fileId}">
								Edit<span class="glyphicon glyphicon-edit" aria-hidden="true" style="margin-left: 5px;"></span>
							</a>
						</span>
					</h2>
					<jsp:include page="/WEB-INF/view/common/player.jsp">
						<jsp:param name="id" value="${audio.fileId}"/>
						<jsp:param name="filePath" value="compiled/${audio.fileId}"/>
						<jsp:param name="fileId" value="${audio.fileId}"/>
					</jsp:include>
				</div>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</div>