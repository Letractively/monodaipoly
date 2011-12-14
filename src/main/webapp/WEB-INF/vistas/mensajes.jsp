<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div id="menuMensajes">
    
    <div id="redactar"><a onclick="javascript:redactarMensaje()">REDACTAR</a></div>
        <br/>
     <div id="recibidos"><p onclick="javascript:verBandejaEntrada()">RECIBIDOS</p></div>
        <br/>
     <div id=enviados">ENVIADOS</div>
        
</div>
   
    
<div class="mensajes ">
        <div id="enviarMensaje" style="visibility:hidden">
            <form action="/enviarMensajes" method="post" id="formMensajes">
                Destinatario:
                <div>
                    <input id="destinatario" type="text" name="destinatario" value="" />
                </div>            
                <br/>
                Asunto:
                <div>
                    <input id="asunto" type="text" name="asunto" value="" />
                </div>            
                <br/>
                Contenido:
                <div>
                    <textarea id="contenidoMensaje" COLS=100 ROWS=12 type="text" name="contenidoMensaje" value="" ></textarea>
                </div>              
                <br/>
                <div align="center">
                    <input id="EnviarMensaje" type="submit" value="Enviar"/>
                    <input id="Limpiarmensaje" type="reset" value="Limpiar"/>
                </div>        
            </form>
        </div>
        
        <div id="bandejaEntrada" style="visibility:hidden">
            
        </div>
</div>



<script>
      function redactarMensaje(){
            $("#bandejaEntrada").css("visibility","hidden");
            $("#enviarMensaje").css("visibility","visible");
        }
        function verBandejaEntrada(){
            $("#enviarMensaje").css("visibility","hidden");
            $("#bandejaEntrada").css("visibility","visible");
            /*alert("hola");
            $.get("/recibidos",null,function(str){
                alert(str);

            })
            alert("hola otra vez");*/
           
        }
        $(document).ready(function(){
            var array=new Array();
            alert("aaaaaaaa");
            <c:forEach var="mensajes" items="${bandejaEntrada}" varStatus="status">
             array[${status.index}]={idMensaje:${mensajes.idMensaje}}
            </c:forEach>
            var jsonArray = array.toSource();
            $.get("/recibidos",{
                mensajes:jsonArray
            },function(db){
                var mensajes = $.parseJSON(db);
                alert(mensajes);
            }, "json");

        })
    
</script>
