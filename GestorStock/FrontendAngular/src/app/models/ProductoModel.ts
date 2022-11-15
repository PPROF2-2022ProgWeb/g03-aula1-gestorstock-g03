export class ProductoModel {
  constructor(
    public idProducto: number | null,
    public nombreProducto: string, //
    public valor: number, //
    public costo: number, // no usado
    public cantidad: number, //
    public idTipoProd: number, //
    public tipoProd: string, //
    public fechaIng: string | null, //
    public barCode?: string, //
    public imageURL?: string //
  ) {}
}

export class Producto extends ProductoModel {
  public get fechaIngreso(): string {
    return this.fechaIng?.split('T')[0] || 'No registrada';
  }
}
