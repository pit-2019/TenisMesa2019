var array_productos = [];
var idCategoria = null;
var data_table_categoria = null;
$(document).ready(function(){
	console.log("init categorias")
	$('#idInputSearch').hide();
	data_table_categoria = $('#dataTableCategoria').DataTable({
		  "pageLength": 5,
    	  "columns": [
	    	  { "data": "id" },
	    	  { "data": "descripcion" }]
	});
	getCategorias();
	
	$('#dataTableCategoria tbody').on('click', 'tr', function () {
		$("#dataTableCategoria tbody tr").removeClass('row_selected');  
		$(this).addClass('row_selected');
	    idCategoria = data_table_categoria.row( this ).data().id; 
	});
	
	$("#btnEditarCategoria").click(function(){
		if(idCategoria!=null && parseInt(idCategoria)>parseInt(0)){
			window.location = 'actualizar_categoria?id='+idCategoria+'';
		}else{
			showMessage('Debe seleccionar un categoria','info')
		}
	});
	
	$("#btnEliminarCategoria").click(function(){
		if(idCategoria!=null && parseInt(idCategoria)>parseInt(0)){
			confirmarDeleteCategoria();
		}else{
			showMessage('Debe seleccionar una categoria','info')
		}
	});
});

function getCategorias(){
	$.ajax({
		url : "rest/getCategorias.json",
		type : "GET",
		datatype : 'json',
		beforeSend: function(xhr) {
    		xhr.setRequestHeader("Accept", "application/json");
    		xhr.setRequestHeader("Content-Type", "application/json");
    	},
		success : function(json) {	
			var data = json.rows;
			for(i=0 ; i<data.length ; i++){
				data_table_categoria.rows.add( [{
					id : data[i].id,
					descripcion : data[i].descripcion
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

function deleteCategoria(){
	var parametros=new Object();
	parametros.id=idCategoria;
	
	$.ajax({
		url : "rest/deleteCategoria.json",
		type : "GET",
		data : parametros,
		datatype : 'json',
		beforeSend: function(xhr) {
    		xhr.setRequestHeader("Accept", "application/json");
    		xhr.setRequestHeader("Content-Type", "application/json");
    	},
		success : function(json) {
        	showMessage(json.mensaje,'info')
        	data_table_categoria.row('.row_selected').remove().draw( false );
	    },
	    error: function (jqXHR, exception){
	    	showMessage(jqXHR.status,'warning')
	    },
	    complete: function(json) {
	    	$('#confirmarDeleteCategoriaModal').modal('toggle');
	    }
	});	
}

function confirmarDeleteCategoria(){
	$.ajax({
		url : "confirmar_delete_categoria",
		type : "GET",
		success : function(json) {
			$("#confirmarDeleteCategoria").html(json);
			$('#confirmarDeleteCategoriaModal').modal('show');
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