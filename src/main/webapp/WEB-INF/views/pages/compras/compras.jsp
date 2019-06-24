<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<section>
		<div class="container">
			<div class="row">
				<div class="col-12">
					<div class="alert alert-danger validation-alert" id="messages-alert" style="display:none">
						<button class="close message-close">&times;</button>
						<span class="glyphicon glyphicon-exclamation-sign"></span>
						<span class="message-content"></span>
					</div>
				
				
					<div class="row">
						<div class="col-xs-12 col-md-12">
							
						</div>
					</div>	
					</br>
					<div class="panel panel-default">
						<div class="panel-heading">
							<i class="fa fa-table"></i> Compras
						</div>
						<div class="panel-body">
							<div class="table-responsive">
								<table class="table table-bordered table-striped table-hover" id="dataTableCompras" width="100%"
									cellspacing="0">
									<thead>
										<tr>
											<th>Numero de Compra</th>
											<th>Fecha compra</th>
											<th>Categoria</th>
											<th>Producto</th>
											<th>Cantidad</th>
											<th>Precio</th>
											<th>Usuario</th>
										</tr>
									</thead>
								</table>
							</div>
						</div>
					</div>

				</div>
			</div>
			<br>
			<br>
		</div>
	</section>
	<script src="js/compra/compras.js"></script>
</body>
</html>