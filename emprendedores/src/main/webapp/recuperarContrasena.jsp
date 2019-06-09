<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="es">
<head>

	<title>Modificar Mi Contrase�a</title>
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
					</div>
				</div>
			<!-- Main Wrapper -->
				<div id="main-wrapper">
					<div class="wrapper style1">
						<div class="inner">
							<div class="container">
								<div class="col-md-12">
									<form:form method="POST" action="${contextPath}/modificarClave" modelAttribute="userForm" class="form-signin">
										<h2 class="form-signin-heading">Recuperar Contrase�a</h2>
										
										<spring:bind path="password">
											<div class="form-group ${status.error ? 'has-error' : ''}">
												<form:input type="password" path="password" class="form-control" placeholder="Contrase�a"></form:input>
												<form:errors path="password"></form:errors>
											</div>
										</spring:bind>
										<br/>
										
										<hr>
										<button class="btn btn-lg btn-primary btn-block" type="submit">Actualizar Contrase�a!</button>
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