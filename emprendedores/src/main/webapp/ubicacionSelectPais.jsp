<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head></head>
<body>
	<div class="form-group" id="paisDiv">
		<select class="form-control" id="paisId" onload="buscarProvincias();" onchange="buscarProvincias();">
			<option></option>
			<c:forEach items="${paises}" var="pa">
				<option value="${pa.id}" ${myPaisId == pa.id ? 'selected' : ''}>${pa.nombre}</option>
			</c:forEach>
		</select>
	</div>
</body>
</html>