<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="es">
<head>

<title>Crea tu cuenta</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />

<style>
.error-messages {
	color: red;
	font-size: 12px;
}
</style>

</head>

<body class="homepage is-preload">
	<div id="page-wrapper">
		<!-- Header -->
		<div id="header-wrapper">
			<div class="container">
				<!-- Header -->
				<header id="header">
					<div class="inner">
						<jsp:include page="navBar.jsp" />
					</div>
				</header>
			</div>
		</div>

		<!-- Main Wrapper -->
		<div id="main-wrapper">
			<div class="wrapper style1">
				<div class="inner">
					<!-- Sing in  Form -->
					<div class="container-fluid">
						<div class="row">
							<div class="col-md-12">
								<div class="row">
									<div class="col-md-6">
										<div class="signin-form">
											<h2 class="form-title">Registrarse</h2>
											<form:form method="POST" modelAttribute="userForm" class="register-form" id="login-form">
												<spring:bind path="nick">
													<div class="form-group ${status.error ? 'has-error' : ''}">
														<label for="nick"><i class="zmdi zmdi-account material-icons-name"></i></label>
														<form:input type="text" path="nick" name="nick" id="nick" placeholder="NickName"></form:input>
														<form:errors class="error-messages" path="nick"></form:errors>
													</div>
												</spring:bind>
												<spring:bind path="nombre">
													<div class="form-group ${status.error ? 'has-error' : ''}">
														<label for="nombre"><i class="zmdi zmdi-account material-icons-name"></i></label>
														<form:input type="text" path="nombre" class="form-control" placeholder="Nombres"></form:input>
														<form:errors class="error-messages" path="nombre"></form:errors>
													</div>
												</spring:bind>
												<spring:bind path="apellido">
													<div class="form-group ${status.error ? 'has-error' : ''}">
														<label for="apellido"><i class="zmdi zmdi-account material-icons-name"></i></label>
														<form:input type="text" path="apellido" name="apellido" id="apellido" placeholder="Apellidos"></form:input>
														<form:errors class="error-messages" path="apellido"></form:errors>
													</div>
												</spring:bind>
												<spring:bind path="email">
													<div class="form-group ${status.error ? 'has-error' : ''}">
														<label for="email"><i class="zmdi zmdi-email"></i></label>
														<form:input type="text" path="email" name="email" id="email" placeholder="E-Mail"></form:input>
														<form:errors class="error-messages" path="email"></form:errors>
													</div>
												</spring:bind>
												<spring:bind path="password">
													<div class="form-group ${status.error ? 'has-error' : ''}">
														<label for="pass"><i class="zmdi zmdi-lock"></i></label>
														<form:input type="password" path="password" name="pass" id="pass" placeholder="Contraseña"></form:input>
														<form:errors class="error-messages" path="password"></form:errors>
													</div>
												</spring:bind>
												<spring:bind path="passwordConfirm">
													<div class="form-group ${status.error ? 'has-error' : ''}">
														<label for="re-pass"><i class="zmdi zmdi-lock-outline"></i></label>
														<form:input type="password" path="passwordConfirm" name="re-pass" id="re-pass" placeholder="Confirme su contraseña"></form:input>
														<form:errors class="error-messages" path="passwordConfirm"></form:errors>
													</div>
												</spring:bind>
												
												<input name="aceptaTyCCheckBox" id="aceptaTyCCheckBox" type="checkbox"/>
												<span title="Haz click aqui para leerlos.">
													<a data-toggle="modal" data-target="#myModal" style="cursor: pointer;" >
														<u>Acepto los términos y condiciones.</u>
													</a>
												</span>
												<br> 
												<br>
												<div class="form-group form-button">
													<button type="submit" class="btn btn-block btn-primary" class="form-submit">Crear Cuenta</button>
												</div>
											</form:form>
										</div>
									</div>
									<div class="col-md-6">
										<div class="signin-image">
											<figure>
												<img src="${contextPath}/assets/css/images/signup-image.jpg" alt="Ingresar a Cuenta">
											</figure>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Modal -->
		<jsp:include page="${contextPath}/modalTyC.jsp"/>
		<!-- Footer Wrapper -->
		<div id="footer-wrapper">
			<jsp:include page="footerNav.jsp" />
		</div>
		
	</div>
</body>
</html>