package com.ar.Grupo3.data.objects.interfaces;

import com.ar.Grupo3.data.factory.interfaces.PlantillaDAO;
import com.ar.Grupo3.data.factory.interfaces.PlantillaModelView;
import com.ar.Grupo3.model.Ventas;
import com.ar.Grupo3.viewmodel.VentasModel;

public interface DaoVentasIntf extends PlantillaDAO<Ventas>,PlantillaModelView<VentasModel> {

}
