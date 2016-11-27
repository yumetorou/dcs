import {Injectable} from "@angular/core";
import {contentHeadersFormData} from "../common/headers-form-data";
import {contentHeaders} from "../common/headers";
import {Observable} from "rxjs/Observable";
import {Http, Response, URLSearchParams} from "@angular/http";

@Injectable()
export class AuthenticationService {

    constructor(private http:Http) {
    }

    login(username, password):Observable<any> {
        let body = 'username=' + username + '&password=' + password;
        return this.http.post('/login', body, {
            headers: contentHeadersFormData
        });

    }

    logout():Observable<any> {
        return this.http.post('/logout',{},{headers: contentHeaders});
    }

    currentUser():Observable<any> {
        return this.http.get('/currentUser', {
            headers: contentHeaders
        }).map(this.extractData).catch(this.handleError);
    }

    private extractData(res:Response) {
        let body = res.json();
        return body || {};
    }

    private handleError(error:any) {
        let errMsg = (error.message) ? error.message :
            error.status ? `${error.status} - ${error.statusText}` : 'Server error';
        return Observable.throw(errMsg);
    }
}
