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
							<h2 class="form-signin-heading">Evento</h2>
							
							
							<!--Google map-->
							<div id="map-container-google-2" class="z-depth-1-half map-container-2">
							  <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3283.4886565863144!2d-58.38423648423651!3d-34.61708966571376!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x95bccaba6ac89b35%3A0x1a2dc24cbca665a7!2sFundaci%C3%B3n+UADE!5e0!3m2!1ses!2sar!4v1559770015442!5m2!1ses!2sar" width="600" height="450" frameborder="0" style="border:0" allowfullscreen></iframe>
							</div>
							
							<!--Google Maps-->


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
	<script src="${contextPath}/assets/js/jquery.datetimepicker.full.min.js"></script>
	<script src="${contextPath}/assets/js/ubicacion.js"></script>
	<script src="${contextPath}/assets/js/fecha.js"></script>
</body>
</html>