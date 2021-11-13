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
            if(element.classList.contains('is-invalid')){
                error = true;
                return;
            }
        });

        if(evt.currentTarget.checkValidity() && !error){
            params['empresa-codigo'] = generarCodigoEmpresa();
            post('./info-procesada.php', params)
        }
    });
}

validarRegistroEmpresa('formRegistroEmpresa')
