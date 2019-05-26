<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Modificar Mi Perfil</title>
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
	
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script	src="https://stackpath.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	
	<script	src="${contextPath}/resources/js/portal.js"></script>		
</head>
<body>
	<div class="container">
		<jsp:include page="navBar.jsp" />

		<div class="col-md-6">
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
				<spring:bind path="pais">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<form:input type="text" path="pais" class="form-control" placeholder="Pais" value="${usuarioLogueado.pais}"></form:input>
						<form:errors path="pais"></form:errors>
					</div>
				</spring:bind>
				<h3>Provincia</h3>
				<spring:bind path="provincia">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<form:input type="text" path="provincia" class="form-control" placeholder="Provincia" value="${usuarioLogueado.provincia}"></form:input>
						<form:errors path="provincia"></form:errors>
					</div>
				</spring:bind>
				<h3>Localidad</h3>
				<spring:bind path="localidad">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<form:input type="text" path="localidad" class="form-control" placeholder="Localidad" value="${usuarioLogueado.localidad}"></form:input>
						<form:errors path="localidad"></form:errors>
					</div>
				</spring:bind>
				
				<security:authorize access="hasRole('EMPRENDEDOR')"	var="isEmprendedor" />
				
				Modo emprendedor
				<input name="emprendedorCheckBox" type="checkbox" ${isEmprendedor ? 'checked' : ''} />
				
				<hr>
				<button class="btn btn-lg btn-primary btn-block" type="submit">Actualizar perfil!</button>
			</form:form>
		</div>
	</div>

</body>
</html>