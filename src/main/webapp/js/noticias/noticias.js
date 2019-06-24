var array_productos = [];
var idNoticia = null;
var data_table_noticia = null;
$(document).ready(function(){
	console.log("init noticias")
	$('#idInputSearch').hide();
	data_table_noticia = $('#dataTableNoticia').DataTable({
		  "pageLength": 5,
    	  "columns": [
	    	  { "data": "id" },
	    	  { "data": "titulo" }]
	});
	getNoticias();
	
	$('#dataTableNoticia tbody').on('click', 'tr', function () {
		$("#dataTableNoticia tbody tr").removeClass('row_selected');  
		$(this).addClass('row_selected');
	    idNoticia = data_table_noticia.row( this ).data().id; 
	});
	
	$("#btnEditarNoticia").click(function(){
		if(idNoticia!=null && parseInt(idNoticia)>parseInt(0)){
			window.location = 'actualizar_noticia?id='+idNoticia+'';
		}else{
			showMessage('Debe seleccionar un noticia','info')
		}
	});
	
	$("#btnEliminarNoticia").click(function(){
		if(idNoticia!=null && parseInt(idNoticia)>parseInt(0)){
			confirmarDeleteNoticia();
		}else{
			showMessage('Debe seleccionar una noticia','info')
		}
	});
});

function getNoticias(){
	$.ajax({
		url : "rest/getNoticias.json",
		type : "GET",
		datatype : 'json',
		beforeSend: function(xhr) {
    		xhr.setRequestHeader("Accept", "application/json");
    		xhr.setRequestHeader("Content-Type", "application/json");
    	},
		success : function(json) {	
			var data = json.rows;
			for(i=0 ; i<data.length ; i++){
				data_table_noticia.rows.add( [{
					id : data[i].id,
					titulo : data[i].titulo,
					descripcion : data[i].descripcion,
					comentarioHabilitado : data[i].comentarioHabilitado,
				}]).draw( false );
			}
	    },
	    error: function (jqXHR, exception){
	    	showMessage(jqXHR.status,'warning')
	    },
	    complete: function(json) {
	    }
	});	
}

function deleteNoticia(){
	var parametros=new Object();
	parametros.id=idNoticia;
	
	$.ajax({
		url : "rest/deleteNoticia.json",
		type : "GET",
		data : parametros,
		datatype : 'json',
		beforeSend: function(xhr) {
    		xhr.setRequestHeader("Accept", "application/json");
    		xhr.setRequestHeader("Content-Type", "application/json");
    	},
		success : function(json) {
        	showMessage(json.mensaje,'info')
        	data_table_noticia.row('.row_selected').remove().draw( false );
	    },
	    error: function (jqXHR, exception){
	    	showMessage(jqXHR.status,'warning')
	    },
	    complete: function(json) {
	    	$('#confirmarDeleteNoticiaModal').modal('toggle');
	    }
	});	
}

function confirmarDeleteNoticia(){
	$.ajax({
		url : "confirmar_delete_noticia",
		type : "GET",
		success : function(json) {
			$("#confirmarDeleteNoticia").html(json);
			$('#confirmarDeleteNoticiaModal').modal('show');
	    }
	});
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