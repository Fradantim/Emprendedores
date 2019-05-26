<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="es">
<head>
	<title>Portal Emprendedores</title>
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
								<c:choose>
									<c:when test="${pageContext.request.userPrincipal.name == null}">
										<h2>Bienvenido Anónimo! Debe iniciar sesion para ver este contenido</h2>
									</c:when>
									<c:otherwise>
										<h2> Perfil de: ${pageContext.request.userPrincipal.name}</h2> <br>
										
										<hr>
										
										Nick: ${usuarioLogueado.nick} <br>
										Nombre: ${usuarioLogueado.nombre} <br>
										Apellido: ${usuarioLogueado.apellido} <br>
										email: ${usuarioLogueado.email} <br>
										---<br>
										<br>
										Pais: ${usuarioLogueado.pais} <br>
										Provincia: ${usuarioLogueado.provincia} <br>
										Localidad: ${usuarioLogueado.localidad} <br>
										---<br>
										<br>
										
										<security:authorize access="hasAuthority('CLIENTE')"	var="isCliente" />
										<c:choose>
											<c:when test="${isCliente}">ES CLIENTE<br>
											</c:when>
											<c:otherwise>
												NO ES CLIENTE<br>
											</c:otherwise>
										</c:choose>
										
										<security:authorize access="hasAuthority('EMPRENDEDOR')"	var="isEmprendedor" />
										<c:choose>
											<c:when test="${not isEmprendedor}">NO ES EMPRENDEDOR <br>
											</c:when>
											<c:otherwise>
												ES EMPRENDEDOR: <br>
												Nombre: ${usuarioLogueado.emprendimiento.nombre} <br>
												Descripcion: ${usuarioLogueado.emprendimiento.descripcion} <br>
												Link: ${usuarioLogueado.emprendimiento.link} <br>
												Contacto: ${usuarioLogueado.emprendimiento.contacto} <br>
												<input type="button" class="btn btn-default" onclick="location.href='${contextPath}/modificarEmprendimiento?idEmprendimiento=${usuarioLogueado.emprendimiento.id}';" value="Modificar Mi Emprendimiento" />
												<br>
											</c:otherwise>
										</c:choose>
										---<br>
										<br>
										Perfiles: <br>
										<c:forEach items="${usuarioLogueado.perfiles}" var="p"> 
											${p.nombre}<br>
										</c:forEach>	
										---<br>
										<br>
										<security:authorize access="hasAuthority('CLIENTE')">
											TIENE PERFIL CLIENTE<br>
										</security:authorize>
										<br>
										<br>
										<security:authorize access="hasAuthority('EMPRENDEDOR')" var="isEmprendedor" />
										<c:choose>
										   <c:when test="${isEmprendedor}">(SI)  </c:when>
										   <c:otherwise>(NO) </c:otherwise>
										</c:choose>
										TIENE PERFIL EMPRENDEDOR <br>
										
										<input type="button" class="btn btn-default" onclick="location.href='${contextPath}/modificarPerfil';" value="Modificar Mi Perfil" />
										<input type="button" class="btn btn-default" onclick="location.href='${contextPath}/modificarClave';" value="Modificar Mi Contraseña" />
										
									</c:otherwise>
								</c:choose>
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