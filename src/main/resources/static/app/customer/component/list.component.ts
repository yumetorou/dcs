import {Component, OnInit} from "@angular/core";
import {Router, ActivatedRoute} from "@angular/router";
import "../../rxjs-extensions";
import {CustomerService} from "../customer.service";

@Component({
    templateUrl: 'app/customer/view/list.html',
    providers: [CustomerService]
})
export class CustomerListComponent implements OnInit {
    public data:any[];

    constructor(public router:Router, private customerService:CustomerService, private route:ActivatedRoute) {
    }

    ngOnInit() {

        this.customerService.getCustomers({page: 0, size: 10}).subscribe(
            result => {
                this.data = result.results;
            }
        );
        console.log('Customer List');
    }

}
