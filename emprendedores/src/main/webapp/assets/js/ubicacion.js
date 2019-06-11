/*JS para manejar los autollenados de los DIV PAIS / PROVINCIA / LOCALIDAD*/

window.onload=buscarPaises;

var delay=10;

console.log(myPaisId+"; "+myProvinciaId+"; "+myLocalidadId);

function buscarPaises() {
	var url='/getSelectPais?selectedId='+myPaisId;
	console.log("Buscando paises url:" + url);
	$('#paisDiv').html('Aguarde...');
	$('#paisDiv').load(url);
	console.log("Fin Buscando paises.");
	buscarProvincias();
}

function buscarProvincias() {
	waitForLoad('paisId', setProvincias);
}

function setProvincias() {
	var idPais=$("#paisId").val();
	if(idPais == 'X'){
		$('#provinciaDiv').html('Elija un Pais');
		console.log("No hay pais, no busco provincias.");
	} else {
		var url='/getSelectProvincia?paisId='+idPais;
		if(myProvinciaId != null && myProvinciaId != ''){
			url = url+'&selectedId='+myProvinciaId;
		}
		console.log("Buscando provincias url:" + url);
		$('#provinciaDiv').html('Aguarde...');
		$('#provinciaDiv').load(url);
		console.log("Fin Buscando Provincias.");
	}
	buscarLocalidades();
}

function buscarLocalidades() {
	var idPais=$("#paisId").val();
	if(idPais != 'X'){
		waitForLoad('provinciaId', setLocalidades);
	} else {
		setLocalidades();
	}
}

function setLocalidades(){
	var idPais=$("#paisId").val();
	var idProv=$("#provinciaId").val();
	if(idProv == 'X' || idPais == 'X'){
		$('#localidadDiv').html('Elija una Provincia');
		console.log("No hay provincia, no busco localidades.")
	} else {
		var url='/getSelectLocalidad?provinciaId='+idProv;
		if(myLocalidadId != null && myLocalidadId != ''){
			url = url +'&selectedId='+myLocalidadId;
		}
		console.log("Buscando localidades url:" + url);
		$('#localidadDiv').html('Aguarde...');
		$('#localidadDiv').load(url);
		console.log("Fin Buscando localidades.");
	}
}

function waitForLoad(domID, _nextFunction) {
	setTimeout(function() {
		var id = $("#" + domID).val();
		if (id === undefined || id == null || id == '') {
			console.log('['+domID+'] no seteado, espero '+delay+'ms'+id);
			waitForLoad(domID, _nextFunction);
		} else {
			console.log('['+domID+'] seteado('+id+'), continuo.');
			_nextFunction();
		}
	}, delay)
}