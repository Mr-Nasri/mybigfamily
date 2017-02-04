"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
/**
 * Created by ahmed on 30/01/17.
 */
var core_1 = require("@angular/core");
var http_1 = require("@angular/http");
require('rxjs/add/operator/toPromise');
var Observable_1 = require("rxjs/Observable");
require('rxjs/add/operator/map');
require('rxjs/add/operator/catch');
var AnchestryService = (function () {
    function AnchestryService(http) {
        this.http = http;
        this.getAnchestryMembers = "http://localhost:8080/anchestry/achestres";
        this.getAnchestryByName = "http://localhost:8080/anchestry/get";
        this.getCommpn = "http://localhost:8080/anchestry/get/couples";
    }
    AnchestryService.prototype.getAnchestry = function () {
        return this.http.get(this.getAnchestryMembers)
            .catch(this.handleError);
    };
    AnchestryService.prototype.getAnchestreByName = function (name) {
        return this.http.get(this.getAnchestryByName + '/' + name)
            .catch(this.handleError);
        //.map(this.extractData)
    };
    AnchestryService.prototype.getCommon = function () {
        return this.http.get(this.getCommpn)
            .catch(this.handleError);
    };
    AnchestryService.prototype.extractData = function (res) {
        console.log("body" + res.json().firstName + " : hehe : " + res.json().familyName);
        var body = res.json();
        return body.data || {};
    };
    AnchestryService.prototype.handleError = function (error) {
        // In a real world app, we might use a remote logging infrastructure
        var errMsg;
        if (error instanceof http_1.Response) {
            var body = error.json() || '';
            var err = body.error || JSON.stringify(body);
            errMsg = error.status + " - " + (error.statusText || '') + " " + err;
        }
        else {
            errMsg = error.message ? error.message : error.toString();
        }
        console.error(errMsg);
        return Observable_1.Observable.throw(errMsg);
    };
    AnchestryService.prototype.clone = function (object) {
        // hack
        return JSON.parse(JSON.stringify(object));
    };
    AnchestryService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [http_1.Http])
    ], AnchestryService);
    return AnchestryService;
}());
exports.AnchestryService = AnchestryService;
//# sourceMappingURL=anchestry.service.js.map