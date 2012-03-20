
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
            
            function verPropiedadesOtrosUser(numJugador){
                $.get("/verPropiedadesOtrosUser",{
                    "numJugador":numJugador
                },
                function(json){ if(json=="No tiene casillas"){  
                        alert("No tiene casillas");   
                    }else{
                        var db = $.parseJSON(json);
                        $("#venderPropiedades").css("visibility", "visible");
                        $("#venderPropiedades").html("<div id='casillasOtrosJug'></div>");
                        $("#casillasOtrosJug").append("<div id='colm1'></div>");
                        $("#casillasOtrosJug").append("<div id='colm2'></div>");
                        $("#casillasOtrosJug").append("<div id='colm3'></div>");
                        var sw=0;
                        $(db).each(function(index,value) {
                            if (sw==0){
                                $("#colm1").append("<center><p>"+value.numero+"  "+value.nombre+"</p></center>");
                                sw=1;
                            }else{
                                $("#colm2").append("<center><p>"+value.numero+"  "+value.nombre+"</p></center>");
                                sw=0;
                            }
                            
                        });
                        $("#colm3").append("<center><button onClick='cerrarDiv()'>Cerrar</button></center>");
                    }
                },"json");
                
            }
            
            function cerrarDiv(){
                $("#venderPropiedades").css("visibility", "hidden");
                $("#casillasVender").css("visibility", "hidden");
            }

            function venderCasas(){
                
                var casas = "";
                $("input[name='cas']").each(
                function(i, e) {
                    if (e.checked) {
                        if (casas != "") {
                            casas += ",";
                        } 
                        casas+=e.id;   
                    }
                });
                //alert("casas"+casas);
                
                $.get("/venderCasillas",{
                    "calles":casas
                },
                function(json){
                    if(json=="Vendidas"){
                        //alert("Casillas vendidas");
                    }
                      

                },"json");
                alert("Casillas vendidas");
            }




            function getVenderPropiedadesURL(){
                $.get("/getVenderPropiedadesURL",null,
                function(html) {
                    $("#venderPropiedades").css("visibility", "visible");
                    $("#venderPropiedades").html(html);
                        
                }

            );

            }

            function timeMsg(){
                var t=setTimeout("reposicionadoDeDatos()",5000);
            }
            function reposicionadoDeDatos(){
                $.get("/datosDeJuego",{

                },
                function(json){
                    var db = $.parseJSON(json);
                    posicionJugador[1]=db.posicion1;
                    posicionJugador[2]=db.posicion2;
                    posicionJugador[3]=db.posicion3;
                    posicionJugador[4]=db.posicion4;
                    for (var i=1;i<=4;i++){
                                
                        var posicionReal=$("#casilla"+posicionJugador[i]).offset();
                        var izq=posicionReal.left;
                        var arriba=posicionReal.top;
                        $("#jugador"+i).css({
                            "left":izq,
                            "top":arriba
                        });
                    }
                                
                    $('#dineroJugador1').html(db.dinero1+"$");
                    $('#dineroJugador2').html(db.dinero2+"$");
                    $('#dineroJugador3').html(db.dinero3+"$");
                    $('#dineroJugador4').html(db.dinero4+"$");
                    $("#turno").html(db.turno);

                               
                    if(db.dinero1==0 && db.posicion1==0){
                        $("#X1").css("visibility", "visible");
                        $("#tux1").css("visibility", "hidden");
                        $("#jugador1").css("visibility", "hidden");
                        $("#jugador1info").css("visibility", "hidden");
                        $("#dineroJugador1").css("visibility", "hidden");
                    }
                    if(db.dinero2==0 && db.posicion2==0){
                        $("#X2").css("visibility", "visible");
                        $("#tux2").css("visibility", "hidden")
                        $("#jugador2").css("visibility", "hidden");
                        $("#jugador2info").css("visibility", "hidden");
                        $("#dineroJugador2").css("visibility", "hidden");
                    }
                    if(db.dinero3==0 && db.posicion3==0){
                        $("#X3").css("visibility", "visible");
                        $("#tux3").css("visibility", "hidden")
                        $("#jugador3").css("visibility", "hidden");
                        $("#jugador3info").css("visibility", "hidden");
                        $("#dineroJugador3").css("visibility", "hidden");
                    }
                    if(db.dinero4==0 && db.posicion4==0){
                        $("#X4").css("visibility", "visible");
                        $("#tux4").css("visibility", "hidden")
                        $("#jugador4").css("visibility", "hidden");
                        $("#jugador4info").css("visibility", "hidden");
                        $("#dineroJugador4").css("visibility", "hidden");
                    }

                    if(db.cantidadJugadores==1){
                        $("#finPartida").css("visibility", "visible");
                        $(".boton").css("visibility", "hidden");                        
                    }

                    $("#bote").html("BOTE: <br/>"+ db.bote+"$");


                                
                }, "json");
                timeMsg();
            }

            $(document).ready(
             
            function () {


                if("${jugadorOnline}"=="${nombre1}"){
                    
                    $("#imagenJugador1").css("border","white");
                    $("#imagenJugador1").css("border-style","double");

                }else if("${jugadorOnline}"=="${nombre2}"){

                    $("#imagenJugador2").css("border","white");
                    $("#imagenJugador2").css("border-style","double");
                }else if("${jugadorOnline}"=="${nombre3}"){

                    $("#imagenJugador3").css("border","white");
                    $("#imagenJugador3").css("border-style","double");
                }else{

                    $("#imagenJugador4").css("border","white");
                    $("#imagenJugador4").css("border-style","double");
                }



                var nombresCasillas=new Array();
                var divTablero = $("#tablero");
                var precioCalles=new Array();
                <c:forEach var="casilla" items="${casillas}"  varStatus="status">
                    divTablero.append("<div id=casilla${casilla.numeroCasilla}></div>");
                    nombresCasillas[${casilla.numeroCasilla}]={nombreCalle:"${casilla.nombre}"};
            </c:forEach>
                    var i=0;
                <c:forEach var="calle" items="${calles}" varStatus="status">
                    precioCalles[i]={precioCalle:"${calle.precio}"};
                    //alert(${calle.precio});
                    i++;
            </c:forEach>
           
            

                
                    $("#casilla9").html("<img src='Estilos/carcel.jpg' width='100%' height='100%'/>");
                    $("#casilla0").html("<img src='Estilos/CasillaSalida.jpg' width='100%' height='100%'/>");
                    $("#casilla18").html("<img src='Estilos/free.jpg' width='100%' height='100%'/>");
                    $("#casilla27").html("<img src='Estilos/Carcel2.png' width='100%' height='100%'/>");
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
                            $(identificacion).append("<div esPrecio='si'  indice='" + c + "' class='precio' ></div>");
                        }
                        if(c==3){
                            $(identificacion).append("<div esSuerte='si' esPequeno='si' indice='" + c + "'  class='suerte'></div>");
                        }
                        if(c==5){
                            $(identificacion).append("<div esPequeno='si' indice='" + c + "' class='estacion'><img src='/Estilos/nintendo_logo.jpg' width='100%' height='100%'/></div>");
                        }
                        if(c==6||c==7||c==8){
                            $(identificacion).append("<div esPequeno='si' indice='" + c + "' id='abajopeke' class='colorazul'></div>");
                            $(identificacion).append("<div esPrecio='si'  indice='" + c + "' class='precio' ></div>");
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
                            $(identificacion).append("<div esPequeno='si' indice='" + c + "' id='izquierdapeke' class='colorrosa'></div>");
                            $(identificacion).append("<div esPrecio='si'  indice='" + c + "' class='precioIzq' ></div>");
                        }
                        if(c==12){
                            $(identificacion).append("<div esPequeno='si' indice='" + c + "' class='hidroelectrica'></div>");
                        }

                        if(c==13){
                            $(identificacion).append("<div esPequeno='si' indice='" + c + "' class='estacion'><img src='/Estilos/sega.jpeg' width='100%' height='100%'/></div>");

                        }
                        if(c==14||c==15||c==17){
                            $(identificacion).append("<div esPequeno='si' indice='" + c + "' id='izquierdapeke' class='colornaranja'></div>");
                            $(identificacion).append("<div esPrecio='si'  indice='" + c + "' class='precioIzq' ></div>");
                        }
                        if(c==16){
                            $(identificacion).append("<div esSuerte='si' esPequeno='si' indice='" + c + "'  class='suerte'></div>");
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
                            $(identificacion).append("<div esPequeno='si' indice='" + c + "'  id='arribapeke' class='colorrojo'></div>");
                            $(identificacion).append("<div esPrecio='si'  indice='" + c + "' class='precioArr' ></div>");
                        }
                        if(c==21){
                            $(identificacion).append("<div esSuerte='si' esPequeno='si' indice='" + c + "' class='suerte'></div>");
                        }
                        if(c==23){
                            $(identificacion).append("<div esPequeno='si' indice='" + c + "' class='estacion'><img src='/Estilos/logoMS.jpg' width='100%' height='100%'/></div>");
                        }
                        if(c==24||c==25||c==26){
                            $(identificacion).append("<div esPequeno='si' indice='" + c + "' id='arribapeke' class='coloramarillo'></div>");
                            $(identificacion).append("<div esPrecio='si'  indice='" + c + "' class='precioArr' ></div>");
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
                            $(identificacion).append("<div esPequeno='si' indice='" + c + "' id='derechapeke' class='colorverde'></div>");
                            $(identificacion).append("<div esPrecio='si'  indice='" + c + "' class='precioDer' ></div>");
                        }
                        if(c==30){
                            $(identificacion).append("<div esPequeno='si' indice='" + c + "' class='hidroelectrica'></div>");
                        }

                        if(c==32){
                            $(identificacion).append("<div esPequeno='si' indice='" + c + "' class='estacion'><img src='/Estilos/sony.jpg' width='100%' height='100%'/></div>");

                        }
                        if(c==33||c==35){
                            $(identificacion).append("<div esPequeno='si' indice='" + c + "' id='derechapeke' class='colorazuloscuro'></div>");
                            $(identificacion).append("<div esPrecio='si'  indice='" + c + "' class='precioDer' ></div>");
                        }
                        if(c==34){
                            $(identificacion).append("<div  esSuerte='si' esPequeno='si' indice='" + c + "' class='suerte'></div>");
                        }
                            


                    }
       
                    $("[esPequeno='si']").each(
                    function() {
                        var indice = $(this).attr("indice");
                        //alert(nombresCasillas[indice].nombreCalle);
                        if(indice>=1 && indice<=8){
                    
                            $(this).append("<div id='nombresAbajo'>"+nombresCasillas[indice].nombreCalle+"</div>");
                        }
                        if(indice>=10 && indice<=17){
                    
                            $(this).append("<div id='nombresIzquierda'>"+nombresCasillas[indice].nombreCalle+"</div>");
                        }
                        if(indice>=19 && indice<=26){
                    
                            $(this).append("<div id='nombresArriba'>"+nombresCasillas[indice].nombreCalle+"</div>");
                        }
                        if(indice>=28 && indice<=35){
                    
                            $(this).append("<div id='nombresDerecha'>"+nombresCasillas[indice].nombreCalle+"</div>");
                        }
                
               
                    }
                );
                    $("[esPrecio='si']").each(

                    function() {

                        var indice = $(this).attr("indice");
                        
                        $(this).html(precioCalles[indice].precioCalle+"$");


                    }
                );
                           $("[esSuerte='si']").each(

                    function() {

                        
                            
                            $(this).append("<img src='/Estilos/suerte.png' width='100%' height='100%'/>");


                    }
                );
        
         


                    var jug1=1;
                    var jug2=2;
                    var jug3=3;
                    var jug4=4;

                    if(${dinero1}!=-1){
                        $("#jugador1info").css("visibility","visible");
                        $("#dineroJugador1").css("visibility","visible");

                        jug1=-1;
                    }if(${dinero2}!=-1){
                        $("#jugador2info").css("visibility","visible");
                        $("#dineroJugador2").css("visibility","visible");
                        jug2=-1;
                    }if(${dinero3}!=-1){
                        $("#jugador3info").css("visibility","visible");
                        $("#dineroJugador3").css("visibility","visible");
                        jug3=-1;
                    }if(${dinero4}!=-1){
                        $("#jugador4info").css("visibility","visible");
                        $("#dineroJugador4").css("visibility","visible");
                        jug4=-1;
                    }

                    for (var i=1;i<=4;i++){
                        if((jug1!=i) || (jug2!=i) || (jug3!=i) || (jug4!=i) || posicionJugador[i]!=-1){
                            var posicionReal=$("#casilla"+posicionJugador[i]).offset();
                            var izq=posicionReal.left;
                            var arriba=posicionReal.top;                           
                            $("#jugador"+i).css({
                                "left":izq,
                                "top":arriba
                            });
                        }
                    }
                    $("#turno").html("${turno}");
                    //llamar a el setTimeOut
                    reposicionadoDeDatos();
                    timeMsg();


                });
                function tirarDado2(jugQueTira){
                    //ahora jugQueTira es la key del jugador que va a tirar...esto lo hago para poder reutilizar el codigo...
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

                            if(db.nuevaPosicion==27){
                                alert("Tienes que tirar otra vez para saber si el FBI te manda a la carcel");
                                $.get("/tirarDado",{
                                    jugQueTira:jugQueTira
                                },function(json){
                                    var db = $.parseJSON(json);
                                    if(db.dado==6){
                                        alert("Has sacado: "+db.dado+" te libras de la carcel");
                                    }else{
                                        alert("Has sacado: "+db.dado);
                                        alert("Tenias que haber sido mas precavido...el FBI te envia a la carcel");
                                        posicionJugador[db.numJugador]=db.nuevaPosicion;
                                        posicionReal = $("#casilla"+posicionJugador[db.numJugador]).offset();
                                        //alert("posicionreal:  "+posicionReal);
                                        var izq=posicionReal.left;
                                        var arriba=posicionReal.top;
                                        $("#jugador"+db.numJugador).css({
                                            "left":izq,
                                            "top":arriba
                                        });
                                    }

                                },"json");

                            }else if(db.nuevaPosicion==18){
                                alert("¡¡Te has llevado el dinero del bote!!");

                                $.get("/cambiarTurnoManual", {
                                    jugQueTira:jugQueTira

                                    }, function(informacion){
                                    }, "json");
                            }else{
                                //si a podido tirar le vamos a decir lo de la calle

                                $.get("/comprobarCalle",{
                                    jugQueTira:jugQueTira
                                },
                                function(json){

                                
                                    //Cuanod no se a temrinado la partida



                                    if(json==null || json=="tuya" ){
                                        if(json=="tuya"){
                                            //la casilla en la q has caido es tuya
                                            alert("La casilla es tuya");
                                        }else{
                                            alert("Has caido en una casilla que no se puede comprar");
                                        }

                                    }else{
                                        var db = $.parseJSON(json);
                                        if(db.tipo=="suerte"){
                                            alert(db.contenido);
                                            if(db.arruinado==true){
                                                alert("Que mala suerte...te has arruinado");
                                                $("#botonAbandonar").submit();
                                            }
                                        }
                                        else if(db.tipo=="multa"){
                                            //cuando has pagado multa
                                            alert("Has pagado una multa de: "+db.multa+" a el jugador numero "+db.numJugador);
                                            if(db.eliminado){
                                                //esta eliminado
                                                alert("ARRUINADOOO!");

                                                //volver al perfil y eliminar jugador
                                                $("#botonAbandonar").submit();
                                            }
                                        
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

                                            $.get("/cambiarTurnoManual", {
                                                jugQueTira:jugQueTira

                                            }, function(informacion){
                                                //alert("cambiarTurno =D");

                                            }, "json");
                                        
                                        }
                                    }

                                    //si no se a terinado la partida                      

                                },"json");

                            


                            }
                        }
                    }, "json");
    
                }
                        
        </script>

        <title>Partida-MonoDaiPoly</title>

    </head>
    <body>
        <div  id="venderPropiedades" style="z-index: 9"></div>

        <div id="tablero">
            <div id="arriba"></div>

            <div id="lateralizq"></div>

            <div id="abajo"></div>

            <div id="lateralderecha"></div>

            <div id="centroFondo">
                <img src='Estilos/Logo.jpg' width='100%' height='100%'/>

            </div>
            <div  id="centro">



                <span class="jugadores" id="jugador1info" style="visibility:hidden"><center>${nombre1}</center></span>
                <span class="jugadores" id="jugador2info" style="visibility:hidden"><center>${nombre2}</center></span>
                <span class="jugadores" id="jugador3info" style="visibility:hidden"><center>${nombre3}</center></span>
                <span class="jugadores" id="jugador4info" style="visibility:hidden"><center>${nombre4}</center></span>


                <span class="imagenesJugador" id="imagenJugador1"><img id="tux1" src='${tux1}' width='100%' height='100%' onclick="verPropiedadesOtrosUser('1')"/><img class="X"id="X1"src='/Estilos/Equis_roja.png' width='100%' height='100%' style="visibility: hidden"/></span>
                <span class="imagenesJugador" id="imagenJugador2"><img id="tux2" src='${tux2}' width='100%' height='100%' onclick="verPropiedadesOtrosUser('2')"/><img class="X"id="X2"src='/Estilos/Equis_roja.png' width='100%' height='100%' style="visibility: hidden"/></span>
                <span class="imagenesJugador" id="imagenJugador3"><img id="tux3" src='${tux3}' width='100%' height='100%' onclick="verPropiedadesOtrosUser('3')"/><img class="X"id="X3"src='/Estilos/Equis_roja.png' width='100%' height='100%' style="visibility: hidden"/></span>
                <span class="imagenesJugador" id="imagenJugador4"><img id="tux4" src='${tux4}' width='100%' height='100%' onclick="verPropiedadesOtrosUser('4')"/><img class="X"id="X4"src='/Estilos/Equis_roja.png' width='100%' height='100%' style="visibility: hidden;z-index: 9"/></span>


                <span class="dineroJugador" id="dineroJugador1" style="visibility:hidden"><center>${dinero1}$</center></span>
                <span class="dineroJugador" id="dineroJugador2" style="visibility:hidden"><center>${dinero2}$</center></span>
                <span class="dineroJugador" id="dineroJugador3" style="visibility:hidden"><center>${dinero3}$</center></span>
                <span class="dineroJugador" id="dineroJugador4" style="visibility:hidden"><center>${dinero4}$</center></span>


                <button class="boton" id="botonTirar"onClick="tirarDado2('${jugador.nick}')" onmouseover="this.style.color='#82cb2f'" onmouseout="this.style.color='white'">Tirar Dado</button>
                <!--provisional-->
                <button class="boton" id="botonVender" onClick="getVenderPropiedadesURL()" onmouseover="this.style.color='#82cb2f'" onmouseout="this.style.color='white'">Vender Propiedades</button>



                <form   action="/perfil" method="post">
                    <input class="boton"  id="botonSalida" type="submit" value="Volver Perfil" onmouseover="this.style.color='#82cb2f'" onmouseout="this.style.color='white'"/>
                </form>


                <form   action="/terminarJugadorPartida" id="botonAbandonar" method="get">
                    <input class="boton"   id="botonAbandonar1" type="submit" value="Abandonar" onmouseover="this.style.color='#82cb2f'" onmouseout="this.style.color='white'"/>
                </form>

                <form   action="/jugadorGanadorPartida" id="botonTerminar" method="get">
                    <input class="boton"   id="botonTerminar" type="submit" style="visibility: hidden"/>
                </form>
            </div>
        </div>

        <div id="centroTurno">
            <b>
                TURNO : <span id="turno"></span>
            </b>
            <input type="button" id="finPartida" value="¡ FINAL DE LA PARTIDA !" style="visibility: hidden" onclick="window.location.href='jugadorGanadorPartida'">


        </div>

        <div id="dineroBote">
            <img id="boteImg" src='/Estilos/monton.png' width='100%' height='100%'/>
            <center>
                <span id="bote">BOTE:<br/> 0$</span>
            </center>
        </div>       

        <div id="jugador1"><img src='${tux1}' width='100%' height='100%'/></div>
        <div id='jugador2'><img src='${tux2}' width='100%' height='100%'/></div>
        <div id='jugador3'><img src='${tux3}' width='100%' height='100%'/></div>
        <div id='jugador4'><img src='${tux4}' width='100%' height='100%'/></div>



    </body>
</html>

