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
							<article>
								<header class="major">
									<h2>Evento: ${eventoRecuperado.nombre} </h2>
									<h2>Creador: ${eventoRecuperado.creador.nick} </h2><br>
								</header>
								
								<h3>Cuando: ${e.fecha} </h3><br>
								<h3>Donde: ${e.localidad.nombre}, ${e.localidad.provinciaNombre} - ${e.localidad.paisNombre}</h3> <br>
								
								<hr>
								<!--  <span class="image featured"><img src="../assets/css/images/pic08.jpg" alt="" /></span>-->
							
								<h2>Descripción del evento<br></h2>
								<p>${eventoRecuperado.descripcionLarga}</p>
								
								<hr>
								
								<h2>Mapa</h2>
								<!--Google map-->
								<div id="map-container-google-2" class="z-depth-1-half map-container-2">
								  ${eventoRecuperado.mapa}
								</div>
								<!--Google Maps-->
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