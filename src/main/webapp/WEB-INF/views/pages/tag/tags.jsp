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
							<a class="btn btn-primary" href="registrar_tag.html">Nuevo</a>
							<a id="btnEditarTag" class="btn btn-primary" href="#">Editar</a>
							<a id="btnEliminarTag" class="btn btn-primary" href="#">Eliminar</a>
						</div>
					</div>	
					</br>
					<div class="panel panel-default">
						<div class="panel-heading">
							<i class="fa fa-table"></i> Tags
						</div>
						<div class="panel-body">
							<div class="table-responsive">
								<table class="table table-bordered table-striped table-hover" id="dataTableTag" width="100%"
									cellspacing="0">
									<thead>
										<tr>
											<th>Id</th>
											<th>Descripcion</th>
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
		<div id="confirmarDeleteTag"></div>
	</section>
	<script src="js/tag/tag.js"></script>
</body>
</html>