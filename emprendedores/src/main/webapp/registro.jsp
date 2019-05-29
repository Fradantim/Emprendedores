<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="es">
  <head>
		
		<title>Crea tu cuenta</title>
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
							</div>
-->
					</div>
				</div>

			<!-- Main Wrapper -->
				<div id="main-wrapper">
					<div class="wrapper style1">
						<div class="inner">

							<!-- Sing in  Form -->
							<section class="sign-in">
								<div class="container">
									<div class="signin-content">
										<div class="signin-form">
											<h2 class="form-title">Registrarse</h2>
											<form:form method="POST" modelAttribute="userForm" class="register-form" id="login-form">
												<spring:bind path="nick">
													<div class="form-group ${status.error ? 'has-error' : ''}">
														<label for="nick"><i class="zmdi zmdi-account material-icons-name"></i></label>
														<form:input type="text" path="nick" name="nick" id="nick" placeholder="NickName"></form:input>
                    									<form:errors path="nick"></form:errors>
													</div>
												</spring:bind>
												<spring:bind path="nombre">
									                <div class="form-group ${status.error ? 'has-error' : ''}">
									                	<label for="nombre"><i class="zmdi zmdi-account material-icons-name"></i></label>
									                    <form:input type="text" path="nombre" class="form-control" placeholder="Nombres"></form:input>
									                    <form:errors path="nombre"></form:errors>
									                </div>
									            </spring:bind>
												<spring:bind path="apellido">
													<div class="form-group ${status.error ? 'has-error' : ''}">
														<label for="apellido"><i class="zmdi zmdi-account material-icons-name"></i></label>
														<form:input type="text" path="apellido" name="apellido" id="apellido" placeholder="Apellidos"></form:input>
                    									<form:errors path="apellido"></form:errors>
													</div>
												</spring:bind>
												<spring:bind path="email">
													<div class="form-group ${status.error ? 'has-error' : ''}">
														<label for="email"><i class="zmdi zmdi-email"></i></label>
														<form:input type="text" path="email" name="email" id="email" placeholder="E-Mail"></form:input>
                    									<form:errors path="email"></form:errors>
													</div>
												</spring:bind>
												<spring:bind path="password">
													<div class="form-group ${status.error ? 'has-error' : ''}">
														<label for="pass"><i class="zmdi zmdi-lock"></i></label>
														<form:input type="password" path="password" name="pass" id="pass" placeholder="Contraseña"></form:input>
                    									<form:errors path="password"></form:errors>
													</div>
												</spring:bind>
												<spring:bind path="passwordConfirm">
									                <div class="form-group ${status.error ? 'has-error' : ''}">
									                	<label for="re-pass"><i class="zmdi zmdi-lock-outline"></i></label>
									                    <form:input type="password" path="passwordConfirm" name="re-pass" id="re-pass" placeholder="Confirme su contraseña"></form:input>
									                    <form:errors path="passwordConfirm"></form:errors>
									                </div>
									            </spring:bind>
												<br/>
												<div class="form-group form-button">
													<input type="submit" name="signup" id="signup" class="form-submit" value="Crear cuenta!"/>
												</div>
											</form:form>
											<!--  
											<div class="social-login">
												<span class="social-label">Or login with</span>
												<ul class="socials">
													<li><a href="#"><i class="display-flex-center zmdi zmdi-facebook"></i></a></li>
													<li><a href="#"><i class="display-flex-center zmdi zmdi-twitter"></i></a></li>
													<li><a href="#"><i class="display-flex-center zmdi zmdi-google"></i></a></li>
												</ul>
											</div>
											-->
										</div>
										
										<div class="signin-image">
											<center>
												<figure ><img src="${contextPath}/assets/css/images/signup-image.jpg" alt="Ingresar a Cuenta"></figure>
											</center>
											<%-- 
												<a href="${contextPath}/login" class="signup-image-link">Ingresar a Cuenta</a>
												--%>
											</div>
									</div>
								</div>
							</section>
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