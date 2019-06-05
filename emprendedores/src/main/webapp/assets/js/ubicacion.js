/*JS para manejar los autollenados de los DIV PAIS / PROVINCIA / LOCALIDAD*/

window.onload=buscarPaises;

var delay=250;

console.log(myPaisId+"; "+myProvinciaId+"; "+myLocalidadId);

function buscarPaises() {
	var url='/getSelectPais';
	console.log("Buscando paises url:" + url);
	$('#paisDiv').html('Aguarde...');
	$('#paisDiv').load(url);
	console.log("Fin Buscando paises.");
	setTimeout(function(){
		$('#paisId').val(myPaisId);
		buscarProvincias();
	}, delay);
}

function buscarProvincias() {
	var idPais=$("#paisId").val();
	if(idPais == null || idPais == ''){
		$('#provinciaDiv').html('Elija un Pais');
		console.log("No hay pais, no busco provincias.");
	} else {
		var url='/getSelectProvincia?paisId='+idPais;
		console.log("Buscando provincias url:" + url);
		$('#provinciaDiv').html('Aguarde...');
		$('#provinciaDiv').load(url);
		console.log("Fin Buscando Provincias.");
	}
	setTimeout(function(){
		if($('#paisId').val() == myPaisId){
			$('#provinciaId').val(myProvinciaId);
		}
		buscarLocalidades();
	}, delay);
}

function buscarLocalidades() {
	var idProv=$("#provinciaId").val();
	if(idProv == null || idProv == ''){
		$('#localidadDiv').html('Elija una Provincia');
		console.log("No hay provincia, no busco localidades.")
	} else {
		var url='/getSelectLocalidad?provinciaId='+idProv;
		console.log("Buscando localidades url:" + url);
		$('#localidadDiv').html('Aguarde...');
		$('#localidadDiv').load(url);
		console.log("Fin Buscando localidades.");
	}
	setTimeout(function(){
		if($('#paisId').val() == myPaisId && $('#provinciaId').val() == myProvinciaId){
			$('#localidadId').val(myLocalidadId);
		}
	}, delay);
}