var count_panel_item_carousel=0;

var index_cont_producto_destacado = 0;
var index_paginacion_num = 1;
var paginacion_num = 3;
var array_productos=[];
$(document).ready(function(){
	console.log("init producto")
	$('#idInputSearch').show();
	getProductos();	
	getCategorias();
	obtenerProductosSession();
	$("#idInputSearchGlobal").keyup(function(){		
		var idInputSearchGlobal= $('#idInputSearchGlobal').val();
		if(idInputSearchGlobal!='' && idInputSearchGlobal.length!=0){
			getCategorias();
		}
	});
})

function obtenerProductosSession(){
	var data = JSON.parse(sessionStorage.getItem("items_producto"));
	if(data!=null){
		for(var i=0;i<data.length;i++){
			array_productos.push(data[i]);
		}
	}
}

function getProductos(){
	$.ajax({
        url : 'rest/getProductos.json',
        type : "GET",
        datatype : 'json',
		beforeSend: function(xhr) {
    		xhr.setRequestHeader("Accept", "application/json");
    		xhr.setRequestHeader("Content-Type", "application/json");
    	},
        success : function(json) {
			var data = json.rows;
			for (var i = 0; i < data.length; i++) {
				build_show_productos_top(data[i],i,data)
			}
			
        },
        error: function(responseText){
        },
	    complete: function(json) {
	    }
    });
}

function getCategorias(){
	$.ajax({
        url : 'rest/getCategorias.json',
        type : "GET",
        datatype : 'json',
		beforeSend: function(xhr) {
    		xhr.setRequestHeader("Accept", "application/json");
    		xhr.setRequestHeader("Content-Type", "application/json");
    	},
        success : function(json) {
        	$("#idTabCategoria").empty();
        	$("#idContentTabCategoria").empty();
			var data = json.rows;
			for (var i = 0; i < data.length; i++) {
				build_show_tab_categoria(data[i],i);
			}
			
        },
        error: function(responseText){
        },
	    complete: function(json) {
	    }
    });
}

function build_show_tab_categoria(data,index){
	var content_items_categoria = $("#idTabCategoria");
	var in_content = null;
	if(index==0){
		in_content = (
				"<li class='active'><a href='#tabCategoria-"+data.id+"' data-toggle='tab'>"+data.descripcion+"</a></li> "
				);
	}else{
		in_content = (
				"<li><a href='#tabCategoria-"+data.id+"' data-toggle='tab'>"+data.descripcion+"</a></li> "
				);
	}
	content_items_categoria.append(in_content);
	build_show_content_categoria(data,index);
}

function build_show_content_categoria(data,index){
	var content_items_categoria = $("#idContentTabCategoria");
	var in_content = null;
	if(index==0){
		in_content = (
				"<div class='tab-pane fade active in' id='tabCategoria-"+data.id+"' > "+
				"</div> "
				);
	}else{
		in_content = (
				"<div class='tab-pane fade' id='tabCategoria-"+data.id+"' > "+
				"</div> "
				);
	}
	content_items_categoria.append(in_content);
	
	build_show_content_producto_categoria(data);
	
}

