import {Component, OnInit} from "@angular/core";
import {Router, ActivatedRoute} from "@angular/router";
import "../../rxjs-extensions";
import {InventoryService} from "../inventory.service";

@Component({
    templateUrl: 'app/inventory/view/form.html',
    providers: [InventoryService]
})
export class InventoryFormComponent implements OnInit {
    public item:any = {};

    constructor(public router:Router, private inventoryService:InventoryService, private route:ActivatedRoute) {
    }

    ngOnInit() {
        this.route.params.subscribe(params => {
            let id = +params['id'];
            if (id) {
                this.inventoryService.getItem(id).subscribe(item => {
                    this.item = item;
                });
            }
        });
        console.log('Inventory Form');
    }

    onSubmit() {
        this.inventoryService.saveItem(this.item).subscribe(
            result => {
                console.log('saved');
            },
            error=> {
                console.log('error');
            }
        );
        console.log(this.item);
    }

}
