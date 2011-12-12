<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>




<div id="tablaContenido">
    <strong>PUNTACION</strong>
     <br/>
     <br/>
</div>
<div id="nickUsuariosMejores">
    <strong>TOP 10 USUARIOS</strong>
 
    <br/>
        
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