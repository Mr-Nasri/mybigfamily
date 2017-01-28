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
var GetFamilyComponent = (function () {
    function GetFamilyComponent(route, router, familyService, loginService) {
        this.route = route;
        this.router = router;
        this.familyService = familyService;
        this.loginService = loginService;
        this.familyId = '';
        this.result = '';
    }
    GetFamilyComponent.prototype.onSubmit = function (event) {
        var _this = this;
        event.preventDefault();
        console.log(this.familyId);
        this.familyService.getFamily(this.familyId).subscribe(function (data) {
            _this.result = data.text();
            if (_this.result == "ok") {
                console.log(_this.result);
                _this.loginService.login(_this.familyId);
                _this.router.navigate(['addMember']);
            }
            else {
                _this.result = "Family not found, please try again !";
            }
        }, function (err) { console.log('Error : ' + err); });
    };
    GetFamilyComponent = __decorate([
        core_1.Component({
            selector: 'get-family',
            templateUrl: 'app/components/getFamily.component.html',
        }), 
        __metadata('design:paramtypes', [router_1.ActivatedRoute, router_1.Router, family_service_1.FamilyService, login_service_1.LoginService])
    ], GetFamilyComponent);
    return GetFamilyComponent;
}());
exports.GetFamilyComponent = GetFamilyComponent;
//# sourceMappingURL=getFamily.component.js.map