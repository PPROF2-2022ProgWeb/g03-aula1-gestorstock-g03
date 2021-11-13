// IEFI - CONSIGNA 5
// Las funciones en JavaScript deberán estar en un archivo llamado funcionesGrupo99.js
// Agregar al menos 2 eventos de Javascript para que el usuario interactúe con el DOM.

// COMIENZA EVENTO 1 //

function sumar(valor) {
    var total = 0;	
    valor = parseInt(valor); // Convertir el valor a un entero (número).
	
    total = document.getElementById('Recaudacion').innerHTML;
	
    // Aquí valido si hay un valor previo, si no hay datos, le pongo un cero "0".
    total = (total == null || total == undefined || total == "") ? 0 : total;
	
    /* Esta es la suma. */
    total = (parseInt(total) + parseInt(valor));
	
    // Colocar el resultado de la suma en el control "span".
    document.getElementById('Recaudacion').innerHTML = total;
}

function restar(valor) {
    var total = 0;	
    valor = parseInt(valor); // Convertir el valor a un entero (número).
	
    total = document.getElementById('Recaudacion').innerHTML;
	
    // Aquí valido si hay un valor previo, si no hay datos, le pongo un cero "0".
    total = (total == null || total == undefined || total == "") ? 0 : total;
	
    /* Esta es la suma. */
    total = (parseInt(total) - parseInt(valor));
	
    // Colocar el resultado de la suma en el control "span".
    document.getElementById('Recaudacion').innerHTML = total;
}

// TERMINA EVENTO 1 //

// COMIENZA EVENTO 2 //

// TERMINA EVENTO 2 //