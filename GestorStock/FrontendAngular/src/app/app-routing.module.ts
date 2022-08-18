import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { NoEncontradoComponent } from './components/no-encontrado/no-encontrado.component';
import { QuienesSomosComponent } from './components/quienes-somos/quienes-somos.component';
import { RegistroComponent } from './components/registro/registro.component';
import { TiendaComponent } from './components/tienda/tienda.component';
import { HomeLayoutComponent } from './layouts/home-layout/home-layout.component';
import { DashboardLayoutComponent } from './layouts/dashboard-layout/dashboard-layout.component';
import { VentasComponent } from './components/dashboar/ventas/ventas.component';
import { AdministracionComponent } from './components/dashboar/administracion/administracion.component';
import { StockComponent } from './components/dashboar/stock/stock.component';
import { CajaComponent } from './components/dashboar/caja/caja.component';

const routes: Routes = [
  {path: '', component: HomeLayoutComponent, children: [
    {path: 'login', component: LoginComponent},
    {path: 'registro', component: RegistroComponent},
    {path: 'quienes-somos', component: QuienesSomosComponent},
    {path: 'tienda', component: TiendaComponent},
  ]},
  {path: 'dashboard', component: DashboardLayoutComponent, children: [
    {path: '', component: AdministracionComponent},
    {path: 'ventas', component: VentasComponent},
    {path: 'stock', component: StockComponent},
    {path: 'caja', component: CajaComponent},
  ]},
  {path: '**', component: NoEncontradoComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
