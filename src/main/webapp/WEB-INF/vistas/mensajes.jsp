<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<%@page contentType="text/html" pageEncoding="windows-1252"%>

<div id="menuMensajes">
    
    <div id="redactar"><a onclick="javascript:redactarMensaje()">REDACTAR</a></div>
        <br/>
     <div id="recibidos"><a onclick="javascript:verBandejaEntrada()">RECIBIDOS</a></div>
        <br/>
     <div id=enviados">ENVIADOS</div>
        
</div>






    
    
    
    
    
    
    
    
<div class="mensajes "id="enviarMensaje">
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


<script>
      function redactarMensaje(){
             $(".mensajes").css("visibility","hidden");
            $("#enviarMensaje").css("visibility","visible");
        }
       /* function verBandejaEntrada(){
            $(".mensajes").css("visibility","hidden");
            $("#bandejaEntrada").css("visibility","visible");
            var array=new Array();
            array=${usuario.bandejaEntrada};
            if(array.size==0){
                $("#bandejaEntrada").html("No tienes mensajes");
                
            }else{
                
                   $("#bandejaEntrada").html("Tienes mensajes");
            }
        }*/
    $(document).ready(
    function(){
        
        
        $(".mensajes").css("visibility","hidden");
        
      
      
 
        
    });
    
</script>
