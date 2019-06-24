var array_productos=[];
$(document).ready(function(){
	console.log("init login")
	$('#idInputSearch').hide();
	$('#idUsername').focus();
	$("#idBtnLogin").click(function(event){
		event.preventDefault();
		console.log("idBtnLogin")
		initLogin();
	});
	$("#idBtnRegister").click(function(event){
		event.preventDefault();
		confirmarProducto();
		
	});
});

function actionUsuario(){
	if(validar_registro_usuario()){
		insertarUsuario();
	}	
}

function confirmarProducto(){
	$.ajax({
		url : "confirmar_usuario",
		type : "GET",
		success : function(json) {
			$("#confirmarUsuario").html(json);
			$('#confirmarUsuarioModal').modal('show');
	    }
	});
}

function validar_registro_usuario(){
	if ('' == $.trim($('#idUsernameRegister').val())) {
		show_modal_message('Debe ingresar username','danger');
		return false;
	}
	if ('' == $.trim($('#idPasswordRegister').val())) {
		show_modal_message('Debe ingresar password','danger');
		return false;
	}
	
	return true;
}

function initLogin(){
	var username = $('#idUsername').val();
	var password = $('#idPassword').val();
	
	var parametros=new Object();
	parametros.username=username;
	parametros.password=password;
	
	$.ajax({
		url : "rest/login-init.json",
		type : "POST",
		data : JSON.stringify(parametros),
		datatype : 'json',
		beforeSend: function(xhr) {
    		xhr.setRequestHeader("Accept", "application/json");
    		xhr.setRequestHeader("Content-Type", "application/json");
    	},
		success : function(json) {
        	console.log(json)
        	if(json.tipoMensaje=="ERROR"){
        		$('#idMensajeError').text(json.mensaje);
        	}else{
        		window.location = "home.html"
        	}
	    },
	    error: function (jqXHR, exception){
	    	showMessage(jqXHR.status,'warning')
	    },
	    complete: function(json) {
	    }
	});
}

function insertarUsuario(){
	var idUsername = $('#idUsernameRegister').val();
	var idPassword = $('#idPasswordRegister').val();
	
	var parametros=new Object();
	parametros.username=idUsername;
	parametros.password=idPassword;
	parametros.idRol = 3;
	
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
	    },
	    error: function (jqXHR, exception){
	    	showMessage(jqXHR.status,'warning')
	    },
	    complete: function(json) {
	    	limpiarCampos();
	    	$('#confirmarUsuarioModal').modal('toggle');
	    }
	});
}

function limpiarCampos(){
	$('#idUsernameRegister').val('');
	$('#idPasswordRegister').val('');
}

function irCarShop(){
	obtenerProductosSession();
	sessionStorage.setItem('items_producto', JSON.stringify(array_productos))
	window.location = "cart.html";
}

function obtenerProductosSession(){
	var data = JSON.parse(sessionStorage.getItem("items_producto"));
	if(data!=null){
		for(var i=0;i<data.length;i++){
			array_productos.push(data[i]);
		}
	}
}