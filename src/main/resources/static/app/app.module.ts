import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {PickListModule, PanelModule, DropdownModule} from "primeng/primeng";
import {AppComponent} from "./app.component";

@NgModule({
    imports: [BrowserModule,
        PickListModule,
        PanelModule,
        DropdownModule],
    bootstrap: [AppComponent],
    declarations: [AppComponent],
})
export class AppModule {
}