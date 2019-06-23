<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="es">
<head>
<title>Mi Perfil</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="${contextPath}/assets/bootstrap-4.0.0/css/bootstrap.css">
</head>
<body class="homepage is-preload">
	<div id="page-wrapper">

		<!-- Header -->
		<div id="header-wrapper" width="90%">
			<div class="container">

				<!-- Header -->
				<header id="header">
					<div class="inner">
						<jsp:include page="navBar.jsp" />
					</div>
				</header>
			</div>
		</div>
		<!-- Main Wrapper -->
		<div id="main-wrapper">
			<div class="wrapper style1">
				<div class="inner">
					<div class="container">
						<div class="container-fluid">

							<div class="row">
								<div class="col-md-12">
									<h2>Perfil de: ${pageContext.request.userPrincipal.name}</h2>
									<div class="row">
										<div class="col-md-6">
											<div class="card">
												<h5 class="card-header">Mis datos</h5>
												<div class="card-body">
													<p class="card-text">
														Nick: ${usuarioLogueado.nick} <br> Nombre: ${usuarioLogueado.nombre} <br> Apellido: ${usuarioLogueado.apellido} <br> email: ${usuarioLogueado.email} <br>
													</p>
												</div>
											</div>
										</div>
										<c:if test="${(not empty usuarioLogueado.localidad)}">
											<div class="col-md-6">
												<div class="card">
													<h5 class="card-header">Mi ubicacion</h5>
													<div class="card-body">
														<p class="card-text">
															Pais: ${usuarioLogueado.localidad.paisNombre} <br> Provincia: ${usuarioLogueado.localidad.provinciaNombre} <br> Localidad: ${usuarioLogueado.localidad.nombre} <br>
														</p>
													</div>
												</div>
											</div>
										</c:if>
									</div>
								</div>
							</div>
							<br />

							<div class="row">
								<div class="col-md-12">
									<div class="row">
										<div class="col-md-4">
											<button type="button" class="btn btn-primary" onclick="location.href='${contextPath}/modificarPerfil';">Modificar Mi Perfil</button>
										</div>
										<div class="col-md-4">
											<button type="button" class="btn btn-primary" onclick="location.href='${contextPath}/modificarClave';">Modificar Mi Contraseña</button>
										</div>
										<div class="col-md-4">
											<button type="button" class="btn btn-primary" onclick="buscarEventosAsistencia(${usuarioLogueado.id})" data-toggle="collapse" data-target="#collapseEventos" aria-expanded="false" aria-controls="collapseEventos">Eventos a los que asistire</button>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="collapse" id="collapseEventos">
										<div class="card card-body">
											<div class="row">
												<div class="col-md-12">
													<div id="eventosAsistenciaDiv" style="overflow-y:auto;">
														<jsp:include page="loading.jsp" />
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- Footer Wrapper -->
		<div id="footer-wrapper">
			<jsp:include page="footerNav.jsp" />
		</div>

	</div>

<script src="${contextPath}/assets/js/eventos.js"></script>
</body>
</html>