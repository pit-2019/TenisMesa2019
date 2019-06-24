<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
<script>
	var idProducto = '${idProducto}';
</script>
	<section>
			<div class="container">
				<div class="row">	
					<div class="row">
						<div class="col-xs-12 col-md-12">
							<a class="btn btn-primary" id="btnRegistrarProducto" href="#">Registrar</a>
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
							
							<form class="form-horizontal" enctype="multipart/form-data" id="registrarProductoForm" method="POST">
								<div class="panel panel-default">
									<div class="panel-heading">
									    Producto
									</div>
									<div class="panel-body">
										<div class="row">
											<div class="col-md-12">
												<label class="control-label" for="idDescripcion">Descripcion:</label>
													<input class="form-control letterinput" id="idDescripcion"
													type="text" aria-describedby="nameHelp"
													placeholder="Ingrese descripcion" autocomplete="off" >
											</div>
											
										</div>
										<div class="row">
											<div class="col-md-12">
												<label class="control-label" for="idDetalle">Detalle:</label>
													<textarea class="form-control alphanumericinput" id="idDetalle"
													type="text" aria-describedby="nameHelp"
													placeholder="Ingrese detalle" autocomplete="off" rows="3"></textarea>
											</div>
											
										</div>
										<div class="row">
											<div class="col-md-12">	
													<label class="control-label" for="idCategoria">Categoria:</label>
													<select id="idCategoria" class="form-control">
				                                    	<option selected="selected" value="-1">--Seleccione categoria--</option>
				                                        <c:forEach var="categoriaItem" items="${categoriaList}">
															<option value="${categoriaItem.id}"><c:out value="${categoriaItem.descripcion}"></c:out></option>
														</c:forEach>
				                                    </select>	
											</div>
										</div>											
										<div class="row">
											<div class="col-md-12">
												<label class="control-label" for="idPrecio">Precio:</label>
												<input class="form-control numberinputdecimal" id="idPrecio"
													type="text" aria-describedby="nameHelp"
													placeholder="Ingrese precio" autocomplete="off"></input>
											</div>
										</div>
										<div class="row">
											<div class="col-md-2">		
												<div class="form-check">											 
													<label class="control-label" >
														<input class="form-check-input" id="ckboxDestacado" type="checkbox" name="ckboxDestacado" value="">
															Destacado
													</label>
												</div>
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
												<img id="idImageProducto" class="media-object image-border" src="" alt="" height="180">
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
			<div id="confirmarProducto"></div>
	</section>
	<script src="js/productos/registrar_producto.js"></script>
</body>
</html>