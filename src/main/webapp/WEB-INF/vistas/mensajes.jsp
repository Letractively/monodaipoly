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
                    <input id="EnviarMensaje" type="submit" value="Enviar" onclick="alert('Mensaje enviado correctamente')"/>
                    <input id="Limpiarmensaje" type="reset" value="Limpiar"/>
                </div>
                </div>
            </form>
        </div>
        
        <div id="bandejaEntrada" style="visibility:hidden">
            
        </div>
</div>



<script>
     function fijarBandejaEntrada(mensaje,i){
         if(mensaje!="No tienes mensajes"){
         var contenido =mensaje.contenido;
         var author = mensaje.author;
         if(i==0){
             $("#bandejaEntrada").html("<br/> Enviado por : <br/>"+author);
         }else{
             $("#bandejaEntrada").append("<br/> Enviado por : <br/>"+author);
         }
        $("#bandejaEntrada").append("<br/> Mensaje "+(i+1)+": <br/>"+contenido+"<br/> ");
         }else{
             $("#bandejaEntrada").append("<br/>No tienes mensajes");
         }
     }

     function redactarMensaje(){
            $("#bandejaEntrada").css("visibility","hidden");
            $("#enviarMensaje").css("visibility","visible");
            

        }
        function verBandejaEntrada(){
            
            $("#enviarMensaje").css("visibility","hidden");
            $("#bandejaEntrada").css("visibility","visible");
            
            $.get("/recibidos",null,function(db){
                
                if(db=="No tienes mensajes"){
                    fijarBandejaEntrada(db,"0");
                }else{
                var mensajes = $.parseJSON(db);
                $(mensajes).each(function(index,value) {                            
                            fijarBandejaEntrada(value,index);
                        });
                }
            }, "json");
           
        }
        $(document).ready(function(){
            var array=new Array();
            
            <c:forEach var="mensajes" items="${bandejaEntrada}" varStatus="status">
             array[${status.index}]={idMensaje:${mensajes.idMensaje}}
            </c:forEach>
            var jsonArray = array.toSource();
            

        })
    
</script>
