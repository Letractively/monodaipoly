
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
        <link type="text/css" rel="stylesheet" href="/Estilos/main.css" />
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
            $(document).ready(
                function () {
                    var divCasillas = $("#casillas");
                    <c:forEach var="casilla" items="${casillas}">
                            divCasillas.append("<div id=casilla${casilla.numeroCasilla}>${casilla.numeroCasilla}</div>")
                    </c:forEach>

                    var c;
                    var resta=8.175;
                    var left=100;
                    var right=0;
                    var parametroizq=left;
                    var parametrodech=right;
                    
                    for(c=1;c<=8;c++){
                        
                        
                         var identificacion="#casilla"+c;
                         $("#abajo").append($(identificacion));
                         $(identificacion).addClass("casillasabajo").css({
                                "left":parametroizq,
                                "right":parametrodech
                         });
                         parametroizq=(left-resta)+"%";
                         parametrodech=(right+resta)+"%";
                     }
                   
                        
                }

            );

       
        </script>




    </head>
    <body>
      
        <div id="casillas">
            <div id="abajo">abajoooooooo!!</div>
            <div id="arriba"/>
            <div id="lateralizq"/>
            <div id="lateralderch"/>
        </div>
                
 
    </body>
</html>