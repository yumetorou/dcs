import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {HttpModule} from "@angular/http";
import {FormsModule} from "@angular/forms";
import {RouterModule} from "@angular/router";
import {InventoryFormComponent} from "./component/form.component";
import {InventoryListComponent} from "./component/list.component";
import {InventoryViewComponent} from "./component/view.component";
import {DataTableModule} from "angular2-datatable";

@NgModule({
    imports: [
        CommonModule,
        HttpModule,
        FormsModule,
        DataTableModule,
        RouterModule
    ],
    declarations: [
        InventoryFormComponent,
        InventoryListComponent,
        InventoryViewComponent
    ]
})

export class InventoryModule {
}