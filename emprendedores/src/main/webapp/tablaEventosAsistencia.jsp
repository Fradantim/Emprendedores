<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<head> </head>
<body>
<%-- class="table-active" || class="table-success" || class="table-warning" || class="table-danger --%>
	<c:choose>
		<c:when test="${empty eventos}">
			No se encontraron eventos para mostrar.
		</c:when>
		<c:otherwise>
			<table class="table table-bordered table-sm table-striped" id="tablaEventosAsistencia">
				<thead>
					<tr>
						<th>
							#
						</th>
						<th>
							Creador
						</th>
						<th>
							Nombre
						</th>
						<th>
							Fecha
						</th>
						<th>
							Ubicacion
						</th>
						<th>
							Acciones
						</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${eventos}" var="e">
						<tr>
							<td>
								${e.id}
							</td>
							<td>
								<c:choose>
									<c:when test="${e.creador.nick == pageContext.request.userPrincipal.name}">
										Yo
									</c:when>
									<c:otherwise>
										<a href="${contextPath}/detalleEmprendedor?idEmprendedor=${e.creador.id}">${e.creador.nick}</a>	
									</c:otherwise>
								</c:choose>
							</td>
							<td>
								<a href="${contextPath}/detalleEvento?idEvento=${e.id}">${e.nombre}</a>
							</td>
							<td>
								${e.fecha}
							</td>
							<td>
								${e.localidad.nombre}, ${e.localidad.provinciaNombre} - ${e.localidad.paisNombre}
							</td>
							<td>
								<c:choose>
									<c:when test="${e.estado == 'FINALIZADO'}">
										Evento Finalizado
									</c:when>
									<c:otherwise>
										<button type="button" class="btn btn-secondary btn-sm" onclick="location.href='${contextPath}/desasistirEvento?jsp=redirect:/miPerfil&idEvento=${e.id}&idUsuario=${usuarioLogueado.id}';">No asistire</button>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:otherwise>
	</c:choose>
</body>
</html>