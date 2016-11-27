import "./rxjs-extensions";
import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {FormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";
import {AppRoutingModule} from "./app-routing.module";
import {AppComponent} from "./app.component";
import {LoginComponent} from "./core/login.component";
import {DashboardComponent} from "./core/dashboard.component";
import {SecurityService} from "./core/service/security.service";
import {AuthenticationService} from "./core/service/authentication.service";
import {CustomerModule} from "./customer/customer.module";
import {DeliveryModule} from "./delivery/delivery.module";
import {InventoryModule} from "./inventory/inventory.module";

@NgModule({
    bootstrap: [AppComponent],
    declarations: [
        AppComponent, LoginComponent, DashboardComponent

    ],
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule,
        AppRoutingModule,
        CustomerModule,
        DeliveryModule,
        InventoryModule
    ],
    providers: [SecurityService, AuthenticationService],
    exports: [BrowserModule, HttpModule]
})
export class AppModule {
}
