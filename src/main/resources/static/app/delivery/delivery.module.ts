import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {HttpModule} from "@angular/http";
import {FormsModule} from "@angular/forms";
import {DeliveryFormComponent} from "./component/form.component";
import {DeliveryListComponent} from "./component/list.component";
import {DeliveryViewComponent} from "./component/view.component";
import {RouterModule} from "@angular/router";
import {DataTableModule} from "angular2-datatable";


@NgModule({
    imports: [
        CommonModule,
        HttpModule,
        FormsModule,
        RouterModule,
        DataTableModule
    ],
    declarations: [
        DeliveryFormComponent,
        DeliveryListComponent,
        DeliveryViewComponent
    ]
})

export class DeliveryModule {
}