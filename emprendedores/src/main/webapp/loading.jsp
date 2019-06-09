<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html>
<head>
	<link rel="stylesheet" href="${contextPath}/assets/css/loading.css" />
</head>
<body>
	<div style="text-align:center; width:100%">
		<div class="lds-ring">
			<!-- Necesita tener divs hijos! -->
			<div></div>
			<div></div>
			<div></div>
			<div></div>
		</div>
	</div>
</body>
</html>