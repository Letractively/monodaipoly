<%@page import="javax.persistence.Query"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>


<html>
<head>
    <script language="javascript" type="text/javascript">
                    function comprobar_campos(){
                        var n=document.getElementsByName("nick");
                        var c=document.getElementsByName("contrasena");
                        
                        if(n[0].value==0){
                            alert("No has introducido un usuario");
                            return false;
                        }
                        else if(c[0].value==0){
                            alert('No has introducido una contrasena');

                        }
                        

                    }


    </script>
    <title>Registro de usuarios</title>

	</head>
<body>
 
<h3>Registro de usuarios</h3>
 
<form action="/registrando" method="post"name="contenido">

	<fieldset>
		<legend>Datos Personales</legend>
		Nombre:
  		<div>
  		<input type="text" name="nombre" value="" />
  		</div>
  		<br>
  		Apellido:
  		<div>
  		<input type="text" name="apellido" value="" />
  		</div>
  		<br>
  		<div>
  		
  		<label for="fecha_dia">Fecha de nacimiento</label> <br/>
 	 <input type="text" size="3" maxlength="2" name="fechaDia" />
 		 de
 	 <select name="fechaMes">
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
 	 </select>
  de
  <input type="text" size="5" maxlength="4" name="fechaAno" />
 
  <br/><br/>
  		</div>
		</fieldset>
	<fieldset>
		<legend>Datos del Juego</legend>
		Nick:
  		<div>
  		<input type="text" name="nick" value="" />
                 
  		</div>
               
                
		Contrasena:
		<div>
		 <input type="password" name="contrasena" value="" />
		</div>
			
		</fieldset>
		
				 <input type="reset" value="Restaurar">
				
 		 		<input type="submit" value="Registrarse" onclick="comprobar_campos()"  />
 		 		
</form>


	<div><input type="button" name="volver" value="Volver al inicio" onclick="window.location.href='monodaipoly'"/></div>
</body>
</html>