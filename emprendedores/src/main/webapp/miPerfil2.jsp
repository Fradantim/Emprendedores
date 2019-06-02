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
  <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseEventos" aria-expanded="false" aria-controls="collapseEventos">
    Mis Eventos
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
<div class="collapse" id="collapseEventos">
  <div class="card card-body">
												<div class="row">
													<div class="col-md-12">
														<table class="table table-bordered table-sm">
															<thead>
																<tr>
																	<th>
																		#
																	</th>
																	<th>
																		Product
																	</th>
																	<th>
																		Payment Taken
																	</th>
																	<th>
																		Status
																	</th>
																</tr>
															</thead>
															<tbody>
																<tr>
																	<td>
																		1
																	</td>
																	<td>
																		TB - Monthly
																	</td>
																	<td>
																		01/04/2012
																	</td>
																	<td>
																		Default
																	</td>
																</tr>
																<tr class="table-active">
																	<td>
																		1
																	</td>
																	<td>
																		TB - Monthly
																	</td>
																	<td>
																		01/04/2012
																	</td>
																	<td>
																		Approved
																	</td>
																</tr>
																<tr class="table-success">
																	<td>
																		2
																	</td>
																	<td>
																		TB - Monthly
																	</td>
																	<td>
																		02/04/2012
																	</td>
																	<td>
																		Declined
																	</td>
																</tr>
																<tr class="table-warning">
																	<td>
																		3
																	</td>
																	<td>
																		TB - Monthly
																	</td>
																	<td>
																		03/04/2012
																	</td>
																	<td>
																		Pending
																	</td>
																</tr>
																<tr class="table-danger">
																	<td>
																		4
																	</td>
																	<td>
																		TB - Monthly
																	</td>
																	<td>
																		04/04/2012
																	</td>
																	<td>
																		Call in to confirm
																	</td>
																</tr>
															</tbody>
														</table>
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
</body>
</html>