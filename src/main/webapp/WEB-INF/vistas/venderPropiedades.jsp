<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div id="casillasVender">
    <FORM  id="checkCasillasVenta">
        <div id="colm1"></div>
        <div id="colm2"></div>
        <div id="colm3"></div>
    </FORM>

</div>




<script>  
    function fijarListaDeCasillas(casilla,i,sw){
        //alert("fijando datos");
        //alert(sw);
        if(casilla!="No tienes casillas"){
            var nombre =casilla.nombre;
            var precio = casilla.precioVenta;
            var numero =casilla.numero;
            if(i==0 || sw==0){
                $("#colm1").append("<INPUT TYPE=CHECKBOX  id="+numero+" NAME='cas'>"+nombre+" por "+precio+"$");
                $("#colm1").append("<br/>");
            }else if(sw==1){
                $("#colm2").append("<INPUT TYPE=CHECKBOX  id="+numero+" NAME='cas'>"+nombre+" por "+precio+"$");
                $("#colm2").append("<br/>");
                
            }

           
        }else{
            $("#casillasVender").html("<br/>No tienes casillas");
        }
    }

    

    $(document).ready(function(){

       


        //alert("vender Propiedades");
        $.get("/casillasConPosibilidadVenta", null, function(db){
            $("#casillasVender").css("visibility", "visible");
            if(db=="No tienes casillas"){
                $("#casillasVender").html("<br/><center>No tienes propiedades</center><br/>");
                $("#casillasVender").append("<center><img src='Estilos/sinDinero.png' width='60%' height='60%'/></center><br/>");
                $("#casillasVender").append("<center><button onClick='cerrarDiv()'>Cerrar</button></center>");
            }else{
                var casillas = $.parseJSON(db);
                var sw=0;
                $(casillas).each(function(index,value) {
                    fijarListaDeCasillas(value,index,sw);
                    if (sw==0){
                        sw=1;
                    }else{
                        sw=0;
                    }
                   
                });
                $("#colm3").append("<center><button  onClick='venderCasas()'>Vender</button> <button onClick='cerrarDiv()'>Cerrar</button></center>");
               
            }
        }, "json")


    })

</script>

