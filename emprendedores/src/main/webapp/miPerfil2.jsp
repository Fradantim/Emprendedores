<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="es">
<head>
	<title>Portal Emprendedores</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
	
	<style>
        .scroll-box {
            overflow-x: scroll;
            /*height: 100px;*/
            padding: 1rem
        }
</style>

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
					<!-- Comienzo de página -->
						<div class="container-fluid">
							<div class="row">
								<div class="col-md-12">
									<p>
										<button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseProductos" aria-expanded="false" aria-controls="collapseProductos">
											Datos de Mis Productos
										</button>
									</p>
									<div class="collapse" id="collapseProductos">
										<div class="card card-body">
											<div class="row">
												<div class="col-md-12">
													<h3><span>Mi Emprendimiento</span></h3>
													<button type="button" class="btn btn-block btn-primary" onclick="location.href='${contextPath}/modificarEmprendimiento?idEmprendimiento=${usuarioLogueado.emprendimiento.id}';">
														Modificar Mi Emprendimiento
													</button>
													<br/>
													<div class="card">
														<h5 class="card-header">
															${usuarioLogueado.emprendimiento.nombre}
														</h5>
														<div class="card-body">
															<div class="card-text">
															<div class="scroll-box">
																${usuarioLogueado.emprendimiento.descripcion}
															</div>
															</div>
														</div>
														<div class="card-footer">
															${usuarioLogueado.emprendimiento.link} <br>
															${usuarioLogueado.emprendimiento.contacto}
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									
									<br/>

									<p>
									<button class="btn btn-primary" type="button" onclick="buscarEventosCreados(${usuarioLogueado.id})" data-toggle="collapse" data-target="#collapseEventos" aria-expanded="false" aria-controls="collapseEventos">
										Mis Eventos Creados
									</button>
									</p>
									<div class="collapse" id="collapseEventos">
									  <div class="card card-body">
										<div class="row">
											<div class="col-md-12">
												<div id="eventosCreadosDiv" style="overflow-y:auto;">
													<jsp:include page="loading.jsp"/>
												</div>
											</div>
										</div>	
									  </div>
									</div>
																					
									<br/>
									
									<button class="btn btn-primary" type="button" onclick="buscarEventosInscriptos(${usuarioLogueado.id})" data-toggle="collapse" data-target="#collapseEventosInscripto" aria-expanded="false" aria-controls="collapseEventos">
										Mis Eventos Inscripto
									</button>
									</p>
									<div class="collapse" id="collapseEventosInscripto">
										<div class="card card-body">
											<div class="row">
												<div class="col-md-12">
													<div id="eventosInscriptosDiv" style="overflow-y:auto;">
														<jsp:include page="loading.jsp"/>
													</div>
												</div>
											</div>	
									  	</div>
									</div>
								</div>
							</div>
						</div>
						<!-- Fin de página -->
					</div>
				</div>
			</div>
		</div>
		
		<!-- Footer Wrapper -->
		<div id="footer-wrapper">
			<jsp:include page="footerNav.jsp"/>
		</div>

	</div>
<script src="${contextPath}/assets/js/eventos.js"></script>
</body>
</html>