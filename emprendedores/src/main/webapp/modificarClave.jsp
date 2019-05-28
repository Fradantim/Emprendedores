<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="es">
<head>

	<title>Modificar Mi Contraseña</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
</head>

<%-- 
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
							</div>-->

					</div>
				</div>
			<!-- Main Wrapper -->
				<div id="main-wrapper">
					<div class="wrapper style1">
						<div class="inner">
							<div class="container">
								<div class="col-md-6">
									<form:form method="POST" action="${contextPath}/modificarClave" modelAttribute="userForm" class="form-signin">
										<h2 class="form-signin-heading">Modifica tu Contraseña</h2>
										
										<spring:bind path="password">
											<div class="form-group ${status.error ? 'has-error' : ''}">
												<form:input type="password" path="password" class="form-control" placeholder="Contraseña"></form:input>
												<form:errors path="password"></form:errors>
											</div>
										</spring:bind>
										<br/>
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