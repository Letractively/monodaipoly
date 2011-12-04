

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>enviar mensajes</h1>
        <form action="/enviarMensajes" method="get">
                Destinatario:
             <input id="destinatario" type="text" name="destinatario" value="" />
                Contenido:
             <input id="conteidoMensaje" type="text" name="contenidoMensaje" value="" />
              <input id="EnviarMensaje" type="button" value="Enviar"/>
        </form>
    </body>
</html>
