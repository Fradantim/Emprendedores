<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="es">
<head>
	
	<title>Modificar Evento</title>
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
							<form:form method="POST" action="${contextPath}/modificarEvento" modelAttribute="eventoForm" class="form-signin" >
								<h2 class="form-signin-heading">Modifica tu Evento</h2>
								<input id="fecha" name="idEvento" type="hidden" value="${eventoGuardado.id}">
								<jsp:include page="plantillaEvento.jsp"/>
								<button class="btn btn-lg btn-primary btn-block" type="submit">Modificar Evento!</button>
							</form:form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Footer Wrapper -->
		<div id="footer-wrapper">
			<jsp:include page="footerNav.jsp"/>
		</div>
	</div>
	<script>
		var myPaisId ="${eventoGuardado.localidad.paisId}";
		var myProvinciaId ="${eventoGuardado.localidad.provinciaId}";
		var myLocalidadId ="${eventoGuardado.localidad.id}";
	</script>
	<script src="${contextPath}/assets/js/jquery.datetimepicker.full.min.js"></script>
	<script src="${contextPath}/assets/js/ubicacion.js"></script>
	<script src="${contextPath}/assets/js/fecha.js"></script>
</body>
</html>