import {Injectable} from "@angular/core";
import {Http, Response, Headers, RequestOptions, URLSearchParams} from "@angular/http";
import {Family} from "../models/Family";


import {Observable} from "rxjs/Observable";
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

@Injectable()
export class FamilyService {

    constructor(private http: Http){}

    createFamilyUrl = "http://localhost:8080/family/create";
    getFamilyUrl = "http://localhost:8080/family/get/";
    getMembersUrl = "http://localhost:8080/family/members/get/";

    createFamily(family : Family): Observable<Response> {
        let body = JSON.stringify(family);
        console.log(body);
        let headers = new Headers({'Content-Type': 'application/json'});
        let options = new RequestOptions({headers: headers});

        return this.http.post(this.createFamilyUrl, body, options)
            //.map(this.extractData)
            .catch(this.handleError);

    }


    getFamily(familyId : string): Observable<Response> {
        return this.http.get(this.getFamilyUrl + familyId)
        //.map(this.extractData)
            .catch(this.handleError);

    }

    getMembers(familyId : string): Observable<Response> {
        return this.http.get(this.getMembersUrl + familyId)
        //.map(this.extractData)
            .catch(this.handleError);

    }

    private extractData(res: Response) {
        console.log("body" + res.json().id + " : hehe : " + res.json().firstName);
        let body = res.json();
        return body.data || { };
    }

    private handleError (error: Response | any) {
        // In a real world app, we might use a remote logging infrastructure
        let errMsg: string;
        if (error instanceof Response) {
            const body = error.json() || '';
            const err = body.error || JSON.stringify(body);
            errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
        } else {
            errMsg = error.message ? error.message : error.toString();
        }
        console.error(errMsg);
        return Observable.throw(errMsg);
    }
}