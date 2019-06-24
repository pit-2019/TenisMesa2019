<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
<script>
	var idCategoria = '${idCategoria}';
</script>
	<section>
			<div class="container">
				<div class="row">	
					<div class="row">
						<div class="col-xs-12 col-md-12">
							<a class="btn btn-primary" id="btnRegistrarCategoria" href="#">Registrar</a>
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
									    Categoria
									</div>
									<div class="panel-body">
											
										<div class="row">
											<div class="col-md-12">
												<label class="control-label" for="idDescripcion">Descripcion:</label>
												<input class="form-control letterinput" id="idDescripcion" type="text"
													aria-describedby="nameHelp" placeholder="Ingrese descripcion"
													autocomplete="off">
											</div>
										</div>

									</div>
								</div>
							</form>
							
						</div>
					</div>
				</div>
			</div>
			<div id="confirmarCategoria"></div>
	</section>
	<script src="js/categoria/registrar_categoria.js"></script>
</body>
</html>