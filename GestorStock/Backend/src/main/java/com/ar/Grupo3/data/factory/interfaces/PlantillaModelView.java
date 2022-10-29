package com.ar.Grupo3.data.factory.interfaces;

import java.util.List;

//Interfaz para la salida de objetos modelo para la vista.
public interface PlantillaModelView<PDAO> {

	public PDAO selectPorId(Long id);
	
	public List<PDAO> SelectTodos();
}
