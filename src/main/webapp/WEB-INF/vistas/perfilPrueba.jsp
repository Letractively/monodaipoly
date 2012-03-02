<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<head>
	<title>Perfil de ${usuario.nick}</title>
	
	<link rel="stylesheet" type="text/css" media="screen" href="/Estilos/layout1.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="/Estilos/perfil2.css" />
        <link rel="stylesheet" type="text/css" media="screen" href="/Estilos/dock-example1.css" />
	<noscript>
		<style type="text/css">
			#dock { top: -32px; }
			a.dock-item { position: relative; float: left; margin-right: 100px; }
			.dock-item span { display: block; }
		</style>
	</noscript>
	<script type="text/javascript" src="jQuery/js/jquery.min.js"></script>
	<script type="text/javascript" src="jQuery/js/fisheye-iutil.min.js"></script>
	<script type="text/javascript" src="jQuery/js/dock-example1.js"></script>
        <script type="text/javascript" src="jQuery/js/jquery-ui.custom.min.js"></script>
  
        
        <script type="text/javascript">
        function timeMsg(){
                var t=setTimeout("saberSiSiguesEnCola()",5000);
            }
            function saberSiSiguesEnCola(){
                //alert("reposicionado");
                 $.get("/saberSiYaPuedeJugar",{

                 },
                            function(json){
                                var db = $.parseJSON(json);
                                //alert(db.todoListo);
                                if(db.todoListo==true){
                                    //alert("dentro");
                                    $("#notificaciones").html("Ya esta tu partida lista");
                                    $("#imagenGif").css("visibility","hidden");
                                    $("#partidaLista").css("visibility","visible");
                                }if(db.todoListo==false){
                                    //alert(db.jugadoresRestantes);
                                    $("#notificaciones").html("${enCola}"+db.jugadoresRestantes);
                                    timeMsg();
                                }


                }, "json");
                
            }




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
            function unirsePartida(){
                 $.get("/getUnirsePartidaJSP",null,
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
                       
                            var c=0;
                            if(c==0){
                                $.get("/getDatosURL",null,
                                      function(html) {
                                            $("#contenido").html(html);
                                        }

                                 );
                                     c++;
                            }
                                
                              
                           
                        
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
                    
                 
                        $("#notificaciones").html("${enCola}");
                        $("#pie").append("${enColaImagen}");
                        //solo voy a llamar al setTimeOut si esta en cola
                        if("${enCola}"=="Esperando a otros jugadores..."){
                            timeMsg();
                        }

                    });
                    
      </script>
</head>
<body>
         <div id="cabezera"> Bienvenido,  ${usuario.nick} !</div>
         <div id="dock">
			<div class="dock-container">
				<a class="dock-item" onclick="javascript:datosPersonales()"><span>Datos&nbsp;Personales</span><img src="/Estilos/tux/tux6.png" /></a> 
				<a class="dock-item" onclick="javascript:mensajes()"><span>Mensajes&nbsp;</span><img src="/Estilos/dock/email.png"  /></a> 
				<a class="dock-item"  onclick="javascript:estadisticas()"><span>Ranking&nbsp;</span><img src="/Estilos/podio.png"/></a>
                                <a class="dock-item"  onclick="javascript:unirsePartida()"><span>Jugar&nbsp;</span><img src="/Estilos/dock/dados1.png"/></a>
				<a class="dock-item" href="/logout"><span>Desconectarse&nbsp;</span><img src="/Estilos/logout2.png" /></a> 
				
				
			</div>
        </div>
                
                
        <div id="contenido">

        </div>
         <div id="pie">
             <input type="button" id="partidaLista" value="¡ ENTRA A TU PARTIDA !" style="visibility:hidden" onclick="window.location.href='prepararPartida2'" />
           
             <div id="notificaciones" >
             </div>
         </div>
</body>
            