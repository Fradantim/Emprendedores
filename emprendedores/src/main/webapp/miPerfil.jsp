<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="es">
<head>
	<title>Mi Perfil</title>
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
		<h2>
				Perfil de: ${pageContext.request.userPrincipal.name}
			</h2>
			<div class="row">
				<div class="col-md-6">
					<div class="card">
						<h5 class="card-header">
							Mis datos
						</h5>
						<div class="card-body">
							<p class="card-text">
								Nick: ${usuarioLogueado.nick} <br>
								Nombre: ${usuarioLogueado.nombre} <br>
								Apellido: ${usuarioLogueado.apellido} <br>
								email: ${usuarioLogueado.email} <br>
							</p>
						</div>
					</div>
				</div>
				<c:if test="${(not empty usuarioLogueado.pais) || (not empty usuarioLogueado.provincia) || (not empty usuarioLogueado.localidad)}">
					<div class="col-md-6">
						<div class="card">
							<h5 class="card-header">
								Mi ubicacion
							</h5>
							<div class="card-body">
								<p class="card-text">
									Pais: ${usuarioLogueado.pais} <br>
									Provincia: ${usuarioLogueado.provincia} <br>
									Localidad: ${usuarioLogueado.localidad} <br>
								</p>
							</div>
						</div>
					</div>
				</c:if>
			</div>
		</div>
	</div>
	<br/>
	
		<div class="row">
		<div class="col-md-12">
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

	<security:authorize access="hasAuthority('EMPRENDEDOR')" >
	<div class="row">
		<div class="col-md-12">
			 <span class="badge badge-default">Mi Emprendimiento</span>
			 <input type="button" class="btn btn-primary btn-sm" onclick="location.href='${contextPath}/modificarEmprendimiento?idEmprendimiento=${usuarioLogueado.emprendimiento.id}';" value="Modificar Mi Emprendimiento" />
			 <div class="card">
				<h5 class="card-header">
					${usuarioLogueado.emprendimiento.nombre}
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
	</security:authorize>

	
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