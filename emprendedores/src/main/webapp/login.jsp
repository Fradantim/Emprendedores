<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="es">
  <head>
      <title>Ingrese a su cuenta</title>
      
      <meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
  </head>

<%-- 
  <body>

    <div class="container">
      <form method="POST" action="${contextPath}/login" class="form-signin">
        <h2 class="form-heading">Ingreso</h2>

        <div class="form-group ${error != null ? 'has-error' : ''}">
            <span>${message}</span>
            <input name="username" type="text" class="form-control" placeholder="Nick"/>
            <input name="password" type="password" class="form-control" placeholder="Contraseña"/>
            <span>${error}</span>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <button class="btn btn-lg btn-primary btn-block" type="submit">Ingresar</button>
            <h4 class="text-center"><a href="${contextPath}/registro">Crear una cuenta</a></h4>
        </div>
      </form>
    </div>

  </body>
  
 --%>
  
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
							</div>
-->
					</div>
				</div>

			<!-- Main Wrapper -->
				<div id="main-wrapper">
					<div class="wrapper style1">
						<div class="inner">

							<!-- Sing in  Form -->
							<section class="sign-in">
								<div class="container">
									<div class="signin-content">
										<div class="signin-form">
											<h2 class="form-title">Sign in</h2>
											<form method="POST" action="${contextPath}/login" class="form-signin">
												<div class="form-group" >
													<label for="your_name"><i class="zmdi zmdi-account material-icons-name"></i></label>
													<input type="text" name="username" id="username" placeholder="Your Name"/>
												</div>
												<div class="form-group">
													<label for="your_pass"><i class="zmdi zmdi-lock"></i></label>
													<input type="password" name="password" id="password" placeholder="Password"/>
													<span>${error}</span>
													<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
												</div>
												<div class="form-group">
													<input type="checkbox" name="remember-me" id="remember-me" class="agree-term" />
													<label for="remember-me" class="label-agree-term"><span><span></span></span>Remember me</label>
												</div>
												<div class="form-group form-button">
													<input type="submit" name="signin" id="signin" class="form-submit" value="Log in"/>
													
												</div>
											</form>
											<div class="social-login">
												<span class="social-label">Or login with</span>
												<ul class="socials">
													<li><a href="#"><i class="display-flex-center zmdi zmdi-facebook"></i></a></li>
													<li><a href="#"><i class="display-flex-center zmdi zmdi-twitter"></i></a></li>
													<li><a href="#"><i class="display-flex-center zmdi zmdi-google"></i></a></li>
												</ul>
											</div>
										</div>
										<div class="signin-image">
											<center>
												<figure ><img src="${contextPath}/assets/css/images/signin-image.jpg" alt="Crear Cuenta"></figure>
											</center>
												<a href="${contextPath}/registro" class="signup-image-link">Crear Cuenta</a>
											</div>
									</div>
								</div>
							</section>

						</div>
					</div>
					
				</div>

			<!-- Footer Wrapper -->
				<div id="footer-wrapper">
					<jsp:include page="footerNav.jsp"/>
				</div>

		</div>

		<!-- Scripts -->
			<script src="${contextPath}/assets/js/jquery.min.js"></script>
			<script src="${contextPath}/assets/js/jquery.dropotron.min.js"></script>
			<script src="${contextPath}/assets/js/browser.min.js"></script>
			<script src="${contextPath}/assets/js/breakpoints.min.js"></script>
			<script src="${contextPath}/assets/js/util.js"></script>
			<script src="${contextPath}/assets/js/main.js"></script>

	</body>
</html>