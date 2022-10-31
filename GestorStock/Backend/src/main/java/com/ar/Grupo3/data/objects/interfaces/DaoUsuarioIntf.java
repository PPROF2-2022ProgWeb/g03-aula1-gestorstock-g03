package com.ar.Grupo3.data.objects.interfaces;

import com.ar.Grupo3.data.factory.interfaces.PlantillaDAO;
import com.ar.Grupo3.data.factory.interfaces.PlantillaModelView;
import com.ar.Grupo3.model.Usuario;
import com.ar.Grupo3.viewmodel.UsuarioModel;

public interface DaoUsuarioIntf extends PlantillaDAO<Usuario>,PlantillaModelView<UsuarioModel> {

}
