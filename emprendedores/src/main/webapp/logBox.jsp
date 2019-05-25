<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<head>
	<title>Ingrese o cree su cuenta</title>
</head>

<body>
	<div class="modal-body">
		<div class="row">
    <div class="col-md-6 col-md-border">
    	<form method="POST" action="${contextPath}/login" class="form-signin">
			  <h2 class="form-signin-heading">Tengo Usuario</h2>

			<div class="form-group ${error != null ? 'has-error' : ''}">
				<span>${message}</span> 
				<input name="nick" type="text" class="form-control" placeholder="Usuario" />
				<input name="password" type="password" class="form-control" placeholder="Contraseña" /> 
				<span>${error}</span> 
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				
				<button class="btn btn-lg btn-primary btn-block" type="submit">Ingresar</button>
			</div>
		</form>
    </div>
    <div class="col-md-6 col-md-border">
    	<%-- <form method="POST" action="${contextPath}/registro" class="form-signin">
			<h3 class="form-heading">No Tengo Usuario</h3>

			<div class="form-group ${error != null ? 'has-error' : ''}">
				<span>${message}</span> 
				<input name="username" type="text" class="form-control" placeholder="Nickname" />
				<input name="nombre" type="text" class="form-control" placeholder="Nombres" />
				<input name="apellido" type="text" class="form-control" placeholder="Apellidos" />
				<input name="email" type="text" class="form-control" placeholder="E-Mail" />
				<input name="password" type="password" class="form-control" placeholder="Contraseña" />
				<input name="passwordConfirm" type="password" class="form-control" placeholder="Repetir contraseña" />  
				<span>${error}</span> 
				
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				
				<button class="btn btn-lg btn-primary btn-block" type="submit">Crear Cuenta</button>
			</div>
		</form>--%>
		
		<form:form method="POST" action="${contextPath}/registro" modelAttribute="userForm" class="form-signin">
            <h2 class="form-signin-heading">Crea tu cuenta</h2>
            <spring:bind path="nick">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="nick" path="nick" class="form-control" placeholder="NickName"></form:input>
                    <form:errors path="nick"></form:errors>
                </div>
            </spring:bind>
            
            <spring:bind path="nombre">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="nombre" class="form-control" placeholder="Nombres"></form:input>
                    <form:errors path="nombre"></form:errors>
                </div>
            </spring:bind>
            
            <spring:bind path="apellido">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="apellido" class="form-control" placeholder="Apellidos"></form:input>
                    <form:errors path="apellido"></form:errors>
                </div>
            </spring:bind>
            
            <spring:bind path="email">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="email" class="form-control" placeholder="E-Mail"></form:input>
                    <form:errors path="email"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="password">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="password" path="password" class="form-control" placeholder="Contraseña"></form:input>
                    <form:errors path="password"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="passwordConfirm">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="password" path="passwordConfirm" class="form-control" placeholder="Confirme su contraseña"></form:input>
                    <form:errors path="passwordConfirm"></form:errors>
                </div>
            </spring:bind>

            <button class="btn btn-lg btn-primary btn-block" type="submit">Crear cuenta!</button>
        </form:form>
    </div>
</div>  
	</div>
			<%--
		<table>
  			<tr>
  				<th>
	  				<form method="POST" action="${contextPath}/login" class="form-signin">
						<h2 class="form-heading">Ingreso</h2>
			
						<div class="form-group ${error != null ? 'has-error' : ''}">
							<span>${message}</span> 
							<input name="username" type="text" class="form-control" placeholder="Usuario" />
							<input name="password" type="password" class="form-control" placeholder="Contraseña" /> 
							<span>${error}</span> 
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
							<button class="btn btn-lg btn-primary btn-block" type="submit">Ingresar</button>
						</div>
					</form>
  				</th>
	  			<th>
	  				<button class="btn btn-lg btn-primary btn-block" type="submit">Ingresar</button>
	  			</th>
  			</tr>
  		</table>
  		 --%>
</body>
</html>