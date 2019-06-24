var data_table_compras = null;
$(document).ready(function(){
	data_table_compras = $('#dataTableCompras').DataTable({
		  "pageLength": 5,
  	  "columns": [
	    	  { "data": "id" },
	    	  { "data": "fechaCompra" },
	    	  { "data": "descripcionCategoria" },
	    	  { "data": "descripcionProducto" },
	    	  { "data": "cantidad" },
	    	  { "data": "precio" },
	    	  { "data": "username" }]
	});
	
	getComprasByUsuario();
});

function getComprasByUsuario(){
	$.ajax({
		url : "rest/getComprasByUsuario.json",
		type : "GET",
		datatype : 'json',
		beforeSend: function(xhr) {
    		xhr.setRequestHeader("Accept", "application/json");
    		xhr.setRequestHeader("Content-Type", "application/json");
    	},
		success : function(json) {	
			var data = json.rows;
			console.log(data)
			for(i=0 ; i<data.length ; i++){
				data_table_compras.rows.add( [{
					id : data[i].id,
					fechaCompra : moment(data[i].fechaCompra).locale('es').format('DD/MM/YYYY'),
					descripcionCategoria : data[i].descripcionCategoria,
					descripcionProducto : data[i].descripcionProducto,
					cantidad : data[i].cantidad,
					precio : data[i].precio,
					username : data[i].username
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