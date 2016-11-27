import {Component, OnInit} from "@angular/core";
import {AuthenticationService} from "./core/service/authentication.service";
import {SecurityService} from "./core/service/security.service";
import {Router} from "@angular/router";
import "./rxjs-extensions";

@Component({
    selector: 'body',
    templateUrl: 'app/core/view/app.html',
    providers:[AuthenticationService]
})
export class AppComponent implements OnInit {
    title = 'Water Factory: Delivery Control System';
    user:any = {};
    constructor(private router:Router,
                private authService:AuthenticationService,
                private securityService:SecurityService) {
    }

    ngOnInit() {
        console.log('app component');
        this.loadCurrent();
    }

    loadCurrent() {
        this.authService.currentUser().subscribe(
            result => {
                this.user = result;
                this.securityService.setCurrentUser(result);
                this.securityService.setIsAuthenticated(result);
            },
            error => {
                this.securityService.setCurrentUser(null);
                this.securityService.setIsAuthenticated(null);
                localStorage.removeItem('currentUser');
            }
        );
    }

    logout() {
        this.authService.logout().subscribe(
            response => {
                this.loadCurrent();
            }
        );
        this.router.navigate(['/']);
    }

    isAuthenticated() {
        return this.securityService.isAuthenticated;
    }

}
