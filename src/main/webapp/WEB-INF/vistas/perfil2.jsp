<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>



<html>
    <head>

        <link type="text/css" rel="stylesheet" href="/Estilos/perfil2.css" />

        <!-- Librerías de JQuery -->
        <script type="text/javascript" src="jQuery/js/jquery.min.js"></script>
        <script type="text/javascript" src="jQuery/js/jquery-ui.custom.min.js"></script>
        <script type="text/javascript">
            function fijarValor(campo){
                if(campo.nombre!=""){
                    $("#nom").html(campo.nombre)
                }

                if(campo.apellido!=""){
                    $("#ape").html(campo.apellido)
                }
                if(campo.fecha!=""){
                    $("#fecha").html(campo.fecha)
                }
            }
            function datosPersonales(){
                 $.get("/getLoginURL",null,
                    function(html) {
                        $("#contenido").html(html);
                    }

               );
                $("#mod").css("visibility","visible");
                
                $("#mod").click(
                function(){

                    $(".modificar").css("visibility","visible");
                }
                )
                    
                    
                $("#modif").click(
                function(){

                    $.post($("#modi").attr("action"),{

                        nombre: $("#campoNombre").attr("value"),
                        apellido : $("#campoApellido").attr("value"),
                        fechaDia: $("#fechaDia").attr("value"),
                        fechaMes: $("#Mes").attr("value"),
                        fechaAño: $("#fechaAno").attr("value")
                    } , function(json){
                        var db = $.parseJSON(json);
                        fijarValor(db);
                    }, "json")
                    $(".modificar").css("visibility","hidden");
                    $("#campoNombre").attr("value", "")
                    $("#campoApellido").attr("value", "")
                    $("#fechaDia").attr("value", "")
                    $("#Mes").attr("value", "")
                    $("#fechaAno").attr("value", "")
                   
                 }
                
               )
            }
         

        



        </script>
        <title>Perfil</title>
</head>
    <body>
        <div id="fondo">
   
         <h1>Bienvenido,  ${usuario.nick} !</h1>
         <div id="menu">
             <div id="datospersonales" onclick="javascript:datosPersonales()">DATOS PERSONALES</div>
             <div id="estadisticas">ESTADISTICAS</div>
             <div id="mensajes">MENSAJES</div>
             <div id="salir">SALIR</div>
             
         </div> 
         <div id="contenido"></div>
         <div id="pie">
             <input id="mod" type="button"  style="visibility:hidden" value="Modificar"></input>
             <input class="modificar" style="visibility:hidden" id="modif" type="button" value="Guardar Cambios"></input>
         </div>
         
         

                  
        </div>


    </body>
</html>