package com.ar.Grupo3.data.objects.interfaces;

import com.ar.Grupo3.data.factory.interfaces.PlantillaDAO;
import com.ar.Grupo3.data.factory.interfaces.PlantillaModelView;
import com.ar.Grupo3.model.Pedido;
import com.ar.Grupo3.viewmodel.PedidoModel;

public interface DaoPedidoIntf extends PlantillaDAO<Pedido>, PlantillaModelView<PedidoModel> {

}
