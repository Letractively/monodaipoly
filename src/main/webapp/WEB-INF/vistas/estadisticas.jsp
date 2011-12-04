<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>




<div id="tablaContenido">
    PUNTACION
    <br> 
</div>
<div id="nickUsuariosMejores">
    TOP 10 USUARIOS
        
</div>




<script>
  

  
  <c:forEach var="usuariosMejores" items="${usuariosMejores}">
     $("#tablaContenido").append(${usuariosMejores.partidasGanadas});
     $("#tablaContenido").append("<br>")
  </c:forEach>
  

</script>
