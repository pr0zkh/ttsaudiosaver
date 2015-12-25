<%@include file="/WEB-INF/view/common/taglibs.jspf" %>

<div class="page-header">
	<h1>History</h1>
</div>

<div class="history-content">
	<div class="audio">
		<jsp:include page="/WEB-INF/view/common/player.jsp">
			<jsp:param value="1" name="id"/>
			<jsp:param value="32d594a1-84c6-4a81-839f-026755e394a9" name="fileId"/>
		</jsp:include>
	</div>
	<div class="audio">
		<jsp:include page="/WEB-INF/view/common/player.jsp">
			<jsp:param value="2" name="id"/>
			<jsp:param value="32d594a1-84c6-4a81-839f-026755e394a9" name="fileId"/>
		</jsp:include>
	</div>
	<div class="audio">
		<jsp:include page="/WEB-INF/view/common/player.jsp">
			<jsp:param value="3" name="id"/>
			<jsp:param value="32d594a1-84c6-4a81-839f-026755e394a9" name="fileId"/>
		</jsp:include>
	</div>
	<div class="audio">
		<jsp:include page="/WEB-INF/view/common/player.jsp">
			<jsp:param value="4" name="id"/>
			<jsp:param value="32d594a1-84c6-4a81-839f-026755e394a9" name="fileId"/>
			
		</jsp:include>
	</div>
</div>