<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
<script>
	var idPerfil = '${idPerfil}';
</script>
	<section>
			<div class="container">
				<div class="row">	
					<div class="row">
						<div class="col-xs-12 col-md-12">
							<a class="btn btn-primary" id="btnRegistrarPerfil" href="#">Registrar</a>
						</div>
					</div>	
					<br>
					<div class="alert alert-danger validation-alert" id="messages-alert"
						style="display: none">
						<button class="close message-close">&times;</button>
						<span class="glyphicon glyphicon-exclamation-sign"></span> <span
							class="message-content"></span>
					</div>
		
					<div class="row">
						<div class="col-xs-12 col-md-12">
							
							<form class="form-horizontal" enctype="multipart/form-data" id="registrarPerfilForm" method="POST">
								<div class="panel panel-default">
									<div class="panel-heading">
									    Jugador
									</div>
									<div class="panel-body">
										<div class="row">
											<div class="col-md-6">
												<label class="control-label" for="idNombre">Nombre:</label>
												<input class="form-control letterinput" id="idNombre" type="text"
													aria-describedby="nameHelp" placeholder="Ingrese nombre"
													autocomplete="off">
											</div>
											<div class="col-md-6">	
												<label class="control-label" for="idApellidoPaterno">Apellido paterno:</label>
												<input class="form-control letterinput" id="idApellidoPaterno" type="text"
													aria-describedby="nameHelp" placeholder="Ingrese apellido paterno"
													autocomplete="off">	
											</div>
										</div>
											
										<div class="row">
											<div class="col-md-6">
												<label class="control-label" for="idApellidoMaterno">Apellido materno:</label>
												<input class="form-control letterinput" id="idApellidoMaterno" type="text"
													aria-describedby="nameHelp" placeholder="Ingrese apellido materno"
													autocomplete="off">
											</div>
											<div class="col-md-6">
												<label class="control-label" for="idPais">Pais:</label>
												<input class="form-control letterinput" id="idPais" type="text"
													aria-describedby="nameHelp" placeholder="Ingrese pais"
													autocomplete="off">
											</div>
										</div>
										<div class="row">
											<div class="col-md-6">
												<label class="control-label" for="idDireccion">Direccion:</label>
												<input class="form-control alphanumericinput" id="idDireccion" type="text"
													aria-describedby="nameHelp" placeholder="Ingrese direccion"
													autocomplete="off">
											</div>
											<div class="col-md-6">
												<label class="control-label" for="idRanking">Ranking:</label>
												<input class="form-control numberinput" id="idRanking" type="text"
													aria-describedby="nameHelp" placeholder="Ingrese ranking"
													autocomplete="off">
											</div>
										</div>
										<div class="row">
											<div class="col-md-6">
												<label class="control-label" for="idDescripcion">Descripcion:</label>
												<input class="form-control alphanumericinput" id="idDescripcion" type="text"
													aria-describedby="nameHelp" placeholder="Ingrese descripcion"
													autocomplete="off">
											</div>
											<div class="col-md-6">
												<label class="control-label">Fecha Nacimiento:</label>
												<input data-inputmask="'alias': 'date'" class="form-control" readonly="true" id="idFechaNacimiento"/>
											</div>
										</div>
										<div class="row">
											<div>
												<div class="col-md-12">
													<input type="file" class="form-control" id="archivo" name="archivo" placeholder="Upload File"></input>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-4"></div>
											<div class="col-md-4">
												<img id="idImagePerfil" class="media-object image-border" src="" alt="" height="180">
											</div>
											<div class="col-md-4"></div>
										</div>
									</div>
								</div>
							</form>
							
						</div>
					</div>
				</div>
			</div>
			<div id="confirmarPerfil"></div>
	</section>
	<script src="js/perfil/registrar_perfil.js"></script>
</body>
</html>