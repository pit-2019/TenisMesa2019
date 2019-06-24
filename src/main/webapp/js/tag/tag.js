var array_productos = [];
var idTag = null;
var data_table_tag = null;
$(document).ready(function(){
	console.log("init tags")
	$('#idInputSearch').hide();
	data_table_tag = $('#dataTableTag').DataTable({
		  "pageLength": 5,
    	  "columns": [
	    	  { "data": "id" },
	    	  { "data": "descripcion" }]
	});
	getTags();
	
	$('#dataTableTag tbody').on('click', 'tr', function () {
		$("#dataTableTag tbody tr").removeClass('row_selected');  
		$(this).addClass('row_selected');
	    idTag = data_table_tag.row( this ).data().id; 
	});
	
	$("#btnEditarTag").click(function(){
		if(idTag!=null && parseInt(idTag)>parseInt(0)){
			window.location = 'actualizar_tag?id='+idTag+'';
		}else{
			showMessage('Debe seleccionar un tag','info')
		}
	});
	
	$("#btnEliminarTag").click(function(){
		if(idTag!=null && parseInt(idTag)>parseInt(0)){
			confirmarDeleteTag();
		}else{
			showMessage('Debe seleccionar una tag','info')
		}
	});
});

function getTags(){
	$.ajax({
		url : "rest/getTags.json",
		type : "GET",
		datatype : 'json',
		beforeSend: function(xhr) {
    		xhr.setRequestHeader("Accept", "application/json");
    		xhr.setRequestHeader("Content-Type", "application/json");
    	},
		success : function(json) {	
			var data = json.rows;
			for(i=0 ; i<data.length ; i++){
				data_table_tag.rows.add( [{
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

function deleteTag(){
	var parametros=new Object();
	parametros.id=idTag;
	
	$.ajax({
		url : "rest/deleteTag.json",
		type : "GET",
		data : parametros,
		datatype : 'json',
		beforeSend: function(xhr) {
    		xhr.setRequestHeader("Accept", "application/json");
    		xhr.setRequestHeader("Content-Type", "application/json");
    	},
		success : function(json) {
        	showMessage(json.mensaje,'info')
        	data_table_tag.row('.row_selected').remove().draw( false );
	    },
	    error: function (jqXHR, exception){
	    	showMessage(jqXHR.status,'warning')
	    },
	    complete: function(json) {
	    	$('#confirmarDeleteTagModal').modal('toggle');
	    }
	});	
}

function confirmarDeleteTag(){
	$.ajax({
		url : "confirmar_delete_tag",
		type : "GET",
		success : function(json) {
			$("#confirmarDeleteTag").html(json);
			$('#confirmarDeleteTagModal').modal('show');
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