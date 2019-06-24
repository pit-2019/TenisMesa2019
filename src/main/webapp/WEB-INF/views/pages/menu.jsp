<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<header id="header"><!--header-->
		<div class="header_top"><!--header_top-->
			<div class="container">
				<div class="row">
					<div class="col-sm-6">
						<div class="contactinfo">
							<ul class="nav nav-pills">
<!-- 								<li><a href="#"><i class="fa fa-phone"></i> +2 95 01 88 821</a></li> -->
								<li><a href="#"><i class="fa fa-envelope"></i> ProyectoIntegradorII2019@gmail.com</a></li>
							</ul>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="social-icons pull-right">
							<ul class="nav navbar-nav">
								<li><a href="#"><i class="fa fa-facebook"></i></a></li>
								<li><a href="#"><i class="fa fa-twitter"></i></a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div><!--/header_top-->
		
		<div class="header-middle"><!--header-middle-->
			<div class="container">
				<div class="row">
					<div class="col-md-4 clearfix">
						<div class="logo pull-left">
						</div>

					</div>
					<div class="col-md-8 clearfix">
						<div class="shop-menu clearfix pull-right">
							<ul class="nav navbar-nav">
								<li><a href="javascript:irCarShop();"><i class="fa fa-shopping-cart"></i> Cart</a></li>
								<c:if test="${usuario==null}">
									<li><a href="login.html"><i class="fa fa-lock"></i> Login</a></li>
								</c:if>
								
								<c:if test="${usuario!=null}">
									<li><a href="#"><i class="fa fa-user"></i> ${usuario.username}</a></li>
									<li><a href="javascript:logout_method();"><i class="fa fa-lock"></i> Logout</a></li>
								</c:if>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div><!--/header-middle-->
	
		<div class="header-bottom"><!--header-bottom-->
			<div class="container">
				<div class="row">
					<div class="col-sm-9">
						<div class="navbar-header">
							<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
								<span class="sr-only">Toggle navigation</span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
							</button>
						</div>
						<div class="mainmenu pull-left">
							<ul class="nav navbar-nav collapse navbar-collapse">
			                    <c:forEach var="opciones" items="${listaOpciones}">
											<c:if test="${opciones.path!='#' && opciones.idPadre==null}">
												<li><a href=${opciones.path} class="active">${opciones.descripcion}</a></li>
											</c:if>									
					             </c:forEach>
								 <c:forEach var="opcionesAlter" items="${listaOpciones}">
					                    	<c:if test="${opcionesAlter.path=='#' && opcionesAlter.idPadre==null}">
					                    		 <li class="dropdown">
							                     	<a href="#">${opcionesAlter.descripcion} <i class="fa fa-angle-down"></i></a>
							                     	<ul role="menu" class="sub-menu">
									           		
										            			<c:forEach var="opcionesAlterChild" items="${listaOpciones}">
										            				<c:if test="${opcionesAlter.id==opcionesAlterChild.idPadre}">
											            				<li><a href=${opcionesAlterChild.path}>${opcionesAlterChild.descripcion}</a></li>
											            			</c:if>
										            			</c:forEach>
										            
										            </ul>
							                     </li>
					                    	</c:if>
						                   
					               </c:forEach>
							</ul>
						</div>
					</div>
					<div class="col-sm-3" id="idInputSearch">
						<div class="search_box pull-right">
							<input id="idInputSearchGlobal" type="text" placeholder="Search"/>
						</div>
					</div>
				</div>
			</div>
		</div><!--/header-bottom-->
	</header><!--/header-->