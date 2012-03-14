<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div id="recibidos2">

    holaa
   
</div>



<script type="text/javascript">
    $(document).ready(function(){
        <c:forEach var="mensaje" items="${mensajes}">
                $("#recibidos2").html(${mensaje.autor});
        </c:forEach>
    });
</script>