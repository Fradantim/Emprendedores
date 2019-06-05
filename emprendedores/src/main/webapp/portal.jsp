<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="es">
<head>
	<title>Portal Emprendedores</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
	<link rel="stylesheet" href="${contextPath}/assets/css/jquery-ui.css" />

</head>

<body class="homepage is-preload">
		<div id="page-wrapper">

			<!-- Header -->
				<div id="header-wrapper">
					<div class="container">

						<!-- Header -->
							<header id="header">
								<div class="inner">
									<jsp:include page="navBar.jsp"/>
								</div>
							</header>

						<!-- Banner 
							<div id="banner">
								<h2><strong>ZeroFour:</strong> A free responsive site template
								<br />
								built on HTML5 and CSS3 by <a href="http://html5up.net">HTML5 UP</a></h2>
								<p>Does this statement make you want to click the big shiny button?</p>
								<a href="#" class="button large icon fa-check-circle">Yes it does</a>
							</div>-->

					</div>
				</div>

			<!-- Main Wrapper -->
				<div id="main-wrapper">
					<div class="wrapper style3">
						<div class="inner">
							<div class="container">
								<div class="row">
								
									<div class="col-md-8">

										<!-- Article list -->
											<section class="box article-list">
												<h2 class="icon fa-file-text-o">Eventos recientes</h2>
													<security:authorize access="hasAuthority('EMPRENDEDOR')" var="isEmp" />
													<security:authorize access="hasAuthority('MODERADOR')" var="isMod" />
													<security:authorize access="hasAuthority('ADMINISTRADOR')" var="isAdmin" />
													<c:forEach items="${eventos}" var="e">
														<!-- Excerpt -->
														<article class="box excerpt">
															<a href="#" class="image left"><img src="../assets/css/images/pic06.jpg" alt="" /></a>
															<div>
																<header>
																	<span class="date">
																		 <fmt:formatDate pattern = "MMMMMMMMM dd" value = "${e.fechaCreacion}" />
																	</span>
																	<h3><a href="${contextPath}/detalleEvento?idEvento=${e.id}">#${e.id} - ${e.nombre}</a></h3>
																</header>
																Creador: ${e.creador.nick} <br>
																Cuando: ${e.fecha} <br>
																Donde: ${e.localidad.nombre}, ${e.localidad.provinciaNombre} - ${e.localidad.paisNombre} <br>
																Inscripcion: ${e.tipoInscripcion} <br>
																<p>${e.descripcion}</p>
															</div>
															<c:if test="${pageContext.request.userPrincipal.name != null}">
																<c:if test="${(e.creador.id == usuarioLogueado.id || isMod || isAdmin) && ! e.finalizado}">
																	<button type="button" class="btn btn-outline-warning btn-sm text-dark" onclick="location.href='${contextPath}/modificarEvento?idEvento=${e.id}';">Modificar</button>
																	<button type="button" class="btn btn-outline-danger btn-sm text-dark" onclick="location.href='${contextPath}/borrarEvento?idEvento=${e.id}';">Borrar</button>
																</c:if>
																<c:if test="${isEmp && e.abierto && !e.finalizado && (e.creador.id != usuarioLogueado.id)}">
																	<c:choose>
																		<c:when test="${e.inscripto}">
																			<button type="button" class="btn btn-outline-secondary btn-sm text-dark" onclick="location.href='${contextPath}/desinscribirmeEvento?idEvento=${e.id}';">Desinscribirme</button>
																		</c:when>
																		<c:otherwise>
																			<button type="button" class="btn btn-outline-secondary btn-sm text-dark" onclick="location.href='${contextPath}/inscribirmeEvento?idEvento=${e.id}';">Inscribirme</button>
																		</c:otherwise>
																	</c:choose>
																</c:if>
															</c:if>
														</article>
													</c:forEach>

												<!-- Excerpt -->
													<article class="box excerpt">
														<a href="#" class="image left"><img src="../assets/css/images/pic05.jpg" alt="" /></a>
														<div>
															<header>
																<span class="date">July 18</span>
																<h3><a href="#">Adventuring with a knee injury</a></h3>
															</header>
															<p>Phasellus quam turpis, feugiat sit amet ornare in, hendrerit in lectus
															semper mod quisturpis nisi consequat etiam lorem. Phasellus quam turpis,
															feugiat et sit amet ornare in, hendrerit in lectus semper mod quis eget mi dolore.</p>
														</div>
													</article>

												<!-- Excerpt -->
													<article class="box excerpt">
														<a href="#" class="image left"><img src="../assets/css/images/pic06.jpg" alt="" /></a>
														<div>
															<header>
																<span class="date">July 14</span>
																<h3><a href="#">Preparing for Y2K38</a></h3>
															</header>
															<p>Phasellus quam turpis, feugiat sit amet ornare in, hendrerit in lectus
															semper mod quisturpis nisi consequat etiam lorem. Phasellus quam turpis,
															feugiat et sit amet ornare in, hendrerit in lectus semper mod quis eget mi dolore.</p>
														</div>
													</article>

											</section>
											<!-- Pagination -->
											<nav>
											<ul class="pagination">
												<li class="page-item">
													<a class="page-link" href="#">Anterior</a>
												</li>
												<li class="page-item">
													<a class="page-link" href="#">1</a>
												</li>
												<li class="page-item">
													<a class="page-link" href="#">2</a>
												</li>
												<li class="page-item">
													<a class="page-link" href="#">3</a>
												</li>
												<li class="page-item">
													<a class="page-link" href="#">4</a>
												</li>
												<li class="page-item">
													<a class="page-link" href="#">5</a>
												</li>
												<li class="page-item">
													<a class="page-link" href="#">Siguiente</a>
												</li>
											</ul>
										</nav>
									</div>
									<div class="col-md-4">
											<div id="sidebar">
	
												<!-- Sidebar -->
	
													<section>
														<header class="major">
															<h2>Calendario de Eventos</h2>
														</header>
													<center>
														<div id="datepicker"></div>
													</center>
														<!--
														<footer>
															<a href="#" class="button icon fa-info-circle">Find out more</a>
														</footer>
														-->
													</section>
	
													<section>
														<header class="major">
															<h2>Ultimos Emprendedores</h2>
														</header>
														<ul class="style2">
															<li><a href="#">Amet turpis, feugiat et sit amet</a></li>
															<li><a href="#">Ornare in hendrerit in lectus</a></li>
															<li><a href="#">Semper mod quis eget mi dolore</a></li>
															<li><a href="#">Quam turpis feugiat sit dolor</a></li>
															<li><a href="#">Amet ornare in hendrerit in lectus</a></li>
															<li><a href="#">Semper mod quisturpis nisi</a></li>
															<li><a href="#">Consequat etiam lorem phasellus</a></li>
															<li><a href="#">Amet turpis, feugiat et sit amet</a></li>
															<li><a href="#">Semper mod quisturpis nisi</a></li>
														</ul>
													</section>
											</div>
										</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<!-- Footer Wrapper -->
				<div id="footer-wrapper">
					<jsp:include page="footerNav.jsp"/>
				</div>
		</div>
		
		<script src="${contextPath}/assets/js/jquery-1.12.4.js"></script>
		<script src="${contextPath}/assets/js/jquery-ui.js"></script>
		<script>		
			$( function() {
			  $( "#datepicker" ).datepicker();
			} );
		</script>
</body>

</html>