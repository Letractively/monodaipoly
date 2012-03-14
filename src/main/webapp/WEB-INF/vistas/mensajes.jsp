<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div id="menuMensajes">
        <br/>
    <div id="redactar"><p onclick="javascript:redactarMensaje()">REDACTAR</a></div>
        <br/>
     <div id="recibidos"><p>RECIBIDOS</p></div>
        <br/>
   
        
</div>
   
    
<div class="mensajes ">
        <div id="enviarMensaje" >
            <form action="/enviarMensajes" method="get" id="formMensajes">
                Destinatario:
                <div>
                    <input id="destinatario" type="text" name="destinatario" value="" />
                </div>    
                <br/>
                Contenido:
                <div>
                    <textarea id="contenidoMensaje" COLS=100 ROWS=10 type="text" name="contenidoMensaje" value="" ></textarea>
                </div>              
                <br/>
                <div id="mensaje" align="center">
                    <input id="EnviarMensajeboton" type="submit" value="Enviar" />
                    <input id="Limpiarmensajeboton" type="reset" value="Limpiar"/>
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
             $("#bandejaEntrada").html("<br/>No tienes mensajes");
         }
     }

     function redactarMensaje(){
            $("#bandejaEntrada").css("visibility","hidden");
            $("#enviarMensaje").css("visibility","visible");

            

        }
       /* function verBandejaEntrada(){
            
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
           
        }*/
       /* $(document).ready(function(){
           
           

        })*/
    
</script>
