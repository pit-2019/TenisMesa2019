var array_productos=[];

$(document).ready(function(){
	$('#idInputSearch').hide();
	console.log("init registrar perfiles")
	
	if(idPerfil!=null && idPerfil.trim().length>0){
		obtenerPerfil(idPerfil);
	}else{
		cargaValidacion();
	}
	
	$("#btnRegistrarPerfil").click(function(){
		console.log("btn")
		if (validarPerfil()) {
			console.log("paso")
			confirmarPerfil();
		}
	});
	
	$('#idFechaNacimiento').datepicker({
	      pickTime: false,
	      autoclose: true,
	      language: 'es',
	      format: "dd/mm/yyyy"
	});
	
	$("#archivo").change(function () {
        readURL(this);
    });
	
	$('#idFechaNacimiento').datepicker().datepicker('setDate', (new Date()));

	
});

function readURL(input) {
	console.log("read")
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
        	console.log(e.target.result)
            $('#idImagePerfil').attr('src', e.target.result);
        }

        reader.readAsDataURL(input.files[0]);
    }
}

function obtenerPerfil(idPerfil){
	var parametros=new Object();
	parametros.id=idPerfil;
	
	$.ajax({
		url : "rest/getPerfil.json",
		type : "GET",
		data : parametros,
		datatype : 'json',
		beforeSend: function(xhr) {
    		xhr.setRequestHeader("Accept", "application/json");
    		xhr.setRequestHeader("Content-Type", "application/json");
    	},
		success : function(json) {		
			console.log(json.objeto)
			$("#idNombre").val(json.objeto.nombre);	
			$("#idApellidoPaterno").val(json.objeto.apellidoPaterno);	
			$("#idApellidoMaterno").val(json.objeto.apellidoMaterno);
			$('#idFechaNacimiento').datepicker().datepicker('setDate', (new Date(json.objeto.fechaNacimiento)));
			$("#idPais").val(json.objeto.pais);
			$("#idDireccion").val(json.objeto.direccion);
			$("#idRanking").val(json.objeto.ranking);
			$("#idDescripcion").val(json.objeto.descripcion);
			$('#idImagePerfil').attr("src", 'data:image/jpeg;base64,'+json.objeto.imageView);
	    },
	    error: function (jqXHR, exception){
	    	showMessage(jqXHR.status,'warning')
	    },
	    complete: function(json) {
	    	cargaValidacion();
	    }
	});	
}

function confirmarPerfil(){
	$.ajax({
		url : "confirmar_perfil",
		type : "GET",
		success : function(json) {
			$("#confirmarPerfil").html(json);
			$('#confirmarPerfilModal').modal('show');
	    }
	});
}

function validarPerfil(){
	closeAllValidations();
	
	if ('' == $.trim($('#idNombre').val())) {
		showMessage('Debe ingresar el nombre del perfil','danger');
		return false;
	}
	
	if ('' == $.trim($('#idApellidoPaterno').val())) {
		showMessage('Debe ingresar el apellido paterno del perfil','danger');
		return false;
	}
	
	if ('' == $.trim($('#idApellidoMaterno').val())) {
		showMessage('Debe ingresar el apellido materno del perfil','danger');
		return false;
	}
	
	if ('' == $.trim($('#idPais').val())) {
		showMessage('Debe ingresar el pais del perfil','danger');
		return false;
	}
	
	if ('' == $.trim($('#idDireccion').val())) {
		showMessage('Debe ingresar la direccion del perfil','danger');
		return false;
	}
	
	if ('' == $.trim($('#idRanking').val())) {
		showMessage('Debe ingresar el ranking del perfil','danger');
		return false;
	}
	
	if ('0' == $.trim($('#idRanking').val())) {
		showMessage('Debe ingresa un numero mayor o igual a uno','danger');
		return false;
	}
	
	var date_nac = $("#idFechaNacimiento").datepicker('getDate');
	if(date_nac!=null){
		var date_nac_year = date_nac.getFullYear();
		var date_now = new Date();
		var date_year_now = date_now.getFullYear();
		var result_year = parseInt(date_year_now)-parseInt(date_nac_year);
		if(parseInt(result_year)<parseInt(18)){
			showMessage('Debe ingresar fecha de nacimiento mayor a 18 anios','danger');
			return false;
		}
	}
	
	if ('' == $.trim($('#idDescripcion').val())) {
		showMessage('Debe ingresar la  descripcion del perfil','danger');
		return false;
	}
	
	var image_perfil = $('#idImagePerfil').attr("src");
	if(image_perfil==''){
		showMessage('Debe ingresar imagen del perfil','danger');
		return false;
	}
	
	
	return true;
}

