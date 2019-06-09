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
	
	<style>
		.map-container-2
		{
			overflow:hidden;
			padding-bottom:50%;
			position:relative;
			height:0;
		}
		.map-container-2 iframe
		{
			left:0;
			top:0;
			height:100%;
			width:100%;
			position:absolute;
		}
	</style>
	
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
							<security:authorize access="hasAuthority('EMPRENDEDOR')" var="isEmp" />
							<security:authorize access="hasAuthority('MODERADOR')" var="isMod" />
							<security:authorize access="hasAuthority('ADMINISTRADOR')" var="isAdmin" />
							<c:if test="${pageContext.request.userPrincipal.name != null}">
								<c:if test="${(evento.creador.id == usuarioLogueado.id || isMod || isAdmin) && ! e.finalizado}">
									<button type="button" class="btn btn-warning btn-sm" onclick="location.href='${contextPath}/modificarEvento?idEvento=${evento.id}';">Modificar</button>
									<button type="button" class="btn btn-danger btn-sm" onclick="location.href='${contextPath}/borrarEvento?idEvento=${evento.id}';">Borrar</button>
								</c:if>
								<c:if test="${evento.abierto && !evento.finalizado && (evento.creador.id != usuarioLogueado.id)}">
									<c:if test="${isEmp}">
										<c:choose>
											<c:when test="${evento.inscripto}">
												<button type="button" class="btn btn-secondary btn-sm" onclick="location.href='${contextPath}/desinscribirmeEvento?idEvento=${evento.id}';">Desinscribirme</button>
											</c:when>
											<c:otherwise>
												<button type="button" class="btn btn-secondary btn-sm" onclick="location.href='${contextPath}/inscribirmeEvento?idEvento=${evento.id}';">Inscribirme</button>
											</c:otherwise>
										</c:choose>
									</c:if>
									<c:choose>
										<c:when test="${evento.asiste}">
											<button type="button" class="btn btn-outline-secondary btn-sm text-dark" onclick="location.href='${contextPath}/desasistirEvento?idEvento=${evento.id}&idUsuario=${usuarioLogueado.id}';">No asistire</button>
										</c:when>
										<c:otherwise>
											<button type="button" class="btn btn-outline-secondary btn-sm text-dark" onclick="location.href='${contextPath}/asistirEvento?idEvento=${evento.id}&idUsuario=${usuarioLogueado.id}';">Asistire</button>
										</c:otherwise>
									</c:choose>
								</c:if>
							</c:if>
							<hr>
							<article>
								<header class="major">
									<h2>Evento: ${evento.nombre} </h2>
									<h2>Creador: ${evento.creador.nick} </h2><br>
								</header>
								
								<h3>Cuando: ${evento.fecha} </h3><br>
								<h3>Donde: ${evento.localidad.nombre}, ${evento.localidad.provinciaNombre} - ${evento.localidad.paisNombre}</h3> <br>
								
								<hr>
								<!--  <span class="image featured"><img src="../assets/css/images/pic08.jpg" alt="" /></span>-->
							
								<h2>Descripción del evento<br></h2>
								<p>${evento.descripcionLarga}</p>
								
								<hr>
								
								<c:if test="${! empty evento.mapa}">
									<!--Google map-->
									<div id="map-container-google-2" class="z-depth-1-half map-container-2">
									  ${evento.mapa}
									</div>
									<!--Google Maps-->
								</c:if>
								<h2>Mapa</h2>
							</article>
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
</body>
</html>