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
									<h2>Creador: ${eventoRecuperado.creador.nick} <br></h2>
									<p>Sidebars are not welcome here</p>
								</header>
							
								<!--  <span class="image featured"><img src="../assets/css/images/pic08.jpg" alt="" /></span>-->
							
								
								<p>${eventoRecuperado.descripcion}</p>
							
								<h3>Descripcion</h3>
								${eventoRecuperado.descripcionLarga}
							
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
							  ${eventoRecuperado.mapa}
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
</body>
</html>