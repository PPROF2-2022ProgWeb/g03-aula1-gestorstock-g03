
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

    /* Esta es la resta. */
    total = (parseInt(total) - parseInt(valor));

    // Colocar el resultado de la resta en el control "span".
    document.getElementById('Recaudacion').innerHTML = total;
}

// TERMINA EVENTO 1 //

// COMIENZA EVENTO 2 //

function cerrarSesion() {
    console.log('Sesion cerrada');
    window.location.replace('./index.html')
}

document.getElementById('bt-cerrarSesion')?.addEventListener('click', cerrarSesion)

// TERMINA EVENTO 2 //

// EMPIEZA CALCULO DE EDAD //
const fechaNacimiento = document.getElementById("fechaNacimiento");
const edad = document.getElementById("edad");

const calcularEdad = (fechaNacimiento) => {
    const fechaActual = new Date();
    const anoActual = parseInt(fechaActual.getFullYear());
    const mesActual = parseInt(fechaActual.getMonth()) + 1;
    const diaActual = parseInt(fechaActual.getDate());

    // 2016-07-11
    const anoNacimiento = parseInt(String(fechaNacimiento).substring(0, 4));
    const mesNacimiento = parseInt(String(fechaNacimiento).substring(5, 7));
    const diaNacimiento = parseInt(String(fechaNacimiento).substring(8, 10));

    let edad = anoActual - anoNacimiento;
    if (mesActual < mesNacimiento) {
        edad--;
    } else if (mesActual === mesNacimiento) {
        if (diaActual < diaNacimiento) {
            edad--;
        }
    }
    return edad;
};

window.addEventListener('load', function () {

    fechaNacimiento?.addEventListener('change', function () {
        if (this.value) {
            console.log(`La edad es: ${calcularEdad(this.value)} años`)
        }
    });

});
// TERMINA CALCULO DE EDAD //

// EMPIEZA VALIDACION Y ENVIO DE FORMULARIO REGISTRO //
function post(path, params, method = 'post') {

    const form = document.createElement('form'); //crea un elemento (form) y lo coloca en HTML
    form.method = method;
    form.action = path;

    for (const key in params) {
        if (params.hasOwnProperty(key)) {
            const hiddenField = document.createElement('input');
            hiddenField.type = 'hidden'; //Esconde los campos para no mostrar otro formulario extra con los datos cargados
            hiddenField.name = key;
            hiddenField.value = params[key]; //Lo asigna a una llave, donde la clave es el nombre del campo y el valor es lo que contiene campo por HTTP

            form.appendChild(hiddenField); //Meter el campo creado dentro del formulario
        }
    }
    document.body.appendChild(form); //Meter el campo creado dentro del formulario dentro del body
    form.submit();
}

function generarCodigoEmpresa() {
    return Math.random().toString(36).replace(/[^a-z]+/g, '')
}

function validarRegistroEmpresa(idForm) {
    document.getElementById(idForm).addEventListener('submit', (evt) => {
        console.log('Form submiteado');
        evt.preventDefault();
        evt.stopPropagation();

        var error = false;
        var params = {}
        document.querySelectorAll(`#${evt.currentTarget.id} input, #${evt.currentTarget.id} select`).forEach(element => {
            params[element.name] = element.value;
            if (element.classList.contains('is-invalid')) {
                error = true;
                return;
            }
        });

        if (evt.currentTarget.checkValidity() && !error) {
            params['empresa-codigo'] = generarCodigoEmpresa();
            post('./info-procesada.php', params)
        }
    });
}

validarRegistroEmpresa('formRegistroEmpresa')

// TERMINA VALIDACION Y ENVIO DE FORMULARIO REGISTRO //