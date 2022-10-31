export class ProductoModel {
  constructor(
    public idProducto: number,
    public nombreProducto: string,
    public valor: number,
    public costo: number,
    public cantidad: number,
    public idTipoProd: number,
    public tipoProd: string,
    public fechaIng: string,
    public barCode?: string,
    public imageUrl?: string
  ) {}
}

export class Producto extends ProductoModel {
  public get fechaIngreso(): string {
    return this.fechaIng?.split('T')[0] || 'No registrada';
  }
}