function actionPerfil(){
	
	var id = null;
	if(idPerfil!=null && idPerfil.trim().length>0){
		id = idPerfil;
	}
	
	var idNombre = $("#idNombre").val();
	var idApellidoPaterno = $("#idApellidoPaterno").val();
	var idApellidoMaterno = $("#idApellidoMaterno").val();
	var idFechaNacimiento = $("#idFechaNacimiento").datepicker('getDate');
	var idPais = $("#idPais").val();
	var idDireccion = $("#idDireccion").val();
	var idRanking = $("#idRanking").val();
	var idDescripcion = $("#idDescripcion").val();
	var idArchivo = $("#archivo").val();
	
	var parametros=new Object();
	parametros.nombre=idNombre;
	parametros.apellidoPaterno=idApellidoPaterno;
	parametros.apellidoMaterno=idApellidoMaterno;
	parametros.fechaNacimiento=idFechaNacimiento;
	parametros.pais=idPais;
	parametros.direccion=idDireccion;
	parametros.ranking=idRanking;
	parametros.descripcion=idDescripcion;
	parametros.imageView=idArchivo;
	
	
	$('#idBodyGlobal').loading({
		  theme: 'dark',
		  message: 'Cargando...'
	});
	
	if(id==null){
		registrarPerfil(parametros);
	}else{
		parametros.id=id;
		actualizarPerfil(parametros);
	}
}

function registrarPerfil(parametros){
	var form = $('#registrarPerfilForm')[0];
	var data = new FormData(form);
	
	data.append("nombre" , parametros.nombre);
	data.append("apellidoPaterno" , parametros.apellidoPaterno);
	data.append("apellidoMaterno" , parametros.apellidoMaterno);
	data.append("fechaNacimiento" , parametros.fechaNacimiento);
	data.append("pais" , parametros.pais);
	data.append("direccion" , parametros.direccion);
	data.append("ranking" , parametros.ranking);
	data.append("descripcion" , parametros.descripcion);
	
	$.ajax({
		url : "rest/insertarPerfil.json",
		type : "POST",
		data: data,
        enctype: 'multipart/form-data',
        processData: false, //prevent jQuery from automatically transforming the data into a query string
        contentType: false,
        cache: false,
		success : function(json) {
			
			var idPerfil = json.objeto.id;	
        	
        	show_modal_message(json.mensaje)
        	$('#idBodyGlobal').loading('toggle');
        	
	    },
	    error: function (jqXHR, exception){
	    	showMessage(jqXHR.status,'warning')
	    },
	    complete: function(json) {
	    	limpiarCampos();
	    	$('#confirmarPerfilModal').modal('toggle');
	    }
	});
}
function actualizarPerfil(parametros){
	var form = $('#registrarPerfilForm')[0];
	var data = new FormData(form);
	
	data.append("id" , parametros.id);
	data.append("nombre" , parametros.nombre);
	data.append("apellidoPaterno" , parametros.apellidoPaterno);
	data.append("apellidoMaterno" , parametros.apellidoMaterno);
	data.append("fechaNacimiento" , parametros.fechaNacimiento);
	data.append("pais" , parametros.pais);
	data.append("direccion" , parametros.direccion);
	data.append("ranking" , parametros.ranking);
	data.append("descripcion" , parametros.descripcion);
	
	$.ajax({
		url : "rest/updatePerfil.json",
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
	    	$('#confirmarPerfilModal').modal('toggle');
	    }
	});
}

function limpiarCampos(){	
	$("#idNombre").val("");
	$("#idApellidoPaterno").val("");
	$('#idApellidoMaterno').val("");
	$('#idPais').val("");
	$('#idDireccion').val("");
	$('#idRanking').val("");
	$('#idDescripcion').val("");
	$('#idFechaNacimiento').datepicker().datepicker('setDate', (new Date()));
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
