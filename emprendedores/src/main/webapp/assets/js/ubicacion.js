/*JS para manejar los autollenados de los DIV PAIS / PROVINCIA / LOCALIDAD*/

window.onload=buscarPaises;

var delay=50;

function buscarPaises() {
	var url='/getSelectPais';
	console.log("Buscando paises url:" + url);
	$('#paisDiv').html('Aguarde...');
	$('#paisDiv').load(url);
	console.log("Fin Buscando paises.");
	setTimeout(function(){
		buscarProvincias();
	}, delay);
}

function buscarProvincias() {
	var idPais=$("#paisId").val();
	var url='/getSelectProvincia?paisId='+idPais;
	console.log("Buscando provincias url:" + url);
	if(idPais == null || idPais == ''){
		$('#provinciaDiv').html('Elija un Pais');
		console.log("No hay pais.");
	} else {
		$('#provinciaDiv').html('Aguarde...');
		$('#provinciaDiv').load(url);
		console.log("Fin Buscando Provincias.");
	}
	setTimeout(function(){
		buscarLocalidades();
	}, delay);
}

function buscarLocalidades() {
	var idProv=$("#provinciaId").val();
	var url='/getSelectLocalidad?provinciaId='+idProv;
	console.log("Buscando localidades url:" + url);
	if(idProv == null || idProv == ''){
		$('#localidadDiv').html('Elija una Provincia');
		console.log("No hay provincia.")
	} else {
		$('#localidadDiv').html('Aguarde...');
		$('#localidadDiv').load(url);
		console.log("Fin Buscando localidades.");
	}
}
