/**
 * Created by ahmed on 30/01/17.
 */
import {Injectable} from "@angular/core";
import {Http, Response, Headers, RequestOptions, URLSearchParams} from "@angular/http";
import {Family} from "../models/Family";

import 'rxjs/add/operator/toPromise';
import {Observable} from "rxjs/Observable";
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import {Member} from "../models/Member";

@Injectable()
export class AnchestryService {

    constructor(private http: Http){}

    getAnchestryMembers = "http://localhost:8080/anchestry/achestres";
    getAnchestryByName = "http://localhost:8080/anchestry/get";
    getCommpn = "http://localhost:8080/anchestry/get/couples";


    getAnchestry(): Observable<Response> {
        return this.http.get(this.getAnchestryMembers)
        //.map(this.extractData)

            .catch(this.handleError);

    }

    getAnchestreByName( name : string): Observable<Response> {

         return this.http.get(this.getAnchestryByName + '/'+ name)

             .catch(this.handleError);


         //.map(this.extractData)


    }

    getCommon(): Observable<Response> {
        return this.http.get(this.getCommpn )
           // .map(this.extractData)
            .catch(this.handleError);

    }

    private extractData(res: Response) {
        console.log("body" + res.json().firstName + " : hehe : " + res.json().familyName);
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
    private clone(object: any){
        // hack
        return JSON.parse(JSON.stringify(object));
    }
}