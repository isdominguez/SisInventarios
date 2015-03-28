/**
 * Todos los Derechos Reservados © 2014 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgación en forma
 * parcial o total.
 *	---------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: index.js
 * Fecha de creacion : Noviembre, 2013
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 *---------------------------------------------------------------------------
**/

/**
 * Función que inicializa nuestra pantalla
 * @return
 */
function carga() {
    $("#formularioLogin\\:user").focus();
}

function showError(error) {
    alert(error);
    if(error == 'null' || error == "null" || error == null) {
        return;
    }
    limite = 0;
    while(error.length > limite) {
        for(var i = 0; i < 100; i++) {
            document.write(error.charAt(limite));
            limite++;
            if(limite >= error.length) {
                break;
            }
        }
        document.write("<br>");
    }
}
