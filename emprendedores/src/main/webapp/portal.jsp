<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="es">
<head>
	<title>Portal Emprendedores</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
	<link rel="stylesheet" href="${contextPath}/assets/css/jquery-ui.css" />
	<link rel="stylesheet" href="${contextPath}/assets/css/calendarioEventos.css" />
</head>

<body class="homepage is-preload">
	<div id="page-wrapper">
		<!-- Header -->
		<div id="header-wrapper">
			<div class="container">
				<!-- Header -->
				<header id="header">
					<div class="inner">
						<jsp:include page="navBar.jsp"/>
					</div>
				</header>
			</div>
		</div>
		<!-- Main Wrapper -->
		<div id="main-wrapper">
			<div class="wrapper style3">
				<div class="inner">
					<div class="container">
						<div class="row">
							<div class="col-md-8">
								<h2 id="tituloListaEventos" class="icon fa-file-text-o">Eventos recientes</h2>
								<div id="eventosPortalDiv">
									<jsp:include page="loading.jsp"/>
								</div>
								
							</div>
							<div class="col-md-4">
								<div id="sidebar">
									<!-- Sidebar -->
									<section>
										<header class="major">
											<h2>Calendario de Eventos</h2>
										</header>
										<center>
											<div id="datepicker"></div>
											<div id="datepickerLoading">
												Buscando Eventos...
												<jsp:include page="loading.jsp"/>
											</div>
										</center>
										<!--
										<footer>
											<a href="#" class="button icon fa-info-circle">Find out more</a>
										</footer>
										-->
									</section>

									<section>
										<header class="major">
											<h2>Ultimos Emprendedores</h2>
										</header>
										<ul class="style2">
											<div id="divListaEmprendedores">
											</div>
											<li><a href="${contextPath}/detalleEmprendedor?idEmprendedor=${usuarioLogueado.emprendimiento.id}">Un emprendedor</a></li>
										</ul>
									</section>
								</div>
							</div>
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
		
<script src="${contextPath}/assets/js/jquery-1.12.4.js"></script>
<script src="${contextPath}/assets/js/jquery-ui.js"></script>
<script src="${contextPath}/assets/js/eventos.js"></script>
<script src="${contextPath}/assets/js/emprendedores.js"></script>
<script src="${contextPath}/assets/js/calendarioEventos.js"></script>
<script>
	window.onload=buscarEventosPublicos;
</script>
</body>

</html>