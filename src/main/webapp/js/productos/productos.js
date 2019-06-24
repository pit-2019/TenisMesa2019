var array_productos = [];
var data_table_producto = null;
var idProducto = null;
$(document).ready(function(){
	console.log("init productos")
	$('#idInputSearch').hide();
	data_table_producto = $('#dataTableProducto').DataTable({
		  "pageLength": 5,
    	  "columns": [
	    	  { "data": "id" },
	    	  { "data": "descripcion" },
	    	  { "data": "precio" },
	    	  { "data": "destacado",
	    		  render: function (data, type, row) {
            		  var result = "";
            		  if(data==="S"){
            			  result = 'Si'
            		  }else{
            			  result = 'No'
            		  }
            		  return result;
                  }    
	    	  }]
	});
	getProductos();
	
	$('#dataTableProducto tbody').on('click', 'tr', function () {
		$("#dataTableProducto tbody tr").removeClass('row_selected');  
		$(this).addClass('row_selected');
	    idProducto = data_table_producto.row( this ).data().id; 
	});
	
	$("#btnEditarProducto").click(function(){
		if(idProducto!=null && parseInt(idProducto)>parseInt(0)){
			window.location = 'actualizar_producto?id='+idProducto+'';
		}else{
			showMessage('Debe seleccionar un producto','info')
		}
	});
	
	$("#btnEliminarProducto").click(function(){
		if(idProducto!=null && parseInt(idProducto)>parseInt(0)){
			confirmarDeleteProducto();
		}else{
			showMessage('Debe seleccionar un producto','info')
		}
	});
});

function getProductos(){
	$.ajax({
		url : "rest/getProductos.json",
		type : "GET",
		datatype : 'json',
		beforeSend: function(xhr) {
    		xhr.setRequestHeader("Accept", "application/json");
    		xhr.setRequestHeader("Content-Type", "application/json");
    	},
		success : function(json) {	
			var data = json.rows;
			for(i=0 ; i<data.length ; i++){
				data_table_producto.rows.add( [{
					id : data[i].id,
					descripcion : data[i].descripcion,
					precio : data[i].precio,
					destacado : data[i].destacado,
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

function deleteProducto(){
	var parametros=new Object();
	parametros.id=idProducto;
	
	$.ajax({
		url : "rest/deleteProducto.json",
		type : "GET",
		data : parametros,
		datatype : 'json',
		beforeSend: function(xhr) {
    		xhr.setRequestHeader("Accept", "application/json");
    		xhr.setRequestHeader("Content-Type", "application/json");
    	},
		success : function(json) {
        	showMessage(json.mensaje,'info')
        	data_table_producto.row('.row_selected').remove().draw( false );
	    },
	    error: function (jqXHR, exception){
	    	showMessage(jqXHR.status,'warning')
	    },
	    complete: function(json) {
	    	$('#confirmarDeleteProductoModal').modal('toggle');
	    }
	});	
}

function confirmarDeleteProducto(){
	$.ajax({
		url : "confirmar_delete_producto",
		type : "GET",
		success : function(json) {
			$("#confirmarDeleteProducto").html(json);
			$('#confirmarDeleteProductoModal').modal('show');
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