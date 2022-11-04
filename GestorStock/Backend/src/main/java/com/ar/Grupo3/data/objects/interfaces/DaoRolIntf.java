package com.ar.Grupo3.data.objects.interfaces;

import com.ar.Grupo3.data.factory.interfaces.PlantillaDAO;
import com.ar.Grupo3.data.factory.interfaces.PlantillaModelView;
import com.ar.Grupo3.seguridad.entity.Rol;
import com.ar.Grupo3.viewmodel.RolModel;

public interface DaoRolIntf extends PlantillaDAO<Rol>,PlantillaModelView<RolModel> {

}
