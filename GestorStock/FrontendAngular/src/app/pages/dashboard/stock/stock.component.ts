import { CurrencyPipe, DatePipe } from '@angular/common';
import { Component, OnInit, ViewChild } from '@angular/core';
import { DataTableComponent } from 'src/app/components/data-table/data-table.component';
import { ModalComponent } from 'src/app/components/modal/modal.component';
import { SearchResult } from 'src/app/interfaces/searchResult';
import { Producto, ProductoModel } from 'src/app/models/ProductoModel';
import { ProductosService } from 'src/app/services/productos.service';
import { Productos } from 'src/app/utils/data/productos';
import {
  IDataTableColumn,
  IDataTableSelectionChanged,
} from '../../../interfaces/dataTable';

@Component({
  selector: 'app-stock',
  templateUrl: './stock.component.html',
  styleUrls: ['./stock.component.css'],
})
export class StockComponent implements OnInit {
  public stock: Producto[] = [];
  public stockColumns: IDataTableColumn[] = [
    {
      name: 'Producto',
      source: 'nombreProducto',
    },
    {
      name: 'Precio',
      source: 'valor',
      pipe: CurrencyPipe,
      editable: true,
    },
    {
      name: 'Stock disponible',
      source: 'cantidad',
    },
    {
      name: 'Fecha de ingreso',
      source: 'fechaIng',
      pipe: DatePipe,
    },
  ];

  @ViewChild('tablaStock') tablaStock: DataTableComponent;
  @ViewChild('modal') modal: ModalComponent;

  constructor(private ps: ProductosService) {}

  ngOnInit(): void {
    this.cargarProductos();
  }

  cargarProductos(): void {
    this.ps.cargarProductos().subscribe((products) => {
      this.stock = [];
      products.forEach((p) => {
        if (p.idProducto === 1) {
          console.log(p);
        }
        this.stock.push(
          new Producto(
            p.idProducto,
            p.nombreProducto,
            p.valor,
            p.costo,
            p.cantidad,
            p.idTipoProd,
            p.tipoProd,
            p.fechaIng,
            p.barCode,
            p.imageURL
          )
        );
      });
    });
  }

  onSearchDone(e: SearchResult) {
    if (e.result) {
      this.tablaStock.selectedIndex = this.stock.findIndex(
        (p) => p.idProducto === e.result.idProducto
      );
    } else {
      this.tablaStock.selectedIndex = -1;
    }
  }

  openModal(): void {
    this.modal.isOpen = true;
  }

  onAgregarProducto(): void {
    this.tablaStock.selectedIndex = -1;
    this.openModal();
  }

  onModalProductoSubmit(producto: ProductoModel): void {
    console.log(producto);
    let action = () => this.cargarProductos();
    producto.idProducto
      ? this.ps.modificar(producto).subscribe(action)
      : this.ps.agregar(producto).subscribe(action);
    this.modal.closeModal();
  }
}
