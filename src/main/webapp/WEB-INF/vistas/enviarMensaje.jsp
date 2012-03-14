<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div class="mensajes ">
        <div id="enviarMensaje" >
            <form action="/enviarMensajes" method="get" id="formMensajes">
                Destinatario:
                <div>
                    <input id="destinatario" type="text" name="destinatario" value="" />
                </div>
                <br/>
                Contenido:
                <div>
                    <textarea id="contenidoMensaje" cols="80" rows="5" type="text" name="contenidoMensaje" value="" ></textarea>
                </div>
                <br/>
                <div id="mensaje" align="center">
                    <input id="EnviarMensajeboton" type="submit" value="Enviar" />
                    <input id="Limpiarmensajeboton" type="reset" value="Limpiar"/>
                </div>

            </form>
            </div>

     
</div>