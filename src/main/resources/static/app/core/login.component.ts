import {Component} from "@angular/core";
import {Router} from "@angular/router";
import "../rxjs-extensions";
import {AuthenticationService} from "./service/authentication.service";
import {SecurityService} from "./service/security.service";
@Component({
    selector: 'login',
    templateUrl: 'app/core/view/login.html',
    providers:[AuthenticationService]
})
export class LoginComponent {
    constructor(public router:Router,
                private authenticationService:AuthenticationService,
    private securityService:SecurityService) {
    }

    login(event, username, password) {
        event.preventDefault();
        this.authenticationService.login(username, password).subscribe(
            response => {
                this.setUser();
                console.log('logged in');
            },
            error => {
                alert(error);
            }
        );
    }

    setUser() {
        this.authenticationService.currentUser().subscribe(
            user => {
                this.securityService.setCurrentUser(user);
                this.securityService.setIsAuthenticated(user);
            }
        )
    }

}
