<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="es">
<head>
	
	<title>Crear Evento</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
	<link rel="stylesheet" href="${contextPath}/assets/css/jquery.datetimepicker.min.css" />
	
	
</head>
<body class="homepage is-preload">
	<div id="page-wrapper">
		<!-- Header -->
		<div id="header-wrapper">
			<div class="container">
				<header id="header">
					<div class="inner">
						<jsp:include page="navBar.jsp"/>
					</div>
				</header>
			</div>
		</div>
		
		<!-- Main Wrapper -->
		<div id="main-wrapper">
			<div class="wrapper style1">
				<div class="inner">
					<div class="container">
						<div class="col-md-12">
							<form:form method="POST" action="${contextPath}/crearEvento" modelAttribute="eventoForm" class="form-signin" >
								<h2 class="form-signin-heading">Crea tu Evento</h2>
								<jsp:include page="plantillaEvento.jsp"/>
								<button class="btn btn-lg btn-primary btn-block" type="submit">Crear Evento!</button>
							</form:form>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<!-- Modal -->
		<jsp:include page="${contextPath}/modalTutoEventoMapa.jsp"/>
 	
		<!-- Footer Wrapper -->
		<div id="footer-wrapper">
			<jsp:include page="footerNav.jsp"/>
		</div>
	</div>
	<script>
		var myPaisId ="${usuarioLogueado.localidad.paisId}";
		var myProvinciaId ="${usuarioLogueado.localidad.provinciaId}";
		var myLocalidadId ="${usuarioLogueado.localidad.id}";
	</script>
	<script src="${contextPath}/assets/js/jquery.datetimepicker.full.min.js"></script>
	<script src="${contextPath}/assets/js/ubicacion.js"></script>
	<script src="${contextPath}/assets/js/fecha.js"></script>
</body>
</html>