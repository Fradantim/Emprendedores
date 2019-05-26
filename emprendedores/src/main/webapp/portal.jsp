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
	<div class="container">
		<jsp:include page="navBar.jsp"/>
		<c:choose>
			<c:when test="${pageContext.request.userPrincipal.name != null}">
				<h2> Bienvenido ${pageContext.request.userPrincipal.name} | <a	onclick="document.forms['logoutForm'].submit()">Logout</a>
				</h2>
			</c:when>
			<c:otherwise>
				<h2>Bienvenido Anónimo!</h2>
			</c:otherwise>
		</c:choose>
		<%-- peligroso... 
		<security:authorize access="isAuthenticated()">
    		authenticated as <security:authentication property="principal.username" /> 
		</security:authorize>
		--%>
	</div>

</body>
</html>