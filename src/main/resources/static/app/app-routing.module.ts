import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {DashboardComponent} from "./core/dashboard.component";
import {CustomerListComponent} from "./customer/component/list.component";
import {CustomerFormComponent} from "./customer/component/form.component";
import {CustomerViewComponent} from "./customer/component/view.component";
import {DeliveryListComponent} from "./delivery/component/list.component";
import {DeliveryFormComponent} from "./delivery/component/form.component";
import {DeliveryViewComponent} from "./delivery/component/view.component";
import {InventoryListComponent} from "./inventory/component/list.component";
import {InventoryFormComponent} from "./inventory/component/form.component";
import {InventoryViewComponent} from "./inventory/component/view.component";


const routes:Routes = [
    {
        path: '',
        component: DashboardComponent,
    },
    {
        path: 'customer',
        children: [
            {
                path: '',
                component: CustomerListComponent
            },
            {
                path: 'create',
                component: CustomerFormComponent
            },
            {
                path: 'update/:id',
                component: CustomerFormComponent
            },
            {
                path: 'view',
                component: CustomerViewComponent
            }
        ]
    },
    {
        path: 'delivery',
        children: [
            {
                path: '',
                component: DeliveryListComponent
            },
            {
                path: 'create',
                component: DeliveryFormComponent
            },
            {
                path: 'update/:id',
                component: DeliveryFormComponent
            },
            {
                path: 'view',
                component: DeliveryViewComponent
            }
        ]
    }
    ,
    {
        path: 'inventory',
        children: [
            {
                path: '',
                component: InventoryListComponent
            },
            {
                path: 'create',
                component: InventoryFormComponent
            },
            {
                path: 'update/:id',
                component: InventoryFormComponent
            },
            {
                path: 'view',
                component: InventoryViewComponent
            }
        ]
    }
];

@NgModule({
    exports: [RouterModule],
    imports: [RouterModule.forRoot(routes, {useHash: true})],
})
export class AppRoutingModule {
}
