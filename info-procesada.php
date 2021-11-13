<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="apple-touch-icon" sizes="180x180" href="./icons/apple-touch-icon.png">
    <link rel="icon" type="image/png" sizes="32x32" href="./icons/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="./icons/favicon-16x16.png">
    <link rel="manifest" href="./icons/site.webmanifest">
    <link rel="mask-icon" href="./icons/safari-pinned-tab.svg" color="#00324a">
    <link rel="shortcut icon" href="./icons/favicon.ico">
    <meta name="msapplication-TileColor" content="#00324a">
    <meta name="msapplication-config" content="./icons/browserconfig.xml">
    <meta name="theme-color" content="#00324a">

    <!-- implementacion de bibliotecas de bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <!-- en este pequeño bloque se importo los elementos neceasrios de bootstrap -->

    <title>Registro</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="fonts/gs-icofont/style.css">


</head>

<body>
    <header>
        <!-- Con esto se implementa Bootstrap 4 en el nav de este archivo -->
        <nav class="navbar navbar-expand-lg bg-primary navbar-dark text text-white">
            <a class="navbar-brand" href="index.html">
                <img src="img/logo.svg" alt="logo" class="logo">
            </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <!--Se agrega el acceso a la pagina de informacion-->
                        <a class="navlink" href="pag_info.html">Quiénes Somos?</a>
                    </li>
                    <li class="nav-item">
                        <a class="navlink" href="login.html">Ingresar</a>
                    </li>

                    <li class="nav-item dropdown ">
                        <a class="navlink dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Registrarse
                        </a>
                        <ul class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="registro-usuario.html">Para mí</a></li>
                            <li><a class="dropdown-item" href="registro-empresa.html">Para empresa</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </nav>
    </header>

    <main id="content">
        <div class="container-fluid d-flex flex-column align-items-center my-5">
            <h2 class="titulo exito">Registro finalizado con exito</h2>
            <section id="sectionregistro">
                <form class="container">
                    <div class="row ">
                        <div class="col-lg-12 p-5">
                            <div class="container">
                                <fieldset class="row g3">
                                    <legend>Usuario</legend>
                                    <div class="col-sm-6 mb-3">
                                        <!--El echo crea un string en el HTML y asi los muestra-->
                                        <label for="inNombre" class="form-label mr-3">Nombre</label>
                                        <div class="input-group">
                                            <span class="input-group-text" id="inputGroupNombre"><i class="icono-user"></i></span>
                                            <input id="inNombre" aria-describedby="inputGroupNombre" type="text" disabled value="<?php echo $_POST['nombre'] ?>" class="form-control rounded col-auto pr-3">
                                        </div>
                                    </div>
                                    <div class="col-sm-6 mb-3">
                                        <label class="mr-3">Apellido</label>
                                        <div class="input-group">
                                            <span class="input-group-text" id="inputGroupApellido"><i class="icono-user"></i></span>
                                            <input type="text" aria-describedby="inputGroupApellido" disabled value="<?php echo $_POST['apellido'] ?>" class="form-control rounded col-auto pr-3">
                                        </div>
                                    </div>
                                    <div class="col-sm-6 mb-3">
                                        <label class="mr-3">Correo</label>
                                        <div class="input-group">
                                            <span class="input-group-text" id="inputGroupCorreo"><i class="icono-email"></i></span>
                                            <input type="text" aria-describedby="inputGroupCorreo" disabled value="<?php echo $_POST['correo'] ?>" class="form-control rounded col-auto pr-3">
                                        </div>
                                    </div>
                                    <div class="col-sm-6 mb-3">
                                        <label class="mr-3">Fecha de nacimiento</label>
                                        <div class="input-group">
                                            <span class="input-group-text" id="inputGroupNacimento"><i class="icono-calendar"></i></span>
                                            <input type="text" aria-describedby="inputGroupNacimento" disabled value="<?php echo $_POST['nacimiento'] ?>" class="form-control rounded pr-3" id="validacionPais">
                                        </div>
                                    </div>
                                    <div class="col-sm-6 mb-3">
                                        <label class="mr-3">Pais</label>
                                        <div class="input-group">
                                            <span class="input-group-text" id="inputGroupPais"><i class="icono-world"></i></span>
                                            <input type="text" aria-describedby="inputGroupPais" disabled value="<?php echo $_POST['pais'] ?>" class="form-control rounded col-auto pr-3">
                                        </div>
                                    </div>
                                    <div class="col-sm-6 mb-3">
                                        <label class="mr-3">Provincia</label>
                                        <div class="input-group">
                                            <span class="input-group-text" id="inputGroupProvincia"><i class="icono-world"></i></span>
                                            <input type="text" aria-describedby="inputGroupProvincia" disabled value="<?php echo $_POST['provincia'] ?>" class="form-control rounded col-auto pr-3">
                                        </div>
                                    </div>
                                </fieldset>
                                <fieldset class="row g3">
                                    <legend>Empresa</legend>
                                    <div class="col-sm-6 mb-3">
                                        <label class="mr-3">Nombre</label>
                                        <div class="input-group">
                                            <span class="input-group-text" id="inputGroupEmpresaNombre"><i class="icono-empresa"></i></span>
                                            <input type="text" aria-describedby="inputGroupEmpresaNombre" disabled value="<?php echo $_POST['empresa-nombre'] ?>" data-minage="13" class="form-control rounded col-auto pr-3">
                                        </div>
                                    </div>
                                    <div class="col-sm-6 mb-3">
                                        <label class="mr-3">Descripcion</label>
                                        <div class="input-group">
                                            <span class="input-group-text" id="inputGroupEmpresaDescripcion"><i class="icono-empresa"></i></span>
                                            <input type="text" aria-describedby="inputGroupEmpresaDescripcion" disabled value="<?php echo $_POST['empresa-descripcion'] ?>" class="form-control rounded col-auto pr-3">
                                        </div>
                                    </div>
                                    <div class="col-sm-6 mb-3">
                                        <label class="mr-3">Codigo de identificacion</label>
                                        <div class="input-group">
                                            <span class="input-group-text" id="inputGroupEmpresaCodigo"><i class="icono-adn"></i></span>
                                            <input type="text" aria-describedby="inputGroupEmpresaCodigo" disabled value="<?php echo $_POST['empresa-codigo'] ?>" class="form-control rounded col-auto pr-3">
                                        </div>
                                    </div>
                                </fieldset>
                            </div>
                        </div>
                    </div>
                </form>
            </section>
        </div>
    </main>

    <footer id="piePagina" class="container-fluid d-flex flex-column">
        <div class="row d-flex justify-content-around">
            <div class="form-container col-4">
                <form id="formContacto" class="pb-5 pt-5 container no-gutters">
                    <legend>Contactanos</legend>
                    <div class="row">
                        <div class="col-6">
                            <span>Nombre</span>
                            <!-- utilizamos form-group que es un grupo de formulario pero me apoyo de la caracteristica "responsive-design" de bootstrap -->
                            <div class="form-group input-container">
                                <i class="icono-user"></i>

                                <!-- Tomamos la caracteristica form-control para estilo completo y relleno suficiente, pertence a bootstrap borre logininput pues no lo encontre en css avisenme si hice algo mal -->
                                <input class="form-control" type="text" required placeholder="Ingrese su nombre">
                            </div>
                        </div>

                        <div class="col-6">
                            <span>Apellido</span>
                            <!-- utilizamos form-group que es un grupo de formulario pero me apoyo de la caracteristica "responsive-design" de bootstrap -->
                            <div class="form-group input-container ">
                                <i class="icono-user"></i>
                                <!-- Tomamos la caracteristica form-control para estilo completo y relleno suficiente, pertence a bootstrap borre logininput pues no lo encontre en css avisenme si hice algo mal -->
                                <input class="form-control" type="text" required placeholder="Ingrese su apellido">
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-12">
                            <span>Correo</span>
                            <!-- utilizamos form-group que es un grupo de formulario pero me apoyo de la caracteristica "responsive-design" de bootstrap -->
                            <div class="form-group input-container ">
                                <i class="icono-email"></i>
                                <!-- Tomamos la caracteristica form-control para estilo completo y relleno suficiente, pertence a bootstrap borre logininput pues no lo encontre en css avisenme si hice algo mal -->
                                <input class="form-control" type="email" required placeholder="Ingrese su correo">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <span>Mensaje</span>
                            <!-- utilizamos form-group que es un grupo de formulario pero me apoyo de la caracteristica "responsive-design" de bootstrap -->
                            <div class="form-group input-container ">
                                <i class="icono-mensaje"></i>
                                <!-- Tomamos la caracteristica form-control para estilo completo y relleno suficiente, pertence a bootstrap borre logininput pues no lo encontre en css avisenme si hice algo mal -->
                                <textarea class="form-control" type="email" required placeholder="Ingrese su correo"></textarea>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-12">
                            <button class="action-button col-12" type="submit">Enviar mensaje</button>
                        </div>
                    </div>
                </form>
            </div>

            <div class="contact-info col-4 py-5">
                <h3 class="titulo">Informacion de contacto</h3>

                <span><i class="icono-home"></i> Argentina, Cordoba, Cordoba</span>
                <span><i class="icono-mensaje"></i> gestor.stock@gmail.com</span>
                <span><i class="icono-telefono"></i> +54 351 347 5441</span>
            </div>
        </div>
        <hr class="footer-divider">
        <span class="footer-legend">Sitio diseñado y desarrollado por Grupo1 PP TSDWAD ISPC Cohorte 2021©</span>
    </footer>

    <!-- SCRIPTS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
    <script>
		setTimeout(() => {
			window.location.assign('./index.html')
		}, 7000) //El contador para redireccion al index
	</script>
    <!-- SCRIPTS -->
</body>

</html>