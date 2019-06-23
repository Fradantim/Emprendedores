<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
			<table class="table table-bordered table-sm table-striped" id="tablaEventosCreados">
				<thead>
					<tr>
						<th>
							#
						</th>
						<th>
							Nombre
						</th>
						<th>
							Fecha
						</th>
						<th>
							Inscripcion
						</th>
						<th>
							Visibilidad
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
								<a href="${contextPath}/detalleEvento?idEvento=${e.id}">${e.nombre}</a>
							</td>
							<td>
								${e.fecha}
							</td>
							<td>
								${e.tipoInscripcion}
							</td>
							<td>
								${e.tipoVisibilidad}
							</td>
							<td>
								<c:choose>
									<c:when test="${e.estado == 'FINALIZADO'}">
										Evento Finalizado
									</c:when>
									<c:otherwise>
										<button type="button" class="btn btn-warning btn-sm" onclick="location.href='${contextPath}/modificarEvento?idEvento=${e.id}';">Modificar</button>
										<button type="button" class="btn btn-danger btn-sm" onclick="location.href='${contextPath}/borrarEvento?jsp=redirect:/miPerfil2&idEvento=${e.id}';">Borrar</button>
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