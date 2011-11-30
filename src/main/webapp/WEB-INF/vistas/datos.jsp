<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<table BORDER="0">

                      
                        <form id="modi" action="/mod" method="post">
                        <tr>
                            <td>
                                <strong>Nombre: <e id="nom">${usuario.nombre}</e></strong><br/>
                                <div class="modificar" style="visibility:hidden">
                                     <input id="campoNombre" type="text" value=""/>
                                     <br></br>
                                </div>

                                <br/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <strong>Apellido:<e id="ape">${usuario.apellido}</e></strong><br/>
                                <div class="modificar" style="visibility:hidden">
                                     <input id="campoApellido" type="text" value=""/>
                                     <br></br>
                                </div>

                                <br/>
                            </td>
                                <td>
                                   

                                </td>

                        </tr>
                        <tr>
                            <td>
                                <strong>Fecha de Nacimiento: <e id="fecha">${usuario.fechaNacimiento}</e></strong><br/>
                                <div class="modificar" style="visibility:hidden">
                                  <input type="number" size="3" maxlength="2" id="fechaDia" value="" />
 				 de
                                    <select name="fechaMes" id="Mes">
                                        <option value="1">Enero</option>
                                        <option value="2">Febrero</option>
                                        <option value="3">Marzo</option>
                                        <option value="4">Abril</option>
                                        <option value="5">Mayo</option>
                                        <option value="6">Junio</option>
                                        <option value="7">Julio</option>
                                        <option value="8">Agosto</option>
                                        <option value="9">Septiembre</option>
                                        <option value="10">Octubre</option>
                                        <option value="11">Noviembre</option>
                                        <option value="12">Diciembre</option>
                                        <option selected="0"></option>
                                    </select>
 				 de
                                    <input type="number" size="5" maxlength="4" id="fechano" value="" />

                                <br/>
                                </div>
                            </td>
                            
                        </tr>
                        </form>
                
                    </table>
                    
                            
