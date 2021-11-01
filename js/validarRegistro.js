function validarRegistro() {
    if (document.formRegistro.pais.
        value == 0 ||
        document.formRegistro.pais.
            value == "") {
        alert("Selecciona Una opción");
        document.nombreFormulario.nombreSelect.
            focus();
    }
}

document.getElementById('btRegistrar')?.addEventListener('click', validarRegistro);

function cerrarSesion() {
    console.log('Sesion cerrada');
    window.location.replace('./index.html')
}

document.getElementById('bt-cerrarSesion')?.addEventListener('click', cerrarSesion)