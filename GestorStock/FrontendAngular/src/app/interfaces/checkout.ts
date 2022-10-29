import { ProductoCarrito } from "../models/ProductoCarrito";

export interface ICheckoutStatus {
  state: 'success' | 'canceled' | 'pending';
  amount: number;
  products: ProductoCarrito[];
  paymentType: string;
}