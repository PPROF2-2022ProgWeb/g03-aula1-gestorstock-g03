package com.ar.Grupo3.seguridad.wserviceController;

public class Mensaje {

	private String Mensaje;

	public Mensaje() {
		//Constructor Vacio
	}

	public Mensaje(String mensaje) {
		Mensaje = mensaje;
	}

	public String getMensaje() {
		return Mensaje;
	}

	public void setMensaje(String mensaje) {
		Mensaje = mensaje;
	}
	
}
