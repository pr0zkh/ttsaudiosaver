<footer class="footer">
	<div class="container">
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
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
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
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<button type="button" class="btn btn-danger" id="remove-translation-modal-btn">Remove</button>
				</div>
			</div>
		</div>
	</div>
	
	<div id="remove-audio-dialog" class="modal fade" tabindex="-1" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">Remove audio</h4>
				</div>
				<div class="modal-body">
					<p>Do you want to remove the audio file?</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<button type="button" class="btn btn-danger" id="remove-audio-modal-btn">Remove</button>
				</div>
			</div>
		</div>
	</div>
	
	<div id="update-audio-dialog" class="modal fade" tabindex="-1" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">Update audio</h4>
				</div>
				<div class="modal-body">
					<p>Do you want to update the audio file?</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<button type="button" class="btn btn-primary" id="update-audio-modal-btn">Update</button>
				</div>
			</div>
		</div>
	</div>
	
	<div id="cancel-update-audio-dialog" class="modal fade" tabindex="-1" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">Dismiss changes</h4>
				</div>
				<div class="modal-body">
					<p>Do you want to dismiss all of your changes? In this case all unsaved data will be lost!</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<button type="button" class="btn btn-primary" id="cancel-update-audio-modal-btn">Dismiss</button>
				</div>
			</div>
		</div>
	</div>
	
	<div id="loader-box" class="modal fade" role="dialog">
		<div class="modal-dialog">
			
		</div>
	</div>
</footer>
