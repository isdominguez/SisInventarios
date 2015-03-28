<%@page import="com.javarevolutions.siycp.globals.Global"%>
<!--
 * Todos los Derechos Reservados © 2014 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgación en forma
 * parcial o total.
 *	---------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: asistencia.jsp
 * Fecha de creacion : Febrero, 2014
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 *---------------------------------------------------------------------------
-->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
    String resources = request.getContextPath()+"/resources";
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<%= resources %>/css/jr.css" rel="stylesheet" type="text/css"/>
<title><%= Global.getPropRB("com.jr.props.title") %></title>
<script type="text/javascript" language="JavaScript" src="<%= resources %>/js/jquery-1.10.2.js"></script>
<script type="text/javascript" language="JavaScript" src="<%= resources %>/js/asistencia.js"></script>
<script type="text/javascript" language="JavaScript">
/**
 * Función que cancela la forma
 * @return
 */
function cancelar() {
    window.location.replace("<%= request.getContextPath() %>");
}

/**
 * Función que valida si los datos ingresados en la forma son correctos
 * @return true si los datos son correctos, false si hay datos requeridos nulos
 */
function validate() {
    user = $("#formularioAsistencia\\:user");
    password = $("#formularioAsistencia\\:password");
    if(user.val() == '') {
        alert('<%= Global.getPropRB("com.jr.props.msg.user") %>');
        user.focus();
        return false;
    } else if(password.val() == '') {
        alert('<%= Global.getPropRB("com.jr.props.msg.password") %>');
        password.focus();
        return false;
    } else {
        return true;
    }
}
</script>
<title><%= Global.getPropRB("com.jr.props.title.modulo.asistencia") %></title>
</head>
<body onload="carga();" class="formaBackGround">
<f:view>
<h:form id="formularioAsistencia">
<table width="100%">
    <tr>
        <td align="center">
        <br><br><br><br><br><br><br>
        <table align="center">
            <tr>
                <td>
                    <p style="font-size: 16px; font-style: italic; color: darkblue;">
                        <%= Global.getPropRB("com.jr.props.title.modulo.asistencia") %>
                    </p>
                </td>
            </tr>
        </table>
        <br><br>
        <table align="center">
        <tr>
        <td>
            <fieldset>
            <legend style="font-weight: bold; font-style: italic;">
                <%= Global.getPropRB("com.jr.props.title.modulo.asistencia.legend") %>
            </legend>
            <table>
                <tr>
                    <td class="FondoTabla"><%= Global.getPropRB("com.jr.props.access.lbl.usuario") %> :</td>
                    <td>
                        <h:inputText id="user" value="#{beanLogin.user}" styleClass="TextoCampo2"/>
                    </td>
                </tr>
                <tr>
                    <td class="FondoTabla"><%= Global.getPropRB("com.jr.props.access.lbl.contrasenia") %> :</td>
                    <td>
                        <h:inputSecret id="password" value="#{beanLogin.password}" styleClass="TextoCampo2"/>
                    </td>
                </tr>
                <tr>
                    <td><input type="button" value="<%= Global.getPropRB("com.jr.props.btn.cancelar") %>" 
                               class="estiloBoton" onclick="cancelar();"
                               title="Haz clic para limpiar los datos ingresados"/></td>
                    <td align="center">
                        <h:commandButton id="btnAceptar" value="#{props['com.jr.props.btn.aceptar']}" styleClass="estiloBoton" 
                                         onclick="return validate();" action="#{beanLogin.registraAsistencia}"
                        title="Haz clic para ingresar al sistema"/>
                    </td>
                </tr>
            </table>
            </fieldset>
        </td>
        </tr>
        </table>
        <table align="center">
        <tr>
        <td style="color: red; font-weight: bold; text-align: justify; font-size: 14px;"
        align="center"><%= request.getAttribute("errorIndex") != null ? request.getAttribute("errorIndex") : "" %></td>
        </tr>
        </table>
        <br><br>
        </td>
    </tr>
</table>

<table align="center" width="60%">
    <tr>
        <td height="0.5px" bgcolor="#E0E0E0"></td>
    </tr>
    <tr class="TextoCampo2">
        <td class="textoFooter" align="center">
            <h:outputText value="#{props['com.jr.props.derechos.only.jr']}"/>
        </td>
    </tr>
</table>
        
</h:form>
</f:view>
</body>
</html>
