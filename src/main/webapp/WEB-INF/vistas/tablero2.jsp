
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

        
        
        <script language="javascript" type="text/javascript"></script>
        <script type="text/javascript">
           
            var posicionJugador=new Array();
            posicionJugador[1]=${jugador1};
            posicionJugador[2]=${jugador2};
            posicionJugador[3]=${jugador3};
            posicionJugador[4]=${jugador4};
    

            $(document).ready(
             
            function () {

             var nombresCasillas=new Array();
             var divTablero = $("#tablero");
            <c:forEach var="casilla" items="${casillas}"  varStatus="status">
                            divTablero.append("<div id=casilla${casilla.numeroCasilla}></div>");
                            nombresCasillas[${casilla.numeroCasilla}]={nombreCalle:"${casilla.nombre}"};
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
                                    $(identificacion).append("<div esPequeno='si' indice='" + c + "' id='abajopeke' class='colorgris'></div>");
                                }
                                if(c==3){
                                    $(identificacion).append("<div esPequeno='si' indice='" + c + "' class='suerte'> Suerte</div>");
                                }
                                if(c==5){
                                    $(identificacion).append("<div esPequeno='si' indice='" + c + "' class='estacion'>Estacion</div>");
                                }
                                if(c==6||c==7||c==8){
                                    $(identificacion).append("<div esPequeno='si' indice='" + c + "' id='abajopeke' class='colorazul'>Calle Azul</div>");
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
                                    $(identificacion).append("<div esPequeno='si' indice='" + c + "' id='izquierdapeke' class='colorrosa'> Calle rosa</div>");
                                }
                                if(c==12){
                                    $(identificacion).append("<div esPequeno='si' indice='" + c + "' class='hidroelectrica'>Hidroelectrica</div>");
                                }

                                if(c==13){
                                    $(identificacion).append("<div esPequeno='si' indice='" + c + "' class='estacion'>Estacion</div>");

                                }
                                if(c==14||c==15||c==17){
                                    $(identificacion).append("<div esPequeno='si' indice='" + c + "' id='izquierdapeke' class='colornaranja'>Calle Naranja</div>");
                                }
                                if(c==16){
                                    $(identificacion).append("<div esPequeno='si' indice='" + c + "'  class='suerte'>Suerte</div>");
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
                                    $(identificacion).append("<div esPequeno='si' indice='" + c + "'  id='arribapeke' class='colorrojo'>${casillas[c].nombre}</div>");
                                }
                                if(c==21){
                                    $(identificacion).append("<div esPequeno='si' indice='" + c + "' class='suerte'> Suerte</div>");
                                }
                                if(c==23){
                                    $(identificacion).append("<div esPequeno='si' indice='" + c + "' class='estacion'>Estacion</div>");
                                }
                                if(c==24||c==25||c==26){
                                    $(identificacion).append("<div esPequeno='si' indice='" + c + "' id='arribapeke' class='coloramarillo'>Calle Amarilla</div>");
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
                                    $(identificacion).append("<div esPequeno='si' indice='" + c + "' id='derechapeke' class='colorverde'> Calle Verde</div>");
                                }
                                if(c==30){
                                    $(identificacion).append("<div esPequeno='si' indice='" + c + "' class='hidroelectrica'>Hidroelectrica</div>");
                                }

                                if(c==32){
                                    $(identificacion).append("<div esPequeno='si' indice='" + c + "' class='estacion'>Estacion</div>");

                                }
                                if(c==33||c==35){
                                    $(identificacion).append("<div esPequeno='si' indice='" + c + "' id='derechapeke' class='colorazuloscuro'>Calle Azul oscuro</div>");
                                }
                                if(c==34){
                                    $(identificacion).append("<div  esPequeno='si' indice='" + c + "' class='suerte'>¡¡PAGA!!</div>");
                                }
                            


                            }
       
        $("[esPequeno='si']").each(
            function() {
                var indice = $(this).attr("indice");
                //alert(nombresCasillas[indice].nombreCalle);
                $(this).html(nombresCasillas[indice].nombreCalle);

            }
        );






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
                        function tirarDado2(jugQueTira){
                            /*var dado=Math.ceil(Math.random()*6);
                            alert(dado);
                            posAnt=posicionJugador[1];
                            posicionJugador[1]=posicionJugador[1]+dado;
                            if(posicionJugador[1]>35){
                                var dif=0;
                                var dif=36-posAnt;
                                posicionJugador[1]=0+dado-dif;
                            }
                            */
                            $.get("/moverJugador",{
                                jugQueTira:jugQueTira
                            },
                            function(json){
                                if(json=="no"){
                                    alert("No es tu turno, No puedes tirar");
                                }else{
                                var db = $.parseJSON(json);
                                alert(db.dado);
                                //alert(db.nuevaPosicion);
                                //alert(db.numJugador);
                                posicionJugador[db.numJugador]=db.nuevaPosicion;
                                posicionReal = $("#casilla"+posicionJugador[db.numJugador]).offset();
                                //alert("posicionreal:  "+posicionReal);
                                var izq=posicionReal.left;
                                var arriba=posicionReal.top;
                                $("#jugador"+db.numJugador).css({
                                "left":izq,
                                "top":arriba
                                });



                                 //si a podido tirar le vamos a decir lo de la calle

                                $.get("/comprobarCalle",{
                                jugQueTira:jugQueTira
                            },
                            function(json){
                                if(json==null || json=="tuya"){
                                    if(json=="tuya"){
                                        //la casilla en la q has caido es tuya
                                        alert("La casilla es tuya");
                                    }else{
                                        alert("Has caido en una casilla que no se puede comprar");
                                    }

                                }else{
                                    var db = $.parseJSON(json);
                                    if(db.tipo=="multa"){
                                        //cuando has pagado multa
                                        alert("Has pagado una multa de: "+db.multa+" a el jugador numero "+db.numJugador);
                                    }else{
                                        //te tendria q aparecer la opcion de compra
                                        comprarCalle=confirm("Quieres comprar la calle " + db.nombre + " que cuesta: "+db.precio);
                                        if(comprarCalle){

                                            $.get("/comprarCalle", {
                                                jugQueTira:jugQueTira
                                            }, function(json){
                                                if(json=="calle comprada"){
                                                    alert("Has comprado la calle: "+db.nombre);
                                                }else{
                                                    alert("No tienes dinero suficiente para comprar la calle...");
                                                }

                                            }, "json")

                                        }
                                        //terminamos turno
                                        
                                    }
                                }
                             

                            },"json");
                                }
                            }, "json");
    
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


                    
                        <span class="jugadores" id="jugador1info"><center>${nombre1}</center></span>
                        <span class="jugadores" id="jugador2info"><center>${nombre2}</center></span>
                        <span class="jugadores" id="jugador3info"><center>${nombre3}</center></span>
                        <span class="jugadores" id="jugador4info"><center>${nombre4}</center></span>
                    
                    
                        <span class="imagenesJugador" id="imagenJugador1"><img src='Estilos/tux/batman.png' width='100%' height='100%'/></span>
                        <span class="imagenesJugador" id="imagenJugador2"><img src='Estilos/tux/patricio.png' width='100%' height='100%'/></span>
                        <span class="imagenesJugador" id="imagenJugador3"><img src='Estilos/tux/croft.png' width='100%' height='100%'/></span>
                        <span class="imagenesJugador" id="imagenJugador4"><img src='Estilos/tux/naruto.png' width='100%' height='100%'/></span>
                    
                    
                        <span class="dineroJugador" id="dineroJugador1"><center>${dinero1}$</center></span>
                        <span class="dineroJugador" id="dineroJugador2"><center>${dinero2}$</center></span>
                        <span class="dineroJugador" id="dineroJugador3"><center>${dinero3}$</center></span>
                        <span class="dineroJugador" id="dineroJugador4"><center>${dinero4}$</center></span>


                        <button class="boton" id="botonTirar"onClick="tirarDado2('${jugador.nick}')" onmouseover="this.style.color='red'" onmouseout="this.style.color='black'">Tirar Dado</button>
                        <!--provisional-->
                        <button class="boton" id="botonVender" onmouseover="this.style.color='red'" onmouseout="this.style.color='black'">Vender Propiedades</button>



                        <form action="/perfil" method="post">
                    <input class="boton" id="botonSalida" type="submit" value="Volver Perfil" onmouseover="this.style.color='red'" onmouseout="this.style.color='black'"/>
                </form>

                        <!--provisional-->
                        <button class="boton" id="botonAbandonar" onmouseover="this.style.color='red'" onmouseout="this.style.color='black'">Abandonar</button>
                
           </div>
        </div>


	


                

                
            



            <div id="jugador1"><img src='Estilos/tux/batman.png' width='100%' height='100%'/></div>
            <div id='jugador2'><img src='Estilos/tux/patricio.png' width='100%' height='100%'/></div>
            <div id='jugador3'><img src='Estilos/tux/croft.png' width='100%' height='100%'/></div>
            <div id='jugador4'><img src='Estilos/tux/naruto.png' width='100%' height='100%'/></div>



    </body>
</html>
