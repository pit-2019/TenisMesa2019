<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
<script>
	var idNoticia = '${idNoticia}';
</script>
	<section>
			<div class="container">
				<div class="row">	
					<div class="row">
						<div class="col-xs-12 col-md-12">
							<a class="btn btn-primary" id="btnRegistrarNoticia" href="#">Registrar</a>
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
							
							<form class="form-horizontal" enctype="multipart/form-data" id="registrarNoticiaForm" method="POST">
								<div class="panel panel-default">
									<div class="panel-heading">
									    Noticia
									</div>
									<div class="panel-body">
										<div class="row">
											<div class="col-md-6">
												<label class="control-label" for="idTitulo">Titulo:</label>
												<input class="form-control alphanumericinput" id="idTitulo" type="text"
													aria-describedby="nameHelp" placeholder="Ingrese titulo"
													autocomplete="off">
											</div>
											<div class="col-md-6">	
													<label class="control-label" for="idTag">Tag:</label>
													<select id="idTag" class="form-control">
				                                    	<option selected="selected" value="-1">--Seleccione Tag--</option>
				                                        <c:forEach var="tagItem" items="${tagList}">
															<option value="${tagItem.id}"><c:out value="${tagItem.descripcion}"></c:out></option>
														</c:forEach>
				                                    </select>	
											</div>
										</div>
											
										<div class="row">
											<div class="col-md-12">
												<label class="control-label" for="idDescripcion">Descripcion:</label>
												<textarea class="form-control alphanumericinput" id="idDescripcion"
													type="text" aria-describedby="nameHelp"
													placeholder="Ingrese descripcion" autocomplete="off" rows="3"></textarea>
											</div>
										</div>
										<div class="row">
											<div class="col-md-2">		
												<div class="form-check">											 
													<label class="control-label" >
														<input class="form-check-input" id="ckboxComentario" type="checkbox" name="ckboxComentario" value="">
															Aprobado
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
												<img id="idImageNoticia" class="media-object image-border" src="" alt="" height="180">
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
			<div id="confirmarNoticia"></div>
	</section>
	<script src="js/noticias/registrar_noticia.js"></script>
</body>
</html>