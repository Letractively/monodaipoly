<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link type="text/css" rel="stylesheet" href="/Estilos/mainperfil.css" />


<title>Perfil</title>
</head>
<body>
	
    <div id="contenedor">
    	<div id="cabecera">
    	
    	    <h1>Bienvenido,  ${usuario.nick} !</h1>
           
    	
    	</div>
    	
    	<div id="contenido">
    		<div id="menu">
    		
    			<h3><strong><u>Datos personales</u></strong></h3>
    			
    		<table BORDER="0">
    		
    			<tr>
    				<td>
    				<strong>Nombre: ${usuario.nombre}</strong><br/>
    			
    			<br/>
    				</td>
    			</tr>
    			<tr> 
    				<td>
    				<strong>Apellido: ${usuario.apellido}</strong><br/>
    				
    			<br/>
    				</td>
    				
    			</tr>
    			<tr>
    				<td>
    			<strong>Fecha de Nacimiento:${usuario.fechaNacimiento}</strong><br/>
    			
    			<br/>
    			<td>
    			

    			<p><!--Clave <%//nose como ponerlo aki%>--></p>
    			</td>
    			</tr>
                        <tr>
    				<td>
    				<strong>Partidas Jugadas: ${usuario.partidasJugadas}</strong><br/>

    			<br/>
    				</td>
    			</tr>
                                <tr>
    				<td>
    				<strong>Partidas Ganadas: ${usuario.partidasGanadas}</strong><br/>

    			<br/>
    				</td>
    			</tr>
    		</table>
    		</div>
    	
    	</div>
            <tr>
                <td>
        	<form action="/crearJugador" method="post">
                	<input  type="submit" name="crearJugador" value="Crear Jugador" onmouseover="this.style.color='red'" onmouseout="this.style.color='black'"/>
                        <input type="hidden" name="nick" value="" />
                </form>
                </td>
                <td>
    		<form action="/modificarDatos" method="post">
    			<input type="submit" name="cambiarDatos" value="Modificar Datos" onmouseover="this.style.color='red'" onmouseout="this.style.color='black'" />
                        <input type="hidden" name="nick" value="${usuario.nick}" />
                </form>
                </td>
            </tr>
                <tr>
                    <button onclickonclick="window.location.href='tablero2'" onmouseover="this.style.color='red'" onmouseout="this.style.color='black'" ></button>
                </tr>
    	
    </div>
    <p></p>
    
    
</body>

</html>