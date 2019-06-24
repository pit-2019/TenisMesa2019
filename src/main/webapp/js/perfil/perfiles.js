var array_productos = [];
var data_table_perfil = null;
var idPerfil = null;
$(document).ready(function(){
	console.log("init perfiles")
	$('#idInputSearch').hide();
	data_table_perfil = $('#dataTablePerfil').DataTable({
		  "pageLength": 5,
    	  "columns": [
	    	  { "data": "id" },
	    	  { "data": "nombre" },
	    	  { "data": "apellidoPaterno" },
	    	  { "data": "apellidoMaterno" },
	    	  { "data": "pais" },
	    	  { "data": "ranking" },
	    	  { "data": "descripcion" }]
	});
	getPerfiles();
	
	$('#dataTablePerfil tbody').on('click', 'tr', function () {
		$("#dataTablePerfil tbody tr").removeClass('row_selected');  
		$(this).addClass('row_selected');
	    idPerfil = data_table_perfil.row( this ).data().id; 
	});
	
	$("#btnEditarPerfil").click(function(){
		if(idPerfil!=null && parseInt(idPerfil)>parseInt(0)){
			window.location = 'actualizar_perfil?id='+idPerfil+'';
		}else{
			showMessage('Debe seleccionar un perfil','info')
		}
	});
	
	$("#btnEliminarPerfil").click(function(){
		if(idPerfil!=null && parseInt(idPerfil)>parseInt(0)){
			confirmarDeletePerfil();
		}else{
			showMessage('Debe seleccionar un perfil','info')
		}
	});
});

function getPerfiles(){
	$.ajax({
		url : "rest/getPerfiles.json",
		type : "GET",
		datatype : 'json',
		beforeSend: function(xhr) {
    		xhr.setRequestHeader("Accept", "application/json");
    		xhr.setRequestHeader("Content-Type", "application/json");
    	},
		success : function(json) {	
			var data = json.rows;
			for(i=0 ; i<data.length ; i++){
				data_table_perfil.rows.add( [{
					id : data[i].id,
					nombre : data[i].nombre,
					apellidoPaterno : data[i].apellidoPaterno,
					apellidoMaterno : data[i].apellidoMaterno,
					pais : data[i].pais,
					ranking : data[i].ranking,
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

function deletePerfil(){
	var parametros=new Object();
	parametros.id=idPerfil;
	
	$.ajax({
		url : "rest/deletePerfil.json",
		type : "GET",
		data : parametros,
		datatype : 'json',
		beforeSend: function(xhr) {
    		xhr.setRequestHeader("Accept", "application/json");
    		xhr.setRequestHeader("Content-Type", "application/json");
    	},
		success : function(json) {
        	showMessage(json.mensaje,'info')
        	data_table_perfil.row('.row_selected').remove().draw( false );
	    },
	    error: function (jqXHR, exception){
	    	showMessage(jqXHR.status,'warning')
	    },
	    complete: function(json) {
	    	$('#confirmarDeletePerfilModal').modal('toggle');
	    }
	});	
}

function confirmarDeletePerfil(){
	$.ajax({
		url : "confirmar_delete_perfil",
		type : "GET",
		success : function(json) {
			$("#confirmarDeletePerfil").html(json);
			$('#confirmarDeletePerfilModal').modal('show');
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