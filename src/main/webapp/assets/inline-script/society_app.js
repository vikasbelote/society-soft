function showValidationMsg(titleName, msgText) {
	
	$.gritter.add({
		title: titleName,
		text: msgText,
		sticky: true,
		time: '',
		class_name: 'gritter-error'
	});
}

$('.date-picker').datepicker({
	autoclose: true,
	todayHighlight: true
});


function validateEmail(email) {
    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}