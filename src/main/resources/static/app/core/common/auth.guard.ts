import {Injectable} from "@angular/core";
import {Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot} from "@angular/router";
import {SecurityService} from "../service/security.service";

@Injectable()
export class AuthGuard implements CanActivate {
    constructor(private router:Router, private service:SecurityService) {
    }

    canActivate(destination:ActivatedRouteSnapshot,
                state:RouterStateSnapshot) {
        return true;
    }
}
