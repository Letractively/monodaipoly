<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div id="estadisticas">
    <div  style="color:#111;left: 42%;position: absolute"  ><strong>TOP 10  USUARIOS</strong></div>
<br/>
<br/>

<div id="tablaContenido">
    <strong style="color:#111">PUNTACION</strong>
     <br/>
     <br/>
</div>
<div id="nickUsuariosMejores">
    <strong style="color:#111"> NICK</strong>
    <br/>
    <br/>
</div>

</div>




<script>
  

  
  <c:forEach var="usuariosMejores" items="${usuariosMejores}">
     $("#tablaContenido").append(${usuariosMejores.partidasGanadas});
     $("#tablaContenido").append("<br>")
     $("#tablaContenido").append("<br>")
     $("#nickUsuariosMejores").append("${usuariosMejores.nick}");
     $("#nickUsuariosMejores").append("<br>")
     $("#nickUsuariosMejores").append("<br>")
  </c:forEach>
  

</script>
