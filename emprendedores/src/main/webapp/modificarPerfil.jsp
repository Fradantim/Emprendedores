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

		
</body>
</html>