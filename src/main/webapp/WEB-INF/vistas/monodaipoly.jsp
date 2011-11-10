<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>

<html>
<head>
                <script type="text/javascript" src="jQuery/js/jquery.min.js"></script>
		<script language="javascript" type="text/javascript">
                    $(document).ready(
                    function(){
                    $("#botonEntrar").click(
                            function (){
                                var n=document.getElementsByName("nick");
                                var c=document.getElementsByName("contrasena");
                        
                        if(n[0].value==0){
                            $("#error").html("<b>No has introducido usuario</b>").fadeIn(1000).fadeOut(3000);

                        }
                        else if(c[0].value==0){
                            $("#error").html("<b>No has introducido contrasena</b>").fadeIn(1000).fadeOut(3000);

                        }else{
                            $("#campos").submit(window.location.href='entrar');
                        }
                        

                    }
                    );
                    }
);
    </script>
    <title>Monodaipoly</title>
    <link type="text/css" rel="stylesheet" href="/Estilos/main.css" />
</head>
  <body>

	<table align="center">
            <form action="/entrar" id="campos" method="post">
		<tr>
			<td><Strong>Usuario</Strong></td>
			<td>  <input type="text" name="nick" value="" /></td>
			
			
			
                        <%if(request.getParameter("registrado")!=null){%>
			<td style="color:green">Usuario Registrado Correctamente</td>
			<%}%>
		</tr>
		<tr>
			<td><strong>Contrasena</strong></td>
			<td>  <input type="password" name="contrasena" value="" /></td>
		</tr>
                
             
		<tr>
		<td><input id="botonEntrar"type="button" value="Entrar" onmouseover="this.style.color='green'" onmouseout="this.style.color='black'"/></td>
                
		</form>
                    <td><input class="mkbutton" type=button onclick="window.location.href='registro'" name="registrarse" value="Registrarse"onmouseover="this.style.color='green'" onmouseout="this.style.color='black'"/></td>
                </tr>
                </table>
                <table align="center">
                    <tr>
                        <td>
                            <p style="color:red" id="error"> </p>
                        </td>
                    </tr>
                </table>
                
  </body>
</html>
