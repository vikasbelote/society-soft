function showValidationMsg(titleName, msgText) {
	
	$.gritter.add({
		title: titleName,
		text: msgText,
		sticky: true,
		time: '',
		class_name: 'gritter-error'
	});
}