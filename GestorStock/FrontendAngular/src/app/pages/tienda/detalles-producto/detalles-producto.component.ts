import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProductoModel } from 'src/app/models/ProductoModel';
import { ProductosService } from 'src/app/services/productos.service';
import { Iconos } from 'src/app/utils/iconos.enum';

@Component({
  selector: 'app-detalles-producto',
  templateUrl: './detalles-producto.component.html',
  styleUrls: ['./detalles-producto.component.css']
})
export class DetallesProductoComponent implements OnInit {

  public productosInteres: ProductoModel[] = [];
  public iconos = Iconos;
  public producto: ProductoModel;
  public cargando: boolean = true;

  constructor(private prodServ: ProductosService,
              private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      this.productosInteres = []
      let id = params['id'];
      this.prodServ.buscarProducto(id).subscribe(data => {
        this.producto = data;
        
        this.prodServ.cargarProductos().subscribe(productos => {
          let filtrado = productos.filter( p => p.idTipoProd === this.producto.idTipoProd && p.idProducto !== this.producto.idProducto)
          if (filtrado.length === 0) {
            filtrado = productos;
          }
          let cantidad = Math.min(5, filtrado.length)
          for (let i = 0; i < cantidad; i++) {
            let index = Math.floor(Math.random() * filtrado.length)
            this.productosInteres.push(filtrado[index])        
          }
          this.cargando = false;
        })
      })
    });    

    
  }

  



}
