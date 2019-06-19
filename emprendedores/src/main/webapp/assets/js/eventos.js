/*JS para manejar los autollenados de los DIV EVENTOS*/

//console.log(myPaisId+"; "+myProvinciaId+"; "+myLocalidadId);

var eventosCreadosBuscados = 0;
var eventosInscriptosBuscados = 0;
var eventosAsistenciaBuscados = 0;

function buscarEventosPublicos() {
	var url='/getEventosPublicos';
	console.log("Buscando eventos publicos url:" + url);
	$('#eventosPortalDiv').load(url);
	console.log("Buscando eventos publicos FIN.");
}

function buscarEventosCreados(id){
	if(!eventosCreadosBuscados){
		eventosCreadosBuscados = 1;
		console.log("Busco eventos creados.");
		var url='/getEventosCreados?jsp=tablaEventosCreados&idUsuario='+id;
		console.log("Buscando eventos creados url:" + url);
		$('#eventosCreadosDiv').load(url);
		console.log("Buscando eventos creados FIN.");
	}
}

function buscarEventosInscriptos(id){
	if(!eventosInscriptosBuscados){
		eventosInscriptosBuscados = 1;
		console.log("Busco eventos inscriptos.");
		var url='/getEventosInscriptos?jsp=tablaEventosInscriptos&idUsuario='+id;
		console.log("Buscando eventos creados url:" + url);
		$('#eventosInscriptosDiv').load(url);
		console.log("Buscando eventos creados FIN.");
	}
}

function buscarEventosAsistencia(id){
	if(!eventosAsistenciaBuscados){
		eventosAsistenciaBuscados = 1;
		console.log("Busco eventos a los que asistire.");
		var url='/getEventosAsistencia?jsp=tablaEventosAsistencia&idUsuario='+id;
		console.log("Buscando eventos a los que asistire url:" + url);
		$('#eventosAsistenciaDiv').load(url);
		console.log("Buscando eventos a los que asistire FIN.");
	}
}