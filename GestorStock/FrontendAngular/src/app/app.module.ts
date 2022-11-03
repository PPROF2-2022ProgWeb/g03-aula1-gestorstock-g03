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
import { TiendaComponent } from './pages/tienda/tienda-home/tienda.component';
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
import { ReactiveFormsModule } from '@angular/forms';
import { LoginService } from './services/login.service';
import { InputContainerComponent } from './components/input-container/input-container.component';
import { RegistroService } from './services/registro.service';
import { DataCounterComponent } from './components/data-counter/data-counter.component';
import { AvisoComponent } from './components/utils/aviso/aviso.component';
import { ModalComponent } from './components/modal/modal.component';
import { DataTableComponent } from './components/data-table/data-table.component';
import { DynamicPipe } from './pipes/dynamic.pipe';
import { CurrencyPipe, PercentPipe } from '@angular/common';
import { SearchbarComponent } from './components/searchbar/searchbar.component';
import { FmEdicCartProductComponent } from './components/forms/fm-editCartProduct/fm-editCartProduct.component';
import { TextInputComponent } from './components/form-controls/text-input/text-input.component';
import { NumberInputComponent } from './components/form-controls/number-input/number-input.component';
import { SelectionGroupComponent } from './components/selection-group/selection-group.component';
import { SelectionItemComponent } from './components/selection-group/selection-item/selection-item.component';
import { VentasCheckoutComponent } from './components/ventas-checkout/ventas-checkout.component';
import { SelectInputComponent } from './components/form-controls/select-input/select-input.component';
import { ProductosService } from './services/productos.service';
import { HttpClientModule } from '@angular/common/http';
import { ListadoProductosComponent } from './pages/tienda/listado-productos/listado-productos.component';
import { HeaderComponent } from './components/tienda/header/header.component';
import { HeroProductComponent } from './components/tienda/hero-product/hero-product.component';
import { ProductCardComponent } from './components/tienda/product-card/product-card.component';
import { CategoriesComponent } from './components/tienda/categories/categories.component';
import { CardDetailProductComponent } from './components/tienda/card-detail-product/card-detail-product.component';

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
    InputContainerComponent,
    DataCounterComponent,
    AvisoComponent,
    ModalComponent,
    DataTableComponent,
    DynamicPipe,
    SearchbarComponent,
    FmEdicCartProductComponent,
    TextInputComponent,
    NumberInputComponent,
    SelectionGroupComponent,
    SelectionItemComponent,
    VentasCheckoutComponent,
    SelectInputComponent,
    ListadoProductosComponent,
    HeaderComponent,
    HeroProductComponent,
    ProductCardComponent,
    CategoriesComponent,
    CardDetailProductComponent,
  ],
  imports: [BrowserModule, AppRoutingModule, SvgModule, ReactiveFormsModule, HttpClientModule],
  providers: [LoginService, RegistroService, ProductosService, CurrencyPipe, PercentPipe],
  bootstrap: [AppComponent],
})
export class AppModule {}
