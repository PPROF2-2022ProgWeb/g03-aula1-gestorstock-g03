import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RegistroService } from 'src/app/services/registro.service';
import { Iconos } from 'src/app/utils/iconos.enum';

@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.css']
})
export class RegistroComponent implements OnInit {

  nombre = new FormControl('', []);
  apellido = new FormControl('', []);
  pais = new FormControl('');
  provincia = new FormControl('', []);
  fechaNacimiento = new FormControl('');
  confirmarContraseña = new FormControl('', [], []);
  empresa = new FormControl('', []);
  descripcion = new FormControl('', []);
  mail = new FormControl('', [], []);
  password = new FormControl('', [], []);
  
  form: any;


  mostrar = false;
  public iconos = Iconos;

  constructor(private formBuilder: FormBuilder,
    private registroService: RegistroService,
    private router: Router) {
    this.form = this.formBuilder.group({
      nombre: ['', [Validators.minLength(4)]],
      apellido: ['', [Validators.minLength(4)]],
      pais: [Validators.required],
      provincia: ['', [Validators.minLength(4)]],
      fechaNacimiento: [Validators.required],
      confirmarContraseña: ['', [Validators.required, Validators.minLength(6)]],
      empresa: ['', [Validators.minLength(4)]],
      descripcion: ['', [Validators.minLength(10)]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      mail: ['', [Validators.required, Validators.email]]
    });
  }

  ngOnInit(): void {
  }


  cargarEmpresa() {
    this.mostrar = true;
  }

  
  get mailValid(){
    return this.form.get('mail')?.touched && !this.form.get('mail')?.valid;
  }

  get mailErrorMessage(){
    if(this.mail.hasError('required')){
      return "El correo es requerido"
    }else {
      return "Formato de correo no valido"
    }
  }

  get passwordValid(){
    return this.form.get('password')?.touched && !this.form.get('password')?.valid;
  }

  get passwordErrorMessage(){
    return "Debe contener mínimo 6 caracteres"
  }

  get nombreValid(){
    return !this.form.get('nombre')?.valid;
  }
  get nombreErrorMessage(){
    return "Debe contener mínimo 4 caracteres"
  }

  get apellidoValid(){
    return !this.form.get('apellido')?.valid;
  }
  get apellidoErrorMessage(){
    return "Debe contener mínimo 4 caracteres"
  }

  

  

  registro(){
    this.registroService.registro(this.mail, this.password)
    .then(() => {
      this.router.navigate(['login']);
    })
    .catch(() => {
      alert("Revise los datos ingresados.")
    });    
  }

  // const countryList = [
  //   "Afghanistan",
  //   "Albania",
  //   "Algeria",
  //   "American Samoa",
  //   "Andorra",
  //   "Angola",
  //   "Anguilla",
  //   "Antarctica",
  //   "Antigua and Barbuda",
  //   "Argentina",
  //   "Armenia",
  //   "Aruba",
  //   "Australia",
  //   "Austria",
  //   "Azerbaijan",
  //   "Bahamas (the)",
  //   "Bahrain",
  //   "Bangladesh",
  //   "Barbados",
  //   "Belarus",
  //   "Belgium",
  //   "Belize",
  //   "Benin",
  //   "Bermuda",
  //   "Bhutan",
  //   "Bolivia (Plurinational State of)",
  //   "Bonaire, Sint Eustatius and Saba",
  //   "Bosnia and Herzegovina",
  //   "Botswana",
  //   "Bouvet Island",
  //   "Brazil",
  //   "British Indian Ocean Territory (the)",
  //   "Brunei Darussalam",
  //   "Bulgaria",
  //   "Burkina Faso",
  //   "Burundi",
  //   "Cabo Verde",
  //   "Cambodia",
  //   "Cameroon",
  //   "Canada",
  //   "Cayman Islands (the)",
  //   "Central African Republic (the)",
  //   "Chad",
  //   "Chile",
  //   "China",
  //   "Christmas Island",
  //   "Cocos (Keeling) Islands (the)",
  //   "Colombia",
  //   "Comoros (the)",
  //   "Congo (the Democratic Republic of the)",
  //   "Congo (the)",
  //   "Cook Islands (the)",
  //   "Costa Rica",
  //   "Croatia",
  //   "Cuba",
  //   "Curaçao",
  //   "Cyprus",
  //   "Czechia",
  //   "Côte d'Ivoire",
  //   "Denmark",
  //   "Djibouti",
  //   "Dominica",
  //   "Dominican Republic (the)",
  //   "Ecuador",
  //   "Egypt",
  //   "El Salvador",
  //   "Equatorial Guinea",
  //   "Eritrea",
  //   "Estonia",
  //   "Eswatini",
  //   "Ethiopia",
  //   "Falkland Islands (the) [Malvinas]",
  //   "Faroe Islands (the)",
  //   "Fiji",
  //   "Finland",
  //   "France",
  //   "French Guiana",
  //   "French Polynesia",
  //   "French Southern Territories (the)",
  //   "Gabon",
  //   "Gambia (the)",
  //   "Georgia",
  //   "Germany",
  //   "Ghana",
  //   "Gibraltar",
  //   "Greece",
  //   "Greenland",
  //   "Grenada",
  //   "Guadeloupe",
  //   "Guam",
  //   "Guatemala",
  //   "Guernsey",
  //   "Guinea",
  //   "Guinea-Bissau",
  //   "Guyana",
  //   "Haiti",
  //   "Heard Island and McDonald Islands",
  //   "Holy See (the)",
  //   "Honduras",
  //   "Hong Kong",
  //   "Hungary",
  //   "Iceland",
  //   "India",
  //   "Indonesia",
  //   "Iran (Islamic Republic of)",
  //   "Iraq",
  //   "Ireland",
  //   "Isle of Man",
  //   "Israel",
  //   "Italy",
  //   "Jamaica",
  //   "Japan",
  //   "Jersey",
  //   "Jordan",
  //   "Kazakhstan",
  //   "Kenya",
  //   "Kiribati",
  //   "Korea (the Democratic People's Republic of)",
  //   "Korea (the Republic of)",
  //   "Kuwait",
  //   "Kyrgyzstan",
  //   "Lao People's Democratic Republic (the)",
  //   "Latvia",
  //   "Lebanon",
  //   "Lesotho",
  //   "Liberia",
  //   "Libya",
  //   "Liechtenstein",
  //   "Lithuania",
  //   "Luxembourg",
  //   "Macao",
  //   "Madagascar",
  //   "Malawi",
  //   "Malaysia",
  //   "Maldives",
  //   "Mali",
  //   "Malta",
  //   "Marshall Islands (the)",
  //   "Martinique",
  //   "Mauritania",
  //   "Mauritius",
  //   "Mayotte",
  //   "Mexico",
  //   "Micronesia (Federated States of)",
  //   "Moldova (the Republic of)",
  //   "Monaco",
  //   "Mongolia",
  //   "Montenegro",
  //   "Montserrat",
  //   "Morocco",
  //   "Mozambique",
  //   "Myanmar",
  //   "Namibia",
  //   "Nauru",
  //   "Nepal",
  //   "Netherlands (the)",
  //   "New Caledonia",
  //   "New Zealand",
  //   "Nicaragua",
  //   "Niger (the)",
  //   "Nigeria",
  //   "Niue",
  //   "Norfolk Island",
  //   "Northern Mariana Islands (the)",
  //   "Norway",
  //   "Oman",
  //   "Pakistan",
  //   "Palau",
  //   "Palestine, State of",
  //   "Panama",
  //   "Papua New Guinea",
  //   "Paraguay",
  //   "Peru",
  //   "Philippines (the)",
  //   "Pitcairn",
  //   "Poland",
  //   "Portugal",
  //   "Puerto Rico",
  //   "Qatar",
  //   "Republic of North Macedonia",
  //   "Romania",
  //   "Russian Federation (the)",
  //   "Rwanda",
  //   "Réunion",
  //   "Saint Barthélemy",
  //   "Saint Helena, Ascension and Tristan da Cunha",
  //   "Saint Kitts and Nevis",
  //   "Saint Lucia",
  //   "Saint Martin (French part)",
  //   "Saint Pierre and Miquelon",
  //   "Saint Vincent and the Grenadines",
  //   "Samoa",
  //   "San Marino",
  //   "Sao Tome and Principe",
  //   "Saudi Arabia",
  //   "Senegal",
  //   "Serbia",
  //   "Seychelles",
  //   "Sierra Leone",
  //   "Singapore",
  //   "Sint Maarten (Dutch part)",
  //   "Slovakia",
  //   "Slovenia",
  //   "Solomon Islands",
  //   "Somalia",
  //   "South Africa",
  //   "South Georgia and the South Sandwich Islands",
  //   "South Sudan",
  //   "Spain",
  //   "Sri Lanka",
  //   "Sudan (the)",
  //   "Suriname",
  //   "Svalbard and Jan Mayen",
  //   "Sweden",
  //   "Switzerland",
  //   "Syrian Arab Republic",
  //   "Taiwan",
  //   "Tajikistan",
  //   "Tanzania, United Republic of",
  //   "Thailand",
  //   "Timor-Leste",
  //   "Togo",
  //   "Tokelau",
  //   "Tonga",
  //   "Trinidad and Tobago",
  //   "Tunisia",
  //   "Turkey",
  //   "Turkmenistan",
  //   "Turks and Caicos Islands (the)",
  //   "Tuvalu",
  //   "Uganda",
  //   "Ukraine",
  //   "United Arab Emirates (the)",
  //   "United Kingdom of Great Britain and Northern Ireland (the)",
  //   "United States Minor Outlying Islands (the)",
  //   "United States of America (the)",
  //   "Uruguay",
  //   "Uzbekistan",
  //   "Vanuatu",
  //   "Venezuela (Bolivarian Republic of)",
  //   "Viet Nam",
  //   "Virgin Islands (British)",
  //   "Virgin Islands (U.S.)",
  //   "Wallis and Futuna",
  //   "Western Sahara",
  //   "Yemen",
  //   "Zambia",
  //   "Zimbabwe",
  //   "Åland Islands"
  // ];
  
  
  // dropPais = document.getElementById('dropPais')
  
  // for (i = 1; i <= countryList.length; i++) {
  //   dropPais.options[i] = new Option(countryList[i-1]);
  // }
  // console.log(dropPais.options.length);


}
