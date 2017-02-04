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
var login_service_1 = require("../services/login.service");
var core_1 = require("@angular/core");
var router_1 = require("@angular/router");
var anchestry_service_1 = require('../services/anchestry.service');
var GetAnchestryComponent = (function () {
    function GetAnchestryComponent(route, router, loginService, anchestryService) {
        this.route = route;
        this.router = router;
        this.loginService = loginService;
        this.anchestryService = anchestryService;
    }
    GetAnchestryComponent = __decorate([
        core_1.Component({
            selector: 'get-anchestry',
            templateUrl: 'app/components/getAnchestry.component.html',
        }), 
        __metadata('design:paramtypes', [router_1.ActivatedRoute, router_1.Router, login_service_1.LoginService, anchestry_service_1.AnchestryService])
    ], GetAnchestryComponent);
    return GetAnchestryComponent;
}());
exports.GetAnchestryComponent = GetAnchestryComponent; /**
 * Created by ahmed on 30/01/17.
 */
//# sourceMappingURL=getAnchestry.component.js.map