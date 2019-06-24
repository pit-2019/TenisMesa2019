var id_noticia = sessionStorage.getItem("id_noticia");
$(document).ready(function(){
	console.log("init blog-single")
	$('#idInputSearch').hide();
	console.log(id_noticia)
	getNoticia(id_noticia);
	
	$('#idNombre').val(user)
	$("#btnRegistrarComentario").click(function(event){
		event.preventDefault();

		var correo_e = $('#idEmail').val();

		if ('' == $.trim($('#idNombre').val())) {
			show_modal_message("Debe ingresar el nombre para comentario")
			return false;
		}
		
		if ('' == $.trim($('#idComentario').val())) {
			show_modal_message("Debe ingresar el comentario")
			return false;
		}
		
		
		if(!check_email(correo_e)){
			show_modal_message("Correo electronico no valido")
			return false;
		}else{
			insertarComentario(id_noticia);
		}
		
		
		return true;
		
	});
	
	$(".numberinput").forceNumeric();
	$(".letterinput").forceLetters();
	$(".alphanumericinput").forceAlphaNumeric();
	$(".numberinputdecimal").forceNumericDecimal();
	$(".valid-email").forceEmail();
});

function check_email(val) {
	if (!val.match(/\S+@\S+\.\S+/)) { // Jaymon's / Squirtle's solution
		// Do something
		return false;
	}
	if (val.indexOf(' ') != -1 || val.indexOf('..') != -1) {
		// Do something
		return false;
	}
	return true;
}

function insertarComentario(id_noticia){
	var idNombre = $('#idNombre').val();
	var idEmail = $('#idEmail').val();
	var idComentario = $('#idComentario').val();
	
	var parametros=new Object();
	parametros.nombre=idNombre;
	parametros.email=idEmail;
	parametros.texto=idComentario;
	parametros.idNoticia=id_noticia;
	
	$.ajax({
		url : "rest/insertarComentario.json",
		type : "POST",
		data : JSON.stringify(parametros),
		datatype : 'json',
		beforeSend: function(xhr) {
    		xhr.setRequestHeader("Accept", "application/json");
    		xhr.setRequestHeader("Content-Type", "application/json");
    	},
		success : function(json) {
        	console.log(json)
	    },
	    error: function (jqXHR, exception){
	    	showMessage(jqXHR.status,'warning')
	    },
	    complete: function(json) {
	    	limpiarCampos();
	    	var id_noticia = sessionStorage.getItem("id_noticia");
	    	getComentarios(id_noticia);
	    }
	});
}

function limpiarCampos(){
	$('#idEmail').val("");
	$('#idComentario').val("");
}

function getNoticia(id){
	var parametros=new Object();
	parametros.id=id;
	
	$.ajax({
        url : 'rest/getNoticia.json',
        type : "GET",
        data : parametros,
        datatype : 'json',
		beforeSend: function(xhr) {
    		xhr.setRequestHeader("Accept", "application/json");
    		xhr.setRequestHeader("Content-Type", "application/json");
    	},
        success : function(json) {
			var data = json.objeto;
			build_noticia(data)
        },
        error: function(responseText){
        },
	    complete: function(json) {
	    }
    });
}

function build_noticia(data){
	var content = $('#idContentBlog');
	var in_content = (
			"<div class='single-blog-post'> "+
				"<h3>"+data.titulo+"</h3> "+
				"<div class='post-meta'> "+
					"<ul> "+
						"<li><i class='fa fa-calendar'></i>"+moment(data.fechaPublicacion).locale('es').format('DD/MM/YYYY')+"</li> "+
					"</ul> "+
				"</div> "+
				"<a href='javascript:irDetalleNoticia("+data.id+");'> "+
					"<img src='data:image/jpeg;base64,"+data.imagePreview+"' alt='' width='846' height='390'> "+
				"</a> "+
				"<p>"+data.descripcion+"</p> "+				
			"</div>"
	);
	content.append(in_content);
	
	var tag_content = $('#idContentTagArea');
	var in_tag_content = (
			"<ul class='tag'> "+
				"<li>TAG:</li> "+
				"<li><a class='color' href=''>"+data.descripcionTag+"</a></li> "+
			"</ul> "
	);
	tag_content.append(in_tag_content);
	
	getComentarios(data.id);
}

