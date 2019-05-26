<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="es">
<head>
	<title>Modificar Mi Emprendimiento</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
</head>
<%--   
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
--%>

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
							</div>
-->
					</div>
				</div>

			<!-- Main Wrapper -->
				<div id="main-wrapper">
					<div class="wrapper style1">
						<div class="inner">

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
					</div>
					
				</div>

			<!-- Footer Wrapper -->
				<div id="footer-wrapper">
					<jsp:include page="footerNav.jsp"/>
				</div>

		</div>

		<!-- Scripts -->
			<script src="${contextPath}/assets/js/jquery.min.js"></script>
			<script src="${contextPath}/assets/js/jquery.dropotron.min.js"></script>
			<script src="${contextPath}/assets/js/browser.min.js"></script>
			<script src="${contextPath}/assets/js/breakpoints.min.js"></script>
			<script src="${contextPath}/assets/js/util.js"></script>
			<script src="${contextPath}/assets/js/main.js"></script>

	</body>


</html>