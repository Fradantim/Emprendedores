<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
 
<head>

	<link rel="stylesheet" href="${contextPath}/assets/bootstrap-4.0.0/css/bootstrap.css" />
	<link rel="stylesheet" href="${contextPath}/assets/css/main.css" />
	
			<!-- Font Icon -->
		<link rel="stylesheet" href="${contextPath}/assets/fonts/material-icon/css/material-design-iconic-font.min.css">
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://rawgit.com/notifyjs/notifyjs/master/dist/notify.js"></script>
</head>

<body>

<security:authorize access="hasAuthority('EMPRENDEDOR')" var="isEmp" />

	<form id="logoutForm" method="POST" action="${contextPath}/logout">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
	<script>
		<c:forEach var="m" items="${mensajes}">
		    console.log('${m.mensaje}');
		    $.notify(${m.build});
		</c:forEach>
	</script>
	
	<div class="logo" id="logo">
           <!--  <a class="navbar-brand" href="#"></a>--> 
           <img  src="${contextPath}/assets/pics/logo.png" alt="">
        </div>
        
	<nav id="nav">
	<!-- Nav -->
		<ul>
			<li><a href="${contextPath}/portal">Home</a></li>
			<!--  
			<li class="current_page_item"><a href="${contextPath}/portal">Home</a></li>
			<li>
				<a href="#">Dropdown</a>
				<ul>
					<li><a href="#">Lorem ipsum dolor</a></li>
					<li><a href="#">Magna phasellus</a></li>
					<li>
						<a href="#">Phasellus consequat</a>
						<ul>
							<li><a href="#">Lorem ipsum dolor</a></li>
							<li><a href="#">Phasellus consequat</a></li>
							<li><a href="#">Magna phasellus</a></li>
							<li><a href="#">Etiam dolore nisl</a></li>
						</ul>
					</li>
					<li><a href="#">Veroeros feugiat</a></li>
				</ul>
			</li>
			<li><a href="left-sidebar.html">Left Sidebar</a></li>
			<li><a href="right-sidebar.html">Right Sidebar</a></li>
			<li><a href="no-sidebar.html">No Sidebar</a></li>
			-->
			<c:choose>
				<c:when test="${pageContext.request.userPrincipal.name != null}">
					<li>
						<security:authorize access="hasAuthority('EMPRENDEDOR')" >
							<a href="${contextPath}/crearEvento">Crear Evento</a>
						</security:authorize>
					</li>
					<li>
					
					<c:if test="${isEmp != null}">
						<c:choose>
							<c:when test="${isEmp == true}">
								<a href="#">Mi Perfil</a>
								<ul>
									<li>
										<a href="${contextPath}/miPerfil">Usuario</a>
									</li>
									<li>
										<a href="${contextPath}/miPerfil2">Emprendedor</a>
									</li>
								</ul>
							</c:when>
							<c:otherwise>
								<a href="${contextPath}/miPerfil">Mi Perfil</a>
							</c:otherwise>
						</c:choose>
					</c:if>
					
						
					</li>
					<li><a href="#" onclick="document.forms['logoutForm'].submit()">Log out</a></li>
					<li><p id="usuarioLogueado" class="navbar-text">Usuario: ${pageContext.request.userPrincipal.name}</p> </li>
				</c:when>
				<c:otherwise>
					<li><p id="usuarioLogueado" class="navbar-text"></p> </li>
					<li><a href="${contextPath}/login">Ingresar</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
   </nav>
</body>