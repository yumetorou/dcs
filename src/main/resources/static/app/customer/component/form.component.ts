import {Component, OnInit} from "@angular/core";
import "../../rxjs-extensions";
import {CustomerService} from "../customer.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
    templateUrl: 'app/customer/view/form.html',
    providers: [CustomerService]
})
export class CustomerFormComponent implements OnInit {

    public customer:any = {};

    constructor(public router:Router, private customerService:CustomerService, private route:ActivatedRoute) {
    }

    ngOnInit() {
        this.route.params.subscribe(params => {
            let id = +params['id'];
            this.customerService.getCustomer(id).subscribe(customer => {
                this.customer = customer;
            });
        });

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