function build_show_content_producto_categoria(data){
	var child_categoria = $('#tabCategoria-'+data.id+'')
	child_categoria.empty();
	
	var idInputSearchGlobal= $('#idInputSearchGlobal').val();
	if(idInputSearchGlobal!='' && idInputSearchGlobal.length!=0){
		var parametros=new Object();
		parametros.descripcion=idInputSearchGlobal;
		$.ajax({
	        url : 'rest/getProductosByDescripcion.json',
	        type : "GET",
	        data : parametros,
	        datatype : 'json',
			beforeSend: function(xhr) {
	    		xhr.setRequestHeader("Accept", "application/json");
	    		xhr.setRequestHeader("Content-Type", "application/json");
	    	},
	        success : function(json) {
	        	
				var data_row = json.rows;
				
				for (var i = 0; i < data_row.length; i++) {
					if(data.id==data_row[i].idCategoria){
						var in_child = (
								"<div class='col-sm-4'> "+
									"<div class='product-image-wrapper'> "+
										"<div class='single-products'> "+
											"<div class='productinfo text-center'> "+
												"<img src='data:image/jpeg;base64,"+data_row[i].imageView+"' alt='' width='184' height='222' /> "+
												"<h2>$"+data_row[i].precio+"</h2> "+
												"<p>"+data_row[i].descripcion+"</p> "+
												"<p>"+data_row[i].detalle+"</p> "+
												"<a href='javascript:verDetalleProducto("+data_row[i].id+");' class='btn btn-default add-to-cart'><i class='fa fa-shopping-cart'></i>Agregar al carrito</a> "+
											"</div> "+
											"<div class='product-overlay'> "+
												"<div class='overlay-content'> "+
													"<h2>$"+data_row[i].precio+"</h2> "+
													"<p>"+data_row[i].descripcion+"</p> "+
													"<p>"+data_row[i].detalle+"</p> "+
													"<a href='javascript:verDetalleProducto("+data_row[i].id+");' class='btn btn-default add-to-cart'><i class='fa fa-shopping-cart'></i>Agregar al carrito</a> "+
												"</div> "+
											"</div> "+
										"</div> "+										
									"</div> "+
								"</div> "
								);
						child_categoria.append(in_child);
					}
				}
				
	        },
	        error: function(responseText){
	        },
		    complete: function(json) {
		    }
	    });
	}else{
		$.ajax({
	        url : 'rest/getProductos.json',
	        type : "GET",
	        datatype : 'json',
			beforeSend: function(xhr) {
	    		xhr.setRequestHeader("Accept", "application/json");
	    		xhr.setRequestHeader("Content-Type", "application/json");
	    	},
	        success : function(json) {
				var data_row = json.rows;
				for (var i = 0; i < data_row.length; i++) {
					if(data.id==data_row[i].idCategoria){
						var in_child = (
								"<div class='col-sm-4'> "+
									"<div class='product-image-wrapper'> "+
										"<div class='single-products'> "+
											"<div class='productinfo text-center'> "+
												"<img src='data:image/jpeg;base64,"+data_row[i].imageView+"' alt='' width='184' height='222' /> "+
												"<h2>$"+data_row[i].precio+"</h2> "+
												"<p>"+data_row[i].descripcion+"</p> "+
												"<a href='javascript:verDetalleProducto("+data_row[i].id+");' class='btn btn-default add-to-cart'><i class='fa fa-shopping-cart'></i>Agregar al carrito</a> "+
											"</div> "+
											"<div class='product-overlay'> "+
												"<div class='overlay-content'> "+
													"<h2>$"+data_row[i].precio+"</h2> "+
													"<p>"+data_row[i].descripcion+"</p> "+
													"<a href='javascript:verDetalleProducto("+data_row[i].id+");' class='btn btn-default add-to-cart'><i class='fa fa-shopping-cart'></i>Agregar al carrito</a> "+
												"</div> "+
											"</div> "+
										"</div> "+										
									"</div> "+
								"</div> "
								);
						child_categoria.append(in_child);
					}
				}
				
	        },
	        error: function(responseText){
	        },
		    complete: function(json) {
		    }
	    });
	}
}

function build_show_productos_top(data,index,array_data_producto){
	if(data.destacado=='S'){
		count_panel_item_carousel = count_panel_item_carousel+1;
		if(count_panel_item_carousel==1){
			var content_items_producto = $("#idContentCarouselProducto");
			var in_content = null;
			if(index==0){
				in_content = (
						"<div class='item active' id='item-"+data.id+"'> "+			
						"</div> "
						);
			}else{
				in_content = (
						"<div class='item' id='item-"+data.id+"'> "+			
						"</div> "
						);
			}
			content_items_producto.append(in_content);
			/****************************************************/
			build_show_child_producto_top(data,array_data_producto)
			/**************************************************/
		}else if(count_panel_item_carousel==3){
			count_panel_item_carousel = 0;
		}
		
	}	
}

