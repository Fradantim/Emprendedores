<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head></head>
<body>
	<div class="form-group" id="localidadDiv">
		<select class="form-control" id="localidadId" name="localidadId">
			<option value="X"></option>
			<c:forEach items="${localidades}" var="lo">
				<option value="${lo.id}" ${lo.selected ? 'selected' : ''}>${lo.nombre}</option>
			</c:forEach>
		</select>
	</div>
</body>
</html>