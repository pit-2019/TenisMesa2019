<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
<script>
	var idUsuario = '${idUsuario}';
</script>
	<section>
			<div class="container">
				<div class="row">	
					<div class="row">
						<div class="col-xs-12 col-md-12">
							<a class="btn btn-primary" id="btnRegistrarUsuario" href="#">Registrar</a>
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
							
							<form class="form-horizontal">
								<div class="panel panel-default">
									<div class="panel-heading">
									    Usuario
									</div>
									<div class="panel-body">
											
										<div class="row">
											<div class="col-md-6">
												<label class="control-label" for="idUsername">Username:</label>
												<input class="form-control alphanumericinput" id="idUsername" type="text"
													aria-describedby="nameHelp" placeholder="Ingrese username"
													autocomplete="off">
											</div>
											<div class="col-md-6">
												<label class="control-label" for="idPassword">Password:</label>
												<input class="form-control alphanumericinput" id="idPassword" type="password"
													aria-describedby="nameHelp" placeholder="Ingrese password"
													autocomplete="off">
											</div>
											<div class="col-md-6">	
													<label class="control-label" for="idRol">Rol:</label>
													<select id="idRol" class="form-control">
				                                    	<option selected="selected" value="-1">--Seleccione rol--</option>
				                                        <c:forEach var="rolItem" items="${rolList}">
															<option value="${rolItem.id}"><c:out value="${rolItem.descripcion}"></c:out></option>
														</c:forEach>
				                                    </select>	
											</div>
										</div>

									</div>
								</div>
							</form>
							
						</div>
					</div>
				</div>
			</div>
			<div id="confirmarUsuario"></div>
	</section>
	<script src="js/usuario/registrar_usuario.js"></script>
</body>
</html>