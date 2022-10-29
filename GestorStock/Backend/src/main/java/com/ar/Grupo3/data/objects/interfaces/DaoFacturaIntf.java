package com.ar.Grupo3.data.objects.interfaces;

import com.ar.Grupo3.data.factory.interfaces.PlantillaDAO;
import com.ar.Grupo3.data.factory.interfaces.PlantillaModelView;
import com.ar.Grupo3.model.Factura;
import com.ar.Grupo3.viewmodel.FacturaModel;

public interface DaoFacturaIntf extends PlantillaDAO<Factura>,PlantillaModelView<FacturaModel> {

}
