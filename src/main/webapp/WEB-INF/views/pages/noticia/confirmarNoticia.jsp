<!-- Modal -->
<div class="modal fade " id="confirmarNoticiaModal" tabindex="-1" role="dialog" width="30%" height="10%" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">Confirmacion</h4>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" role="form">
					<div class="form-group">
						<div class="col-md-8">
							<label class="control-label">�Desea guardar los datos de la noticia?</label>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<input type="button" class="btn btn-primary" onclick="actionNoticia();" id="btnConfirmarNoticiaSi" value="Si">
				<input type="button" class="btn btn-primary" data-dismiss="modal" id="btnConfirmarNoticiaNo" value="No">
			</div>
		</div>
	</div>
</div>