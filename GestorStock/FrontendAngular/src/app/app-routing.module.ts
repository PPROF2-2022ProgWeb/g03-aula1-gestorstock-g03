import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeLayoutComponent } from './layouts/home-layout/home-layout.component';
import { DashboardLayoutComponent } from './layouts/dashboard-layout/dashboard-layout.component';
import { VentasComponent } from './components/dashboard/ventas/ventas.component';
import { AdministracionComponent } from './components/dashboard/administracion/administracion.component';
import { StockComponent } from './components/dashboard/stock/stock.component';
import { CajaComponent } from './components/dashboard/caja/caja.component';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { RegistroComponent } from './pages/registro/registro.component';
import { QuienesSomosComponent } from './pages/quienes-somos/quienes-somos.component';
import { TiendaComponent } from './pages/tienda/tienda.component';
import { NoEncontradoComponent } from './pages/no-encontrado/no-encontrado.component';

const routes: Routes = [
  {path: '', component: HomeLayoutComponent, children: [
    {path: '', component: HomeComponent},
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
