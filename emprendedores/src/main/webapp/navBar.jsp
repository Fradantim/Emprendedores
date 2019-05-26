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
	
		<%--
	<div class="modal fade" id="modal-popUp" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title"></h5>
				</div>
				<div class="modal-body"> ...  </div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"	data-dismiss="modal">Cerrar</button>
				</div>
			</div>
		</div>
	</div>
	
 
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<a class="navbar-brand" href="${contextPath}/"> 
					<img alt="Emprendedores" src="favicon.ico" style="width:15px;height:15px;">
				</a>
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> 
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
			</div>
	
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="#">Menu1</a></li>
					<li><a href="#">Menu2</a></li>
				</ul>
				
				<ul class="nav navbar-nav navbar-right">
					<c:choose>
						<c:when test="${pageContext.request.userPrincipal.name != null}">
							<li><p class="navbar-text">Logueado como: ${pageContext.request.userPrincipal.name}</p> </li>
							<li> <input type="button" class="btn btn-default navbar-btn" onclick="location.href='${contextPath}/miPerfil';" value="Mi Perfil" /> </li>
							<li> <button type="button" class="btn btn-default navbar-btn" onclick="document.forms['logoutForm'].submit()">Log out</button> </li>
						</c:when>
						<c:otherwise>
							<li><button type="button" class="btn btn-default navbar-btn" onclick="logInPopUp();">Log in</button> </li>
						</c:otherwise>
					</c:choose>
				</ul>				
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
	--%>
	
	
	<!-- Logo 
		<h1><a href="index.html" id="logo">ZeroFour</a></h1>
	-->
	<!-- Nav -->
	<nav id="nav">
		<ul>
			<li class="current_page_item"><a href="${contextPath}/portal">Home</a></li>
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