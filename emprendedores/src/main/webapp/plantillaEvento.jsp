<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<head>

<script src="${contextPath}/assets/ckeditor/ckeditor.js"></script>
<script src="${contextPath}/assets/bootstrap-4.0.0/js/bootstrap.min.js"></script>

</head>
<body>
	<div class="form-group" id="plantillaEventoDiv">
		<h3>Nombre</h3>
		<spring:bind path="nombre">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<form:input type="text" path="nombre" class="form-control" placeholder="Nombre" value="${eventoGuardado.nombre}"></form:input>
				<form:errors path="nombre"></form:errors>
			</div>
		</spring:bind>

		<h3>Descripcion Corta</h3>
		<spring:bind path="descripcion">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<form:input type="text" maxlength="254" path="descripcion" class="form-control" placeholder="Descripcion" value="${eventoGuardado.descripcion}"></form:input>
				<form:errors path="descripcion"></form:errors>
			</div>
		</spring:bind>
																
		<h3>Pais</h3>
		<div class="form-group" id="paisDiv">
			Aguarde ...
		</div>
		<h3>Provincia</h3>
		<div class="form-group" id="provinciaDiv">
			Aguarde ...
		</div>
		<h3>Localidad</h3>
		<div class="form-group" id="localidadDiv">
			Aguarde ...
		</div>
		
		<h3>Descripcion Larga</h3>
		<div class="form-group ${status.error ? 'has-error' : ''}">
			<form:textarea name="editor1" id="editor1" rows="10" cols="80" path="descripcion" class="form-control" placeholder="Descripcion" value="${emprendimiento.descripcion}"></form:textarea>
			<form:errors path="descripcion"></form:errors>
		</div>
		<script>
			// Replace the <textarea id="editor1"> with a CKEditor
			// instance, using default configuration.
			CKEDITOR.replace( 'editor1' );
			CKEDITOR.instances['editor1'].setData('${emprendimiento.descripcion}');
		</script>
		
		<h3>Inscripcion</h3>
		<div class="form-group" id="VisibilidadDiv">
			<select class="form-control" id="tipoInscripcion" name="tipoInscripcion">
				<c:forEach items="${tiposInscripcion}" var="t">
					<option value="${t}" ${eventoGuardado.tipoInscripcion == t ? 'selected' : ''}>${t}</option>
				</c:forEach>
			</select>
		</div>
		
		<h3>Visibilidad</h3>
		<div class="form-group" id="InscripcionDiv">
			<select class="form-control" id="tipoVisibilidad" name="tipoVisibilidad">
				<c:forEach items="${tiposVisibilidad}" var="t">
					<option value="${t}" ${eventoGuardado.tipoVisibilidad == t ? 'selected' : ''}>${t}</option>
				</c:forEach>
			</select>
		</div>
		
		<h3>Fecha y Hora</h3>
		<div class="form-group" id="fechaDiv">
			<input id="fecha" name="fecha" type="text" class="form-control" value="${eventoGuardado.fecha}">
		</div>
		
		<h3>Mapa del lugar</h3>
		<div class="form-group" id="fechaDiv">
			<input id="fecha" name="fecha" type="text" class="form-control" value="${eventoGuardado.fecha}">
		</div>
		<!-- Button HTML (to Trigger Modal) -->
        
		<!-- Button trigger modal -->
		<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
		  Launch demo modal
		</button>
		
		
	</div>	
</body>
</html>