<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<head>
	<title>Perfil de ${usuario.nick}</title>
	
	<link rel="stylesheet" type="text/css" media="screen" href="/Estilos/cssPerfil.css"/>
     

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
         
            function EnviarMensajes(){
                  $.get("/enviarMensajesURL",null,
                    function(html) {
                        $("#contenido").html(html);
                    }

               );
            }
             function verBandejaEntrada(){
                  $.get("/verMensajesRecibidosURL",null,
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
                        
                        $("ul.subnav").parent().append("<span></span>"); //Only shows drop down trigger when js is enabled (Adds empty span tag after ul.subnav*)  
  
    $("ul.topnav li span").click(function() { //When trigger is clicked...  
  
        //Following events are applied to the subnav itself (moving subnav up and down)  
        $(this).parent().find("ul.subnav").slideDown('fast').show(); //Drop down the subnav on click  
  
        $(this).parent().hover(function() {  
        }, function(){  
            $(this).parent().find("ul.subnav").slideUp('slow'); //When the mouse hovers out of the subnav, move it back up  
        });  
  
        //Following events are applied to the trigger (Hover events for the trigger)  
        }).hover(function() {  
            $(this).addClass("subhover"); //On hover over, add class "subhover"  
        }, function(){  //On Hover Out  
            $(this).removeClass("subhover"); //On hover out, remove class "subhover"  
    });  
                      
                        
                        
                       
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
                        if("${enPartida}"=="Estas en una partida"){
                            $("#notificaciones").html("${enPartida}");
                            $("#partidaLista").css("visibility","visible")
                        }
                        
                        $("#pie").append("${enColaImagen}");
                        //solo voy a llamar al setTimeOut si esta en cola
                        if("${enCola}"=="Esperando a otros jugadores..."){
                            timeMsg();
                        }

                    });
                    
      </script>
</head>
<body>
    
    <div id="header"><img src="/Estilos/header2.png" width="100%" height="100%"></div>
    
        <ul class="topnav">  
        <li><a onclick="javascript:datosPersonales()">Inicio</a></li>  
        <li>  
            <a onclick="javascript:EnviarMensajes()">Mensajes</a> 
            <ul class="subnav">  
            <li><a onclick="javascript:EnviarMensajes()">Enviar</a></li>
            <li><a onclick="javascript:verBandejaEntrada()">Recibidos</a></li>
        </ul>  
            
        </li>  
        <li>  
            <a onclick="javascript:estadisticas()">Ranking</a>  
            
        </li>  
        <li><a onclick="javascript:unirsePartida()">Empezar a Jugar</a></li>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <li><a href="http://monodaipoly2.appspot.com/spring/index">Administracion</a></li>  
        </sec:authorize>
        
        <li><a href="/logout">Desconectarse</a></li>  
        
        <div id="UsuarioConectado"> Bienvenido,  ${usuario.nick} !</div>
        
        </ul>
    
    
    <div id="contenido"></div>
    <div id="pie">
             <input type="button" id="partidaLista" value="¡ ENTRA A TU PARTIDA !" style="visibility:hidden" onclick="window.location.href='prepararPartida2'" />
           
             <div id="notificaciones" >
             </div>
    </div>
    
    
    
</body>