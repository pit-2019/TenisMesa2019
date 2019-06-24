var array_productos=[];
$(document).ready(function(){
	console.log('init cart')	
	$('#idInputSearch').hide();
	var data = JSON.parse(sessionStorage.getItem("items_producto"));
	buildIndex(data);
	data = JSON.parse(sessionStorage.getItem("items_producto"));
	build_car_shop(data);
	sumTotalProductos();
	
	$("#idBtnVenta").click(function(event){
		event.preventDefault();
		if(validarVenta()){
			//confirmarVenta();
			confirmarTarjetaCredito();
		}
	});
	
	$(".numberinput").forceNumeric();
	$(".letterinput").forceLetters();
	$(".alphanumericinput").forceAlphaNumeric();
	$(".numberinputdecimal").forceNumericDecimal();
	
});

function buildIndex(data){
	var rows = [];
	for(var i=0;i<data.length;i++){
		rows.push(new itemVenta(
				i,
				data[i].id,
				data[i].descripcion,
				data[i].precio,
				data[i].imageView,
				data[i].cantidad,
				data[i].total))
	}
	sessionStorage.setItem('items_producto', JSON.stringify(rows))
}

function itemVenta (index,id,descripcion,precio,imageView,cantidad,total) {
	this.index = index;
    this.id = id;
    this.descripcion = descripcion;
    this.precio = precio;
    this.imageView = imageView;
    this.cantidad = cantidad;
    this.total = total;
}

function validarVenta(){
	var data_rows = $("#idTableCart tbody tr").length;
	if(data_rows==0){
		show_modal_message('Debe agregar al menos un item');
		return false;
	}
	
	
	var data = JSON.parse(sessionStorage.getItem("items_producto"));
	if(data!=null){
		for(var i=0;i<data.length;i++){
			if(parseInt(data[i].cantidad) == 0){
				show_modal_message('La cantidad debe ser mayor a cero');
				return false;
			}
		}
	}
	
	return true;
}

function modalVenta(){
	$.ajax({
		url : "confirmar_venta",
		type : "GET",
		success : function(json) {
			$("#confirmarVenta").html(json);
			$('#confirmarVentaModal').modal('show');
	    }
	});
}

function confirmarVenta(){
	if(validar_venta_tarjeta_credito()){
		modalVenta();
	}
}

function validar_venta_tarjeta_credito(){
	if ('' == $.trim($('#idNumeroTarjeta').val())) {
		show_modal_message('Debe ingresar numero de tarjeta de credito','danger');
		return false;
	}
	return true;
}

function confirmarTarjetaCredito(){
	var parametros=new Object();
	parametros.importe = $('#idTotalVenta').text();
	
	$.ajax({
		url : "confirmar_tarjeta_credito",
		type : "GET",
		data : parametros,
		success : function(json) {
			$("#confirmarTarjetaCredito").html(json);
			$('#confirmarTarjetaCreditoModal').modal('show');
	    }
	});
}

function DetalleVenta (idProducto,precio,cantidad) {
    this.idProducto = idProducto;
    this.precio = precio;
    this.cantidad = cantidad;
}

function insertarVenta(){
	var rows = [];
	$("#idTableCart tbody tr").each(function () {
		var row = $(this);
		var input_val_precio = row.find("td").find("input.selector-precio").val();
		var input_val_id_producto = row.find("td").find("input.selector-id-producto").val();
		var input_val_cantidad = row.find("td").find("input.cart_quantity_input").val();
		
		rows.push(new DetalleVenta(
				input_val_id_producto,
				input_val_precio,
				input_val_cantidad))
	
	});
	
	registrarVenta(rows)
	$('#idBodyGlobal').loading({
		  theme: 'dark',
		  message: 'Cargando...'
	});
}

function registrarVenta(data){
	var idMontoTotal = $('#idHiddentotalVenta').val()
	
	var parametros=new Object();
	parametros.detalle = JSON.stringify(data);
	parametros.montoTotal = idMontoTotal;
	
	$.ajax({
		url : "insertVenta.json",
		type : "POST",
		data : JSON.stringify(parametros),
		datatype : 'json',
		beforeSend: function(xhr) {
    		xhr.setRequestHeader("Accept", "application/json");
    		xhr.setRequestHeader("Content-Type", "application/json");
    	},
		success : function(json) {
			
			if(json.objeto!=null){
				limpiarListaProducto();
			}
        	
        	show_modal_message(json.mensaje)
        	$('#idBodyGlobal').loading('toggle');
        	
	    },
	    error: function (jqXHR, exception){
	    	showMessage(jqXHR.status,'warning')
	    },
	    complete: function(json) {
	    	$('#confirmarVentaModal').modal('toggle');  
	    	$('#confirmarTarjetaCreditoModal').modal('toggle'); 
	    }
	});
}

function limpiarListaProducto(){
	array_productos = [];
	sessionStorage.setItem('items_producto', JSON.stringify(array_productos))
	$('#idTableCart tbody').empty();
	$('#idTotalVenta').text("$0");
	$('#idHiddentotalVenta').val(0);
}

