<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div id="menuMensajes">
    <br/>
    <div id="redactar1"><p onclick="javascript:EnviarMensajes()">REDACTAR</p></div>
    <br/>
    <div id="recibidos2" ><p onclick="javascript:verBandejaEntrada()">RECIBIDOS</p></div>
    <br/>


</div>


<div id="enviarMensaje" >
    <form action="/enviarMensajes" method="get" id="formMensajes">
        Destinatario:
        <div>
            <input id="destinatario" type="text" name="destinatarioMensaje" value="" />
        </div>
        <br/>
        Contenido:
        <div>
            <textarea id="contenidoMensaje" cols="100" rows="10" type="text" name="contenidoMensaje" value="" ></textarea>
        </div>
        <br/>
        <div id="mensaje" align="center">
            <input id="EnviarMensajeboton" type="button" value="Enviar" />
            <input id="Limpiarmensajeboton" type="reset" value="Limpiar"/>
        </div>

    </form>
</div>

<script>

    $(document).ready(function(){

        $("#EnviarMensajeboton").click(
        function (){

            var d=$("#destinatario").val();
            var c=$("#contenidoMensaje").val();

         


            if(d.length==0){
                            
                $("#estadoMensaje").html("<b>No has introducido destinatario</b>").fadeIn(1000).fadeOut(3000);

            }else if(c.length==0){
                $("#estadoMensaje").html("<b>No has introducido ningun mensaje</b>").fadeIn(1000).fadeOut(3000);

            }else{
                
                /*$("#formMensajes").submit();*/

                $.get("/enviarMensaje2",{
                    destinatario:$("#destinatario").val(),
                    contenido:$("#contenidoMensaje").val()
                }, function(json){
                    
                    var db = $.parseJSON(json);
                    $("#estadoMensaje").html("<b>"+db.estado+"</b>").fadeIn(1000).fadeOut(3000);
                }, "json");

                $.get("/getDatosURL",null,
                    function(html) {
                        $("#contenido").html(html);
                    }

               );

                
            }
        }
    );
     

    });
                        
</script>