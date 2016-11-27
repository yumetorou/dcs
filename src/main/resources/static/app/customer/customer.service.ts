import {Injectable} from "@angular/core";
import {Http, Response, URLSearchParams} from "@angular/http";
import {Observable} from "rxjs/Observable";
import {contentHeaders} from "../core/common/headers";

@Injectable()
export class CustomerService {

    private customerUrl = '/customer';

    constructor(private http:Http) {
    }

    saveCustomer(customer:any):Observable<any> {
        return this.http.post(this.customerUrl, customer, {
            headers: contentHeaders
        }).map(this.extractData).catch(this.handleError);
    }

    getCustomers(paging:any):Observable<any> {
        let page:URLSearchParams = new URLSearchParams();
        page.set('page', paging.page);
        page.set('size', paging.size);
        // page.set('term', paging.term);

        return this.http.get(this.customerUrl, {
            search: page,
            headers: contentHeaders,
        }).map(this.extractData).catch(this.handleError);
    }

    getCustomer(id:number):Observable<any> {
        return this.http.get(this.customerUrl + '/' + id, {
            headers: contentHeaders,
        }).map(this.extractData).catch(this.handleError);
    }

    private extractData(res:Response) {
        let body = res.json();
        return body || {};
    }

    private handleError(error:any) {
        let errMsg = (error.message) ? error.message :
            error.status ? `${error.status} - ${error.statusText}` : 'Server error';
        console.error(errMsg);
        return Observable.throw(errMsg);
    }
}
