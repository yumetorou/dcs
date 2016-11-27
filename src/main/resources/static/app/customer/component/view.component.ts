import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import "../../rxjs-extensions";

@Component({
    templateUrl: 'app/customer/view/view.html'
})
export class CustomerViewComponent implements OnInit {
    constructor(public router:Router) {
    }

    ngOnInit() {
        console.log('Customer View');
    }

}
