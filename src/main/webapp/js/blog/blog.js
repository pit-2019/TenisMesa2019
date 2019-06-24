var array_productos = [];
$(document).ready(function(){
	console.log("init blog");
	$('#idInputSearch').show();
	getNoticias();
	
	$("#idInputSearchGlobal").keyup(function(){		
		var idInputSearchGlobal= $('#idInputSearchGlobal').val();
		if(idInputSearchGlobal!='' && idInputSearchGlobal.length!=0){
			getNoticiasByDescripcion();
		}else{
			getNoticias();
		}
	});
});

function getNoticias(){
	$('#idContentBlog').empty();
	$.ajax({
        url : 'rest/getNoticias.json',
        type : "GET",
        datatype : 'json',
		beforeSend: function(xhr) {
    		xhr.setRequestHeader("Accept", "application/json");
    		xhr.setRequestHeader("Content-Type", "application/json");
    	},
        success : function(json) {
			var data = json.rows;
			for (var i = 0; i < data.length; i++) {
				build_noticias(data[i])
			}
			//pagination_noticias();
        },
        error: function(responseText){
        },
	    complete: function(json) {
	    }
    });
}

function getNoticiasByDescripcion(){
	var idInputSearchGlobal= $('#idInputSearchGlobal').val();
	var parametros=new Object();
	parametros.titulo=idInputSearchGlobal;
	$('#idContentBlog').empty();
	$.ajax({
        url : 'rest/getNoticiasByDescripcion.json',
        type : "GET",
        data : parametros,
        datatype : 'json',
		beforeSend: function(xhr) {
    		xhr.setRequestHeader("Accept", "application/json");
    		xhr.setRequestHeader("Content-Type", "application/json");
    	},
        success : function(json) {
			var data = json.rows;
			console.log(data)
			for (var i = 0; i < data.length; i++) {
				build_noticias(data[i])
			}
			//pagination_noticias();
        },
        error: function(responseText){
        },
	    complete: function(json) {
	    }
    });
}

function pagination_noticias(){
	var content = $('#idContentBlog');
	var in_content = (
			"<div class='pagination-area'> "+
				"<ul class='pagination'> "+
					"<li><a href='' class='active'>1</a></li> "+
					"<li><a href=''>2</a></li> "+
					"<li><a href=''>3</a></li> "+
					"<li><a href=''><i class='fa fa-angle-double-right'></i></a></li> "+
				"</ul> "+
			"</div> "
	);
	content.append(in_content);
}

function build_noticias(data){
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
				"<a  class='btn btn-primary' href='javascript:irDetalleNoticia("+data.id+");'>Read More</a> "+
			"</div>"
	);
	content.append(in_content);
}

function irDetalleNoticia(id){
	console.log("irDetalleNoticia->"+id)
	sessionStorage.setItem('id_noticia', id)
	window.location = "blog-single.html";
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