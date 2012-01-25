
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>

<html>
    <head>
        <link type="text/css" rel="stylesheet" href="/Estilos/tablero.css" />
        <!-- Librerías de JQuery -->
        <script type="text/javascript" src="jQuery/js/jquery.min.js"></script>
        <script type="text/javascript" src="jQuery/js/jquery-ui.custom.min.js"></script>


        <!-- Librerías del plugin de rating para JQuery-->
        <script type="text/javascript" src="jQuery/js/jquery.ui.stars.js"></script>
        <link rel="stylesheet" type="text/css" href="jQuery/css/crystal-stars.css" />

        <!--Librerías de FancyBox-->
        <script type="text/javascript" src="fancybox/jquery.fancybox-1.3.4.pack.js"></script>
        <script type="text/javascript" src="fancybox/jquery.easing-1.3.pack.js"></script>
        <script type="text/javascript" src="fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
        <link rel="stylesheet" href="fancybox/jquery.fancybox-1.3.4.css" type="text/css" media="screen" />
        <script language="javascript" type="text/javascript"></script>
        <script type="text/javascript">
           
            var posicionJugador=new Array();
            posicionJugador[1]=${jugador1};
            posicionJugador[2]=0;
            var posAnt=0;

            $(document).ready(
             
            function () {

                 //
                 //alert($("#partida").attr("value"));
                 //
                var divTablero = $("#tablero");
            <c:forEach var="casilla" items="${casillas}">
                            divTablero.append("<div id=casilla${casilla.numeroCasilla}></div>")
            </c:forEach>
                            $("#casilla9").html("<img src='Estilos/carcel.jpg' width='100%' height='100%'/>");
                            $("#casilla0").html("<img src='Estilos/CasillaSalida.jpg' width='100%' height='100%'/>");
                            $("#casilla18").html("<img src='Estilos/free.jpg' width='100%' height='100%'/>");
                            $("#casilla27").html("<img src='Estilos/carcel.jpg' width='100%' height='100%'/>");
                            var c;//contador de las casillas
                            var resta=12.5;//tamaño de cada casilla
                            var left=87.5;
                            var right=0;
                            var parametroizq=left;
                            var parametrodech=right;

                            for(c=1;c<=8;c++){
                        
                        
                                var identificacion="#casilla"+c;
                                $("#abajo").append($(identificacion));
                                $(identificacion).addClass("casillasabajo").css({
                                    "left":parametroizq+"%",
                                    "right":parametrodech+"%"
                                });
                                parametroizq=(parametroizq-resta);
                                parametrodech=(parametrodech+resta);
                                if(c==1||c==2||c==4){
                                    $(identificacion).append("<div id='abajopeke' class='colorgris'> Calle Gris</div>");
                                }
                                if(c==3){
                                    $(identificacion).append("<div class='suerte'> Suerte</div>");
                                }
                                if(c==5){
                                    $(identificacion).append("<div class='estacion'>Estacion</div>");
                                }
                                if(c==6||c==7||c==8){
                                    $(identificacion).append("<div id='abajopeke' class='colorazul'>Calle Azul</div>");
                                }

                            }


                            var top=0;

                            for(c=17;c>=10;c--)
                            {
                                var identificacion="#casilla"+c;
                                $("#lateralizq").append($(identificacion));
                                $(identificacion).addClass("casillasizq").css("top",top+"%");
                                top=top+resta;
                                if(c==10||c==11){
                                    $(identificacion).append("<div id='izquierdapeke' class='colorrosa'> Calle rosa</div>");
                                }
                                if(c==12){
                                    $(identificacion).append("<div class='hidroelectrica'>Hidroelectrica</div>");
                                }

                                if(c==13){
                                    $(identificacion).append("<div class='estacion'>Estacion</div>");

                                }
                                if(c==14||c==15||c==17){
                                    $(identificacion).append("<div id='izquierdapeke' class='colornaranja'>Calle Naranja</div>");
                                }
                                if(c==16){
                                    $(identificacion).append("<div  class='suerte'>Suerte</div>");
                                }


                            }
                            var parametroizq=left;
                            var parametrodech=right;
                            for(c=26;c>=19;c--)
                            {
                                var identificacion="#casilla"+c;
                                $("#arriba").append($(identificacion));
                                $(identificacion).addClass("casillasarriba").css({
                                    "left":parametroizq+"%",
                                    "right":parametrodech+"%"
                                });
                                parametroizq=(parametroizq-resta);
                                parametrodech=(parametrodech+resta);


                                if(c==19||c==20||c==22){
                                    $(identificacion).append("<div id='arribapeke' class='colorrojo'> Calle Roja</div>");
                                }
                                if(c==21){
                                    $(identificacion).append("<div class='suerte'> Suerte</div>");
                                }
                                if(c==23){
                                    $(identificacion).append("<div class='estacion'>Estacion</div>");
                                }
                                if(c==24||c==25||c==26){
                                    $(identificacion).append("<div id='arribapeke' class='coloramarillo'>Calle Amarilla</div>");
                                }
                            }
                            var top=0;

                            for(c=28;c<=35;c++)
                            {
                                var identificacion="#casilla"+c;
                                $("#lateralderecha").append($(identificacion));
                                $(identificacion).addClass("casillasdech").css("top",top+"%");
                                top=top+resta;
                                //Estructura1(c,"derechapeke","28","colorverde","colorazuloscuro",identificacion);
                              
                                if(c==28||c==29||c==31){
                                    $(identificacion).append("<div id='derechapeke' class='colorverde'> Calle Verde</div>");
                                }
                                if(c==30){
                                    $(identificacion).append("<div class='hidroelectrica'>Hidroelectrica</div>");
                                }

                                if(c==32){
                                    $(identificacion).append("<div class='estacion'>Estacion</div>");

                                }
                                if(c==33||c==35){
                                    $(identificacion).append("<div id='derechapeke' class='colorazuloscuro'>Calle Azul oscuro</div>");
                                }
                                if(c==34){
                                    $(identificacion).append("<div  class='suerte'>¡¡PAGA!!</div>");
                                }
                            


                            }







                            for (var i=1;i<=4;i++){
                                var posicionReal=$("#casilla"+posicionJugador[i]).offset();
                                var izq=posicionReal.left;
                                var arriba=posicionReal.top;
                                //alert('top:'+arriba);
                                //alert('left:'+izq);
                                $("#jugador"+i).css({
                                    "left":izq,
                                    "top":arriba
                                });
                            }




                        });
                        function tirarDado2(jugador){
                            var dado=Math.ceil(Math.random()*6);
                            alert(dado);
                            posAnt=posicionJugador[1];
                            posicionJugador[1]=posicionJugador[1]+dado;
                            if(posicionJugador[1]>35){
                                var dif=0;
                                var dif=36-posAnt;
                                posicionJugador[1]=0+dado-dif;
                            }
                            posicionReal = $("#casilla"+posicionJugador[1]).offset();
                            var izq=posicionReal.left;
                            var arriba=posicionReal.top;
                            $("#"+jugador).css({
                                "left":izq,
                                "top":arriba
                            });
                            $.get("/moverJugador",{
                                dado:dado
                            },
                            function(json){
                                var db = $.parseJSON(json);
                                //alert(db);
                            }, "json")
    
                        }
                        
        </script>

        <title>Partida-MonoDaiPoly</title>

    </head>
    <body>


        <div id="tablero">
            <div id="arriba"></div>

            <div id="lateralizq"></div>

            <div id="abajo"></div>

            <div id="lateralderecha"></div>

            <div id="centroFondo">
                <img src='Estilos/Logo.jpg' width='100%' height='100%'/>

            </div>
            <div  id="centro">
                    <div id="partida" value="${partida}"></div>

                <form action="/perfil" method="post">
                    <input id="botonEntrar" type="submit" value="Terminar Partida" onmouseover="this.style.color='red'" onmouseout="this.style.color='black'"/>
                </form>

                


                <button onClick="tirarDado2('jugador1')" onmouseover="this.style.color='red'" onmouseout="this.style.color='black'">Tirar Dado</button>
            </div>
            <div id="jugador1"><img src='Estilos/tux/batman.png' width='100%' height='100%'/></div>
            <div id='jugador2'><img src='Estilos/tux/patricio.png' width='100%' height='100%'/></div>



        </div>


    </body>
</html>
