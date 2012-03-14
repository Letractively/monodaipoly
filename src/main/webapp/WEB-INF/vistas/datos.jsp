<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


  
    
<div id="datospersonales">
      <legend>Datos Personales</legend>
      <br/>
    <br/>
    <form id="modi" action="/modificarDatosAsincronamente" method="post">
        <div style="color:  black"><strong>Nombre:  </strong><span id="nom" style="color:white">${usuario.nombre}</span></div>
        <div class="modificar" style="visibility:hidden">
            <input id="campoNombre" type="text" value=""/>
            
        </div>
        <div style="color: black"><strong>Apellido:</strong>
            <span id="ape"style="color:white">${usuario.apellido}</span>
        </div>
        <div class="modificar" style="visibility:hidden">
            <input id="campoApellido" type="text" value=""/>
        </div>
            <div style="color: black"><strong>Fecha de Nacimiento:</strong><span id="fecha"style="color:white">${usuario.fechaNacimiento}</span></div>
        <div class="modificar" style="visibility:hidden">
             <input type="number" size="3" maxlength="2" id="fechaDia" value="" />
             de
            <select name="fechaMes" id="fechaMes">
                <option value="1">Enero</option>
                <option value="2">Febrero</option>
                <option value="3">Marzo</option>
                <option value="4">Abril</option>
                <option value="5">Mayo</option>
                <option value="6">Junio</option>
                <option value="7">Julio</option>
                <option value="8">Agosto</option>
                <option value="9">Septiembre</option>
                <option value="10">Octubre</option>
                <option value="11">Noviembre</option>
                <option value="12">Diciembre</option>
                <option id="mes" selected="0"></option>
           </select>
             de
            <input type="number" size="5" maxlength="4" id="fechaAno" value="" />

       </div>
        <br/>
        
       <input id="mod" type="button" value="Modificar">
       
       <div class="modificar" style="visibility:hidden">
           <input id="modif" type="button" value="Guardar Cambios"></input>
       </div>
       
    </form>
        
        
    
</div>
<div id="DatosdeUsuario">
    <legend>Datos de Usuario</legend>
    <br/>
    <br/>
    
     <div style="color: black"><strong>Nick:</strong>    
            <span id="ape"style="color:white">${usuario.nick}</span>
     </div>
    <br/> 
     
    
    
</div>
     
<div id="DatosdePartidas">
    <legend>Datos de Partidas</legend>
    <br/>
    <br/>
    
     <div style="color:black"><strong>Partidas Jugadas:</strong>  
            <span id="ape"style="color:white">${usuario.partidasJugadas}</span>
     </div>
    <br/> 
    <div style="color: black"><strong>Partidas Ganadas:</strong>
            <span id="ape"style="color:white">${usuario.partidasGanadas}</span>
     </div>
    
    
</div>
      
        <head>  
     
        <script>
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
            
            $(document).ready(
            function(){
                $("#mod").click(
                function(){
                    $("#mod").css("visibility","hidden");
                    $(".modificar").css("visibility","visible");
                }
            )
                
                $("#modif").click(

                function(){

                  

                     var apellido=$("#campoApellido").val();
                     var nombre=$("#campoNombre").val();
                     var fechaDia=$("#fechaDia").val();
                     var fechaMes=$("#fechaMes").val();
                     var fechaAno=$("#fechaAno").val();



                    $.get($("#modi").attr("action"),
                        {
                            nombre: nombre,
                            apellido : apellido,
                            fechaDia: fechaDia,
                            fechaMes: fechaMes,
                            fechaAno: fechaAno

                        },
                    function(json){
                        var db = $.parseJSON(json);
                        fijarValor(db);
                    },
                    "json")

                    $("#mod").css("visibility","visible");
                   $(".modificar").css("visibility","hidden");
                   $("#campoNombre").attr("value", "")
                    $("#campoApellido").attr("value", "")
                    $("#fechaDia").attr("value", "")
                    $("#mes").attr("selected", "0")
                    $("#fechaAno").attr("value", "")

           
                
                /*$("#modi").post("/modi",{

                        nombre: nombre,
                        apellido : apellido,
                        fechaDia: fechaDia,
                        fechaMes: fechaMes,
                        fechaAño: fechaAño
                    } , function(json){
                        var db = $.parseJSON(json);
                        fijarValor(db);
                    }, "json")
                    var c=$("#campoNombre").attr("value");
                    alert(c);
                    $(".modificar").css("visibility","hidden");                    
                    $("#campoNombre").attr("value", "")
                    $("#campoApellido").attr("value", "")
                    $("#fechaDia").attr("value", "")
                    $("#Mes").attr("value", "")
                    $("#fechaAno").attr("value", "")
                    */
                    
                }
            )
            });
        </script>
                            
</head>