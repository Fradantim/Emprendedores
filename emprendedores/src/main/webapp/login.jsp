<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="es">
  <head>
  		<title>Ingrese a su cuenta</title>
      
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		
		<style>
		.error-messages {
  			color: red;
  			font-size: 12px; }
		
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
					<div class="wrapper style1" >
						<div class="inner">
						<div class="container">
						<div class="container-fluid">
							<div class="row">
								<div class="col-md-12">
									<div class="row">
										<div class="col-md-6">
												<h2 class="form-title">Ingresar</h2>
												<form method="POST" action="${contextPath}/login" class="form-signin">
													<div class="form-group" >
														<label for="your_name"><i class="zmdi zmdi-account material-icons-name"></i></label>
														<input type="text" name="username" id="username" placeholder="Usuario"/>
													</div>
													<div class="form-group">
														<label for="your_pass"><i class="zmdi zmdi-lock"></i></label>
														<input type="password" name="password" id="password" placeholder="Contraseña"/>
														<span class="error-messages">${error}</span>
														<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
													</div>
													<div class="form-group">
														Olvidó su contraseña? <u><a data-toggle="modal" data-target="#myModal">Click Aquí</a></u>
													</div>
													<div class="form-group form-button">
													<br/>
														<table style="width:100%">
															<tr align="center">
																<td width="50%" align="center">
																	<button type="submit" class="btn btn-block btn-primary" id="submit" name="signin" id="signin" class="form-submit">
																		Ingresar
																	</button>
																</td>
																<td width="50%" align="center">
																	<button type="button" class="btn btn-block btn-primary" class="form-submit" onclick="location.href='${contextPath}/registro';">
																		Crear Cuenta
																	</button>
																</td>
															</tr>
														</table>	
													</div>
												</form>
										</div>
										<div class="col-md-6">
												<img class="rounded mx-auto d-block" src="${contextPath}/assets/css/images/signin-image.jpg" alt="Crear Cuenta">
										</div>
									</div>
								</div>
							</div>
						</div>
						</div>
						
						</div>
					</div>
				</div>
				
				<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="overflow: auto;">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title" id="myModalLabel">Recuperar Contraseña</h4>
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								
							</div>
							<div class="modal-body">
								<h2 class="form-title">e-mail</h2>
								<form method="POST" action="${contextPath}/recupero" class="form-signin">
									<div class="form-group" >
										<input type="text" name="email" id="email" placeholder="e-mail"/>
										<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
									</div>
									<div class="form-group form-button">
									<br/>
										<table style="width:100%">
											<tr align="center">
												<td width="50%" align="center">
													<button type="submit" class="btn btn-block btn-primary" id="submit" name="signin" class="form-submit">
														Recuperar
													</button>
												</td>
											</tr>
										</table>	
									</div>
								</form>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
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