function build_show_child_producto_top(data,array_data_producto){
	//PAGINADO
	var content_items_producto = $("#item-"+data.id+"");
	var val_i=index_cont_producto_destacado*paginacion_num;
	var paginacion = paginacion_num*index_paginacion_num;
	for(var i = val_i; i < paginacion; i++){
		if(i<array_data_producto.length){
			if(array_data_producto[i].destacado=='S'){
				var in_content = (
						"<div class='col-sm-4'> "+
							"<div class='product-image-wrapper'> "+
								"<div class='single-products'> "+
									"<div class='productinfo text-center'> "+
										"<img src='data:image/jpeg;base64,"+array_data_producto[i].imageView+"' alt='' width='255' height='188'/> "+
										"<h2>$"+array_data_producto[i].precio+"</h2> "+
										"<p>"+array_data_producto[i].descripcion+"</p> "+
										"<a href='javascript:verDetalleProducto("+array_data_producto[i].id+");' class='btn btn-default add-to-cart'><i class='fa fa-shopping-cart'></i>Agregar al carrito</a> "+
									"</div> "+
								"</div> "+
							"</div> "+
						"</div> "
				);
				content_items_producto.append(in_content)
			}	
		}
		
	}
	index_paginacion_num++;
	index_cont_producto_destacado++;
}

function verDetalleProducto(id){
	var parametros=new Object();
	parametros.id=id;
	
	$.ajax({
        url : 'rest/getProducto.json',
        type : "GET",
        data : parametros,
        datatype : 'json',
		beforeSend: function(xhr) {
    		xhr.setRequestHeader("Accept", "application/json");
    		xhr.setRequestHeader("Content-Type", "application/json");
    	},
        success : function(json) {		
			$("#idContentImageProduct").attr("src","data:image/jpeg;base64,"+json.objeto.imageView);
			$("#idDescripcionProductoModal").text(json.objeto.descripcion);
			$("#idDetalleProductoModal").html(json.objeto.detalle);
			$("#idPrecioProductoModal").text("$"+json.objeto.precio);	
			$("#idAnclaSendProduct").attr("href","javascript:sendProductoToCar("+id+");");
			$('#verDetalleProductoModal').modal('show');
        },
        error: function(responseText){
        },
	    complete: function(json) {
	    }
    });
	
	
}

function sendProductoToCar(id){
	var parametros=new Object();
	parametros.id=id;
	
	$.ajax({
        url : 'rest/getProducto.json',
        type : "GET",
        data : parametros,
        datatype : 'json',
		beforeSend: function(xhr) {
    		xhr.setRequestHeader("Accept", "application/json");
    		xhr.setRequestHeader("Content-Type", "application/json");
    	},
        success : function(json) {
			var convert = json.objeto;
			
			console.log("for")
			console.log(array_productos.length)
			var valid_repite_product=false;
			if(array_productos.length>0){
				for (var i = 0; i < array_productos.length; i++) {
					if(array_productos[i].id==json.objeto.id){
						console.log("producto repetido")
						valid_repite_product = true;
					}
					
				}
			}
			
			if(!valid_repite_product){
				array_productos.push(new itemVenta(
						0,
						json.objeto.id,
						json.objeto.descripcion,
						json.objeto.precio,
						json.objeto.imageView,
						0,
						0))
				
				sessionStorage.setItem('items_producto', JSON.stringify(array_productos))
				window.location = "cart.html";
			}else{
				show_modal_message('El producto ya fue ingresado al carro de compras');
			}
			
        },
        error: function(responseText){
        },
	    complete: function(json) {
	    }
    });
	
}

function irCarShop(){
	sessionStorage.setItem('items_producto', JSON.stringify(array_productos))
	window.location = "cart.html";
}

function itemVenta (index,id,descripcion,precio,imageView,cantidad,total) {
	this.index = index;
    this.id = id;
    this.descripcion = descripcion;
    this.precio = precio;
    this.imageView = imageView;
    this.cantidad = cantidad;
    this.total = total;
}






