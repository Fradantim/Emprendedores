<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Modificar Mi Emprendimiento</title>
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
	
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script	src="https://stackpath.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	
	<script	src="${contextPath}/resources/js/portal.js"></script>		
</head>
<body>
	<div class="container">
		<jsp:include page="navBar.jsp" />

		<div class="col-md-6">
			<form:form method="POST" action="${contextPath}/modificarEmprendimiento" modelAttribute="emprendimientoForm" class="form-signin">
				<h2 class="form-signin-heading">Modifica tu Emprendimiento</h2>
				
				<h3>Nombre</h3>
					<spring:bind path="nombre">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<form:input type="text" path="nombre" class="form-control" placeholder="Nombre" value="${emprendimiento.nombre}"></form:input>
							<form:errors path="nombre"></form:errors>
						</div>
					</spring:bind>
				<h3>Descripcion</h3>
				<spring:bind path="descripcion">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<form:input type="text" path="descripcion" class="form-control" placeholder="Descripcion" value="${emprendimiento.descripcion}"></form:input>
						<form:errors path="descripcion"></form:errors>
					</div>
				</spring:bind>
				<h3>Link</h3>
				<spring:bind path="link">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<form:input type="text" path="link" class="form-control" placeholder="Link" value="${emprendimiento.link}"></form:input>
						<form:errors path="link"></form:errors>
					</div>
				</spring:bind>
				<h3>Contacto</h3>
				<spring:bind path="contacto">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<form:input type="text" path="contacto" class="form-control" placeholder="Contacto" value="${emprendimiento.contacto}"></form:input>
						<form:errors path="contacto"></form:errors>
					</div>
				</spring:bind>
				<spring:bind path="id">
					<div class="form-group">
						<form:input type="hidden" path="id" class="form-control" value="${emprendimiento.id}"></form:input>
						<form:errors path="id"></form:errors>
					</div>
				</spring:bind>
				
				<hr>
				<button class="btn btn-lg btn-primary btn-block" type="submit">Actualizar emprendimiento!</button>
			</form:form>
		</div>
	</div>

</body>
</html>