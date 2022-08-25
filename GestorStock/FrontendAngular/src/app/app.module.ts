import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FooterComponent } from './components/footer/footer.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { InputComponent } from './components/form-controls/input/input.component';
import { TextareaComponent } from './components/form-controls/textarea/textarea.component';
import { HomeLayoutComponent } from './layouts/home-layout/home-layout.component';
import { DashboardLayoutComponent } from './layouts/dashboard-layout/dashboard-layout.component';
import { HomeComponent } from './pages/home/home.component';
import { QuienesSomosComponent } from './pages/quienes-somos/quienes-somos.component';
import { LoginComponent } from './pages/login/login.component';
import { RegistroComponent } from './pages/registro/registro.component';
import { TiendaComponent } from './pages/tienda/tienda.component';
import { NoEncontradoComponent } from './pages/no-encontrado/no-encontrado.component';
import { AdministracionComponent } from './pages/dashboard/administracion/administracion.component';
import { CajaComponent } from './pages/dashboard/caja/caja.component';
import { StockComponent } from './pages/dashboard/stock/stock.component';
import { VentasComponent } from './pages/dashboard/ventas/ventas.component';
import { NavlinkComponent } from './components/navlink/navlink.component';
import { MenuComponent } from './components/menu/menu.component';
import { SvgModule } from './components/svg/svg.module';
import { IconComponent } from './components/icon/icon.component';
import { ButtonComponent } from './components/utils/button/button.component';

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
    AdministracionComponent,
    NavlinkComponent,
    MenuComponent,
    IconComponent,
    ButtonComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    SvgModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
