<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head></head>
<body>
	<div class="form-group" id="provinciaDiv">
		<select class="form-control" id="provinciaId" onload="buscarLocalidades();" onchange="buscarLocalidades();">
			<option></option>
			<c:forEach items="${provincias}" var="pr">
				<option value="${pr.id}" ${myProvinciaId == pr.id ? 'selected' : ''}>${pr.nombre}</option>
			</c:forEach>
		</select>
	</div>
</body>
</html>