

function buscarEmprendedores() {
	var url='/getListaEmprendedores';
	console.log("Buscando empnrenderores publicos url:" + url);
	$('#divListaEmprendedores').load(url);
	console.log("Buscando emprendedores publicos FIN.");
}