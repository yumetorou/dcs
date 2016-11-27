import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import "../../rxjs-extensions";
import {CustomerService} from "../customer.service";

@Component({
    templateUrl: 'app/customer/view/form.html',
    providers: [CustomerService]
})
export class CustomerFormComponent implements OnInit {

    public customer:any = {};

    constructor(public router:Router, private customerService:CustomerService) {
    }

    ngOnInit() {
        console.log('Customer Form');
    }

    onSubmit() {
        this.customerService.saveCustomer(this.customer).subscribe(
            result => {
                console.log('Saved');
            },
            error => {
                console.log('Not Saved');
            }
        )
    }

}
