function show_modal_message(message){
	var modal_confirmation_action = $('#idModalConfirmationAction');
    modal_confirmation_action.find('.modal-body').text(message);    	
    modal_confirmation_action.modal('toggle')
}
function closeAllValidations() {
	$('.validation-alert').hide();
}

function showMessage(message, type, messageId) {
	messageId = messageId || '#messages-alert';

	if ($(messageId).length == 0) {
		console.warn('Element ' + messageId + ' no existe');
		return;
	}
	$(messageId).hide().fadeIn().find('.message-content').text(message).end()
			.removeClass('alert-info alert-success alert-warning alert-danger')
			.addClass('alert-' + type).fadeOut(1500);
	if ($(messageId)[0].scrollIntoViewIfNeeded) {
		$(messageId)[0].scrollIntoViewIfNeeded();
	}
}

jQuery.fn.forceEmail = function() {
	return this.each(function() {
		var input = this;
		var state = {
		    value: input.value,
		    start: input.selectionStart,
		    end: input.selectionEnd,
		    pattern: RegExp('^([a-zA-Z0-9\@\.\\-\_\]*)$')
		};
		input.addEventListener('input', function(event) {
		    if (state.pattern.test(input.value)) {
		      state.value = input.value;
		    } else {
		      input.value = state.value;
		      input.setSelectionRange(state.start, state.end);
		    }
		});
		input.addEventListener('keydown', function(event) {
			state.start = input.selectionStart;
			state.end = input.selectionEnd;
		});
	});
}

jQuery.fn.forceNumeric = function() {
	return this.each(function() {
		var input = this;
		var state = {
		    value: input.value,
		    start: input.selectionStart,
		    end: input.selectionEnd,
		    pattern: RegExp('^([0-9]*)$')
		};
		input.addEventListener('input', function(event) {
		    if (state.pattern.test(input.value)) {
		      state.value = input.value;
		    } else {
		      input.value = state.value;
		      input.setSelectionRange(state.start, state.end);
		    }
		});
		input.addEventListener('keydown', function(event) {
			state.start = input.selectionStart;
			state.end = input.selectionEnd;
		});
	});
}

jQuery.fn.forceNumericDecimal = function() {
	return this.each(function() {
		var input = this;
		var state = {
		    value: input.value,
		    start: input.selectionStart,
		    end: input.selectionEnd,
		    pattern: RegExp('^([0-9\.]*)$')
		};
		input.addEventListener('input', function(event) {
		    if (state.pattern.test(input.value)) {
		      state.value = input.value;
		    } else {
		      input.value = state.value;
		      input.setSelectionRange(state.start, state.end);
		    }
		});
		input.addEventListener('keydown', function(event) {
			state.start = input.selectionStart;
			state.end = input.selectionEnd;
		});
	});
}

jQuery.fn.forceLetters = function() {
	return this.each(function() {
		var input = this;
		var state = {
		    value: input.value,
		    start: input.selectionStart,
		    end: input.selectionEnd,
		    pattern: RegExp('^([a-zA-Z\\s\u00f1\u00d1\u00e1\u00e9\u00ed\u00f3\u00fa\u00c1\u00c9\u00cd\u00d3\u00da]*)$')
		};
		input.addEventListener('input', function(event) {
		    if (state.pattern.test(input.value)) {
		      state.value = input.value;
		    } else {
		      input.value = state.value;
		      input.setSelectionRange(state.start, state.end);
		    }
		});
		input.addEventListener('keydown', function(event) {
			state.start = input.selectionStart;
			state.end = input.selectionEnd;
		});
	});
}

jQuery.fn.forceAlphaNumeric = function() {
	return this.each(function() {
		var input = this;
		var state = {
		    value: input.value,
		    start: input.selectionStart,
		    end: input.selectionEnd,
		    pattern: RegExp('^([a-zA-Z0-9\\s\u00f1\u00d1\u00e1\u00e9\u00ed\u00f3\u00fa\u00c1\u00c9\u00cd\u00d3\u00da]*)$')
		};
		input.addEventListener('input', function(event) {
		    if (state.pattern.test(input.value)) {
		      state.value = input.value;
		    } else {
		      input.value = state.value;
		      input.setSelectionRange(state.start, state.end);
		    }
		});
		input.addEventListener('keydown', function(event) {
			state.start = input.selectionStart;
			state.end = input.selectionEnd;
		});
	});
}

function cargaValidacion(){
	$(".numberinput").forceNumeric();
	$(".letterinput").forceLetters();
	$(".alphanumericinput").forceAlphaNumeric();
	$(".numberinputdecimal").forceNumericDecimal();
}

function logout_method(){
	array_productos = [];
	sessionStorage.setItem('items_producto', JSON.stringify(array_productos))
	window.location = "logout.html";
}