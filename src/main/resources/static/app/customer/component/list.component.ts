import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import "../../rxjs-extensions";
import {CustomerService} from "../customer.service";

@Component({
    templateUrl: 'app/customer/view/list.html',
    providers: [CustomerService]
})
export class CustomerListComponent implements OnInit {
    public data:any[];

    constructor(public router:Router, private customerService:CustomerService) {
    }
    ngOnInit() {

        this.customerService.getCustomers({page:0,size:10}).subscribe(
            result => {
                this.data = result.data;
            }
        );
        console.log('Customer List');
    }

}
