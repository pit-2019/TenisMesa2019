var array_productos = [];
$(document).ready(function(){
	$('#idInputSearch').hide();
	console.log("init registrar productos")
	$("#idDetalle").ckeditor();
	if(idProducto!=null && idProducto.trim().length>0){
		obtenerProducto(idProducto);
	}else{
		cargaValidacion();
	}
	
	$("#btnRegistrarProducto").click(function(){
		if (validarProducto()) {
			confirmarProducto();
		}
	});
	
	$("#archivo").change(function () {
        readURL(this);
    });

});

function readURL(input) {
	console.log("read")
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
        	console.log(e.target.result)
            $('#idImageProducto').attr('src', e.target.result);
        }

        reader.readAsDataURL(input.files[0]);
    }
}

function obtenerProducto(idProducto){
	var parametros=new Object();
	parametros.id=idProducto;
	
	$.ajax({
		url : "rest/getProducto.json",
		type : "GET",
		data : parametros,
		datatype : 'json',
		beforeSend: function(xhr) {
    		xhr.setRequestHeader("Accept", "application/json");
    		xhr.setRequestHeader("Content-Type", "application/json");
    	},
		success : function(json) {		
			console.log(json.objeto)
			$("#idCategoria").val(json.objeto.idCategoria);	

			if(json.objeto.destacado==='S'){
				$("#ckboxDestacado").prop( "checked", true );
			}else{
				$("#ckboxDestacado").prop( "checked", false );
			}
			
			$("#idDescripcion").val(json.objeto.descripcion);	
			$("#idDetalle").val(json.objeto.detalle);	
			$("#idPrecio").val(json.objeto.precio);
			$('#idImageProducto').attr("src", 'data:image/jpeg;base64,'+json.objeto.imageView);
	    },
	    error: function (jqXHR, exception){
	    	showMessage(jqXHR.status,'warning')
	    },
	    complete: function(json) {
	    	cargaValidacion();
	    }
	});	
}

function confirmarProducto(){
	$.ajax({
		url : "confirmar_producto",
		type : "GET",
		success : function(json) {
			$("#confirmarProducto").html(json);
			$('#confirmarProductoModal').modal('show');
	    }
	});
}

function validarProducto(){
	closeAllValidations();
	
	if ('' == $.trim($('#idDescripcion').val())) {
		showMessage('Debe ingresar la descripcion del producto','danger');
		return false;
	}
	
	if ('' == $.trim($('#idPrecio').val())) {
		showMessage('Debe ingresar el precio del producto','danger');
		return false;
	}
	
	if ('0' == $.trim($('#idPrecio').val())) {
		showMessage('El precio del producto debe ser diferente de cero','danger');
		return false;
	}
	
	if (-1 == $('#idCategoria').val()) {
		showMessage('Debe seleccionar categoria de producto','danger');
		return false;
	}
	
	var image_producto = $('#idImageProducto').attr("src");
	if(image_producto==''){
		showMessage('Debe ingresar imagen del producto','danger');
		return false;
	}


	
	return true;
}

function actionProducto(){
	
	var id = null;
	if(idProducto!=null && idProducto.trim().length>0){
		id = idProducto;
	}
	
	var idDescripcion = $("#idDescripcion").val();
	var idPrecio = $("#idPrecio").val();
	var idCategoria = $("#idCategoria").val();
	var idArchivo = $("#archivo").val();
	var idDetalle = $("#idDetalle").val();
	
	var idDestacado = null
	if($("#ckboxDestacado").is(':checked')){
		idDestacado = 'S'
	}else{
		idDestacado = 'N'
	}
	
	var parametros=new Object();
	parametros.descripcion=idDescripcion;
	parametros.precio=idPrecio;
	parametros.idCategoria=idCategoria;
	parametros.destacado=idDestacado;
	parametros.imageView=idArchivo;
	parametros.detalle=idDetalle;
	
	
	$('#idBodyGlobal').loading({
		  theme: 'dark',
		  message: 'Cargando...'
	});
	
	if(id==null){
		registrarProducto(parametros);
	}else{
		parametros.id=id;
		actualizarProducto(parametros);
	}
}

function registrarProducto(parametros){
	var form = $('#registrarProductoForm')[0];
	var data = new FormData(form);
	
	data.append("descripcion" , parametros.descripcion);
	data.append("precio" , parametros.precio);
	data.append("idCategoria" , parametros.idCategoria);
	data.append("destacado" , parametros.destacado);
	data.append("detalle" , parametros.detalle);
	
	$.ajax({
		url : "rest/insertarProducto.json",
		type : "POST",
		data: data,
        enctype: 'multipart/form-data',
        processData: false, //prevent jQuery from automatically transforming the data into a query string
        contentType: false,
        cache: false,
		success : function(json) {
			
			var idProducto = json.objeto.id;	
        	
        	show_modal_message(json.mensaje)
        	$('#idBodyGlobal').loading('toggle');
        	
	    },
	    error: function (jqXHR, exception){
	    	showMessage(jqXHR.status,'warning')
	    },
	    complete: function(json) {
	    	limpiarCampos();
	    	$('#confirmarProductoModal').modal('toggle');
	    }
	});
}
function actualizarProducto(parametros){
	var form = $('#registrarProductoForm')[0];
	var data = new FormData(form);
	
	data.append("id" , parametros.id);
	data.append("descripcion" , parametros.descripcion);
	data.append("precio" , parametros.precio);
	data.append("idCategoria" , parametros.idCategoria);
	data.append("destacado" , parametros.destacado);
	data.append("detalle" , parametros.detalle);
	
	$.ajax({
		url : "rest/updateProducto.json",
		type : "POST",
		data: data,
        enctype: 'multipart/form-data',
        processData: false, //prevent jQuery from automatically transforming the data into a query string
        contentType: false,
        cache: false,
		success : function(json) {
	        
	        show_modal_message(json.mensaje)
	        $('#idBodyGlobal').loading('toggle');

	    },
	    error: function (jqXHR, exception){
	    	showMessage(jqXHR.status,'warning')
	    },
	    complete: function(json) {
	    	$('#confirmarProductoModal').modal('toggle');
	    }
	});
}

function limpiarCampos(){
	$("#idCategoria").val("-1");	
	$("#ckboxDestacado").prop( "checked", false );
	$("#idPrecio").val("");
	$("#idDescripcion").val("");
	$("#idDetalle").val("");
	$('#archivo').val("");
	$('#idImageProducto').attr('src', '');
}

function obtenerProductosSession(){
	var data = JSON.parse(sessionStorage.getItem("items_producto"));
	if(data!=null){
		for(var i=0;i<data.length;i++){
			array_productos.push(data[i]);
		}
	}
}

function irCarShop(){
	obtenerProductosSession();
	sessionStorage.setItem('items_producto', JSON.stringify(array_productos))
	window.location = "cart.html";
}