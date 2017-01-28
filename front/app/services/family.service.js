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
var core_1 = require("@angular/core");
var http_1 = require("@angular/http");
var Observable_1 = require("rxjs/Observable");
require('rxjs/add/operator/map');
require('rxjs/add/operator/catch');
var FamilyService = (function () {
    function FamilyService(http) {
        this.http = http;
        this.createFamilyUrl = "http://localhost:8080/family/create";
        this.getFamilyUrl = "http://localhost:8080/family/get/";
        this.getMembersUrl = "http://localhost:8080/family/members/get/";
    }
    FamilyService.prototype.createFamily = function (family) {
        var body = JSON.stringify(family);
        console.log(body);
        var headers = new http_1.Headers({ 'Content-Type': 'application/json' });
        var options = new http_1.RequestOptions({ headers: headers });
        return this.http.post(this.createFamilyUrl, body, options)
            .catch(this.handleError);
    };
    FamilyService.prototype.getFamily = function (familyId) {
        return this.http.get(this.getFamilyUrl + familyId)
            .catch(this.handleError);
    };
    FamilyService.prototype.getMembers = function (familyId) {
        return this.http.get(this.getMembersUrl + familyId)
            .catch(this.handleError);
    };
    FamilyService.prototype.extractData = function (res) {
        console.log("body" + res.json().id + " : hehe : " + res.json().firstName);
        var body = res.json();
        return body.data || {};
    };
    FamilyService.prototype.handleError = function (error) {
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
    FamilyService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [http_1.Http])
    ], FamilyService);
    return FamilyService;
}());
exports.FamilyService = FamilyService;
//# sourceMappingURL=family.service.js.map