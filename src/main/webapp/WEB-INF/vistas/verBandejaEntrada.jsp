<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<style type="text/css">
    body
    {
        padding-top: 20px;
    }
    #wrapper
    {
        margin: auto;
        width: 645px;
        position: absolute;
        top: 5%;
        left: 30%;
    }
    .contents
    {
        width: 91%; /*height: 150px;*/
        margin: 0;
    }
    .contents > p
    {
        padding: 8px;
    }
    .table
    {
        width: 100%;
    }
    .table th, .table td
    {
        width: 16%;
        height: 20px;
        padding: 4px;
        text-align: left;
    }   
    .header
    {
        background-color: #4f7305;
        color: White;
    }
    #divs
    {
        margin: 0;
        height: 200px;
        font: verdana;
        font-size: 14px;
        background-color: White;
    }
    #divs > div
    {
        width: 98%;
        padding: 8px;
    }
    #divs > div p
    {
        width: 95%;
        padding: 8px;
    }
    ul.tab
    {
        list-style: none;
        margin: 0;
        padding: 0;
    }
    ul.tab li
    {
        display: inline;
        padding: 10px;
        color: Black;
        cursor: pointer;
    }
    #container
    {
        width: 100%;
        border: solid 1px red;
    }
</style>

<link href="/Estilos/smartpaginator.css" rel="stylesheet" type="text/css" />
<script src="jQuery/js/smartpaginator.js" type="text/javascript"></script>


<script type="text/javascript">
    $(document).ready(function() {
        $('#green-contents').css('display', '');
        
       
        

        $('#green').smartpaginator(
        { 
            totalrecords: ${mensajesTotal},
            recordsperpage: 3, 
            datacontainer: 'mt', 
            dataelement: 'tr', 
            initval: 0, 
            next: 'Next', 
            prev: 'Prev', 
            first: 'First', 
            last: 'Last', 
            theme: 'black' 
        });
        
        

    });
</script>

            
            <div id="menuMensajes">
        <br/>
    <div id="redactar1"><p onclick="javascript:EnviarMensajes()">REDACTAR</a></div>
        <br/>
     <div id="recibidos2" ><p onclick="javascript:verBandejaEntrada()">RECIBIDOS</p></div>
        <br/>
   
        
</div>
            <c:choose>
                <c:when test="${!empty mensajes}">
                
                      
<div id="wrapper">   

    <div id="green-contents" class="contents">

        <table id="mt" cellpadding="0" cellspacing="0" border="0" class="table">
            <c:forEach var="mensaje" items="${mensajes}" varStatus="status">
                <tr>
                    
                    <td>
                        <div >Autor:</div> 
                        <div style="color:white">${mensaje.autor}</div>
                    
                   
                        <div >Contenido:</div> 
                        <div style="color:white">${mensaje.contenido}</div>
                        &minus;
                    </td>
                    
                    <br/>
                </tr>
               
            </c:forEach>
        </table> 
        
        <div id="green" style="margin: auto; width: 500px">
        </div>
        
    </div>
    
    
</div>
                    </c:when>
<c:otherwise>
    <div id="noMensajes"> No tienes Mensajes</div>
</c:otherwise>
                </c:choose>

