import { CurrencyPipe } from '@angular/common';
import {
  Component,
  ElementRef,
  OnInit,
  ViewChild,
  ViewChildren,
} from '@angular/core';
import { DataTableComponent } from 'src/app/components/data-table/data-table.component';
import { SearchbarComponent } from 'src/app/components/searchbar/searchbar.component';
import {
  IDataTableColumn,
  IDataTableEditionFinished,
  IDataTableSelectionChanged,
} from 'src/app/interfaces/dataTable';
import { SearchResult } from 'src/app/interfaces/searchResult';
import { Producto } from 'src/app/models/Producto';
import { ProductoCarrito } from 'src/app/models/ProductoCarrito';
import { Productos } from 'src/app/utils/data/productos';
@Component({
  selector: 'app-ventas',
  templateUrl: './ventas.component.html',
  styleUrls: ['./ventas.component.css'],
})
export class VentasComponent implements OnInit {
  private _productosCarrito: ProductoCarrito[];

  public productos: Producto[] = [];
  public searchResult: any[] = [];
  public searchValue: string;
  public productoStockSeleccionado: Producto | undefined;

  public cartColumns: IDataTableColumn[] = [
    {
      name: 'Producto',
      source: 'nombre',
    },
    {
      name: 'Precio Unitario',
      source: 'precioUnitario',
      pipe: CurrencyPipe,
      type: 'number',
    },
    {
      name: 'Unidades',
      source: 'cantidad',
      editable: true
    },
    {
      name: 'Total',
      source: 'total',
      pipe: CurrencyPipe,
    },
  ];

  public stockColumns: IDataTableColumn[] = [
    {
      name: 'Producto',
      source: 'name',
    },
    {
      name: 'Precio',
      source: 'price',
      pipe: CurrencyPipe,
    },
  ];

  get totalCarrito(): number {
    return this.productosCarrito.reduce((acc, curr) => acc + curr.total, 0)
  }

  get productosCarrito(): ProductoCarrito[] {
    if (!this._productosCarrito) {
      this._productosCarrito = [];
      let carrito = sessionStorage.getItem('carrito');
      if (carrito) {
        let productos = JSON.parse(carrito) as ProductoCarrito[];
        productos.forEach(({ producto, cantidad }) => {
          let p = new ProductoCarrito(producto, cantidad);
          this._productosCarrito.push(p);
        });
      }
    }
    return this._productosCarrito;
  }

  set productosCarrito(value: ProductoCarrito[]) {
    this._productosCarrito = value;
    sessionStorage.setItem('carrito', JSON.stringify(value));
  }

  @ViewChild('contenedorTabla') contenedorTabla: ElementRef;
  @ViewChild('searchbar') searchBar: SearchbarComponent;

  constructor() {
    this.productos = Productos;
  }

  ngOnInit(): void {}

  onSearchDone(e: SearchResult): void {
    this.searchValue = e.value;
    this.searchResult = e.result || [];
  }

  addToCart(producto: Producto | undefined): void {
    if (!producto) return;
    let toAdd = new ProductoCarrito(producto, 1);
    this.productosCarrito = [...this.productosCarrito, toAdd];
    this.searchResult = [];
    this.searchValue = '';
    this.productoStockSeleccionado = undefined;
    this.searchBar.reset();
  }

  enableButton(): boolean {
    return this.productoStockSeleccionado ? true : false;
  }

  stockSelectionChanged(e: IDataTableSelectionChanged): void {
    this.productoStockSeleccionado = e.current?.item;
  }
  
  eliminarDelCarrito(item: ProductoCarrito): void {
    this.productosCarrito = this.productosCarrito.filter((x: ProductoCarrito) => x !== item)
  }

  cartEditionFinished(e: IDataTableEditionFinished): void {
    this.editCartProduct(e.item)
  }

  editCartProduct(item: ProductoCarrito): void{
    let index = this.productosCarrito.findIndex(p => p === item);
    if (index > -1) {
      this.productosCarrito[index] = item
      this.productosCarrito = [...this.productosCarrito]
    }
  }
}
