<%@include file="/WEB-INF/view/common/taglibs.jspf" %>
<!DOCTYPE html>
<html>
	<head>
		<%@include file="/WEB-INF/view/common/head.jspf" %>
		<title>Text To Speech</title>
	</head>
	<body>
		<jsp:include page="/WEB-INF/view/common/header.jsp"/>
		<div class=container>
			<div class="page-header">
				<h1>Step 1: select language to translate from</h1>
			</div>
			<div class="dropdown clearfix">
				<a id="lang-selector" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
					Select language
					<span class="caret"></span>
				</a>
				<ul id="lang-selector-dropdown pull-left" class="dropdown-menu lang-selector" aria-labeledby="lang-selector">
					<li>
						<a class="lang-opt" href="#" data-lang="en">
							English
						</a>
					</li>
					<li>
						<a class="lang-opt" href="#" data-lang="ru">
							Russian
						</a>
					</li>
				</ul>
			</div>
			<div class="lang-selector-msg">
				<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
			</div>
			<div id="translations" class="page-header hidden">
				<h1>Step 2: add words to translate</h1>
			</div>
			<div id="audio-controls" class="page-header hidden">
				<h1>Step 3: create an audio file</h1>
			</div>
		</div>
		<jsp:include page="/WEB-INF/view/common/footer.jsp"/>
	</body>
</html>