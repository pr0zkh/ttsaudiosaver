<footer class="footer">
	<div class="container">
		<p class="text-muted">This is footer</p>
	</div>
	
	<div id="alert-box" class="modal fade alert-box" role="dialog" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="alert" role="alert" id="alert-content">
					
				</div>
			</div>
		</div>
	</div>
	
	<div id="create-audio-dialog" class="modal fade" tabindex="-1" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">Create audio</h4>
				</div>
				<div class="modal-body">
					<p>Do you want to create an audio file?</p>
					<input type="text" class="form-control" id="compiled-file-name" placeholder="Enter file name">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" id="create-audio-modal-btn">Create</button>
				</div>
				</div>
		</div>
	</div>
	
	<div id="remove-translation-dialog" class="modal fade" tabindex="-1" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">Remove translation</h4>
				</div>
				<div class="modal-body">
					<p>Do you want to remove translation pair?</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-danger" id="remove-translation-modal-btn">Remove</button>
				</div>
				</div>
		</div>
	</div>
	
	<div id="loader-box" class="modal fade" role="dialog">
		<div class="modal-dialog">
			
		</div>
	</div>
</footer>