function getComentarios(id){
	var parametros=new Object();
	parametros.idNoticia=id;
	
	$.ajax({
        url : 'rest/getComentarios.json',
        type : "GET",
        data : parametros,
        datatype : 'json',
		beforeSend: function(xhr) {
    		xhr.setRequestHeader("Accept", "application/json");
    		xhr.setRequestHeader("Content-Type", "application/json");
    	},
        success : function(json) {
			var data = json.rows;
			count_coments(data);
			var content = $('#idContentComentarios')
			content.empty();
			for (var i = 0; i < data.length; i++) {
				build_comentarios(data[i],i)	
			}
			
        },
        error: function(responseText){
        },
	    complete: function(json) {
	    }
    });
}

function count_coments(data){
	var content = $('#idCountComentarios')
	content.text(data.length+" COMENTARIO(S)")
}

function removeComent(id){	
	$('#confirmarDeleteComentarioModal').modal('show');
	sessionStorage.setItem('remove_coment_id', id)
}

function deleteComentario(){
	var id = sessionStorage.getItem("remove_coment_id");
	var parametros=new Object();
	parametros.id=id;
	
	$.ajax({
		url : "rest/deleteComentario.json",
		type : "GET",
		data : parametros,
		datatype : 'json',
		beforeSend: function(xhr) {
    		xhr.setRequestHeader("Accept", "application/json");
    		xhr.setRequestHeader("Content-Type", "application/json");
    	},
		success : function(json) {
        	showMessage(json.mensaje,'info')
	    },
	    error: function (jqXHR, exception){
	    	showMessage(jqXHR.status,'warning')
	    },
	    complete: function(json) {
	    	getComentarios(id_noticia)
	    	$('#confirmarDeleteComentarioModal').modal('toggle');
	    }
	});	
}

function build_comentarios(data,index){
	var remove = ""
	if(user == data.nombre){
		remove = "<li><a class='cart_quantity_delete' href='javascript:removeComent("+data.id+");'><i class='fa fa-times'></i></a></li>"
	}
	var content = $('#idContentComentarios')
	if(index % 2 == 0){
		var in_content = (
				"<li class='media'> "+
					"<a class='pull-left' href='#'> "+
						"<img class='media-object' src='images/blog/user_coment.png' alt=''> "+
					"</a> "+
					"<div class='media-body'> "+
						"<ul class='sinlge-post-meta'> "+
							"<li><i class='fa fa-user'></i>"+data.nombre+"</li> "+
							"<li><i class='fa fa-clock-o'></i>"+moment(data.fechaRegistro).locale('es').format('hh:mm A')+"</li> "+
							"<li><i class='fa fa-calendar'></i>"+moment(data.fechaRegistro).locale('es').format('DD/MM/YYYY')+"</li>"+
							remove+
						"</ul> "+
						"<p>"+data.texto+"</p> "+
					"</div> "+
				"</li> "		
		);
		content.append(in_content);
	}
	else{
		var in_content = (
				"<li class='media second-media'> "+
					"<a class='pull-left' href='#'> "+
						"<img class='media-object' src='images/blog/user_coment.png' alt=''>"+
					"</a> "+
					"<div class='media-body'> "+
						"<ul class='sinlge-post-meta'> "+
							"<li><i class='fa fa-user'></i>"+data.nombre+"</li> "+
							"<li><i class='fa fa-clock-o'></i>"+moment(data.fechaRegistro).locale('es').format('hh:mm A')+"</li> "+
							"<li><i class='fa fa-calendar'></i>"+moment(data.fechaRegistro).locale('es').format('DD/MM/YYYY')+"</li> "+
							remove+
						"</ul> "+
						"<p>"+data.texto+"</p> "+
					"</div> "+
				"</li> "
		);
		content.append(in_content);
	}	
}