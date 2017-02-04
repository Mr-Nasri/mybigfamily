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
var core_1 = require('@angular/core');
var router_1 = require('@angular/router');
var family_service_1 = require("../services/family.service");
var login_service_1 = require("../services/login.service");
var anchestry_service_1 = require('../services/anchestry.service');
var GetCommonComponent = (function () {
    function GetCommonComponent(route, router, familyService, loginService, anchestryService) {
        var _this = this;
        this.route = route;
        this.router = router;
        this.familyService = familyService;
        this.loginService = loginService;
        this.anchestryService = anchestryService;
        this.result = '';
        this.anchestryService.getCommon().subscribe(function (res) {
            _this.result = res.json();
            console.log(_this.result);
        }, 
        //console.log(result);
        function (err) { console.log('Error : ' + err); });
    }
    GetCommonComponent.prototype.clone = function (object) {
        // hack
        return JSON.parse(JSON.stringify(object));
    };
    GetCommonComponent = __decorate([
        core_1.Component({
            selector: 'getCommon',
            templateUrl: 'app/components/getCommon.component.html',
        }), 
        __metadata('design:paramtypes', [router_1.ActivatedRoute, router_1.Router, family_service_1.FamilyService, login_service_1.LoginService, anchestry_service_1.AnchestryService])
    ], GetCommonComponent);
    return GetCommonComponent;
}());
exports.GetCommonComponent = GetCommonComponent; /**
 * Created by user on 02/02/17.
 */
//# sourceMappingURL=getCommon.component.js.map