import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FooterComponent } from './components/footer/footer.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { InputComponent } from './components/form-controls/input/input.component';
import { HomeComponent } from './components/home/home.component';
import { QuienesSomosComponent } from './components/quienes-somos/quienes-somos.component';
import { LoginComponent } from './components/login/login.component';
import { RegistroComponent } from './components/registro/registro.component';
import { TiendaComponent } from './components/tienda/tienda.component';
import { NoEncontradoComponent } from './components/no-encontrado/no-encontrado.component';
import { TextareaComponent } from './components/form-controls/textarea/textarea.component';
import { HomeLayoutComponent } from './layouts/home-layout/home-layout.component';
import { DashboardLayoutComponent } from './layouts/dashboard-layout/dashboard-layout.component';
import { VentasComponent } from './components/dashboar/ventas/ventas.component';
import { StockComponent } from './components/dashboar/stock/stock.component';
import { CajaComponent } from './components/dashboar/caja/caja.component';
import { AdministracionComponent } from './components/dashboar/administracion/administracion.component';

@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    NavbarComponent,
    HomeComponent,
    QuienesSomosComponent,
    LoginComponent,
    RegistroComponent,
    TiendaComponent,
    NoEncontradoComponent,
    InputComponent,
    TextareaComponent,
    HomeLayoutComponent,
    DashboardLayoutComponent,
    VentasComponent,
    StockComponent,
    CajaComponent,
    AdministracionComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
