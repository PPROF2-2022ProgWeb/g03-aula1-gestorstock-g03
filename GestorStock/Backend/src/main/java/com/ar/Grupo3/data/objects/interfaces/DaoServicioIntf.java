package com.ar.Grupo3.data.objects.interfaces;

import com.ar.Grupo3.data.factory.interfaces.PlantillaDAO;
import com.ar.Grupo3.data.factory.interfaces.PlantillaModelView;
import com.ar.Grupo3.model.Servicio;
import com.ar.Grupo3.viewmodel.ServicioModel;

public interface DaoServicioIntf extends PlantillaDAO<Servicio>,PlantillaModelView<ServicioModel> {

}
