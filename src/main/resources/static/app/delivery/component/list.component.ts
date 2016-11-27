import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import "../../rxjs-extensions";

@Component({
    templateUrl: 'app/delivery/view/list.html'
})
export class DeliveryListComponent implements OnInit {
    constructor(public router:Router) {
    }

    ngOnInit() {
        console.log('Customer List');
    }

}
