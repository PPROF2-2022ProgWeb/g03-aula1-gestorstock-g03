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

document.getElementById('btRegistrar').addEventListener('click', validarRegistro);