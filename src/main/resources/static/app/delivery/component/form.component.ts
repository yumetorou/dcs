import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import "../../rxjs-extensions";

@Component({
    templateUrl: 'app/delivery/view/form.html'
})
export class DeliveryFormComponent implements OnInit {
    public delivery:any = {};
    constructor(public router:Router) {
    }

    ngOnInit() {
        console.log('Delivery Form');
    }

    onSubmit(){
        console.log(this.delivery);
    }

}
