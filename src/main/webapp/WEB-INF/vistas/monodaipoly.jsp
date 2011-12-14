
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
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
                        $("#tren").animate({"left": "+=1000px"}, "slow");
                        $("#tren").animate({"top": "-=445px"}, "slow");
                       $("#tren").animate({"left": "-=800px"}, "slow");
                       

                        <%if(request.getParameter("registrado")!=null){%>
                        $("#usuarioRegistrado").html("<b>Usuario Registrado Correctamente</b>").fadeIn(1000).fadeOut(3000);
			<%}%>

                        <%if(request.getParameter("error")!=null){%>
                        $("#error").html("<b>Error al introducir los datos</b>").fadeIn(1000).fadeOut(3000);
			<%}%>

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

                            $("#campos").submit();
                            
                            
                        }



                    }
                    );
                    }
);
    </script>
    <title>Monodaipoly</title>
    <link type="text/css" rel="stylesheet" href="/Estilos/monodaipoly.css" />
</head>
  <body>
      <div id="fondo">
	<table align="center">
            <form action="/entrar" id="campos" method="post">
		<tr>
			<td><Strong>Usuario</Strong></td>
			<td>  <input id="nick" type="text" name="nick" value="" /></td>



                        <%if(request.getParameter("registrado")!=null){%>
                        <script>$("#usuarioRegistrado").html("<b>Usuario Registrado Correctamente</b>").fadeIn(1000).fadeOut(3000);</script>
			<%}%>
		</tr>
		<tr>
			<td><strong>Contrasena</strong></td>
			<td>  <input id="contrasena" type="password" name="contrasena" value="" /></td>
		</tr>


		<tr>
		<td><input id="botonEntrar"type="button" value="Entrar" onmouseover="this.style.color='green'" onmouseout="this.style.color='black'"/></td>

		</form>
                    <td><input type=button onclick="window.location.href='registro'" name="registrarse" value="Registrarse" onmouseover="this.style.color='green'" onmouseout="this.style.color='black'"/></td>
                </tr>
                </table>
                <table align="center">
                    <tr>
                        <td>
                            <p style="color:red" id="error"> </p>
                            <p style="color: yellow" id="usuarioRegistrado"> </p>
                        </td>
                    </tr>
                </table>
                <div id="tren"></div>
             </div>
      </body>
</html>
