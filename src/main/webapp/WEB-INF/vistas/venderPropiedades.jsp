<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

    <div id="casillasVender">
    <FORM ACTION="" id="checkCasillasVenta">

       
    </FORM>

    </div>





<script>  
    function fijarListaDeCasillas(casilla,i){
        //alert("fijando datos");
         if(casilla!="No tienes mensajes"){
            var nombre =casilla.nombre;
            var precio = casilla.precioVenta;
            if(i==0){
              $("#checkCasillasVenta").append("<INPUT TYPE=CHECKBOX NAME="+nombre+">"+nombre+" por "+precio+"$"+"<BR>");
            }else{
              //$("#casillasVender").append("<br/> Casilla : "+nombre +"Se vendera a: "+precio);
              $("#checkCasillasVenta").append("<INPUT TYPE=CHECKBOX NAME="+nombre+">"+nombre+" por "+precio+"$"+"<BR>");
            }
            $("#checkCasillasVenta").append("<br/>");
            $("#checkCasillasVenta").append("<center><INPUT TYPE=SUBMIT VALUE='Vender'></center>");
         }else{
             $("#casillasVender").html("<br/>No tienes casillas");
         }
     }

        $(document).ready(function(){
            //alert("vender Propiedades");
            $.get("/casillasConPosibilidadVenta", null, function(db){
                 if(db=="No tienes casillas"){
                    fijarBandejaEntrada(db,"0");
                }else{
                var casillas = $.parseJSON(db);
                $(casillas).each(function(index,value) {
                            fijarListaDeCasillas(value,index);
                        });
                }
            }, "json")


        })

</script>

