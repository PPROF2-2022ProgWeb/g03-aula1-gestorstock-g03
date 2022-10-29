package com.ar.Grupo3.data.factory.interfaces;

//Interfaz madre que permitira el el comportamiento basico de una tabla
public interface PlantillaDAO<PDAO>{
	
	public PDAO buscar(Long id);

    public Long contarTodos();

    public void agregar(PDAO p);

    public void modificar(PDAO p);

    public void borrar(PDAO p);
	
}
