<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div id="contenedorUnirse">
    <div>Haz click en Unirse a Partida y espera a que otros jugadores quieran jugar</div>
    
</div>

<img id="money1" src="/Estilos/money.png"/>
<img id="money2" src="/Estilos/money2.png"/>

<input type="button" id="aJugarPartida" value="¡ UNIRSE A PARTIDA !" onclick="window.location.href='prepararPartida'">