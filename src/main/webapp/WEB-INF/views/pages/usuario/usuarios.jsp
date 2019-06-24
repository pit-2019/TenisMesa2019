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
							<a class="btn btn-primary" href="registrar_usuario.html">Nuevo</a>
							<a id="btnEditarUsuario" class="btn btn-primary" href="#">Editar</a>
							<a id="btnEliminarUsuario" class="btn btn-primary" href="#">Eliminar</a>
						</div>
					</div>	
					</br>
					<div class="panel panel-default">
						<div class="panel-heading">
							<i class="fa fa-table"></i> Usuarios
						</div>
						<div class="panel-body">
							<div class="table-responsive">
								<table class="table table-bordered table-striped table-hover" id="dataTableUsuario" width="100%"
									cellspacing="0">
									<thead>
										<tr>
											<th>Id</th>
											<th>Username</th>
											<th>Rol</th>
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
		<div id="confirmarDeleteUsuario"></div>
	</section>
	<script src="js/usuario/usuarios.js"></script>
</body>
</html>