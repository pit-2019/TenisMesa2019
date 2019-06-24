<!-- Modal -->
<div class="modal fade " id="confirmarTarjetaCreditoModal" tabindex="-1" role="dialog" width="30%" height="10%" aria-labelledby="myModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">Pago con tarjeta</h4>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" role="form">
					<div class="form-group border-form-group">
						<div class="row">
							<div class="col-md-12">
								<img src="images/cart/tarjeta_credito.png" alt="" width="100%" height="130">
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<label class="control-label" for="idNumeroTarjeta">Numero de tarjeta :</label>
												<input class="form-control numberinput" id="idNumeroTarjeta" type="text"
													aria-describedby="nameHelp" placeholder="Ingrese numero de tarjeta"
													autocomplete="off" maxlength="16">
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<label class="control-label" for="idImporte">Importe :</label>
												<input class="form-control alphanumericinput" id="idImporte" type="text"
													aria-describedby="nameHelp" readonly="true" 
													autocomplete="off" value="${storageImporte}">
							</div>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<input type="button" class="btn btn-primary" onclick="confirmarVenta();" id="btnConfirmarTarjetaCreditoSi" value="Pagar">
				<input type="button" class="btn btn-primary" data-dismiss="modal" id="btnConfirmarTarjetaCreditoNo" value="Cancelar">
			</div>
		</div>
	</div>
</div>
<script>
$(".numberinput").forceNumeric();
</script>