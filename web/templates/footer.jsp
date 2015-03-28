<%@page import="com.javarevolutions.siycp.globals.Global"%>
<!--
 * Todos los Derechos Reservados © 2014 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgación en forma
 * parcial o total.
 *	---------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: footer.jsp
 * Fecha de creacion : Noviembre, 2013
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 *---------------------------------------------------------------------------
-->
<table class="tablaGeneral" align="center">
    <tr class="TextoCampo2">
        <td class="textoFooter" align="center">
            <%= Global.getPropRB("com.jr.props.derechos.only.jr") %>
        </td>
    </tr>
    <tr>
        <td class="TextoCampo2" align="center">
            <%= Global.getPropRB("com.jr.props.derechos.version.jr") %>	
        </td>
    </tr>
</table>
