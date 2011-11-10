<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Random" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="monodaipoly.dao.UsuarioDAO" %>
<%@page import="monodaipoly.dao.UsuarioDAOImpl"%>
<%@page import="monodaipoly.persistencia.Usuario"%>


<html>
	<head>
            <title>Modificar datos</title> </head>
	
	<body>
		<form action="/modificando" method="post">

			<fieldset>
				<legend>Datos Personales</legend>
				Contraseña:
  				<div>
  				<input type="password" name="contraseñaNueva" value="" />
  				</div>
  				<br>
                                Nombre:
  				<div>
  				<input type="text" name="nombre" value="${usuario.nombre}" />
  				</div>
  				<br>
  				Apellido:
  				<div>
  				<input type="text" name="apellido" value="${usuario.apellido}" />
  				</div>
  				<br>
  				<div>
  		
  				<label for="fecha_dia">Fecha de nacimiento</label> <br/>
 				 <input type="text" size="3" maxlength="2" name="fechaDia" />
 				 de
 				 <select name="fechaMes" value="Mes">
 				   <option value="1">Enero</option>
  					<option value="2">Febrero</option>
  	 				<option value="3">Marzo</option>
   					<option value="4">Abril</option>
   		 			<option value="5">Mayo</option>
   	 				<option value="6">Junio</option>
   	 				<option value="7">Julio</option>
                                        <option value="8">Agosto</option>
                                        <option value="9">Septiembre</option>
   	 				<option value="10">Octubre</option>
   	 				<option value="11">Noviembre</option>
   	 				<option value="12">Diciembre</option>
                                        <option selected="0"></option>
 	 			</select>
 				 de
 				 <input type="text" size="5" maxlength="4" name="fechaAño" />

                                 <input type="hidden" name="nick" value="${usuario.nick}" />

 			 <br/><br/>
  				</div>
			</fieldset>
			
				<input type="reset" value="Restaurar">
 		 		<input type="submit" value="Guardar Cambios"/>
			
		</form>
	
			
			<div>
                            <form action="/entrar" method="post">
                                <input type="submit" value="Volver a Perfil sin guardar cambios" />                          
                                <input type="hidden" name="nick" value="${usuario.nick}" />
                                <input type="hidden" name="contraseña" value="${usuario.contraseña}" />

                            </form>
                        </div>
		
	
	
	
	
	</body>
</html>