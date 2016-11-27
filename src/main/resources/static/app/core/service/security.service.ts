import {Injectable} from "@angular/core";
import "../../rxjs-extensions";

@Injectable()
export class SecurityService {
    currentUser:any = JSON.parse(localStorage.getItem('currentUser'));
    authorities:Array<any> = localStorage.getItem('currentUser') ? JSON.parse(localStorage.getItem('currentUser')).authorities : [];
    isAuthenticated:boolean = false;

    constructor() {
    }

    setCurrentUser(user:any) {
        localStorage.setItem('currentUser', JSON.stringify(user));
        this.currentUser = user;
    }

    setIsAuthenticated(user:any) {
        if (user != null) {
            this.isAuthenticated = true;
        } else {
            this.isAuthenticated = false;
        }
    }
}
