var array_productos = [];
var idUsuario = null;
var data_table_usuario = null;
$(document).ready(function(){
	console.log("init usuarios")
	$('#idInputSearch').hide();
	data_table_usuario = $('#dataTableUsuario').DataTable({
		  "pageLength": 5,
    	  "columns": [
	    	  { "data": "id" },
	    	  { "data": "username" },
	    	  { "data": "descripcionRol" }]
	});
	getUsuarios();
	
	$('#dataTableUsuario tbody').on('click', 'tr', function () {
		$("#dataTableUsuario tbody tr").removeClass('row_selected');  
		$(this).addClass('row_selected');
	    idUsuario = data_table_usuario.row( this ).data().id; 
	});
	
	$("#btnEditarUsuario").click(function(){
		if(idUsuario!=null && parseInt(idUsuario)>parseInt(0)){
			window.location = 'actualizar_usuario?id='+idUsuario+'';
		}else{
			showMessage('Debe seleccionar un usuario','info')
		}
	});
	
	$("#btnEliminarUsuario").click(function(){
		if(idUsuario!=null && parseInt(idUsuario)>parseInt(0)){
			confirmarDeleteUsuario();
		}else{
			showMessage('Debe seleccionar una usuario','info')
		}
	});
});

function getUsuarios(){
	$.ajax({
		url : "rest/getUsuarios.json",
		type : "GET",
		datatype : 'json',
		beforeSend: function(xhr) {
    		xhr.setRequestHeader("Accept", "application/json");
    		xhr.setRequestHeader("Content-Type", "application/json");
    	},
		success : function(json) {	
			var data = json.rows;
			for(i=0 ; i<data.length ; i++){
				data_table_usuario.rows.add( [{
					id : data[i].id,
					username : data[i].username,
					descripcionRol : data[i].descripcionRol
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

function deleteUsuario(){
	var parametros=new Object();
	parametros.id=idUsuario;
	
	$.ajax({
		url : "rest/deleteUsuario.json",
		type : "GET",
		data : parametros,
		datatype : 'json',
		beforeSend: function(xhr) {
    		xhr.setRequestHeader("Accept", "application/json");
    		xhr.setRequestHeader("Content-Type", "application/json");
    	},
		success : function(json) {
        	showMessage(json.mensaje,'info')
        	data_table_usuario.row('.row_selected').remove().draw( false );
	    },
	    error: function (jqXHR, exception){
	    	showMessage(jqXHR.status,'warning')
	    },
	    complete: function(json) {
	    	$('#confirmarDeleteUsuarioModal').modal('toggle');
	    }
	});	
}

function confirmarDeleteUsuario(){
	$.ajax({
		url : "confirmar_delete_usuario",
		type : "GET",
		success : function(json) {
			$("#confirmarDeleteUsuario").html(json);
			$('#confirmarDeleteUsuarioModal').modal('show');
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