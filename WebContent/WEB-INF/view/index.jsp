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
			<div id="lang-from">
				<div class="page-header">
					<h1>Step 1: select language to translate from</h1>
				</div>
				<div class="dropdown clearfix">
					<a id="lang-selector" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
						Select language
						<span class="caret"></span>
					</a>
					<ul id="from-lang-selector-dropdown" class="dropdown-menu lang-selector" aria-labeledby="lang-selector">
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
				<h4 id="lang-from"></h4>
			</div>
			<div id="lang-to" class="hidden">
				<div id="translations" class="page-header">
					<h1>Step 2: select language to translate to</h1>
				</div>
				<div class="dropdown clearfix">
					<a id="lang-selector" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
						Select language
						<span class="caret"></span>
					</a>
					<ul id="to-lang-selector-dropdown" class="dropdown-menu lang-selector" aria-labeledby="lang-selector">
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
				<h4 id="lang-to"></h4>
			</div>
			<div id="main-area" class="hidden">
				<div id="audio-controls" class="page-header">
					<h1>Step 3: add words to translate</h1>
				</div>
				<div class="audio-wrapper">
					<div id="audio-container">
					</div>
					<div class="query-controls">
						<div class="input-group query-input">
							<input type="text" class="form-control" id="query-input" placeholder="Enter word to translate">
							<div class="input-group-btn">
								<button class="btn btn-default" id="btn-translate" type="button">Translate</button>
							    <button class="btn btn-default disabled" id="btn-create-audio" type="button">Create audio</button>
							</div>
					    </div>
				    </div>
				    <div id="compiled-audio-container">
				    </div>
			    </div>
			</div>
		</div>
		<jsp:include page="/WEB-INF/view/common/footer.jsp"/>
	</body>
</html>