import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import "../../rxjs-extensions";

@Component({
    templateUrl: 'app/inventory/view/list.html'
})
export class InventoryListComponent implements OnInit {
    constructor(public router:Router) {
    }

    ngOnInit() {
        console.log('Inventory List');
    }

}
