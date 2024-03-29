<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>



<html>
    <head>

        <link type="text/css" rel="stylesheet" href="/Estilos/perfil2.css" />

        <!-- Librerías de JQuery -->
        <script type="text/javascript" src="jQuery/js/jquery.min.js"></script>
        <script type="text/javascript" src="jQuery/js/jquery-ui.custom.min.js"></script>
        <script type="text/javascript">
        
            function datosPersonales(){
                 $.get("/getDatosURL",null,
                    function(html) {
                        $("#contenido").html(html);
                    }

               );
            }
            
            function estadisticas(){
                $.get("/estadisticas",null,
                    function(html) {
                        $("#contenido").html(html);
                    }

               );
            }
            function mensajes(){
                  $.get("/mensajes",null,
                    function(html) {
                        $("#contenido").html(html);
                    }

               );
            }
            
            
            
              
            
            function salirPerfil(){
                
                $.getScript("/logout",null);
                
            }
            
          
                $(document).ready(
                    function (){
                        if($("#estadoMensaje").attr("value")!=""){
                            var estado = $("#estadoMensaje").attr("value");
                            if(estado=="ERROR"){
                                $("#estadoMensaje").css("visibility","visible");
                                $("#estadoMensaje").html("<b>Imposible enviar mensaje.<br/>Destinatario no existente</b>").fadeIn(1000).fadeOut(10000);
                                
                            }else if(estado=="CORRECTO"){
                                $("#estadoMensaje").css("visibility","visible");
                                $("#estadoMensaje").html("<b>Mensaje enviado correctamente</b>").fadeIn(1000).fadeOut(10000);
                                
                            }
                        }
                });
         

        



        </script>
        <title>Perfil de ${usuario.nick}</title>
</head>
    <body>
        <div id="fondo">
   
         <h1>Bienvenido,  ${usuario.nick} !</h1>
         <div id="menu">
             <div id="datospersonales"><a class="botonMenu" onclick="javascript:datosPersonales()">DATOS PERSONALES</a></div>
             <div id="estadisticas"><a class="botonMenu" onclick="javascript:estadisticas()">RANKING</a></div>
             <div id="mensajes"><a class="botonMenu" onclick="javascript:mensajes()">MENSAJES</a></div>
             <div id="salir"><a class="botonMenu" id="logout" href="/logout">SALIR</a></div>

             
         </div> 
         
         <div id="contenido">
             <div id="estadoMensaje" value="${estado}" style="visibility:hidden">
             </div>
         </div>
         
         <div id="pie">
             <input type="button" id="aJugar" value="¡ A JUGAR !" onclick="window.location.href='tablero2'"></input>
         </div>
         
         
        </div>


    </body>
</html>