import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeLayoutComponent } from './layouts/home-layout/home-layout.component';
import { DashboardLayoutComponent } from './layouts/dashboard-layout/dashboard-layout.component';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { RegistroComponent } from './pages/registro/registro.component';
import { QuienesSomosComponent } from './pages/quienes-somos/quienes-somos.component';
import { TiendaComponent } from './pages/tienda/tienda-home/tienda-home.component';
import { NoEncontradoComponent } from './pages/no-encontrado/no-encontrado.component';
import { AdministracionComponent } from './pages/dashboard/administracion/administracion.component';
import { VentasComponent } from './pages/dashboard/ventas/ventas.component';
import { StockComponent } from './pages/dashboard/stock/stock.component';
import { CajaComponent } from './pages/dashboard/caja/caja.component';
import { DetallesProductoComponent } from './pages/tienda/detalles-producto/detalles-producto.component';
import { TiendaCarritoComponent } from './pages/tienda/tienda-carrito/tienda-carrito.component';
import { CheckoutComponent } from './pages/tienda/checkout/checkout.component';
import { TiendaLayoutsComponent } from './layouts/tienda-layouts/tienda-layouts.component';
import { ListadoProductosComponent } from './pages/tienda/listado-productos/listado-productos.component';
import { SellerGuard } from './guards/seller.guard';

const routes: Routes = [
  {path: '', component: HomeLayoutComponent, children: [
    {path: '', component: HomeComponent},
    {path: 'login', component: LoginComponent},
    {path: 'registro', component: RegistroComponent},
    {path: 'quienes-somos', component: QuienesSomosComponent},
  ]},
  {path: 'dashboard', canActivate: [SellerGuard], component: DashboardLayoutComponent, children: [
    {path: '', component: AdministracionComponent},
    {path: 'ventas', component: VentasComponent},
    {path: 'stock', component: StockComponent},
    {path: 'caja', component: CajaComponent},
  ]},
  {path: 'tienda', component: TiendaLayoutsComponent, children: [
    {path: '', component: TiendaComponent},
    {path: 'producto/:id', component: DetallesProductoComponent},
    {path: 'carrito', component: TiendaCarritoComponent},
    {path: 'checkout', component: CheckoutComponent},
    {path: 'listado', component: ListadoProductosComponent},
    {path: 'listado/:busqueda', component: ListadoProductosComponent},
  ]},
  {path: '**', component: NoEncontradoComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
