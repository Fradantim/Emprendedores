<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="es">
<head>
	
	<title>Modificar Mi Perfil</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
	
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
							<form:form method="POST" action="${contextPath}/modificarPerfil" modelAttribute="userForm" class="form-signin">
								<h2 class="form-signin-heading">Modifica tu Perfil</h2>
								<h3>Nombre</h3>
								<spring:bind path="nombre">
									<div class="form-group ${status.error ? 'has-error' : ''}">
										<form:input type="text" path="nombre" class="form-control" placeholder="Nombres" value="${usuarioLogueado.nombre}"></form:input>
										<form:errors path="nombre"></form:errors>
									</div>
								</spring:bind>
								<h3>Apellido</h3>
								<spring:bind path="apellido">
									<div class="form-group ${status.error ? 'has-error' : ''}">
										<form:input type="text" path="apellido" class="form-control" placeholder="Apellidos" value="${usuarioLogueado.apellido}"></form:input>
										<form:errors path="apellido"></form:errors>
									</div>
								</spring:bind>
								<h3>NickName</h3>
								<spring:bind path="nick">
									<div class="form-group ${status.error ? 'has-error' : ''}">
										<form:input type="text" path="nick" class="form-control" placeholder="NickName" value="${usuarioLogueado.nick}"></form:input>
										<form:errors path="nick"></form:errors>
									</div>
								</spring:bind>
								<h3>Email</h3>
								<spring:bind path="email">
									<div class="form-group ${status.error ? 'has-error' : ''}">
										<form:input type="text" path="email" class="form-control" placeholder="E-Mail" value="${usuarioLogueado.email}"></form:input>
										<form:errors path="email"></form:errors>
									</div>
								</spring:bind>
								<h3>Pais</h3>
								<div class="form-group" id="paisDiv">
									Aguarde ...
								</div>
								<h3>Provincia</h3>
								<div class="form-group" id="provinciaDiv">
									Aguarde ...
								</div>
								<h3>Localidad</h3>
								<div class="form-group" id="localidadDiv">
									Aguarde ...
								</div>
																
								<security:authorize access="hasAuthority('EMPRENDEDOR')" var="isEmprendedor" />

								<br/>
								<input name="emprendedorCheckBox" type="checkbox" ${isEmprendedor ? 'checked' : ''} />
								Modo emprendedor
								<hr>
								<br/>
								<button class="btn btn-lg btn-primary btn-block" type="submit">Actualizar perfil!</button>
							</form:form>
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
	<script>
		var myPaisId ="${usuarioLogueado.localidad.paisId}";
		var myProvinciaId ="${usuarioLogueado.localidad.provinciaId}";
		var myLocalidadId ="${usuarioLogueado.localidad.id}";
	</script>
	<script src="${contextPath}/assets/js/ubicacion.js"></script>
</body>
</html>