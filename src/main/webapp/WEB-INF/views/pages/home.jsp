<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>

	
	<section>
		<div class="container">
			<div class="row">

				
				<div class="col-sm-12 padding-right">
					<div class="features_items"><!--features_items-->
						<h2 class="title text-center">Productos por Categoria</h2>
					</div><!--features_items-->
					
					<div class="category-tab"><!--category-tab-->
						<div class="col-sm-12">
							<ul class="nav nav-tabs" id="idTabCategoria">	
							</ul>
						</div>
						<div class="tab-content" id="idContentTabCategoria">
						</div>
					</div><!--/category-tab-->	
								
					<div class="recommended_items"><!--recommended_items-->
						<h2 class="title text-center">Productos recomendados</h2>					
						<div id="recommended-item-carousel" class="carousel slide" data-ride="carousel">
							<div class="carousel-inner" id="idContentCarouselProducto">
							</div>
							 <a class="left recommended-item-control" href="#recommended-item-carousel" data-slide="prev">
								<i class="fa fa-angle-left"></i>
							  </a>
							  <a class="right recommended-item-control" href="#recommended-item-carousel" data-slide="next">
								<i class="fa fa-angle-right"></i>
							  </a>			
						</div>
					</div><!--/recommended_items-->
					
				</div>
			</div>
		</div>
		<div id="verDetalleProducto">
			<div class="modal fade " id="verDetalleProductoModal" tabindex="-1" role="dialog" width="60%" height="20%" aria-labelledby="myModalLabel">
				<div class="modal-dialog modal-lg" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h4 class="modal-title" id="myModalLabel">Producto</h4>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<form class="form-horizontal" role="form">
								<div class="form-group border-form-group">
									
											<div class="row">
												<div class="col-md-6">
													<img id="idContentImageProduct"  alt="" width="100%" height="100%">
												</div>
												<div class="col-md-6">
													<div class="panel panel-default">
														<div class="panel-body">
															<div class="row">
																<div class="col-md-12">
																	<label class="control-label">Descripción :</label>
																	<p id="idDescripcionProductoModal"></p>
																</div>
															</div>
															<div class="row">
																<div class="col-md-12">
																	<label class="control-label">Caracteristicas :</label>
																	<p id="idDetalleProductoModal"></p>
																</div>
															</div>
															<div class="row">
																<div class="col-md-12">
																	<label class="control-label">Precio :</label>
																	<p id="idPrecioProductoModal"></p>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
									
									<br>
									<div class="row">
										<div class="col-md-12">
											<div class='productinfo text-center'>
												<a id="idAnclaSendProduct" href='#' class='btn btn-default add-to-cart' style="background: #FE980F; color: #FFFFFF"><i class='fa fa-shopping-cart'></i>Agregar al carrito</a>
											</div>
										</div>
									</div>
								</div>
							</form>
						</div>
						<div class="modal-footer">
						</div>
					</div>
				</div>
			</div>
		
		</div>
	</section>
	<script src="js/home/home.js"></script>
</body>
</html>