<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Portal Emprendedores</title>
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
	
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script	src="https://stackpath.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	
	<script	src="${contextPath}/resources/js/portal.js"></script>		
</head>
<body>
	
	<form id="logoutForm" method="POST" action="${contextPath}/logout">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
				
	<div class="modal fade" id="modal-popUp" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title"></h5>
				</div>
				<div class="modal-body"> ...  </div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"	data-dismiss="modal">Cerrar</button>
				</div>
			</div>
		</div>
	</div>
	
	<div class="container">
		<jsp:include page="navBar.jsp"/>
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
				<c:choose>
					<c:when test="${empty usuarioLogueado.ubicacion}">
				        Ubicacion no ingresada <br>
					</c:when>
					<c:otherwise>
						Pais: ${usuarioLogueado.ubicacion.pais} <br>
						Provincia: ${usuarioLogueado.ubicacion.provincia} <br>
						Localidad: ${usuarioLogueado.ubicacion.localidad} <br>
					</c:otherwise>
				</c:choose>
				---<br>
				<br>
				<security:authorize access="hasRole('EMPRENDEDOR')"	var="isEmprendedor" />
				<c:choose>
					<c:when test="${not isEmprendedor}">NO TIENE PERFIL EMPRENDEDOR <br>
					</c:when>
					<c:otherwise>
						ES EMPRENDEDOR: <br>
						Nombre: ${usuarioLogueado.emprendimiento.nombre} <br>
						Descripcion: ${usuarioLogueado.emprendimiento.descripcion} <br>
						Link: ${usuarioLogueado.emprendimiento.link} <br>
						Contacto: ${usuarioLogueado.emprendimiento.contacto} <br>
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
				<security:authorize access="hasRole('CLIENTE')">
					TIENE PERFIL CLIENTE<br>
				</security:authorize>
				
				<security:authorize access="hasRole('EMPRENDEDOR')" var="isEmprendedor" />
				<c:choose>
				   <c:when test="${isEmprendedor}">TIENE PERFIL EMPRENDEDOR <br> </c:when>
				   <c:otherwise>NO TIENE PERFIL EMPRENDEDOR <br></c:otherwise>
				</c:choose>
				
				
			</c:otherwise>
		</c:choose>
	</div>

</body>
</html>