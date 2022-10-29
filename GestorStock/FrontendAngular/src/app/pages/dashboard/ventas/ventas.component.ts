import { CurrencyPipe } from '@angular/common';
import {
  Component,
  ViewChild,
} from '@angular/core';
import { DataTableComponent } from 'src/app/components/data-table/data-table.component';
import { ModalComponent } from 'src/app/components/modal/modal.component';
import { SearchbarComponent } from 'src/app/components/searchbar/searchbar.component';
import { ICheckoutStatus } from 'src/app/interfaces/checkout';
import {
  IDataTableColumn,
  IDataTableEditionFinished
} from 'src/app/interfaces/dataTable';
import { SearchResult } from 'src/app/interfaces/searchResult';
import { Producto } from 'src/app/models/Producto';
import { ProductoCarrito } from 'src/app/models/ProductoCarrito';
import { VentasService } from 'src/app/services/ventas.service';
import { Productos } from 'src/app/utils/data/productos';
import { Iconos } from 'src/app/utils/iconos.enum';
@Component({
  selector: 'app-ventas',
  templateUrl: './ventas.component.html',
  styleUrls: ['./ventas.component.css'],
})
export class VentasComponent {
  public iconos = Iconos;
  private _productosCarrito: ProductoCarrito[];

  public productos: Producto[] = [];
  public searchResult: any[] = [];
  public searchValue: string;
  // public productoStockSeleccionado: Producto | undefined;
  public addToCartEnabled: boolean = false;

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
      editable: true,
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
    return this.productosCarrito.reduce((acc, curr) => acc + curr.total, 0);
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

  @ViewChild('searchbar') searchBar: SearchbarComponent;
  @ViewChild('modal') modal: ModalComponent;
  @ViewChild('cart') cart: DataTableComponent;
  @ViewChild('stock') tablaStock: DataTableComponent;
  @ViewChild('modalFinalizarVenta') modalFinalizarVenta: ModalComponent;

  constructor(private vs: VentasService) {
    this.productos = Productos;
  }

  onSearchDone(e: SearchResult): void {
    this.searchValue = e.value;
    this.searchResult = e.result || [];
  }

  addToCart(producto: Producto | undefined): void {
    if (!producto) return;
    let existent = this.productosCarrito.find( p => p.producto.id === producto.id)
    if(existent) {
      existent.cantidad++;
      this.editCartProduct(existent)
    }
    else {
      let toAdd = new ProductoCarrito(producto, 1);
      this.productosCarrito = [...this.productosCarrito, toAdd];
    }
    this.searchResult = [];
    this.searchValue = '';
    this.searchBar.reset();
    this.addToCartEnabled = false;
  }

  enableButton(): void {
    this.addToCartEnabled = true;
  }

  eliminarDelCarrito(item: ProductoCarrito): void {
    this.productosCarrito = this.productosCarrito.filter(
      (x: ProductoCarrito) => x !== item
    );
  }

  cartEditionFinished(e: IDataTableEditionFinished): void {
    this.editCartProduct(e.item);
  }

  editCartProduct(item: ProductoCarrito): void {
    let index = this.productosCarrito.findIndex((p) => p === item);
    if (index > -1) {
      this.productosCarrito[index] = item;
      this.productosCarrito = [...this.productosCarrito];
    }
  }

  onEditCartProductSubmit(e: ProductoCarrito): void {
    this.editCartProduct(e);
    this.modal.isOpen = false;
    this.cart.selectedIndex = 1;
    // this.cart.selectedItem = null;
    // console.log();
  }

  openModal() {
    this.modal.isOpen = true;
  }

  onCheckoutFinish(e: ICheckoutStatus): void {
    console.log(e);
    this.modalFinalizarVenta.isOpen = false;
    if(e.state === 'success') {
      this.vs.doSell(e.products);
      this.productosCarrito = [];
    }
  }

  test(e: string): void {
    console.log(e);
  }
}
