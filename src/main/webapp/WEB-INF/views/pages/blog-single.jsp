<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
<script>
var user = '${usuario.username}';
</script>
<section>
		<div class="container">
			<div class="row">
				<div class="col-sm-12">
					<div class="blog-post-area" id="idContentBlog">
						<h2 class="title text-center">Noticia</h2>					
					</div><!--/blog-post-area-->

					<div class="rating-area" id="idContentTagArea">					
					</div>
					<div class="response-area">
						<h2 id="idCountComentarios" >3 RESPONSES</h2>
						<ul class="media-list" id="idContentComentarios">
						</ul>					
					</div><!--/Response-area-->
					<c:if test="${usuario==null}">
					<a href='login.html'>Ingresa para comentar</a>
					<br>
					<br>
					</c:if>
					
					<c:if test="${usuario!=null}">
					<div class="replay-box">
						<div class="row">
							<div class="col-sm-4">
								<h2>Deje su comentario</h2>
								<form>
									<div class="blank-arrow">
										<label>Tu nombre</label>
									</div>
									<span>*</span>
									<input id="idNombre" type="text" class="letterinput" readonly="true" placeholder="Escribe tu nombre...">
									<div class="blank-arrow">
										<label>Correo electronico</label>
									</div>
									<span>*</span>
									<input id="idEmail" type="text" class="valid-email" placeholder="Escribe tu correo...">
								</form>
							</div>
							<div class="col-sm-8">
								<div class="text-area">
									<div class="blank-arrow">
										<label>Comentario</label>
									</div>
									<span>*</span>
									<textarea id="idComentario" name="message" rows="11" placeholder="Escribe tu comentario..."></textarea>
									<a class="btn btn-primary" id="btnRegistrarComentario" href="">Enviar comentario</a>
								</div>
							</div>
						</div>
					</div><!--/Repaly Box-->
					</c:if>
				</div>	
			</div>
		</div>
		<div id="confirmarDeleteComentario">
			<div class="modal fade " id="confirmarDeleteComentarioModal" tabindex="-1" role="dialog" width="30%" height="10%" aria-labelledby="myModalLabel">
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
										<label class="control-label">¿Desea eliminar el comentario seleccionada?</label>
									</div>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<input type="button" class="btn btn-primary" onclick="deleteComentario();" id="btnConfirmarComentarioSi" value="Si">
							<input type="button" class="btn btn-primary" data-dismiss="modal" id="btnConfirmarComentarioNo" value="No">
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<script src="js/blog/blog-single.js"></script>
</body>
</html>