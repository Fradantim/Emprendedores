<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Modificar Mi Contraseña</title>
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
	
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script	src="https://stackpath.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	
	<script	src="${contextPath}/resources/js/portal.js"></script>		
</head>
<body>
	<div class="container">
		<jsp:include page="navBar.jsp" />

		<div class="col-md-6">
			<form:form method="POST" action="${contextPath}/modificarClave" modelAttribute="userForm" class="form-signin">
				<h2 class="form-signin-heading">Modifica tu Contraseña</h2>
				
				<spring:bind path="password">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<form:input type="password" path="password" class="form-control" placeholder="Contraseña"></form:input>
						<form:errors path="password"></form:errors>
					</div>
				</spring:bind>

				<spring:bind path="passwordConfirm">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<form:input type="password" path="passwordConfirm" class="form-control" placeholder="Confirme su contraseña"></form:input>
						<form:errors path="passwordConfirm"></form:errors>
					</div>
				</spring:bind>
				
				<hr>
				<button class="btn btn-lg btn-primary btn-block" type="submit">Actualizar Contraseña!</button>
			</form:form>
		</div>
	</div>

</body>
</html>