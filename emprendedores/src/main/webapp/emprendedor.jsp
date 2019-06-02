<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="es">
<head>
	<title>Mi Emprendimiento</title>
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
				<div class="container-fluid">
	

	<div class="row">
		<div class="col-md-12">
			 <h>Emprendedor</h>
			 
			 <div class="card">
				<h5 class="card-header">
					<!--  ${usuarioLogueado.emprendimiento.nombre}-->
					Juan Pablo
				</h5>
				<div class="card-body">
					<div class="card-text">
						${usuarioLogueado.emprendimiento.descripcion}
					</div>
				</div>
				<div class="card-footer">
					${usuarioLogueado.emprendimiento.link} <br>
					${usuarioLogueado.emprendimiento.contacto}
				</div>
			</div>
			
		</div>
	</div>	
	
	<br/>
	

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