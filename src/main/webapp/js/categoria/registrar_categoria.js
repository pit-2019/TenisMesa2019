var array_productos = [];

$(document).ready(function(){
	$('#idInputSearch').hide();
	console.log("init registrar categorias")
	
	if(idCategoria!=null && idCategoria.trim().length>0){
		obtenerCategoria(idCategoria);
	}else{
		cargaValidacion();
	}
	
	$("#btnRegistrarCategoria").click(function(){
		if (validarCategoria()) {
			confirmarCategoria();
		}
	});

});


function obtenerCategoria(idCategoria){
	var parametros=new Object();
	parametros.id=idCategoria;
	
	$.ajax({
		url : "rest/getCategoria.json",
		type : "GET",
		data : parametros,
		datatype : 'json',
		beforeSend: function(xhr) {
    		xhr.setRequestHeader("Accept", "application/json");
    		xhr.setRequestHeader("Content-Type", "application/json");
    	},
		success : function(json) {		
			console.log(json.objeto)

			$("#idDescripcion").val(json.objeto.descripcion);
	    },
	    error: function (jqXHR, exception){
	    	showMessage(jqXHR.status,'warning')
	    },
	    complete: function(json) {
	    	cargaValidacion();
	    }
	});	
}

function confirmarCategoria(){
	$.ajax({
		url : "confirmar_categoria",
		type : "GET",
		success : function(json) {
			$("#confirmarCategoria").html(json);
			$('#confirmarCategoriaModal').modal('show');
	    }
	});
}

function validarCategoria(){
	closeAllValidations();
	
	if ('' == $.trim($('#idDescripcion').val())) {
		showMessage('Debe ingresar la descripcion de la noticia','danger');
		return false;
	}
	return true;
}

function actionCategoria(){
	
	var id = null;
	if(idCategoria!=null && idCategoria.trim().length>0){
		id = idCategoria;
	}

	var idDescripcion = $("#idDescripcion").val();
	
	
	var parametros=new Object();
	parametros.descripcion=idDescripcion;
	
	
	$('#idBodyGlobal').loading({
		  theme: 'dark',
		  message: 'Cargando...'
	});
	
	if(id==null){
		registrarCategoria(parametros);
	}else{
		parametros.id=id;
		actualizarCategoria(parametros);
	}
}

function registrarCategoria(parametros){
	
	$.ajax({
		url : "rest/insertarCategoria.json",
		type : "POST",
		data : JSON.stringify(parametros),
		datatype : 'json',
		beforeSend: function(xhr) {
    		xhr.setRequestHeader("Accept", "application/json");
    		xhr.setRequestHeader("Content-Type", "application/json");
    	},
		success : function(json) {
			show_modal_message(json.mensaje)
	        $('#idBodyGlobal').loading('toggle');
	    },
	    error: function (jqXHR, exception){
	    	showMessage(jqXHR.status,'warning')
	    },
	    complete: function(json) {
	    	$('#confirmarCategoriaModal').modal('toggle');
	    	limpiarCampos();
	    }
	});
}
function actualizarCategoria(parametros){
	$.ajax({
		url : "rest/updateCategoria.json",
		type : "POST",
		data : JSON.stringify(parametros),
		datatype : 'json',
		beforeSend: function(xhr) {
    		xhr.setRequestHeader("Accept", "application/json");
    		xhr.setRequestHeader("Content-Type", "application/json");
    	},
		success : function(json) {
			show_modal_message(json.mensaje)
	        $('#idBodyGlobal').loading('toggle');
	    },
	    error: function (jqXHR, exception){
	    	showMessage(jqXHR.status,'warning')
	    },
	    complete: function(json) {
	    	$('#confirmarCategoriaModal').modal('toggle');
	    }
	});
}

function limpiarCampos(){
	$("#idDescripcion").val("");
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
