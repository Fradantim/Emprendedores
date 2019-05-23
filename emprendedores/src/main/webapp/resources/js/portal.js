window.onload = function aa(){
	console.log("portal.js cargado");
}


function logInPopUp(){

	console.log('holi');
	
	$('.modal-body').load("/logBox",function(result){				
		$('#modal-popUp .modal-title').text(''); //titulo > Acceso a usuarios
	    $('#modal-popUp').modal({show:true});
	});
	
	console.log('chau');
}