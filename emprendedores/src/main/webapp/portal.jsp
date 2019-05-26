<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="es">
<html>
<head>
	<title>Portal Emprendedores</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
	<link rel="stylesheet" href="${contextPath}/assets/css/jquery-ui.css" />
	
	<!-- Scripts -->
	<script src="${contextPath}/assets/js/jquery.min.js"></script>
	<script src="${contextPath}/assets/js/jquery.dropotron.min.js"></script>
	<script src="${contextPath}/assets/js/browser.min.js"></script>
	<script src="${contextPath}/assets/js/breakpoints.min.js"></script>
	<script src="${contextPath}/assets/js/util.js"></script>
	<script src="${contextPath}/assets/js/main.js"></script>
	<script src="${contextPath}/assets/js/jquery-1.12.4.js"></script>
	<script src="${contextPath}/assets/js/jquery-ui.js"></script>
		
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
									<div class="col-8 col-12-medium">

										<!-- Article list -->
											<section class="box article-list">
												<h2 class="icon fa-file-text-o">Eventos recientes</h2>

												<!-- Excerpt -->
													<article class="box excerpt">
														<a href="#" class="image left"><img src="../assets/css/images/pic04.jpg" alt="" /></a>
														<div>
															<header>
																<span class="date">July 24</span>
																<h3><a href="#">Repairing a hyperspace window</a></h3>
															</header>
															<p>Phasellus quam turpis, feugiat sit amet ornare in, hendrerit in lectus
															semper mod quisturpis nisi consequat etiam lorem. Phasellus quam turpis,
															feugiat et sit amet ornare in, hendrerit in lectus semper mod quis eget mi dolore.</p>
														</div>
													</article>

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
						<div class="pagination">
								<!--<a href="#" class="button previous">Previous Page</a>-->
								<div class="pages">
									<a href="#" class="active">1</a>
									<a href="#">2</a>
									<a href="#">3</a>
									<a href="#">4</a>
									<span>&hellip;</span>
									<a href="#">20</a>
								</div>
								<a href="#" class="button next">Next Page</a>
							</div>
									</div>
									<div class="col-4 col-12-medium">
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
														<!--
														<footer>
															<a href="#" class="button icon fa-arrow-circle-o-right">Do Something</a>
														</footer>

														-->
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
		
		<script>
			$( function() {
			  $( "#datepicker" ).datepicker();
			} );
		</script>
</body>

</html>