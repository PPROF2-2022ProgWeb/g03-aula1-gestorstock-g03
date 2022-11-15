package com.ar.Grupo3.data.objects.interfaces;

import com.ar.Grupo3.data.factory.interfaces.PlantillaDAO;
import com.ar.Grupo3.data.factory.interfaces.PlantillaModelView;
import com.ar.Grupo3.model.Provincia;
import com.ar.Grupo3.viewmodel.ProvinciaModel;

public interface DaoProvinciaIntf extends PlantillaDAO<Provincia>, PlantillaModelView<ProvinciaModel> {

}
