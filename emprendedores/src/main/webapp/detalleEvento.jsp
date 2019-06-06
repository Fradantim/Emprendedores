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
									<h2 class="form-signin-heading">Evento</h2>
									<h2>Creador: ${eventoRecuperado.creador.nick} <br></h2>
									<p>Sidebars are not welcome here</p>
								</header>
							
								<!--  <span class="image featured"><img src="../assets/css/images/pic08.jpg" alt="" /></span>-->
							
								<p>Phasellus quam turpis, feugiat sit amet ornare in, hendrerit in lectus.
								Praesent semper mod quis eget mi. Etiam eu ante risus. Aliquam erat volutpat.
								Aliquam luctus et mattis lectus sit amet pulvinar. Nam turpis nisi
								consequat etiam lorem ipsum dolor sit amet nullam. Phasellus quam turpis,
								feugiat sit amet ornare in, hendrerit in lectus. Praesent semper mod quis
								eget mi. Etiam eu ante risus. Aliquam erat volutpat. Aliquam luctus et
								mattis lectus sit amet pulvinar. Nam turpis nisi consequat etiam.</p>
							
								<h3>More intriguing information</h3>
								<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas ac quam risus, at tempus
								justo. Sed dictum rutrum massa eu volutpat. Quisque vitae hendrerit sem. Pellentesque lorem felis,
								ultricies a bibendum id, bibendum sit amet nisl. Mauris et lorem quam. Maecenas rutrum imperdiet
								vulputate. Nulla quis nibh ipsum, sed egestas justo. Morbi ut ante mattis orci convallis tempor.
								Etiam a lacus a lacus pharetra porttitor quis accumsan odio. Sed vel euismod nisi. Etiam convallis
								rhoncus dui quis euismod. Maecenas lorem tellus, congue et condimentum ac, ullamcorper non sapien.
								Donec sagittis massa et leo semper a scelerisque metus faucibus. Morbi congue mattis mi.
								ultricies a bibendum id, bibendum sit amet nisl. Mauris et lorem quam. Maecenas rutrum imperdiet
								vulputate. Nulla quis nibh ipsum, sed egestas justo. Morbi ut ante mattis orci convallis tempor.
								Etiam a lacus a lacus pharetra porttitor quis accumsan odio. Sed vel euismod nisi. Etiam convallis
								rhoncus dui quis euismod. Maecenas lorem tellus, congue et condimentum ac, ullamcorper non sapien.
								Donec sagittis massa et leo semper a scelerisque metus faucibus. Morbi congue mattis mi.
								Phasellus sed nisl vitae risus tristique volutpat. Cras rutrum commodo luctus lorem ipsum
								dolor sit amet phasellus consequat.</p>
							
								<p>Phasellus odio risus, faucibus et viverra vitae, eleifend ac purus. Praesent mattis, enim
								quis hendrerit porttitor, sapien tortor viverra magna, sit amet rhoncus nisl lacus nec arcu.
								Suspendisse laoreet metus ut metus imperdiet interdum aliquam justo tincidunt. Mauris dolor urna,
								fringilla vel malesuada ac, dignissim eu mi. Praesent mollis massa ac nulla pretium pretium.
								Suspendisse laoreet metus ut metus imperdiet interdum aliquam justo tincidunt. Mauris dolor urna,
								ultricies a bibendum id, bibendum sit amet nisl. Mauris et lorem quam. Maecenas rutrum imperdiet
								vulputate. Nulla quis nibh ipsum, sed egestas justo. Morbi ut ante mattis orci convallis tempor.
								fringilla vel malesuada ac, dignissim eu mi. Praesent mollis massa ac nulla pretium pretium.
								Maecenas tortor mauris, consectetur pellentesque dapibus eget, tincidunt vitae arcu.
								Vestibulum purus augue, tincidunt sit amet iaculis id, porta eu purus sed feugiat lorem ipsum
								dolor nullam blandit tempus.</p>
							</article>
							
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