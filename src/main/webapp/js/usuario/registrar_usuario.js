var array_productos = [];

$(document).ready(function(){
	$('#idInputSearch').hide();
	console.log("init registrar usuarios")
	
	if(idUsuario!=null && idUsuario.trim().length>0){
		obtenerUsuario(idUsuario);
	}else{
		cargaValidacion();
	}
	
	$("#btnRegistrarUsuario").click(function(){
		if (validarUsuario()) {
			confirmarUsuario();
		}
	});

});


function obtenerUsuario(idUsuario){
	var parametros=new Object();
	parametros.id=idUsuario;
	
	$.ajax({
		url : "rest/getUsuario.json",
		type : "GET",
		data : parametros,
		datatype : 'json',
		beforeSend: function(xhr) {
    		xhr.setRequestHeader("Accept", "application/json");
    		xhr.setRequestHeader("Content-Type", "application/json");
    	},
		success : function(json) {		
			console.log(json.objeto)

			$("#idUsername").val(json.objeto.username);
			$("#idRol").val(json.objeto.idRol);
			$("#idPassword").val(json.objeto.password);
	    },
	    error: function (jqXHR, exception){
	    	showMessage(jqXHR.status,'warning')
	    },
	    complete: function(json) {
	    	cargaValidacion();
	    }
	});	
}

function confirmarUsuario(){
	$.ajax({
		url : "confirmar_usuario",
		type : "GET",
		success : function(json) {
			$("#confirmarUsuario").html(json);
			$('#confirmarUsuarioModal').modal('show');
	    }
	});
}

function validarUsuario(){
	closeAllValidations();
	
	if ('' == $.trim($('#idUsername').val())) {
		showMessage('Debe ingresar el username','danger');
		return false;
	}
	
	if ('' == $.trim($('#idPassword').val())) {
		showMessage('Debe ingresar la clave','danger');
		return false;
	}
	
	if (-1 == $('#idRol').val()) {
		showMessage('Debe seleccionar rol de usuario','danger');
		return false;
	}
	
	return true;
}

function actionUsuario(){
	
	var id = null;
	if(idUsuario!=null && idUsuario.trim().length>0){
		id = idUsuario;
	}

	var idUsername = $("#idUsername").val();
	var idRol = $("#idRol").val();
	var idPassword = $("#idPassword").val();
	
	
	var parametros=new Object();
	parametros.username=idUsername;
	parametros.idRol=idRol;
	parametros.password=idPassword;
	
	
	$('#idBodyGlobal').loading({
		  theme: 'dark',
		  message: 'Cargando...'
	});
	
	if(id==null){
		registrarUsuario(parametros);
	}else{
		parametros.id=id;
		actualizarUsuario(parametros);
	}
}

function registrarUsuario(parametros){
	
	$.ajax({
		url : "rest/insertarUsuario.json",
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
	    	$('#confirmarUsuarioModal').modal('toggle');
	    	limpiarCampos();
	    }
	});
}
function actualizarUsuario(parametros){
	$.ajax({
		url : "rest/updateUsuario.json",
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
	    	$('#confirmarUsuarioModal').modal('toggle');
	    }
	});
}

function limpiarCampos(){
	$("#idUsername").val("");
	$("#idRol").val("");
	$("#idPassword").val("");
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
