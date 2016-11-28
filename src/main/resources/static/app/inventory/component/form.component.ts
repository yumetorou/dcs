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
    public errorMessage:string;
    public successMessage:string;

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
    }

    onSubmit() {
        this.inventoryService.saveItem(this.item).subscribe(
            result => {
                this.successMessage = 'Item code: ' + this.item.code + ' successfully ' + (this.item.id ? 'updated!' : 'saved!');
                this.item = {};
            },
            error=> {
                this.errorMessage = 'Code already exists!';
            }
        );
    }

}
