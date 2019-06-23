<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head></head>
<body>
	<div class="form-group" id="provinciaDiv">
		<select class="form-control" id="provinciaId" onload="buscarLocalidades();" onchange="buscarLocalidades();">
			<option value="X"></option>
			<c:forEach items="${provincias}" var="pr">
				<option value="${pr.id}" ${pr.selected ? 'selected' : ''}>${pr.nombre}</option>
			</c:forEach>
		</select>
	</div>
</body>
</html>