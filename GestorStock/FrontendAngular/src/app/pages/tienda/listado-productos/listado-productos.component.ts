import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProductoModel } from 'src/app/models/ProductoModel';
import { ProductosService } from 'src/app/services/productos.service';

@Component({
  selector: 'app-listado-productos',
  templateUrl: './listado-productos.component.html',
  styleUrls: ['./listado-productos.component.css'],
})
export class ListadoProductosComponent implements OnInit {
  public productos: ProductoModel[] = [];
  constructor(private route: ActivatedRoute, private ps: ProductosService) {}

  ngOnInit(): void {
    this.route.url.subscribe({
      next: () => {
        let param = this.route.snapshot.paramMap.get('busqueda');
        if (param) {
          this.ps.cargarPorNombreProducto(param).subscribe({
            next: (data) => {
              this.productos = data;
            },
            error: (err) => console.log(err),
          });
        } else {
          this.ps.cargarProductos().subscribe({
            next: (data) => {
              this.productos = data;
            },
            error: (err) => console.log(err),
          });
        }
      },
    });
  }
}
