import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {HttpModule} from "@angular/http";
import {FormsModule} from "@angular/forms";
import {RouterModule} from "@angular/router";
import {CustomerFormComponent} from "./component/form.component";
import {CustomerListComponent} from "./component/list.component";
import {CustomerViewComponent} from "./component/view.component";
import {DataTableModule} from "angular2-datatable";
@NgModule({
    imports: [
        CommonModule,
        HttpModule,
        FormsModule, DataTableModule,
        RouterModule
    ],
    declarations: [
        CustomerFormComponent,
        CustomerListComponent,
        CustomerViewComponent,
    ]
})

export class CustomerModule {
}