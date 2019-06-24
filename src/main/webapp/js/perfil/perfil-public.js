var array_productos = [];
$(document).ready(function(){
	console.log("init perfil public");
	$('#idInputSearch').show();
	getPerfil();
});

function getPerfil(){
	$.ajax({
        url : 'rest/getPerfiles.json',
        type : "GET",
        datatype : 'json',
		beforeSend: function(xhr) {
    		xhr.setRequestHeader("Accept", "application/json");
    		xhr.setRequestHeader("Content-Type", "application/json");
    	},
        success : function(json) {
			var data = json.rows;
			for (var i = 0; i < data.length; i++) {
				build_perfiles(data[i])
			}
        },
        error: function(responseText){
        },
	    complete: function(json) {
	    }
    });
}


function build_perfiles(data){
	var content = $('#idContentPerfil');
	var in_content = (
			"<div class='single-blog-post'> "+
				"<div class='post-meta'> "+
					"<div class='row'> "+
						"<div class='panel panel-default'> "+
							"<div class='panel-heading'> "+
							"</div> "+
							"<div class='panel-body'> "+
								"<div class='row'> "+
									"<div class='col-md-2'></div> "+
									"<div class='col-md-4'> "+
										"<p>Ranking : "+data.ranking+"</p> "+
										"<p>Fecha nacimiento : "+moment(data.fechaNacimiento).locale('es').format('DD/MM/YYYY')+"</p> "+
										"<p>Nombre completo : "+data.nombre+' '+data.apellidoPaterno+' '+data.apellidoMaterno+"</p> "+
										"<p>Pais : "+data.pais+"</p> "+
										"<p>Direccion : "+data.direccion+"</p> "+
										"<p>Descripcion : "+data.descripcion+"</p> "+
									"</div> "+
									"<div class='col-md-4'> "+
										"<img src='data:image/jpeg;base64,"+data.imageView+"' alt='' width='246' height='190'> "+
									"</div> "+
									"<div class='col-md-2'></div> "+
								"</div> "+
							"</div> "+
						"</div> "+
					"</div> "+

				"</div> "+
			"</div>"
	);
	content.append(in_content);
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