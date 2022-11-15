package com.ar.Grupo3.data.objects.interfaces;

import java.util.List;

import com.ar.Grupo3.data.factory.interfaces.PlantillaDAO;
import com.ar.Grupo3.data.factory.interfaces.PlantillaModelView;
import com.ar.Grupo3.model.Producto;
import com.ar.Grupo3.viewmodel.ProductoModel;

public interface DaoProductoIntf extends PlantillaDAO<Producto>, PlantillaModelView<ProductoModel> {

    public List<ProductoModel> SelectTodosPorNombre(String nombre);
}
