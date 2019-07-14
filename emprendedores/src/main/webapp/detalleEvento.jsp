<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
									<h2>Creador: <a href="${contextPath}/detalleEmprendedor?idEmprendedor=${evento.creador.id}">${evento.creador.nick}</a> </h2><br>
								</header>
								
								<h3>Cuando: ${evento.fecha} </h3><br>
								
								<h3>Donde: ${evento.localidad.nombre}, ${evento.localidad.provinciaNombre} - ${evento.localidad.paisNombre}</h3> <br> 
								<button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseEmprendedores" aria-expanded="false" aria-controls="collapseEmprendedores">
									Ver Emprendedores Confirmados
								</button>
								<div class="collapse" id="collapseEmprendedores">
									<div class="card card-body" style="overflow-x:auto;  max-height: 250px;">
										<table class="table table-striped">
											<tbody>
												<c:set var="index" value="1" />
												
												<c:forEach items="${evento.emprendedores}" var="emp">
													<tr>
														<td scope="row">${index}</td>
														<td><a href="${contextPath}/detalleEmprendedor?idEmprendedor=${emp.id}">${emp.nick}</a></td>
													</tr>
													<c:set var="index" value="${index + 1}" />
												</c:forEach>
												
												<c:forEach var="i" begin="${index}" end="${evento.cantidadMaxInscripcion}">
													<tr>
														<td scope="row">${i}</td>
														<td> - </td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
								
								<hr>
								<!--  <span class="image featured"><img src="../assets/css/images/pic08.jpg" alt="" /></span>-->
							
								<h2>Descripción del evento<br></h2>
								<p>${evento.descripcionLarga}</p>
								
								<c:if test="${! empty evento.mapa}">
									<hr>
									<h2>Mapa</h2>
									<!--Google map-->
									<div id="map-container-google-2" class="z-depth-1-half map-container-2">
									  ${evento.mapa}
									</div>
									<!--Google Maps-->
								</c:if>
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