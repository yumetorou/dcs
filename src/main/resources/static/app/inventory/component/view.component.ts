import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import "../../rxjs-extensions";

@Component({
    templateUrl: 'app/inventory/view/view.html'
})
export class InventoryViewComponent implements OnInit {
    constructor(public router:Router) {
    }

    ngOnInit() {
        console.log('Inventory View');
    }

}
