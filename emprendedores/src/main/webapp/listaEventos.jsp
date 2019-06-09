<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="es">
<head> </head>

<body>
	<section class="box article-list">
		<security:authorize access="hasAuthority('EMPRENDEDOR')" var="isEmp" />
		<security:authorize access="hasAuthority('MODERADOR')" var="isMod" />
		<security:authorize access="hasAuthority('ADMINISTRADOR')" var="isAdmin" />
		<c:forEach items="${eventos}" var="e">
			<!-- Excerpt -->
			<article class="box excerpt">
				<a href="#" class="image left"><img src="../assets/css/images/pic06.jpg" alt="" /></a>
				<div>
					<header>
						<span class="date">
							 <fmt:formatDate pattern = "MMMMMMMMM dd" value = "${e.fechaCreacion}" />
						</span>
						<h3>
							<a href="${contextPath}/detalleEvento?idEvento=${e.id}">#${e.id} - ${e.nombre}</a>
							<c:if test="${e.finalizado}">
								<span> - [FINALIZADO]</span>
							</c:if>
						</h3>
					</header>
					Creador: ${e.creador.nick} <br>
					Cuando: ${e.fecha} <br>
					Donde: ${e.localidad.nombre}, ${e.localidad.provinciaNombre} - ${e.localidad.paisNombre} <br>
					<c:if test="${!e.finalizado && (e.creador.id != usuarioLogueado.id) && isEmp && e.tipoInscripcion =='ABIERTA'}">
						<div title="La inscripcion a emprendedores está abierta!">
							Emprendedores Bienvenidos!
						</div>
					</c:if>
					<p>${e.descripcion}</p>
				</div>
				<c:if test="${pageContext.request.userPrincipal.name != null}">
					<c:if test="${!e.finalizado && (e.creador.id != usuarioLogueado.id)}">
						<c:choose>
							<c:when test="${e.asiste}">
								<button type="button" class="btn btn-outline-secondary btn-sm text-dark" onclick="location.href='${contextPath}/desasistirEvento?idEvento=${e.id}&idUsuario=${usuarioLogueado.id}';">No asistire</button>
							</c:when>
							<c:otherwise>
								<button type="button" class="btn btn-outline-secondary btn-sm text-dark" onclick="location.href='${contextPath}/asistirEvento?idEvento=${e.id}&idUsuario=${usuarioLogueado.id}';">Asistire</button>
							</c:otherwise>
						</c:choose>
					</c:if>
				</c:if>
			</article>
		</c:forEach>											
	</section>
	<!-- Pagination -->
	<!--
	<nav>
		<ul class="pagination">
			<li class="page-item">
				<a class="page-link" href="#">Anterior</a>
			</li>
			<li class="page-item">
				<a class="page-link" href="#">1</a>
			</li>
			<li class="page-item">
				<a class="page-link" href="#">2</a>
			</li>
			<li class="page-item">
				<a class="page-link" href="#">3</a>
			</li>
			<li class="page-item">
				<a class="page-link" href="#">4</a>
			</li>
			<li class="page-item">
				<a class="page-link" href="#">5</a>
			</li>
			<li class="page-item">
				<a class="page-link" href="#">Siguiente</a>
			</li>
		</ul>
	</nav>
	-->
</body>

</html>