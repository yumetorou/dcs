import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import "../../rxjs-extensions";
import {InventoryService} from "../inventory.service";

@Component({
    templateUrl: 'app/inventory/view/list.html',
    providers: [InventoryService],
})
export class InventoryListComponent implements OnInit {
    public data:any[] = [];

    constructor(public router:Router, private inventoryService:InventoryService) {
    }

    ngOnInit() {
        this.inventoryService.getItems({page: 0, size: 10}).subscribe(
            result => {
                this.data = result.results;
            }
        );
    }

}