function sumTotalProductos(){
	var acum_total = 0;
	$("#idTableCart tbody tr").each(function () {
		var row = $(this);
		var input_val = row.find("td").find("input.selector-precio").val();
		if(typeof input_val !== "undefined"){
			acum_total = parseFloat(acum_total)+parseFloat(input_val)
		}
		
	});
	
	$('#idHiddentotalVenta').val(acum_total);
	$('#idTotalVenta').text("$"+acum_total);
	
}

function build_car_shop(data){
	var table_cart = $("#idTableCart tbody");
	for(var i=0;i<data.length;i++){
		var content = (
				"<tr id='idRow"+i+"'>"+
					"<td class='cart_product'> "+
						"<a href=''><img src='data:image/jpeg;base64,"+data[i].imageView+"' alt='' width='110' height='110'></a> "+
					"</td> "+
					"<td class='cart_description' >"+
						"<h4><a href=''>"+data[i].descripcion+"</a></h4> "+
					"</td> "+
					"<td class='cart_price'> "+
						"<p>$"+data[i].precio+"</p> "+
					"</td> "+
					"<td class='cart_quantity'> "+
						"<div class='cart_quantity_button'> "+
							"<a class='cart_quantity_up' href='javascript:addCantidad("+data[i].precio+","+i+");'> + </a> "+
							"<input onchange='javascript:calcularMontoTotal("+data[i].precio+","+i+")' id='idAddShop"+i+"' class='cart_quantity_input numberinput' type='text' name='quantity' value='"+data[i].cantidad+"' autocomplete='off' size='2'> "+
							"<a class='cart_quantity_down' href='javascript:removeCantidad("+data[i].precio+","+i+");'> - </a> "+
						"</div> "+
					"</td> "+
					"<td class='cart_total'> "+
						"<p id='idTotalPrecio"+i+"' class='cart_total_price'>$"+data[i].total+"</p> "+
						"<input id='idHiddenTotalPrecio"+i+"' class='selector-precio' type='hidden' value='"+data[i].total+"'> "+
						"<input id='idHiddenidProducto"+i+"' class='selector-id-producto' type='hidden' value='"+data[i].id+"'> "+
					"</td> "+
					"<td class='cart_delete'> "+
						"<a class='cart_quantity_delete' href='javascript:removeRow("+i+");'><i class='fa fa-times'></i></a> "+
					"</td> "+
				"</tr>"
		);
		table_cart.append(content);	
	}	
}

function removeRow(index){
	$('#idRow'+index).remove();
	
	var data = JSON.parse(sessionStorage.getItem("items_producto"));
	array_productos = [];
	if(data!=null){
		for(var i=0;i<data.length;i++){
			if(data[i].index != index){
				array_productos.push(data[i]);
			}		
		}
	}
	sessionStorage.setItem('items_producto', JSON.stringify(array_productos))
	
}

function calcularMontoTotal(precio,index){
	var cantidad_producto = $('#idAddShop'+index).val();
	if(parseInt(cantidad_producto)!=0){
		var monto_total = precio;		
		var monto_total_new = parseFloat(cantidad_producto)*parseFloat(monto_total)
		$('#idTotalPrecio'+index).text('$'+monto_total_new.toFixed(2));
		$('#idHiddenTotalPrecio'+index).val(monto_total_new.toFixed(2));
	}	
	sumTotalProductos();
	alterItemProducto(cantidad_producto,index)
}

function addCantidad(precio,index){
	var cantidad_producto = $('#idAddShop'+index).val();
	var count = parseInt(cantidad_producto)+parseInt(1);
	var monto_total = precio;
	var monto_total_new = parseFloat(count)*parseFloat(monto_total)
	$('#idAddShop'+index).val(count)
	$('#idTotalPrecio'+index).text('$'+monto_total_new.toFixed(2));
	$('#idHiddenTotalPrecio'+index).val(monto_total_new.toFixed(2));
	sumTotalProductos();
	alterItemProducto(count,index)
}

function alterItemProducto(cantidad_val,index){
	var data = JSON.parse(sessionStorage.getItem("items_producto"));
	array_productos = [];
	if(data!=null){
		for(var i=0;i<data.length;i++){
			if(data[i].index == index){
				data[i].cantidad = cantidad_val
				data[i].total = parseFloat(cantidad_val)*parseFloat(data[i].precio)
			}
			array_productos.push(data[i]);
		}
	}
	sessionStorage.setItem('items_producto', JSON.stringify(array_productos))
}

function removeCantidad(precio,index){
	var cantidad_producto = $('#idAddShop'+index).val();
	if(parseInt(cantidad_producto)!=0){
		var monto_total = precio;		
		var count = parseInt(cantidad_producto)-parseInt(1);
		var monto_total_new = parseFloat(count)*parseFloat(monto_total)
		$('#idAddShop'+index).val(count)
		$('#idTotalPrecio'+index).text('$'+monto_total_new.toFixed(2));
		$('#idHiddenTotalPrecio'+index).val(monto_total_new.toFixed(2));
		sumTotalProductos();
		alterItemProducto(count,index)
	}	
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