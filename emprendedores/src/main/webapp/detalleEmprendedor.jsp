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
						<article>
							<div class="card card-body">
								<h2>Datos del Emprendedor</h2>
								<div class="row">
									<div class="col-md-6">
										<div class="card">
											<h5 class="card-header">Mis datos</h5>
											<div class="card-body">
												<p class="card-text">
													Nick: ${usuarioEmprendedor.nick} <br> Nombre: ${usuarioEmprendedor.nombre} <br> Apellido: ${usuarioEmprendedor.apellido} <br> email: ${usuarioEmprendedor.email} <br>
												</p>
											</div>
										</div>
									</div>
									<c:if test="${(not empty usuarioEmprendedor.localidad)}">
										<div class="col-md-6">
											<div class="card">
												<h5 class="card-header">Mi ubicacion</h5>
												<div class="card-body">
													<p class="card-text">
														Pais: ${usuarioEmprendedor.localidad.provincia.pais.nombre} <br> Provincia: ${usuarioEmprendedor.localidad.provincia.nombre} <br> Localidad: ${usuarioEmprendedor.localidad.nombre} <br>
													</p>
												</div>
											</div>
										</div>
									</c:if>
								</div>
							</div>
						</article>
						
						<article>
							<div class="card card-body">
								<div class="row">
									<div class="col-md-12">
										<h3><span>Mi Emprendimiento</span></h3>
										<div class="card">
											<h5 class="card-header">
												${usuarioEmprendedor.emprendimiento.nombre}
											</h5>
											<div class="card-body">
												<div class="card-text">
													<div class="scroll-box">
														${usuarioEmprendedor.emprendimiento.descripcion}
													</div>
												</div>
											</div>
											<div class="card-footer">
												${usuarioEmprendedor.emprendimiento.link} <br>
												${usuarioEmprendedor.emprendimiento.contacto}
											</div>
										</div>
									</div>
								</div>
							</div>
						</article>
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