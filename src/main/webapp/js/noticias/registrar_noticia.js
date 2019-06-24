var array_productos = [];

$(document).ready(function(){
	$('#idInputSearch').hide();
	$("#ckboxComentario").hide();
	console.log("init registrar noticias")
	
	
	$("#idDescripcion").ckeditor();
	
	if(idNoticia!=null && idNoticia.trim().length>0){
		obtenerNoticia(idNoticia);
	}else{
		cargaValidacion();
	}
	
	$("#btnRegistrarNoticia").click(function(){
		if (validarNoticia()) {
			confirmarNoticia();
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
            $('#idImageNoticia').attr('src', e.target.result);
        }

        reader.readAsDataURL(input.files[0]);
    }
}

function obtenerNoticia(idNoticia){
	var parametros=new Object();
	parametros.id=idNoticia;
	
	$.ajax({
		url : "rest/getNoticia.json",
		type : "GET",
		data : parametros,
		datatype : 'json',
		beforeSend: function(xhr) {
    		xhr.setRequestHeader("Accept", "application/json");
    		xhr.setRequestHeader("Content-Type", "application/json");
    	},
		success : function(json) {		
			console.log(json.objeto)
			$("#idTag").val(json.objeto.idTag);	

			if(json.objeto.comentarioHabilitado==='S'){
				$("#ckboxComentario").prop( "checked", true );
			}else{
				$("#ckboxComentario").prop( "checked", false );
			}
			
			$("#idTitulo").val(json.objeto.titulo);	
			$("#idDescripcion").val(json.objeto.descripcion);
			$('#idImageNoticia').attr("src", 'data:image/jpeg;base64,'+json.objeto.imagePreview);
	    },
	    error: function (jqXHR, exception){
	    	showMessage(jqXHR.status,'warning')
	    },
	    complete: function(json) {
	    	cargaValidacion();
	    }
	});	
}

function confirmarNoticia(){
	$.ajax({
		url : "confirmar_noticia",
		type : "GET",
		success : function(json) {
			$("#confirmarNoticia").html(json);
			$('#confirmarNoticiaModal').modal('show');
	    }
	});
}

function validarNoticia(){
	closeAllValidations();
	
	if ('' == $.trim($('#idTitulo').val())) {
		showMessage('Debe ingresar el titulo de la noticia','danger');
		return false;
	}
	
	if ('' == $.trim($('#idDescripcion').val())) {
		showMessage('Debe ingresar la descripcion de la noticia','danger');
		return false;
	}
	
	if (-1 == $('#idTag').val()) {
		showMessage('Debe seleccionar tag de noticia','danger');
		return false;
	}
	
	var image_noticia = $('#idImageNoticia').attr("src");
	if(image_noticia==''){
		showMessage('Debe ingresar imagen de la noticia','danger');
		return false;
	}
	
	
	return true;
}

function actionNoticia(){
	
	var id = null;
	if(idNoticia!=null && idNoticia.trim().length>0){
		id = idNoticia;
	}
	
	var idTitulo = $("#idTitulo").val();
	var idDescripcion = $("#idDescripcion").val();
	var idTag = $("#idTag").val();
	var idArchivo = $("#archivo").val();
	
	var idComentarioHabilitado = null
	if($("#ckboxComentario").is(':checked')){
		idComentarioHabilitado = 'S'
	}else{
		idComentarioHabilitado = 'N'
	}
	
	var parametros=new Object();
	parametros.titulo=idTitulo;
	parametros.descripcion=idDescripcion;
	parametros.idTag=idTag;
	parametros.comentarioHabilitado=idComentarioHabilitado;
	parametros.imagePreview=idArchivo;
	
	
	$('#idBodyGlobal').loading({
		  theme: 'dark',
		  message: 'Cargando...'
	});
	
	if(id==null){
		registrarNoticia(parametros);
	}else{
		parametros.id=id;
		actualizarNoticia(parametros);
	}
}

function registrarNoticia(parametros){
	var form = $('#registrarNoticiaForm')[0];
	var data = new FormData(form);
	
	data.append("titulo" , parametros.titulo);
	data.append("descripcion" , parametros.descripcion);
	data.append("idTag" , parametros.idTag);
	data.append("comentarioHabilitado" , parametros.comentarioHabilitado);
	
	$.ajax({
		url : "rest/insertarNoticia.json",
		type : "POST",
		data: data,
        enctype: 'multipart/form-data',
        processData: false, //prevent jQuery from automatically transforming the data into a query string
        contentType: false,
        cache: false,
		success : function(json) {
			
			var idNoticia = json.objeto.id;	
        	
        	show_modal_message(json.mensaje)
        	$('#idBodyGlobal').loading('toggle');
        	
	    },
	    error: function (jqXHR, exception){
	    	showMessage(jqXHR.status,'warning')
	    },
	    complete: function(json) {
	    	limpiarCampos();
	    	$('#confirmarNoticiaModal').modal('toggle');
	    }
	});
}
function actualizarNoticia(parametros){
	var form = $('#registrarNoticiaForm')[0];
	var data = new FormData(form);
	
	data.append("id" , parametros.id);
	data.append("titulo" , parametros.titulo);
	data.append("descripcion" , parametros.descripcion);
	data.append("idTag" , parametros.idTag);
	data.append("comentarioHabilitado" , parametros.comentarioHabilitado);
	
	$.ajax({
		url : "rest/updateNoticia.json",
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
	    	$('#confirmarNoticiaModal').modal('toggle');
	    }
	});
}

function limpiarCampos(){
	$("#idTag").val("-1");	
	$("#ckboxComentario").prop( "checked", false );
	$("#idTitulo").val("");
	$("#idDescripcion").val("");
	$('#archivo').val("");
	$('#idImageNoticia').attr('src', '');
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
