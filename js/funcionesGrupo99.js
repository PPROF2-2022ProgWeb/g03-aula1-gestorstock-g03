
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

    total = document.getElementById('Recaudacion')?.innerHTML;

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

    fechaNacimiento?.setAttribute('max', new Date().toISOString().split('T')[0])
    fechaNacimiento?.addEventListener('change', function () {
        if (this.value && calcularEdad(this.value) >= 18) {
            this.classList.add('is-valid')
            this.classList.remove('is-invalid')
        }
        else {
            this.classList.add('is-invalid')
            this.classList.remove('is-valid')
        }
    });

});
// TERMINA CALCULO DE EDAD //

// EMPIEZA VALIDACION Y ENVIO DE FORMULARIO REGISTRO //
function post(path, params, method = 'post') {

    const form = document.createElement('form');

    form.method = method;
    form.action = path;

    for (const key in params) {
        if (params.hasOwnProperty(key)) {
            const hiddenField = document.createElement('input');
            hiddenField.type = 'hidden';
            hiddenField.name = key;
            hiddenField.value = params[key];

            form.appendChild(hiddenField);
        }
    }
    document.body.appendChild(form);
    form.submit();
}

function generarCodigoEmpresa() {
    return Math.random().toString(36).replace(/[^a-z]+/g, '')
}

function validarRegistroEmpresa(idForm) {
    document.getElementById(idForm)?.addEventListener('submit', (evt) => {
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
            alert('Operacion exitosa!')
            params['empresa-codigo'] = generarCodigoEmpresa();
            post('./info-procesada.php', params)
        }
        else {
            alert('Error en los datos del formulario, por favor revise los datos ingresados')
        }
    });
}

validarRegistroEmpresa('formRegistroEmpresa')
// TERMINA VALIDACION Y ENVIO DE FORMULARIO REGISTRO //

// EMPIEZA VALIDACION INDIVIDUAL DE CAMPOS DE FORMULARIO //
function validacionIndividualCampo(idFormulario) {
    console.log(idFormulario + document.getElementById(idFormulario));
    document.querySelectorAll(`#${idFormulario} input`).forEach(input => {
        document.querySelector(`input[name=${input.getAttribute('name')}] ~ .invalid-tooltip`).textContent = input.getAttribute('title');
        input?.addEventListener('input', () => {
            var regex = new RegExp(input.getAttribute('pattern'));
            console.log(regex);

            if (regex.test(input.value)) {
                console.log(regex.test(input.value));
                input.classList.add('is-valid')
                input.classList.remove('is-invalid')
            }
            else {
                input.classList.add('is-invalid')
                input.classList.remove('is-valid')
            }
        })
    });
}
validacionIndividualCampo('formRegistroEmpresa')
validacionIndividualCampo('formRegistroUsuario')
// TERMINA VALIDACION INDIVIDUAL DE CAMPOS DE FORMULARIO //

// EMPIEZA VALIDACION DE CONTRASEÑA //
function validarPassword(idFormulario) {

    var password = document.querySelector(`#${idFormulario} input[name="contraseña"]`);
    var input = document.querySelector(`#${idFormulario} input[name="confirmacion-contraseña"]`);
    var tooltip = document.querySelector(`#${idFormulario} input[name="confirmacion-contraseña"] ~ .invalid-tooltip`);
    input?.addEventListener('input', () => {
        console.log(`Pass: ${password.value} Conf: ${input.value}`);
        if (input.value === password.value) {
            input.classList.add('is-valid')
            input.classList.remove('is-invalid')
        }
        else {
            tooltip.textContent = 'Las contraseñas no coinciden'
            input.classList.add('is-invalid')
            input.classList.remove('is-valid')
        }
    })
}

validarPassword('formRegistroEmpresa')
validarPassword('formRegistroUsuario')
// TERMINA VALIDACION DE CONTRASEÑA //