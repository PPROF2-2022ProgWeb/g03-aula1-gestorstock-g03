package com.ar.Grupo3.data.objects.interfaces;

import com.ar.Grupo3.data.factory.interfaces.PlantillaDAO;
import com.ar.Grupo3.data.factory.interfaces.PlantillaModelView;
import com.ar.Grupo3.model.Delivery;
import com.ar.Grupo3.viewmodel.DeliveryModel;

public interface DaoDeliveryIntf extends PlantillaDAO<Delivery>, PlantillaModelView<DeliveryModel> {

}
