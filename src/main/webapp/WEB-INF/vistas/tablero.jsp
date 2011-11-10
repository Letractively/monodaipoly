<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>

<html>
<head>
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



        <script>
             $(document).ready(
                function(){
                $("div.casilla").fancybox(
                {
                    'modal'                     : true,
                    'showCloseButton'           : false,
                    'enableEscapeButton'     	:       false,
                    'transitionIn'	:	'elastic',
                    'transitionOut'	:	'elastic',
                    'speedIn'		:	1200,
                    'speedOut'		:	1200,
                    'overlayShow'	:	false,
                    'autoDimensions'    :       false,
                    'width'    :       400,
                    'height'    :       150
                });

        </script>


</head>
<body>
    <table align="center" border="3">
        <tr>
            <td><div class="casilla" id="casilla19">
                    <img src='Estilos/Avenida Linux.jpg' />
                </div>
            </td>
            <td><div class="casilla" id="casilla20">
                    <img src='Estilos/Avenida Linux.jpg' />
                </div>
            </td><td><div class="casilla" id="casilla21">
                    <img src='Estilos/Avenida Linux.jpg' />
                </div>
            </td><td><div class="casilla" id="casilla22">
                    <img src='Estilos/Avenida Linux.jpg' />
                </div>
            </td><td><div class="casilla" id="casilla23">
                    <img src='Estilos/Avenida Linux.jpg' />
                </div>
            </td><td><div class="casilla" id="casilla24">
                    <img src='Estilos/Avenida Linux.jpg' />
                </div>
            </td><td><div class="casilla" id="casilla25">
                    <img src='Estilos/Avenida Linux.jpg' />
                </div>
            </td><td><div class="casilla" id="casilla26">
                    <img src='Estilos/Avenida Linux.jpg' />
                </div>
            </td><td><div class="casilla" id="casilla27">
                    <img src='Estilos/Avenida Linux.jpg' />
                </div>
            </td><td><div class="casilla" id="casilla28">
                    <img src='Estilos/Avenida Linux.jpg' />
                </div>
            </td>
        </tr>
        <tr>

            <td>

                    <div class="casilla" id="casilla18">
                    <img src='Estilos/lateral.jpg' />
                </div><div class="casilla" id="casilla17">
                    <img src='Estilos/lateral.jpg' />
                </div><div class="casilla" id="casilla16">
                    <img src='Estilos/lateral.jpg' />
                </div><div class="casilla" id="casilla15">
                    <img src='Estilos/lateral.jpg' />
                </div><div class="casilla" id="casilla14">
                    <img src='Estilos/lateral.jpg' />
                </div><div class="casilla" id="casilla13">
                    <img src='Estilos/lateral.jpg' />
                </div><div class="casilla" id="casilla12">
                    <img src='Estilos/lateral.jpg' />
                </div><div class="casilla" id="casilla11">
                   <img src='Estilos/lateral.jpg' />
                </div><div class="casilla" id="casilla10">
                    <img src='Estilos/lateral.jpg' />
                </div>

            </td>
            <td colspan="8"><div  id="casillaCentral">
                    <img src='Estilos/Logo.jpg' />
                </div></td>
            <td>

                    <div class="casilla" id="casilla29">
                    <img src='Estilos/lateral2.jpg' />
                </div><div class="casilla" id="casilla30">
                    <img src='Estilos/lateral2.jpg' />
                </div><div class="casilla" id="casilla31">
                    <img src='Estilos/lateral2.jpg' />
                </div><div class="casilla" id="casilla32">
                    <img src='Estilos/lateral2.jpg' />
                </div><div class="casilla" id="casilla33">
                    <img src='Estilos/lateral2.jpg' />
                </div><div class="casilla" id="casilla34">
                    <img src='Estilos/lateral2.jpg' />
                </div><div class="casilla" id="casilla35">
                    <img src='Estilos/lateral2.jpg' />
                </div><div class="casilla" id="casilla36">
                    <img src='Estilos/lateral2.jpg' />
                </div><div class="casilla" id="casilla37">
                    <img src='Estilos/lateral2.jpg' />
                </div>

            </td>
        </tr>
        <tr>
            <td><div class="casilla" id="casilla9">
                    <img src='Estilos/Avenida Linux.jpg' />
                </div>
            </td>
            <td><div class="casilla" id="casilla8">
                    <img src='Estilos/Avenida Linux.jpg' />
                </div>
            </td><td><div class="casilla" id="casilla7">
                    <img src='Estilos/Avenida Linux.jpg' />
                </div>
            </td><td><div class="casilla" id="casilla6">
                    <img src='Estilos/Avenida Linux.jpg' />
                </div>
            </td><td><div class="casilla" id="casilla5">
                    <img src='Estilos/Avenida Linux.jpg' />
                </div>
            </td><td><div class="casilla" id="casilla4">
                    <img src='Estilos/Avenida Linux.jpg' />
                </div>
            </td><td><div class="casilla" id="casilla3">
                    <img src='Estilos/Avenida Linux.jpg' />
                </div>
            </td><td><div class="casilla" id="casilla2">
                    <img src='Estilos/Avenida Linux.jpg' />
                </div>
            </td><td><div class="casilla" id="casilla1">
                    <img src='Estilos/Avenida Linux.jpg' />
                </div>
            </td><td><div class="casilla" id="casilla0">
                    <img src='Estilos/Avenida Linux.jpg' />
                </div>
            </td>
        </tr>
    </table>
   
</body>
</html>