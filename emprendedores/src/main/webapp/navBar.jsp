<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<head>
	<link rel="stylesheet" href="${contextPath}/assets/css/main.css" />
			<!-- Font Icon -->
		<link rel="stylesheet" href="${contextPath}/assets/fonts/material-icon/css/material-design-iconic-font.min.css">
</head>
<body>
	<form id="logoutForm" method="POST" action="${contextPath}/logout">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>

	<!-- Nav -->
	<nav id="nav">
		<ul>
			<li class="current_page_item"><a href="${contextPath}/portal">Home</a></li>
			<!--  
			<li>
				<a href="#">Dropdown</a>
				<ul>
					<li><a href="#">Lorem ipsum dolor</a></li>
					<li><a href="#">Magna phasellus</a></li>
					<li>
						<a href="#">Phasellus consequat</a>
						<ul>
							<li><a href="#">Lorem ipsum dolor</a></li>
							<li><a href="#">Phasellus consequat</a></li>
							<li><a href="#">Magna phasellus</a></li>
							<li><a href="#">Etiam dolore nisl</a></li>
						</ul>
					</li>
					<li><a href="#">Veroeros feugiat</a></li>
				</ul>
			</li>
			<li><a href="left-sidebar.html">Left Sidebar</a></li>
			<li><a href="right-sidebar.html">Right Sidebar</a></li>
			<li><a href="no-sidebar.html">No Sidebar</a></li>
			-->
			<c:choose>
				<c:when test="${pageContext.request.userPrincipal.name != null}">
					<li><a href="${contextPath}/miPerfil">Mi Perfil</a></li>
					<li><a href="#" onclick="document.forms['logoutForm'].submit()">Log out</a></li>
					<li><p class="navbar-text">Logueado como: ${pageContext.request.userPrincipal.name}</p> </li>
				</c:when>
				<c:otherwise>
					<li><a href="${contextPath}/login">Ingresar</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</nav>
</body>