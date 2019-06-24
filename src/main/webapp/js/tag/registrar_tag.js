var array_productos = [];

$(document).ready(function(){
	$('#idInputSearch').hide();
	console.log("init registrar tags")
	
	if(idTag!=null && idTag.trim().length>0){
		obtenerTag(idTag);
	}else{
		cargaValidacion();
	}
	
	$("#btnRegistrarTag").click(function(){
		if (validarTag()) {
			confirmarTag();
		}
	});

});


function obtenerTag(idTag){
	var parametros=new Object();
	parametros.id=idTag;
	
	$.ajax({
		url : "rest/getTag.json",
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

function confirmarTag(){
	$.ajax({
		url : "confirmar_tag",
		type : "GET",
		success : function(json) {
			$("#confirmarTag").html(json);
			$('#confirmarTagModal').modal('show');
	    }
	});
}

function validarTag(){
	closeAllValidations();
	
	if ('' == $.trim($('#idDescripcion').val())) {
		showMessage('Debe ingresar la descripcion de la noticia','danger');
		return false;
	}
	return true;
}

function actionTag(){
	
	var id = null;
	if(idTag!=null && idTag.trim().length>0){
		id = idTag;
	}

	var idDescripcion = $("#idDescripcion").val();
	
	
	var parametros=new Object();
	parametros.descripcion=idDescripcion;
	
	
	$('#idBodyGlobal').loading({
		  theme: 'dark',
		  message: 'Cargando...'
	});
	
	if(id==null){
		registrarTag(parametros);
	}else{
		parametros.id=id;
		actualizarTag(parametros);
	}
}

function registrarTag(parametros){
	
	$.ajax({
		url : "rest/insertarTag.json",
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
	    	$('#confirmarTagModal').modal('toggle');
	    	limpiarCampos();
	    }
	});
}
function actualizarTag(parametros){
	$.ajax({
		url : "rest/updateTag.json",
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
	    	$('#confirmarTagModal').modal('toggle');
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
