/*JS para manejar el calendario de eventos.*/

var eventDates = {};
var today = new Date();
console.log(today);
loadEvents(today.getFullYear(), today.getMonth()+1);

function loadEvents(year, month){
	$("#datepickerLoading").show();
	$("#datepicker").hide();
	var url='/getEventosCalendario?year='+year+'&month='+month;
	
	console.log('Buscando eventos para calentadio url= '+url);
	
	eventDates = {};
	
	var fechasArray = [];
	$.getJSON(url, function(data) {
		//console.log('Buscando eventos para calentadio rta '+data);
		fechasArray = (new Function("return [" + data+ "];")());
		jQuery.each(fechasArray, function() {
			//console.log('cargo: '+this.toString());
			eventDates[this.toString()] = 'Haz click para ver los eventos de este día!';

			});
		console.log('Buscando eventos para calentadio FIN.');
		$("#datepickerLoading").hide();
		$("#datepicker").show();
		$("#datepicker").datepicker( "refresh" );
	});
}

$(function() {
	$("#datepicker").datepicker({
		altFormat: "yyyymmdd",
		beforeShowDay : function(date) {
			
			var day = date.getDate();			
			var month = date.getMonth()+1;			
			if(month < 10) month = "0".concat(month);
			if(day < 10) day = "0".concat(day);
			
			var dateKey= date.getFullYear().toString().concat(month).concat(day);
			//console.log('consulto: '+dateKey);
			var highlight = eventDates[dateKey];
			if (highlight) {
				//console.log(highlight);
				return [ true, "event", highlight ];
			} else {
				return [ false, '', '' ];
			}
			return [ false, '', '' ];
		},

		onSelect : function(date) {
			console.log('fecha elegida '+ date);
			var fecha = new Date(date);
			buscarEventosPorDia(fecha.getFullYear(),fecha.getMonth()+1,fecha.getDate());
		},

		onChangeMonthYear : function(year, month) {
			console.log('onChangeMonthYear: ' + year + '_' + month);
			loadEvents(year, month);
			
		}

	});
});

function buscarEventosPorDia(year, month, day) {
	$('#tituloListaEventos').html('Eventos del día '+day+'-'+month+'-'+	year);
	$("#eventosPortalDiv").load("loading.jsp");
	var url='/getEventosFiltrados?year='+year+'&month='+month+'&day='+day;
	console.log("Buscando eventos filtrados url:" + url);
	$('#eventosPortalDiv').load(url);
	console.log("Buscando eventos filtrados FIN.");
}
