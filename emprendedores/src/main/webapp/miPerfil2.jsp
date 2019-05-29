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
	<link rel="stylesheet" href="${contextPath}/assets/bootstrap-4.2.1/css/bootstrap.css">
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

						<!-- Banner 
							<div id="banner">
								<h2><strong>ZeroFour:</strong> A free responsive site template
								<br />
								built on HTML5 and CSS3 by <a href="http://html5up.net">HTML5 UP</a></h2>
								<p>Does this statement make you want to click the big shiny button?</p>
								<a href="#" class="button large icon fa-check-circle">Yes it does</a>
							</div>-->

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
			<h3>
				Perfil de: ${pageContext.request.userPrincipal.name}
			</h3>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
		<h2>
				Usuario
			</h2>
			<div class="row">
				<div class="col-md-6">
					<div class="card">
						<h5 class="card-header">
							Datos
						</h5>
						<div class="card-body">
							<p class="card-text">
								Nick: ${usuarioLogueado.nick} <br>
								Nombre: ${usuarioLogueado.nombre} <br>
								Apellido: ${usuarioLogueado.apellido} <br>
								email: ${usuarioLogueado.email} <br>
							</p>
						</div>
						<div class="card-footer">
							Card footer
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="card">
						<h5 class="card-header">
							Domicilio
						</h5>
						<div class="card-body">
							<p class="card-text">
								Pais: ${usuarioLogueado.pais} <br>
								Provincia: ${usuarioLogueado.provincia} <br>
								Localidad: ${usuarioLogueado.localidad} <br>
							</p>
						</div>
						<div class="card-footer">
							Card footer
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<br/>
	<div class="row">
		<div class="col-md-12">
			 <span class="badge badge-default">Emprendimientos</span>
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
	<br/>
	
	<div class="row">
		<div class="col-md-12">
			<div class="row">
				<div class="col-md-4">
					 <span class="badge badge-default">
					 Perfiles: <br>
										<c:forEach items="${usuarioLogueado.perfiles}" var="p"> 
											${p.nombre}<br>
										</c:forEach>	
					 </span>
				</div>
				<div class="col-md-4">
					 <span class="badge badge-default">
					 <security:authorize access="hasAuthority('CLIENTE')">
											TIENE PERFIL CLIENTE<br>
										</security:authorize>
					 </span>
				</div>
				<div class="col-md-4">
					 <span class="badge badge-default">
					 <security:authorize access="hasAuthority('EMPRENDEDOR')" var="isEmprendedor" />
										<c:choose>
										   <c:when test="${isEmprendedor}">(SI)  </c:when>
										   <c:otherwise>(NO) </c:otherwise>
										</c:choose>
										TIENE PERFIL EMPRENDEDOR <br>
					 </span>
				</div>
			</div>
			<br/>
			<div class="row">
				<div class="col-md-6">
					 
					<button type="button" class="btn btn-block btn-primary" onclick="location.href='${contextPath}/modificarPerfil';">
						Modificar Mi Perfil
					</button>
				</div>
				<div class="col-md-6">
					 
					<button type="button" class="btn btn-block btn-primary" onclick="location.href='${contextPath}/modificarClave';">
						Modificar Mi Contraseña
					</button>
				</div>
			</div>
			<br/>
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

		
</body>
</html>