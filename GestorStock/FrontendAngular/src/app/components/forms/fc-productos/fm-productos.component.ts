import {
  Component,
  EventEmitter,
  Input,
  OnInit,
  Output,
  SimpleChanges,
  ViewChild,
} from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Producto, ProductoModel } from 'src/app/models/ProductoModel';
import { TipoProducto } from 'src/app/models/TipoProducto';
import { ProductosService } from 'src/app/services/productos.service';
import { Iconos } from 'src/app/utils/iconos.enum';

@Component({
  selector: 'fm-productos',
  templateUrl: './fm-productos.component.html',
  styleUrls: ['./fm-productos.component.css'],
})
export class FmProductosComponent implements OnInit {
  @Input() producto: Producto | undefined;

  @Output('onSubmit') submit = new EventEmitter<ProductoModel>();
  @Output('onCancel') cancel = new EventEmitter();

  public Iconos = Iconos;
  public categorias: TipoProducto[];

  public get titulo(): string {
    return `${this.producto ? 'Editar' : 'Agregar'} producto`;
  }

  public get imageUrl(): string {
    return <string>this.form.get('imageUrl')?.value;
  }

  public form = new FormGroup({
    nombre: new FormControl('', [Validators.required]),
    imageUrl: new FormControl('', [Validators.required]),
    categoria: new FormControl(''),
    barcode: new FormControl('', [Validators.required]),
    unidades: new FormControl(0, [Validators.required, Validators.min(0)]),
    precio: new FormControl(0, [Validators.required, Validators.min(0)]),
  });

  constructor(private ps: ProductosService) {}

  ngOnInit(): void {
    this.ps.cargarCategorias().subscribe((data) => {
      this.categorias = data;
    });
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['producto']) {
      if (changes['producto'].currentValue) {
        this.setState(changes['producto'].currentValue);
      } else {
        this.form.reset();
      }
    }
  }

  setState(producto: Producto) {
    this.form.get('nombre')?.setValue(producto.nombreProducto);
    this.form.get('imageUrl')?.setValue(producto.imageURL);
    this.form.get('categoria')?.setValue(producto.idTipoProd);
    this.form.get('barcode')?.setValue(producto.barCode);
    this.form.get('unidades')?.setValue(producto.cantidad);
    this.form.get('precio')?.setValue(producto.valor);
  }

  private getTodayDate() {
    return Date.now().toLocaleString();
  }

  onSubmit(): void {
    const { nombre, imageUrl, categoria, barcode, unidades, precio } =
      this.form.value;

    let id = this.producto ? this.producto.idProducto : null;
    let fechaIng = this.producto ? this.producto.fechaIng : null;
    if (this.form.valid) {
      let producto = new Producto(
        id,
        nombre,
        precio,
        precio,
        unidades,
        categoria,
        this.categorias[categoria]?.nombreTipoProd,
        fechaIng,
        barcode,
        imageUrl
      );
      this.submit.emit(producto);
    }
  }

  public reset() {
    if (this.producto) {
      this.setState(this.producto);
    } else this.form.reset();
  }

  onCancel() {
    this.cancel.emit();
    this.reset();
  }
}
