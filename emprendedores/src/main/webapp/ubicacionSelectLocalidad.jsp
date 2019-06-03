<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head></head>
<body>
	<div class="form-group" id="localidadDiv">
		<select class="form-control" id="localidadId" name="localidadId">
			<option></option>
			<c:forEach items="${localidades}" var="lo">
				<option value="${lo.id}" ${myLocalidadId == lo.id ? 'selected' : ''}>${lo.nombre}</option>
			</c:forEach>
		</select>
	</div>
</body>
</html>