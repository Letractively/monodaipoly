<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>



<script src="jQuery/js/smartpaginator.js" type="text/javascript"></script>


<script type="text/javascript">
    $(document).ready(function() {
        $.get("/jugadoresDisponibles", {},
        function(json){
                var db = $.parseJSON(json);
                
                $(db.disponibles).each(function(index,value) {
                    $("#jugad1").append("<option value="+value+">"+value+"</option>");
                    $("#jugad2").append("<option value="+value+">"+value+"</option>");
                    $("#jugad3").append("<option value="+value+">"+value+"</option>");
                    $("#jugad4").append("<option value="+value+">"+value+"</option>");
                });
            
        }, "json");

    });
    function showimage(){
    //alert("Estilos/tux/"+$(".casillas").val());
    var avatar1 ="Estilos/tux/tux"+$("#avata1").val()+".png"
    var avatar2 ="Estilos/tux/tux"+$("#avata2").val()+".png"
    var avatar3 ="Estilos/tux/tux"+$("#avata3").val()+".png"
    var avatar4 ="Estilos/tux/tux"+$("#avata4").val()+".png"
    //alert(avatar);
    $("#img1").html("<img style='height: 20%;width: 20%' src="+avatar1+">");
    $("#img2").html("<img style='height: 20%;width: 20%' src="+avatar2+">");
    $("#img3").html("<img style='height: 20%;width: 20%' src="+avatar3+">");
    $("#img4").html("<img style='height: 20%;width: 20%' src="+avatar4+">");

} 
</script>


<div id="crearPartidaAdmin">
    <center>
        <br/>
        <br/>
        <br/>
        <form action="/adCrearPartida" method="post" name="datos">
        <table border="5" style="color: #FFF">
            <tr>
            <td></td>
            <td><p>JUGADOR 1</p></td>
            <td><p>JUGADOR 2</p></td>
            <td><p>JUGADOR 3</p></td>
            <td><p>JUGADOR 4</p></td>
            </tr>
            <tr>
                <td><p>USUARIO</p></td>
                <td>
                    <select id="jugad1" name="jug1">
                        
                    </select>
                </td>
                <td>
                    <select id="jugad2" name="jug2">
                        
                    </select>
                </td>
                <td>
                    <select id="jugad3" name="jug3">
                        
                    </select>
                </td>
                <td>
                    <select id="jugad4" name="jug4">
                        
                    </select>
                </td>
            </tr>
            <tr>
                <td><p>TUX</p></td>
                <td>
                    <select id="avata1" name="avatar1" onChange="showimage()">
                        <option value="0">Lara Croft</option>
                        <option value="1">Batman</option>
                        <option value="2">Shreck</option>
                        <option value="3">Vegeta</option>
                        <option value="4">Villa</option>
                        <option value="5">Ghost Rider</option>
                        <option value="6">Empresario</option>
                        <option value="7">Rambo</option>
                        <option value="8">Harry Potter</option>
                        <option value="9">Patricio</option>
                        <option value="10">Neo</option>
                        <option value="11">Naruto</option>
                        <option value="12">Mario</option>
                        <option value="13">Legolas</option>
                        <option value="14">Goku</option>
                    </select>
                    <div id="img1"><img style="height: 20%;width: 20%" src="Estilos/tux/tux0.png" alt="" ></div>
                </td>
                <td>
                    <select id="avata2" name="avatar2" onChange="showimage()">
                        <option value="0">Lara Croft</option>
                        <option value="1">Batman</option>
                        <option value="2">Shreck</option>
                        <option value="3">Vegeta</option>
                        <option value="4">Villa</option>
                        <option value="5">Ghost Rider</option>
                        <option value="6">Empresario</option>
                        <option value="7">Rambo</option>
                        <option value="8">Harry Potter</option>
                        <option value="9">Patricio</option>
                        <option value="10">Neo</option>
                        <option value="11">Naruto</option>
                        <option value="12">Mario</option>
                        <option value="13">Legolas</option>
                        <option value="14">Goku</option>
                    </select>
                    <div id="img2"><img style="height: 20%;width: 20%" src="Estilos/tux/tux0.png" alt="" ></div>
                </td>
                <td>
                    <select id="avata3" name="avatar3" onChange="showimage()">
                        <option value="0">Lara Croft</option>
                        <option value="1">Batman</option>
                        <option value="2">Shreck</option>
                        <option value="3">Vegeta</option>
                        <option value="4">Villa</option>
                        <option value="5">Ghost Rider</option>
                        <option value="6">Empresario</option>
                        <option value="7">Rambo</option>
                        <option value="8">Harry Potter</option>
                        <option value="9">Patricio</option>
                        <option value="10">Neo</option>
                        <option value="11">Naruto</option>
                        <option value="12">Mario</option>
                        <option value="13">Legolas</option>
                        <option value="14">Goku</option>
                    </select>
                    <div id="img3"><img style="height: 20%;width: 20%" src="Estilos/tux/tux0.png" alt="" ></div>
                </td>
                <td>
                    <select id="avata4" name="avatar4" onChange="showimage()">
                        <option value="0">Lara Croft</option>
                        <option value="1">Batman</option>
                        <option value="2">Shreck</option>
                        <option value="3">Vegeta</option>
                        <option value="4">Villa</option>
                        <option value="5">Ghost Rider</option>
                        <option value="6">Empresario</option>
                        <option value="7">Rambo</option>
                        <option value="8">Harry Potter</option>
                        <option value="9">Patricio</option>
                        <option value="10">Neo</option>
                        <option value="11">Naruto</option>
                        <option value="12">Mario</option>
                        <option value="13">Legolas</option>
                        <option value="14">Goku</option>
                    </select>
                    <div id="img4"><img style="height: 20%;width: 20%" src="Estilos/tux/tux0.png" alt="" ></div>
                </td>
            </tr>
            <tr>
                <td><p>DINERO</p></td>
                <td> 
                    <input name="dinero1" value="200">
                </td>
                <td> 
                    <input name="dinero2" value="200">
                </td>
                <td> 
                    <input name="dinero3" value="200">
                </td>
                <td> 
                    <input name="dinero4" value="200">
                </td>
            </tr>
            <tr>
                <td><p>POSICION</p></td>
                <td> 
                    <input name="pos1" value="0">
                </td>
                <td> 
                    <input name="pos2" value="0">
                </td>
                <td> 
                    <input name="pos3" value="0">
                </td>
                <td> 
                    <input name="pos4" value="0">
                </td>
            </tr>
        </table>
            <p>BOTE</p>
            <input name="bote" value="0"/>
            <br/>
            <input type="submit" value="Crear" />
        </form>
        </center>
    
</div>

